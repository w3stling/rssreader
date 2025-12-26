package com.apptasticsoftware.rssreader.module.georss;

import com.apptasticsoftware.rssreader.AbstractRssReader;
import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.module.georss.internal.GeoRssChannelImpl;
import com.apptasticsoftware.rssreader.module.georss.internal.GeoRssItemImpl;

import java.net.http.HttpClient;

public class GeoRssFeedReader extends AbstractRssReader<GeoRssChannel, GeoRssItem> {

    /**
     * Constructor
     */
    public GeoRssFeedReader() {
        super();
    }

    /**
     * Constructor
     * @param httpClient http client
     */
    public GeoRssFeedReader(HttpClient httpClient) {
        super(httpClient);
    }

    @Override
    protected GeoRssChannel createChannel(DateTimeParser dateTimeParser) {
        return new GeoRssChannelImpl(dateTimeParser);
    }

    @Override
    protected GeoRssItem createItem(DateTimeParser dateTimeParser) {
        return new GeoRssItemImpl(dateTimeParser);
    }

    @Override
    protected void registerChannelTags() {
        super.registerChannelTags();
        var registry = getFeedExtensionRegistry();
        GeoRssExtensions.register(registry);
    }
}
