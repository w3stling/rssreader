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

import com.apptasticsoftware.rssreader.util.Mapper;

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
import java.util.function.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import java.util.zip.GZIPInputStream;

import static com.apptasticsoftware.rssreader.util.Mapper.mapLong;
import static com.apptasticsoftware.rssreader.util.Mapper.mapInteger;
import static com.apptasticsoftware.rssreader.util.Mapper.createIfNull;
import static com.apptasticsoftware.rssreader.util.Mapper.createIfNullOptional;

import static javax.xml.stream.XMLStreamConstants.CDATA;
import static javax.xml.stream.XMLStreamConstants.CHARACTERS;
import static javax.xml.stream.XMLStreamConstants.END_ELEMENT;
import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;


/**
 * Abstract base class for implementing modules or extensions of RSS / Atom feeds with custom tags and attributes.
 */
public abstract class AbstractRssReader<C extends Channel, I extends Item> {
    private static final String LOG_GROUP = "com.apptasticsoftware.rssreader";
    private final HttpClient httpClient;
    private DateTimeParser dateTimeParser = new DateTime();
    private String userAgent = "";
    private final Map<String, String> headers = new HashMap<>();
    private final HashMap<String, BiConsumer<C, String>> channelTags = new HashMap<>();
    private final HashMap<String, Map<String, BiConsumer<C, String>>> channelAttributes = new HashMap<>();
    private final HashMap<String, Consumer<I>> onItemTags = new HashMap<>();
    private final HashMap<String, BiConsumer<I, String>> itemTags = new HashMap<>();
    private final HashMap<String, Map<String, BiConsumer<I, String>>> itemAttributes = new HashMap<>();
    private final Set<String> collectChildNodesForTag = Set.of("content", "summary");
    private boolean isInitialized;


    /**
     * Constructor
     */
    protected AbstractRssReader() {
        httpClient = createHttpClient();
    }

    /**
     * Constructor
     * @param httpClient http client
     */
    protected AbstractRssReader(HttpClient httpClient) {
        Objects.requireNonNull(httpClient, "Http client must not be null");
        this.httpClient = httpClient;
    }

    /**
     * Returns an object of a Channel implementation.
     *
     * @deprecated
     * Use {@link AbstractRssReader#createChannel(DateTimeParser)} instead.
     *
     * @return channel
     */
    @SuppressWarnings("java:S1133")
    @Deprecated(since="3.5.0", forRemoval=true)
    protected C createChannel() {
        return null;
    }

    /**
     * Returns an object of a Channel implementation.
     *
     * @param dateTimeParser dateTimeParser
     * @return channel
     */
    protected abstract C createChannel(DateTimeParser dateTimeParser);

    /**
     * Returns an object of an Item implementation.
     *
     * @deprecated
     * Use {@link AbstractRssReader#createItem(DateTimeParser)} instead.
     *
     * @return item
     */
    @SuppressWarnings("java:S1133")
    @Deprecated(since="3.5.0", forRemoval=true)
    protected I createItem() {
        return null;
    }

    /**
     * Returns an object of an Item implementation.
     *
     * @param dateTimeParser dateTimeParser
     * @return item
     */
    protected abstract I createItem(DateTimeParser dateTimeParser);

    /**
     * Initialize channel and items tags and attributes
     */
    protected void initialize() {
        registerChannelTags();
        registerChannelAttributes();
        registerItemTags();
        registerItemAttributes();
    }

