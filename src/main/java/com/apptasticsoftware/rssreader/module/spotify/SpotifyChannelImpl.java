package com.apptasticsoftware.rssreader.module.spotify;

import com.apptasticsoftware.rssreader.internal.ChannelImpl;
import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.module.itunes.ItunesChannelData;
import com.apptasticsoftware.rssreader.module.itunes.ItunesChannelDataImpl;
import com.apptasticsoftware.rssreader.module.mediarss.MediaRssChannelData;
import com.apptasticsoftware.rssreader.module.mediarss.MediaRssChannelDataImpl;

import java.util.Objects;

public class SpotifyChannelImpl extends ChannelImpl implements SpotifyChannel {
    private final SpotifyChannelData spotifyData = new SpotifyChannelDataImpl();
    private final ItunesChannelData itunesData = new ItunesChannelDataImpl();
    private final MediaRssChannelData mediaRssData = new MediaRssChannelDataImpl();

    public SpotifyChannelImpl(DateTimeParser dateTimeParser) {
        super(dateTimeParser);
    }

    @Override
    public SpotifyChannelData getSpotifyChannelData() {
        return spotifyData;
    }

    @Override
    public ItunesChannelData getItunesChannelData() {
        return itunesData;
    }

    @Override
    public MediaRssChannelData getMediaRssChannelData() {
        return mediaRssData;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SpotifyChannelImpl that = (SpotifyChannelImpl) o;
        return Objects.equals(getSpotifyChannelData(), that.getSpotifyChannelData()) && Objects.equals(getItunesChannelData(), that.getItunesChannelData()) && Objects.equals(getMediaRssChannelData(), that.getMediaRssChannelData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getSpotifyChannelData(), getItunesChannelData(), getMediaRssChannelData());
    }
}
