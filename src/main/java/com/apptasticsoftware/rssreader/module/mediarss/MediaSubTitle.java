package com.apptasticsoftware.rssreader.module.mediarss;

import java.util.Objects;

public class MediaSubTitle {
    private String type;
    private String lang;
    private String href;

    /**
     * Type of subtitle.
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Type of subtitle.
     * @param type type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Language of the subtitle.
     * @return language
     */
    public String getLang() {
        return lang;
    }

    /**
     * Language of the subtitle.
     * @param lang language
     */
    public void setLang(String lang) {
        this.lang = lang;
    }

    /**
     * Href of the subtitle.
     * @return href
     */
    public String getHref() {
        return href;
    }

    /**
     * Href of the subtitle.
     * @param href href
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
