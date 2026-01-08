package com.apptasticsoftware.rssreader.filter;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A {@link FeedFilter} implementation that removes invalid XML characters from the feed stream.
 * <p>
 * This filter wraps the input stream with a {@link StreamingXmlFilterInputStream} to ensure
 * that any characters not allowed in XML 1.0 are filtered out before parsing.
 * </p>
 */
public class InvalidXmlCharacterFilter implements FeedFilter {

    /**
     * Filters the provided XML feed stream by removing invalid XML characters.
     *
     * @param feedStream the input stream of the feed to be filtered
     * @return a filtered input stream
     */
    @Override
    public InputStream filter(InputStream feedStream) {
        return new StreamingXmlFilterInputStream(feedStream);
    }

    /**
     * A streaming {@link InputStream} that wraps an existing XML input stream and filters out
     * any characters that are invalid according to the XML 1.0 specification.
     * <p>
     * This class ensures that the filtered data can be safely passed to an XML parser like
     * {@link javax.xml.stream.XMLStreamReader} without encountering parse errors due to
     * illegal control characters or surrogates.
     * </p>
     *
     * <p><strong>Invalid characters removed:</strong> control characters in the range
     * {@code [\u0000-\u0008]}, {@code [\u000B-\u000C]}, {@code [\u000E-\u001F]},
     * and surrogates and other forbidden code points.</p>
     */
    private static class StreamingXmlFilterInputStream extends InputStream {
        private static final Map<String, String> HTML_ENTITIES;
        private static final String CDATA_START = "<![CDATA[";
        private static final String CDATA_END = "]]>";
        private static final String QUOTE_ESCAPE = "&quot;";

        static {
            HTML_ENTITIES = new HashMap<>();
            // XML predefined entities (must be preserved)
            HTML_ENTITIES.put("amp", "&amp;");
            HTML_ENTITIES.put("lt", "&lt;");
            HTML_ENTITIES.put("gt", "&gt;");
            HTML_ENTITIES.put("apos", "&apos;");
            HTML_ENTITIES.put("quot", QUOTE_ESCAPE);
            // Special quotes and typographic entities
            HTML_ENTITIES.put("ldquo", QUOTE_ESCAPE);
            HTML_ENTITIES.put("rdquo", QUOTE_ESCAPE);
            HTML_ENTITIES.put("rsquo", QUOTE_ESCAPE);
            // Common special characters
            HTML_ENTITIES.put("auml", "&#228;");  // ä
            HTML_ENTITIES.put("ouml", "&#246;");  // ö
            HTML_ENTITIES.put("uuml", "&#252;");  // ü
            HTML_ENTITIES.put("Auml", "&#196;");  // Ä
            HTML_ENTITIES.put("Ouml", "&#214;");  // Ö
            HTML_ENTITIES.put("Uuml", "&#220;");  // Ü
            HTML_ENTITIES.put("aacute", "&#225;"); // á
            HTML_ENTITIES.put("eacute", "&#233;"); // é
            HTML_ENTITIES.put("iacute", "&#237;"); // í
            HTML_ENTITIES.put("oacute", "&#243;"); // ó
            HTML_ENTITIES.put("uacute", "&#250;"); // ú
        }

        private final Reader reader;
        private final Charset charset;
        private final ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream(1024);
        private byte[] buffer = new byte[0];
        private int bufferPos = 0;
        private final StringBuilder entityBuffer = new StringBuilder();
        private final StringBuilder cdataBuffer = new StringBuilder();
        private boolean inEntity = false;
        private boolean inCDATA = false;
        private int cdataMatchPos = 0;
        private int cdataEndMatchPos = 0;
        private boolean inTag = false;
        private boolean inAttributeValue = false;
        private char attributeQuoteChar = '\0';
        private char previousChar = '\0';
        private boolean attributeStartedWithCurlyQuote = false;

        /**
         * Constructs a new {@code StreamingXmlFilterInputStream} with UTF-8 encoding.
         *
         * @param inputStream the original XML {@link InputStream}
         */
        public StreamingXmlFilterInputStream(InputStream inputStream) {
            this(inputStream, StandardCharsets.UTF_8);
        }

        /**
         * Constructs a new {@code StreamingXmlFilterInputStream} with the specified character encoding.
         *
         * @param inputStream the original XML {@link InputStream}
         * @param charset     the character encoding used to decode the XML input
         */
        public StreamingXmlFilterInputStream(InputStream inputStream, Charset charset) {
            this.reader = new InputStreamReader(inputStream, charset);
            this.charset = charset;
        }

