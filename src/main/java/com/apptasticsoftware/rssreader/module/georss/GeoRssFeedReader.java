package com.apptasticsoftware.rssreader.module.georss;

import com.apptasticsoftware.rssreader.AbstractRssReader;
import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.module.georss.internal.GeoRssChannelImpl;
import com.apptasticsoftware.rssreader.module.georss.internal.GeoRssItemImpl;

import java.net.http.HttpClient;

/**
 * RSS reader implementation supporting GeoRSS extensions.
 */
public class GeoRssFeedReader extends AbstractRssReader<GeoRssChannel, GeoRssItem> {

    /**
     * Constructs a GeoRssFeedReader with default HTTP client.
     */
    public GeoRssFeedReader() {
        super();
    }

    /**
     * Constructs a GeoRssFeedReader with the specified HTTP client.
     *
     * @param httpClient the HTTP client to use
     */
    public GeoRssFeedReader(HttpClient httpClient) {
        super(httpClient);
    }

    /**
     * Creates a GeoRSS channel implementation.
     *
     * @param dateTimeParser the date time parser
     * @return a new GeoRSS channel
     */
    @Override
    protected GeoRssChannel createChannel(DateTimeParser dateTimeParser) {
        return new GeoRssChannelImpl(dateTimeParser);
    }

    /**
     * Creates a GeoRSS item implementation.
     *
     * @param dateTimeParser the date time parser
     * @return a new GeoRSS item
     */
    @Override
    protected GeoRssItem createItem(DateTimeParser dateTimeParser) {
        return new GeoRssItemImpl(dateTimeParser);
    }

    /**
     * Registers GeoRSS channel tags and extensions.
     */
    @Override
    protected void registerChannelTags() {
        super.registerChannelTags();
        var registry = getFeedExtensionRegistry();
        GeoRssExtensions.register(registry);
    }
}
