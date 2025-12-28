package com.apptasticsoftware.rssreader.module.wfw;

import com.apptasticsoftware.rssreader.Channel;

/**
 * Represents an RSS channel (feed) with support for the Well-Formed Web Comment API (WFW) namespace.
 * The WFW namespace enables RSS feeds to support comments by publicizing URLs of feeds that contain
 * an item's comments and providing a REST interface for RSS readers to submit comments.
 * This interface extends both {@link Channel} and {@link WfwChannelData} to provide access to
 * standard RSS channel properties as well as WFW-specific data.
 *
 * @see <a href="http://wellformedweb.org/CommentAPI/">Well-Formed Web Comment API</a>
 */
public interface WfwChannel extends Channel, WfwChannelData {
}
