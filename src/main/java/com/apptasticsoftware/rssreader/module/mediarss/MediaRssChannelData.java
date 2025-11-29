package com.apptasticsoftware.rssreader.module.mediarss;

import java.util.List;
import java.util.Optional;

public interface MediaRssChannelData {
    MediaRssChannelData getMediaRssChannelData();

    default List<MediaRating> getMediaRatings() {
        return getMediaRssChannelData().getMediaRatings();
    }

    default void addMediaRating(MediaRating mediaRating) {
        getMediaRssChannelData().addMediaRating(mediaRating);
    }

    default Optional<MediaTitle> getMediaTitle() {
        return getMediaRssChannelData().getMediaTitle();
    }

    default void setMediaTitle(MediaTitle mediaTitle) {
        getMediaRssChannelData().setMediaTitle(mediaTitle);
    }

    default Optional<MediaDescription> getMediaDescription() {
        return getMediaRssChannelData().getMediaDescription();
    }

    default void setMediaDescription(MediaDescription mediaDescription) {
        getMediaRssChannelData().setMediaDescription(mediaDescription);
    }

    default List<String> getMediaKeywords() {
        return getMediaRssChannelData().getMediaKeywords();
    }

    default void addMediaKeyword(String mediaKeyword) {
        getMediaRssChannelData().addMediaKeyword(mediaKeyword);
    }

    default List<MediaThumbnail> getMediaThumbnails() {
        return getMediaRssChannelData().getMediaThumbnails();
    }

    default void addMediaThumbnail(MediaThumbnail mediaThumbnail) {
        getMediaRssChannelData().addMediaThumbnail(mediaThumbnail);
    }

    default List<MediaCategory> getMediaCategories() {
        return getMediaRssChannelData().getMediaCategories();
    }

    default void addMediaCategory(MediaCategory mediaCategory) {
        getMediaRssChannelData().addMediaCategory(mediaCategory);
    }

    default List<MediaHash> getMediaHashes() {
        return getMediaRssChannelData().getMediaHashes();
    }

    default void addMediaHash(MediaHash mediaHash) {
        getMediaRssChannelData().addMediaHash(mediaHash);
    }

    default Optional<MediaPlayer> getMediaPlayer() {
        return getMediaRssChannelData().getMediaPlayer();
    }

    default void setMediaPlayer(MediaPlayer mediaPlayer) {
        getMediaRssChannelData().setMediaPlayer(mediaPlayer);
    }

    default List<MediaCredit> getMediaCredits() {
        return getMediaRssChannelData().getMediaCredits();
    }

    default void addMediaCredit(MediaCredit mediaCredit) {
        getMediaRssChannelData().addMediaCredit(mediaCredit);
    }

    default Optional<MediaCopyright> getMediaCopyright() {
        return getMediaRssChannelData().getMediaCopyright();
    }

    default void setMediaCopyright(MediaCopyright mediaCopyright) {
        getMediaRssChannelData().setMediaCopyright(mediaCopyright);
    }

    default List<MediaText> getMediaTexts() {
        return getMediaRssChannelData().getMediaTexts();
    }

    default void addMediaText(MediaText mediaText) {
        getMediaRssChannelData().addMediaText(mediaText);
    }

    default List<MediaRestriction> getMediaRestrictions() {
        return getMediaRssChannelData().getMediaRestrictions();
    }

    default void addMediaRestriction(MediaRestriction mediaRestriction) {
        getMediaRssChannelData().addMediaRestriction(mediaRestriction);
    }

    default Optional<MediaCommunity> getMediaCommunity() {
        return getMediaRssChannelData().getMediaCommunity();
    }

    default void setMediaCommunity(MediaCommunity mediaCommunity) {
        getMediaRssChannelData().setMediaCommunity(mediaCommunity);
    }

    default List<String> getMediaComments() {
        return getMediaRssChannelData().getMediaComments();
    }

    default void addMediaComment(String mediaComment) {
        getMediaRssChannelData().addMediaComment(mediaComment);
    }

    default Optional<MediaEmbed> getMediaEmbed() {
        return getMediaRssChannelData().getMediaEmbed();
    }

    default void setMediaEmbed(MediaEmbed mediaEmbed) {
        getMediaRssChannelData().setMediaEmbed(mediaEmbed);
    }

    default List<String> getMediaResponses() {
        return getMediaRssChannelData().getMediaResponses();
    }

    default void addMediaResponse(String mediaResponse) {
        getMediaRssChannelData().addMediaResponse(mediaResponse);
    }

    default List<String> getMediaBackLinks() {
        return getMediaRssChannelData().getMediaBackLinks();
    }

    default void addMediaBackLink(String mediaBackLink) {
        getMediaRssChannelData().addMediaBackLink(mediaBackLink);
    }

    default Optional<MediaStatus> getMediaStatus() {
        return getMediaRssChannelData().getMediaStatus();
    }

    default void setMediaStatus(MediaStatus mediaStatus) {
        getMediaRssChannelData().setMediaStatus(mediaStatus);
    }

    default List<MediaPrice> getMediaPrices() {
        return getMediaRssChannelData().getMediaPrices();
    }

    default void addMediaPrice(MediaPrice mediaPrice) {
        getMediaRssChannelData().addMediaPrice(mediaPrice);
    }

    default List<MediaLicense> getMediaLicenses() {
        return getMediaRssChannelData().getMediaLicenses();
    }

    default void addMediaLicense(MediaLicense mediaLicense) {
        getMediaRssChannelData().addMediaLicense(mediaLicense);
    }

    default List<MediaSubTitle> getMediaSubTitles() {
        return getMediaRssChannelData().getMediaSubTitles();
    }

    default void addMediaSubTitle(MediaSubTitle mediaSubTitle) {
        getMediaRssChannelData().addMediaSubTitle(mediaSubTitle);
    }

    default List<MediaPeerLink> getMediaPeerLinks() {
        return getMediaRssChannelData().getMediaPeerLinks();
    }

    default void addMediaPeerLink(MediaPeerLink mediaPeerLink) {
        getMediaRssChannelData().addMediaPeerLink(mediaPeerLink);
    }

    default List<MediaLocation> getMediaLocations() {
        return getMediaRssChannelData().getMediaLocations();
    }

    default void addMediaLocation(MediaLocation mediaLocation) {
        getMediaRssChannelData().addMediaLocation(mediaLocation);
    }

    default Optional<MediaRights> getMediaRights() {
        return getMediaRssChannelData().getMediaRights();
    }

    default void setMediaRights(MediaRights mediaRights) {
        getMediaRssChannelData().setMediaRights(mediaRights);
    }

    default List<MediaScene> getMediaScenes() {
        return getMediaRssChannelData().getMediaScenes();
    }

    default void addMediaScene(MediaScene mediaScenes) {
        getMediaRssChannelData().addMediaScene(mediaScenes);
    }
}
