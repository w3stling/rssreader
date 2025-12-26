package com.apptasticsoftware.rssreader.module.georss.internal;

import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.internal.ChannelImpl;
import com.apptasticsoftware.rssreader.module.georss.GeoRssChannel;

public class GeoRssChannelImpl extends ChannelImpl implements GeoRssChannel {

    /**
     * Constructor
     * @param dateTimeParser timestamp parser
     */
    public GeoRssChannelImpl(DateTimeParser dateTimeParser) {
        super(dateTimeParser);
    }
}