        /**
         * Reads the next filtered byte from the stream.
         *
         * @return the next byte of filtered data, or -1 if the end of the stream is reached
         * @throws IOException if an I/O error occurs
         */
        @Override
        public int read() throws IOException {
            if (bufferPos >= buffer.length) {
                refillBuffer();
                if (buffer.length == 0) {
                    return -1; // EOF
                }
            }
            return buffer[bufferPos++] & 0xFF;
        }

        /**
         * Reads up to {@code len} bytes of filtered data into an array of bytes.
         *
         * @param b   the buffer into which the data is read
         * @param off the start offset in the destination array {@code b}
         * @param len the maximum number of bytes to read
         * @return the number of bytes read, or -1 if the end of the stream has been reached
         * @throws IOException if an I/O error occurs
         */
        @Override
        public int read(byte[] b, int off, int len) throws IOException {
            int bytesRead = 0;
            while (len > 0) {
                if (bufferPos >= buffer.length) {
                    refillBuffer();
                    if (buffer.length == 0) {
                        return bytesRead == 0 ? -1 : bytesRead;
                    }
                }
                int toCopy = Math.min(len, buffer.length - bufferPos);
                System.arraycopy(buffer, bufferPos, b, off, toCopy);
                bufferPos += toCopy;
                off += toCopy;
                len -= toCopy;
                bytesRead += toCopy;
            }
            return bytesRead;
        }

        /**
         * Refills the internal byte buffer with the next valid XML characters encoded as bytes.
         * Filters out invalid XML characters as defined by XML 1.0.
         *
         * @throws IOException if an I/O error occurs
         */
        @SuppressWarnings({"java:S135", "java:S3776"})
        private void refillBuffer() throws IOException {
            byteBuffer.reset();
            int c;
            entityBuffer.setLength(0);

            while (true) {
                c = reader.read();
                if (c == -1) {
                    if (inEntity && !inCDATA) {
                        // Write any incomplete entity as-is
                        writeStringToBuffer(entityBuffer.toString());
                    }
                    if (cdataBuffer.length() > 0) {
                        // Write any incomplete CDATA buffer
                        writeStringToBuffer(cdataBuffer.toString());
                    }
                    break; // EOF
                }

                char ch = (char) c;

                // Always filter out invalid XML characters, regardless of context
                if (!isValidXmlChar(ch)) {
                    continue;
                }

                // Check for CDATA section start
                if (!inCDATA && ch == CDATA_START.charAt(cdataMatchPos)) {
                    cdataMatchPos++;
                    cdataBuffer.append(ch);
                    if (cdataMatchPos == CDATA_START.length()) {
                        inCDATA = true;
                        cdataMatchPos = 0;
                        writeStringToBuffer(cdataBuffer.toString());
                        cdataBuffer.setLength(0);
                        continue;
                    }
                } else if (!inCDATA && cdataMatchPos > 0) {
                    // Not a CDATA start, process buffered content character by character, then current char
                    String buffered = cdataBuffer.toString();
                    cdataBuffer.setLength(0);
                    cdataMatchPos = 0;
                    for (int i = 0; i < buffered.length(); i++) {
                        processRegularChar(buffered.charAt(i));
                    }
                    processRegularChar(ch);
                } else if (inCDATA) {
                    // Check for CDATA end
                    if (ch == CDATA_END.charAt(cdataEndMatchPos)) {
                        cdataEndMatchPos++;
                        cdataBuffer.append(ch);
                        if (cdataEndMatchPos == CDATA_END.length()) {
                            inCDATA = false;
                            cdataEndMatchPos = 0;
                            writeStringToBuffer(cdataBuffer.toString());
                            cdataBuffer.setLength(0);
                        }
                    } else {
                        // Character doesn't match the next position in "]]>"
                        // First, write any previously buffered partial "]]>" match
                        if (cdataEndMatchPos > 0) {
                            writeStringToBuffer(cdataBuffer.toString());
                            cdataBuffer.setLength(0);
                            cdataEndMatchPos = 0;
                        }
                        // Now check if current character could start a new "]]>" match
                        if (ch == CDATA_END.charAt(0)) {
                            // Start matching "]]>"
                            cdataEndMatchPos = 1;
                            cdataBuffer.append(ch);
                        } else {
                            // Just a regular character in CDATA
                            writeStringToBuffer(String.valueOf(ch));
                        }
                    }
                } else {
                    processRegularChar(ch);
                }

                if (byteBuffer.size() > 0) {
                    break;
                }
            }

            buffer = byteBuffer.toByteArray();
            bufferPos = 0;
        }

        private void processRegularChar(char ch) throws IOException {
            if (inEntity) {
                processEntity(ch);
            } else if (ch == '&') {
                startEntity(ch);
            } else {
                processNonEntityChar(ch);
            }
        }

