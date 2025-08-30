package com.apptasticsoftware.rssreader.module.mediarss;

import java.util.Objects;
import java.util.Optional;

/**
 * This element stands for the community related content.
 * This allows inclusion of the user perception about a media object in the form of view count, ratings and tags.
 */
public class MediaCommunity {
    private MediaStarRating mediaStarRating;
    private MediaStatistics mediaStatistics;
    private MediaTags mediaTags;

    /**
     * Specifies the rating-related information about a media object.
     * @return media star rating
     */
    public Optional<MediaStarRating> getMediaStarRating() {
        return Optional.ofNullable(mediaStarRating);
    }

    /**
     * Specifies the rating-related information about a media object.
     * @param mediaStarRating media star rating
     */
    public void setMediaStarRating(MediaStarRating mediaStarRating) {
        this.mediaStarRating = mediaStarRating;
    }

    /**
     * Specifies various statistics about a media object like the view count and the favorite count.
     * @return media statistics
     */
    public Optional<MediaStatistics> getMediaStatistics() {
        return Optional.ofNullable(mediaStatistics);
    }

    /**
     * Specifies various statistics about a media object like the view count and the favorite count.
     * @param mediaStatistics media statistics
     */
    public void setMediaStatistics(MediaStatistics mediaStatistics) {
        this.mediaStatistics = mediaStatistics;
    }

    /**
     * Contains user-generated tags separated by commas in the decreasing order of each tag's weight.
     * Each tag can be assigned an integer weight in tag_name:weight format.
     * It's up to the provider to choose the way weight is determined for a tag; for example,
     * number of occurrences can be one way to decide weight of a particular tag.
     * @return media tags
     */
    public Optional<MediaTags> getMediaTags() {
        return Optional.ofNullable(mediaTags);
    }

    /**
     * Contains user-generated tags separated by commas in the decreasing order of each tag's weight.
     * Each tag can be assigned an integer weight in tag_name:weight format.
     * It's up to the provider to choose the way weight is determined for a tag; for example,
     * number of occurrences can be one way to decide weight of a particular tag.
     * @param mediaTags media tags
     */
    public void setMediaTags(MediaTags mediaTags) {
        this.mediaTags = mediaTags;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MediaCommunity that = (MediaCommunity) o;
        return Objects.equals(getMediaStarRating(), that.getMediaStarRating()) && Objects.equals(getMediaStatistics(), that.getMediaStatistics()) && Objects.equals(getMediaTags(), that.getMediaTags());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMediaStarRating(), getMediaStatistics(), getMediaTags());
    }
}
