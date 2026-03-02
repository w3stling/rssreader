package com.apptasticsoftware.rssreader.module.atom;

/**
 * Represents an Atom author element describing a person, corporation, or similar entity.
 * Contains one required element (name) and two optional elements (uri, email).
 */
public class AtomAuthor {
    String name;
    String uri;
    String email;

    /**
     * Gets the human-readable name for the person.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the human-readable name for the person.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the home page for the person.
     *
     * @return the URI
     */
    public String getUri() {
        return uri;
    }

    /**
     * Sets the home page for the person.
     *
     * @param uri the URI
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * Gets the email address for the person.
     *
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address for the person.
     *
     * @param email the email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
