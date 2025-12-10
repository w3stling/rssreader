package com.apptasticsoftware.rssreader.module.podcast;

import java.util.Objects;

/**
 * Represents the podcast:contentLink element that indicates where content can be found externally.
 * <p>
 * The contentLink tag is used to indicate that the content being delivered by the parent element
 * can be found at an external location instead of, or in addition to, the tag itself within an app.
 * In most instances it is used as a fallback link for the user to use when the app itself can't
 * handle a certain content delivery directly.
 * </p>
 * <p>
 * For example, a podcast feed may specify a live item to deliver a live stream to apps, and also
 * provide a contentLink pointing to YouTube and Twitch versions of the live stream as a fallback
 * for apps that don't fully support live streaming content.
 * </p>
 *
 * @see <a href="https://podcastindex.org/namespace/">Podcast Index Specification</a>
 */
public class PodcastContentLink {
    /** A free-form string explaining to the user where this content link points and/or its purpose */
    private String contentLink;
    /** The URI pointing to content outside of the application (required) */
    private String href;

    /**
     * Gets the descriptive text explaining where this content link points.
     * This is the node value of the podcast:contentLink element.
     *
     * @return a free-form string describing the content link's purpose and location
     */
    public String getContentLink() {
        return contentLink;
    }

    /**
     * Sets the descriptive text for this content link.
     *
     * @param contentLink a free-form string meant to explain to the user where this content link
     *                    points and/or the nature of its purpose
     */
    public void setContentLink(String contentLink) {
        this.contentLink = contentLink;
    }

    /**
     * Gets the URI pointing to the external content location.
     *
     * @return the URI pointing to content outside of the application (required)
     */
    public String getHref() {
        return href;
    }

    /**
     * Sets the URI pointing to the external content location.
     *
     * @param href the URI pointing to content outside of the application (required)
     */
    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PodcastContentLink that = (PodcastContentLink) o;
        return Objects.equals(getContentLink(), that.getContentLink()) && Objects.equals(getHref(), that.getHref());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getContentLink(), getHref());
    }
}