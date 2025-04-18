package com.apptasticsoftware.rssreader.module.mediarss;

import java.util.LinkedHashMap;
import java.util.Map;

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
        System.out.println("addParamName: " + name + ", params: " + params);
        params.put(name, "");
        System.out.println("size: " + params.size() + ", params: " + params.hashCode());
    }

    public void addParamValue(String value) {
        System.out.println("addParamValue: " + value);
        System.out.println("size: " + params.size() + ", params: " + params.hashCode());
        System.out.println(params.lastEntry());
        params.put(params.lastEntry().getKey(), value);
    }

}
