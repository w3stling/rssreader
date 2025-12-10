package com.apptasticsoftware.rssreader.module.podcast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Represents an alternate enclosure that provides different versions of, or companion media to the main enclosure file.
 * This can include audio-only versions of video podcasts, different bitrate versions for bandwidth management,
 * alternative codecs for different platforms, alternate URI schemes, download types (like IPFS or WebTorrent),
 * commentary tracks, or supporting source clips.
 *
 * &lt;p&gt;The alternate enclosure allows podcast apps to switch between different versions of the same content,
 * such as switching between audio/video formats or adapting to bandwidth constraints.&lt;/p&gt;
 *
 * @see PodcastSource
 * @see PodcastIntegrity
 */
public class PodcastAlternateEnclosure {
    private String type;
    private Long length;
    private Double bitrate;
    private Integer height;
    private String lang;
    private String title;
    private String rel;
    private String codecs;
    private boolean defaults = false;
    private final List<PodcastSource> sources = new ArrayList<>();
    private PodcastIntegrity integrity;

    /**
     * Gets the MIME type of the media asset.
     * @return the MIME type (required)
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the MIME type of the media asset.
     * @param type the MIME type to set (required)
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the length of the file in bytes.
     * @return the file length in bytes (recommended)
     */
    public Long getLength() {
        return length;
    }

    /**
     * Sets the length of the file in bytes.
     * @param length the file length to set in bytes (recommended)
     */
    public void setLength(Long length) {
        this.length = length;
    }

    /**
     * Gets the average encoding bitrate of the media asset in bits per second.
     * @return the bitrate in bits per second
     */
    public Double getBitrate() {
        return bitrate;
    }

    /**
     * Sets the average encoding bitrate of the media asset in bits per second.
     * @param bitrate the bitrate to set in bits per second
     */
    public void setBitrate(Double bitrate) {
        this.bitrate = bitrate;
    }

    /**
     * Gets the height of the media asset for video formats.
     * @return the height in pixels
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * Sets the height of the media asset for video formats.
     * @param height the height to set in pixels
     */
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     * Gets the IETF language tag (BCP 47) identifying the language of this media.
     * @return the language code
     */
    public String getLang() {
        return lang;
    }

    /**
     * Sets the IETF language tag (BCP 47) identifying the language of this media.
     * @param lang the language code to set
     */
    public void setLang(String lang) {
        this.lang = lang;
    }

    /**
     * Gets the human-readable name of the media asset (limited to 32 characters for UX).
     * @return the title of the media asset
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the human-readable name of the media asset (should be limited to 32 characters for UX).
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the relationship type used for grouping different media elements.
     * If not set or set to "default", the media will be grouped with the enclosure
     * and assumed to be an alternative to the enclosure's encoding/transport.
     * @return the relationship type (limited to 32 characters for UX)
     */
    public String getRel() {
        return rel;
    }

    /**
     * Sets the relationship type used for grouping different media elements.
     * @param rel the relationship type to set (should be limited to 32 characters for UX)
     */
    public void setRel(String rel) {
        this.rel = rel;
    }

    /**
     * Gets the RFC 6381 string specifying the codecs available in this media.
     * @return the codecs specification string
     */
    public String getCodecs() {
        return codecs;
    }

    /**
     * Sets the RFC 6381 string specifying the codecs available in this media.
     * @param codecs the codecs specification string to set
     */
    public void setCodecs(String codecs) {
        this.codecs = codecs;
    }

    /**
     * Checks if this media is the same as the file from the enclosure element.
     * When true, this should be the preferred media element.
     * @return true if this is the default media, false otherwise
     */
    public boolean isDefaults() {
        return defaults;
    }

    /**
     * Sets whether this media is the same as the file from the enclosure element.
     * @param defaults true to mark this as the default media, false otherwise
     */
    public void setDefaults(boolean defaults) {
        this.defaults = defaults;
    }

    /**
     * Gets the list of alternative sources for this media asset.
     * Sources can include different URIs and transport methods (HTTP, IPFS, BitTorrent, etc.).
     * @return the list of media sources
     */
    public List<PodcastSource> getSources() {
        return sources;
    }

    /**
     * Adds a new source for this media asset.
     * @param source the source to add
     */
    public void addSource(PodcastSource source) {
        this.sources.add(source);
    }

    /**
     * Gets the integrity information for this media asset, if available.
     * @return an Optional containing the integrity information, or empty if not available
     */
    public Optional<PodcastIntegrity> getIntegrity() {
        return Optional.ofNullable(integrity);
    }

    /**
     * Sets the integrity information for this media asset.
     * @param integrity the integrity information to set
     */
    public void setIntegrity(PodcastIntegrity integrity) {
        this.integrity = integrity;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PodcastAlternateEnclosure that = (PodcastAlternateEnclosure) o;
        return isDefaults() == that.isDefaults() && Objects.equals(getType(), that.getType()) && Objects.equals(getLength(), that.getLength()) && Objects.equals(getBitrate(), that.getBitrate()) && Objects.equals(getHeight(), that.getHeight()) && Objects.equals(getLang(), that.getLang()) && Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getRel(), that.getRel()) && Objects.equals(getCodecs(), that.getCodecs()) && Objects.equals(getSources(), that.getSources()) && Objects.equals(getIntegrity(), that.getIntegrity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getLength(), getBitrate(), getHeight(), getLang(), getTitle(), getRel(), getCodecs(), isDefaults(), getSources(), getIntegrity());
    }
}
