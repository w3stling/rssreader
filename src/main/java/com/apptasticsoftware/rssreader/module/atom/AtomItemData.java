package com.apptasticsoftware.rssreader.module.atom;

import com.apptasticsoftware.rssreader.module.atom.internal.AtomItemDataProvider;
import java.util.List;

/**
 * Provides access to Atom-specific data for a feed entry, including links, authors, and contributors.
 */
public interface AtomItemData {

    /**
     * Returns the list of Atom links.
     * @return the list of Atom links
     */
    default List<AtomLink> getAtomLinks() {
        return ((AtomItemDataProvider) this).atomItemData().getAtomLinks();
    }

    /**
     * Adds an Atom link.
     * @param atomLink the Atom link to add
     */
    default void addAtomLink(AtomLink atomLink) {
        ((AtomItemDataProvider) this).atomItemData().addAtomLink(atomLink);
    }

    /**
     * Returns the list of Atom authors.
     * @return the list of Atom authors
     */
    default List<AtomAuthor> getAtomAuthors() {
        return ((AtomItemDataProvider) this).atomItemData().getAtomAuthors();
    }

    /**
     * Adds an Atom author.
     * @param atomAuthor the Atom author to add
     */
    default void addAtomAuthor(AtomAuthor atomAuthor) {
        ((AtomItemDataProvider) this).atomItemData().addAtomAuthor(atomAuthor);
    }

    /**
     * Returns the list of Atom contributors.
     * @return the list of Atom contributors
     */
    default List<AtomContributor> getAtomContributors() {
        return ((AtomItemDataProvider) this).atomItemData().getAtomContributors();
    }

    /**
     * Adds an Atom contributor.
     * @param atomContributor the Atom contributor to add
     */
    default void addAtomContributor(AtomContributor atomContributor) {
        ((AtomItemDataProvider) this).atomItemData().addAtomContributor(atomContributor);
    }
}
