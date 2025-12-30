package com.apptasticsoftware.rssreader.module.dc;

import com.apptasticsoftware.rssreader.module.dc.internal.DcChannelDataImpl;
import com.apptasticsoftware.rssreader.module.dc.internal.DcChannelImpl;
import com.apptasticsoftware.rssreader.module.dc.internal.DcItemDataImpl;
import com.apptasticsoftware.rssreader.module.dc.internal.DcItemImpl;
import com.apptasticsoftware.rssreader.module.georss.internal.*;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.stream.Collectors;

import static com.github.npathai.hamcrestopt.OptionalMatchers.isPresentAnd;
import static com.github.npathai.hamcrestopt.OptionalMatchers.isPresentAndIs;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DcFeedReaderTest {

    @Test
    void example1() {
        var items = new DcFeedReader().read(fromFile("module/dc/example1.xml")).collect(Collectors.toList());
        assertEquals(1, items.size());

        var item = items.get(0);
        var channel = (DcChannel) item.getChannel();

        assertThat(channel.getTitle(), is("Meerkat"));
        assertThat(channel.getLink(), is("http://meerkat.oreillynet.com"));
        assertThat(channel.getDescription(), is("Meerkat: An Open Wire Service"));
        assertThat(channel.getDcTitle(), isPresentAndIs("Channel title"));
        assertThat(channel.getDcCreator(), isPresentAndIs("Rael Dornfest (mailto:rael@oreilly.com)"));
        assertThat(channel.getDcSubject(), isPresentAndIs("Channel subject"));
        assertThat(channel.getDcDescription(), isPresentAndIs("Channel description"));
        assertThat(channel.getDcPublisher(), isPresentAndIs("The O'Reilly Network"));
        assertThat(channel.getDcContributor(), isPresentAndIs("Channel contributor"));
        assertThat(channel.getDcDate(), isPresentAndIs("2000-01-01T12:00+00:00"));
        assertThat(channel.getDcType(), isPresentAndIs("Channel type"));
        assertThat(channel.getDcFormat(), isPresentAndIs("Channel format"));
        assertThat(channel.getDcIdentifier(), isPresentAndIs("Channel identifier"));
        assertThat(channel.getDcSource(), isPresentAndIs("Channel source"));
        assertThat(channel.getDcLanguage(), isPresentAndIs("en"));
        assertThat(channel.getDcRelation(), isPresentAndIs("Channel relation"));
        assertThat(channel.getDcCoverage(), isPresentAndIs("Channel coverage"));
        assertThat(channel.getDcRights(), isPresentAndIs("Copyright © 2000 O'Reilly & Associates, Inc."));

        assertThat(item.getTitle(), isPresentAndIs("XML: A Disruptive Technology"));
        //assertThat(item.getLink(), isPresentAndIs("http://c.moreover.com/click/here.pl?r123"));
        assertThat(item.getDcTitle(), isPresentAndIs("Item title"));
        assertThat(item.getDcCreator(), isPresentAndIs("Simon St.Laurent (mailto:simonstl@simonstl.com)"));
        assertThat(item.getDcSubject(), isPresentAndIs("XML"));
        assertThat(item.getDcDescription(), isPresentAnd(containsString("XML is placing increasingly heavy loads on the existing technical")));
        assertThat(item.getDcPublisher(), isPresentAndIs("The O'Reilly Network"));
        assertThat(item.getDcContributor(), isPresentAndIs("Item contributor"));
        assertThat(item.getDcDate(), isPresentAndIs("2000-01-01T12:00+00:00"));
        assertThat(item.getDcType(), isPresentAndIs("Item type"));
        assertThat(item.getDcFormat(), isPresentAndIs("Item format"));
        assertThat(item.getDcIdentifier(), isPresentAndIs("Item identifier"));
        assertThat(item.getDcSource(), isPresentAndIs("Item source"));
        assertThat(item.getDcLanguage(), isPresentAndIs("en"));
        assertThat(item.getDcRelation(), isPresentAndIs("Item relation"));
        assertThat(item.getDcCoverage(), isPresentAndIs("Item coverage"));
        assertThat(item.getDcRights(), isPresentAndIs("Copyright © 2000 O'Reilly & Associates, Inc."));
    }

    @Test
    void equalsContract() {
        EqualsVerifier.simple().forClass(DcChannelImpl.class).withNonnullFields("dcData").withIgnoredFields("dateTimeParser").withIgnoredFields("category").withNonnullFields("categories").withIgnoredFields("syUpdatePeriod").withIgnoredFields("syUpdateFrequency").verify();
        EqualsVerifier.simple().forClass(DcChannelDataImpl.class).verify();
        EqualsVerifier.simple().forClass(DcItemImpl.class).withNonnullFields("dcData").withIgnoredFields("defaultComparator").withIgnoredFields("dateTimeParser").withIgnoredFields("category").withNonnullFields("categories").withIgnoredFields("enclosure").withNonnullFields("enclosures").verify();
        EqualsVerifier.simple().forClass(DcItemDataImpl.class).verify();
        EqualsVerifier.simple().forClass(MetaData.class).verify();
    }

    private InputStream fromFile(String fileName) {
        return getClass().getClassLoader().getResourceAsStream(fileName);
    }
}
