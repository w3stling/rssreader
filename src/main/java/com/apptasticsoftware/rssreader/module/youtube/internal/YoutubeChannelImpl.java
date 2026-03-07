package com.apptasticsoftware.rssreader.module.youtube.internal;

import com.apptasticsoftware.rssreader.internal.ChannelImpl;
import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.module.mediarss.MediaRssChannelData;
import com.apptasticsoftware.rssreader.module.mediarss.internal.MediaRssChannelDataImpl;
import com.apptasticsoftware.rssreader.module.youtube.YoutubeChannel;
import com.apptasticsoftware.rssreader.module.youtube.YoutubeChannelData;

import java.util.Objects;
import com.apptasticsoftware.rssreader.module.mediarss.internal.MediaRssChannelDataProvider;

public class YoutubeChannelImpl extends ChannelImpl implements YoutubeChannel, YoutubeChannelDataProvider, MediaRssChannelDataProvider {
    private final YoutubeChannelData youtubeData = new YoutubeChannelDataImpl();
    private final MediaRssChannelData mediaRssData = new MediaRssChannelDataImpl();

    public YoutubeChannelImpl(DateTimeParser dateTimeParser) {
        super(dateTimeParser);
    }

    @Override
    public YoutubeChannelData youtubeChannelData() {
        return youtubeData;
    }

    @Override
    public MediaRssChannelData mediaRssChannelData() {
        return mediaRssData;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        YoutubeChannelImpl that = (YoutubeChannelImpl) o;
        return Objects.equals(youtubeChannelData(), that.youtubeChannelData()) && Objects.equals(mediaRssChannelData(), that.mediaRssChannelData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), youtubeChannelData(), mediaRssChannelData());
    }
}
