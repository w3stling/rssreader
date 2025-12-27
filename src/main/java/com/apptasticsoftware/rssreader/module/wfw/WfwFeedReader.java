package com.apptasticsoftware.rssreader.module.wfw;

import com.apptasticsoftware.rssreader.AbstractRssReader;
import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.module.wfw.internal.WfwChannelImpl;
import com.apptasticsoftware.rssreader.module.wfw.internal.WfwItemImpl;

/**
 * RSS feed reader with support for the Well-Formed Web Comment API (WFW) namespace.
 * This reader extends {@link AbstractRssReader} to parse RSS feeds that include WFW comment-related
 * elements, allowing access to comment RSS feeds and comment submission endpoints.
 *
 * <p>The WFW namespace was introduced in 2003 and provides two ways for RSS feeds to support comments:
 * <ul>
 *   <li>Publicizing the URL of feeds that contain an item's comments ({@code wfw:commentRss})</li>
 *   <li>Providing a REST interface for RSS readers to submit comments ({@code wfw:comment})</li>
 * </ul>
 * </p>
 *
 * @see WfwChannel
 * @see WfwItem
 * @see WfwExtensions
 * @see <a href="http://wellformedweb.org/CommentAPI/">Well-Formed Web Comment API</a>
 */
public class WfwFeedReader extends AbstractRssReader<WfwChannel, WfwItem> {

    /**
     * Creates a new WFW-enabled RSS channel instance.
     *
     * @param dateTimeParser the parser for converting date/time strings in the feed
     * @return a new WfwChannel implementation
     */
    @Override
    protected WfwChannel createChannel(DateTimeParser dateTimeParser) {
        return new WfwChannelImpl(dateTimeParser);
    }

    /**
     * Creates a new WFW-enabled RSS item instance.
     *
     * @param dateTimeParser the parser for converting date/time strings in the feed
     * @return a new WfwItem implementation
     */
    @Override
    protected WfwItem createItem(DateTimeParser dateTimeParser) {
        return new WfwItemImpl(dateTimeParser);
    }

    /**
     * Registers WFW channel tags and extensions.
     * This method is called during feed parser initialization to register all WFW-specific elements
     * and their handlers with the feed extension registry.
     */
    @Override
    protected void registerChannelTags() {
        super.registerChannelTags();
        var registry = getFeedExtensionRegistry();
        WfwExtensions.register(registry);
    }
}
