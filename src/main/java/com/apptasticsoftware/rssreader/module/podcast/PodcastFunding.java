package com.apptasticsoftware.rssreader.module.podcast;

import java.util.Objects;

public class PodcastFunding {
    private String funding;
    private String url;

    public String getFunding() {
        return funding;
    }

    public void setFunding(String funding) {
        this.funding = funding;
    }

    public String getUrl() {
        return url;
    }

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
