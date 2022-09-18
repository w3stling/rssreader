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
import java.util.*;
import java.util.concurrent.*;
import java.util.function.BiConsumer;
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
 * Abstract base class for implementing modules or extensions of RSS / Atom feeds with custom tags and attributes.
 */
public abstract class AbstractRssReader<C extends Channel, I extends Item> {
    private static final String LOG_GROUP = "com.apptastic.rssreader";
    private final HttpClient httpClient;
    private String userAgent = "";
    private final Map<String, String> headers = new HashMap<>();
    private final HashMap<String, BiConsumer<C, String>> channelTags = new HashMap<>();
    private final HashMap<String, BiConsumer<C, String>> channelTagExtensions = new HashMap<>();
    private final HashMap<String, Map<String, BiConsumer<C, String>>> channelAttributeExtensions = new HashMap<>();

    private final HashMap<String, BiConsumer<Image, String>> imageTags = new HashMap<>();
    private final HashMap<String, BiConsumer<I, String>> itemTags = new HashMap<>();
    private final HashMap<String, BiConsumer<I, String>> itemTagExtensions = new HashMap<>();
    private final HashMap<String, Map<String, BiConsumer<I, String>>> itemAttributeExtensions = new HashMap<>();
    private static final BiConsumer<Image, String> EMPTY_IMAGE_MAPPING = (image, value) -> {};
    @SuppressWarnings("java:S1170")
    private final BiConsumer<C, String> emptyChannelMapping = (channel, value) -> {};
    @SuppressWarnings("java:S1170")
    private final BiConsumer<I, String> emptyItemMapping = (item, value) -> {};

    protected AbstractRssReader() {
        httpClient = createHttpClient();
        init();
    }

    protected AbstractRssReader(HttpClient httpClient) {
        Objects.requireNonNull(httpClient, "Http client must not be null");
        this.httpClient = httpClient;
        init();
    }

    /**
     * Returns an object of a Channel implementation.
     * @return channel
     */
    protected abstract C createChannel();

    /**
     * Returns an object of an Item implementation.
     * @return item
     */
    protected abstract I createItem();

    protected void init() {
        registerChanelTags();
        registerItemTags();
        registerImageTags();
    }

    @SuppressWarnings("java:S1192")
    protected void registerChanelTags() {
        channelTags.put("title", Channel::setTitle);
        channelTags.put("description", Channel::setDescription);
        channelTags.put("subtitle", Channel::setDescription);
        channelTags.put("link", Channel::setLink);
        channelTags.put("category", Channel::setCategory);
        channelTags.put("language", Channel::setLanguage);
        channelTags.put("copyright", Channel::setCopyright);
        channelTags.put("rights", Channel::setCopyright);
        channelTags.put("generator", Channel::setGenerator);
        channelTags.put("ttl", Channel::setTtl);
        channelTags.put("pubDate", Channel::setPubDate);
        channelTags.put("lastBuildDate", Channel::setLastBuildDate);
        channelTags.put("updated", Channel::setLastBuildDate);
        channelTags.put("managingEditor", Channel::setManagingEditor);
        channelTags.put("webMaster", Channel::setWebMaster);
    }

    @SuppressWarnings("java:S1192")
    protected void registerItemTags() {
        itemTags.put("guid", Item::setGuid);
        itemTags.put("id", Item::setGuid);
        itemTags.put("title", Item::setTitle);
        itemTags.put("description", Item::setDescription);
        itemTags.put("summary", Item::setDescription);
        itemTags.put("content", Item::setDescription);
        itemTags.put("link", Item::setLink);
        itemTags.put("author", Item::setAuthor);
        itemTags.put("category", Item::setCategory);
        itemTags.put("pubDate", Item::setPubDate);
        itemTags.put("published", Item::setPubDate);
        itemTags.put("updated", (i, v) -> { if (i.getPubDate().isEmpty()) i.setPubDate(v); });
    }

    @SuppressWarnings("java:S1192")
    protected void registerImageTags() {
        imageTags.put("title", Image::setTitle);
        imageTags.put("link", Image::setLink);
        imageTags.put("url", Image::setUrl);
        imageTags.put("description", Image::setDescription);
        imageTags.put("height", (i, v) -> i.setHeight(Integer.valueOf(v)) );
        imageTags.put("width", (i, v) -> i.setWidth(Integer.valueOf(v)) );
    }

