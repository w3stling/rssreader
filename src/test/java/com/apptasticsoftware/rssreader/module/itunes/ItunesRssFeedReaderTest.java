package com.apptasticsoftware.rssreader.module.itunes;

import com.apptasticsoftware.rssreader.*;
import com.apptasticsoftware.rssreader.module.itunes.internal.ItunesChannelImpl;
import com.apptasticsoftware.rssreader.module.itunes.internal.ItunesItemImpl;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpClient;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ItunesRssFeedReaderTest {

    @ParameterizedTest
    @MethodSource("feedReaderArguments")
    void example1(AbstractRssReader<ItunesChannel, ItunesItem> feedReader) {
        var items = feedReader.read(fromFile("module/itunes/itunes-podcast.xml"))
                .collect(Collectors.toList());
        assertEquals(9, items.size());
        items.forEach(this::assertHasFeedItem);
    }

    @Test
    void readItunesPodcastFeedFromUrl() throws IOException {
        var res = new ItunesFeedReader().read("https://feeds.theincomparable.com/batmanuniversity")
                .collect(Collectors.toList());

        assertFalse(res.isEmpty());
    }

    @Test
    void httpClient() throws IOException, KeyManagementException, NoSuchAlgorithmException {
        SSLContext context = SSLContext.getInstance("TLSv1.3");
        context.init(null, null, null);

        HttpClient httpClient = HttpClient.newBuilder()
                .sslContext(context)
                .connectTimeout(Duration.ofSeconds(15))
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();

        var res = new ItunesFeedReader(httpClient).read("https://feeds.theincomparable.com/batmanuniversity")
                .collect(Collectors.toList());

        assertFalse(res.isEmpty());
    }

    @Test
    void equalsContract() {
        EqualsVerifier.simple().forClass(ItunesChannelImpl.class).withNonnullFields("data").withIgnoredFields("dateTimeParser").withIgnoredFields("category").withNonnullFields("categories").withIgnoredFields("syUpdatePeriod").withIgnoredFields("syUpdateFrequency").verify();
        EqualsVerifier.simple().forClass(ItunesItemImpl.class).withNonnullFields("data").withIgnoredFields("defaultComparator").withIgnoredFields("dateTimeParser").withIgnoredFields("category").withNonnullFields("categories").withIgnoredFields("enclosure").withNonnullFields("enclosures").verify();
        EqualsVerifier.simple().forClass(ItunesOwner.class).verify();
    }

    @Test
    void duration() {
        ItunesItem item = new ItunesItemImpl(new DateTime());
        item.setItunesDuration("1");
        assertEquals(1, item.getItunesDurationAsDuration().get().getSeconds());
        item.setItunesDuration("01:02");
        assertEquals(62, item.getItunesDurationAsDuration().get().getSeconds());
        item.setItunesDuration("01:02:03");
        assertEquals(3723, item.getItunesDurationAsDuration().get().getSeconds());
    }

    @Test
    void badDuration() {
        ItunesItem item = new ItunesItemImpl(new DateTime());
        item.setItunesDuration(null);
        assertTrue(item.getItunesDurationAsDuration().isEmpty());
        item.setItunesDuration(" ");
        assertTrue(item.getItunesDurationAsDuration().isEmpty());
        item.setItunesDuration(":");
        assertTrue(item.getItunesDurationAsDuration().isEmpty());
    }

    private void assertHasFeedItem(ItunesItem item) {
        if (item instanceof FeedItem) {
            FeedItem feedItem = (FeedItem) item;
            assertFalse(feedItem.hasAtomItem());
            assertFalse(feedItem.hasDcItem());
            assertFalse(feedItem.hasGeoRssItem());
            assertTrue(feedItem.hasItunesItem());
            assertFalse(feedItem.hasMediaRssItem());
            assertFalse(feedItem.hasPodcastItem());
            assertFalse(feedItem.hasPscItem());
            assertFalse(feedItem.hasSlashItem());
            assertFalse(feedItem.hasWfwItem());
            assertFalse(feedItem.hasYoutubeItem());

            FeedChannel feedChannel = feedItem.getChannel();
            assertFalse(feedChannel.hasAtomChannel());
            assertFalse(feedChannel.hasDcChannel());
            assertFalse(feedChannel.hasGeoRssChannel());
            assertTrue(feedChannel.hasItunesChannel());
            assertFalse(feedChannel.hasMediaRssChannel());
            assertFalse(feedChannel.hasOpenSearchChannel());
            assertFalse(feedChannel.hasPodcastChannel());
            assertFalse(feedChannel.hasSpotifyChannel());
            assertFalse(feedChannel.hasYoutubeChannel());
        }
    }

    private static Stream<? extends Arguments> feedReaderArguments() {
        return Stream.of(
            Arguments.of(new ItunesFeedReader()),
            Arguments.of(new FeedReader())
        );
    }

    private InputStream fromFile(String fileName) {
        return getClass().getClassLoader().getResourceAsStream(fileName);
    }
}
