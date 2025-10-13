package com.apptasticsoftware.rssreader.module.podcast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.apptasticsoftware.rssreader.util.Mapper.emptyListIfNull;

public class PodcastValueTimeSplit {
    private int startTime;
    private int duration;
    private Integer remoteStartTime;
    private Integer remotePercentage;
    private PodcastRemoteItem remoteItem;
    private List<PodcastValueRecipient> valueRecipients;

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Optional<Integer> getRemoteStartTime() {
        return Optional.ofNullable(remoteStartTime);
    }

    public void setRemoteStartTime(Integer remoteStartTime) {
        this.remoteStartTime = remoteStartTime;
    }

    public Optional<Integer> getRemotePercentage() {
        return Optional.ofNullable(remotePercentage);
    }

    public void setRemotePercentage(Integer remotePercentage) {
        this.remotePercentage = remotePercentage;
    }

    public Optional<PodcastRemoteItem> getRemoteItem() {
        return Optional.ofNullable(remoteItem);
    }

    public void setRemoteItem(PodcastRemoteItem remoteItem) {
        this.remoteItem = remoteItem;
    }

    public List<PodcastValueRecipient> getValueRecipients() {
        return emptyListIfNull(valueRecipients);
    }

    public void addValueRecipients(PodcastValueRecipient valueRecipient) {
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
