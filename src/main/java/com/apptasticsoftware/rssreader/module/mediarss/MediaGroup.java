package com.apptasticsoftware.rssreader.module.mediarss;

import com.apptasticsoftware.rssreader.util.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A group element that allows grouping of {@code <media:content>} elements that are effectively
 * the same content, yet different representations. For instance: the same song recorded in both
 * WAV and MP3 format.
 * <p>
 * Example:
 * {@code
 * <media:group>
 *     <media:content url="http://www.foo.com/song.wav"
 *                    type="audio/wav"
 *                    bitrate="128"
 *                    isDefault="true" />
 * <p>
 *     <media:content url="http://www.foo.com/song.mp3"
 *                    type="audio/mpeg"
 *                    bitrate="64" />
 * </media:group>
 * }
 */
public class MediaGroup extends MetadataImpl {
    private List<MediaContent> mediaContents;

    /**
     * Get the media content
     *
     * @return media content
     */
    public List<MediaContent> getMediaContents() {
        return Mapper.emptyListIfNull(mediaContents);
    }

    /**
     * Set the media content
     *
     * @param mediaContent media content
     */
    public void addMediaContent(MediaContent mediaContent) {
        if (mediaContents == null) {
            mediaContents = new ArrayList<>();
        }
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
