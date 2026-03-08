package com.apptasticsoftware.rssreader.module.podcast;

import com.apptasticsoftware.rssreader.AbstractRssReader;
import com.apptasticsoftware.rssreader.FeedChannel;
import com.apptasticsoftware.rssreader.FeedItem;
import com.apptasticsoftware.rssreader.FeedReader;
import com.apptasticsoftware.rssreader.module.podcast.internal.PodcastChannelImpl;
import com.apptasticsoftware.rssreader.module.podcast.internal.PodcastItemImpl;
import com.apptasticsoftware.rssreader.util.Default;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.InputStream;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

class PodcastFeedReaderTest {

    @ParameterizedTest
    @MethodSource("feedReaderArguments")
    @SuppressWarnings({"java:S5961", "java:S5738"})
    void example1(AbstractRssReader<PodcastChannel, PodcastItem> feedReader) {
        var items = feedReader.read(fromFile("module/podcast/example1.xml"))
                .collect(Collectors.toList());

        assertEquals(4, items.size());

        var liveItem = items.get(0);
        assertTrue(liveItem.isPodcastLiveItem());
        assertThat(liveItem.getPodcastLiveItemStatus()).hasValue("live");
        assertThat(liveItem.getPodcastLiveItemStart()).hasValue("2021-09-26T07:30:00.000-0600");
        assertThat(liveItem.getPodcastLiveItemStartAsZonedDateTime()).hasValue(Default.getDateTimeParser().parse("2021-09-26T07:30:00.000-0600"));
        assertThat(liveItem.getPodcastLiveItemEnd()).hasValue("2021-09-26T09:30:00.000-0600");
        assertThat(liveItem.getPodcastLiveItemEndAsZonedDateTime()).hasValue(Default.getDateTimeParser().parse("2021-09-26T09:30:00.000-0600"));
        assertThat(liveItem.getTitle()).hasValue("Podcasting 2.0 Live Show");
        assertThat(liveItem.getDescription()).hasValue("A look into the future of podcasting and how we get to Podcasting 2.0!");
        assertThat(liveItem.getLink()).hasValue("https://example.com/podcast/live");
        assertThat(liveItem.getIsPermaLink()).hasValue(true);
        assertThat(liveItem.getGuid()).hasValue("https://example.com/live");
        assertThat(liveItem.getItunesImage()).hasValue("https://behindthesch3m3s.com/wp-content/uploads/2023/01/v4v-storm-good-quality.gif");
        assertThat(liveItem.getPodcastAlternateEnclosures()).hasSize(1);
        assertThat(liveItem.getPodcastAlternateEnclosures().get(0).getType()).isEqualTo("audio/mpeg");
        assertThat(liveItem.getPodcastAlternateEnclosures().get(0).getLength()).isEqualTo(312L);
        assertThat(liveItem.getPodcastAlternateEnclosures().get(0).isDefaults()).isTrue();
        assertThat(liveItem.getPodcastAlternateEnclosures().get(0).getLang()).isEqualTo("en");
        assertThat(liveItem.getPodcastAlternateEnclosures().get(0).getRel()).isEqualTo("default");
        assertThat(liveItem.getPodcastAlternateEnclosures().get(0).getCodecs()).isEqualTo("video/mp4");
        assertThat(liveItem.getPodcastAlternateEnclosures().get(0).getSources()).hasSize(2);
        assertThat(liveItem.getPodcastAlternateEnclosures().get(0).getSources().get(0).getUri()).isEqualTo("https://example.com/pc20/livestream");
        assertThat(liveItem.getPodcastAlternateEnclosures().get(0).getSources().get(1).getUri()).isEqualTo("https://example.com/file-1080.torrent");
        assertThat(liveItem.getPodcastAlternateEnclosures().get(0).getSources().get(1).getContentType()).hasValue("application/x-bittorrent");
        assertThat(liveItem.getEnclosures()).hasSize(1);
        assertThat(liveItem.getEnclosures().get(0).getUrl()).isEqualTo("https://example.com/pc20/livestream?format=.mp3");
        assertThat(liveItem.getEnclosures().get(0).getType()).isEqualTo("audio/mpeg");
        assertThat(liveItem.getEnclosures().get(0).getLength()).hasValue(312L);
        assertThat(liveItem.getPodcastContentLinks()).hasSize(2);
        assertThat(liveItem.getPodcastContentLinks().get(0).getContentLink()).isEqualTo("YouTube!");
        assertThat(liveItem.getPodcastContentLinks().get(0).getHref()).isEqualTo("https://youtube.com/pc20/livestream");
        assertThat(liveItem.getPodcastContentLinks().get(1).getContentLink()).isEqualTo("Twitch!");
        assertThat(liveItem.getPodcastContentLinks().get(1).getHref()).isEqualTo("https://twitch.com/pc20/livestream");
        assertTrue(liveItem.getPodcastChat().isPresent());
        assertThat(liveItem.getPodcastChat().get().getServer()).isEqualTo("jabber.example.com");
        assertThat(liveItem.getPodcastChat().get().getProtocol()).isEqualTo("xmpp");
        assertThat(liveItem.getPodcastChat().get().getAccountId()).isEqualTo("jsmith@jabber.example.org");
        assertThat(liveItem.getPodcastChat().get().getSpace()).hasValue("myawesomepodcast@jabber.example.org");

        for (PodcastItem podcastItem : items) {
            assertHasFeedItem(podcastItem, false);
            var channel = podcastItem.getChannel();
            assertThat(channel.getPodcastGuid()).isEqualTo("y0ur-gu1d-g035-h3r3");
            assertTrue(channel.getPodcastLicense().isPresent());
            assertThat(channel.getPodcastLicense().get().getUrl()).hasValue("https://example.org/mypodcastlicense/full.pdf");
            assertTrue(channel.getPodcastLocked().isPresent());
            assertThat(channel.getPodcastLocked().get().isLocked()).isTrue();
            assertThat(channel.getPodcastLocked().get().getOwner()).hasValue("podcastowner@example.com");
            assertThat(channel.getPodcastBlocks()).hasSize(3);
            assertThat(channel.getPodcastBlocks().get(0).isBlock()).isTrue();
            assertThat(channel.getPodcastBlocks().get(0).getId()).isEmpty();
            assertThat(channel.getPodcastBlocks().get(1).getId()).hasValue("google");
            assertThat(channel.getPodcastBlocks().get(1).isBlock()).isFalse();
            assertThat(channel.getPodcastBlocks().get(2).getId()).hasValue("amazon");
            assertThat(channel.getPodcastBlocks().get(2).isBlock()).isFalse();
            assertThat(channel.getPodcastFundings()).hasSize(1);
            assertThat(channel.getPodcastFundings().get(0).getFunding()).isEqualTo("Support the show!");
            assertThat(channel.getPodcastFundings().get(0).getUrl()).isEqualTo("https://example.com/donate");
            assertThat(channel.getPodcastLocations()).hasSize(1);
            assertThat(channel.getPodcastLocations().get(0).getLocation()).isEqualTo("Austin, TX");
            assertThat(channel.getPodcastLocations().get(0).getGeo()).hasValue("geo:30.2672,97.7431");
            assertThat(channel.getPodcastLocations().get(0).getOsm()).hasValue("R113314");
            assertThat(channel.getPodcastMedium()).isEqualTo("podcast");
            assertThat(channel.getItunesAuthor()).hasValue("John Doe");
            assertThat(channel.getItunesExplicit()).isFalse();
            assertThat(channel.getItunesType()).hasValue("episodic");
            assertThat(channel.getItunesCategories()).hasSize(2);
            assertThat(channel.getItunesCategories().get(0)).isEqualTo("News");
            assertThat(channel.getItunesCategories().get(1)).isEqualTo("Technology");
            assertTrue(channel.getItunesOwner().isPresent());
            assertThat(channel.getItunesOwner().get().getName()).hasValue("John Doe");
            assertThat(channel.getItunesOwner().get().getEmail()).isEqualTo("johndoe@example.com");
            assertThat(channel.getItunesImage()).isEqualTo("https://example.com/images/pci_avatar-massive.jpg");
            assertThat(channel.getPodcastValues()).hasSize(1);
            assertThat(channel.getPodcastValues().get(0).getType()).isEqualTo("lightning");
            assertThat(channel.getPodcastValues().get(0).getMethod()).isEqualTo("keysend");
            assertThat(channel.getPodcastValues().get(0).getSuggested()).hasValue(0.00000005000);
            assertThat(channel.getPodcastValues().get(0).getValueRecipients()).hasSize(2);
            assertThat(channel.getPodcastValues().get(0).getValueRecipients().get(0).getName()).isEqualTo("podcaster");
            assertThat(channel.getPodcastValues().get(0).getValueRecipients().get(0).getType()).isEqualTo("node");
            assertThat(channel.getPodcastValues().get(0).getValueRecipients().get(0).getAddress()).isEqualTo("036557ea56b3b86f08be31bcd2557cae8021b0e3a9413f0c0e52625c6696972e57");
            assertThat(channel.getPodcastValues().get(0).getValueRecipients().get(0).getSplit()).isEqualTo(99);
            assertThat(channel.getPodcastValues().get(0).getValueRecipients().get(0).getCustomKey()).hasValue("channelKey");
            assertThat(channel.getPodcastValues().get(0).getValueRecipients().get(0).getCustomValue()).hasValue("channelValue");
            assertThat(channel.getPodcastValues().get(0).getValueRecipients().get(0).isFee()).isTrue();
            assertThat(channel.getPodcastValues().get(0).getValueRecipients().get(1).getName()).isEqualTo("hosting company");
            assertThat(channel.getPodcastValues().get(0).getValueRecipients().get(1).getType()).isEqualTo("node");
            assertThat(channel.getPodcastValues().get(0).getValueRecipients().get(1).getAddress()).isEqualTo("036557ea56b3b86f08be31bcd2557cae8021b0e3a9413f0c0e52625c6696972e58");
            assertThat(channel.getPodcastValues().get(0).getValueRecipients().get(1).getSplit()).isEqualTo(1);
            assertThat(channel.getPodcastValues().get(0).getValueTimeSplits()).hasSize(2);
            assertThat(channel.getPodcastValues().get(0).getValueTimeSplits().get(0).getStartTime()).isEqualTo(60);
            assertThat(channel.getPodcastValues().get(0).getValueTimeSplits().get(0).getStartTimeAsDuration()).isEqualTo(Duration.ofSeconds(60));
            assertThat(channel.getPodcastValues().get(0).getValueTimeSplits().get(0).getDuration()).isEqualTo(237);
            assertThat(channel.getPodcastValues().get(0).getValueTimeSplits().get(0).getDurationAsDuration()).isEqualTo(Duration.ofSeconds(237));
            assertThat(channel.getPodcastValues().get(0).getValueTimeSplits().get(0).getRemoteStartTime()).hasValue(174);
            assertThat(channel.getPodcastValues().get(0).getValueTimeSplits().get(0).getRemoteStartTimeAsDuration()).hasValue(Duration.ofSeconds(174));
            assertThat(channel.getPodcastValues().get(0).getValueTimeSplits().get(0).getRemotePercentage()).hasValue(95);
            assertTrue(channel.getPodcastValues().get(0).getValueTimeSplits().get(0).getRemoteItem().isPresent());
            assertThat(channel.getPodcastValues().get(0).getValueTimeSplits().get(0).getRemoteItem().get().getItemGuid()).hasValue("asdf089j0-ep240-20230510");
            assertThat(channel.getPodcastValues().get(0).getValueTimeSplits().get(0).getRemoteItem().get().getFeedGuid()).isEqualTo("917393e3-1b1e-5cef-ace4-edaa54e1f810");
            assertThat(channel.getPodcastValues().get(0).getValueTimeSplits().get(0).getRemoteItem().get().getFeedUrl()).hasValue("https://feeds.example.org/917393e3-1b1e-5cef-ace4-edaa54e1f810/rss.xml");
            assertThat(channel.getPodcastValues().get(0).getValueTimeSplits().get(0).getRemoteItem().get().getMedium()).hasValue("music");
            assertThat(channel.getPodcastValues().get(0).getValueTimeSplits().get(0).getRemoteItem().get().getTitle()).hasValue("Here Comes the Sun");
            assertThat(channel.getPodcastValues().get(0).getValueTimeSplits().get(1).getStartTime()).isEqualTo(367);
            assertThat(channel.getPodcastValues().get(0).getValueTimeSplits().get(1).getDuration()).isEqualTo(246);
            assertThat(channel.getPodcastValues().get(0).getValueTimeSplits().get(1).getValueRecipients()).hasSize(2);
            assertThat(channel.getPodcastValues().get(0).getValueTimeSplits().get(1).getValueRecipients().get(0).getName()).isEqualTo("Alice (Podcaster)");
            assertThat(channel.getPodcastValues().get(0).getValueTimeSplits().get(1).getValueRecipients().get(0).getType()).isEqualTo("node");
            assertThat(channel.getPodcastValues().get(0).getValueTimeSplits().get(1).getValueRecipients().get(0).getAddress()).isEqualTo("02d5c1bf8b940dc9cadca86d1b0a3c37fbe39cee4c7e839e33bef9174531d27f52");
            assertThat(channel.getPodcastValues().get(0).getValueTimeSplits().get(1).getValueRecipients().get(0).getSplit()).isEqualTo(85);
            assertThat(channel.getPodcastValues().get(0).getValueTimeSplits().get(1).getValueRecipients().get(1).getName()).isEqualTo("Bobjim (Guest)");
            assertThat(channel.getPodcastValues().get(0).getValueTimeSplits().get(1).getValueRecipients().get(1).getType()).isEqualTo("node");
            assertThat(channel.getPodcastValues().get(0).getValueTimeSplits().get(1).getValueRecipients().get(1).getAddress()).isEqualTo("032f4ffbbafffbe51726ad3c164a3d0d37ec27bc67b29a159b0f49ae8ac21b8508");
            assertThat(channel.getPodcastValues().get(0).getValueTimeSplits().get(1).getValueRecipients().get(1).getSplit()).isEqualTo(10);
            assertThat(channel.getPodcastTrailers()).hasSize(1);
            assertThat(channel.getPodcastTrailers().get(0).getTrailer()).isEqualTo("Coming April 1st, 2021");
            assertThat(channel.getPodcastTrailers().get(0).getPubDate()).isEqualTo("Thu, 01 Apr 2021 08:00:00 EST");
            assertThat(channel.getPodcastTrailers().get(0).getPubDateAsZonedDateTime()).isEqualTo(Default.getDateTimeParser().parse("Thu, 01 Apr 2021 08:00:00 EST"));
            assertThat(channel.getPodcastTrailers().get(0).getUrl()).isEqualTo("https://example.org/trailers/teaser");
            assertThat(channel.getPodcastTrailers().get(0).getLength()).isEqualTo(12345678L);
            assertThat(channel.getPodcastTrailers().get(0).getType()).isEqualTo("audio/mp3");
            assertThat(channel.getPodcastTrailers().get(0).getSeason()).hasValue(2);
            assertTrue(channel.getPodcastChat().isPresent());
            assertThat(channel.getPodcastChat().get().getServer()).isEqualTo("irc.zeronode.net");
            assertThat(channel.getPodcastChat().get().getProtocol()).isEqualTo("irc");
            assertThat(channel.getPodcastChat().get().getAccountId()).isEqualTo("@jsmith");
            assertThat(channel.getPodcastChat().get().getSpace()).hasValue("#myawesomepodcast");
            assertThat(channel.getPodcastTxts()).hasSize(3);
            assertThat(channel.getPodcastTxts().get(0).getTxt()).isEqualTo("naj3eEZaWVVY9a38uhX8FekACyhtqP4JO");
            assertThat(channel.getPodcastTxts().get(0).getPurpose()).isEmpty();
            assertThat(channel.getPodcastTxts().get(1).getTxt()).isEqualTo("S6lpp-7ZCn8-dZfGc-OoyaH");
            assertThat(channel.getPodcastTxts().get(1).getPurpose()).hasValue("verify");
            assertThat(channel.getPodcastTxts().get(2).getTxt()).isEqualTo("05125");
            assertThat(channel.getPodcastTxts().get(2).getPurpose()).hasValue("applepodcastsverify");
            assertThat(channel.getPodcastImages()).hasSize(4);
            assertThat(channel.getPodcastImages().get(0).getHref()).isEqualTo("https://example.com/images/channel/pci_avatar-massive.jpg");
            assertThat(channel.getPodcastImages().get(0).getWidth()).hasValue(1500);
            assertThat(channel.getPodcastImages().get(1).getHref()).isEqualTo("https://example.com/images/channel/pci_avatar-middle.jpg");
            assertThat(channel.getPodcastImages().get(1).getWidth()).hasValue(600);
            assertThat(channel.getPodcastImages().get(2).getHref()).isEqualTo("https://example.com/images/channel/pci_avatar-small.jpg");
            assertThat(channel.getPodcastImages().get(2).getWidth()).hasValue(300);
            assertThat(channel.getPodcastImages().get(3).getHref()).isEqualTo("https://example.com/images/channel/pci_avatar-tiny.jpg");
            assertThat(channel.getPodcastImages().get(3).getWidth()).hasValue(150);
            assertThat(channel.isPodcastUsingPodping()).isEmpty();
        }

        var item = items.get(1);
        assertThat(item.getTitle()).hasValue("Episode 3 - The Future");
        assertThat(item.getDescription()).hasValue("<p>A look into the future of podcasting and how we get to Podcasting 2.0!</p>");
        assertThat(item.getLink()).hasValue("https://example.com/podcast/ep0003");
        assertThat(item.getGuid()).hasValue("https://example.com/ep0003");
        assertThat(item.getIsPermaLink()).hasValue(true);
        assertThat(item.getPubDate()).hasValue("Fri, 09 Oct 2020 04:30:38 GMT");
        assertThat(item.getPubDateAsZonedDateTime()).hasValue(Default.getDateTimeParser().parse("Fri, 09 Oct 2020 04:30:38 GMT"));
        assertThat(item.getPubDateZonedDateTime()).hasValue(Default.getDateTimeParser().parse("Fri, 09 Oct 2020 04:30:38 GMT"));
        assertThat(item.getAuthor()).hasValue("John Doe (john@example.com)");
        assertThat(item.getItunesImage()).hasValue("https://example.com/ep0003/artMd.jpg");
        assertThat(item.getPodcastImages()).hasSize(4);
        assertThat(item.getPodcastImages().get(0).getHref()).isEqualTo("https://example.com/images/ep3/pci_avatar-massive.jpg");
        assertThat(item.getPodcastImages().get(0).getWidth()).hasValue(1500);
        assertThat(item.getPodcastImages().get(1).getHref()).isEqualTo("https://example.com/images/ep3/pci_avatar-middle.jpg");
        assertThat(item.getPodcastImages().get(1).getWidth()).hasValue(600);
        assertThat(item.getPodcastImages().get(2).getHref()).isEqualTo("https://example.com/images/ep3/pci_avatar-small.jpg");
        assertThat(item.getPodcastImages().get(2).getWidth()).hasValue(300);
        assertThat(item.getPodcastImages().get(3).getHref()).isEqualTo("https://example.com/images/ep3/pci_avatar-tiny.jpg");
        assertThat(item.getPodcastImages().get(3).getWidth()).hasValue(150);
        assertThat(item.isItunesExplicit()).isFalse();
        assertTrue(item.getPodcastSeason().isPresent());
        assertThat(item.getPodcastSeason().get().getSeason()).isEqualTo(1);
        assertThat(item.getPodcastSeason().get().getName()).hasValue("Podcasting 2.0");
        assertTrue(item.getPodcastEpisode().isPresent());
        assertThat(item.getPodcastEpisode().get().getEpisode()).isEqualTo(1.3d);
        assertThat(item.getPodcastEpisode().get().getDisplay()).hasValue("S1E3");
        assertTrue(item.getPodcastChapters().isPresent());
        assertThat(item.getPodcastChapters().get().getUrl()).isEqualTo("https://example.com/ep3_chapters.json");
        assertThat(item.getPodcastChapters().get().getType()).isEqualTo("application/json");
        assertThat(item.getPodcastSoundbites()).hasSize(1);
        assertThat(item.getPodcastSoundbites().get(0).getStartTime()).isEqualTo(33.833);
        assertThat(item.getPodcastSoundbites().get(0).getStartTimeAsDuration()).isEqualTo(Duration.ofMillis(33833));
        assertThat(item.getPodcastSoundbites().get(0).getDuration()).isEqualTo(42.25);
        assertThat(item.getPodcastSoundbites().get(0).getDurationAsDuration()).isEqualTo(Duration.ofMillis(42250));
        assertThat(item.getPodcastSoundbites().get(0).getSoundbite()).hasValue("Why the Podcast Namespace Matters");
        assertThat(item.getPodcastTranscripts()).hasSize(2);
        assertThat(item.getPodcastTranscripts().get(0).getUrl()).isEqualTo("https://example.com/ep3/transcript.txt");
        assertThat(item.getPodcastTranscripts().get(0).getType()).isEqualTo("text/plain");
        assertThat(item.getPodcastTranscripts().get(1).getUrl()).isEqualTo("https://example.com/episode1/transcript.json");
        assertThat(item.getPodcastTranscripts().get(1).getType()).isEqualTo("application/json");
        assertThat(item.getPodcastTranscripts().get(1).getLanguage()).hasValue("es");
        assertThat(item.getPodcastTranscripts().get(1).getRel()).hasValue("captions");
        assertThat(item.getPodcastPersons()).hasSize(3);
        assertThat(item.getPodcastPersons().get(0).getPerson()).isEqualTo("Adam Curry");
        assertThat(item.getPodcastPersons().get(0).getHref()).hasValue("https://www.podchaser.com/creators/adam-curry-107ZzmWE5f");
        assertThat(item.getPodcastPersons().get(0).getImg()).hasValue("https://example.com/images/adamcurry.jpg");
        assertThat(item.getPodcastPersons().get(1).getPerson()).isEqualTo("Dave Jones");
        assertThat(item.getPodcastPersons().get(1).getRole()).hasValue("guest");
        assertThat(item.getPodcastPersons().get(1).getHref()).hasValue("https://github.com/daveajones/");
        assertThat(item.getPodcastPersons().get(1).getImg()).hasValue("https://example.com/images/davejones.jpg");
        assertThat(item.getPodcastPersons().get(2).getPerson()).isEqualTo("Becky Smith");
        assertThat(item.getPodcastPersons().get(2).getGroup()).hasValue("visuals");
        assertThat(item.getPodcastPersons().get(2).getRole()).hasValue("cover art designer");
        assertThat(item.getPodcastPersons().get(2).getHref()).hasValue("https://example.com/artist/beckysmith");
        assertThat(item.getEnclosures()).hasSize(1);
        assertThat(item.getEnclosures().get(0).getUrl()).isEqualTo("https://example.com/file-03.mp3");
        assertThat(item.getEnclosures().get(0).getLength()).hasValue(43200000L);
        assertThat(item.getEnclosures().get(0).getType()).isEqualTo("audio/mpeg");
        assertThat(item.getPodcastAlternateEnclosures()).hasSize(5);
        assertThat(item.getPodcastAlternateEnclosures().get(0).getType()).isEqualTo("audio/mpeg");
        assertThat(item.getPodcastAlternateEnclosures().get(0).getLength()).isEqualTo(43200000L);
        assertThat(item.getPodcastAlternateEnclosures().get(0).getBitrate()).isEqualTo(128000d);
        assertThat(item.getPodcastAlternateEnclosures().get(0).isDefaults()).isTrue();
        assertThat(item.getPodcastAlternateEnclosures().get(0).getTitle()).isEqualTo("Standard");
        assertThat(item.getPodcastAlternateEnclosures().get(0).getSources()).hasSize(2);
        assertThat(item.getPodcastAlternateEnclosures().get(0).getSources().get(0).getUri()).isEqualTo("https://example.com/file-03.mp3");
        assertThat(item.getPodcastAlternateEnclosures().get(0).getSources().get(1).getUri()).isEqualTo("https://example.com/file-720.torrent");
        assertThat(item.getPodcastAlternateEnclosures().get(0).getSources().get(1).getContentType()).hasValue("application/x-bittorrent");
        assertThat(item.getPodcastAlternateEnclosures().get(4).getType()).isEqualTo("video/mp4");
        assertThat(item.getPodcastAlternateEnclosures().get(4).getLength()).isEqualTo(7924786L);
        assertThat(item.getPodcastAlternateEnclosures().get(4).getBitrate()).isEqualTo(511276.52d);
        assertThat(item.getPodcastAlternateEnclosures().get(4).getHeight()).isEqualTo(720);
        assertThat(item.getPodcastAlternateEnclosures().get(4).getTitle()).isEqualTo("Video version");
        assertThat(item.getPodcastAlternateEnclosures().get(4).getSources()).hasSize(2);
        assertThat(item.getPodcastAlternateEnclosures().get(4).getSources().get(0).getUri()).isEqualTo("https://example.com/file-720.mp4");
        assertThat(item.getPodcastAlternateEnclosures().get(4).getSources().get(1).getUri()).isEqualTo("ipfs://QmX33FYehk6ckGQ6g1D9D3FqZPix5JpKstKQKbaS8quUFb");
        assertTrue(item.getPodcastAlternateEnclosures().get(4).getIntegrity().isPresent());
        assertThat(item.getPodcastAlternateEnclosures().get(4).getIntegrity().get().getType()).isEqualTo("sri");
        assertThat(item.getPodcastAlternateEnclosures().get(4).getIntegrity().get().getValue()).isEqualTo("sha384-ExVqijgYHm15PqQqdXfW95x+Rs6C+d6E/ICxyQOeFevnxNLR/wtJNrNYTjIysUBo");
        assertThat(item.getPodcastValues()).hasSize(1);
        assertThat(item.getPodcastValues().get(0).getType()).isEqualTo("lightning");
        assertThat(item.getPodcastValues().get(0).getMethod()).isEqualTo("keysend");
        assertThat(item.getPodcastValues().get(0).getSuggested()).hasValue(0.00000005000d);
        assertThat(item.getPodcastValues().get(0).getValueRecipients()).hasSize(3);
        assertThat(item.getPodcastValues().get(0).getValueRecipients().get(0).getName()).isEqualTo("podcaster");
        assertThat(item.getPodcastValues().get(0).getValueRecipients().get(0).getType()).isEqualTo("node");
        assertThat(item.getPodcastValues().get(0).getValueRecipients().get(0).getAddress()).isEqualTo("036557ea56b3b86f08be31bcd2557cae8021b0e3a9413f0c0e52625c6696972e57");
        assertThat(item.getPodcastValues().get(0).getValueRecipients().get(0).getSplit()).isEqualTo(49);
        assertThat(item.getPodcastValues().get(0).getValueRecipients().get(0).getCustomKey()).hasValue("itemKey");
        assertThat(item.getPodcastValues().get(0).getValueRecipients().get(0).getCustomValue()).hasValue("itemValue");
        assertThat(item.getPodcastValues().get(0).getValueRecipients().get(0).isFee()).isTrue();
        assertThat(item.getPodcastValues().get(0).getValueRecipients().get(2).getName()).isEqualTo("Gigi (Guest)");
        assertThat(item.getPodcastValues().get(0).getValueRecipients().get(2).getType()).isEqualTo("node");
        assertThat(item.getPodcastValues().get(0).getValueRecipients().get(2).getAddress()).isEqualTo("02e12fea95f576a680ec1938b7ed98ef0855eadeced493566877d404e404bfbf52");
        assertThat(item.getPodcastValues().get(0).getValueRecipients().get(2).getSplit()).isEqualTo(50);
        assertThat(item.getPodcastValues().get(0).getValueTimeSplits()).hasSize(2);
        assertThat(item.getPodcastValues().get(0).getValueTimeSplits().get(0).getStartTime()).isEqualTo(60);
        assertThat(item.getPodcastValues().get(0).getValueTimeSplits().get(0).getDuration()).isEqualTo(237);
        assertThat(item.getPodcastValues().get(0).getValueTimeSplits().get(0).getRemoteStartTime()).hasValue(175);
        assertThat(item.getPodcastValues().get(0).getValueTimeSplits().get(0).getRemotePercentage()).hasValue(96);
        assertTrue(item.getPodcastValues().get(0).getValueTimeSplits().get(0).getRemoteItem().isPresent());
        assertThat(item.getPodcastValues().get(0).getValueTimeSplits().get(0).getRemoteItem().get().getItemGuid()).hasValue("https://podcastindex.org/podcast/4148683#1");
        assertThat(item.getPodcastValues().get(0).getValueTimeSplits().get(0).getRemoteItem().get().getFeedGuid()).isEqualTo("a94f5cc9-8c58-55fc-91fe-a324087a655b");
        assertThat(item.getPodcastValues().get(0).getValueTimeSplits().get(0).getRemoteItem().get().getMedium()).hasValue("music");
        assertThat(item.getPodcastValues().get(0).getValueTimeSplits().get(1).getStartTime()).isEqualTo(367);
        assertThat(item.getPodcastValues().get(0).getValueTimeSplits().get(1).getDuration()).isEqualTo(246);
        assertThat(item.getPodcastValues().get(0).getValueTimeSplits().get(1).getValueRecipients()).hasSize(2);
        assertThat(item.getPodcastValues().get(0).getValueTimeSplits().get(1).getValueRecipients().get(0).getName()).isEqualTo("Alice (Podcaster)");
        assertThat(item.getPodcastValues().get(0).getValueTimeSplits().get(1).getValueRecipients().get(0).getType()).isEqualTo("node");
        assertThat(item.getPodcastValues().get(0).getValueTimeSplits().get(1).getValueRecipients().get(0).getAddress()).isEqualTo("02d5c1bf8b940dc9cadca86d1b0a3c37fbe39cee4c7e839e33bef9174531d27f52");
        assertThat(item.getPodcastValues().get(0).getValueTimeSplits().get(1).getValueRecipients().get(0).getSplit()).isEqualTo(85);
        assertThat(item.getPodcastValues().get(0).getValueTimeSplits().get(1).getValueRecipients().get(1).getName()).isEqualTo("Bobjim (Guest)");
        assertThat(item.getPodcastValues().get(0).getValueTimeSplits().get(1).getValueRecipients().get(1).getType()).isEqualTo("node");
        assertThat(item.getPodcastValues().get(0).getValueTimeSplits().get(1).getValueRecipients().get(1).getAddress()).isEqualTo("032f4ffbbafffbe51726ad3c164a3d0d37ec27bc67b29a159b0f49ae8ac21b8508");
        assertThat(item.getPodcastValues().get(0).getValueTimeSplits().get(1).getValueRecipients().get(1).getSplit()).isEqualTo(10);
        assertThat(item.getPodcastValues().get(0).getValueTimeSplits().get(1).getStartTime()).isEqualTo(367);
        assertThat(item.getPodcastValues().get(0).getValueTimeSplits().get(1).getDuration()).isEqualTo(246);
        assertThat(item.getPodcastValues().get(0).getValueTimeSplits().get(1).getRemotePercentage()).isEmpty();
        assertThat(item.getPodcastSocialInteracts()).hasSize(2);
        assertThat(item.getPodcastSocialInteracts().get(0).getPriority()).hasValue(1);
        assertThat(item.getPodcastSocialInteracts().get(0).getUri()).isEqualTo("https://podcastindex.social/web/@dave/108013847520053258");
        assertThat(item.getPodcastSocialInteracts().get(0).getProtocol()).isEqualTo("activitypub");
        assertThat(item.getPodcastSocialInteracts().get(0).getAccountId()).hasValue("@dave");
        assertThat(item.getPodcastSocialInteracts().get(0).getAccountUrl()).hasValue("https://podcastindex.social/web/@dave");
        assertThat(item.getPodcastSocialInteracts().get(1).getPriority()).hasValue(2);
        assertThat(item.getPodcastSocialInteracts().get(1).getUri()).isEqualTo("https://twitter.com/PodcastindexOrg/status/1507120226361647115");
        assertThat(item.getPodcastSocialInteracts().get(1).getProtocol()).isEqualTo("twitter");
        assertThat(item.getPodcastSocialInteracts().get(1).getAccountId()).hasValue("@podcastindexorg");
        assertThat(item.getPodcastSocialInteracts().get(1).getAccountUrl()).hasValue("https://twitter.com/PodcastindexOrg");
        assertThat(item.getPodcastTxts()).hasSize(4);
        assertThat(item.getPodcastTxts().get(0).getTxt()).isEqualTo("naj3eEZaWVVY9a38uhX8FekACyhtqP4JN");
        assertThat(item.getPodcastTxts().get(0).getPurpose()).isEmpty();
        assertThat(item.getPodcastTxts().get(1).getTxt()).isEqualTo("S6lpp-7ZCn8-dZfGc-OoyaG");
        assertThat(item.getPodcastTxts().get(1).getPurpose()).hasValue("verify");
        assertThat(item.getPodcastTxts().get(2).getTxt()).isEqualTo("05124");
        assertThat(item.getPodcastTxts().get(2).getPurpose()).hasValue("applepodcastsverify");
        assertThat(item.getPodcastTxts().get(3).getTxt()).isEqualTo("2022-10-26T04:45:30.742Z");
        assertThat(item.getPodcastTxts().get(3).getPurpose()).hasValue("release");
    }

