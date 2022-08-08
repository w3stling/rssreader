/*
 * MIT License
 *
 * Copyright (c) 2022, Apptastic Software
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.apptasticsoftware.rssreader;

import javax.net.ssl.SSLContext;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import java.util.zip.GZIPInputStream;

import static javax.xml.stream.XMLStreamConstants.CDATA;
import static javax.xml.stream.XMLStreamConstants.CHARACTERS;
import static javax.xml.stream.XMLStreamConstants.END_ELEMENT;
import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;


/**
 * Class for reading RSS (Rich Site Summary) and Atom types of web feeds.
 */
public class RssReader {
    private static final String LOG_GROUP = "com.apptastic.rssreader";
    private HttpClient httpClient;

    public RssReader() {
    }

    public RssReader(HttpClient httpClient) {
        Objects.requireNonNull(httpClient, "Http client must not be null");
        this.httpClient = httpClient;
    }

    /**
     * Read RSS feed with the given URL.
     * @param url URL to RSS feed.
     * @return Stream of items
     * @throws IOException Fail to read url or its content
     */
    @SuppressWarnings("squid:S1181")
    public Stream<Item> read(String url) throws IOException {
        try {
            return readAsync(url).get(1, TimeUnit.MINUTES);
        } catch (CompletionException e) {
            try {
                throw e.getCause();
            } catch (IOException e2) {
                throw e2;
            } catch(Throwable e2) {
                throw new AssertionError(e2);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IOException(e);
        } catch (ExecutionException | TimeoutException e) {
            throw new IOException(e);
        }
    }

    /**
     * Read RSS feed from input stream.
     * @param inputStream inputStream containing the RSS feed.
     * @return Stream of items
     */
    public Stream<Item> read(InputStream inputStream) {
        var itemIterator = new RssItemIterator(inputStream);
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(itemIterator, Spliterator.ORDERED), false);
    }

    /**
     * Read RSS feed asynchronous with the given URL.
     * @param url URL to RSS feed.
     * @return Stream of items
     */
    public CompletableFuture<Stream<Item>> readAsync(String url) {
        return sendAsyncRequest(url).thenApply(processResponse());
    }

    protected CompletableFuture<HttpResponse<InputStream>> sendAsyncRequest(String url) {
        HttpRequest req = HttpRequest.newBuilder(URI.create(url))
                .timeout(Duration.ofSeconds(25))
                .header("Accept-Encoding", "gzip")
                .GET()
                .build();

        HttpClient client = this.httpClient;

        if (client == null) {
            client = createHttpClient();
        }

        return client.sendAsync(req, HttpResponse.BodyHandlers.ofInputStream());
    }

    private Function<HttpResponse<InputStream>, Stream<Item>> processResponse() {
        return response -> {
            try {
                if (response.statusCode() >= 400 && response.statusCode() < 600) {
                    throw new IOException("Response http status code: " + response.statusCode());
                }

                var inputStream = response.body();
                if (Optional.of("gzip").equals(response.headers().firstValue("Content-Encoding")))
                    inputStream = new GZIPInputStream(inputStream);

                inputStream = new BufferedInputStream(inputStream);

                removeBadDate(inputStream);
                var itemIterator = new RssItemIterator(inputStream);
                return StreamSupport.stream(Spliterators.spliteratorUnknownSize(itemIterator, Spliterator.ORDERED), false);
            } catch (IOException e) {
                throw new CompletionException(e);
            }
        };
    }

    private void removeBadDate(InputStream inputStream) throws IOException {
        inputStream.mark(2);
        var firstChar = inputStream.read();

        if (firstChar != 65279 && firstChar != 13 && firstChar != 10 && !Character.isWhitespace(firstChar)) {
            inputStream.reset();
        }
        else if (firstChar == 13 || Character.isWhitespace(firstChar)) {
            var secondChar = inputStream.read();

            if (secondChar != 10 && !Character.isWhitespace(secondChar)) {
                inputStream.reset();
                inputStream.read();
            }
        }
    }