    /**
     * Register channel tags for mapping to channel object fields
     */
    @SuppressWarnings("java:S1192")
    protected void registerChannelTags() {
        channelTags.putIfAbsent("/feed/title", Channel::setTitle);
        channelTags.putIfAbsent("/rss/channel/title", Channel::setTitle);
        channelTags.putIfAbsent("/rss/channel/description", Channel::setDescription);
        channelTags.putIfAbsent("subtitle", Channel::setDescription);
        channelTags.putIfAbsent("link", Channel::setLink);
        channelTags.putIfAbsent("category", Channel::addCategory);
        channelTags.putIfAbsent("language", Channel::setLanguage);
        channelTags.putIfAbsent("copyright", Channel::setCopyright);
        channelTags.putIfAbsent("rights", Channel::setCopyright);
        channelTags.putIfAbsent("generator", Channel::setGenerator);
        channelTags.putIfAbsent("ttl", Channel::setTtl);
        channelTags.putIfAbsent("pubDate", Channel::setPubDate);
        channelTags.putIfAbsent("lastBuildDate", Channel::setLastBuildDate);
        channelTags.putIfAbsent("updated", Channel::setLastBuildDate);
        channelTags.putIfAbsent("managingEditor", Channel::setManagingEditor);
        channelTags.putIfAbsent("webMaster", Channel::setWebMaster);
        channelTags.putIfAbsent("docs", Channel::setDocs);
        channelTags.putIfAbsent("rating", Channel::setRating);
        channelTags.putIfAbsent("/rss/channel/image/link", (C c, String v) -> createIfNull(c::getImage, c::setImage, Image::new).setLink(v));
        channelTags.putIfAbsent("/rss/channel/image/title", (C c, String v) -> createIfNull(c::getImage, c::setImage, Image::new).setTitle(v));
        channelTags.putIfAbsent("/rss/channel/image/url", (C c, String v) -> createIfNull(c::getImage, c::setImage, Image::new).setUrl(v));
        channelTags.putIfAbsent("/rss/channel/image/description", (C c, String v) -> createIfNullOptional(c::getImage, c::setImage, Image::new).ifPresent(i -> i.setDescription(v)));
        channelTags.putIfAbsent("/rss/channel/image/height", (C c, String v) -> createIfNullOptional(c::getImage, c::setImage, Image::new).ifPresent(i -> mapInteger(v, i::setHeight)));
        channelTags.putIfAbsent("/rss/channel/image/width", (C c, String v) -> createIfNullOptional(c::getImage, c::setImage, Image::new).ifPresent(i -> mapInteger(v, i::setWidth)));
    }

    /**
     * Register channel attributes for mapping to channel object fields
     */
    protected void registerChannelAttributes() {
        channelAttributes.computeIfAbsent("link", k -> new HashMap<>()).put("href", Channel::setLink);
    }

    /**
     * Register item tags for mapping to item object fields
     */
    @SuppressWarnings("java:S1192")
    protected void registerItemTags() {
        itemTags.putIfAbsent("guid", Item::setGuid);
        itemTags.putIfAbsent("id", Item::setGuid);
        itemTags.putIfAbsent("/feed/entry/title", Item::setTitle);
        itemTags.putIfAbsent("/rss/channel/item/title", Item::setTitle);
        itemTags.putIfAbsent("description", Item::setDescription);
        itemTags.putIfAbsent("summary", Item::setDescription);
        itemTags.putIfAbsent("content", Item::setDescription);
        itemTags.putIfAbsent("link", Item::setLink);
        itemTags.putIfAbsent("author", Item::setAuthor);
        itemTags.putIfAbsent("/feed/entry/author/name", Item::setAuthor);
        itemTags.putIfAbsent("category", Item::addCategory);
        itemTags.putIfAbsent("pubDate", Item::setPubDate);
        itemTags.putIfAbsent("published", Item::setPubDate);
        itemTags.putIfAbsent("updated", (i, v) -> { if (i.getPubDate().isEmpty()) i.setPubDate(v); });
        itemTags.putIfAbsent("comments", Item::setComments);
        itemTags.putIfAbsent("dc:creator", (i, v) -> Mapper.mapIfEmpty(v, i::getAuthor, i::setAuthor));
        itemTags.putIfAbsent("dc:date", (i, v) -> Mapper.mapIfEmpty(v, i::getPubDate, i::setPubDate));

        onItemTags.put("enclosure", i -> i.addEnclosure(new Enclosure()));
    }

    /**
     * Register itam attributes for mapping to item object fields
     */
    protected void registerItemAttributes() {
        itemAttributes.computeIfAbsent("link", k -> new HashMap<>()).putIfAbsent("href", Item::setLink);
        itemAttributes.computeIfAbsent("guid", k -> new HashMap<>()).putIfAbsent("isPermaLink", (i, v) -> i.setIsPermaLink(Boolean.parseBoolean(v)) );

        var enclosureAttributes = itemAttributes.computeIfAbsent("enclosure", k -> new HashMap<>());
        enclosureAttributes.putIfAbsent("url", (i, v) -> i.getEnclosure().ifPresent(a -> a.setUrl(v)));
        enclosureAttributes.putIfAbsent("type", (i, v) -> i.getEnclosure().ifPresent(a -> a.setType(v)));
        enclosureAttributes.putIfAbsent("length", (i, v) -> i.getEnclosure().ifPresent(e -> mapLong(v, e::setLength)));
    }