    @ParameterizedTest
    @MethodSource("feedReaderArguments")
    @SuppressWarnings({"java:S5961", "java:S5738"})
    void example2(AbstractRssReader<PodcastChannel, PodcastItem> feedReader) {
        var items = feedReader.read(fromFile("module/podcast/example2.xml"))
                .collect(Collectors.toList());

        assertEquals(3, items.size());
        var item = items.get(0);
        assertHasFeedItem(item, true);
        var channel = item.getChannel();
        assertThat(channel.getLink()).isEqualTo("https://podnews.net");
        assertThat(channel.getTitle()).isEqualTo("Podnews Daily - podcast industry news");
        assertThat(channel.getDescription()).isEqualTo("Daily news for the podcast and on-demand audio industry - from Apple Podcasts to Spotify, YouTube\n" +
                "            Music to Joe Rogan. Podnews also covers the latest jobs and events and trending shows in a short update\n" +
                "            every weekday. editor@podnews.net - visit https://podnews.net to get our free newsletter.");
        assertThat(channel.getLanguage()).hasValue("en-gb");
        assertThat(channel.getGenerator()).hasValue("Podnews LLC");
        assertThat(channel.getItunesNewFeedUrl()).hasValue("https://podnews.net/rss");
        assertThat(channel.getLastBuildDate()).hasValue("Sat, 27 Sep 2025 11:00:18 +0000");
        assertThat(channel.getLastBuildDateAsZonedDateTime()).hasValue(Default.getDateTimeParser().parse("Sat, 27 Sep 2025 11:00:18 +0000"));
        assertThat(channel.getLastBuildDateZonedDateTime()).hasValue(Default.getDateTimeParser().parse("Sat, 27 Sep 2025 11:00:18 +0000"));
        assertThat(channel.getPodcastLocations()).hasSize(1);
        assertThat(channel.getPodcastLocations().get(0).getLocation()).isEqualTo("Brisbane, Australia");
        assertThat(channel.getPodcastLocations().get(0).getGeo()).hasValue("geo:-27.4679,153.028");
        assertThat(channel.getPodcastLocations().get(0).getOsm()).hasValue("R11677792");
        assertThat(channel.getPodcastLocations().get(0).getCountry()).hasValue("au");
        assertThat(channel.getPodcastLocations().get(0).getRel()).hasValue("creator");
        assertThat(channel.getPubDate()).hasValue("Fri, 26 Sep 2025 09:19:05 +0000");
        assertThat(channel.getPubDateAsZonedDateTime()).hasValue(Default.getDateTimeParser().parse("Fri, 26 Sep 2025 09:19:05 +0000"));
        assertThat(channel.getPubDateZonedDateTime()).hasValue(Default.getDateTimeParser().parse("Fri, 26 Sep 2025 09:19:05 +0000"));
        assertThat(channel.getSkipDays()).isEqualTo(List.of("Saturday", "Sunday"));
        assertThat(channel.getSkipHours()).isEqualTo(List.of(0, 1, 2, 3, 4, 5, 6, 15, 16, 17, 18, 19, 20, 21, 22, 23));
        assertThat(channel.getPodcastGuid()).isEqualTo("9b024349-ccf0-5f69-a609-6b82873eab3c");
        assertThat(channel.getPodcastFundings()).hasSize(1);
        assertThat(channel.getPodcastFundings().get(0).getFunding()).isEqualTo("Support us");
        assertThat(channel.getPodcastFundings().get(0).getUrl()).isEqualTo("https://buy.stripe.com/4gw8zIcpc58CcGQ6ot");
        assertThat(channel.getPodcastValues()).hasSize(1);
        assertThat(channel.getPodcastValues().get(0).getType()).isEqualTo("lightning");
        assertThat(channel.getPodcastValues().get(0).getMethod()).isEqualTo("keysend");
        assertThat(channel.getPodcastValues().get(0).getSuggested()).hasValue(0.00000100000);
        assertThat(channel.getPodcastValues().get(0).getValueRecipients()).hasSize(3);
        assertThat(channel.getPodcastValues().get(0).getValueRecipients().get(0).getName()).isEqualTo("Podnews via node");
        assertThat(channel.getPodcastValues().get(0).getValueRecipients().get(0).getType()).isEqualTo("node");
        assertThat(channel.getPodcastValues().get(0).getValueRecipients().get(0).getAddress()).isEqualTo("02b307fdad2e68d08ba5a59cfc8a0a7ec0ff375291e1082fa22a5524e68608c520");
        assertThat(channel.getPodcastValues().get(0).getValueRecipients().get(0).getSplit()).isEqualTo(49);
        assertThat(channel.getPodcastValues().get(0).getValueRecipients().get(2).getName()).isEqualTo("Podcast Index");
        assertThat(channel.getPodcastValues().get(0).getValueRecipients().get(2).getType()).isEqualTo("node");
        assertThat(channel.getPodcastValues().get(0).getValueRecipients().get(2).getAddress()).isEqualTo("03ae9f91a0cb8ff43840e3c322c4c61f019d8c1c3cea15a25cfc425ac605e61a4a");
        assertThat(channel.getPodcastValues().get(0).getValueRecipients().get(2).getSplit()).isEqualTo(2);
        assertThat(channel.isPodcastUsingPodping()).hasValue(true);
        assertThat(channel.getPodcastPersons()).hasSize(1);
        assertThat(channel.getPodcastPersons().get(0).getPerson()).isEqualTo("James Cridland");
        assertThat(channel.getPodcastPersons().get(0).getRole()).hasValue("host");
        assertThat(channel.getPodcastPersons().get(0).getGroup()).hasValue("cast");
        assertThat(channel.getPodcastPersons().get(0).getImg()).hasValue("https://james.cridland.net/james-cridland-thumbnail.jpg");
        assertThat(channel.getPodcastPersons().get(0).getHref()).hasValue("https://james.cridland.net/");
        assertThat(channel.getItunesAuthor()).hasValue("Podnews LLC");
        assertTrue(channel.getItunesOwner().isPresent());
        assertThat(channel.getItunesOwner().get().getEmail()).isEqualTo("editor+dailyrss@podnews.net");
        assertThat(channel.getItunesOwner().get().getName()).hasValue("Podnews LLC");
        assertThat(channel.getItunesSubtitle()).hasValue("Daily news for the podcast and on-demand audio industry - from Apple Podcasts to Spotify,\n" +
                "            YouTube Music to Joe Rogan. Podnews also covers the latest jobs and events and trending shows in a short\n" +
                "            update every weekday. editor@podnews.net");
        assertThat(channel.getItunesCategories()).isEqualTo(List.of("News", "Daily News", "Technology"));
        assertThat(channel.getItunesType()).hasValue("episodic");
        assertThat(channel.getItunesImage()).isEqualTo("https://podnews.net/static/podnews-2000x2000.png");
        assertTrue(channel.getImage().isPresent());
        assertThat(channel.getImage().get().getUrl()).isEqualTo("https://podnews.net/static/_favicons/apple-icon.png");
        assertThat(channel.getImage().get().getTitle()).isEqualTo("Podnews");
        assertThat(channel.getImage().get().getLink()).isEqualTo("https://podnews.net");
        assertTrue(channel.getPodcastUpdateFrequency().isPresent());
        assertThat(channel.getPodcastUpdateFrequency().get().isComplete()).isTrue();
        assertThat(channel.getPodcastUpdateFrequency().get().getDtstart()).hasValue("2025-01-01T00:00:00.000Z");
        assertThat(channel.getPodcastUpdateFrequency().get().getDtstartAsZonedDateTime()).hasValue(Default.getDateTimeParser().parse("2025-01-01T00:00:00.000Z"));
        assertThat(channel.getPodcastUpdateFrequency().get().getUpdateFrequency()).isEqualTo("Every weekday");
        assertThat(channel.getPodcastUpdateFrequency().get().getRrule()).isEqualTo("FREQ=DAILY;INTERVAL=1;BYDAY=MO,TU,WE,TH,FR");

        assertThat(channel.getPodcastPodrolls()).hasSize(3);
        assertThat(channel.getPodcastPodrolls().get(0).getFeedGuid()).isEqualTo("396d9ae0-da7e-5557-b894-b606231fa3ea");
        assertThat(channel.getPodcastPodrolls().get(0).getFeedUrl()).hasValue("https://feeds.buzzsprout.com/1538779.rss");
        assertThat(channel.getPodcastPodrolls().get(1).getFeedGuid()).isEqualTo("d04f3195-25e8-52d9-b87f-969251009f1e");
        assertThat(channel.getPodcastPodrolls().get(1).getFeedUrl()).hasValue("https://feeds.buzzsprout.com/2105583.rss");
        assertThat(channel.getPodcastPodrolls().get(2).getFeedGuid()).isEqualTo("dc87f50b-8c0d-5980-8f2d-067dde67c7e4");
        assertThat(channel.getPodcastPodrolls().get(2).getFeedUrl()).hasValue("https://podnews.net/sitemap/trailers.xml");
        assertTrue(channel.getPodcastPublisher().isPresent());
        assertThat(channel.getPodcastPublisher().get().getFeedGuid()).isEqualTo("d76ccec7-e6df-5616-9d7d-8dc1fb9d9820");
        assertThat(channel.getPodcastPublisher().get().getMedium()).hasValue("publisher");
        assertThat(channel.getPodcastPublisher().get().getFeedUrl()).hasValue("https://podnews.net/static/feeds/publisher.xml");

        assertThat(channel.getPodcastImages()).hasSize(2);
        assertThat(channel.getPodcastImages().get(0).getHref()).isEqualTo("https://example.com/images/channel/pci_square-massive.jpg");
        assertThat(channel.getPodcastImages().get(1).getAlt()).hasValue("An antenna emanating signal waves channel");
        assertThat(channel.getPodcastImages().get(1).getPurpose()).hasValue("channel artwork social");
        assertThat(channel.getPodcastImages().get(1).getType()).hasValue("image/png");
        assertThat(channel.getPodcastImages().get(1).getAspectRatio()).hasValue("9/16");
        assertThat(channel.getPodcastImages().get(1).getWidth()).hasValue(90);
        assertThat(channel.getPodcastImages().get(1).getHeight()).hasValue(160);
        assertThat(channel.getPodcastImages().get(1).getHref()).isEqualTo("https://example.com/images/channel/pci_landscape-massive.jpg");

        assertThat(channel.getCategories()).isEqualTo(List.of("podcasting", "radio"));
        assertThat(channel.getTtl()).hasValue("30");

        assertThat(item.getPodcastImages()).hasSize(1);
        assertThat(item.getPodcastImages().get(0).getAlt()).hasValue("An antenna emanating signal waves");
        assertThat(item.getPodcastImages().get(0).getPurpose()).hasValue("artwork social");
        assertThat(item.getPodcastImages().get(0).getType()).hasValue("image/jpeg");
        assertThat(item.getPodcastImages().get(0).getAspectRatio()).hasValue("16/9");
        assertThat(item.getPodcastImages().get(0).getWidth()).hasValue(1600);
        assertThat(item.getPodcastImages().get(0).getHeight()).hasValue(900);
        assertThat(item.getPodcastImages().get(0).getHref()).isEqualTo("https://example.com/images/ep1/pci_landscape-massive.jpg");
    }

