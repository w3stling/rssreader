package com.apptasticsoftware.rssreader;

import com.apptasticsoftware.rssreader.module.atom.AtomChannel;
import com.apptasticsoftware.rssreader.module.dc.DcChannel;
import com.apptasticsoftware.rssreader.module.georss.GeoRssChannel;
import com.apptasticsoftware.rssreader.module.itunes.ItunesChannel;
import com.apptasticsoftware.rssreader.module.mediarss.MediaRssChannel;
import com.apptasticsoftware.rssreader.module.opensearch.OpenSearchChannel;
import com.apptasticsoftware.rssreader.module.podcast.PodcastChannel;
import com.apptasticsoftware.rssreader.module.psc.PscChannel;
import com.apptasticsoftware.rssreader.module.slash.SlashChannel;
import com.apptasticsoftware.rssreader.module.spotify.SpotifyChannel;
import com.apptasticsoftware.rssreader.module.wfw.WfwChannel;
import com.apptasticsoftware.rssreader.module.youtube.YoutubeChannel;

/**
 * Unified feed channel interface combining {@link Channel} with all supported namespace module extensions.
 * Used by {@link FeedReader} to expose data from Atom, DC, GeoRSS, iTunes, Media RSS,
 * OpenSearch, Podcast, PSC, Slash, Spotify, WFW, and YouTube namespaces in a single object.
 */
public interface FeedChannel extends Channel, AtomChannel, DcChannel, GeoRssChannel, ItunesChannel, MediaRssChannel, OpenSearchChannel, PodcastChannel, PscChannel, SlashChannel, SpotifyChannel, WfwChannel, YoutubeChannel {

    /**
     * Returns {@code true} if this channel contains Atom namespace data.
     * Always check this before accessing any Atom-specific fields.
     */
    boolean hasAtomChannel();

    /**
     * Returns {@code true} if this channel contains Dublin Core (DC) namespace data.
     * Always check this before accessing any DC-specific fields.
     */
    boolean hasDcChannel();

    /**
     * Returns {@code true} if this channel contains GeoRSS namespace data.
     * Always check this before accessing any GeoRSS-specific fields.
     */
    boolean hasGeoRssChannel();

    /**
     * Returns {@code true} if this channel contains iTunes namespace data.
     * Always check this before accessing any iTunes-specific fields.
     */
    boolean hasItunesChannel();

    /**
     * Returns {@code true} if this channel contains Media RSS namespace data.
     * Always check this before accessing any Media RSS-specific fields.
     */
    boolean hasMediaRssChannel();

    /**
     * Returns {@code true} if this channel contains OpenSearch namespace data.
     * Always check this before accessing any OpenSearch-specific fields.
     */
    boolean hasOpenSearchChannel();

    /**
     * Returns {@code true} if this channel contains Podcast namespace data.
     * Always check this before accessing any Podcast-specific fields.
     */
    boolean hasPodcastChannel();

    /**
     * Returns {@code true} if this channel contains Spotify namespace data.
     * Always check this before accessing any Spotify-specific fields.
     */
    boolean hasSpotifyChannel();

    /**
     * Returns {@code true} if this channel contains YouTube namespace data.
     * Always check this before accessing any YouTube-specific fields.
     */
    boolean hasYoutubeChannel();
}
