package com.apptasticsoftware.rssreader;

import java.util.Optional;

/**
 * Generic representation of a feed record, pairing a typed item with its channel.
 *
 * @param <I> the item type, extending {@link Item}
 * @param <C> the channel type, extending {@link Channel}
 */
public interface FeedRecord<I extends Item, C extends Channel> {

    /**
     * Checks if the record has an item. A record is considered to have an item if both the channel and item are empty.
     * @return true if the record has an item, false otherwise
     */
    default boolean hasItem() {
        return getChannel().isEmpty() && getItem().isEmpty();
    }

    /**
     * Checks if the record is an empty feed. A record is considered an empty feed if it has a channel but no item.
     * @return true if the record is an empty feed, false otherwise
     */
    default boolean isEmptyFeed() {
        return !getChannel().isEmpty() && getItem().isEmpty();
    }

    /**
     * Gets the URL of the feed associated with this record.
     * @return the feed URL
     */
    String getFeedUrl();

    /**
     * Gets the channel associated with this record.
     * @return the channel
     */
    Optional<C> getChannel();

    /**
     * Gets the item associated with this record.
     * @return the item
     */
    Optional<I> getItem();
}
