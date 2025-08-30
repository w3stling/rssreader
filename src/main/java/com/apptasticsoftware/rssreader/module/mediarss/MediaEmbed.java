package com.apptasticsoftware.rssreader.module.mediarss;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Sometimes player-specific embed code is needed for a player to play any video.
 * <media:embed> allows inclusion of such information in the form of key-value pairs.
 */
public class MediaEmbed {
    private String url;
    private Integer width;
    private Integer height;
    private final LinkedHashMap<String, String> params = new LinkedHashMap<>();

    /**
     * The url of the embed code.
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * The url of the embed code.
     * @param url url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * The width of the embed code.
     * @return width
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * The width of the embed code.
     * @param width width
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * The height of the embed code.
     * @return height
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * The height of the embed code.
     * @param height height
     */
    public void setHeight(Integer height) {
        this.height = height;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void addParamName(String name) {
        params.put(name, "");
    }

    public void addParamValue(String value) {
        params.put(params.lastEntry().getKey(), value);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MediaEmbed that = (MediaEmbed) o;
        return Objects.equals(getUrl(), that.getUrl()) && Objects.equals(getWidth(), that.getWidth()) && Objects.equals(getHeight(), that.getHeight()) && Objects.equals(getParams(), that.getParams());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUrl(), getWidth(), getHeight(), getParams());
    }
}
