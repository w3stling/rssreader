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
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import java.util.zip.GZIPInputStream;


public class RssReader {
    private static final String LOG_GROUP = "com.apptastic.rssreader";
    private static final String HTTP_USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11";

    public Stream<Item> read(String url) throws IOException {
        InputStream inputStream = sendRequest(url);
        removeBadDate(inputStream);

        RssItemIterator itemIterator = new RssItemIterator(inputStream);
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(itemIterator, Spliterator.ORDERED), false);
    }

    void removeBadDate(InputStream inputStream) throws IOException {
        inputStream.mark(1);
        int firstChar = inputStream.read();

        if (firstChar != 65279)
            inputStream.reset();
    }

    /**
     * Internal method for sending the http request.
     *
     * @param url URL to send the request
     * @return The response for the request
     * @throws IOException exception
     */
    protected InputStream sendRequest(String url) throws IOException {
        URLConnection connection = new URL(url).openConnection();
        connection.setRequestProperty("Accept-Encoding", "gzip");
        connection.setRequestProperty("User-Agent", HTTP_USER_AGENT);
        connection.connect();
        InputStream inputStream = connection.getInputStream();

        if ("gzip".equals(connection.getContentEncoding()))
            inputStream = new GZIPInputStream(inputStream);

        return new BufferedInputStream(inputStream);
    }


    static class RssItemIterator implements Iterator<Item> {
        InputStream is;
        XMLStreamReader reader;
        Channel channel;
        private Item nextItem;

        public RssItemIterator(InputStream is) {
            this.is = is;
            nextItem = null;

            try {
                XMLInputFactory xmlInFact = XMLInputFactory.newInstance();
                reader = xmlInFact.createXMLStreamReader(is);
            } catch (XMLStreamException e) {
                Logger logger = Logger.getLogger(LOG_GROUP);

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
                Item next = nextItem;
                nextItem = null;

                return next;
            }

            boolean isChannelPart = true;
            String elementName = null;
            Item item = null;

            try {
                while (reader.hasNext()) {
                    int type = reader.next(); // do something here

                    if (type == XMLEvent.CHARACTERS) {
                        parseCharacters(elementName, item, isChannelPart);
                    }
                    else if (type == XMLEvent.START_ELEMENT) {
                        elementName = reader.getName().toString();

                        if ("channel".equals(reader.getName().getLocalPart())) {
                            channel = new Channel();
                            isChannelPart = true;
                        }
                        else if ("item".equals(reader.getName().getLocalPart())) {
                            item = new Item();
                            item.setChannel(channel);
                            isChannelPart = false;
                        }
                        else if ("guid".equals(elementName)) {
                            String value = reader.getAttributeValue(null, "isPermaLink");
                            if (item != null)
                                item.setIsPermaLink(Boolean.valueOf(value));
                        }
                    }
                    else if (type == XMLEvent.END_ELEMENT) {
                        String name = reader.getName().toString();

                        if ("item".equals(name))
                            return item;
                    }
                }
            } catch (XMLStreamException e) {
                Logger logger = Logger.getLogger(LOG_GROUP);

                if (logger.isLoggable(Level.WARNING))
                    logger.log(Level.WARNING, "Failed to parse XML. ", e);
            }

            try {
                reader.close();
                is.close();
            } catch (XMLStreamException | IOException e) {
                Logger logger = Logger.getLogger(LOG_GROUP);

                if (logger.isLoggable(Level.WARNING))
                    logger.log(Level.WARNING, "Failed to close XML stream. ", e);
            }

            throw new NoSuchElementException();
        }

        void parseCharacters(String elementName, Item item, boolean isChannelPart) {
            String text = reader.getText().trim();

            if (text.isEmpty())
                return;

            if (isChannelPart) {
                parseChannelCharacters(elementName, item, text);
            } else {
                parseItemCharacters(elementName, item, text);
            }
        }

        void parseChannelCharacters(String elementName, Item item, String text) {
            if ("title".equals(elementName))
                channel.setTitle(text);
            else if ("description".equals(elementName))
                channel.setDescription(text);
            else if ("language".equals(elementName))
                channel.setLanguage(text);
            else if ("link".equals(elementName))
                channel.setLink(text);
            else if ("copyright".equals(elementName))
                channel.setCopyright(text);
            else if ("generator".equals(elementName))
                channel.setGenerator(text);
            else if ("lastBuildDate".equals(elementName))
                channel.setLastBuildDate(text);
        }

        void parseItemCharacters(String elementName, Item item, String text) {
            if ("guid".equals(elementName))
                item.setGuid(text);
            else if ("title".equals(elementName))
                item.setTitle(text);
            else if ("description".equals(elementName))
                item.setDescription(text);
            else if ("pubDate".equals(elementName))
                item.setPubDate(text);
            else if ("link".equals(elementName))
                item.setLink(text);
        }

    }
}