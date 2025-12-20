package com.apptasticsoftware.rssreader.module.all;

import com.apptasticsoftware.rssreader.Channel;
import com.apptasticsoftware.rssreader.module.itunes.ItunesChannel;
import com.apptasticsoftware.rssreader.module.mediarss.MediaRssChannel;
import com.apptasticsoftware.rssreader.module.opensearch.OpenSearchChannel;
import com.apptasticsoftware.rssreader.module.podcast.PodcastChannel;
import com.apptasticsoftware.rssreader.module.psc.PscChannel;
import com.apptasticsoftware.rssreader.module.youtube.YoutubeChannel;

public interface FeedChannel extends Channel, ItunesChannel, MediaRssChannel, OpenSearchChannel, PodcastChannel, PscChannel, YoutubeChannel {
}
