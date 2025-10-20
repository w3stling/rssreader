package com.apptasticsoftware.rssreader.filter;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

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

        private final Reader reader;
        private final Charset charset;
        private final ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream(1024);
        private byte[] buffer = new byte[0];
        private int bufferPos = 0;

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
        @SuppressWarnings("java:S135")
        private void refillBuffer() throws IOException {
            byteBuffer.reset();
            int c;
            // Read chars until we have some bytes to output or EOF
            while (true) {
                c = reader.read();
                if (c == -1) {
                    break; // EOF
                }
                char ch = (char) c;
                if (isValidXmlChar(ch)) {
                    // Convert char to bytes and write to buffer
                    byte[] bytes = String.valueOf(ch).getBytes(charset);
                    byteBuffer.write(bytes);
                    // After writing at least one char, break to serve bytes first
                    break;
                }
                // else skip invalid char silently
            }

            // Also read more valid chars immediately after to reduce calls
            while (true) {
                c = reader.read();
                if (c == -1) break;
                char ch = (char) c;
                if (isValidXmlChar(ch)) {
                    byte[] bytes = String.valueOf(ch).getBytes(charset);
                    byteBuffer.write(bytes);
                } else {
                    break;
                }
            }

            buffer = byteBuffer.toByteArray();
            bufferPos = 0;
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
