package com.apptasticsoftware.rssreader.module.podcast;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents a Podcast Txt element ({@code <podcast:txt>}).
 *
 * <p>This element holds free-form text and is modeled after the DNS "TXT" record.
 * It's meant to allow for usages that might be niche or otherwise not rise to the level
 * of needing a dedicated tag. Just like TXT records in DNS allowed for new things like
 * SPF to evolve, this tag can allow novel techniques to be created and sandboxed without
 * a formalization process.</p>
 *
 * <p>The txt value should not exceed 4000 characters, as it may be truncated by aggregators.</p>
 */
public class PodcastTxt {
    private String txt;
    private String purpose;

    /**
     * Gets the free-form text content of this podcast:txt element.
     *
     * @return the text content, or {@code null} if not set
     */
    public String getTxt() {
        return txt;
    }

    /**
     * Sets the free-form text content of this podcast:txt element.
     *
     * <p>The value should not exceed 4000 characters, as it may be truncated by aggregators.</p>
     *
     * @param txt the text content to set
     */
    public void setTxt(String txt) {
        this.txt = txt;
    }

    /**
     * Gets the purpose attribute of this podcast:txt element.
     *
     * <p>The purpose is a service-specific string that denotes what purpose this tag serves.
     * This could be something like "example.com" if it's a third party hosting platform
     * needing to insert this data, or something like "verify", "release" or any other
     * free form bit of info that is useful to the end consumer.
     * The value should not exceed 128 characters.</p>
     *
     * <p>Known purposes include:
     * <ul>
     *   <li>{@code verify} - The node value contains a string given by a third party platform
     *       to prove ownership of the feed.</li>
     *   <li>{@code applepodcastsverify} - Apple's variant of the verify purpose.</li>
     * </ul>
     * </p>
     *
     * @return an {@link Optional} containing the purpose attribute, or an empty Optional if not set
     */
    public Optional<String> getPurpose() {
        return Optional.ofNullable(purpose);
    }

    /**
     * Sets the purpose attribute of this podcast:txt element.
     *
     * <p>The purpose should not exceed 128 characters.</p>
     *
     * @param purpose the purpose attribute to set
     */
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PodcastTxt that = (PodcastTxt) o;
        return Objects.equals(getTxt(), that.getTxt()) && Objects.equals(getPurpose(), that.getPurpose());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTxt(), getPurpose());
    }
}
