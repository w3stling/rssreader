package com.apptasticsoftware.rssreader.module.georss.internal;

import com.apptasticsoftware.rssreader.module.georss.GeoRssItemData;

/**
 * Implementation of GeoRssItemData containing GeoRSS item geographic information.
 */
public class GeoRssItemDataImpl extends MetaData implements GeoRssItemData, GeoRssItemDataProvider {

    /**
     * Returns the underlying GeoRSS item data.
     *
     * @return this instance
     */
    @Override
    public GeoRssItemData geoRssItemData() {
        return this;
    }
}
