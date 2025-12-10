package com.apptasticsoftware.rssreader.module.podcast;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents the podcast:block element that allows podcasters to control platform access to their feed.
 * This element determines which platforms are allowed to publicly display the feed and its contents.
 * It serves as a more flexible replacement for the itunes:block tag.
 */
public class PodcastBlock {
    private boolean block;
    private String id;

    /**
     * Returns the block status.
     * When true, indicates that the feed should be blocked from ingestion.
     * The value corresponds to "yes" (true) or "no" (false) in the XML.
     *
     * @return true if the feed should be blocked, false otherwise
     */
    public boolean isBlock() {
        return block;
    }

    /**
     * Sets the block status.
     *
     * @param block true to block ingestion ("yes"), false to allow it ("no")
     */
    public void setBlock(boolean block) {
        this.block = block;
    }

    /**
     * Gets the platform identifier (slug) that this block rule applies to.
     * When not present, the block rule applies to all platforms.
     *
     * @return an Optional containing the platform identifier, or empty if no specific platform is targeted
     */
    public Optional<String> getId() {
        return Optional.ofNullable(id);
    }

    /**
     * Sets the platform identifier (slug) that this block rule applies to.
     *
     * @param id the platform identifier from the service slug list
     */
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PodcastBlock that = (PodcastBlock) o;
        return isBlock() == that.isBlock() && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(isBlock(), getId());
    }
}
