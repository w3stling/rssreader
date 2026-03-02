package com.apptasticsoftware.rssreader.module.atom.internal;

import com.apptasticsoftware.rssreader.module.atom.AtomAuthor;
import com.apptasticsoftware.rssreader.module.atom.AtomContributor;
import com.apptasticsoftware.rssreader.module.atom.AtomLink;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.apptasticsoftware.rssreader.util.Mapper.emptyListIfNull;

/**
 * Container for Atom feed metadata including links, authors, and contributors.
 */
public class MetaData {
    private List<AtomLink> atomLinks;
    private List<AtomAuthor> atomAuthors;
    private List<AtomContributor> atomContributors;

    /**
     * Gets the list of Atom links.
     *
     * @return the list of Atom links
     */
    public List<AtomLink> getAtomLinks() {
        return emptyListIfNull(atomLinks);
    }

    /**
     * Adds an Atom link to the list.
     *
     * @param atomLink the Atom link to add
     */
    public void addAtomLink(AtomLink atomLink) {
        if (atomLinks == null) {
            atomLinks = new ArrayList<>();
        }
        atomLinks.add(atomLink);
    }

    /**
     * Gets the list of Atom authors.
     *
     * @return the list of Atom authors
     */
    public List<AtomAuthor> getAtomAuthors() {
        return emptyListIfNull(atomAuthors);
    }

    /**
     * Adds an Atom author to the list.
     *
     * @param atomAuthor the Atom author to add
     */
    public void addAtomAuthor(AtomAuthor atomAuthor) {
        if (atomAuthors == null) {
            atomAuthors = new ArrayList<>();
        }
        atomAuthors.add(atomAuthor);
    }

    /**
     * Gets the list of Atom contributors.
     *
     * @return the list of Atom contributors
     */
    public List<AtomContributor> getAtomContributors() {
        return emptyListIfNull(atomContributors);
    }

    /**
     * Adds an Atom contributor to the list.
     *
     * @param atomContributor the Atom contributor to add
     */
    public void addAtomContributor(AtomContributor atomContributor) {
        if (atomContributors == null) {
            atomContributors = new ArrayList<>();
        }
        atomContributors.add(atomContributor);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MetaData metaData = (MetaData) o;
        return Objects.equals(getAtomLinks(), metaData.getAtomLinks()) && Objects.equals(getAtomAuthors(), metaData.getAtomAuthors()) && Objects.equals(getAtomContributors(), metaData.getAtomContributors());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAtomLinks(), getAtomAuthors(), getAtomContributors());
    }
}
