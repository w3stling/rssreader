package com.apptasticsoftware.rssreader.module.spotify;

import com.apptasticsoftware.rssreader.Item;
import com.apptasticsoftware.rssreader.module.itunes.ItunesItem;
import com.apptasticsoftware.rssreader.module.mediarss.MediaRssItem;

public interface SpotifyItem extends Item, SpotifyItemData, ItunesItem, MediaRssItem {

}
