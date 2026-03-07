package com.apptasticsoftware.rssreader.internal;

import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.FeedChannel;
import com.apptasticsoftware.rssreader.module.atom.AtomChannelData;
import com.apptasticsoftware.rssreader.module.atom.internal.AtomChannelDataImpl;
import com.apptasticsoftware.rssreader.module.dc.DcChannelData;
import com.apptasticsoftware.rssreader.module.dc.internal.DcChannelDataImpl;
import com.apptasticsoftware.rssreader.module.georss.GeoRssChannelData;
import com.apptasticsoftware.rssreader.module.georss.internal.GeoRssChannelDataImpl;
import com.apptasticsoftware.rssreader.module.itunes.ItunesChannelData;
import com.apptasticsoftware.rssreader.module.itunes.internal.ItunesChannelDataImpl;
import com.apptasticsoftware.rssreader.module.mediarss.MediaRssChannelData;
import com.apptasticsoftware.rssreader.module.mediarss.internal.MediaRssChannelDataImpl;
import com.apptasticsoftware.rssreader.module.opensearch.OpenSearchChannelData;
import com.apptasticsoftware.rssreader.module.opensearch.internal.OpenSearchChannelDataImpl;
import com.apptasticsoftware.rssreader.module.podcast.PodcastChannelData;
import com.apptasticsoftware.rssreader.module.podcast.internal.PodcastChannelDataImpl;
import com.apptasticsoftware.rssreader.module.spotify.SpotifyChannelData;
import com.apptasticsoftware.rssreader.module.spotify.internal.SpotifyChannelDataImpl;
import com.apptasticsoftware.rssreader.module.youtube.YoutubeChannelData;
import com.apptasticsoftware.rssreader.module.youtube.internal.YoutubeChannelDataImpl;

import java.util.Objects;
import com.apptasticsoftware.rssreader.module.atom.internal.AtomChannelDataProvider;
import com.apptasticsoftware.rssreader.module.dc.internal.DcChannelDataProvider;
import com.apptasticsoftware.rssreader.module.georss.internal.GeoRssChannelDataProvider;
import com.apptasticsoftware.rssreader.module.itunes.internal.ItunesChannelDataProvider;
import com.apptasticsoftware.rssreader.module.mediarss.internal.MediaRssChannelDataProvider;
import com.apptasticsoftware.rssreader.module.opensearch.internal.OpenSearchChannelDataProvider;
import com.apptasticsoftware.rssreader.module.podcast.internal.PodcastChannelDataProvider;
import com.apptasticsoftware.rssreader.module.spotify.internal.SpotifyChannelDataProvider;
import com.apptasticsoftware.rssreader.module.youtube.internal.YoutubeChannelDataProvider;

public class FeedChannelImpl extends ChannelImpl implements FeedChannel, AtomChannelDataProvider, DcChannelDataProvider, GeoRssChannelDataProvider, ItunesChannelDataProvider, MediaRssChannelDataProvider, OpenSearchChannelDataProvider, PodcastChannelDataProvider, SpotifyChannelDataProvider, YoutubeChannelDataProvider {
    private final AtomChannelData atomChannelData = new AtomChannelDataImpl();
    private final DcChannelData dcChannelData = new DcChannelDataImpl();
    private final GeoRssChannelData geoRssChannelData = new GeoRssChannelDataImpl();
    private final ItunesChannelData itunesChannelData = new ItunesChannelDataImpl();
    private final MediaRssChannelData mediaRssChannelData = new MediaRssChannelDataImpl();
    private final OpenSearchChannelData openSearchChannelData = new OpenSearchChannelDataImpl();
    private final PodcastChannelData podcastChannelData = new PodcastChannelDataImpl();
    private final SpotifyChannelData spotifyChannelData = new SpotifyChannelDataImpl();
    private final YoutubeChannelData youtubeChannelData = new YoutubeChannelDataImpl();

    /**
     * Constructor
     *
     * @param dateTimeParser timestamp parser
     */
    public FeedChannelImpl(DateTimeParser dateTimeParser) {
        super(dateTimeParser);
    }

    @Override
    public AtomChannelData atomChannelData() {
        return atomChannelData;
    }

    @Override
    public DcChannelData dcChannelData() {
        return dcChannelData;
    }

    @Override
    public GeoRssChannelData geoRssChannelData() {
        return geoRssChannelData;
    }

    @Override
    public ItunesChannelData itunesChannelData() {
        return itunesChannelData;
    }

    @Override
    public MediaRssChannelData mediaRssChannelData() {
        return mediaRssChannelData;
    }

    @Override
    public OpenSearchChannelData openSearchChannelData() {
        return openSearchChannelData;
    }

    @Override
    public PodcastChannelData podcastChannelData() {
        return podcastChannelData;
    }

    @Override
    public SpotifyChannelData spotifyChannelData() {
        return spotifyChannelData;
    }

    @Override
    public YoutubeChannelData youtubeChannelData() {
        return youtubeChannelData;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof FeedChannelImpl)) return false;
        if (!super.equals(o)) return false;
        FeedChannelImpl that = (FeedChannelImpl) o;
        return Objects.equals(itunesChannelData(), that.itunesChannelData()) && Objects.equals(mediaRssChannelData(), that.mediaRssChannelData()) && Objects.equals(openSearchChannelData(), that.openSearchChannelData()) && Objects.equals(podcastChannelData(), that.podcastChannelData()) && Objects.equals(youtubeChannelData(), that.youtubeChannelData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), itunesChannelData(), mediaRssChannelData(), openSearchChannelData(), podcastChannelData(), youtubeChannelData());
    }
}
