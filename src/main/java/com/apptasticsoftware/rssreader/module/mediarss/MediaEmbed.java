package com.apptasticsoftware.rssreader.module.mediarss;

import com.apptasticsoftware.rssreader.util.Mapper;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Sometimes player-specific embed code is needed for a player to play any video.
 * <media:embed> allows inclusion of such information in the form of key-value pairs.
 * Example:
 * {@code
 * <media:embed url="http://d.yimg.com/static.video.yahoo.com/yep/YV_YEP.swf?ver=2.2.2"
 *            width="512"
 *            height="323">
 *     <media:param name="type">application/x-shockwave-flash</media:param>
 *     <media:param name="width">512</media:param>
 *     <media:param name="height">323</media:param>
 *     <media:param name="allowFullScreen">true</media:param>
 *     <media:param name="flashVars">
 *         id=7809705&amp;vid=2666306&amp;lang=en-us&amp;intl=us&amp;thumbUrl=http%3A//example.com/thumb.jpg
 *     </media:param>
 * </media:embed>}
 */
public class MediaEmbed {
    private String url;
    private Integer width;
    private Integer height;
    private LinkedHashMap<String, String> params;

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
        return Mapper.emptyMapIfNull(params);
    }

    public void addParamName(String name) {
        if (params == null) {
            params = new LinkedHashMap<>();
        }
        params.put(name, "");
    }

    public void addParamValue(String value) {
        if (params == null) {
            params = new LinkedHashMap<>();
            return;
        }

        if (params.isEmpty()) {
            return;
        }

        // Get the last key in insertion order
        String lastKey = null;
        for (String key : params.keySet()) {
            lastKey = key;
        }

        params.put(lastKey, value);
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
