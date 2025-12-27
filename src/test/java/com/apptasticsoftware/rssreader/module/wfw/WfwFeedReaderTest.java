package com.apptasticsoftware.rssreader.module.wfw;

import com.apptasticsoftware.rssreader.module.georss.internal.*;
import com.apptasticsoftware.rssreader.module.wfw.internal.WfwItemDataImpl;
import com.apptasticsoftware.rssreader.module.wfw.internal.WfwItemImpl;
import com.apptasticsoftware.rssreader.util.Default;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.stream.Collectors;

import static com.github.npathai.hamcrestopt.OptionalMatchers.isPresentAndIs;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WfwFeedReaderTest {

    @Test
    void example1() {
        var items = new WfwFeedReader().read(fromFile("module/wfw/example1.xml"))
                .collect(Collectors.toList());

        assertEquals(1, items.size());
        var item = items.get(0);
        var channel = item.getChannel();
        assertThat(channel.getTitle(), is("Example Website"));
        assertThat(channel.getLink(), is("https://www.example.com"));
        assertThat(channel.getDescription(), is("Latest news and updates"));

        assertThat(item.getTitle(), isPresentAndIs("First Sample Post"));
        assertThat(item.getLink(), isPresentAndIs("https://www.example.com/post1"));
        assertThat(item.getDescription(), isPresentAndIs("This is a summary of the first post."));
        assertThat(item.getPubDate(), isPresentAndIs("Mon, 27 May 2024 10:00:00 GMT"));
        assertThat(item.getPubDateAsZonedDateTime(), isPresentAndIs(Default.getDateTimeParser().parse("Mon, 27 May 2024 10:00:00 GMT")));
        assertThat(item.getWfwCommentRss(), isPresentAndIs("https://ekzemplo.com/news/130/comments.xml"));
        assertThat(item.getWfwComment(), isPresentAndIs("https://ekzemplo.com/comment?post=130"));
    }

    @Test
    void equalsContract() {
        EqualsVerifier.simple().forClass(WfwItemImpl.class).withNonnullFields("wfwData").withIgnoredFields("defaultComparator").withIgnoredFields("dateTimeParser").withIgnoredFields("category").withNonnullFields("categories").withIgnoredFields("enclosure").withNonnullFields("enclosures").verify();
        EqualsVerifier.simple().forClass(WfwItemDataImpl.class).verify();
    }

    private InputStream fromFile(String fileName) {
        return getClass().getClassLoader().getResourceAsStream(fileName);
    }
}
