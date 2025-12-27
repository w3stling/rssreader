package com.apptasticsoftware.rssreader.module.georss;

import java.util.List;
import java.util.Optional;

/**
 * Interface for GeoRSS item data containing geographic information.
 */
public interface GeoRssItemData {

    /**
     * Returns the underlying GeoRSS item data implementation.
     *
     * @return the GeoRSS item data
     */
    GeoRssItemData getGeoRssItemData();

    /**
     * Returns the GeoRSS point as a string.
     *
     * @return the GeoRSS point
     */
    default Optional<String> getGeoRssPoint() {
        return getGeoRssItemData().getGeoRssPoint();
    }

    /**
     * Returns the GeoRSS point as a Coordinate.
     *
     * @return the coordinate
     */
    default Optional<Coordinate> getGeoRssPointAsCoordinate() {
        return getGeoRssItemData().getGeoRssPointAsCoordinate();
    }

    /**
     * Sets the GeoRSS point.
     *
     * @param geoRssPoint the point to set
     */
    default void setGeoRssPoint(String geoRssPoint) {
        getGeoRssItemData().setGeoRssPoint(geoRssPoint);
    }

    /**
     * Returns the GeoRSS line as a string.
     *
     * @return the GeoRSS line
     */
    default Optional<String> getGeoRssLine() {
        return getGeoRssItemData().getGeoRssLine();
    }

    /**
     * Returns the GeoRSS line as a list of Coordinates.
     *
     * @return the list of coordinates
     */
    default List<Coordinate> getGeoRssLineAsCoordinates() {
        return getGeoRssItemData().getGeoRssLineAsCoordinates();
    }

    /**
     * Sets the GeoRSS line.
     *
     * @param geoRssLine the line to set
     */
    default void setGeoRssLine(String geoRssLine) {
        getGeoRssItemData().setGeoRssLine(geoRssLine);
    }

    /**
     * Returns the GeoRSS polygon as a string.
     *
     * @return the GeoRSS polygon
     */
    default Optional<String> getGeoRssPolygon() {
        return getGeoRssItemData().getGeoRssPolygon();
    }

    /**
     * Returns the GeoRSS polygon as a list of Coordinates.
     *
     * @return the list of coordinates
     */
    default List<Coordinate> getGeoRssPolygonAsCoordinates() {
        return getGeoRssItemData().getGeoRssPolygonAsCoordinates();
    }

    /**
     * Sets the GeoRSS polygon.
     *
     * @param geoRssPolygon the polygon to set
     */
    default void setGeoRssPolygon(String geoRssPolygon) {
        getGeoRssItemData().setGeoRssPolygon(geoRssPolygon);
    }

    /**
     * Returns the GeoRSS box as a string.
     *
     * @return the GeoRSS box
     */
    default Optional<String> getGeoRssBox() {
        return getGeoRssItemData().getGeoRssBox();
    }

    /**
     * Returns the GeoRSS box as a list of Coordinates.
     *
     * @return the list of coordinates
     */
    default List<Coordinate> getGeoRssBoxAsCoordinates() {
        return getGeoRssItemData().getGeoRssBoxAsCoordinates();
    }

    /**
     * Sets the GeoRSS box.
     *
     * @param geoRssBox the box to set
     */
    default void setGeoRssBox(String geoRssBox) {
        getGeoRssItemData().setGeoRssBox(geoRssBox);
    }

    /**
     * Returns the GeoRSS elevation.
     *
     * @return the elevation
     */
    default Optional<Double> getGeoRssElevation() {
        return getGeoRssItemData().getGeoRssElevation();
    }

    /**
     * Sets the GeoRSS elevation.
     *
     * @param geoRssElevation the elevation to set
     */
    default void setGeoRssElevation(Double geoRssElevation) {
        getGeoRssItemData().setGeoRssElevation(geoRssElevation);
    }

    /**
     * Returns the GeoRSS floor.
     *
     * @return the floor
     */
    default Optional<Integer> getGeoRssFloor() {
        return getGeoRssItemData().getGeoRssFloor();
    }

    /**
     * Sets the GeoRSS floor.
     *
     * @param geoRssFloor the floor to set
     */
    default void setGeoRssFloor(Integer geoRssFloor) {
        getGeoRssItemData().setGeoRssFloor(geoRssFloor);
    }

    /**
     * Returns the GeoRSS radius.
     *
     * @return the radius
     */
    default Optional<Double> getGeoRssRadius() {
        return getGeoRssItemData().getGeoRssRadius();
    }

    /**
     * Sets the GeoRSS radius.
     *
     * @param geoRssRadius the radius to set
     */
    default void setGeoRssRadius(Double geoRssRadius) {
        getGeoRssItemData().setGeoRssRadius(geoRssRadius);
    }

    /**
     * Returns the GeoRSS feature type tag.
     *
     * @return the feature type tag
     */
    default Optional<String> getGeoRssFeatureTypeTag() {
        return getGeoRssItemData().getGeoRssFeatureTypeTag();
    }

    /**
     * Sets the GeoRSS feature type tag.
     *
     * @param geoRssFeatureTypeTag the feature type tag to set
     */
    default void setGeoRssFeatureTypeTag(String geoRssFeatureTypeTag) {
        getGeoRssItemData().setGeoRssFeatureTypeTag(geoRssFeatureTypeTag);
    }

    /**
     * Returns the GeoRSS relationship tag.
     *
     * @return the relationship tag
     */
    default Optional<String> getGeoRssRelationshipTag() {
        return getGeoRssItemData().getGeoRssRelationshipTag();
    }

    /**
     * Sets the GeoRSS relationship tag.
     *
     * @param geoRssRelationshipTag the relationship tag to set
     */
    default void setGeoRssRelationshipTag(String geoRssRelationshipTag) {
        getGeoRssItemData().setGeoRssRelationshipTag(geoRssRelationshipTag);
    }

    /**
     * Returns the GeoRSS feature name.
     *
     * @return the feature name
     */
    default Optional<String> getGeoRssFeatureName() {
        return getGeoRssItemData().getGeoRssFeatureName();
    }

    /**
     * Sets the GeoRSS feature name.
     *
     * @param geoRssFeatureName the feature name to set
     */
    default void setGeoRssFeatureName(String geoRssFeatureName) {
        getGeoRssItemData().setGeoRssFeatureName(geoRssFeatureName);
    }
}
