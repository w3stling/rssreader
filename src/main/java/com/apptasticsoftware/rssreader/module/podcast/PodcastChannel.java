package com.apptasticsoftware.rssreader.module.podcast;

import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.module.itunes.ItunesChannel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.apptasticsoftware.rssreader.util.Mapper.*;

public class PodcastChannel extends ItunesChannel {
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
    private List<PodcastLiveItem> podcastLiveItems;

    /**
     * Constructor
     *
     * @param dateTimeParser timestamp parser
     */
    public PodcastChannel(DateTimeParser dateTimeParser) {
        super(dateTimeParser);
    }

    public String getPodcastGuid() {
        return podcastGuid;
    }

    public void setPodcastGuid(String podcastGuid) {
        this.podcastGuid = podcastGuid;
    }

    public Optional<PodcastLicense> getPodcastLicense() {
        return Optional.ofNullable(podcastLicense);
    }

    public void setPodcastLicense(PodcastLicense podcastLicense) {
        this.podcastLicense = podcastLicense;
    }

    public Optional<PodcastLocked> getPodcastLocked() {
        return Optional.ofNullable(podcastLocked);
    }

    public void setPodcastLocked(PodcastLocked podcastLocked) {
        this.podcastLocked = podcastLocked;
    }

    public List<PodcastBlock> getPodcastBlocks() {
        return emptyListIfNull(podcastBlocks);
    }

    public void addPodcastBlock(PodcastBlock podcastBlock) {
        if (podcastBlocks == null) {
            podcastBlocks = new ArrayList<>();
        }
        this.podcastBlocks.add(podcastBlock);
    }

    public List<PodcastFunding> getPodcastFundings() {
        return emptyListIfNull(podcastFundings);
    }

    public void addPodcastFunding(PodcastFunding podcastFunding) {
        if (podcastFundings == null) {
            podcastFundings = new ArrayList<>();
        }
        this.podcastFundings.add(podcastFunding);
    }

    public List<PodcastLocation> getPodcastLocations() {
        return emptyListIfNull(podcastLocations);
    }

    public void addPodcastLocation(PodcastLocation podcastLocation) {
        if (podcastLocations == null) {
            podcastLocations = new ArrayList<>();
        }
        podcastLocations.add(podcastLocation);
    }

    public String getPodcastMedium() {
        return podcastMedium;
    }

    public void setPodcastMedium(String podcastMedium) {
        this.podcastMedium = podcastMedium;
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


    public List<PodcastRemoteItem> getPodcastPodrolls() {
        return emptyListIfNull(podcastPodrolls);
    }

    public void addPodcastPodroll(PodcastRemoteItem podcastPodroll) {
        if (podcastPodrolls == null) {
            podcastPodrolls = new ArrayList<>();
        }
        podcastPodrolls.add(podcastPodroll);
    }

    public Optional<PodcastRemoteItem> getPodcastPublisher() {
        return Optional.ofNullable(podcastPublisher);
    }

    public void setPodcastPublisher(PodcastRemoteItem podcastPublisher) {
        this.podcastPublisher = podcastPublisher;
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

    public Optional<PodcastUpdateFrequency> getPodcastUpdateFrequency() {
        return Optional.ofNullable(podcastUpdateFrequency);
    }

    public void setPodcastUpdateFrequency(PodcastUpdateFrequency podcastUpdateFrequency) {
        this.podcastUpdateFrequency = podcastUpdateFrequency;
    }

    public List<PodcastTrailer> getPodcastTrailers() {
            return emptyListIfNull(podcastTrailers);
    }

    public void addPodcastTrailer(PodcastTrailer podcastTrailer) {
        if (podcastTrailers == null) {
            podcastTrailers = new ArrayList<>();
        }
        podcastTrailers.add(podcastTrailer);
    }

    public List<PodcastLiveItem> getPodcastLiveItems() {
        return emptyListIfNull(podcastLiveItems);
    }

    public void addPodcastLiveItem(PodcastLiveItem podcastLiveItem) {
        if (podcastLiveItems == null) {
            podcastLiveItems = new ArrayList<>();
        }
        podcastLiveItems.add(podcastLiveItem);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PodcastChannel that = (PodcastChannel) o;
        return Objects.equals(getPodcastGuid(), that.getPodcastGuid()) &&
                Objects.equals(getPodcastLicense(), that.getPodcastLicense()) &&
                Objects.equals(getPodcastLocked(), that.getPodcastLocked()) &&
                Objects.equals(getPodcastBlocks(), that.getPodcastBlocks()) &&
                Objects.equals(getPodcastFundings(), that.getPodcastFundings()) &&
                Objects.equals(getPodcastLocations(), that.getPodcastLocations()) &&
                Objects.equals(getPodcastMedium(), that.getPodcastMedium()) &&
                Objects.equals(getPodcastValues(), that.getPodcastValues()) &&
                Objects.equals(getPodcastPodrolls(), that.getPodcastPodrolls()) &&
                Objects.equals(getPodcastPublisher(), that.getPodcastPublisher()) &&
                Objects.equals(getPodcastPersons(), that.getPodcastPersons()) &&
                Objects.equals(getPodcastUpdateFrequency(), that.getPodcastUpdateFrequency()) &&
                Objects.equals(getPodcastTrailers(), that.getPodcastTrailers()) &&
                Objects.equals(getPodcastLiveItems(), that.getPodcastLiveItems());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPodcastGuid(), getPodcastLicense(), getPodcastLocked(),
                getPodcastBlocks(), getPodcastFundings(), getPodcastLocations(), getPodcastMedium(),
                getPodcastValues(), getPodcastPodrolls(), getPodcastPublisher(), getPodcastPersons(),
                getPodcastUpdateFrequency(), getPodcastTrailers(), getPodcastLiveItems());
    }
}
