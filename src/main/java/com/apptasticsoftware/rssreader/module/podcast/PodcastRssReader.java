package com.apptasticsoftware.rssreader.module.podcast;

import com.apptasticsoftware.rssreader.AbstractRssReader;
import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.module.itunes.*;

import java.net.http.HttpClient;

public class PodcastRssReader extends AbstractRssReader<PodcastChannel, PodcastItem> {

    /**
     * Constructor
     */
    public PodcastRssReader() {
        super();
    }

    /**
     * Constructor
     * @param httpClient http client
     */
    public PodcastRssReader(HttpClient httpClient) {
        super(httpClient);
    }

    @Override
    protected PodcastChannelImpl createChannel(DateTimeParser dateTimeParser) {
        return new PodcastChannelImpl(dateTimeParser);
    }

    @Override
    protected PodcastItemImpl createItem(DateTimeParser dateTimeParser) {
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
