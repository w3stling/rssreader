package com.apptasticsoftware.rssreader.module.podcast;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.apptasticsoftware.rssreader.util.Mapper.emptyListIfNull;

public class PodcastItemDataImpl implements PodcastItemData {
    private PodcastSeason podcastSeason;
    private PodcastEpisode podcastEpisode;
    private PodcastChapters podcastChapters;
    private List<PodcastImage> podcastImages;
    private List<PodcastSoundbite> podcastSoundbites;
    private List<PodcastTranscript> podcastTranscripts;
    private List<PodcastPerson> podcastPersons;
    private List<PodcastAlternateEnclosure> podcastAlternateEnclosures;
    private List<PodcastValue> podcastValues;
    private List<PodcastSocialInteract> podcastSocialInteracts;

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
}
