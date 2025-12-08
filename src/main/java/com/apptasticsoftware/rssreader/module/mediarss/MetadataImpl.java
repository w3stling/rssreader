package com.apptasticsoftware.rssreader.module.mediarss;

import com.apptasticsoftware.rssreader.util.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@SuppressWarnings("java:S6539")
public class MetadataImpl implements Metadata {
    private List<MediaRating> mediaRatings;
    private MediaTitle mediaTitle;
    private MediaDescription mediaDescription;
    private List<String> mediaKeywords;
    private List<MediaThumbnail> mediaThumbnails;
    private List<MediaCategory> mediaCategories;
    private List<MediaHash> mediaHashes;
    private MediaPlayer mediaPlayer;
    private List<MediaCredit> mediaCredits;
    private MediaCopyright mediaCopyright;
    private List<MediaText> mediaTexts;
    private List<MediaRestriction> mediaRestrictions;
    private MediaCommunity mediaCommunity;
    private List<String> mediaComments;
    private MediaEmbed mediaEmbed;
    private List<String> mediaResponses;
    private List<String> mediaBackLinks;
    private MediaStatus mediaStatus;
    private List<MediaPrice> mediaPrices;
    private List<MediaLicense> mediaLicenses;
    private List<MediaSubTitle> mediaSubTitles;
    private List<MediaPeerLink> mediaPeerLinks;
    private List<MediaLocation> mediaLocations;
    private MediaRights mediaRights;
    private List<MediaScene> mediaScenes;

    /**
     * Gets a list of media rating that declares the permissible audience. If this element is not included,
     * it assumes that no restrictions are necessary. The rating can use different schemes like
     * urn:simple (adult | nonadult), urn:mpaa (g | pg | pg-13 | r | nc-17), urn:v-chip, or urn:icra.
     *
     * @return media rating or empty if not set
     */
    public List<MediaRating> getMediaRatings() {
        return Mapper.emptyListIfNull(mediaRatings);
    }

    /**
     * Sets the media rating that declares the permissible audience. If this element is not included,
     * it assumes that no restrictions are necessary. The rating can use different schemes like
     * urn:simple (adult | nonadult), urn:mpaa (g | pg | pg-13 | r | nc-17), urn:v-chip, or urn:icra.
     *
     * @param mediaRating media rating
     */
    public void addMediaRating(MediaRating mediaRating) {
        if (mediaRatings == null) {
            mediaRatings = new ArrayList<>();
        }
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
        return Mapper.emptyListIfNull(mediaKeywords);
    }

    /**
     * Adds a keyword describing the media object.
     * Use this to provide relevant keywords for indexing and discovery.
     *
     * @param mediaKeyword the keyword to add
     */
    @Override
    public void addMediaKeyword(String mediaKeyword) {
        if (mediaKeywords == null) {
            mediaKeywords = new ArrayList<>();
        }
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
        return Mapper.emptyListIfNull(mediaThumbnails);
    }

    /**
     * Adds a thumbnail to this item.
     * Use this to associate a representative image with the media object.
     *
     * @param mediaThumbnail the thumbnail to add
     */
    @Override
    public void addMediaThumbnail(MediaThumbnail mediaThumbnail) {
        if (mediaThumbnails == null) {
            mediaThumbnails = new ArrayList<>();
        }
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
        return Mapper.emptyListIfNull(mediaCategories);
    }

    /**
     * Adds a category to this item.
     * Use this to specify taxonomy or categorization for the media object.
     *
     * @param mediaCategory the category to add
     */
    @Override
    public void addMediaCategory(MediaCategory mediaCategory) {
        if (mediaCategories == null) {
            mediaCategories = new ArrayList<>();
        }
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
        return Mapper.emptyListIfNull(mediaHashes);
    }

    /**
     * Adds a hash to this item.
     * Use this to associate a hash value with the media object for identification purposes.
     *
     * @param mediaHash the hash to add
     */
    @Override
    public void addMediaHash(MediaHash mediaHash) {
        if (mediaHashes == null) {
            mediaHashes = new ArrayList<>();
        }
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
        return Mapper.emptyListIfNull(mediaCredits);
    }

    /**
     * Adds a credit to this item.
     * Use this to specify contributors, such as people or companies, and their roles in the media object.
     *
     * @param mediaCredit the credit to add
     */
    @Override
    public void addMediaCredit(MediaCredit mediaCredit) {
        if (mediaCredits == null) {
            mediaCredits = new ArrayList<>();
        }
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
        return Mapper.emptyListIfNull(mediaTexts);
    }

