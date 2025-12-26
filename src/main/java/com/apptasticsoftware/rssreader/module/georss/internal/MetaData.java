package com.apptasticsoftware.rssreader.module.georss.internal;

import com.apptasticsoftware.rssreader.module.georss.Coordinate;

import java.util.*;

public class MetaData {
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

    public Optional<String> getGeoRssPoint() {
        return Optional.ofNullable(geoRssPoint);
    }

    public Optional<Coordinate> getGeoRssPointAsCoordinate() {
        return getGeoRssPoint().map(this::toCoordinate);
    }

    public void setGeoRssPoint(String geoRssPoint) {
        this.geoRssPoint = geoRssPoint;
    }

    public Optional<String> getGeoRssLine() {
        return Optional.ofNullable(geoRssLine);
    }

    public List<Coordinate> getGeoRssLineAsCoordinates() {
        return getGeoRssLine().map(this::toCoordinates).orElse(Collections.emptyList());
    }

    public void setGeoRssLine(String geoRssLine) {
        this.geoRssLine = geoRssLine;
    }

    public Optional<String> getGeoRssPolygon() {
        return Optional.ofNullable(geoRssPolygon);
    }

    public List<Coordinate> getGeoRssPolygonAsCoordinates() {
        return Optional.ofNullable(geoRssPolygon).map(this::toCoordinates).orElse(Collections.emptyList());
    }

    public void setGeoRssPolygon(String geoRssPolygon) {
        this.geoRssPolygon = geoRssPolygon;
    }

    public Optional<String> getGeoRssBox() {
        return Optional.ofNullable(geoRssBox);
    }

    public List<Coordinate> getGeoRssBoxAsCoordinates() {
        return getGeoRssBox().map(this::toCoordinates).orElse(Collections.emptyList());
    }

    public void setGeoRssBox(String geoRssBox) {
        this.geoRssBox = geoRssBox;
    }

    public Optional<Double> getGeoRssElev() {
        return Optional.ofNullable(geoRssElev);
    }

    public void setGeoRssElev(Double geoRssElev) {
        this.geoRssElev = geoRssElev;
    }

    public Optional<Integer> getGeoRssFloor() {
        return Optional.ofNullable(geoRssFloor);
    }

    public void setGeoRssFloor(Integer geoRssFloor) {
        this.geoRssFloor = geoRssFloor;
    }

    public Optional<Double> getGeoRssRadius() {
        return Optional.ofNullable(geoRssRadius);
    }

    public void setGeoRssRadius(Double geoRssRadius) {
        this.geoRssRadius = geoRssRadius;
    }

    public Optional<String> getGeoRssFeatureTypeTag() {
        return Optional.ofNullable(geoRssFeatureTypeTag);
    }

    public void setGeoRssFeatureTypeTag(String geoRssFeatureTypeTag) {
        this.geoRssFeatureTypeTag = geoRssFeatureTypeTag;
    }

    public Optional<String> getGeoRssRelationshipTag() {
        return Optional.ofNullable(geoRssRelationshipTag);
    }

    public void setGeoRssRelationshipTag(String geoRssRelationshipTag) {
        this.geoRssRelationshipTag = geoRssRelationshipTag;
    }

    public  Optional<String> getGeoRssFeatureName() {
        return Optional.ofNullable(geoRssFeatureName);
    }

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
        MetaData that = (MetaData) o;
        return Objects.equals(getGeoRssPoint(), that.getGeoRssPoint()) && Objects.equals(getGeoRssLine(), that.getGeoRssLine()) && Objects.equals(getGeoRssPolygon(), that.getGeoRssPolygon()) && Objects.equals(getGeoRssBox(), that.getGeoRssBox()) && Objects.equals(getGeoRssElev(), that.getGeoRssElev()) && Objects.equals(getGeoRssFloor(), that.getGeoRssFloor()) && Objects.equals(getGeoRssRadius(), that.getGeoRssRadius()) && Objects.equals(getGeoRssFeatureTypeTag(), that.getGeoRssFeatureTypeTag()) && Objects.equals(getGeoRssRelationshipTag(), that.getGeoRssRelationshipTag()) && Objects.equals(getGeoRssFeatureName(), that.getGeoRssFeatureName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGeoRssPoint(), getGeoRssLine(), getGeoRssPolygon(), getGeoRssBox(), getGeoRssElev(), getGeoRssFloor(), getGeoRssRadius(), getGeoRssFeatureTypeTag(), getGeoRssRelationshipTag(), getGeoRssFeatureName());
    }
}
