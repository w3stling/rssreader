package com.apptasticsoftware.rssreader.module.wfw;

import java.util.Optional;

/**
 * Interface providing access to Well-Formed Web Comment API (WFW) data for RSS items.
 * This interface defines methods for accessing and setting WFW comment-related properties,
 * including comment RSS feed URLs and comment submission endpoints.
 *
 * <p>The WFW namespace was introduced in 2003 and has become the most popular namespace
 * for supporting comments in RSS feeds.</p>
 *
 * @see <a href="http://wellformedweb.org/CommentAPI/">Well-Formed Web Comment API</a>
 */
public interface WfwItemData {

    /**
     * Returns the underlying WFW item data implementation.
     *
     * @return the WFW item data instance
     */
    WfwItemData getWfWItemData();

    /**
     * Returns the URL of an RSS feed that contains user comments for this item.
     * This corresponds to the {@code wfw:commentRss} element in the item.
     *
     * <p>Note: The WFW specification indicates that some documentation has incorrectly
     * referred to {@code wfw:commentRSS} (with uppercase RSS), though {@code wfw:commentRss}
     * is the correct element name. Software reading feeds should accept both spellings.</p>
     *
     * @return an Optional containing the comment RSS feed URL, or an empty Optional if not present
     */
    default Optional<String> getWfwCommentRss() {
        return getWfWItemData().getWfwCommentRss();
    }

    /**
     * Sets the URL of an RSS feed that contains user comments for this item.
     * This corresponds to the {@code wfw:commentRss} element in the item.
     *
     * <p>An item must not contain more than one {@code wfw:commentRss} element.</p>
     *
     * @param wfwCommentRss the URL of the comment RSS feed, must be a valid URL
     */
    default void setWfwCommentRss(String wfwCommentRss) {
        getWfWItemData().setWfwCommentRss(wfwCommentRss);
    }

    /**
     * Returns the URL endpoint that accepts HTTP POST requests to submit comments for this item.
     * This corresponds to the {@code wfw:comment} element in the item.
     *
     * <p>This endpoint is intended for use by RSS readers and other aggregators so they can
     * offer a form to users where a comment can be entered and submitted to the publisher.</p>
     *
     * <p>The POST request should have a "Content-Type" header with value "text/xml" and a body
     * containing XML resembling an RSS 2.0 feed item with optional elements: title, author, link,
     * description, source, and the Dublin Core creator element. At minimum, either a title or
     * description must be present.</p>
     *
     * @return an Optional containing the comment submission endpoint URL, or an empty Optional if not present
     */
    default Optional<String> getWfwComment() {
        return getWfWItemData().getWfwComment();
    }

    /**
     * Sets the URL endpoint that accepts HTTP POST requests to submit comments for this item.
     * This corresponds to the {@code wfw:comment} element in the item.
     *
     * @param wfwComment the URL endpoint for comment submission, must be a valid URL
     */
    default void setWfwComment(String wfwComment) {
        getWfWItemData().setWfwComment(wfwComment);
    }
}
