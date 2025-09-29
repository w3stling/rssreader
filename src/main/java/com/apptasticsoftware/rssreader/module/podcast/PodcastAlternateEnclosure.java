package com.apptasticsoftware.rssreader.module.podcast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PodcastAlternateEnclosure {
    private String type;
    private Long length;
    private Double bitrate;
    private Integer height;
    private String lang;
    private String title;
    private String rel;
    private String codecs;
    private boolean defaults = false;
    private final List<PodcastSource> sources = new ArrayList<>();
    private PodcastIntegrity integrity;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public Double getBitrate() {
        return bitrate;
    }

    public void setBitrate(Double bitrate) {
        this.bitrate = bitrate;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getCodecs() {
        return codecs;
    }

    public void setCodecs(String codecs) {
        this.codecs = codecs;
    }

    public boolean isDefaults() {
        return defaults;
    }

    public void setDefaults(boolean defaults) {
        this.defaults = defaults;
    }

    public List<PodcastSource> getSources() {
        return sources;
    }

    public void addSource(PodcastSource source) {
        this.sources.add(source);
    }

    public Optional<PodcastIntegrity> getIntegrity() {
        return Optional.ofNullable(integrity);
    }

    public void setIntegrity(PodcastIntegrity integrity) {
        this.integrity = integrity;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PodcastAlternateEnclosure that = (PodcastAlternateEnclosure) o;
        return isDefaults() == that.isDefaults() && Objects.equals(getType(), that.getType()) && Objects.equals(getLength(), that.getLength()) && Objects.equals(getBitrate(), that.getBitrate()) && Objects.equals(getHeight(), that.getHeight()) && Objects.equals(getLang(), that.getLang()) && Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getRel(), that.getRel()) && Objects.equals(getCodecs(), that.getCodecs()) && Objects.equals(getSources(), that.getSources()) && Objects.equals(getIntegrity(), that.getIntegrity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getLength(), getBitrate(), getHeight(), getLang(), getTitle(), getRel(), getCodecs(), isDefaults(), getSources(), getIntegrity());
    }
}
