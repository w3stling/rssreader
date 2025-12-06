package com.apptasticsoftware.rssreader.module.podcast;

import java.util.Objects;
import java.util.Optional;

public class PodcastChat {
    private String server;
    private String protocol;
    private String accountId;
    private String space;

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Optional<String> getSpace() {
        return Optional.ofNullable(space);
    }

    public void setSpace(String space) {
        this.space = space;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PodcastChat that = (PodcastChat) o;
        return Objects.equals(getServer(), that.getServer()) && Objects.equals(getProtocol(), that.getProtocol()) && Objects.equals(getAccountId(), that.getAccountId()) && Objects.equals(getSpace(), that.getSpace());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getServer(), getProtocol(), getAccountId(), getSpace());
    }
}