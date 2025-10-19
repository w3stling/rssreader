package com.apptasticsoftware.rssreader.module.podcast;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents a social interaction point for a podcast episode or the podcast as a whole.
 * This element specifies the "root post" of a comment thread that serves as the canonical
 * location for comments and discussion. It can be considered the "official" social media
 * comment space for the episode or podcast.
 *
 * &lt;p&gt;Multiple social interaction points can be specified, with priority determining
 * the display order. The tag can also be used to disable public comments by using
 * the "disabled" protocol value.
 */
public class PodcastSocialInteract {
    private String protocol;
    private String uri;
    private String accountId;
    private String accountUrl;
    private Integer priority;

    /**
     * Gets the protocol used for interacting with the comment root post.
     * A special value of "disabled" can be used to indicate that public comments
     * should not be shown alongside the episode or podcast.
     *
     * @return The protocol name (required).
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * Sets the protocol used for interacting with the comment root post.
     * @param protocol The protocol name (required). Use "disabled" to indicate
     *                that public comments should not be shown.
     */
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    /**
     * Gets the URI/URL of the root post comment.
     * For ActivityPub platforms (like Mastodon or Pleroma), it's recommended to use
     * the URI value (fully-formed URL with a GUID) rather than the HTML page URL.
     *
     * @return The URI of the root post comment (required).
     */
    public String getUri() {
        return uri;
    }

    /**
     * Sets the URI/URL of the root post comment.
     * @param uri The URI of the root post comment (required).
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * Gets the account identifier on the commenting platform.
     * @return An Optional containing the account ID of the root post creator,
     *         or empty if not provided (recommended but optional).
     */
    public Optional<String> getAccountId() {
        return Optional.ofNullable(accountId);
    }

    /**
     * Sets the account identifier on the commenting platform.
     * @param accountId The account ID of the root post creator (recommended).
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    /**
     * Gets the public URL of the account on the commenting platform.
     * @return An Optional containing the account's public URL,
     *         or empty if not provided.
     */
    public Optional<String> getAccountUrl() {
        return Optional.ofNullable(accountUrl);
    }

    /**
     * Sets the public URL of the account on the commenting platform.
     * @param accountUrl The public URL of the account that created the root post.
     */
    public void setAccountUrl(String accountUrl) {
        this.accountUrl = accountUrl;
    }

    /**
     * Gets the display priority of this social interaction point.
     * Lower numbers indicate higher priority. Used when multiple
     * social interaction points are present to determine display order.
     *
     * @return An Optional containing the priority value,
     *         or empty if not specified.
     */
    public Optional<Integer> getPriority() {
        return Optional.ofNullable(priority);
    }

    /**
     * Sets the display priority of this social interaction point.
     * @param priority The priority value (lower numbers mean higher priority).
     *                Used to determine display order when multiple social
     *                interaction points are present.
     */
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
