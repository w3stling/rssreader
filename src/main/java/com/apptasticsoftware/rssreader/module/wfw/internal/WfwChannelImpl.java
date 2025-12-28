package com.apptasticsoftware.rssreader.module.wfw.internal;

import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.internal.ChannelImpl;
import com.apptasticsoftware.rssreader.module.wfw.WfwChannel;

/**
 * Implementation of {@link WfwChannel} for Well-Formed Web Comment API (WFW) support.
 * This class extends {@link ChannelImpl} to provide a concrete implementation of the WfwChannel interface,
 * enabling parsing and access to WFW comment-related properties in RSS feeds.
 *
 * @see WfwChannel
 * @see ChannelImpl
 */
public class WfwChannelImpl extends ChannelImpl implements WfwChannel {

    /**
     * Creates a new WFW-enabled channel instance.
     *
     * @param dateTimeParser the parser for converting date/time strings in the channel
     */
    public WfwChannelImpl(DateTimeParser dateTimeParser) {
        super(dateTimeParser);
    }
}
