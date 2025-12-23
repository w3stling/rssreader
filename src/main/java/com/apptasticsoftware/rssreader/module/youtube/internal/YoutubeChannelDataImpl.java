package com.apptasticsoftware.rssreader.module.youtube.internal;

import com.apptasticsoftware.rssreader.module.youtube.YoutubeChannelData;

import java.util.Objects;

public class YoutubeChannelDataImpl implements YoutubeChannelData {
    private String youtubeChannelId;

    @Override
    public YoutubeChannelData getYoutubeChannelData() {
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
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        YoutubeChannelDataImpl that = (YoutubeChannelDataImpl) o;
        return Objects.equals(getYoutubeChannelId(), that.getYoutubeChannelId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getYoutubeChannelId());
    }
}
