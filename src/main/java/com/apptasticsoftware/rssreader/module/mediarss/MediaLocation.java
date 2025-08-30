package com.apptasticsoftware.rssreader.module.mediarss;

import java.util.Objects;

/**
 * For specifying various locations conforming to geoRSS
 */
public class MediaLocation {
    private String description;
    private String start;
    private String end;

    /**
     * The description for the location.
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * The description for the location.
     * @param description description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * The start of the location.
     * @return start
     */
    public String getStart() {
        return start;
    }

    /**
     * The start of the location.
     * @param start start
     */
    public void setStart(String start) {
        this.start = start;
    }

    /**
     * The end of the location.
     * @return end
     */
    public String getEnd() {
        return end;
    }

    /**
     * The end of the location.
     * @param end end
     */
    public void setEnd(String end) {
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MediaLocation that = (MediaLocation) o;
        return Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getStart(), that.getStart()) && Objects.equals(getEnd(), that.getEnd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDescription(), getStart(), getEnd());
    }
}
