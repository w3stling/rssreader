package com.apptasticsoftware.rssreader.module.georss.internal;

import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.internal.ChannelImpl;
import com.apptasticsoftware.rssreader.module.georss.GeoRssChannel;
import com.apptasticsoftware.rssreader.module.georss.GeoRssChannelData;

import java.util.Objects;

public class GeoRssChannelImpl extends ChannelImpl implements GeoRssChannel {
    private final GeoRssChannelData geoRssData = new GeoRssChannelDataImpl();

    /**
     * Constructor
     * @param dateTimeParser timestamp parser
     */
    public GeoRssChannelImpl(DateTimeParser dateTimeParser) {
        super(dateTimeParser);
    }

    @Override
    public GeoRssChannelData getGeoRssChannelData() {
        return geoRssData;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof GeoRssChannelImpl)) return false;
        if (!super.equals(o)) return false;
        GeoRssChannelImpl that = (GeoRssChannelImpl) o;
        return Objects.equals(getGeoRssChannelData(), that.getGeoRssChannelData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getGeoRssChannelData());
    }
}
