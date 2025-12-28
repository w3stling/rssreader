package com.apptasticsoftware.rssreader.module.slash;

import com.apptasticsoftware.rssreader.Item;

/**
 * Slash RSS module item interface extending the core Item with Slash-specific metadata.
 * Provides access to item-level Slash elements including section, department, comments count, and hit parade.
 *
 * @see SlashItemData for the data accessor methods
 * @see <a href="http://purl.org/rss/1.0/modules/slash/">Slash RSS Module Specification</a>
 */
public interface SlashItem extends Item, SlashItemData {
}
