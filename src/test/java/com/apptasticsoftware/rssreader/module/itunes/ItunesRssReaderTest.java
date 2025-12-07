package com.apptasticsoftware.rssreader.module.itunes;

import com.apptasticsoftware.rssreader.DateTime;
import com.apptasticsoftware.rssreader.util.ItemComparator;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpClient;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ItunesRssReaderTest {

    @Test
    void readItunesPodcastFeed() {
        var res = new ItunesRssReader().read(fromFile("itunes-podcast.xml"))
                .sorted(ItemComparator.oldestPublishedItemFirst())
                .collect(Collectors.toList());

        assertEquals(9, res.size());
    }

    @Test
    void readItunesPodcastFeedFromUrl() throws IOException {
        var res = new ItunesRssReader().read("https://feeds.theincomparable.com/batmanuniversity")
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

        var res = new ItunesRssReader(httpClient).read("https://feeds.theincomparable.com/batmanuniversity")
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

    private InputStream fromFile(String fileName) {
        return getClass().getClassLoader().getResourceAsStream(fileName);
    }
}
