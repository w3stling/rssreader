package com.apptasticsoftware.rssreader.module.podcast;

import java.util.List;
import java.util.Optional;

public interface PodcastChannelData {

    PodcastChannelData getPodcastChannelData();

    default String getPodcastGuid() {
        return getPodcastChannelData().getPodcastGuid();
    }

    default void setPodcastGuid(String podcastGuid) {
        getPodcastChannelData().setPodcastGuid(podcastGuid);
    }

    default Optional<PodcastLicense> getPodcastLicense() {
        return getPodcastChannelData().getPodcastLicense();
    }

    default void setPodcastLicense(PodcastLicense podcastLicense) {
        getPodcastChannelData().setPodcastLicense(podcastLicense);
    }

    default Optional<PodcastLocked> getPodcastLocked() {
        return getPodcastChannelData().getPodcastLocked();
    }

    default void setPodcastLocked(PodcastLocked podcastLocked) {
        getPodcastChannelData().setPodcastLocked(podcastLocked);
    }

    default List<PodcastBlock> getPodcastBlocks() {
        return getPodcastChannelData().getPodcastBlocks();
    }

    default void addPodcastBlock(PodcastBlock podcastBlock) {
        getPodcastChannelData().addPodcastBlock(podcastBlock);
    }

    default List<PodcastFunding> getPodcastFundings() {
        return getPodcastChannelData().getPodcastFundings();
    }

    default void addPodcastFunding(PodcastFunding podcastFunding) {
        getPodcastChannelData().addPodcastFunding(podcastFunding);
    }

    default List<PodcastLocation> getPodcastLocations() {
        return getPodcastChannelData().getPodcastLocations();
    }

    default void addPodcastLocation(PodcastLocation podcastLocation) {
        getPodcastChannelData().addPodcastLocation(podcastLocation);
    }

    default String getPodcastMedium() {
        return getPodcastChannelData().getPodcastMedium();
    }

    default void setPodcastMedium(String podcastMedium) {
        getPodcastChannelData().setPodcastMedium(podcastMedium);
    }

    default List<PodcastValue> getPodcastValues() {
        return getPodcastChannelData().getPodcastValues();
    }

    default void addPodcastValue(PodcastValue podcastValue) {
        getPodcastChannelData().addPodcastValue(podcastValue);
    }

    default List<PodcastRemoteItem> getPodcastPodrolls() {
        return getPodcastChannelData().getPodcastPodrolls();
    }

    default void addPodcastPodroll(PodcastRemoteItem podcastPodroll) {
        getPodcastChannelData().addPodcastPodroll(podcastPodroll);
    }

    default Optional<PodcastRemoteItem> getPodcastPublisher() {
        return getPodcastChannelData().getPodcastPublisher();
    }

    default void setPodcastPublisher(PodcastRemoteItem podcastPublisher) {
        getPodcastChannelData().setPodcastPublisher(podcastPublisher);
    }

    default List<PodcastPerson> getPodcastPersons() {
        return getPodcastChannelData().getPodcastPersons();
    }

    default void addPodcastPerson(PodcastPerson podcastPerson) {
        getPodcastChannelData().addPodcastPerson(podcastPerson);
    }

    default Optional<PodcastUpdateFrequency> getPodcastUpdateFrequency() {
        return getPodcastChannelData().getPodcastUpdateFrequency();
    }

    default void setPodcastUpdateFrequency(PodcastUpdateFrequency podcastUpdateFrequency) {
        getPodcastChannelData().setPodcastUpdateFrequency(podcastUpdateFrequency);
    }

    default List<PodcastTrailer> getPodcastTrailers() {
        return getPodcastChannelData().getPodcastTrailers();
    }

    default void addPodcastTrailer(PodcastTrailer podcastTrailer) {
        getPodcastChannelData().addPodcastTrailer(podcastTrailer);
    }

    default Optional<PodcastChat> getPodcastChat() {
        return getPodcastChannelData().getPodcastChat();
    }

    default void setPodcastChat(PodcastChat podcastChat) {
        getPodcastChannelData().setPodcastChat(podcastChat);
    }

    default List<PodcastTxt> getPodcastTxts() {
        return getPodcastChannelData().getPodcastTxts();
    }

    default void addPodcastTxt(PodcastTxt podcastTxt) {
        getPodcastChannelData().addPodcastTxt(podcastTxt);
    }

    default Optional<Boolean> isPodcastUsingPodping() {
        return getPodcastChannelData().isPodcastUsingPodping();
    }

    default void setPodcastUsingPodping(Boolean podcastUsesPodping) {
        getPodcastChannelData().setPodcastUsingPodping(podcastUsesPodping);
    }
}
