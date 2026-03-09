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
        var channel = first.getChannel().get();
        var item = first.getItem().get();
        assertThat(first.getChannel()).hasValue(channel);
        assertThat(first.getItem()).hasValue(item);
        assertThat(first.getFeedUrl()).isNotEmpty();
    }

    @Test
    void readEmptyRssFeed() throws Exception {
        var reader = new FeedReader();
        List<FeedData> results = reader.readFeed(fileUri("rss-feed-no-items.xml"))
                .collect(Collectors.toList());

        assertThat(results).hasSize(1);
        FeedData first = results.get(0);
        assertThat(first.getChannel()).isNotEmpty();
        assertThat(first.getItem()).isEmpty();
    }

    @Test
    void readEmptyAtomFeed() throws Exception {
        var reader = new FeedReader();
        List<FeedData> results = reader.readFeed(fileUri("atom-feed-no-entries.xml"))
                .collect(Collectors.toList());

        assertThat(results).hasSize(1);
        FeedData first = results.get(0);
        assertThat(first.getChannel()).isNotEmpty();
        assertThat(first.getItem()).isEmpty();
    }

    @Test
    void equalsContract() {
        EqualsVerifier.simple().forClass(FeedDataImpl.class).verify();
    }

    private String fileUri(String fileName) throws URISyntaxException {
        return getClass().getClassLoader().getResource(fileName).toURI().toString();
    }
}
