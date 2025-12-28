package com.apptasticsoftware.rssreader.module.wfw.internal;

import com.apptasticsoftware.rssreader.module.wfw.WfwItemData;

import java.util.Objects;
import java.util.Optional;

/**
 * Implementation of {@link WfwItemData} that stores WFW (Well-Formed Web Comment API) properties for RSS items.
 * This class maintains the WFW-specific data such as comment RSS feed URLs and comment submission endpoints.
 *
 * Each WfwItemDataImpl instance stores:
 * <ul>
 *   <li>{@code wfwCommentRss} - URL of an RSS feed containing comments for the item (optional)</li>
 *   <li>{@code wfwComment} - URL endpoint for submitting comments to the item (optional)</li>
 * </ul>
 *
 * @see WfwItemData
 */
public class WfwItemDataImpl implements WfwItemData {
    private String wfwCommentRss;
    private String wfwComment;

    /**
     * Returns this object as its own WfwItemData implementation.
     *
     * @return this WfwItemDataImpl instance
     */
    @Override
    public WfwItemData getWfWItemData() {
        return this;
    }

    /**
     * Returns the URL of an RSS feed that contains user comments for the item.
     * This corresponds to the {@code wfw:commentRss} element.
     *
     * @return an Optional containing the comment RSS feed URL, or an empty Optional if not set
     */
    @Override
    public Optional<String> getWfwCommentRss() {
        return Optional.ofNullable(wfwCommentRss);
    }

    /**
     * Sets the URL of an RSS feed that contains user comments for the item.
     * This corresponds to the {@code wfw:commentRss} element.
     *
     * @param wfwCommentRss the URL of the comment RSS feed
     */
    @Override
    public void setWfwCommentRss(String wfwCommentRss) {
        this.wfwCommentRss = wfwCommentRss;
    }

    /**
     * Returns the URL endpoint that accepts HTTP POST requests to submit comments for the item.
     * This corresponds to the {@code wfw:comment} element.
     *
     * @return an Optional containing the comment submission endpoint URL, or an empty Optional if not set
     */
    @Override
    public Optional<String> getWfwComment() {
        return Optional.ofNullable(wfwComment);
    }

    /**
     * Sets the URL endpoint that accepts HTTP POST requests to submit comments for the item.
     * This corresponds to the {@code wfw:comment} element.
     *
     * @param wfwComment the URL endpoint for comment submission
     */
    @Override
    public void setWfwComment(String wfwComment) {
        this.wfwComment = wfwComment;
    }

    /**
     * Compares this WfwItemDataImpl with another object for equality.
     * Two WfwItemDataImpl instances are considered equal if they have the same comment RSS URL and comment endpoint URL.
     *
     * @param o the object to compare with
     * @return true if this object is equal to the other object, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        WfwItemDataImpl that = (WfwItemDataImpl) o;
        return Objects.equals(getWfwCommentRss(), that.getWfwCommentRss()) && Objects.equals(getWfwComment(), that.getWfwComment());
    }

    /**
     * Returns the hash code for this WfwItemDataImpl.
     * The hash code is computed from the comment RSS URL and comment endpoint URL.
     *
     * @return the hash code for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(getWfwCommentRss(), getWfwComment());
    }
}
