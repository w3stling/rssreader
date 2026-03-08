package com.apptasticsoftware.rssreader.module.mediarss;

import com.apptasticsoftware.rssreader.AbstractRssReader;
import com.apptasticsoftware.rssreader.FeedChannel;
import com.apptasticsoftware.rssreader.FeedItem;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("java:S5961")
class MediaRssFeedReaderTest {

    @Test
    void readMediaRssFeed() {
        var res = new MediaRssFeedReader().read(fromFile("module/mediarss/media-rss.xml"))
                .collect(Collectors.toList());

        assertEquals(10, res.size());
    }

    @Test
    void readMediaRssFeedItemTitle() {
        var res = new MediaRssFeedReader().read(fromFile("module/mediarss/media-rss.xml"))
                .sorted(ItemComparator.oldestPublishedItemFirst())
                .collect(Collectors.toList());

        MediaRssItem item = res.get(0);
        assertThat(item.getTitle()).hasValue("Ignitis_wind");
    }

    @Test
    void readMediaRssFeedItemPubDate() {
        var res = new MediaRssFeedReader().read(fromFile("module/mediarss/media-rss.xml"))
                .sorted(ItemComparator.oldestPublishedItemFirst())
                .collect(Collectors.toList());

        MediaRssItem item = res.get(0);
        assertThat(item.getPubDate()).hasValue("Mon, 07 Nov 2022 14:51:45 -0500");
    }

    @Test
    void readMediaRssFeedItemLink() {
        var res = new MediaRssFeedReader().read(fromFile("module/mediarss/media-rss.xml"))
                .sorted(ItemComparator.oldestPublishedItemFirst())
                .collect(Collectors.toList());

        MediaRssItem item = res.get(0);
        assertThat(item.getLink()).hasValue("https://vimeo.com/768251452");
    }

    @Test
    void readMediaRssFeedDescription() {
        var res = new MediaRssFeedReader().read(fromFile("module/mediarss/media-rss.xml"))
                .sorted(ItemComparator.oldestPublishedItemFirst())
                .collect(Collectors.toList());

        MediaRssItem item = res.get(0);
        assertThat(item.getDescription()).hasValue("This is &quot;Ignitis_wind&quot; by pvz.lt on Vimeo, the home for high quality videos and the people who love them.");
    }

    @Test
    void readMediaRssFeedGuid() {
        var res = new MediaRssFeedReader().read(fromFile("module/mediarss/media-rss.xml"))
                .sorted(ItemComparator.oldestPublishedItemFirst())
                .collect(Collectors.toList());

        MediaRssItem item = res.get(0);
        assertThat(item.getGuid()).hasValue("tag:vimeo,2022-11-07:clip768251452");
    }

    @Test
    void readMediaRssFeedIsPermaLink() {
        var res = new MediaRssFeedReader().read(fromFile("module/mediarss/media-rss.xml"))
                .sorted(ItemComparator.oldestPublishedItemFirst())
                .collect(Collectors.toList());

        MediaRssItem item = res.get(0);
        assertThat(item.getIsPermaLink()).hasValue(false);
    }

    @Test
    void readMediaRssFeedThumbnail() {
        var res = new MediaRssFeedReader().read(fromFile("module/mediarss/media-rss.xml"))
                .sorted(ItemComparator.oldestPublishedItemFirst())
                .collect(Collectors.toList());

        MediaRssItem item = res.get(0);
        MediaContent content = item.getMediaContents().get(0);
        MediaThumbnail mediaThumbnail = content.getMediaThumbnails().get(0);

        assertEquals("https://i.vimeocdn.com/video/1542457228-31ab55501fdd5316663c63781ae1a37932abc4b314bcc619e3377c0ca85b859d-d_960", mediaThumbnail.getUrl());
        assertThat(mediaThumbnail.getHeight()).hasValue(540);
        assertThat(mediaThumbnail.getWidth()).hasValue(960);
        assertThat(mediaThumbnail.getTime()).isEmpty();
    }

    @Test
    void readMediaRssContent() {
        var items = new MediaRssFeedReader().read(fromFile("module/mediarss/media-rss-content.xml"))
                .collect(Collectors.toList());

        assertEquals(1, items.size());
        MediaRssItem item = items.get(0);
        assertEquals(2, item.getMediaContents().size());

        // First media content
        MediaContent mediaContent = item.getMediaContents().get(0);
        assertNotNull(mediaContent);
        assertThat(mediaContent.getUrl()).hasValue("http://d1nixf144dcz0j.cloudfront.net/shade.mp4");
        assertThat(mediaContent.getFileSize()).hasValue(1326739508L);
        assertThat(mediaContent.getMedium()).hasValue("video");
        assertThat(mediaContent.getType()).hasValue("video/mp4");
        assertThat(mediaContent.isDefault()).hasValue(true);
        assertThat(mediaContent.getExpression()).hasValue("sample");
        assertThat(mediaContent.getBitRate()).hasValue(3317.2d);
        assertThat(mediaContent.getFrameRate()).hasValue(25.3d);
        assertThat(mediaContent.getSamplingRate()).hasValue(44.1d);
        assertThat(mediaContent.getSamplingRate()).hasValue(44.1d);
        assertThat(mediaContent.getChannels()).hasValue(2);
        assertThat(mediaContent.getDuration()).hasValue(120);
        assertThat(mediaContent.getDurationAsDuration()).hasValue(Duration.ofSeconds(120));
        assertThat(mediaContent.getHeight()).hasValue(200);
        assertThat(mediaContent.getWidth()).hasValue(300);
        assertThat(mediaContent.getLang()).hasValue("en-us");

        MediaPlayer mediaPlayer = mediaContent.getMediaPlayer().orElse(null);
        assertNotNull(mediaPlayer);
        assertThat(mediaPlayer.getUrl()).isEqualTo("https://player.vimeo.com/video/768251452?h=4d67921198");
        assertThat(mediaPlayer.getHeight()).hasValue(480);
        assertThat(mediaPlayer.getWidth()).hasValue(640);

        assertThat(mediaContent.getMediaCredits()).hasSize(2);
        MediaCredit mediaCredit1 = mediaContent.getMediaCredits().get(0);
        assertThat(mediaCredit1.getRole()).hasValue("producer");
        assertThat(mediaCredit1.getScheme()).hasValue("urn:ebu");
        assertThat(mediaCredit1.getCredit()).isEqualTo("entity name");
        MediaCredit mediaCredit2 = mediaContent.getMediaCredits().get(1);
        assertThat(mediaCredit2.getRole()).hasValue("owner");
        assertThat(mediaCredit2.getScheme()).hasValue("urn:yvs");
        assertThat(mediaCredit2.getCredit()).isEqualTo("copyright holder of the entity");

        MediaTitle mediaTitle = mediaContent.getMediaTitle().orElse(null);
        assertNotNull(mediaTitle);
        assertThat(mediaTitle.getType()).hasValue("plain");
        assertThat(mediaTitle.getTitle()).isEqualTo("Title 1");

        MediaDescription mediaDescription = mediaContent.getMediaDescription().orElse(null);
        assertNotNull(mediaDescription);
        assertThat(mediaDescription.getType()).hasValue("plain");
        assertThat(mediaDescription.getDescription()).isEqualTo("Get perfectly done steaks every time");

        MediaCopyright mediaCopyright = mediaContent.getMediaCopyright().orElse(null);
        assertNotNull(mediaCopyright);
        assertThat(mediaCopyright.getUrl()).hasValue("https://creativecommons.org/licenses/by/4.0/");
        assertThat(mediaCopyright.getCopyright()).isEqualTo("2005 FooBar Media");

        assertThat(mediaContent.getMediaKeywords()).isEqualTo(List.of("Steaks", "Summer"));
        assertThat(mediaContent.getMediaRestrictions()).hasSize(2);
        assertThat(mediaContent.getMediaRestrictions().get(0).getType()).isEqualTo("sharing");
        assertThat(mediaContent.getMediaRestrictions().get(0).getRelationship()).isEqualTo("deny");
        assertThat(mediaContent.getMediaRestrictions().get(1).getType()).isEqualTo("country");
        assertThat(mediaContent.getMediaRestrictions().get(1).getRelationship()).isEqualTo("allow");
        assertThat(mediaContent.getMediaRestrictions().get(1).getRestriction()).isEqualTo("au us");

        var community = mediaContent.getMediaCommunity().orElse(null);
        assertNotNull(community);
        var starRating = community.getMediaStarRating().orElse(null);
        assertNotNull(starRating);
        assertThat(starRating.getAverage()).isEqualTo(3.5);
        assertThat(starRating.getCount()).isEqualTo(20);
        assertThat(starRating.getMin()).isEqualTo(1);
        assertThat(starRating.getMax()).isEqualTo(10);
        var statistics = community.getMediaStatistics().orElse(null);
        assertNotNull(statistics);
        assertThat(statistics.getViews()).isEqualTo(5L);
        assertThat(statistics.getFavorites()).isEqualTo(4);
        assertThat(statistics.getViews()).isEqualTo(5L);
        var tags = community.getMediaTags().orElse(null);
        assertNotNull(tags);
        assertThat(tags.getTags()).isEqualTo("news: 5, abc:3");

        assertThat(mediaContent.getMediaComments()).isEqualTo(List.of("comment1", "comment2"));
        assertThat(mediaContent.getMediaResponses()).isEqualTo(List.of("http://www.response1.com", "http://www.response2.com"));
        assertThat(mediaContent.getMediaBackLinks()).isEqualTo(List.of("http://www.backlink1.com", "http://www.backlink2.com"));

        var status = mediaContent.getMediaStatus().orElse(null);
        assertNotNull(status);
        assertThat(status.getReason()).hasValue("http://www.reasonforblocking.com");
        assertThat(status.getState()).isEqualTo("blocked");

        assertThat(mediaContent.getMediaPrices().get(0).getType()).isEqualTo("rent");
        assertThat(mediaContent.getMediaPrices().get(0).getCurrency()).hasValue("EUR");
        assertThat(mediaContent.getMediaPrices().get(0).getPrice()).hasValue(19.99);
        assertThat(mediaContent.getMediaPrices().get(1).getType()).isEqualTo("subscription");
        assertThat(mediaContent.getMediaPrices().get(1).getInfo()).hasValue("http://www.dummy.jp/subscription_info");
        assertThat(mediaContent.getMediaPrices().get(1).getCurrency()).hasValue("USD");
        assertThat(mediaContent.getMediaPrices().get(1).getPrice()).hasValue(18.88);

        var embed = mediaContent.getMediaEmbed().orElse(null);
        assertNotNull(embed);
        assertThat(embed.getUrl()).isEqualTo("http://www.foo.com/player.swf");
        assertThat(embed.getHeight()).isEqualTo(323);
        assertThat(embed.getWidth()).isEqualTo(512);
        assertThat(embed.getParams()).hasSize(5);
        assertThat(embed.getParams()).containsEntry("type", "application/x-shockwave-flash");
        assertThat(embed.getParams()).containsEntry("width", "512");
        assertThat(embed.getParams()).containsEntry("height", "323");
        assertThat(embed.getParams()).containsEntry("allowFullScreen", "true");
        assertThat(embed.getParams()).containsEntry("flashVars", "id=12345&vid=678912i&lang=en-us&intl=us&thumbUrl=http://www.foo.com/thumbnail.jpg");

        assertThat(mediaContent.getMediaSubTitles().get(0).getType()).isEqualTo("application/smil");
        assertThat(mediaContent.getMediaSubTitles().get(0).getLang()).isEqualTo("en-us");
        assertThat(mediaContent.getMediaSubTitles().get(0).getHref()).isEqualTo("http://www.foo.org/subtitle.smil");
        assertThat(mediaContent.getMediaSubTitles().get(1).getType()).isEqualTo("application/smil");
        assertThat(mediaContent.getMediaSubTitles().get(1).getLang()).isEqualTo("fr-FR");
        assertThat(mediaContent.getMediaSubTitles().get(1).getHref()).isEqualTo("http://www.foo.org/fr/subtitle.smil");

        assertThat(mediaContent.getMediaPeerLinks().get(0).getType()).isEqualTo("application/x-bittorrent");
        assertThat(mediaContent.getMediaPeerLinks().get(0).getHref()).isEqualTo("http://www.foo.org/sampleFile480.torrent");
        assertThat(mediaContent.getMediaPeerLinks().get(1).getType()).isEqualTo("application/x-bittorrent");
        assertThat(mediaContent.getMediaPeerLinks().get(1).getHref()).isEqualTo("http://www.foo.org/sampleFile1080.torrent");

        assertThat(mediaContent.getMediaLocations().get(0).getDescription()).isEqualTo("My house");
        assertThat(mediaContent.getMediaLocations().get(0).getStart()).isEqualTo("00:01");
        assertThat(mediaContent.getMediaLocations().get(0).getEnd()).isEqualTo("01:00");

        assertThat(mediaContent.getMediaScenes().get(0).getSceneTitle()).isEqualTo("sceneTitle1");
        assertThat(mediaContent.getMediaScenes().get(0).getSceneDescription()).isEqualTo("sceneDesc1");
        assertThat(mediaContent.getMediaScenes().get(0).getSceneStartTime()).isEqualTo("00:15");
        assertThat(mediaContent.getMediaScenes().get(0).getSceneStartTimeAsDuration()).isEqualTo(Duration.ofSeconds(15));
        assertThat(mediaContent.getMediaScenes().get(0).getSceneEndTime()).isEqualTo("00:45");
        assertThat(mediaContent.getMediaScenes().get(0).getSceneEndTimeAsDuration()).isEqualTo(Duration.ofSeconds(45));
        assertThat(mediaContent.getMediaScenes().get(1).getSceneTitle()).isEqualTo("sceneTitle2");
        assertThat(mediaContent.getMediaScenes().get(1).getSceneDescription()).isEqualTo("sceneDesc2");
        assertThat(mediaContent.getMediaScenes().get(1).getSceneStartTime()).isEqualTo("00:57");
        assertThat(mediaContent.getMediaScenes().get(1).getSceneStartTimeAsDuration()).isEqualTo(Duration.ofSeconds(57));
        assertThat(mediaContent.getMediaScenes().get(1).getSceneEndTime()).isEqualTo("01:45");
        assertThat(mediaContent.getMediaScenes().get(1).getSceneEndTimeAsDuration()).isEqualTo(Duration.ofSeconds(105));

        assertThat(mediaContent.getMediaScenes().get(0).getSceneTitle()).isEqualTo("sceneTitle1");
        assertThat(mediaContent.getMediaScenes().get(0).getSceneDescription()).isEqualTo("sceneDesc1");
        assertThat(mediaContent.getMediaScenes().get(0).getSceneStartTime()).isEqualTo("00:15");
        assertThat(mediaContent.getMediaScenes().get(0).getSceneEndTime()).isEqualTo("00:45");
        assertThat(mediaContent.getMediaScenes().get(1).getSceneTitle()).isEqualTo("sceneTitle2");
        assertThat(mediaContent.getMediaScenes().get(1).getSceneDescription()).isEqualTo("sceneDesc2");
        assertThat(mediaContent.getMediaScenes().get(1).getSceneStartTime()).isEqualTo("00:57");
        assertThat(mediaContent.getMediaScenes().get(1).getSceneEndTime()).isEqualTo("01:45");


        // Second media content
        MediaContent mediaContent2 = item.getMediaContents().get(1);
        MediaTitle mediaTitle2 = mediaContent2.getMediaTitle().orElse(null);
        assertNotNull(mediaTitle2);
        assertThat(mediaTitle2.getType()).hasValue("plain");
        assertThat(mediaTitle2.getTitle()).isEqualTo("Title 2");
    }

