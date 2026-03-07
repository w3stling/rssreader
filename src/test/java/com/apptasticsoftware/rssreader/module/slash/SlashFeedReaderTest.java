package com.apptasticsoftware.rssreader.module.slash;

import com.apptasticsoftware.rssreader.AbstractRssReader;
import com.apptasticsoftware.rssreader.FeedItem;
import com.apptasticsoftware.rssreader.FeedReader;
import com.apptasticsoftware.rssreader.module.slash.internal.SlashItemDataImpl;
import com.apptasticsoftware.rssreader.module.slash.internal.SlashItemImpl;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.InputStream;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SuppressWarnings("java:S5961")
class SlashFeedReaderTest {

    @ParameterizedTest
    @MethodSource("feedReaderArguments")
    void example1(AbstractRssReader<SlashChannel, SlashItem> feedReader) {
        var items = feedReader.read(fromFile("module/slash/example1.xml")).collect(Collectors.toList());
        assertEquals(1, items.size());

        var item = items.get(0);
        assertHasFeedItem(item);
        var channel = item.getChannel();
        assertThat(channel.getTitle()).isEqualTo("Slashdot");
        assertThat(channel.getDescription()).isEqualTo("News for nerds, stuff that matters");

        assertThat(item.getTitle()).hasValue("Jupiter Moon Ganymede May Have An Ocean");
        assertThat(item.getLink()).hasValue("http://slashdot.org/article.pl?sid=00/12/17/0622203");
        assertThat(item.getDescription()).hasValueSatisfying(v -> assertThat(v).startsWith("This article talks about how Jupiter's moon, Ganymede, may have a"));
        assertThat(item.getSlashSection()).hasValue("articles");
        assertThat(item.getSlashDepartment()).hasValue("not-an-ocean-unless-there-are-lobsters");
        assertThat(item.getSlashComments()).hasValue(177);
        assertThat(item.getSlashHitParade()).hasValue("177,155,105,33,6,3,0");
    }

    @Test
    void equalsContract() {
        EqualsVerifier.simple().forClass(SlashItemImpl.class).withNonnullFields("slashData").withIgnoredFields("defaultComparator").withIgnoredFields("dateTimeParser").withIgnoredFields("category").withNonnullFields("categories").withIgnoredFields("enclosure").withNonnullFields("enclosures").verify();
        EqualsVerifier.simple().forClass(SlashItemDataImpl.class).verify();
    }

    private void assertHasFeedItem(SlashItem item) {
        if (item instanceof FeedItem) {
            FeedItem feedItem = (FeedItem) item;
            assertFalse(feedItem.hasAtomItem());
            assertTrue(feedItem.hasDcItem());
            assertFalse(feedItem.hasGeoRssItem());
            assertFalse(feedItem.hasItunesItem());
            assertFalse(feedItem.hasMediaRssItem());
            assertFalse(feedItem.hasPodcastItem());
            assertFalse(feedItem.hasPscItem());
            assertTrue(feedItem.hasSlashItem());
            assertFalse(feedItem.hasWfwItem());
            assertFalse(feedItem.hasYoutubeItem());
        }
    }

    private static Stream<? extends Arguments> feedReaderArguments() {
        return Stream.of(
                Arguments.of(new SlashFeedReader()),
                Arguments.of(new FeedReader())
        );
    }

    private InputStream fromFile(String fileName) {
        return getClass().getClassLoader().getResourceAsStream(fileName);
    }
}
