package com.apptasticsoftware.rssreader.module.mediarss;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MediaGroup extends MediaOptionalFieldsImpl {
    private final List<MediaContent> mediaContents = new ArrayList<>();

    /**
     * Get the media content
     *
     * @return media content
     */
    public List<MediaContent> getMediaContents() {
        return mediaContents;
    }

    /**
     * Set the media content
     *
     * @param mediaContent media content
     */
    public void addMediaContent(MediaContent mediaContent) {
        mediaContents.add(mediaContent);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MediaGroup that = (MediaGroup) o;
        return Objects.equals(getMediaContents(), that.getMediaContents());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getMediaContents());
    }
}
