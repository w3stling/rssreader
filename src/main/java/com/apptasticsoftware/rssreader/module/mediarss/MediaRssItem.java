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
package com.apptasticsoftware.rssreader.module.mediarss;

import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Class representing the media rss item.
 */
public class MediaRssItem extends Item {
    private final List<MediaContent> mediaContents = new ArrayList<>();
    private MediaGroup mediaGroup;
    private MediaCommunity mediaCommunity;
    private final List<String> mediaComments = new ArrayList<>();
    private final List<String> mediaResponses = new ArrayList<>();
    private final List<String> mediaBackLinks = new ArrayList<>();
    private MediaThumbnail mediaThumbnail;
    private final List<String> mediaKeywords = new ArrayList<>();
    private final List<MediaHash> mediaHashes = new ArrayList<>();
    private final List<MediaCategory> mediaCategories = new ArrayList<>();
    private final List<MediaText> mediaTexts = new ArrayList<>();
    private final List<MediaCredit> mediaCredits = new ArrayList<>();
    private MediaStatus mediaStatus;
    private final List<MediaPrice> mediaPrices = new ArrayList<>();
    private MediaLicense mediaLicense;
    private final List<MediaSubTitle> mediaSubTitles = new ArrayList<>();
    private final List<MediaPeerLink> mediaPeerLinks = new ArrayList<>();
    private final List<MediaRestriction> mediaRestrictions = new ArrayList<>();
    private final List<MediaLocation> mediaLocations = new ArrayList<>();
    private final List<MediaScene> mediaScenes = new ArrayList<>();
    private MediaEmbed mediaEmbed;
    private MediaTitle mediaTitle;
    private MediaDescription mediaDescription;
    private MediaCopyright mediaCopyright;

    // TODO: missing: keywords, rating
    // TODO: thumbnail change from Optional to List
    // TODO: OK - Needed? MediaResponses change to String?
    // TODO: OK - Needed? MediaComments change to String?
    // TODO: OK - Needed? MediaKeywords change to String?
    // TODO: OK - Needed? MediaBacklinks change to String?
    // TODO: Needed? MediaTags change to String?

    // MediaRatings[]??, MediaThumbnail[]??, MediaLicense[]??


    /**
     * Constructor
     *
     * @param dateTimeParser timestamp parser
     */
    public MediaRssItem(DateTimeParser dateTimeParser) {
        super(dateTimeParser);
    }

    /**
     * Get the media content.
     *
     * @return media content
     */
    public List<MediaContent> getMediaContents() {
        return mediaContents;
    }

    /**
     * Set the media content.
     *
     * @param mediaContent media content
     */
    public void addMediaContents(MediaContent mediaContent) {
        mediaContents.add(mediaContent);
    }

    /**
     * Get the media group.
     *
     * @return media group
     */
    public Optional<MediaGroup> getMediaGroup() {
        return Optional.ofNullable(mediaGroup);
    }

    /**
     * Set the media group.
     *
     * @param mediaGroup media group
     */
    public void setMediaGroup(MediaGroup mediaGroup) {
        this.mediaGroup = mediaGroup;
    }

    /**
     * Get the media community.
     *
     * @return media community
     */
    public Optional<MediaCommunity> getMediaCommunity() {
        return Optional.ofNullable(mediaCommunity);
    }

    /**
     * Add media community.
     *
     * @param mediaCommunity media community
     */
    public void setMediaCommunity(MediaCommunity mediaCommunity) {
        this.mediaCommunity = mediaCommunity;
    }

    /**
     * Get the media comments.
     *
     * @return media comments
     */
    public List<String> getMediaComments() {
        return mediaComments;
    }

    /**
     * Add media comment.
     *
     * @param mediaComment media comment
     */
    public void addMediaComment(String mediaComment) {
        this.mediaComments.add(mediaComment);
    }

    /**
     * Get the media responses.
     *
     * @return media responses
     */
    public List<String> getMediaResponses() {
        return mediaResponses;
    }

