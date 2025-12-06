package com.apptasticsoftware.rssreader.module.podcast;

import java.util.Objects;

public class PodcastContentLink {
    private String contentLink;
    private String href;

    public String getContentLink() {
        return contentLink;
    }

    public void setContentLink(String contentLink) {
        this.contentLink = contentLink;
    }

    public String getHref() {
        return href;
    }

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