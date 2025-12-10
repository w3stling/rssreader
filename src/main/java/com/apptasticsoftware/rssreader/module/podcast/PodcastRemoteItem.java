package com.apptasticsoftware.rssreader.module.podcast;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents a remote item in a podcast feed that references content hosted on a different feed.
 * This class implements the Podcast Namespace remote-item tag specification, which allows podcast
 * feeds to reference episodes that exist in other feeds, creating a virtual playlist or compilation
 * of content from multiple sources.
 */
public class PodcastRemoteItem {
    private String feedGuid;
    private String feedUrl;
    private String itemGuid;
    private String medium;
    private String title;

    /**
     * Gets the GUID of the source feed where the remote item is located.
     * @return the feed's GUID
     */
    public String getFeedGuid() {
        return feedGuid;
    }

    /**
     * Sets the GUID of the source feed.
     * @param feedGuid the feed's GUID to set
     */
    public void setFeedGuid(String feedGuid) {
        this.feedGuid = feedGuid;
    }

    /**
     * Gets the URL of the source feed where the remote item is located.
     * @return an Optional containing the feed URL, or empty if not set
     */
    public Optional<String> getFeedUrl() {
        return Optional.ofNullable(feedUrl);
    }

    /**
     * Sets the URL of the source feed.
     * @param feedUrl the feed URL to set
     */
    public void setFeedUrl(String feedUrl) {
        this.feedUrl = feedUrl;
    }

    /**
     * Gets the GUID of the specific item within the source feed.
     * @return an Optional containing the item's GUID, or empty if not set
     */
    public Optional<String> getItemGuid() {
        return Optional.ofNullable(itemGuid);
    }

    /**
     * Sets the GUID of the specific item within the source feed.
     * @param itemGuid the item GUID to set
     */
    public void setItemGuid(String itemGuid) {
        this.itemGuid = itemGuid;
    }

    /**
     * Gets the media type of the remote item.
     * @return an Optional containing the media type, or empty if not set
     */
    public Optional<String> getMedium() {
        return Optional.ofNullable(medium);
    }

    /**
     * Sets the media type of the remote item.
     * @param medium the media type to set
     */
    public void setMedium(String medium) {
        this.medium = medium;
    }

    /**
     * Gets the title of the remote item.
     * @return an Optional containing the title, or empty if not set
     */
    public Optional<String> getTitle() {
        return Optional.ofNullable(title);
    }

    /**
     * Sets the title of the remote item.
     * @param title the title to set
     */
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
