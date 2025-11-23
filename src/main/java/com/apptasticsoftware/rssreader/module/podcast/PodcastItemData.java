package com.apptasticsoftware.rssreader.module.podcast;

import java.util.List;
import java.util.Optional;

public interface PodcastItemData {
    Optional<PodcastSeason> getPodcastSeason();

    void setPodcastSeason(PodcastSeason podcastSeason);

    Optional<PodcastEpisode> getPodcastEpisode();

    void setPodcastEpisode(PodcastEpisode podcastEpisode);

    Optional<PodcastChapters> getPodcastChapters();

    void setPodcastChapters(PodcastChapters podcastChapters);

    List<PodcastImage> getPodcastImages();

    void addPodcastImage(PodcastImage podcastImage);

    List<PodcastSoundbite> getPodcastSoundbites();

    void addPodcastSoundbite(PodcastSoundbite podcastSoundbite);

    List<PodcastTranscript> getPodcastTranscripts();

    void addPodcastTranscript(PodcastTranscript podcastTranscript);

    List<PodcastPerson> getPodcastPersons();

    void addPodcastPerson(PodcastPerson podcastPerson);

    List<PodcastAlternateEnclosure> getPodcastAlternateEnclosures();

    void addPodcastAlternateEnclosure(PodcastAlternateEnclosure podcastAlternateEnclosure);

    List<PodcastValue> getPodcastValues();

    void addPodcastValue(PodcastValue podcastValue);

    List<PodcastSocialInteract> getPodcastSocialInteracts();

    void addPodcastSocialInteract(PodcastSocialInteract podcastSocialInteract);
}
