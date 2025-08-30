package com.apptasticsoftware.rssreader.module.mediarss;

import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MediaRating that = (MediaRating) o;
        return Objects.equals(getRating(), that.getRating()) && Objects.equals(getScheme(), that.getScheme());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRating(), getScheme());
    }
}
