package com.apptasticsoftware.rssreader.module.podcast;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PodcastRssReaderTest {

    @Test
    void readItunesPodcastFeed() {
        var res = new PodcastRssReader().read(fromFile("podcast/example.xml"))
                .collect(Collectors.toList());

        assertEquals(3, res.size());
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
    }

    private InputStream fromFile(String fileName) {
        return getClass().getClassLoader().getResourceAsStream(fileName);
    }
}
