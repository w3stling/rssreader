package com.apptasticsoftware.rssreader.module.podcast;

import java.util.Objects;
import java.util.Optional;

public class PodcastTrailer {
    private String trailer;
    private String url;
    private String pubDate;
    private Long length;
    private String type;
    private Integer season;

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Optional<Integer> getSeason() {
        return Optional.ofNullable(season);
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PodcastTrailer that = (PodcastTrailer) o;
        return Objects.equals(getTrailer(), that.getTrailer()) && Objects.equals(getUrl(), that.getUrl()) && Objects.equals(getPubDate(), that.getPubDate()) && Objects.equals(getLength(), that.getLength()) && Objects.equals(getType(), that.getType()) && Objects.equals(getSeason(), that.getSeason());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTrailer(), getUrl(), getPubDate(), getLength(), getType(), getSeason());
    }
}
