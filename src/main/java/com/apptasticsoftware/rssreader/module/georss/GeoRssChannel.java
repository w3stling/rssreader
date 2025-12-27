package com.apptasticsoftware.rssreader.module.georss;

import com.apptasticsoftware.rssreader.Channel;

/**
 * Represents a GeoRSS channel with geographic extension data.
 */
public interface GeoRssChannel extends Channel, GeoRssChannelData {

    /**
     * Returns the GeoRSS channel data.
     *
     * @return the GeoRSS channel data
     */
    GeoRssChannelData getGeoRssChannelData();
}
