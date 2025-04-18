package com.apptasticsoftware.rssreader.module.mediarss;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MediaGroup {
    private MediaTitle mediaTitle;
    private MediaDescription mediaDescription;
    private MediaCopyright mediaCopyright;
    private final List<MediaThumbnail> mediaThumbnails = new ArrayList<>();
    private final List<String> mediaKeywords = new ArrayList<>();
    private final List<MediaRestriction> mediaRestrictions = new ArrayList<>();
    private MediaCommunity mediaCommunity;
    private final List<MediaCredit> mediaCredits = new ArrayList<>();
    private final List<MediaHash> mediaHashes = new ArrayList<>();
    private final List<MediaCategory> mediaCategories = new ArrayList<>();
    private MediaRating mediaRating;
    private MediaStatus mediaStatus;
    private final List<MediaPrice> mediaPrices = new ArrayList<>();
    private MediaEmbed mediaEmbed;
    private final List<MediaSubTitle> mediaSubTitles = new ArrayList<>();
    private final List<MediaPeerLink> mediaPeerLinks = new ArrayList<>();
    private final List<MediaLocation> mediaLocations = new ArrayList<>();
    private final List<MediaScene> mediaScenes = new ArrayList<>();
    private final List<MediaText> mediaTexts = new ArrayList<>();
    private final List<MediaContent> mediaContents = new ArrayList<>();
    private final List<String> mediaComments = new ArrayList<>();
    private final List<String> mediaResponses = new ArrayList<>();
    private final List<String> mediaBackLinks = new ArrayList<>();

    // MediaRatings[]??, MediaThumbnail[]??, MediaLicense[]??,

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

    /**
     * Allows particular images to be used as representative images for the media object.
     * @return media thumbnail
     */
    public List<MediaThumbnail> getMediaThumbnails() {
        return mediaThumbnails;
    }

    /**
     * Allows particular images to be used as representative images for the media object.
     * @param mediaThumbnail media thumbnail
     */
    public void addMediaThumbnail(MediaThumbnail mediaThumbnail) {
        this.mediaThumbnails.add(mediaThumbnail);
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

    public List<MediaRestriction> getMediaRestrictions() {
        return mediaRestrictions;
    }

    public void addMediaRestriction(MediaRestriction mediaRestriction) {
        this.mediaRestrictions.add(mediaRestriction);
    }

    /**
     * This element stands for the community related content.
     * This allows inclusion of the user perception about a media object in the form of view count, ratings and tags.
     * @return media community
     */
    public Optional<MediaCommunity> getMediaCommunity() {
        return Optional.ofNullable(mediaCommunity);
    }

    /**
     * This element stands for the community related content.
     * This allows inclusion of the user perception about a media object in the form of view count, ratings and tags.
     * @param mediaCommunity media community
     */
    public void setMediaCommunity(MediaCommunity mediaCommunity) {
        this.mediaCommunity = mediaCommunity;
    }

    /**
     * Notable entity and the contribution to the creation of the media object.
     * @return media credits
     */
    public List<MediaCredit> getMediaCredits() {
        return mediaCredits;
    }

    /**
     * Notable entity and the contribution to the creation of the media object.
     * @param mediaCredit media credit
     */
    public void addMediaCredit(MediaCredit mediaCredit) {
        mediaCredits.add(mediaCredit);
    }

    public List<MediaHash> getMediaHashes() {
        return mediaHashes;
    }

    public void addMediaHash(MediaHash mediaHash) {
        mediaHashes.add(mediaHash);
    }

    /**
     * Allows a taxonomy to be set that gives an indication of the type of media content, and its particular contents.
     * @return media categories
     */
    public List<MediaCategory> getMediaCategories() {
        return mediaCategories;
    }

    /**
     * Allows a taxonomy to be set that gives an indication of the type of media content, and its particular contents.
     * @param mediaCategory media category
     */
    public void addMediaCategory(MediaCategory mediaCategory) {
        mediaCategories.add(mediaCategory);
    }

    /**
     * This allows the permissible audience to be declared.
     * @return media rating
     */
    public Optional<MediaRating> getMediaRating() {
        return Optional.ofNullable(mediaRating);
    }

    /**
     * This allows the permissible audience to be declared.
     * @param mediaRating media rating
     */
    public void setMediaRating(MediaRating mediaRating) {
        this.mediaRating = mediaRating;
    }

    public Optional<MediaStatus> getMediaStatus() {
        return Optional.ofNullable(mediaStatus);
    }

    public void setMediaStatus(MediaStatus mediaStatus) {
        this.mediaStatus = mediaStatus;
    }

    public List<MediaPrice> getMediaPrices() {
        return mediaPrices;
    }

    public void addMediaPrice(MediaPrice mediaPrice) {
        mediaPrices.add(mediaPrice);
    }

    public Optional<MediaEmbed> getMediaEmbed() {
        return Optional.ofNullable(mediaEmbed);
    }

    public void setMediaEmbed(MediaEmbed mediaEmbed) {
        this.mediaEmbed = mediaEmbed;
    }

    public List<MediaSubTitle> getMediaSubTitles() {
        return mediaSubTitles;
    }

    public void addMediaSubTitle(MediaSubTitle mediaSubTitle) {
        mediaSubTitles.add(mediaSubTitle);
    }

    public List<MediaPeerLink> getMediaPeerLinks() {
        return mediaPeerLinks;
    }

    public void addMediaPeerLink(MediaPeerLink mediaPeerLink) {
        mediaPeerLinks.add(mediaPeerLink);
    }

    public List<MediaLocation> getMediaLocations() {
        return mediaLocations;
    }

    public void addMediaLocation(MediaLocation mediaLocation) {
        mediaLocations.add(mediaLocation);
    }

    public List<MediaScene> getMediaScenes() {
        return mediaScenes;
    }

    public void addMediaScene(MediaScene mediaScene) {
        mediaScenes.add(mediaScene);
    }

    public List<MediaText> getMediaTexts() {
        return mediaTexts;
    }

    public void addMediaText(MediaText mediaText) {
        mediaTexts.add(mediaText);
    }

    public List<String> getMediaComments() {
        return mediaComments;
    }

    public void addMediaComment(String mediaComment) {
        mediaComments.add(mediaComment);
    }

    public List<String> getMediaResponses() {
        return mediaResponses;
    }

    public void addMediaResponse(String mediaResponse) {
        mediaResponses.add(mediaResponse);
    }

    public List<String> getMediaBackLinks() {
        return mediaBackLinks;
    }

    public void addMediaBackLink(String mediaBackLink) {
        mediaBackLinks.add(mediaBackLink);
    }


    /**
     * Get the media content
     *
     * @return media content
     */
    public List<MediaContent> getMediaContents() {
        return mediaContents;
    }

    /**
     * Set the media content
     *
     * @param mediaContent media content
     */
    public void addMediaContent(MediaContent mediaContent) {
        mediaContents.add(mediaContent);
    }
}
