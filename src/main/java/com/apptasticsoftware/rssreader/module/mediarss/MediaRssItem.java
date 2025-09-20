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
public class MediaRssItem extends Item implements Metadata {
    private final List<MediaContent> mediaContents = new ArrayList<>();
    private MediaGroup mediaGroup;
    private final Metadata optionalFields = new MetadataImpl();

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
    @Override
    public List<MediaLocation> getMediaLocations() {
        return optionalFields.getMediaLocations();
    }

    /**
     * Adds a media location element to this item.
     * Use this to specify a location referenced in the media object, including description and time offsets.
     *
     * @param mediaLocation the media location to add
     */
    @Override
    public void addMediaLocation(MediaLocation mediaLocation) {
        optionalFields.addMediaLocation(mediaLocation);
    }

    // Optional

    /**
     * Gets a list of media rating that declares the permissible audience. If this element is not included,
     * it assumes that no restrictions are necessary. The rating can use different schemes like
     * urn:simple (adult | nonadult), urn:mpaa (g | pg | pg-13 | r | nc-17), urn:v-chip, or urn:icra.
     *
     * @return media rating or empty if not set
     */
    @Override
    public List<MediaRating> getMediaRatings() {
        return optionalFields.getMediaRatings();
    }

    /**
     * Sets the media rating that declares the permissible audience. If this element is not included,
     * it assumes that no restrictions are necessary. The rating can use different schemes like
     * urn:simple (adult | nonadult), urn:mpaa (g | pg | pg-13 | r | nc-17), urn:v-chip, or urn:icra.
     *
     * @param mediaRating media rating
     */
    @Override
    public void addMediaRating(MediaRating mediaRating) {
        optionalFields.addMediaRating(mediaRating);
    }

    /**
     * Returns the title for this item, if present.
     * The title provides a human-readable name for the media object, with optional plain or HTML text formatting.
     *
     * @return optional title
     */
    @Override
    public Optional<MediaTitle> getMediaTitle() {
        return optionalFields.getMediaTitle();
    }

    /**
     * Sets the title for this item.
     * Use this to specify the name of the media object, supporting plain or HTML text.
     *
     * @param mediaTitle the title to set
     */
    @Override
    public void setMediaTitle(MediaTitle mediaTitle) {
        optionalFields.setMediaTitle(mediaTitle);
    }

    /**
     * Returns the description for this item, if present.
     * The description provides a short summary of the media object, with optional plain or HTML text formatting.
     *
     * @return optional description
     */
    @Override
    public Optional<MediaDescription> getMediaDescription() {
        return optionalFields.getMediaDescription();
    }

    /**
     * Sets the description for this item.
     * Use this to specify a short summary of the media object, supporting plain or HTML text.
     *
     * @param mediaDescription the description to set
     */
    @Override
    public void setMediaDescription(MediaDescription mediaDescription) {
        optionalFields.setMediaDescription(mediaDescription);
    }

    /**
     * Returns the list of keywords describing the media object.
     * Keywords are comma-delimited and relevant to the media object, typically up to 10 words.
     *
     * @return list of keywords
     */
    @Override
    public List<String> getMediaKeywords() {
        return optionalFields.getMediaKeywords();
    }

    /**
     * Adds a keyword describing the media object.
     * Use this to provide relevant keywords for indexing and discovery.
     *
     * @param mediaKeyword the keyword to add
     */
    @Override
    public void addMediaKeyword(String mediaKeyword) {
        optionalFields.addMediaKeyword(mediaKeyword);
    }

    /**
     * Returns the list of thumbnails for this item.
     * Thumbnails are representative images for the media object, with attributes for URL, width, height, and time offset.
     *
     * @return list of thumbnails
     */
    @Override
    public List<MediaThumbnail> getMediaThumbnails() {
        return optionalFields.getMediaThumbnails();
    }

