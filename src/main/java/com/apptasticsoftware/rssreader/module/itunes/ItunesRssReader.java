package com.apptasticsoftware.rssreader.module.itunes;

import com.apptasticsoftware.rssreader.AbstractRssReader;

/**
 * Class for reading podcast (itunes) feeds.
 */
public class ItunesRssReader extends AbstractRssReader<ItunesChannel, ItunesItem> {

    @Override
    protected void registerChannelTags() {
        super.registerChannelTags();
        addChannelExtension("itunes:explicit", (i, v) -> mapBoolean(v, i::setItunesExplicit));
        addChannelExtension("itunes:author", ItunesChannel::setItunesAuthor);

        addChannelExtension("itunes:name", (i, v) -> {
            if (i.getItunesOwner().isEmpty())
                i.setItunesOwner(new ItunesOwner());
            i.getItunesOwner().ifPresent(a -> a.setName(v));
        });

        addChannelExtension("itunes:email", (i, v) -> {
            if (i.getItunesOwner().isEmpty())
                i.setItunesOwner(new ItunesOwner());
            i.getItunesOwner().ifPresent(a -> a.setEmail(v));
        });

        addChannelExtension("itunes:title", ItunesChannel::setItunesTitle);
        addChannelExtension("itunes:subtitle", ItunesChannel::setItunesSubtitle);
        addChannelExtension("itunes:summary", ItunesChannel::setItunesSummary);
        addChannelExtension("itunes:type", ItunesChannel::setItunesType);
        addChannelExtension("itunes:new-feed-url", ItunesChannel::setItunesNewFeedUrl);
        addChannelExtension("itunes:block", (i, v) -> mapBoolean(v, i::setItunesBlock));
        addChannelExtension("itunes:complete", (i, v) -> mapBoolean(v, i::setItunesComplete));
    }

    @Override
    protected void registerChannelAttributes() {
        super.registerChannelAttributes();
        addChannelExtension("itunes:image", "href", ItunesChannel::setItunesImage);
        addChannelExtension("itunes:category", "text", ItunesChannel::addItunesCategory);
    }

    @Override
    protected void registerItemTags() {
        super.registerItemTags();
        addItemExtension("itunes:duration", ItunesItem::setItunesDuration);
        addItemExtension("itunes:explicit", (i, v) -> mapBoolean(v, i::setItunesExplicit));
        addItemExtension("itunes:title", ItunesItem::setItunesTitle);
        addItemExtension("itunes:subtitle", ItunesItem::setItunesSubtitle);
        addItemExtension("itunes:summary", ItunesItem::setItunesSummary);
        addItemExtension("itunes:keywords", ItunesItem::setItunesKeywords);
        addItemExtension("itunes:episode", (i, v) -> mapInteger(v, i::setItunesEpisode));
        addItemExtension("itunes:season", (i, v) -> mapInteger(v, i::setItunesSeason));
        addItemExtension("itunes:episodeType", ItunesItem::setItunesEpisodeType);
        addItemExtension("itunes:block", (i, v) -> mapBoolean(v, i::setItunesBlock));
        addItemExtension("itunes:image", "href", ItunesItem::setItunesImage);
    }

    @Override
    protected ItunesChannel createChannel() {
        return new ItunesChannel(getDateTimeParser());
    }

    @Override
    protected ItunesItem createItem() {
        return new ItunesItem(getDateTimeParser());
    }
}
