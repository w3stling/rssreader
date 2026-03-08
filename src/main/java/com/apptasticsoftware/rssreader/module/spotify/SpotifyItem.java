package com.apptasticsoftware.rssreader.module.spotify;

import com.apptasticsoftware.rssreader.Item;
import com.apptasticsoftware.rssreader.module.itunes.ItunesItem;
import com.apptasticsoftware.rssreader.module.mediarss.MediaRssItem;
import com.apptasticsoftware.rssreader.module.psc.PscItem;

public interface SpotifyItem extends Item, SpotifyItemData, ItunesItem, MediaRssItem, PscItem {

    /**
     * Retrieves the Spotify channel (feed) that this item belongs to.
     *
     * @return the SpotifyChannel associated with this item
     */
    @Override
    SpotifyChannel getChannel();

}
