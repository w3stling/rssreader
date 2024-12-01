package com.apptasticsoftware.rssreader.util;

import com.apptasticsoftware.rssreader.RssReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ItemComparatorTest {

    @Test
    void testSortNewestItem() throws IOException {
        var items = new RssReader().read("https://www.theverge.com/rss/reviews/index.xml")
                                   .sorted(ItemComparator.newestItemFirst())
                                   .map(i -> i.getPubDateZonedDateTime().orElse(null))
                                   .filter(Objects::nonNull)
                                   .map(ZonedDateTime::toEpochSecond)
                                   .collect(Collectors.toList());

        assertTrue(isDescendingSortOrder(items));
    }

    @Test
    void testSortNewestPublishedItem() throws IOException {
        var items = new RssReader().read("https://www.theverge.com/rss/reviews/index.xml")
                .sorted(ItemComparator.newestPublishedItemFirst())
                .map(i -> i.getPubDateZonedDateTime().orElse(null))
                .filter(Objects::nonNull)
                .map(ZonedDateTime::toEpochSecond)
                .collect(Collectors.toList());

        assertTrue(isDescendingSortOrder(items));
    }

    @Test
    void testSortNewestItemWithCustomDateTimeParser() throws IOException {
        var items = new RssReader().setDateTimeParser(Default.getDateTimeParser())
                                   .read("https://www.theverge.com/rss/reviews/index.xml")
                                   .sorted(ItemComparator.newestItemFirst())
                                   .map(i -> i.getPubDateZonedDateTime().orElse(null))
                                   .filter(Objects::nonNull)
                                   .map(ZonedDateTime::toEpochSecond)
                                   .collect(Collectors.toList());

        assertTrue(isDescendingSortOrder(items));
    }

    @Test
    void testSortNewestPublishedItemWithCustomDateTimeParser() throws IOException {
        var items = new RssReader().setDateTimeParser(Default.getDateTimeParser())
                .read("https://www.theverge.com/rss/reviews/index.xml")
                .sorted(ItemComparator.newestPublishedItemFirst())
                .map(i -> i.getPubDateZonedDateTime().orElse(null))
                .filter(Objects::nonNull)
                .map(ZonedDateTime::toEpochSecond)
                .collect(Collectors.toList());

        assertTrue(isDescendingSortOrder(items));
    }

    @Test
    void testSortNewestItemWithDateTimeParser() throws IOException {
        var items = new RssReader().read("https://www.theverge.com/rss/reviews/index.xml")
                                   .sorted(ItemComparator.newestItemFirst(Default.getDateTimeParser()))
                                   .map(i -> i.getPubDateZonedDateTime().orElse(null))
                                   .filter(Objects::nonNull)
                                   .map(ZonedDateTime::toEpochSecond)
                                   .collect(Collectors.toList());

        assertTrue(isDescendingSortOrder(items));
    }

    @Test
    void testSortNewestPublishedItemWithDateTimeParser() throws IOException {
        var items = new RssReader().read("https://www.theverge.com/rss/reviews/index.xml")
                .sorted(ItemComparator.newestPublishedItemFirst(Default.getDateTimeParser()))
                .map(i -> i.getPubDateZonedDateTime().orElse(null))
                .filter(Objects::nonNull)
                .map(ZonedDateTime::toEpochSecond)
                .collect(Collectors.toList());

        assertTrue(isDescendingSortOrder(items));
    }

    @Test
    void testSortOldestItemFirst() throws IOException {
        var items = new RssReader().read("https://www.theverge.com/rss/reviews/index.xml")
                                   .sorted(ItemComparator.oldestItemFirst())
                                   .map(i -> i.getPubDateZonedDateTime().orElse(null))
                                   .filter(Objects::nonNull)
                                   .collect(Collectors.toList());

        assertTrue(isAscendingSortOrder(items));
    }

    @Test
    void testSortOldestPublishedItemFirst() throws IOException {
        var items = new RssReader().read("https://www.theverge.com/rss/reviews/index.xml")
                .sorted(ItemComparator.oldestPublishedItemFirst())
                .map(i -> i.getPubDateZonedDateTime().orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        assertTrue(isAscendingSortOrder(items));
    }

    @Test
    void testSortOldestItemFirstWithDateTimeParser() throws IOException {
        var items = new RssReader().read("https://www.theverge.com/rss/reviews/index.xml")
                                   .sorted(ItemComparator.oldestItemFirst(Default.getDateTimeParser()))
                                   .map(i -> i.getPubDateZonedDateTime().orElse(null))
                                   .filter(Objects::nonNull)
                                   .collect(Collectors.toList());

        assertTrue(isAscendingSortOrder(items));
    }

    @Test
    void testSortOldestPublishedItemFirstWithDateTimeParser() throws IOException {
        var items = new RssReader().read("https://www.theverge.com/rss/reviews/index.xml")
                .sorted(ItemComparator.oldestPublishedItemFirst(Default.getDateTimeParser()))
                .map(i -> i.getPubDateZonedDateTime().orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        assertTrue(isAscendingSortOrder(items));
    }

    @Test
    void testSortNewestUpdatedItem() throws IOException {
        var items = new RssReader().read("https://www.theverge.com/rss/reviews/index.xml")
                .sorted(ItemComparator.newestUpdatedItemFirst())
                .map(i -> i.getUpdatedZonedDateTime().orElse(null))
                .filter(Objects::nonNull)
                .map(ZonedDateTime::toEpochSecond)
                .collect(Collectors.toList());

        assertTrue(isDescendingSortOrder(items));
    }

    @Test
    void testSortNewestUpdatedItemWithCustomDateTimeParser() throws IOException {
        var items = new RssReader().setDateTimeParser(Default.getDateTimeParser())
                .read("https://www.theverge.com/rss/reviews/index.xml")
                .sorted(ItemComparator.newestUpdatedItemFirst())
                .map(i -> i.getUpdatedZonedDateTime().orElse(null))
                .filter(Objects::nonNull)
                .map(ZonedDateTime::toEpochSecond)
                .collect(Collectors.toList());

        assertTrue(isDescendingSortOrder(items));
    }

    @Test
    void testSortNewestUpdatedItemWithDateTimeParser() throws IOException {
        var items = new RssReader().read("https://www.theverge.com/rss/reviews/index.xml")
                .sorted(ItemComparator.newestUpdatedItemFirst(Default.getDateTimeParser()))
                .map(i -> i.getUpdatedZonedDateTime().orElse(null))
                .filter(Objects::nonNull)
                .map(ZonedDateTime::toEpochSecond)
                .collect(Collectors.toList());

        assertTrue(isDescendingSortOrder(items));
    }

    @Test
    void testSortOldestUpdatedItemFirst() throws IOException {
        var items = new RssReader().read("https://www.theverge.com/rss/reviews/index.xml")
                .sorted(ItemComparator.oldestUpdatedItemFirst())
                .map(i -> i.getUpdatedZonedDateTime().orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        assertTrue(isAscendingSortOrder(items));
    }

    @Test
    void testSortOldestUpdatedItemFirstWithDateTimeParser() throws IOException {
        var items = new RssReader().read("https://www.theverge.com/rss/reviews/index.xml")
                .sorted(ItemComparator.oldestUpdatedItemFirst(Default.getDateTimeParser()))
                .map(i -> i.getUpdatedZonedDateTime().orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        assertTrue(isAscendingSortOrder(items));
    }

    @Test
    void testSortChannelTitle() {
        var urlList = List.of("https://www.theverge.com/rss/reviews/index.xml", "https://feeds.macrumors.com/MacRumors-All");
        var items = new RssReader().read(urlList)
                                   .sorted(ItemComparator.channelTitle())
                                   .map(i -> i.getChannel().getTitle())
                                   .filter(Objects::nonNull)
                                   .collect(Collectors.toList());

        assertTrue(isAscendingSortOrder(items));
    }


    private static <T extends Comparable<? super T>> boolean isAscendingSortOrder(List<T> array){
        for (int i = 0; i < array.size()-1; i++) {
            if (array.get(i).compareTo(array.get(i+1)) > 0){
                return false;
            }
        }
        return true;
    }

    private static <T extends Comparable<? super T>> boolean isDescendingSortOrder(List<T> array){
        for (int i = 0; i < array.size()-1; i++) {
            if (array.get(i).compareTo(array.get(i+1)) < 0){
                return false;
            }
        }
        return true;
    }

}
