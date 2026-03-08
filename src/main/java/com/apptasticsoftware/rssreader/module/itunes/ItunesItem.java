package com.apptasticsoftware.rssreader.module.itunes;

import com.apptasticsoftware.rssreader.Item;

public interface ItunesItem extends Item, ItunesItemData {

    /**
     * Retrieves the iTunes channel (feed) that this item belongs to.
     *
     * @return the ItunesChannel associated with this item
     */
    @Override
    ItunesChannel getChannel();

}
