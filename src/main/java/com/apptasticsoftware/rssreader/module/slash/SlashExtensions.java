package com.apptasticsoftware.rssreader.module.slash;

import com.apptasticsoftware.rssreader.FeedExtensionRegistry;

import static com.apptasticsoftware.rssreader.util.Mapper.mapInteger;

public class SlashExtensions {

    private SlashExtensions() {
        // Prevent instantiation
    }

    public static void register(FeedExtensionRegistry<? extends SlashChannel, ? extends SlashItem> registry) {
        itemTagExtensions(registry);
    }

    private static void itemTagExtensions(FeedExtensionRegistry<? extends SlashChannel, ? extends SlashItem> registry) {
        registry.addItemExtension("slash:section", SlashItem::setSlashSection);
        registry.addItemExtension("slash:department", SlashItem::setSlashDepartment);
        registry.addItemExtension("slash:comments", (item, value) -> mapInteger(value, item::setSlashComments));
        registry.addItemExtension("slash:hit_parade", SlashItem::setSlashHitParade);
    }

}
