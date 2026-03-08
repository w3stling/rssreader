package com.apptasticsoftware.rssreader.module.opensearch.internal;

import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.internal.ItemImpl;
import com.apptasticsoftware.rssreader.module.opensearch.OpenSearchChannel;
import com.apptasticsoftware.rssreader.module.opensearch.OpenSearchItem;

public class OpenSearchItemImpl extends ItemImpl implements OpenSearchItem {

    public OpenSearchItemImpl(DateTimeParser dateTimeParser) {
        super(dateTimeParser);
    }

    @Override
    public OpenSearchChannel getChannel() {
        var channel = super.getChannel();
        if (channel instanceof OpenSearchChannel) {
            return (OpenSearchChannel) super.getChannel();
        }
        return null;
    }
}
