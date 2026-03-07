package com.apptasticsoftware.rssreader.module.georss;

import com.apptasticsoftware.rssreader.module.georss.internal.GeoRssItemDataProvider;
import java.util.List;
import java.util.Optional;

/**
 * Interface for GeoRSS item data containing geographic information.
 */
public interface GeoRssItemData {

    /**
     * Returns the GeoRSS point as a string.
     *
     * @return the GeoRSS point
     */
    default Optional<String> getGeoRssPoint() {
        return ((GeoRssItemDataProvider) this).geoRssItemData().getGeoRssPoint();
    }

    /**
     * Returns the GeoRSS point as a Coordinate.
     *
     * @return the coordinate
     */
    default Optional<Coordinate> getGeoRssPointAsCoordinate() {
        return ((GeoRssItemDataProvider) this).geoRssItemData().getGeoRssPointAsCoordinate();
    }

    /**
     * Sets the GeoRSS point.
     *
     * @param geoRssPoint the point to set
     */
    default void setGeoRssPoint(String geoRssPoint) {
        ((GeoRssItemDataProvider) this).geoRssItemData().setGeoRssPoint(geoRssPoint);
    }

    /**
     * Returns the GeoRSS line as a string.
     *
     * @return the GeoRSS line
     */
    default Optional<String> getGeoRssLine() {
        return ((GeoRssItemDataProvider) this).geoRssItemData().getGeoRssLine();
    }

    /**
     * Returns the GeoRSS line as a list of Coordinates.
     *
     * @return the list of coordinates
     */
    default List<Coordinate> getGeoRssLineAsCoordinates() {
        return ((GeoRssItemDataProvider) this).geoRssItemData().getGeoRssLineAsCoordinates();
    }

    /**
     * Sets the GeoRSS line.
     *
     * @param geoRssLine the line to set
     */
    default void setGeoRssLine(String geoRssLine) {
        ((GeoRssItemDataProvider) this).geoRssItemData().setGeoRssLine(geoRssLine);
    }

    /**
     * Returns the GeoRSS polygon as a string.
     *
     * @return the GeoRSS polygon
     */
    default Optional<String> getGeoRssPolygon() {
        return ((GeoRssItemDataProvider) this).geoRssItemData().getGeoRssPolygon();
    }

    /**
     * Returns the GeoRSS polygon as a list of Coordinates.
     *
     * @return the list of coordinates
     */
    default List<Coordinate> getGeoRssPolygonAsCoordinates() {
        return ((GeoRssItemDataProvider) this).geoRssItemData().getGeoRssPolygonAsCoordinates();
    }

    /**
     * Sets the GeoRSS polygon.
     *
     * @param geoRssPolygon the polygon to set
     */
    default void setGeoRssPolygon(String geoRssPolygon) {
        ((GeoRssItemDataProvider) this).geoRssItemData().setGeoRssPolygon(geoRssPolygon);
    }

    /**
     * Returns the GeoRSS box as a string.
     *
     * @return the GeoRSS box
     */
    default Optional<String> getGeoRssBox() {
        return ((GeoRssItemDataProvider) this).geoRssItemData().getGeoRssBox();
    }

    /**
     * Returns the GeoRSS box as a list of Coordinates.
     *
     * @return the list of coordinates
     */
    default List<Coordinate> getGeoRssBoxAsCoordinates() {
        return ((GeoRssItemDataProvider) this).geoRssItemData().getGeoRssBoxAsCoordinates();
    }

    /**
     * Sets the GeoRSS box.
     *
     * @param geoRssBox the box to set
     */
    default void setGeoRssBox(String geoRssBox) {
        ((GeoRssItemDataProvider) this).geoRssItemData().setGeoRssBox(geoRssBox);
    }

    /**
     * Returns the GeoRSS elevation.
     *
     * @return the elevation
     */
    default Optional<Double> getGeoRssElevation() {
        return ((GeoRssItemDataProvider) this).geoRssItemData().getGeoRssElevation();
    }

    /**
     * Sets the GeoRSS elevation.
     *
     * @param geoRssElevation the elevation to set
     */
    default void setGeoRssElevation(Double geoRssElevation) {
        ((GeoRssItemDataProvider) this).geoRssItemData().setGeoRssElevation(geoRssElevation);
    }

    /**
     * Returns the GeoRSS floor.
     *
     * @return the floor
     */
    default Optional<Integer> getGeoRssFloor() {
        return ((GeoRssItemDataProvider) this).geoRssItemData().getGeoRssFloor();
    }

    /**
     * Sets the GeoRSS floor.
     *
     * @param geoRssFloor the floor to set
     */
    default void setGeoRssFloor(Integer geoRssFloor) {
        ((GeoRssItemDataProvider) this).geoRssItemData().setGeoRssFloor(geoRssFloor);
    }

    /**
     * Returns the GeoRSS radius.
     *
     * @return the radius
     */
    default Optional<Double> getGeoRssRadius() {
        return ((GeoRssItemDataProvider) this).geoRssItemData().getGeoRssRadius();
    }

    /**
     * Sets the GeoRSS radius.
     *
     * @param geoRssRadius the radius to set
     */
    default void setGeoRssRadius(Double geoRssRadius) {
        ((GeoRssItemDataProvider) this).geoRssItemData().setGeoRssRadius(geoRssRadius);
    }

    /**
     * Returns the GeoRSS feature type tag.
     *
     * @return the feature type tag
     */
    default Optional<String> getGeoRssFeatureTypeTag() {
        return ((GeoRssItemDataProvider) this).geoRssItemData().getGeoRssFeatureTypeTag();
    }

    /**
     * Sets the GeoRSS feature type tag.
     *
     * @param geoRssFeatureTypeTag the feature type tag to set
     */
    default void setGeoRssFeatureTypeTag(String geoRssFeatureTypeTag) {
        ((GeoRssItemDataProvider) this).geoRssItemData().setGeoRssFeatureTypeTag(geoRssFeatureTypeTag);
    }

    /**
     * Returns the GeoRSS relationship tag.
     *
     * @return the relationship tag
     */
    default Optional<String> getGeoRssRelationshipTag() {
        return ((GeoRssItemDataProvider) this).geoRssItemData().getGeoRssRelationshipTag();
    }

    /**
     * Sets the GeoRSS relationship tag.
     *
     * @param geoRssRelationshipTag the relationship tag to set
     */
    default void setGeoRssRelationshipTag(String geoRssRelationshipTag) {
        ((GeoRssItemDataProvider) this).geoRssItemData().setGeoRssRelationshipTag(geoRssRelationshipTag);
    }

    /**
     * Returns the GeoRSS feature name.
     *
     * @return the feature name
     */
    default Optional<String> getGeoRssFeatureName() {
        return ((GeoRssItemDataProvider) this).geoRssItemData().getGeoRssFeatureName();
    }

    /**
     * Sets the GeoRSS feature name.
     *
     * @param geoRssFeatureName the feature name to set
     */
    default void setGeoRssFeatureName(String geoRssFeatureName) {
        ((GeoRssItemDataProvider) this).geoRssItemData().setGeoRssFeatureName(geoRssFeatureName);
    }
}
