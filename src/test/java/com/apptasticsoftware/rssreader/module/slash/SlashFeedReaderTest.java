package com.apptasticsoftware.rssreader.module.slash;

import com.apptasticsoftware.rssreader.AbstractRssReader;
import com.apptasticsoftware.rssreader.FeedReader;
import com.apptasticsoftware.rssreader.module.slash.internal.SlashItemDataImpl;
import com.apptasticsoftware.rssreader.module.slash.internal.SlashItemImpl;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.InputStream;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.github.npathai.hamcrestopt.OptionalMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("java:S5961")
class SlashFeedReaderTest {

    @ParameterizedTest
    @MethodSource("feedReaderArguments")
    void example1(AbstractRssReader<SlashChannel, SlashItem> feedReader) {
        var items = feedReader.read(fromFile("module/slash/example1.xml")).collect(Collectors.toList());
        assertEquals(1, items.size());

        var item = items.get(0);
        var channel = item.getChannel();
        assertThat(channel.getTitle(), is("Slashdot"));
        assertThat(channel.getDescription(), is("News for nerds, stuff that matters"));

        assertThat(item.getTitle(), isPresentAndIs("Jupiter Moon Ganymede May Have An Ocean"));
        assertThat(item.getLink(), isPresentAndIs("http://slashdot.org/article.pl?sid=00/12/17/0622203"));
        assertThat(item.getDescription(), isPresentAnd(startsWith("This article talks about how Jupiter's moon, Ganymede, may have a")));
        assertThat(item.getSlashSection(), isPresentAndIs("articles"));
        assertThat(item.getSlashDepartment(), isPresentAndIs("not-an-ocean-unless-there-are-lobsters"));
        assertThat(item.getSlashComments(), isPresentAndIs(177));
        assertThat(item.getSlashHitParade(), isPresentAndIs("177,155,105,33,6,3,0"));
    }

    @Test
    void equalsContract() {
        EqualsVerifier.simple().forClass(SlashItemImpl.class).withNonnullFields("slashData").withIgnoredFields("defaultComparator").withIgnoredFields("dateTimeParser").withIgnoredFields("category").withNonnullFields("categories").withIgnoredFields("enclosure").withNonnullFields("enclosures").verify();
        EqualsVerifier.simple().forClass(SlashItemDataImpl.class).verify();
    }

    private static Stream<? extends Arguments> feedReaderArguments() {
        return Stream.of(
                Arguments.of(new SlashFeedReader()),
                Arguments.of(new FeedReader())
        );
    }

    private InputStream fromFile(String fileName) {
        return getClass().getClassLoader().getResourceAsStream(fileName);
    }
}
