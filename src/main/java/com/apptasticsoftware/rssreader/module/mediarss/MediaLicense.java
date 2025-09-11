package com.apptasticsoftware.rssreader.module.mediarss;

import java.util.Objects;

/**
 * Optional link to specify the machine-readable license associated with the content.
 *
 * Example:
 * {@code <media:license type="text/html" href="http://creativecommons.org/licenses/by/3.0/us/">
 *     Creative Commons Attribution 3.0 United States License
 * </media:license>}
 */
public class MediaLicense {
    private String license;
    private String type;
    private String href;

    /**
     * Gets the license text that appears between the media:license tags.
     * @return the license text (e.g. "Creative Commons Attribution 3.0 United States License")
     */
    public String getLicense() {
        return license;
    }

    /**
     * Sets the license text that appears between the media:license tags.
     * @param license the license text (e.g. "Creative Commons Attribution 3.0 United States License")
     */
    public void setLicense(String license) {
        this.license = license;
    }

    /**
     * Gets the MIME type of the license content.
     * @return the MIME type (e.g. "text/html")
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the MIME type of the license content.
     * @param type the MIME type (e.g. "text/html")
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the URL pointing to the full license text.
     * @return the URL (e.g. "http://creativecommons.org/licenses/by/3.0/us/")
     */
    public String getHref() {
        return href;
    }

    /**
     * Sets the URL pointing to the full license text.
     * @param href the URL (e.g. "http://creativecommons.org/licenses/by/3.0/us/")
     */
    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MediaLicense that = (MediaLicense) o;
        return Objects.equals(getLicense(), that.getLicense()) &&
               Objects.equals(getType(), that.getType()) &&
               Objects.equals(getHref(), that.getHref());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLicense(), getType(), getHref());
    }
}
