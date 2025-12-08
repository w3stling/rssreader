package com.apptasticsoftware.rssreader.module.itunes;

import java.util.List;
import java.util.Optional;

public interface ItunesChannelData {

    ItunesChannelData getItunesChannelData();

    /**
     * Get the artwork for the show.
     * Specify your show artwork by providing a URL linking to it.
     * @return image
     */
    default String getItunesImage() {
        return getItunesChannelData().getItunesImage();
    }

    /**
     * Set the artwork for the show.
     * Specify your show artwork by providing a URL linking to it.
     * @param image image
     */
    default void setItunesImage(String image) {
        getItunesChannelData().setItunesImage(image);
    }

    /**
     * Get the show category information. For a complete list of categories and subcategories
     * @return list of categories
     */
    default List<String> getItunesCategories() {
        return getItunesChannelData().getItunesCategories();
    }

    /**
     * Add the show category information. For a complete list of categories and subcategories
     * @param itunesCategory category
     */
    default void addItunesCategory(String itunesCategory) {
        getItunesChannelData().addItunesCategory(itunesCategory);
    }

    /**
     * Get the podcast parental advisory information.
     * The explicit value can be one of the following:
     * <p>
     * True. If you specify true, indicating the presence of explicit content,
     * Apple Podcasts displays an Explicit parental advisory graphic for your podcast.
     * Podcasts containing explicit material aren’t available in some Apple Podcasts territories.
     * <p>
     * False. If you specify false, indicating that your podcast doesn’t contain
     * explicit language or adult content, Apple Podcasts displays a Clean parental
     * advisory graphic for your podcast.
     * @return explicit
     */
    default Boolean getItunesExplicit() {
        return getItunesChannelData().getItunesExplicit();
    }

    /**
     * Get the podcast parental advisory information.
     * The explicit value can be one of the following:
     * <p>
     * True. If you specify true, indicating the presence of explicit content,
     * Apple Podcasts displays an Explicit parental advisory graphic for your podcast.
     * Podcasts containing explicit material aren’t available in some Apple Podcasts territories.
     * <p>
     * False. If you specify false, indicating that your podcast doesn’t contain
     * explicit language or adult content, Apple Podcasts displays a Clean parental
     * advisory graphic for your podcast.
     * @param itunesExplicit explicit
     */
    default void setItunesExplicit(Boolean itunesExplicit) {
        getItunesChannelData().setItunesExplicit(itunesExplicit);
    }

    /**
     * Get the group responsible for creating the show.
     * @return author
     */
    default Optional<String> getItunesAuthor() {
        return getItunesChannelData().getItunesAuthor();
    }

    /**
     * Set the group responsible for creating the show.
     * @param itunesAuthor author
     */
    default void setItunesAuthor(String itunesAuthor) {
        getItunesChannelData().setItunesAuthor(itunesAuthor);
    }

    /**
     * Set the podcast owner contact information.
     * @param itunesOwner owner
     */
    default void setItunesOwner(ItunesOwner itunesOwner) {
        getItunesChannelData().setItunesOwner(itunesOwner);
    }

    /**
     * Get the podcast owner contact information.
     * @return owner
     */
    default Optional<ItunesOwner> getItunesOwner() {
        return getItunesChannelData().getItunesOwner();
    }

    /**
     * Get the title specific for Apple Podcasts.
     * @return title
     */
    default Optional<String> getItunesTitle() {
        return getItunesChannelData().getItunesTitle();
    }

    /**
     * Set the title specific for Apple Podcasts.
     * @param itunesTitle title
     */
    default void setItunesTitle(String itunesTitle) {
        getItunesChannelData().setItunesTitle(itunesTitle);
    }

    /**
     * Get the subtitle specific for Apple Podcasts.
     * @return subtitle
     */
    default Optional<String> getItunesSubtitle() {
        return getItunesChannelData().getItunesSubtitle();
    }

    /**
     * Set the subtitle specific for Apple Podcasts.
     * @param itunesSubtitle subtitle
     */
    default void setItunesSubtitle(String itunesSubtitle) {
        getItunesChannelData().setItunesSubtitle(itunesSubtitle);
    }

    /**
     * Get the summary.
     * @return summary
     */
    default String getItunesSummary() {
        return getItunesChannelData().getItunesSummary();
    }

    /**
     * Set the summary.
     * @param itunesSummary summary
     */
    default void setItunesSummary(String itunesSummary) {
        getItunesChannelData().setItunesSummary(itunesSummary);
    }

    /**
     * Get the type of show.
     * @return type
     */
    default Optional<String> getItunesType() {
        return getItunesChannelData().getItunesType();
    }

    /**
     * Set the type of show.
     * @param itunesType type
     */
    default void setItunesType(String itunesType) {
        getItunesChannelData().setItunesType(itunesType);
    }

    /**
     * Get the new podcast RSS Feed URL.
     * If you change the URL of your podcast feed, you should use this tag in your new feed.
     * @return new feed url
     */
    default Optional<String> getItunesNewFeedUrl() {
        return getItunesChannelData().getItunesNewFeedUrl();
    }

    /**
     * Set the new podcast RSS Feed URL.
     * If you change the URL of your podcast feed, you should use this tag in your new feed.
     * @param itunesNewFeedUrl new feed url
     */
    default void setItunesNewFeedUrl(String itunesNewFeedUrl) {
        getItunesChannelData().setItunesNewFeedUrl(itunesNewFeedUrl);
    }

    /**
     * Get the podcast show or hide status.
     * If you want your show removed from the Apple directory, use this tag.
     * @return block
     */
    default boolean isItunesBlock() {
        return getItunesChannelData().isItunesBlock();
    }

    /**
     * Set the podcast show or hide status.
     * If you want your show removed from the Apple directory, use this tag.
     * @param itunesBlock block
     */
    default void setItunesBlock(boolean itunesBlock) {
        getItunesChannelData().setItunesBlock(itunesBlock);
    }

    /**
     * Set the podcast update status.
     * If you will never publish another episode to your show, use this tag.
     * @return complete
     */
    default boolean isItunesComplete() {
        return getItunesChannelData().isItunesComplete();
    }

    /**
     * Get the podcast update status.
     * If you will never publish another episode to your show, use this tag.
     * @param itunesComplete complete
     */
    default void setItunesComplete(boolean itunesComplete) {
        getItunesChannelData().setItunesComplete(itunesComplete);
    }
}
