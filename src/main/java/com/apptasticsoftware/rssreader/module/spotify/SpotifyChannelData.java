package com.apptasticsoftware.rssreader.module.spotify;

import com.apptasticsoftware.rssreader.module.spotify.internal.SpotifyChannelDataProvider;
import java.util.List;
import java.util.Optional;

public interface SpotifyChannelData {
    default Optional<Integer> getSpotifyLimitRecentCount() {
        return ((SpotifyChannelDataProvider) this).spotifyChannelData().getSpotifyLimitRecentCount();
    }

    default void setSpotifyLimitRecentCount(Integer spotifyLimitRecentCount) {
        ((SpotifyChannelDataProvider) this).spotifyChannelData().setSpotifyLimitRecentCount(spotifyLimitRecentCount);
    }

    default Optional<String> getSpotifyCountryOfOrigin() {
        return ((SpotifyChannelDataProvider) this).spotifyChannelData().getSpotifyCountryOfOrigin();
    }

    default List<String> getSpotifyCountryOfOriginAsList() {
        return ((SpotifyChannelDataProvider) this).spotifyChannelData().getSpotifyCountryOfOriginAsList();
    }

    default void setSpotifyCountryOfOrigin(String spotifyCountryOfOrigin) {
       ((SpotifyChannelDataProvider) this).spotifyChannelData().setSpotifyCountryOfOrigin(spotifyCountryOfOrigin);
    }
}
