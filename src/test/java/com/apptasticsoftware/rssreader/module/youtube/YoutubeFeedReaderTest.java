package com.apptasticsoftware.rssreader.module.youtube;

import com.apptasticsoftware.rssreader.AbstractRssReader;
import com.apptasticsoftware.rssreader.FeedItem;
import com.apptasticsoftware.rssreader.FeedReader;
import com.apptasticsoftware.rssreader.module.youtube.internal.YoutubeChannelDataImpl;
import com.apptasticsoftware.rssreader.module.youtube.internal.YoutubeChannelImpl;
import com.apptasticsoftware.rssreader.module.youtube.internal.YoutubeItemDataImpl;
import com.apptasticsoftware.rssreader.module.youtube.internal.YoutubeItemImpl;
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

class YoutubeFeedReaderTest {

    @ParameterizedTest
    @MethodSource("feedReaderArguments")
    @SuppressWarnings("java:S5961")
    void example1(AbstractRssReader<YoutubeChannel, YoutubeItem> feedReader) {
        var items = feedReader.read(fromFile("module/youtube/example1.xml"))
                .collect(Collectors.toList());
        assertEquals(15, items.size());

        items.forEach(this::assertHasFeedItem);

        var item = items.get(0);
        YoutubeChannel channel = (YoutubeChannel) item.getChannel();
        assertThat(channel.getLink()).isEqualTo("https://www.youtube.com/channel/UCX6OQ3DkcsbYNE6H8uQQuVA");
        assertThat(channel.getYoutubeChannelId()).isEqualTo("X6OQ3DkcsbYNE6H8uQQuVA");
        assertThat(channel.getTitle()).isEqualTo("MrBeast");
        assertThat(channel.getManagingEditor()).hasValue("MrBeast");
        assertThat(channel.getPubDate()).hasValue("2012-02-20T00:43:50+00:00");
        assertThat(channel.getPubDateAsZonedDateTime()).hasValue(Default.getDateTimeParser().parse("2012-02-20T00:43:50+00:00"));

        assertThat(item.getGuid()).hasValue("yt:video:fO5FwBcCJBY");
        assertThat(item.getYoutubeVideoId()).isEqualTo("fO5FwBcCJBY");
        assertThat(item.getYoutubeChannelId()).isEqualTo("UCX6OQ3DkcsbYNE6H8uQQuVA");
        assertThat(item.getTitle()).hasValue("Would You Date Him for $10,000?");
        assertThat(item.getLink()).hasValue("https://www.youtube.com/shorts/fO5FwBcCJBY");
        assertThat(item.getAuthor()).hasValue("MrBeast");
        assertThat(item.getPubDate()).hasValue("2025-12-12T17:00:03+00:00");
        assertThat(item.getPubDateAsZonedDateTime()).hasValue(Default.getDateTimeParser().parse("2025-12-12T17:00:03+00:00"));
        assertThat(item.getUpdated()).hasValue("2025-12-12T17:13:26+00:00");
        assertThat(item.getUpdatedAsZonedDateTime()).hasValue(Default.getDateTimeParser().parse("2025-12-12T17:13:26+00:00"));
        assertTrue(item.getMediaGroup().isPresent());
        assertTrue(item.getMediaGroup().get().getMediaTitle().isPresent());
        assertThat(item.getMediaGroup().get().getMediaTitle().get().getTitle()).isEqualTo("Would You Date Him for $10,000?");
        assertThat(item.getMediaGroup().get().getMediaContents()).hasSize(1);
        assertThat(item.getMediaGroup().get().getMediaContents().get(0).getUrl()).hasValue("https://www.youtube.com/v/fO5FwBcCJBY?version=3");
        assertThat(item.getMediaGroup().get().getMediaContents().get(0).getType()).hasValue("application/x-shockwave-flash");
        assertThat(item.getMediaGroup().get().getMediaContents().get(0).getWidth()).hasValue(640);
        assertThat(item.getMediaGroup().get().getMediaContents().get(0).getHeight()).hasValue(390);
        assertThat(item.getMediaGroup().get().getMediaThumbnails()).hasSize(1);
        assertThat(item.getMediaGroup().get().getMediaThumbnails().get(0).getUrl()).isEqualTo("https://i3.ytimg.com/vi/fO5FwBcCJBY/hqdefault.jpg");
        assertThat(item.getMediaGroup().get().getMediaThumbnails().get(0).getWidth()).hasValue(480);
        assertThat(item.getMediaGroup().get().getMediaThumbnails().get(0).getHeight()).hasValue(360);
        assertTrue(item.getMediaGroup().get().getMediaDescription().isEmpty());
        assertTrue(item.getMediaGroup().get().getMediaCommunity().isPresent());
        assertTrue(item.getMediaGroup().get().getMediaCommunity().get().getMediaStarRating().isPresent());
        assertThat(item.getMediaGroup().get().getMediaCommunity().get().getMediaStarRating().get().getCount()).isEqualTo(1223877);
        assertThat(item.getMediaGroup().get().getMediaCommunity().get().getMediaStarRating().get().getAverage()).isEqualTo(5.00);
        assertThat(item.getMediaGroup().get().getMediaCommunity().get().getMediaStarRating().get().getMin()).isEqualTo(1);
        assertThat(item.getMediaGroup().get().getMediaCommunity().get().getMediaStarRating().get().getMax()).isEqualTo(5);
        assertTrue(item.getMediaGroup().get().getMediaCommunity().get().getMediaStatistics().isPresent());
        assertThat(item.getMediaGroup().get().getMediaCommunity().get().getMediaStatistics().get().getViews()).isEqualTo(39516010L);


        item = items.get(14);
        assertThat(item.getGuid()).hasValue("yt:video:26QQE7EsnLA");
        assertThat(item.getYoutubeVideoId()).isEqualTo("26QQE7EsnLA");
        assertThat(item.getYoutubeChannelId()).isEqualTo("UCX6OQ3DkcsbYNE6H8uQQuVA");
        assertThat(item.getTitle()).hasValue("MrBeast Lab Disaster!");
        assertThat(item.getLink()).hasValue("https://www.youtube.com/shorts/26QQE7EsnLA");
        assertThat(item.getAuthor()).hasValue("MrBeast");
        assertThat(item.getPubDate()).hasValue("2025-11-03T17:00:01+00:00");
        assertThat(item.getPubDateAsZonedDateTime()).hasValue(Default.getDateTimeParser().parse("2025-11-03T17:00:01+00:00"));
        assertThat(item.getUpdated()).hasValue("2025-11-10T07:46:31+00:00");
        assertThat(item.getUpdatedAsZonedDateTime()).hasValue(Default.getDateTimeParser().parse("2025-11-10T07:46:31+00:00"));
        assertTrue(item.getMediaGroup().isPresent());
        assertTrue(item.getMediaGroup().get().getMediaTitle().isPresent());
        assertThat(item.getMediaGroup().get().getMediaTitle().get().getTitle()).isEqualTo("MrBeast Lab Disaster!");
        assertThat(item.getMediaGroup().get().getMediaContents()).hasSize(1);
        assertThat(item.getMediaGroup().get().getMediaContents().get(0).getUrl()).hasValue("https://www.youtube.com/v/26QQE7EsnLA?version=3");
        assertThat(item.getMediaGroup().get().getMediaContents().get(0).getType()).hasValue("application/x-shockwave-flash");
        assertThat(item.getMediaGroup().get().getMediaContents().get(0).getWidth()).hasValue(640);
        assertThat(item.getMediaGroup().get().getMediaContents().get(0).getHeight()).hasValue(390);
        assertThat(item.getMediaGroup().get().getMediaThumbnails()).hasSize(1);
        assertThat(item.getMediaGroup().get().getMediaThumbnails().get(0).getUrl()).isEqualTo("https://i3.ytimg.com/vi/26QQE7EsnLA/hqdefault.jpg");
        assertThat(item.getMediaGroup().get().getMediaThumbnails().get(0).getWidth()).hasValue(480);
        assertThat(item.getMediaGroup().get().getMediaThumbnails().get(0).getHeight()).hasValue(360);
        assertTrue(item.getMediaGroup().get().getMediaDescription().isEmpty());
        assertTrue(item.getMediaGroup().get().getMediaCommunity().isPresent());
        assertTrue(item.getMediaGroup().get().getMediaCommunity().get().getMediaStarRating().isPresent());
        assertThat(item.getMediaGroup().get().getMediaCommunity().get().getMediaStarRating().get().getCount()).isEqualTo(1095788);
        assertThat(item.getMediaGroup().get().getMediaCommunity().get().getMediaStarRating().get().getAverage()).isEqualTo(5.00);
        assertThat(item.getMediaGroup().get().getMediaCommunity().get().getMediaStarRating().get().getMin()).isEqualTo(1);
        assertThat(item.getMediaGroup().get().getMediaCommunity().get().getMediaStarRating().get().getMax()).isEqualTo(5);
        assertTrue(item.getMediaGroup().get().getMediaCommunity().get().getMediaStatistics().isPresent());
        assertThat(item.getMediaGroup().get().getMediaCommunity().get().getMediaStatistics().get().getViews()).isEqualTo(26775512L);
    }

