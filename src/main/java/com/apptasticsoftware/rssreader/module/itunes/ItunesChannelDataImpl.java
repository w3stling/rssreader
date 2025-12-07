package com.apptasticsoftware.rssreader.module.itunes;

import com.apptasticsoftware.rssreader.util.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ItunesChannelDataImpl implements ItunesChannelData {
    private String itunesImage;
    private List<String> itunesCategories;
    private boolean itunesExplicit;
    private String itunesAuthor;
    private ItunesOwner itunesOwner;
    private String itunesTitle;
    private String itunesSubtitle;
    private String itunesSummary;
    private String itunesType;
    private String itunesNewFeedUrl;
    private boolean itunesBlock;
    private boolean itunesComplete;

    @Override
    public ItunesChannelData getItunesChannelData() {
        return this;
    }

    /**
     * Get the artwork for the show.
     * Specify your show artwork by providing a URL linking to it.
     * @return image
     */
    @Override
    public String getItunesImage() {
        return itunesImage;
    }

    /**
     * Set the artwork for the show.
     * Specify your show artwork by providing a URL linking to it.
     * @param image image
     */
    @Override
    public void setItunesImage(String image) {
        this.itunesImage = image;
    }

    /**
     * Get the show category information. For a complete list of categories and subcategories
     * @return list of categories
     */
    @Override
    public List<String> getItunesCategories() {
        return Mapper.emptyListIfNull(itunesCategories);
    }

    /**
     * Add the show category information. For a complete list of categories and subcategories
     * @param itunesCategory category
     */
    @Override
    public void addItunesCategory(String itunesCategory) {
        if (itunesCategories == null) {
            itunesCategories = new ArrayList<>();
        }
        itunesCategories.add(itunesCategory);
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
    @Override
    public Boolean getItunesExplicit() {
        return itunesExplicit;
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
    @Override
    public void setItunesExplicit(Boolean itunesExplicit) {
        this.itunesExplicit = itunesExplicit;
    }

    /**
     * Get the group responsible for creating the show.
     * @return author
     */
    public Optional<String> getItunesAuthor() {
        return Optional.ofNullable(itunesAuthor);
    }

    /**
     * Set the group responsible for creating the show.
     * @param itunesAuthor author
     */
    @Override
    public void setItunesAuthor(String itunesAuthor) {
        this.itunesAuthor = itunesAuthor;
    }

    /**
     * Set the podcast owner contact information.
     * @param itunesOwner owner
     */
    @Override
    public void setItunesOwner(ItunesOwner itunesOwner) {
        this.itunesOwner = itunesOwner;
    }

    /**
     * Get the podcast owner contact information.
     * @return owner
     */
    @Override
    public Optional<ItunesOwner> getItunesOwner() {
        return Optional.ofNullable(itunesOwner);
    }

    /**
     * Get the title specific for Apple Podcasts.
     * @return title
     */
    @Override
    public Optional<String> getItunesTitle() {
        return Optional.ofNullable(itunesTitle);
    }

    /**
     * Set the title specific for Apple Podcasts.
     * @param itunesTitle title
     */
    @Override
    public void setItunesTitle(String itunesTitle) {
        this.itunesTitle = itunesTitle;
    }

    /**
     * Get the subtitle specific for Apple Podcasts.
     * @return subtitle
     */
    @Override
    public Optional<String> getItunesSubtitle() {
        return Optional.ofNullable(itunesSubtitle);
    }

    /**
     * Set the subtitle specific for Apple Podcasts.
     * @param itunesSubtitle subtitle
     */
    @Override
    public void setItunesSubtitle(String itunesSubtitle) {
        this.itunesSubtitle = itunesSubtitle;
    }

    /**
     * Get the summary.
     * @return summary
     */
    @Override
    public String getItunesSummary() {
        return itunesSummary;
    }

    /**
     * Set the summary.
     * @param itunesSummary summary
     */
    @Override
    public void setItunesSummary(String itunesSummary) {
        this.itunesSummary = itunesSummary;
    }

    /**
     * Get the type of show.
     * @return type
     */
    @Override
    public Optional<String> getItunesType() {
        return Optional.ofNullable(itunesType);
    }

    /**
     * Set the type of show.
     * @param itunesType type
     */
    @Override
    public void setItunesType(String itunesType) {
        this.itunesType = itunesType;
    }

    /**
     * Get the new podcast RSS Feed URL.
     * If you change the URL of your podcast feed, you should use this tag in your new feed.
     * @return new feed url
     */
    @Override
    public Optional<String> getItunesNewFeedUrl() {
        return Optional.ofNullable(itunesNewFeedUrl);
    }

    /**
     * Set the new podcast RSS Feed URL.
     * If you change the URL of your podcast feed, you should use this tag in your new feed.
     * @param itunesNewFeedUrl new feed url
     */
    @Override
    public void setItunesNewFeedUrl(String itunesNewFeedUrl) {
        this.itunesNewFeedUrl = itunesNewFeedUrl;
    }

    /**
     * Get the podcast show or hide status.
     * If you want your show removed from the Apple directory, use this tag.
     * @return block
     */
    @Override
    public boolean isItunesBlock() {
        return itunesBlock;
    }

    /**
     * Set the podcast show or hide status.
     * If you want your show removed from the Apple directory, use this tag.
     * @param itunesBlock block
     */
    @Override
    public void setItunesBlock(boolean itunesBlock) {
        this.itunesBlock = itunesBlock;
    }

    /**
     * Set the podcast update status.
     * If you will never publish another episode to your show, use this tag.
     * @return complete
     */
    @Override
    public boolean isItunesComplete() {
        return itunesComplete;
    }

    /**
     * Get the podcast update status.
     * If you will never publish another episode to your show, use this tag.
     * @param itunesComplete complete
     */
    @Override
    public void setItunesComplete(boolean itunesComplete) {
        this.itunesComplete = itunesComplete;
    }

}
