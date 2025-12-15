package com.apptasticsoftware.rssreader.module.youtube;

import com.apptasticsoftware.rssreader.util.Default;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.stream.Collectors;

import static com.github.npathai.hamcrestopt.OptionalMatchers.isPresentAndIs;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class YoutubeFeedReaderTest {

    @Test
    @SuppressWarnings("java:S5961")
    void example1() {
        var items = new YoutubeFeedReader().read(fromFile("youtube/example1.xml"))
                .collect(Collectors.toList());
        assertEquals(15, items.size());

        var item = items.get(0);
        YoutubeChannel channel = (YoutubeChannel) item.getChannel();
        assertThat(channel.getLink(), is("https://www.youtube.com/channel/UCX6OQ3DkcsbYNE6H8uQQuVA"));
        assertThat(channel.getYoutubeChannelId(), is("X6OQ3DkcsbYNE6H8uQQuVA"));
        assertThat(channel.getTitle(), is("MrBeast"));
        assertThat(channel.getManagingEditor(), isPresentAndIs("MrBeast"));
        assertThat(channel.getPubDate(), isPresentAndIs("2012-02-20T00:43:50+00:00"));
        assertThat(channel.getPubDateAsZonedDateTime(), isPresentAndIs(Default.getDateTimeParser().parse("2012-02-20T00:43:50+00:00")));

        assertThat(item.getGuid(), isPresentAndIs("yt:video:fO5FwBcCJBY"));
        assertThat(item.getYoutubeVideoId(), is("fO5FwBcCJBY"));
        assertThat(item.getYoutubeChannelId(), is("UCX6OQ3DkcsbYNE6H8uQQuVA"));
        assertThat(item.getTitle(), isPresentAndIs("Would You Date Him for $10,000?"));
        assertThat(item.getLink(), isPresentAndIs("https://www.youtube.com/shorts/fO5FwBcCJBY"));
        assertThat(item.getAuthor(), isPresentAndIs("MrBeast"));
        assertThat(item.getPubDate(), isPresentAndIs("2025-12-12T17:00:03+00:00"));
        assertThat(item.getPubDateAsZonedDateTime(), isPresentAndIs(Default.getDateTimeParser().parse("2025-12-12T17:00:03+00:00")));
        assertThat(item.getUpdated(), isPresentAndIs("2025-12-12T17:13:26+00:00"));
        assertThat(item.getUpdatedAsZonedDateTime(), isPresentAndIs(Default.getDateTimeParser().parse("2025-12-12T17:13:26+00:00")));
        assertTrue(item.getMediaGroup().isPresent());
        assertTrue(item.getMediaGroup().get().getMediaTitle().isPresent());
        assertThat(item.getMediaGroup().get().getMediaTitle().get().getTitle(), is("Would You Date Him for $10,000?"));
        assertThat(item.getMediaGroup().get().getMediaContents().size(), is(1));
        assertThat(item.getMediaGroup().get().getMediaContents().get(0).getUrl(), isPresentAndIs("https://www.youtube.com/v/fO5FwBcCJBY?version=3"));
        assertThat(item.getMediaGroup().get().getMediaContents().get(0).getType(), isPresentAndIs("application/x-shockwave-flash"));
        assertThat(item.getMediaGroup().get().getMediaContents().get(0).getWidth(), isPresentAndIs(640));
        assertThat(item.getMediaGroup().get().getMediaContents().get(0).getHeight(), isPresentAndIs(390));
        assertThat(item.getMediaGroup().get().getMediaThumbnails().size(), is(1));
        assertThat(item.getMediaGroup().get().getMediaThumbnails().get(0).getUrl(), is("https://i3.ytimg.com/vi/fO5FwBcCJBY/hqdefault.jpg"));
        assertThat(item.getMediaGroup().get().getMediaThumbnails().get(0).getWidth(), isPresentAndIs(480));
        assertThat(item.getMediaGroup().get().getMediaThumbnails().get(0).getHeight(), isPresentAndIs(360));
        assertTrue(item.getMediaGroup().get().getMediaDescription().isEmpty());
        assertTrue(item.getMediaGroup().get().getMediaCommunity().isPresent());
        assertTrue(item.getMediaGroup().get().getMediaCommunity().get().getMediaStarRating().isPresent());
        assertThat(item.getMediaGroup().get().getMediaCommunity().get().getMediaStarRating().get().getCount(), is(1223877));
        assertThat(item.getMediaGroup().get().getMediaCommunity().get().getMediaStarRating().get().getAverage(), is(5.00));
        assertThat(item.getMediaGroup().get().getMediaCommunity().get().getMediaStarRating().get().getMin(), is(1));
        assertThat(item.getMediaGroup().get().getMediaCommunity().get().getMediaStarRating().get().getMax(), is(5));
        assertTrue(item.getMediaGroup().get().getMediaCommunity().get().getMediaStatistics().isPresent());
        assertThat(item.getMediaGroup().get().getMediaCommunity().get().getMediaStatistics().get().getViews(), is(39516010L));


        item = items.get(14);
        assertThat(item.getGuid(), isPresentAndIs("yt:video:26QQE7EsnLA"));
        assertThat(item.getYoutubeVideoId(), is("26QQE7EsnLA"));
        assertThat(item.getYoutubeChannelId(), is("UCX6OQ3DkcsbYNE6H8uQQuVA"));
        assertThat(item.getTitle(), isPresentAndIs("MrBeast Lab Disaster!"));
        assertThat(item.getLink(), isPresentAndIs("https://www.youtube.com/shorts/26QQE7EsnLA"));
        assertThat(item.getAuthor(), isPresentAndIs("MrBeast"));
        assertThat(item.getPubDate(), isPresentAndIs("2025-11-03T17:00:01+00:00"));
        assertThat(item.getPubDateAsZonedDateTime(), isPresentAndIs(Default.getDateTimeParser().parse("2025-11-03T17:00:01+00:00")));
        assertThat(item.getUpdated(), isPresentAndIs("2025-11-10T07:46:31+00:00"));
        assertThat(item.getUpdatedAsZonedDateTime(), isPresentAndIs(Default.getDateTimeParser().parse("2025-11-10T07:46:31+00:00")));
        assertTrue(item.getMediaGroup().isPresent());
        assertTrue(item.getMediaGroup().get().getMediaTitle().isPresent());
        assertThat(item.getMediaGroup().get().getMediaTitle().get().getTitle(), is("MrBeast Lab Disaster!"));
        assertThat(item.getMediaGroup().get().getMediaContents().size(), is(1));
        assertThat(item.getMediaGroup().get().getMediaContents().get(0).getUrl(), isPresentAndIs("https://www.youtube.com/v/26QQE7EsnLA?version=3"));
        assertThat(item.getMediaGroup().get().getMediaContents().get(0).getType(), isPresentAndIs("application/x-shockwave-flash"));
        assertThat(item.getMediaGroup().get().getMediaContents().get(0).getWidth(), isPresentAndIs(640));
        assertThat(item.getMediaGroup().get().getMediaContents().get(0).getHeight(), isPresentAndIs(390));
        assertThat(item.getMediaGroup().get().getMediaThumbnails().size(), is(1));
        assertThat(item.getMediaGroup().get().getMediaThumbnails().get(0).getUrl(), is("https://i3.ytimg.com/vi/26QQE7EsnLA/hqdefault.jpg"));
        assertThat(item.getMediaGroup().get().getMediaThumbnails().get(0).getWidth(), isPresentAndIs(480));
        assertThat(item.getMediaGroup().get().getMediaThumbnails().get(0).getHeight(), isPresentAndIs(360));
        assertTrue(item.getMediaGroup().get().getMediaDescription().isEmpty());
        assertTrue(item.getMediaGroup().get().getMediaCommunity().isPresent());
        assertTrue(item.getMediaGroup().get().getMediaCommunity().get().getMediaStarRating().isPresent());
        assertThat(item.getMediaGroup().get().getMediaCommunity().get().getMediaStarRating().get().getCount(), is(1095788));
        assertThat(item.getMediaGroup().get().getMediaCommunity().get().getMediaStarRating().get().getAverage(), is(5.00));
        assertThat(item.getMediaGroup().get().getMediaCommunity().get().getMediaStarRating().get().getMin(), is(1));
        assertThat(item.getMediaGroup().get().getMediaCommunity().get().getMediaStarRating().get().getMax(), is(5));
        assertTrue(item.getMediaGroup().get().getMediaCommunity().get().getMediaStatistics().isPresent());
        assertThat(item.getMediaGroup().get().getMediaCommunity().get().getMediaStatistics().get().getViews(), is(26775512L));
    }

    @Test
    @SuppressWarnings("java:S5961")
    void equalsContract() {
        EqualsVerifier.simple().forClass(YoutubeChannelImpl.class).withNonnullFields("youtubeData", "mediaRssData").withIgnoredFields("dateTimeParser").withIgnoredFields("category").withNonnullFields("categories").withIgnoredFields("syUpdatePeriod").withIgnoredFields("syUpdateFrequency").verify();
        EqualsVerifier.simple().forClass(YoutubeItemImpl.class).withIgnoredFields("defaultComparator").withIgnoredFields("dateTimeParser").withIgnoredFields("category").withNonnullFields("categories").withIgnoredFields("enclosure").withNonnullFields("enclosures").verify();
    }

    private InputStream fromFile(String fileName) {
        return getClass().getClassLoader().getResourceAsStream(fileName);
    }
}
