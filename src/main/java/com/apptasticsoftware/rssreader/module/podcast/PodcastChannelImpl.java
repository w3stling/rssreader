package com.apptasticsoftware.rssreader.module.podcast;

import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.module.itunes.ItunesChannelImpl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PodcastChannelImpl extends ItunesChannelImpl implements PodcastChannel {
    private final PodcastChannelDataImpl data = new PodcastChannelDataImpl();

    /**
     * Constructor
     *
     * @param dateTimeParser timestamp parser
     */
    public PodcastChannelImpl(DateTimeParser dateTimeParser) {
        super(dateTimeParser);
    }

    public String getPodcastGuid() {
        return data.getPodcastGuid();
    }

    public void setPodcastGuid(String podcastGuid) {
        data.setPodcastGuid(podcastGuid);
    }

    public Optional<PodcastLicense> getPodcastLicense() {
        return data.getPodcastLicense();
    }

    public void setPodcastLicense(PodcastLicense podcastLicense) {
        data.setPodcastLicense(podcastLicense);
    }

    public Optional<PodcastLocked> getPodcastLocked() {
        return data.getPodcastLocked();
    }

    public void setPodcastLocked(PodcastLocked podcastLocked) {
        data.setPodcastLocked(podcastLocked);
    }

    public List<PodcastBlock> getPodcastBlocks() {
        return data.getPodcastBlocks();
    }

    public void addPodcastBlock(PodcastBlock podcastBlock) {
        data.addPodcastBlock(podcastBlock);
    }

    public List<PodcastFunding> getPodcastFundings() {
        return data.getPodcastFundings();
    }

    public void addPodcastFunding(PodcastFunding podcastFunding) {
        data.addPodcastFunding(podcastFunding);
    }

    public List<PodcastLocation> getPodcastLocations() {
        return data.getPodcastLocations();
    }

    public void addPodcastLocation(PodcastLocation podcastLocation) {
        data.addPodcastLocation(podcastLocation);
    }

    public String getPodcastMedium() {
        return data.getPodcastMedium();
    }

    public void setPodcastMedium(String podcastMedium) {
        data.setPodcastMedium(podcastMedium);
    }

    public List<PodcastValue> getPodcastValues() {
        return data.getPodcastValues();
    }

    public void addPodcastValue(PodcastValue podcastValue) {
        data.addPodcastValue(podcastValue);
    }


    public List<PodcastRemoteItem> getPodcastPodrolls() {
        return data.getPodcastPodrolls();
    }

    public void addPodcastPodroll(PodcastRemoteItem podcastPodroll) {
        data.addPodcastPodroll(podcastPodroll);
    }

    public Optional<PodcastRemoteItem> getPodcastPublisher() {
        return data.getPodcastPublisher();
    }

    public void setPodcastPublisher(PodcastRemoteItem podcastPublisher) {
        data.setPodcastPublisher(podcastPublisher);
    }

    public List<PodcastPerson> getPodcastPersons() {
        return data.getPodcastPersons();
    }

    public void addPodcastPerson(PodcastPerson podcastPerson) {
        data.addPodcastPerson(podcastPerson);
    }

    public Optional<PodcastUpdateFrequency> getPodcastUpdateFrequency() {
        return data.getPodcastUpdateFrequency();
    }

    public void setPodcastUpdateFrequency(PodcastUpdateFrequency podcastUpdateFrequency) {
        data.setPodcastUpdateFrequency(podcastUpdateFrequency);
    }

    public List<PodcastTrailer> getPodcastTrailers() {
        return data.getPodcastTrailers();
    }

    public void addPodcastTrailer(PodcastTrailer podcastTrailer) {
        data.addPodcastTrailer(podcastTrailer);
    }

    public List<PodcastLiveItem> getPodcastLiveItems() {
        return data.getPodcastLiveItems();
    }

    public void addPodcastLiveItem(PodcastLiveItem podcastLiveItem) {
        data.addPodcastLiveItem(podcastLiveItem);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PodcastChannelImpl that = (PodcastChannelImpl) o;
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