    /**
     * Add media response.
     *
     * @param mediaResponse media response
     */
    public void addMediaResponse(String mediaResponse) {
        this.mediaResponses.add(mediaResponse);
    }

    /**
     * Get the media back links.
     *
     * @return media back links
     */
    public List<String> getMediaBackLinks() {
        return mediaBackLinks;
    }

    /**
     * Add media back link.
     *
     * @param mediaBackLink media back link
     */
    public void addMediaBackLink(String mediaBackLink) {
        this.mediaBackLinks.add(mediaBackLink);
    }

    /**
     * Get the media thumbnail
     *
     * @return media thumbnail
     */
    public Optional<MediaThumbnail> getMediaThumbnail() {
        return Optional.ofNullable(mediaThumbnail);
    }

    /**
     * Set the media thumbnail
     *
     * @param mediaThumbnail media thumbnail
     */
    public void setMediaThumbnail(MediaThumbnail mediaThumbnail) {
        this.mediaThumbnail = mediaThumbnail;
    }

    /**
     * Highly relevant keywords describing the media object with typically a maximum of 10 words.
     * @return media keywords
     */
    public List<String> getMediaKeywords() {
        return mediaKeywords;
    }

    /**
     * Highly relevant keywords describing the media object with typically a maximum of 10 words.
     * @param mediaKeyword media keywords
     */
    public void addMediaKeyword(String mediaKeyword) {
        this.mediaKeywords.add(mediaKeyword);
    }

    public List<MediaHash> getMediaHashes() {
        return mediaHashes;
    }

    public void addMediaHash(MediaHash mediaHash) {
        mediaHashes.add(mediaHash);
    }

    /**
     * Allows a taxonomy to be set that gives an indication of the type of media content, and its particular contents.
     *
     * @return media categories
     */
    public List<MediaCategory> getMediaCategories() {
        return mediaCategories;
    }

    /**
     * Allows a taxonomy to be set that gives an indication of the type of media content, and its particular contents.
     *
     * @param mediaCategory media category
     */
    public void addMediaCategories(MediaCategory mediaCategory) {
        mediaCategories.add(mediaCategory);
    }

    public List<MediaText> getMediaTexts() {
        return mediaTexts;
    }

    public void addMediaText(MediaText mediaText) {
        mediaTexts.add(mediaText);
    }

    public List<MediaCredit> getMediaCredits() {
        return mediaCredits;
    }

    public void addMediaCredits(MediaCredit mediaCredit) {
        mediaCredits.add(mediaCredit);
    }

    public Optional<MediaStatus> getMediaStatus() {
        return Optional.ofNullable(mediaStatus);
    }

    public void setMediaStatus(MediaStatus mediaStatus) {
        this.mediaStatus = mediaStatus;
    }

    public List<MediaPrice> getMediaPrice() {
        return mediaPrices;
    }

    public void addMediaPrices(MediaPrice mediaPrice) {
        mediaPrices.add(mediaPrice);
    }

    public Optional<MediaLicense> getMediaLicense() {
        return Optional.ofNullable(mediaLicense);
    }

    public void setMediaLicense(MediaLicense mediaLicense) {
        this.mediaLicense = mediaLicense;
    }

    public List<MediaSubTitle> getMediaSubTitle() {
        return mediaSubTitles;
    }

    public void addMediaSubTitle(MediaSubTitle mediaSubTitle) {
        this.mediaSubTitles.add(mediaSubTitle);
    }

    public List<MediaPeerLink> getMediaPeerLinks() {
        return mediaPeerLinks;
    }

    public void addMediaPeerLink(MediaPeerLink mediaPeerLink) {
        this.mediaPeerLinks.add(mediaPeerLink);
    }

    public List<MediaRestriction> getMediaRestrictions() {
        return mediaRestrictions;
    }

    public void addMediaRestriction(MediaRestriction mediaRestriction) {
        this.mediaRestrictions.add(mediaRestriction);
    }

    public List<MediaLocation> getMediaLocations() {
        return mediaLocations;
    }

