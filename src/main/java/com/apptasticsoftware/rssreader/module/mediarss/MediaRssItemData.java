package com.apptasticsoftware.rssreader.module.mediarss;

import com.apptasticsoftware.rssreader.module.mediarss.internal.MediaRssItemDataProvider;
import java.util.List;
import java.util.Optional;

public interface MediaRssItemData {
    /**
     * Returns the list of media content elements associated with this item.
     * Each item represents a media object, such as video, audio, or image, and may include attributes like URL, type, medium, duration, and more, as defined by the Media RSS specification.
     *
     * @return list of media content elements
     */
    default List<MediaContent> getMediaContents() {
        return ((MediaRssItemDataProvider) this).mediaRssItemData().getMediaContents();
    }

    /**
     * Adds a media content element to this item.
     * Use this to associate a new media object with the item.
     *
     * @param mediaContent the media content element to add
     */
    default void addMediaContents(MediaContent mediaContent) {
        ((MediaRssItemDataProvider) this).mediaRssItemData().addMediaContents(mediaContent);
    }

    /**
     * Returns the media group associated with this item, if present.
     * A group allows grouping of media objects that are different representations of the same content.
     *
     * @return optional media group
     */
    default Optional<MediaGroup> getMediaGroup() {
        return ((MediaRssItemDataProvider) this).mediaRssItemData().getMediaGroup();
    }

    /**
     * Sets the media group for this item.
     * Use this to group multiple media objects representing the same content in different formats.
     *
     * @param mediaGroup the media group to set
     */
    default void setMediaGroup(MediaGroup mediaGroup) {
        ((MediaRssItemDataProvider) this).mediaRssItemData().setMediaGroup(mediaGroup);
    }

    /**
     * Returns the list of media location elements for this item.
     * Each location specifies geographical information about places captured in the media content.
     *
     * @return list of media locations
     */
    default List<MediaLocation> getMediaLocations() {
        return ((MediaRssItemDataProvider) this).mediaRssItemData().getMediaLocations();
    }

    /**
     * Adds a media location element to this item.
     * Use this to specify a location referenced in the media object, including description and time offsets.
     *
     * @param mediaLocation the media location to add
     */
    default void addMediaLocation(MediaLocation mediaLocation) {
        ((MediaRssItemDataProvider) this).mediaRssItemData().addMediaLocation(mediaLocation);
    }

    /**
     * Gets a list of media rating that declares the permissible audience. If this element is not included,
     * it assumes that no restrictions are necessary. The rating can use different schemes like
     * urn:simple (adult | nonadult), urn:mpaa (g | pg | pg-13 | r | nc-17), urn:v-chip, or urn:icra.
     *
     * @return media rating or empty if not set
     */
    default List<MediaRating> getMediaRatings() {
        return ((MediaRssItemDataProvider) this).mediaRssItemData().getMediaRatings();
    }

    /**
     * Sets the media rating that declares the permissible audience. If this element is not included,
     * it assumes that no restrictions are necessary. The rating can use different schemes like
     * urn:simple (adult | nonadult), urn:mpaa (g | pg | pg-13 | r | nc-17), urn:v-chip, or urn:icra.
     *
     * @param mediaRating media rating
     */
    default void addMediaRating(MediaRating mediaRating) {
        ((MediaRssItemDataProvider) this).mediaRssItemData().addMediaRating(mediaRating);
    }

    /**
     * Returns the title for this item, if present.
     * The title provides a human-readable name for the media object, with optional plain or HTML text formatting.
     *
     * @return optional title
     */
    default Optional<MediaTitle> getMediaTitle() {
        return ((MediaRssItemDataProvider) this).mediaRssItemData().getMediaTitle();
    }

    /**
     * Sets the title for this item.
     * Use this to specify the name of the media object, supporting plain or HTML text.
     *
     * @param mediaTitle the title to set
     */
    default void setMediaTitle(MediaTitle mediaTitle) {
        ((MediaRssItemDataProvider) this).mediaRssItemData().setMediaTitle(mediaTitle);
    }

    /**
     * Returns the description for this item, if present.
     * The description provides a short summary of the media object, with optional plain or HTML text formatting.
     *
     * @return optional description
     */
    default Optional<MediaDescription> getMediaDescription() {
        return ((MediaRssItemDataProvider) this).mediaRssItemData().getMediaDescription();
    }

    /**
     * Sets the description for this item.
     * Use this to specify a short summary of the media object, supporting plain or HTML text.
     *
     * @param mediaDescription the description to set
     */
    default void setMediaDescription(MediaDescription mediaDescription) {
        ((MediaRssItemDataProvider) this).mediaRssItemData().setMediaDescription(mediaDescription);
    }

