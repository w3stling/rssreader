package com.apptasticsoftware.rssreader.module.dc;

import com.apptasticsoftware.rssreader.Channel;

/**
 * Channel interface with Dublin Core metadata extensions.
 * Combines standard RSS channel data with Dublin Core metadata elements.
 */
public interface DcChannel extends Channel, DcChannelData {
}
