package com.apptasticsoftware.rssreader.module.mediarss;

import com.apptasticsoftware.rssreader.Channel;
import com.apptasticsoftware.rssreader.DateTimeParser;

import java.util.List;
import java.util.Optional;

public class MediaRssChannel extends Channel implements Metadata {
    private final Metadata metadata = new MetadataImpl();

    public MediaRssChannel(DateTimeParser dateTimeParser) {
        super(dateTimeParser);
    }

    @Override
    public List<MediaRating> getMediaRatings() {
        return metadata.getMediaRatings();
    }

    @Override
    public void addMediaRating(MediaRating mediaRating) {
        metadata.addMediaRating(mediaRating);
    }

    @Override
    public Optional<MediaTitle> getMediaTitle() {
        return metadata.getMediaTitle();
    }

    @Override
    public void setMediaTitle(MediaTitle mediaTitle) {
        metadata.setMediaTitle(mediaTitle);
    }

    @Override
    public Optional<MediaDescription> getMediaDescription() {
        return metadata.getMediaDescription();
    }

    @Override
    public void setMediaDescription(MediaDescription mediaDescription) {
        metadata.setMediaDescription(mediaDescription);
    }

    @Override
    public List<String> getMediaKeywords() {
        return metadata.getMediaKeywords();
    }

    @Override
    public void addMediaKeyword(String mediaKeyword) {
        metadata.addMediaKeyword(mediaKeyword);
    }

    @Override
    public List<MediaThumbnail> getMediaThumbnails() {
        return metadata.getMediaThumbnails();
    }

    @Override
    public void addMediaThumbnail(MediaThumbnail mediaThumbnail) {
        metadata.addMediaThumbnail(mediaThumbnail);
    }

    @Override
    public List<MediaCategory> getMediaCategories() {
        return metadata.getMediaCategories();
    }

    @Override
    public void addMediaCategory(MediaCategory mediaCategory) {
        metadata.addMediaCategory(mediaCategory);
    }

    @Override
    public List<MediaHash> getMediaHashes() {
        return metadata.getMediaHashes();
    }

    @Override
    public void addMediaHash(MediaHash mediaHash) {
        metadata.addMediaHash(mediaHash);
    }

    @Override
    public Optional<MediaPlayer> getMediaPlayer() {
        return metadata.getMediaPlayer();
    }

    @Override
    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        metadata.setMediaPlayer(mediaPlayer);
    }

    @Override
    public List<MediaCredit> getMediaCredits() {
        return metadata.getMediaCredits();
    }

    @Override
    public void addMediaCredit(MediaCredit mediaCredit) {
        metadata.addMediaCredit(mediaCredit);
    }

    @Override
    public Optional<MediaCopyright> getMediaCopyright() {
        return metadata.getMediaCopyright();
    }

    @Override
    public void setMediaCopyright(MediaCopyright mediaCopyright) {
        metadata.setMediaCopyright(mediaCopyright);
    }

    @Override
    public List<MediaText> getMediaTexts() {
        return metadata.getMediaTexts();
    }

    @Override
    public void addMediaText(MediaText mediaText) {
        metadata.addMediaText(mediaText);
    }

    @Override
    public List<MediaRestriction> getMediaRestrictions() {
        return metadata.getMediaRestrictions();
    }

    @Override
    public void addMediaRestriction(MediaRestriction mediaRestriction) {
        metadata.addMediaRestriction(mediaRestriction);
    }

    @Override
    public Optional<MediaCommunity> getMediaCommunity() {
        return metadata.getMediaCommunity();
    }

    @Override
    public void setMediaCommunity(MediaCommunity mediaCommunity) {
        metadata.setMediaCommunity(mediaCommunity);
    }

    @Override
    public List<String> getMediaComments() {
        return metadata.getMediaComments();
    }

    @Override
    public void addMediaComment(String mediaComment) {
        metadata.addMediaComment(mediaComment);
    }

    @Override
    public Optional<MediaEmbed> getMediaEmbed() {
        return metadata.getMediaEmbed();
    }

    @Override
    public void setMediaEmbed(MediaEmbed mediaEmbed) {
        metadata.setMediaEmbed(mediaEmbed);
    }

    @Override
    public List<String> getMediaResponses() {
        return metadata.getMediaResponses();
    }

    @Override
    public void addMediaResponse(String mediaResponse) {
        metadata.addMediaResponse(mediaResponse);
    }

    @Override
    public List<String> getMediaBackLinks() {
        return metadata.getMediaBackLinks();
    }

    @Override
    public void addMediaBackLink(String mediaBackLink) {
        metadata.addMediaBackLink(mediaBackLink);
    }

    @Override
    public Optional<MediaStatus> getMediaStatus() {
        return metadata.getMediaStatus();
    }

    @Override
    public void setMediaStatus(MediaStatus mediaStatus) {
        metadata.setMediaStatus(mediaStatus);
    }

    @Override
    public List<MediaPrice> getMediaPrices() {
        return metadata.getMediaPrices();
    }

    @Override
    public void addMediaPrice(MediaPrice mediaPrice) {
        metadata.getMediaPrices();
    }

    @Override
    public List<MediaLicense> getMediaLicenses() {
        return metadata.getMediaLicenses();
    }

    @Override
    public void addMediaLicense(MediaLicense mediaLicense) {
        metadata.addMediaLicense(mediaLicense);
    }

    @Override
    public List<MediaSubTitle> getMediaSubTitles() {
        return metadata.getMediaSubTitles();
    }

    @Override
    public void addMediaSubTitle(MediaSubTitle mediaSubTitle) {
        metadata.addMediaSubTitle(mediaSubTitle);
    }

    @Override
    public List<MediaPeerLink> getMediaPeerLinks() {
        return metadata.getMediaPeerLinks();
    }

    @Override
    public void addMediaPeerLink(MediaPeerLink mediaPeerLink) {
        metadata.addMediaPeerLink(mediaPeerLink);
    }

    @Override
    public List<MediaLocation> getMediaLocations() {
        return metadata.getMediaLocations();
    }

    @Override
    public void addMediaLocation(MediaLocation mediaLocation) {
        metadata.addMediaLocation(mediaLocation);
    }

    @Override
    public Optional<MediaRights> getMediaRights() {
        return metadata.getMediaRights();
    }

    @Override
    public void setMediaRights(MediaRights mediaRights) {
        metadata.setMediaRights(mediaRights);
    }

    @Override
    public List<MediaScene> getMediaScenes() {
        return metadata.getMediaScenes();
    }

    @Override
    public void addMediaScene(MediaScene mediaScenes) {
        metadata.addMediaScene(mediaScenes);
    }
}
