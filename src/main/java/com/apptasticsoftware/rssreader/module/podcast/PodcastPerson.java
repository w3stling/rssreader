package com.apptasticsoftware.rssreader.module.podcast;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents a person of interest to a podcast, primarily intended to identify people like hosts, co-hosts, and guests.
 * This class is flexible enough to allow fuller credits using roles and groups as defined in the Podcast Taxonomy Project.
 */
public class PodcastPerson {
    private String person;
    private String role;
    private String group;
    private String img;
    private String href;

    /**
     * Gets the name of the person.
     * @return the person's name
     */
    public String getPerson() {
        return person;
    }

    /**
     * Sets the name of the person.
     * @param person the person's name to set
     */
    public void setPerson(String person) {
        this.person = person;
    }

    /**
     * Gets the role of the person.
     * If not specified, "host" is assumed.
     * @return an Optional containing the person's role, or empty if not set
     */
    public Optional<String> getRole() {
        return Optional.ofNullable(role);
    }

    /**
     * Sets the role of the person.
     * Should reference an official role from the Podcast Taxonomy Project list.
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Gets the group classification of the person.
     * If not specified, "cast" is assumed.
     * @return an Optional containing the person's group, or empty if not set
     */
    public Optional<String> getGroup() {
        return Optional.ofNullable(group);
    }

    /**
     * Sets the group classification of the person.
     * Should reference an official group from the Podcast Taxonomy Project list.
     * @param group the group to set
     */
    public void setGroup(String group) {
        this.group = group;
    }

    /**
     * Gets the URL of the person's image or avatar.
     * @return an Optional containing the image URL, or empty if not set
     */
    public Optional<String> getImg() {
        return Optional.ofNullable(img);
    }

    /**
     * Sets the URL of the person's image or avatar.
     * @param img the image URL to set
     */
    public void setImg(String img) {
        this.img = img;
    }

    /**
     * Gets the URL of the person's related resource (e.g., homepage or profile).
     * @return an Optional containing the resource URL, or empty if not set
     */
    public Optional<String> getHref() {
        return Optional.ofNullable(href);
    }

    /**
     * Sets the URL of the person's related resource.
     * @param href the resource URL to set
     */
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
