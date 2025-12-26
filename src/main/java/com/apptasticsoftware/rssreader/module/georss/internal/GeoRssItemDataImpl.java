package com.apptasticsoftware.rssreader.module.georss.internal;

import com.apptasticsoftware.rssreader.module.georss.Coordinate;
import com.apptasticsoftware.rssreader.module.georss.GeoRssItemData;

import java.util.*;

public class GeoRssItemDataImpl implements GeoRssItemData {
    private String geoRssPoint;
    private String geoRssLine;
    private String geoRssPolygon;
    private String geoRssBox;
    private Double geoRssElev;
    private Integer geoRssFloor;
    private Double geoRssRadius;
    private String geoRssFeatureTypeTag;
    private String geoRssRelationshipTag;
    private String geoRssFeatureName;

    @Override
    public GeoRssItemData getGeoRssItemData() {
        return this;
    }

    @Override
    public Optional<String> getGeoRssPoint() {
        return Optional.ofNullable(geoRssPoint);
    }

    @Override
    public Optional<Coordinate> getGeoRssPointAsCoordinate() {
        return getGeoRssPoint().map(this::toCoordinate);
    }

    @Override
    public void setGeoRssPoint(String geoRssPoint) {
        this.geoRssPoint = geoRssPoint;
    }

    @Override
    public Optional<String> getGeoRssLine() {
        return Optional.ofNullable(geoRssLine);
    }

    @Override
    public List<Coordinate> getGeoRssLineAsCoordinates() {
        return getGeoRssLine().map(this::toCoordinates).orElse(Collections.emptyList());
    }

    @Override
    public void setGeoRssLine(String geoRssLine) {
        this.geoRssLine = geoRssLine;
    }

    @Override
    public Optional<String> getGeoRssPolygon() {
        return Optional.ofNullable(geoRssPolygon);
    }

    @Override
    public List<Coordinate> getGeoRssPolygonAsCoordinates() {
        return Optional.ofNullable(geoRssPolygon).map(this::toCoordinates).orElse(Collections.emptyList());
    }

    @Override
    public void setGeoRssPolygon(String geoRssPolygon) {
        this.geoRssPolygon = geoRssPolygon;
    }

    @Override
    public Optional<String> getGeoRssBox() {
        return Optional.ofNullable(geoRssBox);
    }

    @Override
    public List<Coordinate> getGeoRssBoxAsCoordinates() {
        return getGeoRssBox().map(this::toCoordinates).orElse(Collections.emptyList());
    }

    @Override
    public void setGeoRssBox(String geoRssBox) {
        this.geoRssBox = geoRssBox;
    }

    @Override
    public Optional<Double> getGeoRssElev() {
        return Optional.ofNullable(geoRssElev);
    }

    @Override
    public void setGeoRssElev(Double geoRssElev) {
        this.geoRssElev = geoRssElev;
    }

    @Override
    public Optional<Integer> getGeoRssFloor() {
        return Optional.ofNullable(geoRssFloor);
    }

    @Override
    public void setGeoRssFloor(Integer geoRssFloor) {
        this.geoRssFloor = geoRssFloor;
    }

    @Override
    public Optional<Double> getGeoRssRadius() {
        return Optional.ofNullable(geoRssRadius);
    }

    @Override
    public void setGeoRssRadius(Double geoRssRadius) {
        this.geoRssRadius = geoRssRadius;
    }

    @Override
    public Optional<String> getGeoRssFeatureTypeTag() {
        return Optional.ofNullable(geoRssFeatureTypeTag);
    }

    @Override
    public void setGeoRssFeatureTypeTag(String geoRssFeatureTypeTag) {
        this.geoRssFeatureTypeTag = geoRssFeatureTypeTag;
    }

    @Override
    public Optional<String> getGeoRssRelationshipTag() {
        return Optional.ofNullable(geoRssRelationshipTag);
    }

    @Override
    public void setGeoRssRelationshipTag(String geoRssRelationshipTag) {
        this.geoRssRelationshipTag = geoRssRelationshipTag;
    }

    @Override
    public  Optional<String> getGeoRssFeatureName() {
        return Optional.ofNullable(geoRssFeatureName);
    }

    @Override
    public void setGeoRssFeatureName(String geoRssFeatureName) {
        this.geoRssFeatureName = geoRssFeatureName;
    }

    private Coordinate toCoordinate(String value) {
        var coordinates = toCoordinates(value);
        return coordinates.isEmpty() ? null : coordinates.get(0);

    }

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GeoRssItemDataImpl that = (GeoRssItemDataImpl) o;
        return Objects.equals(getGeoRssPoint(), that.getGeoRssPoint()) && Objects.equals(getGeoRssLine(), that.getGeoRssLine()) && Objects.equals(getGeoRssPolygon(), that.getGeoRssPolygon()) && Objects.equals(getGeoRssBox(), that.getGeoRssBox()) && Objects.equals(getGeoRssElev(), that.getGeoRssElev()) && Objects.equals(getGeoRssFloor(), that.getGeoRssFloor()) && Objects.equals(getGeoRssRadius(), that.getGeoRssRadius()) && Objects.equals(getGeoRssFeatureTypeTag(), that.getGeoRssFeatureTypeTag()) && Objects.equals(getGeoRssRelationshipTag(), that.getGeoRssRelationshipTag()) && Objects.equals(getGeoRssFeatureName(), that.getGeoRssFeatureName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGeoRssPoint(), getGeoRssLine(), getGeoRssPolygon(), getGeoRssBox(), getGeoRssElev(), getGeoRssFloor(), getGeoRssRadius(), getGeoRssFeatureTypeTag(), getGeoRssRelationshipTag(), getGeoRssFeatureName());
    }
}
