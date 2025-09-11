package com.apptasticsoftware.rssreader.module.mediarss;

import java.util.Objects;

/**
 * Specifies the rating-related information about a media object. This element appears within
 * the media:community element and provides user-generated rating information.
 *
 * Example:
 * {@code <media:community>
 *     <media:starRating average="3.5" count="20" min="1" max="10" />
 * </media:community>}
 *
 * In this example, the media object has received 20 ratings with an average of 3.5 stars,
 * where ratings can range from 1 to 10.
 */
public class MediaStarRating {
    private Integer max;
    private Integer min;
    private Integer count;
    private Double average;

    /**
     * Gets the maximum rating value.
     * @return max The maximum possible rating value
     */
    public Integer getMax() {
        return max;
    }

    /**
     * Sets the maximum rating value.
     * @param max The maximum possible rating value
     */
    public void setMax(Integer max) {
        this.max = max;
    }

    /**
     * Gets the minimum rating value.
     * @return min The minimum possible rating value
     */
    public Integer getMin() {
        return min;
    }

    /**
     * Sets the minimum rating value.
     * @param min The minimum possible rating value
     */
    public void setMin(Integer min) {
        this.min = min;
    }

    /**
     * Gets the total count of ratings.
     * @return count The total number of ratings received
     */
    public Integer getCount() {
        return count;
    }

    /**
     * Sets the total count of ratings.
     * @param count The total number of ratings received
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * Gets the average rating value.
     * @return average The average value of all ratings
     */
    public Double getAverage() {
        return average;
    }

    /**
     * Sets the average rating value.
     * @param average The average value of all ratings
     */
    public void setAverage(Double average) {
        this.average = average;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MediaStarRating that = (MediaStarRating) o;
        return Objects.equals(getMax(), that.getMax()) &&
               Objects.equals(getMin(), that.getMin()) &&
               Objects.equals(getCount(), that.getCount()) &&
               Objects.equals(getAverage(), that.getAverage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMax(), getMin(), getCount(), getAverage());
    }
}