    @Test
    @SuppressWarnings("java:S5961")
    void equalsContract() {
        EqualsVerifier.simple().forClass(PodcastChannelImpl.class).withNonnullFields("data").withIgnoredFields("dateTimeParser").withIgnoredFields("category").withNonnullFields("categories").withIgnoredFields("syUpdatePeriod").withIgnoredFields("syUpdateFrequency").verify();
        EqualsVerifier.simple().forClass(PodcastItemImpl.class).withNonnullFields("data").withNonnullFields("itunesData").withIgnoredFields("defaultComparator").withIgnoredFields("dateTimeParser").withIgnoredFields("category").withNonnullFields("categories").withIgnoredFields("enclosure").withNonnullFields("enclosures").withIgnoredFields("channel").verify();
        EqualsVerifier.simple().forClass(PodcastChapters.class).verify();
        EqualsVerifier.simple().forClass(PodcastAlternateEnclosure.class).verify();
        EqualsVerifier.simple().forClass(PodcastBlock.class).verify();
        EqualsVerifier.simple().forClass(PodcastChat.class).verify();
        EqualsVerifier.simple().forClass(PodcastContentLink.class).verify();
        EqualsVerifier.simple().forClass(PodcastEpisode.class).verify();
        EqualsVerifier.simple().forClass(PodcastFunding.class).verify();
        EqualsVerifier.simple().forClass(PodcastImage.class).verify();
        EqualsVerifier.simple().forClass(PodcastIntegrity.class).verify();
        EqualsVerifier.simple().forClass(PodcastLicense.class).verify();
        EqualsVerifier.simple().forClass(PodcastLocation.class).verify();
        EqualsVerifier.simple().forClass(PodcastLocked.class).verify();
        EqualsVerifier.simple().forClass(PodcastPerson.class).verify();
        EqualsVerifier.simple().forClass(PodcastRemoteItem.class).verify();
        EqualsVerifier.simple().forClass(PodcastSeason.class).verify();
        EqualsVerifier.simple().forClass(PodcastSocialInteract.class).verify();
        EqualsVerifier.simple().forClass(PodcastSoundbite.class).verify();
        EqualsVerifier.simple().forClass(PodcastSource.class).verify();
        EqualsVerifier.simple().forClass(PodcastTrailer.class).withIgnoredFields("dateTimeParser").verify();
        EqualsVerifier.simple().forClass(PodcastTranscript.class).verify();
        EqualsVerifier.simple().forClass(PodcastTxt.class).verify();
        EqualsVerifier.simple().forClass(PodcastUpdateFrequency.class).verify();
        EqualsVerifier.simple().forClass(PodcastValue.class).verify();
        EqualsVerifier.simple().forClass(PodcastValueRecipient.class).verify();
        EqualsVerifier.simple().forClass(PodcastValueTimeSplit.class).verify();
    }

