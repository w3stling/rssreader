package com.apptasticsoftware.rssreader.module.atom;

import com.apptasticsoftware.rssreader.module.atom.internal.AtomChannelDataProvider;
import java.util.List;

/**
 * Provides access to Atom-specific data for a feed channel, including links, authors, and contributors.
 */
public interface AtomChannelData {

    /**
     * Returns the list of Atom links.
     * @return the list of Atom links
     */
    default List<AtomLink> getAtomLinks() {
        return ((AtomChannelDataProvider) this).atomChannelData().getAtomLinks();
    }

    /**
     * Adds an Atom link.
     * @param atomLink the Atom link to add
     */
    default void addAtomLink(AtomLink atomLink) {
        ((AtomChannelDataProvider) this).atomChannelData().addAtomLink(atomLink);
    }

    /**
     * Returns the list of Atom authors.
     * @return the list of Atom authors
     */
    default List<AtomAuthor> getAtomAuthors() {
        return ((AtomChannelDataProvider) this).atomChannelData().getAtomAuthors();
    }

    /**
     * Adds an Atom author.
     * @param atomAuthor the Atom author to add
     */
    default void addAtomAuthor(AtomAuthor atomAuthor) {
        ((AtomChannelDataProvider) this).atomChannelData().addAtomAuthor(atomAuthor);
    }

    /**
     * Returns the list of Atom contributors.
     * @return the list of Atom contributors
     */
    default List<AtomContributor> getAtomContributors() {
        return ((AtomChannelDataProvider) this).atomChannelData().getAtomContributors();
    }

    /**
     * Adds an Atom contributor.
     * @param atomContributor the Atom contributor to add
     */
    default void addAtomContributor(AtomContributor atomContributor) {
        ((AtomChannelDataProvider) this).atomChannelData().addAtomContributor(atomContributor);
    }
}
