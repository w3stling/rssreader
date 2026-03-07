package com.apptasticsoftware.rssreader.module.youtube.internal;

import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.internal.ItemImpl;
import com.apptasticsoftware.rssreader.module.mediarss.MediaRssItemData;
import com.apptasticsoftware.rssreader.module.mediarss.internal.MediaRssItemDataImpl;
import com.apptasticsoftware.rssreader.module.youtube.YoutubeItem;
import com.apptasticsoftware.rssreader.module.youtube.YoutubeItemData;

import java.util.Objects;
import com.apptasticsoftware.rssreader.module.mediarss.internal.MediaRssItemDataProvider;

public class YoutubeItemImpl extends ItemImpl implements YoutubeItem, YoutubeItemDataProvider, MediaRssItemDataProvider {
    private final YoutubeItemData youtubeData = new YoutubeItemDataImpl();
    private final MediaRssItemData mediaRssData = new MediaRssItemDataImpl();

    public YoutubeItemImpl(DateTimeParser dateTimeParser) {
        super(dateTimeParser);
    }

    @Override
    public YoutubeItemData youtubeItemData() {
        return youtubeData;
    }

    @Override
    public MediaRssItemData mediaRssItemData() {
        return mediaRssData;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        YoutubeItemImpl that = (YoutubeItemImpl) o;
        return Objects.equals(youtubeItemData(), that.youtubeItemData()) && Objects.equals(mediaRssItemData(), that.mediaRssItemData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), youtubeItemData(), mediaRssItemData());
    }
}
