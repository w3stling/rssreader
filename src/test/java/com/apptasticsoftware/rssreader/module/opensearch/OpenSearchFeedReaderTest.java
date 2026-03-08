package com.apptasticsoftware.rssreader.module.opensearch;

import com.apptasticsoftware.rssreader.AbstractRssReader;
import com.apptasticsoftware.rssreader.FeedChannel;
import com.apptasticsoftware.rssreader.FeedItem;
import com.apptasticsoftware.rssreader.FeedReader;
import com.apptasticsoftware.rssreader.module.opensearch.internal.OpenSearchChannelDataImpl;
import com.apptasticsoftware.rssreader.module.opensearch.internal.OpenSearchChannelImpl;
import com.apptasticsoftware.rssreader.module.opensearch.internal.OpenSearchItemImpl;
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

class OpenSearchFeedReaderTest {

    @ParameterizedTest
    @MethodSource("feedReaderArguments")
    void example1(AbstractRssReader<OpenSearchChannel, OpenSearchItem> feedReader) {
        var items = feedReader.read(fromFile("module/opensearch/example1.xml"))
                .collect(Collectors.toList());

        assertEquals(1, items.size());
        var item = items.get(0);
        assertHasFeedItem(item);
        OpenSearchChannel channel = (OpenSearchChannel) item.getChannel();

        assertThat(channel.getTitle()).isEqualTo("Example.com Search: New York history");
        assertThat(channel.getLink()).isEqualTo("http://example.com/New+York+history");
        assertThat(channel.getDescription()).isEqualTo("Search results for \"New York history\" at Example.com");
        assertThat(channel.getOpenSearchTotalResults()).hasValue(4230000);
        assertThat(channel.getOpenSearchStartIndex()).hasValue(21);
        assertThat(channel.getOpenSearchItemsPerPage()).hasValue(10);
        assertThat(channel.getOpenSearchQueries()).hasSize(1);
        assertThat(channel.getOpenSearchQueries().get(0).getRole()).isEqualTo("request");
        assertThat(channel.getOpenSearchQueries().get(0).getTitle()).hasValue("History");
        assertThat(channel.getOpenSearchQueries().get(0).getTotalResults()).hasValue(10);
        assertThat(channel.getOpenSearchQueries().get(0).getSearchTerms()).hasValue("New York History");
        assertThat(channel.getOpenSearchQueries().get(0).getCount()).hasValue(15);
        assertThat(channel.getOpenSearchQueries().get(0).getStartIndex()).hasValue(0);
        assertThat(channel.getOpenSearchQueries().get(0).getStartPage()).hasValue(1);
        assertThat(channel.getOpenSearchQueries().get(0).getLanguage()).hasValue("en");
        assertThat(channel.getOpenSearchQueries().get(0).getInputEncoding()).hasValue("UTF-8");
        assertThat(channel.getOpenSearchQueries().get(0).getOutputEncoding()).hasValue("ISO-88859-1");

        assertThat(item.getTitle()).hasValue("New York History");
        assertThat(item.getLink()).hasValue("http://www.columbia.edu/cu/lweb/eguids/amerihist/nyc.html");
        assertThat(item.getDescription()).hasValueSatisfying(v -> assertThat(v).contains("... Harlem.NYC - A virtual tour and information on"));
    }

    @ParameterizedTest
    @MethodSource("feedReaderArguments")
    void example2(AbstractRssReader<OpenSearchChannel, OpenSearchItem> feedReader) {
        var items = feedReader.read(fromFile("module/opensearch/example2.xml"))
                .collect(Collectors.toList());

        assertEquals(1, items.size());
        var item = items.get(0);
        OpenSearchChannel channel = (OpenSearchChannel) item.getChannel();

        assertThat(channel.getTitle()).isEqualTo("Example.com Search: New York history");
        assertThat(channel.getLink()).isEqualTo("http://example.com/opensearchdescription.xml");
        assertThat(channel.getLastBuildDate()).hasValue("2003-12-13T18:30:02Z");
        assertThat(channel.getOpenSearchTotalResults()).hasValue(4230000);
        assertThat(channel.getOpenSearchStartIndex()).hasValue(21);
        assertThat(channel.getOpenSearchItemsPerPage()).hasValue(10);
        assertThat(channel.getOpenSearchQueries().get(0).getRole()).isEqualTo("request");
        assertThat(channel.getOpenSearchQueries().get(0).getTitle()).hasValue("History");
        assertThat(channel.getOpenSearchQueries().get(0).getTotalResults()).hasValue(10);
        assertThat(channel.getOpenSearchQueries().get(0).getSearchTerms()).hasValue("New York History");
        assertThat(channel.getOpenSearchQueries().get(0).getCount()).hasValue(15);
        assertThat(channel.getOpenSearchQueries().get(0).getStartIndex()).hasValue(0);
        assertThat(channel.getOpenSearchQueries().get(0).getStartPage()).hasValue(1);
        assertThat(channel.getOpenSearchQueries().get(0).getLanguage()).hasValue("en");
        assertThat(channel.getOpenSearchQueries().get(0).getInputEncoding()).hasValue("UTF-8");
        assertThat(channel.getOpenSearchQueries().get(0).getOutputEncoding()).hasValue("ISO-88859-1");

        assertThat(item.getTitle()).hasValue("New York History");
        assertThat(item.getLink()).hasValue("http://www.columbia.edu/cu/lweb/eguids/amerihist/nyc.html");
        assertThat(item.getDescription()).hasValueSatisfying(v -> assertThat(v).contains("... Harlem.NYC - A virtual tour and information on"));
    }

    @Test
    @SuppressWarnings("java:S5961")
    void equalsContract() {
        EqualsVerifier.simple().forClass(OpenSearchChannelImpl.class).withNonnullFields("openSearchData").withIgnoredFields("dateTimeParser").withIgnoredFields("category").withNonnullFields("categories").withIgnoredFields("syUpdatePeriod").withIgnoredFields("syUpdateFrequency").verify();
        EqualsVerifier.simple().forClass(OpenSearchItemImpl.class).withIgnoredFields("defaultComparator").withIgnoredFields("dateTimeParser").withIgnoredFields("category").withNonnullFields("categories").withIgnoredFields("enclosure").withNonnullFields("enclosures").verify();
        EqualsVerifier.simple().forClass(OpenSearchChannelDataImpl.class).verify();
        EqualsVerifier.simple().forClass(OpenSearchQuery.class).verify();
    }

    private void assertHasFeedItem(OpenSearchItem item) {
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
            assertFalse(feedItem.hasWfwItem());
            assertFalse(feedItem.hasYoutubeItem());

            FeedChannel feedChannel = feedItem.getChannel();
            assertTrue(feedChannel.hasAtomChannel());
            assertFalse(feedChannel.hasDcChannel());
            assertFalse(feedChannel.hasGeoRssChannel());
            assertFalse(feedChannel.hasItunesChannel());
            assertFalse(feedChannel.hasMediaRssChannel());
            assertTrue(feedChannel.hasOpenSearchChannel());
            assertFalse(feedChannel.hasPodcastChannel());
            assertFalse(feedChannel.hasSpotifyChannel());
            assertFalse(feedChannel.hasYoutubeChannel());
        }
    }

    private static Stream<? extends Arguments> feedReaderArguments() {
        return Stream.of(
                Arguments.of(new OpenSearchFeedReader()),
                Arguments.of(new FeedReader())
        );
    }

    private InputStream fromFile(String fileName) {
        return getClass().getClassLoader().getResourceAsStream(fileName);
    }
}
