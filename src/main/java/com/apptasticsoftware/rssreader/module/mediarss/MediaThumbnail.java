package com.apptasticsoftware.rssreader.module.mediarss;

import com.apptasticsoftware.rssreader.util.Util;

import java.time.Duration;
import java.util.Objects;
import java.util.Optional;

/**
 * Class representing the media thumbnail from the media rss spec.
 */
public class MediaThumbnail {
    private String url;
    private Integer width;
    private Integer height;
    private String time;

    /**
     * Get the url of the thumbnail
     *
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Set the url of the thumbnail
     *
     * @param url url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Get the width of the thumbnail
     *
     * @return width
     */
    public Optional<Integer> getWidth() {
        return Optional.ofNullable(width);
    }

    /**
     * Set the width of the thumbnail
     *
     * @param width width
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * Get the height of the thumbnail
     *
     * @return height
     */
    public Optional<Integer> getHeight() {
        return Optional.ofNullable(height);
    }

    /**
     * Set the height of the thumbnail
     *
     * @param height height
     */
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     * Get the time of the thumbnail
     *
     * @return time
     */
    public Optional<String> getTime() {
        return Optional.ofNullable(time);
    }

    /**
     * Set the time of the thumbnail
     *
     * @param time time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Get the duration of the thumbnail as a java Duration object
     * Parses the time field (H:M:S.h or S.h) to Duration
     *
     * @return Optional Duration
     */
    public Optional<Duration> getTimeAsDuration() {
        return getTime().map(Util::toDuration);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MediaThumbnail that = (MediaThumbnail) o;
        return Objects.equals(getUrl(), that.getUrl()) && Objects.equals(getWidth(), that.getWidth()) && Objects.equals(getHeight(), that.getHeight()) && Objects.equals(getTime(), that.getTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUrl(), getWidth(), getHeight(), getTime());
    }
}
