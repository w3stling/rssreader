package com.apptasticsoftware.rssreader.module.mediarss;

/**
 * Specifies the rights information for the media object. This element indicates whether
 * the media object has been created by the publisher or if they have rights to circulate it.
 * The status attribute is required and must be either "userCreated" or "official".
 *
 * Example:
 * {@code
 * <item>
 *     <title>User Submitted Video</title>
 *     <media:content url="http://videos.example.com/user/12345.mp4" />
 *     <media:rights status="userCreated" />
 * </item>
 *
 * <item>
 *     <title>Official News Report</title>
 *     <media:content url="http://videos.example.com/news/report.mp4" />
 *     <media:rights status="official" />
 * </item>
 * }
 */
public class MediaRights {
    private String status;

    public String getStatus() {
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
