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
    protected PodcastChannel createChannel(DateTimeParser dateTimeParser) {
        return new PodcastChannel(dateTimeParser);
    }

    @Override
    protected PodcastItem createItem(DateTimeParser dateTimeParser) {
        return new PodcastItem(dateTimeParser);
    }

    @Override
    protected void registerChannelTags() {
        super.registerChannelTags();
        var registry = getFeedExtensionRegistry();
        PodcastExtensions.register(registry);
        ItunesExtensions.register(registry);
    }
}
