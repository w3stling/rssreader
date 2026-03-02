package com.apptasticsoftware.rssreader.module.atom;

import com.apptasticsoftware.rssreader.Channel;

/**
 * Represents an Atom feed channel, combining standard RSS channel fields with Atom-specific data.
 */
public interface AtomChannel extends Channel, AtomChannelData {

    /**
     * Returns the Atom channel data.
     *
     * @return the Atom channel data
     */
    AtomChannelData getAtomChannelData();
}
