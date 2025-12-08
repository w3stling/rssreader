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

    private static void channelAttributeExtensions(FeedExtensionRegistry<? extends ItunesChannel, ? extends ItunesItem> extensions) {
        extensions.addChannelExtension("itunes:image", "href", ItunesChannel::setItunesImage);
        extensions.addChannelExtension("itunes:category", "text", ItunesChannel::addItunesCategory);
    }

    private static void itemTagExtensions(FeedExtensionRegistry<? extends ItunesChannel, ? extends ItunesItem> extensions) {
        extensions.addItemExtension("itunes:duration", ItunesItem::setItunesDuration);
        extensions.addItemExtension("itunes:explicit", (i, v) -> mapBoolean(v, i::setItunesExplicit));
        extensions.addItemExtension("itunes:title", ItunesItem::setItunesTitle);
        extensions.addItemExtension("itunes:subtitle", ItunesItem::setItunesSubtitle);
        extensions.addItemExtension("itunes:summary", ItunesItem::setItunesSummary);
        extensions.addItemExtension("itunes:keywords", ItunesItem::setItunesKeywords);
        extensions.addItemExtension("itunes:episode", (i, v) -> mapInteger(v, i::setItunesEpisode));
        extensions.addItemExtension("itunes:season", (i, v) -> mapInteger(v, i::setItunesSeason));
        extensions.addItemExtension("itunes:episodeType", ItunesItem::setItunesEpisodeType);
        extensions.addItemExtension("itunes:block", (i, v) -> mapBoolean(v, i::setItunesBlock));
    }

    private static void itemAttributesExtensions(FeedExtensionRegistry<? extends ItunesChannel, ? extends ItunesItem> extensions) {
        extensions.addItemExtension("itunes:image", "href", ItunesItem::setItunesImage);
    }
}
