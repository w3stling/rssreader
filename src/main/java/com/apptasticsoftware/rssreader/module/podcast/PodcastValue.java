package com.apptasticsoftware.rssreader.module.podcast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.apptasticsoftware.rssreader.util.Mapper.emptyListIfNull;

public class PodcastValue {
    private String type;
    private String method;
    private Double suggested;
    private List<PodcastValueRecipient> valueRecipients;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Optional<Double> getSuggested() {
        return Optional.ofNullable(suggested);
    }

    public void setSuggested(Double suggested) {
        this.suggested = suggested;
    }

    public List<PodcastValueRecipient> getValueRecipients() {
        return emptyListIfNull(valueRecipients);
    }

    public void addValueRecipient(PodcastValueRecipient valueRecipient) {
        if (valueRecipients == null) {
            valueRecipients = new ArrayList<>();
        }
        valueRecipients.add(valueRecipient);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PodcastValue that = (PodcastValue) o;
        return Objects.equals(getType(), that.getType()) && Objects.equals(getMethod(), that.getMethod()) && Objects.equals(getSuggested(), that.getSuggested()) && Objects.equals(getValueRecipients(), that.getValueRecipients());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getMethod(), getSuggested(), getValueRecipients());
    }
}
