package com.apptasticsoftware.rssreader.module.youtube;

public interface YoutubeChannelData {

    YoutubeChannelData getYoutubeChannelData();

    default String getYoutubeChannelId() {
        return getYoutubeChannelData().getYoutubeChannelId();
    }

    default void setYoutubeChannelId(String youtubeChannelId) {
        getYoutubeChannelData().setYoutubeChannelId(youtubeChannelId);
    }
}
