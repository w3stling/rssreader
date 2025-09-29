package com.apptasticsoftware.rssreader.module.podcast;

import java.util.Objects;
import java.util.Optional;

public class PodcastTranscript {
    private String url;
    private String type;
    private String language;
    private String rel;

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

    public Optional<String> getLanguage() {
        return Optional.ofNullable(language);
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Optional<String> getRel() {
        return Optional.ofNullable(rel);
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PodcastTranscript that = (PodcastTranscript) o;
        return Objects.equals(getUrl(), that.getUrl()) && Objects.equals(getType(), that.getType()) && Objects.equals(getLanguage(), that.getLanguage()) && Objects.equals(getRel(), that.getRel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUrl(), getType(), getLanguage(), getRel());
    }
}
