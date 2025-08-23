package com.apptasticsoftware.rssreader.module.mediarss;

import com.apptasticsoftware.rssreader.util.ItemComparator;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.npathai.hamcrestopt.OptionalMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

// https://feeds.bloomberg.com/markets/news.rss

class MediaRssReaderTest {

    @Test
    void readMediaRssFeed() {
        var res = new MediaRssReader().read(fromFile("media-rss.xml"))
                .collect(Collectors.toList());

        assertEquals(10, res.size());
    }

    @Test
    void readMediaRssFeedItemTitle() {
        var res = new MediaRssReader().read(fromFile("media-rss.xml"))
                .sorted(ItemComparator.oldestPublishedItemFirst())
                .collect(Collectors.toList());

        MediaRssItem item = res.get(0);
        assertThat(item.getTitle(), isPresentAnd(equalTo("Ignitis_wind")));
    }

    @Test
    void readMediaRssFeedItemPubDate() {
        var res = new MediaRssReader().read(fromFile("media-rss.xml"))
                .sorted(ItemComparator.oldestPublishedItemFirst())
                .collect(Collectors.toList());

        MediaRssItem item = res.get(0);
        assertThat(item.getPubDate(), isPresentAnd(equalTo("Mon, 07 Nov 2022 14:51:45 -0500")));
    }

    @Test
    void readMediaRssFeedItemLink() {
        var res = new MediaRssReader().read(fromFile("media-rss.xml"))
                .sorted(ItemComparator.oldestPublishedItemFirst())
                .collect(Collectors.toList());

        MediaRssItem item = res.get(0);
        assertThat(item.getLink(), isPresentAnd(equalTo("https://vimeo.com/768251452")));
    }

    @Test
    void readMediaRssFeedDescription() {
        var res = new MediaRssReader().read(fromFile("media-rss.xml"))
                .sorted(ItemComparator.oldestPublishedItemFirst())
                .collect(Collectors.toList());

        MediaRssItem item = res.get(0);
        assertThat(item.getDescription(), isPresentAnd(equalTo("This is &quot;Ignitis_wind&quot; by pvz.lt on Vimeo, the home for high quality videos and the people who love them.")));
    }

    @Test
    void readMediaRssFeedGuid() {
        var res = new MediaRssReader().read(fromFile("media-rss.xml"))
                .sorted(ItemComparator.oldestPublishedItemFirst())
                .collect(Collectors.toList());

        MediaRssItem item = res.get(0);
        assertThat(item.getGuid(), isPresentAnd(equalTo("tag:vimeo,2022-11-07:clip768251452")));
    }

    @Test
    void readMediaRssFeedIsPermaLink() {
        var res = new MediaRssReader().read(fromFile("media-rss.xml"))
                .sorted(ItemComparator.oldestPublishedItemFirst())
                .collect(Collectors.toList());

        MediaRssItem item = res.get(0);
        assertThat(item.getIsPermaLink(), isPresentAnd(equalTo(false)));
    }

    @Test
    void readMediaRssFeedThumbnail() {
        var res = new MediaRssReader().read(fromFile("media-rss.xml"))
                .sorted(ItemComparator.oldestPublishedItemFirst())
                .collect(Collectors.toList());

        MediaRssItem item = res.get(0);
        MediaContent content = item.getMediaContents().get(0);
        MediaThumbnail mediaThumbnail = content.getMediaThumbnails().get(0);

        assertEquals("https://i.vimeocdn.com/video/1542457228-31ab55501fdd5316663c63781ae1a37932abc4b314bcc619e3377c0ca85b859d-d_960", mediaThumbnail.getUrl());
        assertThat(mediaThumbnail.getHeight(), isPresentAnd(equalTo(540)));
        assertThat(mediaThumbnail.getWidth(), isPresentAnd(equalTo(960)));
        assertThat(mediaThumbnail.getTime(), isEmpty());
    }

