package com.apptasticsoftware.rssreader.module.youtube;

public interface YoutubeItemData {

    YoutubeItemData getYoutubeItemData();

    default String getYoutubeChannelId() {
        return getYoutubeItemData().getYoutubeChannelId();
    }

    default void setYoutubeChannelId(String youtubeChannelId) {
        getYoutubeItemData().setYoutubeChannelId(youtubeChannelId);
    }

    default String getYoutubeVideoId() {
        return getYoutubeItemData().getYoutubeVideoId();
    }

    default void setYoutubeVideoId(String youtubeVideoId) {
        getYoutubeItemData().setYoutubeVideoId(youtubeVideoId);
    }
}
