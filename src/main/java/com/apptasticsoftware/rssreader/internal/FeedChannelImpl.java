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
    private AtomChannelData atomChannelData;
    private DcChannelData dcChannelData;
    private GeoRssChannelData geoRssChannelData;
    private ItunesChannelData itunesChannelData;
    private MediaRssChannelData mediaRssChannelData;
    private OpenSearchChannelData openSearchChannelData;
    private PodcastChannelData podcastChannelData;
    private SpotifyChannelData spotifyChannelData;
    private YoutubeChannelData youtubeChannelData;

    /**
     * Constructor
     *
     * @param dateTimeParser timestamp parser
     */
    public FeedChannelImpl(DateTimeParser dateTimeParser) {
        super(dateTimeParser);
    }

    @Override
    public boolean hasAtomChannel() {
        return atomChannelData != null;
    }

    @Override
    public AtomChannelData atomChannelData() {
        if  (atomChannelData == null) {
            atomChannelData = new AtomChannelDataImpl();
        }
        return atomChannelData;
    }

    @Override
    public boolean hasDcChannel() {
        return dcChannelData != null;
    }

    @Override
    public DcChannelData dcChannelData() {
        if (dcChannelData == null) {
            dcChannelData = new DcChannelDataImpl();
        }
        return dcChannelData;
    }

    @Override
    public boolean hasGeoRssChannel() {
        return geoRssChannelData != null;
    }

    @Override
    public GeoRssChannelData geoRssChannelData() {
        if (geoRssChannelData == null) {
            geoRssChannelData = new GeoRssChannelDataImpl();
        }
        return geoRssChannelData;
    }

    @Override
    public boolean hasItunesChannel() {
        return itunesChannelData != null;
    }

    @Override
    public ItunesChannelData itunesChannelData() {
        if (itunesChannelData == null) {
            itunesChannelData = new ItunesChannelDataImpl();
        }
        return itunesChannelData;
    }

    @Override
    public boolean hasMediaRssChannel() {
        return mediaRssChannelData != null;
    }

    @Override
    public MediaRssChannelData mediaRssChannelData() {
        if (mediaRssChannelData == null) {
            mediaRssChannelData = new MediaRssChannelDataImpl();
        }
        return mediaRssChannelData;
    }

    @Override
    public boolean hasOpenSearchChannel() {
        return openSearchChannelData != null;
    }

    @Override
    public OpenSearchChannelData openSearchChannelData() {
        if (openSearchChannelData == null) {
            openSearchChannelData = new OpenSearchChannelDataImpl();
        }
        return openSearchChannelData;
    }

    @Override
    public boolean hasPodcastChannel() {
        return podcastChannelData != null;
    }

    @Override
    public PodcastChannelData podcastChannelData() {
        if (podcastChannelData == null) {
            podcastChannelData = new PodcastChannelDataImpl();
        }
        return podcastChannelData;
    }

    @Override
    public boolean hasSpotifyChannel() {
        return spotifyChannelData != null;
    }

    @Override
    public SpotifyChannelData spotifyChannelData() {
        if (spotifyChannelData == null) {
            spotifyChannelData = new SpotifyChannelDataImpl();
        }
        return spotifyChannelData;
    }

    @Override
    public boolean hasYoutubeChannel() {
        return youtubeChannelData != null;
    }

    @Override
    public YoutubeChannelData youtubeChannelData() {
        if (youtubeChannelData == null) {
            youtubeChannelData = new YoutubeChannelDataImpl();
        }
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
