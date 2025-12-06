package com.apptasticsoftware.rssreader.module.podcast;

import com.apptasticsoftware.rssreader.DateTimeParser;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.apptasticsoftware.rssreader.util.Mapper.emptyListIfNull;

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

    public PodcastItemDataImpl(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public PodcastItemData getPodcastItemData() {
        return this;
    }

    @Override
    public Optional<PodcastSeason> getPodcastSeason() {
        return Optional.ofNullable(podcastSeason);
    }

    @Override
    public void setPodcastSeason(PodcastSeason podcastSeason) {
        this.podcastSeason = podcastSeason;
    }

    @Override
    public Optional<PodcastEpisode> getPodcastEpisode() {
        return Optional.ofNullable(podcastEpisode);
    }

    @Override
    public void setPodcastEpisode(PodcastEpisode podcastEpisode) {
        this.podcastEpisode = podcastEpisode;
    }

    @Override
    public Optional<PodcastChapters> getPodcastChapters() {
        return Optional.ofNullable(podcastChapters);
    }

    @Override
    public void setPodcastChapters(PodcastChapters podcastChapters) {
        this.podcastChapters = podcastChapters;
    }

    @Override
    public Optional<PodcastChat> getPodcastChat() {
        return Optional.ofNullable(podcastChat);
    }

    @Override
    public void setPodcastChat(PodcastChat podcastChat) {
        this.podcastChat = podcastChat;
    }

    @Override
    public List<PodcastImage> getPodcastImages() {
        return emptyListIfNull(podcastImages);
    }

    @Override
    public void addPodcastImage(PodcastImage podcastImage) {
        if (podcastImages == null) {
            podcastImages = new ArrayList<>();
        }
        podcastImages.add(podcastImage);
    }

    @Override
    public List<PodcastSoundbite> getPodcastSoundbites() {
        return emptyListIfNull(podcastSoundbites);
    }

    @Override
    public void addPodcastSoundbite(PodcastSoundbite podcastSoundbite) {
        if (podcastSoundbites == null) {
            podcastSoundbites = new ArrayList<>();
        }
        podcastSoundbites.add(podcastSoundbite);
    }

    @Override
    public List<PodcastTranscript> getPodcastTranscripts() {
        return emptyListIfNull(podcastTranscripts);
    }

    @Override
    public void addPodcastTranscript(PodcastTranscript podcastTranscript) {
        if (podcastTranscripts == null) {
            podcastTranscripts = new ArrayList<>();
        }
        podcastTranscripts.add(podcastTranscript);
    }

    @Override
    public List<PodcastPerson> getPodcastPersons() {
        return emptyListIfNull(podcastPersons);
    }

    @Override
    public void addPodcastPerson(PodcastPerson podcastPerson) {
        if (podcastPersons == null) {
            podcastPersons = new ArrayList<>();
        }
        podcastPersons.add(podcastPerson);
    }

    @Override
    public List<PodcastAlternateEnclosure> getPodcastAlternateEnclosures() {
        return emptyListIfNull(podcastAlternateEnclosures);
    }

    @Override
    public void addPodcastAlternateEnclosure(PodcastAlternateEnclosure podcastAlternateEnclosure) {
        if (podcastAlternateEnclosures == null) {
            podcastAlternateEnclosures = new ArrayList<>();
        }
        podcastAlternateEnclosures.add(podcastAlternateEnclosure);
    }

    @Override
    public List<PodcastValue> getPodcastValues() {
        return emptyListIfNull(podcastValues);
    }

    @Override
    public void addPodcastValue(PodcastValue podcastValue) {
        if (podcastValues == null) {
            podcastValues = new ArrayList<>();
        }
        podcastValues.add(podcastValue);
    }

    @Override
    public List<PodcastSocialInteract> getPodcastSocialInteracts() {
        return emptyListIfNull(podcastSocialInteracts);
    }

    @Override
    public void addPodcastSocialInteract(PodcastSocialInteract podcastSocialInteract) {
        if (podcastSocialInteracts == null) {
            podcastSocialInteracts = new ArrayList<>();
        }
        podcastSocialInteracts.add(podcastSocialInteract);
    }

    @Override
    public List<PodcastContentLink> getPodcastContentLinks() {
        return emptyListIfNull(podcastContentLinks);
    }

    @Override
    public void addPodcastContentLink(PodcastContentLink podcastContentLink) {
        if (podcastContentLinks == null) {
            podcastContentLinks = new ArrayList<>();
        }
        podcastContentLinks.add(podcastContentLink);
    }

    @Override
    public List<PodcastTxt> getPodcastTxts() {
        return emptyListIfNull(podcastTxts);
    }

    @Override
    public void addPodcastTxt(PodcastTxt podcastTxt) {
        if (podcastTxts == null) {
            podcastTxts = new ArrayList<>();
        }
        podcastTxts.add(podcastTxt);
    }

    public boolean isPodcastLiveItem() {
        return podcastLiveItemStatus != null;
    }

    public Optional<String> getPodcastLiveItemStatus() {
        return Optional.ofNullable(podcastLiveItemStatus);
    }

    public void setPodcastLiveItemStatus(String podcastLiveItemStatus) {
        this.podcastLiveItemStatus = podcastLiveItemStatus;
    }

    public Optional<String> getPodcastLiveItemStart() {
        return Optional.ofNullable(podcastLiveItemStart);
    }

    public Optional<ZonedDateTime> getPodcastLiveItemStartAsZonedDateTime() {
        return Optional.ofNullable(podcastLiveItemStart).map(dateTimeParser::parse);
    }

    public void setPodcastLiveItemStart(String podcastLiveItemStart) {
        this.podcastLiveItemStart = podcastLiveItemStart;
    }

    public Optional<String> getPodcastLiveItemEnd() {
        return Optional.ofNullable(podcastLiveItemEnd);
    }

    public Optional<ZonedDateTime> getPodcastLiveItemEndAsZonedDateTime() {
        return Optional.ofNullable(podcastLiveItemEnd).map(dateTimeParser::parse);
    }

    public void setPodcastLiveItemEnd(String podcastLiveItemEnd) {
        this.podcastLiveItemEnd = podcastLiveItemEnd;
    }

}
