package com.apptasticsoftware.rssreader.module.podcast;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents a podcast transcript or closed captions file.
 * Multiple transcript instances can be present for multiple transcript formats.
 * This class handles the attributes of a podcast:transcript tag which provides links
 * to transcript or closed captions files in various formats.
 */
public class PodcastTranscript {
    private String url;
    private String type;
    private String language;
    private String rel;

    /**
     * Gets the URL of the podcast transcript.
     * @return the URL of the transcript file
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the URL of the podcast transcript.
     * @param url the required URL of the transcript file
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Gets the MIME type of the transcript file.
     * Common MIME types include text/plain, text/html, text/vtt, application/json, application/x-subrip.
     * @return the MIME type of the transcript file
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the MIME type of the transcript file.
     * @param type the required MIME type of the transcript file (e.g., text/plain, text/html, text/vtt)
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the language of the transcript file.
     * If no language is specified, the transcript is assumed to be in the same language
     * as specified by the RSS &lt;language&gt; element.
     * @return an Optional containing the language code, or empty if not specified
     */
    public Optional<String> getLanguage() {
        return Optional.ofNullable(language);
    }

    /**
     * Sets the language of the transcript file.
     * @param language the optional language code of the transcript
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Gets the relationship type of the transcript.
     * When rel="captions" is present, the linked file is considered to be a closed captions file,
     * regardless of the MIME type. In this case, time codes are assumed to be present in the file.
     * @return an Optional containing the relationship type, or empty if not specified
     */
    public Optional<String> getRel() {
        return Optional.ofNullable(rel);
    }

    /**
     * Sets the relationship type of the transcript.
     * @param rel the optional relationship type (e.g., "captions" for closed captions files)
     */
    public void setRel(String rel) {
        this.rel = rel;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PodcastTranscript that = (PodcastTranscript) o;
        return Objects.equals(getUrl(), that.getUrl()) && Objects.equals(getType(), that.getType()) && Objects.equals(getLanguage(), that.getLanguage()) && Objects.equals(getRel(), that.getRel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUrl(), getType(), getLanguage(), getRel());
    }
}
