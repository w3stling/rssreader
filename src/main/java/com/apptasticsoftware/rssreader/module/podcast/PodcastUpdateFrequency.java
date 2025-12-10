package com.apptasticsoftware.rssreader.module.podcast;

import com.apptasticsoftware.rssreader.util.Default;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Optional;

/**
 * Represents the {@code &lt;podcast:updateFrequency&gt;} element for expressing a podcast's release schedule.
 * <p>
 * Contains a free-form description (max 128 characters) and optional attributes: {@code complete},
 * {@code dtstart} (ISO8601 date/datetime), and {@code rrule} (iCalendar RFC 5545 recurrence rule).
 * </p>
 *
 * @see <a href="https://www.rfc-editor.org/rfc/rfc5545#section-3.3.10">iCalendar RFC 5545 Section 3.3.10</a>
 */
public class PodcastUpdateFrequency {
    private String updateFrequency;
    private boolean complete;
    private String dtstart;
    private String rrule;

    /**
     * Gets the node value describing the update frequency.
     * <p>
     * This is a free-form string that might be displayed alongside other information about the podcast.
     * The value should not exceed 128 characters to avoid truncation by aggregators.
     * </p>
     *
     * @return the update frequency string, or {@code null} if not set
     */
    public String getUpdateFrequency() {
        return updateFrequency;
    }

    /**
     * Sets the node value describing the update frequency.
     * <p>
     * This is a free-form string that might be displayed alongside other information about the podcast.
     * Please do not exceed 128 characters for this value or it may be truncated by aggregators.
     * </p>
     *
     * @param updateFrequency the update frequency string (preferably not exceeding 128 characters)
     */
    public void setUpdateFrequency(String updateFrequency) {
        this.updateFrequency = updateFrequency;
    }

    /**
     * Checks if the podcast is complete with no intention to release further episodes.
     * <p>
     * This boolean attribute specifies if the podcast has no intention to release further episodes.
     * If not set, this should be assumed to be {@code false}.
     * </p>
     *
     * @return {@code true} if the podcast is complete, {@code false} otherwise
     */
    public boolean isComplete() {
        return complete;
    }

    /**
     * Sets whether the podcast is complete with no intention to release further episodes.
     * <p>
     * This boolean attribute specifies if the podcast has no intention to release further episodes.
     * </p>
     *
     * @param complete {@code true} if the podcast is complete, {@code false} otherwise
     */
    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    /**
     * Gets the date or datetime the recurrence rule begins.
     * <p>
     * This is an ISO8601-formatted string representing when the recurrence rule starts.
     * If the recurrence rule contains a value for {@code COUNT}, this attribute is required.
     * If the recurrence rule contains a value for {@code UNTIL}, this attribute must be formatted
     * to the same date/datetime standard as the UNTIL value.
     * </p>
     *
     * @return an {@link Optional} containing the ISO8601-formatted dtstart string, or an empty Optional if not set
     */
    public Optional<String> getDtstart() {
        return Optional.ofNullable(dtstart);
    }

    /**
     * Gets the date or datetime the recurrence rule begins as a {@link ZonedDateTime}.
     * <p>
     * This is a parsed version of the ISO8601-formatted dtstart string.
     * If the recurrence rule contains a value for {@code COUNT}, this attribute is required.
     * If the recurrence rule contains a value for {@code UNTIL}, this attribute must be formatted
     * to the same date/datetime standard as the UNTIL value.
     * </p>
     *
     * @return an {@link Optional} containing the parsed {@link ZonedDateTime}, or an empty Optional if not set
     */
    public Optional<ZonedDateTime> getDtstartAsZonedDateTime() {
        return Optional.ofNullable(dtstart).map(time -> Default.getDateTimeParser().parse(time));
    }

    /**
     * Sets the date or datetime the recurrence rule begins.
     * <p>
     * This should be an ISO8601-formatted string representing when the recurrence rule starts.
     * If the recurrence rule contains a value for {@code COUNT}, this attribute is required.
     * If the recurrence rule contains a value for {@code UNTIL}, this attribute must be formatted
     * to the same date/datetime standard as the UNTIL value.
     * </p>
     *
     * @param dtstart the ISO8601-formatted date/datetime string when the recurrence rule begins
     */
    public void setDtstart(String dtstart) {
        this.dtstart = dtstart;
    }

    /**
     * Gets the recurrence rule for this update frequency.
     * <p>
     * This is a recurrence rule as defined in iCalendar RFC 5545 Section 3.3.10.
     * The rule defines the frequency and pattern of release episodes.
     * </p>
     * <p>
     * Common examples include:
     * </p>
     * <ul>
     *     <li>{@code FREQ=DAILY} - Daily releases</li>
     *     <li>{@code FREQ=WEEKLY} - Weekly releases</li>
     *     <li>{@code FREQ=WEEKLY;INTERVAL=2} - Biweekly releases</li>
     *     <li>{@code FREQ=WEEKLY;BYDAY=MO,WE} - Every Monday and Wednesday</li>
     *     <li>{@code FREQ=WEEKLY;UNTIL=2023-12-31T00:00:00.000Z;BYDAY=MO} - Every Monday until a specific date</li>
     *     <li>{@code FREQ=WEEKLY;INTERVAL=2;BYDAY=MO;COUNT=10} - Biweekly on Monday for 10 episodes</li>
     * </ul>
     *
     * @return the recurrence rule string, or {@code null} if not set
     * @see <a href="https://www.rfc-editor.org/rfc/rfc5545#section-3.3.10">iCalendar RFC 5545 Section 3.3.10</a>
     */
    public String getRrule() {
        return rrule;
    }

    /**
     * Sets the recurrence rule for this update frequency.
     * <p>
     * This should be a recurrence rule as defined in iCalendar RFC 5545 Section 3.3.10.
     * The rule defines the frequency and pattern of release episodes.
     * </p>
     *
     * @param rrule the recurrence rule string
     * @see <a href="https://www.rfc-editor.org/rfc/rfc5545#section-3.3.10">iCalendar RFC 5545 Section 3.3.10</a>
     */
    public void setRrule(String rrule) {
        this.rrule = rrule;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PodcastUpdateFrequency that = (PodcastUpdateFrequency) o;
        return Objects.equals(getUpdateFrequency(), that.getUpdateFrequency()) &&
               Objects.equals(isComplete(), that.isComplete()) &&
               Objects.equals(getDtstart(), that.getDtstart()) &&
               Objects.equals(getRrule(), that.getRrule());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUpdateFrequency(), isComplete(), getDtstart(), getRrule());
    }
}
