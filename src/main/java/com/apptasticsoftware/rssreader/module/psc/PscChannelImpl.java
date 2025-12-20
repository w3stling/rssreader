package com.apptasticsoftware.rssreader.module.psc;

import com.apptasticsoftware.rssreader.ChannelImpl;
import com.apptasticsoftware.rssreader.DateTimeParser;

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
