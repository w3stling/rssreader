package com.apptasticsoftware.rssreader.module.mediarss.internal;

import com.apptasticsoftware.rssreader.internal.ChannelImpl;
import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.module.mediarss.MediaRssChannel;
import com.apptasticsoftware.rssreader.module.mediarss.MediaRssChannelData;

import java.util.Objects;

public class MediaRssChannelImpl extends ChannelImpl implements MediaRssChannel, MediaRssChannelDataProvider {
    private final MediaRssChannelData mediaRssChannelData = new MediaRssChannelDataImpl();

    public MediaRssChannelImpl(DateTimeParser dateTimeParser) {
        super(dateTimeParser);
    }

    @Override
    public MediaRssChannelData mediaRssChannelData() {
        return mediaRssChannelData;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MediaRssChannelImpl)) return false;
        if (!super.equals(o)) return false;
        MediaRssChannelImpl that = (MediaRssChannelImpl) o;
        return Objects.equals(mediaRssChannelData, that.mediaRssChannelData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), mediaRssChannelData());
    }
}
