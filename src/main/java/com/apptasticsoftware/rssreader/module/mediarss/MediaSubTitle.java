package com.apptasticsoftware.rssreader.module.mediarss;

import java.util.Objects;

/**
 * Optional element for subtitle/CC link. It contains type and language attributes.
 * Language is based on RFC 3066. There can be more than one such tag per media element,
 * for example one per language.
 *
 * Example:
 * {@code <media:subTitle type="application/smil" lang="en-us" href="http://www.example.org/subtitle.smil" />}
 */
public class MediaSubTitle {
    private String type;
    private String lang;
    private String href;

    /**
     * Gets the type of the subtitle.
     * Specifies the MIME type of the subtitle file (e.g., "application/smil").
     * @return the MIME type of the subtitle
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the subtitle.
     * @param type the MIME type of the subtitle file (e.g., "application/smil")
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the language of the subtitle.
     * The language codes are based on RFC 3066.
     * @return the language code of the subtitle (e.g., "en-us")
     */
    public String getLang() {
        return lang;
    }

    /**
     * Sets the language of the subtitle.
     * @param lang the language code based on RFC 3066 (e.g., "en-us")
     */
    public void setLang(String lang) {
        this.lang = lang;
    }

    /**
     * Gets the URL of the subtitle file.
     * @return the URL pointing to the subtitle file
     */
    public String getHref() {
        return href;
    }

    /**
     * Sets the URL of the subtitle file.
     * @param href the URL pointing to the subtitle file
     */
    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MediaSubTitle that = (MediaSubTitle) o;
        return Objects.equals(getType(), that.getType()) && Objects.equals(getLang(), that.getLang()) && Objects.equals(getHref(), that.getHref());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getLang(), getHref());
    }
}
