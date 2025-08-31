package com.apptasticsoftware.rssreader.module.mediarss;

/**
 * Specifies the rights information for the media object.
 */
public class MediaRights {
    private String status;

    public enum Status {
        USER_CREATED("userCreated"),
        OFFICIAL("official");

        private final String value;

        Status(String value) {
            this.value = value;
        }

        public static Status of(String value) {
            if ("userCreated".equalsIgnoreCase(value)) {
                return USER_CREATED;
            } else if ("official".equalsIgnoreCase(value)) {
                return OFFICIAL;
            }
            return null;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    /**
     * The status of the media object saying whether a media object has been created by
     * the publisher or they have rights to circulate it.
     * Supported values are "userCreated" and "official"
     *
     * @return status
     */
    public Status getStatus() {
        return Status.of(status);
    }

    public String getStatusValue() {
        return status;
    }

    /**
     * The status of the media object saying whether a media object has been created by
     * the publisher or they have rights to circulate it.
     *
     * @param status status
     */
    public void setStatus(String status) {
        this.status = status;
     }
}
