package com.apptasticsoftware.rssreader.module.georss.internal;

import com.apptasticsoftware.rssreader.module.georss.Coordinate;

import java.util.*;

/**
 * Internal metadata class storing GeoRSS geographic information.
 */
public class MetaData {
    private String geoRssPoint;
    private String geoRssLine;
    private String geoRssPolygon;
    private String geoRssBox;
    private Double geoRssElevation;
    private Integer geoRssFloor;
    private Double geoRssRadius;
    private String geoRssFeatureTypeTag;
    private String geoRssRelationshipTag;
    private String geoRssFeatureName;

    /**
     * Returns the GeoRSS point as an Optional string.
     *
     * @return the GeoRSS point
     */
    public Optional<String> getGeoRssPoint() {
        return Optional.ofNullable(geoRssPoint);
    }

    /**
     * Returns the GeoRSS point as an Optional Coordinate.
     *
     * @return the coordinate
     */
    public Optional<Coordinate> getGeoRssPointAsCoordinate() {
        return getGeoRssPoint().map(this::toCoordinate);
    }

    /**
     * Sets the GeoRSS point.
     *
     * @param geoRssPoint the point to set
     */
    public void setGeoRssPoint(String geoRssPoint) {
        this.geoRssPoint = geoRssPoint;
    }

    /**
     * Returns the GeoRSS line as an Optional string.
     *
     * @return the GeoRSS line
     */
    public Optional<String> getGeoRssLine() {
        return Optional.ofNullable(geoRssLine);
    }

    /**
     * Returns the GeoRSS line as a list of Coordinates.
     *
     * @return the list of coordinates
     */
    public List<Coordinate> getGeoRssLineAsCoordinates() {
        return getGeoRssLine().map(this::toCoordinates).orElse(Collections.emptyList());
    }

    /**
     * Sets the GeoRSS line.
     *
     * @param geoRssLine the line to set
     */
    public void setGeoRssLine(String geoRssLine) {
        this.geoRssLine = geoRssLine;
    }

    /**
     * Returns the GeoRSS polygon as an Optional string.
     *
     * @return the GeoRSS polygon
     */
    public Optional<String> getGeoRssPolygon() {
        return Optional.ofNullable(geoRssPolygon);
    }

    /**
     * Returns the GeoRSS polygon as a list of Coordinates.
     *
     * @return the list of coordinates
     */
    public List<Coordinate> getGeoRssPolygonAsCoordinates() {
        return Optional.ofNullable(geoRssPolygon).map(this::toCoordinates).orElse(Collections.emptyList());
    }

    /**
     * Sets the GeoRSS polygon.
     *
     * @param geoRssPolygon the polygon to set
     */
    public void setGeoRssPolygon(String geoRssPolygon) {
        this.geoRssPolygon = geoRssPolygon;
    }

    /**
     * Returns the GeoRSS box as an Optional string.
     *
     * @return the GeoRSS box
     */
    public Optional<String> getGeoRssBox() {
        return Optional.ofNullable(geoRssBox);
    }

    /**
     * Returns the GeoRSS box as a list of Coordinates.
     *
     * @return the list of coordinates
     */
    public List<Coordinate> getGeoRssBoxAsCoordinates() {
        return getGeoRssBox().map(this::toCoordinates).orElse(Collections.emptyList());
    }

    /**
     * Sets the GeoRSS box.
     *
     * @param geoRssBox the box to set
     */
    public void setGeoRssBox(String geoRssBox) {
        this.geoRssBox = geoRssBox;
    }

    /**
     * Returns the GeoRSS elevation as an Optional Double.
     *
     * @return the elevation
     */
    public Optional<Double> getGeoRssElevation() {
        return Optional.ofNullable(geoRssElevation);
    }

    /**
     * Sets the GeoRSS elevation.
     *
     * @param geoRssElevation the elevation to set
     */
    public void setGeoRssElevation(Double geoRssElevation) {
        this.geoRssElevation = geoRssElevation;
    }

    /**
     * Returns the GeoRSS floor as an Optional Integer.
     *
     * @return the floor
     */
    public Optional<Integer> getGeoRssFloor() {
        return Optional.ofNullable(geoRssFloor);
    }

    /**
     * Sets the GeoRSS floor.
     *
     * @param geoRssFloor the floor to set
     */
    public void setGeoRssFloor(Integer geoRssFloor) {
        this.geoRssFloor = geoRssFloor;
    }