    /**
     * Returns the list of keywords describing the media object.
     * Keywords are comma-delimited and relevant to the media object, typically up to 10 words.
     *
     * @return list of keywords
     */
    default List<String> getMediaKeywords() {
        return ((MediaRssItemDataProvider) this).mediaRssItemData().getMediaKeywords();
    }

    /**
     * Adds a keyword describing the media object.
     * Use this to provide relevant keywords for indexing and discovery.
     *
     * @param mediaKeyword the keyword to add
     */
    default void addMediaKeyword(String mediaKeyword) {
        ((MediaRssItemDataProvider) this).mediaRssItemData().addMediaKeyword(mediaKeyword);
    }

    /**
     * Returns the list of thumbnails for this item.
     * Thumbnails are representative images for the media object, with attributes for URL, width, height, and time offset.
     *
     * @return list of thumbnails
     */
    default List<MediaThumbnail> getMediaThumbnails() {
        return ((MediaRssItemDataProvider) this).mediaRssItemData().getMediaThumbnails();
    }

    /**
     * Adds a thumbnail to this item.
     * Use this to associate a representative image with the media object.
     *
     * @param mediaThumbnail the thumbnail to add
     */
    default void addMediaThumbnail(MediaThumbnail mediaThumbnail) {
        ((MediaRssItemDataProvider) this).mediaRssItemData().addMediaThumbnail(mediaThumbnail);
    }

    /**
     * Returns the list of categories for this item.
     * Categories allow taxonomy assignment to indicate the type and contents of the media object, with optional scheme and label attributes.
     *
     * @return list of categories
     */
    default List<MediaCategory> getMediaCategories() {
        return ((MediaRssItemDataProvider) this).mediaRssItemData().getMediaCategories();
    }

    /**
     * Adds a category to this item.
     * Use this to specify taxonomy or categorization for the media object.
     *
     * @param mediaCategory the category to add
     */
    default void addMediaCategory(MediaCategory mediaCategory) {
        ((MediaRssItemDataProvider) this).mediaRssItemData().addMediaCategory(mediaCategory);
    }

    /**
     * Returns the list of hashes for this item.
     * Hashes provide a value for the media object, useful for duplicate detection and caching, with optional algorithm attribute.
     *
     * @return list of hashes
     */
    default List<MediaHash> getMediaHashes() {
        return ((MediaRssItemDataProvider) this).mediaRssItemData().getMediaHashes();
    }

    /**
     * Adds a hash to this item.
     * Use this to associate a hash value with the media object for identification purposes.
     *
     * @param mediaHash the hash to add
     */
    default void addMediaHash(MediaHash mediaHash) {
        ((MediaRssItemDataProvider) this).mediaRssItemData().addMediaHash(mediaHash);
    }

    /**
     * Returns the media player information for this item, if present.
     * The media player provides a URL to a web-based media player for the media object, with optional width and height attributes.
     *
     * @return optional media player information
     */
    default Optional<MediaPlayer> getMediaPlayer() {
        return ((MediaRssItemDataProvider) this).mediaRssItemData().getMediaPlayer();
    }

    /**
     * Sets the media player information for this item.
     * Use this to specify a web-based player URL for the media object, with optional dimensions.
     *
     * @param mediaPlayer the media player information to set
     */
    default void setMediaPlayer(MediaPlayer mediaPlayer) {
        ((MediaRssItemDataProvider) this).mediaRssItemData().setMediaPlayer(mediaPlayer);
    }

    /**
     * Returns the list of credits for this item.
     * Credits identify notable entities and their contributions to the creation of the media object, with optional role and scheme attributes.
     *
     * @return list of credits
     */
    default List<MediaCredit> getMediaCredits() {
        return ((MediaRssItemDataProvider) this).mediaRssItemData().getMediaCredits();
    }

    /**
     * Adds a credit to this item.
     * Use this to specify contributors, such as people or companies, and their roles in the media object.
     *
     * @param mediaCredit the credit to add
     */
    default void addMediaCredit(MediaCredit mediaCredit) {
        ((MediaRssItemDataProvider) this).mediaRssItemData().addMediaCredit(mediaCredit);
    }

    /**
     * Returns the copyright information for this item, if present.
     * Copyright information may include a URL for terms of use or additional details.
     *
     * @return optional copyright information
     */
    default Optional<MediaCopyright> getMediaCopyright() {
        return ((MediaRssItemDataProvider) this).mediaRssItemData().getMediaCopyright();
    }

    /**
     * Sets the copyright information for this item.
     * Use this to specify copyright details for the media object.
     *
     * @param mediaCopyright the copyright information to set
     */
    default void setMediaCopyright(MediaCopyright mediaCopyright) {
        ((MediaRssItemDataProvider) this).mediaRssItemData().setMediaCopyright(mediaCopyright);
    }

