package com.apptasticsoftware.rssreader.module.georss;

import com.apptasticsoftware.rssreader.AbstractRssReader;
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

import static com.github.npathai.hamcrestopt.OptionalMatchers.isPresentAndIs;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("java:S5961")
class GeoRssFeedReaderTest {

    @ParameterizedTest
    @MethodSource("feedReaderArguments")
    void example1(AbstractRssReader<GeoRssChannel, GeoRssItem> feedReader) {
        var items = feedReader.read(fromFile("georss/example1.xml"))
                .collect(Collectors.toList());

        assertEquals(1, items.size());
        var item = items.get(0);
        var channel = (GeoRssChannel) item.getChannel();
        assertThat(channel.getTitle(), is("Earthquakes"));
        assertThat(channel.getLink(), is("http://example.org/"));
        assertThat(channel.getLastBuildDate(), isPresentAndIs("2005-12-13T18:30:02Z"));
        assertThat(channel.getLastBuildDateAsZonedDateTime(), isPresentAndIs(Default.getDateTimeParser().parse("2005-12-13T18:30:02Z")));
        assertThat(channel.getManagingEditor(), isPresentAndIs("Dr. Thaddeus Remor"));
        assertThat(channel.getGeoRssPoint(), isPresentAndIs("45.255    -71.91 1.2"));
        assertThat(channel.getGeoRssPointAsCoordinate(), isPresentAndIs(new Coordinate(45.255, -71.91)));
        assertThat(channel.getGeoRssLine(), isPresentAndIs("45.255 -110.44 46.45 -109.47 43.83 -109.85"));
        assertThat(channel.getGeoRssLineAsCoordinates(), is(List.of(new Coordinate(45.255, -110.44), new Coordinate(46.45, -109.47), new Coordinate(43.83, -109.85))));
        assertThat(channel.getGeoRssPolygon(), isPresentAndIs("45.255 -110.44 46.45 -109.47 43.83 -109.85 45.255 -110.44"));
        assertThat(channel.getGeoRssPolygonAsCoordinates(), is(List.of(new Coordinate(45.255, -110.44), new Coordinate(46.45, -109.47), new Coordinate(43.83, -109.85), new Coordinate(45.255, -110.44))));
        assertThat(channel.getGeoRssBox(), isPresentAndIs("42.942 -71.031 43.038 -69.855"));
        assertThat(channel.getGeoRssBoxAsCoordinates(), is(List.of(new Coordinate(42.942, -71.031), new Coordinate(43.038, -69.855))));
        assertThat(channel.getGeoRssElevation(), isPresentAndIs(312.0));
        assertThat(channel.getGeoRssFloor(), isPresentAndIs(1));
        assertThat(channel.getGeoRssRadius(), isPresentAndIs(499.0));
        assertThat(channel.getGeoRssFeatureTypeTag(), isPresentAndIs("city-2"));
        assertThat(channel.getGeoRssRelationshipTag(), isPresentAndIs("is-centered-at-2"));
        assertThat(channel.getGeoRssFeatureName(), isPresentAndIs("Podunk-2"));

        assertThat(item.getTitle(), isPresentAndIs("M 3.2, Mona Passage"));
        assertThat(item.getLink(), isPresentAndIs("http://example.org/2005/09/09/atom01"));
        assertThat(item.getGuid(), isPresentAndIs("urn:uuid:1225c695-cfb8-4ebb-aaaa-80da344efa6a"));
        assertThat(item.getPubDate(), isPresentAndIs("2005-08-17T07:02:32Z"));
        assertThat(item.getPubDateAsZonedDateTime(), isPresentAndIs(Default.getDateTimeParser().parse("2005-08-17T07:02:32Z")));
        assertThat(item.getUpdated(), isPresentAndIs("2005-08-17T07:02:32Z"));
        assertThat(item.getUpdatedAsZonedDateTime(), isPresentAndIs(Default.getDateTimeParser().parse("2005-08-17T07:02:32Z")));
        assertThat(item.getGeoRssPoint(), isPresentAndIs("45.256    -71.92 1.2"));
        assertThat(item.getGeoRssPointAsCoordinate(), isPresentAndIs(new Coordinate(45.256, -71.92)));
        assertThat(item.getGeoRssLine(), isPresentAndIs("45.256 -110.45 46.46 -109.48 43.84 -109.86"));
        assertThat(item.getGeoRssLineAsCoordinates(), is(List.of(new Coordinate(45.256, -110.45), new Coordinate(46.46, -109.48), new Coordinate(43.84, -109.86))));
        assertThat(item.getGeoRssPolygon(), isPresentAndIs("45.256 -110.45 46.46 -109.48 43.84 -109.86 45.256 -110.45"));
        assertThat(item.getGeoRssPolygonAsCoordinates(), is(List.of(new Coordinate(45.256, -110.45), new Coordinate(46.46, -109.48), new Coordinate(43.84, -109.86), new Coordinate(45.256, -110.45))));
        assertThat(item.getGeoRssBox(), isPresentAndIs("42.943 -71.032 43.039 -69.856"));
        assertThat(item.getGeoRssBoxAsCoordinates(), is(List.of(new Coordinate(42.943, -71.032), new Coordinate(43.039, -69.856))));
        assertThat(item.getGeoRssElevation(), isPresentAndIs(313.0));
        assertThat(item.getGeoRssFloor(), isPresentAndIs(2));
        assertThat(item.getGeoRssRadius(), isPresentAndIs(500.0));
        assertThat(item.getGeoRssFeatureTypeTag(), isPresentAndIs("city"));
        assertThat(item.getGeoRssRelationshipTag(), isPresentAndIs("is-centered-at"));
        assertThat(item.getGeoRssFeatureName(), isPresentAndIs("Podunk"));
    }

