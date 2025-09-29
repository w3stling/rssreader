package com.apptasticsoftware.rssreader.module.podcast;

import java.util.Objects;

public class PodcastChapters {
    private String url;
    private String type;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

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
