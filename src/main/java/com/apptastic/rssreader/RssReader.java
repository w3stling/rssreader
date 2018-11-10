/*
 * MIT License
 *
 * Copyright (c) 2018, Apptastic Software
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
package com.apptastic.rssreader;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import java.util.zip.GZIPInputStream;


/**
 * Class for reading  RSS (Rich Site Summary) type of web feeds.
 */
public class RssReader {
    private static final String LOG_GROUP = "com.apptastic.rssreader";
    private static final String HTTP_USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11";
    private final HttpClient httpClient;

    public RssReader() {
        httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(15))
                .build();
    }

    /**
     * Read RSS feed synchronous with the given URL.
     * @param url URL to RSS feed.
     * @return Stream of items
     * @throws IOException Fail to read url or its content
     */
    public Stream<Item> read(String url) throws IOException {
        try {
            return readAsync(url).join();
        } catch (CompletionException e) {
            try {
                throw e.getCause();
            } catch (IOException e2) {
                throw e2;
            } catch(Throwable e2) {
                throw new AssertionError(e2);
            }
        }
    }

    /**
     * Read RSS feed asynchronous with the given URL.
     * @param url URL to RSS feed.
     * @return Stream of items
     */
    public CompletableFuture<Stream<Item>> readAsync(String url) {
        return sendAsyncRequest(url).thenApplyAsync(processResponse());
    }

    private void removeBadDate(InputStream inputStream) throws IOException {
        inputStream.mark(2);
        var firstChar = inputStream.read();

        if (firstChar != 65279 && firstChar != 13)
            inputStream.reset();

        if (firstChar == 13) {
            var secondChar = inputStream.read();

            if (secondChar != 10) {
                inputStream.reset();
                inputStream.read();
            }
        }
    }

    protected CompletableFuture<HttpResponse<InputStream>> sendAsyncRequest(String url) {

        HttpRequest req = HttpRequest.newBuilder(URI.create(url))
                .timeout(Duration.ofSeconds(15))
                .header("Accept-Encoding", "gzip")
                .header("User-Agent", HTTP_USER_AGENT)
                .GET()
                .build();

        return httpClient.sendAsync(req, HttpResponse.BodyHandlers.ofInputStream());
    }

    private Function<HttpResponse<InputStream>, Stream<Item>> processResponse() {
        return response -> {
            try {
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

    static class RssItemIterator implements Iterator<Item> {
        private InputStream is;
        private XMLStreamReader reader;
        private Channel channel;
        private Item item = null;
        private Item nextItem;
        private boolean isChannelPart = true;
        private String elementName = null;
        private StringBuilder textBuilder;

        public RssItemIterator(InputStream is) {
            this.is = is;
            nextItem = null;
            textBuilder = new StringBuilder();

            try {
                var xmlInFact = XMLInputFactory.newInstance();
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
        public Item next() {
            if (nextItem != null) {
                var next = nextItem;
                nextItem = null;

                return next;
            }

            try {
                while (reader.hasNext()) {
                    var type = reader.next(); // do something here

                    if (type == XMLEvent.CHARACTERS) {
                        parseCharacters();
                    }
                    else if (type == XMLEvent.START_ELEMENT) {
                        parseStartElement();
                        parseAttributes();
                    }
                    else if (type == XMLEvent.END_ELEMENT) {
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

        void parseStartElement() {
            textBuilder.setLength(0);
            elementName = reader.getLocalName();

            if ("channel".equals(elementName) || "feed".equals(elementName)) {
                channel = new Channel();
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
                    item.setIsPermaLink(Boolean.valueOf(value));
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
            }
        }

        boolean parseEndElement() {
            var name = reader.getLocalName();
            var text = textBuilder.toString().trim();

            if (isChannelPart)
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

        void parseChannelCharacters(String elementName, String text) {
            if (channel == null || text.isEmpty())
                return;

            if ("title".equals(elementName))
                channel.setTitle(text);
            else if ("description".equals(elementName) || "subtitle".equals(elementName))
                channel.setDescription(text);
            else if ("language".equals(elementName))
                channel.setLanguage(text);
            else if ("link".equals(elementName))
                channel.setLink(text);
            else if ("copyright".equals(elementName))
                channel.setCopyright(text);
            else if ("generator".equals(elementName))
                channel.setGenerator(text);
            else if ("lastBuildDate".equals(elementName) || "updated".equals(elementName))
                channel.setLastBuildDate(text);
        }

        void parseItemCharacters(String elementName, Item item, String text) {
            if (text.isEmpty())
                return;

            if ("guid".equals(elementName) || "id".equals(elementName))
                item.setGuid(text);
            else if ("title".equals(elementName))
                item.setTitle(text);
            else if ("description".equals(elementName) || "content".equals(elementName))
                item.setDescription(text);
            else if ("pubDate".equals(elementName) || "published".equals(elementName))
                item.setPubDate(text);
            else if ("link".equals(elementName))
                item.setLink(text);
        }
    }

}