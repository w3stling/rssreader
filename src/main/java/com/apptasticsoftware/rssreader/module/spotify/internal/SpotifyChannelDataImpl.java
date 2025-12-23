package com.apptasticsoftware.rssreader.module.spotify.internal;

import com.apptasticsoftware.rssreader.module.spotify.SpotifyChannelData;
import com.apptasticsoftware.rssreader.util.Util;

import java.util.*;

public class SpotifyChannelDataImpl implements SpotifyChannelData {
    private Integer spotifyLimitRecentCount;
    private String spotifyCountryOfOrigin;

    @Override
    public SpotifyChannelData getSpotifyChannelData() {
        return this;
    }

    @Override
    public Optional<Integer> getSpotifyLimitRecentCount() {
        return Optional.ofNullable(spotifyLimitRecentCount);
    }

    @Override
    public void setSpotifyLimitRecentCount(Integer spotifyLimitRecentCount) {
        this.spotifyLimitRecentCount = spotifyLimitRecentCount;
    }

    @Override
    public Optional<String> getSpotifyCountryOfOrigin() {
        return Optional.ofNullable(spotifyCountryOfOrigin);
    }

    @Override
    public List<String> getSpotifyCountryOfOriginAsList() {
        if (Util.isBlank(spotifyCountryOfOrigin)) {
            return Collections.emptyList();
        }
        return Arrays.asList(spotifyCountryOfOrigin.trim().split("\\s+"));
    }

    @Override
    public void setSpotifyCountryOfOrigin(String spotifyCountryOfOrigin) {
        this.spotifyCountryOfOrigin = spotifyCountryOfOrigin;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SpotifyChannelDataImpl that = (SpotifyChannelDataImpl) o;
        return Objects.equals(getSpotifyLimitRecentCount(), that.getSpotifyLimitRecentCount()) && Objects.equals(getSpotifyCountryOfOrigin(), that.getSpotifyCountryOfOrigin());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSpotifyLimitRecentCount(), getSpotifyCountryOfOrigin());
    }
}