    /**
     * Sets the user-agent of the HttpClient.
     * This is completely optional and if not set then it will not send a user-agent header.
     * @param userAgent the user-agent to use.
     * @return updated RSSReader.
     */
    public AbstractRssReader<C, I> setUserAgent(String userAgent) {
        Objects.requireNonNull(userAgent, "User-agent must not be null");

        this.userAgent = userAgent;
        return this;
    }

    /**
     * Adds a header to the HttpClient.
     * This is completely optional and if no headers are set then it will not add anything.
     * @param key the key name of the header.
     * @param value the value of the header.
     * @return updated RSSReader.
     */
    public AbstractRssReader<C, I> addHeader(String key, String value){
        this.headers.put(key, value);
        return this;
    }

    /**
     * Add item extension for tags
     * @param tag - tag name
     * @param consumer - setter method in Item class to use for mapping
     * @return this instance
     */
    public AbstractRssReader<C, I> addItemExtension(String tag, BiConsumer<I, String> consumer) {
        Objects.requireNonNull(tag, "Item tag must not be null");
        Objects.requireNonNull(consumer, "Item consumer must not be null");

        itemTagExtensions.put(tag, consumer);
        return this;
    }

    /**
     * Add item extension for attributes
     * @param tag - tag name
     * @param attribute - attribute name
     * @param consumer - setter method in Item class to use for mapping
     * @return this instance
     */
    public AbstractRssReader<C, I> addItemExtension(String tag, String attribute, BiConsumer<I, String> consumer) {
        Objects.requireNonNull(tag, "Item tag must not be null");
        Objects.requireNonNull(attribute, "Item attribute must not be null");
        Objects.requireNonNull(consumer, "Item consumer must not be null");

        itemAttributeExtensions.computeIfAbsent(tag, k -> new HashMap<>())
                               .put(attribute, consumer);
        return this;
    }

    /**
     * Add channel extension for tags
     * @param tag - tag name
     * @param consumer - setter method in Channel class to use for mapping
     * @return this instance
     */
    public AbstractRssReader<C, I> addChannelExtension(String tag, BiConsumer<C, String> consumer) {
        Objects.requireNonNull(tag, "Channel tag must not be null");
        Objects.requireNonNull(consumer, "Channel consumer must not be null");

        channelTagExtensions.put(tag, consumer);
        return this;
    }

    /**
     * Add channel extension for attributes
     * @param tag - tag name
     * @param attribute - attribute name
     * @param consumer - setter method in Channel class to use for mapping
     * @return this instance
     */
    public AbstractRssReader<C, I> addChannelExtension(String tag, String attribute, BiConsumer<C, String> consumer) {
        Objects.requireNonNull(tag, "Channel tag must not be null");
        Objects.requireNonNull(attribute, "Channel attribute must not be null");
        Objects.requireNonNull(consumer, "Channel consumer must not be null");

        channelAttributeExtensions.computeIfAbsent(tag, k -> new HashMap<>())
                                  .put(attribute, consumer);
        return this;
    }

    /**
     * Read RSS feed with the given URL.
     * @param url URL to RSS feed.
     * @return Stream of items
     * @throws IOException Fail to read url or its content
     */
    @SuppressWarnings("squid:S1181")
    public Stream<I> read(String url) throws IOException {
        Objects.requireNonNull(url, "URL must not be null");

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
    public Stream<I> read(InputStream inputStream) {
        Objects.requireNonNull(inputStream, "Input stream must not be null");

        var itemIterator = new RssItemIterator(inputStream);
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(itemIterator, Spliterator.ORDERED), false);
    }

    /**
     * Read RSS feed asynchronous with the given URL.
     * @param url URL to RSS feed.
     * @return Stream of items
     */
    public CompletableFuture<Stream<I>> readAsync(String url) {
        Objects.requireNonNull(url, "URL must not be null");

        return sendAsyncRequest(url).thenApply(processResponse());
    }

    protected CompletableFuture<HttpResponse<InputStream>> sendAsyncRequest(String url) {
        HttpRequest.Builder builder = HttpRequest.newBuilder(URI.create(url))
                .timeout(Duration.ofSeconds(25))
                .header("Accept-Encoding", "gzip");

        if (!userAgent.isBlank())
            builder.header("User-Agent", userAgent);

        headers.forEach(builder::header);

        return httpClient.sendAsync(builder.GET().build(), HttpResponse.BodyHandlers.ofInputStream());
    }

