package com.apptasticsoftware.rssreader.module.podcast;

import com.apptasticsoftware.rssreader.util.Util;

import java.time.Duration;
import java.util.Objects;
import java.util.Optional;

/**
 * Represents a soundbite within a podcast episode. Soundbites can be used for episode previews,
 * discoverability, audiogram generation, episode highlights, etc. The audio/video source of the
 * soundbite is assumed to be from the item's &lt;enclosure&gt; element.
 */
public class PodcastSoundbite {
    private String soundbite;
    private double startTime;
    private double duration;

    /**
     * Gets the title or description of the soundbite.
     * @return An Optional containing the soundbite title, or empty if not provided.
     * The value should not exceed 128 characters as it may be truncated by aggregators.
     */
    public Optional<String> getSoundbite() {
        return Optional.ofNullable(soundbite);
    }

    /**
     * Sets the title or description of the soundbite.
     * @param soundbite The free-form string from the podcast creator specifying a title for the soundbite.
     *                  Should not exceed 128 characters.
     */
    public void setSoundbite(String soundbite) {
        this.soundbite = soundbite;
    }

    /**
     * Gets the start time of the soundbite within the podcast episode.
     * @return The time in seconds where the soundbite begins.
     */
    public double getStartTime() {
        return startTime;
    }

    /**
     * Gets the start time of the soundbite within the podcast episode.
     * @return The time in seconds where the soundbite begins.
     */
    public Duration getStartTimeAsDuration() {
        return Util.toDuration(Double.toString(startTime));
    }

    /**
     * Sets the start time of the soundbite within the podcast episode.
     * @param startTime The time in seconds where the soundbite begins.
     */
    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets the duration of the soundbite.
     * @return The length of the soundbite in seconds (recommended between 15 and 120 seconds).
     */
    public double getDuration() {
        return duration;
    }

    /**
     * Gets the duration of the soundbite.
     * @return The length of the soundbite in seconds (recommended between 15 and 120 seconds).
     */
    public Duration getDurationAsDuration() {
        return Util.toDuration(Double.toString(duration));
    }

    /**
     * Sets the duration of the soundbite.
     * @param duration The length of the soundbite in seconds (recommended between 15 and 120 seconds).
     */
    public void setDuration(double duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PodcastSoundbite that = (PodcastSoundbite) o;
        return Double.compare(getStartTime(), that.getStartTime()) == 0 && Double.compare(getDuration(), that.getDuration()) == 0 && Objects.equals(getSoundbite(), that.getSoundbite());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSoundbite(), getStartTime(), getDuration());
    }
}
