package com.apptasticsoftware.rssreader.module.mediarss;

import java.util.Objects;

/**
 * Specifies the rating-related information about a media object.
 */
public class MediaStarRating {
    private Integer max;
    private Integer min;
    private Integer count;
    private Double average;

    /**
     * Max rating value.
     * @return max
     */
    public Integer getMax() {
        return max;
    }

    /**
     * Max rating value.
     * @param max max
     */
    public void setMax(Integer max) {
        this.max = max;
    }

    /**
     * Min rating value.
     * @return min
     */
    public Integer getMin() {
        return min;
    }

    /**
     * Min rating value.
     * @param min min
     */
    public void setMin(Integer min) {
        this.min = min;
    }

    /**
     * Count of ratings.
     * @return count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * Count of ratings.
     * @param count
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * Average rating.
     * @return average
     */
    public Double getAverage() {
        return average;
    }

    /**
     * Average rating.
     * @param average
     */
    public void setAverage(Double average) {
        this.average = average;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MediaStarRating that = (MediaStarRating) o;
        return Objects.equals(getMax(), that.getMax()) && Objects.equals(getMin(), that.getMin()) && Objects.equals(getCount(), that.getCount()) && Objects.equals(getAverage(), that.getAverage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMax(), getMin(), getCount(), getAverage());
    }
}