    public void addMediaLocation(MediaLocation mediaLocation) {
        this.mediaLocations.add(mediaLocation);
    }

    public List<MediaScene> getMediaScenes() {
        return mediaScenes;
    }

    public void addMediaScene(MediaScene mediaScenes) {
        this.mediaScenes.add(mediaScenes);
    }

    public Optional<MediaEmbed> getMediaEmbed() {
        return Optional.ofNullable(mediaEmbed);
    }

    public void setMediaEmbed(MediaEmbed mediaEmbed) {
        this.mediaEmbed = mediaEmbed;
    }

    /**
     * The title of the particular media object.
     * @return media title
     */
    public Optional<MediaTitle> getMediaTitle() {
        return Optional.ofNullable(mediaTitle);
    }

    /**
     * The title of the particular media object.
     * @param mediaTitle media title
     */
    public void setMediaTitle(MediaTitle mediaTitle) {
        this.mediaTitle = mediaTitle;
    }

    /**
     * Short description describing the media object typically a sentence in length.
     * @return media description
     */
    public Optional<MediaDescription> getMediaDescription() {
        return Optional.ofNullable(mediaDescription);
    }

    /**
     * Short description describing the media object typically a sentence in length.
     * @param mediaDescription media description
     */
    public void setMediaDescription(MediaDescription mediaDescription) {
        this.mediaDescription = mediaDescription;
    }

    public Optional<MediaCopyright> getMediaCopyright() {
        return Optional.ofNullable(mediaCopyright);
    }

    public void setMediaCopyright(MediaCopyright mediaCopyright) {
        this.mediaCopyright = mediaCopyright;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MediaRssItem that = (MediaRssItem) o;
        return Objects.equals(getMediaContents(), that.getMediaContents()) && Objects.equals(getMediaGroup(), that.getMediaGroup()) && Objects.equals(getMediaCommunity(), that.getMediaCommunity()) && Objects.equals(getMediaComments(), that.getMediaComments()) && Objects.equals(getMediaResponses(), that.getMediaResponses()) && Objects.equals(getMediaBackLinks(), that.getMediaBackLinks()) && Objects.equals(getMediaThumbnail(), that.getMediaThumbnail()) && Objects.equals(getMediaKeywords(), that.getMediaKeywords()) && Objects.equals(getMediaHashes(), that.getMediaHashes()) && Objects.equals(getMediaCategories(), that.getMediaCategories()) && Objects.equals(getMediaTexts(), that.getMediaTexts()) && Objects.equals(getMediaCredits(), that.getMediaCredits()) && Objects.equals(getMediaStatus(), that.getMediaStatus()) && Objects.equals(mediaPrices, that.mediaPrices) && Objects.equals(getMediaLicense(), that.getMediaLicense()) && Objects.equals(mediaSubTitles, that.mediaSubTitles) && Objects.equals(getMediaPeerLinks(), that.getMediaPeerLinks()) && Objects.equals(getMediaRestrictions(), that.getMediaRestrictions()) && Objects.equals(getMediaLocations(), that.getMediaLocations()) && Objects.equals(getMediaScenes(), that.getMediaScenes()) && Objects.equals(getMediaEmbed(), that.getMediaEmbed()) && Objects.equals(getMediaTitle(), that.getMediaTitle()) && Objects.equals(getMediaDescription(), that.getMediaDescription()) && Objects.equals(getMediaCopyright(), that.getMediaCopyright());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getMediaContents(), getMediaGroup(), getMediaCommunity(), getMediaComments(), getMediaResponses(), getMediaBackLinks(), getMediaThumbnail(), getMediaKeywords(), getMediaHashes(), getMediaCategories(), getMediaTexts(), getMediaCredits(), getMediaStatus(), mediaPrices, getMediaLicense(), mediaSubTitles, getMediaPeerLinks(), getMediaRestrictions(), getMediaLocations(), getMediaScenes(), getMediaEmbed(), getMediaTitle(), getMediaDescription(), getMediaCopyright());
    }
}
