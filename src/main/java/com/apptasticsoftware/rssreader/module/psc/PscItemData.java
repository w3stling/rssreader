package com.apptasticsoftware.rssreader.module.psc;

import com.apptasticsoftware.rssreader.module.psc.internal.PscItemDataProvider;
import java.util.Optional;

public interface PscItemData {
    default Optional<PscChapters> getPscChapters() {
        return ((PscItemDataProvider) this).pscItemData().getPscChapters();
    }

    default void setPscChapters(PscChapters pscChapters) {
        ((PscItemDataProvider) this).pscItemData().setPscChapters(pscChapters);
    }
}
