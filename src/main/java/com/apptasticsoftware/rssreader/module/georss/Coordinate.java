package com.apptasticsoftware.rssreader.module.georss;

import java.util.Objects;

/**
 * Represents a geographic coordinate with latitude and longitude.
 */
public class Coordinate {
    private double latitude;
    private double longitude;

    /**
     * Constructs a Coordinate with default values.
     */
    public Coordinate() {

    }

    /**
     * Constructs a Coordinate with the specified latitude and longitude.
     *
     * @param latitude the latitude value
     * @param longitude the longitude value
     */
    public Coordinate(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Returns the latitude value.
     *
     * @return the latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Sets the latitude value.
     *
     * @param latitude the latitude to set
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Returns the longitude value.
     *
     * @return the longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Sets the longitude value.
     *
     * @param longitude the longitude to set
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Compares this coordinate with another object for equality.
     *
     * @param o the object to compare with
     * @return true if both coordinates have equal latitude and longitude
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return Double.compare(getLatitude(), that.getLatitude()) == 0 && Double.compare(getLongitude(), that.getLongitude()) == 0;
    }

    /**
     * Returns the hash code for this coordinate.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(getLatitude(), getLongitude());
    }
}
