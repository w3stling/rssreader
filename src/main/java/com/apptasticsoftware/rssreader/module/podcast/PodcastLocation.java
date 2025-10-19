package com.apptasticsoftware.rssreader.module.podcast;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents a podcast location, describing either the editorial focus or the source of production
 * for a podcast's content. This class answers either "where is this podcast about?" or
 * "where was the podcast made?".
 */
public class PodcastLocation {
    private String location;
    private String rel;
    private String geo;
    private String osm;
    private String country;

    /**
     * Gets the human-readable location string.
     * This is a free-form string that may conform to conventional location verbiage (e.g., "Austin, TX"),
     * but should not be depended on to be parseable in any specific way.
     *
     * @return the location string, intended as a display value
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the human-readable location string.
     * The value should not exceed 128 characters as it may be truncated by aggregators.
     *
     * @param location the location string to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets the relationship type of the location.
     *
     * @return Optional containing either "subject" (default, content focus) or "creator" (production location)
     */
    public Optional<String> getRel() {
        return Optional.ofNullable(rel);
    }

    /**
     * Sets the relationship type of the location.
     *
     * @param rel either "subject" (content focus) or "creator" (production location)
     */
    public void setRel(String rel) {
        this.rel = rel;
    }

    /**
     * Gets the geographical coordinates in geoURI format.
     *
     * @return Optional containing the geoURI (RFC5870) formatted string (e.g., "geo:30.2672,97.7431")
     */
    public Optional<String> getGeo() {
        return Optional.ofNullable(geo);
    }

    /**
     * Sets the geographical coordinates in geoURI format.
     *
     * @param geo the geoURI (RFC5870) formatted string
     */
    public void setGeo(String geo) {
        this.geo = geo;
    }

    /**
     * Gets the OpenStreetMap identifier.
     *
     * @return Optional containing the OSM identifier (e.g., "R113314")
     */
    public Optional<String> getOsm() {
        return Optional.ofNullable(osm);
    }

    /**
     * Sets the OpenStreetMap identifier.
     * The format should be the first character of OSM object type (Node, Way, Relation) followed by the ID.
     *
     * @param osm the OpenStreetMap identifier
     */
    public void setOsm(String osm) {
        this.osm = osm;
    }

    /**
     * Gets the country code.
     *
     * @return Optional containing the ISO 3166-1 alpha-2 country code
     */
    public Optional<String> getCountry() {
        return Optional.ofNullable(country);
    }

    /**
     * Sets the country code.
     *
     * @param country the ISO 3166-1 alpha-2 country code
     */
    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PodcastLocation that = (PodcastLocation) o;
        return Objects.equals(getLocation(), that.getLocation()) &&
               Objects.equals(getRel(), that.getRel()) &&
               Objects.equals(getGeo(), that.getGeo()) &&
               Objects.equals(getOsm(), that.getOsm()) &&
               Objects.equals(getCountry(), that.getCountry());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLocation(), getRel(), getGeo(), getOsm(), getCountry());
    }
}
