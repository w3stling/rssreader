package com.apptasticsoftware.rssreader.module.itunes;

import com.apptasticsoftware.rssreader.DateTime;
import com.apptasticsoftware.rssreader.util.ItemComparator;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ItunesRssReaderTest {

    @Test
    void readItunesPodcastFeed() {
        var res = new ItunesRssReader().read(ItunesRssReader.class.getClassLoader().getResourceAsStream("itunes-podcast.xml"))
                                       .sorted(ItemComparator.oldestItemFirst())
                                       .collect(Collectors.toList());

        assertEquals(9, res.size());
    }

    @Test
    void readItunesPodcastFeed2() throws IOException {
        var res = new ItunesRssReader().read("https://feeds.theincomparable.com/batmanuniversity")
                                       .collect(Collectors.toList());

        assertTrue(res.size() > 0);
    }

    @Test
    void equalsContract() {
        EqualsVerifier.simple().forClass(ItunesChannel.class).withIgnoredFields("dateTimeParser").withIgnoredFields("category").withNonnullFields("categories").withNonnullFields("itunesCategories").verify();
        EqualsVerifier.simple().forClass(ItunesItem.class).withIgnoredFields("defaultComparator").withIgnoredFields("dateTimeParser").withIgnoredFields("category").withNonnullFields("categories").withIgnoredFields("enclosure").withNonnullFields("enclosures").verify();
        EqualsVerifier.simple().forClass(ItunesOwner.class).verify();
    }

    @Test
    void duration() {
        ItunesItem item = new ItunesItem(new DateTime());
        item.setItunesDuration("1");
        assertEquals(1, item.getItunesDurationAsDuration().get().getSeconds());
        item.setItunesDuration("01:02");
        assertEquals(62, item.getItunesDurationAsDuration().get().getSeconds());
        item.setItunesDuration("01:02:03");
        assertEquals(3723, item.getItunesDurationAsDuration().get().getSeconds());
    }

    @Test
    void badDuration() {
        ItunesItem item = new ItunesItem(new DateTime());
        item.setItunesDuration(null);
        assertTrue(item.getItunesDurationAsDuration().isEmpty());
        item.setItunesDuration(" ");
        assertTrue(item.getItunesDurationAsDuration().isEmpty());
        item.setItunesDuration(":");
        assertTrue(item.getItunesDurationAsDuration().isEmpty());
        item.setItunesDuration("a");
        assertTrue(item.getItunesDurationAsDuration().isEmpty());
        item.setItunesDuration("a:b");
        assertTrue(item.getItunesDurationAsDuration().isEmpty());
        item.setItunesDuration("a:b:c");
        assertTrue(item.getItunesDurationAsDuration().isEmpty());
    }
}
