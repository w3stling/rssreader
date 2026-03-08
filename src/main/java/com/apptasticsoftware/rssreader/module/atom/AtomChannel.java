package com.apptasticsoftware.rssreader.module.atom;

import com.apptasticsoftware.rssreader.Channel;

/**
 * Represents an Atom feed channel, combining standard RSS channel fields with Atom-specific data.
 */
public interface AtomChannel extends Channel, AtomChannelData {
}
