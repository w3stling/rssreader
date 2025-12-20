package com.apptasticsoftware.rssreader.module.all;

import com.apptasticsoftware.rssreader.AbstractRssReader;
import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.module.itunes.ItunesExtensions;
import com.apptasticsoftware.rssreader.module.mediarss.MediaRssExtensions;
import com.apptasticsoftware.rssreader.module.opensearch.OpenSearchExtensions;
import com.apptasticsoftware.rssreader.module.podcast.PodcastExtensions;
import com.apptasticsoftware.rssreader.module.psc.PscExtensions;
import com.apptasticsoftware.rssreader.module.youtube.YoutubeExtensions;

public class FeedReader extends AbstractRssReader<FeedChannel, FeedItem> {

    @Override
    protected FeedChannel createChannel(DateTimeParser dateTimeParser) {
        return new FeedChannelImpl(dateTimeParser);
    }

    @Override
    protected FeedItem createItem(DateTimeParser dateTimeParser) {
        return new FeedItemImpl(dateTimeParser);
    }

    @Override
    protected void registerChannelTags() {
        super.registerChannelTags();
        var registry = getFeedExtensionRegistry();
        ItunesExtensions.register(registry);
        MediaRssExtensions.register(registry);
        OpenSearchExtensions.register(registry);
        PodcastExtensions.register(registry, dateTimeParser);
        PscExtensions.register(registry);
        YoutubeExtensions.register(registry);
    }
}
