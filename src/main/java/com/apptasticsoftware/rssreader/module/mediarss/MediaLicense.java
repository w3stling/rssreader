package com.apptasticsoftware.rssreader.module.mediarss;

/**
 * Link to specify the machine-readable license associated with the content.
 */
public class MediaLicense {
    private String license;
    private String type;
    private String href;

    /**
     * The license associated with the content
     * @return license
     */
    public String getLicense() {
        return license;
    }

    /**
     * The license associated with the content
     * @param license license
     */
    public void setLicense(String license) {
        this.license = license;
    }

    /**
     * Type of the license.
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * Type of the license.
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Href of the license.
     * @return href
     */
    public String getHref() {
        return href;
    }

    /**
     * Href of the license.
     * @param href href
     */
    public void setHref(String href) {
        this.href = href;
    }
}
