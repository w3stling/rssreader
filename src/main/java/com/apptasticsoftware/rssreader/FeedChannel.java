package com.apptasticsoftware.rssreader;

import com.apptasticsoftware.rssreader.module.dc.DcChannel;
import com.apptasticsoftware.rssreader.module.georss.GeoRssChannel;
import com.apptasticsoftware.rssreader.module.itunes.ItunesChannel;
import com.apptasticsoftware.rssreader.module.mediarss.MediaRssChannel;
import com.apptasticsoftware.rssreader.module.opensearch.OpenSearchChannel;
import com.apptasticsoftware.rssreader.module.podcast.PodcastChannel;
import com.apptasticsoftware.rssreader.module.psc.PscChannel;
import com.apptasticsoftware.rssreader.module.slash.SlashChannel;
import com.apptasticsoftware.rssreader.module.spotify.SpotifyChannel;
import com.apptasticsoftware.rssreader.module.wfw.WfwChannel;
import com.apptasticsoftware.rssreader.module.youtube.YoutubeChannel;

public interface FeedChannel extends Channel, DcChannel, GeoRssChannel, ItunesChannel, MediaRssChannel, OpenSearchChannel, PodcastChannel, PscChannel, SlashChannel, SpotifyChannel, WfwChannel, YoutubeChannel {
}