    /**
     * Adds a text element to this item.
     * Use this to provide transcripts, captions, or lyrics for the media object.
     *
     * @param mediaText the text element to add
     */
    @Override
    public void addMediaText(MediaText mediaText) {
        if (mediaTexts == null) {
            mediaTexts = new ArrayList<>();
        }
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
        return Mapper.emptyListIfNull(mediaRestrictions);
    }

    /**
     * Adds a restriction to this item.
     * Use this to specify syndication or sharing restrictions for the media object.
     *
     * @param mediaRestriction the restriction to add
     */
    @Override
    public void addMediaRestriction(MediaRestriction mediaRestriction) {
        if (mediaRestrictions == null) {
            mediaRestrictions = new ArrayList<>();
        }
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
        return Mapper.emptyListIfNull(mediaComments);
    }

    /**
     * Adds a comment to this item.
     * Use this to associate user feedback with the media object.
     *
     * @param mediaComment the comment to add
     */
    @Override
    public void addMediaComment(String mediaComment) {
        if (mediaComments == null) {
            mediaComments = new ArrayList<>();
        }
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
        return Mapper.emptyListIfNull(mediaResponses);
    }

    /**
     * Adds a response to this item.
     * Use this to associate feedback or reactions with the media object.
     *
     * @param mediaResponse the response to add
     */
    @Override
    public void addMediaResponse(String mediaResponse) {
        if (mediaResponses == null) {
            mediaResponses = new ArrayList<>();
        }
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
        return Mapper.emptyListIfNull(mediaBackLinks);
    }

    /**
     * Adds a back link to this item.
     * Use this to associate URLs that reference the media object.
     *
     * @param mediaBackLink the back link to add
     */
    @Override
    public void addMediaBackLink(String mediaBackLink) {
        if (mediaBackLinks == null) {
            mediaBackLinks = new ArrayList<>();
        }
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
        return Mapper.emptyListIfNull(mediaPrices);
    }

    /**
     * Adds a price to this item.
     * Use this to specify pricing details for the media object, including type, price, currency, and info URL.
     *
     * @param mediaPrice the price to add
     */
    @Override
    public void addMediaPrice(MediaPrice mediaPrice) {
        if (mediaPrices == null) {
            mediaPrices = new ArrayList<>();
        }
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
        return Mapper.emptyListIfNull(mediaLicenses);
    }

    /**
     * Sets the license information for this item.
     * Use this to specify license details for the media object.
     *
     * @param mediaLicense the license information to set
     */
    @Override
    public void addMediaLicense(MediaLicense mediaLicense) {
        if (mediaLicenses == null) {
            mediaLicenses = new ArrayList<>();
        }
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
        return Mapper.emptyListIfNull(mediaSubTitles);
    }

    /**
     * Adds a subtitle to this item.
     * Use this to associate subtitle or closed captioning links with the media object.
     *
     * @param mediaSubTitle the subtitle to add
     */
    @Override
    public void addMediaSubTitle(MediaSubTitle mediaSubTitle) {
        if (mediaSubTitles == null) {
            mediaSubTitles = new ArrayList<>();
        }
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
        return Mapper.emptyListIfNull(mediaPeerLinks);
    }

    /**
     * Adds a peer link to this item.
     * Use this to associate P2P links with the media object.
     *
     * @param mediaPeerLink the peer link to add
     */
    @Override
    public void addMediaPeerLink(MediaPeerLink mediaPeerLink) {
        if (mediaPeerLinks == null) {
            mediaPeerLinks = new ArrayList<>();
        }
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
        return Mapper.emptyListIfNull(mediaLocations);
    }

    /**
     * Adds a media location element to this item.
     * Use this to specify a location referenced in the media object, including description and time offsets.
     *
     * @param mediaLocation the media location to add
     */
    @Override
    public void addMediaLocation(MediaLocation mediaLocation) {
        if (mediaLocations == null) {
            mediaLocations = new ArrayList<>();
        }
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
        return Mapper.emptyListIfNull(mediaScenes);
    }

    /**
     * Adds a scene to this item.
     * Use this to associate scene-specific information, such as title, description, and time offsets, with the media object.
     *
     * @param mediaScene the scene to add
     */
    @Override
    public void addMediaScene(MediaScene mediaScene) {
        if (mediaScenes == null) {
            this.mediaScenes = new ArrayList<>();
        }
        this.mediaScenes.add(mediaScene);
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
