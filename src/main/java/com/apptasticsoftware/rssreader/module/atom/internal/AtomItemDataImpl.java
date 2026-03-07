package com.apptasticsoftware.rssreader.module.atom.internal;

import com.apptasticsoftware.rssreader.module.atom.AtomItemData;

/**
 * Internal implementation of {@link com.apptasticsoftware.rssreader.module.atom.AtomItemData}.
 */
public class AtomItemDataImpl extends MetaData implements AtomItemData, AtomItemDataProvider {

    /**
     * Returns the underlying Atom item data.
     *
     * @return this instance
     */
    public AtomItemData atomItemData() {
        return this;
    }
}
