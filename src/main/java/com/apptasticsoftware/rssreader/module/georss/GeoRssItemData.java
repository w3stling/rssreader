package com.apptasticsoftware.rssreader.module.georss;

import java.util.List;
import java.util.Optional;

public interface GeoRssItemData {

    GeoRssItemData getGeoRssItemData();

    default Optional<String> getGeoRssPoint() {
        return getGeoRssItemData().getGeoRssPoint();
    }

    default Optional<Coordinate> getGeoRssPointAsCoordinate() {
        return getGeoRssItemData().getGeoRssPointAsCoordinate();
    }

    default void setGeoRssPoint(String geoRssPoint) {
        getGeoRssItemData().setGeoRssPoint(geoRssPoint);
    }

    default Optional<String> getGeoRssLine() {
        return getGeoRssItemData().getGeoRssLine();
    }

    default List<Coordinate> getGeoRssLineAsCoordinates() {
        return getGeoRssItemData().getGeoRssLineAsCoordinates();
    }

    default void setGeoRssLine(String geoRssLine) {
        getGeoRssItemData().setGeoRssLine(geoRssLine);
    }

    default Optional<String> getGeoRssPolygon() {
        return getGeoRssItemData().getGeoRssPolygon();
    }

    default List<Coordinate> getGeoRssPolygonAsCoordinates() {
        return getGeoRssItemData().getGeoRssPolygonAsCoordinates();
    }

    default void setGeoRssPolygon(String geoRssPolygon) {
        getGeoRssItemData().setGeoRssPolygon(geoRssPolygon);
    }

    default Optional<String> getGeoRssBox() {
        return getGeoRssItemData().getGeoRssBox();
    }

    default List<Coordinate> getGeoRssBoxAsCoordinates() {
        return getGeoRssItemData().getGeoRssBoxAsCoordinates();
    }

    default void setGeoRssBox(String geoRssBox) {
        getGeoRssItemData().setGeoRssBox(geoRssBox);
    }

    default Optional<Double> getGeoRssElevation() {
        return getGeoRssItemData().getGeoRssElevation();
    }

    default void setGeoRssElevation(Double geoRssElevation) {
        getGeoRssItemData().setGeoRssElevation(geoRssElevation);
    }

    default Optional<Integer> getGeoRssFloor() {
        return getGeoRssItemData().getGeoRssFloor();
    }

    default void setGeoRssFloor(Integer geoRssFloor) {
        getGeoRssItemData().setGeoRssFloor(geoRssFloor);
    }

    default Optional<Double> getGeoRssRadius() {
        return getGeoRssItemData().getGeoRssRadius();
    }

    default void setGeoRssRadius(Double geoRssRadius) {
        getGeoRssItemData().setGeoRssRadius(geoRssRadius);
    }

    default Optional<String> getGeoRssFeatureTypeTag() {
        return getGeoRssItemData().getGeoRssFeatureTypeTag();
    }

    default void setGeoRssFeatureTypeTag(String geoRssFeatureTypeTag) {
        getGeoRssItemData().setGeoRssFeatureTypeTag(geoRssFeatureTypeTag);
    }

    default Optional<String> getGeoRssRelationshipTag() {
        return getGeoRssItemData().getGeoRssRelationshipTag();
    }

    default void setGeoRssRelationshipTag(String geoRssRelationshipTag) {
        getGeoRssItemData().setGeoRssRelationshipTag(geoRssRelationshipTag);
    }

    default Optional<String> getGeoRssFeatureName() {
        return getGeoRssItemData().getGeoRssFeatureName();
    }

    default void setGeoRssFeatureName(String geoRssFeatureName) {
        getGeoRssItemData().setGeoRssFeatureName(geoRssFeatureName);
    }
}
