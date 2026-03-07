package com.apptasticsoftware.rssreader.module.itunes;

import com.apptasticsoftware.rssreader.module.itunes.internal.ItunesItemDataProvider;
import java.time.Duration;
import java.util.Optional;

public interface ItunesItemData {
    /**
     * Get the duration of an episode.
     * @return duration
     */
    default Optional<String> getItunesDuration() {
        return ((ItunesItemDataProvider) this).itunesItemData().getItunesDuration();
    }

    /**
     * Set the duration of an episode.
     * @param itunesDuration duration
     */
    default void setItunesDuration(String itunesDuration) {
        ((ItunesItemDataProvider) this).itunesItemData().setItunesDuration(itunesDuration);
    }

    /**
     * Get the duration
     * @return duration
     */
    @SuppressWarnings("java:S108")
    default Optional<Duration> getItunesDurationAsDuration() {
        return ((ItunesItemDataProvider) this).itunesItemData().getItunesDurationAsDuration();
    }

    /**
     * Get the episode artwork.
     * @return image
     */
    default Optional<String> getItunesImage() {
        return ((ItunesItemDataProvider) this).itunesItemData().getItunesImage();
    }

    /**
     * Set the episode artwork.
     * @param itunesImage image
     */
    default void setItunesImage(String itunesImage) {
        ((ItunesItemDataProvider) this).itunesItemData().setItunesImage(itunesImage);
    }

    /**
     * Set the episode parental advisory information.
     * Where the explicit value can be one of the following:
     * <p>
     * true. If you specify true, indicating the presence of explicit content, Apple Podcasts displays an Explicit parental advisory graphic for your episode.
     * Episodes containing explicit material aren’t available in some Apple Podcasts territories.
     * <p>
     * false. If you specify false, indicating that the episode does not contain explicit language or adult content, Apple Podcasts displays a Clean parental advisory graphic for your episode.
     * @return explicit
     */
    default boolean isItunesExplicit() {
        return ((ItunesItemDataProvider) this).itunesItemData().isItunesExplicit();
    }

    /**
     * Set the episode parental advisory information.
     * Where the explicit value can be one of the following:
     * <p>
     * true. If you specify true, indicating the presence of explicit content, Apple Podcasts displays an Explicit parental advisory graphic for your episode.
     * Episodes containing explicit material aren’t available in some Apple Podcasts territories.
     * <p>
     * false. If you specify false, indicating that the episode does not contain explicit language or adult content, Apple Podcasts displays a Clean parental advisory graphic for your episode.
     * @param itunesExplicit explicit
     */
    default void setItunesExplicit(boolean itunesExplicit) {
        ((ItunesItemDataProvider) this).itunesItemData().setItunesExplicit(itunesExplicit);
    }

    /**
     * Get the episode title specific for Apple Podcasts.
     * @return title
     */
    default Optional<String> getItunesTitle() {
        return ((ItunesItemDataProvider) this).itunesItemData().getItunesTitle();
    }

    /**
     * Set the episode title specific for Apple Podcasts.
     * @param itunesTitle title
     */
    default void setItunesTitle(String itunesTitle) {
        ((ItunesItemDataProvider) this).itunesItemData().setItunesTitle(itunesTitle);
    }

    /**
     * Get the episode subtitle specific for Apple Podcasts.
     * @return subtitle
     */
    default Optional<String> getItunesSubtitle() {
        return ((ItunesItemDataProvider) this).itunesItemData().getItunesSubtitle();
    }

    /**
     * Set the episode subtitle specific for Apple Podcasts.
     * @param itunesSubtitle subtitle
     */
    default void setItunesSubtitle(String itunesSubtitle) {
        ((ItunesItemDataProvider) this).itunesItemData().setItunesSubtitle(itunesSubtitle);
    }

    /**
     * Get the episode summary.
     * @return summary
     */
    default Optional<String> getItunesSummary() {
        return ((ItunesItemDataProvider) this).itunesItemData().getItunesSummary();
    }

    /**
     * Get the episode summary.
     * @param itunesSummary summary
     */
    default void setItunesSummary(String itunesSummary) {
        ((ItunesItemDataProvider) this).itunesItemData().setItunesSummary(itunesSummary);
    }

    /**
     * Get the keywords.
     * @return keywords
     */
    default Optional<String> getItunesKeywords() {
        return ((ItunesItemDataProvider) this).itunesItemData().getItunesKeywords();
    }

    /**
     * Set the keywords.
     * @param itunesKeywords keywords
     */
    default void setItunesKeywords(String itunesKeywords) {
        ((ItunesItemDataProvider) this).itunesItemData().setItunesKeywords(itunesKeywords);
    }

    /**
     * Get the episode number.
     * @return episode
     */
    default Optional<Integer> getItunesEpisode() {
        return ((ItunesItemDataProvider) this).itunesItemData().getItunesEpisode();
    }

    /**
     * Set the episode number.
     * @param itunesEpisode episode
     */
    default void setItunesEpisode(Integer itunesEpisode) {
        ((ItunesItemDataProvider) this).itunesItemData().setItunesEpisode(itunesEpisode);
    }

    /**
     * Get the episode season number.
     * @return season
     */
    default Optional<Integer> getItunesSeason() {
        return ((ItunesItemDataProvider) this).itunesItemData().getItunesSeason();
    }

    /**
     * Set the episode season number.
     * @param itunesSeason season
     */
    default void setItunesSeason(Integer itunesSeason) {
        ((ItunesItemDataProvider) this).itunesItemData().setItunesSeason(itunesSeason);
    }

    /**
     * Get the episode type.
     * @return type
     */
    default Optional<String> getItunesEpisodeType() {
        return ((ItunesItemDataProvider) this).itunesItemData().getItunesEpisodeType();
    }

    /**
     * Set the episode type.
     * @param itunesEpisodeType type
     */
    default void setItunesEpisodeType(String itunesEpisodeType) {
        ((ItunesItemDataProvider) this).itunesItemData().setItunesEpisodeType(itunesEpisodeType);
    }

    /**
     * Get the episode show or hide status.
     * If you want an episode removed from the Apple directory, use this tag.
     * @return block
     */
    default boolean isItunesBlock() {
        return ((ItunesItemDataProvider) this).itunesItemData().isItunesBlock();
    }

    /**
     * Set the episode show or hide status.
     * If you want an episode removed from the Apple directory, use this tag.
     * @param itunesBlock block
     */
    default void setItunesBlock(boolean itunesBlock) {
        ((ItunesItemDataProvider) this).itunesItemData().setItunesBlock(itunesBlock);
    }
}
