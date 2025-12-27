package com.apptasticsoftware.rssreader.module.georss.internal;

import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.internal.ChannelImpl;
import com.apptasticsoftware.rssreader.module.georss.GeoRssChannel;
import com.apptasticsoftware.rssreader.module.georss.GeoRssChannelData;

import java.util.Objects;

/**
 * Implementation of GeoRssChannel with geographic extension support.
 */
public class GeoRssChannelImpl extends ChannelImpl implements GeoRssChannel {
    private final GeoRssChannelData geoRssData = new GeoRssChannelDataImpl();

    /**
     * Constructs a GeoRssChannelImpl with the specified date time parser.
     *
     * @param dateTimeParser the date time parser
     */
    public GeoRssChannelImpl(DateTimeParser dateTimeParser) {
        super(dateTimeParser);
    }

    /**
     * Returns the GeoRSS channel data.
     *
     * @return the GeoRSS channel data
     */
    @Override
    public GeoRssChannelData getGeoRssChannelData() {
        return geoRssData;
    }

    /**
     * Compares this channel with another object for equality.
     *
     * @param o the object to compare with
     * @return true if both channels are equal
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof GeoRssChannelImpl)) return false;
        if (!super.equals(o)) return false;
        GeoRssChannelImpl that = (GeoRssChannelImpl) o;
        return Objects.equals(getGeoRssChannelData(), that.getGeoRssChannelData());
    }

    /**
     * Returns the hash code for this channel.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getGeoRssChannelData());
    }
}
