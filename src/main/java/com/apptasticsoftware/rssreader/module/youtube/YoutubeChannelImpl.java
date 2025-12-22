package com.apptasticsoftware.rssreader.module.youtube;

import com.apptasticsoftware.rssreader.internal.ChannelImpl;
import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.module.mediarss.MediaRssChannelData;
import com.apptasticsoftware.rssreader.module.mediarss.MediaRssChannelDataImpl;

import java.util.Objects;

public class YoutubeChannelImpl extends ChannelImpl implements YoutubeChannel {
    private final YoutubeChannelData youtubeData = new YoutubeChannelDataImpl();
    private final MediaRssChannelData mediaRssData = new MediaRssChannelDataImpl();

    public YoutubeChannelImpl(DateTimeParser dateTimeParser) {
        super(dateTimeParser);
    }

    @Override
    public YoutubeChannelData getYoutubeChannelData() {
        return youtubeData;
    }

    @Override
    public MediaRssChannelData getMediaRssChannelData() {
        return mediaRssData;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        YoutubeChannelImpl that = (YoutubeChannelImpl) o;
        return Objects.equals(getYoutubeChannelData(), that.getYoutubeChannelData()) && Objects.equals(getMediaRssChannelData(), that.getMediaRssChannelData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getYoutubeChannelData(), getMediaRssChannelData());
    }
}
