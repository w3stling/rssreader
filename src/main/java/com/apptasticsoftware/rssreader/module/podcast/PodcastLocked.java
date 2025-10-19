package com.apptasticsoftware.rssreader.module.podcast;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents the podcast:locked tag in an RSS feed which controls feed importability.
 * This tag indicates whether other podcast hosting platforms are allowed to import this feed.
 * A locked value of true means that any attempt to import this feed into a new platform should be rejected.
 */
public class PodcastLocked {
    private boolean locked;
    private String owner;

    /**
     * Gets the locked status of the podcast feed.
     *
     * @return true if the feed is locked (imports should be rejected), false otherwise
     */
    public boolean isLocked() {
        return locked;
    }

    /**
     * Sets the locked status of the podcast feed.
     *
     * @param locked true to prevent feed imports, false to allow them
     */
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    /**
     * Gets the owner's email address that can be used to verify ownership during move and import operations.
     * This could be a public email or a virtual email address at the hosting provider that redirects to the owner's true email address.
     *
     * @return an Optional containing the owner's email address, or empty if not set
     */
    public Optional<String> getOwner() {
        return Optional.ofNullable(owner);
    }

    /**
     * Sets the owner's email address for feed ownership verification.
     *
     * @param owner the email address of the feed owner
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PodcastLocked that = (PodcastLocked) o;
        return isLocked() == that.isLocked() && Objects.equals(getOwner(), that.getOwner());
    }

    @Override
    public int hashCode() {
        return Objects.hash(isLocked(), getOwner());
    }
}
