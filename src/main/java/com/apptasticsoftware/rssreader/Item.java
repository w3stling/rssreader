package com.apptasticsoftware.rssreader;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public interface Item extends Comparable<Item> {

    /**
     * Get the title of the item.
     *
     * @return title
     */
    Optional<String> getTitle();

    /**
     * Set the title of the item.
     *
     * @param title title
     */
    void setTitle(String title);

    /**
     * Get the item synopsis.
     *
     * @return description
     */
    Optional<String> getDescription();

    /**
     * Set the item synopsis.
     *
     * @param description description
     */
    void setDescription(String description);

    /**
     * Get the item content.
     *
     * @return content
     */
    Optional<String> getContent();

    /**
     * Set the item content.
     *
     * @param content content
     */
    void setContent(String content);

    /**
     * Get the URL of the item.
     *
     * @return link
     */
    Optional<String> getLink();

    /**
     * Set the URL of the item.
     *
     * @param link link
     */
    void setLink(String link);

    /**
     * Get email address of the author of the item.
     *
     * @return author
     */
    Optional<String> getAuthor();

    /**
     * Set email address of the author of the item.
     *
     * @param author author
     */
    void setAuthor(String author);

    /**
     * Get category for item.
     *
     * @deprecated
     * This method be removed in a future version.
     * <p> Use {@link ItemImpl#getCategories()} instead.
     *
     * @return category
     */
    @SuppressWarnings("java:S1133")
    @Deprecated(since="3.3.0", forRemoval=true)
    Optional<String> getCategory();

    /**
     * Set category for item.
     *
     * @deprecated
     * This method be removed in a future version.
     * <p> Use {@link ItemImpl#addCategory(String category)} instead.
     *
     * @param category category
     */
    @SuppressWarnings("java:S1133")
    @Deprecated(since="3.3.0", forRemoval=true)
    void setCategory(String category);

    /**
     * Get categories for item.
     * @return list of categories
     */
    List<String> getCategories();

    /**
     * Add category for item.
     * @param category category
     */
    void addCategory(String category);

    /**
     * Get a string that uniquely identifies the item.
     *
     * @return guid
     */
    Optional<String> getGuid();

    /**
     * Set a string that uniquely identifies the item.
     *
     * @param guid guid
     */
    void setGuid(String guid);

    /**
     * If the guid element has an attribute named "isPermaLink" with a value of true, the reader may assume that
     * it is a permalink to the item, that is, an url that can be opened in a Web browser, that points to the full
     * item described by the item element.
     *
     * @return permanent link
     */
    Optional<Boolean> getIsPermaLink();

    /**
     * If the guid element has an attribute named "isPermaLink" with a value of true, the reader may assume that
     * it is a permalink to the item, that is, an url that can be opened in a Web browser, that points to the full
     * item described by the item element.
     *
     * @param isPermaLink is perma link
     */
    void setIsPermaLink(boolean isPermaLink);

    /**
     * Get a string that indicates when the item was published.
     *
     * @return publication date
     */
    Optional<String> getPubDate();

    /**
     * Set a string that indicates when the item was published.
     *
     * @param pubDate publication date
     */
    void setPubDate(String pubDate);

    /**
     * Get a ZonedDateTime that indicates when the item was published.
     *
     * @return publication date
     */
    Optional<ZonedDateTime> getPubDateAsZonedDateTime();

    /**
     * Get a ZonedDateTime that indicates when the item was published.
     *
     * @deprecated
     * <p> Use {@link Item#getPubDateAsZonedDateTime()} instead.
     *
     * @return publication date
     */
    @SuppressWarnings("java:S1133")
    @Deprecated(since="3.12.0", forRemoval=true)
    Optional<ZonedDateTime> getPubDateZonedDateTime();

    /**
     * Get a string that indicates when the item was updated.
     *
     * @return updated date
     */
    Optional<String> getUpdated();

    /**
     * Set a string that indicates when the item was updated.
     *
     * @param updated updated date
     */
    void setUpdated(String updated);

    /**
     * Get a ZonedDateTime that indicates when the item was updated.
     *
     * @return publication date
     */
    Optional<ZonedDateTime> getUpdatedAsZonedDateTime();

    /**
     * Get a ZonedDateTime that indicates when the item was updated.
     *
     * @deprecated
     * <p> Use {@link Item#getUpdatedAsZonedDateTime()} instead.
     *
     * @return publication date
     */
    @SuppressWarnings("java:S1133")
    @Deprecated(since="3.12.0", forRemoval=true)
    Optional<ZonedDateTime> getUpdatedZonedDateTime();

    /**
     * Get comments relating to the item.
     * @return comments
     */
    Optional<String> getComments();

    /**
     * Set comments relating to the item.
     * @param comments comments
     */
    void setComments(String comments);

    /**
     * Get the enclosure of the item.
     *
     * @return enclosure
     */
    Optional<Enclosure> getEnclosure();

    /**
     * Set the enclosure of the item.
     *
     * @param enclosure enclosure
     */
    void setEnclosure(Enclosure enclosure);

    /**
     * Get enclosures for item.
     * Use this method if multiple enclosures exist per item.
     * @return list of enclosures
     */
    List<Enclosure> getEnclosures();

    /**
     * Add enclosure for item.
     * @param enclosure enclosure
     */
    void addEnclosure(Enclosure enclosure);

    /**
     * Get the channel that this item was published in.
     *
     * @return channel
     */
    Channel getChannel();

    /**
     * Set the channel that this item was published in.
     *
     * @param channel channel
     */
    void setChannel(Channel channel);
}
