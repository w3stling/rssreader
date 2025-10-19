package com.apptasticsoftware.rssreader.module.podcast;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents a podcast episode number with optional display text.
 * This element exists largely for compatibility with the &lt;podcast:season&gt; tag,
 * while also providing display flexibility for episode numbering.
 */
public class PodcastEpisode {
    private double episode;
    private String display;

    /**
     * Gets the decimal episode number.
     * Supports decimal numbers (e.g., 100.5) for special episodes between regular episodes.
     * @return the episode number
     */
    public double getEpisode() {
        return episode;
    }

    /**
     * Sets the decimal episode number.
     * @param episode the episode number to set
     */
    public void setEpisode(double episode) {
        this.episode = episode;
    }

    /**
     * Gets the display text for the episode.
     * If present, podcast apps are encouraged to show this value instead of the numerical episode number.
     * Limited to 32 characters.
     * @return Optional containing the display text, or empty if not set
     */
    public Optional<String> getDisplay() {
        return Optional.ofNullable(display);
    }

    /**
     * Sets the display text for the episode.
     * @param display the display text to set (should not exceed 32 characters)
     */
    public void setDisplay(String display) {
        this.display = display;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PodcastEpisode that = (PodcastEpisode) o;
        return Double.compare(getEpisode(), that.getEpisode()) == 0 && Objects.equals(getDisplay(), that.getDisplay());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEpisode(), getDisplay());
    }
}
