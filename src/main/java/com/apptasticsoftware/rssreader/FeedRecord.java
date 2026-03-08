package com.apptasticsoftware.rssreader;

/**
 * Generic representation of a feed record, pairing a typed item with its channel.
 *
 * @param <I> the item type, extending {@link Item}
 * @param <C> the channel type, extending {@link Channel}
 */
public interface FeedRecord<I extends Item, C extends Channel> {

    /**
     * Gets the URL of the feed associated with this record.
     * @return the feed URL
     */
    String getFeedUrl();

    /**
     * Gets the channel associated with this record.
     * @return the channel
     */
    C getChannel();

    /**
     * Gets the item associated with this record.
     * @return the item
     */
    I getItem();
}
