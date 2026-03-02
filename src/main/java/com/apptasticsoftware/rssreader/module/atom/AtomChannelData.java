package com.apptasticsoftware.rssreader.module.atom;

import java.util.List;

/**
 * Provides access to Atom-specific data for a feed channel, including links, authors, and contributors.
 */
public interface AtomChannelData {

    /**
     * Returns the underlying Atom channel data.
     * @return the Atom channel data
     */
    AtomChannelData getAtomChannelData();

    /**
     * Returns the list of Atom links.
     * @return the list of Atom links
     */
    default List<AtomLink> getAtomLinks() {
        return getAtomChannelData().getAtomLinks();
    }

    /**
     * Adds an Atom link.
     * @param atomLink the Atom link to add
     */
    default void addAtomLink(AtomLink atomLink) {
        getAtomChannelData().addAtomLink(atomLink);
    }

    /**
     * Returns the list of Atom authors.
     * @return the list of Atom authors
     */
    default List<AtomAuthor> getAtomAuthors() {
        return getAtomChannelData().getAtomAuthors();
    }

    /**
     * Adds an Atom author.
     * @param atomAuthor the Atom author to add
     */
    default void addAtomAuthor(AtomAuthor atomAuthor) {
        getAtomChannelData().addAtomAuthor(atomAuthor);
    }

    /**
     * Returns the list of Atom contributors.
     * @return the list of Atom contributors
     */
    default List<AtomContributor> getAtomContributors() {
        return getAtomChannelData().getAtomContributors();
    }

    /**
     * Adds an Atom contributor.
     * @param atomContributor the Atom contributor to add
     */
    default void addAtomContributor(AtomContributor atomContributor) {
        getAtomChannelData().addAtomContributor(atomContributor);
    }
}
