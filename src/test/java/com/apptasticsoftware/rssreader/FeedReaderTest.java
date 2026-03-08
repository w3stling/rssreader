package com.apptasticsoftware.rssreader;

import com.apptasticsoftware.rssreader.internal.FeedDataImpl;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class FeedReaderTest {

    @Test
    void readFeed() throws Exception {
        var reader = new FeedReader();
        List<FeedData> results = reader.readFeed(fileUri("rss-feed.xml"))
                .collect(Collectors.toList());

        assertThat(results).hasSize(20);
        FeedData first = results.get(0);
        assertThat(first.getChannel()).isNotNull();
        assertThat(first.getItem()).isNotNull();
        assertThat(first.getChannel()).isSameAs(first.getChannel());
        assertThat(first.getItem()).isSameAs(first.getItem());
        assertThat(first.getFeedUrl()).isNotEmpty();
    }

    @Test
    void readEmptyRssFeed() throws Exception {
        var reader = new FeedReader();
        List<FeedData> results = reader.readFeed(fileUri("rss-feed-no-items.xml"))
                .collect(Collectors.toList());

        assertThat(results).hasSize(1);
        FeedData first = results.get(0);
        assertThat(first.getChannel()).isNotNull();
        assertThat(first.getItem()).isNull();
    }

    @Test
    void readEmptyAtomFeed() throws Exception {
        var reader = new FeedReader();
        List<FeedData> results = reader.readFeed(fileUri("atom-feed-no-entries.xml"))
                .collect(Collectors.toList());

        assertThat(results).hasSize(1);
        FeedData first = results.get(0);
        assertThat(first.getChannel()).isNotNull();
        assertThat(first.getItem()).isNull();
    }

    @Test
    void equalsContract() {
        EqualsVerifier.simple().forClass(FeedDataImpl.class).verify();
    }

    private String fileUri(String fileName) throws URISyntaxException {
        return getClass().getClassLoader().getResource(fileName).toURI().toString();
    }
}
