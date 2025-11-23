package com.apptasticsoftware.rssreader.module.podcast;

import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.module.itunes.ItunesItemImpl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PodcastItemImpl extends ItunesItemImpl implements PodcastItem {
    private final PodcastItemDataImpl data = new PodcastItemDataImpl();

    /**
     * Constructor
     *
     * @param dateTimeParser timestamp parser
     */
    public PodcastItemImpl(DateTimeParser dateTimeParser) {
        super(dateTimeParser);
    }

    public Optional<PodcastSeason> getPodcastSeason() {
        return data.getPodcastSeason();
    }

    public void setPodcastSeason(PodcastSeason podcastSeason) {
        data.setPodcastSeason(podcastSeason);
    }

    public Optional<PodcastEpisode> getPodcastEpisode() {
        return data.getPodcastEpisode();
    }

    public void setPodcastEpisode(PodcastEpisode podcastEpisode) {
        data.setPodcastEpisode(podcastEpisode);
    }

    public Optional<PodcastChapters> getPodcastChapters() {
        return data.getPodcastChapters();
    }

    public void setPodcastChapters(PodcastChapters podcastChapters) {
        data.setPodcastChapters(podcastChapters);
    }

    public List<PodcastImage> getPodcastImages() {
        return data.getPodcastImages();
    }

    public void addPodcastImage(PodcastImage podcastImage) {
        data.addPodcastImage(podcastImage);
    }

    public List<PodcastSoundbite> getPodcastSoundbites() {
        return data.getPodcastSoundbites();
    }

    public void addPodcastSoundbite(PodcastSoundbite podcastSoundbite) {
        data.addPodcastSoundbite(podcastSoundbite);
    }

    public List<PodcastTranscript> getPodcastTranscripts() {
        return data.getPodcastTranscripts();
    }

    public void addPodcastTranscript(PodcastTranscript podcastTranscript) {
        data.addPodcastTranscript(podcastTranscript);
    }

    public List<PodcastPerson> getPodcastPersons() {
        return data.getPodcastPersons();
    }

    public void addPodcastPerson(PodcastPerson podcastPerson) {
        data.addPodcastPerson(podcastPerson);
    }

    public List<PodcastAlternateEnclosure> getPodcastAlternateEnclosures() {
        return data.getPodcastAlternateEnclosures();
    }

    public void addPodcastAlternateEnclosure(PodcastAlternateEnclosure podcastAlternateEnclosure) {
        data.addPodcastAlternateEnclosure(podcastAlternateEnclosure);
    }

    public List<PodcastValue> getPodcastValues() {
        return data.getPodcastValues();
    }

    public void addPodcastValue(PodcastValue podcastValue) {
        data.addPodcastValue(podcastValue);
    }

    public List<PodcastSocialInteract> getPodcastSocialInteracts() {
        return data.getPodcastSocialInteracts();
    }

    public void addPodcastSocialInteract(PodcastSocialInteract podcastSocialInteract) {
        data.addPodcastSocialInteract(podcastSocialInteract);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PodcastItemImpl that = (PodcastItemImpl) o;
        return Objects.equals(getPodcastSeason(), that.getPodcastSeason()) && Objects.equals(getPodcastEpisode(), that.getPodcastEpisode()) && Objects.equals(getPodcastChapters(), that.getPodcastChapters()) && Objects.equals(getPodcastImages(), that.getPodcastImages()) && Objects.equals(getPodcastSoundbites(), that.getPodcastSoundbites()) && Objects.equals(getPodcastTranscripts(), that.getPodcastTranscripts()) && Objects.equals(getPodcastPersons(), that.getPodcastPersons()) && Objects.equals(getPodcastAlternateEnclosures(), that.getPodcastAlternateEnclosures()) && Objects.equals(getPodcastValues(), that.getPodcastValues()) && Objects.equals(getPodcastSocialInteracts(), that.getPodcastSocialInteracts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPodcastSeason(), getPodcastEpisode(), getPodcastChapters(), getPodcastImages(), getPodcastSoundbites(), getPodcastTranscripts(), getPodcastPersons(), getPodcastAlternateEnclosures(), getPodcastValues(), getPodcastSocialInteracts());
    }
}
