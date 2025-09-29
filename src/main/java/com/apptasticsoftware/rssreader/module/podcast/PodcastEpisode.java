package com.apptasticsoftware.rssreader.module.podcast;

import java.util.Objects;
import java.util.Optional;

public class PodcastEpisode {
    private double episode;
    private String display;

    public double getEpisode() {
        return episode;
    }

    public void setEpisode(double episode) {
        this.episode = episode;
    }

    public Optional<String> getDisplay() {
        return Optional.ofNullable(display);
    }

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
