package com.apptasticsoftware.rssreader.module.itunes;

import java.time.Duration;
import java.util.Optional;

public class ItunesItemDataImpl implements ItunesItemData {
    private String itunesDuration;
    private String itunesImage;
    private boolean itunesExplicit;
    private String itunesTitle;
    private String itunesSubtitle;
    private String itunesSummary;
    private String itunesKeywords;
    private Integer itunesEpisode;
    private Integer itunesSeason;
    private String itunesEpisodeType;
    private boolean itunesBlock;

    /**
     * Get the duration of an episode.
     * @return duration
     */
    @Override
    public Optional<String> getItunesDuration() {
        return Optional.ofNullable(itunesDuration);
    }

    /**
     * Set the duration of an episode.
     * @param itunesDuration duration
     */
    @Override
    public void setItunesDuration(String itunesDuration) {
        this.itunesDuration = itunesDuration;
    }

    /**
     * Get the duration
     * @return duration
     */
    @Override
    @SuppressWarnings("java:S108")
    public Optional<Duration> getItunesDurationAsDuration() {
        if (itunesDuration != null && !itunesDuration.isBlank()) {
            try {
                String[] parts = itunesDuration.split(":");
                if (parts.length == 1) {
                    return Optional.of(Duration.ofSeconds(Long.parseLong(parts[0])));
                } else if (parts.length == 2) {
                    return Optional.of(Duration.ofMinutes(Long.parseLong(parts[0]))
                            .plusSeconds(Long.parseLong(parts[1])));
                } else if (parts.length == 3) {
                    return Optional.of(Duration.ofHours(Long.parseLong(parts[0]))
                            .plusMinutes(Long.parseLong(parts[1]))
                            .plusSeconds(Long.parseLong(parts[2])));
                }
            } catch (NumberFormatException ignored) { }
        }

        return Optional.empty();
    }

    /**
     * Get the episode artwork.
     * @return image
     */
    @Override
    public Optional<String> getItunesImage() {
        return Optional.ofNullable(itunesImage);
    }

    /**
     * Set the episode artwork.
     * @param itunesImage image
     */
    @Override
    public void setItunesImage(String itunesImage) {
        this.itunesImage = itunesImage;
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
    @Override
    public boolean isItunesExplicit() {
        return itunesExplicit;
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
    @Override
    public void setItunesExplicit(boolean itunesExplicit) {
        this.itunesExplicit = itunesExplicit;
    }

    /**
     * Get the episode title specific for Apple Podcasts.
     * @return title
     */
    @Override
    public Optional<String> getItunesTitle() {
        return Optional.ofNullable(itunesTitle);
    }

    /**
     * Set the episode title specific for Apple Podcasts.
     * @param itunesTitle title
     */
    @Override
    public void setItunesTitle(String itunesTitle) {
        this.itunesTitle = itunesTitle;
    }

    /**
     * Get the episode subtitle specific for Apple Podcasts.
     * @return subtitle
     */
    @Override
    public Optional<String> getItunesSubtitle() {
        return Optional.ofNullable(itunesSubtitle);
    }

    /**
     * Set the episode subtitle specific for Apple Podcasts.
     * @param itunesSubtitle subtitle
     */
    @Override
    public void setItunesSubtitle(String itunesSubtitle) {
        this.itunesSubtitle = itunesSubtitle;
    }

    /**
     * Get the episode summary.
     * @return summary
     */
    @Override
    public Optional<String> getItunesSummary() {
        return Optional.ofNullable(itunesSummary);
    }

    /**
     * Get the episode summary.
     * @param itunesSummary summary
     */
    @Override
    public void setItunesSummary(String itunesSummary) {
        this.itunesSummary = itunesSummary;
    }

    /**
     * Get the keywords.
     * @return keywords
     */
    @Override
    public Optional<String> getItunesKeywords() {
        return Optional.ofNullable(itunesKeywords);
    }

    /**
     * Set the keywords.
     * @param itunesKeywords keywords
     */
    @Override
    public void setItunesKeywords(String itunesKeywords) {
        this.itunesKeywords = itunesKeywords;
    }

    /**
     * Get the episode number.
     * @return episode
     */
    @Override
    public Optional<Integer> getItunesEpisode() {
        return Optional.ofNullable(itunesEpisode);
    }

    /**
     * Set the episode number.
     * @param itunesEpisode episode
     */
    @Override
    public void setItunesEpisode(Integer itunesEpisode) {
        this.itunesEpisode = itunesEpisode;
    }

    /**
     * Get the episode season number.
     * @return season
     */
    @Override
    public Optional<Integer> getItunesSeason() {
        return Optional.ofNullable(itunesSeason);
    }

    /**
     * Set the episode season number.
     * @param itunesSeason season
     */
    @Override
    public void setItunesSeason(Integer itunesSeason) {
        this.itunesSeason = itunesSeason;
    }

    /**
     * Get the episode type.
     * @return type
     */
    public Optional<String> getItunesEpisodeType() {
        return Optional.ofNullable(itunesEpisodeType);
    }

    /**
     * Set the episode type.
     * @param itunesEpisodeType type
     */
    @Override
    public void setItunesEpisodeType(String itunesEpisodeType) {
        this.itunesEpisodeType = itunesEpisodeType;
    }

    /**
     * Get the episode show or hide status.
     * If you want an episode removed from the Apple directory, use this tag.
     * @return block
     */
    @Override
    public boolean isItunesBlock() {
        return itunesBlock;
    }

    /**
     * Set the episode show or hide status.
     * If you want an episode removed from the Apple directory, use this tag.
     * @param itunesBlock block
     */
    @Override
    public void setItunesBlock(boolean itunesBlock) {
        this.itunesBlock = itunesBlock;
    }
}
