package com.apptasticsoftware.rssreader.util;

import com.apptasticsoftware.rssreader.DateTime;
import com.apptasticsoftware.rssreader.RssReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItemComparatorTest {

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
    void testSortNewestItemWithDateTimeParser() throws IOException {
        var items = new RssReader().read("https://www.theverge.com/rss/reviews/index.xml")
                .sorted(ItemComparator.newestItemFirst(new DateTime()))
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
    void testSortOldestItemFirstWithDateTimeParser() throws IOException {
        var items = new RssReader().read("https://www.theverge.com/rss/reviews/index.xml")
                .sorted(ItemComparator.oldestItemFirst(new DateTime()))
                .map(i -> i.getPubDateZonedDateTime().orElse(null))
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
