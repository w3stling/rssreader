package com.apptasticsoftware.rssreader.module.podcast;

import java.util.Objects;

/**
 * Represents an external chapters file for a podcast episode. The chapters data is stored in an external
 * file, allowing for dynamic chapter information without modifying the audio files.
 */
public class PodcastChapters {
    private String url;
    private String type;

    /**
     * Gets the URL where the chapters file is located.
     *
     * @return the URL of the chapters file
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the URL where the chapters file is located.
     *
     * @param url the URL of the chapters file
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Gets the MIME type of the chapters file.
     * The preferred format is JSON with MIME type 'application/json+chapters'.
     *
     * @return the MIME type of the chapters file
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the MIME type of the chapters file.
     * The preferred format is JSON with MIME type 'application/json+chapters'.
     *
     * @param type the MIME type of the chapters file
     */
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PodcastChapters that = (PodcastChapters) o;
        return Objects.equals(getUrl(), that.getUrl()) && Objects.equals(getType(), that.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUrl(), getType());
    }
}