    private Function<HttpResponse<InputStream>, Stream<I>> processResponse() {
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

    class RssItemIterator implements Iterator<I> {
        private final StringBuilder textBuilder;
        private final InputStream is;
        private XMLStreamReader reader;
        private C channel;
        private Image image = null;
        private I item = null;
        private I nextItem;
        private boolean isChannelPart = true;
        private boolean isImagePart = false;
        private boolean isItemPart = false;
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
        public I next() {
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
            var prefix = reader.getPrefix();

            if ("channel".equals(elementName) || "feed".equals(elementName)) {
                channel = createChannel();
                channel.setTitle("");
                channel.setDescription("");
                channel.setLink("");
                isChannelPart = true;
            }
            else if ("item".equals(elementName) || "entry".equals(elementName)) {
                item = createItem();
                item.setChannel(channel);
                isChannelPart = false;
                isImagePart = false;
                isItemPart = true;
            }
            else if ("guid".equals(elementName)) {
                var value = reader.getAttributeValue(null, "isPermaLink");
                if (item != null)
                    item.setIsPermaLink(Boolean.parseBoolean(value));
            }
            else if ("image".equals(elementName) && prefix.isEmpty()) {
                image = new Image();
                channel.setImage(image);
                isImagePart = true;
            }
        }

        @SuppressWarnings({"squid:S3776", "squid:S1192"})
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
                item.setEnclosure(new Enclosure(url, type, parsedLength));
            } else {
                if (isChannelPart) {
                    var name = reader.getLocalName();
                    var prefix = reader.getPrefix();
                    var nsElementName = prefix.isEmpty() ? name : prefix + ":" + name;
                    Map<String, BiConsumer<C, String>> a = channelAttributeExtensions.get(nsElementName);
                    if (a != null) {
                        a.forEach((key, value) -> {
                            var attributeValue = reader.getAttributeValue(null, key);
                            value.accept(channel, attributeValue);
                        });
                    }
                } else if (isItemPart) {
                    var name = reader.getLocalName();
                    var prefix = reader.getPrefix();
                    var nsElementName = prefix.isEmpty() ? name : prefix + ":" + name;
                    Map<String, BiConsumer<I, String>> a = itemAttributeExtensions.get(nsElementName);
                    if (a != null) {
                        a.forEach((key, value) -> {
                            var attributeValue = reader.getAttributeValue(null, key);
                            value.accept(item, attributeValue);
                        });
                    }
                }
            }
        }

        boolean parseEndElement() {
            var name = reader.getLocalName();
            var prefix = reader.getPrefix();
            var text = textBuilder.toString().trim();

            if ("image".equals(name))
                isImagePart = false;
            else if (isImagePart)
                parseImageCharacters(image, elementName, text);
            else if (isChannelPart)
                parseChannelCharacters(channel, prefix, elementName, text);
            else
                parseItemCharacters(item, prefix, elementName, text);

            textBuilder.setLength(0);

            return "item".equals(name) || "entry".equals(name);
        }

        void parseCharacters() {
            var text = reader.getText();

            if (text.trim().isEmpty())
                return;

            textBuilder.append(text);
        }

        void parseChannelCharacters(C channel, String prefix, String elementName, String text) {
            if (channel == null || text.isEmpty())
                return;

            if (prefix.isEmpty()) {
                channelTags.getOrDefault(elementName, emptyChannelMapping).accept(channel, text);
            } else {
                var nsElementName = prefix + ":" + elementName;
                channelTagExtensions.getOrDefault(nsElementName, emptyChannelMapping).accept(channel, text);
            }
        }

        void parseImageCharacters(Image image, String elementName, String text) {
            if (image == null || text.isEmpty())
                return;

            imageTags.getOrDefault(elementName, EMPTY_IMAGE_MAPPING).accept(image, text);
        }

        void parseItemCharacters(I item, String prefix, String elementName, String text) {
            if (item == null || text.isEmpty())
                return;

            if (prefix.isEmpty()) {
                itemTags.getOrDefault(elementName, emptyItemMapping).accept(item, text);
            } else {
                var nsElementName = prefix + ":" + elementName;
                itemTagExtensions.getOrDefault(nsElementName, emptyItemMapping).accept(item, text);
            }
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