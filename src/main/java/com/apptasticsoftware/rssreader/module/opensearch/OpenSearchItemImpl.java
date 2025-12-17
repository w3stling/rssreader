package com.apptasticsoftware.rssreader.module.opensearch;

import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.ItemImpl;

public class OpenSearchItemImpl extends ItemImpl implements OpenSearchItem {

    public OpenSearchItemImpl(DateTimeParser dateTimeParser) {
        super(dateTimeParser);
    }

}
