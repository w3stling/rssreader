package com.apptasticsoftware.rssreader.internal;

import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.FeedChannel;
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

public class FeedChannelImpl extends ChannelImpl implements FeedChannel {
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
    public DcChannelData getDcChannelData() {
        return dcChannelData;
    }

    @Override
    public GeoRssChannelData getGeoRssChannelData() {
        return geoRssChannelData;
    }

    @Override
    public ItunesChannelData getItunesChannelData() {
        return itunesChannelData;
    }

    @Override
    public MediaRssChannelData getMediaRssChannelData() {
        return mediaRssChannelData;
    }

    @Override
    public OpenSearchChannelData getOpenSearchChannelData() {
        return openSearchChannelData;
    }

    @Override
    public PodcastChannelData getPodcastChannelData() {
        return podcastChannelData;
    }

    @Override
    public SpotifyChannelData getSpotifyChannelData() {
        return spotifyChannelData;
    }

    @Override
    public YoutubeChannelData getYoutubeChannelData() {
        return youtubeChannelData;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof FeedChannelImpl)) return false;
        if (!super.equals(o)) return false;
        FeedChannelImpl that = (FeedChannelImpl) o;
        return Objects.equals(getItunesChannelData(), that.getItunesChannelData()) && Objects.equals(getMediaRssChannelData(), that.getMediaRssChannelData()) && Objects.equals(getOpenSearchChannelData(), that.getOpenSearchChannelData()) && Objects.equals(getPodcastChannelData(), that.getPodcastChannelData()) && Objects.equals(getYoutubeChannelData(), that.getYoutubeChannelData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getItunesChannelData(), getMediaRssChannelData(), getOpenSearchChannelData(), getPodcastChannelData(), getYoutubeChannelData());
    }
}
