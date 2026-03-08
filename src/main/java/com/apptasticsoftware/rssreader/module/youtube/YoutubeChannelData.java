package com.apptasticsoftware.rssreader.module.youtube;

import com.apptasticsoftware.rssreader.module.youtube.internal.YoutubeChannelDataProvider;

public interface YoutubeChannelData {
    default String getYoutubeChannelId() {
        return ((YoutubeChannelDataProvider) this).youtubeChannelData().getYoutubeChannelId();
    }

    default void setYoutubeChannelId(String youtubeChannelId) {
        ((YoutubeChannelDataProvider) this).youtubeChannelData().setYoutubeChannelId(youtubeChannelId);
    }
}
