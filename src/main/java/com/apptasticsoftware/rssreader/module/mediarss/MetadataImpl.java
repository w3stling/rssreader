package com.apptasticsoftware.rssreader.module.mediarss;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class MetadataImpl implements Metadata {
    private final List<MediaRating> mediaRatings = new ArrayList<>();
    private MediaTitle mediaTitle;
    private MediaDescription mediaDescription;
    private final List<String> mediaKeywords = new ArrayList<>();
    private final List<MediaThumbnail> mediaThumbnails = new ArrayList<>();
    private final List<MediaCategory> mediaCategories = new ArrayList<>();
    private final List<MediaHash> mediaHashes = new ArrayList<>();
    private MediaPlayer mediaPlayer;
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
    private final List<MediaLicense> mediaLicenses = new ArrayList<>();
    private final List<MediaSubTitle> mediaSubTitles = new ArrayList<>();
    private final List<MediaPeerLink> mediaPeerLinks = new ArrayList<>();
    private final List<MediaLocation> mediaLocations = new ArrayList<>();
    private MediaRights mediaRights;
    private final List<MediaScene> mediaScenes = new ArrayList<>();

    /**
     * Gets a list of media rating that declares the permissible audience. If this element is not included,
     * it assumes that no restrictions are necessary. The rating can use different schemes like
     * urn:simple (adult | nonadult), urn:mpaa (g | pg | pg-13 | r | nc-17), urn:v-chip, or urn:icra.
     *
     * @return media rating or empty if not set
     */
    public List<MediaRating> getMediaRatings() {
        return mediaRatings;
    }

    /**
     * Sets the media rating that declares the permissible audience. If this element is not included,
     * it assumes that no restrictions are necessary. The rating can use different schemes like
     * urn:simple (adult | nonadult), urn:mpaa (g | pg | pg-13 | r | nc-17), urn:v-chip, or urn:icra.
     *
     * @param mediaRating media rating
     */
    public void addMediaRating(MediaRating mediaRating) {
        this.mediaRatings.add(mediaRating);
    }

    /**
     * Returns the title for this item, if present.
     * The title provides a human-readable name for the media object, with optional plain or HTML text formatting.
     *
     * @return optional title
     */
    @Override
    public Optional<MediaTitle> getMediaTitle() {
        return Optional.ofNullable(mediaTitle);
    }

    /**
     * Sets the title for this item.
     * Use this to specify the name of the media object, supporting plain or HTML text.
     *
     * @param mediaTitle the title to set
     */
    @Override
    public void setMediaTitle(MediaTitle mediaTitle) {
        this.mediaTitle = mediaTitle;
    }

    /**
     * Returns the description for this item, if present.
     * The description provides a short summary of the media object, with optional plain or HTML text formatting.
     *
     * @return optional description
     */
    @Override
    public Optional<MediaDescription> getMediaDescription() {
        return Optional.ofNullable(mediaDescription);
    }

    /**
     * Sets the description for this item.
     * Use this to specify a short summary of the media object, supporting plain or HTML text.
     *
     * @param mediaDescription the description to set
     */
    @Override
    public void setMediaDescription(MediaDescription mediaDescription) {
        this.mediaDescription = mediaDescription;
    }

    /**
     * Returns the list of keywords describing the media object.
     * Keywords are comma-delimited and relevant to the media object, typically up to 10 words.
     *
     * @return list of keywords
     */
    @Override
    public List<String> getMediaKeywords() {
        return mediaKeywords;
    }

    /**
     * Adds a keyword describing the media object.
     * Use this to provide relevant keywords for indexing and discovery.
     *
     * @param mediaKeyword the keyword to add
     */
    @Override
    public void addMediaKeyword(String mediaKeyword) {
        this.mediaKeywords.add(mediaKeyword);
    }

    /**
     * Returns the list of thumbnails for this item.
     * Thumbnails are representative images for the media object, with attributes for URL, width, height, and time offset.
     *
     * @return list of thumbnails
     */
    @Override
    public List<MediaThumbnail> getMediaThumbnails() {
        return mediaThumbnails;
    }

    /**
     * Adds a thumbnail to this item.
     * Use this to associate a representative image with the media object.
     *
     * @param mediaThumbnail the thumbnail to add
     */
    @Override
    public void addMediaThumbnail(MediaThumbnail mediaThumbnail) {
        this.mediaThumbnails.add(mediaThumbnail);
    }

    /**
     * Returns the list of categories for this item.
     * Categories allow taxonomy assignment to indicate the type and contents of the media object, with optional scheme and label attributes.
     *
     * @return list of categories
     */
    @Override
    public List<MediaCategory> getMediaCategories() {
        return mediaCategories;
    }

    /**
     * Adds a category to this item.
     * Use this to specify taxonomy or categorization for the media object.
     *
     * @param mediaCategory the category to add
     */
    @Override
    public void addMediaCategory(MediaCategory mediaCategory) {
        mediaCategories.add(mediaCategory);
    }

    /**
     * Returns the list of hashes for this item.
     * Hashes provide a value for the media object, useful for duplicate detection and caching, with optional algorithm attribute.
     *
     * @return list of hashes
     */
    @Override
    public List<MediaHash> getMediaHashes() {
        return mediaHashes;
    }

    /**
     * Adds a hash to this item.
     * Use this to associate a hash value with the media object for identification purposes.
     *
     * @param mediaHash the hash to add
     */
    @Override
    public void addMediaHash(MediaHash mediaHash) {
        mediaHashes.add(mediaHash);
    }

    /**
     * Returns the media player information for this item, if present.
     * The media player provides a URL to a web-based media player for the media object, with optional width and height attributes.
     *
     * @return optional media player information
     */
    public Optional<MediaPlayer> getMediaPlayer() {
        return Optional.ofNullable(mediaPlayer);
    }

    /**
     * Sets the media player information for this item.
     * Use this to specify a web-based player URL for the media object, with optional dimensions.
     *
     * @param mediaPlayer the media player information to set
     */
    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    /**
     * Returns the list of credits for this item.
     * Credits identify notable entities and their contributions to the creation of the media object, with optional role and scheme attributes.
     *
     * @return list of credits
     */
    @Override
    public List<MediaCredit> getMediaCredits() {
        return mediaCredits;
    }

    /**
     * Adds a credit to this item.
     * Use this to specify contributors, such as people or companies, and their roles in the media object.
     *
     * @param mediaCredit the credit to add
     */
    @Override
    public void addMediaCredit(MediaCredit mediaCredit) {
        mediaCredits.add(mediaCredit);
    }

    /**
     * Returns the copyright information for this item, if present.
     * Copyright information may include a URL for terms of use or additional details.
     *
     * @return optional copyright information
     */
    @Override
    public Optional<MediaCopyright> getMediaCopyright() {
        return Optional.ofNullable(mediaCopyright);
    }

    /**
     * Sets the copyright information for this item.
     * Use this to specify copyright details for the media object.
     *
     * @param mediaCopyright the copyright information to set
     */
    @Override
    public void setMediaCopyright(MediaCopyright mediaCopyright) {
        this.mediaCopyright = mediaCopyright;
    }

    /**
     * Returns the list of text elements for this item.
     * Text elements allow inclusion of transcripts, closed captioning, or lyrics, with support for time offsets and language.
     *
     * @return list of text elements
     */
    @Override
    public List<MediaText> getMediaTexts() {
        return mediaTexts;
    }

    /**
     * Adds a text element to this item.
     * Use this to provide transcripts, captions, or lyrics for the media object.
     *
     * @param mediaText the text element to add
     */
    @Override
    public void addMediaText(MediaText mediaText) {
        mediaTexts.add(mediaText);
    }

    /**
     * Returns the list of restrictions for this item.
     * Restrictions allow specification of limitations on the media object, such as geographic, URI, or sharing restrictions.
     *
     * @return list of restrictions
     */
    @Override
    public List<MediaRestriction> getMediaRestrictions() {
        return mediaRestrictions;
    }

    /**
     * Adds a restriction to this item.
     * Use this to specify syndication or sharing restrictions for the media object.
     *
     * @param mediaRestriction the restriction to add
     */
    @Override
    public void addMediaRestriction(MediaRestriction mediaRestriction) {
        this.mediaRestrictions.add(mediaRestriction);
    }

    /**
     * Returns the community information for this item, if present.
     * Community information includes feedback, ratings, statistics, and tags for the media object.
     *
     * @return optional community information
     */
    @Override
    public Optional<MediaCommunity> getMediaCommunity() {
        return Optional.ofNullable(mediaCommunity);
    }

    /**
     * Sets the community information for this item.
     * Use this to include community feedback, ratings, statistics, and tags for the media object.
     *
     * @param mediaCommunity the community information to set
     */
    @Override
    public void setMediaCommunity(MediaCommunity mediaCommunity) {
        this.mediaCommunity = mediaCommunity;
    }

    /**
     * Returns the list of comments for this item.
     * Comments allow inclusion of user feedback associated with the media object.
     *
     * @return list of comments
     */
    @Override
    public List<String> getMediaComments() {
        return mediaComments;
    }

    /**
     * Adds a comment to this item.
     * Use this to associate user feedback with the media object.
     *
     * @param mediaComment the comment to add
     */
    @Override
    public void addMediaComment(String mediaComment) {
        this.mediaComments.add(mediaComment);
    }

    /**
     * Returns the embed information for this item, if present.
     * Embed information allows inclusion of player-specific embed code for the media object, with support for key-value parameters.
     *
     * @return optional embed information
     */
    @Override
    public Optional<MediaEmbed> getMediaEmbed() {
        return Optional.ofNullable(mediaEmbed);
    }

    /**
     * Sets the embed information for this item.
     * Use this to provide embeddable player code for the media object.
     *
     * @param mediaEmbed the embed information to set
     */
    @Override
    public void setMediaEmbed(MediaEmbed mediaEmbed) {
        this.mediaEmbed = mediaEmbed;
    }

    /**
     * Returns the list of responses for this item.
     * Responses allow inclusion of feedback or reactions received by the media object.
     *
     * @return list of responses
     */
    @Override
    public List<String> getMediaResponses() {
        return mediaResponses;
    }

    /**
     * Adds a response to this item.
     * Use this to associate feedback or reactions with the media object.
     *
     * @param mediaResponse the response to add
     */
    @Override
    public void addMediaResponse(String mediaResponse) {
        this.mediaResponses.add(mediaResponse);
    }

    /**
     * Returns the list of back links for this item.
     * Back links allow inclusion of URLs referencing the media object.
     *
     * @return list of back links
     */
    @Override
    public List<String> getMediaBackLinks() {
        return mediaBackLinks;
    }

    /**
     * Adds a back link to this item.
     * Use this to associate URLs that reference the media object.
     *
     * @param mediaBackLink the back link to add
     */
    @Override
    public void addMediaBackLink(String mediaBackLink) {
        this.mediaBackLinks.add(mediaBackLink);
    }

    /**
     * Returns the status information for this item, if present.
     * Status information specifies the current state of the media object, such as active, blocked, or deleted, and may include a reason.
     *
     * @return optional status information
     */
    @Override
    public Optional<MediaStatus> getMediaStatus() {
        return Optional.ofNullable(mediaStatus);
    }

    /**
     * Sets the status information for this item.
     * Use this to specify the current state of the media object, including reason for blocking or deletion.
     *
     * @param mediaStatus the status information to set
     */
    @Override
    public void setMediaStatus(MediaStatus mediaStatus) {
        this.mediaStatus = mediaStatus;
    }

    /**
     * Returns the list of prices for this item.
     * Price information includes pricing details for the media object, supporting multiple pricing structures and currencies.
     *
     * @return list of prices
     */
    @Override
    public List<MediaPrice> getMediaPrices() {
        return mediaPrices;
    }

    /**
     * Adds a price to this item.
     * Use this to specify pricing details for the media object, including type, price, currency, and info URL.
     *
     * @param mediaPrice the price to add
     */
    @Override
    public void addMediaPrice(MediaPrice mediaPrice) {
        mediaPrices.add(mediaPrice);
    }

    /**
     * Returns the license information for this item, if present.
     * License information provides machine-readable details about the rights associated with the media object.
     *
     * @return optional license information
     */
    @Override
    public List<MediaLicense> getMediaLicenses() {
        return mediaLicenses;
    }

    /**
     * Sets the license information for this item.
     * Use this to specify license details for the media object.
     *
     * @param mediaLicense the license information to set
     */
    @Override
    public void addMediaLicense(MediaLicense mediaLicense) {
        this.mediaLicenses.add(mediaLicense);
    }

    /**
     * Returns the list of subtitles for this item.
     * Subtitles provide links for subtitle or closed captioning, supporting multiple languages and types.
     *
     * @return list of subtitles
     */
    @Override
    public List<MediaSubTitle> getMediaSubTitles() {
        return mediaSubTitles;
    }

    /**
     * Adds a subtitle to this item.
     * Use this to associate subtitle or closed captioning links with the media object.
     *
     * @param mediaSubTitle the subtitle to add
     */
    @Override
    public void addMediaSubTitle(MediaSubTitle mediaSubTitle) {
        this.mediaSubTitles.add(mediaSubTitle);
    }

    /**
     * Returns the list of peer links for this item.
     * Peer links provide P2P links for the media object, such as BitTorrent files.
     *
     * @return list of peer links
     */
    @Override
    public List<MediaPeerLink> getMediaPeerLinks() {
        return mediaPeerLinks;
    }

    /**
     * Adds a peer link to this item.
     * Use this to associate P2P links with the media object.
     *
     * @param mediaPeerLink the peer link to add
     */
    @Override
    public void addMediaPeerLink(MediaPeerLink mediaPeerLink) {
        this.mediaPeerLinks.add(mediaPeerLink);
    }

    /**
     * Returns the list of media location elements for this item.
     * Each location specifies geographical information about places captured in the media content.
     *
     * @return list of media locations
     */
    @Override
    public List<MediaLocation> getMediaLocations() {
        return mediaLocations;
    }

    /**
     * Adds a media location element to this item.
     * Use this to specify a location referenced in the media object, including description and time offsets.
     *
     * @param mediaLocation the media location to add
     */
    @Override
    public void addMediaLocation(MediaLocation mediaLocation) {
        this.mediaLocations.add(mediaLocation);
    }

    /**
     * Returns the rights information of a media object, if present.
     * Rights information specifies the rights status of the media object, such as user-created or official.
     *
     * @return optional rights information
     */
    @Override
    public Optional<MediaRights> getMediaRights() {
        return Optional.ofNullable(mediaRights);
    }

    /**
     * Sets the rights information of a media object.
     * Use this to specify the rights status of the media object.
     *
     * @param mediaRights the rights information to set
     */
    @Override
    public void setMediaRights(MediaRights mediaRights) {
        this.mediaRights = mediaRights;
    }

    /**
     * Returns the list of scenes for this item.
     * Scene information specifies details about specific segments within the media object, such as title, description, and time offsets.
     *
     * @return list of scenes
     */
    @Override
    public List<MediaScene> getMediaScenes() {
        return mediaScenes;
    }

    /**
     * Adds a scene to this item.
     * Use this to associate scene-specific information, such as title, description, and time offsets, with the media object.
     *
     * @param mediaScenes the scene to add
     */
    @Override
    public void addMediaScene(MediaScene mediaScenes) {
        this.mediaScenes.add(mediaScenes);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MetadataImpl that = (MetadataImpl) o;
        return Objects.equals(getMediaRatings(), that.getMediaRatings()) && Objects.equals(getMediaTitle(), that.getMediaTitle()) && Objects.equals(getMediaDescription(), that.getMediaDescription()) && Objects.equals(getMediaKeywords(), that.getMediaKeywords()) && Objects.equals(getMediaThumbnails(), that.getMediaThumbnails()) && Objects.equals(getMediaCategories(), that.getMediaCategories()) && Objects.equals(getMediaHashes(), that.getMediaHashes()) && Objects.equals(getMediaPlayer(), that.getMediaPlayer()) && Objects.equals(getMediaCredits(), that.getMediaCredits()) && Objects.equals(getMediaCopyright(), that.getMediaCopyright()) && Objects.equals(getMediaTexts(), that.getMediaTexts()) && Objects.equals(getMediaRestrictions(), that.getMediaRestrictions()) && Objects.equals(getMediaCommunity(), that.getMediaCommunity()) && Objects.equals(getMediaComments(), that.getMediaComments()) && Objects.equals(getMediaEmbed(), that.getMediaEmbed()) && Objects.equals(getMediaResponses(), that.getMediaResponses()) && Objects.equals(getMediaBackLinks(), that.getMediaBackLinks()) && Objects.equals(getMediaStatus(), that.getMediaStatus()) && Objects.equals(getMediaPrices(), that.getMediaPrices()) && Objects.equals(getMediaLicenses(), that.getMediaLicenses()) && Objects.equals(getMediaSubTitles(), that.getMediaSubTitles()) && Objects.equals(getMediaPeerLinks(), that.getMediaPeerLinks()) && Objects.equals(getMediaLocations(), that.getMediaLocations()) && Objects.equals(getMediaRights(), that.getMediaRights()) && Objects.equals(getMediaScenes(), that.getMediaScenes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMediaRatings(), getMediaTitle(), getMediaDescription(), getMediaKeywords(), getMediaThumbnails(), getMediaCategories(), getMediaHashes(), getMediaPlayer(), getMediaCredits(), getMediaCopyright(), getMediaTexts(), getMediaRestrictions(), getMediaCommunity(), getMediaComments(), getMediaEmbed(), getMediaResponses(), getMediaBackLinks(), getMediaStatus(), getMediaPrices(), getMediaLicenses(), getMediaSubTitles(), getMediaPeerLinks(), getMediaLocations(), getMediaRights(), getMediaScenes());
    }
}
