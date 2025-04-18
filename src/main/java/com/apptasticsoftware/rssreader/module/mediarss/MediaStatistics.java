package com.apptasticsoftware.rssreader.module.mediarss;

/**
 * Specifies various statistics about a media object like the view count and the favorite count.
 */
public class MediaStatistics {
    private Integer views;
    private Integer favorites;

    /**
     * Number of views.
     * @return views
     */
    public Integer getViews() {
        return views;
    }

    /**
     * Number of views.
     * @param views views
     */
    public void setViews(Integer views) {
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
