package com.apptasticsoftware.rssreader.module.podcast.internal;

import com.apptasticsoftware.rssreader.internal.ChannelImpl;
import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.module.itunes.ItunesChannelData;
import com.apptasticsoftware.rssreader.module.itunes.internal.ItunesChannelDataImpl;
import com.apptasticsoftware.rssreader.module.podcast.PodcastChannel;
import com.apptasticsoftware.rssreader.module.podcast.PodcastChannelData;

import java.util.Objects;

public class PodcastChannelImpl extends ChannelImpl implements PodcastChannel {
    private final PodcastChannelDataImpl data = new PodcastChannelDataImpl();
    private final ItunesChannelDataImpl itunesData = new ItunesChannelDataImpl();

    /**
     * Constructor
     *
     * @param dateTimeParser timestamp parser
     */
    public PodcastChannelImpl(DateTimeParser dateTimeParser) {
        super(dateTimeParser);
    }

    @Override
    public PodcastChannelData getPodcastChannelData() {
        return data;
    }

    @Override
    public ItunesChannelData getItunesChannelData() {
        return itunesData;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PodcastChannelImpl that = (PodcastChannelImpl) o;
        return Objects.equals(data, that.data) && Objects.equals(itunesData, that.itunesData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), data, itunesData);
    }
}
