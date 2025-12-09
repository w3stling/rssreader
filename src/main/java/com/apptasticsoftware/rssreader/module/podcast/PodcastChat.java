package com.apptasticsoftware.rssreader.module.podcast;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents podcast chat information for a channel, item, or live event.
 * <p>
 * This element allows podcasters to attach information about where the "official" chat
 * for the podcast, a specific episode, or a live event can be found. It supports various
 * chat protocols (IRC, XMPP, Nostr, Matrix, etc.) and is flexible enough to adapt to
 * different communication protocols.
 * </p>
 * <p>
 * The presence of this element at a particular level (channel, item, or liveItem) governs
 * how it should be treated. At the item or liveItem level, it overrides channel-level settings.
 * At the channel level, it represents the chat for the entire podcast.
 * </p>
 *
 * @see <a href="https://podcastindex.org/namespace/">Podcast Index Specification</a>
 */
public class PodcastChat {
    private String server;
    private String protocol;
    private String accountId;
    private String space;

    /**
     * Gets the FQDN of the chat server.
     *
     * @return the fully qualified domain name of the chat server (required)
     */
    public String getServer() {
        return server;
    }

    /**
     * Sets the FQDN of the chat server.
     *
     * @param server the fully qualified domain name of the chat server that serves as the
     *               "bootstrap" server to connect to (required)
     */
    public void setServer(String server) {
        this.server = server;
    }

    /**
     * Gets the chat protocol in use on the server.
     *
     * @return the protocol name (e.g., "irc", "xmpp", "nostr", "matrix") (required)
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * Sets the chat protocol in use on the server.
     *
     * @param protocol the protocol in use on the server (required)
     */
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    /**
     * Gets the account ID of the podcaster on the chat server.
     *
     * @return the account ID on the server or platform (recommended)
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * Sets the account ID of the podcaster on the chat server.
     *
     * @param accountId the account ID of the podcaster on the server or platform being
     *                  connected to (recommended)
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    /**
     * Gets the chat space, room, or topic on the server.
     *
     * @return an Optional containing the chat space (optional)
     */
    public Optional<String> getSpace() {
        return Optional.ofNullable(space);
    }

    /**
     * Sets the chat space, room, or topic on the server.
     *
     * @param space a chat "space" or "room" or "topic" identifier used by some chat
     *              systems (optional)
     */
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