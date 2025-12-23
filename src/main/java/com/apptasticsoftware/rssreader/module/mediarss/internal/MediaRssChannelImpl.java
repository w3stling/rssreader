package com.apptasticsoftware.rssreader.module.mediarss.internal;

import com.apptasticsoftware.rssreader.internal.ChannelImpl;
import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.module.mediarss.MediaRssChannel;
import com.apptasticsoftware.rssreader.module.mediarss.MediaRssChannelData;

import java.util.Objects;

public class MediaRssChannelImpl extends ChannelImpl implements MediaRssChannel {
    private final MediaRssChannelData mediaRssChannelData = new MediaRssChannelDataImpl();

    public MediaRssChannelImpl(DateTimeParser dateTimeParser) {
        super(dateTimeParser);
    }

    @Override
    public MediaRssChannelData getMediaRssChannelData() {
        return mediaRssChannelData;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MediaRssChannel)) return false;
        if (!super.equals(o)) return false;
        MediaRssChannel that = (MediaRssChannel) o;
        return Objects.equals(getMediaRssChannelData(), that.getMediaRssChannelData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getMediaRssChannelData());
    }
}
