package com.apptasticsoftware.rssreader.module.atom;

import java.util.List;

/**
 * Provides access to Atom-specific data for a feed entry, including links, authors, and contributors.
 */
public interface AtomItemData {

    /**
     * Returns the underlying Atom item data.
     * @return the Atom item data
     */
    AtomItemData getAtomItemData();

    /**
     * Returns the list of Atom links.
     * @return the list of Atom links
     */
    default List<AtomLink> getAtomLinks() {
        return getAtomItemData().getAtomLinks();
    }

    /**
     * Adds an Atom link.
     * @param atomLink the Atom link to add
     */
    default void addAtomLink(AtomLink atomLink) {
        getAtomItemData().addAtomLink(atomLink);
    }

    /**
     * Returns the list of Atom authors.
     * @return the list of Atom authors
     */
    default List<AtomAuthor> getAtomAuthors() {
        return getAtomItemData().getAtomAuthors();
    }

    /**
     * Adds an Atom author.
     * @param atomAuthor the Atom author to add
     */
    default void addAtomAuthor(AtomAuthor atomAuthor) {
        getAtomItemData().addAtomAuthor(atomAuthor);
    }

    /**
     * Returns the list of Atom contributors.
     * @return the list of Atom contributors
     */
    default List<AtomContributor> getAtomContributors() {
        return getAtomItemData().getAtomContributors();
    }

    /**
     * Adds an Atom contributor.
     * @param atomContributor the Atom contributor to add
     */
    default void addAtomContributor(AtomContributor atomContributor) {
        getAtomItemData().addAtomContributor(atomContributor);
    }
}
