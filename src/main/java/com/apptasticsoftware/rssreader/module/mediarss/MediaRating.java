package com.apptasticsoftware.rssreader.module.mediarss;

import java.util.Objects;
import java.util.Optional;

/**
 * Allows the permissible audience to be declared. If this element is not included,
 * it assumes that no restrictions are necessary. Multiple rating elements can be used
 * to specify ratings according to different schemes.
 *
 * Examples:
 * {@code
 * <!-- Simple rating scheme -->
 * <media:rating scheme="urn:simple">adult</media:rating>
 *
 * <!-- ICRA rating scheme -->
 * <media:rating scheme="urn:icra">r (cz 1 lz 1 nz 1 oz 1 vz 1)</media:rating>
 *
 * <!-- MPAA rating scheme -->
 * <media:rating scheme="urn:mpaa">pg</media:rating>
 *
 * <!-- V-Chip rating scheme -->
 * <media:rating scheme="urn:v-chip">tv-y7-fv</media:rating>
 * }
 *
 * Note: If no scheme is specified, the default scheme is "urn:simple" with possible
 * values of "adult" or "nonadult".
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