    @Test
    void readMediaRssContent() {
        var items = new MediaRssReader().read(fromFile("media-rss-content.xml"))
                .collect(Collectors.toList());

        assertEquals(1, items.size());
        MediaRssItem item = items.get(0);
        assertEquals(2, item.getMediaContents().size());

        // First media content
        MediaContent mediaContent = item.getMediaContents().get(0);
        assertNotNull(mediaContent);
        assertThat(mediaContent.getUrl(), isPresentAnd(equalTo("http://d1nixf144dcz0j.cloudfront.net/shade.mp4")));
        assertThat(mediaContent.getFileSize(), isPresentAnd(equalTo(1326739508L)));
        assertThat(mediaContent.getMedium(), isPresentAnd(equalTo("video")));
        assertThat(mediaContent.getType(), isPresentAnd(equalTo("video/mp4")));
        assertThat(mediaContent.isDefault(), isPresentAnd(equalTo(true)));
        assertThat(mediaContent.getExpression(), isPresentAnd(equalTo("sample")));
        assertThat(mediaContent.getBitrate(), isPresentAnd(equalTo(3317.2d)));
        assertThat(mediaContent.getFramerate(), isPresentAnd(equalTo(25.3d)));
        assertThat(mediaContent.getSamplingrate(), isPresentAnd(equalTo(44.1d)));
        assertThat(mediaContent.getSamplingrate(), isPresentAnd(equalTo(44.1d)));
        assertThat(mediaContent.getChannels(), isPresentAnd(equalTo(2)));
        assertThat(mediaContent.getDuration(), isPresentAnd(equalTo(120)));
        assertThat(mediaContent.getHeight(), isPresentAnd(equalTo(200)));
        assertThat(mediaContent.getWidth(), isPresentAnd(equalTo(300)));
        assertThat(mediaContent.getLang(), isPresentAnd(equalTo("en-us")));

        MediaPlayer mediaPlayer = mediaContent.getMediaPlayer().orElse(null);
        assertNotNull(mediaPlayer);
        assertThat(mediaPlayer.getUrl(), equalTo("https://player.vimeo.com/video/768251452?h=4d67921198"));
        assertThat(mediaPlayer.getHeight(), isPresentAnd(equalTo(480)));
        assertThat(mediaPlayer.getWidth(), isPresentAnd(equalTo(640)));

        assertThat(mediaContent.getMediaCredits().size(), equalTo(2));
        MediaCredit mediaCredit1 = mediaContent.getMediaCredits().get(0);
        assertThat(mediaCredit1.getRole(), isPresentAnd(equalTo("producer")));
        assertThat(mediaCredit1.getScheme(), isPresentAnd(equalTo("urn:ebu")));
        assertThat(mediaCredit1.getCredit(), equalTo("entity name"));
        MediaCredit mediaCredit2 = mediaContent.getMediaCredits().get(1);
        assertThat(mediaCredit2.getRole(), isPresentAnd(equalTo("owner")));
        assertThat(mediaCredit2.getScheme(), isPresentAnd(equalTo("urn:yvs")));
        assertThat(mediaCredit2.getCredit(), equalTo("copyright holder of the entity"));

        MediaTitle mediaTitle = mediaContent.getMediaTitle().orElse(null);
        assertNotNull(mediaTitle);
        assertThat(mediaTitle.getType(), isPresentAnd(equalTo("plain")));
        assertThat(mediaTitle.getTitle(), equalTo("Title 1"));

        MediaDescription mediaDescription = mediaContent.getMediaDesciption().orElse(null);
        assertNotNull(mediaDescription);
        assertThat(mediaDescription.getType(), isPresentAnd(equalTo("plain")));
        assertThat(mediaDescription.getDescription(), equalTo("Get perfectly done steaks every time"));

        MediaCopyright mediaCopyright = mediaContent.getMediaCopyright().orElse(null);
        assertNotNull(mediaCopyright);
        assertThat(mediaCopyright.getUrl(), isPresentAnd(equalTo("https://creativecommons.org/licenses/by/4.0/")));
        assertThat(mediaCopyright.getCopyright(), equalTo("2005 FooBar Media"));

        assertThat(mediaContent.getMediaKeywords(), equalTo(List.of("Steaks", "Summer")));
        assertThat(mediaContent.getMediaRestrictions(), hasSize(2));
        assertThat(mediaContent.getMediaRestrictions().get(0).getType(), equalTo("sharing"));
        assertThat(mediaContent.getMediaRestrictions().get(0).getRelationship(), equalTo("deny"));
        assertThat(mediaContent.getMediaRestrictions().get(1).getType(), equalTo("country"));
        assertThat(mediaContent.getMediaRestrictions().get(1).getRelationship(), equalTo("allow"));
        assertThat(mediaContent.getMediaRestrictions().get(1).getRestriction(), equalTo("au us"));

        var community = mediaContent.getMediaCommunity().orElse(null);
        assertNotNull(community);
        assertThat(community.getMediaStarRating().get().getAverage(), equalTo(3.5));
        assertThat(community.getMediaStarRating().get().getCount(), equalTo(20));
        assertThat(community.getMediaStarRating().get().getMin(), equalTo(1));
        assertThat(community.getMediaStarRating().get().getMax(), equalTo(10));
        assertThat(community.getMediaStatistics().get().getViews(), equalTo(5));
        assertThat(community.getMediaStatistics().get().getFavorites(), equalTo(4));
        assertThat(community.getMediaStatistics().get().getViews(), equalTo(5));
        assertThat(community.getMediaTags().get().getTags(), equalTo("news: 5, abc:3"));

        assertThat(mediaContent.getMediaComments(), equalTo(List.of("comment1", "comment2")));
        assertThat(mediaContent.getMediaResponses(), equalTo(List.of("http://www.response1.com", "http://www.response2.com")));
        assertThat(mediaContent.getMediaBackLinks(), equalTo(List.of("http://www.backlink1.com", "http://www.backlink2.com")));

        assertThat(mediaContent.getMediaStatus().get().getReason(), isPresentAndIs("http://www.reasonforblocking.com"));
        assertThat(mediaContent.getMediaStatus().get().getState(), equalTo("blocked"));

        assertThat(mediaContent.getMediaPrices().get(0).getType(), equalTo("rent"));
        assertThat(mediaContent.getMediaPrices().get(0).getCurrency(), isPresentAndIs("EUR"));
        assertThat(mediaContent.getMediaPrices().get(0).getPrice(), isPresentAndIs(19.99));
        assertThat(mediaContent.getMediaPrices().get(1).getType(), equalTo("subscription"));
        assertThat(mediaContent.getMediaPrices().get(1).getInfo(), isPresentAndIs("http://www.dummy.jp/subscription_info"));
        assertThat(mediaContent.getMediaPrices().get(1).getCurrency(), isPresentAndIs("USD"));
        assertThat(mediaContent.getMediaPrices().get(1).getPrice(), isPresentAndIs(18.88));

        assertThat(mediaContent.getMediaEmbed().get().getUrl(), equalTo("http://www.foo.com/player.swf"));
        assertThat(mediaContent.getMediaEmbed().get().getHeight(), equalTo(323));
        assertThat(mediaContent.getMediaEmbed().get().getWidth(), equalTo(512));
        assertThat(mediaContent.getMediaEmbed().get().getParams().size(), is(5));
        assertThat(mediaContent.getMediaEmbed().get().getParams(), hasEntry("type", "application/x-shockwave-flash"));
        assertThat(mediaContent.getMediaEmbed().get().getParams(), hasEntry("width", "512"));
        assertThat(mediaContent.getMediaEmbed().get().getParams(), hasEntry("height", "323"));
        assertThat(mediaContent.getMediaEmbed().get().getParams(), hasEntry("allowFullScreen", "true"));
        assertThat(mediaContent.getMediaEmbed().get().getParams(), hasEntry("flashVars", "id=12345&vid=678912i&lang=en-us&intl=us&thumbUrl=http://www.foo.com/thumbnail.jpg"));

        assertThat(mediaContent.getMediaSubTitles().get(0).getType(), equalTo("application/smil"));
        assertThat(mediaContent.getMediaSubTitles().get(0).getLang(), equalTo("en-us"));
        assertThat(mediaContent.getMediaSubTitles().get(0).getHref(), equalTo("http://www.foo.org/subtitle.smil"));
        assertThat(mediaContent.getMediaSubTitles().get(1).getType(), equalTo("application/smil"));
        assertThat(mediaContent.getMediaSubTitles().get(1).getLang(), equalTo("fr-FR"));
        assertThat(mediaContent.getMediaSubTitles().get(1).getHref(), equalTo("http://www.foo.org/fr/subtitle.smil"));

        assertThat(mediaContent.getMediaPeerLinks().get(0).getType(), equalTo("application/x-bittorrent"));
        assertThat(mediaContent.getMediaPeerLinks().get(0).getHref(), equalTo("http://www.foo.org/sampleFile480.torrent"));
        assertThat(mediaContent.getMediaPeerLinks().get(1).getType(), equalTo("application/x-bittorrent"));
        assertThat(mediaContent.getMediaPeerLinks().get(1).getHref(), equalTo("http://www.foo.org/sampleFile1080.torrent"));

        assertThat(mediaContent.getMediaLocations().get(0).getDescription(), equalTo("My house"));
        assertThat(mediaContent.getMediaLocations().get(0).getStart(), equalTo("00:01"));
        assertThat(mediaContent.getMediaLocations().get(0).getEnd(), equalTo("01:00"));

        assertThat(mediaContent.getMediaScenes().get(0).getSceneTitle(), equalTo("sceneTitle1"));
        assertThat(mediaContent.getMediaScenes().get(0).getSceneDescription(), equalTo("sceneDesc1"));
        assertThat(mediaContent.getMediaScenes().get(0).getSceneStartTime(), equalTo("00:15"));
        assertThat(mediaContent.getMediaScenes().get(0).getSceneEndTime(), equalTo("00:45"));
        assertThat(mediaContent.getMediaScenes().get(1).getSceneTitle(), equalTo("sceneTitle2"));
        assertThat(mediaContent.getMediaScenes().get(1).getSceneDescription(), equalTo("sceneDesc2"));
        assertThat(mediaContent.getMediaScenes().get(1).getSceneStartTime(), equalTo("00:57"));
        assertThat(mediaContent.getMediaScenes().get(1).getSceneEndTime(), equalTo("01:45"));

        assertThat(mediaContent.getMediaScenes().get(0).getSceneTitle(), equalTo("sceneTitle1"));
        assertThat(mediaContent.getMediaScenes().get(0).getSceneDescription(), equalTo("sceneDesc1"));
        assertThat(mediaContent.getMediaScenes().get(0).getSceneStartTime(), equalTo("00:15"));
        assertThat(mediaContent.getMediaScenes().get(0).getSceneEndTime(), equalTo("00:45"));
        assertThat(mediaContent.getMediaScenes().get(1).getSceneTitle(), equalTo("sceneTitle2"));
        assertThat(mediaContent.getMediaScenes().get(1).getSceneDescription(), equalTo("sceneDesc2"));
        assertThat(mediaContent.getMediaScenes().get(1).getSceneStartTime(), equalTo("00:57"));
        assertThat(mediaContent.getMediaScenes().get(1).getSceneEndTime(), equalTo("01:45"));


        // Second media content
        MediaContent mediaContent2 = item.getMediaContents().get(1);
        MediaTitle mediaTitle2 = mediaContent2.getMediaTitle().orElse(null);
        assertNotNull(mediaTitle2);
        assertThat(mediaTitle2.getType(), isPresentAnd(equalTo("plain")));
        assertThat(mediaTitle2.getTitle(), equalTo("Title 2"));
    }

