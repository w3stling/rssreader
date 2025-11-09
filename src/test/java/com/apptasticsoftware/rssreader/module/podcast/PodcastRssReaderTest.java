package com.apptasticsoftware.rssreader.module.podcast;

import com.apptasticsoftware.rssreader.util.Default;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.stream.Collectors;

import static com.github.npathai.hamcrestopt.OptionalMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PodcastRssReaderTest {

    @Test
    void example1() {
        var items = new PodcastRssReader().read(fromFile("podcast/example1.xml"))
                .collect(Collectors.toList());

        assertEquals(3, items.size());
        var item = items.get(0);
        var channel = (PodcastChannel) item.getChannel();
        assertThat(channel.getPodcastGuid(), is("y0ur-gu1d-g035-h3r3"));
        assertTrue(channel.getPodcastLicense().isPresent());
        assertThat(channel.getPodcastLicense().get().getUrl(), isPresentAndIs("https://example.org/mypodcastlicense/full.pdf"));
        assertThat(channel.getPodcastBlocks().size(), is(3));
        assertThat(channel.getPodcastBlocks().get(0).isBlock(), is(true));
        assertThat(channel.getPodcastBlocks().get(0).getId(), isEmpty());
        assertThat(channel.getPodcastBlocks().get(1).getId(), isPresentAndIs("google"));
        assertThat(channel.getPodcastBlocks().get(1).isBlock(), is(false));
        assertThat(channel.getPodcastBlocks().get(2).getId(), isPresentAndIs("amazon"));
        assertThat(channel.getPodcastBlocks().get(2).isBlock(), is(false));
        assertThat(channel.getPodcastFundings().size(), is(1));
        assertThat(channel.getPodcastFundings().get(0).getFunding(), is("Support the show!"));
        assertThat(channel.getPodcastFundings().get(0).getUrl(), is("https://example.com/donate"));
        assertThat(channel.getPodcastLocations().size(), is(1));
        assertThat(channel.getPodcastLocations().get(0).getLocation(), is("Austin, TX"));
        assertThat(channel.getPodcastLocations().get(0).getGeo(), isPresentAndIs("geo:30.2672,97.7431"));
        assertThat(channel.getPodcastLocations().get(0).getOsm(), isPresentAndIs("R113314"));
        assertThat(channel.getPodcastMedium(), is("podcast"));
        assertThat(channel.getItunesAuthor(), isPresentAndIs("John Doe"));
        assertThat(channel.getItunesExplicit(), is(false));
        assertThat(channel.getItunesType(), isPresentAndIs("episodic"));
        assertThat(channel.getItunesCategories().size(), is(2));
        assertThat(channel.getItunesCategories().get(0), is("News"));
        assertThat(channel.getItunesCategories().get(1), is("Technology"));
        assertTrue(channel.getItunesOwner().isPresent());
        assertThat(channel.getItunesOwner().get().getName(), isPresentAndIs("John Doe"));
        assertThat(channel.getItunesOwner().get().getEmail(), is("johndoe@example.com"));
        assertThat(channel.getItunesImage(), is("https://example.com/images/pci_avatar-massive.jpg"));
        assertThat(channel.getPodcastValues().size(), is(1));
        assertThat(channel.getPodcastValues().get(0).getType(), is("lightning"));
        assertThat(channel.getPodcastValues().get(0).getMethod(), is("keysend"));
        assertThat(channel.getPodcastValues().get(0).getSuggested(), isPresentAndIs(0.00000005000));
        assertThat(channel.getPodcastValues().get(0).getValueRecipients().size(), is(2));
        assertThat(channel.getPodcastValues().get(0).getValueRecipients().get(0).getName(), is("podcaster"));
        assertThat(channel.getPodcastValues().get(0).getValueRecipients().get(0).getType(), is("node"));
        assertThat(channel.getPodcastValues().get(0).getValueRecipients().get(0).getAddress(), is("036557ea56b3b86f08be31bcd2557cae8021b0e3a9413f0c0e52625c6696972e57"));
        assertThat(channel.getPodcastValues().get(0).getValueRecipients().get(0).getSplit(), is(99));
        assertThat(channel.getPodcastValues().get(0).getValueRecipients().get(1).getName(), is("hosting company"));
        assertThat(channel.getPodcastValues().get(0).getValueRecipients().get(1).getType(), is("node"));
        assertThat(channel.getPodcastValues().get(0).getValueRecipients().get(1).getAddress(), is("036557ea56b3b86f08be31bcd2557cae8021b0e3a9413f0c0e52625c6696972e58"));
        assertThat(channel.getPodcastValues().get(0).getValueRecipients().get(1).getSplit(), is(1));
        assertThat(channel.getPodcastValues().get(0).getValueTimeSplits().size(), is(2));
        assertThat(channel.getPodcastValues().get(0).getValueTimeSplits().get(0).getStartTime(), is(60));
        assertThat(channel.getPodcastValues().get(0).getValueTimeSplits().get(0).getDuration(), is(237));
        assertThat(channel.getPodcastValues().get(0).getValueTimeSplits().get(0).getRemotePercentage(), isPresentAndIs(95));
        assertTrue(channel.getPodcastValues().get(0).getValueTimeSplits().get(0).getRemoteItem().isPresent());
        assertThat(channel.getPodcastValues().get(0).getValueTimeSplits().get(0).getRemoteItem().get().getItemGuid(), isPresentAndIs("https://podcastindex.org/podcast/4148683#1"));
        assertThat(channel.getPodcastValues().get(0).getValueTimeSplits().get(0).getRemoteItem().get().getFeedGuid(), is("a94f5cc9-8c58-55fc-91fe-a324087a655b"));
        assertThat(channel.getPodcastValues().get(0).getValueTimeSplits().get(0).getRemoteItem().get().getMedium(), isPresentAndIs("music"));
        assertThat(channel.getPodcastValues().get(0).getValueTimeSplits().get(1).getStartTime(), is(367));
        assertThat(channel.getPodcastValues().get(0).getValueTimeSplits().get(1).getDuration(), is(246));
        assertThat(channel.getPodcastValues().get(0).getValueTimeSplits().get(1).getValueRecipients().size(), is(2));
        assertThat(channel.getPodcastValues().get(0).getValueTimeSplits().get(1).getValueRecipients().get(0).getName(), is("Alice (Podcaster)"));
        assertThat(channel.getPodcastValues().get(0).getValueTimeSplits().get(1).getValueRecipients().get(0).getType(), is("node"));
        assertThat(channel.getPodcastValues().get(0).getValueTimeSplits().get(1).getValueRecipients().get(0).getAddress(), is("02d5c1bf8b940dc9cadca86d1b0a3c37fbe39cee4c7e839e33bef9174531d27f52"));
        assertThat(channel.getPodcastValues().get(0).getValueTimeSplits().get(1).getValueRecipients().get(0).getSplit(), is(85));
        assertThat(channel.getPodcastValues().get(0).getValueTimeSplits().get(1).getValueRecipients().get(1).getName(), is("Bobjim (Guest)"));
        assertThat(channel.getPodcastValues().get(0).getValueTimeSplits().get(1).getValueRecipients().get(1).getType(), is("node"));
        assertThat(channel.getPodcastValues().get(0).getValueTimeSplits().get(1).getValueRecipients().get(1).getAddress(), is("032f4ffbbafffbe51726ad3c164a3d0d37ec27bc67b29a159b0f49ae8ac21b8508"));
        assertThat(channel.getPodcastValues().get(0).getValueTimeSplits().get(1).getValueRecipients().get(1).getSplit(), is(10));
        assertThat(channel.getPodcastTrailers().size(), is(1));
        assertThat(channel.getPodcastTrailers().get(0).getTrailer(), is("Coming April 1st, 2021"));
        assertThat(channel.getPodcastTrailers().get(0).getPubDate(), is("Thu, 01 Apr 2021 08:00:00 EST"));
        assertThat(channel.getPodcastTrailers().get(0).getPubDateAsZonedDateTime(), is(Default.getDateTimeParser().parse("Thu, 01 Apr 2021 08:00:00 EST")));
        assertThat(channel.getPodcastTrailers().get(0).getUrl(), is("https://example.org/trailers/teaser"));
        assertThat(channel.getPodcastTrailers().get(0).getLength(), is(12345678L));
        assertThat(channel.getPodcastTrailers().get(0).getType(), is("audio/mp3"));
        // TODO: test podcast:liveItem

        assertThat(item.getTitle(), isPresentAndIs("Episode 3 - The Future"));
        assertThat(item.getDescription(), isPresentAndIs("<p>A look into the future of podcasting and how we get to Podcasting 2.0!</p>"));
        assertThat(item.getLink(), isPresentAndIs("https://example.com/podcast/ep0003"));
        assertThat(item.getGuid(), isPresentAndIs("https://example.com/ep0003"));
        assertThat(item.getIsPermaLink(), isPresentAndIs(true));
        assertThat(item.getPubDate(), isPresentAndIs("Fri, 09 Oct 2020 04:30:38 GMT"));
        assertThat(item.getPubDateZonedDateTime(), isPresentAndIs(Default.getDateTimeParser().parse("Fri, 09 Oct 2020 04:30:38 GMT")));
        assertThat(item.getAuthor(), isPresentAndIs("John Doe (john@example.com)"));
        assertThat(item.getItunesImage(), isPresentAndIs("https://example.com/ep0003/artMd.jpg"));
        // TODO: add mapping of old images tag
        //assertThat(item.getPodcastImages().size(), is(1));
        assertThat(item.isItunesExplicit(), is(false));
        assertTrue(item.getPodcastSeason().isPresent());
        assertThat(item.getPodcastSeason().get().getSeason(), is(1));
        assertThat(item.getPodcastSeason().get().getName(), isPresentAndIs("Podcasting 2.0"));
        assertTrue(item.getPodcastEpisode().isPresent());
        assertThat(item.getPodcastEpisode().get().getEpisode(), is(3d));
        assertTrue(item.getPodcastChapters().isPresent());
        assertThat(item.getPodcastChapters().get().getUrl(), is("https://example.com/ep3_chapters.json"));
        assertThat(item.getPodcastChapters().get().getType(), is("application/json"));
        assertThat(item.getPodcastSoundbites().size(), is(1));
        assertThat(item.getPodcastSoundbites().get(0).getStartTime(), is(33.833));
        assertThat(item.getPodcastSoundbites().get(0).getDuration(), is(60.0));
        assertThat(item.getPodcastTranscripts().size(), is(1));
        assertThat(item.getPodcastTranscripts().get(0).getUrl(), is("https://example.com/ep3/transcript.txt"));
        assertThat(item.getPodcastTranscripts().get(0).getType(), is("text/plain"));
        assertThat(item.getPodcastPersons().size(), is(3));
        assertThat(item.getPodcastPersons().get(0).getPerson(), is("Adam Curry"));
        assertThat(item.getPodcastPersons().get(0).getHref(), isPresentAndIs("https://www.podchaser.com/creators/adam-curry-107ZzmWE5f"));
        assertThat(item.getPodcastPersons().get(0).getImg(), isPresentAndIs("https://example.com/images/adamcurry.jpg"));
        assertThat(item.getPodcastPersons().get(1).getPerson(), is("Dave Jones"));
        assertThat(item.getPodcastPersons().get(1).getRole(), isPresentAndIs("guest"));
        assertThat(item.getPodcastPersons().get(1).getHref(), isPresentAndIs("https://github.com/daveajones/"));
        assertThat(item.getPodcastPersons().get(1).getImg(), isPresentAndIs("https://example.com/images/davejones.jpg"));
        assertThat(item.getPodcastPersons().get(2).getPerson(), is("Becky Smith"));
        assertThat(item.getPodcastPersons().get(2).getGroup(), isPresentAndIs("visuals"));
        assertThat(item.getPodcastPersons().get(2).getRole(), isPresentAndIs("cover art designer"));
        assertThat(item.getPodcastPersons().get(2).getHref(), isPresentAndIs("https://example.com/artist/beckysmith"));
        assertThat(item.getEnclosures().size(), is(1));
        assertThat(item.getEnclosures().get(0).getUrl(), is("https://example.com/file-03.mp3"));
        assertThat(item.getEnclosures().get(0).getLength(), isPresentAndIs(43200000L));
        assertThat(item.getEnclosures().get(0).getType(), is("audio/mpeg"));
        assertThat(item.getPodcastAlternateEnclosures().size(), is(5));
        assertThat(item.getPodcastAlternateEnclosures().get(0).getType(), is("audio/mpeg"));
        assertThat(item.getPodcastAlternateEnclosures().get(0).getLength(), is(43200000L));
        assertThat(item.getPodcastAlternateEnclosures().get(0).getBitrate(), is(128000d));
        assertThat(item.getPodcastAlternateEnclosures().get(0).isDefaults(), is(true));
        assertThat(item.getPodcastAlternateEnclosures().get(0).getTitle(), is("Standard"));
        assertThat(item.getPodcastAlternateEnclosures().get(4).getType(), is("video/mp4"));
        assertThat(item.getPodcastAlternateEnclosures().get(4).getLength(), is(7924786L));
        assertThat(item.getPodcastAlternateEnclosures().get(4).getBitrate(), is(511276.52d));
        assertThat(item.getPodcastAlternateEnclosures().get(4).getHeight(), is(720));
        assertThat(item.getPodcastAlternateEnclosures().get(4).getTitle(), is("Video version"));
        assertThat(item.getPodcastAlternateEnclosures().get(4).getSources().size(), is(2));
        assertThat(item.getPodcastAlternateEnclosures().get(4).getSources().get(0).getUri(), is("https://example.com/file-720.mp4"));
        assertThat(item.getPodcastAlternateEnclosures().get(4).getSources().get(1).getUri(), is("ipfs://QmX33FYehk6ckGQ6g1D9D3FqZPix5JpKstKQKbaS8quUFb"));
        assertTrue(item.getPodcastAlternateEnclosures().get(4).getIntegrity().isPresent());
        assertThat(item.getPodcastAlternateEnclosures().get(4).getIntegrity().get().getType(), is("sri"));
        assertThat(item.getPodcastAlternateEnclosures().get(4).getIntegrity().get().getValue(), is("sha384-ExVqijgYHm15PqQqdXfW95x+Rs6C+d6E/ICxyQOeFevnxNLR/wtJNrNYTjIysUBo"));
        assertThat(item.getPodcastValues().size(), is(1));
        assertThat(item.getPodcastValues().get(0).getType(), is("lightning"));
        assertThat(item.getPodcastValues().get(0).getMethod(), is("keysend"));
        assertThat(item.getPodcastValues().get(0).getSuggested(), isPresentAndIs(0.00000005000d));
        assertThat(item.getPodcastValues().get(0).getValueRecipients().size(), is(3));
        assertThat(item.getPodcastValues().get(0).getValueRecipients().get(0).getName(), is("podcaster"));
        assertThat(item.getPodcastValues().get(0).getValueRecipients().get(0).getType(), is("node"));
        assertThat(item.getPodcastValues().get(0).getValueRecipients().get(0).getAddress(), is("036557ea56b3b86f08be31bcd2557cae8021b0e3a9413f0c0e52625c6696972e57"));
        assertThat(item.getPodcastValues().get(0).getValueRecipients().get(0).getSplit(), is(49));
        assertThat(item.getPodcastValues().get(0).getValueRecipients().get(2).getName(), is("Gigi (Guest)"));
        assertThat(item.getPodcastValues().get(0).getValueRecipients().get(2).getType(), is("node"));
        assertThat(item.getPodcastValues().get(0).getValueRecipients().get(2).getAddress(), is("02e12fea95f576a680ec1938b7ed98ef0855eadeced493566877d404e404bfbf52"));
        assertThat(item.getPodcastValues().get(0).getValueRecipients().get(2).getSplit(), is(50));
        assertThat(item.getPodcastValues().get(0).getValueTimeSplits().size(), is(2));
        assertThat(item.getPodcastValues().get(0).getValueTimeSplits().get(0).getStartTime(), is(60));
        assertThat(item.getPodcastValues().get(0).getValueTimeSplits().get(0).getDuration(), is(237));
        assertThat(item.getPodcastValues().get(0).getValueTimeSplits().get(0).getRemotePercentage(), isPresentAndIs(95));
        assertTrue(item.getPodcastValues().get(0).getValueTimeSplits().get(0).getRemoteItem().isPresent());
        assertThat(item.getPodcastValues().get(0).getValueTimeSplits().get(0).getRemoteItem().get().getItemGuid(), isPresentAndIs("https://podcastindex.org/podcast/4148683#1"));
        assertThat(item.getPodcastValues().get(0).getValueTimeSplits().get(0).getRemoteItem().get().getFeedGuid(), is("a94f5cc9-8c58-55fc-91fe-a324087a655b"));
        assertThat(item.getPodcastValues().get(0).getValueTimeSplits().get(0).getRemoteItem().get().getMedium(), isPresentAndIs("music"));
        assertThat(item.getPodcastValues().get(0).getValueTimeSplits().get(1).getStartTime(), is(367));
        assertThat(item.getPodcastValues().get(0).getValueTimeSplits().get(1).getDuration(), is(246));
        assertThat(item.getPodcastValues().get(0).getValueTimeSplits().get(1).getValueRecipients().size(), is(2));
        assertThat(item.getPodcastValues().get(0).getValueTimeSplits().get(1).getValueRecipients().get(0).getName(), is("Alice (Podcaster)"));
        assertThat(item.getPodcastValues().get(0).getValueTimeSplits().get(1).getValueRecipients().get(0).getType(), is("node"));
        assertThat(item.getPodcastValues().get(0).getValueTimeSplits().get(1).getValueRecipients().get(0).getAddress(), is("02d5c1bf8b940dc9cadca86d1b0a3c37fbe39cee4c7e839e33bef9174531d27f52"));
        assertThat(item.getPodcastValues().get(0).getValueTimeSplits().get(1).getValueRecipients().get(0).getSplit(), is(85));
        assertThat(item.getPodcastValues().get(0).getValueTimeSplits().get(1).getValueRecipients().get(1).getName(), is("Bobjim (Guest)"));
        assertThat(item.getPodcastValues().get(0).getValueTimeSplits().get(1).getValueRecipients().get(1).getType(), is("node"));
        assertThat(item.getPodcastValues().get(0).getValueTimeSplits().get(1).getValueRecipients().get(1).getAddress(), is("032f4ffbbafffbe51726ad3c164a3d0d37ec27bc67b29a159b0f49ae8ac21b8508"));
        assertThat(item.getPodcastValues().get(0).getValueTimeSplits().get(1).getValueRecipients().get(1).getSplit(), is(10));
        assertThat(item.getPodcastValues().get(0).getValueTimeSplits().get(1).getStartTime(), is(367));
        assertThat(item.getPodcastValues().get(0).getValueTimeSplits().get(1).getDuration(), is(246));
        assertThat(item.getPodcastValues().get(0).getValueTimeSplits().get(1).getRemotePercentage(), isEmpty());
        assertThat(item.getPodcastSocialInteracts().size(), is(2));
        assertThat(item.getPodcastSocialInteracts().get(0).getPriority(), isPresentAndIs(1));
        assertThat(item.getPodcastSocialInteracts().get(0).getUri(), is("https://podcastindex.social/web/@dave/108013847520053258"));
        assertThat(item.getPodcastSocialInteracts().get(0).getProtocol(), is("activitypub"));
        assertThat(item.getPodcastSocialInteracts().get(0).getAccountId(), isPresentAndIs("@dave"));
        assertThat(item.getPodcastSocialInteracts().get(0).getAccountUrl(), isPresentAndIs("https://podcastindex.social/web/@dave"));
        assertThat(item.getPodcastSocialInteracts().get(1).getPriority(), isPresentAndIs(2));
        assertThat(item.getPodcastSocialInteracts().get(1).getUri(), is("https://twitter.com/PodcastindexOrg/status/1507120226361647115"));
        assertThat(item.getPodcastSocialInteracts().get(1).getProtocol(), is("twitter"));
        assertThat(item.getPodcastSocialInteracts().get(1).getAccountId(), isPresentAndIs("@podcastindexorg"));
        assertThat(item.getPodcastSocialInteracts().get(1).getAccountUrl(), isPresentAndIs("https://twitter.com/PodcastindexOrg"));
    }

    @Test
    void example2() {
        var items = new PodcastRssReader().read(fromFile("podcast/example2.xml"))
                .collect(Collectors.toList());

        assertEquals(3, items.size());
    }


    @Test
    void equalsContract() {
        EqualsVerifier.simple().forClass(PodcastChannel.class).withIgnoredFields("dateTimeParser").withIgnoredFields("category").withNonnullFields("categories").withIgnoredFields("syUpdatePeriod").withIgnoredFields("syUpdateFrequency").withNonnullFields("itunesCategories").verify();
        EqualsVerifier.simple().forClass(PodcastItem.class).withIgnoredFields("defaultComparator").withIgnoredFields("dateTimeParser").withIgnoredFields("category").withNonnullFields("categories").withIgnoredFields("enclosure").withNonnullFields("enclosures").verify();
        EqualsVerifier.simple().forClass(PodcastAlternateEnclosure.class).verify();
        EqualsVerifier.simple().forClass(PodcastBlock.class).verify();
        EqualsVerifier.simple().forClass(PodcastChapters.class).verify();
        EqualsVerifier.simple().forClass(PodcastEpisode.class).verify();
        EqualsVerifier.simple().forClass(PodcastFunding.class).verify();
        EqualsVerifier.simple().forClass(PodcastImage.class).verify();
        EqualsVerifier.simple().forClass(PodcastIntegrity.class).verify();
        EqualsVerifier.simple().forClass(PodcastLicense.class).verify();
        EqualsVerifier.simple().forClass(PodcastLocation.class).verify();
        EqualsVerifier.simple().forClass(PodcastLocked.class).verify();
        EqualsVerifier.simple().forClass(PodcastPerson.class).verify();
        EqualsVerifier.simple().forClass(PodcastSeason.class).verify();
        EqualsVerifier.simple().forClass(PodcastSocialInteract.class).verify();
        EqualsVerifier.simple().forClass(PodcastSoundbite.class).verify();
        EqualsVerifier.simple().forClass(PodcastSource.class).verify();
        EqualsVerifier.simple().forClass(PodcastTrailer.class).verify();
        EqualsVerifier.simple().forClass(PodcastTranscript.class).verify();
        EqualsVerifier.simple().forClass(PodcastValue.class).verify();
        EqualsVerifier.simple().forClass(PodcastValueRecipient.class).verify();
        EqualsVerifier.simple().forClass(PodcastLiveItem.class).verify();
        EqualsVerifier.simple().forClass(PodcastRemoteItem.class).verify();
        EqualsVerifier.simple().forClass(PodcastValueTimeSplit.class).verify();
    }

    private InputStream fromFile(String fileName) {
        return getClass().getClassLoader().getResourceAsStream(fileName);
    }
}
