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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SpotifyFeedReaderTest {

    @ParameterizedTest
    @MethodSource("feedReaderArguments")
    @SuppressWarnings("java:S5961")
    void example1(AbstractRssReader<SpotifyChannel, SpotifyItem> feedReader) {
        var items = feedReader.read(fromFile("module/spotify/example1.xml"))
                .collect(Collectors.toList());

        assertEquals(1, items.size());

        SpotifyItem item = items.get(0);
        SpotifyChannel channel = (SpotifyChannel) item.getChannel();
        assertThat(channel.getTitle()).isEqualTo("Serial");
        assertThat(channel.getLink()).isEqualTo("https://serialpodcast.org");
        assertThat(channel.getDescription()).contains("Serial is a new podcast from the creators of This American Life,");
        assertThat(channel.getLanguage()).hasValue("en");
        assertThat(channel.getSpotifyLimitRecentCount()).hasValue(1);
        assertThat(channel.getSpotifyCountryOfOrigin()).hasValue("no dk se");
        assertThat(channel.getSpotifyCountryOfOriginAsList()).isEqualTo(List.of("no", "dk", "se"));
        assertThat(channel.getItunesAuthor()).hasValue("This American Life");
        assertThat(channel.getItunesCategories()).isEqualTo(List.of("News & Politics"));
        assertThat(channel.getItunesType()).hasValue("episodic");
        assertThat(channel.getItunesImage()).isEqualTo("https://serialpodcast.org/sites/all/modules/custom/serial/img/serial-itunes-logo.png");
        assertThat(channel.getMediaRestrictions().size()).isEqualTo(1);
        assertThat(channel.getMediaRestrictions().get(0).getType()).isEqualTo("country");
        assertThat(channel.getMediaRestrictions().get(0).getRelationship()).isEqualTo("allow");
        assertThat(channel.getMediaRestrictions().get(0).getRestriction()).isEqualTo("us se");

        assertThat(item.getGuid()).hasValue("1234");
        assertThat(item.getTitle()).hasValue("Episode 09: To Be Suspected");
        assertThat(item.getDescription()).hasValueSatisfying(v -> assertThat(v).contains("New information is coming in about what maybe did not happen on"));
        assertThat(item.getPubDate()).hasValue("Thu, 20 Nov 2014 10:30:00 +0000");
        assertThat(item.getPubDateAsZonedDateTime()).hasValue(Default.getDateTimeParser().parse("Thu, 20 Nov 2014 10:30:00 +0000"));
        assertThat(item.getItunesDuration()).hasValue("2700");
        assertThat(item.getItunesDurationAsDuration()).hasValue(Duration.ofSeconds(2700));
        assertThat(item.getEnclosures().size()).isEqualTo(1);
        assertThat(item.getEnclosures().get(0).getUrl()).isEqualTo("https://dts.podtrac.com/redirect.mp3/files.serialpodcast.org/sites/default/files/podcast/1422481890/serial-s01-e09.mp3");
        assertThat(item.getEnclosures().get(0).getType()).isEqualTo("audio/mpeg");
        assertTrue(item.getMediaTitle().isPresent());
        assertThat(item.getMediaTitle().get().getTitle()).isEqualTo("Episode 9: Spotify");

        assertTrue(item.getPscChapters().isPresent());
        assertThat(item.getPscChapters().get().getVersion()).isEmpty();
        assertThat(item.getPscChapters().get().getChapters().size()).isEqualTo(3);
        assertThat(item.getPscChapters().get().getChapters().get(0).getStart()).isEqualTo("0");
        assertThat(item.getPscChapters().get().getChapters().get(0).getStartAsDuration()).isEqualTo(Duration.ofSeconds(0));
        assertThat(item.getPscChapters().get().getChapters().get(0).getTitle()).isEqualTo("Opening credits");
        assertThat(item.getPscChapters().get().getChapters().get(1).getStart()).isEqualTo("0:35");
        assertThat(item.getPscChapters().get().getChapters().get(1).getStartAsDuration()).isEqualTo(Duration.ofSeconds(35));
        assertThat(item.getPscChapters().get().getChapters().get(1).getTitle()).isEqualTo("Today's CNN headlines");
        assertThat(item.getPscChapters().get().getChapters().get(1).getHref()).hasValue("https://edition.cnn.com/");
        assertThat(item.getPscChapters().get().getChapters().get(1).getImage()).hasValue("https://cdn.cnn.com/cnn/.e/img/3.0/global/misc/cnn-logo.png");
        assertThat(item.getPscChapters().get().getChapters().get(2).getStart()).isEqualTo("8:36");
        assertThat(item.getPscChapters().get().getChapters().get(2).getStartAsDuration()).isEqualTo(Duration.parse("PT8M36S"));
        assertThat(item.getPscChapters().get().getChapters().get(2).getTitle()).isEqualTo("End credits");
    }

    @Test
    @SuppressWarnings("java:S5961")
    void equalsContract() {
        EqualsVerifier.simple().forClass(SpotifyChannelImpl.class).withNonnullFields("spotifyData", "itunesData", "mediaRssData").withIgnoredFields("dateTimeParser").withIgnoredFields("category").withNonnullFields("categories").withIgnoredFields("syUpdatePeriod").withIgnoredFields("syUpdateFrequency").verify();
        EqualsVerifier.simple().forClass(SpotifyItemImpl.class).withNonnullFields("itunesData", "mediaRssData").withIgnoredFields("defaultComparator").withIgnoredFields("dateTimeParser").withIgnoredFields("category").withNonnullFields("categories").withIgnoredFields("enclosure").withNonnullFields("enclosures").verify();
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
