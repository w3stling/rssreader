package com.apptasticsoftware.rssreader.module.podcast;

import java.util.Objects;
import java.util.Optional;

public class PodcastImage {
    private String href;
    private String alt;
    private String aspectRatio;
    private Integer width;
    private Integer height;
    private String type;
    private String purpose;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Optional<String> getAlt() {
        return Optional.ofNullable(alt);
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public Optional<String> getAspectRatio() {
        return Optional.ofNullable(aspectRatio);
    }

    public void setAspectRatio(String aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public Optional<Integer> getWidth() {
        return Optional.ofNullable(width);
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Optional<Integer> getHeight() {
        return Optional.ofNullable(height);
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Optional<String> getType() {
        return Optional.ofNullable(type);
    }

    public void setType(String type) {
        this.type = type;
    }

    public Optional<String> getPurpose() {
        return Optional.ofNullable(purpose);
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PodcastImage that = (PodcastImage) o;
        return Objects.equals(getHref(), that.getHref()) && Objects.equals(getAlt(), that.getAlt()) && Objects.equals(getAspectRatio(), that.getAspectRatio()) && Objects.equals(getWidth(), that.getWidth()) && Objects.equals(getHeight(), that.getHeight()) && Objects.equals(getType(), that.getType()) && Objects.equals(getPurpose(), that.getPurpose());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHref(), getAlt(), getAspectRatio(), getWidth(), getHeight(), getType(), getPurpose());
    }
}
