package com.apptasticsoftware.rssreader.module.psc;

import com.apptasticsoftware.rssreader.AbstractRssReader;
import com.apptasticsoftware.rssreader.DateTimeParser;

public class PscFeedReader extends AbstractRssReader<PscChannel, PscItem> {

    @Override
    protected PscChannel createChannel(DateTimeParser dateTimeParser) {
        return new PscChannelImpl(dateTimeParser);
    }

    @Override
    protected PscItem createItem(DateTimeParser dateTimeParser) {
        return new PscItemImpl(dateTimeParser);
    }

    @Override
    protected void registerChannelTags() {
        super.registerChannelTags();
        var registry = getFeedExtensionRegistry();
        PscExtensions.register(registry);
    }
}
