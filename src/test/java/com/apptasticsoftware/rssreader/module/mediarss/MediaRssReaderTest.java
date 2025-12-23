package com.apptasticsoftware.rssreader.module.mediarss;

import com.apptasticsoftware.rssreader.AbstractRssReader;
import com.apptasticsoftware.rssreader.FeedReader;
import com.apptasticsoftware.rssreader.module.mediarss.internal.MediaRssChannelImpl;
import com.apptasticsoftware.rssreader.module.mediarss.internal.MediaRssItemImpl;
import com.apptasticsoftware.rssreader.util.ItemComparator;
import com.apptasticsoftware.rssreader.util.Util;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.InputStream;
import java.time.Duration;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.github.npathai.hamcrestopt.OptionalMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SuppressWarnings("java:S5961")
class MediaRssReaderTest {

    @Test
    void readMediaRssFeed() {
        var res = new MediaRssReader().read(fromFile("mediarss/media-rss.xml"))
                .collect(Collectors.toList());

        assertEquals(10, res.size());
    }

    @Test
    void readMediaRssFeedItemTitle() {
        var res = new MediaRssReader().read(fromFile("mediarss/media-rss.xml"))
                .sorted(ItemComparator.oldestPublishedItemFirst())
                .collect(Collectors.toList());

        MediaRssItem item = res.get(0);
        assertThat(item.getTitle(), isPresentAnd(equalTo("Ignitis_wind")));
    }

    @Test
    void readMediaRssFeedItemPubDate() {
        var res = new MediaRssReader().read(fromFile("mediarss/media-rss.xml"))
                .sorted(ItemComparator.oldestPublishedItemFirst())
                .collect(Collectors.toList());

        MediaRssItem item = res.get(0);
        assertThat(item.getPubDate(), isPresentAnd(equalTo("Mon, 07 Nov 2022 14:51:45 -0500")));
    }

    @Test
    void readMediaRssFeedItemLink() {
        var res = new MediaRssReader().read(fromFile("mediarss/media-rss.xml"))
                .sorted(ItemComparator.oldestPublishedItemFirst())
                .collect(Collectors.toList());

        MediaRssItem item = res.get(0);
        assertThat(item.getLink(), isPresentAnd(equalTo("https://vimeo.com/768251452")));
    }

    @Test
    void readMediaRssFeedDescription() {
        var res = new MediaRssReader().read(fromFile("mediarss/media-rss.xml"))
                .sorted(ItemComparator.oldestPublishedItemFirst())
                .collect(Collectors.toList());

        MediaRssItem item = res.get(0);
        assertThat(item.getDescription(), isPresentAnd(equalTo("This is &quot;Ignitis_wind&quot; by pvz.lt on Vimeo, the home for high quality videos and the people who love them.")));
    }

    @Test
    void readMediaRssFeedGuid() {
        var res = new MediaRssReader().read(fromFile("mediarss/media-rss.xml"))
                .sorted(ItemComparator.oldestPublishedItemFirst())
                .collect(Collectors.toList());

        MediaRssItem item = res.get(0);
        assertThat(item.getGuid(), isPresentAnd(equalTo("tag:vimeo,2022-11-07:clip768251452")));
    }

    @Test
    void readMediaRssFeedIsPermaLink() {
        var res = new MediaRssReader().read(fromFile("mediarss/media-rss.xml"))
                .sorted(ItemComparator.oldestPublishedItemFirst())
                .collect(Collectors.toList());

        MediaRssItem item = res.get(0);
        assertThat(item.getIsPermaLink(), isPresentAnd(equalTo(false)));
    }

