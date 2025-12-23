package com.apptasticsoftware.rssreader.module.youtube;

import com.apptasticsoftware.rssreader.AbstractRssReader;
import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.module.mediarss.MediaRssExtensions;
import com.apptasticsoftware.rssreader.module.youtube.internal.YoutubeChannelImpl;
import com.apptasticsoftware.rssreader.module.youtube.internal.YoutubeItemImpl;

public class YoutubeFeedReader extends AbstractRssReader<YoutubeChannel, YoutubeItem> {

    @Override
    protected YoutubeChannel createChannel(DateTimeParser dateTimeParser) {
        return new YoutubeChannelImpl(dateTimeParser);
    }

    @Override
    protected YoutubeItem createItem(DateTimeParser dateTimeParser) {
        return new YoutubeItemImpl(dateTimeParser);
    }

    @Override
    protected void registerChannelTags() {
        super.registerChannelTags();
        var registry = getFeedExtensionRegistry();
        MediaRssExtensions.register(registry);
        YoutubeExtensions.register(registry);
    }
}
