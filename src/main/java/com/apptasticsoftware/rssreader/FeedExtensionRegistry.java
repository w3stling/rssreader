package com.apptasticsoftware.rssreader;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

// Move into subpackage 'module'?
public interface FeedExtensionRegistry<C extends Channel, I extends Item> {
    void addOnChannelTag(String tag, Consumer<C> mapper);
    void addChannelExtension(String tag, BiConsumer<C, String> consumer);
    void addChannelExtension(String tag, String attribute, BiConsumer<C, String> consumer);

    void addOnItemTag(String tag, Consumer<I> mapper);
    void addItemExtension(String tag, BiConsumer<I, String> consumer);
    void addItemExtension(String tag, String attribute, BiConsumer<I, String> consumer);
}
