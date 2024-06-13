package com.apptasticsoftware.rssreader.module.mediarss;

import com.apptasticsoftware.rssreader.util.ItemComparator;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static com.github.npathai.hamcrestopt.OptionalMatchers.isEmpty;
import static com.github.npathai.hamcrestopt.OptionalMatchers.isPresentAnd;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MediaRssReaderTest {

    @Test
    void readMediaRssFeed() {
        var res = new MediaRssReader().read(MediaRssReader.class.getClassLoader().getResourceAsStream("media-rss.xml"))
                .sorted(ItemComparator.oldestItemFirst())
                .collect(Collectors.toList());

        assertEquals(9, res.size());
    }

    @Test
    void readMediaRssFeedItemTitle() {
        var res = new MediaRssReader().read(MediaRssReader.class.getClassLoader().getResourceAsStream("media-rss.xml"))
                .sorted(ItemComparator.oldestItemFirst())
                .collect(Collectors.toList());

        MediaRssItem item = res.get(0);
        assertThat(item.getTitle(), isPresentAnd(equalTo("Ignitis_wind")));
    }

    @Test
    void readMediaRssFeedItemPubDate() {
        var res = new MediaRssReader().read(MediaRssReader.class.getClassLoader().getResourceAsStream("media-rss.xml"))
                .sorted(ItemComparator.oldestItemFirst())
                .collect(Collectors.toList());

        MediaRssItem item = res.get(0);
        assertThat(item.getPubDate(), isPresentAnd(equalTo("Mon, 07 Nov 2022 14:51:45 -0500")));
    }

    @Test
    void readMediaRssFeedItemLink() {
        var res = new MediaRssReader().read(MediaRssReader.class.getClassLoader().getResourceAsStream("media-rss.xml"))
                .sorted(ItemComparator.oldestItemFirst())
                .collect(Collectors.toList());

        MediaRssItem item = res.get(0);
        assertThat(item.getLink(), isPresentAnd(equalTo("https://vimeo.com/768251452")));
    }

    @Test
    void readMediaRssFeedDescription() {
        var res = new MediaRssReader().read(MediaRssReader.class.getClassLoader().getResourceAsStream("media-rss.xml"))
                .sorted(ItemComparator.oldestItemFirst())
                .collect(Collectors.toList());

        MediaRssItem item = res.get(0);
        assertThat(item.getDescription(), isPresentAnd(equalTo("This is &quot;Ignitis_wind&quot; by pvz.lt on Vimeo, the home for high quality videos and the people who love them.")));
    }

    @Test
    void readMediaRssFeedGuid() {
        var res = new MediaRssReader().read(MediaRssReader.class.getClassLoader().getResourceAsStream("media-rss.xml"))
                .sorted(ItemComparator.oldestItemFirst())
                .collect(Collectors.toList());

        MediaRssItem item = res.get(0);
        assertThat(item.getGuid(), isPresentAnd(equalTo("tag:vimeo,2022-11-07:clip768251452")));
    }

    @Test
    void readMediaRssFeedIsPermaLink() {
        var res = new MediaRssReader().read(MediaRssReader.class.getClassLoader().getResourceAsStream("media-rss.xml"))
                .sorted(ItemComparator.oldestItemFirst())
                .collect(Collectors.toList());

        MediaRssItem item = res.get(0);
        assertThat(item.getIsPermaLink(), isPresentAnd(equalTo(false)));
    }

    @Test
    void readMediaRssFeedThumbnail() {
        var res = new MediaRssReader().read(MediaRssReader.class.getClassLoader().getResourceAsStream("media-rss.xml"))
                .sorted(ItemComparator.oldestItemFirst())
                .collect(Collectors.toList());

        MediaRssItem item = res.get(0);
        MediaThumbnail mediaThumbnail = item.getMediaThumbnail().get();
        assertEquals("https://i.vimeocdn.com/video/1542457228-31ab55501fdd5316663c63781ae1a37932abc4b314bcc619e3377c0ca85b859d-d_960", mediaThumbnail.getUrl());
        assertThat(mediaThumbnail.getHeight(), isPresentAnd(equalTo(540)));
        assertThat(mediaThumbnail.getWidth(), isPresentAnd(equalTo(960)));
        assertThat(mediaThumbnail.getTime(), isEmpty());
    }

    @Test
    void equalsContract() {
        EqualsVerifier.simple().forClass(MediaRssItem.class).withIgnoredFields("defaultComparator").withIgnoredFields("dateTimeParser").withIgnoredFields("category").withNonnullFields("categories").withIgnoredFields("enclosure").withNonnullFields("enclosures").verify();
    }
}
