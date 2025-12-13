package com.apptasticsoftware.rssreader.module.spotify;

import com.apptasticsoftware.rssreader.FeedExtensionRegistry;

import static com.apptasticsoftware.rssreader.util.Mapper.mapInteger;

public class SpotifyExtensions {

    private SpotifyExtensions() {

    }

    public static void register(FeedExtensionRegistry<? extends SpotifyChannel, ? extends SpotifyItem> registry) {
        channelTagExtensions(registry);
        channelAttributeExtensions(registry);
    }

    private static void channelTagExtensions(FeedExtensionRegistry<? extends SpotifyChannel, ? extends SpotifyItem> registry) {
        registry.addChannelExtension("spotify:countryOfOrigin", SpotifyChannelData::setSpotifyCountryOfOrigin);
    }

    private static void channelAttributeExtensions(FeedExtensionRegistry<? extends SpotifyChannel, ? extends SpotifyItem> registry) {
        registry.addChannelExtension("spotify:limit", "recentCount", (c, v) -> mapInteger(v, c::setSpotifyLimitRecentCount));
    }
}