    @ParameterizedTest
    @MethodSource("feedReaderArguments")
    void example2(AbstractRssReader<GeoRssChannel, GeoRssItem> feedReader) {
        var items = feedReader.read(fromFile("georss/example2.xml"))
                .collect(Collectors.toList());

        assertEquals(1, items.size());
        var item = items.get(0);

        var channel = (GeoRssChannel) item.getChannel();
        assertThat(channel.getTitle(), is("Earthquakes"));
        assertThat(channel.getLink(), is("http://example.org/"));
        assertThat(channel.getLastBuildDate(), isPresentAndIs("2005-12-13T18:30:02Z"));
        assertThat(channel.getLastBuildDateAsZonedDateTime(), isPresentAndIs(Default.getDateTimeParser().parse("2005-12-13T18:30:02Z")));
        assertThat(channel.getManagingEditor(), isPresentAndIs("Dr. Thaddeus Remor"));
        assertThat(channel.getGeoRssPoint(), isPresentAndIs("45.256 -71.92"));
        assertThat(channel.getGeoRssPointAsCoordinate(), isPresentAndIs(new Coordinate(45.256, -71.92)));
        assertThat(channel.getGeoRssLine(), isPresentAndIs("45.255 -110.44 46.45 -109.47 43.83 -109.85"));
        assertThat(channel.getGeoRssLineAsCoordinates(), is(List.of(new Coordinate(45.255, -110.44), new Coordinate(46.45, -109.47), new Coordinate(43.83, -109.85))));
        assertThat(channel.getGeoRssPolygon(), isPresentAndIs("45.255 -110.44 46.45 -109.47 43.83 -109.85 45.255 -110.44"));
        assertThat(channel.getGeoRssPolygonAsCoordinates(), is(List.of(new Coordinate(45.255, -110.44), new Coordinate(46.45, -109.47), new Coordinate(43.83, -109.85), new Coordinate(45.255, -110.44))));
        assertThat(channel.getGeoRssBox(), isPresentAndIs("42.942 -71.031 43.038 -69.855"));
        assertThat(channel.getGeoRssBoxAsCoordinates(), is(List.of(new Coordinate(42.942, -71.031), new Coordinate(43.038, -69.855))));

        assertThat(item.getTitle(), isPresentAndIs("M 3.2, Mona Passage"));
        assertThat(item.getLink(), isPresentAndIs("http://example.org/2005/09/09/atom01"));
        assertThat(item.getGuid(), isPresentAndIs("urn:uuid:1225c695-cfb8-4ebb-aaaa-80da344efa6a"));
        assertThat(item.getPubDate(), isPresentAndIs("2005-08-17T07:02:32Z"));
        assertThat(item.getPubDateAsZonedDateTime(), isPresentAndIs(Default.getDateTimeParser().parse("2005-08-17T07:02:32Z")));
        assertThat(item.getUpdated(), isPresentAndIs("2005-08-17T07:02:32Z"));
        assertThat(item.getUpdatedAsZonedDateTime(), isPresentAndIs(Default.getDateTimeParser().parse("2005-08-17T07:02:32Z")));
        assertThat(item.getGeoRssPoint(), isPresentAndIs("45.257 -71.93"));
        assertThat(item.getGeoRssPointAsCoordinate(), isPresentAndIs(new Coordinate(45.257, -71.93)));
        assertThat(item.getGeoRssLine(), isPresentAndIs("45.256 -110.45 46.46 -109.48 43.84 -109.86"));
        assertThat(item.getGeoRssLineAsCoordinates(), is(List.of(new Coordinate(45.256, -110.45), new Coordinate(46.46, -109.48), new Coordinate(43.84, -109.86))));
        assertThat(item.getGeoRssPolygon(), isPresentAndIs("45.256 -110.45 46.46 -109.48 43.84 -109.86 45.256 -110.45"));
        assertThat(item.getGeoRssPolygonAsCoordinates(), is(List.of(new Coordinate(45.256, -110.45), new Coordinate(46.46, -109.48), new Coordinate(43.84, -109.86), new Coordinate(45.256, -110.45))));
        assertThat(item.getGeoRssBox(), isPresentAndIs("42.943 -71.032 43.039 -69.856"));
        assertThat(item.getGeoRssBoxAsCoordinates(), is(List.of(new Coordinate(42.943, -71.032), new Coordinate(43.039, -69.856))));
    }

