package com.apptasticsoftware.rssreader.module.mediarss;

import java.util.Objects;

/**
 * Represents a scene within a media object. Each scene can have a title, description, start time,
 * and end time to describe different segments of the media content.
 * <p>
 * Example:
 * {@code 
 * <media:scenes>
 *     <media:scene>
 *         <sceneTitle>Hurricane Florence Makes Landfall</sceneTitle>
 *         <sceneDescription>Category 1 hurricane hits the coast</sceneDescription>
 *         <sceneStartTime>00:15</sceneStartTime>
 *         <sceneEndTime>00:45</sceneEndTime>
 *     </media:scene>
 * </media:scenes>
 * }
 * 
 * In this example, the scene describes a 30-second segment about a hurricane landfall,
 * starting at 15 seconds into the media and ending at 45 seconds.
 */
public class MediaScene {
    private String sceneTitle;
    private String sceneDescription;
    private String sceneStartTime;
    private String sceneEndTime;

    /**
     * The title of a particular scene in the media.
     * @return scene title
     */
    public String getSceneTitle() {
        return sceneTitle;
    }

    /**
     * The title of a particular scene in the media.
     * @param sceneTitle scene title
     */
    public void setSceneTitle(String sceneTitle) {
        this.sceneTitle = sceneTitle;
    }

    /**
     * The description of a particular scene in the media.
     * @return scene description
     */
    public String getSceneDescription() {
        return sceneDescription;
    }

    /**
     * The description of a particular scene in the media.
     * @param sceneDescription scene description
     */
    public void setSceneDescription(String sceneDescription) {
        this.sceneDescription = sceneDescription;
    }

    /**
     * The start time of a particular scene in the media.
     * @return scene start time
     */
    public String getSceneStartTime() {
        return sceneStartTime;
    }

    /**
     * The start time of a particular scene in the media.
     * @param sceneStartTime scene start time
     */
    public void setSceneStartTime(String sceneStartTime) {
        this.sceneStartTime = sceneStartTime;
    }

    /**
     * The end time of a particular scene in the media.
     * @return scene end time
     */
    public String getSceneEndTime() {
        return sceneEndTime;
    }

    /**
     * The end time of a particular scene in the media.
     * @param sceneEndTime scene end time
     */
    public void setSceneEndTime(String sceneEndTime) {
        this.sceneEndTime = sceneEndTime;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MediaScene that = (MediaScene) o;
        return Objects.equals(getSceneTitle(), that.getSceneTitle()) && Objects.equals(getSceneDescription(), that.getSceneDescription()) && Objects.equals(getSceneStartTime(), that.getSceneStartTime()) && Objects.equals(getSceneEndTime(), that.getSceneEndTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSceneTitle(), getSceneDescription(), getSceneStartTime(), getSceneEndTime());
    }
}