    @Test
    @SuppressWarnings("java:S5961")
    void equalsContract() {
        EqualsVerifier.simple().forClass(YoutubeChannelImpl.class).withNonnullFields("youtubeData", "mediaRssData").withIgnoredFields("dateTimeParser").withIgnoredFields("category").withNonnullFields("categories").withIgnoredFields("syUpdatePeriod").withIgnoredFields("syUpdateFrequency").verify();
        EqualsVerifier.simple().forClass(YoutubeChannelDataImpl.class).verify();
        EqualsVerifier.simple().forClass(YoutubeItemImpl.class).withIgnoredFields("defaultComparator").withIgnoredFields("dateTimeParser").withIgnoredFields("category").withNonnullFields("categories").withIgnoredFields("enclosure").withNonnullFields("enclosures").verify();
        EqualsVerifier.simple().forClass(YoutubeItemDataImpl.class).verify();
    }

    private void assertHasFeedItem(YoutubeItem item) {
        if (item instanceof FeedItem) {
            FeedItem feedItem = (FeedItem) item;
            assertFalse(feedItem.hasAtomItem());
            assertFalse(feedItem.hasDcItem());
            assertFalse(feedItem.hasGeoRssItem());
            assertFalse(feedItem.hasItunesItem());
            assertTrue(feedItem.hasMediaRssItem());
            assertFalse(feedItem.hasPodcastItem());
            assertFalse(feedItem.hasPscItem());
            assertFalse(feedItem.hasSlashItem());
            assertFalse(feedItem.hasWfwItem());
            assertTrue(feedItem.hasYoutubeItem());
        }
    }

    private static Stream<? extends Arguments> feedReaderArguments() {
        return Stream.of(
                Arguments.of(new YoutubeFeedReader()),
                Arguments.of(new FeedReader())
        );
    }

    private InputStream fromFile(String fileName) {
        return getClass().getClassLoader().getResourceAsStream(fileName);
    }
}
