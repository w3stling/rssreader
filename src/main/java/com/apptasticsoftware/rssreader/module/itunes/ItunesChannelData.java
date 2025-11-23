package com.apptasticsoftware.rssreader.module.itunes;

import java.util.List;
import java.util.Optional;

public interface ItunesChannelData {
    /**
     * Get the artwork for the show.
     * Specify your show artwork by providing a URL linking to it.
     * @return image
     */
    String getItunesImage();

    /**
     * Set the artwork for the show.
     * Specify your show artwork by providing a URL linking to it.
     * @param image image
     */
    void setItunesImage(String image);

    /**
     * Get the show category information. For a complete list of categories and subcategories
     * @return list of categories
     */
    List<String> getItunesCategories();

    /**
     * Add the show category information. For a complete list of categories and subcategories
     * @param itunesCategory category
     */
    void addItunesCategory(String itunesCategory);

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
    Boolean getItunesExplicit();

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
    void setItunesExplicit(Boolean itunesExplicit);

    /**
     * Get the group responsible for creating the show.
     * @return author
     */
    Optional<String> getItunesAuthor();

    /**
     * Set the group responsible for creating the show.
     * @param itunesAuthor author
     */
    void setItunesAuthor(String itunesAuthor);

    /**
     * Set the podcast owner contact information.
     * @param itunesOwner owner
     */
    void setItunesOwner(ItunesOwner itunesOwner);

    /**
     * Get the podcast owner contact information.
     * @return owner
     */
    Optional<ItunesOwner> getItunesOwner();

    /**
     * Get the title specific for Apple Podcasts.
     * @return title
     */
    Optional<String> getItunesTitle();

    /**
     * Set the title specific for Apple Podcasts.
     * @param itunesTitle title
     */
    void setItunesTitle(String itunesTitle);

    /**
     * Get the subtitle specific for Apple Podcasts.
     * @return subtitle
     */
    Optional<String> getItunesSubtitle();

    /**
     * Set the subtitle specific for Apple Podcasts.
     * @param itunesSubtitle subtitle
     */
    void setItunesSubtitle(String itunesSubtitle);

    /**
     * Get the summary.
     * @return summary
     */
    String getItunesSummary();

    /**
     * Set the summary.
     * @param itunesSummary summary
     */
    void setItunesSummary(String itunesSummary);

    /**
     * Get the type of show.
     * @return type
     */
    Optional<String> getItunesType();

    /**
     * Set the type of show.
     * @param itunesType type
     */
    void setItunesType(String itunesType);

    /**
     * Get the new podcast RSS Feed URL.
     * If you change the URL of your podcast feed, you should use this tag in your new feed.
     * @return new feed url
     */
    Optional<String> getItunesNewFeedUrl();

    /**
     * Set the new podcast RSS Feed URL.
     * If you change the URL of your podcast feed, you should use this tag in your new feed.
     * @param itunesNewFeedUrl new feed url
     */
    void setItunesNewFeedUrl(String itunesNewFeedUrl);

    /**
     * Get the podcast show or hide status.
     * If you want your show removed from the Apple directory, use this tag.
     * @return block
     */
    boolean isItunesBlock();

    /**
     * Set the podcast show or hide status.
     * If you want your show removed from the Apple directory, use this tag.
     * @param itunesBlock block
     */
    void setItunesBlock(boolean itunesBlock);

    /**
     * Set the podcast update status.
     * If you will never publish another episode to your show, use this tag.
     * @return complete
     */
    boolean isItunesComplete();

    /**
     * Get the podcast update status.
     * If you will never publish another episode to your show, use this tag.
     * @param itunesComplete complete
     */
    void setItunesComplete(boolean itunesComplete);
}
