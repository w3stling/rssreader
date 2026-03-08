package com.apptasticsoftware.rssreader.module.opensearch;

import com.apptasticsoftware.rssreader.Item;

public interface OpenSearchItem extends Item, OpenSearchItemData {

    /**
     * Retrieves the OpenSearch channel (feed) that this item belongs to.
     *
     * @return the OpenSearchChannel associated with this item
     */
    @Override
    OpenSearchChannel getChannel();
}
