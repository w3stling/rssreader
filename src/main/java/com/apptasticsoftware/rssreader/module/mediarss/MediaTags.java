package com.apptasticsoftware.rssreader.module.mediarss;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Contains user-generated tags separated by commas in the decreasing order of each tag's weight.
 * Each tag can be assigned an integer weight in tag_name:weight format. If no weight is specified,
 * the default weight is 1.
 *
 * Example:
 * {@code <media:tags>news:5, abc:3, reuters</media:tags>}
 * In this example, "news" has weight 5, "abc" has weight 3, and "reuters" has the default weight of 1.
 */
public class MediaTags {
    private String tags;

    /**
     * This element contains user-generated tags separated by commas in the decreasing order of each tag's weight.
     * Each tag can be assigned an integer weight in tag_name:weight format.
     * It's up to the provider to choose the way weight is determined for a tag; for example,
     * number of occurrences can be one way to decide weight of a particular tag. Default weight is 1.
     * @return tags
     */
    public String getTags() {
        return tags;
    }

    /**
     * This element contains user-generated tags separated by commas in the decreasing order of each tag's weight.
     * Each tag can be assigned an integer weight in tag_name:weight format.
     * It's up to the provider to choose the way weight is determined for a tag; for example,
     * number of occurrences can be one way to decide weight of a particular tag. Default weight is 1.
     * @param tags tags
     */
    public void setTags(String tags) {
        this.tags = tags;
    }

    public List<MediaTag> getTagList() {
        if (tags == null || tags.isBlank()) {
            return List.of();
        }

        return Arrays.stream(tags.split(","))
                .filter(Predicate.not(String::isBlank))
                .map(String::trim)
                .map(MediaTag::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MediaTags mediaTags = (MediaTags) o;
        return Objects.equals(getTags(), mediaTags.getTags());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getTags());
    }
}
