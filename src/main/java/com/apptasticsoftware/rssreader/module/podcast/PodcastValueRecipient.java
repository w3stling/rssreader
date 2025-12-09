package com.apptasticsoftware.rssreader.module.podcast;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents a podcast value recipient that designates a destination for payments during media consumption.
 *
 * <p>Each recipient receives a "split" of the total payment according to the number of shares specified
 * in the {@code split} attribute. This element may only exist within a {@code <podcast:value>} element.</p>
 *
 * <p>Multiple recipients can be present in a single {@code <podcast:value>} element, allowing for
 * distributed payment schemes among podcasters, producers, and service providers.</p>
 */
public class PodcastValueRecipient {
    private String type;
    private String address;
    private int split;
    private String name;
    private String customKey;
    private String customValue;
    private boolean fee = false;

    /**
     * Gets the type of receiving address (e.g., "node").
     *
     * @return the slug representing the type of receiving address
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of receiving address.
     *
     * @param type the slug representing the type of receiving address
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the receiving address of the payee.
     *
     * @return the receiving address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the receiving address of the payee.
     *
     * @param address the receiving address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the number of shares of the payment this recipient will receive.
     *
     * @return the split value
     */
    public int getSplit() {
        return split;
    }

    /**
     * Sets the number of shares of the payment this recipient will receive.
     *
     * @param split the split value
     */
    public void setSplit(int split) {
        this.split = split;
    }

    /**
     * Gets the name designating who or what this recipient is.
     *
     * @return the recipient name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name designating who or what this recipient is.
     *
     * @param name the recipient name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the custom record key associated with this recipient's payment.
     *
     * @return an {@code Optional} containing the custom key, or empty if not specified
     */
    public Optional<String> getCustomKey() {
        return Optional.ofNullable(customKey);
    }

    /**
     * Sets the custom record key to send along with the payment.
     *
     * @param customKey the custom record key
     */
    public void setCustomKey(String customKey) {
        this.customKey = customKey;
    }

    /**
     * Gets the custom value associated with the custom key.
     *
     * @return an {@code Optional} containing the custom value, or empty if not specified
     */
    public Optional<String> getCustomValue() {
        return Optional.ofNullable(customValue);
    }

    /**
     * Sets the custom value to pass along with the payment.
     *
     * @param customValue the custom value associated with the custom key
     */
    public void setCustomValue(String customValue) {
        this.customValue = customValue;
    }

    /**
     * Checks whether this recipient is a fee recipient.
     *
     * @return {@code true} if this is a fee recipient, {@code false} otherwise
     */
    public boolean isFee() {
        return fee;
    }

    /**
     * Sets whether this recipient is a fee recipient.
     *
     * @param fee {@code true} if this is a fee recipient, {@code false} otherwise
     */
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
