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
import com.apptasticsoftware.rssreader.module.itunes.internal.ItunesItemDataProvider;
import com.apptasticsoftware.rssreader.module.mediarss.internal.MediaRssItemDataProvider;
import com.apptasticsoftware.rssreader.module.psc.internal.PscItemDataProvider;

public class SpotifyItemImpl extends ItemImpl implements SpotifyItem, ItunesItemDataProvider, MediaRssItemDataProvider, PscItemDataProvider {
    private final ItunesItemData itunesData = new ItunesItemDataImpl();
    private final MediaRssItemData mediaRssData = new MediaRssItemDataImpl();
    private final PscItemData pscData = new PscItemDataImpl();

    public SpotifyItemImpl(DateTimeParser dateTimeParser) {
        super(dateTimeParser);
    }

    @Override
    public ItunesItemData itunesItemData() {
        return itunesData;
    }

    @Override
    public MediaRssItemData mediaRssItemData() {
        return mediaRssData;
    }

    @Override
    public PscItemData pscItemData() {
        return pscData;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SpotifyItemImpl that = (SpotifyItemImpl) o;
        return Objects.equals(itunesItemData(), that.itunesItemData()) && Objects.equals(mediaRssItemData(), that.mediaRssItemData()) && Objects.equals(pscItemData(), that.pscItemData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), itunesItemData(), mediaRssItemData(), pscItemData());
    }
}
