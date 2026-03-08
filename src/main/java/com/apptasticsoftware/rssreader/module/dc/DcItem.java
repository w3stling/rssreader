package com.apptasticsoftware.rssreader.module.dc;

import com.apptasticsoftware.rssreader.Item;

/**
 * Item interface with Dublin Core metadata extensions.
 * Combines standard RSS item data with Dublin Core metadata elements.
 */
public interface DcItem extends Item, DcItemData {

    /**
     * Retrieves the Dublin Core channel (feed) that this item belongs to.
     *
     * @return the DcChannel associated with this item
     */
    @Override
    DcChannel getChannel();

}