        private void startEntity(char ch) {
            inEntity = true;
            entityBuffer.setLength(0);
            entityBuffer.append(ch);
            previousChar = ch;
        }

        private void processEntity(char ch) throws IOException {
            entityBuffer.append(ch);
            if (ch == ';') {
                inEntity = false;
                String entity = entityBuffer.toString();
                if (entity.startsWith("&#")) {
                    // Preserve numeric entities as-is
                    writeStringToBuffer(entity);
                } else {
                    String entityName = entity.substring(1, entity.length() - 1);
                    String replacement = HTML_ENTITIES.get(entityName);
                    // If we don't recognize the entity, write it as-is
                    writeStringToBuffer(Objects.requireNonNullElse(replacement, entity));
                }
                entityBuffer.setLength(0);
            }
            previousChar = ch;
        }

        private void processNonEntityChar(char ch) throws IOException {
            // Determine if we should replace this curly quote
            boolean replaceQuote = false;
            if (isInvalidQuotationMark(ch)) {
                replaceQuote = shouldReplaceQuotationMark(ch);
            }

            updateTagState(ch, replaceQuote);

            // Write the character (replaced or original)
            if (replaceQuote) {
                writeStringToBuffer("\"");
            } else if (isValidXmlChar(ch)) {
                writeStringToBuffer(String.valueOf(ch));
            }

            previousChar = ch;
        }

        private void updateTagState(char ch, boolean replaceQuote) {
            // Track XML tag/processing instruction state (after deciding on quote replacement)
            if (ch == '<') {
                inTag = true;
                inAttributeValue = false;
                attributeQuoteChar = '\0';
            } else if (ch == '>' && !inAttributeValue) {
                // End of tag or processing instruction (> including ?>)
                inTag = false;
                attributeQuoteChar = '\0';
            } else if (inTag) {
                // Track attribute value state
                char effectiveChar = replaceQuote ? '"' : ch;
                if (!inAttributeValue && (effectiveChar == '"' || effectiveChar == '\'')) {
                    // Starting an attribute value
                    inAttributeValue = true;
                    attributeQuoteChar = effectiveChar;
                    attributeStartedWithCurlyQuote = replaceQuote;
                } else if (inAttributeValue && effectiveChar == attributeQuoteChar) {
                    // Ending an attribute value
                    inAttributeValue = false;
                    attributeQuoteChar = '\0';
                }
            }
        }

        /**
         * Determines if a character is a fancy/curly quotation mark that should be replaced
         * with a standard straight quote.
         *
         * @param ch the character to check
         * @return {@code true} if the character is a fancy quotation mark; {@code false} otherwise
         */
        private boolean isInvalidQuotationMark(char ch) {
            // U+201C (left double quotation mark) and U+201D (right double quotation mark)
            return ch == '\u201C' || ch == '\u201D';
        }

        /**
         * Determines if a curly quotation mark should be replaced based on its context.
         * Only replaces curly quotes when they appear at the start or end of an attribute value.
         *
         * @param ch the character to check (should be a curly quote)
         * @return {@code true} if the curly quote should be replaced; {@code false} otherwise
         */
        private boolean shouldReplaceQuotationMark(char ch) {
            if (!inTag) {
                // Not in a tag, preserve the curly quote
                return false;
            }

            // Check if this curly quote is at the boundary of an attribute value
            if (ch == '\u201C') {
                // Left curly quote - replace if previous character was '='
                // This means it's starting an attribute value
                return previousChar == '=';
            } else if (ch == '\u201D') {
                // Right curly quote - replace if we're currently in an attribute value
                // This means it's ending an attribute value
                // We need to check if the attribute was started with a curly quote (converted to straight quote)
                return inAttributeValue && attributeStartedWithCurlyQuote;
            }

            return false;
        }

        private void writeStringToBuffer(String str) throws IOException {
            byte[] bytes = str.getBytes(charset);
            byteBuffer.write(bytes);
        }

        /**
         * Determines whether a character is valid according to the XML 1.0 specification.
         *
         * @param ch the character to check
         * @return {@code true} if the character is valid in XML; {@code false} otherwise
         */
        private boolean isValidXmlChar(char ch) {
            return (ch == 0x9 || ch == 0xA || ch == 0xD ||
                    (ch >= 0x20 && ch <= 0xD7FF) ||
                    (ch >= 0xE000 && ch <= 0xFFFD));
        }

        /**
         * Closes the underlying {@link Reader}.
         *
         * @throws IOException if an I/O error occurs
         */
        @Override
        public void close() throws IOException {
            reader.close();
        }
    }
}
