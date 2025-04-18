package com.apptasticsoftware.rssreader.module.mediarss;

import java.util.Optional;

/**
 * Short description describing the media object typically a sentence in length.
 */
public class MediaDescription {
    private String type;
    private String description;

    /**
     * The type of text embedded. Possible values are either "plain" or "html".
     * @return type
     */
    public Optional<String> getType() {
        return Optional.ofNullable(type);
    }

    /**
     * The type of text embedded. Possible values are either "plain" or "html".
     * @param type type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Short description describing the media object typically a sentence in length.
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Short description describing the media object typically a sentence in length.
     * @param description description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
