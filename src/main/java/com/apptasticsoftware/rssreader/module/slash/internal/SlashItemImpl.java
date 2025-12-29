package com.apptasticsoftware.rssreader.module.slash.internal;

import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.internal.ItemImpl;
import com.apptasticsoftware.rssreader.module.slash.SlashItem;
import com.apptasticsoftware.rssreader.module.slash.SlashItemData;

import java.util.Objects;

/**
 * Implementation of SlashItem combining core item functionality with Slash-specific metadata.
 */
public class SlashItemImpl extends ItemImpl implements SlashItem {
    private final SlashItemData slashData = new SlashItemDataImpl();

    /**
     * Constructs a SlashItemImpl with the provided date-time parser.
     *
     * @param dateTimeParser the parser for parsing date-time values
     */
    public SlashItemImpl(DateTimeParser dateTimeParser) {
        super(dateTimeParser);
    }

    @Override
    public SlashItemData getSlashItemData() {
        return slashData;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof SlashItemImpl)) return false;
        if (!super.equals(o)) return false;
        SlashItemImpl slashItem = (SlashItemImpl) o;
        return Objects.equals(getSlashItemData(), slashItem.getSlashItemData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getSlashItemData());
    }
}