    @Test
    void readMediaRssGroup() {
        var items = new MediaRssFeedReader().read(fromFile("module/mediarss/media-rss-group.xml"))
                .collect(Collectors.toList());

        assertEquals(1, items.size());
        MediaRssItem item = items.get(0);

        assertNotNull(item.getMediaGroup());
        assertThat(item.getMediaGroup()).isPresent();
        assertThat(item.getMediaGroup().map(MediaGroup::getMediaContents).orElse(List.of())).hasSize(2);

        var group = item.getMediaGroup().orElse(null);
        assertNotNull(group);

        var title = group.getMediaTitle().orElse(null);
        assertNotNull(title);
        assertThat(title.getTitle()).isEqualTo("Grilling Steaks for Summer");
        assertThat(title.getType()).hasValue("plain");

        var description = group.getMediaDescription().orElse(null);
        assertNotNull(description);
        assertThat(description.getDescription()).isEqualTo("Get perfectly done steaks every time");
        assertThat(description.getType()).hasValue("plain");

        var keywords = group.getMediaKeywords();
        assertThat(keywords).isEqualTo(List.of("Steaks", "Summer"));

        assertThat(group.getMediaRestrictions()).hasSize(2);
        assertThat(group.getMediaRestrictions().get(0).getType()).isEqualTo("sharing");
        assertThat(group.getMediaRestrictions().get(0).getRelationship()).isEqualTo("deny");
        assertThat(group.getMediaRestrictions().get(1).getType()).isEqualTo("country");
        assertThat(group.getMediaRestrictions().get(1).getRelationship()).isEqualTo("allow");
        assertThat(group.getMediaRestrictions().get(1).getRestriction()).isEqualTo("au us");


        var content1 = item.getMediaGroup().map(g -> g.getMediaContents().get(0)).orElse(null);
        assertNotNull(content1);
        assertThat(content1.getUrl()).hasValue("http://www.example.com/examples/mrss/example1.mp4");
        assertThat(content1.getFileSize()).hasValue(1326739508L);
        assertThat(content1.getMedium()).hasValue("video");
        assertThat(content1.getType()).hasValue("video/mp4");
        assertThat(content1.isDefault()).hasValue(true);
        assertThat(content1.getExpression()).hasValue("sample");
        assertThat(content1.getBitRate()).hasValue(3317.2d);
        assertThat(content1.getFrameRate()).hasValue(25.3d);
        assertThat(content1.getSamplingRate()).hasValue(44.1d);
        assertThat(content1.getSamplingRate()).hasValue(44.1d);
        assertThat(content1.getChannels()).hasValue(2);
        assertThat(content1.getDuration()).hasValue(120);
        assertThat(content1.getHeight()).hasValue(200);
        assertThat(content1.getWidth()).hasValue(300);
        assertThat(content1.getLang()).hasValue("en-us");

        var content2 = item.getMediaGroup().map(g -> g.getMediaContents().get(1)).orElse(null);
        assertNotNull(content2);
        assertThat(content2.getUrl()).hasValue("http://www.example.com/examples/mrss/example2.mp4");
        assertThat(content2.getType()).hasValue("video/mp4");
        assertThat(content2.getDuration()).hasValue(91);
        assertThat(content2.getHeight()).hasValue(406);
        assertThat(content2.getWidth()).hasValue(720);

        var thumbnail1 = item.getMediaGroup().map(g -> g.getMediaThumbnails().get(0)).orElse(null);
        assertNotNull(thumbnail1);
        assertThat(thumbnail1.getUrl()).isEqualTo("http://www.example.com/examples/mrss/example.jpg");
        assertThat(thumbnail1.getWidth()).hasValue(720);

        var thumbnail2 = item.getMediaGroup().map(g -> g.getMediaThumbnails().get(1)).orElse(null);
        assertNotNull(thumbnail2);
        assertThat(thumbnail2.getUrl()).isEqualTo("http://www.foo.com/keyframe.jpg");
        assertThat(thumbnail2.getWidth()).hasValue(75);
        assertThat(thumbnail2.getHeight()).hasValue(50);
        assertThat(thumbnail2.getTime()).hasValue("12:05:01.123");

        var community = item.getMediaGroup().flatMap(MediaGroup::getMediaCommunity).orElse(null);
        assertNotNull(community);

        var starRating = community.getMediaStarRating().orElse(null);
        assertNotNull(starRating);
        assertThat(starRating.getCount()).isEqualTo(20);
        assertThat(starRating.getAverage()).isEqualTo(3.5);
        assertThat(starRating.getMax()).isEqualTo(10);
        assertThat(starRating.getMin()).isEqualTo(1);

        var statistics = community.getMediaStatistics().orElse(null);
        assertNotNull(statistics);
        assertThat(statistics.getViews()).isEqualTo(56L);
        assertThat(statistics.getFavorites()).isEqualTo(5);

        var tags = community.getMediaTags().orElse(null);
        assertNotNull(tags);
        assertThat(tags.getTags()).isEqualTo("news: 5, abc:3, reuters");

        var copyright = group.getMediaCopyright().orElse(null);
        assertNotNull(copyright);
        assertThat(copyright.getCopyright()).isEqualTo("2005 FooBar Media");
        assertThat(copyright.getUrl()).hasValue("https://creativecommons.org/licenses/by/4.0/");

        assertThat(group.getMediaTexts()).hasSize(2);
        var text1 = group.getMediaTexts().get(0);
        assertThat(text1.getType()).hasValue("plain");
        assertThat(text1.getText()).isEqualTo("Oh, say, can you see, by the dawn's early light");
        var text2 = group.getMediaTexts().get(1);
        assertThat(text2.getType()).hasValue("plain");
        assertThat(text2.getText()).isEqualTo("By the dawn's early light");
        assertThat(text2.getLang()).hasValue("en");
        assertThat(text2.getStart()).hasValue("00:00:10.000");
        assertThat(text2.getStartAsDuration()).hasValue(Util.toDuration("00:00:10.000"));
        assertThat(text2.getEnd()).hasValue("00:00:17.000");
        assertThat(text2.getEndAsDuration()).hasValue(Util.toDuration("00:00:17.000"));

        assertThat(group.getMediaComments()).isEqualTo(List.of("comment1", "comment2"));
        assertThat(group.getMediaResponses()).isEqualTo(List.of("http://www.response1.com", "http://www.response2.com"));
        assertThat(group.getMediaBackLinks()).isEqualTo(List.of("http://www.backlink1.com", "http://www.backlink2.com"));

        var status = group.getMediaStatus().orElse(null);
        assertNotNull(status);
        assertThat(status.getReason()).hasValue("http://www.reasonforblocking.com");
        assertThat(status.getState()).isEqualTo("blocked");

        assertThat(group.getMediaPrices().get(0).getType()).isEqualTo("rent");
        assertThat(group.getMediaPrices().get(0).getCurrency()).hasValue("EUR");
        assertThat(group.getMediaPrices().get(0).getPrice()).hasValue(19.99);
        assertThat(group.getMediaPrices().get(1).getType()).isEqualTo("subscription");
        assertThat(group.getMediaPrices().get(1).getInfo()).hasValue("http://www.dummy.jp/subscription_info");
        assertThat(group.getMediaPrices().get(1).getCurrency()).hasValue("USD");
        assertThat(group.getMediaPrices().get(1).getPrice()).hasValue(18.88);

        var embed = group.getMediaEmbed().orElse(null);
        assertNotNull(embed);
        assertThat(embed.getUrl()).isEqualTo("http://www.foo.com/player.swf");
        assertThat(embed.getHeight()).isEqualTo(323);
        assertThat(embed.getWidth()).isEqualTo(512);
        assertThat(embed.getParams()).hasSize(5);
        assertThat(embed.getParams()).containsEntry("type", "application/x-shockwave-flash");
        assertThat(embed.getParams()).containsEntry("width", "512");
        assertThat(embed.getParams()).containsEntry("height", "323");
        assertThat(embed.getParams()).containsEntry("allowFullScreen", "true");
        assertThat(embed.getParams()).containsEntry("flashVars", "id=12345&vid=678912i&lang=en-us&intl=us&thumbUrl=http://www.foo.com/thumbnail.jpg");

        assertThat(group.getMediaSubTitles().get(0).getType()).isEqualTo("application/smil");
        assertThat(group.getMediaSubTitles().get(0).getLang()).isEqualTo("en-us");
        assertThat(group.getMediaSubTitles().get(0).getHref()).isEqualTo("http://www.foo.org/subtitle.smil");
        assertThat(group.getMediaSubTitles().get(1).getType()).isEqualTo("application/smil");
        assertThat(group.getMediaSubTitles().get(1).getLang()).isEqualTo("fr-FR");
        assertThat(group.getMediaSubTitles().get(1).getHref()).isEqualTo("http://www.foo.org/fr/subtitle.smil");

        assertThat(group.getMediaPeerLinks().get(0).getType()).isEqualTo("application/x-bittorrent");
        assertThat(group.getMediaPeerLinks().get(0).getHref()).isEqualTo("http://www.foo.org/sampleFile480.torrent");
        assertThat(group.getMediaPeerLinks().get(1).getType()).isEqualTo("application/x-bittorrent");
        assertThat(group.getMediaPeerLinks().get(1).getHref()).isEqualTo("http://www.foo.org/sampleFile1080.torrent");

        assertThat(group.getMediaLocations().get(0).getDescription()).isEqualTo("My house");
        assertThat(group.getMediaLocations().get(0).getStart()).isEqualTo("00:01");
        assertThat(group.getMediaLocations().get(0).getEnd()).isEqualTo("01:00");

        assertThat(group.getMediaScenes().get(0).getSceneTitle()).isEqualTo("sceneTitle1");
        assertThat(group.getMediaScenes().get(0).getSceneDescription()).isEqualTo("sceneDesc1");
        assertThat(group.getMediaScenes().get(0).getSceneStartTime()).isEqualTo("00:15");
        assertThat(group.getMediaScenes().get(0).getSceneEndTime()).isEqualTo("00:45");
        assertThat(group.getMediaScenes().get(1).getSceneTitle()).isEqualTo("sceneTitle2");
        assertThat(group.getMediaScenes().get(1).getSceneDescription()).isEqualTo("sceneDesc2");
        assertThat(group.getMediaScenes().get(1).getSceneStartTime()).isEqualTo("00:57");
        assertThat(group.getMediaScenes().get(1).getSceneEndTime()).isEqualTo("01:45");

    }

