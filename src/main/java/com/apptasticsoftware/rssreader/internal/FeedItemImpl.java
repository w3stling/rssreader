package com.apptasticsoftware.rssreader.internal;

import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.FeedChannel;
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
    private AtomItemData atomItemData;
    private DcItemData dcItemData;
    private GeoRssItemData geoRssItemData;
    private ItunesItemData itunesItemData;
    private MediaRssItemData mediaRssItemData;
    private PodcastItemData podcastItemData;
    private PscItemData pscItemData;
    private SlashItemData slashItemData;
    private WfwItemData wfwItemData;
    private YoutubeItemData youtubeItemData;

    /**
     * Constructor
     *
     * @param dateTimeParser timestamp parser
     */
    public FeedItemImpl(DateTimeParser dateTimeParser) {
        super(dateTimeParser);
    }

    @Override
    public FeedChannel getChannel() {
        var channel = super.getChannel();
        if (channel instanceof FeedChannel) {
            return (FeedChannel) channel;
        }
        return null;
    }

    @Override
    public boolean hasAtomItem() {
        return atomItemData != null;
    }

    @Override
    public AtomItemData atomItemData() {
        if (atomItemData == null) {
            atomItemData = new AtomItemDataImpl();
        }
        return atomItemData;
    }

    @Override
    public boolean hasDcItem() {
        return dcItemData != null;
    }

    @Override
    public DcItemData dcItemData() {
        if (dcItemData == null) {
            dcItemData = new DcItemDataImpl();
        }
        return dcItemData;
    }

    @Override
    public boolean hasGeoRssItem() {
        return geoRssItemData != null;
    }

    @Override
    public GeoRssItemData geoRssItemData() {
        if (geoRssItemData == null) {
            geoRssItemData = new GeoRssItemDataImpl();
        }
        return geoRssItemData;
    }

    @Override
    public boolean hasItunesItem() {
        return itunesItemData != null;
    }

    @Override
    public ItunesItemData itunesItemData() {
        if (itunesItemData == null) {
            itunesItemData = new ItunesItemDataImpl();
        }
        return itunesItemData;
    }

    @Override
    public boolean hasMediaRssItem() {
        return mediaRssItemData != null;
    }

    @Override
    public MediaRssItemData mediaRssItemData() {
        if (mediaRssItemData == null) {
            mediaRssItemData = new MediaRssItemDataImpl();
        }
        return mediaRssItemData;
    }

    @Override
    public boolean hasPodcastItem() {
        return podcastItemData != null;
    }

    @Override
    public PodcastItemData podcastItemData() {
        if (podcastItemData == null) {
            podcastItemData = new PodcastItemDataImpl(dateTimeParser);
        }
        return podcastItemData;
    }

    @Override
    public boolean hasPscItem() {
        return pscItemData != null;
    }

    @Override
    public PscItemData pscItemData() {
        if (pscItemData == null) {
            pscItemData = new PscItemDataImpl();
        }
        return pscItemData;
    }

    @Override
    public boolean hasSlashItem() {
        return slashItemData != null;
    }

    @Override
    public SlashItemData slashItemData() {
        if (slashItemData == null) {
            slashItemData = new SlashItemDataImpl();
        }
        return slashItemData;
    }

    @Override
    public boolean hasWfwItem() {
        return wfwItemData != null;
    }

    @Override
    public WfwItemData wfwItemData() {
        if (wfwItemData == null) {
            wfwItemData = new WfwItemDataImpl();
        }
        return wfwItemData;
    }

    @Override
    public boolean hasYoutubeItem() {
        return youtubeItemData != null;
    }

    @Override
    public YoutubeItemData youtubeItemData() {
        if (youtubeItemData == null) {
            youtubeItemData = new YoutubeItemDataImpl();
        }
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
