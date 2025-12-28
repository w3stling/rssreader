package com.apptasticsoftware.rssreader.module.slash;

import com.apptasticsoftware.rssreader.Channel;

/**
 * Slash RSS module channel interface extending the core Channel with Slash-specific metadata.
 * Slash is a module for RSS feeds from Slash-based sites (like Slashdot).
 *
 * @see SlashItem for item-level Slash extensions
 * @see <a href="http://purl.org/rss/1.0/modules/slash/">Slash RSS Module Specification</a>
 */
public interface SlashChannel extends Channel {
}