    @Test
    void mediaRssChannel() {
        var items = new MediaRssFeedReader().read(fromFile("module/mediarss/media-rss-channel.xml"))
                .collect(Collectors.toList());

        assertEquals(1, items.size());
        MediaRssItem item = items.get(0);
        assertNotNull(item);
        assertThat(item.getTitle()).hasValue("Movie Title: Is this a good movie?");
        assertThat(item.getLink()).hasValue("http://www.foo.com/item1.htm");

        MediaRssChannelImpl channel = (MediaRssChannelImpl) item.getChannel();
        assertNotNull(channel);

        assertThat(channel.getTitle()).isEqualTo("My Movie Review Site");
        assertThat(channel.getLink()).isEqualTo("http://www.foo.com");
        assertThat(channel.getDescription()).isEqualTo("I review movies.");

        // Rating
        var ratings = channel.getMediaRatings();
        assertEquals(2, ratings.size());
        assertThat(ratings.get(0).getScheme()).hasValue("urn:simple");
        assertEquals("nonadult", ratings.get(0).getRating());
        assertThat(ratings.get(1).getScheme()).hasValue("urn:mpaa");
        assertEquals("pg", ratings.get(1).getRating());

        // Title
        var title = channel.getMediaTitle().orElse(null);
        assertNotNull(title);
        assertThat(title.getType()).hasValue("plain");
        assertEquals("The Judy's - The Moo Song", title.getTitle());

        // Description
        var description = channel.getMediaDescription().orElse(null);
        assertNotNull(description);
        assertThat(description.getType()).hasValue("plain");
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
        assertThat(thumbnails.get(0).getWidth()).hasValue(720);
        assertThat(thumbnails.get(0).getHeight()).isEmpty();
        assertThat(thumbnails.get(0).getTime()).hasValue("50.10");
        assertThat(thumbnails.get(0).getTimeAsDuration()).isPresent();
        assertEquals("http://www.foo.com/keyframe.jpg", thumbnails.get(1).getUrl());
        assertThat(thumbnails.get(1).getWidth()).hasValue(75);
        assertThat(thumbnails.get(1).getHeight()).hasValue(50);
        assertThat(thumbnails.get(1).getTime()).hasValue("12:05:01.123");
        assertThat(thumbnails.get(1).getTimeAsDuration()).isPresent();

        // Category
        var categories = channel.getMediaCategories();
        assertEquals(2, categories.size());
        assertEquals("music/artistname/album/song1", categories.get(0).getCategory());
        assertThat(categories.get(0).getSchema()).hasValue("http://blah.com/scheme");
        assertThat(categories.get(0).getLabel()).hasValue("music");
        assertEquals("music/artistname/album/song2", categories.get(1).getCategory());
        assertThat(categories.get(1).getSchema()).isEmpty();
        assertThat(categories.get(1).getLabel()).isEmpty();

        // Hash
        var hashes = channel.getMediaHashes();
        assertEquals(2, hashes.size());
        assertEquals("dfdec888b72151965a34b4b59031290a", hashes.get(0).getHash());
        assertThat(hashes.get(0).getAlgorithm()).hasValue("md5");
        assertEquals("2fd4e1c67a2d28fced849ee1bb76e7391b93eb12", hashes.get(1).getHash());
        assertThat(hashes.get(1).getAlgorithm()).hasValue("sha-1");

        // Player
        var player = channel.getMediaPlayer().orElse(null);
        assertNotNull(player);
        assertThat(player.getUrl()).isEqualTo("http://www.foo.com/player?id=1111");
        assertThat(player.getHeight()).hasValue(200);
        assertThat(player.getWidth()).hasValue(400);

        // Credits
        var credits = channel.getMediaCredits();
        assertEquals(2, credits.size());
        assertThat(credits.get(0).getRole()).hasValue("producer");
        assertThat(credits.get(0).getScheme()).hasValue("urn:ebu");
        assertEquals("entity name", credits.get(0).getCredit());
        assertThat(credits.get(1).getRole()).hasValue("owner");
        assertThat(credits.get(1).getScheme()).hasValue("urn:yvs");
        assertEquals("copyright holder of the entity", credits.get(1).getCredit());

        // Copyright
        var copyright = channel.getMediaCopyright().orElse(null);
        assertNotNull(copyright);
        assertThat(copyright.getUrl()).hasValue("https://creativecommons.org/licenses/by/4.0/");
        assertEquals("2005 FooBar Media", copyright.getCopyright());

        // Text
        var texts = channel.getMediaTexts();
        assertEquals(2, texts.size());
        assertThat(texts.get(0).getType()).hasValue("plain");
        assertEquals("Oh, say, can you see, by the dawn's early light", texts.get(0).getText());
        assertThat(texts.get(0).getLang()).isEmpty();
        assertThat(texts.get(0).getStart()).isEmpty();
        assertThat(texts.get(0).getEnd()).isEmpty();
        assertThat(texts.get(1).getType()).hasValue("plain");
        assertEquals("By the dawn's early light", texts.get(1).getText());
        assertThat(texts.get(1).getLang()).hasValue("en");
        assertThat(texts.get(1).getStart()).hasValue("00:00:10.000");
        assertThat(texts.get(1).getEnd()).hasValue("00:00:17.000");

        // Restriction
        var restrictions = channel.getMediaRestrictions();
        assertEquals(2, restrictions.size());
        assertThat(restrictions.get(0).getType()).isEqualTo("sharing");
        assertThat(restrictions.get(0).getRelationship()).isEqualTo("deny");
        assertThat(restrictions.get(0).getRestriction()).isNullOrEmpty();
        assertThat(restrictions.get(1).getType()).isEqualTo("country");
        assertThat(restrictions.get(1).getRelationship()).isEqualTo("allow");
        assertThat(restrictions.get(1).getRestriction()).isEqualTo("au us");

        // Community
        var community = channel.getMediaCommunity().orElse(null);
        assertNotNull(community);
        var starRating = community.getMediaStarRating().orElse(null);
        assertNotNull(starRating);
        assertThat(starRating.getAverage()).isEqualTo(3.5);
        assertThat(starRating.getCount()).isEqualTo(20);
        assertThat(starRating.getMin()).isEqualTo(1);
        assertThat(starRating.getMax()).isEqualTo(10);
        var statistics = community.getMediaStatistics().orElse(null);
        assertNotNull(statistics);
        assertThat(statistics.getViews()).isEqualTo(5L);
        assertThat(statistics.getFavorites()).isEqualTo(4);
        var tags = community.getMediaTags().orElse(null);
        assertNotNull(tags);
        assertThat(tags.getTags()).isEqualTo("news:5, nbc, abc:3, reuters:b");
        var tagList = tags.getTagAsList();
        assertEquals(4, tagList.size());
        assertEquals("news:5", tagList.get(0).getTag());
        assertEquals("news", tagList.get(0).getName());
        assertThat(tagList.get(0).getWeight()).hasValue(5);
        assertEquals("nbc", tagList.get(1).getTag());
        assertEquals("nbc", tagList.get(1).getName());
        assertThat(tagList.get(1).getWeight()).isEmpty();
        assertEquals("abc:3", tagList.get(2).getTag());
        assertEquals("abc", tagList.get(2).getName());
        assertThat(tagList.get(2).getWeight()).hasValue(3);
        assertEquals("reuters:b", tagList.get(3).getTag());
        assertEquals("reuters", tagList.get(3).getName());
        assertThat(tagList.get(3).getWeight()).isEmpty();

        // Comments
        var comments = channel.getMediaComments();
        assertEquals(2, comments.size());
        assertEquals("comment1", comments.get(0));
        assertEquals("comment2", comments.get(1));

        // Embed
        var embed = channel.getMediaEmbed().orElse(null);
        assertNotNull(embed);
        assertThat(embed.getUrl()).isEqualTo("http://www.foo.com/player.swf");
        assertThat(embed.getHeight()).isEqualTo(323);
        assertThat(embed.getWidth()).isEqualTo(512);
        assertThat(embed.getParams()).hasSize(5);
        assertThat(embed.getParams()).containsEntry("type", "application/x-shockwave-flash");
        assertThat(embed.getParams()).containsEntry("width", "512");
        assertThat(embed.getParams()).containsEntry("height", "323");
        assertThat(embed.getParams()).containsEntry("allowFullScreen", "true");
        assertThat(embed.getParams()).containsEntry("flashVars", "id=12345&vid=678912i&lang=en-us&intl=us&thumbUrl=http://www.foo.com/thumbnail.jpg");

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
        assertThat(status.getReason()).hasValue("http://www.reasonforblocking.com");
        assertThat(status.getState()).isEqualTo("blocked");

        // Price
        var prices = channel.getMediaPrices();
        assertEquals(2, prices.size());
        assertThat(prices.get(0).getType()).isEqualTo("rent");
        assertThat(prices.get(0).getCurrency()).hasValue("EUR");
        assertThat(prices.get(0).getCurrencyAsCurrency()).hasValue(Currency.getInstance("EUR"));
        assertThat(prices.get(0).getPrice()).hasValue(19.99);
        assertThat(prices.get(1).getType()).isEqualTo("subscription");
        assertThat(prices.get(1).getInfo()).hasValue("http://www.dummy.jp/subscription_info");
        assertThat(prices.get(1).getCurrency()).hasValue("USD");
        assertThat(prices.get(1).getCurrencyAsCurrency()).hasValue(Currency.getInstance("USD"));
        assertThat(prices.get(1).getPrice()).hasValue(18.88);

        // License
        var licenses = channel.getMediaLicenses();
        assertEquals(2, licenses.size());
        assertThat(licenses.get(0).getLicense()).isEqualTo("This work is licensed under a Creative Commons License");
        assertThat(licenses.get(0).getHref()).isEqualTo("http://creativecommons.org/licenses/by/4.0/");
        assertThat(licenses.get(0).getType()).isEqualTo("text/html");
        assertThat(licenses.get(1).getLicense()).isEqualTo("This work is licensed under a GNU General Public License");
        assertThat(licenses.get(1).getHref()).isEqualTo("https://www.gnu.org/licenses/gpl-3.0.en.html");
        assertThat(licenses.get(1).getType()).isEqualTo("text/html");

        // Subtitles
        var subtitles = channel.getMediaSubTitles();
        assertEquals(2, subtitles.size());
        assertThat(subtitles.get(0).getType()).isEqualTo("application/smil");
        assertThat(subtitles.get(0).getLang()).isEqualTo("en-us");
        assertThat(subtitles.get(0).getHref()).isEqualTo("http://www.foo.org/subtitle.smil");
        assertThat(subtitles.get(1).getType()).isEqualTo("application/smil");
        assertThat(subtitles.get(1).getLang()).isEqualTo("fr-FR");
        assertThat(subtitles.get(1).getHref()).isEqualTo("http://www.foo.org/fr/subtitle.smil");

        // PeerLinks
        var peerLinks = channel.getMediaPeerLinks();
        assertEquals(2, peerLinks.size());
        assertThat(peerLinks.get(0).getType()).isEqualTo("application/x-bittorrent");
        assertThat(peerLinks.get(0).getHref()).isEqualTo("http://www.foo.org/sampleFile480.torrent");
        assertThat(peerLinks.get(1).getType()).isEqualTo("application/x-bittorrent");
        assertThat(peerLinks.get(1).getHref()).isEqualTo("http://www.foo.org/sampleFile1080.torrent");

        // Rights
        var rights = channel.getMediaRights().orElse(null);
        assertNotNull(rights);
        assertThat(rights.getStatus()).isEqualTo("userCreated");

        // Scenes
        var scenes = channel.getMediaScenes();
        assertEquals(2, scenes.size());
        assertThat(scenes.get(0).getSceneTitle()).isEqualTo("sceneTitle1");
        assertThat(scenes.get(0).getSceneDescription()).isEqualTo("sceneDesc1");
        assertThat(scenes.get(0).getSceneStartTime()).isEqualTo("00:15");
        assertThat(scenes.get(0).getSceneEndTime()).isEqualTo("00:45");
        assertThat(scenes.get(1).getSceneTitle()).isEqualTo("sceneTitle2");
        assertThat(scenes.get(1).getSceneDescription()).isEqualTo("sceneDesc2");
        assertThat(scenes.get(1).getSceneStartTime()).isEqualTo("00:57");
        assertThat(scenes.get(1).getSceneEndTime()).isEqualTo("01:45");
    }

