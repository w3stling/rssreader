package com.apptasticsoftware.rssreader.module.mediarss;

import java.util.Objects;
import java.util.Optional;

/**
 * Short description describing the media object typically a sentence in length.
 *
 * Examples:
 * {@code
 * <!-- Plain text description -->
 * <media:description type="plain">
 *     This was some really bizarre band I listened to as a young lad.
 * </media:description>
 *
 * <!-- HTML formatted description -->
 * <media:description type="html">
 *     This was a &lt;b&gt;really bizarre&lt;/b&gt; band I listened to as a &lt;i&gt;young lad&lt;/i&gt;.
 * </media:description>
 * }
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MediaDescription that = (MediaDescription) o;
        return Objects.equals(getType(), that.getType()) && Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getDescription());
    }
}
