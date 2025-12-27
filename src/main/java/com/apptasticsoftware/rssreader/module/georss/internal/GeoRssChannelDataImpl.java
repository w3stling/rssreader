package com.apptasticsoftware.rssreader.module.georss.internal;

import com.apptasticsoftware.rssreader.module.georss.GeoRssChannelData;

/**
 * Implementation of GeoRssChannelData containing GeoRSS channel geographic information.
 */
public class GeoRssChannelDataImpl extends MetaData implements GeoRssChannelData {

    /**
     * Returns the underlying GeoRSS channel data.
     *
     * @return this instance
     */
    public GeoRssChannelData getGeoRssChannelData() {
        return this;
    }
}
