package com.apptasticsoftware.rssreader.module.opensearch;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.stream.Collectors;

import static com.github.npathai.hamcrestopt.OptionalMatchers.isPresentAnd;
import static com.github.npathai.hamcrestopt.OptionalMatchers.isPresentAndIs;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OpenSearchFeedReaderTest {

    @Test
    void example1() {
        var items = new OpenSearchFeedReader().read(fromFile("opensearch/example1.xml"))
                .collect(Collectors.toList());

        assertEquals(1, items.size());
        var item = items.get(0);
        OpenSearchChannel channel = (OpenSearchChannel) item.getChannel();

        assertThat(channel.getTitle(), is("Example.com Search: New York history"));
        assertThat(channel.getLink(), is("http://example.com/New+York+history"));
        assertThat(channel.getDescription(), is("Search results for \"New York history\" at Example.com"));
        assertThat(channel.getOpenSearchTotalResults(), isPresentAndIs(4230000));
        assertThat(channel.getOpenSearchStartIndex(), isPresentAndIs(21));
        assertThat(channel.getOpenSearchItemsPerPage(), isPresentAndIs(10));
        assertThat(channel.getOpenSearchQueries().size(), is(1));
        assertThat(channel.getOpenSearchQueries().get(0).getRole(), is("request"));
        assertThat(channel.getOpenSearchQueries().get(0).getTitle(), isPresentAndIs("History"));
        assertThat(channel.getOpenSearchQueries().get(0).getTotalResults(), isPresentAndIs(10));
        assertThat(channel.getOpenSearchQueries().get(0).getSearchTerms(), isPresentAndIs("New York History"));
        assertThat(channel.getOpenSearchQueries().get(0).getCount(), isPresentAndIs(15));
        assertThat(channel.getOpenSearchQueries().get(0).getStartIndex(), isPresentAndIs(0));
        assertThat(channel.getOpenSearchQueries().get(0).getStartPage(), isPresentAndIs(1));
        assertThat(channel.getOpenSearchQueries().get(0).getLanguage(), isPresentAndIs("en"));
        assertThat(channel.getOpenSearchQueries().get(0).getInputEncoding(), isPresentAndIs("UTF-8"));
        assertThat(channel.getOpenSearchQueries().get(0).getOutputEncoding(), isPresentAndIs("ISO-88859-1"));

        assertThat(item.getTitle(), isPresentAndIs("New York History"));
        assertThat(item.getLink(), isPresentAndIs("http://www.columbia.edu/cu/lweb/eguids/amerihist/nyc.html"));
        assertThat(item.getDescription(), isPresentAnd(containsString("... Harlem.NYC - A virtual tour and information on")));
    }

    @Test
    void example2() {
        var items = new OpenSearchFeedReader().read(fromFile("opensearch/example2.xml"))
                .collect(Collectors.toList());

        assertEquals(1, items.size());
        var item = items.get(0);
        OpenSearchChannel channel = (OpenSearchChannel) item.getChannel();

        assertThat(channel.getTitle(), is("Example.com Search: New York history"));
        assertThat(channel.getLink(), is("http://example.com/opensearchdescription.xml"));
        assertThat(channel.getLastBuildDate(), isPresentAndIs("2003-12-13T18:30:02Z"));
        assertThat(channel.getOpenSearchTotalResults(), isPresentAndIs(4230000));
        assertThat(channel.getOpenSearchStartIndex(), isPresentAndIs(21));
        assertThat(channel.getOpenSearchItemsPerPage(), isPresentAndIs(10));
        assertThat(channel.getOpenSearchQueries().get(0).getRole(), is("request"));
        assertThat(channel.getOpenSearchQueries().get(0).getTitle(), isPresentAndIs("History"));
        assertThat(channel.getOpenSearchQueries().get(0).getTotalResults(), isPresentAndIs(10));
        assertThat(channel.getOpenSearchQueries().get(0).getSearchTerms(), isPresentAndIs("New York History"));
        assertThat(channel.getOpenSearchQueries().get(0).getCount(), isPresentAndIs(15));
        assertThat(channel.getOpenSearchQueries().get(0).getStartIndex(), isPresentAndIs(0));
        assertThat(channel.getOpenSearchQueries().get(0).getStartPage(), isPresentAndIs(1));
        assertThat(channel.getOpenSearchQueries().get(0).getLanguage(), isPresentAndIs("en"));
        assertThat(channel.getOpenSearchQueries().get(0).getInputEncoding(), isPresentAndIs("UTF-8"));
        assertThat(channel.getOpenSearchQueries().get(0).getOutputEncoding(), isPresentAndIs("ISO-88859-1"));

        assertThat(item.getTitle(), isPresentAndIs("New York History"));
        assertThat(item.getLink(), isPresentAndIs("http://www.columbia.edu/cu/lweb/eguids/amerihist/nyc.html"));
        assertThat(item.getDescription(), isPresentAnd(containsString("... Harlem.NYC - A virtual tour and information on")));
    }

    @Test
    @SuppressWarnings("java:S5961")
    void equalsContract() {
        EqualsVerifier.simple().forClass(OpenSearchChannelImpl.class).withNonnullFields("openSearchData").withIgnoredFields("dateTimeParser").withIgnoredFields("category").withNonnullFields("categories").withIgnoredFields("syUpdatePeriod").withIgnoredFields("syUpdateFrequency").verify();
        EqualsVerifier.simple().forClass(OpenSearchItemImpl.class).withIgnoredFields("defaultComparator").withIgnoredFields("dateTimeParser").withIgnoredFields("category").withNonnullFields("categories").withIgnoredFields("enclosure").withNonnullFields("enclosures").verify();
        EqualsVerifier.simple().forClass(OpenSearchChannelDataImpl.class).verify();
        EqualsVerifier.simple().forClass(OpenSearchQuery.class).verify();
    }

    private InputStream fromFile(String fileName) {
        return getClass().getClassLoader().getResourceAsStream(fileName);
    }
}
