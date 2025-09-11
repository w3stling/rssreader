package com.apptasticsoftware.rssreader.module.mediarss;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents a media object that can be used to publish any type of media. It contains attributes
 * for specifying the content URL, format, size, and other media-specific characteristics.
 *
 * Example:
 * {@code
 * <media:content
 *     url="http://www.foo.com/movie.mov"
 *     fileSize="12216320"
 *     type="video/quicktime"
 *     medium="video"
 *     isDefault="true"
 *     expression="full"
 *     bitrate="128"
 *     framerate="25"
 *     samplingrate="44.1"
 *     channels="2"
 *     duration="185"
 *     height="200"
 *     width="300"
 *     lang="en" />
 * }
 */
public class MediaContent extends MediaOptionalFieldsImpl{
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MediaContent content = (MediaContent) o;
        return Objects.equals(getUrl(), content.getUrl()) && Objects.equals(getFileSize(), content.getFileSize()) && Objects.equals(getType(), content.getType()) && Objects.equals(getMedium(), content.getMedium()) && Objects.equals(isDefault, content.isDefault) && Objects.equals(getExpression(), content.getExpression()) && Objects.equals(getBitrate(), content.getBitrate()) && Objects.equals(getFramerate(), content.getFramerate()) && Objects.equals(getSamplingrate(), content.getSamplingrate()) && Objects.equals(getChannels(), content.getChannels()) && Objects.equals(getDuration(), content.getDuration()) && Objects.equals(getHeight(), content.getHeight()) && Objects.equals(getWidth(), content.getWidth()) && Objects.equals(getLang(), content.getLang());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getUrl(), getFileSize(), getType(), getMedium(), isDefault, getExpression(), getBitrate(), getFramerate(), getSamplingrate(), getChannels(), getDuration(), getHeight(), getWidth(), getLang());
    }
}
