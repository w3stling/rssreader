package com.apptasticsoftware.rssreader.module.mediarss;

import java.time.Duration;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        if (time == null || time.isEmpty()) {
            return Optional.empty();
        }
        // Pattern for H:M:S.h (npt-hhmmss)
        Pattern patternHMS = Pattern.compile("^(\\d+):(\\d+):(\\d+(?:\\.\\d+)?)$");
        // Pattern for S.h (npt-sec)
        Pattern patternS = Pattern.compile("^(\\d+(?:\\.\\d+)?)$");
        Matcher matcherHMS = patternHMS.matcher(time);
        Matcher matcherS = patternS.matcher(time);
        try {
            if (matcherHMS.matches()) {
                int hours = Integer.parseInt(matcherHMS.group(1));
                int minutes = Integer.parseInt(matcherHMS.group(2));
                double seconds = Double.parseDouble(matcherHMS.group(3));
                long totalMillis = (long)(((long)hours * 3600L + (long)minutes * 60L + seconds) * 1000);
                return Optional.of(Duration.ofMillis(totalMillis));
            } else if (matcherS.matches()) {
                double seconds = Double.parseDouble(matcherS.group(1));
                long totalMillis = (long)(seconds * 1000);
                return Optional.of(Duration.ofMillis(totalMillis));
            }
        } catch (NumberFormatException e) {
            // ignore and return empty
        }
        return Optional.empty();
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