    /**
     * Adds a thumbnail to this item.
     * Use this to associate a representative image with the media object.
     *
     * @param mediaThumbnail the thumbnail to add
     */
    @Override
    public void addMediaThumbnail(MediaThumbnail mediaThumbnail) {
        optionalFields.addMediaThumbnail(mediaThumbnail);
    }

    /**
     * Returns the list of categories for this item.
     * Categories allow taxonomy assignment to indicate the type and contents of the media object, with optional scheme and label attributes.
     *
     * @return list of categories
     */
    @Override
    public List<MediaCategory> getMediaCategories() {
        return optionalFields.getMediaCategories();
    }

    /**
     * Adds a category to this item.
     * Use this to specify taxonomy or categorization for the media object.
     *
     * @param mediaCategory the category to add
     */
    @Override
    public void addMediaCategory(MediaCategory mediaCategory) {
        optionalFields.addMediaCategory(mediaCategory);
    }

    /**
     * Returns the list of hashes for this item.
     * Hashes provide a value for the media object, useful for duplicate detection and caching, with optional algorithm attribute.
     *
     * @return list of hashes
     */
    @Override
    public List<MediaHash> getMediaHashes() {
        return optionalFields.getMediaHashes();
    }

    /**
     * Adds a hash to this item.
     * Use this to associate a hash value with the media object for identification purposes.
     *
     * @param mediaHash the hash to add
     */
    @Override
    public void addMediaHash(MediaHash mediaHash) {
        optionalFields.addMediaHash(mediaHash);
    }

    /**
     * Returns the media player information for this item, if present.
     * The media player provides a URL to a web-based media player for the media object, with optional width and height attributes.
     *
     * @return optional media player information
     */
    @Override
    public Optional<MediaPlayer> getMediaPlayer() {
        return optionalFields.getMediaPlayer();
    }

