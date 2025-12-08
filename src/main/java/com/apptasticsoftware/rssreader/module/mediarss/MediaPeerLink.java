package com.apptasticsoftware.rssreader.module.mediarss;

import java.util.Objects;

/**
 * Optional element for P2P link of a media object. This element can be used alongside or instead of
 * direct media URLs to provide peer-to-peer download options. For a valid Media RSS item,
 * at least one of media:content, media:player, or media:peerLink is required.
 *
 * Example:
 * {@code
 * <item>
 *     <title>Sample Video</title>
 *     <media:peerLink
 *         type="application/x-bittorrent"
 *         href="http://www.example.org/sampleFile.torrent" />
 * </item>
 * }
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
