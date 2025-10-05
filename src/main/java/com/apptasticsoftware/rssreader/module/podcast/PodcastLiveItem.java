package com.apptasticsoftware.rssreader.module.podcast;

import com.apptasticsoftware.rssreader.Enclosure;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.apptasticsoftware.rssreader.util.Mapper.emptyListIfNull;

public class PodcastLiveItem {
    private String status;
    private String startTime;
    private String endTime;
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
    private List<Enclosure> enclosures;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Optional<String> getEndTime() {
        return Optional.ofNullable(endTime);
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Optional<PodcastSeason> getPodcastSeason() {
        return Optional.ofNullable(podcastSeason);
    }

    public void setPodcastSeason(PodcastSeason podcastSeason) {
        this.podcastSeason = podcastSeason;
    }

    public Optional<PodcastEpisode> getPodcastEpisode() {
        return Optional.ofNullable(podcastEpisode);
    }

    public void setPodcastEpisode(PodcastEpisode podcastEpisode) {
        this.podcastEpisode = podcastEpisode;
    }

    public Optional<PodcastChapters> getPodcastChapters() {
        return Optional.ofNullable(podcastChapters);
    }

    public void setPodcastChapters(PodcastChapters podcastChapters) {
        this.podcastChapters = podcastChapters;
    }

    public List<PodcastImage> getPodcastImages() {
        return emptyListIfNull(podcastImages);
    }

    public void addPodcastImage(PodcastImage podcastImage) {
        if (podcastImages == null) {
            podcastImages = new ArrayList<>();
        }
        podcastImages.add(podcastImage);
    }

    public List<PodcastSoundbite> getPodcastSoundbites() {
        return emptyListIfNull(podcastSoundbites);
    }

    public void addPodcastSoundbite(PodcastSoundbite podcastSoundbite) {
        if (podcastSoundbites == null) {
            podcastSoundbites = new ArrayList<>();
        }
        podcastSoundbites.add(podcastSoundbite);
    }

    public List<PodcastTranscript> getPodcastTranscripts() {
        return emptyListIfNull(podcastTranscripts);
    }

    public void addPodcastTranscript(PodcastTranscript podcastTranscript) {
        if (podcastTranscripts == null) {
            podcastTranscripts = new ArrayList<>();
        }
        podcastTranscripts.add(podcastTranscript);
    }

    public List<PodcastPerson> getPodcastPersons() {
        return emptyListIfNull(podcastPersons);
    }

    public void addPodcastPerson(PodcastPerson podcastPerson) {
        if (podcastPersons == null) {
            podcastPersons = new ArrayList<>();
        }
        podcastPersons.add(podcastPerson);
    }

    public List<PodcastAlternateEnclosure> getPodcastAlternateEnclosures() {
        return emptyListIfNull(podcastAlternateEnclosures);
    }

    public void addPodcastAlternateEnclosure(PodcastAlternateEnclosure podcastAlternateEnclosure) {
        if (podcastAlternateEnclosures == null) {
            podcastAlternateEnclosures = new ArrayList<>();
        }
        podcastAlternateEnclosures.add(podcastAlternateEnclosure);
    }

    public List<PodcastValue> getPodcastValues() {
        return emptyListIfNull(podcastValues);
    }

    public void addPodcastValue(PodcastValue podcastValue) {
        if (podcastValues == null) {
            podcastValues = new ArrayList<>();
        }
        podcastValues.add(podcastValue);
    }

    public List<PodcastSocialInteract> getPodcastSocialInteracts() {
        return emptyListIfNull(podcastSocialInteracts);
    }

    public void addPodcastSocialInteract(PodcastSocialInteract podcastSocialInteract) {
        if (podcastSocialInteracts == null) {
            podcastSocialInteracts = new ArrayList<>();
        }
        podcastSocialInteracts.add(podcastSocialInteract);
    }

    public List<Enclosure> getEnclosures() {
        return emptyListIfNull(enclosures);
    }

    public void addEnclosure(Enclosure enclosure) {
        if (enclosures == null) {
            enclosures = new ArrayList<>();
        }
        enclosures.add(enclosure);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PodcastLiveItem that = (PodcastLiveItem) o;
        return Objects.equals(getStatus(), that.getStatus()) && Objects.equals(getStartTime(), that.getStartTime()) && Objects.equals(getEndTime(), that.getEndTime()) && Objects.equals(getPodcastSeason(), that.getPodcastSeason()) && Objects.equals(getPodcastEpisode(), that.getPodcastEpisode()) && Objects.equals(getPodcastChapters(), that.getPodcastChapters()) && Objects.equals(getPodcastImages(), that.getPodcastImages()) && Objects.equals(getPodcastSoundbites(), that.getPodcastSoundbites()) && Objects.equals(getPodcastTranscripts(), that.getPodcastTranscripts()) && Objects.equals(getPodcastPersons(), that.getPodcastPersons()) && Objects.equals(getPodcastAlternateEnclosures(), that.getPodcastAlternateEnclosures()) && Objects.equals(getPodcastValues(), that.getPodcastValues()) && Objects.equals(getPodcastSocialInteracts(), that.getPodcastSocialInteracts()) && Objects.equals(getEnclosures(), that.getEnclosures());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStatus(), getStartTime(), getEndTime(), getPodcastSeason(), getPodcastEpisode(), getPodcastChapters(), getPodcastImages(), getPodcastSoundbites(), getPodcastTranscripts(), getPodcastPersons(), getPodcastAlternateEnclosures(), getPodcastValues(), getPodcastSocialInteracts(), getEnclosures());
    }
}
