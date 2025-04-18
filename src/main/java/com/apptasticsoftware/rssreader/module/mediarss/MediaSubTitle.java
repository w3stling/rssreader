package com.apptasticsoftware.rssreader.module.mediarss;

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
}
