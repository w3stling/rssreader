package com.apptasticsoftware.rssreader.module.youtube;

import com.apptasticsoftware.rssreader.Item;
import com.apptasticsoftware.rssreader.module.mediarss.MediaRssItem;

public interface YoutubeItem extends Item, YoutubeItemData, MediaRssItem {

    /**
     * Retrieves the YouTube channel (feed) that this item belongs to.
     *
     * @return the YoutubeChannel associated with this item
     */
    @Override
    YoutubeChannel getChannel();

}
