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
    private final List<MediaLocation> mediaLocations = new ArrayList<>();

    // Optional Elements
    private MediaRating mediaRating;
    private MediaTitle mediaTitle;
    private MediaDescription mediaDescription;
    private final List<String> mediaKeywords = new ArrayList<>();
    private final List<MediaThumbnail> mediaThumbnails = new ArrayList<>();
    private final List<MediaCategory> mediaCategories = new ArrayList<>();
    private final List<MediaHash> mediaHashes = new ArrayList<>();
    // media:player
    private final List<MediaCredit> mediaCredits = new ArrayList<>();
    private MediaCopyright mediaCopyright;
    private final List<MediaText> mediaTexts = new ArrayList<>();
    private final List<MediaRestriction> mediaRestrictions = new ArrayList<>();
    private MediaCommunity mediaCommunity;
    private final List<String> mediaComments = new ArrayList<>();
    private MediaEmbed mediaEmbed;
    private final List<String> mediaResponses = new ArrayList<>();
    private final List<String> mediaBackLinks = new ArrayList<>();
    private MediaStatus mediaStatus;
    private final List<MediaPrice> mediaPrices = new ArrayList<>();
    private MediaLicense mediaLicense;
    private final List<MediaSubTitle> mediaSubTitles = new ArrayList<>();
    private final List<MediaPeerLink> mediaPeerLinks = new ArrayList<>();
    // media:rights
    private final List<MediaScene> mediaScenes = new ArrayList<>();


    // TODO: FIXED - missing: keywords, ratings
    // TODO: FIXED - thumbnail change from Optional to List
    // TODO: FIXED - Needed? MediaResponses change to String?
    // TODO: FIXED - Needed? MediaComments change to String?
    // TODO: FIXED - Needed? MediaKeywords change to String?
    // TODO: FIXED - Needed? MediaBacklinks change to String?
    // TODO: FIXED - Needed? MediaTags change to String?

    /*
    Missing mapping on Item level
    =============================
    media:keywords
    media:category
    media:player <-- missing field
    media:credit
    media:comments
    media:responses
    media:backLinks
    media:rights <-- missing field
    media:scenes
     */


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
     * Returns the list of media content elements associated with this item.
     * Each item represents a media object, such as video, audio, or image, and may include attributes like URL, type, medium, duration, and more, as defined by the Media RSS specification.
     *
     * @return list of media content elements
     */
    public List<MediaContent> getMediaContents() {
        return mediaContents;
    }

    /**
     * Adds a media content element to this item.
     * Use this to associate a new media object with the item.
     *
     * @param mediaContent the media content element to add
     */
    public void addMediaContents(MediaContent mediaContent) {
        mediaContents.add(mediaContent);
    }

    /**
     * Returns the media group associated with this item, if present.
     * A group allows grouping of media objects that are different representations of the same content.
     *
     * @return optional media group
     */
    public Optional<MediaGroup> getMediaGroup() {
        return Optional.ofNullable(mediaGroup);
    }

    /**
     * Sets the media group for this item.
     * Use this to group multiple media objects representing the same content in different formats.
     *
     * @param mediaGroup the media group to set
     */
    public void setMediaGroup(MediaGroup mediaGroup) {
        this.mediaGroup = mediaGroup;
    }

    /**
     * Returns the list of media location elements for this item.
     * Each location specifies geographical information about places captured in the media content.
     *
     * @return list of media locations
     */
    public List<MediaLocation> getMediaLocations() {
        return mediaLocations;
    }

    /**
     * Adds a media location element to this item.
     * Use this to specify a location referenced in the media object, including description and time offsets.
     *
     * @param mediaLocation the media location to add
     */
    public void addMediaLocation(MediaLocation mediaLocation) {
        this.mediaLocations.add(mediaLocation);
    }

    // Optional

    /**
     * Gets the media rating that declares the permissible audience. If this element is not included,
     * it assumes that no restrictions are necessary. The rating can use different schemes like
     * urn:simple (adult | nonadult), urn:mpaa (g | pg | pg-13 | r | nc-17), urn:v-chip, or urn:icra.
     *
     * @return media rating or empty if not set
     */
    public Optional<MediaRating> getMediaRating() {
        return Optional.ofNullable(mediaRating);
    }

    /**
     * Sets the media rating that declares the permissible audience. If this element is not included,
     * it assumes that no restrictions are necessary. The rating can use different schemes like
     * urn:simple (adult | nonadult), urn:mpaa (g | pg | pg-13 | r | nc-17), urn:v-chip, or urn:icra.
     *
     * @param mediaRating media rating
     */
    public void setMediaRating(MediaRating mediaRating) {
        this.mediaRating = mediaRating;
    }

    /**
     * Returns the title for this item, if present.
     * The title provides a human-readable name for the media object, with optional plain or HTML text formatting.
     *
     * @return optional title
     */
    public Optional<MediaTitle> getMediaTitle() {
        return Optional.ofNullable(mediaTitle);
    }

    /**
     * Sets the title for this item.
     * Use this to specify the name of the media object, supporting plain or HTML text.
     *
     * @param mediaTitle the title to set
     */
    public void setMediaTitle(MediaTitle mediaTitle) {
        this.mediaTitle = mediaTitle;
    }

    /**
     * Returns the description for this item, if present.
     * The description provides a short summary of the media object, with optional plain or HTML text formatting.
     *
     * @return optional description
     */
    public Optional<MediaDescription> getMediaDescription() {
        return Optional.ofNullable(mediaDescription);
    }

    /**
     * Sets the description for this item.
     * Use this to specify a short summary of the media object, supporting plain or HTML text.
     *
     * @param mediaDescription the description to set
     */
    public void setMediaDescription(MediaDescription mediaDescription) {
        this.mediaDescription = mediaDescription;
    }

    /**
     * Returns the list of keywords describing the media object.
     * Keywords are comma-delimited and relevant to the media object, typically up to 10 words.
     *
     * @return list of keywords
     */
    public List<String> getMediaKeywords() {
        return mediaKeywords;
    }

    /**
     * Adds a keyword describing the media object.
     * Use this to provide relevant keywords for indexing and discovery.
     *
     * @param mediaKeyword the keyword to add
     */
    public void addMediaKeyword(String mediaKeyword) {
        this.mediaKeywords.add(mediaKeyword);
    }

    /**
     * Returns the list of thumbnails for this item.
     * Thumbnails are representative images for the media object, with attributes for URL, width, height, and time offset.
     *
     * @return list of thumbnails
     */
    public List<MediaThumbnail> getMediaThumbnails() {
        return mediaThumbnails;
    }

    /**
     * Adds a thumbnail to this item.
     * Use this to associate a representative image with the media object.
     *
     * @param mediaThumbnail the thumbnail to add
     */
    public void addMediaThumbnail(MediaThumbnail mediaThumbnail) {
        this.mediaThumbnails.add(mediaThumbnail);
    }

    /**
     * Returns the list of categories for this item.
     * Categories allow taxonomy assignment to indicate the type and contents of the media object, with optional scheme and label attributes.
     *
     * @return list of categories
     */
    public List<MediaCategory> getMediaCategories() {
        return mediaCategories;
    }

    /**
     * Adds a category to this item.
     * Use this to specify taxonomy or categorization for the media object.
     *
     * @param mediaCategory the category to add
     */
    public void addMediaCategory(MediaCategory mediaCategory) {
        mediaCategories.add(mediaCategory);
    }

    /**
     * Returns the list of hashes for this item.
     * Hashes provide a value for the media object, useful for duplicate detection and caching, with optional algorithm attribute.
     *
     * @return list of hashes
     */
    public List<MediaHash> getMediaHashes() {
        return mediaHashes;
    }

    /**
     * Adds a hash to this item.
     * Use this to associate a hash value with the media object for identification purposes.
     *
     * @param mediaHash the hash to add
     */
    public void addMediaHash(MediaHash mediaHash) {
        mediaHashes.add(mediaHash);
    }

    /**
     * Returns the list of credits for this item.
     * Credits identify notable entities and their contributions to the creation of the media object, with optional role and scheme attributes.
     *
     * @return list of credits
     */
    public List<MediaCredit> getMediaCredits() {
        return mediaCredits;
    }

    /**
     * Adds a credit to this item.
     * Use this to specify contributors, such as people or companies, and their roles in the media object.
     *
     * @param mediaCredit the credit to add
     */
    public void addMediaCredit(MediaCredit mediaCredit) {
        mediaCredits.add(mediaCredit);
    }

    /**
     * Returns the copyright information for this item, if present.
     * Copyright information may include a URL for terms of use or additional details.
     *
     * @return optional copyright information
     */
    public Optional<MediaCopyright> getMediaCopyright() {
        return Optional.ofNullable(mediaCopyright);
    }

    /**
     * Sets the copyright information for this item.
     * Use this to specify copyright details for the media object.
     *
     * @param mediaCopyright the copyright information to set
     */
    public void setMediaCopyright(MediaCopyright mediaCopyright) {
        this.mediaCopyright = mediaCopyright;
    }

    /**
     * Returns the list of text elements for this item.
     * Text elements allow inclusion of transcripts, closed captioning, or lyrics, with support for time offsets and language.
     *
     * @return list of text elements
     */
    public List<MediaText> getMediaTexts() {
        return mediaTexts;
    }

    /**
     * Adds a text element to this item.
     * Use this to provide transcripts, captions, or lyrics for the media object.
     *
     * @param mediaText the text element to add
     */
    public void addMediaText(MediaText mediaText) {
        mediaTexts.add(mediaText);
    }

    /**
     * Returns the list of restrictions for this item.
     * Restrictions allow specification of limitations on the media object, such as geographic, URI, or sharing restrictions.
     *
     * @return list of restrictions
     */
    public List<MediaRestriction> getMediaRestrictions() {
        return mediaRestrictions;
    }

    /**
     * Adds a restriction to this item.
     * Use this to specify syndication or sharing restrictions for the media object.
     *
     * @param mediaRestriction the restriction to add
     */
    public void addMediaRestriction(MediaRestriction mediaRestriction) {
        this.mediaRestrictions.add(mediaRestriction);
    }

    /**
     * Returns the community information for this item, if present.
     * Community information includes feedback, ratings, statistics, and tags for the media object.
     *
     * @return optional community information
     */
    public Optional<MediaCommunity> getMediaCommunity() {
        return Optional.ofNullable(mediaCommunity);
    }

    /**
     * Sets the community information for this item.
     * Use this to include community feedback, ratings, statistics, and tags for the media object.
     *
     * @param mediaCommunity the community information to set
     */
    public void setMediaCommunity(MediaCommunity mediaCommunity) {
        this.mediaCommunity = mediaCommunity;
    }

    /**
     * Returns the list of comments for this item.
     * Comments allow inclusion of user feedback associated with the media object.
     *
     * @return list of comments
     */
    public List<String> getMediaComments() {
        return mediaComments;
    }

    /**
     * Adds a comment to this item.
     * Use this to associate user feedback with the media object.
     *
     * @param mediaComment the comment to add
     */
    public void addMediaComment(String mediaComment) {
        this.mediaComments.add(mediaComment);
    }

    /**
     * Returns the embed information for this item, if present.
     * Embed information allows inclusion of player-specific embed code for the media object, with support for key-value parameters.
     *
     * @return optional embed information
     */
    public Optional<MediaEmbed> getMediaEmbed() {
        return Optional.ofNullable(mediaEmbed);
    }

    /**
     * Sets the embed information for this item.
     * Use this to provide embeddable player code for the media object.
     *
     * @param mediaEmbed the embed information to set
     */
    public void setMediaEmbed(MediaEmbed mediaEmbed) {
        this.mediaEmbed = mediaEmbed;
    }

    /**
     * Returns the list of responses for this item.
     * Responses allow inclusion of feedback or reactions received by the media object.
     *
     * @return list of responses
     */
    public List<String> getMediaResponses() {
        return mediaResponses;
    }

    /**
     * Adds a response to this item.
     * Use this to associate feedback or reactions with the media object.
     *
     * @param mediaResponse the response to add
     */
    public void addMediaResponse(String mediaResponse) {
        this.mediaResponses.add(mediaResponse);
    }

    /**
     * Returns the list of back links for this item.
     * Back links allow inclusion of URLs referencing the media object.
     *
     * @return list of back links
     */
    public List<String> getMediaBackLinks() {
        return mediaBackLinks;
    }

    /**
     * Adds a back link to this item.
     * Use this to associate URLs that reference the media object.
     *
     * @param mediaBackLink the back link to add
     */
    public void addMediaBackLink(String mediaBackLink) {
        this.mediaBackLinks.add(mediaBackLink);
    }

    /**
     * Returns the status information for this item, if present.
     * Status information specifies the current state of the media object, such as active, blocked, or deleted, and may include a reason.
     *
     * @return optional status information
     */
    public Optional<MediaStatus> getMediaStatus() {
        return Optional.ofNullable(mediaStatus);
    }

    /**
     * Sets the status information for this item.
     * Use this to specify the current state of the media object, including reason for blocking or deletion.
     *
     * @param mediaStatus the status information to set
     */
    public void setMediaStatus(MediaStatus mediaStatus) {
        this.mediaStatus = mediaStatus;
    }

    /**
     * Returns the list of prices for this item.
     * Price information includes pricing details for the media object, supporting multiple pricing structures and currencies.
     *
     * @return list of prices
     */
    public List<MediaPrice> getMediaPrice() {
        return mediaPrices;
    }

    /**
     * Adds a price to this item.
     * Use this to specify pricing details for the media object, including type, price, currency, and info URL.
     *
     * @param mediaPrice the price to add
     */
    public void addMediaPrices(MediaPrice mediaPrice) {
        mediaPrices.add(mediaPrice);
    }

    /**
     * Returns the license information for this item, if present.
     * License information provides machine-readable details about the rights associated with the media object.
     *
     * @return optional license information
     */
    public Optional<MediaLicense> getMediaLicense() {
        return Optional.ofNullable(mediaLicense);
    }

    /**
     * Sets the license information for this item.
     * Use this to specify license details for the media object.
     *
     * @param mediaLicense the license information to set
     */
    public void setMediaLicense(MediaLicense mediaLicense) {
        this.mediaLicense = mediaLicense;
    }

    /**
     * Returns the list of subtitles for this item.
     * Subtitles provide links for subtitle or closed captioning, supporting multiple languages and types.
     *
     * @return list of subtitles
     */
    public List<MediaSubTitle> getMediaSubTitles() {
        return mediaSubTitles;
    }

    /**
     * Adds a subtitle to this item.
     * Use this to associate subtitle or closed captioning links with the media object.
     *
     * @param mediaSubTitle the subtitle to add
     */
    public void addMediaSubTitle(MediaSubTitle mediaSubTitle) {
        this.mediaSubTitles.add(mediaSubTitle);
    }

    /**
     * Returns the list of peer links for this item.
     * Peer links provide P2P links for the media object, such as BitTorrent files.
     *
     * @return list of peer links
     */
    public List<MediaPeerLink> getMediaPeerLinks() {
        return mediaPeerLinks;
    }

    /**
     * Adds a peer link to this item.
     * Use this to associate P2P links with the media object.
     *
     * @param mediaPeerLink the peer link to add
     */
    public void addMediaPeerLink(MediaPeerLink mediaPeerLink) {
        this.mediaPeerLinks.add(mediaPeerLink);
    }

    /**
     * Returns the list of scenes for this item.
     * Scene information specifies details about specific segments within the media object, such as title, description, and time offsets.
     *
     * @return list of scenes
     */
    public List<MediaScene> getMediaScenes() {
        return mediaScenes;
    }

    /**
     * Adds a scene to this item.
     * Use this to associate scene-specific information, such as title, description, and time offsets, with the media object.
     *
     * @param mediaScenes the scene to add
     */
    public void addMediaScene(MediaScene mediaScenes) {
        this.mediaScenes.add(mediaScenes);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MediaRssItem that = (MediaRssItem) o;
        return Objects.equals(getMediaContents(), that.getMediaContents()) && Objects.equals(getMediaGroup(), that.getMediaGroup()) && Objects.equals(getMediaRating(), that.getMediaRating()) && Objects.equals(getMediaCommunity(), that.getMediaCommunity()) && Objects.equals(getMediaComments(), that.getMediaComments()) && Objects.equals(getMediaResponses(), that.getMediaResponses()) && Objects.equals(getMediaBackLinks(), that.getMediaBackLinks()) && Objects.equals(getMediaThumbnails(), that.getMediaThumbnails()) && Objects.equals(getMediaKeywords(), that.getMediaKeywords()) && Objects.equals(getMediaHashes(), that.getMediaHashes()) && Objects.equals(getMediaCategories(), that.getMediaCategories()) && Objects.equals(getMediaTexts(), that.getMediaTexts()) && Objects.equals(getMediaCredits(), that.getMediaCredits()) && Objects.equals(getMediaStatus(), that.getMediaStatus()) && Objects.equals(mediaPrices, that.mediaPrices) && Objects.equals(getMediaLicense(), that.getMediaLicense()) && Objects.equals(mediaSubTitles, that.mediaSubTitles) && Objects.equals(getMediaPeerLinks(), that.getMediaPeerLinks()) && Objects.equals(getMediaRestrictions(), that.getMediaRestrictions()) && Objects.equals(getMediaLocations(), that.getMediaLocations()) && Objects.equals(getMediaScenes(), that.getMediaScenes()) && Objects.equals(getMediaEmbed(), that.getMediaEmbed()) && Objects.equals(getMediaTitle(), that.getMediaTitle()) && Objects.equals(getMediaDescription(), that.getMediaDescription()) && Objects.equals(getMediaCopyright(), that.getMediaCopyright());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getMediaContents(), getMediaGroup(), getMediaRating(), getMediaCommunity(), getMediaComments(), getMediaResponses(), getMediaBackLinks(), getMediaThumbnails(), getMediaKeywords(), getMediaHashes(), getMediaCategories(), getMediaTexts(), getMediaCredits(), getMediaStatus(), mediaPrices, getMediaLicense(), mediaSubTitles, getMediaPeerLinks(), getMediaRestrictions(), getMediaLocations(), getMediaScenes(), getMediaEmbed(), getMediaTitle(), getMediaDescription(), getMediaCopyright());
    }
}