    private void assertHasFeedItem(PodcastItem item, boolean hasAtomChannel) {
        if (item instanceof FeedItem) {
            FeedItem feedItem = (FeedItem) item;
            assertFalse(feedItem.hasAtomItem());
            assertFalse(feedItem.hasDcItem());
            assertFalse(feedItem.hasGeoRssItem());
            assertTrue(feedItem.hasItunesItem());
            assertFalse(feedItem.hasMediaRssItem());
            assertTrue(feedItem.hasPodcastItem());
            assertFalse(feedItem.hasPscItem());
            assertFalse(feedItem.hasSlashItem());
            assertFalse(feedItem.hasWfwItem());
            assertFalse(feedItem.hasYoutubeItem());

            FeedChannel feedChannel = feedItem.getChannel();
            assertEquals(feedChannel.hasAtomChannel(), hasAtomChannel);
            assertFalse(feedChannel.hasDcChannel());
            assertFalse(feedChannel.hasGeoRssChannel());
            assertTrue(feedChannel.hasItunesChannel());
            assertFalse(feedChannel.hasMediaRssChannel());
            assertFalse(feedChannel.hasOpenSearchChannel());
            assertTrue(feedChannel.hasPodcastChannel());
            assertFalse(feedChannel.hasSpotifyChannel());
            assertFalse(feedChannel.hasYoutubeChannel());
        }
    }

    private static Stream<? extends Arguments> feedReaderArguments() {
        return Stream.of(
                Arguments.of(new PodcastFeedReader()),
                Arguments.of(new FeedReader())
        );
    }

    private InputStream fromFile(String fileName) {
        return getClass().getClassLoader().getResourceAsStream(fileName);
    }
}
