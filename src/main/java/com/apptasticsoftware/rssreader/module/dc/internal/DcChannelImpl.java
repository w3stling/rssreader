package com.apptasticsoftware.rssreader.module.dc.internal;

import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.internal.ChannelImpl;
import com.apptasticsoftware.rssreader.module.dc.DcChannel;
import com.apptasticsoftware.rssreader.module.dc.DcChannelData;

import java.util.Objects;

/**
 * Implementation of RSS channel with Dublin Core metadata support.
 */
public class DcChannelImpl extends ChannelImpl implements DcChannel {
    private final DcChannelDataImpl dcData = new DcChannelDataImpl();

    /**
     * Constructs a Dublin Core channel with the specified date-time parser.
     * @param dateTimeParser date-time parser
     */
    public DcChannelImpl(DateTimeParser dateTimeParser) {
        super(dateTimeParser);
    }

    @Override
    public DcChannelData getDcChannelData() {
        return dcData;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DcChannelImpl)) return false;
        if (!super.equals(o)) return false;
        DcChannelImpl dcChannel = (DcChannelImpl) o;
        return Objects.equals(getDcDate(), dcChannel.getDcDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getDcDate());
    }
}
