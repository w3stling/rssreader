package com.apptasticsoftware.rssreader.module.dc.internal;

import com.apptasticsoftware.rssreader.module.dc.DcChannelData;

/**
 * Implementation of Dublin Core metadata for RSS channels.
 */
public class DcChannelDataImpl extends MetaData implements DcChannelData {

    @Override
    public DcChannelData getDcChannelData() {
        return this;
    }
}
