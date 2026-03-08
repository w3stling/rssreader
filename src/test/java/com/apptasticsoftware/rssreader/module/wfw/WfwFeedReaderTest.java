package com.apptasticsoftware.rssreader.module.wfw;

import com.apptasticsoftware.rssreader.AbstractRssReader;
import com.apptasticsoftware.rssreader.FeedChannel;
import com.apptasticsoftware.rssreader.FeedItem;
import com.apptasticsoftware.rssreader.FeedReader;
import com.apptasticsoftware.rssreader.module.wfw.internal.WfwItemDataImpl;
import com.apptasticsoftware.rssreader.module.wfw.internal.WfwItemImpl;
import com.apptasticsoftware.rssreader.util.Default;
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

class WfwFeedReaderTest {

    @ParameterizedTest
    @MethodSource("feedReaderArguments")
    void example1(AbstractRssReader<WfwChannel, WfwItem> feedReader) {
        var items = feedReader.read(fromFile("module/wfw/example1.xml"))
                .collect(Collectors.toList());

        assertEquals(1, items.size());
        var item = items.get(0);
        assertHasFeedItem(item);
        var channel = item.getChannel();
        assertThat(channel.getTitle()).isEqualTo("Example Website");
        assertThat(channel.getLink()).isEqualTo("https://www.example.com");
        assertThat(channel.getDescription()).isEqualTo("Latest news and updates");

        assertThat(item.getTitle()).hasValue("First Sample Post");
        assertThat(item.getLink()).hasValue("https://www.example.com/post1");
        assertThat(item.getDescription()).hasValue("This is a summary of the first post.");
        assertThat(item.getPubDate()).hasValue("Mon, 27 May 2024 10:00:00 GMT");
        assertThat(item.getPubDateAsZonedDateTime()).hasValue(Default.getDateTimeParser().parse("Mon, 27 May 2024 10:00:00 GMT"));
        assertThat(item.getWfwCommentRss()).hasValue("https://ekzemplo.com/news/130/comments.xml");
        assertThat(item.getWfwComment()).hasValue("https://ekzemplo.com/comment?post=130");
    }

    private void assertHasFeedItem(WfwItem item) {
        if (item instanceof FeedItem) {
            FeedItem feedItem = (FeedItem) item;
            assertFalse(feedItem.hasAtomItem());
            assertFalse(feedItem.hasDcItem());
            assertFalse(feedItem.hasGeoRssItem());
            assertFalse(feedItem.hasItunesItem());
            assertFalse(feedItem.hasMediaRssItem());
            assertFalse(feedItem.hasPodcastItem());
            assertFalse(feedItem.hasPscItem());
            assertFalse(feedItem.hasSlashItem());
            assertTrue(feedItem.hasWfwItem());
            assertFalse(feedItem.hasYoutubeItem());

            FeedChannel feedChannel = feedItem.getChannel();
            assertFalse(feedChannel.hasAtomChannel());
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

    @Test
    void equalsContract() {
        EqualsVerifier.simple().forClass(WfwItemImpl.class).withNonnullFields("wfwData").withIgnoredFields("defaultComparator").withIgnoredFields("dateTimeParser").withIgnoredFields("category").withNonnullFields("categories").withIgnoredFields("enclosure").withNonnullFields("enclosures").withIgnoredFields("channel").verify();
        EqualsVerifier.simple().forClass(WfwItemDataImpl.class).verify();
    }

    private static Stream<? extends Arguments> feedReaderArguments() {
        return Stream.of(
                Arguments.of(new WfwFeedReader()),
                Arguments.of(new FeedReader())
        );
    }

    private InputStream fromFile(String fileName) {
        return getClass().getClassLoader().getResourceAsStream(fileName);
    }
}
