package com.apptasticsoftware.rssreader.module.dc.internal;

import com.apptasticsoftware.rssreader.module.dc.DcItemData;

/**
 * Implementation of Dublin Core metadata for RSS items.
 */
public class DcItemDataImpl extends MetaData implements DcItemData {

    @Override
    public DcItemData getDcItemData() {
        return this;
    }
}
