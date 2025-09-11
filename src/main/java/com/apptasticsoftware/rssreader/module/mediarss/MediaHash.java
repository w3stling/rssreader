package com.apptasticsoftware.rssreader.module.mediarss;

import java.util.Objects;
import java.util.Optional;

/**
 * The hash of the binary media file. Can appear multiple times as long as each instance
 * uses a different algorithm. If no algorithm is specified, the default is MD5.
 *
 * Examples:
 * {@code
 * <!-- MD5 hash -->
 * <media:hash algo="md5">dfdec888b72151965a34b4b59031290a</media:hash>
 *
 * <!-- SHA-1 hash -->
 * <media:hash algo="sha-1">2b8b815229aa8a61e483fb4ba0588b8b6c21645c</media:hash>
 * }
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
