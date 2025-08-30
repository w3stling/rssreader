package com.apptasticsoftware.rssreader.module.mediarss;

import java.util.Objects;
import java.util.Optional;

/**
 * Notable entity and the contribution to the creation of the media object.
 * Current entities can include people, companies, locations, etc. Specific entities can have multiple roles,
 * and several entities can have the same role. These should appear as distinct <media:credit> elements
 */
public class MediaCredit {
    private String role;
    private String scheme;
    private String credit;

    /**
     * Specifies the role the entity played.
     * @return role
     */
    public Optional<String> getRole() {
        return Optional.ofNullable(role);
    }

    /**
     * Specifies the role the entity played.
     * @param role role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * The URI that identifies the role scheme.
     * @return scheme
     */
    public Optional<String> getScheme() {
        return Optional.ofNullable(scheme);
    }

    /**
     * The URI that identifies the role scheme.
     * @param scheme scheme
     */
    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    /**
     * Notable entity and the contribution to the creation of the media object.
     * @return credit
     */
    public String getCredit() {
        return credit;
    }

    /**
     * Notable entity and the contribution to the creation of the media object.
     * @param credit credit
     */
    public void setCredit(String credit) {
        this.credit = credit;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MediaCredit that = (MediaCredit) o;
        return Objects.equals(getRole(), that.getRole()) && Objects.equals(getScheme(), that.getScheme()) && Objects.equals(getCredit(), that.getCredit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRole(), getScheme(), getCredit());
    }
}
