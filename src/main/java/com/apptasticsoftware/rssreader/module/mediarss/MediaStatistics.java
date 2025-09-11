package com.apptasticsoftware.rssreader.module.mediarss;

import java.util.Objects;

/**
 * Specifies various statistics about a media object like the view count and the favorite count.
 * This element is part of the media:community element and provides engagement metrics for the media object.
 *
 * Example:
 * {@code <media:community>
 *     <media:statistics views="5" favorites="5" />
 * </media:community>}
 */
public class MediaStatistics {
    private Long views;
    private Integer favorites;

    /**
     * Number of views.
     * @return views
     */
    public Long getViews() {
        return views;
    }

    /**
     * Number of views.
     * @param views views
     */
    public void setViews(Long views) {
        this.views = views;
    }

    /**
     * Favorites count.
     * @return favorites
     */
    public Integer getFavorites() {
        return favorites;
    }

    /**
     * Favorites count.
     * @param favorites favorites
     */
    public void setFavorites(Integer favorites) {
        this.favorites = favorites;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MediaStatistics that = (MediaStatistics) o;
        return Objects.equals(getViews(), that.getViews()) && Objects.equals(getFavorites(), that.getFavorites());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getViews(), getFavorites());
    }
}
