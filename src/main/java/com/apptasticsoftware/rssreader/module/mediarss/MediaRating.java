package com.apptasticsoftware.rssreader.module.mediarss;

import java.util.Optional;

/**
 * Allows the permissible audience to be declared
 */
public class MediaRating {
    private String rating;
    private String scheme;

    /**
     * The permissible audience to be declared
     * @return rating
     */
    public String getRating() {
        return rating;
    }

    /**
     * The permissible audience to be declared
     * @param rating rating
     */
    public void setRating(String rating) {
        this.rating = rating;
    }

    /**
     * The URI that identifies the rating scheme.
     * @return schema
     */
    public Optional<String> getScheme() {
        return Optional.ofNullable(scheme);
    }

    /**
     * The URI that identifies the rating scheme.
     * @param scheme schema
     */
    public void setScheme(String scheme) {
        this.scheme = scheme;
    }
}
