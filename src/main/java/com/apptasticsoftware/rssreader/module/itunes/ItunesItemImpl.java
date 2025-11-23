/*
 * MIT License
 *
 * Copyright (c) 2022, Apptastic Software
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.apptasticsoftware.rssreader.module.itunes;

import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.ItemImpl;

import java.time.Duration;
import java.util.Objects;
import java.util.Optional;

/**
 * Class representing the Itunes item.
 */
public class ItunesItemImpl extends ItemImpl implements ItunesItem {
    private final ItunesItemDataImpl data = new ItunesItemDataImpl();

    /**
     * Constructor
     * @param dateTimeParser timestamp parser
     */
    public ItunesItemImpl(DateTimeParser dateTimeParser) {
        super(dateTimeParser);
    }

    /**
     * Get the duration of an episode.
     * @return duration
     */
    public Optional<String> getItunesDuration() {
        return data.getItunesDuration();
    }

    /**
     * Set the duration of an episode.
     * @param itunesDuration duration
     */
    public void setItunesDuration(String itunesDuration) {
        data.setItunesDuration(itunesDuration);
    }

    /**
     * Get the duration
     * @return duration
     */
    @SuppressWarnings("java:S108")
    public Optional<Duration> getItunesDurationAsDuration() {
        return data.getItunesDurationAsDuration();
    }

    /**
     * Get the episode artwork.
     * @return image
     */
    public Optional<String> getItunesImage() {
        return data.getItunesImage();
    }

    /**
     * Set the episode artwork.
     * @param itunesImage image
     */
    public void setItunesImage(String itunesImage) {
        data.setItunesImage(itunesImage);
    }

    /**
     * Set the episode parental advisory information.
     * Where the explicit value can be one of the following:
     * <p>
     * true. If you specify true, indicating the presence of explicit content, Apple Podcasts displays an Explicit parental advisory graphic for your episode.
     * Episodes containing explicit material aren’t available in some Apple Podcasts territories.
     * <p>
     * false. If you specify false, indicating that the episode does not contain explicit language or adult content, Apple Podcasts displays a Clean parental advisory graphic for your episode.
     * @return explicit
     */
    public boolean isItunesExplicit() {
        return data.isItunesExplicit();
    }

    /**
     * Set the episode parental advisory information.
     * Where the explicit value can be one of the following:
     * <p>
     * true. If you specify true, indicating the presence of explicit content, Apple Podcasts displays an Explicit parental advisory graphic for your episode.
     * Episodes containing explicit material aren’t available in some Apple Podcasts territories.
     * <p>
     * false. If you specify false, indicating that the episode does not contain explicit language or adult content, Apple Podcasts displays a Clean parental advisory graphic for your episode.
     * @param itunesExplicit explicit
     */
    public void setItunesExplicit(boolean itunesExplicit) {
        data.setItunesExplicit(itunesExplicit);
    }

    /**
     * Get the episode title specific for Apple Podcasts.
     * @return title
     */
    public Optional<String> getItunesTitle() {
        return data.getItunesTitle();
    }

    /**
     * Set the episode title specific for Apple Podcasts.
     * @param itunesTitle title
     */
    public void setItunesTitle(String itunesTitle) {
        data.setItunesTitle(itunesTitle);
    }

    /**
     * Get the episode subtitle specific for Apple Podcasts.
     * @return subtitle
     */
    public Optional<String> getItunesSubtitle() {
        return data.getItunesSubtitle();
    }

    /**
     * Set the episode subtitle specific for Apple Podcasts.
     * @param itunesSubtitle subtitle
     */
    public void setItunesSubtitle(String itunesSubtitle) {
        data.setItunesSubtitle(itunesSubtitle);
    }

    /**
     * Get the episode summary.
     * @return summary
     */
    public Optional<String> getItunesSummary() {
        return data.getItunesSummary();
    }

    /**
     * Get the episode summary.
     * @param itunesSummary summary
     */
    public void setItunesSummary(String itunesSummary) {
        data.setItunesSummary(itunesSummary);
    }

    /**
     * Get the keywords.
     * @return keywords
     */
    public Optional<String> getItunesKeywords() {
        return data.getItunesKeywords();
    }

    /**
     * Set the keywords.
     * @param itunesKeywords keywords
     */
    public void setItunesKeywords(String itunesKeywords) {
        data.setItunesKeywords(itunesKeywords);
    }

    /**
     * Get the episode number.
     * @return episode
     */
    public Optional<Integer> getItunesEpisode() {
        return data.getItunesEpisode();
    }

    /**
     * Set the episode number.
     * @param itunesEpisode episode
     */
    public void setItunesEpisode(Integer itunesEpisode) {
        data.setItunesEpisode(itunesEpisode);
    }

    /**
     * Get the episode season number.
     * @return season
     */
    public Optional<Integer> getItunesSeason() {
        return data.getItunesSeason();
    }

    /**
     * Set the episode season number.
     * @param itunesSeason season
     */
    public void setItunesSeason(Integer itunesSeason) {
        data.setItunesSeason(itunesSeason);
    }

    /**
     * Get the episode type.
     * @return type
     */
    public Optional<String> getItunesEpisodeType() {
        return data.getItunesEpisodeType();
    }

    /**
     * Set the episode type.
     * @param itunesEpisodeType type
     */
    public void setItunesEpisodeType(String itunesEpisodeType) {
        data.setItunesEpisodeType(itunesEpisodeType);
    }

    /**
     * Get the episode show or hide status.
     * If you want an episode removed from the Apple directory, use this tag.
     * @return block
     */
    public boolean isItunesBlock() {
        return data.isItunesBlock();
    }

    /**
     * Set the episode show or hide status.
     * If you want an episode removed from the Apple directory, use this tag.
     * @param itunesBlock block
     */
    public void setItunesBlock(boolean itunesBlock) {
        data.setItunesBlock(itunesBlock);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ItunesItemImpl that = (ItunesItemImpl) o;
        return isItunesExplicit() == that.isItunesExplicit() && isItunesBlock() == that.isItunesBlock() && Objects.equals(getItunesDuration(), that.getItunesDuration()) && Objects.equals(getItunesImage(), that.getItunesImage()) && Objects.equals(getItunesTitle(), that.getItunesTitle()) && Objects.equals(getItunesSubtitle(), that.getItunesSubtitle()) && Objects.equals(getItunesSummary(), that.getItunesSummary()) && Objects.equals(getItunesKeywords(), that.getItunesKeywords()) && Objects.equals(getItunesEpisode(), that.getItunesEpisode()) && Objects.equals(getItunesSeason(), that.getItunesSeason()) && Objects.equals(getItunesEpisodeType(), that.getItunesEpisodeType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getItunesDuration(), getItunesImage(), isItunesExplicit(), getItunesTitle(), getItunesSubtitle(), getItunesSummary(), getItunesKeywords(), getItunesEpisode(), getItunesSeason(), getItunesEpisodeType(), isItunesBlock());
    }
}
