package com.apptasticsoftware.rssreader.module.podcast;

import com.apptasticsoftware.rssreader.module.podcast.internal.PodcastChannelDataProvider;
import java.util.List;
import java.util.Optional;

public interface PodcastChannelData {
    default String getPodcastGuid() {
        return ((PodcastChannelDataProvider) this).podcastChannelData().getPodcastGuid();
    }

    default void setPodcastGuid(String podcastGuid) {
        ((PodcastChannelDataProvider) this).podcastChannelData().setPodcastGuid(podcastGuid);
    }

    default Optional<PodcastLicense> getPodcastLicense() {
        return ((PodcastChannelDataProvider) this).podcastChannelData().getPodcastLicense();
    }

    default void setPodcastLicense(PodcastLicense podcastLicense) {
        ((PodcastChannelDataProvider) this).podcastChannelData().setPodcastLicense(podcastLicense);
    }

    default Optional<PodcastLocked> getPodcastLocked() {
        return ((PodcastChannelDataProvider) this).podcastChannelData().getPodcastLocked();
    }

    default void setPodcastLocked(PodcastLocked podcastLocked) {
        ((PodcastChannelDataProvider) this).podcastChannelData().setPodcastLocked(podcastLocked);
    }

    default List<PodcastBlock> getPodcastBlocks() {
        return ((PodcastChannelDataProvider) this).podcastChannelData().getPodcastBlocks();
    }

    default void addPodcastBlock(PodcastBlock podcastBlock) {
        ((PodcastChannelDataProvider) this).podcastChannelData().addPodcastBlock(podcastBlock);
    }

    default List<PodcastFunding> getPodcastFundings() {
        return ((PodcastChannelDataProvider) this).podcastChannelData().getPodcastFundings();
    }

    default void addPodcastFunding(PodcastFunding podcastFunding) {
        ((PodcastChannelDataProvider) this).podcastChannelData().addPodcastFunding(podcastFunding);
    }

    default List<PodcastLocation> getPodcastLocations() {
        return ((PodcastChannelDataProvider) this).podcastChannelData().getPodcastLocations();
    }

    default void addPodcastLocation(PodcastLocation podcastLocation) {
        ((PodcastChannelDataProvider) this).podcastChannelData().addPodcastLocation(podcastLocation);
    }

    default String getPodcastMedium() {
        return ((PodcastChannelDataProvider) this).podcastChannelData().getPodcastMedium();
    }

    default void setPodcastMedium(String podcastMedium) {
        ((PodcastChannelDataProvider) this).podcastChannelData().setPodcastMedium(podcastMedium);
    }

    default List<PodcastValue> getPodcastValues() {
        return ((PodcastChannelDataProvider) this).podcastChannelData().getPodcastValues();
    }

    default void addPodcastValue(PodcastValue podcastValue) {
        ((PodcastChannelDataProvider) this).podcastChannelData().addPodcastValue(podcastValue);
    }

    default List<PodcastRemoteItem> getPodcastPodrolls() {
        return ((PodcastChannelDataProvider) this).podcastChannelData().getPodcastPodrolls();
    }

    default void addPodcastPodroll(PodcastRemoteItem podcastPodroll) {
        ((PodcastChannelDataProvider) this).podcastChannelData().addPodcastPodroll(podcastPodroll);
    }

    default Optional<PodcastRemoteItem> getPodcastPublisher() {
        return ((PodcastChannelDataProvider) this).podcastChannelData().getPodcastPublisher();
    }

    default void setPodcastPublisher(PodcastRemoteItem podcastPublisher) {
        ((PodcastChannelDataProvider) this).podcastChannelData().setPodcastPublisher(podcastPublisher);
    }

    default List<PodcastPerson> getPodcastPersons() {
        return ((PodcastChannelDataProvider) this).podcastChannelData().getPodcastPersons();
    }

    default void addPodcastPerson(PodcastPerson podcastPerson) {
        ((PodcastChannelDataProvider) this).podcastChannelData().addPodcastPerson(podcastPerson);
    }

    default Optional<PodcastUpdateFrequency> getPodcastUpdateFrequency() {
        return ((PodcastChannelDataProvider) this).podcastChannelData().getPodcastUpdateFrequency();
    }

    default void setPodcastUpdateFrequency(PodcastUpdateFrequency podcastUpdateFrequency) {
        ((PodcastChannelDataProvider) this).podcastChannelData().setPodcastUpdateFrequency(podcastUpdateFrequency);
    }

    default List<PodcastTrailer> getPodcastTrailers() {
        return ((PodcastChannelDataProvider) this).podcastChannelData().getPodcastTrailers();
    }

    default void addPodcastTrailer(PodcastTrailer podcastTrailer) {
        ((PodcastChannelDataProvider) this).podcastChannelData().addPodcastTrailer(podcastTrailer);
    }

    default Optional<PodcastChat> getPodcastChat() {
        return ((PodcastChannelDataProvider) this).podcastChannelData().getPodcastChat();
    }

    default void setPodcastChat(PodcastChat podcastChat) {
        ((PodcastChannelDataProvider) this).podcastChannelData().setPodcastChat(podcastChat);
    }

    default List<PodcastTxt> getPodcastTxts() {
        return ((PodcastChannelDataProvider) this).podcastChannelData().getPodcastTxts();
    }

    default void addPodcastTxt(PodcastTxt podcastTxt) {
        ((PodcastChannelDataProvider) this).podcastChannelData().addPodcastTxt(podcastTxt);
    }

    default List<PodcastImage> getPodcastImages() {
        return ((PodcastChannelDataProvider) this).podcastChannelData().getPodcastImages();
    }

    default void addPodcastImage(PodcastImage podcastImage) {
        ((PodcastChannelDataProvider) this).podcastChannelData().addPodcastImage(podcastImage);
    }

    default Optional<Boolean> isPodcastUsingPodping() {
        return ((PodcastChannelDataProvider) this).podcastChannelData().isPodcastUsingPodping();
    }

    default void setPodcastUsingPodping(Boolean podcastUsesPodping) {
        ((PodcastChannelDataProvider) this).podcastChannelData().setPodcastUsingPodping(podcastUsesPodping);
    }
}
