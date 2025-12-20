package com.apptasticsoftware.rssreader.module.psc;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.apptasticsoftware.rssreader.util.Mapper.emptyListIfNull;

/**
 * Represents a collection of chapters for a podcast episode.
 * <p>
 * This class serves as a container for chapter metadata, including the chapters list and
 * the version of the chapters specification being used. Each episode may contain multiple
 * chapters that segment the episode into distinct sections.
 * </p>
 *
 * @see <a href="https://podcastindex.org/namespace/">Podcast Index Specification</a>
 */
public class PscChapters {
    private String version;
    private List<PscChapter> chapters;

    /**
     * Gets the version of the chapters specification.
     * <p>
     * The version indicates which version of the PSC (Podcast Soundbites/Chapters)
     * specification is being used for this chapter collection.
     * </p>
     *
     * @return the version string of the chapters specification
     */
    public Optional<String> getVersion() {
        return Optional.ofNullable(version);
    }

    /**
     * Sets the version of the chapters specification.
     *
     * @param version the version string of the chapters specification
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * Gets the list of chapters for this episode.
     * <p>
     * Each chapter in the list represents a distinct section or segment of the episode,
     * defined by a start time, title, and optional external references.
     * </p>
     *
     * @return the list of {@code PscChapter} objects representing the episode segments
     */
    public List<PscChapter> getChapters() {
        return emptyListIfNull(chapters);
    }

    /**
     * Adds a chapter to this collection.
     *
     * @param chapter the {@code PscChapter} to add to this collection
     */
    public void addChapter(PscChapter chapter) {
        if (chapters == null) {
            chapters = new ArrayList<>();
        }
        chapters.add(chapter);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PscChapters that = (PscChapters) o;
        return Objects.equals(getVersion(), that.getVersion()) && Objects.equals(getChapters(), that.getChapters());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVersion(), getChapters());
    }
}
