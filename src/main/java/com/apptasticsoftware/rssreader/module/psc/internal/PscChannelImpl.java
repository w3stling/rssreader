package com.apptasticsoftware.rssreader.module.psc.internal;

import com.apptasticsoftware.rssreader.internal.ChannelImpl;
import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.module.psc.PscChannel;

/**
 * Implementation of a podcast channel with Podcast Index Specification (PSC) data.
 *
 * @see <a href="https://podcastindex.org/namespace/">Podcast Index Specification</a>
 */
public class PscChannelImpl extends ChannelImpl implements PscChannel {

    public PscChannelImpl(DateTimeParser dateTimeParser) {
        super(dateTimeParser);
    }
}
