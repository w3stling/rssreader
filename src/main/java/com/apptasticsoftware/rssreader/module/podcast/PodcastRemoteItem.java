package com.apptasticsoftware.rssreader.module.podcast;

import java.util.Objects;
import java.util.Optional;

public class PodcastRemoteItem {
    private String feedGuid;
    private String feedUrl;
    private String itemGuid;
    private String medium;
    private String title;

    public String getFeedGuid() {
        return feedGuid;
    }

    public void setFeedGuid(String feedGuid) {
        this.feedGuid = feedGuid;
    }

    public Optional<String> getFeedUrl() {
        return Optional.ofNullable(feedUrl);
    }

    public void setFeedUrl(String feedUrl) {
        this.feedUrl = feedUrl;
    }

    public Optional<String> getItemGuid() {
        return Optional.ofNullable(itemGuid);
    }

    public void setItemGuid(String itemGuid) {
        this.itemGuid = itemGuid;
    }

    public Optional<String> getMedium() {
        return Optional.ofNullable(medium);
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public Optional<String> getTitle() {
        return Optional.ofNullable(title);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PodcastRemoteItem that = (PodcastRemoteItem) o;
        return Objects.equals(getFeedGuid(), that.getFeedGuid()) && Objects.equals(getFeedUrl(), that.getFeedUrl()) && Objects.equals(getItemGuid(), that.getItemGuid()) && Objects.equals(getMedium(), that.getMedium()) && Objects.equals(getTitle(), that.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFeedGuid(), getFeedUrl(), getItemGuid(), getMedium(), getTitle());
    }
}
