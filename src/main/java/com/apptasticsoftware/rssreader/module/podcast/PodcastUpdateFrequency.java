package com.apptasticsoftware.rssreader.module.podcast;

import com.apptasticsoftware.rssreader.util.Default;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Optional;


public class PodcastUpdateFrequency {
    private String updateFrequency;
    private boolean complete;
    private String dtstart;
    private String rrule;

    public String getUpdateFrequency() {
        return updateFrequency;
    }
    public void setUpdateFrequency(String updateFrequency) {
        this.updateFrequency = updateFrequency;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public Optional<String> getDtstart() {
        return Optional.ofNullable(dtstart);
    }

    public Optional<ZonedDateTime> getDtstartAsZonedDateTime() {
        return Optional.ofNullable(dtstart).map(time -> Default.getDateTimeParser().parse(time));
    }

    public void setDtstart(String dtstart) {
        this.dtstart = dtstart;
    }

    public String getRrule() {
        return rrule;
    }

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
