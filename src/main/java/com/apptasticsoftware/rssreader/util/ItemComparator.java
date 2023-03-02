package com.apptasticsoftware.rssreader.util;

import com.apptasticsoftware.rssreader.DateTime;
import com.apptasticsoftware.rssreader.Item;

import java.util.Comparator;

/**
 * Comparator for sorting item objects.
 */
public final class ItemComparator {

    private ItemComparator() {

    }

    /**
     * Comparator for sorting Items on publication date in ascending order (oldest first)
     * @param <I> any class that extend Item
     * @return comparator
     */
    public static <I extends Item> Comparator<I> oldestItemFirst() {
        return Comparator.comparing((I i) -> i.getPubDate().map(DateTime::toEpochMilli).orElse(0L));
    }

    /**
     * Comparator for sorting Items on publication date in descending order (newest first)
     * @param <I> any class that extend Item
     * @return comparator
     */
    public static <I extends Item> Comparator<I> newestItemFirst() {
        return Comparator.comparing((I i) -> i.getPubDate().map(DateTime::toEpochMilli).orElse(0L)).reversed();
    }

    /**
     * Comparator for sorting Items on channel title
     * @param <I> any class that extend Item
     * @return comparator
     */
    public static <I extends Item> Comparator<I> channelTitle() {
        return Comparator.comparing((I i) -> i.getChannel().getTitle());
    }

}