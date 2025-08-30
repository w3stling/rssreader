package com.apptasticsoftware.rssreader.module.mediarss;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MediaLicense that = (MediaLicense) o;
        return Objects.equals(getLicense(), that.getLicense()) && Objects.equals(getType(), that.getType()) && Objects.equals(getHref(), that.getHref());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLicense(), getType(), getHref());
    }
}
