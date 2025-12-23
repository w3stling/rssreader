package com.apptasticsoftware.rssreader.module.opensearch;

import com.apptasticsoftware.rssreader.AbstractRssReader;
import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.module.opensearch.internal.OpenSearchChannelImpl;
import com.apptasticsoftware.rssreader.module.opensearch.internal.OpenSearchItemImpl;

public class OpenSearchFeedReader extends AbstractRssReader<OpenSearchChannel, OpenSearchItem> {

    @Override
    protected OpenSearchChannel createChannel(DateTimeParser dateTimeParser) {
        return new OpenSearchChannelImpl(dateTimeParser);
    }

    @Override
    protected OpenSearchItem createItem(DateTimeParser dateTimeParser) {
        return new OpenSearchItemImpl(dateTimeParser);
    }

    @Override
    protected void registerChannelTags() {
        super.registerChannelTags();
        OpenSearchExtensions.register(getFeedExtensionRegistry());
    }

}
