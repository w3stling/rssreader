package com.apptasticsoftware.rssreader.module.podcast;

import com.apptasticsoftware.rssreader.FeedExtensionRegistry;

public class PodcastExtensions {

    public static void register(FeedExtensionRegistry<? extends PodcastChannel, ? extends PodcastItem> feedExtensionRegistry) {
        onChannelTag(feedExtensionRegistry);
        channelTagExtensions(feedExtensionRegistry);
        channelAttributeExtensions(feedExtensionRegistry);

        onItemTag(feedExtensionRegistry);
        itemTagExtensions(feedExtensionRegistry);
        itemAttributesExtensions(feedExtensionRegistry);
    }

    private static void onChannelTag(FeedExtensionRegistry<? extends PodcastChannel, ? extends PodcastItem> extensions) {

    }

    private static void channelTagExtensions(FeedExtensionRegistry<? extends PodcastChannel, ? extends PodcastItem> extensions) {
    }

    private static void channelAttributeExtensions(FeedExtensionRegistry<? extends PodcastChannel, ? extends PodcastItem> extensions) {
    }

    private static void onItemTag(FeedExtensionRegistry<? extends PodcastChannel, ? extends PodcastItem> extensions) {

    }

    private static void itemTagExtensions(FeedExtensionRegistry<? extends PodcastChannel, ? extends PodcastItem> extensions) {
    }

    private static void itemAttributesExtensions(FeedExtensionRegistry<? extends PodcastChannel, ? extends PodcastItem> extensions) {
    }
}
