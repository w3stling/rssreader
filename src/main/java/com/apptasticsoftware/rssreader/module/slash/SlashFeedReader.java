package com.apptasticsoftware.rssreader.module.slash;

import com.apptasticsoftware.rssreader.AbstractRssReader;
import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.module.slash.internal.SlashChannelImpl;
import com.apptasticsoftware.rssreader.module.slash.internal.SlashItemImpl;

/**
 * RSS feed reader with Slash module extensions.
 * Provides parsing of RSS feeds that include Slash-specific metadata for items.
 *
 * @see SlashItem for item-level Slash properties
 * @see SlashChannel for channel-level Slash properties
 */
public class SlashFeedReader extends AbstractRssReader<SlashChannel, SlashItem> {

    @Override
    protected SlashChannel createChannel(DateTimeParser dateTimeParser) {
        return new SlashChannelImpl(dateTimeParser);
    }

    @Override
    protected SlashItem createItem(DateTimeParser dateTimeParser) {
        return new SlashItemImpl(dateTimeParser);
    }

    @Override
    protected void registerChannelTags() {
        super.registerChannelTags();
        var registry = getFeedExtensionRegistry();
        SlashExtensions.register(registry);
    }
}
