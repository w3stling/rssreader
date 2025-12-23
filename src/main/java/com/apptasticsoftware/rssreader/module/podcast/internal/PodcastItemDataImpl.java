package com.apptasticsoftware.rssreader.module.podcast.internal;

import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.module.podcast.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.apptasticsoftware.rssreader.util.Mapper.emptyListIfNull;

/**
 * Implementation of {@link PodcastItemData} for managing podcast item metadata.
 * Stores and provides access to podcast-specific information such as episodes, chapters,
 * images, transcripts, and live item data.
 */
public class PodcastItemDataImpl implements PodcastItemData {
    private final DateTimeParser dateTimeParser;
    private PodcastSeason podcastSeason;
    private PodcastEpisode podcastEpisode;
    private PodcastChapters podcastChapters;
    private PodcastChat podcastChat;
    private List<PodcastImage> podcastImages;
    private List<PodcastSoundbite> podcastSoundbites;
    private List<PodcastTranscript> podcastTranscripts;
    private List<PodcastPerson> podcastPersons;
    private List<PodcastAlternateEnclosure> podcastAlternateEnclosures;
    private List<PodcastValue> podcastValues;
    private List<PodcastSocialInteract> podcastSocialInteracts;
    private List<PodcastContentLink> podcastContentLinks;
    private List<PodcastTxt> podcastTxts;
    private String podcastLiveItemStatus;
    private String podcastLiveItemStart;
    private String podcastLiveItemEnd;

