package com.apptasticsoftware.rssreader.module.slash.internal;

import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.internal.ChannelImpl;
import com.apptasticsoftware.rssreader.module.slash.SlashChannel;

/**
 * Implementation of SlashChannel extending core channel functionality.
 */
public class SlashChannelImpl extends ChannelImpl implements SlashChannel {

    /**
     * Constructs a SlashChannelImpl with the provided date-time parser.
     *
     * @param dateTimeParser the parser for parsing date-time values
     */
    public SlashChannelImpl(DateTimeParser dateTimeParser) {
        super(dateTimeParser);
    }
}
