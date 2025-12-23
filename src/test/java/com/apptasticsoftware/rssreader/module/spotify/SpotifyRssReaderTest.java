package com.apptasticsoftware.rssreader.module.spotify;

import com.apptasticsoftware.rssreader.AbstractRssReader;
import com.apptasticsoftware.rssreader.FeedReader;
import com.apptasticsoftware.rssreader.module.spotify.internal.SpotifyChannelImpl;
import com.apptasticsoftware.rssreader.module.spotify.internal.SpotifyItemImpl;
import com.apptasticsoftware.rssreader.util.Default;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.InputStream;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.github.npathai.hamcrestopt.OptionalMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SpotifyRssReaderTest {

    @ParameterizedTest
    @MethodSource("feedReaderArguments")
    @SuppressWarnings("java:S5961")
    void example1(AbstractRssReader<SpotifyChannel, SpotifyItem> feedReader) {
        var items = feedReader.read(fromFile("spotify/example1.xml"))
                .collect(Collectors.toList());

        assertEquals(1, items.size());

        SpotifyItem item = items.get(0);
        SpotifyChannel channel = (SpotifyChannel) item.getChannel();
        assertThat(channel.getTitle(), is("Serial"));
        assertThat(channel.getLink(), is("https://serialpodcast.org"));
        assertThat(channel.getDescription(), containsString("Serial is a new podcast from the creators of This American Life,"));
        assertThat(channel.getLanguage(), isPresentAndIs("en"));
        assertThat(channel.getSpotifyLimitRecentCount(), isPresentAndIs(1));
        assertThat(channel.getSpotifyCountryOfOrigin(), isPresentAndIs("no dk se"));
        assertThat(channel.getSpotifyCountryOfOriginAsList(), is(List.of("no", "dk", "se")));
        assertThat(channel.getItunesAuthor(), isPresentAndIs("This American Life"));
        assertThat(channel.getItunesCategories(), is(List.of("News & Politics")));
        assertThat(channel.getItunesType(), isPresentAndIs("episodic"));
        assertThat(channel.getItunesImage(), is("https://serialpodcast.org/sites/all/modules/custom/serial/img/serial-itunes-logo.png"));
        assertThat(channel.getMediaRestrictions().size(), is(1));
        assertThat(channel.getMediaRestrictions().get(0).getType(), is("country"));
        assertThat(channel.getMediaRestrictions().get(0).getRelationship(), is("allow"));
        assertThat(channel.getMediaRestrictions().get(0).getRestriction(), is("us se"));

        assertThat(item.getGuid(), isPresentAndIs("1234"));
        assertThat(item.getTitle(), isPresentAndIs("Episode 09: To Be Suspected"));
        assertThat(item.getDescription(), isPresentAnd(containsString("New information is coming in about what maybe did not happen on")));
        assertThat(item.getPubDate(), isPresentAndIs("Thu, 20 Nov 2014 10:30:00 +0000"));
        assertThat(item.getPubDateAsZonedDateTime(), isPresentAndIs(Default.getDateTimeParser().parse("Thu, 20 Nov 2014 10:30:00 +0000")));
        assertThat(item.getItunesDuration(), isPresentAndIs("2700"));
        assertThat(item.getItunesDurationAsDuration(), isPresentAndIs(Duration.ofSeconds(2700)));
        assertThat(item.getEnclosures().size(), is(1));
        assertThat(item.getEnclosures().get(0).getUrl(), is("https://dts.podtrac.com/redirect.mp3/files.serialpodcast.org/sites/default/files/podcast/1422481890/serial-s01-e09.mp3"));
        assertThat(item.getEnclosures().get(0).getType(), is("audio/mpeg"));
        assertTrue(item.getMediaTitle().isPresent());
        assertThat(item.getMediaTitle().get().getTitle(), is("Episode 9: Spotify"));

        assertTrue(item.getPscChapters().isPresent());
        assertThat(item.getPscChapters().get().getVersion(), isEmpty());
        assertThat(item.getPscChapters().get().getChapters().size(), is(3));
        assertThat(item.getPscChapters().get().getChapters().get(0).getStart(), is("0"));
        assertThat(item.getPscChapters().get().getChapters().get(0).getStartAsDuration(), is(Duration.ofSeconds(0)));
        assertThat(item.getPscChapters().get().getChapters().get(0).getTitle(), is("Opening credits"));
        assertThat(item.getPscChapters().get().getChapters().get(1).getStart(), is("0:35"));
        assertThat(item.getPscChapters().get().getChapters().get(1).getStartAsDuration(), is(Duration.ofSeconds(35)));
        assertThat(item.getPscChapters().get().getChapters().get(1).getTitle(), is("Today's CNN headlines"));
        assertThat(item.getPscChapters().get().getChapters().get(1).getHref(), isPresentAndIs("https://edition.cnn.com/"));
        assertThat(item.getPscChapters().get().getChapters().get(1).getImage(), isPresentAndIs("https://cdn.cnn.com/cnn/.e/img/3.0/global/misc/cnn-logo.png"));
        assertThat(item.getPscChapters().get().getChapters().get(2).getStart(), is("8:36"));
        assertThat(item.getPscChapters().get().getChapters().get(2).getStartAsDuration(), is(Duration.parse("PT8M36S")));
        assertThat(item.getPscChapters().get().getChapters().get(2).getTitle(), is("End credits"));
    }

    @Test
    @SuppressWarnings("java:S5961")
    void equalsContract() {
        EqualsVerifier.simple().forClass(SpotifyChannelImpl.class).withNonnullFields("spotifyData", "itunesData", "mediaRssData").withIgnoredFields("dateTimeParser").withIgnoredFields("category").withNonnullFields("categories").withIgnoredFields("syUpdatePeriod").withIgnoredFields("syUpdateFrequency").verify();
        EqualsVerifier.simple().forClass(SpotifyItemImpl.class).withNonnullFields("spotifyData", "itunesData", "mediaRssData").withIgnoredFields("defaultComparator").withIgnoredFields("dateTimeParser").withIgnoredFields("category").withNonnullFields("categories").withIgnoredFields("enclosure").withNonnullFields("enclosures").verify();
    }

    private static Stream<? extends Arguments> feedReaderArguments() {
        return Stream.of(
                Arguments.of(new SpotifyFeedReader()),
                Arguments.of(new FeedReader())
        );
    }

    private InputStream fromFile(String fileName) {
        return getClass().getClassLoader().getResourceAsStream(fileName);
    }
}
