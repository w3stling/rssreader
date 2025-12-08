package com.apptasticsoftware.rssreader.module.mediarss;

import java.util.Objects;
import java.util.Optional;

/**
 * Specifies the status of a media object. This element indicates whether a media object is still active
 * or if it has been blocked/deleted by the publisher. The status can be "active", "blocked", or "deleted",
 * and may include a reason explaining why the media object has been blocked or deleted.
 *
 * Example:
 * {@code <media:status state="blocked" reason="http://www.reasonforblocking.com" />}
 *
 * Values for state:
 * - "active": media object is active in the system
 * - "blocked": media object is blocked by the publisher
 * - "deleted": media object has been deleted by the publisher
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MediaStatus that = (MediaStatus) o;
        return Objects.equals(getState(), that.getState()) && Objects.equals(getReason(), that.getReason());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getState(), getReason());
    }
}
