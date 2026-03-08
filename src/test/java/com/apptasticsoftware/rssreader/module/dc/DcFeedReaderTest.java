package com.apptasticsoftware.rssreader.module.dc;

import com.apptasticsoftware.rssreader.FeedChannel;
import com.apptasticsoftware.rssreader.FeedItem;
import com.apptasticsoftware.rssreader.FeedReader;
import com.apptasticsoftware.rssreader.module.dc.internal.DcChannelDataImpl;
import com.apptasticsoftware.rssreader.module.dc.internal.DcChannelImpl;
import com.apptasticsoftware.rssreader.module.dc.internal.DcItemDataImpl;
import com.apptasticsoftware.rssreader.module.dc.internal.DcItemImpl;
import com.apptasticsoftware.rssreader.module.georss.internal.*;
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
class DcFeedReaderTest {

    @ParameterizedTest
    @MethodSource("feedReaderArguments")
    void example1() {
        var items = new DcFeedReader().read(fromFile("module/dc/example1.xml")).collect(Collectors.toList());
        assertEquals(1, items.size());

        var item = items.get(0);
        assertHasFeedItem(item);
        var channel = (DcChannel) item.getChannel();

        assertThat(channel.getTitle()).isEqualTo("Meerkat");
        assertThat(channel.getLink()).isEqualTo("http://meerkat.oreillynet.com");
        assertThat(channel.getDescription()).isEqualTo("Meerkat: An Open Wire Service");
        assertThat(channel.getDcTitle()).hasValue("Channel title");
        assertThat(channel.getTitle()).isEqualTo("Meerkat");
        assertThat(channel.getDcCreator()).hasValue("Rael Dornfest (mailto:rael@oreilly.com)");
        assertThat(channel.getManagingEditor()).hasValue("Rael Dornfest (mailto:rael@oreilly.com)");
        assertThat(channel.getDcSubject()).hasValue("Channel subject");
        assertThat(channel.getDcDescription()).hasValue("Channel description");
        assertThat(channel.getDcPublisher()).hasValue("The O'Reilly Network");
        assertThat(channel.getDcContributor()).hasValue("Channel contributor");
        assertThat(channel.getDcDate()).hasValue("2000-01-01T12:00+00:00");
        assertThat(channel.getPubDate()).hasValue("2000-01-01T12:00+00:00");
        assertThat(channel.getDcType()).hasValue("Channel type");
        assertThat(channel.getDcFormat()).hasValue("Channel format");
        assertThat(channel.getDcIdentifier()).hasValue("Channel identifier");
        assertThat(channel.getDcSource()).hasValue("Channel source");
        assertThat(channel.getDcLanguage()).hasValue("en");
        assertThat(channel.getLanguage()).hasValue("en");
        assertThat(channel.getDcRelation()).hasValue("Channel relation");
        assertThat(channel.getDcCoverage()).hasValue("Channel coverage");
        assertThat(channel.getDcRights()).hasValue("Copyright © 2000 O'Reilly & Associates, Inc.");
        assertThat(channel.getCopyright()).hasValue("Copyright © 2000 O'Reilly & Associates, Inc.");

        assertThat(item.getTitle()).hasValue("XML: A Disruptive Technology");
        assertThat(item.getDcTitle()).hasValue("Item title");
        assertThat(item.getDcCreator()).hasValue("Simon St.Laurent (mailto:simonstl@simonstl.com)");
        assertThat(item.getAuthor()).hasValue("Simon St.Laurent (mailto:simonstl@simonstl.com)");
        assertThat(item.getDcSubject()).hasValue("XML");
        assertThat(item.getDcDescription()).hasValueSatisfying(v -> assertThat(v).contains("XML is placing increasingly heavy loads on the existing technical"));
        assertThat(item.getDcPublisher()).hasValue("The O'Reilly Network");
        assertThat(item.getDcContributor()).hasValue("Item contributor");
        assertThat(item.getDcDate()).hasValue("2000-01-01T12:00+00:00");
        assertThat(item.getPubDate()).hasValue("2000-01-01T12:00+00:00");
        assertThat(item.getDcType()).hasValue("Item type");
        assertThat(item.getDcFormat()).hasValue("Item format");
        assertThat(item.getDcIdentifier()).hasValue("Item identifier");
        assertThat(item.getGuid()).hasValue("Item identifier");
        assertThat(item.getDcSource()).hasValue("Item source");
        assertThat(item.getDcLanguage()).hasValue("en-us");
        assertThat(item.getDcRelation()).hasValue("Item relation");
        assertThat(item.getDcCoverage()).hasValue("Item coverage");
        assertThat(item.getDcRights()).hasValue("Copyright © 2000 O'Reilly & Associates, Inc.");
    }

    @Test
    void equalsContract() {
        EqualsVerifier.simple().forClass(DcChannelImpl.class).withNonnullFields("dcData").withIgnoredFields("dateTimeParser").withIgnoredFields("category").withNonnullFields("categories").withIgnoredFields("syUpdatePeriod").withIgnoredFields("syUpdateFrequency").verify();
        EqualsVerifier.simple().forClass(DcChannelDataImpl.class).verify();
        EqualsVerifier.simple().forClass(DcItemImpl.class).withNonnullFields("dcData").withIgnoredFields("defaultComparator").withIgnoredFields("dateTimeParser").withIgnoredFields("category").withNonnullFields("categories").withIgnoredFields("enclosure").withNonnullFields("enclosures").verify();
        EqualsVerifier.simple().forClass(DcItemDataImpl.class).verify();
        EqualsVerifier.simple().forClass(MetaData.class).verify();
    }

    private void assertHasFeedItem(DcItem item) {
        if (item instanceof FeedItem) {
            FeedItem feedItem = (FeedItem) item;
            assertFalse(feedItem.hasAtomItem());
            assertTrue(feedItem.hasDcItem());
            assertFalse(feedItem.hasGeoRssItem());
            assertFalse(feedItem.hasItunesItem());
            assertFalse(feedItem.hasMediaRssItem());
            assertFalse(feedItem.hasPodcastItem());
            assertFalse(feedItem.hasPscItem());
            assertFalse(feedItem.hasSlashItem());
            assertFalse(feedItem.hasWfwItem());
            assertFalse(feedItem.hasYoutubeItem());

            FeedChannel feedChannel = feedItem.getChannel();
            assertFalse(feedChannel.hasAtomChannel());
            assertTrue(feedChannel.hasDcChannel());
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
            Arguments.of(new DcFeedReader()),
            Arguments.of(new FeedReader())
        );
    }

    private InputStream fromFile(String fileName) {
        return getClass().getClassLoader().getResourceAsStream(fileName);
    }
}
