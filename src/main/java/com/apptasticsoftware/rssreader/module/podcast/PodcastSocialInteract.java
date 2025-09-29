package com.apptasticsoftware.rssreader.module.podcast;

import java.util.Objects;
import java.util.Optional;

public class PodcastSocialInteract {
    private String protocol;
    private String uri;
    private String accountId;
    private String accountUrl;
    private Integer priority;

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Optional<String> getAccountId() {
        return Optional.ofNullable(accountId);
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Optional<String> getAccountUrl() {
        return Optional.ofNullable(accountUrl);
    }

    public void setAccountUrl(String accountUrl) {
        this.accountUrl = accountUrl;
    }

    public Optional<Integer> getPriority() {
        return Optional.ofNullable(priority);
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PodcastSocialInteract that = (PodcastSocialInteract) o;
        return Objects.equals(getProtocol(), that.getProtocol()) && Objects.equals(getUri(), that.getUri()) && Objects.equals(getAccountId(), that.getAccountId()) && Objects.equals(getAccountUrl(), that.getAccountUrl()) && Objects.equals(getPriority(), that.getPriority());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProtocol(), getUri(), getAccountId(), getAccountUrl(), getPriority());
    }
}