    /**
     * Sets the media player information for this item.
     * Use this to specify a web-based player URL for the media object, with optional dimensions.
     *
     * @param mediaPlayer the media player information to set
     */
    @Override
    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        optionalFields.setMediaPlayer(mediaPlayer);
    }

    /**
     * Returns the list of credits for this item.
     * Credits identify notable entities and their contributions to the creation of the media object, with optional role and scheme attributes.
     *
     * @return list of credits
     */
    @Override
    public List<MediaCredit> getMediaCredits() {
        return optionalFields.getMediaCredits();
    }

    /**
     * Adds a credit to this item.
     * Use this to specify contributors, such as people or companies, and their roles in the media object.
     *
     * @param mediaCredit the credit to add
     */
    @Override
    public void addMediaCredit(MediaCredit mediaCredit) {
        optionalFields.addMediaCredit(mediaCredit);
    }

    /**
     * Returns the copyright information for this item, if present.
     * Copyright information may include a URL for terms of use or additional details.
     *
     * @return optional copyright information
     */
    @Override
    public Optional<MediaCopyright> getMediaCopyright() {
        return optionalFields.getMediaCopyright();
    }

    /**
     * Sets the copyright information for this item.
     * Use this to specify copyright details for the media object.
     *
     * @param mediaCopyright the copyright information to set
     */
    @Override
    public void setMediaCopyright(MediaCopyright mediaCopyright) {
        optionalFields.setMediaCopyright(mediaCopyright);
    }

    /**
     * Returns the list of text elements for this item.
     * Text elements allow inclusion of transcripts, closed captioning, or lyrics, with support for time offsets and language.
     *
     * @return list of text elements
     */
    @Override
    public List<MediaText> getMediaTexts() {
        return optionalFields.getMediaTexts();
    }

    /**
     * Adds a text element to this item.
     * Use this to provide transcripts, captions, or lyrics for the media object.
     *
     * @param mediaText the text element to add
     */
    @Override
    public void addMediaText(MediaText mediaText) {
        optionalFields.addMediaText(mediaText);
    }

    /**
     * Returns the list of restrictions for this item.
     * Restrictions allow specification of limitations on the media object, such as geographic, URI, or sharing restrictions.
     *
     * @return list of restrictions
     */
    @Override
    public List<MediaRestriction> getMediaRestrictions() {
        return optionalFields.getMediaRestrictions();
    }

    /**
     * Adds a restriction to this item.
     * Use this to specify syndication or sharing restrictions for the media object.
     *
     * @param mediaRestriction the restriction to add
     */
    @Override
    public void addMediaRestriction(MediaRestriction mediaRestriction) {
        optionalFields.addMediaRestriction(mediaRestriction);
    }

    /**
     * Returns the community information for this item, if present.
     * Community information includes feedback, ratings, statistics, and tags for the media object.
     *
     * @return optional community information
     */
    @Override
    public Optional<MediaCommunity> getMediaCommunity() {
        return optionalFields.getMediaCommunity();
    }

    /**
     * Sets the community information for this item.
     * Use this to include community feedback, ratings, statistics, and tags for the media object.
     *
     * @param mediaCommunity the community information to set
     */
    @Override
    public void setMediaCommunity(MediaCommunity mediaCommunity) {
        optionalFields.setMediaCommunity(mediaCommunity);
    }

    /**
     * Returns the list of comments for this item.
     * Comments allow inclusion of user feedback associated with the media object.
     *
     * @return list of comments
     */
    @Override
    public List<String> getMediaComments() {
        return optionalFields.getMediaComments();
    }

    /**
     * Adds a comment to this item.
     * Use this to associate user feedback with the media object.
     *
     * @param mediaComment the comment to add
     */
    @Override
    public void addMediaComment(String mediaComment) {
        optionalFields.addMediaComment(mediaComment);
    }

    /**
     * Returns the embed information for this item, if present.
     * Embed information allows inclusion of player-specific embed code for the media object, with support for key-value parameters.
     *
     * @return optional embed information
     */
    @Override
    public Optional<MediaEmbed> getMediaEmbed() {
        return optionalFields.getMediaEmbed();
    }

    /**
     * Sets the embed information for this item.
     * Use this to provide embeddable player code for the media object.
     *
     * @param mediaEmbed the embed information to set
     */
    @Override
    public void setMediaEmbed(MediaEmbed mediaEmbed) {
        optionalFields.setMediaEmbed(mediaEmbed);
    }

    /**
     * Returns the list of responses for this item.
     * Responses allow inclusion of feedback or reactions received by the media object.
     *
     * @return list of responses
     */
    @Override
    public List<String> getMediaResponses() {
        return optionalFields.getMediaResponses();
    }

    /**
     * Adds a response to this item.
     * Use this to associate feedback or reactions with the media object.
     *
     * @param mediaResponse the response to add
     */
    @Override
    public void addMediaResponse(String mediaResponse) {
        optionalFields.addMediaResponse(mediaResponse);
    }

    /**
     * Returns the list of back links for this item.
     * Back links allow inclusion of URLs referencing the media object.
     *
     * @return list of back links
     */
    @Override
    public List<String> getMediaBackLinks() {
        return optionalFields.getMediaBackLinks();
    }

    /**
     * Adds a back link to this item.
     * Use this to associate URLs that reference the media object.
     *
     * @param mediaBackLink the back link to add
     */
    @Override
    public void addMediaBackLink(String mediaBackLink) {
        optionalFields.addMediaBackLink(mediaBackLink);
    }

    /**
     * Returns the status information for this item, if present.
     * Status information specifies the current state of the media object, such as active, blocked, or deleted, and may include a reason.
     *
     * @return optional status information
     */
    @Override
    public Optional<MediaStatus> getMediaStatus() {
        return optionalFields.getMediaStatus();
    }

    /**
     * Sets the status information for this item.
     * Use this to specify the current state of the media object, including reason for blocking or deletion.
     *
     * @param mediaStatus the status information to set
     */
    @Override
    public void setMediaStatus(MediaStatus mediaStatus) {
        optionalFields.setMediaStatus(mediaStatus);
    }

    /**
     * Returns the list of prices for this item.
     * Price information includes pricing details for the media object, supporting multiple pricing structures and currencies.
     *
     * @return list of prices
     */
    @Override
    public List<MediaPrice> getMediaPrices() {
        return optionalFields.getMediaPrices();
    }

    /**
     * Adds a price to this item.
     * Use this to specify pricing details for the media object, including type, price, currency, and info URL.
     *
     * @param mediaPrice the price to add
     */
    @Override
    public void addMediaPrice(MediaPrice mediaPrice) {
        optionalFields.addMediaPrice(mediaPrice);
    }

    /**
     * Returns the license information for this item, if present.
     * License information provides machine-readable details about the rights associated with the media object.
     *
     * @return optional license information
     */
    @Override
    public List<MediaLicense> getMediaLicenses() {
        return optionalFields.getMediaLicenses();
    }

    /**
     * Sets the license information for this item.
     * Use this to specify license details for the media object.
     *
     * @param mediaLicense the license information to set
     */
    @Override
    public void addMediaLicense(MediaLicense mediaLicense) {
        optionalFields.addMediaLicense(mediaLicense);
    }

    /**
     * Returns the list of subtitles for this item.
     * Subtitles provide links for subtitle or closed captioning, supporting multiple languages and types.
     *
     * @return list of subtitles
     */
    @Override
    public List<MediaSubTitle> getMediaSubTitles() {
        return optionalFields.getMediaSubTitles();
    }

    /**
     * Adds a subtitle to this item.
     * Use this to associate subtitle or closed captioning links with the media object.
     *
     * @param mediaSubTitle the subtitle to add
     */
    @Override
    public void addMediaSubTitle(MediaSubTitle mediaSubTitle) {
        optionalFields.addMediaSubTitle(mediaSubTitle);
    }

    /**
     * Returns the list of peer links for this item.
     * Peer links provide P2P links for the media object, such as BitTorrent files.
     *
     * @return list of peer links
     */
    @Override
    public List<MediaPeerLink> getMediaPeerLinks() {
        return optionalFields.getMediaPeerLinks();
    }

    /**
     * Adds a peer link to this item.
     * Use this to associate P2P links with the media object.
     *
     * @param mediaPeerLink the peer link to add
     */
    @Override
    public void addMediaPeerLink(MediaPeerLink mediaPeerLink) {
        optionalFields.addMediaPeerLink(mediaPeerLink);
    }

    /**
     * Returns the rights information of a media object, if present.
     * Rights information specifies the rights status of the media object, such as user-created or official.
     *
     * @return optional rights information
     */
    @Override
    public Optional<MediaRights> getMediaRights() {
        return optionalFields.getMediaRights();
    }

    /**
     * Sets the rights information of a media object.
     * Use this to specify the rights status of the media object.
     *
     * @param mediaRights the rights information to set
     */
    @Override
    public void setMediaRights(MediaRights mediaRights) {
        optionalFields.setMediaRights(mediaRights);
    }

    /**
     * Returns the list of scenes for this item.
     * Scene information specifies details about specific segments within the media object, such as title, description, and time offsets.
     *
     * @return list of scenes
     */
    @Override
    public List<MediaScene> getMediaScenes() {
        return optionalFields.getMediaScenes();
    }

    /**
     * Adds a scene to this item.
     * Use this to associate scene-specific information, such as title, description, and time offsets, with the media object.
     *
     * @param mediaScenes the scene to add
     */
    @Override
    public void addMediaScene(MediaScene mediaScenes) {
        optionalFields.addMediaScene(mediaScenes);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MediaRssItem that = (MediaRssItem) o;
        return Objects.equals(getMediaContents(), that.getMediaContents()) && Objects.equals(getMediaGroup(), that.getMediaGroup()) && Objects.equals(optionalFields, that.optionalFields);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getMediaContents(), getMediaGroup(), optionalFields);
    }
}
