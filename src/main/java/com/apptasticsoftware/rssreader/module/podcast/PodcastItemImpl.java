package com.apptasticsoftware.rssreader.module.podcast;

import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.ItemImpl;
import com.apptasticsoftware.rssreader.module.itunes.ItunesItemData;
import com.apptasticsoftware.rssreader.module.itunes.ItunesItemDataImpl;

import java.util.Objects;

public class PodcastItemImpl extends ItemImpl implements PodcastItem {
    private final PodcastItemDataImpl data;
    private final ItunesItemDataImpl itunesData;

    /**
     * Constructor
     *
     * @param dateTimeParser timestamp parser
     */
    public PodcastItemImpl(DateTimeParser dateTimeParser) {
        super(dateTimeParser);
        data = new PodcastItemDataImpl(dateTimeParser);
        itunesData = new ItunesItemDataImpl();
    }

    @Override
    public PodcastItemData getPodcastItemData() {
        return data;
    }

    public ItunesItemData getItunesItemData() {
        return itunesData;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PodcastItemImpl that = (PodcastItemImpl) o;
        return Objects.equals(data, that.data) && Objects.equals(itunesData, that.itunesData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), data, itunesData);
    }
}
