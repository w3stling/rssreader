package com.apptasticsoftware.rssreader.module.podcast;

import java.util.Objects;

/**
 * Represents donation/funding links for a podcast. This element allows podcast creators
 * to specify ways for listeners to financially support the podcast.
 */
public class PodcastFunding {
    private String funding;
    private String url;

    /**
     * Gets the display text for the funding link.
     * @return The recommended text to be displayed alongside the funding URL.
     * The value should not exceed 128 characters as it may be truncated by aggregators.
     */
    public String getFunding() {
        return funding;
    }

    /**
     * Sets the display text for the funding link.
     * @param funding A free-form string provided by the creator to be displayed alongside the URL.
     *               Should not exceed 128 characters.
     */
    public void setFunding(String funding) {
        this.funding = funding;
    }

    /**
     * Gets the URL where listeners can provide funding support.
     * @return The URL to be followed to fund the podcast.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the URL where listeners can provide funding support.
     * @param url The URL to be followed to fund the podcast (required).
     */
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PodcastFunding that = (PodcastFunding) o;
        return Objects.equals(getFunding(), that.getFunding()) && Objects.equals(getUrl(), that.getUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFunding(), getUrl());
    }
}
