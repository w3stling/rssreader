package com.apptasticsoftware.rssreader.module.podcast;

import java.util.Objects;
import java.util.Optional;

public class PodcastSoundbite {
    private String soundbite;
    private double startTime;
    private double duration;

    public Optional<String> getSoundbite() {
        return Optional.ofNullable(soundbite);
    }

    public void setSoundbite(String soundbite) {
        this.soundbite = soundbite;
    }

    public double getStartTime() {
        return startTime;
    }

    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }

    public double getDuration() {
        return duration;
    }

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
