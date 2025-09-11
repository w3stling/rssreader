package com.apptasticsoftware.rssreader.module.mediarss;

import java.util.Objects;
import java.util.Optional;

/**
 * Allows the media object to be accessed through a web browser media player console.
 * This element is required only if a direct media url attribute is not specified in
 * the media:content element.
 *
 * Example:
 * {@code
 * <!-- Video player with specified dimensions -->
 * <item>
 *     <title>Funny Cats Compilation</title>
 *     <media:player
 *         url="http://www.example.com/player?id=1234"
 *         height="200"
 *         width="400" />
 * </item>
 *
 * <!-- Simple video player without dimensions -->
 * <media:player url="http://www.example.com/player?id=5678" />
 * }
 */
public class MediaPlayer {
    private String url;
    private Integer width;
    private Integer height;

    /**
     * The URL of the player console that plays the media.
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * The URL of the player console that plays the media.
     * @param url url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * The height of the browser window that the URL should be opened in.
     * @return height
     */
    public Optional<Integer> getHeight() {
        return Optional.ofNullable(height);
    }

    /**
     * The height of the browser window that the URL should be opened in.
     * @param height height
     */
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     * The width of the browser window that the URL should be opened in.
     * @return width
     */
    public Optional<Integer> getWidth() {
        return Optional.ofNullable(width);
    }

    /**
     * The width of the browser window that the URL should be opened in.
     * @param width width
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MediaPlayer that = (MediaPlayer) o;
        return Objects.equals(getUrl(), that.getUrl()) && Objects.equals(getWidth(), that.getWidth()) && Objects.equals(getHeight(), that.getHeight());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUrl(), getWidth(), getHeight());
    }
}