    @ParameterizedTest
    @MethodSource("feedReaderArguments")
    void example3(AbstractRssReader<GeoRssChannel, GeoRssItem> feedReader) {
        var items =feedReader.read(fromFile("georss/example3.xml"))
                .collect(Collectors.toList());

        assertEquals(1, items.size());
        var item = items.get(0);

        var channel = (GeoRssChannel) item.getChannel();
        assertThat(channel.getTitle(), is("USGS M5+ Earthquakes"));
        assertThat(channel.getDescription(), is("Real-time, worldwide earthquake list for the past 7 days"));
        assertThat(channel.getLink(), is("https://earthquake.usgs.gov/eqcenter/"));
        assertThat(channel.getPubDate(), isPresentAndIs("Thu, 27 Dec 2007 23:56:15 PST"));
        assertThat(channel.getPubDateAsZonedDateTime(), isPresentAndIs(Default.getDateTimeParser().parse("Thu, 27 Dec 2007 23:56:15 PST")));
        assertThat(channel.getGeoRssPoint(), isPresentAndIs("5.5318 95.8971"));
        assertThat(channel.getGeoRssPointAsCoordinate(), isPresentAndIs(new Coordinate(5.5318, 95.8971)));

        assertThat(item.getPubDate(), isPresentAndIs("Fri, 28 Dec 2007 05:24:17 GMT"));
        assertThat(item.getPubDateAsZonedDateTime(), isPresentAndIs(Default.getDateTimeParser().parse("Fri, 28 Dec 2007 05:24:17 GMT")));
        assertThat(item.getTitle(), isPresentAndIs("M 5.3, northern Sumatra, Indonesia"));
        assertThat(item.getDescription(), isPresentAndIs("December 28, 2007 05:24:17 GMT"));
        assertThat(item.getLink(), isPresentAndIs("https://earthquake.usgs.gov/eqcenter/recenteqsww/Quakes/us2007llai.php"));
        assertThat(item.getGeoRssPoint(), isPresentAndIs("5.5319 95.8972"));
        assertThat(item.getGeoRssPointAsCoordinate(), isPresentAndIs(new Coordinate(5.5319, 95.8972)));
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
