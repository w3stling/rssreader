package com.apptasticsoftware.rssreader.module.atom;

import com.apptasticsoftware.rssreader.AbstractRssReader;
import com.apptasticsoftware.rssreader.FeedItem;
import com.apptasticsoftware.rssreader.FeedReader;
import com.apptasticsoftware.rssreader.module.atom.internal.*;
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


class AtomFeedReaderTest {

    @SuppressWarnings("java:S5961")
    @ParameterizedTest
    @MethodSource("feedReaderArguments")
    void example1(AbstractRssReader<AtomChannel, AtomItem> feedReader) {
        var items = feedReader.read(fromFile("module/atom/example1.xml")).collect(Collectors.toList());
        assertEquals(1, items.size());

        // Verify channel atom:link elements
        var channel = (AtomChannel) items.get(0).getChannel();

        // Verify item link
        assertThat(channel.getLink()).isEqualTo("https://technewsdaily.com");

        var channelLinks = channel.getAtomLinks();
        assertEquals(2, channelLinks.size());

        var channelLink1 = channelLinks.get(0);
        assertEquals("https://technewsdaily.com/feed", channelLink1.getHref());
        assertEquals("application/rss+xml", channelLink1.getType().orElse(null));
        assertEquals("self", channelLink1.getRel().orElse(null));

        var channelLink2 = channelLinks.get(1);
        assertEquals("https://technewsdaily.com/feed/alt", channelLink2.getHref());
        assertEquals("application/rss+xml", channelLink2.getType().orElse(null));
        assertEquals("alternate", channelLink2.getRel().orElse(null));

        // Verify channel atom:author
        var channelAuthors = channel.getAtomAuthors();
        assertEquals(1, channelAuthors.size());
        var channelAuthor = channelAuthors.get(0);
        assertEquals("Jane Doe", channelAuthor.getName());
        assertEquals("jane.doe@technewsdaily.com", channelAuthor.getEmail());
        assertEquals("https://technewsdaily.com/authors/jane-doe", channelAuthor.getUri());

        // Verify channel atom:contributor
        var channelContributors = channel.getAtomContributors();
        assertEquals(1, channelContributors.size());
        var channelContributor = channelContributors.get(0);
        assertEquals("John Smith", channelContributor.getName());
        assertEquals("john.smith@technewsdaily.com", channelContributor.getEmail());
        assertEquals("https://technewsdaily.com/authors/john-smith", channelContributor.getUri());

        var atomItem = items.get(0);
        assertHasFeedItem(atomItem);

        // Verify item link
        assertThat(atomItem.getLink()).hasValue("https://technewsdaily.com/articles/new-programming-language");

        // Verify item atom:link
        var itemLinks = atomItem.getAtomLinks();
        assertEquals(1, itemLinks.size());
        var itemLink = itemLinks.get(0);
        assertEquals("https://technewsdaily.com/articles/new-programming-language/feed", itemLink.getHref());
        assertEquals("application/rss+xml", itemLink.getType().orElse(null));
        assertEquals("self", itemLink.getRel().orElse(null));
        assertEquals("en", itemLink.getHrefLang().orElse(null));
        assertEquals(900, itemLink.getLength().orElse(null));
        assertEquals("Some title", itemLink.getTitle().orElse(null));

        // Verify item atom:author
        var itemAuthors = atomItem.getAtomAuthors();
        assertEquals(1, itemAuthors.size());
        var itemAuthor = itemAuthors.get(0);
        assertEquals("Alice Johnson", itemAuthor.getName());
        assertEquals("alice.johnson@technewsdaily.com", itemAuthor.getEmail());
        assertEquals("https://technewsdaily.com/authors/alice-johnson", itemAuthor.getUri());

        // Verify item atom:contributor
        var itemContributors = atomItem.getAtomContributors();
        assertEquals(1, itemContributors.size());
        var itemContributor = itemContributors.get(0);
        assertEquals("Bob Williams", itemContributor.getName());
        assertEquals("bob.williams@technewsdaily.com", itemContributor.getEmail());
        assertEquals("https://technewsdaily.com/authors/bob-williams", itemContributor.getUri());
    }

    @Test
    void equalsContract() {
        EqualsVerifier.simple().forClass(AtomChannelImpl.class).withNonnullFields("atomData").withIgnoredFields("dateTimeParser").withIgnoredFields("category").withNonnullFields("categories").withIgnoredFields("syUpdatePeriod").withIgnoredFields("syUpdateFrequency").verify();
        EqualsVerifier.simple().forClass(AtomChannelDataImpl.class).verify();
        EqualsVerifier.simple().forClass(AtomItemImpl.class).withNonnullFields("atomData").withIgnoredFields("defaultComparator").withIgnoredFields("dateTimeParser").withIgnoredFields("category").withNonnullFields("categories").withIgnoredFields("enclosure").withNonnullFields("enclosures").verify();
        EqualsVerifier.simple().forClass(AtomItemDataImpl.class).verify();
        EqualsVerifier.simple().forClass(MetaData.class).verify();
    }

    private void assertHasFeedItem(AtomItem item) {
        if (item instanceof FeedItem) {
            FeedItem feedItem = (FeedItem) item;
            assertTrue(feedItem.hasAtomItem());
            assertFalse(feedItem.hasDcItem());
            assertFalse(feedItem.hasGeoRssItem());
            assertFalse(feedItem.hasItunesItem());
            assertFalse(feedItem.hasMediaRssItem());
            assertFalse(feedItem.hasPodcastItem());
            assertFalse(feedItem.hasPscItem());
            assertFalse(feedItem.hasSlashItem());
            assertFalse(feedItem.hasWfwItem());
            assertFalse(feedItem.hasYoutubeItem());
        }
    }

    private static Stream<? extends Arguments> feedReaderArguments() {
        return Stream.of(
            Arguments.of(new AtomFeedReader()),
            Arguments.of(new FeedReader())
        );
    }

    private InputStream fromFile(String fileName) {
        return getClass().getClassLoader().getResourceAsStream(fileName);
    }

}
