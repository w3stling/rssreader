package com.apptasticsoftware.rssreader.module.podcast;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents a season within a podcast, allowing for identification and grouping of episodes.
 * This class corresponds to the podcast:season tag in RSS feeds.
 */
public class PodcastSeason {
    private int season;
    private String name;

    /**
     * Gets the season number.
     *
     * @return the season number
     */
    public int getSeason() {
        return season;
    }

    /**
     * Sets the season number. This is a required attribute that represents the chronological
     * number of the season.
     *
     * @param season the season number to set
     */
    public void setSeason(int season) {
        this.season = season;
    }

    /**
     * Gets the optional name of the season. If present, applications may use this name
     * instead of the season number for display purposes.
     *
     * @return an Optional containing the season name if present, or empty if not set
     */
    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    /**
     * Sets the name of the season. The name should not exceed 128 characters.
     *
     * @param name the season name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PodcastSeason that = (PodcastSeason) o;
        return getSeason() == that.getSeason() && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSeason(), getName());
    }
}