    @Test
    void readMediaRssGroup() {
        var items = new MediaRssReader().read(fromFile("media-rss-group.xml"))
                .collect(Collectors.toList());

        assertEquals(1, items.size());
        MediaRssItem item = items.get(0);

        assertNotNull(item.getMediaGroup());
        assertThat(item.getMediaGroup(), isPresent());
        assertThat(item.getMediaGroup().map(MediaGroup::getMediaContents).orElse(List.of()).size(), equalTo(2));

        var group = item.getMediaGroup().orElse(null);
        assertNotNull(group);

        var title = group.getMediaTitle().orElse(null);
        assertNotNull(title);
        assertThat(title.getTitle(), equalTo("Grilling Steaks for Summer"));
        assertThat(title.getType(), isPresentAndIs("plain"));

        var description = group.getMediaDescription().orElse(null);
        assertNotNull(description);
        assertThat(description.getDescription(), equalTo("Get perfectly done steaks every time"));
        assertThat(description.getType(), isPresentAndIs("plain"));

        var keywords = group.getMediaKeywords();
        assertThat(keywords, equalTo(List.of("Steaks", "Summer")));

        assertThat(group.getMediaRestrictions(), hasSize(2));
        assertThat(group.getMediaRestrictions().get(0).getType(), equalTo("sharing"));
        assertThat(group.getMediaRestrictions().get(0).getRelationship(), equalTo("deny"));
        assertThat(group.getMediaRestrictions().get(1).getType(), equalTo("country"));
        assertThat(group.getMediaRestrictions().get(1).getRelationship(), equalTo("allow"));
        assertThat(group.getMediaRestrictions().get(1).getRestriction(), equalTo("au us"));


        var content1 = item.getMediaGroup().map(g -> g.getMediaContents().get(0)).orElse(null);
        assertNotNull(content1);
        assertThat(content1.getUrl(), isPresentAndIs("http://www.example.com/examples/mrss/example1.mp4"));
        assertThat(content1.getFileSize(), isPresentAndIs(1326739508L));
        assertThat(content1.getMedium(), isPresentAndIs("video"));
        assertThat(content1.getType(), isPresentAndIs("video/mp4"));
        assertThat(content1.isDefault(), isPresentAndIs(true));
        assertThat(content1.getExpression(), isPresentAndIs("sample"));
        assertThat(content1.getBitrate(), isPresentAndIs(3317.2d));
        assertThat(content1.getFramerate(), isPresentAndIs(25.3d));
        assertThat(content1.getSamplingrate(), isPresentAndIs(44.1d));
        assertThat(content1.getSamplingrate(), isPresentAndIs(44.1d));
        assertThat(content1.getChannels(), isPresentAndIs(2));
        assertThat(content1.getDuration(), isPresentAndIs(120));
        assertThat(content1.getHeight(), isPresentAndIs(200));
        assertThat(content1.getWidth(), isPresentAndIs(300));
        assertThat(content1.getLang(), isPresentAndIs("en-us"));

        var content2 = item.getMediaGroup().map(g -> g.getMediaContents().get(1)).orElse(null);
        assertNotNull(content2);
        assertThat(content2.getUrl(), isPresentAndIs("http://www.example.com/examples/mrss/example2.mp4"));
        assertThat(content2.getType(), isPresentAndIs("video/mp4"));
        assertThat(content2.getDuration(), isPresentAndIs(91));
        assertThat(content2.getHeight(), isPresentAndIs(406));
        assertThat(content2.getWidth(), isPresentAndIs(720));

        var thumbnail1 = item.getMediaGroup().map(g -> g.getMediaThumbnails().get(0)).orElse(null);
        assertNotNull(thumbnail1);
        assertThat(thumbnail1.getUrl(), is("http://www.example.com/examples/mrss/example.jpg"));
        assertThat(thumbnail1.getWidth(), isPresentAndIs(720));

        var thumbnail2 = item.getMediaGroup().map(g -> g.getMediaThumbnails().get(1)).orElse(null);
        assertNotNull(thumbnail2);
        assertThat(thumbnail2.getUrl(), is("http://www.foo.com/keyframe.jpg"));
        assertThat(thumbnail2.getWidth(), isPresentAndIs(75));
        assertThat(thumbnail2.getHeight(), isPresentAndIs(50));
        assertThat(thumbnail2.getTime(), isPresentAndIs("12:05:01.123"));

        var community = item.getMediaGroup().flatMap(MediaGroup::getMediaCommunity).orElse(null);
        assertNotNull(community);

        var starRating = community.getMediaStarRating().orElse(null);
        assertNotNull(starRating);
        assertThat(starRating.getCount(), is(20));
        assertThat(starRating.getAverage(), is(3.5));
        assertThat(starRating.getMax(), is(10));
        assertThat(starRating.getMin(), is(1));

        var statistics = community.getMediaStatistics().orElse(null);
        assertNotNull(statistics);
        assertThat(statistics.getViews(), is(56));
        assertThat(statistics.getFavorites(), is(5));

        var tags = community.getMediaTags().orElse(null);
        assertNotNull(tags);
        assertThat(tags.getTags(), is("news: 5, abc:3, reuters"));

        var copyright = group.getMediaCopyright().orElse(null);
        assertNotNull(copyright);
        assertThat(copyright.getCopyright(), equalTo("2005 FooBar Media"));
        assertThat(copyright.getUrl(), isPresentAndIs("https://creativecommons.org/licenses/by/4.0/"));

        assertThat(group.getMediaTexts(), hasSize(2));
        var text1 = group.getMediaTexts().get(0);
        assertThat(text1.getType(), isPresentAndIs("plain"));
        assertThat(text1.getText(), equalTo("Oh, say, can you see, by the dawn's early light"));
        var text2 = group.getMediaTexts().get(1);
        assertThat(text2.getType(), isPresentAndIs("plain"));
        assertThat(text2.getText(), equalTo("By the dawn's early light"));
        assertThat(text2.getLang(), isPresentAndIs("en"));
        assertThat(text2.getStart(), isPresentAndIs("00:00:10.000"));
        assertThat(text2.getEnd(), isPresentAndIs("00:00:17.000"));

        assertThat(group.getMediaComments(), equalTo(List.of("comment1", "comment2")));
        assertThat(group.getMediaResponses(), equalTo(List.of("http://www.response1.com", "http://www.response2.com")));
        assertThat(group.getMediaBackLinks(), equalTo(List.of("http://www.backlink1.com", "http://www.backlink2.com")));

        assertThat(group.getMediaStatus().get().getReason(), isPresentAndIs("http://www.reasonforblocking.com"));
        assertThat(group.getMediaStatus().get().getState(), equalTo("blocked"));

        assertThat(group.getMediaPrices().get(0).getType(), equalTo("rent"));
        assertThat(group.getMediaPrices().get(0).getCurrency(), isPresentAndIs("EUR"));
        assertThat(group.getMediaPrices().get(0).getPrice(), isPresentAndIs(19.99));
        assertThat(group.getMediaPrices().get(1).getType(), equalTo("subscription"));
        assertThat(group.getMediaPrices().get(1).getInfo(), isPresentAndIs("http://www.dummy.jp/subscription_info"));
        assertThat(group.getMediaPrices().get(1).getCurrency(), isPresentAndIs("USD"));
        assertThat(group.getMediaPrices().get(1).getPrice(), isPresentAndIs(18.88));

        assertThat(group.getMediaEmbed().get().getUrl(), equalTo("http://www.foo.com/player.swf"));
        assertThat(group.getMediaEmbed().get().getHeight(), equalTo(323));
        assertThat(group.getMediaEmbed().get().getWidth(), equalTo(512));
        assertThat(group.getMediaEmbed().get().getParams().size(), is(5));
        assertThat(group.getMediaEmbed().get().getParams(), hasEntry("type", "application/x-shockwave-flash"));
        assertThat(group.getMediaEmbed().get().getParams(), hasEntry("width", "512"));
        assertThat(group.getMediaEmbed().get().getParams(), hasEntry("height", "323"));
        assertThat(group.getMediaEmbed().get().getParams(), hasEntry("allowFullScreen", "true"));
        assertThat(group.getMediaEmbed().get().getParams(), hasEntry("flashVars", "id=12345&vid=678912i&lang=en-us&intl=us&thumbUrl=http://www.foo.com/thumbnail.jpg"));

        assertThat(group.getMediaSubTitles().get(0).getType(), equalTo("application/smil"));
        assertThat(group.getMediaSubTitles().get(0).getLang(), equalTo("en-us"));
        assertThat(group.getMediaSubTitles().get(0).getHref(), equalTo("http://www.foo.org/subtitle.smil"));
        assertThat(group.getMediaSubTitles().get(1).getType(), equalTo("application/smil"));
        assertThat(group.getMediaSubTitles().get(1).getLang(), equalTo("fr-FR"));
        assertThat(group.getMediaSubTitles().get(1).getHref(), equalTo("http://www.foo.org/fr/subtitle.smil"));

        assertThat(group.getMediaPeerLinks().get(0).getType(), equalTo("application/x-bittorrent"));
        assertThat(group.getMediaPeerLinks().get(0).getHref(), equalTo("http://www.foo.org/sampleFile480.torrent"));
        assertThat(group.getMediaPeerLinks().get(1).getType(), equalTo("application/x-bittorrent"));
        assertThat(group.getMediaPeerLinks().get(1).getHref(), equalTo("http://www.foo.org/sampleFile1080.torrent"));

        assertThat(group.getMediaLocations().get(0).getDescription(), equalTo("My house"));
        assertThat(group.getMediaLocations().get(0).getStart(), equalTo("00:01"));
        assertThat(group.getMediaLocations().get(0).getEnd(), equalTo("01:00"));

        assertThat(group.getMediaScenes().get(0).getSceneTitle(), equalTo("sceneTitle1"));
        assertThat(group.getMediaScenes().get(0).getSceneDescription(), equalTo("sceneDesc1"));
        assertThat(group.getMediaScenes().get(0).getSceneStartTime(), equalTo("00:15"));
        assertThat(group.getMediaScenes().get(0).getSceneEndTime(), equalTo("00:45"));
        assertThat(group.getMediaScenes().get(1).getSceneTitle(), equalTo("sceneTitle2"));
        assertThat(group.getMediaScenes().get(1).getSceneDescription(), equalTo("sceneDesc2"));
        assertThat(group.getMediaScenes().get(1).getSceneStartTime(), equalTo("00:57"));
        assertThat(group.getMediaScenes().get(1).getSceneEndTime(), equalTo("01:45"));

    }

