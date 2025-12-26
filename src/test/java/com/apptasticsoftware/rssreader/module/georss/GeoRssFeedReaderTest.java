package com.apptasticsoftware.rssreader.module.georss;

import com.apptasticsoftware.rssreader.filter.InvalidXmlCharacterFilter;
import com.apptasticsoftware.rssreader.module.georss.internal.GeoRssChannelImpl;
import com.apptasticsoftware.rssreader.module.georss.internal.GeoRssItemDataImpl;
import com.apptasticsoftware.rssreader.module.georss.internal.GeoRssItemImpl;
import com.apptasticsoftware.rssreader.util.Default;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.npathai.hamcrestopt.OptionalMatchers.isPresentAndIs;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GeoRssFeedReaderTest {

    @Test
    void example1() {
        var items = new GeoRssFeedReader().addFeedFilter(new InvalidXmlCharacterFilter()).read(fromFile("georss/example1.xml"))
                .collect(Collectors.toList());

        assertEquals(1, items.size());
        var item = items.get(0);
        var channel = (GeoRssChannel) item.getChannel();
        assertThat(channel.getTitle(), is("Earthquakes"));
        assertThat(channel.getLink(), is("http://example.org/"));
        assertThat(channel.getLastBuildDate(), isPresentAndIs("2005-12-13T18:30:02Z"));
        assertThat(channel.getLastBuildDateAsZonedDateTime(), isPresentAndIs(Default.getDateTimeParser().parse("2005-12-13T18:30:02Z")));
        assertThat(channel.getManagingEditor(), isPresentAndIs("Dr. Thaddeus Remor"));

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
        assertThat(item.getGeoRssElev(), isPresentAndIs(313.0));
        assertThat(item.getGeoRssFloor(), isPresentAndIs(2));
        assertThat(item.getGeoRssRadius(), isPresentAndIs(500.0));
        assertThat(item.getGeoRssFeatureTypeTag(), isPresentAndIs("city"));
        assertThat(item.getGeoRssRelationshipTag(), isPresentAndIs("is-centered-at"));
        assertThat(item.getGeoRssFeatureName(), isPresentAndIs("Podunk"));
    }

    @Test
    void equalsContract() {
        EqualsVerifier.simple().forClass(GeoRssChannelImpl.class).withIgnoredFields("dateTimeParser").withIgnoredFields("category").withNonnullFields("categories").withIgnoredFields("syUpdatePeriod").withIgnoredFields("syUpdateFrequency").verify();
        EqualsVerifier.simple().forClass(GeoRssItemImpl.class).withNonnullFields("geoRssData").withIgnoredFields("defaultComparator").withIgnoredFields("dateTimeParser").withIgnoredFields("category").withNonnullFields("categories").withIgnoredFields("enclosure").withNonnullFields("enclosures").verify();
        EqualsVerifier.simple().forClass(GeoRssItemDataImpl.class).verify();
    }

    private InputStream fromFile(String fileName) {
        return getClass().getClassLoader().getResourceAsStream(fileName);
    }
}
