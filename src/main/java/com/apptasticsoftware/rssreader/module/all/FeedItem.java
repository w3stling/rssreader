package com.apptasticsoftware.rssreader.module.all;

import com.apptasticsoftware.rssreader.Item;
import com.apptasticsoftware.rssreader.module.itunes.ItunesItem;
import com.apptasticsoftware.rssreader.module.mediarss.MediaRssItem;
import com.apptasticsoftware.rssreader.module.opensearch.OpenSearchItem;
import com.apptasticsoftware.rssreader.module.podcast.PodcastItem;
import com.apptasticsoftware.rssreader.module.psc.PscItem;
import com.apptasticsoftware.rssreader.module.spotify.SpotifyItem;
import com.apptasticsoftware.rssreader.module.youtube.YoutubeItem;

public interface FeedItem extends Item, ItunesItem, MediaRssItem, OpenSearchItem, PodcastItem, PscItem, SpotifyItem, YoutubeItem {

}
