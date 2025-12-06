package com.apptasticsoftware.rssreader.module.podcast;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.apptasticsoftware.rssreader.util.Mapper.emptyListIfNull;

public class PodcastChannelDataImpl implements PodcastChannelData {
    private String podcastGuid;
    private PodcastLicense podcastLicense;
    private PodcastLocked podcastLocked;
    private List<PodcastBlock> podcastBlocks;
    private List<PodcastFunding> podcastFundings;
    private List<PodcastLocation> podcastLocations;
    private String podcastMedium;
    private List<PodcastValue> podcastValues;
    private List<PodcastRemoteItem> podcastPodrolls;
    private PodcastRemoteItem podcastPublisher;
    private List<PodcastPerson> podcastPersons;
    private PodcastUpdateFrequency podcastUpdateFrequency;
    private List<PodcastTrailer> podcastTrailers;
    private PodcastChat podcastChat;
    private List<PodcastTxt> podcastTxts;

    @Override
    public PodcastChannelData getPodcastChannelData() {
        return this;
    }

    @Override
    public String getPodcastGuid() {
        return podcastGuid;
    }

    @Override
    public void setPodcastGuid(String podcastGuid) {
        this.podcastGuid = podcastGuid;
    }

    @Override
    public Optional<PodcastLicense> getPodcastLicense() {
        return Optional.ofNullable(podcastLicense);
    }

    @Override
    public void setPodcastLicense(PodcastLicense podcastLicense) {
        this.podcastLicense = podcastLicense;
    }

    @Override
    public Optional<PodcastLocked> getPodcastLocked() {
        return Optional.ofNullable(podcastLocked);
    }

    @Override
    public void setPodcastLocked(PodcastLocked podcastLocked) {
        this.podcastLocked = podcastLocked;
    }

    @Override
    public List<PodcastBlock> getPodcastBlocks() {
        return emptyListIfNull(podcastBlocks);
    }

    @Override
    public void addPodcastBlock(PodcastBlock podcastBlock) {
        if (podcastBlocks == null) {
            podcastBlocks = new ArrayList<>();
        }
        this.podcastBlocks.add(podcastBlock);
    }

    @Override
    public List<PodcastFunding> getPodcastFundings() {
        return emptyListIfNull(podcastFundings);
    }

    @Override
    public void addPodcastFunding(PodcastFunding podcastFunding) {
        if (podcastFundings == null) {
            podcastFundings = new ArrayList<>();
        }
        this.podcastFundings.add(podcastFunding);
    }

    @Override
    public List<PodcastLocation> getPodcastLocations() {
        return emptyListIfNull(podcastLocations);
    }

    @Override
    public void addPodcastLocation(PodcastLocation podcastLocation) {
        if (podcastLocations == null) {
            podcastLocations = new ArrayList<>();
        }
        podcastLocations.add(podcastLocation);
    }

    @Override
    public String getPodcastMedium() {
        return podcastMedium;
    }

    @Override
    public void setPodcastMedium(String podcastMedium) {
        this.podcastMedium = podcastMedium;
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
    public List<PodcastRemoteItem> getPodcastPodrolls() {
        return emptyListIfNull(podcastPodrolls);
    }

    @Override
    public void addPodcastPodroll(PodcastRemoteItem podcastPodroll) {
        if (podcastPodrolls == null) {
            podcastPodrolls = new ArrayList<>();
        }
        podcastPodrolls.add(podcastPodroll);
    }

    @Override
    public Optional<PodcastRemoteItem> getPodcastPublisher() {
        return Optional.ofNullable(podcastPublisher);
    }

    @Override
    public void setPodcastPublisher(PodcastRemoteItem podcastPublisher) {
        this.podcastPublisher = podcastPublisher;
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
    public Optional<PodcastUpdateFrequency> getPodcastUpdateFrequency() {
        return Optional.ofNullable(podcastUpdateFrequency);
    }

    @Override
    public void setPodcastUpdateFrequency(PodcastUpdateFrequency podcastUpdateFrequency) {
        this.podcastUpdateFrequency = podcastUpdateFrequency;
    }

    @Override
    public List<PodcastTrailer> getPodcastTrailers() {
        return emptyListIfNull(podcastTrailers);
    }

    @Override
    public void addPodcastTrailer(PodcastTrailer podcastTrailer) {
        if (podcastTrailers == null) {
            podcastTrailers = new ArrayList<>();
        }
        podcastTrailers.add(podcastTrailer);
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
}