    @ParameterizedTest
    @MethodSource("feedReaderArguments")
    void mediaRssExample1(AbstractRssReader<MediaRssChannel, MediaRssItem> feedReader) {
        var items = feedReader.read(fromFile("module/mediarss/media-rss-example-1.xml"))
                .collect(Collectors.toList());

        assertEquals(1, items.size());
        MediaRssItem item = items.get(0);
        assertHasFeedItem(item);
        assertThat(item.getTitle()).hasValue("Movie Title: Is this a good movie?");
        assertThat(item.getLink()).hasValue("http://www.foo.com/item1.htm");
        assertNotNull(item);

        // Rating
        var ratings = item.getMediaRatings();
        assertEquals(2, ratings.size());
        assertThat(ratings.get(0).getScheme()).hasValue("urn:simple");
        assertEquals("nonadult", ratings.get(0).getRating());
        assertThat(ratings.get(1).getScheme()).hasValue("urn:mpaa");
        assertEquals("pg", ratings.get(1).getRating());

        // Title
        var title = item.getMediaTitle().orElse(null);
        assertNotNull(title);
        assertThat(title.getType()).hasValue("plain");
        assertEquals("The Judy's - The Moo Song", title.getTitle());

        // Description
        var description = item.getMediaDescription().orElse(null);
        assertNotNull(description);
        assertThat(description.getType()).hasValue("plain");
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
        assertThat(thumbnails.get(0).getWidth()).hasValue(720);
        assertThat(thumbnails.get(0).getHeight()).isEmpty();
        assertThat(thumbnails.get(0).getTime()).hasValue("50.10");
        assertThat(thumbnails.get(0).getTimeAsDuration()).isPresent();
        assertEquals("http://www.foo.com/keyframe.jpg", thumbnails.get(1).getUrl());
        assertThat(thumbnails.get(1).getWidth()).hasValue(75);
        assertThat(thumbnails.get(1).getHeight()).hasValue(50);
        assertThat(thumbnails.get(1).getTime()).hasValue("12:05:01.123");
        assertThat(thumbnails.get(1).getTimeAsDuration()).isPresent();

        // Category
        var categories = item.getMediaCategories();
        assertEquals(2, categories.size());
        assertEquals("music/artistname/album/song1", categories.get(0).getCategory());
        assertThat(categories.get(0).getSchema()).hasValue("http://blah.com/scheme");
        assertThat(categories.get(0).getLabel()).hasValue("music");
        assertEquals("music/artistname/album/song2", categories.get(1).getCategory());
        assertThat(categories.get(1).getSchema()).isEmpty();
        assertThat(categories.get(1).getLabel()).isEmpty();

        // Hash
        var hashes = item.getMediaHashes();
        assertEquals(2, hashes.size());
        assertEquals("dfdec888b72151965a34b4b59031290a", hashes.get(0).getHash());
        assertThat(hashes.get(0).getAlgorithm()).hasValue("md5");
        assertEquals("2fd4e1c67a2d28fced849ee1bb76e7391b93eb12", hashes.get(1).getHash());
        assertThat(hashes.get(1).getAlgorithm()).hasValue("sha-1");

        // Player
        var player = item.getMediaPlayer().orElse(null);
        assertNotNull(player);
        assertThat(player.getUrl()).isEqualTo("http://www.foo.com/player?id=1111");
        assertThat(player.getHeight()).hasValue(200);
        assertThat(player.getWidth()).hasValue(400);

        // Credits
        var credits = item.getMediaCredits();
        assertEquals(2, credits.size());
        assertThat(credits.get(0).getRole()).hasValue("producer");
        assertThat(credits.get(0).getScheme()).hasValue("urn:ebu");
        assertEquals("entity name", credits.get(0).getCredit());
        assertThat(credits.get(1).getRole()).hasValue("owner");
        assertThat(credits.get(1).getScheme()).hasValue("urn:yvs");
        assertEquals("copyright holder of the entity", credits.get(1).getCredit());

        // Copyright
        var copyright = item.getMediaCopyright().orElse(null);
        assertNotNull(copyright);
        assertThat(copyright.getUrl()).hasValue("https://creativecommons.org/licenses/by/4.0/");
        assertEquals("2005 FooBar Media", copyright.getCopyright());

        // Text
        var texts = item.getMediaTexts();
        assertEquals(2, texts.size());
        assertThat(texts.get(0).getType()).hasValue("plain");
        assertEquals("Oh, say, can you see, by the dawn's early light", texts.get(0).getText());
        assertThat(texts.get(0).getLang()).isEmpty();
        assertThat(texts.get(0).getStart()).isEmpty();
        assertThat(texts.get(0).getEnd()).isEmpty();
        assertThat(texts.get(1).getType()).hasValue("plain");
        assertEquals("By the dawn's early light", texts.get(1).getText());
        assertThat(texts.get(1).getLang()).hasValue("en");
        assertThat(texts.get(1).getStart()).hasValue("00:00:10.000");
        assertThat(texts.get(1).getEnd()).hasValue("00:00:17.000");

        // Restriction
        var restrictions = item.getMediaRestrictions();
        assertEquals(2, restrictions.size());
        assertThat(restrictions.get(0).getType()).isEqualTo("sharing");
        assertThat(restrictions.get(0).getRelationship()).isEqualTo("deny");
        assertThat(restrictions.get(0).getRestriction()).isNullOrEmpty();
        assertThat(restrictions.get(1).getType()).isEqualTo("country");
        assertThat(restrictions.get(1).getRelationship()).isEqualTo("allow");
        assertThat(restrictions.get(1).getRestriction()).isEqualTo("au us");

        // Community
        var community = item.getMediaCommunity().orElse(null);
        assertNotNull(community);
        var starRating = community.getMediaStarRating().orElse(null);
        assertNotNull(starRating);
        assertThat(starRating.getAverage()).isEqualTo(3.5);
        assertThat(starRating.getCount()).isEqualTo(20);
        assertThat(starRating.getMin()).isEqualTo(1);
        assertThat(starRating.getMax()).isEqualTo(10);
        var statistics = community.getMediaStatistics().orElse(null);
        assertNotNull(statistics);
        assertThat(statistics.getViews()).isEqualTo(5L);
        assertThat(statistics.getFavorites()).isEqualTo(4);
        var tags = community.getMediaTags().orElse(null);
        assertNotNull(tags);
        assertThat(tags.getTags()).isEqualTo("news:5, nbc, abc:3, reuters:b");
        var tagList = tags.getTagAsList();
        assertEquals(4, tagList.size());
        assertEquals("news:5", tagList.get(0).getTag());
        assertEquals("news", tagList.get(0).getName());
        assertThat(tagList.get(0).getWeight()).hasValue(5);
        assertEquals("nbc", tagList.get(1).getTag());
        assertEquals("nbc", tagList.get(1).getName());
        assertThat(tagList.get(1).getWeight()).isEmpty();
        assertEquals("abc:3", tagList.get(2).getTag());
        assertEquals("abc", tagList.get(2).getName());
        assertThat(tagList.get(2).getWeight()).hasValue(3);
        assertEquals("reuters:b", tagList.get(3).getTag());
        assertEquals("reuters", tagList.get(3).getName());
        assertThat(tagList.get(3).getWeight()).isEmpty();

        // Comments
        var comments = item.getMediaComments();
        assertEquals(2, comments.size());
        assertEquals("comment1", comments.get(0));
        assertEquals("comment2", comments.get(1));

        // Embed
        var embed = item.getMediaEmbed().orElse(null);
        assertNotNull(embed);
        assertThat(embed.getUrl()).isEqualTo("http://www.foo.com/player.swf");
        assertThat(embed.getHeight()).isEqualTo(323);
        assertThat(embed.getWidth()).isEqualTo(512);
        assertThat(embed.getParams()).hasSize(5);
        assertThat(embed.getParams()).containsEntry("type", "application/x-shockwave-flash");
        assertThat(embed.getParams()).containsEntry("width", "512");
        assertThat(embed.getParams()).containsEntry("height", "323");
        assertThat(embed.getParams()).containsEntry("allowFullScreen", "true");
        assertThat(embed.getParams()).containsEntry("flashVars", "id=12345&vid=678912i&lang=en-us&intl=us&thumbUrl=http://www.foo.com/thumbnail.jpg");

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
        assertThat(status.getReason()).hasValue("http://www.reasonforblocking.com");
        assertThat(status.getState()).isEqualTo("blocked");

        // Price
        var prices = item.getMediaPrices();
        assertEquals(2, prices.size());
        assertThat(prices.get(0).getType()).isEqualTo("rent");
        assertThat(prices.get(0).getCurrency()).hasValue("EUR");
        assertThat(prices.get(0).getPrice()).hasValue(19.99);
        assertThat(prices.get(1).getType()).isEqualTo("subscription");
        assertThat(prices.get(1).getInfo()).hasValue("http://www.dummy.jp/subscription_info");
        assertThat(prices.get(1).getCurrency()).hasValue("USD");
        assertThat(prices.get(1).getPrice()).hasValue(18.88);

        // License
        var licenses = item.getMediaLicenses();
        assertEquals(2, licenses.size());
        assertThat(licenses.get(0).getLicense()).isEqualTo("This work is licensed under a Creative Commons License");
        assertThat(licenses.get(0).getHref()).isEqualTo("http://creativecommons.org/licenses/by/4.0/");
        assertThat(licenses.get(0).getType()).isEqualTo("text/html");
        assertThat(licenses.get(1).getLicense()).isEqualTo("This work is licensed under a GNU General Public License");
        assertThat(licenses.get(1).getHref()).isEqualTo("https://www.gnu.org/licenses/gpl-3.0.en.html");
        assertThat(licenses.get(1).getType()).isEqualTo("text/html");

        // Subtitles
        var subtitles = item.getMediaSubTitles();
        assertEquals(2, subtitles.size());
        assertThat(subtitles.get(0).getType()).isEqualTo("application/smil");
        assertThat(subtitles.get(0).getLang()).isEqualTo("en-us");
        assertThat(subtitles.get(0).getHref()).isEqualTo("http://www.foo.org/subtitle.smil");
        assertThat(subtitles.get(1).getType()).isEqualTo("application/smil");
        assertThat(subtitles.get(1).getLang()).isEqualTo("fr-FR");
        assertThat(subtitles.get(1).getHref()).isEqualTo("http://www.foo.org/fr/subtitle.smil");

        // PeerLinks
        var peerLinks = item.getMediaPeerLinks();
        assertEquals(2, peerLinks.size());
        assertThat(peerLinks.get(0).getType()).isEqualTo("application/x-bittorrent");
        assertThat(peerLinks.get(0).getHref()).isEqualTo("http://www.foo.org/sampleFile480.torrent");
        assertThat(peerLinks.get(1).getType()).isEqualTo("application/x-bittorrent");
        assertThat(peerLinks.get(1).getHref()).isEqualTo("http://www.foo.org/sampleFile1080.torrent");

        // Rights
        var rights = item.getMediaRights().orElse(null);
        assertNotNull(rights);
        assertThat(rights.getStatus()).isEqualTo("userCreated");

        // Scenes
        var scenes = item.getMediaScenes();
        assertEquals(2, scenes.size());
        assertThat(scenes.get(0).getSceneTitle()).isEqualTo("sceneTitle1");
        assertThat(scenes.get(0).getSceneDescription()).isEqualTo("sceneDesc1");
        assertThat(scenes.get(0).getSceneStartTime()).isEqualTo("00:15");
        assertThat(scenes.get(0).getSceneEndTime()).isEqualTo("00:45");
        assertThat(scenes.get(1).getSceneTitle()).isEqualTo("sceneTitle2");
        assertThat(scenes.get(1).getSceneDescription()).isEqualTo("sceneDesc2");
        assertThat(scenes.get(1).getSceneStartTime()).isEqualTo("00:57");
        assertThat(scenes.get(1).getSceneEndTime()).isEqualTo("01:45");
    }

