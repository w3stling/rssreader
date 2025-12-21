package com.apptasticsoftware.rssreader.module.spotify;

import java.util.List;
import java.util.Optional;

public interface SpotifyChannelData {

    SpotifyChannelData getSpotifyChannelData();

    default Optional<Integer> getSpotifyLimitRecentCount() {
        return getSpotifyChannelData().getSpotifyLimitRecentCount();
    }

    default void setSpotifyLimitRecentCount(Integer spotifyLimitRecentCount) {
        getSpotifyChannelData().setSpotifyLimitRecentCount(spotifyLimitRecentCount);
    }

    default Optional<String> getSpotifyCountryOfOrigin() {
        return getSpotifyChannelData().getSpotifyCountryOfOrigin();
    }

    default List<String> getSpotifyCountryOfOriginAsList() {
        return getSpotifyChannelData().getSpotifyCountryOfOriginAsList();
    }

    default void setSpotifyCountryOfOrigin(String spotifyCountryOfOrigin) {
       getSpotifyChannelData().setSpotifyCountryOfOrigin(spotifyCountryOfOrigin);
    }
}