    /**
     * Returns the list of text elements for this item.
     * Text elements allow inclusion of transcripts, closed captioning, or lyrics, with support for time offsets and language.
     *
     * @return list of text elements
     */
    default List<MediaText> getMediaTexts() {
        return ((MediaRssItemDataProvider) this).mediaRssItemData().getMediaTexts();
    }

    /**
     * Adds a text element to this item.
     * Use this to provide transcripts, captions, or lyrics for the media object.
     *
     * @param mediaText the text element to add
     */
    default void addMediaText(MediaText mediaText) {
        ((MediaRssItemDataProvider) this).mediaRssItemData().addMediaText(mediaText);
    }

    /**
     * Returns the list of restrictions for this item.
     * Restrictions allow specification of limitations on the media object, such as geographic, URI, or sharing restrictions.
     *
     * @return list of restrictions
     */
    default List<MediaRestriction> getMediaRestrictions() {
        return ((MediaRssItemDataProvider) this).mediaRssItemData().getMediaRestrictions();
    }

    /**
     * Adds a restriction to this item.
     * Use this to specify syndication or sharing restrictions for the media object.
     *
     * @param mediaRestriction the restriction to add
     */
    default void addMediaRestriction(MediaRestriction mediaRestriction) {
        ((MediaRssItemDataProvider) this).mediaRssItemData().addMediaRestriction(mediaRestriction);
    }

    /**
     * Returns the community information for this item, if present.
     * Community information includes feedback, ratings, statistics, and tags for the media object.
     *
     * @return optional community information
     */
    default Optional<MediaCommunity> getMediaCommunity() {
        return ((MediaRssItemDataProvider) this).mediaRssItemData().getMediaCommunity();
    }

    /**
     * Sets the community information for this item.
     * Use this to include community feedback, ratings, statistics, and tags for the media object.
     *
     * @param mediaCommunity the community information to set
     */
    default void setMediaCommunity(MediaCommunity mediaCommunity) {
        ((MediaRssItemDataProvider) this).mediaRssItemData().setMediaCommunity(mediaCommunity);
    }

    /**
     * Returns the list of comments for this item.
     * Comments allow inclusion of user feedback associated with the media object.
     *
     * @return list of comments
     */
    default List<String> getMediaComments() {
        return ((MediaRssItemDataProvider) this).mediaRssItemData().getMediaComments();
    }

    /**
     * Adds a comment to this item.
     * Use this to associate user feedback with the media object.
     *
     * @param mediaComment the comment to add
     */
    default void addMediaComment(String mediaComment) {
        ((MediaRssItemDataProvider) this).mediaRssItemData().addMediaComment(mediaComment);
    }

    /**
     * Returns the embed information for this item, if present.
     * Embed information allows inclusion of player-specific embed code for the media object, with support for key-value parameters.
     *
     * @return optional embed information
     */
    default Optional<MediaEmbed> getMediaEmbed() {
        return ((MediaRssItemDataProvider) this).mediaRssItemData().getMediaEmbed();
    }

    /**
     * Sets the embed information for this item.
     * Use this to provide embeddable player code for the media object.
     *
     * @param mediaEmbed the embed information to set
     */
    default void setMediaEmbed(MediaEmbed mediaEmbed) {
        ((MediaRssItemDataProvider) this).mediaRssItemData().setMediaEmbed(mediaEmbed);
    }

    /**
     * Returns the list of responses for this item.
     * Responses allow inclusion of feedback or reactions received by the media object.
     *
     * @return list of responses
     */
    default List<String> getMediaResponses() {
        return ((MediaRssItemDataProvider) this).mediaRssItemData().getMediaResponses();
    }

    /**
     * Adds a response to this item.
     * Use this to associate feedback or reactions with the media object.
     *
     * @param mediaResponse the response to add
     */
    default void addMediaResponse(String mediaResponse) {
        ((MediaRssItemDataProvider) this).mediaRssItemData().addMediaResponse(mediaResponse);
    }

    /**
     * Returns the list of back links for this item.
     * Back links allow inclusion of URLs referencing the media object.
     *
     * @return list of back links
     */
    default List<String> getMediaBackLinks() {
        return ((MediaRssItemDataProvider) this).mediaRssItemData().getMediaBackLinks();
    }

    /**
     * Adds a back link to this item.
     * Use this to associate URLs that reference the media object.
     *
     * @param mediaBackLink the back link to add
     */
    default void addMediaBackLink(String mediaBackLink) {
        ((MediaRssItemDataProvider) this).mediaRssItemData().addMediaBackLink(mediaBackLink);
    }