    @Test
    void mediaRssExample1() {
        var items = new MediaRssReader().read(fromFile("media-rss-example-1.xml"))
                .collect(Collectors.toList());

        assertEquals(1, items.size());
        MediaRssItem item = items.get(0);
        assertNotNull(item);

        // Rating
        var rating = item.getMediaRating().orElse(null);
        assertNotNull(rating);
        assertThat(rating.getScheme(), isPresentAnd(equalTo("urn:simple")));
        assertEquals(rating.getRating(), "nonadult");

        // Keywords
        var keywords = item.getMediaKeywords();
        assertEquals(2, keywords.size());
        assertEquals("Steaks", keywords.get(0));
        assertEquals("Summer", keywords.get(1));

        // Thumbnails
        var thumbnails = item.getMediaThumbnails();
        assertEquals(2, thumbnails.size());
        assertEquals("http://www.example.com/examples/mrss/example.jpg", thumbnails.get(0).getUrl());
        assertThat(thumbnails.get(0).getWidth(), isPresentAnd(equalTo(720)));
        assertThat(thumbnails.get(0).getHeight(), isEmpty());
        assertThat(thumbnails.get(0).getTime(), isPresentAnd(equalTo("50.10")));
        assertThat(thumbnails.get(0).getTimeDuration(), isPresent());
        assertEquals("http://www.foo.com/keyframe.jpg", thumbnails.get(1).getUrl());
        assertThat(thumbnails.get(1).getWidth(), isPresentAnd(equalTo(75)));
        assertThat(thumbnails.get(1).getHeight(), isPresentAnd(equalTo(50)));
        assertThat(thumbnails.get(1).getTime(), isPresentAnd(equalTo("12:05:01.123")));
        assertThat(thumbnails.get(1).getTimeDuration(), isPresent());
    }

