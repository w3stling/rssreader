package com.apptasticsoftware.rssreader.module.podcast;

import com.apptasticsoftware.rssreader.Item;
import com.apptasticsoftware.rssreader.module.itunes.ItunesItem;

public interface PodcastItem extends Item, PodcastItemData, ItunesItem {

    /**
     * Retrieves the Podcast channel (feed) that this item belongs to.
     *
     * @return the PodcastChannel associated with this item
     */
    @Override
    PodcastChannel getChannel();
}
