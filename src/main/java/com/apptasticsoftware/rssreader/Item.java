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


import com.apptasticsoftware.rssreader.util.ItemComparator;

import java.time.ZonedDateTime;
import java.util.*;

/**
 * Class representing a RSS item. A channel may contain any number of items. An item may represent a "story" -- much
 * like a story in a newspaper or magazine; if so its description is a synopsis of the story, and the link points
 * to the full story.
 */
public class Item implements Comparable<Item> {
    private final Comparator<Item> defaultComparator;
    private String title;
    private String description;
    private String link;
    private String author;
    private String category;
    private final List<String> categories = new ArrayList<>();
    private String guid;
    private Boolean isPermaLink;
    private String pubDate;
    private String comments;
    private Enclosure enclosure;
    private Channel channel;
    private final DateTimeParser dateTimeParser;

    /**
     * Constructor for Item
     * @deprecated
     * Use {@link Item#Item(DateTimeParser)} instead.
     */
    @Deprecated(since="3.5.0", forRemoval=true)
    public Item() {
        dateTimeParser = new DateTime();
        defaultComparator = ItemComparator.newestItemFirst();
    }

    /**
     * Constructor for Item
     * @param dateTimeParser dateTimeParser
     */
    public Item(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
        defaultComparator = ItemComparator.newestItemFirst(dateTimeParser);
    }

    /**
     * Get the title of the item.
     *
     * @return title
     */
    public Optional<String> getTitle() {
        return Optional.ofNullable(title);
    }

    /**
     * Set the title of the item.
     *
     * @param title title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get the item synopsis.
     *
     * @return description
     */
    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }

    /**
     * Set the item synopsis.
     *
     * @param description description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the URL of the item.
     *
     * @return link
     */
    public Optional<String> getLink() {
        return Optional.ofNullable(link);
    }

    /**
     * Set the URL of the item.
     *
     * @param link link
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * Get email address of the author of the item.
     *
     * @return author
     */
    public Optional<String> getAuthor() {
        return Optional.ofNullable(author);
    }

    /**
     * Set email address of the author of the item.
     *
     * @param author author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Get category for item.
     *
     * @deprecated
     * This method be removed in a future version.
     * <p> Use {@link Item#getCategories()} instead.
     *
     * @return category
     */
    @SuppressWarnings("java:S1133")
    @Deprecated(since="3.3.0", forRemoval=true)
    public Optional<String> getCategory() {
        return Optional.ofNullable(category);
    }

    /**
     * Set category for item.
     *
     * @deprecated
     * This method be removed in a future version.
     * <p> Use {@link Item#addCategory(String category)} instead.
     *
     * @param category category
     */
    @SuppressWarnings("java:S1133")
    @Deprecated(since="3.3.0", forRemoval=true)
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Get categories for item.
     * @return list of categories
     */
    public List<String> getCategories() {
        return Collections.unmodifiableList(categories);
    }

    /**
     * Add category for item.
     * @param category category
     */
    public void addCategory(String category) {
        this.category = category;
        categories.add(category);
    }

    /**
     * Get a string that uniquely identifies the item.
     *
     * @return guid
     */
    public Optional<String> getGuid() {
        return Optional.ofNullable(guid);
    }

    /**
     * Set a string that uniquely identifies the item.
     *
     * @param guid guid
     */
    public void setGuid(String guid) {
        this.guid = guid;
    }

    /**
     * If the guid element has an attribute named "isPermaLink" with a value of true, the reader may assume that
     * it is a permalink to the item, that is, a url that can be opened in a Web browser, that points to the full
     * item described by the item element.
     *
     * @return permanent link
     */
    public Optional<Boolean> getIsPermaLink() {
        return Optional.ofNullable(isPermaLink);
    }

    /**
     * If the guid element has an attribute named "isPermaLink" with a value of true, the reader may assume that
     * it is a permalink to the item, that is, a url that can be opened in a Web browser, that points to the full
     * item described by the item element.
     *
     * @param isPermaLink is perma link
     */
    public void setIsPermaLink(boolean isPermaLink) {
        this.isPermaLink = isPermaLink;
    }

    /**
     * Get a string that indicates when the item was published.
     *
     * @return publication date
     */
    public Optional<String> getPubDate() {
        return Optional.ofNullable(pubDate);
    }

    /**
     * Set a string that indicates when the item was published.
     *
     * @param pubDate publication date
     */
    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    /**
     * Get a ZonedDateTime that indicates when the item was published.
     *
     * @return publication date
     */
    public Optional<ZonedDateTime> getPubDateZonedDateTime() {
        return getPubDate().map(dateTimeParser::parse);
    }

    /**
     * Get comments relating to the item.
     * @return comments
     */
    public Optional<String> getComments() {
        return Optional.ofNullable(comments);
    }

    /**
     * Set comments relating to the item.
     * @param comments comments
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * Get the enclosure of the item.
     *
     * @return enclosure
     */
    public Optional<Enclosure> getEnclosure() {
        return Optional.ofNullable(enclosure);
    }

    /**
     * Set the enclosure of the item.
     *
     * @param enclosure enclosure
     */
    public void setEnclosure(Enclosure enclosure) {
        this.enclosure = enclosure;
    }

    /**
     * Get the channel that this item was published in.
     *
     * @return channel
     */
    public Channel getChannel() {
        return channel;
    }

    /**
     * Set the channel that this item was published in.
     *
     * @param channel channel
     */
    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(getTitle(), item.getTitle()) &&
                Objects.equals(getDescription(), item.getDescription()) &&
                Objects.equals(getLink(), item.getLink()) &&
                Objects.equals(getAuthor(), item.getAuthor()) &&
                getCategories().equals(item.getCategories()) &&
                Objects.equals(getGuid(), item.getGuid()) &&
                Objects.equals(getIsPermaLink(), item.getIsPermaLink()) &&
                Objects.equals(getPubDate(), item.getPubDate()) &&
                Objects.equals(getComments(), item.getComments()) &&
                Objects.equals(getEnclosure(), item.getEnclosure()) &&
                Objects.equals(getChannel(), item.getChannel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getDescription(), getLink(), getAuthor(), getCategories(),
                getGuid(), getIsPermaLink(), getPubDate(), getComments(), getEnclosure(), getChannel());
    }

    /**
     * Compares publication time of two {@code Item} objects.
     *
     * @param o item to compare
     * @return value
     * @since 2.2.0
     */
    @Override
    public int compareTo(Item o) {
        return defaultComparator.compare(this, o);
    }
}
