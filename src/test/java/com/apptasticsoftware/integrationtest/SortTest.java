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
                "https://rss.nytimes.com/services/xml/rss/nyt/HomePage.xml",
                "https://github.com/openjdk/jdk/commits.atom",
                "https://azurecomcdn.azureedge.net/en-us/updates/feed/?updateType=retirements",
                "https://blog.ploeh.dk/rss.xml",
                "https://www.politico.com/rss/politicopicks.xml",
                "https://www.e1.ru/talk/forum/rss.php?f=86",
                "https://failed-to-read-from-this-url.com",
                "https://www.nrdc.org/rss.xml",
                "https://www.theverge.com/rss/reviews/index.xml",
                "https://feeds.macrumors.com/MacRumors-All",
                "https://www.ksl.com/rss/news",
                "http://rss.cnn.com/rss/cnn_latest.rss",
                "https://moxie.foxnews.com/google-publisher/latest.xml",
                "https://techcrunch.com/feed/",
                "https://feeds.arstechnica.com/arstechnica/science"
        );

        List<String> extendedUrlList = new ArrayList<>(urlList);
        extendedUrlList.add(null);

        var timestamps = new RssReader().read(extendedUrlList)
                .sorted()
                .map(Item::getPubDateZonedDateTime)
                .flatMap(Optional::stream)
                .map(t -> t.toInstant().toEpochMilli())
                .collect(Collectors.toList());

        assertTrue(timestamps.size() > 200);

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
        var list = new RssReader().read("https://feeds.macrumors.com/MacRumors-All")
                .sorted(ItemComparator.newestItemFirst())
                .collect(Collectors.toList());

        assertFalse(list.isEmpty());

        var previous = list.get(0);
        for (Item current : list) {
            assertTrue(previous.compareTo(current) <= 0);
            previous = current;
        }
    }

    @Test
    void testSortOldestFirst() throws IOException {
        var list = new RssReader().read("https://feeds.macrumors.com/MacRumors-All")
                .sorted(ItemComparator.oldestItemFirst())
                .collect(Collectors.toList());

        assertFalse(list.isEmpty());

        var previous = list.get(0);
        for (Item current : list) {
            assertTrue(previous.compareTo(current) >= 0);
            previous = current;
        }
    }

    @Test
    void testSortChannelTitle() {
        var urls = List.of("https://feeds.a.dj.com/rss/RSSMarketsMain.xml", "https://gizmodo.com/feed");
        var list = new RssReader().read(urls)
                .sorted(ItemComparator.channelTitle())
                .collect(Collectors.toList());

        var first = list.get(0);
        var last = list.get(list.size() - 1);
        assertNotEquals(first.getChannel().getTitle(), last.getChannel().getTitle());
        assertTrue(first.getChannel().getTitle().toLowerCase().contains("gizmodo"));
        assertTrue(last.getChannel().getTitle().toLowerCase().contains("wsj"));
    }
}
