package com.apptasticsoftware.rssreader.module.spotify.internal;

import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.internal.ItemImpl;
import com.apptasticsoftware.rssreader.module.itunes.ItunesItemData;
import com.apptasticsoftware.rssreader.module.itunes.internal.ItunesItemDataImpl;
import com.apptasticsoftware.rssreader.module.mediarss.MediaRssItemData;
import com.apptasticsoftware.rssreader.module.mediarss.internal.MediaRssItemDataImpl;
import com.apptasticsoftware.rssreader.module.psc.PscItemData;
import com.apptasticsoftware.rssreader.module.psc.internal.PscItemDataImpl;
import com.apptasticsoftware.rssreader.module.spotify.SpotifyItem;

import java.util.Objects;

public class SpotifyItemImpl extends ItemImpl implements SpotifyItem {
    private final ItunesItemData itunesData = new ItunesItemDataImpl();
    private final MediaRssItemData mediaRssData = new MediaRssItemDataImpl();
    private final PscItemData pscData = new PscItemDataImpl();

    public SpotifyItemImpl(DateTimeParser dateTimeParser) {
        super(dateTimeParser);
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
    public PscItemData getPscItemData() {
        return pscData;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SpotifyItemImpl that = (SpotifyItemImpl) o;
        return Objects.equals(getItunesItemData(), that.getItunesItemData()) && Objects.equals(getMediaRssItemData(), that.getMediaRssItemData()) && Objects.equals(getPscItemData(), that.getPscItemData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getItunesItemData(), getMediaRssItemData(), getPscItemData());
    }
}
