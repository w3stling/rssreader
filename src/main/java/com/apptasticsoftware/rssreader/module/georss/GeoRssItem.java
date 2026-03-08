package com.apptasticsoftware.rssreader.module.georss;

import com.apptasticsoftware.rssreader.Item;

/**
 * Represents a GeoRSS item with geographic extension data.
 */
public interface GeoRssItem extends Item, GeoRssItemData {

    /**
     * Retrieves the GeoRSS channel (feed) that this item belongs to.
     *
     * @return the GeoRssChannel associated with this item
     */
    @Override
    GeoRssChannel getChannel();

}
