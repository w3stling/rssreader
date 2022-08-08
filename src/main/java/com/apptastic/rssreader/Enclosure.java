package com.apptastic.rssreader;

import java.util.Optional;

/**
 * Class representing the Enclosure.
 */
public class Enclosure {
    private String url;
    private String type;
    private Long length;

    public Enclosure(String url, String type, Long length) {
        this.url = url;
        this.type = type;
        this.length = length;
    }

    /**
     * Get the url of enclosure.
     * @return url
     */
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Get the type of enclosure.
     * @return type
     */
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * Get the length of enclosure.
     * @return length
     */
    public Optional<Long> getLength() {
        return Optional.ofNullable(length);
    }

    public void setLength(Long length) {
        this.length = length;
    }
}
