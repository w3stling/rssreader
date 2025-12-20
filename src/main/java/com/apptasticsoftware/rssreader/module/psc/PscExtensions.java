package com.apptasticsoftware.rssreader.module.psc;

import com.apptasticsoftware.rssreader.FeedExtensionRegistry;

import java.util.List;

import static com.apptasticsoftware.rssreader.util.Util.getLast;

public class PscExtensions {
    private static final List<String> ITEM_PATHS = List.of("/rss/channel/item/", "/feed/entry/");

    private PscExtensions() {

    }

    public static void register(FeedExtensionRegistry<? extends PscChannel, ? extends PscItem> registry) {
        onItemTag(registry);
        itemAttributesExtensions(registry);
    }

    private static void onItemTag(FeedExtensionRegistry<? extends PscChannel, ? extends PscItem> registry) {
        registry.addOnItemTag(ITEM_PATHS, "psc:chapters", item -> item.setPscChapters(new PscChapters()));
        registry.addOnItemTag(ITEM_PATHS, "psc:chapters/psc:chapter", item -> item.getPscChapters().ifPresent(i -> i.addChapter(new PscChapter())));
    }

    private static void itemAttributesExtensions(FeedExtensionRegistry<? extends PscChannel, ? extends PscItem> registry) {
        registry.addItemExtension(ITEM_PATHS, "psc:chapters", "version", (item, value) -> item.getPscChapters().ifPresent(chapter -> chapter.setVersion(value)));
        registry.addItemExtension(ITEM_PATHS, "psc:chapters/psc:chapter", "start", (item, value) -> item.getPscChapters().ifPresent(chapters -> getLast(chapters.getChapters()).setStart(value)));
        registry.addItemExtension(ITEM_PATHS, "psc:chapters/psc:chapter", "title", (item, value) -> item.getPscChapters().ifPresent(chapters -> getLast(chapters.getChapters()).setTitle(value)));
        registry.addItemExtension(ITEM_PATHS, "psc:chapters/psc:chapter", "href", (item, value) -> item.getPscChapters().ifPresent(chapters -> getLast(chapters.getChapters()).setHref(value)));
        registry.addItemExtension(ITEM_PATHS, "psc:chapters/psc:chapter", "image", (item, value) -> item.getPscChapters().ifPresent(chapters -> getLast(chapters.getChapters()).setImage(value)));
    }
}
