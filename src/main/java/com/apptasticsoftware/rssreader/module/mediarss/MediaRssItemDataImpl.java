package com.apptasticsoftware.rssreader.module.mediarss;

import com.apptasticsoftware.rssreader.util.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@SuppressWarnings("java:S6539")
public class MediaRssItemDataImpl implements MediaRssItemData {
    private List<MediaContent> mediaContents;
    private MediaGroup mediaGroup;
    private final Metadata metadata = new MetadataImpl();

    @Override
    public MediaRssItemData getMediaRssItemData() {
        return this;
    }

    @Override
    public List<MediaContent> getMediaContents() {
        return Mapper.emptyListIfNull(mediaContents);
    }

    /**
     * Adds a media content element to this item.
     * Use this to associate a new media object with the item.
     *
     * @param mediaContent the media content element to add
     */
    @Override
    public void addMediaContents(MediaContent mediaContent) {
        if (mediaContents == null) {
            mediaContents = new ArrayList<>();
        }
        mediaContents.add(mediaContent);
    }

    /**
     * Returns the media group associated with this item, if present.
     * A group allows grouping of media objects that are different representations of the same content.
     *
     * @return optional media group
     */
    @Override
    public Optional<MediaGroup> getMediaGroup() {
        return Optional.ofNullable(mediaGroup);
    }