    @Test
    void mediaRssExample2() {
        var items = new MediaRssFeedReader().read(fromFile("module/mediarss/media-rss-example-2.xml"))
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
        assertThat(ratings.get(0).getScheme()).hasValue("urn:simple");
        assertEquals("nonadult", ratings.get(0).getRating());
        assertThat(ratings.get(1).getScheme()).hasValue("urn:mpaa");
        assertEquals("pg", ratings.get(1).getRating());

        // Title
        var title = content.get(0).getMediaTitle().orElse(null);
        assertNotNull(title);
        assertThat(title.getType()).hasValue("plain");
        assertEquals("The Judy's - The Moo Song", title.getTitle());

        // Description
        var description = content.get(0).getMediaDescription().orElse(null);
        assertNotNull(description);
        assertThat(description.getType()).hasValue("plain");
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
        assertThat(thumbnails.get(0).getWidth()).hasValue(720);
        assertThat(thumbnails.get(0).getHeight()).isEmpty();
        assertThat(thumbnails.get(0).getTime()).hasValue("50.10");
        assertThat(thumbnails.get(0).getTimeAsDuration()).isPresent();
        assertEquals("http://www.foo.com/keyframe.jpg", thumbnails.get(1).getUrl());
        assertThat(thumbnails.get(1).getWidth()).hasValue(75);
        assertThat(thumbnails.get(1).getHeight()).hasValue(50);
        assertThat(thumbnails.get(1).getTime()).hasValue("12:05:01.123");
        assertThat(thumbnails.get(1).getTimeAsDuration()).isPresent();

        // Category
        var categories = content.get(0).getMediaCategories();
        assertEquals(2, categories.size());
        assertEquals("music/artistname/album/song1", categories.get(0).getCategory());
        assertThat(categories.get(0).getSchema()).hasValue("http://blah.com/scheme");
        assertThat(categories.get(0).getLabel()).hasValue("music");
        assertEquals("music/artistname/album/song2", categories.get(1).getCategory());
        assertThat(categories.get(1).getSchema()).isEmpty();
        assertThat(categories.get(1).getLabel()).isEmpty();

        // Hash
        var hashes = content.get(0).getMediaHashes();
        assertEquals(2, hashes.size());
        assertEquals("dfdec888b72151965a34b4b59031290a", hashes.get(0).getHash());
        assertThat(hashes.get(0).getAlgorithm()).hasValue("md5");
        assertEquals("2fd4e1c67a2d28fced849ee1bb76e7391b93eb12", hashes.get(1).getHash());
        assertThat(hashes.get(1).getAlgorithm()).hasValue("sha-1");

        // Player
        var player = content.get(0).getMediaPlayer().orElse(null);
        assertNotNull(player);
        assertThat(player.getUrl()).isEqualTo("http://www.foo.com/player?id=1111");
        assertThat(player.getHeight()).hasValue(200);
        assertThat(player.getWidth()).hasValue(400);

        // Credits
        var credits = content.get(0).getMediaCredits();
        assertEquals(2, credits.size());
        assertThat(credits.get(0).getRole()).hasValue("producer");
        assertThat(credits.get(0).getScheme()).hasValue("urn:ebu");
        assertEquals("entity name", credits.get(0).getCredit());
        assertThat(credits.get(1).getRole()).hasValue("owner");
        assertThat(credits.get(1).getScheme()).hasValue("urn:yvs");
        assertEquals("copyright holder of the entity", credits.get(1).getCredit());

        // Copyright
        var copyright = content.get(0).getMediaCopyright().orElse(null);
        assertNotNull(copyright);
        assertThat(copyright.getUrl()).hasValue("https://creativecommons.org/licenses/by/4.0/");
        assertEquals("2005 FooBar Media", copyright.getCopyright());

        // Text
        var texts = content.get(0).getMediaTexts();
        assertEquals(2, texts.size());
        assertThat(texts.get(0).getType()).hasValue("plain");
        assertEquals("Oh, say, can you see, by the dawn's early light", texts.get(0).getText());
        assertThat(texts.get(0).getLang()).isEmpty();
        assertThat(texts.get(0).getStart()).isEmpty();
        assertThat(texts.get(0).getEnd()).isEmpty();
        assertThat(texts.get(1).getType()).hasValue("plain");
        assertEquals("By the dawn's early light", texts.get(1).getText());
        assertThat(texts.get(1).getLang()).hasValue("en");
        assertThat(texts.get(1).getStart()).hasValue("00:00:10.000");
        assertThat(texts.get(1).getEnd()).hasValue("00:00:17.000");

        // Restriction
        var restrictions = content.get(0).getMediaRestrictions();
        assertEquals(2, restrictions.size());
        assertThat(restrictions.get(0).getType()).isEqualTo("sharing");
        assertThat(restrictions.get(0).getRelationship()).isEqualTo("deny");
        assertThat(restrictions.get(0).getRestriction()).isNullOrEmpty();
        assertThat(restrictions.get(1).getType()).isEqualTo("country");
        assertThat(restrictions.get(1).getRelationship()).isEqualTo("allow");
        assertThat(restrictions.get(1).getRestriction()).isEqualTo("au us");

        // Community
        var community = content.get(0).getMediaCommunity().orElse(null);
        assertNotNull(community);
        var starRating = community.getMediaStarRating().orElse(null);
        assertNotNull(starRating);
        assertThat(starRating.getAverage()).isEqualTo(3.5);
        assertThat(starRating.getCount()).isEqualTo(20);
        assertThat(starRating.getMin()).isEqualTo(1);
        assertThat(starRating.getMax()).isEqualTo(10);
        var statistics = community.getMediaStatistics().orElse(null);
        assertNotNull(statistics);
        assertThat(statistics.getViews()).isEqualTo(5L);
        assertThat(statistics.getFavorites()).isEqualTo(4);
        var tags = community.getMediaTags().orElse(null);
        assertNotNull(tags);
        assertThat(tags.getTags()).isEqualTo("news:5, nbc, abc:3, reuters:b");
        var tagList = tags.getTagAsList();
        assertEquals(4, tagList.size());
        assertEquals("news:5", tagList.get(0).getTag());
        assertEquals("news", tagList.get(0).getName());
        assertThat(tagList.get(0).getWeight()).hasValue(5);
        assertEquals("nbc", tagList.get(1).getTag());
        assertEquals("nbc", tagList.get(1).getName());
        assertThat(tagList.get(1).getWeight()).isEmpty();
        assertEquals("abc:3", tagList.get(2).getTag());
        assertEquals("abc", tagList.get(2).getName());
        assertThat(tagList.get(2).getWeight()).hasValue(3);
        assertEquals("reuters:b", tagList.get(3).getTag());
        assertEquals("reuters", tagList.get(3).getName());
        assertThat(tagList.get(3).getWeight()).isEmpty();

        // Comments
        var comments = content.get(0).getMediaComments();
        assertEquals(2, comments.size());
        assertEquals("comment1", comments.get(0));
        assertEquals("comment2", comments.get(1));

        // Embed
        var embed = content.get(0).getMediaEmbed().orElse(null);
        assertNotNull(embed);
        assertThat(embed.getUrl()).isEqualTo("http://www.foo.com/player.swf");
        assertThat(embed.getHeight()).isEqualTo(323);
        assertThat(embed.getWidth()).isEqualTo(512);
        assertThat(embed.getParams()).hasSize(5);
        assertThat(embed.getParams()).containsEntry("type", "application/x-shockwave-flash");
        assertThat(embed.getParams()).containsEntry("width", "512");
        assertThat(embed.getParams()).containsEntry("height", "323");
        assertThat(embed.getParams()).containsEntry("allowFullScreen", "true");
        assertThat(embed.getParams()).containsEntry("flashVars", "id=12345&vid=678912i&lang=en-us&intl=us&thumbUrl=http://www.foo.com/thumbnail.jpg");

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
        assertThat(status.getReason()).hasValue("http://www.reasonforblocking.com");
        assertThat(status.getState()).isEqualTo("blocked");

        // Price
        var prices = content.get(0).getMediaPrices();
        assertEquals(2, prices.size());
        assertThat(prices.get(0).getType()).isEqualTo("rent");
        assertThat(prices.get(0).getCurrency()).hasValue("EUR");
        assertThat(prices.get(0).getPrice()).hasValue(19.99);
        assertThat(prices.get(1).getType()).isEqualTo("subscription");
        assertThat(prices.get(1).getInfo()).hasValue("http://www.dummy.jp/subscription_info");
        assertThat(prices.get(1).getCurrency()).hasValue("USD");
        assertThat(prices.get(1).getPrice()).hasValue(18.88);

        // License
        var licenses = content.get(0).getMediaLicenses();
        assertEquals(2, licenses.size());
        assertThat(licenses.get(0).getLicense()).isEqualTo("This work is licensed under a Creative Commons License");
        assertThat(licenses.get(0).getHref()).isEqualTo("http://creativecommons.org/licenses/by/4.0/");
        assertThat(licenses.get(0).getType()).isEqualTo("text/html");
        assertThat(licenses.get(1).getLicense()).isEqualTo("This work is licensed under a GNU General Public License");
        assertThat(licenses.get(1).getHref()).isEqualTo("https://www.gnu.org/licenses/gpl-3.0.en.html");
        assertThat(licenses.get(1).getType()).isEqualTo("text/html");

        // Subtitles
        var subtitles = content.get(0).getMediaSubTitles();
        assertEquals(2, subtitles.size());
        assertThat(subtitles.get(0).getType()).isEqualTo("application/smil");
        assertThat(subtitles.get(0).getLang()).isEqualTo("en-us");
        assertThat(subtitles.get(0).getHref()).isEqualTo("http://www.foo.org/subtitle.smil");
        assertThat(subtitles.get(1).getType()).isEqualTo("application/smil");
        assertThat(subtitles.get(1).getLang()).isEqualTo("fr-FR");
        assertThat(subtitles.get(1).getHref()).isEqualTo("http://www.foo.org/fr/subtitle.smil");

        // PeerLinks
        var peerLinks = content.get(0).getMediaPeerLinks();
        assertEquals(2, peerLinks.size());
        assertThat(peerLinks.get(0).getType()).isEqualTo("application/x-bittorrent");
        assertThat(peerLinks.get(0).getHref()).isEqualTo("http://www.foo.org/sampleFile480.torrent");
        assertThat(peerLinks.get(1).getType()).isEqualTo("application/x-bittorrent");
        assertThat(peerLinks.get(1).getHref()).isEqualTo("http://www.foo.org/sampleFile1080.torrent");

        // Rights
        var rights = content.get(0).getMediaRights().orElse(null);
        assertNotNull(rights);
        assertThat(rights.getStatus()).isEqualTo("official");

        // Scenes
        var scenes = content.get(0).getMediaScenes();
        assertEquals(2, scenes.size());
        assertThat(scenes.get(0).getSceneTitle()).isEqualTo("sceneTitle1");
        assertThat(scenes.get(0).getSceneDescription()).isEqualTo("sceneDesc1");
        assertThat(scenes.get(0).getSceneStartTime()).isEqualTo("00:15");
        assertThat(scenes.get(0).getSceneEndTime()).isEqualTo("00:45");
        assertThat(scenes.get(1).getSceneTitle()).isEqualTo("sceneTitle2");
        assertThat(scenes.get(1).getSceneDescription()).isEqualTo("sceneDesc2");
        assertThat(scenes.get(1).getSceneStartTime()).isEqualTo("00:57");
        assertThat(scenes.get(1).getSceneEndTime()).isEqualTo("01:45");

        // -- Second media content

        // Rating
        credits = content.get(1).getMediaCredits();
        assertEquals(1, credits.size());
        assertThat(credits.get(0).getCredit()).isEqualTo("member of band1");
        assertThat(credits.get(0).getRole()).hasValue("musician");
        assertThat(credits.get(0).getScheme()).isEmpty();

        // Category
        categories = content.get(1).getMediaCategories();
        assertEquals(1, categories.size());
        assertEquals("music/band1/album/song", categories.get(0).getCategory());
        assertThat(categories.get(0).getSchema()).isEmpty();
        assertThat(categories.get(0).getLabel()).isEmpty();

        // Rating
        ratings = content.get(1).getMediaRatings();
        assertEquals(1, ratings.size());
        assertEquals("nonadult", ratings.get(0).getRating());
        assertThat(ratings.get(0).getScheme()).isEmpty();

        // -- Third media content

        // Rating
        credits = content.get(2).getMediaCredits();
        assertEquals(1, credits.size());
        assertThat(credits.get(0).getCredit()).isEqualTo("member of band2");
        assertThat(credits.get(0).getRole()).hasValue("musician");
        assertThat(credits.get(0).getScheme()).isEmpty();

        // Category
        categories = content.get(1).getMediaCategories();
        assertEquals(1, categories.size());
        assertEquals("music/band1/album/song", categories.get(0).getCategory());
        assertThat(categories.get(0).getSchema()).isEmpty();
        assertThat(categories.get(0).getLabel()).isEmpty();

        // Rating
        ratings = content.get(1).getMediaRatings();
        assertEquals(1, ratings.size());
        assertEquals("nonadult", ratings.get(0).getRating());
        assertThat(ratings.get(0).getScheme()).isEmpty();
    }

