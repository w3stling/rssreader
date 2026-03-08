package com.apptasticsoftware.rssreader.module.spotify.internal;

import com.apptasticsoftware.rssreader.internal.ChannelImpl;
import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.module.itunes.ItunesChannelData;
import com.apptasticsoftware.rssreader.module.itunes.internal.ItunesChannelDataImpl;
import com.apptasticsoftware.rssreader.module.mediarss.MediaRssChannelData;
import com.apptasticsoftware.rssreader.module.mediarss.internal.MediaRssChannelDataImpl;
import com.apptasticsoftware.rssreader.module.spotify.SpotifyChannel;
import com.apptasticsoftware.rssreader.module.spotify.SpotifyChannelData;

import java.util.Objects;
import com.apptasticsoftware.rssreader.module.itunes.internal.ItunesChannelDataProvider;
import com.apptasticsoftware.rssreader.module.mediarss.internal.MediaRssChannelDataProvider;

public class SpotifyChannelImpl extends ChannelImpl implements SpotifyChannel, SpotifyChannelDataProvider, ItunesChannelDataProvider, MediaRssChannelDataProvider {
    private final SpotifyChannelData spotifyData = new SpotifyChannelDataImpl();
    private final ItunesChannelData itunesData = new ItunesChannelDataImpl();
    private final MediaRssChannelData mediaRssData = new MediaRssChannelDataImpl();

    public SpotifyChannelImpl(DateTimeParser dateTimeParser) {
        super(dateTimeParser);
    }

    @Override
    public SpotifyChannelData spotifyChannelData() {
        return spotifyData;
    }

    @Override
    public ItunesChannelData itunesChannelData() {
        return itunesData;
    }

    @Override
    public MediaRssChannelData mediaRssChannelData() {
        return mediaRssData;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SpotifyChannelImpl that = (SpotifyChannelImpl) o;
        return Objects.equals(spotifyChannelData(), that.spotifyChannelData()) && Objects.equals(itunesChannelData(), that.itunesChannelData()) && Objects.equals(mediaRssChannelData(), that.mediaRssChannelData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), spotifyChannelData(), itunesChannelData(), mediaRssChannelData());
    }
}
