package com.apptasticsoftware.rssreader.module.georss;

import java.util.List;
import java.util.Optional;

/**
 * Interface for GeoRSS channel data containing geographic information.
 */
public interface GeoRssChannelData {

    /**
     * Returns the underlying GeoRSS channel data implementation.
     *
     * @return the GeoRSS channel data
     */
    GeoRssChannelData getGeoRssChannelData();

    /**
     * Returns the GeoRSS point as a string.
     *
     * @return the GeoRSS point
     */
    default Optional<String> getGeoRssPoint() {
        return getGeoRssChannelData().getGeoRssPoint();
    }

    /**
     * Returns the GeoRSS point as a Coordinate.
     *
     * @return the coordinate
     */
    default Optional<Coordinate> getGeoRssPointAsCoordinate() {
        return getGeoRssChannelData().getGeoRssPointAsCoordinate();
    }

    /**
     * Sets the GeoRSS point.
     *
     * @param geoRssPoint the point to set
     */
    default void setGeoRssPoint(String geoRssPoint) {
        getGeoRssChannelData().setGeoRssPoint(geoRssPoint);
    }

    /**
     * Returns the GeoRSS line as a string.
     *
     * @return the GeoRSS line
     */
    default Optional<String> getGeoRssLine() {
        return getGeoRssChannelData().getGeoRssLine();
    }

    /**
     * Returns the GeoRSS line as a list of Coordinates.
     *
     * @return the list of coordinates
     */
    default List<Coordinate> getGeoRssLineAsCoordinates() {
        return getGeoRssChannelData().getGeoRssLineAsCoordinates();
    }

    /**
     * Sets the GeoRSS line.
     *
     * @param geoRssLine the line to set
     */
    default void setGeoRssLine(String geoRssLine) {
        getGeoRssChannelData().setGeoRssLine(geoRssLine);
    }

    /**
     * Returns the GeoRSS polygon as a string.
     *
     * @return the GeoRSS polygon
     */
    default Optional<String> getGeoRssPolygon() {
        return getGeoRssChannelData().getGeoRssPolygon();
    }

    /**
     * Returns the GeoRSS polygon as a list of Coordinates.
     *
     * @return the list of coordinates
     */
    default List<Coordinate> getGeoRssPolygonAsCoordinates() {
        return getGeoRssChannelData().getGeoRssPolygonAsCoordinates();
    }

    /**
     * Sets the GeoRSS polygon.
     *
     * @param geoRssPolygon the polygon to set
     */
    default void setGeoRssPolygon(String geoRssPolygon) {
        getGeoRssChannelData().setGeoRssPolygon(geoRssPolygon);
    }

    /**
     * Returns the GeoRSS box as a string.
     *
     * @return the GeoRSS box
     */
    default Optional<String> getGeoRssBox() {
        return getGeoRssChannelData().getGeoRssBox();
    }

    /**
     * Returns the GeoRSS box as a list of Coordinates.
     *
     * @return the list of coordinates
     */
    default List<Coordinate> getGeoRssBoxAsCoordinates() {
        return getGeoRssChannelData().getGeoRssBoxAsCoordinates();
    }

    /**
     * Sets the GeoRSS box.
     *
     * @param geoRssBox the box to set
     */
    default void setGeoRssBox(String geoRssBox) {
        getGeoRssChannelData().setGeoRssBox(geoRssBox);
    }

    /**
     * Returns the GeoRSS elevation.
     *
     * @return the elevation
     */
    default Optional<Double> getGeoRssElevation() {
        return getGeoRssChannelData().getGeoRssElevation();
    }

    /**
     * Sets the GeoRSS elevation.
     *
     * @param geoRssElevation the elevation to set
     */
    default void setGeoRssElevation(Double geoRssElevation) {
        getGeoRssChannelData().setGeoRssElevation(geoRssElevation);
    }

    /**
     * Returns the GeoRSS floor.
     *
     * @return the floor
     */
    default Optional<Integer> getGeoRssFloor() {
        return getGeoRssChannelData().getGeoRssFloor();
    }

    /**
     * Sets the GeoRSS floor.
     *
     * @param geoRssFloor the floor to set
     */
    default void setGeoRssFloor(Integer geoRssFloor) {
        getGeoRssChannelData().setGeoRssFloor(geoRssFloor);
    }

    /**
     * Returns the GeoRSS radius.
     *
     * @return the radius
     */
    default Optional<Double> getGeoRssRadius() {
        return getGeoRssChannelData().getGeoRssRadius();
    }

    /**
     * Sets the GeoRSS radius.
     *
     * @param geoRssRadius the radius to set
     */
    default void setGeoRssRadius(Double geoRssRadius) {
        getGeoRssChannelData().setGeoRssRadius(geoRssRadius);
    }

    /**
     * Returns the GeoRSS feature type tag.
     *
     * @return the feature type tag
     */
    default Optional<String> getGeoRssFeatureTypeTag() {
        return getGeoRssChannelData().getGeoRssFeatureTypeTag();
    }

    /**
     * Sets the GeoRSS feature type tag.
     *
     * @param geoRssFeatureTypeTag the feature type tag to set
     */
    default void setGeoRssFeatureTypeTag(String geoRssFeatureTypeTag) {
        getGeoRssChannelData().setGeoRssFeatureTypeTag(geoRssFeatureTypeTag);
    }

    /**
     * Returns the GeoRSS relationship tag.
     *
     * @return the relationship tag
     */
    default Optional<String> getGeoRssRelationshipTag() {
        return getGeoRssChannelData().getGeoRssRelationshipTag();
    }

    /**
     * Sets the GeoRSS relationship tag.
     *
     * @param geoRssRelationshipTag the relationship tag to set
     */
    default void setGeoRssRelationshipTag(String geoRssRelationshipTag) {
        getGeoRssChannelData().setGeoRssRelationshipTag(geoRssRelationshipTag);
    }

    /**
     * Returns the GeoRSS feature name.
     *
     * @return the feature name
     */
    default Optional<String> getGeoRssFeatureName() {
        return getGeoRssChannelData().getGeoRssFeatureName();
    }

    /**
     * Sets the GeoRSS feature name.
     *
     * @param geoRssFeatureName the feature name to set
     */
    default void setGeoRssFeatureName(String geoRssFeatureName) {
        getGeoRssChannelData().setGeoRssFeatureName(geoRssFeatureName);
    }
}