    @Test
    void mediaRssExample3() {
        var items = new MediaRssFeedReader().read(fromFile("module/mediarss/media-rss-example-3.xml"))
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
        assertThat(content.getUrl()).hasValue("http://www.foo.com/song64kbps.mp3");
        assertThat(content.getFileSize()).hasValue(1000L);
        assertThat(content.getBitRate()).hasValue(64.0);
        assertThat(content.getType()).hasValue("audio/mpeg");
        assertThat(content.isDefault()).hasValue(true);
        assertThat(content.getExpression()).hasValue("full");

        // Second group content
        content = groupContent.get(1);
        assertThat(content.getUrl()).hasValue("http://www.foo.com/song128kbps.mp3");
        assertThat(content.getFileSize()).hasValue(2000L);
        assertThat(content.getBitRate()).hasValue(128.0);
        assertThat(content.getType()).hasValue("audio/mpeg");
        assertThat(content.isDefault()).isEmpty();
        assertThat(content.getExpression()).hasValue("full");

        // Rating
        var ratings = group.getMediaRatings();
        assertEquals(2, ratings.size());
        assertThat(ratings.get(0).getScheme()).hasValue("urn:simple");
        assertEquals("nonadult", ratings.get(0).getRating());
        assertThat(ratings.get(1).getScheme()).hasValue("urn:mpaa");
        assertEquals("pg", ratings.get(1).getRating());

        // Title
        var title = group.getMediaTitle().orElse(null);
        assertNotNull(title);
        assertThat(title.getType()).hasValue("plain");
        assertEquals("The Judy's - The Moo Song", title.getTitle());

        // Description
        var description = group.getMediaDescription().orElse(null);
        assertNotNull(description);
        assertThat(description.getType()).hasValue("plain");
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
        assertThat(thumbnails.get(0).getWidth()).hasValue(720);
        assertThat(thumbnails.get(0).getHeight()).isEmpty();
        assertThat(thumbnails.get(0).getTime()).hasValue("50.10");
        assertThat(thumbnails.get(0).getTimeAsDuration()).isPresent();
        assertEquals("http://www.foo.com/keyframe.jpg", thumbnails.get(1).getUrl());
        assertThat(thumbnails.get(1).getWidth()).hasValue(75);
        assertThat(thumbnails.get(1).getHeight()).hasValue(50);
        assertThat(thumbnails.get(1).getTime()).hasValue("12:05:01.123");
        assertThat(thumbnails.get(1).getTimeAsDuration()).isPresent();

        // Category
        var categories = group.getMediaCategories();
        assertEquals(2, categories.size());
        assertEquals("music/artistname/album/song1", categories.get(0).getCategory());
        assertThat(categories.get(0).getSchema()).hasValue("http://blah.com/scheme");
        assertThat(categories.get(0).getLabel()).hasValue("music");
        assertEquals("music/artistname/album/song2", categories.get(1).getCategory());
        assertThat(categories.get(1).getSchema()).isEmpty();
        assertThat(categories.get(1).getLabel()).isEmpty();

        // Hash
        var hashes = group.getMediaHashes();
        assertEquals(2, hashes.size());
        assertEquals("dfdec888b72151965a34b4b59031290a", hashes.get(0).getHash());
        assertThat(hashes.get(0).getAlgorithm()).hasValue("md5");
        assertEquals("2fd4e1c67a2d28fced849ee1bb76e7391b93eb12", hashes.get(1).getHash());
        assertThat(hashes.get(1).getAlgorithm()).hasValue("sha-1");

        // Player
        var player = group.getMediaPlayer().orElse(null);
        assertNotNull(player);
        assertThat(player.getUrl()).isEqualTo("http://www.foo.com/player?id=1111");
        assertThat(player.getHeight()).hasValue(200);
        assertThat(player.getWidth()).hasValue(400);

        // Credits
        var credits = group.getMediaCredits();
        assertEquals(2, credits.size());
        assertThat(credits.get(0).getRole()).hasValue("producer");
        assertThat(credits.get(0).getScheme()).hasValue("urn:ebu");
        assertEquals("entity name", credits.get(0).getCredit());
        assertThat(credits.get(1).getRole()).hasValue("owner");
        assertThat(credits.get(1).getScheme()).hasValue("urn:yvs");
        assertEquals("copyright holder of the entity", credits.get(1).getCredit());

        // Copyright
        var copyright = group.getMediaCopyright().orElse(null);
        assertNotNull(copyright);
        assertThat(copyright.getUrl()).hasValue("https://creativecommons.org/licenses/by/4.0/");
        assertEquals("2005 FooBar Media", copyright.getCopyright());

        // Text
        var texts = group.getMediaTexts();
        assertEquals(2, texts.size());
        assertThat(texts.get(0).getType()).hasValue("plain");
        assertEquals("Oh, say, can you see, by the dawn's early light", texts.get(0).getText());
        assertThat(texts.get(0).getLang()).isEmpty();
        assertThat(texts.get(0).getStart()).isEmpty();
        assertThat(texts.get(0).getEnd()).isEmpty();
        assertThat(texts.get(1).getType()).hasValue("plain");
        assertEquals("By the dawn's early light", texts.get(1).getText());
        assertThat(texts.get(1).getLang()).hasValue("en");
        assertThat(texts.get(1).getStart()).hasValue("00:00:10.000");
        assertThat(texts.get(1).getEnd()).hasValue("00:00:17.000");

        // Restriction
        var restrictions = group.getMediaRestrictions();
        assertEquals(2, restrictions.size());
        assertThat(restrictions.get(0).getType()).isEqualTo("sharing");
        assertThat(restrictions.get(0).getRelationship()).isEqualTo("deny");
        assertThat(restrictions.get(0).getRestriction()).isNullOrEmpty();
        assertThat(restrictions.get(1).getType()).isEqualTo("country");
        assertThat(restrictions.get(1).getRelationship()).isEqualTo("allow");
        assertThat(restrictions.get(1).getRestriction()).isEqualTo("au us");

        // Community
        var community = group.getMediaCommunity().orElse(null);
        assertNotNull(community);
        var starRating = community.getMediaStarRating().orElse(null);
        assertNotNull(starRating);
        assertThat(starRating.getAverage()).isEqualTo(3.5);
        assertThat(starRating.getCount()).isEqualTo(20);
        assertThat(starRating.getMin()).isEqualTo(1);
        assertThat(starRating.getMax()).isEqualTo(10);
        var statistics = community.getMediaStatistics().orElse(null);
        assertNotNull(statistics);
        assertThat(statistics.getViews()).isEqualTo(5L);
        assertThat(statistics.getFavorites()).isEqualTo(4);
        var tags = community.getMediaTags().orElse(null);
        assertNotNull(tags);
        assertThat(tags.getTags()).isEqualTo("news:5, nbc, abc:3, reuters:b");
        var tagList = tags.getTagAsList();
        assertEquals(4, tagList.size());
        assertEquals("news:5", tagList.get(0).getTag());
        assertEquals("news", tagList.get(0).getName());
        assertThat(tagList.get(0).getWeight()).hasValue(5);
        assertEquals("nbc", tagList.get(1).getTag());
        assertEquals("nbc", tagList.get(1).getName());
        assertThat(tagList.get(1).getWeight()).isEmpty();
        assertEquals("abc:3", tagList.get(2).getTag());
        assertEquals("abc", tagList.get(2).getName());
        assertThat(tagList.get(2).getWeight()).hasValue(3);
        assertEquals("reuters:b", tagList.get(3).getTag());
        assertEquals("reuters", tagList.get(3).getName());
        assertThat(tagList.get(3).getWeight()).isEmpty();

        // Comments
        var comments = group.getMediaComments();
        assertEquals(2, comments.size());
        assertEquals("comment1", comments.get(0));
        assertEquals("comment2", comments.get(1));

        // Embed
        var embed = group.getMediaEmbed().orElse(null);
        assertNotNull(embed);
        assertThat(embed.getUrl()).isEqualTo("http://www.foo.com/player.swf");
        assertThat(embed.getHeight()).isEqualTo(323);
        assertThat(embed.getWidth()).isEqualTo(512);
        assertThat(embed.getParams()).hasSize(5);
        assertThat(embed.getParams()).containsEntry("type", "application/x-shockwave-flash");
        assertThat(embed.getParams()).containsEntry("width", "512");
        assertThat(embed.getParams()).containsEntry("height", "323");
        assertThat(embed.getParams()).containsEntry("allowFullScreen", "true");
        assertThat(embed.getParams()).containsEntry("flashVars", "id=12345&vid=678912i&lang=en-us&intl=us&thumbUrl=http://www.foo.com/thumbnail.jpg");

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
        assertThat(status.getReason()).hasValue("http://www.reasonforblocking.com");
        assertThat(status.getState()).isEqualTo("blocked");

        // Price
        var prices = group.getMediaPrices();
        assertEquals(2, prices.size());
        assertThat(prices.get(0).getType()).isEqualTo("rent");
        assertThat(prices.get(0).getCurrency()).hasValue("EUR");
        assertThat(prices.get(0).getPrice()).hasValue(19.99);
        assertThat(prices.get(1).getType()).isEqualTo("subscription");
        assertThat(prices.get(1).getInfo()).hasValue("http://www.dummy.jp/subscription_info");
        assertThat(prices.get(1).getCurrency()).hasValue("USD");
        assertThat(prices.get(1).getPrice()).hasValue(18.88);

        // License
        var licenses = group.getMediaLicenses();
        assertEquals(2, licenses.size());
        assertThat(licenses.get(0).getLicense()).isEqualTo("This work is licensed under a Creative Commons License");
        assertThat(licenses.get(0).getHref()).isEqualTo("http://creativecommons.org/licenses/by/4.0/");
        assertThat(licenses.get(0).getType()).isEqualTo("text/html");
        assertThat(licenses.get(1).getLicense()).isEqualTo("This work is licensed under a GNU General Public License");
        assertThat(licenses.get(1).getHref()).isEqualTo("https://www.gnu.org/licenses/gpl-3.0.en.html");
        assertThat(licenses.get(1).getType()).isEqualTo("text/html");

        // Subtitles
        var subtitles = group.getMediaSubTitles();
        assertEquals(2, subtitles.size());
        assertThat(subtitles.get(0).getType()).isEqualTo("application/smil");
        assertThat(subtitles.get(0).getLang()).isEqualTo("en-us");
        assertThat(subtitles.get(0).getHref()).isEqualTo("http://www.foo.org/subtitle.smil");
        assertThat(subtitles.get(1).getType()).isEqualTo("application/smil");
        assertThat(subtitles.get(1).getLang()).isEqualTo("fr-FR");
        assertThat(subtitles.get(1).getHref()).isEqualTo("http://www.foo.org/fr/subtitle.smil");

        // PeerLinks
        var peerLinks = group.getMediaPeerLinks();
        assertEquals(2, peerLinks.size());
        assertThat(peerLinks.get(0).getType()).isEqualTo("application/x-bittorrent");
        assertThat(peerLinks.get(0).getHref()).isEqualTo("http://www.foo.org/sampleFile480.torrent");
        assertThat(peerLinks.get(1).getType()).isEqualTo("application/x-bittorrent");
        assertThat(peerLinks.get(1).getHref()).isEqualTo("http://www.foo.org/sampleFile1080.torrent");

        // Rights
        var rights = group.getMediaRights().orElse(null);
        assertNotNull(rights);
        assertThat(rights.getStatus()).isEqualTo("official");

        // Scenes
        var scenes = group.getMediaScenes();
        assertEquals(2, scenes.size());
        assertThat(scenes.get(0).getSceneTitle()).isEqualTo("sceneTitle1");
        assertThat(scenes.get(0).getSceneDescription()).isEqualTo("sceneDesc1");
        assertThat(scenes.get(0).getSceneStartTime()).isEqualTo("00:15");
        assertThat(scenes.get(0).getSceneEndTime()).isEqualTo("00:45");
        assertThat(scenes.get(1).getSceneTitle()).isEqualTo("sceneTitle2");
        assertThat(scenes.get(1).getSceneDescription()).isEqualTo("sceneDesc2");
        assertThat(scenes.get(1).getSceneStartTime()).isEqualTo("00:57");
        assertThat(scenes.get(1).getSceneEndTime()).isEqualTo("01:45");
    }

