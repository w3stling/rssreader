package com.apptasticsoftware.rssreader.module.podcast;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents a source element within a podcast:alternateEnclosure tag that defines a URI location for a media file.
 * At least one PodcastSource must be present within every alternateEnclosure element.
 */
public class PodcastSource {
    private String uri;
    private String contentType;

    /**
     * Gets the URI where the media file resides.
     *
     * @return the URI of the media file
     */
    public String getUri() {
        return uri;
    }

    /**
     * Sets the required URI where the media file resides.
     *
     * @param uri the URI location of the media file
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * Gets the content type (MIME type) of the file.
     * This is particularly useful when the transport mechanism is different than the file being delivered,
     * such as in the case of torrents.
     *
     * @return an Optional containing the content type if present, or empty if not set
     */
    public Optional<String> getContentType() {
        return Optional.ofNullable(contentType);
    }

    /**
     * Sets the optional content type (MIME type) of the file.
     *
     * @param contentType the MIME type of the file (e.g., "application/x-bittorrent")
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PodcastSource that = (PodcastSource) o;
        return Objects.equals(getUri(), that.getUri()) && Objects.equals(getContentType(), that.getContentType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUri(), getContentType());
    }
}
