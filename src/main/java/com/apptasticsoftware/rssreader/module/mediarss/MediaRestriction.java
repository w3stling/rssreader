package com.apptasticsoftware.rssreader.module.mediarss;

import java.util.Objects;

/**
 * Allows restrictions to be placed on the aggregator rendering the media in the feed.
 */
public class MediaRestriction {
    private String restriction;
    private String relationship;
    private String type;

    /**
     * The restrictions to be placed on the aggregator rendering the media in the feed.
     * @return relationship
     */
    public String getRestriction() {
        return restriction;
    }

    /**
     * The restrictions to be placed on the aggregator rendering the media in the feed.
     * @param restriction restriction
     */
    public void setRestriction(String restriction) {
        this.restriction = restriction;
    }

    /**
     * Indicates the type of relationship that the restriction represents (allow | deny).
     * @return relationship
     */
    public String getRelationship() {
        return relationship;
    }

    /**
     * Indicates the type of relationship that the restriction represents (allow | deny).
     * @param relationship relationship
     */
    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    /**
     * Specifies the type of restriction (country | uri | sharing ) that the media can be syndicated.
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Specifies the type of restriction (country | uri | sharing ) that the media can be syndicated.
     * @param type type
     */
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MediaRestriction that = (MediaRestriction) o;
        return Objects.equals(getRestriction(), that.getRestriction()) && Objects.equals(getRelationship(), that.getRelationship()) && Objects.equals(getType(), that.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRestriction(), getRelationship(), getType());
    }
}
