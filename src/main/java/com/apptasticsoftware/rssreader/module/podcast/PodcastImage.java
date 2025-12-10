package com.apptasticsoftware.rssreader.module.podcast;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents a podcast image that allows for delivering images of various sizes and use cases.
 * This class is cross-compatible with the &lt;itunes:image&gt; tag but expands the use cases
 * for delivering more than just square channel or episode art.
 */
public class PodcastImage {
    private String href;
    private String alt;
    private String aspectRatio;
    private Integer width;
    private Integer height;
    private String type;
    private String purpose;

    /**
     * Gets the URL of the media to embed.
     *
     * @return the URL to the media
     */
    public String getHref() {
        return href;
    }

    /**
     * Sets the URL of the media to embed.
     *
     * @param href the URL to the media (required)
     */
    public void setHref(String href) {
        this.href = href;
    }

    /**
     * Gets the accessibility text replacement for the image's content.
     *
     * @return an Optional containing the alt text, or empty if not set
     */
    public Optional<String> getAlt() {
        return Optional.ofNullable(alt);
    }

    /**
     * Sets the accessibility text replacement for the image's content.
     *
     * @param alt a clear and concise accessibility text (recommended)
     */
    public void setAlt(String alt) {
        this.alt = alt;
    }

    /**
     * Gets the aspect ratio of the image.
     *
     * @return an Optional containing the aspect ratio (e.g., "1/1", "16/9", "4/1"), or empty if not set
     */
    public Optional<String> getAspectRatio() {
        return Optional.ofNullable(aspectRatio);
    }

    /**
     * Sets the aspect ratio of the image following the CSS syntax.
     *
     * @param aspectRatio the aspect ratio value (e.g., "1/1", "16/9", "4/1") (recommended)
     */
    public void setAspectRatio(String aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    /**
     * Gets the width of the asset in pixels.
     *
     * @return an Optional containing the width in pixels, or empty if not set
     */
    public Optional<Integer> getWidth() {
        return Optional.ofNullable(width);
    }

    /**
     * Sets the width of the asset.
     *
     * @param width the width in pixels (recommended)
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * Gets the height of the asset in pixels.
     *
     * @return an Optional containing the height in pixels, or empty if not set
     */
    public Optional<Integer> getHeight() {
        return Optional.ofNullable(height);
    }

    /**
     * Sets the height of the asset.
     *
     * @param height the height in pixels (optional)
     */
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     * Gets the MIME type of the media.
     *
     * @return an Optional containing the MIME type (e.g., "image/jpeg", "video/mp4"), or empty if not set
     */
    public Optional<String> getType() {
        return Optional.ofNullable(type);
    }

    /**
     * Sets the MIME type of the media.
     *
     * @param type the MIME type (e.g., "image/jpeg", "video/mp4") (optional)
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the purpose tokens indicating suggested uses of this media.
     *
     * @return an Optional containing the purpose tokens as case-insensitive, space-separated values, or empty if not set
     */
    public Optional<String> getPurpose() {
        return Optional.ofNullable(purpose);
    }

    /**
     * Sets the purpose tokens indicating suggested uses of this media.
     * The value should not exceed 128 characters.
     *
     * @param purpose case-insensitive, space-separated tokens following W3C syntax (optional)
     */
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PodcastImage that = (PodcastImage) o;
        return Objects.equals(getHref(), that.getHref()) && Objects.equals(getAlt(), that.getAlt()) && Objects.equals(getAspectRatio(), that.getAspectRatio()) && Objects.equals(getWidth(), that.getWidth()) && Objects.equals(getHeight(), that.getHeight()) && Objects.equals(getType(), that.getType()) && Objects.equals(getPurpose(), that.getPurpose());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHref(), getAlt(), getAspectRatio(), getWidth(), getHeight(), getType(), getPurpose());
    }
}
