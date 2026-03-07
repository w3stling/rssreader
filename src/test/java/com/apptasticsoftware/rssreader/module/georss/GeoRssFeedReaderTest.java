package com.apptasticsoftware.rssreader.module.georss;

import com.apptasticsoftware.rssreader.AbstractRssReader;
import com.apptasticsoftware.rssreader.FeedItem;
import com.apptasticsoftware.rssreader.FeedReader;
import com.apptasticsoftware.rssreader.module.georss.internal.*;
import com.apptasticsoftware.rssreader.util.Default;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SuppressWarnings("java:S5961")
class GeoRssFeedReaderTest {

    @ParameterizedTest
    @MethodSource("feedReaderArguments")
    void example1(AbstractRssReader<GeoRssChannel, GeoRssItem> feedReader) {
        var items = feedReader.read(fromFile("module/georss/example1.xml"))
                .collect(Collectors.toList());

        assertEquals(1, items.size());
        var item = items.get(0);
        assertHasFeedItem(item);
        var channel = (GeoRssChannel) item.getChannel();
        assertThat(channel.getTitle()).isEqualTo("Earthquakes");
        assertThat(channel.getLink()).isEqualTo("http://example.org/");
        assertThat(channel.getLastBuildDate()).hasValue("2005-12-13T18:30:02Z");
        assertThat(channel.getLastBuildDateAsZonedDateTime()).hasValue(Default.getDateTimeParser().parse("2005-12-13T18:30:02Z"));
        assertThat(channel.getManagingEditor()).hasValue("Dr. Thaddeus Remor");
        assertThat(channel.getGeoRssPoint()).hasValue("45.255    -71.91 1.2");
        assertThat(channel.getGeoRssPointAsCoordinate()).hasValue(new Coordinate(45.255, -71.91));
        assertThat(channel.getGeoRssLine()).hasValue("45.255 -110.44 46.45 -109.47 43.83 -109.85");
        assertThat(channel.getGeoRssLineAsCoordinates()).isEqualTo(List.of(new Coordinate(45.255, -110.44), new Coordinate(46.45, -109.47), new Coordinate(43.83, -109.85)));
        assertThat(channel.getGeoRssPolygon()).hasValue("45.255 -110.44 46.45 -109.47 43.83 -109.85 45.255 -110.44");
        assertThat(channel.getGeoRssPolygonAsCoordinates()).isEqualTo(List.of(new Coordinate(45.255, -110.44), new Coordinate(46.45, -109.47), new Coordinate(43.83, -109.85), new Coordinate(45.255, -110.44)));
        assertThat(channel.getGeoRssBox()).hasValue("42.942 -71.031 43.038 -69.855");
        assertThat(channel.getGeoRssBoxAsCoordinates()).isEqualTo(List.of(new Coordinate(42.942, -71.031), new Coordinate(43.038, -69.855)));
        assertThat(channel.getGeoRssElevation()).hasValue(312.0);
        assertThat(channel.getGeoRssFloor()).hasValue(1);
        assertThat(channel.getGeoRssRadius()).hasValue(499.0);
        assertThat(channel.getGeoRssFeatureTypeTag()).hasValue("city-2");
        assertThat(channel.getGeoRssRelationshipTag()).hasValue("is-centered-at-2");
        assertThat(channel.getGeoRssFeatureName()).hasValue("Podunk-2");

        assertThat(item.getTitle()).hasValue("M 3.2, Mona Passage");
        assertThat(item.getLink()).hasValue("http://example.org/2005/09/09/atom01");
        assertThat(item.getGuid()).hasValue("urn:uuid:1225c695-cfb8-4ebb-aaaa-80da344efa6a");
        assertThat(item.getPubDate()).hasValue("2005-08-17T07:02:32Z");
        assertThat(item.getPubDateAsZonedDateTime()).hasValue(Default.getDateTimeParser().parse("2005-08-17T07:02:32Z"));
        assertThat(item.getUpdated()).hasValue("2005-08-17T07:02:32Z");
        assertThat(item.getUpdatedAsZonedDateTime()).hasValue(Default.getDateTimeParser().parse("2005-08-17T07:02:32Z"));
        assertThat(item.getGeoRssPoint()).hasValue("45.256    -71.92 1.2");
        assertThat(item.getGeoRssPointAsCoordinate()).hasValue(new Coordinate(45.256, -71.92));
        assertThat(item.getGeoRssLine()).hasValue("45.256 -110.45 46.46 -109.48 43.84 -109.86");
        assertThat(item.getGeoRssLineAsCoordinates()).isEqualTo(List.of(new Coordinate(45.256, -110.45), new Coordinate(46.46, -109.48), new Coordinate(43.84, -109.86)));
        assertThat(item.getGeoRssPolygon()).hasValue("45.256 -110.45 46.46 -109.48 43.84 -109.86 45.256 -110.45");
        assertThat(item.getGeoRssPolygonAsCoordinates()).isEqualTo(List.of(new Coordinate(45.256, -110.45), new Coordinate(46.46, -109.48), new Coordinate(43.84, -109.86), new Coordinate(45.256, -110.45)));
        assertThat(item.getGeoRssBox()).hasValue("42.943 -71.032 43.039 -69.856");
        assertThat(item.getGeoRssBoxAsCoordinates()).isEqualTo(List.of(new Coordinate(42.943, -71.032), new Coordinate(43.039, -69.856)));
        assertThat(item.getGeoRssElevation()).hasValue(313.0);
        assertThat(item.getGeoRssFloor()).hasValue(2);
        assertThat(item.getGeoRssRadius()).hasValue(500.0);
        assertThat(item.getGeoRssFeatureTypeTag()).hasValue("city");
        assertThat(item.getGeoRssRelationshipTag()).hasValue("is-centered-at");
        assertThat(item.getGeoRssFeatureName()).hasValue("Podunk");
    }

