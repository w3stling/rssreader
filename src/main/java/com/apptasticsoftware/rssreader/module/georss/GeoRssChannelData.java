package com.apptasticsoftware.rssreader.module.georss;

import java.util.List;
import java.util.Optional;

public interface GeoRssChannelData {

    GeoRssChannelData getGeoRssChannelData();

    default Optional<String> getGeoRssPoint() {
        return getGeoRssChannelData().getGeoRssPoint();
    }

    default Optional<Coordinate> getGeoRssPointAsCoordinate() {
        return getGeoRssChannelData().getGeoRssPointAsCoordinate();
    }

    default void setGeoRssPoint(String geoRssPoint) {
        getGeoRssChannelData().setGeoRssPoint(geoRssPoint);
    }

    default Optional<String> getGeoRssLine() {
        return getGeoRssChannelData().getGeoRssLine();
    }

    default List<Coordinate> getGeoRssLineAsCoordinates() {
        return getGeoRssChannelData().getGeoRssLineAsCoordinates();
    }

    default void setGeoRssLine(String geoRssLine) {
        getGeoRssChannelData().setGeoRssLine(geoRssLine);
    }

    default Optional<String> getGeoRssPolygon() {
        return getGeoRssChannelData().getGeoRssPolygon();
    }

    default List<Coordinate> getGeoRssPolygonAsCoordinates() {
        return getGeoRssChannelData().getGeoRssPolygonAsCoordinates();
    }

    default void setGeoRssPolygon(String geoRssPolygon) {
        getGeoRssChannelData().setGeoRssPolygon(geoRssPolygon);
    }

    default Optional<String> getGeoRssBox() {
        return getGeoRssChannelData().getGeoRssBox();
    }

    default List<Coordinate> getGeoRssBoxAsCoordinates() {
        return getGeoRssChannelData().getGeoRssBoxAsCoordinates();
    }

    default void setGeoRssBox(String geoRssBox) {
        getGeoRssChannelData().setGeoRssBox(geoRssBox);
    }

    default Optional<Double> getGeoRssElev() {
        return getGeoRssChannelData().getGeoRssElev();
    }

    default void setGeoRssElev(Double geoRssElev) {
        getGeoRssChannelData().setGeoRssElev(geoRssElev);
    }

    default Optional<Integer> getGeoRssFloor() {
        return getGeoRssChannelData().getGeoRssFloor();
    }

    default void setGeoRssFloor(Integer geoRssFloor) {
        getGeoRssChannelData().setGeoRssFloor(geoRssFloor);
    }

    default Optional<Double> getGeoRssRadius() {
        return getGeoRssChannelData().getGeoRssRadius();
    }

    default void setGeoRssRadius(Double geoRssRadius) {
        getGeoRssChannelData().setGeoRssRadius(geoRssRadius);
    }

    default Optional<String> getGeoRssFeatureTypeTag() {
        return getGeoRssChannelData().getGeoRssFeatureTypeTag();
    }

    default void setGeoRssFeatureTypeTag(String geoRssFeatureTypeTag) {
        getGeoRssChannelData().setGeoRssFeatureTypeTag(geoRssFeatureTypeTag);
    }

    default Optional<String> getGeoRssRelationshipTag() {
        return getGeoRssChannelData().getGeoRssRelationshipTag();
    }

    default void setGeoRssRelationshipTag(String geoRssRelationshipTag) {
        getGeoRssChannelData().setGeoRssRelationshipTag(geoRssRelationshipTag);
    }

    default Optional<String> getGeoRssFeatureName() {
        return getGeoRssChannelData().getGeoRssFeatureName();
    }

    default void setGeoRssFeatureName(String geoRssFeatureName) {
        getGeoRssChannelData().setGeoRssFeatureName(geoRssFeatureName);
    }
}