    /**
     * Constructs a new PodcastItemDataImpl with the specified date-time parser.
     *
     * @param dateTimeParser the parser used for converting date-time strings
     */
    public PodcastItemDataImpl(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    /**
     * Returns this podcast item data instance.
     *
     * @return this PodcastItemData
     */
    @Override
    public PodcastItemData getPodcastItemData() {
        return this;
    }

    /**
     * Returns the podcast season.
     *
     * @return Optional containing the podcast season, or empty if not set
     */
    @Override
    public Optional<PodcastSeason> getPodcastSeason() {
        return Optional.ofNullable(podcastSeason);
    }

    /**
     * Sets the podcast season.
     *
     * @param podcastSeason the podcast season
     */
    @Override
    public void setPodcastSeason(PodcastSeason podcastSeason) {
        this.podcastSeason = podcastSeason;
    }

    /**
     * Returns the podcast episode.
     *
     * @return Optional containing the podcast episode, or empty if not set
     */
    @Override
    public Optional<PodcastEpisode> getPodcastEpisode() {
        return Optional.ofNullable(podcastEpisode);
    }

    /**
     * Sets the podcast episode.
     *
     * @param podcastEpisode the podcast episode
     */
    @Override
    public void setPodcastEpisode(PodcastEpisode podcastEpisode) {
        this.podcastEpisode = podcastEpisode;
    }

    /**
     * Returns the podcast chapters.
     *
     * @return Optional containing the podcast chapters, or empty if not set
     */
    @Override
    public Optional<PodcastChapters> getPodcastChapters() {
        return Optional.ofNullable(podcastChapters);
    }

    /**
     * Sets the podcast chapters.
     *
     * @param podcastChapters the podcast chapters
     */
    @Override
    public void setPodcastChapters(PodcastChapters podcastChapters) {
        this.podcastChapters = podcastChapters;
    }

    /**
     * Returns the podcast chat.
     *
     * @return Optional containing the podcast chat, or empty if not set
     */
    @Override
    public Optional<PodcastChat> getPodcastChat() {
        return Optional.ofNullable(podcastChat);
    }

    /**
     * Sets the podcast chat.
     *
     * @param podcastChat the podcast chat
     */
    @Override
    public void setPodcastChat(PodcastChat podcastChat) {
        this.podcastChat = podcastChat;
    }

    /**
     * Returns the list of podcast images.
     *
     * @return list of podcast images, or empty list if none set
     */
    @Override
    public List<PodcastImage> getPodcastImages() {
        return emptyListIfNull(podcastImages);
    }

    /**
     * Adds a podcast image.
     *
     * @param podcastImage the podcast image to add
     */
    @Override
    public void addPodcastImage(PodcastImage podcastImage) {
        if (podcastImages == null) {
            podcastImages = new ArrayList<>();
        }
        podcastImages.add(podcastImage);
    }

    /**
     * Returns the list of podcast soundbites.
     *
     * @return list of podcast soundbites, or empty list if none set
     */
    @Override
    public List<PodcastSoundbite> getPodcastSoundbites() {
        return emptyListIfNull(podcastSoundbites);
    }

    /**
     * Adds a podcast soundbite.
     *
     * @param podcastSoundbite the podcast soundbite to add
     */
    @Override
    public void addPodcastSoundbite(PodcastSoundbite podcastSoundbite) {
        if (podcastSoundbites == null) {
            podcastSoundbites = new ArrayList<>();
        }
        podcastSoundbites.add(podcastSoundbite);
    }

    /**
     * Returns the list of podcast transcripts.
     *
     * @return list of podcast transcripts, or empty list if none set
     */
    @Override
    public List<PodcastTranscript> getPodcastTranscripts() {
        return emptyListIfNull(podcastTranscripts);
    }

    /**
     * Adds a podcast transcript.
     *
     * @param podcastTranscript the podcast transcript to add
     */
    @Override
    public void addPodcastTranscript(PodcastTranscript podcastTranscript) {
        if (podcastTranscripts == null) {
            podcastTranscripts = new ArrayList<>();
        }
        podcastTranscripts.add(podcastTranscript);
    }

    /**
     * Returns the list of podcast persons.
     *
     * @return list of podcast persons, or empty list if none set
     */
    @Override
    public List<PodcastPerson> getPodcastPersons() {
        return emptyListIfNull(podcastPersons);
    }

    /**
     * Adds a podcast person.
     *
     * @param podcastPerson the podcast person to add
     */
    @Override
    public void addPodcastPerson(PodcastPerson podcastPerson) {
        if (podcastPersons == null) {
            podcastPersons = new ArrayList<>();
        }
        podcastPersons.add(podcastPerson);
    }

    /**
     * Returns the list of podcast alternate enclosures.
     *
     * @return list of podcast alternate enclosures, or empty list if none set
     */
    @Override
    public List<PodcastAlternateEnclosure> getPodcastAlternateEnclosures() {
        return emptyListIfNull(podcastAlternateEnclosures);
    }

    /**
     * Adds a podcast alternate enclosure.
     *
     * @param podcastAlternateEnclosure the podcast alternate enclosure to add
     */
    @Override
    public void addPodcastAlternateEnclosure(PodcastAlternateEnclosure podcastAlternateEnclosure) {
        if (podcastAlternateEnclosures == null) {
            podcastAlternateEnclosures = new ArrayList<>();
        }
        podcastAlternateEnclosures.add(podcastAlternateEnclosure);
    }

    /**
     * Returns the list of podcast values.
     *
     * @return list of podcast values, or empty list if none set
     */
    @Override
    public List<PodcastValue> getPodcastValues() {
        return emptyListIfNull(podcastValues);
    }

    /**
     * Adds a podcast value.
     *
     * @param podcastValue the podcast value to add
     */
    @Override
    public void addPodcastValue(PodcastValue podcastValue) {
        if (podcastValues == null) {
            podcastValues = new ArrayList<>();
        }
        podcastValues.add(podcastValue);
    }

    /**
     * Returns the list of podcast social interactions.
     *
     * @return list of podcast social interactions, or empty list if none set
     */
    @Override
    public List<PodcastSocialInteract> getPodcastSocialInteracts() {
        return emptyListIfNull(podcastSocialInteracts);
    }

    /**
     * Adds a podcast social interaction.
     *
     * @param podcastSocialInteract the podcast social interaction to add
     */
    @Override
    public void addPodcastSocialInteract(PodcastSocialInteract podcastSocialInteract) {
        if (podcastSocialInteracts == null) {
            podcastSocialInteracts = new ArrayList<>();
        }
        podcastSocialInteracts.add(podcastSocialInteract);
    }

    /**
     * Returns the list of podcast content links.
     *
     * @return list of podcast content links, or empty list if none set
     */
    @Override
    public List<PodcastContentLink> getPodcastContentLinks() {
        return emptyListIfNull(podcastContentLinks);
    }

    /**
     * Adds a podcast content link.
     *
     * @param podcastContentLink the podcast content link to add
     */
    @Override
    public void addPodcastContentLink(PodcastContentLink podcastContentLink) {
        if (podcastContentLinks == null) {
            podcastContentLinks = new ArrayList<>();
        }
        podcastContentLinks.add(podcastContentLink);
    }

    /**
     * Returns the list of podcast txt records.
     *
     * @return list of podcast txt records, or empty list if none set
     */
    @Override
    public List<PodcastTxt> getPodcastTxts() {
        return emptyListIfNull(podcastTxts);
    }

    /**
     * Adds a podcast txt record.
     *
     * @param podcastTxt the podcast txt record to add
     */
    @Override
    public void addPodcastTxt(PodcastTxt podcastTxt) {
        if (podcastTxts == null) {
            podcastTxts = new ArrayList<>();
        }
        podcastTxts.add(podcastTxt);
    }

    /**
     * Checks if this is a podcast live item.
     *
     * @return true if this is a live item, false otherwise
     */
    @Override
    public boolean isPodcastLiveItem() {
        return podcastLiveItemStatus != null;
    }

    /**
     * Returns the podcast live item status.
     *
     * @return Optional containing the status, or empty if not a live item
     */
    @Override
    public Optional<String> getPodcastLiveItemStatus() {
        return Optional.ofNullable(podcastLiveItemStatus);
    }

    /**
     * Sets the podcast live item status.
     *
     * @param podcastLiveItemStatus the live item status
     */
    @Override
    public void setPodcastLiveItemStatus(String podcastLiveItemStatus) {
        this.podcastLiveItemStatus = podcastLiveItemStatus;
    }

    /**
     * Returns the podcast live item start time as string.
     *
     * @return Optional containing the start time, or empty if not set
     */
    @Override
    public Optional<String> getPodcastLiveItemStart() {
        return Optional.ofNullable(podcastLiveItemStart);
    }

    /**
     * Returns the podcast live item start time as ZonedDateTime.
     *
     * @return Optional containing the parsed start time, or empty if not set
     */
    @Override
    public Optional<ZonedDateTime> getPodcastLiveItemStartAsZonedDateTime() {
        return Optional.ofNullable(podcastLiveItemStart).map(dateTimeParser::parse);
    }

    /**
     * Sets the podcast live item start time.
     *
     * @param podcastLiveItemStart the start time string
     */
    @Override
    public void setPodcastLiveItemStart(String podcastLiveItemStart) {
        this.podcastLiveItemStart = podcastLiveItemStart;
    }

    /**
     * Returns the podcast live item end time as string.
     *
     * @return Optional containing the end time, or empty if not set
     */
    @Override
    public Optional<String> getPodcastLiveItemEnd() {
        return Optional.ofNullable(podcastLiveItemEnd);
    }

    /**
     * Returns the podcast live item end time as ZonedDateTime.
     *
     * @return Optional containing the parsed end time, or empty if not set
     */
    @Override
    public Optional<ZonedDateTime> getPodcastLiveItemEndAsZonedDateTime() {
        return Optional.ofNullable(podcastLiveItemEnd).map(dateTimeParser::parse);
    }

    /**
     * Sets the podcast live item end time.
     *
     * @param podcastLiveItemEnd the end time string
     */
    @Override
    public void setPodcastLiveItemEnd(String podcastLiveItemEnd) {
        this.podcastLiveItemEnd = podcastLiveItemEnd;
    }

}