    @Test
    void mediaRssExample4() {
        var items = new MediaRssFeedReader().read(fromFile("module/mediarss/media-rss-example-4.xml"))
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
        assertThat(content.getUrl()).hasValue("http://www.foo.com/song64kbps.mp3");
        assertThat(content.getFileSize()).hasValue(1000L);
        assertThat(content.getBitRate()).hasValue(64.0);
        assertThat(content.getType()).hasValue("audio/mpeg");
        assertThat(content.isDefault()).hasValue(true);
        assertThat(content.getExpression()).hasValue("full");

        // Rating
        var ratings = content.getMediaRatings();
        assertEquals(2, ratings.size());
        assertThat(ratings.get(0).getScheme()).hasValue("urn:simple");
        assertEquals("nonadult", ratings.get(0).getRating());
        assertThat(ratings.get(1).getScheme()).hasValue("urn:mpaa");
        assertEquals("pg", ratings.get(1).getRating());

        // Title
        var title = content.getMediaTitle().orElse(null);
        assertNotNull(title);
        assertThat(title.getType()).hasValue("plain");
        assertEquals("The Judy's - The Moo Song", title.getTitle());

        // Description
        var description = content.getMediaDescription().orElse(null);
        assertNotNull(description);
        assertThat(description.getType()).hasValue("plain");
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
        assertThat(thumbnails.get(0).getWidth()).hasValue(720);
        assertThat(thumbnails.get(0).getHeight()).isEmpty();
        assertThat(thumbnails.get(0).getTime()).hasValue("50.10");
        assertThat(thumbnails.get(0).getTimeAsDuration()).isPresent();
        assertEquals("http://www.foo.com/keyframe.jpg", thumbnails.get(1).getUrl());
        assertThat(thumbnails.get(1).getWidth()).hasValue(75);
        assertThat(thumbnails.get(1).getHeight()).hasValue(50);
        assertThat(thumbnails.get(1).getTime()).hasValue("12:05:01.123");
        assertThat(thumbnails.get(1).getTimeAsDuration()).isPresent();

        // Category
        var categories = content.getMediaCategories();
        assertEquals(2, categories.size());
        assertEquals("music/artistname/album/song1", categories.get(0).getCategory());
        assertThat(categories.get(0).getSchema()).hasValue("http://blah.com/scheme");
        assertThat(categories.get(0).getLabel()).hasValue("music");
        assertEquals("music/artistname/album/song2", categories.get(1).getCategory());
        assertThat(categories.get(1).getSchema()).isEmpty();
        assertThat(categories.get(1).getLabel()).isEmpty();

        // Hash
        var hashes = content.getMediaHashes();
        assertEquals(2, hashes.size());
        assertEquals("dfdec888b72151965a34b4b59031290a", hashes.get(0).getHash());
        assertThat(hashes.get(0).getAlgorithm()).hasValue("md5");
        assertEquals("2fd4e1c67a2d28fced849ee1bb76e7391b93eb12", hashes.get(1).getHash());
        assertThat(hashes.get(1).getAlgorithm()).hasValue("sha-1");

        // Player
        var player = content.getMediaPlayer().orElse(null);
        assertNotNull(player);
        assertThat(player.getUrl()).isEqualTo("http://www.foo.com/player?id=1111");
        assertThat(player.getHeight()).hasValue(200);
        assertThat(player.getWidth()).hasValue(400);

        // Credits
        var credits = content.getMediaCredits();
        assertEquals(2, credits.size());
        assertThat(credits.get(0).getRole()).hasValue("producer");
        assertThat(credits.get(0).getScheme()).hasValue("urn:ebu");
        assertEquals("entity name", credits.get(0).getCredit());
        assertThat(credits.get(1).getRole()).hasValue("owner");
        assertThat(credits.get(1).getScheme()).hasValue("urn:yvs");
        assertEquals("copyright holder of the entity", credits.get(1).getCredit());

        // Copyright
        var copyright = content.getMediaCopyright().orElse(null);
        assertNotNull(copyright);
        assertThat(copyright.getUrl()).hasValue("https://creativecommons.org/licenses/by/4.0/");
        assertEquals("2005 FooBar Media", copyright.getCopyright());

        // Text
        var texts = content.getMediaTexts();
        assertEquals(2, texts.size());
        assertThat(texts.get(0).getType()).hasValue("plain");
        assertEquals("Oh, say, can you see, by the dawn's early light", texts.get(0).getText());
        assertThat(texts.get(0).getLang()).isEmpty();
        assertThat(texts.get(0).getStart()).isEmpty();
        assertThat(texts.get(0).getEnd()).isEmpty();
        assertThat(texts.get(1).getType()).hasValue("plain");
        assertEquals("By the dawn's early light", texts.get(1).getText());
        assertThat(texts.get(1).getLang()).hasValue("en");
        assertThat(texts.get(1).getStart()).hasValue("00:00:10.000");
        assertThat(texts.get(1).getEnd()).hasValue("00:00:17.000");

        // Restriction
        var restrictions = content.getMediaRestrictions();
        assertEquals(2, restrictions.size());
        assertThat(restrictions.get(0).getType()).isEqualTo("sharing");
        assertThat(restrictions.get(0).getRelationship()).isEqualTo("deny");
        assertThat(restrictions.get(0).getRestriction()).isNullOrEmpty();
        assertThat(restrictions.get(1).getType()).isEqualTo("country");
        assertThat(restrictions.get(1).getRelationship()).isEqualTo("allow");
        assertThat(restrictions.get(1).getRestriction()).isEqualTo("au us");

        // Community
        var community = content.getMediaCommunity().orElse(null);
        assertNotNull(community);
        var starRating = community.getMediaStarRating().orElse(null);
        assertNotNull(starRating);
        assertThat(starRating.getAverage()).isEqualTo(3.5);
        assertThat(starRating.getCount()).isEqualTo(20);
        assertThat(starRating.getMin()).isEqualTo(1);
        assertThat(starRating.getMax()).isEqualTo(10);
        var statistics = community.getMediaStatistics().orElse(null);
        assertNotNull(statistics);
        assertThat(statistics.getViews()).isEqualTo(5L);
        assertThat(statistics.getFavorites()).isEqualTo(4);
        var tags = community.getMediaTags().orElse(null);
        assertNotNull(tags);
        assertThat(tags.getTags()).isEqualTo("news:5, nbc, abc:3, reuters:b");
        var tagList = tags.getTagAsList();
        assertEquals(4, tagList.size());
        assertEquals("news:5", tagList.get(0).getTag());
        assertEquals("news", tagList.get(0).getName());
        assertThat(tagList.get(0).getWeight()).hasValue(5);
        assertEquals("nbc", tagList.get(1).getTag());
        assertEquals("nbc", tagList.get(1).getName());
        assertThat(tagList.get(1).getWeight()).isEmpty();
        assertEquals("abc:3", tagList.get(2).getTag());
        assertEquals("abc", tagList.get(2).getName());
        assertThat(tagList.get(2).getWeight()).hasValue(3);
        assertEquals("reuters:b", tagList.get(3).getTag());
        assertEquals("reuters", tagList.get(3).getName());
        assertThat(tagList.get(3).getWeight()).isEmpty();

        // Comments
        var comments = content.getMediaComments();
        assertEquals(2, comments.size());
        assertEquals("comment1", comments.get(0));
        assertEquals("comment2", comments.get(1));

        // Embed
        var embed = content.getMediaEmbed().orElse(null);
        assertNotNull(embed);
        assertThat(embed.getUrl()).isEqualTo("http://www.foo.com/player.swf");
        assertThat(embed.getHeight()).isEqualTo(323);
        assertThat(embed.getWidth()).isEqualTo(512);
        assertThat(embed.getParams()).hasSize(5);
        assertThat(embed.getParams()).containsEntry("type", "application/x-shockwave-flash");
        assertThat(embed.getParams()).containsEntry("width", "512");
        assertThat(embed.getParams()).containsEntry("height", "323");
        assertThat(embed.getParams()).containsEntry("allowFullScreen", "true");
        assertThat(embed.getParams()).containsEntry("flashVars", "id=12345&vid=678912i&lang=en-us&intl=us&thumbUrl=http://www.foo.com/thumbnail.jpg");

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
        assertThat(status.getReason()).hasValue("http://www.reasonforblocking.com");
        assertThat(status.getState()).isEqualTo("blocked");

        // Price
        var prices = content.getMediaPrices();
        assertEquals(2, prices.size());
        assertThat(prices.get(0).getType()).isEqualTo("rent");
        assertThat(prices.get(0).getCurrency()).hasValue("EUR");
        assertThat(prices.get(0).getPrice()).hasValue(19.99);
        assertThat(prices.get(1).getType()).isEqualTo("subscription");
        assertThat(prices.get(1).getInfo()).hasValue("http://www.dummy.jp/subscription_info");
        assertThat(prices.get(1).getCurrency()).hasValue("USD");
        assertThat(prices.get(1).getPrice()).hasValue(18.88);

        // License
        var licenses = content.getMediaLicenses();
        assertEquals(2, licenses.size());
        assertThat(licenses.get(0).getLicense()).isEqualTo("This work is licensed under a Creative Commons License");
        assertThat(licenses.get(0).getHref()).isEqualTo("http://creativecommons.org/licenses/by/4.0/");
        assertThat(licenses.get(0).getType()).isEqualTo("text/html");
        assertThat(licenses.get(1).getLicense()).isEqualTo("This work is licensed under a GNU General Public License");
        assertThat(licenses.get(1).getHref()).isEqualTo("https://www.gnu.org/licenses/gpl-3.0.en.html");
        assertThat(licenses.get(1).getType()).isEqualTo("text/html");

        // Subtitles
        var subtitles = content.getMediaSubTitles();
        assertEquals(2, subtitles.size());
        assertThat(subtitles.get(0).getType()).isEqualTo("application/smil");
        assertThat(subtitles.get(0).getLang()).isEqualTo("en-us");
        assertThat(subtitles.get(0).getHref()).isEqualTo("http://www.foo.org/subtitle.smil");
        assertThat(subtitles.get(1).getType()).isEqualTo("application/smil");
        assertThat(subtitles.get(1).getLang()).isEqualTo("fr-FR");
        assertThat(subtitles.get(1).getHref()).isEqualTo("http://www.foo.org/fr/subtitle.smil");

        // PeerLinks
        var peerLinks = content.getMediaPeerLinks();
        assertEquals(2, peerLinks.size());
        assertThat(peerLinks.get(0).getType()).isEqualTo("application/x-bittorrent");
        assertThat(peerLinks.get(0).getHref()).isEqualTo("http://www.foo.org/sampleFile480.torrent");
        assertThat(peerLinks.get(1).getType()).isEqualTo("application/x-bittorrent");
        assertThat(peerLinks.get(1).getHref()).isEqualTo("http://www.foo.org/sampleFile1080.torrent");

        // Rights
        var rights = content.getMediaRights().orElse(null);
        assertNotNull(rights);
        assertThat(rights.getStatus()).isEqualTo("official");

        // Scenes
        var scenes = content.getMediaScenes();
        assertEquals(2, scenes.size());
        assertThat(scenes.get(0).getSceneTitle()).isEqualTo("sceneTitle1");
        assertThat(scenes.get(0).getSceneDescription()).isEqualTo("sceneDesc1");
        assertThat(scenes.get(0).getSceneStartTime()).isEqualTo("00:15");
        assertThat(scenes.get(0).getSceneEndTime()).isEqualTo("00:45");
        assertThat(scenes.get(1).getSceneTitle()).isEqualTo("sceneTitle2");
        assertThat(scenes.get(1).getSceneDescription()).isEqualTo("sceneDesc2");
        assertThat(scenes.get(1).getSceneStartTime()).isEqualTo("00:57");
        assertThat(scenes.get(1).getSceneEndTime()).isEqualTo("01:45");

        // Second group content

        content = contents.get(1);
        assertThat(content.getUrl()).hasValue("http://www.foo.com/song128kbps.mp3");
        assertThat(content.getFileSize()).hasValue(2000L);
        assertThat(content.getBitRate()).hasValue(128.0);
        assertThat(content.getType()).hasValue("audio/mpeg");
        assertThat(content.isDefault()).isEmpty();
        assertThat(content.getExpression()).hasValue("full");

        // Group level

        title = group.getMediaTitle().orElse(null);
        assertNotNull(title);
        assertThat(title.getType()).hasValue("html");
        assertEquals("Group title", title.getTitle());
    }

