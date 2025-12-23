package com.apptasticsoftware.rssreader.internal;

import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.FeedItem;
import com.apptasticsoftware.rssreader.module.itunes.ItunesItemData;
import com.apptasticsoftware.rssreader.module.itunes.internal.ItunesItemDataImpl;
import com.apptasticsoftware.rssreader.module.mediarss.MediaRssItemData;
import com.apptasticsoftware.rssreader.module.mediarss.internal.MediaRssItemDataImpl;
import com.apptasticsoftware.rssreader.module.podcast.PodcastItemData;
import com.apptasticsoftware.rssreader.module.podcast.internal.PodcastItemDataImpl;
import com.apptasticsoftware.rssreader.module.psc.PscItemData;
import com.apptasticsoftware.rssreader.module.psc.internal.PscItemDataImpl;
import com.apptasticsoftware.rssreader.module.spotify.SpotifyItemData;
import com.apptasticsoftware.rssreader.module.spotify.internal.SpotifyItemDataImpl;
import com.apptasticsoftware.rssreader.module.youtube.YoutubeItemData;
import com.apptasticsoftware.rssreader.module.youtube.internal.YoutubeItemDataImpl;

import java.util.Objects;

public class FeedItemImpl extends ItemImpl implements FeedItem {
    private final ItunesItemData itunesItemData = new ItunesItemDataImpl();
    private final MediaRssItemData mediaRssItemData = new MediaRssItemDataImpl();
    private final PodcastItemData podcastItemData;
    private final PscItemData pscItemData = new PscItemDataImpl();
    private final SpotifyItemData spotifyItemData = new SpotifyItemDataImpl();
    private final YoutubeItemData youtubeItemData = new YoutubeItemDataImpl();

    /**
     * Constructor
     *
     * @param dateTimeParser timestamp parser
     */
    public FeedItemImpl(DateTimeParser dateTimeParser) {
        super(dateTimeParser);
        podcastItemData = new PodcastItemDataImpl(dateTimeParser);
    }

    @Override
    public ItunesItemData getItunesItemData() {
        return itunesItemData;
    }

    @Override
    public MediaRssItemData getMediaRssItemData() {
        return mediaRssItemData;
    }

    @Override
    public PodcastItemData getPodcastItemData() {
        return podcastItemData;
    }

    @Override
    public PscItemData getPscItemData() {
        return pscItemData;
    }

    @Override
    public SpotifyItemData getSpotifyItemData() {
        return spotifyItemData;
    }

    @Override
    public YoutubeItemData getYoutubeItemData() {
        return youtubeItemData;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof FeedItemImpl)) return false;
        if (!super.equals(o)) return false;
        FeedItemImpl feedItem = (FeedItemImpl) o;
        return Objects.equals(getItunesItemData(), feedItem.getItunesItemData()) && Objects.equals(getMediaRssItemData(), feedItem.getMediaRssItemData()) && Objects.equals(getPodcastItemData(), feedItem.getPodcastItemData()) && Objects.equals(getPscItemData(), feedItem.getPscItemData()) && Objects.equals(getSpotifyItemData(), feedItem.getSpotifyItemData()) && Objects.equals(getYoutubeItemData(), feedItem.getYoutubeItemData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getItunesItemData(), getMediaRssItemData(), getPodcastItemData(), getPscItemData(), getSpotifyItemData(), getYoutubeItemData());
    }
}
