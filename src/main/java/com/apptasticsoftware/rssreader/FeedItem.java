package com.apptasticsoftware.rssreader;

import com.apptasticsoftware.rssreader.module.atom.AtomItem;
import com.apptasticsoftware.rssreader.module.dc.DcItem;
import com.apptasticsoftware.rssreader.module.georss.GeoRssItem;
import com.apptasticsoftware.rssreader.module.itunes.ItunesItem;
import com.apptasticsoftware.rssreader.module.mediarss.MediaRssItem;
import com.apptasticsoftware.rssreader.module.opensearch.OpenSearchItem;
import com.apptasticsoftware.rssreader.module.podcast.PodcastItem;
import com.apptasticsoftware.rssreader.module.psc.PscItem;
import com.apptasticsoftware.rssreader.module.slash.SlashItem;
import com.apptasticsoftware.rssreader.module.spotify.SpotifyItem;
import com.apptasticsoftware.rssreader.module.wfw.WfwItem;
import com.apptasticsoftware.rssreader.module.youtube.YoutubeItem;

/**
 * Unified feed item interface combining {@link Item} with all supported namespace module extensions.
 * Used by {@link FeedReader} to expose data from Atom, DC, GeoRSS, iTunes, Media RSS,
 * OpenSearch, Podcast, PSC, Slash, Spotify, WFW, and YouTube namespaces in a single object.
 */
public interface FeedItem extends Item, AtomItem, DcItem, GeoRssItem, ItunesItem, MediaRssItem, OpenSearchItem, PodcastItem, PscItem, SlashItem, SpotifyItem, WfwItem, YoutubeItem {

    /**
     * Returns {@code true} if this item contains Atom namespace data.
     * Always check this before accessing any Atom-specific fields.
     */
    boolean hasAtomItem();

    /**
     * Returns {@code true} if this item contains Dublin Core (DC) namespace data.
     * Always check this before accessing any DC-specific fields.
     */
    boolean hasDcItem();

    /**
     * Returns {@code true} if this item contains GeoRSS namespace data.
     * Always check this before accessing any GeoRSS-specific fields.
     */
    boolean hasGeoRssItem();

    /**
     * Returns {@code true} if this item contains iTunes namespace data.
     * Always check this before accessing any iTunes-specific fields.
     */
    boolean hasItunesItem();

    /**
     * Returns {@code true} if this item contains Media RSS namespace data.
     * Always check this before accessing any Media RSS-specific fields.
     */
    boolean hasMediaRssItem();

    /**
     * Returns {@code true} if this item contains Podcast namespace data.
     * Always check this before accessing any Podcast-specific fields.
     */
    boolean hasPodcastItem();

    /**
     * Returns {@code true} if this item contains PSC (Podlove Simple Chapters) namespace data.
     * Always check this before accessing any PSC-specific fields.
     */
    boolean hasPscItem();

    /**
     * Returns {@code true} if this item contains Slash namespace data.
     * Always check this before accessing any Slash-specific fields.
     */
    boolean hasSlashItem();

    /**
     * Returns {@code true} if this item contains WFW (Well-Formed Web) namespace data.
     * Always check this before accessing any WFW-specific fields.
     */
    boolean hasWfwItem();

    /**
     * Returns {@code true} if this item contains YouTube namespace data.
     * Always check this before accessing any YouTube-specific fields.
     */
    boolean hasYoutubeItem();
}
