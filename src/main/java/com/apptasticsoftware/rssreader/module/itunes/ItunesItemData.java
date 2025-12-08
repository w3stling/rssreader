package com.apptasticsoftware.rssreader.module.itunes;

import java.time.Duration;
import java.util.Optional;

public interface ItunesItemData {
    ItunesItemData getItunesItemData();

    /**
     * Get the duration of an episode.
     * @return duration
     */
    default Optional<String> getItunesDuration() {
        return getItunesItemData().getItunesDuration();
    }

    /**
     * Set the duration of an episode.
     * @param itunesDuration duration
     */
    default void setItunesDuration(String itunesDuration) {
        getItunesItemData().setItunesDuration(itunesDuration);
    }

    /**
     * Get the duration
     * @return duration
     */
    @SuppressWarnings("java:S108")
    default Optional<Duration> getItunesDurationAsDuration() {
        return getItunesItemData().getItunesDurationAsDuration();
    }

    /**
     * Get the episode artwork.
     * @return image
     */
    default Optional<String> getItunesImage() {
        return getItunesItemData().getItunesImage();
    }

    /**
     * Set the episode artwork.
     * @param itunesImage image
     */
    default void setItunesImage(String itunesImage) {
        getItunesItemData().setItunesImage(itunesImage);
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
        return getItunesItemData().isItunesExplicit();
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
        getItunesItemData().setItunesExplicit(itunesExplicit);
    }

    /**
     * Get the episode title specific for Apple Podcasts.
     * @return title
     */
    default Optional<String> getItunesTitle() {
        return getItunesItemData().getItunesTitle();
    }

    /**
     * Set the episode title specific for Apple Podcasts.
     * @param itunesTitle title
     */
    default void setItunesTitle(String itunesTitle) {
        getItunesItemData().setItunesTitle(itunesTitle);
    }

    /**
     * Get the episode subtitle specific for Apple Podcasts.
     * @return subtitle
     */
    default Optional<String> getItunesSubtitle() {
        return getItunesItemData().getItunesSubtitle();
    }

    /**
     * Set the episode subtitle specific for Apple Podcasts.
     * @param itunesSubtitle subtitle
     */
    default void setItunesSubtitle(String itunesSubtitle) {
        getItunesItemData().setItunesSubtitle(itunesSubtitle);
    }

    /**
     * Get the episode summary.
     * @return summary
     */
    default Optional<String> getItunesSummary() {
        return getItunesItemData().getItunesSummary();
    }

    /**
     * Get the episode summary.
     * @param itunesSummary summary
     */
    default void setItunesSummary(String itunesSummary) {
        getItunesItemData().setItunesSummary(itunesSummary);
    }

    /**
     * Get the keywords.
     * @return keywords
     */
    default Optional<String> getItunesKeywords() {
        return getItunesItemData().getItunesKeywords();
    }

    /**
     * Set the keywords.
     * @param itunesKeywords keywords
     */
    default void setItunesKeywords(String itunesKeywords) {
        getItunesItemData().setItunesKeywords(itunesKeywords);
    }

    /**
     * Get the episode number.
     * @return episode
     */
    default Optional<Integer> getItunesEpisode() {
        return getItunesItemData().getItunesEpisode();
    }

    /**
     * Set the episode number.
     * @param itunesEpisode episode
     */
    default void setItunesEpisode(Integer itunesEpisode) {
        getItunesItemData().setItunesEpisode(itunesEpisode);
    }

    /**
     * Get the episode season number.
     * @return season
     */
    default Optional<Integer> getItunesSeason() {
        return getItunesItemData().getItunesSeason();
    }

    /**
     * Set the episode season number.
     * @param itunesSeason season
     */
    default void setItunesSeason(Integer itunesSeason) {
        getItunesItemData().setItunesSeason(itunesSeason);
    }

    /**
     * Get the episode type.
     * @return type
     */
    default Optional<String> getItunesEpisodeType() {
        return getItunesItemData().getItunesEpisodeType();
    }

    /**
     * Set the episode type.
     * @param itunesEpisodeType type
     */
    default void setItunesEpisodeType(String itunesEpisodeType) {
        getItunesItemData().setItunesEpisodeType(itunesEpisodeType);
    }

    /**
     * Get the episode show or hide status.
     * If you want an episode removed from the Apple directory, use this tag.
     * @return block
     */
    default boolean isItunesBlock() {
        return getItunesItemData().isItunesBlock();
    }

    /**
     * Set the episode show or hide status.
     * If you want an episode removed from the Apple directory, use this tag.
     * @param itunesBlock block
     */
    default void setItunesBlock(boolean itunesBlock) {
        getItunesItemData().setItunesBlock(itunesBlock);
    }
}
