package com.apptasticsoftware.rssreader.module.atom;

import java.util.Optional;

/**
 * Represents an Atom link element, patterned after HTML's link element.
 * Contains one required attribute (href) and five optional attributes.
 */
public class AtomLink {
    private String href;
    private String rel;
    private String type;
    private String hrefLang;
    private String title;
    private Long length;

    /**
     * Gets the URI of the referenced resource.
     *
     * @return the href URI
     */
    public String getHref() {
        return href;
    }

    /**
     * Sets the URI of the referenced resource.
     *
     * @param href the href URI
     */
    public void setHref(String href) {
        this.href = href;
    }

    /**
     * Gets the link relationship type (e.g., alternate, enclosure, related, self, via).
     *
     * @return an Optional containing the rel attribute, or empty if not set
     */
    public Optional<String> getRel() {
        return Optional.ofNullable(rel);
    }

    /**
     * Sets the link relationship type.
     *
     * @param rel the link relationship type
     */
    public void setRel(String rel) {
        this.rel = rel;
    }

    /**
     * Gets the media type of the resource.
     *
     * @return an Optional containing the media type, or empty if not set
     */
    public Optional<String> getType() {
        return Optional.ofNullable(type);
    }

    /**
     * Sets the media type of the resource.
     *
     * @param type the media type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the language of the referenced resource.
     *
     * @return an Optional containing the language code, or empty if not set
     */
    public Optional<String> getHrefLang() {
        return Optional.ofNullable(hrefLang);
    }

    /**
     * Sets the language of the referenced resource.
     *
     * @param hrefLang the language code
     */
    public void setHrefLang(String hrefLang) {
        this.hrefLang = hrefLang;
    }

    /**
     * Gets human-readable information about the link, typically for display purposes.
     *
     * @return an Optional containing the title, or empty if not set
     */
    public Optional<String> getTitle() {
        return Optional.ofNullable(title);
    }

    /**
     * Sets human-readable information about the link.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the length of the resource, in bytes.
     *
     * @return an Optional containing the length in bytes, or empty if not set
     */
    public Optional<Long> getLength() {
        return Optional.ofNullable(length);
    }

    /**
     * Sets the length of the resource, in bytes.
     *
     * @param length the length in bytes
     */
    public void setLength(Long length) {
        this.length = length;
    }

}
