package com.apptasticsoftware.rssreader.module.mediarss;

import java.util.Optional;

/**
 * Allows the inclusion of a text transcript, closed captioning or lyrics of the media content.
 */
public class MediaText {
    private String text;
    private String type;
    private String lang;
    private String start;
    private String end;

    /**
     * The text transcript.
     * @return text
     */
    public String getText() {
        return text;
    }

    /**
     * The text transcript.
     * @param text text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Specifies the type of text embedded. Possible values are either "plain" or "html".
     * @return type
     */
    public Optional<String> getType() {
        return Optional.ofNullable(type);
    }

    /**
     * Specifies the type of text embedded. Possible values are either "plain" or "html".
     * @param type type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * The primary language encapsulated in the media object. Language codes possible are detailed in RFC 3066.
     * @return language
     */
    public Optional<String> getLang() {
        return Optional.ofNullable(lang);
    }

    /**
     * The primary language encapsulated in the media object. Language codes possible are detailed in RFC 3066.
     * @param lang language
     */
    public void setLang(String lang) {
        this.lang = lang;
    }

    /**
     * Specifies the start time offset that the text starts being relevant to the media object.
     * @return start time
     */
    public Optional<String> getStart() {
        return Optional.ofNullable(start);
    }

    /**
     * Specifies the start time offset that the text starts being relevant to the media object.
     * @param start start time
     */
    public void setStart(String start) {
        this.start = start;
    }

    /**
     * Specifies the end time that the text is relevant. If this attribute is not provided, and a start time is used,
     * it is expected that the end time is either the end of the clip or the start of the next <media:text> element.
     * @return end time
     */
    public Optional<String> getEnd() {
        return Optional.ofNullable(end);
    }

    /**
     * Specifies the end time that the text is relevant. If this attribute is not provided, and a start time is used,
     * it is expected that the end time is either the end of the clip or the start of the next <media:text> element.
     * @param end end time
     */
    public void setEnd(String end) {
        this.end = end;
    }
}
