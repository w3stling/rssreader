package com.apptasticsoftware.rssreader.module.mediarss;

/**
 * Specifies various statistics about a media object like the view count and the favorite count.
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
}
