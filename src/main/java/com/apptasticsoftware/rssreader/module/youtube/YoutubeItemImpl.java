package com.apptasticsoftware.rssreader.module.youtube;

import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.ItemImpl;
import com.apptasticsoftware.rssreader.module.mediarss.MediaRssItemData;
import com.apptasticsoftware.rssreader.module.mediarss.MediaRssItemDataImpl;

import java.util.Objects;

public class YoutubeItemImpl extends ItemImpl implements YoutubeItem {
    private final YoutubeItemData youtubeData = new YoutubeItemDataImpl();
    private final MediaRssItemData mediaRssData = new MediaRssItemDataImpl();

    public YoutubeItemImpl(DateTimeParser dateTimeParser) {
        super(dateTimeParser);
    }

    @Override
    public YoutubeItemData getYoutubeItemData() {
        return youtubeData;
    }

    @Override
    public MediaRssItemData getMediaRssItemData() {
        return mediaRssData;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        YoutubeItemImpl that = (YoutubeItemImpl) o;
        return Objects.equals(getYoutubeItemData(), that.getYoutubeItemData()) && Objects.equals(getMediaRssItemData(), that.getMediaRssItemData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getYoutubeItemData(), getMediaRssItemData());
    }
}
