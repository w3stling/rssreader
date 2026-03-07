package com.apptasticsoftware.rssreader.module.atom.internal;

import com.apptasticsoftware.rssreader.module.atom.AtomChannelData;

/**
 * Internal implementation of {@link com.apptasticsoftware.rssreader.module.atom.AtomChannelData}.
 */
public class AtomChannelDataImpl extends MetaData implements AtomChannelData, AtomChannelDataProvider {

    /**
     * Returns the underlying Atom channel data.
     *
     * @return this instance
     */
    public AtomChannelData atomChannelData() {
        return this;
    }
}
