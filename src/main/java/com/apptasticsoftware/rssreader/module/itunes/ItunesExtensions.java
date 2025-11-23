package com.apptasticsoftware.rssreader.module.itunes;

import com.apptasticsoftware.rssreader.FeedExtensionRegistry;

import static com.apptasticsoftware.rssreader.util.Mapper.mapBoolean;
import static com.apptasticsoftware.rssreader.util.Mapper.mapInteger;

public class ItunesExtensions {

    public static void register(FeedExtensionRegistry<? extends ItunesChannel, ? extends ItunesItem> feedExtensionRegistry) {
        channelTagExtensions(feedExtensionRegistry);
        channelAttributeExtensions(feedExtensionRegistry);
        itemTagExtensions(feedExtensionRegistry);
        itemAttributesExtensions(feedExtensionRegistry);
    }

    private static void channelTagExtensions(FeedExtensionRegistry<? extends ItunesChannel, ? extends ItunesItem> extensions) {
        extensions.addChannelExtension("itunes:explicit", (i, v) -> mapBoolean(v, i::setItunesExplicit));
        extensions.addChannelExtension("itunes:author", ItunesChannel::setItunesAuthor);

        extensions.addChannelExtension("itunes:name", (i, v) -> {
            if (i.getItunesOwner().isEmpty())
                i.setItunesOwner(new ItunesOwner());
            i.getItunesOwner().ifPresent(a -> a.setName(v));
        });

        extensions.addChannelExtension("itunes:email", (i, v) -> {
            if (i.getItunesOwner().isEmpty())
                i.setItunesOwner(new ItunesOwner());
            i.getItunesOwner().ifPresent(a -> a.setEmail(v));
        });

        extensions.addChannelExtension("itunes:title", ItunesChannel::setItunesTitle);
        extensions.addChannelExtension("itunes:subtitle", ItunesChannel::setItunesSubtitle);
        extensions.addChannelExtension("itunes:summary", ItunesChannel::setItunesSummary);
        extensions.addChannelExtension("itunes:type", ItunesChannel::setItunesType);
        extensions.addChannelExtension("itunes:new-feed-url", ItunesChannel::setItunesNewFeedUrl);
        extensions.addChannelExtension("itunes:block", (i, v) -> mapBoolean(v, i::setItunesBlock));
        extensions.addChannelExtension("itunes:complete", (i, v) -> mapBoolean(v, i::setItunesComplete));
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
