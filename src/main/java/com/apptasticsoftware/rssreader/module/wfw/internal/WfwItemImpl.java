package com.apptasticsoftware.rssreader.module.wfw.internal;

import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.internal.ItemImpl;
import com.apptasticsoftware.rssreader.module.wfw.WfwItem;
import com.apptasticsoftware.rssreader.module.wfw.WfwItemData;

import java.util.Objects;

/**
 * Implementation of {@link WfwItem} for Well-Formed Web Comment API (WFW) support.
 * This class extends {@link ItemImpl} to provide a concrete implementation of the WfwItem interface,
 * enabling parsing and access to WFW comment-related properties in RSS feed items.
 *
 * <p>Each WfwItemImpl instance contains a {@link WfwItemDataImpl} instance that holds the WFW-specific
 * data for the item, such as comment RSS feed URLs and comment submission endpoints.</p>
 *
 * @see WfwItem
 * @see WfwItemData
 * @see ItemImpl
 */
public class WfwItemImpl extends ItemImpl implements WfwItem {
    private final WfwItemData wfwData = new WfwItemDataImpl();

    /**
     * Creates a new WFW-enabled item instance.
     *
     * @param dateTimeParser the parser for converting date/time strings in the item
     */
    public WfwItemImpl(DateTimeParser dateTimeParser) {
        super(dateTimeParser);
    }

    /**
     * Returns the WFW-specific data for this item.
     *
     * @return the WfwItemData instance containing WFW comment-related properties
     */
    @Override
    public WfwItemData getWfWItemData() {
        return wfwData;
    }

    /**
     * Compares this WfwItemImpl with another object for equality.
     * Two WfwItemImpl instances are considered equal if they have equal parent item data
     * and equal WFW-specific data.
     *
     * @param o the object to compare with
     * @return true if this object is equal to the other object, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof WfwItemImpl)) return false;
        if (!super.equals(o)) return false;
        WfwItemImpl wfwItem = (WfwItemImpl) o;
        return Objects.equals(getWfWItemData(), wfwItem.getWfWItemData());
    }

    /**
     * Returns the hash code for this WfwItemImpl.
     * The hash code is computed from both the parent item's hash code and the WFW data's hash code.
     *
     * @return the hash code for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getWfWItemData());
    }
}
