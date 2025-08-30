package com.apptasticsoftware.rssreader.module.mediarss;

import java.util.Objects;
import java.util.Optional;

/**
 * Copyright information for the media object.
 */
public class MediaCopyright {
    private String url;
    private String copyright;

    /**
     * The URL for a terms of use page or additional copyright information.
     * @return url
     */
    public Optional<String> getUrl() {
        return Optional.ofNullable(url);
    }

    /**
     * The URL for a terms of use page or additional copyright information.
     * @param url url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Copyright information for the media object.
     * @return copyright
     */
    public String getCopyright() {
        return copyright;
    }

    /**
     * Copyright information for the media object.
     * @param copyright copyright
     */
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MediaCopyright that = (MediaCopyright) o;
        return Objects.equals(getUrl(), that.getUrl()) && Objects.equals(getCopyright(), that.getCopyright());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUrl(), getCopyright());
    }
}
