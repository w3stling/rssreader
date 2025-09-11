package com.apptasticsoftware.rssreader.module.mediarss;

import java.util.Objects;

/**
 * Optional element to specify timing information about various locations captured
 * in the content of a media object.
 *
 * Example:
 * {@code
 * <media:location
 *     description="Times Square, New York"
 *     start="00:01:15.000"
 *     end="00:02:30.000" />
 * }
 *
 * Elements:
 * - description: Description of the place whose location is being specified
 * - start: Time at which the reference to a particular location starts in the media object
 * - end: Time at which the reference to a particular location ends in the media object
 */
public class MediaLocation {
    private String description;
    private String start;
    private String end;

    /**
     * Gets the description for the location.
     *
     * @return description of the location
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description for the location.
     *
     * @param description description of the location
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the start time when this location becomes relevant in the media.
     *
     * @return start time in format HH:MM:SS.mmm
     */
    public String getStart() {
        return start;
    }

    /**
     * Sets the start time when this location becomes relevant in the media.
     *
     * @param start time in format HH:MM:SS.mmm
     */
    public void setStart(String start) {
        this.start = start;
    }

    /**
     * Gets the end time when this location stops being relevant in the media.
     *
     * @return end time in format HH:MM:SS.mmm
     */
    public String getEnd() {
        return end;
    }

    /**
     * Sets the end time when this location stops being relevant in the media.
     *
     * @param end time in format HH:MM:SS.mmm
     */
    public void setEnd(String end) {
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MediaLocation that = (MediaLocation) o;
        return Objects.equals(getDescription(), that.getDescription()) &&
               Objects.equals(getStart(), that.getStart()) &&
               Objects.equals(getEnd(), that.getEnd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDescription(), getStart(), getEnd());
    }
}