    /**
     * Returns the status information for this item, if present.
     * Status information specifies the current state of the media object, such as active, blocked, or deleted, and may include a reason.
     *
     * @return optional status information
     */
    default Optional<MediaStatus> getMediaStatus() {
        return ((MediaRssItemDataProvider) this).mediaRssItemData().getMediaStatus();
    }

    /**
     * Sets the status information for this item.
     * Use this to specify the current state of the media object, including reason for blocking or deletion.
     *
     * @param mediaStatus the status information to set
     */
    default void setMediaStatus(MediaStatus mediaStatus) {
        ((MediaRssItemDataProvider) this).mediaRssItemData().setMediaStatus(mediaStatus);
    }

    /**
     * Returns the list of prices for this item.
     * Price information includes pricing details for the media object, supporting multiple pricing structures and currencies.
     *
     * @return list of prices
     */
    default List<MediaPrice> getMediaPrices() {
        return ((MediaRssItemDataProvider) this).mediaRssItemData().getMediaPrices();
    }

    /**
     * Adds a price to this item.
     * Use this to specify pricing details for the media object, including type, price, currency, and info URL.
     *
     * @param mediaPrice the price to add
     */
    default void addMediaPrice(MediaPrice mediaPrice) {
        ((MediaRssItemDataProvider) this).mediaRssItemData().addMediaPrice(mediaPrice);
    }

    /**
     * Returns the license information for this item, if present.
     * License information provides machine-readable details about the rights associated with the media object.
     *
     * @return optional license information
     */
    default List<MediaLicense> getMediaLicenses() {
        return ((MediaRssItemDataProvider) this).mediaRssItemData().getMediaLicenses();
    }

    /**
     * Sets the license information for this item.
     * Use this to specify license details for the media object.
     *
     * @param mediaLicense the license information to set
     */
    default void addMediaLicense(MediaLicense mediaLicense) {
        ((MediaRssItemDataProvider) this).mediaRssItemData().addMediaLicense(mediaLicense);
    }

    /**
     * Returns the list of subtitles for this item.
     * Subtitles provide links for subtitle or closed captioning, supporting multiple languages and types.
     *
     * @return list of subtitles
     */
    default List<MediaSubTitle> getMediaSubTitles() {
        return ((MediaRssItemDataProvider) this).mediaRssItemData().getMediaSubTitles();
    }

    /**
     * Adds a subtitle to this item.
     * Use this to associate subtitle or closed captioning links with the media object.
     *
     * @param mediaSubTitle the subtitle to add
     */
    default void addMediaSubTitle(MediaSubTitle mediaSubTitle) {
        ((MediaRssItemDataProvider) this).mediaRssItemData().addMediaSubTitle(mediaSubTitle);
    }

    /**
     * Returns the list of peer links for this item.
     * Peer links provide P2P links for the media object, such as BitTorrent files.
     *
     * @return list of peer links
     */
    default List<MediaPeerLink> getMediaPeerLinks() {
        return ((MediaRssItemDataProvider) this).mediaRssItemData().getMediaPeerLinks();
    }

    /**
     * Adds a peer link to this item.
     * Use this to associate P2P links with the media object.
     *
     * @param mediaPeerLink the peer link to add
     */
    default void addMediaPeerLink(MediaPeerLink mediaPeerLink) {
        ((MediaRssItemDataProvider) this).mediaRssItemData().addMediaPeerLink(mediaPeerLink);
    }

    /**
     * Returns the rights information of a media object, if present.
     * Rights information specifies the rights status of the media object, such as user-created or official.
     *
     * @return optional rights information
     */
    default Optional<MediaRights> getMediaRights() {
        return ((MediaRssItemDataProvider) this).mediaRssItemData().getMediaRights();
    }

    /**
     * Sets the rights information of a media object.
     * Use this to specify the rights status of the media object.
     *
     * @param mediaRights the rights information to set
     */
    default void setMediaRights(MediaRights mediaRights) {
        ((MediaRssItemDataProvider) this).mediaRssItemData().setMediaRights(mediaRights);
    }

    /**
     * Returns the list of scenes for this item.
     * Scene information specifies details about specific segments within the media object, such as title, description, and time offsets.
     *
     * @return list of scenes
     */
    default List<MediaScene> getMediaScenes() {
        return ((MediaRssItemDataProvider) this).mediaRssItemData().getMediaScenes();
    }

    /**
     * Adds a scene to this item.
     * Use this to associate scene-specific information, such as title, description, and time offsets, with the media object.
     *
     * @param mediaScenes the scene to add
     */
    default void addMediaScene(MediaScene mediaScenes) {
        ((MediaRssItemDataProvider) this).mediaRssItemData().addMediaScene(mediaScenes);
    }
}
