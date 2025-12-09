package com.apptasticsoftware.rssreader.module.podcast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.apptasticsoftware.rssreader.util.Mapper.emptyListIfNull;

/**
 * Represents a podcast value element that designates the cryptocurrency or payment layer,
 * transport method, and suggested payment amount. Can exist at channel or item level.
 */
public class PodcastValue {
    private String type;
    private String method;
    private Double suggested;
    private List<PodcastValueRecipient> valueRecipients;
    private List<PodcastValueTimeSplit> valueTimeSplits;

    /**
     * Gets the service slug of the cryptocurrency or protocol layer.
     *
     * @return the cryptocurrency type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the service slug of the cryptocurrency or protocol layer.
     *
     * @param type the cryptocurrency type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the transport mechanism for payments.
     *
     * @return the payment transport method
     */
    public String getMethod() {
        return method;
    }

    /**
     * Sets the transport mechanism for payments.
     *
     * @param method the payment transport method
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * Gets the suggested payment amount in the cryptocurrency.
     *
     * @return the suggested payment amount
     */
    public Optional<Double> getSuggested() {
        return Optional.ofNullable(suggested);
    }

    /**
     * Sets the suggested payment amount in the cryptocurrency.
     *
     * @param suggested the suggested payment amount
     */
    public void setSuggested(Double suggested) {
        this.suggested = suggested;
    }

    /**
     * Gets the list of value recipients.
     *
     * @return the value recipients
     */
    public List<PodcastValueRecipient> getValueRecipients() {
        return emptyListIfNull(valueRecipients);
    }

    /**
     * Adds a value recipient.
     *
     * @param valueRecipient the value recipient to add
     */
    public void addValueRecipient(PodcastValueRecipient valueRecipient) {
        if (valueRecipients == null) {
            valueRecipients = new ArrayList<>();
        }
        valueRecipients.add(valueRecipient);
    }

    /**
     * Gets the list of value time splits.
     *
     * @return the value time splits
     */
    public List<PodcastValueTimeSplit> getValueTimeSplits() {
        return emptyListIfNull(valueTimeSplits);
    }

    /**
     * Adds a value time split.
     *
     * @param valueTimeSplit the value time split to add
     */
    public void addValueTimeSplit(PodcastValueTimeSplit valueTimeSplit) {
        if (valueTimeSplits == null) {
            valueTimeSplits = new ArrayList<>();
        }
        valueTimeSplits.add(valueTimeSplit);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PodcastValue that = (PodcastValue) o;
        return Objects.equals(getType(), that.getType()) && Objects.equals(getMethod(), that.getMethod()) && Objects.equals(getSuggested(), that.getSuggested()) && Objects.equals(getValueRecipients(), that.getValueRecipients()) && Objects.equals(getValueTimeSplits(), that.getValueTimeSplits());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getMethod(), getSuggested(), getValueRecipients(), getValueTimeSplits());
    }
}