    @Test
    void mediaRssExample2() {
        var items = new MediaRssReader().read(fromFile("media-rss-example-2.xml"))
                .collect(Collectors.toList());

        assertEquals(1, items.size());
        MediaRssItem item = items.get(0);
        assertNotNull(item);
    }

    @Test
    void mediaRssExample3() {
        var items = new MediaRssReader().read(fromFile("media-rss-example-3.xml"))
                .collect(Collectors.toList());

        assertEquals(1, items.size());
        MediaRssItem item = items.get(0);
        assertNotNull(item);
    }

    @Test
    void mediaRssExample4() {
        var items = new MediaRssReader().read(fromFile("media-rss-example-4.xml"))
                .collect(Collectors.toList());

        assertEquals(1, items.size());
        MediaRssItem item = items.get(0);
        assertNotNull(item);
    }

    @Test
    void mediaRssExample5() {
        var items = new MediaRssReader().read(fromFile("media-rss-example-5.xml"))
                .collect(Collectors.toList());

        assertEquals(1, items.size());
        MediaRssItem item = items.get(0);
        assertNotNull(item);
    }

    @Test
    void equalsContract() {
        EqualsVerifier.simple().forClass(MediaRssItem.class).withIgnoredFields("defaultComparator").withIgnoredFields("dateTimeParser").withIgnoredFields("category").withNonnullFields("categories").withIgnoredFields("enclosure").withNonnullFields("enclosures").verify();
    }

    private InputStream fromFile(String fileName) {
        return getClass().getClassLoader().getResourceAsStream(fileName);
    }
}
