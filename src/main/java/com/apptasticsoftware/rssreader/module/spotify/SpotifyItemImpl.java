package com.apptasticsoftware.rssreader.module.spotify;

import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.ItemImpl;
import com.apptasticsoftware.rssreader.module.itunes.ItunesItemData;
import com.apptasticsoftware.rssreader.module.itunes.ItunesItemDataImpl;
import com.apptasticsoftware.rssreader.module.mediarss.MediaRssItemData;
import com.apptasticsoftware.rssreader.module.mediarss.MediaRssItemDataImpl;

import java.util.Objects;

public class SpotifyItemImpl extends ItemImpl implements SpotifyItem {
    private final SpotifyItemData spotifyData = new SpotifyItemDataImpl();
    private final ItunesItemData itunesData = new ItunesItemDataImpl();
    private final MediaRssItemData mediaRssData = new MediaRssItemDataImpl();

    public SpotifyItemImpl(DateTimeParser dateTimeParser) {
        super(dateTimeParser);
    }

    @Override
    public SpotifyItemData getSpotifyItemData() {
        return spotifyData;
    }

    @Override
    public ItunesItemData getItunesItemData() {
        return itunesData;
    }

    @Override
    public MediaRssItemData getMediaRssItemData() {
        return mediaRssData;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SpotifyItemImpl that = (SpotifyItemImpl) o;
        return Objects.equals(getSpotifyItemData(), that.getSpotifyItemData()) && Objects.equals(getItunesItemData(), that.getItunesItemData()) && Objects.equals(getMediaRssItemData(), that.getMediaRssItemData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getSpotifyItemData(), getItunesItemData(), getMediaRssItemData());
    }
}
