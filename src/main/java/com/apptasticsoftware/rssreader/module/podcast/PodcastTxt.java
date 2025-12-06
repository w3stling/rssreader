package com.apptasticsoftware.rssreader.module.podcast;

import java.util.Objects;
import java.util.Optional;

public class PodcastTxt {
    private String txt;
    private String purpose;

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public Optional<String> getPurpose() {
        return Optional.ofNullable(purpose);
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PodcastTxt that = (PodcastTxt) o;
        return Objects.equals(getTxt(), that.getTxt()) && Objects.equals(getPurpose(), that.getPurpose());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTxt(), getPurpose());
    }
}
