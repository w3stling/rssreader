package com.apptasticsoftware.rssreader.module.youtube;

import com.apptasticsoftware.rssreader.module.youtube.internal.YoutubeItemDataProvider;

public interface YoutubeItemData {
    default String getYoutubeChannelId() {
        return ((YoutubeItemDataProvider) this).youtubeItemData().getYoutubeChannelId();
    }

    default void setYoutubeChannelId(String youtubeChannelId) {
        ((YoutubeItemDataProvider) this).youtubeItemData().setYoutubeChannelId(youtubeChannelId);
    }

    default String getYoutubeVideoId() {
        return ((YoutubeItemDataProvider) this).youtubeItemData().getYoutubeVideoId();
    }

    default void setYoutubeVideoId(String youtubeVideoId) {
        ((YoutubeItemDataProvider) this).youtubeItemData().setYoutubeVideoId(youtubeVideoId);
    }
}
