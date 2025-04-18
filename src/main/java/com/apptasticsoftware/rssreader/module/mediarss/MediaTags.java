package com.apptasticsoftware.rssreader.module.mediarss;

/**
 * Contains user-generated tags separated by commas in the decreasing order of each tag's weight.
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
}
