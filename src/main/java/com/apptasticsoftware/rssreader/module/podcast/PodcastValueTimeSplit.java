package com.apptasticsoftware.rssreader.module.podcast;

import com.apptasticsoftware.rssreader.util.Util;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.apptasticsoftware.rssreader.util.Mapper.emptyListIfNull;

/**
 * Represents a time-based value split for alternative recipients during a specific period of playback.
 * Allows different value recipient information to be used for a defined duration within an enclosure.
 * Can contain either local value recipients or a reference to a remote item.
 */
public class PodcastValueTimeSplit {
    private int startTime;
    private int duration;
    private Integer remoteStartTime;
    private Integer remotePercentage;
    private PodcastRemoteItem remoteItem;
    private List<PodcastValueRecipient> valueRecipients;

    /**
     * Gets the start time in seconds when this value split begins.
     * @return the start time in seconds
     */
    public int getStartTime() {
        return startTime;
    }

    /**
     * Gets the start time as a Duration object.
     * @return the start time as a Duration
     */
    public Duration getStartTimeAsDuration() {
        return Util.toDuration(Integer.toString(startTime));
    }

    /**
     * Sets the start time in seconds.
     * @param startTime the start time in seconds
     */
    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets the duration in seconds for which this value split applies.
     * @return the duration in seconds
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Gets the duration as a Duration object.
     * @return the duration as a Duration
     */
    public Duration getDurationAsDuration() {
        return Util.toDuration(Integer.toString(duration));
    }

    /**
     * Sets the duration in seconds.
     * @param duration the duration in seconds
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Gets the optional start time in the remote item.
     * @return the remote start time in seconds, or empty if not set
     */
    public Optional<Integer> getRemoteStartTime() {
        return Optional.ofNullable(remoteStartTime);
    }

    /**
     * Gets the remote start time as a Duration object.
     * @return the remote start time as a Duration, or empty if not set
     */
    public Optional<Duration> getRemoteStartTimeAsDuration() {
        return Optional.ofNullable(remoteStartTime).map(Object::toString).map(Util::toDuration);
    }

    /**
     * Sets the start time in the remote item.
     * @param remoteStartTime the remote start time in seconds
     */
    public void setRemoteStartTime(Integer remoteStartTime) {
        this.remoteStartTime = remoteStartTime;
    }

    /**
     * Gets the percentage of payment for remote recipients.
     * @return the remote percentage, or empty if not set
     */
    public Optional<Integer> getRemotePercentage() {
        return Optional.ofNullable(remotePercentage);
    }

    /**
     * Sets the percentage of payment for remote recipients.
     * @param remotePercentage the remote percentage
     */
    public void setRemotePercentage(Integer remotePercentage) {
        this.remotePercentage = remotePercentage;
    }

    /**
     * Gets the optional remote item reference.
     * @return the remote item, or empty if not set
     */
    public Optional<PodcastRemoteItem> getRemoteItem() {
        return Optional.ofNullable(remoteItem);
    }

    /**
     * Sets the remote item reference.
     * @param remoteItem the remote item to set
     */
    public void setRemoteItem(PodcastRemoteItem remoteItem) {
        this.remoteItem = remoteItem;
    }

    /**
     * Gets the list of value recipients for this time split.
     * @return the list of value recipients, or empty list if none set
     */
    public List<PodcastValueRecipient> getValueRecipients() {
        return emptyListIfNull(valueRecipients);
    }

    /**
     * Adds a value recipient to this time split.
     * @param valueRecipient the value recipient to add
     */
    public void addValueRecipient(PodcastValueRecipient valueRecipient) {
        if (valueRecipients == null) {
            valueRecipients = new ArrayList<>();
        }
        this.valueRecipients.add(valueRecipient);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PodcastValueTimeSplit that = (PodcastValueTimeSplit) o;
        return getStartTime() == that.getStartTime() && getDuration() == that.getDuration() && Objects.equals(getRemoteStartTime(), that.getRemoteStartTime()) && Objects.equals(getRemotePercentage(), that.getRemotePercentage()) && Objects.equals(getRemoteItem(), that.getRemoteItem()) && Objects.equals(getValueRecipients(), that.getValueRecipients());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStartTime(), getDuration(), getRemoteStartTime(), getRemotePercentage(), getRemoteItem(), getValueRecipients());
    }
}
