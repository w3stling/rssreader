package com.apptasticsoftware.rssreader.module.slash;

import com.apptasticsoftware.rssreader.FeedExtensionRegistry;

import static com.apptasticsoftware.rssreader.util.Mapper.mapInteger;

/**
 * Utility class for registering Slash RSS module extensions.
 * Registers handlers for Slash-specific XML elements during RSS feed parsing.
 */
public class SlashExtensions {

    private SlashExtensions() {
        // Prevent instantiation
    }

    /**
     * Registers all Slash module extensions with the provided feed extension registry.
     *
     * @param registry the feed extension registry to register handlers with
     */
    public static void register(FeedExtensionRegistry<? extends SlashChannel, ? extends SlashItem> registry) {
        itemTagExtensions(registry);
    }

    /**
     * Registers Slash item-level tag extensions.
     *
     * @param registry the feed extension registry to register handlers with
     */
    private static void itemTagExtensions(FeedExtensionRegistry<? extends SlashChannel, ? extends SlashItem> registry) {
        registry.addItemExtension("slash:section", SlashItem::setSlashSection);
        registry.addItemExtension("slash:department", SlashItem::setSlashDepartment);
        registry.addItemExtension("slash:comments", (item, value) -> mapInteger(value, item::setSlashComments));
        registry.addItemExtension("slash:hit_parade", SlashItem::setSlashHitParade);
    }

}