    static class RssItemIterator implements Iterator<Item> {
        private final StringBuilder textBuilder;
        private final InputStream is;
        private XMLStreamReader reader;
        private Channel channel;
        private Image image = null;
        private Item item = null;
        private Item nextItem;
        private boolean isChannelPart = true;
        private boolean isImagePart = false;
        private String elementName = null;

        public RssItemIterator(InputStream is) {
            this.is = is;
            nextItem = null;
            textBuilder = new StringBuilder();

            try {
                var xmlInFact = XMLInputFactory.newInstance();

                // disable XML external entity (XXE) processing
                xmlInFact.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, Boolean.FALSE);
                xmlInFact.setProperty(XMLInputFactory.SUPPORT_DTD, Boolean.FALSE);

                reader = xmlInFact.createXMLStreamReader(is);
            }
            catch (XMLStreamException e) {
                var logger = Logger.getLogger(LOG_GROUP);

                if (logger.isLoggable(Level.WARNING))
                    logger.log(Level.WARNING, "Failed to process XML. ", e);
            }
        }

        void peekNext() {
            if (nextItem == null) {
                try {
                    nextItem = next();
                }
                catch (NoSuchElementException e) {
                    nextItem = null;
                }
            }
        }

        @Override
        public boolean hasNext() {
            peekNext();
            return nextItem != null;
        }

        @Override
        @SuppressWarnings("squid:S3776")
        public Item next() {
            if (nextItem != null) {
                var next = nextItem;
                nextItem = null;

                return next;
            }

            try {
                while (reader.hasNext()) {
                    var type = reader.next(); // do something here

                    if (type == CHARACTERS || type == CDATA) {
                        parseCharacters();
                    }
                    else if (type == START_ELEMENT) {
                        parseStartElement();
                        parseAttributes();
                    }
                    else if (type == END_ELEMENT) {
                        var itemParsed = parseEndElement();

                        if (itemParsed)
                            return item;
                    }
                }
            } catch (XMLStreamException e) {
                var logger = Logger.getLogger(LOG_GROUP);

                if (logger.isLoggable(Level.WARNING))
                    logger.log(Level.WARNING, "Failed to parse XML. ", e);
            }

            try {
                reader.close();
                is.close();
            } catch (XMLStreamException | IOException e) {
                var logger = Logger.getLogger(LOG_GROUP);

                if (logger.isLoggable(Level.WARNING))
                    logger.log(Level.WARNING, "Failed to close XML stream. ", e);
            }

            throw new NoSuchElementException();
        }

        @SuppressWarnings("squid:S1192")
        void parseStartElement() {
            textBuilder.setLength(0);
            elementName = reader.getLocalName();

            if ("channel".equals(elementName) || "feed".equals(elementName)) {
                channel = new Channel();
                channel.setTitle("");
                channel.setDescription("");
                channel.setLink("");
                isChannelPart = true;
            }
            else if ("item".equals(elementName) || "entry".equals(elementName)) {
                item = new Item();
                item.setChannel(channel);
                isChannelPart = false;
            }
            else if ("guid".equals(elementName)) {
                var value = reader.getAttributeValue(null, "isPermaLink");
                if (item != null)
                    item.setIsPermaLink(Boolean.parseBoolean(value));
            }
            else if ("image".equals(elementName)) {
                image = new Image();
                channel.setImage(image);
                isImagePart = true;
            }
        }

        void parseAttributes() {
            if (reader.getLocalName().equals("link")) {
                var rel = reader.getAttributeValue(null, "rel");
                var link = reader.getAttributeValue(null, "href");
                var isAlternate = "alternate".equals(rel);

                if (link != null && isAlternate) {
                    if (isChannelPart)
                        channel.setLink(link);
                    else
                        item.setLink(link);
                }
            } else if(reader.getLocalName().equals("enclosure")) {
                var url = reader.getAttributeValue(null, "url");
                var type = reader.getAttributeValue(null, "type");
                var length = reader.getAttributeValue(null, "length");
                Long parsedLength = (length == null || length.isEmpty()) ? null : Long.parseLong(length);
                item.setEclosure(new Enclosure(url, type, parsedLength));
            }
        }

