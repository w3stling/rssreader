package com.apptasticsoftware.rssreader.module.wfw;

import com.apptasticsoftware.rssreader.Item;

/**
 * Represents an RSS item (entry) with support for the Well-Formed Web Comment API (WFW) namespace.
 * This interface provides access to WFW comment-related properties for RSS items, allowing RSS readers
 * and aggregators to discover and interact with comment feeds and comment submission endpoints.
 * An item can contain:
 * <ul>
 *   <li>{@code wfw:commentRss} - URL of an RSS feed containing comments for the item</li>
 *   <li>{@code wfw:comment} - URL endpoint that accepts HTTP POST requests to submit comments</li>
 * </ul>
 * This interface extends both {@link Item} and {@link WfwItemData} to provide access to standard
 * RSS item properties as well as WFW-specific comment data.
 *
 * @see WfwItemData
 * @see <a href="http://wellformedweb.org/CommentAPI/">Well-Formed Web Comment API</a>
 */
public interface WfwItem extends Item, WfwItemData {
}
