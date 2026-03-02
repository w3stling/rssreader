package com.apptasticsoftware.rssreader.module.atom;

import com.apptasticsoftware.rssreader.Item;

/**
 * Represents an Atom feed entry, combining standard RSS item fields with Atom-specific data.
 */
public interface AtomItem extends Item, AtomItemData {

    /**
     * Returns the Atom item data.
     *
     * @return the Atom item data
     */
    AtomItemData getAtomItemData();
}
