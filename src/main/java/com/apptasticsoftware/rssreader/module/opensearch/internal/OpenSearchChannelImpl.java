package com.apptasticsoftware.rssreader.module.opensearch.internal;

import com.apptasticsoftware.rssreader.internal.ChannelImpl;
import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.module.opensearch.OpenSearchChannel;
import com.apptasticsoftware.rssreader.module.opensearch.OpenSearchChannelData;

import java.util.Objects;

public class OpenSearchChannelImpl extends ChannelImpl implements OpenSearchChannel {
    private final OpenSearchChannelData openSearchData = new OpenSearchChannelDataImpl();

    public OpenSearchChannelImpl(DateTimeParser dateTimeParser) {
        super(dateTimeParser);
    }

    @Override
    public OpenSearchChannelData getOpenSearchChannelData() {
        return openSearchData;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OpenSearchChannelImpl that = (OpenSearchChannelImpl) o;
        return Objects.equals(getOpenSearchChannelData(), that.getOpenSearchChannelData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getOpenSearchChannelData());
    }
}
