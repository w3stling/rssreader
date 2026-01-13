package com.apptasticsoftware.rssreader.module.dc.internal;

import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.internal.ItemImpl;
import com.apptasticsoftware.rssreader.module.dc.DcItem;
import com.apptasticsoftware.rssreader.module.dc.DcItemData;

import java.util.Objects;

/**
 * Implementation of RSS item with Dublin Core metadata support.
 */
public class DcItemImpl extends ItemImpl implements DcItem {
    private final DcItemDataImpl dcData = new DcItemDataImpl();

    /**
     * Constructs a Dublin Core item with the specified date-time parser.
     * @param dateTimeParser date-time parser
     */
    public DcItemImpl(DateTimeParser dateTimeParser) {
        super(dateTimeParser);
    }

    @Override
    public DcItemData getDcItemData() {
        return dcData;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DcItemImpl)) return false;
        if (!super.equals(o)) return false;
        DcItemImpl dcItem = (DcItemImpl) o;
        return Objects.equals(getDcDate(), dcItem.getDcDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getDcDate());
    }
}
