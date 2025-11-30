package com.apptasticsoftware.integrationtest;

import com.apptasticsoftware.rssreader.RssReader;
import com.apptasticsoftware.rssreader.util.Default;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class RdfFeedTest {

    @Test
    void readRdfFeedExample1() {
        var list = new RssReader().read(fromFile("rdf-feed-example1.xml")).collect(Collectors.toList());
        assertEquals(9, list.size());
        var item = list.get(0);
        var channel = item.getChannel();

        assertTrue(channel.getImage().isPresent());
        assertEquals("Journal of Web Librarianship", channel.getImage().get().getTitle());
        assertEquals("https://www.tandfonline.com/loi/wjwl20?ai=1dl&mi=el63dh&af=R", channel.getImage().get().getLink());
        assertEquals("https://www.tandfonline.com/cms/asset/66f7625d-e646-4c24-a36d-18dcf406d2ad/default_cover.jpg", channel.getImage().get().getUrl());

        assertEquals("tandf: Journal of Web Librarianship: Table of Contents", item.getChannel().getTitle());
        assertEquals("Table of Contents for Journal of Web Librarianship. List of articles from both the latest and ahead of print issues.", item.getChannel().getDescription());
        assertEquals("en-US", item.getChannel().getLanguage().orElse(""));
        assertEquals("I Can’t Get No Satis-Searching: Reassessing Discovery Layers in Academic Libraries Journal of Web Librarianship", item.getTitle().orElse(""));
        assertEquals("Volume 18, Issue 1, January-March 2024, Page 1-14<br/>. <br/>", item.getDescription().orElse(""));
        assertEquals("doi:10.1080/19322909.2024.2326687", item.getGuid().orElse(""));
    }

    @Test
    void readRdfFeedExample2() {
        var list = new RssReader().read(fromFile("rdf-feed-example2.xml")).collect(Collectors.toList());
        assertEquals(1, list.size());
        var item = list.get(0);
        var channel = item.getChannel();

        assertTrue(channel.getImage().isPresent());
        assertEquals("Slashdot", channel.getImage().get().getTitle());
        assertEquals("http://slashdot.org", channel.getImage().get().getLink());
        assertEquals("http://images.slashdot.org/topics/topicslashdot.gif", channel.getImage().get().getUrl());

        assertEquals("Slashdot", item.getChannel().getTitle());
        assertEquals("News for nerds, stuff that matters", item.getChannel().getDescription());
        assertEquals("http://slashdot.org", item.getChannel().getLink());
        assertEquals("en-us", item.getChannel().getLanguage().orElse(""));
        assertEquals("pater@slashdot.org", item.getChannel().getManagingEditor().orElse(""));
        assertEquals("Copyright © 2000 Slashdot", item.getChannel().getCopyright().orElse(""));
        assertEquals("2000-12-17T01:17-05:00", item.getChannel().getPubDate().orElse(""));
        assertEquals(Default.getDateTimeParser().parse("2000-12-17T01:17-05:00"), item.getChannel().getPubDateAsZonedDateTime().orElse(null));

        assertEquals("Jupiter Moon Ganymede May Have An Ocean", item.getTitle().orElse(""));
        assertEquals("http://slashdot.org/article.pl?sid=00/12/17/0622203", item.getLink().orElse(""));
        assertEquals("This article talks about how Jupiter's moon, Ganymede, may have a\n" +
                "            salt water ocean on it. Kind of ...", item.getDescription().orElse(""));
        assertEquals("timothy", item.getAuthor().orElse(""));
        assertEquals("2000-12-17T01:17", item.getPubDate().orElse(""));
        assertEquals(Default.getDateTimeParser().parse("2000-12-17T01:17"), item.getPubDateAsZonedDateTime().orElse(null));
        assertEquals(1, item.getCategories().size());
        assertEquals("space", item.getCategories().get(0));
    }

    private InputStream fromFile(String fileName) {
        return getClass().getClassLoader().getResourceAsStream(fileName);
    }
}
