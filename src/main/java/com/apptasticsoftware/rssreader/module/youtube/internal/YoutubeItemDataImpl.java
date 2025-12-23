package com.apptasticsoftware.rssreader.module.youtube.internal;

import com.apptasticsoftware.rssreader.module.youtube.YoutubeItemData;

import java.util.Objects;

public class YoutubeItemDataImpl implements YoutubeItemData {
    private String youtubeChannelId;
    private String youtubeVideoId;

    @Override
    public YoutubeItemData getYoutubeItemData() {
        return this;
    }

    @Override
    public String getYoutubeChannelId() {
        return youtubeChannelId;
    }

    @Override
    public void setYoutubeChannelId(String youtubeChannelId) {
        this.youtubeChannelId = youtubeChannelId;
    }

    @Override
    public String getYoutubeVideoId() {
        return youtubeVideoId;
    }

    @Override
    public void setYoutubeVideoId(String youtubeVideoId) {
        this.youtubeVideoId = youtubeVideoId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        YoutubeItemDataImpl that = (YoutubeItemDataImpl) o;
        return Objects.equals(getYoutubeChannelId(), that.getYoutubeChannelId()) && Objects.equals(getYoutubeVideoId(), that.getYoutubeVideoId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getYoutubeChannelId(), getYoutubeVideoId());
    }
}
