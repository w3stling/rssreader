package com.apptasticsoftware.rssreader.module.podcast;

import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.module.itunes.ItunesItemImpl;

import java.util.Objects;

public class PodcastItemImpl extends ItunesItemImpl implements PodcastItem {
    private final PodcastItemDataImpl data;

    /**
     * Constructor
     *
     * @param dateTimeParser timestamp parser
     */
    public PodcastItemImpl(DateTimeParser dateTimeParser) {
        super(dateTimeParser);
        data = new PodcastItemDataImpl(dateTimeParser);
    }

    @Override
    public PodcastItemData getPodcastItemData() {
        return data;
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
