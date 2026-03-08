package com.apptasticsoftware.rssreader.module.mediarss;

import com.apptasticsoftware.rssreader.Item;

public interface MediaRssItem extends MediaRssItemData, Item {

    /**
     * Retrieves the Media RSS channel (feed) that this item belongs to.
     *
     * @return the MediaRssChannel associated with this item
     */
    @Override
    MediaRssChannel getChannel();

}
