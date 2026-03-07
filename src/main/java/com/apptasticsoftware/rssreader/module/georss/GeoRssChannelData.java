package com.apptasticsoftware.rssreader.module.georss;

import com.apptasticsoftware.rssreader.module.georss.internal.GeoRssChannelDataProvider;
import java.util.List;
import java.util.Optional;

/**
 * Interface for GeoRSS channel data containing geographic information.
 */
public interface GeoRssChannelData {

    /**
     * Returns the GeoRSS point as a string.
     *
     * @return the GeoRSS point
     */
    default Optional<String> getGeoRssPoint() {
        return ((GeoRssChannelDataProvider) this).geoRssChannelData().getGeoRssPoint();
    }

    /**
     * Returns the GeoRSS point as a Coordinate.
     *
     * @return the coordinate
     */
    default Optional<Coordinate> getGeoRssPointAsCoordinate() {
        return ((GeoRssChannelDataProvider) this).geoRssChannelData().getGeoRssPointAsCoordinate();
    }

    /**
     * Sets the GeoRSS point.
     *
     * @param geoRssPoint the point to set
     */
    default void setGeoRssPoint(String geoRssPoint) {
        ((GeoRssChannelDataProvider) this).geoRssChannelData().setGeoRssPoint(geoRssPoint);
    }

    /**
     * Returns the GeoRSS line as a string.
     *
     * @return the GeoRSS line
     */
    default Optional<String> getGeoRssLine() {
        return ((GeoRssChannelDataProvider) this).geoRssChannelData().getGeoRssLine();
    }

    /**
     * Returns the GeoRSS line as a list of Coordinates.
     *
     * @return the list of coordinates
     */
    default List<Coordinate> getGeoRssLineAsCoordinates() {
        return ((GeoRssChannelDataProvider) this).geoRssChannelData().getGeoRssLineAsCoordinates();
    }

    /**
     * Sets the GeoRSS line.
     *
     * @param geoRssLine the line to set
     */
    default void setGeoRssLine(String geoRssLine) {
        ((GeoRssChannelDataProvider) this).geoRssChannelData().setGeoRssLine(geoRssLine);
    }

    /**
     * Returns the GeoRSS polygon as a string.
     *
     * @return the GeoRSS polygon
     */
    default Optional<String> getGeoRssPolygon() {
        return ((GeoRssChannelDataProvider) this).geoRssChannelData().getGeoRssPolygon();
    }

    /**
     * Returns the GeoRSS polygon as a list of Coordinates.
     *
     * @return the list of coordinates
     */
    default List<Coordinate> getGeoRssPolygonAsCoordinates() {
        return ((GeoRssChannelDataProvider) this).geoRssChannelData().getGeoRssPolygonAsCoordinates();
    }

    /**
     * Sets the GeoRSS polygon.
     *
     * @param geoRssPolygon the polygon to set
     */
    default void setGeoRssPolygon(String geoRssPolygon) {
        ((GeoRssChannelDataProvider) this).geoRssChannelData().setGeoRssPolygon(geoRssPolygon);
    }

    /**
     * Returns the GeoRSS box as a string.
     *
     * @return the GeoRSS box
     */
    default Optional<String> getGeoRssBox() {
        return ((GeoRssChannelDataProvider) this).geoRssChannelData().getGeoRssBox();
    }

    /**
     * Returns the GeoRSS box as a list of Coordinates.
     *
     * @return the list of coordinates
     */
    default List<Coordinate> getGeoRssBoxAsCoordinates() {
        return ((GeoRssChannelDataProvider) this).geoRssChannelData().getGeoRssBoxAsCoordinates();
    }

    /**
     * Sets the GeoRSS box.
     *
     * @param geoRssBox the box to set
     */
    default void setGeoRssBox(String geoRssBox) {
        ((GeoRssChannelDataProvider) this).geoRssChannelData().setGeoRssBox(geoRssBox);
    }

    /**
     * Returns the GeoRSS elevation.
     *
     * @return the elevation
     */
    default Optional<Double> getGeoRssElevation() {
        return ((GeoRssChannelDataProvider) this).geoRssChannelData().getGeoRssElevation();
    }

    /**
     * Sets the GeoRSS elevation.
     *
     * @param geoRssElevation the elevation to set
     */
    default void setGeoRssElevation(Double geoRssElevation) {
        ((GeoRssChannelDataProvider) this).geoRssChannelData().setGeoRssElevation(geoRssElevation);
    }

    /**
     * Returns the GeoRSS floor.
     *
     * @return the floor
     */
    default Optional<Integer> getGeoRssFloor() {
        return ((GeoRssChannelDataProvider) this).geoRssChannelData().getGeoRssFloor();
    }

    /**
     * Sets the GeoRSS floor.
     *
     * @param geoRssFloor the floor to set
     */
    default void setGeoRssFloor(Integer geoRssFloor) {
        ((GeoRssChannelDataProvider) this).geoRssChannelData().setGeoRssFloor(geoRssFloor);
    }

    /**
     * Returns the GeoRSS radius.
     *
     * @return the radius
     */
    default Optional<Double> getGeoRssRadius() {
        return ((GeoRssChannelDataProvider) this).geoRssChannelData().getGeoRssRadius();
    }

    /**
     * Sets the GeoRSS radius.
     *
     * @param geoRssRadius the radius to set
     */
    default void setGeoRssRadius(Double geoRssRadius) {
        ((GeoRssChannelDataProvider) this).geoRssChannelData().setGeoRssRadius(geoRssRadius);
    }

    /**
     * Returns the GeoRSS feature type tag.
     *
     * @return the feature type tag
     */
    default Optional<String> getGeoRssFeatureTypeTag() {
        return ((GeoRssChannelDataProvider) this).geoRssChannelData().getGeoRssFeatureTypeTag();
    }

    /**
     * Sets the GeoRSS feature type tag.
     *
     * @param geoRssFeatureTypeTag the feature type tag to set
     */
    default void setGeoRssFeatureTypeTag(String geoRssFeatureTypeTag) {
        ((GeoRssChannelDataProvider) this).geoRssChannelData().setGeoRssFeatureTypeTag(geoRssFeatureTypeTag);
    }

    /**
     * Returns the GeoRSS relationship tag.
     *
     * @return the relationship tag
     */
    default Optional<String> getGeoRssRelationshipTag() {
        return ((GeoRssChannelDataProvider) this).geoRssChannelData().getGeoRssRelationshipTag();
    }

    /**
     * Sets the GeoRSS relationship tag.
     *
     * @param geoRssRelationshipTag the relationship tag to set
     */
    default void setGeoRssRelationshipTag(String geoRssRelationshipTag) {
        ((GeoRssChannelDataProvider) this).geoRssChannelData().setGeoRssRelationshipTag(geoRssRelationshipTag);
    }

    /**
     * Returns the GeoRSS feature name.
     *
     * @return the feature name
     */
    default Optional<String> getGeoRssFeatureName() {
        return ((GeoRssChannelDataProvider) this).geoRssChannelData().getGeoRssFeatureName();
    }

    /**
     * Sets the GeoRSS feature name.
     *
     * @param geoRssFeatureName the feature name to set
     */
    default void setGeoRssFeatureName(String geoRssFeatureName) {
        ((GeoRssChannelDataProvider) this).geoRssChannelData().setGeoRssFeatureName(geoRssFeatureName);
    }
}