        boolean parseEndElement() {
            var name = reader.getLocalName();
            var text = textBuilder.toString().trim();

            if ("image".equals(name))
                isImagePart = false;
            else if (isImagePart)
                parseImageCharacters(elementName, text);
            else if (isChannelPart)
                parseChannelCharacters(elementName, text);
            else
                parseItemCharacters(elementName, item, text);

            textBuilder.setLength(0);

            return "item".equals(name) || "entry".equals(name);
        }

        void parseCharacters() {
            var text = reader.getText();

            if (text.trim().isEmpty())
                return;

            textBuilder.append(text);
        }

        @SuppressWarnings({"squid:S3776", "squid:S1192"})
        void parseChannelCharacters(String elementName, String text) {
            if (channel == null || text.isEmpty())
                return;

            if ("title".equals(elementName))
                channel.setTitle(text);
            else if ("description".equals(elementName) || "subtitle".equals(elementName))
                channel.setDescription(text);
            else if ("link".equals(elementName))
                channel.setLink(text);
            else if ("category".equals(elementName))
                channel.setCategory(text);
            else if ("language".equals(elementName))
                channel.setLanguage(text);
            else if ("copyright".equals(elementName) || "rights".equals(elementName))
                channel.setCopyright(text);
            else if ("generator".equals(elementName))
                channel.setGenerator(text);
            else if ("ttl".equals(elementName))
                channel.setTtl(text);
            else if ("pubDate".equals(elementName))
                channel.setPubDate(text);
            else if ("lastBuildDate".equals(elementName) || "updated".equals(elementName))
                channel.setLastBuildDate(text);
            else if ("managingEditor".equals(elementName))
                channel.setManagingEditor(text);
            else if ("webMaster".equals(elementName))
                channel.setWebMaster(text);
        }

        void parseImageCharacters(String elementName, String text) {
            if (image == null || text.isEmpty())
                return;

            if ("title".equals(elementName))
                image.setTitle(text);
            else if ("link".equals(elementName))
                image.setLink(text);
            else if ("url".equals(elementName))
                image.setUrl(text);
            else if ("description".equals(elementName))
                image.setDescription(text);
            else if ("height".equals(elementName))
                image.setHeight(Integer.valueOf(text));
            else if ("width".equals(elementName))
                image.setWidth(Integer.valueOf(text));
            else if ("image".equals(elementName))
                isImagePart = false;
        }

        void parseItemCharacters(String elementName, Item item, String text) {
            if (text.isEmpty())
                return;

            if ("guid".equals(elementName) || "id".equals(elementName))
                item.setGuid(text);
            else if ("title".equals(elementName))
                item.setTitle(text);
            else if ("description".equals(elementName) || "summary".equals(elementName) || "content".equals(elementName))
                item.setDescription(text);
            else if ("link".equals(elementName))
                item.setLink(text);
            else if ("author".equals(elementName))
                item.setAuthor(text);
            else if ("category".equals(elementName))
                item.setCategory(text);
            else if ("pubDate".equals(elementName) || "published".equals(elementName))
                item.setPubDate(text);
            else if ("updated".equals(elementName) && item.getPubDate().isEmpty())
                item.setPubDate(text);
        }
    }

    private HttpClient createHttpClient() {
        HttpClient client;

        try {
            SSLContext context = SSLContext.getInstance("TLSv1.3");
            context.init(null, null, null);

            client = HttpClient.newBuilder()
                    .sslContext(context)
                    .connectTimeout(Duration.ofSeconds(25))
                    .followRedirects(HttpClient.Redirect.NORMAL)
                    .build();
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            client = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(25))
                    .followRedirects(HttpClient.Redirect.NORMAL)
                    .build();
        }

        return client;
    }

}