    /**
     * Date and Time parser for parsing timestamps.
     * @param dateTimeParser the date time parser to use.
     * @return updated RSSReader.
     */
    public AbstractRssReader<C, I> setDateTimeParser(DateTimeParser dateTimeParser) {
        Objects.requireNonNull(dateTimeParser, "Date time parser must not be null");

        this.dateTimeParser = dateTimeParser;
        return this;
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
     * Adds a http header to the HttpClient.
     * This is completely optional and if no headers are set then it will not add anything.
     * @param key the key name of the header.
     * @param value the value of the header.
     * @return updated RSSReader.
     */
    public AbstractRssReader<C, I> addHeader(String key, String value) {
        Objects.requireNonNull(key, "Key must not be null");
        Objects.requireNonNull(value, "Value must not be null");

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

        itemTags.put(tag, consumer);
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

        itemAttributes.computeIfAbsent(tag, k -> new HashMap<>())
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

        channelTags.put(tag, consumer);
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

        channelAttributes.computeIfAbsent(tag, k -> new HashMap<>())
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
     * Read from a collections of RSS feed.
     * @param urls collections of URLs
     * @return Stream of items
     */
    public Stream<Item> read(Collection<String> urls) {
        Objects.requireNonNull(urls, "URLs collection must not be null");

        if (!isInitialized) {
            initialize();
            isInitialized = true;
        }

        return urls.stream()
                   .parallel()
                   .map(url -> {
                        try {
                            return Map.entry(url, readAsync(url));
                        } catch (Exception e) {
                            var logger = Logger.getLogger(LOG_GROUP);
                            if (logger.isLoggable(Level.WARNING))
                                logger.log(Level.WARNING, () -> String.format("Failed read URL %s. Message: %s", url, e.getMessage()));
                            return null;
                        }
                    })
                   .filter(Objects::nonNull)
                   .flatMap(f -> {
                       try {
                           return f.getValue().join();
                       } catch (Exception e) {
                           var logger = Logger.getLogger(LOG_GROUP);
                           if (logger.isLoggable(Level.WARNING))
                               logger.log(Level.WARNING, () -> String.format("Failed to read URL %s. Message: %s", f.getKey(), e.getMessage()));
                           return null;
                       }
                   });
    }

    /**
     * Read RSS feed from input stream.
     * @param inputStream inputStream containing the RSS feed.
     * @return Stream of items
     */
    public Stream<I> read(InputStream inputStream) {
        Objects.requireNonNull(inputStream, "Input stream must not be null");

        if (!isInitialized) {
            initialize();
            isInitialized = true;
        }

        inputStream = new BufferedInputStream(inputStream);
        removeBadData(inputStream);
        var itemIterator = new RssItemIterator(inputStream);
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(itemIterator, Spliterator.ORDERED), false)
                            .onClose(itemIterator::close);
    }

    /**
     * Read RSS feed asynchronous with the given URL.
     * @param url URL to RSS feed.
     * @return Stream of items
     */
    public CompletableFuture<Stream<I>> readAsync(String url) {
        Objects.requireNonNull(url, "URL must not be null");

        if (!isInitialized) {
            initialize();
            isInitialized = true;
        }

        return sendAsyncRequest(url).thenApply(processResponse());
    }

    /**
     * Sends request
     * @param url url
     * @return response
     */
    protected CompletableFuture<HttpResponse<InputStream>> sendAsyncRequest(String url) {
        var builder = HttpRequest.newBuilder(URI.create(url))
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

                removeBadData(inputStream);
                var itemIterator = new RssItemIterator(inputStream);
                return StreamSupport.stream(Spliterators.spliteratorUnknownSize(itemIterator, Spliterator.ORDERED), false)
                                    .onClose(itemIterator::close);
            } catch (IOException e) {
                throw new CompletionException(e);
            }
        };
    }

    @SuppressWarnings({"java:S108", "java:S2674"})
    private void removeBadData(InputStream inputStream) {
        try {
            inputStream.mark(128);
            long count = 0;
            int data = inputStream.read();
            while (Character.isWhitespace(data)) {
                data = inputStream.read();
                ++count;
            }
            inputStream.reset();
            inputStream.skip(count);
        } catch (IOException ignore) { }
    }

    class RssItemIterator implements Iterator<I> {
        private final StringBuilder textBuilder;
        private final Map<String, StringBuilder> childNodeTextBuilder;
        private final InputStream is;
        private final Deque<String> elementStack;
        private XMLStreamReader reader;
        private C channel;
        private I item = null;
        private I nextItem;
        private boolean isChannelPart = false;
        private boolean isItemPart = false;

        public RssItemIterator(InputStream is) {
            this.is = is;
            nextItem = null;
            textBuilder = new StringBuilder();
            childNodeTextBuilder = new HashMap<>();
            elementStack = new ArrayDeque<>();

            try {
                var xmlInFact = XMLInputFactory.newInstance();

                // disable XML external entity (XXE) processing
                xmlInFact.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, Boolean.FALSE);
                xmlInFact.setProperty(XMLInputFactory.SUPPORT_DTD, Boolean.FALSE);
                xmlInFact.setProperty(XMLInputFactory.IS_COALESCING, true);
                xmlInFact.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, Boolean.FALSE);

                reader = xmlInFact.createXMLStreamReader(is);
            }
            catch (XMLStreamException e) {
                var logger = Logger.getLogger(LOG_GROUP);

                if (logger.isLoggable(Level.WARNING))
                    logger.log(Level.WARNING, "Failed to process XML. ", e);
            }
        }

        public void close() {
            try {
                reader.close();
                is.close();
            } catch (XMLStreamException | IOException e) {
                var logger = Logger.getLogger(LOG_GROUP);

                if (logger.isLoggable(Level.WARNING))
                    logger.log(Level.WARNING, "Failed to close XML stream. ", e);
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
                    var type = reader.next();
                    collectChildNodes(type);

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

            close();
            throw new NoSuchElementException();
        }

        void collectChildNodes(int type) {
            if (type == START_ELEMENT) {
                var nsTagName = toNsName(reader.getPrefix(), reader.getLocalName());

                if (!childNodeTextBuilder.isEmpty()) {
                    StringBuilder startTagBuilder = new StringBuilder("<").append(nsTagName);
                    // Add namespaces to start tag
                    for (int i = 0; i < reader.getNamespaceCount(); ++i) {
                        startTagBuilder.append(" ")
                                       .append(toNamespacePrefix(reader.getNamespacePrefix(i)))
                                       .append("=")
                                       .append(reader.getNamespaceURI(i));
                    }
                    // Add attributes to start tag
                    for (int i = 0; i < reader.getAttributeCount(); ++i) {
                        startTagBuilder.append(" ")
                                       .append(toNsName(reader.getAttributePrefix(i), reader.getAttributeLocalName(i)))
                                       .append("=")
                                       .append(reader.getAttributeValue(i));
                    }
                    startTagBuilder.append(">");
                    var startTag = startTagBuilder.toString();

                    childNodeTextBuilder.entrySet()
                            .stream()
                            .filter(e -> !e.getKey().equals(nsTagName))
                            .forEach(e -> e.getValue().append(startTag));
                }

                // Collect child notes for tag names in this set
                if (collectChildNodesForTag.contains(nsTagName)) {
                    childNodeTextBuilder.put(nsTagName, new StringBuilder());
                }
            }
            else if (type == CHARACTERS || type == CDATA) {
                childNodeTextBuilder.forEach((k, builder) -> builder.append(reader.getText()));
            }
            else if (type == END_ELEMENT) {
                var nsTagName = toNsName(reader.getPrefix(), reader.getLocalName());
                var endTag = "</" + nsTagName + ">";
                childNodeTextBuilder.entrySet()
                                    .stream()
                                    .filter(e -> !e.getKey().equals(nsTagName))
                                    .forEach(e -> e.getValue().append(endTag));
            }
        }

        @SuppressWarnings("java:S5738")
        void parseStartElement() {
            textBuilder.setLength(0);
            var nsTagName = toNsName(reader.getPrefix(), reader.getLocalName());
            elementStack.addLast(nsTagName);

            if (isChannel(nsTagName)) {
                channel = Objects.requireNonNullElse(createChannel(dateTimeParser), createChannel());
                channel.setTitle("");
                channel.setDescription("");
                channel.setLink("");
                isChannelPart = true;
            }
            else if (isItem(nsTagName)) {
                item = Objects.requireNonNullElse(createItem(dateTimeParser), createItem());
                item.setChannel(channel);
                isChannelPart = false;
                isItemPart = true;
            }
        }

        protected boolean isChannel(String tagName) {
            return "channel".equals(tagName) || "feed".equals(tagName);
        }

        protected boolean isItem(String tagName) {
            return "item".equals(tagName) || "entry".equals(tagName);
        }

        void parseAttributes() {
            var nsTagName = toNsName(reader.getPrefix(), reader.getLocalName());
            var elementFullPath = getElementFullPath();

            if (isChannelPart) {
                // Map channel attributes
                mapChannelAttributes(nsTagName);
                mapChannelAttributes(elementFullPath);
            }
            else if (isItemPart) {
                onItemTags.computeIfPresent(nsTagName, (k, f) -> { f.accept(item); return f; });
                onItemTags.computeIfPresent(getElementFullPath(), (k, f) -> { f.accept(item); return f; });
                // Map item attributes
                mapItemAttributes(nsTagName);
                mapItemAttributes(elementFullPath);
            }
        }

        void mapChannelAttributes(String key) {
            var consumers = channelAttributes.get(key);
            if (consumers != null && channel != null) {
                consumers.forEach((attributeName, consumer) -> {
                    var attributeValue = Optional.ofNullable(reader.getAttributeValue(null, attributeName));
                    attributeValue.ifPresent(v -> consumer.accept(channel, v));
                });
            }
        }

        void mapItemAttributes(String key) {
            var consumers = itemAttributes.get(key);
            if (consumers != null && item != null) {
                consumers.forEach((attributeName, consumer) -> {
                    var attributeValue = Optional.ofNullable(reader.getAttributeValue(null, attributeName));
                    attributeValue.ifPresent(v -> consumer.accept(item, v));
                });
            }
        }

        boolean parseEndElement() {
            var nsTagName = toNsName(reader.getPrefix(), reader.getLocalName());
            var text = textBuilder.toString().trim();
            var elementFullPath = getElementFullPath();
            elementStack.removeLast();

            if (isChannelPart)
                parseChannelCharacters(channel, nsTagName, elementFullPath, text);
            else
                parseItemCharacters(item, nsTagName, elementFullPath, text);

            textBuilder.setLength(0);

            return isItem(nsTagName);
        }

        void parseCharacters() {
            var text = reader.getText();

            if (text.isBlank())
                return;

            textBuilder.append(text);
        }

        void parseChannelCharacters(C channel, String nsTagName, String elementFullPath, String text) {
            if (channel == null || text.isEmpty())
                return;

            channelTags.computeIfPresent(nsTagName, (k, f) -> { f.accept(channel, text); return f; });
            channelTags.computeIfPresent(elementFullPath, (k, f) -> { f.accept(channel, text); return f; });
        }

        void parseItemCharacters(final I item, String nsTagName, String elementFullPath, final String text) {
            var builder = childNodeTextBuilder.remove(nsTagName);
            if (item == null || (text.isEmpty() && builder == null))
                return;

            var textValue = (builder != null) ? builder.toString().trim() : text;
            itemTags.computeIfPresent(nsTagName, (k, f) -> { f.accept(item, textValue); return f; });
            itemTags.computeIfPresent(elementFullPath, (k, f) -> { f.accept(item, text); return f; });
        }

        String toNsName(String prefix, String name) {
            return prefix.isEmpty() ? name : prefix + ":" + name;
        }

        String toNamespacePrefix(String prefix) {
            return prefix == null || prefix.isEmpty() ? "xmlns" : "xmlns" + ":" + prefix;
        }

        String getElementFullPath() {
            return "/" + String.join("/", elementStack);
        }
    }

    private HttpClient createHttpClient() {
        HttpClient client;

        try {
            var context = SSLContext.getInstance("TLSv1.3");
            context.init(null, null, null);

            client = HttpClient.newBuilder()
                    .sslContext(context)
                    .connectTimeout(Duration.ofSeconds(25))
                    .followRedirects(HttpClient.Redirect.ALWAYS)
                    .build();
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            client = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(25))
                    .followRedirects(HttpClient.Redirect.ALWAYS)
                    .build();
        }

        return client;
    }

}