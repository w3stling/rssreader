package com.apptasticsoftware.rssreader.module.podcast;

import java.util.Objects;
import java.util.Optional;

public class PodcastValueRecipient {
    private String type;
    private String address;
    private int split;
    private String name;
    private String customKey;
    private String customValue;
    private boolean fee = false;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSplit() {
        return split;
    }

    public void setSplit(int split) {
        this.split = split;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Optional<String> getCustomKey() {
        return Optional.ofNullable(customKey);
    }

    public void setCustomKey(String customKey) {
        this.customKey = customKey;
    }

    public Optional<String> getCustomValue() {
        return Optional.ofNullable(customValue);
    }

    public void setCustomValue(String customValue) {
        this.customValue = customValue;
    }

    public boolean isFee() {
        return fee;
    }

    public void setFee(boolean fee) {
        this.fee = fee;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PodcastValueRecipient that = (PodcastValueRecipient) o;
        return getSplit() == that.getSplit() && isFee() == that.isFee() && Objects.equals(getType(), that.getType()) && Objects.equals(getAddress(), that.getAddress()) && Objects.equals(getName(), that.getName()) && Objects.equals(getCustomKey(), that.getCustomKey()) && Objects.equals(getCustomValue(), that.getCustomValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getAddress(), getSplit(), getName(), getCustomKey(), getCustomValue(), isFee());
    }
}