    /**
     * Sets the media group for this item.
     * Use this to group multiple media objects representing the same content in different formats.
     *
     * @param mediaGroup the media group to set
     */
    @Override
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
        return metadata.getMediaLocations();
    }

    /**
     * Adds a media location element to this item.
     * Use this to specify a location referenced in the media object, including description and time offsets.
     *
     * @param mediaLocation the media location to add
     */
    @Override
    public void addMediaLocation(MediaLocation mediaLocation) {
        metadata.addMediaLocation(mediaLocation);
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
        return metadata.getMediaRatings();
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
        metadata.addMediaRating(mediaRating);
    }

    /**
     * Returns the title for this item, if present.
     * The title provides a human-readable name for the media object, with optional plain or HTML text formatting.
     *
     * @return optional title
     */
    @Override
    public Optional<MediaTitle> getMediaTitle() {
        return metadata.getMediaTitle();
    }

    /**
     * Sets the title for this item.
     * Use this to specify the name of the media object, supporting plain or HTML text.
     *
     * @param mediaTitle the title to set
     */
    @Override
    public void setMediaTitle(MediaTitle mediaTitle) {
        metadata.setMediaTitle(mediaTitle);
    }

    /**
     * Returns the description for this item, if present.
     * The description provides a short summary of the media object, with optional plain or HTML text formatting.
     *
     * @return optional description
     */
    @Override
    public Optional<MediaDescription> getMediaDescription() {
        return metadata.getMediaDescription();
    }

    /**
     * Sets the description for this item.
     * Use this to specify a short summary of the media object, supporting plain or HTML text.
     *
     * @param mediaDescription the description to set
     */
    @Override
    public void setMediaDescription(MediaDescription mediaDescription) {
        metadata.setMediaDescription(mediaDescription);
    }

    /**
     * Returns the list of keywords describing the media object.
     * Keywords are comma-delimited and relevant to the media object, typically up to 10 words.
     *
     * @return list of keywords
     */
    @Override
    public List<String> getMediaKeywords() {
        return metadata.getMediaKeywords();
    }

    /**
     * Adds a keyword describing the media object.
     * Use this to provide relevant keywords for indexing and discovery.
     *
     * @param mediaKeyword the keyword to add
     */
    @Override
    public void addMediaKeyword(String mediaKeyword) {
        metadata.addMediaKeyword(mediaKeyword);
    }

    /**
     * Returns the list of thumbnails for this item.
     * Thumbnails are representative images for the media object, with attributes for URL, width, height, and time offset.
     *
     * @return list of thumbnails
     */
    @Override
    public List<MediaThumbnail> getMediaThumbnails() {
        return metadata.getMediaThumbnails();
    }

    /**
     * Adds a thumbnail to this item.
     * Use this to associate a representative image with the media object.
     *
     * @param mediaThumbnail the thumbnail to add
     */
    @Override
    public void addMediaThumbnail(MediaThumbnail mediaThumbnail) {
        metadata.addMediaThumbnail(mediaThumbnail);
    }

    /**
     * Returns the list of categories for this item.
     * Categories allow taxonomy assignment to indicate the type and contents of the media object, with optional scheme and label attributes.
     *
     * @return list of categories
     */
    @Override
    public List<MediaCategory> getMediaCategories() {
        return metadata.getMediaCategories();
    }

    /**
     * Adds a category to this item.
     * Use this to specify taxonomy or categorization for the media object.
     *
     * @param mediaCategory the category to add
     */
    @Override
    public void addMediaCategory(MediaCategory mediaCategory) {
        metadata.addMediaCategory(mediaCategory);
    }

    /**
     * Returns the list of hashes for this item.
     * Hashes provide a value for the media object, useful for duplicate detection and caching, with optional algorithm attribute.
     *
     * @return list of hashes
     */
    @Override
    public List<MediaHash> getMediaHashes() {
        return metadata.getMediaHashes();
    }

    /**
     * Adds a hash to this item.
     * Use this to associate a hash value with the media object for identification purposes.
     *
     * @param mediaHash the hash to add
     */
    @Override
    public void addMediaHash(MediaHash mediaHash) {
        metadata.addMediaHash(mediaHash);
    }

    /**
     * Returns the media player information for this item, if present.
     * The media player provides a URL to a web-based media player for the media object, with optional width and height attributes.
     *
     * @return optional media player information
     */
    @Override
    public Optional<MediaPlayer> getMediaPlayer() {
        return metadata.getMediaPlayer();
    }

    /**
     * Sets the media player information for this item.
     * Use this to specify a web-based player URL for the media object, with optional dimensions.
     *
     * @param mediaPlayer the media player information to set
     */
    @Override
    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        metadata.setMediaPlayer(mediaPlayer);
    }

    /**
     * Returns the list of credits for this item.
     * Credits identify notable entities and their contributions to the creation of the media object, with optional role and scheme attributes.
     *
     * @return list of credits
     */
    @Override
    public List<MediaCredit> getMediaCredits() {
        return metadata.getMediaCredits();
    }

    /**
     * Adds a credit to this item.
     * Use this to specify contributors, such as people or companies, and their roles in the media object.
     *
     * @param mediaCredit the credit to add
     */
    @Override
    public void addMediaCredit(MediaCredit mediaCredit) {
        metadata.addMediaCredit(mediaCredit);
    }

    /**
     * Returns the copyright information for this item, if present.
     * Copyright information may include a URL for terms of use or additional details.
     *
     * @return optional copyright information
     */
    @Override
    public Optional<MediaCopyright> getMediaCopyright() {
        return metadata.getMediaCopyright();
    }

    /**
     * Sets the copyright information for this item.
     * Use this to specify copyright details for the media object.
     *
     * @param mediaCopyright the copyright information to set
     */
    @Override
    public void setMediaCopyright(MediaCopyright mediaCopyright) {
        metadata.setMediaCopyright(mediaCopyright);
    }

    /**
     * Returns the list of text elements for this item.
     * Text elements allow inclusion of transcripts, closed captioning, or lyrics, with support for time offsets and language.
     *
     * @return list of text elements
     */
    @Override
    public List<MediaText> getMediaTexts() {
        return metadata.getMediaTexts();
    }

    /**
     * Adds a text element to this item.
     * Use this to provide transcripts, captions, or lyrics for the media object.
     *
     * @param mediaText the text element to add
     */
    @Override
    public void addMediaText(MediaText mediaText) {
        metadata.addMediaText(mediaText);
    }

    /**
     * Returns the list of restrictions for this item.
     * Restrictions allow specification of limitations on the media object, such as geographic, URI, or sharing restrictions.
     *
     * @return list of restrictions
     */
    @Override
    public List<MediaRestriction> getMediaRestrictions() {
        return metadata.getMediaRestrictions();
    }

    /**
     * Adds a restriction to this item.
     * Use this to specify syndication or sharing restrictions for the media object.
     *
     * @param mediaRestriction the restriction to add
     */
    @Override
    public void addMediaRestriction(MediaRestriction mediaRestriction) {
        metadata.addMediaRestriction(mediaRestriction);
    }

    /**
     * Returns the community information for this item, if present.
     * Community information includes feedback, ratings, statistics, and tags for the media object.
     *
     * @return optional community information
     */
    @Override
    public Optional<MediaCommunity> getMediaCommunity() {
        return metadata.getMediaCommunity();
    }

    /**
     * Sets the community information for this item.
     * Use this to include community feedback, ratings, statistics, and tags for the media object.
     *
     * @param mediaCommunity the community information to set
     */
    @Override
    public void setMediaCommunity(MediaCommunity mediaCommunity) {
        metadata.setMediaCommunity(mediaCommunity);
    }

    /**
     * Returns the list of comments for this item.
     * Comments allow inclusion of user feedback associated with the media object.
     *
     * @return list of comments
     */
    @Override
    public List<String> getMediaComments() {
        return metadata.getMediaComments();
    }

    /**
     * Adds a comment to this item.
     * Use this to associate user feedback with the media object.
     *
     * @param mediaComment the comment to add
     */
    @Override
    public void addMediaComment(String mediaComment) {
        metadata.addMediaComment(mediaComment);
    }

    /**
     * Returns the embed information for this item, if present.
     * Embed information allows inclusion of player-specific embed code for the media object, with support for key-value parameters.
     *
     * @return optional embed information
     */
    @Override
    public Optional<MediaEmbed> getMediaEmbed() {
        return metadata.getMediaEmbed();
    }

    /**
     * Sets the embed information for this item.
     * Use this to provide embeddable player code for the media object.
     *
     * @param mediaEmbed the embed information to set
     */
    @Override
    public void setMediaEmbed(MediaEmbed mediaEmbed) {
        metadata.setMediaEmbed(mediaEmbed);
    }

    /**
     * Returns the list of responses for this item.
     * Responses allow inclusion of feedback or reactions received by the media object.
     *
     * @return list of responses
     */
    @Override
    public List<String> getMediaResponses() {
        return metadata.getMediaResponses();
    }

    /**
     * Adds a response to this item.
     * Use this to associate feedback or reactions with the media object.
     *
     * @param mediaResponse the response to add
     */
    @Override
    public void addMediaResponse(String mediaResponse) {
        metadata.addMediaResponse(mediaResponse);
    }

    /**
     * Returns the list of back links for this item.
     * Back links allow inclusion of URLs referencing the media object.
     *
     * @return list of back links
     */
    @Override
    public List<String> getMediaBackLinks() {
        return metadata.getMediaBackLinks();
    }

    /**
     * Adds a back link to this item.
     * Use this to associate URLs that reference the media object.
     *
     * @param mediaBackLink the back link to add
     */
    @Override
    public void addMediaBackLink(String mediaBackLink) {
        metadata.addMediaBackLink(mediaBackLink);
    }

    /**
     * Returns the status information for this item, if present.
     * Status information specifies the current state of the media object, such as active, blocked, or deleted, and may include a reason.
     *
     * @return optional status information
     */
    @Override
    public Optional<MediaStatus> getMediaStatus() {
        return metadata.getMediaStatus();
    }

    /**
     * Sets the status information for this item.
     * Use this to specify the current state of the media object, including reason for blocking or deletion.
     *
     * @param mediaStatus the status information to set
     */
    @Override
    public void setMediaStatus(MediaStatus mediaStatus) {
        metadata.setMediaStatus(mediaStatus);
    }

    /**
     * Returns the list of prices for this item.
     * Price information includes pricing details for the media object, supporting multiple pricing structures and currencies.
     *
     * @return list of prices
     */
    @Override
    public List<MediaPrice> getMediaPrices() {
        return metadata.getMediaPrices();
    }

    /**
     * Adds a price to this item.
     * Use this to specify pricing details for the media object, including type, price, currency, and info URL.
     *
     * @param mediaPrice the price to add
     */
    @Override
    public void addMediaPrice(MediaPrice mediaPrice) {
        metadata.addMediaPrice(mediaPrice);
    }

    /**
     * Returns the license information for this item, if present.
     * License information provides machine-readable details about the rights associated with the media object.
     *
     * @return optional license information
     */
    @Override
    public List<MediaLicense> getMediaLicenses() {
        return metadata.getMediaLicenses();
    }

    /**
     * Sets the license information for this item.
     * Use this to specify license details for the media object.
     *
     * @param mediaLicense the license information to set
     */
    @Override
    public void addMediaLicense(MediaLicense mediaLicense) {
        metadata.addMediaLicense(mediaLicense);
    }

    /**
     * Returns the list of subtitles for this item.
     * Subtitles provide links for subtitle or closed captioning, supporting multiple languages and types.
     *
     * @return list of subtitles
     */
    @Override
    public List<MediaSubTitle> getMediaSubTitles() {
        return metadata.getMediaSubTitles();
    }

    /**
     * Adds a subtitle to this item.
     * Use this to associate subtitle or closed captioning links with the media object.
     *
     * @param mediaSubTitle the subtitle to add
     */
    @Override
    public void addMediaSubTitle(MediaSubTitle mediaSubTitle) {
        metadata.addMediaSubTitle(mediaSubTitle);
    }

    /**
     * Returns the list of peer links for this item.
     * Peer links provide P2P links for the media object, such as BitTorrent files.
     *
     * @return list of peer links
     */
    @Override
    public List<MediaPeerLink> getMediaPeerLinks() {
        return metadata.getMediaPeerLinks();
    }

    /**
     * Adds a peer link to this item.
     * Use this to associate P2P links with the media object.
     *
     * @param mediaPeerLink the peer link to add
     */
    @Override
    public void addMediaPeerLink(MediaPeerLink mediaPeerLink) {
        metadata.addMediaPeerLink(mediaPeerLink);
    }

    /**
     * Returns the rights information of a media object, if present.
     * Rights information specifies the rights status of the media object, such as user-created or official.
     *
     * @return optional rights information
     */
    @Override
    public Optional<MediaRights> getMediaRights() {
        return metadata.getMediaRights();
    }

    /**
     * Sets the rights information of a media object.
     * Use this to specify the rights status of the media object.
     *
     * @param mediaRights the rights information to set
     */
    @Override
    public void setMediaRights(MediaRights mediaRights) {
        metadata.setMediaRights(mediaRights);
    }

    /**
     * Returns the list of scenes for this item.
     * Scene information specifies details about specific segments within the media object, such as title, description, and time offsets.
     *
     * @return list of scenes
     */
    @Override
    public List<MediaScene> getMediaScenes() {
        return metadata.getMediaScenes();
    }

    /**
     * Adds a scene to this item.
     * Use this to associate scene-specific information, such as title, description, and time offsets, with the media object.
     *
     * @param mediaScenes the scene to add
     */
    @Override
    public void addMediaScene(MediaScene mediaScenes) {
        metadata.addMediaScene(mediaScenes);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MediaRssItemDataImpl)) return false;
        MediaRssItemDataImpl that = (MediaRssItemDataImpl) o;
        return Objects.equals(getMediaContents(), that.getMediaContents()) && Objects.equals(getMediaGroup(), that.getMediaGroup()) && Objects.equals(metadata, that.metadata);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMediaContents(), getMediaGroup(), metadata);
    }
}