    /**
     * Returns the GeoRSS radius as an Optional Double.
     *
     * @return the radius
     */
    public Optional<Double> getGeoRssRadius() {
        return Optional.ofNullable(geoRssRadius);
    }

    /**
     * Sets the GeoRSS radius.
     *
     * @param geoRssRadius the radius to set
     */
    public void setGeoRssRadius(Double geoRssRadius) {
        this.geoRssRadius = geoRssRadius;
    }

    /**
     * Returns the GeoRSS feature type tag as an Optional string.
     *
     * @return the feature type tag
     */
    public Optional<String> getGeoRssFeatureTypeTag() {
        return Optional.ofNullable(geoRssFeatureTypeTag);
    }

    /**
     * Sets the GeoRSS feature type tag.
     *
     * @param geoRssFeatureTypeTag the feature type tag to set
     */
    public void setGeoRssFeatureTypeTag(String geoRssFeatureTypeTag) {
        this.geoRssFeatureTypeTag = geoRssFeatureTypeTag;
    }

    /**
     * Returns the GeoRSS relationship tag as an Optional string.
     *
     * @return the relationship tag
     */
    public Optional<String> getGeoRssRelationshipTag() {
        return Optional.ofNullable(geoRssRelationshipTag);
    }

    /**
     * Sets the GeoRSS relationship tag.
     *
     * @param geoRssRelationshipTag the relationship tag to set
     */
    public void setGeoRssRelationshipTag(String geoRssRelationshipTag) {
        this.geoRssRelationshipTag = geoRssRelationshipTag;
    }

    /**
     * Returns the GeoRSS feature name as an Optional string.
     *
     * @return the feature name
     */
    public  Optional<String> getGeoRssFeatureName() {
        return Optional.ofNullable(geoRssFeatureName);
    }

    /**
     * Sets the GeoRSS feature name.
     *
     * @param geoRssFeatureName the feature name to set
     */
    public void setGeoRssFeatureName(String geoRssFeatureName) {
        this.geoRssFeatureName = geoRssFeatureName;
    }

    /**
     * Converts a space-separated coordinate string to a single Coordinate.
     *
     * @param value the coordinate string
     * @return a Coordinate object
     */
    private Coordinate toCoordinate(String value) {
        var coordinates = toCoordinates(value);
        return coordinates.isEmpty() ? null : coordinates.get(0);

    }

    /**
     * Converts a space-separated coordinate string to a list of Coordinates.
     *
     * @param value the coordinate string
     * @return a list of Coordinate objects
     */
    private List<Coordinate> toCoordinates(String value) {
        List<Coordinate> coordinates = new ArrayList<>();
        String[] parts = value.trim().split("\\s+");
        for (int i = 0; i < parts.length - 1; i += 2) {
            Coordinate coordinate = new Coordinate();
            coordinate.setLatitude(Double.parseDouble(parts[i]));
            coordinate.setLongitude(Double.parseDouble(parts[i + 1]));
            coordinates.add(coordinate);
        }
        return coordinates;
    }

    /**
     * Compares this metadata with another object for equality.
     *
     * @param o the object to compare with
     * @return true if all GeoRSS fields are equal
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MetaData that = (MetaData) o;
        return Objects.equals(getGeoRssPoint(), that.getGeoRssPoint()) && Objects.equals(getGeoRssLine(), that.getGeoRssLine()) && Objects.equals(getGeoRssPolygon(), that.getGeoRssPolygon()) && Objects.equals(getGeoRssBox(), that.getGeoRssBox()) && Objects.equals(getGeoRssElevation(), that.getGeoRssElevation()) && Objects.equals(getGeoRssFloor(), that.getGeoRssFloor()) && Objects.equals(getGeoRssRadius(), that.getGeoRssRadius()) && Objects.equals(getGeoRssFeatureTypeTag(), that.getGeoRssFeatureTypeTag()) && Objects.equals(getGeoRssRelationshipTag(), that.getGeoRssRelationshipTag()) && Objects.equals(getGeoRssFeatureName(), that.getGeoRssFeatureName());
    }

    /**
     * Returns the hash code for this metadata.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(getGeoRssPoint(), getGeoRssLine(), getGeoRssPolygon(), getGeoRssBox(), getGeoRssElevation(), getGeoRssFloor(), getGeoRssRadius(), getGeoRssFeatureTypeTag(), getGeoRssRelationshipTag(), getGeoRssFeatureName());
    }
}
