package com.apptasticsoftware.rssreader.module.mediarss;

import java.util.Optional;

/**
 * Contains user-generated tags. Each tag can be assigned an integer weight in tag_name:weight format.
 * It's up to the provider to choose the way weight is determined for a tag; for example,
 * number of occurrences can be one way to decide weight of a particular tag. Default weight is 1.
 */
public class MediaTag {
    private final String tag;

    /**
     * Constructs a MediaTag with the specified tag string.
     * @param tag user-generated tag
     */
    public MediaTag(String tag) {
        this.tag = tag;
    }

    /**
     * User-generated tag.
     * @return tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * Returns the name part of the tag (before the colon) or the whole tag if no colon is present.
     * @return name
     */
    public String getName() {
        if (tag != null && tag.contains(":")) {
            String[] parts = tag.split(":");
            if (parts.length == 2) {
                return parts[0];
            }
        }
        return tag;
    }

    /**
     * Returns the weight part of the tag (after the colon) if present and parsable as an integer.
     * @return weight
     */
    public Optional<Integer> getWeight() {
        if (tag != null && tag.contains(":")) {
            String[] parts = tag.split(":");
            if (parts.length == 2) {
                try {
                    return Optional.of(Integer.parseInt(parts[1]));
                } catch (NumberFormatException e) {
                    return Optional.empty();
                }
            }
        }
        return Optional.empty();
    }
}
