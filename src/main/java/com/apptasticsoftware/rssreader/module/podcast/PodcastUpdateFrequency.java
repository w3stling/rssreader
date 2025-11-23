package com.apptasticsoftware.rssreader.module.podcast;

import java.util.Objects;
import java.util.Optional;


public class PodcastUpdateFrequency {
    private String updateFrequency;
    private String complete;
    private String dtstart;
    private String rrule;

    public String getUpdateFrequency() {
        return updateFrequency;
    }
    public void setUpdateFrequency(String updateFrequency) {
        this.updateFrequency = updateFrequency;
    }

    public Optional<String> getComplete() {
        return Optional.ofNullable(complete);
    }

    public void setComplete(String complete) {
        this.complete = complete;
    }

    public Optional<String> getDtstart() {
        return Optional.ofNullable(dtstart);
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
               Objects.equals(getComplete(), that.getComplete()) &&
               Objects.equals(getDtstart(), that.getDtstart()) &&
               Objects.equals(getRrule(), that.getRrule());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUpdateFrequency(), getComplete(), getDtstart(), getRrule());
    }
}
