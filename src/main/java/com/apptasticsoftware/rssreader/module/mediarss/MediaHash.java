package com.apptasticsoftware.rssreader.module.mediarss;

import java.util.Optional;

/**
 * The hash of the binary media file.
 */
public class MediaHash {
    private String hash;
    private String algo;

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
     * @return algo
     */
    public Optional<String> getAlgo() {
        return Optional.ofNullable(algo);
    }

    /**
     * The algorithm used to create the hash.
     * @param algo algo
     */
    public void setAlgo(String algo) {
        this.algo = algo;
    }
}
