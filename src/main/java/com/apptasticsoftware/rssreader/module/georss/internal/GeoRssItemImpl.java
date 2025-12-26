package com.apptasticsoftware.rssreader.module.georss.internal;

import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.internal.ItemImpl;
import com.apptasticsoftware.rssreader.module.georss.GeoRssItem;
import com.apptasticsoftware.rssreader.module.georss.GeoRssItemData;

import java.util.Objects;

public class GeoRssItemImpl extends ItemImpl implements GeoRssItem {
    private final GeoRssItemDataImpl geoRssData = new GeoRssItemDataImpl();

    /**
     * Constructor
     * @param dateTimeParser timestamp parser
     */
    public GeoRssItemImpl(DateTimeParser dateTimeParser) {
        super(dateTimeParser);
    }

    public GeoRssItemData getGeoRssItemData() {
        return geoRssData;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof GeoRssItemImpl)) return false;
        if (!super.equals(o)) return false;
        GeoRssItemImpl that = (GeoRssItemImpl) o;
        return Objects.equals(getGeoRssItemData(), that.getGeoRssItemData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getGeoRssItemData());
    }
}
