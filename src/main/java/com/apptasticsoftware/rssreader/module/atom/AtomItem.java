package com.apptasticsoftware.rssreader.module.atom;

import com.apptasticsoftware.rssreader.Item;

/**
 * Represents an Atom feed entry, combining standard RSS item fields with Atom-specific data.
 */
public interface AtomItem extends Item, AtomItemData {

    /**
     * Retrieves the Atom channel (feed) that this item belongs to.
     *
     * @return the AtomChannel associated with this item
     */
    @Override
    AtomChannel getChannel();

}