    @ParameterizedTest
    @MethodSource("feedReaderArguments")
    void example2(AbstractRssReader<GeoRssChannel, GeoRssItem> feedReader) {
        var items = feedReader.read(fromFile("module/georss/example2.xml"))
                .collect(Collectors.toList());

        assertEquals(1, items.size());
        var item = items.get(0);
        assertHasFeedItem(item);
        var channel = (GeoRssChannel) item.getChannel();
        assertThat(channel.getTitle()).isEqualTo("Earthquakes");
        assertThat(channel.getLink()).isEqualTo("http://example.org/");
        assertThat(channel.getLastBuildDate()).hasValue("2005-12-13T18:30:02Z");
        assertThat(channel.getLastBuildDateAsZonedDateTime()).hasValue(Default.getDateTimeParser().parse("2005-12-13T18:30:02Z"));
        assertThat(channel.getManagingEditor()).hasValue("Dr. Thaddeus Remor");
        assertThat(channel.getGeoRssPoint()).hasValue("45.256 -71.92");
        assertThat(channel.getGeoRssPointAsCoordinate()).hasValue(new Coordinate(45.256, -71.92));
        assertThat(channel.getGeoRssLine()).hasValue("45.255 -110.44 46.45 -109.47 43.83 -109.85");
        assertThat(channel.getGeoRssLineAsCoordinates()).isEqualTo(List.of(new Coordinate(45.255, -110.44), new Coordinate(46.45, -109.47), new Coordinate(43.83, -109.85)));
        assertThat(channel.getGeoRssPolygon()).hasValue("45.255 -110.44 46.45 -109.47 43.83 -109.85 45.255 -110.44");
        assertThat(channel.getGeoRssPolygonAsCoordinates()).isEqualTo(List.of(new Coordinate(45.255, -110.44), new Coordinate(46.45, -109.47), new Coordinate(43.83, -109.85), new Coordinate(45.255, -110.44)));
        assertThat(channel.getGeoRssBox()).hasValue("42.942 -71.031 43.038 -69.855");
        assertThat(channel.getGeoRssBoxAsCoordinates()).isEqualTo(List.of(new Coordinate(42.942, -71.031), new Coordinate(43.038, -69.855)));

        assertThat(item.getTitle()).hasValue("M 3.2, Mona Passage");
        assertThat(item.getLink()).hasValue("http://example.org/2005/09/09/atom01");
        assertThat(item.getGuid()).hasValue("urn:uuid:1225c695-cfb8-4ebb-aaaa-80da344efa6a");
        assertThat(item.getPubDate()).hasValue("2005-08-17T07:02:32Z");
        assertThat(item.getPubDateAsZonedDateTime()).hasValue(Default.getDateTimeParser().parse("2005-08-17T07:02:32Z"));
        assertThat(item.getUpdated()).hasValue("2005-08-17T07:02:32Z");
        assertThat(item.getUpdatedAsZonedDateTime()).hasValue(Default.getDateTimeParser().parse("2005-08-17T07:02:32Z"));
        assertThat(item.getGeoRssPoint()).hasValue("45.257 -71.93");
        assertThat(item.getGeoRssPointAsCoordinate()).hasValue(new Coordinate(45.257, -71.93));
        assertThat(item.getGeoRssLine()).hasValue("45.256 -110.45 46.46 -109.48 43.84 -109.86");
        assertThat(item.getGeoRssLineAsCoordinates()).isEqualTo(List.of(new Coordinate(45.256, -110.45), new Coordinate(46.46, -109.48), new Coordinate(43.84, -109.86)));
        assertThat(item.getGeoRssPolygon()).hasValue("45.256 -110.45 46.46 -109.48 43.84 -109.86 45.256 -110.45");
        assertThat(item.getGeoRssPolygonAsCoordinates()).isEqualTo(List.of(new Coordinate(45.256, -110.45), new Coordinate(46.46, -109.48), new Coordinate(43.84, -109.86), new Coordinate(45.256, -110.45)));
        assertThat(item.getGeoRssBox()).hasValue("42.943 -71.032 43.039 -69.856");
        assertThat(item.getGeoRssBoxAsCoordinates()).isEqualTo(List.of(new Coordinate(42.943, -71.032), new Coordinate(43.039, -69.856)));
    }

