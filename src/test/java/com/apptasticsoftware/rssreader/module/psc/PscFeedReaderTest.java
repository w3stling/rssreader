package com.apptasticsoftware.rssreader.module.psc;

import com.apptasticsoftware.rssreader.util.Default;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.time.Duration;
import java.util.stream.Collectors;

import static com.github.npathai.hamcrestopt.OptionalMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PscFeedReaderTest {

    @Test
    void example1() {
        var items = new PscFeedReader().read(fromFile("psc/example1.xml"))
                .collect(Collectors.toList());

        assertEquals(1, items.size());

        var channel = items.get(0).getChannel();
        assertThat(channel.getTitle(), is("Podlove Podcast"));
        assertThat(channel.getLink(), is("http://podlove.org"));

        var item = items.get(0);
        assertThat(item.getTitle(), isPresentAndIs("Fiat Lux"));
        assertThat(item.getIsPermaLink(), isPresentAndIs(false));
        assertThat(item.getGuid(), isPresentAndIs("urn:uuid:3241ace2-ca21-dd12-2341-1412ce31fad2"));
        assertThat(item.getPubDate(), isPresentAndIs("Fri, 23 Mar 2012 23:25:19 +0000"));
        assertThat(item.getPubDateAsZonedDateTime(), isPresentAndIs(Default.getDateTimeParser().parse("Fri, 23 Mar 2012 23:25:19 +0000")));
        assertThat(item.getDescription(), isPresentAndIs("First episode"));
        assertThat(item.getLink(), isPresentAndIs("http://podlove.org/files/fiatlux.mp3"));
        assertTrue(item.getPscChapters().isPresent());
        assertThat(item.getPscChapters().get().getVersion(), isPresentAndIs("1.2"));
        assertThat(item.getPscChapters().get().getChapters().size(), is(4));

        assertThat(item.getPscChapters().get().getChapters().get(0).getStart(), is("0"));
        assertThat(item.getPscChapters().get().getChapters().get(0).getStartAsDuration(), is(Duration.ofSeconds(0)));
        assertThat(item.getPscChapters().get().getChapters().get(0).getTitle(), is("Welcome"));
        assertThat(item.getPscChapters().get().getChapters().get(0).getHref(), isEmpty());
        assertThat(item.getPscChapters().get().getChapters().get(0).getImage(), isEmpty());

        assertThat(item.getPscChapters().get().getChapters().get(1).getStart(), is("3:07"));
        assertThat(item.getPscChapters().get().getChapters().get(1).getStartAsDuration(), is(Duration.ofSeconds(3 * 60 + 7)));
        assertThat(item.getPscChapters().get().getChapters().get(1).getTitle(), is("Introducing Podlove"));
        assertThat(item.getPscChapters().get().getChapters().get(1).getHref(), isPresentAndIs("http://podlove.org/"));
        assertThat(item.getPscChapters().get().getChapters().get(1).getImage(), isEmpty());

        assertThat(item.getPscChapters().get().getChapters().get(2).getStart(), is("8:26.250"));
        assertThat(item.getPscChapters().get().getChapters().get(2).getStartAsDuration(), is(Duration.parse("PT8M26.250S")));
        assertThat(item.getPscChapters().get().getChapters().get(2).getTitle(), is("Podlove WordPress Plugin"));
        assertThat(item.getPscChapters().get().getChapters().get(2).getHref(), isPresentAndIs("http://podlove.org/podlove-podcast-publisher"));
        assertThat(item.getPscChapters().get().getChapters().get(2).getImage(), isPresentAndIs("https://podlove.org/chapter/Podlove-WordPress-Plugin.png"));

        assertThat(item.getPscChapters().get().getChapters().get(3).getStart(), is("12:42"));
        assertThat(item.getPscChapters().get().getChapters().get(3).getStartAsDuration(), is(Duration.parse("PT12M42S")));
        assertThat(item.getPscChapters().get().getChapters().get(3).getTitle(), is("Resumée"));
        assertThat(item.getPscChapters().get().getChapters().get(3).getHref(), isEmpty());
        assertThat(item.getPscChapters().get().getChapters().get(3).getImage(), isPresentAndIs("https://podlove.org/chapter/Resumee.png"));
    }

    @Test
    @SuppressWarnings("java:S5961")
    void equalsContract() {
        EqualsVerifier.simple().forClass(PscChannelImpl.class).withIgnoredFields("dateTimeParser").withIgnoredFields("category").withNonnullFields("categories").withIgnoredFields("syUpdatePeriod").withIgnoredFields("syUpdateFrequency").verify();
        EqualsVerifier.simple().forClass(PscItemImpl.class).withNonnullFields("pscData").withIgnoredFields("defaultComparator").withIgnoredFields("dateTimeParser").withIgnoredFields("category").withNonnullFields("categories").withIgnoredFields("enclosure").withNonnullFields("enclosures").verify();
        EqualsVerifier.simple().forClass(PscItemImpl.class).withNonnullFields("pscData").withIgnoredFields("defaultComparator").withIgnoredFields("dateTimeParser").withIgnoredFields("category").withNonnullFields("categories").withIgnoredFields("enclosure").withNonnullFields("enclosures").verify();
        EqualsVerifier.simple().forClass(PscItemDataImpl.class).verify();
    }

    private InputStream fromFile(String fileName) {
        return getClass().getClassLoader().getResourceAsStream(fileName);
    }
}
