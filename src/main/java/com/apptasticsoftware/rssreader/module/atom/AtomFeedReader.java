package com.apptasticsoftware.rssreader.module.atom;

import com.apptasticsoftware.rssreader.AbstractRssReader;
import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.module.atom.internal.AtomChannelImpl;
import com.apptasticsoftware.rssreader.module.atom.internal.AtomItemImpl;

import java.net.http.HttpClient;

/**
 * Atom feed reader with support for Atom feed extensions.
 */
public class AtomFeedReader extends AbstractRssReader<AtomChannel, AtomItem> {

    /**
     * Creates an {@code AtomFeedReader} with a default {@link HttpClient}.
     */
    public AtomFeedReader() {
        super();
    }

    /**
     * Creates an {@code AtomFeedReader} with the given {@link HttpClient}.
     *
     * @param httpClient the HTTP client to use for feed requests
     */
    public AtomFeedReader(HttpClient httpClient) {
        super(httpClient);
    }

    @Override
    protected AtomChannel createChannel(DateTimeParser dateTimeParser) {
        return new AtomChannelImpl(dateTimeParser);
    }

    @Override
    protected AtomItem createItem(DateTimeParser dateTimeParser) {
        return new AtomItemImpl(dateTimeParser);
    }

    @Override
    protected void registerChannelTags() {
        super.registerChannelTags();
        var registry = getFeedExtensionRegistry();
        AtomExtensions.register(registry);
    }
}
