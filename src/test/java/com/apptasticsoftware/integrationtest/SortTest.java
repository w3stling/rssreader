package com.apptasticsoftware.integrationtest;

import com.apptasticsoftware.rssreader.Item;
import com.apptasticsoftware.rssreader.RssReader;
import com.apptasticsoftware.rssreader.util.ItemComparator;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class SortTest {

    @Test
    void testTimestampSortTest() {
        var urlList = List.of(
                "https://www.riksbank.se/sv/rss/pressmeddelanden",
                "https://www.konj.se/4.2de5c57614f808a95afcc13f/12.2de5c57614f808a95afcc354.portlet?state=rss&sv.contenttype=text/xml;charset=UTF-8",
                "https://www.scb.se/Feed/statistiknyheter/",
                "https://www.avanza.se/placera/forstasidan.rss.xml",
                "https://www.breakit.se/feed/artiklar",
                "https://feedforall.com/sample-feed.xml",
                "https://se.investing.com/rss/news.rss",
                "https://www.di.se/digital/rss",
                "https://worldoftanks.eu/en/rss/news/",
                "https://lwn.net/headlines/rss",
                "https://rss.nytimes.com/services/xml/rss/nyt/HomePage.xml",
                "https://github.com/openjdk/jdk/commits.atom",
                "https://azurecomcdn.azureedge.net/en-us/updates/feed/?updateType=retirements",
                "https://blog.ploeh.dk/rss.xml",
                "https://www.politico.com/rss/politicopicks.xml",
                "https://www.e1.ru/talk/forum/rss.php?f=86",
                "https://failed-to-read-from-this-url.com",
                "https://www.nrdc.org/rss.xml",
                "https://www.theverge.com/rss/reviews/index.xml",
                "https://feeds.macrumors.com/MacRumors-All");


        List<String> extendedUrlList = new ArrayList<>(urlList);
        extendedUrlList.add(null);

        var timestamps = new RssReader().read(extendedUrlList)
                                        .sorted()
                                        .map(Item::getPubDateZonedDateTime)
                                        .flatMap(Optional::stream)
                                        .map(t -> t.toInstant().toEpochMilli())
                                        .collect(Collectors.toList());

        assertTrue(timestamps.size() > 10);

        var iterator = timestamps.iterator();
        Long current, previous = iterator.next();
        while (iterator.hasNext()) {
            current = iterator.next();
            assertTrue(previous.compareTo(current) >= 0);
            previous = current;
        }
    }

    @Test
    void testSortNewestFirst() throws IOException {
        var list = new RssReader().read("https://lwn.net/headlines/rss")
                .sorted(ItemComparator.newestItemFirst())
                .collect(Collectors.toList());

        assertFalse(list.isEmpty());

        var previous = list.get(0);
        for (Item current : list) {
            assertTrue(previous.compareTo(current) >= 0);
            previous = current;
        }
    }

    @Test
    void testSortOldestFirst() throws IOException {
        var list = new RssReader().read("https://lwn.net/headlines/rss")
                .sorted(ItemComparator.oldestItemFirst())
                .collect(Collectors.toList());

        assertFalse(list.isEmpty());

        var previous = list.get(0);
        for (Item current : list) {
            assertTrue(previous.compareTo(current) <= 0);
            previous = current;
        }
    }

    @Test
    void testSortChannelTitle() throws IOException {

        var list = Stream.concat(new RssReader().read("https://lwn.net/headlines/rss"),
                                 new RssReader().read("https://azurecomcdn.azureedge.net/en-us/updates/feed/?updateType=retirements"))
                         .sorted(ItemComparator.channelTitle())
                         .collect(Collectors.toList());

        var first = list.get(0);
        var last = list.get(list.size() - 1);
        assertNotEquals(first.getChannel().getTitle(), last.getChannel().getTitle());
        assertTrue(first.getChannel().getTitle().toLowerCase().contains("azure"));
        assertTrue(last.getChannel().getTitle().toLowerCase().contains("lwn"));
    }
}
