package com.apptasticsoftware.rssreader.internal;

import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.FeedItem;
import com.apptasticsoftware.rssreader.module.atom.AtomItemData;
import com.apptasticsoftware.rssreader.module.atom.internal.AtomItemDataImpl;
import com.apptasticsoftware.rssreader.module.dc.DcItemData;
import com.apptasticsoftware.rssreader.module.dc.internal.DcItemDataImpl;
import com.apptasticsoftware.rssreader.module.georss.GeoRssItemData;
import com.apptasticsoftware.rssreader.module.georss.internal.GeoRssItemDataImpl;
import com.apptasticsoftware.rssreader.module.itunes.ItunesItemData;
import com.apptasticsoftware.rssreader.module.itunes.internal.ItunesItemDataImpl;
import com.apptasticsoftware.rssreader.module.mediarss.MediaRssItemData;
import com.apptasticsoftware.rssreader.module.mediarss.internal.MediaRssItemDataImpl;
import com.apptasticsoftware.rssreader.module.podcast.PodcastItemData;
import com.apptasticsoftware.rssreader.module.podcast.internal.PodcastItemDataImpl;
import com.apptasticsoftware.rssreader.module.psc.PscItemData;
import com.apptasticsoftware.rssreader.module.psc.internal.PscItemDataImpl;
import com.apptasticsoftware.rssreader.module.slash.SlashItemData;
import com.apptasticsoftware.rssreader.module.slash.internal.SlashItemDataImpl;
import com.apptasticsoftware.rssreader.module.slash.internal.SlashItemDataProvider;
import com.apptasticsoftware.rssreader.module.wfw.WfwItemData;
import com.apptasticsoftware.rssreader.module.wfw.internal.WfwItemDataImpl;
import com.apptasticsoftware.rssreader.module.youtube.YoutubeItemData;
import com.apptasticsoftware.rssreader.module.youtube.internal.YoutubeItemDataImpl;

import java.util.Objects;
import com.apptasticsoftware.rssreader.module.atom.internal.AtomItemDataProvider;
import com.apptasticsoftware.rssreader.module.dc.internal.DcItemDataProvider;
import com.apptasticsoftware.rssreader.module.georss.internal.GeoRssItemDataProvider;
import com.apptasticsoftware.rssreader.module.itunes.internal.ItunesItemDataProvider;
import com.apptasticsoftware.rssreader.module.mediarss.internal.MediaRssItemDataProvider;
import com.apptasticsoftware.rssreader.module.podcast.internal.PodcastItemDataProvider;
import com.apptasticsoftware.rssreader.module.psc.internal.PscItemDataProvider;
import com.apptasticsoftware.rssreader.module.wfw.internal.WfwItemDataProvider;
import com.apptasticsoftware.rssreader.module.youtube.internal.YoutubeItemDataProvider;

public class FeedItemImpl extends ItemImpl implements FeedItem, AtomItemDataProvider, DcItemDataProvider, GeoRssItemDataProvider, ItunesItemDataProvider, MediaRssItemDataProvider, PodcastItemDataProvider, PscItemDataProvider, SlashItemDataProvider, WfwItemDataProvider, YoutubeItemDataProvider {
    private final AtomItemData atomItemData = new AtomItemDataImpl();
    private final DcItemData dcItemData = new DcItemDataImpl();
    private final GeoRssItemData geoRssItemData = new GeoRssItemDataImpl();
    private final ItunesItemData itunesItemData = new ItunesItemDataImpl();
    private final MediaRssItemData mediaRssItemData = new MediaRssItemDataImpl();
    private final PodcastItemData podcastItemData;
    private final PscItemData pscItemData = new PscItemDataImpl();
    private final SlashItemData slashItemData = new SlashItemDataImpl();
    private final WfwItemData wfwItemData = new WfwItemDataImpl();
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
    public AtomItemData atomItemData() {
        return atomItemData;
    }

    @Override
    public DcItemData dcItemData() {
        return dcItemData;
    }

    @Override
    public GeoRssItemData geoRssItemData() {
        return geoRssItemData;
    }

    @Override
    public ItunesItemData itunesItemData() {
        return itunesItemData;
    }

    @Override
    public MediaRssItemData mediaRssItemData() {
        return mediaRssItemData;
    }

    @Override
    public PodcastItemData podcastItemData() {
        return podcastItemData;
    }

    @Override
    public PscItemData pscItemData() {
        return pscItemData;
    }

    @Override
    public SlashItemData slashItemData() {
        return slashItemData;
    }

    @Override
    public WfwItemData wfwItemData() {
        return wfwItemData;
    }

    @Override
    public YoutubeItemData youtubeItemData() {
        return youtubeItemData;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof FeedItemImpl)) return false;
        if (!super.equals(o)) return false;
        FeedItemImpl feedItem = (FeedItemImpl) o;
        return Objects.equals(itunesItemData(), feedItem.itunesItemData()) && Objects.equals(mediaRssItemData(), feedItem.mediaRssItemData()) && Objects.equals(podcastItemData(), feedItem.podcastItemData()) && Objects.equals(pscItemData(), feedItem.pscItemData()) && Objects.equals(youtubeItemData(), feedItem.youtubeItemData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), itunesItemData(), mediaRssItemData(), podcastItemData(), pscItemData(), youtubeItemData());
    }
}