    @Test
    void readMediaRssFeedThumbnail() {
        var res = new MediaRssReader().read(fromFile("mediarss/media-rss.xml"))
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
        var items = new MediaRssReader().read(fromFile("mediarss/media-rss-content.xml"))
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
        assertThat(mediaContent.getBitRate(), isPresentAnd(equalTo(3317.2d)));
        assertThat(mediaContent.getFrameRate(), isPresentAnd(equalTo(25.3d)));
        assertThat(mediaContent.getSamplingRate(), isPresentAnd(equalTo(44.1d)));
        assertThat(mediaContent.getSamplingRate(), isPresentAnd(equalTo(44.1d)));
        assertThat(mediaContent.getChannels(), isPresentAnd(equalTo(2)));
        assertThat(mediaContent.getDuration(), isPresentAnd(equalTo(120)));
        assertThat(mediaContent.getDurationAsDuration(), isPresentAnd(equalTo(Duration.ofSeconds(120))));
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

        MediaDescription mediaDescription = mediaContent.getMediaDescription().orElse(null);
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
        var starRating = community.getMediaStarRating().orElse(null);
        assertNotNull(starRating);
        assertThat(starRating.getAverage(), equalTo(3.5));
        assertThat(starRating.getCount(), equalTo(20));
        assertThat(starRating.getMin(), equalTo(1));
        assertThat(starRating.getMax(), equalTo(10));
        var statistics = community.getMediaStatistics().orElse(null);
        assertNotNull(statistics);
        assertThat(statistics.getViews(), equalTo(5L));
        assertThat(statistics.getFavorites(), equalTo(4));
        assertThat(statistics.getViews(), equalTo(5L));
        var tags = community.getMediaTags().orElse(null);
        assertNotNull(tags);
        assertThat(tags.getTags(), equalTo("news: 5, abc:3"));

        assertThat(mediaContent.getMediaComments(), equalTo(List.of("comment1", "comment2")));
        assertThat(mediaContent.getMediaResponses(), equalTo(List.of("http://www.response1.com", "http://www.response2.com")));
        assertThat(mediaContent.getMediaBackLinks(), equalTo(List.of("http://www.backlink1.com", "http://www.backlink2.com")));

        var status = mediaContent.getMediaStatus().orElse(null);
        assertNotNull(status);
        assertThat(status.getReason(), isPresentAndIs("http://www.reasonforblocking.com"));
        assertThat(status.getState(), equalTo("blocked"));

        assertThat(mediaContent.getMediaPrices().get(0).getType(), equalTo("rent"));
        assertThat(mediaContent.getMediaPrices().get(0).getCurrency(), isPresentAndIs("EUR"));
        assertThat(mediaContent.getMediaPrices().get(0).getPrice(), isPresentAndIs(19.99));
        assertThat(mediaContent.getMediaPrices().get(1).getType(), equalTo("subscription"));
        assertThat(mediaContent.getMediaPrices().get(1).getInfo(), isPresentAndIs("http://www.dummy.jp/subscription_info"));
        assertThat(mediaContent.getMediaPrices().get(1).getCurrency(), isPresentAndIs("USD"));
        assertThat(mediaContent.getMediaPrices().get(1).getPrice(), isPresentAndIs(18.88));

        var embed = mediaContent.getMediaEmbed().orElse(null);
        assertNotNull(embed);
        assertThat(embed.getUrl(), equalTo("http://www.foo.com/player.swf"));
        assertThat(embed.getHeight(), equalTo(323));
        assertThat(embed.getWidth(), equalTo(512));
        assertThat(embed.getParams().size(), is(5));
        assertThat(embed.getParams(), hasEntry("type", "application/x-shockwave-flash"));
        assertThat(embed.getParams(), hasEntry("width", "512"));
        assertThat(embed.getParams(), hasEntry("height", "323"));
        assertThat(embed.getParams(), hasEntry("allowFullScreen", "true"));
        assertThat(embed.getParams(), hasEntry("flashVars", "id=12345&vid=678912i&lang=en-us&intl=us&thumbUrl=http://www.foo.com/thumbnail.jpg"));

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
        assertThat(mediaContent.getMediaScenes().get(0).getSceneStartTimeAsDuration(), equalTo(Duration.ofSeconds(15)));
        assertThat(mediaContent.getMediaScenes().get(0).getSceneEndTime(), equalTo("00:45"));
        assertThat(mediaContent.getMediaScenes().get(0).getSceneEndTimeAsDuration(), equalTo(Duration.ofSeconds(45)));
        assertThat(mediaContent.getMediaScenes().get(1).getSceneTitle(), equalTo("sceneTitle2"));
        assertThat(mediaContent.getMediaScenes().get(1).getSceneDescription(), equalTo("sceneDesc2"));
        assertThat(mediaContent.getMediaScenes().get(1).getSceneStartTime(), equalTo("00:57"));
        assertThat(mediaContent.getMediaScenes().get(1).getSceneStartTimeAsDuration(), equalTo(Duration.ofSeconds(57)));
        assertThat(mediaContent.getMediaScenes().get(1).getSceneEndTime(), equalTo("01:45"));
        assertThat(mediaContent.getMediaScenes().get(1).getSceneEndTimeAsDuration(), equalTo(Duration.ofSeconds(105)));

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
        var items = new MediaRssReader().read(fromFile("mediarss/media-rss-group.xml"))
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
        assertThat(content1.getBitRate(), isPresentAndIs(3317.2d));
        assertThat(content1.getFrameRate(), isPresentAndIs(25.3d));
        assertThat(content1.getSamplingRate(), isPresentAndIs(44.1d));
        assertThat(content1.getSamplingRate(), isPresentAndIs(44.1d));
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
        assertThat(statistics.getViews(), is(56L));
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
        assertThat(text2.getStartAsDuration(), isPresentAndIs(Util.toDuration("00:00:10.000")));
        assertThat(text2.getEnd(), isPresentAndIs("00:00:17.000"));
        assertThat(text2.getEndAsDuration(), isPresentAndIs(Util.toDuration("00:00:17.000")));

        assertThat(group.getMediaComments(), equalTo(List.of("comment1", "comment2")));
        assertThat(group.getMediaResponses(), equalTo(List.of("http://www.response1.com", "http://www.response2.com")));
        assertThat(group.getMediaBackLinks(), equalTo(List.of("http://www.backlink1.com", "http://www.backlink2.com")));

        var status = group.getMediaStatus().orElse(null);
        assertNotNull(status);
        assertThat(status.getReason(), isPresentAndIs("http://www.reasonforblocking.com"));
        assertThat(status.getState(), equalTo("blocked"));

        assertThat(group.getMediaPrices().get(0).getType(), equalTo("rent"));
        assertThat(group.getMediaPrices().get(0).getCurrency(), isPresentAndIs("EUR"));
        assertThat(group.getMediaPrices().get(0).getPrice(), isPresentAndIs(19.99));
        assertThat(group.getMediaPrices().get(1).getType(), equalTo("subscription"));
        assertThat(group.getMediaPrices().get(1).getInfo(), isPresentAndIs("http://www.dummy.jp/subscription_info"));
        assertThat(group.getMediaPrices().get(1).getCurrency(), isPresentAndIs("USD"));
        assertThat(group.getMediaPrices().get(1).getPrice(), isPresentAndIs(18.88));

        var embed = group.getMediaEmbed().orElse(null);
        assertNotNull(embed);
        assertThat(embed.getUrl(), equalTo("http://www.foo.com/player.swf"));
        assertThat(embed.getHeight(), equalTo(323));
        assertThat(embed.getWidth(), equalTo(512));
        assertThat(embed.getParams().size(), is(5));
        assertThat(embed.getParams(), hasEntry("type", "application/x-shockwave-flash"));
        assertThat(embed.getParams(), hasEntry("width", "512"));
        assertThat(embed.getParams(), hasEntry("height", "323"));
        assertThat(embed.getParams(), hasEntry("allowFullScreen", "true"));
        assertThat(embed.getParams(), hasEntry("flashVars", "id=12345&vid=678912i&lang=en-us&intl=us&thumbUrl=http://www.foo.com/thumbnail.jpg"));

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
    void mediaRssChannel() {
        var items = new MediaRssReader().read(fromFile("mediarss/media-rss-channel.xml"))
                .collect(Collectors.toList());

        assertEquals(1, items.size());
        MediaRssItem item = items.get(0);
        assertNotNull(item);
        assertThat(item.getTitle(), isPresentAndIs("Movie Title: Is this a good movie?"));
        assertThat(item.getLink(), isPresentAndIs("http://www.foo.com/item1.htm"));

        MediaRssChannelImpl channel = (MediaRssChannelImpl) item.getChannel();
        assertNotNull(channel);

        assertThat(channel.getTitle(), is("My Movie Review Site"));
        assertThat(channel.getLink(), is("http://www.foo.com"));
        assertThat(channel.getDescription(), is("I review movies."));

        // Rating
        var ratings = channel.getMediaRatings();
        assertEquals(2, ratings.size());
        assertThat(ratings.get(0).getScheme(), isPresentAnd(equalTo("urn:simple")));
        assertEquals("nonadult", ratings.get(0).getRating());
        assertThat(ratings.get(1).getScheme(), isPresentAnd(equalTo("urn:mpaa")));
        assertEquals("pg", ratings.get(1).getRating());

        // Title
        var title = channel.getMediaTitle().orElse(null);
        assertNotNull(title);
        assertThat(title.getType(), isPresentAnd(equalTo("plain")));
        assertEquals("The Judy's - The Moo Song", title.getTitle());

        // Description
        var description = channel.getMediaDescription().orElse(null);
        assertNotNull(description);
        assertThat(description.getType(), isPresentAnd(equalTo("plain")));
        assertEquals("Get perfectly done steaks every time", description.getDescription());

        // Keywords
        var keywords = channel.getMediaKeywords();
        assertEquals(2, keywords.size());
        assertEquals("Steaks", keywords.get(0));
        assertEquals("Summer", keywords.get(1));

        // Thumbnails
        var thumbnails = channel.getMediaThumbnails();
        assertEquals(2, thumbnails.size());
        assertEquals("http://www.example.com/examples/mrss/example.jpg", thumbnails.get(0).getUrl());
        assertThat(thumbnails.get(0).getWidth(), isPresentAnd(equalTo(720)));
        assertThat(thumbnails.get(0).getHeight(), isEmpty());
        assertThat(thumbnails.get(0).getTime(), isPresentAnd(equalTo("50.10")));
        assertThat(thumbnails.get(0).getTimeAsDuration(), isPresent());
        assertEquals("http://www.foo.com/keyframe.jpg", thumbnails.get(1).getUrl());
        assertThat(thumbnails.get(1).getWidth(), isPresentAnd(equalTo(75)));
        assertThat(thumbnails.get(1).getHeight(), isPresentAnd(equalTo(50)));
        assertThat(thumbnails.get(1).getTime(), isPresentAnd(equalTo("12:05:01.123")));
        assertThat(thumbnails.get(1).getTimeAsDuration(), isPresent());

        // Category
        var categories = channel.getMediaCategories();
        assertEquals(2, categories.size());
        assertEquals("music/artistname/album/song1", categories.get(0).getCategory());
        assertThat(categories.get(0).getSchema(), isPresentAnd(equalTo("http://blah.com/scheme")));
        assertThat(categories.get(0).getLabel(), isPresentAnd(equalTo("music")));
        assertEquals("music/artistname/album/song2", categories.get(1).getCategory());
        assertThat(categories.get(1).getSchema(), isEmpty());
        assertThat(categories.get(1).getLabel(), isEmpty());

        // Hash
        var hashes = channel.getMediaHashes();
        assertEquals(2, hashes.size());
        assertEquals("dfdec888b72151965a34b4b59031290a", hashes.get(0).getHash());
        assertThat(hashes.get(0).getAlgorithm(), isPresentAnd(equalTo("md5")));
        assertEquals("2fd4e1c67a2d28fced849ee1bb76e7391b93eb12", hashes.get(1).getHash());
        assertThat(hashes.get(1).getAlgorithm(), isPresentAnd(equalTo("sha-1")));

        // Player
        var player = channel.getMediaPlayer().orElse(null);
        assertNotNull(player);
        assertThat(player.getUrl(), equalTo("http://www.foo.com/player?id=1111"));
        assertThat(player.getHeight(), isPresentAnd(equalTo(200)));
        assertThat(player.getWidth(), isPresentAnd(equalTo(400)));

        // Credits
        var credits = channel.getMediaCredits();
        assertEquals(2, credits.size());
        assertThat(credits.get(0).getRole(), isPresentAnd(equalTo("producer")));
        assertThat(credits.get(0).getScheme(), isPresentAnd(equalTo("urn:ebu")));
        assertEquals("entity name", credits.get(0).getCredit());
        assertThat(credits.get(1).getRole(), isPresentAnd(equalTo("owner")));
        assertThat(credits.get(1).getScheme(), isPresentAnd(equalTo("urn:yvs")));
        assertEquals("copyright holder of the entity", credits.get(1).getCredit());

        // Copyright
        var copyright = channel.getMediaCopyright().orElse(null);
        assertNotNull(copyright);
        assertThat(copyright.getUrl(), isPresentAnd(equalTo("https://creativecommons.org/licenses/by/4.0/")));
        assertEquals("2005 FooBar Media", copyright.getCopyright());

        // Text
        var texts = channel.getMediaTexts();
        assertEquals(2, texts.size());
        assertThat(texts.get(0).getType(), isPresentAnd(equalTo("plain")));
        assertEquals("Oh, say, can you see, by the dawn's early light", texts.get(0).getText());
        assertThat(texts.get(0).getLang(), isEmpty());
        assertThat(texts.get(0).getStart(), isEmpty());
        assertThat(texts.get(0).getEnd(), isEmpty());
        assertThat(texts.get(1).getType(), isPresentAnd(equalTo("plain")));
        assertEquals("By the dawn's early light", texts.get(1).getText());
        assertThat(texts.get(1).getLang(), isPresentAnd(equalTo("en")));
        assertThat(texts.get(1).getStart(), isPresentAnd(equalTo("00:00:10.000")));
        assertThat(texts.get(1).getEnd(), isPresentAnd(equalTo("00:00:17.000")));

        // Restriction
        var restrictions = channel.getMediaRestrictions();
        assertEquals(2, restrictions.size());
        assertThat(restrictions.get(0).getType(), equalTo("sharing"));
        assertThat(restrictions.get(0).getRelationship(), equalTo("deny"));
        assertThat(restrictions.get(0).getRestriction(), is(emptyOrNullString()));
        assertThat(restrictions.get(1).getType(), equalTo("country"));
        assertThat(restrictions.get(1).getRelationship(), equalTo("allow"));
        assertThat(restrictions.get(1).getRestriction(), equalTo("au us"));

        // Community
        var community = channel.getMediaCommunity().orElse(null);
        assertNotNull(community);
        var starRating = community.getMediaStarRating().orElse(null);
        assertNotNull(starRating);
        assertThat(starRating.getAverage(), equalTo(3.5));
        assertThat(starRating.getCount(), equalTo(20));
        assertThat(starRating.getMin(), equalTo(1));
        assertThat(starRating.getMax(), equalTo(10));
        var statistics = community.getMediaStatistics().orElse(null);
        assertNotNull(statistics);
        assertThat(statistics.getViews(), equalTo(5L));
        assertThat(statistics.getFavorites(), equalTo(4));
        var tags = community.getMediaTags().orElse(null);
        assertNotNull(tags);
        assertThat(tags.getTags(), equalTo("news:5, nbc, abc:3, reuters:b"));
        var tagList = tags.getTagAsList();
        assertEquals(4, tagList.size());
        assertEquals("news:5", tagList.get(0).getTag());
        assertEquals("news", tagList.get(0).getName());
        assertThat(tagList.get(0).getWeight(), isPresentAnd(is(5)));
        assertEquals("nbc", tagList.get(1).getTag());
        assertEquals("nbc", tagList.get(1).getName());
        assertThat(tagList.get(1).getWeight(), isEmpty());
        assertEquals("abc:3", tagList.get(2).getTag());
        assertEquals("abc", tagList.get(2).getName());
        assertThat(tagList.get(2).getWeight(), isPresentAnd(is(3)));
        assertEquals("reuters:b", tagList.get(3).getTag());
        assertEquals("reuters", tagList.get(3).getName());
        assertThat(tagList.get(3).getWeight(), isEmpty());

        // Comments
        var comments = channel.getMediaComments();
        assertEquals(2, comments.size());
        assertEquals("comment1", comments.get(0));
        assertEquals("comment2", comments.get(1));

        // Embed
        var embed = channel.getMediaEmbed().orElse(null);
        assertNotNull(embed);
        assertThat(embed.getUrl(), equalTo("http://www.foo.com/player.swf"));
        assertThat(embed.getHeight(), equalTo(323));
        assertThat(embed.getWidth(), equalTo(512));
        assertThat(embed.getParams().size(), is(5));
        assertThat(embed.getParams(), hasEntry("type", "application/x-shockwave-flash"));
        assertThat(embed.getParams(), hasEntry("width", "512"));
        assertThat(embed.getParams(), hasEntry("height", "323"));
        assertThat(embed.getParams(), hasEntry("allowFullScreen", "true"));
        assertThat(embed.getParams(), hasEntry("flashVars", "id=12345&vid=678912i&lang=en-us&intl=us&thumbUrl=http://www.foo.com/thumbnail.jpg"));

        // Responses
        var responses = channel.getMediaResponses();
        assertEquals(2, responses.size());
        assertEquals("http://www.response1.com", responses.get(0));
        assertEquals("http://www.response2.com", responses.get(1));

        // BackLinks
        var backlinks = channel.getMediaBackLinks();
        assertEquals(2, backlinks.size());
        assertEquals("http://www.backlink1.com", backlinks.get(0));
        assertEquals("http://www.backlink2.com", backlinks.get(1));

        // Status
        var status = channel.getMediaStatus().orElse(null);
        assertNotNull(status);
        assertThat(status.getReason(), isPresentAnd(equalTo("http://www.reasonforblocking.com")));
        assertThat(status.getState(), equalTo("blocked"));

        // Price
        var prices = channel.getMediaPrices();
        assertEquals(2, prices.size());
        assertThat(prices.get(0).getType(), equalTo("rent"));
        assertThat(prices.get(0).getCurrency(), isPresentAnd(equalTo("EUR")));
        assertThat(prices.get(0).getCurrencyAsCurrency(), isPresentAnd(equalTo(Currency.getInstance("EUR"))));
        assertThat(prices.get(0).getPrice(), isPresentAnd(equalTo(19.99)));
        assertThat(prices.get(1).getType(), equalTo("subscription"));
        assertThat(prices.get(1).getInfo(), isPresentAnd(equalTo("http://www.dummy.jp/subscription_info")));
        assertThat(prices.get(1).getCurrency(), isPresentAnd(equalTo("USD")));
        assertThat(prices.get(1).getCurrencyAsCurrency(), isPresentAnd(equalTo(Currency.getInstance("USD"))));
        assertThat(prices.get(1).getPrice(), isPresentAnd(equalTo(18.88)));

        // License
        var licenses = channel.getMediaLicenses();
        assertEquals(2, licenses.size());
        assertThat(licenses.get(0).getLicense(), equalTo("This work is licensed under a Creative Commons License"));
        assertThat(licenses.get(0).getHref(), is(equalTo("http://creativecommons.org/licenses/by/4.0/")));
        assertThat(licenses.get(0).getType(), is(equalTo("text/html")));
        assertThat(licenses.get(1).getLicense(), equalTo("This work is licensed under a GNU General Public License"));
        assertThat(licenses.get(1).getHref(), is(equalTo("https://www.gnu.org/licenses/gpl-3.0.en.html")));
        assertThat(licenses.get(1).getType(), is(equalTo("text/html")));

        // Subtitles
        var subtitles = channel.getMediaSubTitles();
        assertEquals(2, subtitles.size());
        assertThat(subtitles.get(0).getType(), equalTo("application/smil"));
        assertThat(subtitles.get(0).getLang(), equalTo("en-us"));
        assertThat(subtitles.get(0).getHref(), equalTo("http://www.foo.org/subtitle.smil"));
        assertThat(subtitles.get(1).getType(), equalTo("application/smil"));
        assertThat(subtitles.get(1).getLang(), equalTo("fr-FR"));
        assertThat(subtitles.get(1).getHref(), equalTo("http://www.foo.org/fr/subtitle.smil"));

        // PeerLinks
        var peerLinks = channel.getMediaPeerLinks();
        assertEquals(2, peerLinks.size());
        assertThat(peerLinks.get(0).getType(), equalTo("application/x-bittorrent"));
        assertThat(peerLinks.get(0).getHref(), equalTo("http://www.foo.org/sampleFile480.torrent"));
        assertThat(peerLinks.get(1).getType(), equalTo("application/x-bittorrent"));
        assertThat(peerLinks.get(1).getHref(), equalTo("http://www.foo.org/sampleFile1080.torrent"));

        // Rights
        var rights = channel.getMediaRights().orElse(null);
        assertNotNull(rights);
        assertThat(rights.getStatus(), equalTo("userCreated"));

        // Scenes
        var scenes = channel.getMediaScenes();
        assertEquals(2, scenes.size());
        assertThat(scenes.get(0).getSceneTitle(), equalTo("sceneTitle1"));
        assertThat(scenes.get(0).getSceneDescription(), equalTo("sceneDesc1"));
        assertThat(scenes.get(0).getSceneStartTime(), equalTo("00:15"));
        assertThat(scenes.get(0).getSceneEndTime(), equalTo("00:45"));
        assertThat(scenes.get(1).getSceneTitle(), equalTo("sceneTitle2"));
        assertThat(scenes.get(1).getSceneDescription(), equalTo("sceneDesc2"));
        assertThat(scenes.get(1).getSceneStartTime(), equalTo("00:57"));
        assertThat(scenes.get(1).getSceneEndTime(), equalTo("01:45"));
    }

    @ParameterizedTest
    @MethodSource("feedReaderArguments")
    void mediaRssExample1(AbstractRssReader<MediaRssChannel, MediaRssItem> feedReader) {
        var items = feedReader.read(fromFile("mediarss/media-rss-example-1.xml"))
                .collect(Collectors.toList());

        assertEquals(1, items.size());
        MediaRssItem item = items.get(0);
        assertThat(item.getTitle(), isPresentAndIs("Movie Title: Is this a good movie?"));
        assertThat(item.getLink(), isPresentAndIs("http://www.foo.com/item1.htm"));
        assertNotNull(item);

        // Rating
        var ratings = item.getMediaRatings();
        assertEquals(2, ratings.size());
        assertThat(ratings.get(0).getScheme(), isPresentAnd(equalTo("urn:simple")));
        assertEquals("nonadult", ratings.get(0).getRating());
        assertThat(ratings.get(1).getScheme(), isPresentAnd(equalTo("urn:mpaa")));
        assertEquals("pg", ratings.get(1).getRating());

        // Title
        var title = item.getMediaTitle().orElse(null);
        assertNotNull(title);
        assertThat(title.getType(), isPresentAnd(equalTo("plain")));
        assertEquals("The Judy's - The Moo Song", title.getTitle());

        // Description
        var description = item.getMediaDescription().orElse(null);
        assertNotNull(description);
        assertThat(description.getType(), isPresentAnd(equalTo("plain")));
        assertEquals("Get perfectly done steaks every time", description.getDescription());

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
        assertThat(thumbnails.get(0).getTimeAsDuration(), isPresent());
        assertEquals("http://www.foo.com/keyframe.jpg", thumbnails.get(1).getUrl());
        assertThat(thumbnails.get(1).getWidth(), isPresentAnd(equalTo(75)));
        assertThat(thumbnails.get(1).getHeight(), isPresentAnd(equalTo(50)));
        assertThat(thumbnails.get(1).getTime(), isPresentAnd(equalTo("12:05:01.123")));
        assertThat(thumbnails.get(1).getTimeAsDuration(), isPresent());

        // Category
        var categories = item.getMediaCategories();
        assertEquals(2, categories.size());
        assertEquals("music/artistname/album/song1", categories.get(0).getCategory());
        assertThat(categories.get(0).getSchema(), isPresentAnd(equalTo("http://blah.com/scheme")));
        assertThat(categories.get(0).getLabel(), isPresentAnd(equalTo("music")));
        assertEquals("music/artistname/album/song2", categories.get(1).getCategory());
        assertThat(categories.get(1).getSchema(), isEmpty());
        assertThat(categories.get(1).getLabel(), isEmpty());

        // Hash
        var hashes = item.getMediaHashes();
        assertEquals(2, hashes.size());
        assertEquals("dfdec888b72151965a34b4b59031290a", hashes.get(0).getHash());
        assertThat(hashes.get(0).getAlgorithm(), isPresentAnd(equalTo("md5")));
        assertEquals("2fd4e1c67a2d28fced849ee1bb76e7391b93eb12", hashes.get(1).getHash());
        assertThat(hashes.get(1).getAlgorithm(), isPresentAnd(equalTo("sha-1")));

        // Player
        var player = item.getMediaPlayer().orElse(null);
        assertNotNull(player);
        assertThat(player.getUrl(), equalTo("http://www.foo.com/player?id=1111"));
        assertThat(player.getHeight(), isPresentAnd(equalTo(200)));
        assertThat(player.getWidth(), isPresentAnd(equalTo(400)));

        // Credits
        var credits = item.getMediaCredits();
        assertEquals(2, credits.size());
        assertThat(credits.get(0).getRole(), isPresentAnd(equalTo("producer")));
        assertThat(credits.get(0).getScheme(), isPresentAnd(equalTo("urn:ebu")));
        assertEquals("entity name", credits.get(0).getCredit());
        assertThat(credits.get(1).getRole(), isPresentAnd(equalTo("owner")));
        assertThat(credits.get(1).getScheme(), isPresentAnd(equalTo("urn:yvs")));
        assertEquals("copyright holder of the entity", credits.get(1).getCredit());

        // Copyright
        var copyright = item.getMediaCopyright().orElse(null);
        assertNotNull(copyright);
        assertThat(copyright.getUrl(), isPresentAnd(equalTo("https://creativecommons.org/licenses/by/4.0/")));
        assertEquals("2005 FooBar Media", copyright.getCopyright());

        // Text
        var texts = item.getMediaTexts();
        assertEquals(2, texts.size());
        assertThat(texts.get(0).getType(), isPresentAnd(equalTo("plain")));
        assertEquals("Oh, say, can you see, by the dawn's early light", texts.get(0).getText());
        assertThat(texts.get(0).getLang(), isEmpty());
        assertThat(texts.get(0).getStart(), isEmpty());
        assertThat(texts.get(0).getEnd(), isEmpty());
        assertThat(texts.get(1).getType(), isPresentAnd(equalTo("plain")));
        assertEquals("By the dawn's early light", texts.get(1).getText());
        assertThat(texts.get(1).getLang(), isPresentAnd(equalTo("en")));
        assertThat(texts.get(1).getStart(), isPresentAnd(equalTo("00:00:10.000")));
        assertThat(texts.get(1).getEnd(), isPresentAnd(equalTo("00:00:17.000")));

        // Restriction
        var restrictions = item.getMediaRestrictions();
        assertEquals(2, restrictions.size());
        assertThat(restrictions.get(0).getType(), equalTo("sharing"));
        assertThat(restrictions.get(0).getRelationship(), equalTo("deny"));
        assertThat(restrictions.get(0).getRestriction(), is(emptyOrNullString()));
        assertThat(restrictions.get(1).getType(), equalTo("country"));
        assertThat(restrictions.get(1).getRelationship(), equalTo("allow"));
        assertThat(restrictions.get(1).getRestriction(), equalTo("au us"));

        // Community
        var community = item.getMediaCommunity().orElse(null);
        assertNotNull(community);
        var starRating = community.getMediaStarRating().orElse(null);
        assertNotNull(starRating);
        assertThat(starRating.getAverage(), equalTo(3.5));
        assertThat(starRating.getCount(), equalTo(20));
        assertThat(starRating.getMin(), equalTo(1));
        assertThat(starRating.getMax(), equalTo(10));
        var statistics = community.getMediaStatistics().orElse(null);
        assertNotNull(statistics);
        assertThat(statistics.getViews(), equalTo(5L));
        assertThat(statistics.getFavorites(), equalTo(4));
        var tags = community.getMediaTags().orElse(null);
        assertNotNull(tags);
        assertThat(tags.getTags(), equalTo("news:5, nbc, abc:3, reuters:b"));
        var tagList = tags.getTagAsList();
        assertEquals(4, tagList.size());
        assertEquals("news:5", tagList.get(0).getTag());
        assertEquals("news", tagList.get(0).getName());
        assertThat(tagList.get(0).getWeight(), isPresentAnd(is(5)));
        assertEquals("nbc", tagList.get(1).getTag());
        assertEquals("nbc", tagList.get(1).getName());
        assertThat(tagList.get(1).getWeight(), isEmpty());
        assertEquals("abc:3", tagList.get(2).getTag());
        assertEquals("abc", tagList.get(2).getName());
        assertThat(tagList.get(2).getWeight(), isPresentAnd(is(3)));
        assertEquals("reuters:b", tagList.get(3).getTag());
        assertEquals("reuters", tagList.get(3).getName());
        assertThat(tagList.get(3).getWeight(), isEmpty());

        // Comments
        var comments = item.getMediaComments();
        assertEquals(2, comments.size());
        assertEquals("comment1", comments.get(0));
        assertEquals("comment2", comments.get(1));

        // Embed
        var embed = item.getMediaEmbed().orElse(null);
        assertNotNull(embed);
        assertThat(embed.getUrl(), equalTo("http://www.foo.com/player.swf"));
        assertThat(embed.getHeight(), equalTo(323));
        assertThat(embed.getWidth(), equalTo(512));
        assertThat(embed.getParams().size(), is(5));
        assertThat(embed.getParams(), hasEntry("type", "application/x-shockwave-flash"));
        assertThat(embed.getParams(), hasEntry("width", "512"));
        assertThat(embed.getParams(), hasEntry("height", "323"));
        assertThat(embed.getParams(), hasEntry("allowFullScreen", "true"));
        assertThat(embed.getParams(), hasEntry("flashVars", "id=12345&vid=678912i&lang=en-us&intl=us&thumbUrl=http://www.foo.com/thumbnail.jpg"));

        // Responses
        var responses = item.getMediaResponses();
        assertEquals(2, responses.size());
        assertEquals("http://www.response1.com", responses.get(0));
        assertEquals("http://www.response2.com", responses.get(1));

        // BackLinks
        var backlinks = item.getMediaBackLinks();
        assertEquals(2, backlinks.size());
        assertEquals("http://www.backlink1.com", backlinks.get(0));
        assertEquals("http://www.backlink2.com", backlinks.get(1));

        // Status
        var status = item.getMediaStatus().orElse(null);
        assertNotNull(status);
        assertThat(status.getReason(), isPresentAnd(equalTo("http://www.reasonforblocking.com")));
        assertThat(status.getState(), equalTo("blocked"));

        // Price
        var prices = item.getMediaPrices();
        assertEquals(2, prices.size());
        assertThat(prices.get(0).getType(), equalTo("rent"));
        assertThat(prices.get(0).getCurrency(), isPresentAnd(equalTo("EUR")));
        assertThat(prices.get(0).getPrice(), isPresentAnd(equalTo(19.99)));
        assertThat(prices.get(1).getType(), equalTo("subscription"));
        assertThat(prices.get(1).getInfo(), isPresentAnd(equalTo("http://www.dummy.jp/subscription_info")));
        assertThat(prices.get(1).getCurrency(), isPresentAnd(equalTo("USD")));
        assertThat(prices.get(1).getPrice(), isPresentAnd(equalTo(18.88)));

        // License
        var licenses = item.getMediaLicenses();
        assertEquals(2, licenses.size());
        assertThat(licenses.get(0).getLicense(), equalTo("This work is licensed under a Creative Commons License"));
        assertThat(licenses.get(0).getHref(), is(equalTo("http://creativecommons.org/licenses/by/4.0/")));
        assertThat(licenses.get(0).getType(), is(equalTo("text/html")));
        assertThat(licenses.get(1).getLicense(), equalTo("This work is licensed under a GNU General Public License"));
        assertThat(licenses.get(1).getHref(), is(equalTo("https://www.gnu.org/licenses/gpl-3.0.en.html")));
        assertThat(licenses.get(1).getType(), is(equalTo("text/html")));

        // Subtitles
        var subtitles = item.getMediaSubTitles();
        assertEquals(2, subtitles.size());
        assertThat(subtitles.get(0).getType(), equalTo("application/smil"));
        assertThat(subtitles.get(0).getLang(), equalTo("en-us"));
        assertThat(subtitles.get(0).getHref(), equalTo("http://www.foo.org/subtitle.smil"));
        assertThat(subtitles.get(1).getType(), equalTo("application/smil"));
        assertThat(subtitles.get(1).getLang(), equalTo("fr-FR"));
        assertThat(subtitles.get(1).getHref(), equalTo("http://www.foo.org/fr/subtitle.smil"));

        // PeerLinks
        var peerLinks = item.getMediaPeerLinks();
        assertEquals(2, peerLinks.size());
        assertThat(peerLinks.get(0).getType(), equalTo("application/x-bittorrent"));
        assertThat(peerLinks.get(0).getHref(), equalTo("http://www.foo.org/sampleFile480.torrent"));
        assertThat(peerLinks.get(1).getType(), equalTo("application/x-bittorrent"));
        assertThat(peerLinks.get(1).getHref(), equalTo("http://www.foo.org/sampleFile1080.torrent"));

        // Rights
        var rights = item.getMediaRights().orElse(null);
        assertNotNull(rights);
        assertThat(rights.getStatus(), equalTo("userCreated"));

        // Scenes
        var scenes = item.getMediaScenes();
        assertEquals(2, scenes.size());
        assertThat(scenes.get(0).getSceneTitle(), equalTo("sceneTitle1"));
        assertThat(scenes.get(0).getSceneDescription(), equalTo("sceneDesc1"));
        assertThat(scenes.get(0).getSceneStartTime(), equalTo("00:15"));
        assertThat(scenes.get(0).getSceneEndTime(), equalTo("00:45"));
        assertThat(scenes.get(1).getSceneTitle(), equalTo("sceneTitle2"));
        assertThat(scenes.get(1).getSceneDescription(), equalTo("sceneDesc2"));
        assertThat(scenes.get(1).getSceneStartTime(), equalTo("00:57"));
        assertThat(scenes.get(1).getSceneEndTime(), equalTo("01:45"));
    }

    @Test
    void mediaRssExample2() {
        var items = new MediaRssReader().read(fromFile("mediarss/media-rss-example-2.xml"))
                .collect(Collectors.toList());

        assertEquals(1, items.size());
        MediaRssItem item = items.get(0);
        assertNotNull(item);
        var content = item.getMediaContents();
        assertEquals(3, content.size());

        // -- First media content

        // Rating
        var ratings = content.get(0).getMediaRatings();
        assertEquals(2, ratings.size());
        assertThat(ratings.get(0).getScheme(), isPresentAnd(equalTo("urn:simple")));
        assertEquals("nonadult", ratings.get(0).getRating());
        assertThat(ratings.get(1).getScheme(), isPresentAnd(equalTo("urn:mpaa")));
        assertEquals("pg", ratings.get(1).getRating());

        // Title
        var title = content.get(0).getMediaTitle().orElse(null);
        assertNotNull(title);
        assertThat(title.getType(), isPresentAnd(equalTo("plain")));
        assertEquals("The Judy's - The Moo Song", title.getTitle());

        // Description
        var description = content.get(0).getMediaDescription().orElse(null);
        assertNotNull(description);
        assertThat(description.getType(), isPresentAnd(equalTo("plain")));
        assertEquals("Get perfectly done steaks every time", description.getDescription());

        // Keywords
        var keywords = content.get(0).getMediaKeywords();
        assertEquals(2, keywords.size());
        assertEquals("Steaks", keywords.get(0));
        assertEquals("Summer", keywords.get(1));

        // Thumbnails
        var thumbnails = content.get(0).getMediaThumbnails();
        assertEquals(2, thumbnails.size());
        assertEquals("http://www.example.com/examples/mrss/example.jpg", thumbnails.get(0).getUrl());
        assertThat(thumbnails.get(0).getWidth(), isPresentAnd(equalTo(720)));
        assertThat(thumbnails.get(0).getHeight(), isEmpty());
        assertThat(thumbnails.get(0).getTime(), isPresentAnd(equalTo("50.10")));
        assertThat(thumbnails.get(0).getTimeAsDuration(), isPresent());
        assertEquals("http://www.foo.com/keyframe.jpg", thumbnails.get(1).getUrl());
        assertThat(thumbnails.get(1).getWidth(), isPresentAnd(equalTo(75)));
        assertThat(thumbnails.get(1).getHeight(), isPresentAnd(equalTo(50)));
        assertThat(thumbnails.get(1).getTime(), isPresentAnd(equalTo("12:05:01.123")));
        assertThat(thumbnails.get(1).getTimeAsDuration(), isPresent());

        // Category
        var categories = content.get(0).getMediaCategories();
        assertEquals(2, categories.size());
        assertEquals("music/artistname/album/song1", categories.get(0).getCategory());
        assertThat(categories.get(0).getSchema(), isPresentAnd(equalTo("http://blah.com/scheme")));
        assertThat(categories.get(0).getLabel(), isPresentAnd(equalTo("music")));
        assertEquals("music/artistname/album/song2", categories.get(1).getCategory());
        assertThat(categories.get(1).getSchema(), isEmpty());
        assertThat(categories.get(1).getLabel(), isEmpty());

        // Hash
        var hashes = content.get(0).getMediaHashes();
        assertEquals(2, hashes.size());
        assertEquals("dfdec888b72151965a34b4b59031290a", hashes.get(0).getHash());
        assertThat(hashes.get(0).getAlgorithm(), isPresentAnd(equalTo("md5")));
        assertEquals("2fd4e1c67a2d28fced849ee1bb76e7391b93eb12", hashes.get(1).getHash());
        assertThat(hashes.get(1).getAlgorithm(), isPresentAnd(equalTo("sha-1")));

        // Player
        var player = content.get(0).getMediaPlayer().orElse(null);
        assertNotNull(player);
        assertThat(player.getUrl(), equalTo("http://www.foo.com/player?id=1111"));
        assertThat(player.getHeight(), isPresentAnd(equalTo(200)));
        assertThat(player.getWidth(), isPresentAnd(equalTo(400)));

        // Credits
        var credits = content.get(0).getMediaCredits();
        assertEquals(2, credits.size());
        assertThat(credits.get(0).getRole(), isPresentAnd(equalTo("producer")));
        assertThat(credits.get(0).getScheme(), isPresentAnd(equalTo("urn:ebu")));
        assertEquals("entity name", credits.get(0).getCredit());
        assertThat(credits.get(1).getRole(), isPresentAnd(equalTo("owner")));
        assertThat(credits.get(1).getScheme(), isPresentAnd(equalTo("urn:yvs")));
        assertEquals("copyright holder of the entity", credits.get(1).getCredit());

        // Copyright
        var copyright = content.get(0).getMediaCopyright().orElse(null);
        assertNotNull(copyright);
        assertThat(copyright.getUrl(), isPresentAnd(equalTo("https://creativecommons.org/licenses/by/4.0/")));
        assertEquals("2005 FooBar Media", copyright.getCopyright());

        // Text
        var texts = content.get(0).getMediaTexts();
        assertEquals(2, texts.size());
        assertThat(texts.get(0).getType(), isPresentAnd(equalTo("plain")));
        assertEquals("Oh, say, can you see, by the dawn's early light", texts.get(0).getText());
        assertThat(texts.get(0).getLang(), isEmpty());
        assertThat(texts.get(0).getStart(), isEmpty());
        assertThat(texts.get(0).getEnd(), isEmpty());
        assertThat(texts.get(1).getType(), isPresentAnd(equalTo("plain")));
        assertEquals("By the dawn's early light", texts.get(1).getText());
        assertThat(texts.get(1).getLang(), isPresentAnd(equalTo("en")));
        assertThat(texts.get(1).getStart(), isPresentAnd(equalTo("00:00:10.000")));
        assertThat(texts.get(1).getEnd(), isPresentAnd(equalTo("00:00:17.000")));

        // Restriction
        var restrictions = content.get(0).getMediaRestrictions();
        assertEquals(2, restrictions.size());
        assertThat(restrictions.get(0).getType(), equalTo("sharing"));
        assertThat(restrictions.get(0).getRelationship(), equalTo("deny"));
        assertThat(restrictions.get(0).getRestriction(), is(emptyOrNullString()));
        assertThat(restrictions.get(1).getType(), equalTo("country"));
        assertThat(restrictions.get(1).getRelationship(), equalTo("allow"));
        assertThat(restrictions.get(1).getRestriction(), equalTo("au us"));

        // Community
        var community = content.get(0).getMediaCommunity().orElse(null);
        assertNotNull(community);
        var starRating = community.getMediaStarRating().orElse(null);
        assertNotNull(starRating);
        assertThat(starRating.getAverage(), equalTo(3.5));
        assertThat(starRating.getCount(), equalTo(20));
        assertThat(starRating.getMin(), equalTo(1));
        assertThat(starRating.getMax(), equalTo(10));
        var statistics = community.getMediaStatistics().orElse(null);
        assertNotNull(statistics);
        assertThat(statistics.getViews(), equalTo(5L));
        assertThat(statistics.getFavorites(), equalTo(4));
        var tags = community.getMediaTags().orElse(null);
        assertNotNull(tags);
        assertThat(tags.getTags(), equalTo("news:5, nbc, abc:3, reuters:b"));
        var tagList = tags.getTagAsList();
        assertEquals(4, tagList.size());
        assertEquals("news:5", tagList.get(0).getTag());
        assertEquals("news", tagList.get(0).getName());
        assertThat(tagList.get(0).getWeight(), isPresentAnd(is(5)));
        assertEquals("nbc", tagList.get(1).getTag());
        assertEquals("nbc", tagList.get(1).getName());
        assertThat(tagList.get(1).getWeight(), isEmpty());
        assertEquals("abc:3", tagList.get(2).getTag());
        assertEquals("abc", tagList.get(2).getName());
        assertThat(tagList.get(2).getWeight(), isPresentAnd(is(3)));
        assertEquals("reuters:b", tagList.get(3).getTag());
        assertEquals("reuters", tagList.get(3).getName());
        assertThat(tagList.get(3).getWeight(), isEmpty());

        // Comments
        var comments = content.get(0).getMediaComments();
        assertEquals(2, comments.size());
        assertEquals("comment1", comments.get(0));
        assertEquals("comment2", comments.get(1));

        // Embed
        var embed = content.get(0).getMediaEmbed().orElse(null);
        assertNotNull(embed);
        assertThat(embed.getUrl(), equalTo("http://www.foo.com/player.swf"));
        assertThat(embed.getHeight(), equalTo(323));
        assertThat(embed.getWidth(), equalTo(512));
        assertThat(embed.getParams().size(), is(5));
        assertThat(embed.getParams(), hasEntry("type", "application/x-shockwave-flash"));
        assertThat(embed.getParams(), hasEntry("width", "512"));
        assertThat(embed.getParams(), hasEntry("height", "323"));
        assertThat(embed.getParams(), hasEntry("allowFullScreen", "true"));
        assertThat(embed.getParams(), hasEntry("flashVars", "id=12345&vid=678912i&lang=en-us&intl=us&thumbUrl=http://www.foo.com/thumbnail.jpg"));

        // Responses
        var responses = content.get(0).getMediaResponses();
        assertEquals(2, responses.size());
        assertEquals("http://www.response1.com", responses.get(0));
        assertEquals("http://www.response2.com", responses.get(1));

        // BackLinks
        var backlinks = content.get(0).getMediaBackLinks();
        assertEquals(2, backlinks.size());
        assertEquals("http://www.backlink1.com", backlinks.get(0));
        assertEquals("http://www.backlink2.com", backlinks.get(1));

        // Status
        var status = content.get(0).getMediaStatus().orElse(null);
        assertNotNull(status);
        assertThat(status.getReason(), isPresentAnd(equalTo("http://www.reasonforblocking.com")));
        assertThat(status.getState(), equalTo("blocked"));

        // Price
        var prices = content.get(0).getMediaPrices();
        assertEquals(2, prices.size());
        assertThat(prices.get(0).getType(), equalTo("rent"));
        assertThat(prices.get(0).getCurrency(), isPresentAnd(equalTo("EUR")));
        assertThat(prices.get(0).getPrice(), isPresentAnd(equalTo(19.99)));
        assertThat(prices.get(1).getType(), equalTo("subscription"));
        assertThat(prices.get(1).getInfo(), isPresentAnd(equalTo("http://www.dummy.jp/subscription_info")));
        assertThat(prices.get(1).getCurrency(), isPresentAnd(equalTo("USD")));
        assertThat(prices.get(1).getPrice(), isPresentAnd(equalTo(18.88)));

        // License
        var licenses = content.get(0).getMediaLicenses();
        assertEquals(2, licenses.size());
        assertThat(licenses.get(0).getLicense(), equalTo("This work is licensed under a Creative Commons License"));
        assertThat(licenses.get(0).getHref(), is(equalTo("http://creativecommons.org/licenses/by/4.0/")));
        assertThat(licenses.get(0).getType(), is(equalTo("text/html")));
        assertThat(licenses.get(1).getLicense(), equalTo("This work is licensed under a GNU General Public License"));
        assertThat(licenses.get(1).getHref(), is(equalTo("https://www.gnu.org/licenses/gpl-3.0.en.html")));
        assertThat(licenses.get(1).getType(), is(equalTo("text/html")));

        // Subtitles
        var subtitles = content.get(0).getMediaSubTitles();
        assertEquals(2, subtitles.size());
        assertThat(subtitles.get(0).getType(), equalTo("application/smil"));
        assertThat(subtitles.get(0).getLang(), equalTo("en-us"));
        assertThat(subtitles.get(0).getHref(), equalTo("http://www.foo.org/subtitle.smil"));
        assertThat(subtitles.get(1).getType(), equalTo("application/smil"));
        assertThat(subtitles.get(1).getLang(), equalTo("fr-FR"));
        assertThat(subtitles.get(1).getHref(), equalTo("http://www.foo.org/fr/subtitle.smil"));

        // PeerLinks
        var peerLinks = content.get(0).getMediaPeerLinks();
        assertEquals(2, peerLinks.size());
        assertThat(peerLinks.get(0).getType(), equalTo("application/x-bittorrent"));
        assertThat(peerLinks.get(0).getHref(), equalTo("http://www.foo.org/sampleFile480.torrent"));
        assertThat(peerLinks.get(1).getType(), equalTo("application/x-bittorrent"));
        assertThat(peerLinks.get(1).getHref(), equalTo("http://www.foo.org/sampleFile1080.torrent"));

        // Rights
        var rights = content.get(0).getMediaRights().orElse(null);
        assertNotNull(rights);
        assertThat(rights.getStatus(), equalTo("official"));

        // Scenes
        var scenes = content.get(0).getMediaScenes();
        assertEquals(2, scenes.size());
        assertThat(scenes.get(0).getSceneTitle(), equalTo("sceneTitle1"));
        assertThat(scenes.get(0).getSceneDescription(), equalTo("sceneDesc1"));
        assertThat(scenes.get(0).getSceneStartTime(), equalTo("00:15"));
        assertThat(scenes.get(0).getSceneEndTime(), equalTo("00:45"));
        assertThat(scenes.get(1).getSceneTitle(), equalTo("sceneTitle2"));
        assertThat(scenes.get(1).getSceneDescription(), equalTo("sceneDesc2"));
        assertThat(scenes.get(1).getSceneStartTime(), equalTo("00:57"));
        assertThat(scenes.get(1).getSceneEndTime(), equalTo("01:45"));

        // -- Second media content

        // Rating
        credits = content.get(1).getMediaCredits();
        assertEquals(1, credits.size());
        assertThat(credits.get(0).getCredit(), equalTo("member of band1"));
        assertThat(credits.get(0).getRole(), isPresentAnd(equalTo("musician")));
        assertThat(credits.get(0).getScheme(), isEmpty());

        // Category
        categories = content.get(1).getMediaCategories();
        assertEquals(1, categories.size());
        assertEquals("music/band1/album/song", categories.get(0).getCategory());
        assertThat(categories.get(0).getSchema(), isEmpty());
        assertThat(categories.get(0).getLabel(), isEmpty());

        // Rating
        ratings = content.get(1).getMediaRatings();
        assertEquals(1, ratings.size());
        assertEquals("nonadult", ratings.get(0).getRating());
        assertThat(ratings.get(0).getScheme(), isEmpty());

        // -- Third media content

        // Rating
        credits = content.get(2).getMediaCredits();
        assertEquals(1, credits.size());
        assertThat(credits.get(0).getCredit(), equalTo("member of band2"));
        assertThat(credits.get(0).getRole(), isPresentAnd(equalTo("musician")));
        assertThat(credits.get(0).getScheme(), isEmpty());

        // Category
        categories = content.get(1).getMediaCategories();
        assertEquals(1, categories.size());
        assertEquals("music/band1/album/song", categories.get(0).getCategory());
        assertThat(categories.get(0).getSchema(), isEmpty());
        assertThat(categories.get(0).getLabel(), isEmpty());

        // Rating
        ratings = content.get(1).getMediaRatings();
        assertEquals(1, ratings.size());
        assertEquals("nonadult", ratings.get(0).getRating());
        assertThat(ratings.get(0).getScheme(), isEmpty());
    }

    @Test
    void mediaRssExample3() {
        var items = new MediaRssReader().read(fromFile("mediarss/media-rss-example-3.xml"))
                .collect(Collectors.toList());

        assertEquals(1, items.size());
        MediaRssItem item = items.get(0);
        assertNotNull(item);

        var contents = item.getMediaContents();
        assertEquals(0, contents.size());


        var group = item.getMediaGroup().orElse(null);
        assertNotNull(group);

        // Group-level elements
        var groupContent = group.getMediaContents();
        assertEquals(2, groupContent.size());

        // First group content
        var content = groupContent.get(0);
        assertThat(content.getUrl(), isPresentAnd(equalTo("http://www.foo.com/song64kbps.mp3")));
        assertThat(content.getFileSize(), isPresentAnd(equalTo(1000L)));
        assertThat(content.getBitRate(), isPresentAnd(equalTo(64.0)));
        assertThat(content.getType(), isPresentAnd(equalTo("audio/mpeg")));
        assertThat(content.isDefault(), isPresentAnd(equalTo(true)));
        assertThat(content.getExpression(), isPresentAnd(equalTo("full")));

        // Second group content
        content = groupContent.get(1);
        assertThat(content.getUrl(), isPresentAnd(equalTo("http://www.foo.com/song128kbps.mp3")));
        assertThat(content.getFileSize(), isPresentAnd(equalTo(2000L)));
        assertThat(content.getBitRate(), isPresentAnd(equalTo(128.0)));
        assertThat(content.getType(), isPresentAnd(equalTo("audio/mpeg")));
        assertThat(content.isDefault(), isEmpty());
        assertThat(content.getExpression(), isPresentAnd(equalTo("full")));

        // Rating
        var ratings = group.getMediaRatings();
        assertEquals(2, ratings.size());
        assertThat(ratings.get(0).getScheme(), isPresentAnd(equalTo("urn:simple")));
        assertEquals("nonadult", ratings.get(0).getRating());
        assertThat(ratings.get(1).getScheme(), isPresentAnd(equalTo("urn:mpaa")));
        assertEquals("pg", ratings.get(1).getRating());

        // Title
        var title = group.getMediaTitle().orElse(null);
        assertNotNull(title);
        assertThat(title.getType(), isPresentAnd(equalTo("plain")));
        assertEquals("The Judy's - The Moo Song", title.getTitle());

        // Description
        var description = group.getMediaDescription().orElse(null);
        assertNotNull(description);
        assertThat(description.getType(), isPresentAnd(equalTo("plain")));
        assertEquals("Get perfectly done steaks every time", description.getDescription());

        // Keywords
        var keywords = group.getMediaKeywords();
        assertEquals(2, keywords.size());
        assertEquals("Steaks", keywords.get(0));
        assertEquals("Summer", keywords.get(1));

        // Thumbnails
        var thumbnails = group.getMediaThumbnails();
        assertEquals(2, thumbnails.size());
        assertEquals("http://www.example.com/examples/mrss/example.jpg", thumbnails.get(0).getUrl());
        assertThat(thumbnails.get(0).getWidth(), isPresentAnd(equalTo(720)));
        assertThat(thumbnails.get(0).getHeight(), isEmpty());
        assertThat(thumbnails.get(0).getTime(), isPresentAnd(equalTo("50.10")));
        assertThat(thumbnails.get(0).getTimeAsDuration(), isPresent());
        assertEquals("http://www.foo.com/keyframe.jpg", thumbnails.get(1).getUrl());
        assertThat(thumbnails.get(1).getWidth(), isPresentAnd(equalTo(75)));
        assertThat(thumbnails.get(1).getHeight(), isPresentAnd(equalTo(50)));
        assertThat(thumbnails.get(1).getTime(), isPresentAnd(equalTo("12:05:01.123")));
        assertThat(thumbnails.get(1).getTimeAsDuration(), isPresent());

        // Category
        var categories = group.getMediaCategories();
        assertEquals(2, categories.size());
        assertEquals("music/artistname/album/song1", categories.get(0).getCategory());
        assertThat(categories.get(0).getSchema(), isPresentAnd(equalTo("http://blah.com/scheme")));
        assertThat(categories.get(0).getLabel(), isPresentAnd(equalTo("music")));
        assertEquals("music/artistname/album/song2", categories.get(1).getCategory());
        assertThat(categories.get(1).getSchema(), isEmpty());
        assertThat(categories.get(1).getLabel(), isEmpty());

        // Hash
        var hashes = group.getMediaHashes();
        assertEquals(2, hashes.size());
        assertEquals("dfdec888b72151965a34b4b59031290a", hashes.get(0).getHash());
        assertThat(hashes.get(0).getAlgorithm(), isPresentAnd(equalTo("md5")));
        assertEquals("2fd4e1c67a2d28fced849ee1bb76e7391b93eb12", hashes.get(1).getHash());
        assertThat(hashes.get(1).getAlgorithm(), isPresentAnd(equalTo("sha-1")));

        // Player
        var player = group.getMediaPlayer().orElse(null);
        assertNotNull(player);
        assertThat(player.getUrl(), equalTo("http://www.foo.com/player?id=1111"));
        assertThat(player.getHeight(), isPresentAnd(equalTo(200)));
        assertThat(player.getWidth(), isPresentAnd(equalTo(400)));

        // Credits
        var credits = group.getMediaCredits();
        assertEquals(2, credits.size());
        assertThat(credits.get(0).getRole(), isPresentAnd(equalTo("producer")));
        assertThat(credits.get(0).getScheme(), isPresentAnd(equalTo("urn:ebu")));
        assertEquals("entity name", credits.get(0).getCredit());
        assertThat(credits.get(1).getRole(), isPresentAnd(equalTo("owner")));
        assertThat(credits.get(1).getScheme(), isPresentAnd(equalTo("urn:yvs")));
        assertEquals("copyright holder of the entity", credits.get(1).getCredit());

        // Copyright
        var copyright = group.getMediaCopyright().orElse(null);
        assertNotNull(copyright);
        assertThat(copyright.getUrl(), isPresentAnd(equalTo("https://creativecommons.org/licenses/by/4.0/")));
        assertEquals("2005 FooBar Media", copyright.getCopyright());

        // Text
        var texts = group.getMediaTexts();
        assertEquals(2, texts.size());
        assertThat(texts.get(0).getType(), isPresentAnd(equalTo("plain")));
        assertEquals("Oh, say, can you see, by the dawn's early light", texts.get(0).getText());
        assertThat(texts.get(0).getLang(), isEmpty());
        assertThat(texts.get(0).getStart(), isEmpty());
        assertThat(texts.get(0).getEnd(), isEmpty());
        assertThat(texts.get(1).getType(), isPresentAnd(equalTo("plain")));
        assertEquals("By the dawn's early light", texts.get(1).getText());
        assertThat(texts.get(1).getLang(), isPresentAnd(equalTo("en")));
        assertThat(texts.get(1).getStart(), isPresentAnd(equalTo("00:00:10.000")));
        assertThat(texts.get(1).getEnd(), isPresentAnd(equalTo("00:00:17.000")));

        // Restriction
        var restrictions = group.getMediaRestrictions();
        assertEquals(2, restrictions.size());
        assertThat(restrictions.get(0).getType(), equalTo("sharing"));
        assertThat(restrictions.get(0).getRelationship(), equalTo("deny"));
        assertThat(restrictions.get(0).getRestriction(), is(emptyOrNullString()));
        assertThat(restrictions.get(1).getType(), equalTo("country"));
        assertThat(restrictions.get(1).getRelationship(), equalTo("allow"));
        assertThat(restrictions.get(1).getRestriction(), equalTo("au us"));

        // Community
        var community = group.getMediaCommunity().orElse(null);
        assertNotNull(community);
        var starRating = community.getMediaStarRating().orElse(null);
        assertNotNull(starRating);
        assertThat(starRating.getAverage(), equalTo(3.5));
        assertThat(starRating.getCount(), equalTo(20));
        assertThat(starRating.getMin(), equalTo(1));
        assertThat(starRating.getMax(), equalTo(10));
        var statistics = community.getMediaStatistics().orElse(null);
        assertNotNull(statistics);
        assertThat(statistics.getViews(), equalTo(5L));
        assertThat(statistics.getFavorites(), equalTo(4));
        var tags = community.getMediaTags().orElse(null);
        assertNotNull(tags);
        assertThat(tags.getTags(), equalTo("news:5, nbc, abc:3, reuters:b"));
        var tagList = tags.getTagAsList();
        assertEquals(4, tagList.size());
        assertEquals("news:5", tagList.get(0).getTag());
        assertEquals("news", tagList.get(0).getName());
        assertThat(tagList.get(0).getWeight(), isPresentAnd(is(5)));
        assertEquals("nbc", tagList.get(1).getTag());
        assertEquals("nbc", tagList.get(1).getName());
        assertThat(tagList.get(1).getWeight(), isEmpty());
        assertEquals("abc:3", tagList.get(2).getTag());
        assertEquals("abc", tagList.get(2).getName());
        assertThat(tagList.get(2).getWeight(), isPresentAnd(is(3)));
        assertEquals("reuters:b", tagList.get(3).getTag());
        assertEquals("reuters", tagList.get(3).getName());
        assertThat(tagList.get(3).getWeight(), isEmpty());

        // Comments
        var comments = group.getMediaComments();
        assertEquals(2, comments.size());
        assertEquals("comment1", comments.get(0));
        assertEquals("comment2", comments.get(1));

        // Embed
        var embed = group.getMediaEmbed().orElse(null);
        assertNotNull(embed);
        assertThat(embed.getUrl(), equalTo("http://www.foo.com/player.swf"));
        assertThat(embed.getHeight(), equalTo(323));
        assertThat(embed.getWidth(), equalTo(512));
        assertThat(embed.getParams().size(), is(5));
        assertThat(embed.getParams(), hasEntry("type", "application/x-shockwave-flash"));
        assertThat(embed.getParams(), hasEntry("width", "512"));
        assertThat(embed.getParams(), hasEntry("height", "323"));
        assertThat(embed.getParams(), hasEntry("allowFullScreen", "true"));
        assertThat(embed.getParams(), hasEntry("flashVars", "id=12345&vid=678912i&lang=en-us&intl=us&thumbUrl=http://www.foo.com/thumbnail.jpg"));

        // Responses
        var responses = group.getMediaResponses();
        assertEquals(2, responses.size());
        assertEquals("http://www.response1.com", responses.get(0));
        assertEquals("http://www.response2.com", responses.get(1));

        // BackLinks
        var backlinks = group.getMediaBackLinks();
        assertEquals(2, backlinks.size());
        assertEquals("http://www.backlink1.com", backlinks.get(0));
        assertEquals("http://www.backlink2.com", backlinks.get(1));

        // Status
        var status = group.getMediaStatus().orElse(null);
        assertNotNull(status);
        assertThat(status.getReason(), isPresentAnd(equalTo("http://www.reasonforblocking.com")));
        assertThat(status.getState(), equalTo("blocked"));

        // Price
        var prices = group.getMediaPrices();
        assertEquals(2, prices.size());
        assertThat(prices.get(0).getType(), equalTo("rent"));
        assertThat(prices.get(0).getCurrency(), isPresentAnd(equalTo("EUR")));
        assertThat(prices.get(0).getPrice(), isPresentAnd(equalTo(19.99)));
        assertThat(prices.get(1).getType(), equalTo("subscription"));
        assertThat(prices.get(1).getInfo(), isPresentAnd(equalTo("http://www.dummy.jp/subscription_info")));
        assertThat(prices.get(1).getCurrency(), isPresentAnd(equalTo("USD")));
        assertThat(prices.get(1).getPrice(), isPresentAnd(equalTo(18.88)));

        // License
        var licenses = group.getMediaLicenses();
        assertEquals(2, licenses.size());
        assertThat(licenses.get(0).getLicense(), equalTo("This work is licensed under a Creative Commons License"));
        assertThat(licenses.get(0).getHref(), is(equalTo("http://creativecommons.org/licenses/by/4.0/")));
        assertThat(licenses.get(0).getType(), is(equalTo("text/html")));
        assertThat(licenses.get(1).getLicense(), equalTo("This work is licensed under a GNU General Public License"));
        assertThat(licenses.get(1).getHref(), is(equalTo("https://www.gnu.org/licenses/gpl-3.0.en.html")));
        assertThat(licenses.get(1).getType(), is(equalTo("text/html")));

        // Subtitles
        var subtitles = group.getMediaSubTitles();
        assertEquals(2, subtitles.size());
        assertThat(subtitles.get(0).getType(), equalTo("application/smil"));
        assertThat(subtitles.get(0).getLang(), equalTo("en-us"));
        assertThat(subtitles.get(0).getHref(), equalTo("http://www.foo.org/subtitle.smil"));
        assertThat(subtitles.get(1).getType(), equalTo("application/smil"));
        assertThat(subtitles.get(1).getLang(), equalTo("fr-FR"));
        assertThat(subtitles.get(1).getHref(), equalTo("http://www.foo.org/fr/subtitle.smil"));

        // PeerLinks
        var peerLinks = group.getMediaPeerLinks();
        assertEquals(2, peerLinks.size());
        assertThat(peerLinks.get(0).getType(), equalTo("application/x-bittorrent"));
        assertThat(peerLinks.get(0).getHref(), equalTo("http://www.foo.org/sampleFile480.torrent"));
        assertThat(peerLinks.get(1).getType(), equalTo("application/x-bittorrent"));
        assertThat(peerLinks.get(1).getHref(), equalTo("http://www.foo.org/sampleFile1080.torrent"));

        // Rights
        var rights = group.getMediaRights().orElse(null);
        assertNotNull(rights);
        assertThat(rights.getStatus(), equalTo("official"));

        // Scenes
        var scenes = group.getMediaScenes();
        assertEquals(2, scenes.size());
        assertThat(scenes.get(0).getSceneTitle(), equalTo("sceneTitle1"));
        assertThat(scenes.get(0).getSceneDescription(), equalTo("sceneDesc1"));
        assertThat(scenes.get(0).getSceneStartTime(), equalTo("00:15"));
        assertThat(scenes.get(0).getSceneEndTime(), equalTo("00:45"));
        assertThat(scenes.get(1).getSceneTitle(), equalTo("sceneTitle2"));
        assertThat(scenes.get(1).getSceneDescription(), equalTo("sceneDesc2"));
        assertThat(scenes.get(1).getSceneStartTime(), equalTo("00:57"));
        assertThat(scenes.get(1).getSceneEndTime(), equalTo("01:45"));
    }

    @Test
    void mediaRssExample4() {
        var items = new MediaRssReader().read(fromFile("mediarss/media-rss-example-4.xml"))
                .collect(Collectors.toList());

        assertEquals(1, items.size());
        MediaRssItem item = items.get(0);
        assertNotNull(item);

        var group = item.getMediaGroup().orElse(null);
        assertNotNull(group);

        var contents = group.getMediaContents();
        assertEquals(2, contents.size());

        // First group content

        var content = contents.get(0);
        assertThat(content.getUrl(), isPresentAnd(equalTo("http://www.foo.com/song64kbps.mp3")));
        assertThat(content.getFileSize(), isPresentAnd(equalTo(1000L)));
        assertThat(content.getBitRate(), isPresentAnd(equalTo(64.0)));
        assertThat(content.getType(), isPresentAnd(equalTo("audio/mpeg")));
        assertThat(content.isDefault(), isPresentAnd(equalTo(true)));
        assertThat(content.getExpression(), isPresentAnd(equalTo("full")));

        // Rating
        var ratings = content.getMediaRatings();
        assertEquals(2, ratings.size());
        assertThat(ratings.get(0).getScheme(), isPresentAnd(equalTo("urn:simple")));
        assertEquals("nonadult", ratings.get(0).getRating());
        assertThat(ratings.get(1).getScheme(), isPresentAnd(equalTo("urn:mpaa")));
        assertEquals("pg", ratings.get(1).getRating());

        // Title
        var title = content.getMediaTitle().orElse(null);
        assertNotNull(title);
        assertThat(title.getType(), isPresentAnd(equalTo("plain")));
        assertEquals("The Judy's - The Moo Song", title.getTitle());

        // Description
        var description = content.getMediaDescription().orElse(null);
        assertNotNull(description);
        assertThat(description.getType(), isPresentAnd(equalTo("plain")));
        assertEquals("Get perfectly done steaks every time", description.getDescription());

        // Keywords
        var keywords = content.getMediaKeywords();
        assertEquals(2, keywords.size());
        assertEquals("Steaks", keywords.get(0));
        assertEquals("Summer", keywords.get(1));

        // Thumbnails
        var thumbnails = content.getMediaThumbnails();
        assertEquals(2, thumbnails.size());
        assertEquals("http://www.example.com/examples/mrss/example.jpg", thumbnails.get(0).getUrl());
        assertThat(thumbnails.get(0).getWidth(), isPresentAnd(equalTo(720)));
        assertThat(thumbnails.get(0).getHeight(), isEmpty());
        assertThat(thumbnails.get(0).getTime(), isPresentAnd(equalTo("50.10")));
        assertThat(thumbnails.get(0).getTimeAsDuration(), isPresent());
        assertEquals("http://www.foo.com/keyframe.jpg", thumbnails.get(1).getUrl());
        assertThat(thumbnails.get(1).getWidth(), isPresentAnd(equalTo(75)));
        assertThat(thumbnails.get(1).getHeight(), isPresentAnd(equalTo(50)));
        assertThat(thumbnails.get(1).getTime(), isPresentAnd(equalTo("12:05:01.123")));
        assertThat(thumbnails.get(1).getTimeAsDuration(), isPresent());

        // Category
        var categories = content.getMediaCategories();
        assertEquals(2, categories.size());
        assertEquals("music/artistname/album/song1", categories.get(0).getCategory());
        assertThat(categories.get(0).getSchema(), isPresentAnd(equalTo("http://blah.com/scheme")));
        assertThat(categories.get(0).getLabel(), isPresentAnd(equalTo("music")));
        assertEquals("music/artistname/album/song2", categories.get(1).getCategory());
        assertThat(categories.get(1).getSchema(), isEmpty());
        assertThat(categories.get(1).getLabel(), isEmpty());

        // Hash
        var hashes = content.getMediaHashes();
        assertEquals(2, hashes.size());
        assertEquals("dfdec888b72151965a34b4b59031290a", hashes.get(0).getHash());
        assertThat(hashes.get(0).getAlgorithm(), isPresentAnd(equalTo("md5")));
        assertEquals("2fd4e1c67a2d28fced849ee1bb76e7391b93eb12", hashes.get(1).getHash());
        assertThat(hashes.get(1).getAlgorithm(), isPresentAnd(equalTo("sha-1")));

        // Player
        var player = content.getMediaPlayer().orElse(null);
        assertNotNull(player);
        assertThat(player.getUrl(), equalTo("http://www.foo.com/player?id=1111"));
        assertThat(player.getHeight(), isPresentAnd(equalTo(200)));
        assertThat(player.getWidth(), isPresentAnd(equalTo(400)));

        // Credits
        var credits = content.getMediaCredits();
        assertEquals(2, credits.size());
        assertThat(credits.get(0).getRole(), isPresentAnd(equalTo("producer")));
        assertThat(credits.get(0).getScheme(), isPresentAnd(equalTo("urn:ebu")));
        assertEquals("entity name", credits.get(0).getCredit());
        assertThat(credits.get(1).getRole(), isPresentAnd(equalTo("owner")));
        assertThat(credits.get(1).getScheme(), isPresentAnd(equalTo("urn:yvs")));
        assertEquals("copyright holder of the entity", credits.get(1).getCredit());

        // Copyright
        var copyright = content.getMediaCopyright().orElse(null);
        assertNotNull(copyright);
        assertThat(copyright.getUrl(), isPresentAnd(equalTo("https://creativecommons.org/licenses/by/4.0/")));
        assertEquals("2005 FooBar Media", copyright.getCopyright());

        // Text
        var texts = content.getMediaTexts();
        assertEquals(2, texts.size());
        assertThat(texts.get(0).getType(), isPresentAnd(equalTo("plain")));
        assertEquals("Oh, say, can you see, by the dawn's early light", texts.get(0).getText());
        assertThat(texts.get(0).getLang(), isEmpty());
        assertThat(texts.get(0).getStart(), isEmpty());
        assertThat(texts.get(0).getEnd(), isEmpty());
        assertThat(texts.get(1).getType(), isPresentAnd(equalTo("plain")));
        assertEquals("By the dawn's early light", texts.get(1).getText());
        assertThat(texts.get(1).getLang(), isPresentAnd(equalTo("en")));
        assertThat(texts.get(1).getStart(), isPresentAnd(equalTo("00:00:10.000")));
        assertThat(texts.get(1).getEnd(), isPresentAnd(equalTo("00:00:17.000")));

        // Restriction
        var restrictions = content.getMediaRestrictions();
        assertEquals(2, restrictions.size());
        assertThat(restrictions.get(0).getType(), equalTo("sharing"));
        assertThat(restrictions.get(0).getRelationship(), equalTo("deny"));
        assertThat(restrictions.get(0).getRestriction(), is(emptyOrNullString()));
        assertThat(restrictions.get(1).getType(), equalTo("country"));
        assertThat(restrictions.get(1).getRelationship(), equalTo("allow"));
        assertThat(restrictions.get(1).getRestriction(), equalTo("au us"));

        // Community
        var community = content.getMediaCommunity().orElse(null);
        assertNotNull(community);
        var starRating = community.getMediaStarRating().orElse(null);
        assertNotNull(starRating);
        assertThat(starRating.getAverage(), equalTo(3.5));
        assertThat(starRating.getCount(), equalTo(20));
        assertThat(starRating.getMin(), equalTo(1));
        assertThat(starRating.getMax(), equalTo(10));
        var statistics = community.getMediaStatistics().orElse(null);
        assertNotNull(statistics);
        assertThat(statistics.getViews(), equalTo(5L));
        assertThat(statistics.getFavorites(), equalTo(4));
        var tags = community.getMediaTags().orElse(null);
        assertNotNull(tags);
        assertThat(tags.getTags(), equalTo("news:5, nbc, abc:3, reuters:b"));
        var tagList = tags.getTagAsList();
        assertEquals(4, tagList.size());
        assertEquals("news:5", tagList.get(0).getTag());
        assertEquals("news", tagList.get(0).getName());
        assertThat(tagList.get(0).getWeight(), isPresentAnd(is(5)));
        assertEquals("nbc", tagList.get(1).getTag());
        assertEquals("nbc", tagList.get(1).getName());
        assertThat(tagList.get(1).getWeight(), isEmpty());
        assertEquals("abc:3", tagList.get(2).getTag());
        assertEquals("abc", tagList.get(2).getName());
        assertThat(tagList.get(2).getWeight(), isPresentAnd(is(3)));
        assertEquals("reuters:b", tagList.get(3).getTag());
        assertEquals("reuters", tagList.get(3).getName());
        assertThat(tagList.get(3).getWeight(), isEmpty());

        // Comments
        var comments = content.getMediaComments();
        assertEquals(2, comments.size());
        assertEquals("comment1", comments.get(0));
        assertEquals("comment2", comments.get(1));

        // Embed
        var embed = content.getMediaEmbed().orElse(null);
        assertNotNull(embed);
        assertThat(embed.getUrl(), equalTo("http://www.foo.com/player.swf"));
        assertThat(embed.getHeight(), equalTo(323));
        assertThat(embed.getWidth(), equalTo(512));
        assertThat(embed.getParams().size(), is(5));
        assertThat(embed.getParams(), hasEntry("type", "application/x-shockwave-flash"));
        assertThat(embed.getParams(), hasEntry("width", "512"));
        assertThat(embed.getParams(), hasEntry("height", "323"));
        assertThat(embed.getParams(), hasEntry("allowFullScreen", "true"));
        assertThat(embed.getParams(), hasEntry("flashVars", "id=12345&vid=678912i&lang=en-us&intl=us&thumbUrl=http://www.foo.com/thumbnail.jpg"));

        // Responses
        var responses = content.getMediaResponses();
        assertEquals(2, responses.size());
        assertEquals("http://www.response1.com", responses.get(0));
        assertEquals("http://www.response2.com", responses.get(1));

        // BackLinks
        var backlinks = content.getMediaBackLinks();
        assertEquals(2, backlinks.size());
        assertEquals("http://www.backlink1.com", backlinks.get(0));
        assertEquals("http://www.backlink2.com", backlinks.get(1));

        // Status
        var status = content.getMediaStatus().orElse(null);
        assertNotNull(status);
        assertThat(status.getReason(), isPresentAnd(equalTo("http://www.reasonforblocking.com")));
        assertThat(status.getState(), equalTo("blocked"));

        // Price
        var prices = content.getMediaPrices();
        assertEquals(2, prices.size());
        assertThat(prices.get(0).getType(), equalTo("rent"));
        assertThat(prices.get(0).getCurrency(), isPresentAnd(equalTo("EUR")));
        assertThat(prices.get(0).getPrice(), isPresentAnd(equalTo(19.99)));
        assertThat(prices.get(1).getType(), equalTo("subscription"));
        assertThat(prices.get(1).getInfo(), isPresentAnd(equalTo("http://www.dummy.jp/subscription_info")));
        assertThat(prices.get(1).getCurrency(), isPresentAnd(equalTo("USD")));
        assertThat(prices.get(1).getPrice(), isPresentAnd(equalTo(18.88)));

        // License
        var licenses = content.getMediaLicenses();
        assertEquals(2, licenses.size());
        assertThat(licenses.get(0).getLicense(), equalTo("This work is licensed under a Creative Commons License"));
        assertThat(licenses.get(0).getHref(), is(equalTo("http://creativecommons.org/licenses/by/4.0/")));
        assertThat(licenses.get(0).getType(), is(equalTo("text/html")));
        assertThat(licenses.get(1).getLicense(), equalTo("This work is licensed under a GNU General Public License"));
        assertThat(licenses.get(1).getHref(), is(equalTo("https://www.gnu.org/licenses/gpl-3.0.en.html")));
        assertThat(licenses.get(1).getType(), is(equalTo("text/html")));

        // Subtitles
        var subtitles = content.getMediaSubTitles();
        assertEquals(2, subtitles.size());
        assertThat(subtitles.get(0).getType(), equalTo("application/smil"));
        assertThat(subtitles.get(0).getLang(), equalTo("en-us"));
        assertThat(subtitles.get(0).getHref(), equalTo("http://www.foo.org/subtitle.smil"));
        assertThat(subtitles.get(1).getType(), equalTo("application/smil"));
        assertThat(subtitles.get(1).getLang(), equalTo("fr-FR"));
        assertThat(subtitles.get(1).getHref(), equalTo("http://www.foo.org/fr/subtitle.smil"));

        // PeerLinks
        var peerLinks = content.getMediaPeerLinks();
        assertEquals(2, peerLinks.size());
        assertThat(peerLinks.get(0).getType(), equalTo("application/x-bittorrent"));
        assertThat(peerLinks.get(0).getHref(), equalTo("http://www.foo.org/sampleFile480.torrent"));
        assertThat(peerLinks.get(1).getType(), equalTo("application/x-bittorrent"));
        assertThat(peerLinks.get(1).getHref(), equalTo("http://www.foo.org/sampleFile1080.torrent"));

        // Rights
        var rights = content.getMediaRights().orElse(null);
        assertNotNull(rights);
        assertThat(rights.getStatus(), equalTo("official"));

        // Scenes
        var scenes = content.getMediaScenes();
        assertEquals(2, scenes.size());
        assertThat(scenes.get(0).getSceneTitle(), equalTo("sceneTitle1"));
        assertThat(scenes.get(0).getSceneDescription(), equalTo("sceneDesc1"));
        assertThat(scenes.get(0).getSceneStartTime(), equalTo("00:15"));
        assertThat(scenes.get(0).getSceneEndTime(), equalTo("00:45"));
        assertThat(scenes.get(1).getSceneTitle(), equalTo("sceneTitle2"));
        assertThat(scenes.get(1).getSceneDescription(), equalTo("sceneDesc2"));
        assertThat(scenes.get(1).getSceneStartTime(), equalTo("00:57"));
        assertThat(scenes.get(1).getSceneEndTime(), equalTo("01:45"));

        // Second group content

        content = contents.get(1);
        assertThat(content.getUrl(), isPresentAnd(equalTo("http://www.foo.com/song128kbps.mp3")));
        assertThat(content.getFileSize(), isPresentAnd(equalTo(2000L)));
        assertThat(content.getBitRate(), isPresentAnd(equalTo(128.0)));
        assertThat(content.getType(), isPresentAnd(equalTo("audio/mpeg")));
        assertThat(content.isDefault(), isEmpty());
        assertThat(content.getExpression(), isPresentAnd(equalTo("full")));

        // Group level

        title = group.getMediaTitle().orElse(null);
        assertNotNull(title);
        assertThat(title.getType(), isPresentAnd(equalTo("html")));
        assertEquals("Group title", title.getTitle());
    }

    @Test
    void mediaRssExample5() {
        var items = new MediaRssReader().read(fromFile("mediarss/media-rss-example-5.xml"))
                .collect(Collectors.toList());

        assertEquals(1, items.size());
        MediaRssItem item = items.get(0);
        assertNotNull(item);
    }

    @Test
    void equalsContract() {
        EqualsVerifier.simple().forClass(MediaCategory.class).verify();
        EqualsVerifier.simple().forClass(MediaCommunity.class).verify();
        EqualsVerifier.simple().forClass(MediaCopyright.class).verify();
        EqualsVerifier.simple().forClass(MediaCredit.class).verify();
        EqualsVerifier.simple().forClass(MediaDescription.class).verify();
        EqualsVerifier.simple().forClass(MediaEmbed.class).verify();
        EqualsVerifier.simple().forClass(MediaHash.class).verify();
        EqualsVerifier.simple().forClass(MediaLicense.class).verify();
        EqualsVerifier.simple().forClass(MediaLocation.class).verify();
        EqualsVerifier.simple().forClass(MediaPeerLink.class).verify();
        EqualsVerifier.simple().forClass(MediaPlayer.class).verify();
        EqualsVerifier.simple().forClass(MediaPrice.class).verify();
        EqualsVerifier.simple().forClass(MediaRating.class).verify();
        EqualsVerifier.simple().forClass(MediaRestriction.class).verify();
        EqualsVerifier.simple().forClass(MediaScene.class).verify();
        EqualsVerifier.simple().forClass(MediaStarRating.class).verify();
        EqualsVerifier.simple().forClass(MediaStatistics.class).verify();
        EqualsVerifier.simple().forClass(MediaStatus.class).verify();
        EqualsVerifier.simple().forClass(MediaSubTitle.class).verify();
        EqualsVerifier.simple().forClass(MediaTag.class).verify();
        EqualsVerifier.simple().forClass(MediaTags.class).verify();
        EqualsVerifier.simple().forClass(MediaText.class).verify();
        EqualsVerifier.simple().forClass(MediaThumbnail.class).verify();
        EqualsVerifier.simple().forClass(MediaTitle.class).verify();

        EqualsVerifier.simple().forClass(MetadataImpl.class).verify();
        EqualsVerifier.simple().forClass(MediaContent.class).verify();
        EqualsVerifier.simple().forClass(MediaGroup.class).verify();

        EqualsVerifier.simple().forClass(MediaRssItemImpl.class).withIgnoredFields("defaultComparator").withIgnoredFields("dateTimeParser").withIgnoredFields("category").withNonnullFields("categories").withIgnoredFields("enclosure").withNonnullFields("enclosures").verify();

        EqualsVerifier.simple().forClass(MediaRssChannelImpl.class).withIgnoredFields("dateTimeParser").withIgnoredFields("category").withIgnoredFields("syUpdatePeriod").withIgnoredFields("syUpdateFrequency").verify();
    }

    private static Stream<? extends Arguments> feedReaderArguments() {
        return Stream.of(
                Arguments.of(new MediaRssReader()),
                Arguments.of(new FeedReader())
        );
    }

    private InputStream fromFile(String fileName) {
        return getClass().getClassLoader().getResourceAsStream(fileName);
    }
}
