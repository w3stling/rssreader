package com.apptasticsoftware.rssreader.module.spotify;

import com.apptasticsoftware.rssreader.Item;
import com.apptasticsoftware.rssreader.module.itunes.ItunesItem;
import com.apptasticsoftware.rssreader.module.mediarss.MediaRssItem;
import com.apptasticsoftware.rssreader.module.psc.PscItem;

public interface SpotifyItem extends Item, SpotifyItemData, ItunesItem, MediaRssItem, PscItem {

}
