package com.apptasticsoftware.rssreader.module.psc;

import java.time.Duration;
import java.util.Objects;
import java.util.Optional;

import static com.apptasticsoftware.rssreader.util.Util.toDuration;

/**
 * Represents a chapter in a podcast's chapter list.
 * <p>
 * A chapter marks a segment or section within a podcast episode. Each chapter is defined by
 * a start time, title, and optional links to external resources.
 * </p>
 *
 * @see <a href="https://podcastindex.org/namespace/">Podcast Index Specification</a>
 */
public class PscChapter {
    private String start;
    private String title;
    private String href;
    private String image;

    /**
     * Gets the start time of this chapter.
     * <p>
     * The start time represents the position in the episode where this chapter begins,
     * typically in a timestamp format (e.g., "00:00:00" for hours:minutes:seconds).
     * </p>
     *
     * @return the start time of this chapter
     */
    public String getStart() {
        return start;
    }

    public Duration getStartAsDuration() {
        return toDuration(start);
    }

    /**
     * Sets the start time of this chapter.
     *
     * @param start the start time in timestamp format (e.g., "00:00:00")
     */
    public void setStart(String start) {
        this.start = start;
    }

    /**
     * Gets the title of this chapter.
     * <p>
     * The title describes the content or topic of this chapter section.
     * </p>
     *
     * @return the title of this chapter
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of this chapter.
     *
     * @param title the descriptive title of this chapter section
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the external resource link (href) for this chapter.
     * <p>
     * The href is an optional URL or URI reference to an external resource associated
     * with this chapter, such as a blog post, image, or related content.
     * </p>
     *
     * @return an {@code Optional} containing the href if present, otherwise empty
     */
    public Optional<String> getHref() {
        return Optional.ofNullable(href);
    }

    /**
     * Sets the external resource link (href) for this chapter.
     *
     * @param href the URL or URI reference to an external resource (optional)
     */
    public void setHref(String href) {
        this.href = href;
    }

    /**
     * Gets the image link for this chapter.
     * <p>
     * The image is an optional URL or URI reference to an image associated
     * with this chapter.
     * </p>
     *
     * @return an {@code Optional} containing the image link if present, otherwise empty
     */
    public Optional<String> getImage() {
        return Optional.ofNullable(image);
    }

    /**
     * Sets the image link for this chapter.
     *
     * @param image the URL or URI reference to an image (optional)
     */
    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PscChapter that = (PscChapter) o;
        return Objects.equals(getStart(), that.getStart()) && Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getHref(), that.getHref()) && Objects.equals(getImage(), that.getImage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStart(), getTitle(), getHref(), getImage());
    }
}
