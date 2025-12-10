package com.apptasticsoftware.rssreader.module.podcast;

import java.util.Objects;

/**
 * Represents the integrity verification method for podcast media within a &lt;podcast:alternateEnclosure&gt; element.
 * This class defines how to verify the integrity of media files using either an SRI-compliant integrity string
 * or a base64 encoded PGP signature, ensuring that the media file has not been tampered with.
 */
public class PodcastIntegrity {
    private String type;
    private String value;

    /**
     * Gets the type of integrity verification method.
     *
     * @return the type of integrity verification, either "sri" or "pgp-signature"
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of integrity verification method.
     *
     * @param type the type of integrity verification, must be either "sri" or "pgp-signature"
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the integrity verification value.
     *
     * @return the value of the SRI string or base64 encoded PGP signature
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the integrity verification value.
     *
     * @param value the value of the SRI string or base64 encoded PGP signature
     */
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PodcastIntegrity that = (PodcastIntegrity) o;
        return Objects.equals(getType(), that.getType()) && Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getValue());
    }
}
