package com.apptasticsoftware.rssreader.module.psc;

import com.apptasticsoftware.rssreader.AbstractRssReader;
import com.apptasticsoftware.rssreader.FeedChannel;
import com.apptasticsoftware.rssreader.FeedItem;
import com.apptasticsoftware.rssreader.FeedReader;
import com.apptasticsoftware.rssreader.module.psc.internal.PscChannelImpl;
import com.apptasticsoftware.rssreader.module.psc.internal.PscItemDataImpl;
import com.apptasticsoftware.rssreader.module.psc.internal.PscItemImpl;
import com.apptasticsoftware.rssreader.util.Default;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.InputStream;
import java.time.Duration;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

class PscFeedReaderTest {

    @ParameterizedTest
    @MethodSource("feedReaderArguments")
    @SuppressWarnings("java:S5961")
    void example1(AbstractRssReader<PscChannel, PscItem> feedReader) {
        var items = feedReader.read(fromFile("module/psc/example1.xml"))
                .collect(Collectors.toList());

        assertEquals(1, items.size());

        var channel = items.get(0).getChannel();
        assertThat(channel.getTitle()).isEqualTo("Podlove Podcast");
        assertThat(channel.getLink()).isEqualTo("http://podlove.org");

        var item = items.get(0);
        assertHasFeedItem(item);
        assertThat(item.getTitle()).hasValue("Fiat Lux");
        assertThat(item.getIsPermaLink()).hasValue(false);
        assertThat(item.getGuid()).hasValue("urn:uuid:3241ace2-ca21-dd12-2341-1412ce31fad2");
        assertThat(item.getPubDate()).hasValue("Fri, 23 Mar 2012 23:25:19 +0000");
        assertThat(item.getPubDateAsZonedDateTime()).hasValue(Default.getDateTimeParser().parse("Fri, 23 Mar 2012 23:25:19 +0000"));
        assertThat(item.getDescription()).hasValue("First episode");
        assertThat(item.getLink()).hasValue("http://podlove.org/files/fiatlux.mp3");
        assertTrue(item.getPscChapters().isPresent());
        assertThat(item.getPscChapters().get().getVersion()).hasValue("1.2");
        assertThat(item.getPscChapters().get().getChapters()).hasSize(4);

        assertThat(item.getPscChapters().get().getChapters().get(0).getStart()).isEqualTo("0");
        assertThat(item.getPscChapters().get().getChapters().get(0).getStartAsDuration()).isEqualTo(Duration.ofSeconds(0));
        assertThat(item.getPscChapters().get().getChapters().get(0).getTitle()).isEqualTo("Welcome");
        assertThat(item.getPscChapters().get().getChapters().get(0).getHref()).isEmpty();
        assertThat(item.getPscChapters().get().getChapters().get(0).getImage()).isEmpty();

        assertThat(item.getPscChapters().get().getChapters().get(1).getStart()).isEqualTo("3:07");
        assertThat(item.getPscChapters().get().getChapters().get(1).getStartAsDuration()).isEqualTo(Duration.ofSeconds(3 * 60 + 7));
        assertThat(item.getPscChapters().get().getChapters().get(1).getTitle()).isEqualTo("Introducing Podlove");
        assertThat(item.getPscChapters().get().getChapters().get(1).getHref()).hasValue("http://podlove.org/");
        assertThat(item.getPscChapters().get().getChapters().get(1).getImage()).isEmpty();

        assertThat(item.getPscChapters().get().getChapters().get(2).getStart()).isEqualTo("8:26.250");
        assertThat(item.getPscChapters().get().getChapters().get(2).getStartAsDuration()).isEqualTo(Duration.parse("PT8M26.250S"));
        assertThat(item.getPscChapters().get().getChapters().get(2).getTitle()).isEqualTo("Podlove WordPress Plugin");
        assertThat(item.getPscChapters().get().getChapters().get(2).getHref()).hasValue("http://podlove.org/podlove-podcast-publisher");
        assertThat(item.getPscChapters().get().getChapters().get(2).getImage()).hasValue("https://podlove.org/chapter/Podlove-WordPress-Plugin.png");

        assertThat(item.getPscChapters().get().getChapters().get(3).getStart()).isEqualTo("12:42");
        assertThat(item.getPscChapters().get().getChapters().get(3).getStartAsDuration()).isEqualTo(Duration.parse("PT12M42S"));
        assertThat(item.getPscChapters().get().getChapters().get(3).getTitle()).isEqualTo("Resumée");
        assertThat(item.getPscChapters().get().getChapters().get(3).getHref()).isEmpty();
        assertThat(item.getPscChapters().get().getChapters().get(3).getImage()).hasValue("https://podlove.org/chapter/Resumee.png");
    }

    @Test
    @SuppressWarnings("java:S5961")
    void equalsContract() {
        EqualsVerifier.simple().forClass(PscChannelImpl.class).withIgnoredFields("dateTimeParser").withIgnoredFields("category").withNonnullFields("categories").withIgnoredFields("syUpdatePeriod").withIgnoredFields("syUpdateFrequency").verify();
        EqualsVerifier.simple().forClass(PscItemImpl.class).withNonnullFields("pscData").withIgnoredFields("defaultComparator").withIgnoredFields("dateTimeParser").withIgnoredFields("category").withNonnullFields("categories").withIgnoredFields("enclosure").withNonnullFields("enclosures").verify();
        EqualsVerifier.simple().forClass(PscItemImpl.class).withNonnullFields("pscData").withIgnoredFields("defaultComparator").withIgnoredFields("dateTimeParser").withIgnoredFields("category").withNonnullFields("categories").withIgnoredFields("enclosure").withNonnullFields("enclosures").verify();
        EqualsVerifier.simple().forClass(PscItemDataImpl.class).verify();
    }

    private void assertHasFeedItem(PscItem item) {
        if (item instanceof FeedItem) {
            FeedItem feedItem = (FeedItem) item;
            assertFalse(feedItem.hasAtomItem());
            assertFalse(feedItem.hasDcItem());
            assertFalse(feedItem.hasGeoRssItem());
            assertFalse(feedItem.hasItunesItem());
            assertFalse(feedItem.hasMediaRssItem());
            assertFalse(feedItem.hasPodcastItem());
            assertTrue(feedItem.hasPscItem());
            assertFalse(feedItem.hasSlashItem());
            assertFalse(feedItem.hasWfwItem());
            assertFalse(feedItem.hasYoutubeItem());

            FeedChannel feedChannel = feedItem.getChannel();
            assertTrue(feedChannel.hasAtomChannel());
            assertFalse(feedChannel.hasDcChannel());
            assertFalse(feedChannel.hasGeoRssChannel());
            assertFalse(feedChannel.hasItunesChannel());
            assertFalse(feedChannel.hasMediaRssChannel());
            assertFalse(feedChannel.hasOpenSearchChannel());
            assertFalse(feedChannel.hasPodcastChannel());
            assertFalse(feedChannel.hasSpotifyChannel());
            assertFalse(feedChannel.hasYoutubeChannel());
        }
    }

    private static Stream<? extends Arguments> feedReaderArguments() {
        return Stream.of(
                Arguments.of(new PscFeedReader()),
                Arguments.of(new FeedReader())
        );
    }

    private InputStream fromFile(String fileName) {
        return getClass().getClassLoader().getResourceAsStream(fileName);
    }
}
