package com.apptasticsoftware.rssreader.module.atom;

import com.apptasticsoftware.rssreader.FeedExtensionRegistry;

import java.util.List;

import static com.apptasticsoftware.rssreader.util.Mapper.mapLong;

/**
 * Utility class for registering Atom feed extensions.
 */
public class AtomExtensions {
    private static final List<String> CHANNEL_PATHS = List.of("/rss/channel/", "/feed/");
    private static final List<String> ITEM_PATHS = List.of("/rss/channel/item/", "/feed/entry/");

    private AtomExtensions() {
        // Prevent instantiation
    }

    /**
     * Registers Atom channel and item extensions with the provided registry.
     *
     * @param registry the feed extension registry
     */
    public static void register(FeedExtensionRegistry<? extends AtomChannel, ? extends AtomItem> registry) {
        channelTagExtensions(registry);
        itemTagExtensions(registry);
    }

    /**
     * Registers channel tag extensions.
     *
     * @param registry the feed extension registry
     */
    @SuppressWarnings("java:S1192")
    private static void channelTagExtensions(FeedExtensionRegistry<? extends AtomChannel, ? extends AtomItem> registry) {
        // atom:link - create a new AtomLink on tag start, then populate attributes
        registry.addOnChannelTag("atom:link", channel -> channel.addAtomLink(new AtomLink()));
        registry.addChannelExtension("atom:link", "href", (channel, value) -> lastAtomLink(channel).setHref(value));
        registry.addChannelExtension("atom:link", "rel",  (channel, value) -> lastAtomLink(channel).setRel(value));
        registry.addChannelExtension("atom:link", "type", (channel, value) -> lastAtomLink(channel).setType(value));
        registry.addChannelExtension("atom:link", "hreflang", (channel, value) -> lastAtomLink(channel).setHrefLang(value));
        registry.addChannelExtension("atom:link", "title", (channel, value) -> lastAtomLink(channel).setTitle(value));
        registry.addChannelExtension("atom:link", "length", (channel, value) -> mapLong(value, lastAtomLink(channel)::setLength));

        // atom:author - create a new AtomAuthor on tag start, then populate sub-tags
        registry.addOnChannelTag("atom:author", channel -> channel.addAtomAuthor(new AtomAuthor()));
        registry.addChannelExtension(CHANNEL_PATHS, "atom:author/atom:name",  (channel, value) -> lastAtomAuthor(channel).setName(value));
        registry.addChannelExtension(CHANNEL_PATHS, "atom:author/atom:uri",   (channel, value) -> lastAtomAuthor(channel).setUri(value));
        registry.addChannelExtension(CHANNEL_PATHS, "atom:author/atom:email", (channel, value) -> lastAtomAuthor(channel).setEmail(value));

        // atom:contributor - create a new AtomContributor on tag start, then populate sub-tags
        registry.addOnChannelTag("atom:contributor", channel -> channel.addAtomContributor(new AtomContributor()));
        registry.addChannelExtension(CHANNEL_PATHS, "atom:contributor/atom:name",  (channel, value) -> lastAtomContributor(channel).setName(value));
        registry.addChannelExtension(CHANNEL_PATHS, "atom:contributor/atom:uri",   (channel, value) -> lastAtomContributor(channel).setUri(value));
        registry.addChannelExtension(CHANNEL_PATHS, "atom:contributor/atom:email", (channel, value) -> lastAtomContributor(channel).setEmail(value));
    }

    /**
     * Registers item tag extensions.
     *
     * @param registry the feed extension registry
     */
    @SuppressWarnings("java:S1192")
    private static void itemTagExtensions(FeedExtensionRegistry<? extends AtomChannel, ? extends AtomItem> registry) {
        // atom:link - create a new AtomLink on tag start, then populate attributes
        registry.addOnItemTag("atom:link", item -> item.addAtomLink(new AtomLink()));
        registry.addItemExtension("atom:link", "href", (item, value) -> lastAtomLink(item).setHref(value));
        registry.addItemExtension("atom:link", "rel",  (item, value) -> lastAtomLink(item).setRel(value));
        registry.addItemExtension("atom:link", "type", (item, value) -> lastAtomLink(item).setType(value));
        registry.addItemExtension("atom:link", "hreflang", (item, value) -> lastAtomLink(item).setHrefLang(value));
        registry.addItemExtension("atom:link", "title", (item, value) -> lastAtomLink(item).setTitle(value));
        registry.addItemExtension("atom:link", "length", (item, value) -> mapLong(value, lastAtomLink(item)::setLength));

        // atom:author - create a new AtomAuthor on tag start, then populate sub-tags
        registry.addOnItemTag("atom:author", item -> item.addAtomAuthor(new AtomAuthor()));
        registry.addItemExtension(ITEM_PATHS, "atom:author/atom:name",  (item, value) -> lastAtomAuthor(item).setName(value));
        registry.addItemExtension(ITEM_PATHS, "atom:author/atom:uri",   (item, value) -> lastAtomAuthor(item).setUri(value));
        registry.addItemExtension(ITEM_PATHS, "atom:author/atom:email", (item, value) -> lastAtomAuthor(item).setEmail(value));

        // atom:contributor - create a new AtomContributor on tag start, then populate sub-tags
        registry.addOnItemTag("atom:contributor", item -> item.addAtomContributor(new AtomContributor()));
        registry.addItemExtension(ITEM_PATHS, "atom:contributor/atom:name",  (item, value) -> lastAtomContributor(item).setName(value));
        registry.addItemExtension(ITEM_PATHS, "atom:contributor/atom:uri",   (item, value) -> lastAtomContributor(item).setUri(value));
        registry.addItemExtension(ITEM_PATHS, "atom:contributor/atom:email", (item, value) -> lastAtomContributor(item).setEmail(value));
    }

    private static AtomLink lastAtomLink(AtomChannelData channel) {
        var links = channel.getAtomLinks();
        return links.get(links.size() - 1);
    }

    private static AtomLink lastAtomLink(AtomItemData item) {
        var links = item.getAtomLinks();
        return links.get(links.size() - 1);
    }

    private static AtomAuthor lastAtomAuthor(AtomChannelData channel) {
        var authors = channel.getAtomAuthors();
        return authors.get(authors.size() - 1);
    }

    private static AtomAuthor lastAtomAuthor(AtomItemData item) {
        var authors = item.getAtomAuthors();
        return authors.get(authors.size() - 1);
    }

    private static AtomContributor lastAtomContributor(AtomChannelData channel) {
        var contributors = channel.getAtomContributors();
        return contributors.get(contributors.size() - 1);
    }

    private static AtomContributor lastAtomContributor(AtomItemData item) {
        var contributors = item.getAtomContributors();
        return contributors.get(contributors.size() - 1);
    }
}
