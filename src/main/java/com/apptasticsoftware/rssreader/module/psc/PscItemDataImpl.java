package com.apptasticsoftware.rssreader.module.psc;

import java.util.Objects;
import java.util.Optional;

/**
 * Implementation of PscItemData interface.
 */
public class PscItemDataImpl implements PscItemData {
    private PscChapters pscChapters;

    @Override
    public PscItemData getPscItemData() {
        return this;
    }

    @Override
    public Optional<PscChapters> getPscChapters() {
        return Optional.ofNullable(pscChapters);
    }

    @Override
    public void setPscChapters(PscChapters pscChapters) {
        this.pscChapters = pscChapters;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PscItemDataImpl)) return false;
        PscItemDataImpl that = (PscItemDataImpl) o;
        return Objects.equals(getPscChapters(), that.getPscChapters());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getPscChapters());
    }
}
