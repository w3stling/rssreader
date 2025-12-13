package com.apptasticsoftware.rssreader.module.spotify;

import com.apptasticsoftware.rssreader.Channel;
import com.apptasticsoftware.rssreader.module.itunes.ItunesChannel;
import com.apptasticsoftware.rssreader.module.mediarss.MediaRssChannel;

public interface SpotifyChannel extends Channel, SpotifyChannelData, ItunesChannel, MediaRssChannel {

}
