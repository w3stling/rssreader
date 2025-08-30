package com.apptasticsoftware.rssreader.module.mediarss;

import java.util.Objects;
import java.util.Optional;

/**
 * The hash of the binary media file.
 */
public class MediaHash {
    private String hash;
    private String algorithm;

    /**
     * The hash of the binary media file.
     * @return hash
     */
    public String getHash() {
        return hash;
    }

    /**
     * The hash of the binary media file.
     * @param hash hash
     */
    public void setHash(String hash) {
        this.hash = hash;
    }

    /**
     * The algorithm used to create the hash.
     * @return algorithm
     */
    public Optional<String> getAlgorithm() {
        return Optional.ofNullable(algorithm);
    }

    /**
     * The algorithm used to create the hash.
     * @param algorithm algorithm
     */
    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MediaHash mediaHash = (MediaHash) o;
        return Objects.equals(getHash(), mediaHash.getHash()) && Objects.equals(getAlgorithm(), mediaHash.getAlgorithm());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHash(), getAlgorithm());
    }
}
