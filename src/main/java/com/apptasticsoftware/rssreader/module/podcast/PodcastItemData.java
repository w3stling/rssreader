package com.apptasticsoftware.rssreader.module.podcast;

import java.util.List;
import java.util.Optional;

public interface PodcastItemData {

    PodcastItemData getPodcastItemData();

    default Optional<PodcastSeason> getPodcastSeason() {
        return getPodcastItemData().getPodcastSeason();
    }

    default void setPodcastSeason(PodcastSeason podcastSeason) {
        getPodcastItemData().setPodcastSeason(podcastSeason);
    }

    default Optional<PodcastEpisode> getPodcastEpisode() {
        return getPodcastItemData().getPodcastEpisode();
    }

    default void setPodcastEpisode(PodcastEpisode podcastEpisode) {
        getPodcastItemData().setPodcastEpisode(podcastEpisode);
    }

    default Optional<PodcastChapters> getPodcastChapters() {
        return getPodcastItemData().getPodcastChapters();
    }

    default void setPodcastChapters(PodcastChapters podcastChapters) {
        getPodcastItemData().setPodcastChapters(podcastChapters);
    }

    default List<PodcastImage> getPodcastImages() {
        return getPodcastItemData().getPodcastImages();
    }

    default void addPodcastImage(PodcastImage podcastImage) {
        getPodcastItemData().addPodcastImage(podcastImage);
    }

    default List<PodcastSoundbite> getPodcastSoundbites() {
        return getPodcastItemData().getPodcastSoundbites();
    }

    default void addPodcastSoundbite(PodcastSoundbite podcastSoundbite) {
        getPodcastItemData().addPodcastSoundbite(podcastSoundbite);
    }

    default List<PodcastTranscript> getPodcastTranscripts() {
        return getPodcastItemData().getPodcastTranscripts();
    }

    default void addPodcastTranscript(PodcastTranscript podcastTranscript) {
        getPodcastItemData().addPodcastTranscript(podcastTranscript);
    }

    default List<PodcastPerson> getPodcastPersons() {
        return getPodcastItemData().getPodcastPersons();
    }

    default void addPodcastPerson(PodcastPerson podcastPerson) {
        getPodcastItemData().addPodcastPerson(podcastPerson);
    }

    default List<PodcastAlternateEnclosure> getPodcastAlternateEnclosures() {
        return getPodcastItemData().getPodcastAlternateEnclosures();
    }

    default void addPodcastAlternateEnclosure(PodcastAlternateEnclosure podcastAlternateEnclosure) {
        getPodcastItemData().addPodcastAlternateEnclosure(podcastAlternateEnclosure);
    }

    default List<PodcastValue> getPodcastValues() {
        return getPodcastItemData().getPodcastValues();
    }

    default void addPodcastValue(PodcastValue podcastValue) {
        getPodcastItemData().addPodcastValue(podcastValue);
    }

    default List<PodcastSocialInteract> getPodcastSocialInteracts() {
        return getPodcastItemData().getPodcastSocialInteracts();
    }

    default void addPodcastSocialInteract(PodcastSocialInteract podcastSocialInteract) {
        getPodcastItemData().addPodcastSocialInteract(podcastSocialInteract);
    }
}
