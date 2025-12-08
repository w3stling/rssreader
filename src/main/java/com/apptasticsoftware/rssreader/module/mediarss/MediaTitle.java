package com.apptasticsoftware.rssreader.module.mediarss;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents the title of a particular media object in the Media RSS specification.
 * The title can be either plain text or HTML-encoded content.
 *
 * Example:
 * {@code <media:title type="plain">The Judy's -- The Moo Song</media:title>}
 */
public class MediaTitle {
    private String type;
    private String title;

    /**
     * Gets the type of text embedded in the title.
     * 
     * @return Optional containing the type of text. Possible values are "plain" or "html".
     *         If not specified, defaults to "plain". Returns empty Optional if not set.
     */
    public Optional<String> getType() {
        return Optional.ofNullable(type);
    }

    /**
     * Sets the type of text embedded in the title.
     * 
     * @param type The type of text. Should be either "plain" or "html".
     *             If using "html", all HTML must be entity-encoded.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the title text of the media object.
     * 
     * @return The title text of the media object.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title text of the media object.
     * 
     * @param title The title text to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MediaTitle that = (MediaTitle) o;
        return Objects.equals(getType(), that.getType()) && Objects.equals(getTitle(), that.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getTitle());
    }
}
