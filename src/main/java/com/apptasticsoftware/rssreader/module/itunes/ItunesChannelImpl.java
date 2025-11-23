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

import com.apptasticsoftware.rssreader.ChannelImpl;
import com.apptasticsoftware.rssreader.DateTimeParser;

import java.util.*;

/**
 * Class representing the Itunes channel.
 */
public class ItunesChannelImpl extends ChannelImpl implements ItunesChannel {
    private final ItunesChannelDataImpl data = new ItunesChannelDataImpl();

    /**
     * Constructor
     * @param dateTimeParser timestamp parser
     */
    public ItunesChannelImpl(DateTimeParser dateTimeParser) {
        super(dateTimeParser);
    }

    /**
     * Get the artwork for the show.
     * Specify your show artwork by providing a URL linking to it.
     * @return image
     */
    public String getItunesImage() {
        return data.getItunesImage();
    }

    /**
     * Set the artwork for the show.
     * Specify your show artwork by providing a URL linking to it.
     * @param image image
     */
    public void setItunesImage(String image) {
        data.setItunesImage(image);
    }

    /**
     * Get the show category information. For a complete list of categories and subcategories
     * @return list of categories
     */
    public List<String> getItunesCategories() {
        return data.getItunesCategories();
    }

    /**
     * Add the show category information. For a complete list of categories and subcategories
     * @param itunesCategory category
     */
    public void addItunesCategory(String itunesCategory) {
        data.addItunesCategory(itunesCategory);
    }

    /**
     * Get the podcast parental advisory information.
     * The explicit value can be one of the following:
     * <p>
     * True. If you specify true, indicating the presence of explicit content,
     * Apple Podcasts displays an Explicit parental advisory graphic for your podcast.
     * Podcasts containing explicit material aren’t available in some Apple Podcasts territories.
     * <p>
     * False. If you specify false, indicating that your podcast doesn’t contain
     * explicit language or adult content, Apple Podcasts displays a Clean parental
     * advisory graphic for your podcast.
     * @return explicit
     */
    public Boolean getItunesExplicit() {
        return data.getItunesExplicit();
    }

    /**
     * Get the podcast parental advisory information.
     * The explicit value can be one of the following:
     * <p>
     * True. If you specify true, indicating the presence of explicit content,
     * Apple Podcasts displays an Explicit parental advisory graphic for your podcast.
     * Podcasts containing explicit material aren’t available in some Apple Podcasts territories.
     * <p>
     * False. If you specify false, indicating that your podcast doesn’t contain
     * explicit language or adult content, Apple Podcasts displays a Clean parental
     * advisory graphic for your podcast.
     * @param itunesExplicit explicit
     */
    public void setItunesExplicit(Boolean itunesExplicit) {
        data.setItunesExplicit(itunesExplicit);
    }

    /**
     * Get the group responsible for creating the show.
     * @return author
     */
    public Optional<String> getItunesAuthor() {
        return data.getItunesAuthor();
    }

    /**
     * Set the group responsible for creating the show.
     * @param itunesAuthor author
     */
    public void setItunesAuthor(String itunesAuthor) {
        data.setItunesAuthor(itunesAuthor);
    }

    /**
     * Set the podcast owner contact information.
     * @param itunesOwner owner
     */
    public void setItunesOwner(ItunesOwner itunesOwner) {
        data.setItunesOwner(itunesOwner);
    }

    /**
     * Get the podcast owner contact information.
     * @return owner
     */
    public Optional<ItunesOwner> getItunesOwner() {
        return data.getItunesOwner();
    }

    /**
     * Get the title specific for Apple Podcasts.
     * @return title
     */
    public Optional<String> getItunesTitle() {
        return data.getItunesTitle();
    }

    /**
     * Set the title specific for Apple Podcasts.
     * @param itunesTitle title
     */
    public void setItunesTitle(String itunesTitle) {
        data.setItunesTitle(itunesTitle);
    }

    /**
     * Get the subtitle specific for Apple Podcasts.
     * @return subtitle
     */
    public Optional<String> getItunesSubtitle() {
        return data.getItunesSubtitle();
    }

    /**
     * Set the subtitle specific for Apple Podcasts.
     * @param itunesSubtitle subtitle
     */
    public void setItunesSubtitle(String itunesSubtitle) {
        data.setItunesSubtitle(itunesSubtitle);
    }

    /**
     * Get the summary.
     * @return summary
     */
    public String getItunesSummary() {
        return data.getItunesSummary();
    }

    /**
     * Set the summary.
     * @param itunesSummary summary
     */
    public void setItunesSummary(String itunesSummary) {
        data.setItunesSummary(itunesSummary);
    }

    /**
     * Get the type of show.
     * @return type
     */
    public Optional<String> getItunesType() {
        return data.getItunesType();
    }

    /**
     * Set the type of show.
     * @param itunesType type
     */
    public void setItunesType(String itunesType) {
        data.setItunesType(itunesType);
    }

    /**
     * Get the new podcast RSS Feed URL.
     * If you change the URL of your podcast feed, you should use this tag in your new feed.
     * @return new feed url
     */
    public Optional<String> getItunesNewFeedUrl() {
        return data.getItunesNewFeedUrl();
    }

    /**
     * Set the new podcast RSS Feed URL.
     * If you change the URL of your podcast feed, you should use this tag in your new feed.
     * @param itunesNewFeedUrl new feed url
     */
    public void setItunesNewFeedUrl(String itunesNewFeedUrl) {
        data.setItunesNewFeedUrl(itunesNewFeedUrl);
    }

    /**
     * Get the podcast show or hide status.
     * If you want your show removed from the Apple directory, use this tag.
     * @return block
     */
    public boolean isItunesBlock() {
        return data.isItunesBlock();
    }

    /**
     * Set the podcast show or hide status.
     * If you want your show removed from the Apple directory, use this tag.
     * @param itunesBlock block
     */
    public void setItunesBlock(boolean itunesBlock) {
        data.setItunesBlock(itunesBlock);
    }

    /**
     * Set the podcast update status.
     * If you will never publish another episode to your show, use this tag.
     * @return complete
     */
    public boolean isItunesComplete() {
        return data.isItunesComplete();
    }

    /**
     * Get the podcast update status.
     * If you will never publish another episode to your show, use this tag.
     * @param itunesComplete complete
     */
    public void setItunesComplete(boolean itunesComplete) {
        data.setItunesComplete(itunesComplete);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ItunesChannelImpl that = (ItunesChannelImpl) o;
        return getItunesExplicit() == that.getItunesExplicit() &&
                isItunesBlock() == that.isItunesBlock() &&
                isItunesComplete() == that.isItunesComplete() &&
                Objects.equals(getItunesImage(), that.getItunesImage()) &&
                getItunesCategories().equals(that.getItunesCategories()) &&
                Objects.equals(getItunesAuthor(), that.getItunesAuthor()) &&
                Objects.equals(getItunesOwner(), that.getItunesOwner()) &&
                Objects.equals(getItunesTitle(), that.getItunesTitle()) &&
                Objects.equals(getItunesSubtitle(), that.getItunesSubtitle()) &&
                Objects.equals(getItunesSummary(), that.getItunesSummary()) &&
                Objects.equals(getItunesType(), that.getItunesType()) &&
                Objects.equals(getItunesNewFeedUrl(), that.getItunesNewFeedUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getItunesImage(), getItunesCategories(), getItunesExplicit(),
                getItunesAuthor(), getItunesOwner(), getItunesTitle(), getItunesSubtitle(), getItunesSummary(),
                getItunesType(), getItunesNewFeedUrl(), isItunesBlock(), isItunesComplete());
    }
}
