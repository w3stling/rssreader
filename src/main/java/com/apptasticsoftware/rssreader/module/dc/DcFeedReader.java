package com.apptasticsoftware.rssreader.module.dc;

import com.apptasticsoftware.rssreader.AbstractRssReader;
import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.module.dc.internal.DcChannelImpl;
import com.apptasticsoftware.rssreader.module.dc.internal.DcItemImpl;

public class DcFeedReader extends AbstractRssReader<DcChannel, DcItem> {

    @Override
    protected DcChannel createChannel(DateTimeParser dateTimeParser) {
        return new DcChannelImpl(dateTimeParser);
    }

    @Override
    protected DcItem createItem(DateTimeParser dateTimeParser) {
        return new DcItemImpl(dateTimeParser);
    }

    @Override
    protected void registerChannelTags() {
        super.registerChannelTags();
        var registry = getFeedExtensionRegistry();
        DcExtensions.register(registry);
    }
}
