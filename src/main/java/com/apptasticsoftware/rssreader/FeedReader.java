package com.apptasticsoftware.rssreader;

import com.apptasticsoftware.rssreader.internal.FeedChannelImpl;
import com.apptasticsoftware.rssreader.internal.FeedDataImpl;
import com.apptasticsoftware.rssreader.internal.FeedItemImpl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.stream.Stream;
import com.apptasticsoftware.rssreader.module.atom.AtomExtensions;
import com.apptasticsoftware.rssreader.module.dc.DcExtensions;
import com.apptasticsoftware.rssreader.module.georss.GeoRssExtensions;
import com.apptasticsoftware.rssreader.module.itunes.ItunesExtensions;
import com.apptasticsoftware.rssreader.module.mediarss.MediaRssExtensions;
import com.apptasticsoftware.rssreader.module.opensearch.OpenSearchExtensions;
import com.apptasticsoftware.rssreader.module.podcast.PodcastExtensions;
import com.apptasticsoftware.rssreader.module.psc.PscExtensions;
import com.apptasticsoftware.rssreader.module.slash.SlashExtensions;
import com.apptasticsoftware.rssreader.module.spotify.SpotifyExtensions;
import com.apptasticsoftware.rssreader.module.wfw.WfwExtensions;
import com.apptasticsoftware.rssreader.module.youtube.YoutubeExtensions;

public class FeedReader extends AbstractRssReader<FeedChannel, FeedItem> {

    @Override
    protected FeedChannel createChannel(DateTimeParser dateTimeParser) {
        return new FeedChannelImpl(dateTimeParser);
    }

    @Override
    protected FeedItem createItem(DateTimeParser dateTimeParser) {
        return new FeedItemImpl(dateTimeParser);
    }

    @Override
    protected void registerChannelTags() {
        super.registerChannelTags();
        var registry = getFeedExtensionRegistry();
        AtomExtensions.register(registry);
        DcExtensions.register(registry);
        GeoRssExtensions.register(registry);
        ItunesExtensions.register(registry);
        MediaRssExtensions.register(registry);
        OpenSearchExtensions.register(registry);
        PodcastExtensions.register(registry, dateTimeParser);
        PscExtensions.register(registry);
        SlashExtensions.register(registry);
        SpotifyExtensions.register(registry);
        WfwExtensions.register(registry);
        YoutubeExtensions.register(registry);
    }

    /**
     * Reads a feed from the given URL and returns a stream of {@link FeedData} records,
     * each combining the feed URL, channel, and item.
     *
     * @param url the URL of the feed
     * @return a stream of {@link FeedData}
     * @throws IOException if an I/O error occurs
     */
    public Stream<FeedData> readFeed(String url) throws IOException {
        return readFeedRecords(url).map(r -> new FeedDataImpl(r.getFeedUrl(), r.getChannel(), r.getItem()));
    }

    /**
     * Reads feeds from the given URLs and returns a stream of {@link FeedData} records,
     * each combining the feed URL, channel, and item.
     *
     * @param urls collection of URLs or file URIs
     * @return a stream of {@link FeedData}
     */
    public Stream<FeedData> readFeed(Collection<String> urls) {
        return readFeedRecords(urls).map(r -> new FeedDataImpl(r.getFeedUrl(), r.getChannel(), r.getItem()));
    }

    /**
     * Reads a feed from the given input stream and returns a stream of {@link FeedData} records,
     * each combining the feed URL, channel, and item.
     *
     * @param inputStream input stream containing the RSS feed
     * @return a stream of {@link FeedData}
     */
    public Stream<FeedData> readFeed(InputStream inputStream) {
        return readFeedRecords(inputStream).map(r -> new FeedDataImpl(r.getFeedUrl(), r.getChannel(), r.getItem()));
    }
}
