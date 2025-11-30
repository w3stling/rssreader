package com.apptasticsoftware.integrationtest;

import com.apptasticsoftware.rssreader.Item;
import com.apptasticsoftware.rssreader.RssReader;
import com.apptasticsoftware.rssreader.internal.RssServer;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionTest {
    private static final int PORT = 8008;
    private static final Duration NEGATIVE_DURATION = Duration.ofSeconds(-30);

    @Test
    void testConnectionTimeoutWithNullValue() {
        var rssReader = new RssReader();
        var exception = assertThrows(NullPointerException.class, () -> rssReader.setConnectionTimeout(null));
        assertEquals("Connection timeout must not be null", exception.getMessage());
    }

    @Test
    void testRequestTimeoutWithNullValue() {
        var rssReader = new RssReader();
        var exception = assertThrows(NullPointerException.class, () -> rssReader.setRequestTimeout(null));
        assertEquals("Request timeout must not be null", exception.getMessage());
    }

    @Test
    void testReadTimeoutWithNullValue() {
        var rssReader = new RssReader();
        var exception = assertThrows(NullPointerException.class, () -> rssReader.setReadTimeout(null));
        assertEquals("Read timeout must not be null", exception.getMessage());
    }

    @Test
    void testConnectionTimeoutWithNegativeValue() {
        var rssReader = new RssReader();
        var exception = assertThrows(IllegalArgumentException.class, () -> rssReader.setConnectionTimeout(NEGATIVE_DURATION));
        assertEquals("Connection timeout must not be negative", exception.getMessage());
    }

    @Test
    void testRequestTimeoutWithNegativeValue() {
        var rssReader = new RssReader();
        var exception = assertThrows(IllegalArgumentException.class, () -> rssReader.setRequestTimeout(NEGATIVE_DURATION));
        assertEquals("Request timeout must not be negative", exception.getMessage());
    }

    @Test
    void testReadTimeoutWithNegativeValue() {
        var rssReader = new RssReader();
        var exception = assertThrows(IllegalArgumentException.class, () -> rssReader.setReadTimeout(NEGATIVE_DURATION));
        assertEquals("Read timeout must not be negative", exception.getMessage());
    }

    @Test
    void testReadFromLocalRssServerNoTimeout() throws IOException {
        var server = RssServer.with(getFile("atom-feed.xml"))
                .port(PORT)
                .endpointPath("/rss")
                .build();
        server.start();

        var items = new RssReader()
                .setConnectionTimeout(Duration.ZERO)
                .setRequestTimeout(Duration.ZERO)
                .setReadTimeout(Duration.ZERO)
                .read("http://localhost:8008/rss")
                .collect(Collectors.toList());

        server.stop();
        verify(3, items);
    }

    @Test
    void testReadFromLocalRssServer10SecondTimeout() throws IOException {
        var server = RssServer.with(getFile("atom-feed.xml"))
                .port(PORT)
                .endpointPath("/rss")
                .build();
        server.start();

        var items = new RssReader()
                .setConnectionTimeout(Duration.ofSeconds(10))
                .setRequestTimeout(Duration.ofSeconds(10))
                .setReadTimeout(Duration.ofSeconds(10))
                .read("http://localhost:8008/rss")
                .collect(Collectors.toList());

        server.stop();
        verify(3, items);
    }


    @Test
    void testReadFromLocalRssServer() throws IOException {
        var server = RssServer.with(getFile("atom-feed.xml"))
                .port(PORT)
                .endpointPath("/rss")
                .build();
        server.start();

        var items = new RssReader()
                .setReadTimeout(Duration.ofSeconds(2))
                .read("http://localhost:8008/rss")
                .collect(Collectors.toList());

        server.stop();
        verify(3, items);
    }

    @Test
    void testNoReadTimeout() throws IOException {
        var server = RssServer.with(getFile("atom-feed.xml"))
                .port(PORT)
                .endpointPath("/rss")
                .build();
        server.start();

        var items = new RssReader()
                .setReadTimeout(Duration.ZERO)
                .read("http://localhost:8008/rss")
                .collect(Collectors.toList());

        server.stop();
        verify(3, items);
    }

    @Test
    void testReadTimeout() throws IOException {
        var server = RssServer.withWritePause(getFile("atom-feed.xml"), Duration.ofSeconds(4))
                .port(PORT)
                .endpointPath("/slow-server")
                .build();
        server.start();

        var items = new RssReader()
                .setReadTimeout(Duration.ofSeconds(2))
                .read("http://localhost:8008/slow-server")
                .collect(Collectors.toList());

        server.stop();
        verify(2, items);
    }

    private static void verify(int expectedSize, List<Item> items) {
        assertEquals(expectedSize, items.size());

        if (!items.isEmpty()) {
            assertEquals("dive into mark", items.get(0).getChannel().getTitle());
            assertEquals(65, items.get(0).getChannel().getDescription().length());
            assertEquals("http://example.org/feed.atom", items.get(0).getChannel().getLink());
            assertEquals("Copyright (c) 2003, Mark Pilgrim", items.get(0).getChannel().getCopyright().orElse(null));
            assertEquals("Example Toolkit", items.get(0).getChannel().getGenerator().orElse(null));
            assertEquals("2005-07-31T12:29:29Z", items.get(0).getChannel().getLastBuildDate().orElse(null));

            assertEquals("Atom draft-07 snapshot", items.get(0).getTitle().orElse(null));
            assertNull(items.get(1).getAuthor().orElse(null));
            assertEquals("http://example.org/audio/ph34r_my_podcast.mp3", items.get(0).getLink().orElse(null));
            assertEquals("tag:example.org,2003:3.2397", items.get(0).getGuid().orElse(null));
            assertEquals("2003-12-13T08:29:29-04:00", items.get(0).getPubDate().orElse(null));
            assertEquals("2005-07-31T12:29:29Z", items.get(0).getUpdated().orElse(null));
            assertEquals(211, items.get(1).getDescription().orElse("").length());
        }
        if (items.size() >= 2) {
            assertEquals("Atom-Powered Robots Run Amok", items.get(1).getTitle().orElse(null));
            assertNull(items.get(1).getAuthor().orElse(null));
            assertEquals("http://example.org/2003/12/13/atom03", items.get(1).getLink().orElse(null));
            assertEquals("urn:uuid:1225c695-cfb8-4ebb-aaaa-80da344efa6a", items.get(1).getGuid().orElse(null));
            assertEquals("2003-12-13T18:30:02Z", items.get(1).getPubDate().orElse(null));
            assertEquals("2003-12-13T18:30:02Z", items.get(1).getUpdated().orElse(null));
            assertEquals(211, items.get(1).getDescription().orElse("").length());
        }
        if (items.size() >= 3) {
            assertEquals("Atom-Powered Robots Run Amok 2", items.get(2).getTitle().orElse(null));
            assertNull(items.get(2).getAuthor().orElse(null));
            assertEquals("http://example.org/2003/12/13/atom04", items.get(2).getLink().orElse(null));
            assertEquals("urn:uuid:1225c695-cfb8-4ebb-aaaa-80da344efa6b", items.get(2).getGuid().orElse(null));
            assertEquals("2003-12-13T09:28:28-04:00", items.get(2).getPubDate().orElse(null));
            assertEquals(1071322108, items.get(2).getPubDateAsZonedDateTime().map(ZonedDateTime::toEpochSecond).orElse(null));
            assertEquals(1071322108, items.get(2).getPubDateZonedDateTime().map(ZonedDateTime::toEpochSecond).orElse(null));
            assertEquals("2003-12-13T18:30:01Z", items.get(2).getUpdated().orElse(null));
            assertEquals(1071340201, items.get(2).getUpdatedAsZonedDateTime().map(ZonedDateTime::toEpochSecond).orElse(null));
            assertEquals(1071340201, items.get(2).getUpdatedZonedDateTime().map(ZonedDateTime::toEpochSecond).orElse(null));
            assertEquals(47, items.get(2).getDescription().orElse("").length());
        }
    }

    private File getFile(String filename) {
        var url = getClass().getClassLoader().getResource(filename);
        return new File(url.getFile());
    }
}