    @ParameterizedTest
    @MethodSource("feedReaderArguments")
    void example3(AbstractRssReader<GeoRssChannel, GeoRssItem> feedReader) {
        var items =feedReader.read(fromFile("module/georss/example3.xml"))
                .collect(Collectors.toList());

        assertEquals(1, items.size());
        var item = items.get(0);

        var channel = (GeoRssChannel) item.getChannel();
        assertThat(channel.getTitle()).isEqualTo("USGS M5+ Earthquakes");
        assertThat(channel.getDescription()).isEqualTo("Real-time, worldwide earthquake list for the past 7 days");
        assertThat(channel.getLink()).isEqualTo("https://earthquake.usgs.gov/eqcenter/");
        assertThat(channel.getPubDate()).hasValue("Thu, 27 Dec 2007 23:56:15 PST");
        assertThat(channel.getPubDateAsZonedDateTime()).hasValue(Default.getDateTimeParser().parse("Thu, 27 Dec 2007 23:56:15 PST"));
        assertThat(channel.getGeoRssPoint()).hasValue("5.5318 95.8971");
        assertThat(channel.getGeoRssPointAsCoordinate()).hasValue(new Coordinate(5.5318, 95.8971));

        assertThat(item.getPubDate()).hasValue("Fri, 28 Dec 2007 05:24:17 GMT");
        assertThat(item.getPubDateAsZonedDateTime()).hasValue(Default.getDateTimeParser().parse("Fri, 28 Dec 2007 05:24:17 GMT"));
        assertThat(item.getTitle()).hasValue("M 5.3, northern Sumatra, Indonesia");
        assertThat(item.getDescription()).hasValue("December 28, 2007 05:24:17 GMT");
        assertThat(item.getLink()).hasValue("https://earthquake.usgs.gov/eqcenter/recenteqsww/Quakes/us2007llai.php");
        assertThat(item.getGeoRssPoint()).hasValue("5.5319 95.8972");
        assertThat(item.getGeoRssPointAsCoordinate()).hasValue(new Coordinate(5.5319, 95.8972));
    }

    @Test
    void equalsContract() {
        EqualsVerifier.simple().forClass(GeoRssChannelImpl.class).withNonnullFields("geoRssData").withIgnoredFields("dateTimeParser").withIgnoredFields("category").withNonnullFields("categories").withIgnoredFields("syUpdatePeriod").withIgnoredFields("syUpdateFrequency").verify();
        EqualsVerifier.simple().forClass(GeoRssChannelDataImpl.class).verify();
        EqualsVerifier.simple().forClass(GeoRssItemImpl.class).withNonnullFields("geoRssData").withIgnoredFields("defaultComparator").withIgnoredFields("dateTimeParser").withIgnoredFields("category").withNonnullFields("categories").withIgnoredFields("enclosure").withNonnullFields("enclosures").verify();
        EqualsVerifier.simple().forClass(GeoRssItemDataImpl.class).verify();
        EqualsVerifier.simple().forClass(MetaData.class).verify();
        EqualsVerifier.simple().forClass(Coordinate.class).verify();
    }

    private void assertHasFeedItem(GeoRssItem item) {
        if (item instanceof FeedItem) {
            FeedItem feedItem = (FeedItem) item;
            assertFalse(feedItem.hasAtomItem());
            assertFalse(feedItem.hasDcItem());
            assertTrue(feedItem.hasGeoRssItem());
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
            Arguments.of(new GeoRssFeedReader()),
            Arguments.of(new FeedReader())
        );
    }

    private InputStream fromFile(String fileName) {
        return getClass().getClassLoader().getResourceAsStream(fileName);
    }
}
