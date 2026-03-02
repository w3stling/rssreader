package com.apptasticsoftware.rssreader.module.atom.internal;

import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.internal.ChannelImpl;
import com.apptasticsoftware.rssreader.module.atom.AtomChannel;
import com.apptasticsoftware.rssreader.module.atom.AtomChannelData;

import java.util.Objects;

/**
 * Internal implementation of {@link com.apptasticsoftware.rssreader.module.atom.AtomChannel}.
 */
public class AtomChannelImpl extends ChannelImpl implements AtomChannel {
    private final AtomChannelDataImpl atomData = new AtomChannelDataImpl();

    /**
     * Creates an {@code AtomChannelImpl} with the given date-time parser.
     *
     * @param dateTimeParser the parser used to parse date-time values
     */
    public AtomChannelImpl(DateTimeParser dateTimeParser) {
        super(dateTimeParser);
    }

    @Override
    public AtomChannelData getAtomChannelData() {
        return atomData;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof AtomChannelImpl)) return false;
        if (!super.equals(o)) return false;
        AtomChannelImpl that = (AtomChannelImpl) o;
        return Objects.equals(getAtomChannelData(), that.getAtomChannelData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getAtomChannelData());
    }
}
