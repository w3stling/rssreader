package com.apptasticsoftware.rssreader.module.itunes;

import java.time.Duration;
import java.util.Optional;

public interface ItunesItemData {
    /**
     * Get the duration of an episode.
     * @return duration
     */
    Optional<String> getItunesDuration();

    /**
     * Set the duration of an episode.
     * @param itunesDuration duration
     */
    void setItunesDuration(String itunesDuration);

    /**
     * Get the duration
     * @return duration
     */
    @SuppressWarnings("java:S108")
    Optional<Duration> getItunesDurationAsDuration();

    /**
     * Get the episode artwork.
     * @return image
     */
    Optional<String> getItunesImage();

    /**
     * Set the episode artwork.
     * @param itunesImage image
     */
    void setItunesImage(String itunesImage);

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
    boolean isItunesExplicit();

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
    void setItunesExplicit(boolean itunesExplicit);

    /**
     * Get the episode title specific for Apple Podcasts.
     * @return title
     */
    Optional<String> getItunesTitle();

    /**
     * Set the episode title specific for Apple Podcasts.
     * @param itunesTitle title
     */
    void setItunesTitle(String itunesTitle);

    /**
     * Get the episode subtitle specific for Apple Podcasts.
     * @return subtitle
     */
    Optional<String> getItunesSubtitle();

    /**
     * Set the episode subtitle specific for Apple Podcasts.
     * @param itunesSubtitle subtitle
     */
    void setItunesSubtitle(String itunesSubtitle);

    /**
     * Get the episode summary.
     * @return summary
     */
    Optional<String> getItunesSummary();

    /**
     * Get the episode summary.
     * @param itunesSummary summary
     */
    void setItunesSummary(String itunesSummary);

    /**
     * Get the keywords.
     * @return keywords
     */
    Optional<String> getItunesKeywords();

    /**
     * Set the keywords.
     * @param itunesKeywords keywords
     */
    void setItunesKeywords(String itunesKeywords);

    /**
     * Get the episode number.
     * @return episode
     */
    Optional<Integer> getItunesEpisode();

    /**
     * Set the episode number.
     * @param itunesEpisode episode
     */
    void setItunesEpisode(Integer itunesEpisode);

    /**
     * Get the episode season number.
     * @return season
     */
    Optional<Integer> getItunesSeason();

    /**
     * Set the episode season number.
     * @param itunesSeason season
     */
    void setItunesSeason(Integer itunesSeason);

    /**
     * Get the episode type.
     * @return type
     */
    Optional<String> getItunesEpisodeType();

    /**
     * Set the episode type.
     * @param itunesEpisodeType type
     */
    void setItunesEpisodeType(String itunesEpisodeType);

    /**
     * Get the episode show or hide status.
     * If you want an episode removed from the Apple directory, use this tag.
     * @return block
     */
    boolean isItunesBlock();

    /**
     * Set the episode show or hide status.
     * If you want an episode removed from the Apple directory, use this tag.
     * @param itunesBlock block
     */
    void setItunesBlock(boolean itunesBlock);
}
