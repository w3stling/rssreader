package com.apptasticsoftware.rssreader.module.slash.internal;

import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.internal.ChannelImpl;
import com.apptasticsoftware.rssreader.module.slash.SlashChannel;

public class SlashChannelImpl extends ChannelImpl implements SlashChannel {

    public SlashChannelImpl(DateTimeParser dateTimeParser) {
        super(dateTimeParser);
    }
}
