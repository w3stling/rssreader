package com.apptasticsoftware.rssreader.module.psc;

import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.internal.ItemImpl;

import java.util.Objects;

public class PscItemImpl extends ItemImpl implements PscItem {
    private final PscItemData pscData = new PscItemDataImpl();

    public PscItemImpl(DateTimeParser dateTimeParser) {
        super(dateTimeParser);
    }

    @Override
    public PscItemData getPscItemData() {
        return pscData;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PscItemImpl)) return false;
        if (!super.equals(o)) return false;
        PscItemImpl pscItem = (PscItemImpl) o;
        return Objects.equals(pscData, pscItem.pscData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pscData);
    }
}
