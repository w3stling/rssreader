package com.apptasticsoftware.rssreader.module.itunes;

import com.apptasticsoftware.rssreader.FeedExtensionRegistry;

import static com.apptasticsoftware.rssreader.util.Mapper.mapBoolean;
import static com.apptasticsoftware.rssreader.util.Mapper.mapInteger;

public class ItunesExtensions {

    private ItunesExtensions() {

    }

    public static void register(FeedExtensionRegistry<? extends ItunesChannel, ? extends ItunesItem> registry) {
        channelTagExtensions(registry);
        channelAttributeExtensions(registry);
        itemTagExtensions(registry);
        itemAttributesExtensions(registry);
    }

    private static void channelTagExtensions(FeedExtensionRegistry<? extends ItunesChannel, ? extends ItunesItem> registry) {
        registry.addChannelExtension("itunes:explicit", (c, v) -> mapBoolean(v, c::setItunesExplicit));
        registry.addChannelExtension("itunes:author", ItunesChannel::setItunesAuthor);

        registry.addChannelExtension("itunes:name", (i, v) -> {
            if (i.getItunesOwner().isEmpty())
                i.setItunesOwner(new ItunesOwner());
            i.getItunesOwner().ifPresent(a -> a.setName(v));
        });

        registry.addChannelExtension("itunes:email", (i, v) -> {
            if (i.getItunesOwner().isEmpty())
                i.setItunesOwner(new ItunesOwner());
            i.getItunesOwner().ifPresent(a -> a.setEmail(v));
        });

        registry.addChannelExtension("itunes:title", ItunesChannel::setItunesTitle);
        registry.addChannelExtension("itunes:subtitle", ItunesChannel::setItunesSubtitle);
        registry.addChannelExtension("itunes:summary", ItunesChannel::setItunesSummary);
        registry.addChannelExtension("itunes:type", ItunesChannel::setItunesType);
        registry.addChannelExtension("itunes:new-feed-url", ItunesChannel::setItunesNewFeedUrl);
        registry.addChannelExtension("itunes:block", (c, v) -> mapBoolean(v, c::setItunesBlock));
        registry.addChannelExtension("itunes:complete", (c, v) -> mapBoolean(v, c::setItunesComplete));
    }

    private static void channelAttributeExtensions(FeedExtensionRegistry<? extends ItunesChannel, ? extends ItunesItem> registry) {
        registry.addChannelExtension("itunes:image", "href", ItunesChannel::setItunesImage);
        registry.addChannelExtension("itunes:category", "text", ItunesChannel::addItunesCategory);
    }

    private static void itemTagExtensions(FeedExtensionRegistry<? extends ItunesChannel, ? extends ItunesItem> registry) {
        registry.addItemExtension("itunes:duration", ItunesItem::setItunesDuration);
        registry.addItemExtension("itunes:explicit", (i, v) -> mapBoolean(v, i::setItunesExplicit));
        registry.addItemExtension("itunes:title", ItunesItem::setItunesTitle);
        registry.addItemExtension("itunes:subtitle", ItunesItem::setItunesSubtitle);
        registry.addItemExtension("itunes:summary", ItunesItem::setItunesSummary);
        registry.addItemExtension("itunes:keywords", ItunesItem::setItunesKeywords);
        registry.addItemExtension("itunes:episode", (i, v) -> mapInteger(v, i::setItunesEpisode));
        registry.addItemExtension("itunes:season", (i, v) -> mapInteger(v, i::setItunesSeason));
        registry.addItemExtension("itunes:episodeType", ItunesItem::setItunesEpisodeType);
        registry.addItemExtension("itunes:block", (i, v) -> mapBoolean(v, i::setItunesBlock));
    }

    private static void itemAttributesExtensions(FeedExtensionRegistry<? extends ItunesChannel, ? extends ItunesItem> registry) {
        registry.addItemExtension("itunes:image", "href", ItunesItem::setItunesImage);
    }
}
