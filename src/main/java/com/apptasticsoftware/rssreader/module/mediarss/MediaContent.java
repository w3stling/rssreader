package com.apptasticsoftware.rssreader.module.mediarss;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MediaContent {
    private String url;
    private Long fileSize;
    private String type;
    private String medium;
    private Boolean isDefault;
    private String expression;
    private Double bitrate;
    private Double framerate;
    private Double samplingrate;
    private Integer channels;
    private Integer duration;
    private Integer height;
    private Integer width;
    private String lang;
    private MediaTitle mediaTitle;
    private MediaDescription mediaDescription;
    private MediaThumbnail mediaThumbnail;
    private MediaPlayer mediaPlayer;
    private final List<MediaCredit> mediaCredits = new ArrayList<>();
    private MediaCopyright mediaCopyright;
    private final List<MediaHash> mediaHashes = new ArrayList<>();
    private final List<MediaCategory> mediaCategories = new ArrayList<>();
    private final List<MediaText> mediaTexts = new ArrayList<>();
    private MediaRating mediaRating;
    private final List<String> mediaKeywords = new ArrayList<>();
    private final List<MediaRestriction> mediaRestrictions = new ArrayList<>();
    private MediaCommunity mediaCommunity;
    private final List<String> mediaComments = new ArrayList<>();
    private final List<String> mediaResponses = new ArrayList<>();
    private final List<String> mediaBackLinks = new ArrayList<>();
    private MediaStatus mediaStatus;
    private final List<MediaPrice> mediaPrices = new ArrayList<>();
    private MediaEmbed mediaEmbed;
    private final List<MediaSubTitle> mediaSubTitles = new ArrayList<>();
    private final List<MediaPeerLink> mediaPeerLinks = new ArrayList<>();
    private final List<MediaLocation> mediaLocations = new ArrayList<>();
    private final List<MediaScene> mediaScenes = new ArrayList<>();

    // MediaRatings[]??, MediaThumbnail[]??, MediaLicense[]??,

    public Optional<String> getUrl() {
        return Optional.ofNullable(url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Optional<String> getLang() {
        return Optional.ofNullable(lang);
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Optional<Long> getFileSize() {
        return Optional.ofNullable(fileSize);
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Optional<String> getMedium() {
        return Optional.ofNullable(medium);
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public Optional<Boolean> isDefault() {
        return Optional.ofNullable(isDefault);
    }

    public void setDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Optional<String> getExpression() {
        return Optional.ofNullable(expression);
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    /**
     * The kilobits per second rate of media.
     * @return bitrate
     */
    public Optional<Double> getBitrate() {
        return Optional.ofNullable(bitrate);
    }

    /**
     * The kilobits per second rate of media.
     * @param bitrate bitrate
     */
    public void setBitrate(Double bitrate) {
        this.bitrate = bitrate;
    }

    /**
     * The number of frames per second for the media object.
     *
     * @return frame rate
     */
    public Optional<Double> getFramerate() {
        return Optional.ofNullable(framerate);
    }

    /**
     * The number of frames per second for the media object.
     *
     * @param framerate frame rate
     */
    public void setFramerate(Double framerate) {
        this.framerate = framerate;
    }

    /**
     * The number of samples per second taken to create the media object.
     * It is expressed in thousands of samples per second (kHz).
     *
     * @return sampling rate
     */
    public Optional<Double> getSamplingrate() {
        return Optional.ofNullable(samplingrate);
    }

    /**
     * The number of samples per second taken to create the media object.
     * It is expressed in thousands of samples per second (kHz).
     *
     * @param samplingrate sampling rate
     */
    public void setSamplingrate(Double samplingrate) {
        this.samplingrate = samplingrate;
    }

    /**
     * The number of audio channels in the media object.
     *
     * @return channels
     */
    public Optional<Integer> getChannels() {
        return Optional.ofNullable(channels);
    }

    /**
     * The number of audio channels in the media object.
     *
     * @param channels channels
     */
    public void setChannels(Integer channels) {
        this.channels = channels;
    }

    /**
     * The number of seconds the media object plays.
     * @return duration
     */
    public Optional<Integer> getDuration() {
        return Optional.ofNullable(duration);
    }

    /**
     * The number of seconds the media object plays.
     * @param duration duration
     */
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    /**
     * The height of the media object.
     * @return height
     */
    public Optional<Integer> getHeight() {
        return Optional.ofNullable(height);
    }

    /**
     * The height of the media object.
     * @param height height
     */
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     * The width of the media object.
     * @return width
     */
    public Optional<Integer> getWidth() {
        return Optional.ofNullable(width);
    }

    /**
     * The width of the media object.
     * @param width width
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

    public Optional<String> getType() {
        return Optional.ofNullable(type);
    }

    public void setType(String type) {
        this.type = type;
    }

    // ---

    public Optional<MediaTitle> getMediaTitle() {
        return Optional.ofNullable(mediaTitle);
    }

    public void setMediaTitle(MediaTitle mediaTitle) {
        this.mediaTitle = mediaTitle;
    }

    public Optional<MediaDescription> getMediaDesciption() {
        return Optional.ofNullable(mediaDescription);
    }

    public void setMediaDesciption(MediaDescription mediaDescription) {
        this.mediaDescription = mediaDescription;
    }

    public Optional<MediaPlayer> getMediaPlayer() {
        return Optional.ofNullable(mediaPlayer);
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    public List<MediaCredit> getMediaCredits() {
        return mediaCredits;
    }

    public void addMediaCredit(MediaCredit mediaCredit) {
        mediaCredits.add(mediaCredit);
    }

    public Optional<MediaCopyright> getMediaCopyright() {
        return Optional.ofNullable(mediaCopyright);
    }

    public void setMediaCopyright(MediaCopyright mediaCopyright) {
        this.mediaCopyright = mediaCopyright;
    }

    public List<MediaHash> getMediaHashes() {
        return mediaHashes;
    }

    public void addMediaHash(MediaHash mediaHash) {
        mediaHashes.add(mediaHash);
    }

    public List<MediaCategory> getMediaCategories() {
        return mediaCategories;
    }

    public void addMediaCategory(MediaCategory mediaCategory) {
        mediaCategories.add(mediaCategory);
    }

    public List<MediaText> getMediaTexts() {
        return mediaTexts;
    }

    public void addMediaText(MediaText mediaText) {
        mediaTexts.add(mediaText);
    }

    public Optional<MediaRating> getMediaRating() {
        return Optional.ofNullable(mediaRating);
    }

    public void setMediaRating(MediaRating mediaRating) {
        this.mediaRating = mediaRating;
    }

    public List<String> getMediaKeywords() {
        return mediaKeywords;
    }

    public void addMediaKeyword(String mediaKeyword) {
        mediaKeywords.add(mediaKeyword);
    }

    public List<MediaRestriction> getMediaRestrictions() {
        return mediaRestrictions;
    }

    public void addMediaRestriction(MediaRestriction mediaRestriction) {
        mediaRestrictions.add(mediaRestriction);
    }

    public Optional<MediaCommunity> getMediaCommunity() {
        return Optional.ofNullable(mediaCommunity);
    }

    public void setMediaCommunity(MediaCommunity mediaCommunity) {
        this.mediaCommunity = mediaCommunity;
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

}
