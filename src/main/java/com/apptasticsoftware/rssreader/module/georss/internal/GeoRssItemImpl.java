package com.apptasticsoftware.rssreader.module.georss.internal;

import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.internal.ItemImpl;
import com.apptasticsoftware.rssreader.module.georss.GeoRssItem;
import com.apptasticsoftware.rssreader.module.georss.GeoRssItemData;

import java.util.Objects;

/**
 * Implementation of GeoRssItem with geographic extension support.
 */
public class GeoRssItemImpl extends ItemImpl implements GeoRssItem {
    private final GeoRssItemDataImpl geoRssData = new GeoRssItemDataImpl();

    /**
     * Constructs a GeoRssItemImpl with the specified date time parser.
     *
     * @param dateTimeParser the date time parser
     */
    public GeoRssItemImpl(DateTimeParser dateTimeParser) {
        super(dateTimeParser);
    }

    /**
     * Returns the GeoRSS item data.
     *
     * @return the GeoRSS item data
     */
    @Override
    public GeoRssItemData getGeoRssItemData() {
        return geoRssData;
    }

    /**
     * Compares this item with another object for equality.
     *
     * @param o the object to compare with
     * @return true if both items are equal
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof GeoRssItemImpl)) return false;
        if (!super.equals(o)) return false;
        GeoRssItemImpl that = (GeoRssItemImpl) o;
        return Objects.equals(getGeoRssItemData(), that.getGeoRssItemData());
    }

    /**
     * Returns the hash code for this item.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getGeoRssItemData());
    }
}
