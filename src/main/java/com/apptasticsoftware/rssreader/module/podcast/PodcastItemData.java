package com.apptasticsoftware.rssreader.module.podcast;

import com.apptasticsoftware.rssreader.module.podcast.internal.PodcastItemDataProvider;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public interface PodcastItemData {
    default Optional<PodcastSeason> getPodcastSeason() {
        return ((PodcastItemDataProvider) this).podcastItemData().getPodcastSeason();
    }

    default void setPodcastSeason(PodcastSeason podcastSeason) {
        ((PodcastItemDataProvider) this).podcastItemData().setPodcastSeason(podcastSeason);
    }

    default Optional<PodcastEpisode> getPodcastEpisode() {
        return ((PodcastItemDataProvider) this).podcastItemData().getPodcastEpisode();
    }

    default void setPodcastEpisode(PodcastEpisode podcastEpisode) {
        ((PodcastItemDataProvider) this).podcastItemData().setPodcastEpisode(podcastEpisode);
    }

    default Optional<PodcastChapters> getPodcastChapters() {
        return ((PodcastItemDataProvider) this).podcastItemData().getPodcastChapters();
    }

    default void setPodcastChapters(PodcastChapters podcastChapters) {
        ((PodcastItemDataProvider) this).podcastItemData().setPodcastChapters(podcastChapters);
    }

    default Optional<PodcastChat> getPodcastChat() {
        return ((PodcastItemDataProvider) this).podcastItemData().getPodcastChat();
    }

    default void setPodcastChat(PodcastChat podcastChat) {
        ((PodcastItemDataProvider) this).podcastItemData().setPodcastChat(podcastChat);
    }

    default List<PodcastImage> getPodcastImages() {
        return ((PodcastItemDataProvider) this).podcastItemData().getPodcastImages();
    }

    default void addPodcastImage(PodcastImage podcastImage) {
        ((PodcastItemDataProvider) this).podcastItemData().addPodcastImage(podcastImage);
    }

    default List<PodcastSoundbite> getPodcastSoundbites() {
        return ((PodcastItemDataProvider) this).podcastItemData().getPodcastSoundbites();
    }

    default void addPodcastSoundbite(PodcastSoundbite podcastSoundbite) {
        ((PodcastItemDataProvider) this).podcastItemData().addPodcastSoundbite(podcastSoundbite);
    }

    default List<PodcastTranscript> getPodcastTranscripts() {
        return ((PodcastItemDataProvider) this).podcastItemData().getPodcastTranscripts();
    }

    default void addPodcastTranscript(PodcastTranscript podcastTranscript) {
        ((PodcastItemDataProvider) this).podcastItemData().addPodcastTranscript(podcastTranscript);
    }

    default List<PodcastPerson> getPodcastPersons() {
        return ((PodcastItemDataProvider) this).podcastItemData().getPodcastPersons();
    }

    default void addPodcastPerson(PodcastPerson podcastPerson) {
        ((PodcastItemDataProvider) this).podcastItemData().addPodcastPerson(podcastPerson);
    }

    default List<PodcastAlternateEnclosure> getPodcastAlternateEnclosures() {
        return ((PodcastItemDataProvider) this).podcastItemData().getPodcastAlternateEnclosures();
    }

    default void addPodcastAlternateEnclosure(PodcastAlternateEnclosure podcastAlternateEnclosure) {
        ((PodcastItemDataProvider) this).podcastItemData().addPodcastAlternateEnclosure(podcastAlternateEnclosure);
    }

    default List<PodcastValue> getPodcastValues() {
        return ((PodcastItemDataProvider) this).podcastItemData().getPodcastValues();
    }

    default void addPodcastValue(PodcastValue podcastValue) {
        ((PodcastItemDataProvider) this).podcastItemData().addPodcastValue(podcastValue);
    }

    default List<PodcastSocialInteract> getPodcastSocialInteracts() {
        return ((PodcastItemDataProvider) this).podcastItemData().getPodcastSocialInteracts();
    }

    default void addPodcastSocialInteract(PodcastSocialInteract podcastSocialInteract) {
        ((PodcastItemDataProvider) this).podcastItemData().addPodcastSocialInteract(podcastSocialInteract);
    }

    default List<PodcastContentLink> getPodcastContentLinks() {
        return ((PodcastItemDataProvider) this).podcastItemData().getPodcastContentLinks();
    }

    default void addPodcastContentLink(PodcastContentLink podcastContentLink) {
        ((PodcastItemDataProvider) this).podcastItemData().addPodcastContentLink(podcastContentLink);
    }

    default List<PodcastTxt> getPodcastTxts() {
        return ((PodcastItemDataProvider) this).podcastItemData().getPodcastTxts();
    }

    default void addPodcastTxt(PodcastTxt podcastTxt) {
        ((PodcastItemDataProvider) this).podcastItemData().addPodcastTxt(podcastTxt);
    }

    default boolean isPodcastLiveItem() {
        return ((PodcastItemDataProvider) this).podcastItemData().isPodcastLiveItem();
    }

    default Optional<String> getPodcastLiveItemStatus() {
        return ((PodcastItemDataProvider) this).podcastItemData().getPodcastLiveItemStatus();
    }

    default void setPodcastLiveItemStatus(String podcastLiveItemStatus) {
        ((PodcastItemDataProvider) this).podcastItemData().setPodcastLiveItemStatus(podcastLiveItemStatus);
    }

    default Optional<String> getPodcastLiveItemStart() {
        return ((PodcastItemDataProvider) this).podcastItemData().getPodcastLiveItemStart();
    }

    default Optional<ZonedDateTime> getPodcastLiveItemStartAsZonedDateTime() {
        return ((PodcastItemDataProvider) this).podcastItemData().getPodcastLiveItemStartAsZonedDateTime();
    }

    default void setPodcastLiveItemStart(String podcastLiveItemStart) {
        ((PodcastItemDataProvider) this).podcastItemData().setPodcastLiveItemStart(podcastLiveItemStart);
    }

    default Optional<String> getPodcastLiveItemEnd() {
        return ((PodcastItemDataProvider) this).podcastItemData().getPodcastLiveItemEnd();
    }

    default Optional<ZonedDateTime> getPodcastLiveItemEndAsZonedDateTime() {
        return ((PodcastItemDataProvider) this).podcastItemData().getPodcastLiveItemEndAsZonedDateTime();
    }

    default void setPodcastLiveItemEnd(String podcastLiveItemEnd) {
        ((PodcastItemDataProvider) this).podcastItemData().setPodcastLiveItemEnd(podcastLiveItemEnd);
    }
}
