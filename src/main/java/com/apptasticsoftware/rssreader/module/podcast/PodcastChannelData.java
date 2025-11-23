package com.apptasticsoftware.rssreader.module.podcast;

import java.util.List;
import java.util.Optional;

public interface PodcastChannelData {
    String getPodcastGuid();

    void setPodcastGuid(String podcastGuid);

    Optional<PodcastLicense> getPodcastLicense();

    void setPodcastLicense(PodcastLicense podcastLicense);

    Optional<PodcastLocked> getPodcastLocked();

    void setPodcastLocked(PodcastLocked podcastLocked);

    List<PodcastBlock> getPodcastBlocks();

    void addPodcastBlock(PodcastBlock podcastBlock);

    List<PodcastFunding> getPodcastFundings();

    void addPodcastFunding(PodcastFunding podcastFunding);

    List<PodcastLocation> getPodcastLocations();

    void addPodcastLocation(PodcastLocation podcastLocation);

    String getPodcastMedium();

    void setPodcastMedium(String podcastMedium);

    List<PodcastValue> getPodcastValues();

    void addPodcastValue(PodcastValue podcastValue);


    List<PodcastRemoteItem> getPodcastPodrolls();

    void addPodcastPodroll(PodcastRemoteItem podcastPodroll);

    Optional<PodcastRemoteItem> getPodcastPublisher();

    void setPodcastPublisher(PodcastRemoteItem podcastPublisher);

    List<PodcastPerson> getPodcastPersons();

    void addPodcastPerson(PodcastPerson podcastPerson);

    Optional<PodcastUpdateFrequency> getPodcastUpdateFrequency();

    void setPodcastUpdateFrequency(PodcastUpdateFrequency podcastUpdateFrequency);

    List<PodcastTrailer> getPodcastTrailers();

    void addPodcastTrailer(PodcastTrailer podcastTrailer);

    List<PodcastLiveItem> getPodcastLiveItems();

    void addPodcastLiveItem(PodcastLiveItem podcastLiveItem);
}
