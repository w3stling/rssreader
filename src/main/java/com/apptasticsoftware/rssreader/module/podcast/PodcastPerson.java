package com.apptasticsoftware.rssreader.module.podcast;

import java.util.Objects;
import java.util.Optional;

public class PodcastPerson {
    private String person;
    private String role;
    private String group;
    private String img;
    private String href;

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public Optional<String> getRole() {
        return Optional.ofNullable(role);
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Optional<String> getGroup() {
        return Optional.ofNullable(group);
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Optional<String> getImg() {
        return Optional.ofNullable(img);
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Optional<String> getHref() {
        return Optional.ofNullable(href);
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PodcastPerson that = (PodcastPerson) o;
        return Objects.equals(getPerson(), that.getPerson()) && Objects.equals(getRole(), that.getRole()) && Objects.equals(getGroup(), that.getGroup()) && Objects.equals(getImg(), that.getImg()) && Objects.equals(getHref(), that.getHref());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPerson(), getRole(), getGroup(), getImg(), getHref());
    }
}
