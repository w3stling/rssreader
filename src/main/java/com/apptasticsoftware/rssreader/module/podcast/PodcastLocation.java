package com.apptasticsoftware.rssreader.module.podcast;

import java.util.Objects;
import java.util.Optional;

public class PodcastLocation {
    private String location;
    private String rel;
    private String geo;
    private String osm;
    private String country;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Optional<String> getRel() {
        return Optional.ofNullable(rel);
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public Optional<String> getGeo() {
        return Optional.ofNullable(geo);
    }

    public void setGeo(String geo) {
        this.geo = geo;
    }

    public Optional<String> getOsm() {
        return Optional.ofNullable(osm);
    }

    public void setOsm(String osm) {
        this.osm = osm;
    }

    public Optional<String> getCountry() {
        return Optional.ofNullable(country);
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PodcastLocation that = (PodcastLocation) o;
        return Objects.equals(getLocation(), that.getLocation()) && Objects.equals(getRel(), that.getRel()) && Objects.equals(getGeo(), that.getGeo()) && Objects.equals(getOsm(), that.getOsm()) && Objects.equals(getCountry(), that.getCountry());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLocation(), getRel(), getGeo(), getOsm(), getCountry());
    }
}
