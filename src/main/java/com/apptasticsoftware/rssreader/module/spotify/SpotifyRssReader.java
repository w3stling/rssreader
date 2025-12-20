package com.apptasticsoftware.rssreader.module.spotify;

import com.apptasticsoftware.rssreader.AbstractRssReader;
import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.module.itunes.ItunesExtensions;
import com.apptasticsoftware.rssreader.module.mediarss.MediaRssExtensions;
import com.apptasticsoftware.rssreader.module.psc.PscExtensions;

public class SpotifyRssReader extends AbstractRssReader<SpotifyChannel, SpotifyItem> {

    @Override
    protected SpotifyChannel createChannel(DateTimeParser dateTimeParser) {
        return new SpotifyChannelImpl(dateTimeParser);
    }

    @Override
    protected SpotifyItem createItem(DateTimeParser dateTimeParser) {
        return new SpotifyItemImpl(dateTimeParser);
    }

    @Override
    protected void registerChannelTags() {
        super.registerChannelTags();
        var registry = getFeedExtensionRegistry();
        ItunesExtensions.register(registry);
        MediaRssExtensions.register(registry);
        PscExtensions.register(registry);
        SpotifyExtensions.register(registry);
    }
}
