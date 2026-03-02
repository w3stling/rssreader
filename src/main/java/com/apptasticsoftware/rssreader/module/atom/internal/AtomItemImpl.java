package com.apptasticsoftware.rssreader.module.atom.internal;

import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.internal.ItemImpl;
import com.apptasticsoftware.rssreader.module.atom.AtomItem;
import com.apptasticsoftware.rssreader.module.atom.AtomItemData;

import java.util.Objects;

/**
 * Internal implementation of {@link com.apptasticsoftware.rssreader.module.atom.AtomItem}.
 */
public class AtomItemImpl extends ItemImpl implements AtomItem {
    private final AtomItemDataImpl atomData = new AtomItemDataImpl();

    /**
     * Creates an {@code AtomItemImpl} with the given date-time parser.
     *
     * @param dateTimeParser the parser used to parse date-time values
     */
    public AtomItemImpl(DateTimeParser dateTimeParser) {
        super(dateTimeParser);
    }

    @Override
    public AtomItemData getAtomItemData() {
        return atomData;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof AtomItemImpl)) return false;
        if (!super.equals(o)) return false;
        AtomItemImpl that = (AtomItemImpl) o;
        return Objects.equals(getAtomItemData(), that.getAtomItemData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getAtomItemData());
    }
}
