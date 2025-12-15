package com.apptasticsoftware.rssreader;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

// Move into subpackage 'module'?
public interface FeedExtensionRegistry<C extends Channel, I extends Item> {
    // Channel
    void addOnChannelTag(String tag, Consumer<C> mapper);
    default void addOnChannelTag(List<String> basePaths, String tag, Consumer<C> mapper) {
        basePaths.forEach(basePath -> addOnChannelTag(basePath + tag, mapper));
    }

    void addChannelExtension(String tag, BiConsumer<C, String> consumer);
    default void addChannelExtension(List<String> basePaths, String tag, BiConsumer<C, String> consumer) {
        basePaths.forEach(basePath -> addChannelExtension(basePath + tag, consumer));
    }

    void addChannelExtension(String tag, String attribute, BiConsumer<C, String> consumer);
    default void addChannelExtension(List<String> basePaths, String tag, String attribute, BiConsumer<C, String> consumer) {
        basePaths.forEach(basePath -> addChannelExtension(basePath + tag, attribute, consumer));
    }

    // Item
    void addOnItemTag(String tag, Consumer<I> mapper);
    default void addOnItemTag(List<String> basePaths, String tag, Consumer<I> mapper) {
        basePaths.forEach(basePath -> addOnItemTag(basePath + tag, mapper));
    }

    void addItemExtension(String tag, BiConsumer<I, String> consumer);
    default void addItemExtension(List<String> basePaths, String tag, BiConsumer<I, String> consumer) {
        basePaths.forEach(basePath -> addItemExtension(basePath + tag, consumer));
    }

    void addItemExtension(String tag, String attribute, BiConsumer<I, String> consumer);
    default void addItemExtension(List<String> basePaths, String tag, String attribute, BiConsumer<I, String> consumer) {
        basePaths.forEach(basePath -> addItemExtension(basePath + tag, attribute, consumer));
    }
}
