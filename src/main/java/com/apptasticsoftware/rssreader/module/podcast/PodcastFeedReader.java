package com.apptasticsoftware.rssreader.module.podcast;

import com.apptasticsoftware.rssreader.AbstractRssReader;
import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.module.itunes.*;
import com.apptasticsoftware.rssreader.module.podcast.internal.PodcastChannelImpl;
import com.apptasticsoftware.rssreader.module.podcast.internal.PodcastItemImpl;

import java.net.http.HttpClient;

public class PodcastFeedReader extends AbstractRssReader<PodcastChannel, PodcastItem> {

    /**
     * Constructor
     */
    public PodcastFeedReader() {
        super();
    }

    /**
     * Constructor
     * @param httpClient http client
     */
    public PodcastFeedReader(HttpClient httpClient) {
        super(httpClient);
    }

    @Override
    protected PodcastChannel createChannel(DateTimeParser dateTimeParser) {
        return new PodcastChannelImpl(dateTimeParser);
    }

    @Override
    protected PodcastItem createItem(DateTimeParser dateTimeParser) {
        return new PodcastItemImpl(dateTimeParser);
    }

    @Override
    protected void registerChannelTags() {
        super.registerChannelTags();
        var registry = getFeedExtensionRegistry();
        PodcastExtensions.register(registry, dateTimeParser);
        ItunesExtensions.register(registry);
    }
}
