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

    @SuppressWarnings("java:S1192")
    private static void itemAttributesExtensions(FeedExtensionRegistry<? extends PscChannel, ? extends PscItem> registry) {
        registry.addItemExtension(ITEM_PATHS, "psc:chapters", "version", (item, value) -> item.getPscChapters().ifPresent(chapter -> chapter.setVersion(value)));
        registry.addItemExtension(ITEM_PATHS, "psc:chapters/psc:chapter", "start", (item, value) -> setChapterAttribute(item, value, PscChapter::setStart));
        registry.addItemExtension(ITEM_PATHS, "psc:chapters/psc:chapter", "title", (item, value) -> setChapterAttribute(item, value, PscChapter::setTitle));
        registry.addItemExtension(ITEM_PATHS, "psc:chapters/psc:chapter", "href", (item, value) -> setChapterAttribute(item, value, PscChapter::setHref));
        registry.addItemExtension(ITEM_PATHS, "psc:chapters/psc:chapter", "image", (item, value) -> setChapterAttribute(item, value, PscChapter::setImage));
    }

    private static void setChapterAttribute(PscItem item, String value, java.util.function.BiConsumer<PscChapter, String> setter) {
        item.getPscChapters().ifPresent(chapters -> setter.accept(getLast(chapters.getChapters()), value));
    }
}