    @Test
    void mediaRssExample5() {
        var items = new MediaRssFeedReader().read(fromFile("module/mediarss/media-rss-example-5.xml"))
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

    private void assertHasFeedItem(MediaRssItem item) {
        if (item instanceof FeedItem) {
            FeedItem feedItem = (FeedItem) item;
            assertFalse(feedItem.hasAtomItem());
            assertFalse(feedItem.hasDcItem());
            assertFalse(feedItem.hasGeoRssItem());
            assertFalse(feedItem.hasItunesItem());
            assertTrue(feedItem.hasMediaRssItem());
            assertFalse(feedItem.hasPodcastItem());
            assertFalse(feedItem.hasPscItem());
            assertFalse(feedItem.hasSlashItem());
            assertFalse(feedItem.hasWfwItem());
            assertFalse(feedItem.hasYoutubeItem());

            FeedChannel feedChannel = feedItem.getChannel();
            assertFalse(feedChannel.hasAtomChannel());
            assertFalse(feedChannel.hasDcChannel());
            assertFalse(feedChannel.hasGeoRssChannel());
            assertFalse(feedChannel.hasItunesChannel());
            assertFalse(feedChannel.hasMediaRssChannel());
            assertFalse(feedChannel.hasOpenSearchChannel());
            assertFalse(feedChannel.hasPodcastChannel());
            assertFalse(feedChannel.hasSpotifyChannel());
            assertFalse(feedChannel.hasYoutubeChannel());
        }
    }

    private static Stream<? extends Arguments> feedReaderArguments() {
        return Stream.of(
                Arguments.of(new MediaRssFeedReader()),
                Arguments.of(new FeedReader())
        );
    }

    private InputStream fromFile(String fileName) {
        return getClass().getClassLoader().getResourceAsStream(fileName);
    }
}
