package com.apptasticsoftware.rssreader.module.mediarss;

import java.util.Optional;

/**
 * Specifies the status of a media object.
 */
public class MediaStatus {
    private String state;
    private String reason;

    /**
     * The status of a media object.
     * @return status
     */
    public String getState() {
        return state;
    }

    /**
     * The status of a media object.
     * @param state state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * The reason explaining why a media object has been blocked/deleted.
     * @return reason
     */
    public Optional<String> getReason() {
        return Optional.ofNullable(reason);
    }

    /**
     * The reason explaining why a media object has been blocked/deleted.
     * @param reason reason
     */
    public void setReason(String reason) {
        this.reason = reason;
    }
}
