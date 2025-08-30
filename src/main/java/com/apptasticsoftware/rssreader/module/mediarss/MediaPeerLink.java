package com.apptasticsoftware.rssreader.module.mediarss;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MediaPeerLink that = (MediaPeerLink) o;
        return Objects.equals(getType(), that.getType()) && Objects.equals(getHref(), that.getHref());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getHref());
    }
}
