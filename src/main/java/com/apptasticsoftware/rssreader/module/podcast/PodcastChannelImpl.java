package com.apptasticsoftware.rssreader.module.podcast;

import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.module.itunes.ItunesChannelImpl;

import java.util.Objects;

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

    @Override
    public PodcastChannelData getPodcastChannelData() {
        return data;
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
                Objects.equals(getPodcastTrailers(), that.getPodcastTrailers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPodcastGuid(), getPodcastLicense(), getPodcastLocked(),
                getPodcastBlocks(), getPodcastFundings(), getPodcastLocations(), getPodcastMedium(),
                getPodcastValues(), getPodcastPodrolls(), getPodcastPublisher(), getPodcastPersons(),
                getPodcastUpdateFrequency(), getPodcastTrailers());
    }
}
