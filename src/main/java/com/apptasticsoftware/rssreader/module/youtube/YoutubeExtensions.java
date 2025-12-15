package com.apptasticsoftware.rssreader.module.youtube;

import com.apptasticsoftware.rssreader.FeedExtensionRegistry;

public class YoutubeExtensions {

    private YoutubeExtensions() {

    }

    public static void register(FeedExtensionRegistry<? extends YoutubeChannel, ? extends YoutubeItem> registry) {
        channelTagExtensions(registry);
        itemTagExtensions(registry);
    }

    private static void channelTagExtensions(FeedExtensionRegistry<? extends YoutubeChannel, ? extends YoutubeItem> registry) {
        registry.addChannelExtension("yt:channelId", YoutubeChannel::setYoutubeChannelId);
    }

    private static void itemTagExtensions(FeedExtensionRegistry<? extends YoutubeChannel, ? extends YoutubeItem> registry) {
        registry.addItemExtension("yt:channelId", YoutubeItem::setYoutubeChannelId);
        registry.addItemExtension("yt:videoId", YoutubeItem::setYoutubeVideoId);
    }
}
