package com.apptasticsoftware.rssreader.module.mediarss;

/**
 * P2P link of a media object.
 */
public class MediaPeerLink {
    private String type;
    private String href;

    /**
     * Type of P2P link.
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Type of P2P link.
     * @param type type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Href of the P2P link.
     * @return href
     */
    public String getHref() {
        return href;
    }

    /**
     * Href of the P2P link.
     * @param href href
     */
    public void setHref(String href) {
        this.href = href;
    }
}
