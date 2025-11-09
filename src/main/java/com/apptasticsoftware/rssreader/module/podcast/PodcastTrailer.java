package com.apptasticsoftware.rssreader.module.podcast;

import com.apptasticsoftware.rssreader.util.Default;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Optional;

/**
 * Represents a podcast trailer that defines the location of an audio or video file to be used as a trailer
 * for the entire podcast or a specific season. Multiple trailers can be present in a channel feed, with
 * the most recent one (by pubdate) being chosen as the default preview in podcast apps.
 *
 * This element is similar to an enclosure with additional pubdate and season attributes.
 */
public class PodcastTrailer {
    private String trailer;
    private String url;
    private String pubDate;
    private Long length;
    private String type;
    private Integer season;

    /**
     * Gets the title of the trailer.
     * This value should not exceed 128 characters as it may be truncated by aggregators.
     * @return the trailer title
     */
    public String getTrailer() {
        return trailer;
    }

    /**
     * Sets the title of the trailer.
     * @param trailer the trailer title (should not exceed 128 characters)
     */
    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    /**
     * Gets the URL that points to the audio or video file to be played.
     * This is a required attribute.
     * @return the URL of the trailer file
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the URL that points to the audio or video file to be played.
     * @param url the URL of the trailer file
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Gets the publication date of the trailer.
     * This is a required attribute in RFC2822 formatted date string.
     * @return the publication date as RFC2822 formatted string
     */
    public String getPubDate() {
        return pubDate;
    }

    /**
     * Gets the publication date of the trailer as a ZonedDateTime.
     * @return the publication date as ZonedDateTime
     */
    public ZonedDateTime getPubDateAsZonedDateTime() {
        return Default.getDateTimeParser().parse(pubDate);
    }

    /**
     * Sets the publication date of the trailer.
     * @param pubDate the publication date in RFC2822 format
     */
    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    /**
     * Gets the length of the file in bytes.
     * This is a recommended attribute.
     * @return the length of the file in bytes
     */
    public Long getLength() {
        return length;
    }

    /**
     * Sets the length of the file in bytes.
     * @param length the length of the file in bytes
     */
    public void setLength(Long length) {
        this.length = length;
    }

    /**
     * Gets the MIME type of the file.
     * This is a recommended attribute.
     * @return the MIME type of the file
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the MIME type of the file.
     * @param type the MIME type of the file
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the season number this trailer is associated with.
     * This is an optional attribute that specifies the particular season number this trailer is for.
     * The season number must match the format of the podcast:season tag.
     * @return an Optional containing the season number, or empty if not set
     */
    public Optional<Integer> getSeason() {
        return Optional.ofNullable(season);
    }

    /**
     * Sets the season number this trailer is associated with.
     * @param season the season number this trailer is for
     */
    public void setSeason(Integer season) {
        this.season = season;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PodcastTrailer that = (PodcastTrailer) o;
        return Objects.equals(getTrailer(), that.getTrailer()) && Objects.equals(getUrl(), that.getUrl()) && Objects.equals(getPubDate(), that.getPubDate()) && Objects.equals(getLength(), that.getLength()) && Objects.equals(getType(), that.getType()) && Objects.equals(getSeason(), that.getSeason());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTrailer(), getUrl(), getPubDate(), getLength(), getType(), getSeason());
    }
}
