package com.apptasticsoftware.rssreader.module.podcast;

import java.util.Objects;
import java.util.Optional;

/**
 * Defines a license that is applied to the audio/video content of a single episode,
 * or the audio/video of the podcast as a whole. Custom licenses must always include
 * a url attribute.
 */
public class PodcastLicense {
    private String license;
    private String url;

    /**
     * Gets the license identifier.
     * @return A lower-cased reference to a license identifier for well-known public licenses,
     *         or a free form abbreviation (max 128 characters) for custom licenses.
     */
    public String getLicense() {
        return license;
    }

    /**
     * Sets the license identifier.
     * @param license A lower-cased reference to a license identifier for well-known public licenses,
     *               or a free form abbreviation (max 128 characters) for custom licenses.
     */
    public void setLicense(String license) {
        this.license = license;
    }

    /**
     * Gets the URL pointing to the full legal language of the license.
     * @return An Optional containing the URL to the full legal text of the license.
     *         Optional for well-known public licenses, required for custom licenses.
     */
    public Optional<String> getUrl() {
        return Optional.ofNullable(url);
    }

    /**
     * Sets the URL pointing to the full legal language of the license.
     * @param url The URL to the full legal text of the license.
     *           Optional for well-known public licenses, required for custom licenses.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PodcastLicense that = (PodcastLicense) o;
        return Objects.equals(getLicense(), that.getLicense()) && Objects.equals(getUrl(), that.getUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLicense(), getUrl());
    }
}
