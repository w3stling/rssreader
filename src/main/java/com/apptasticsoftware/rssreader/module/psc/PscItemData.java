package com.apptasticsoftware.rssreader.module.psc;

import java.util.Optional;

public interface PscItemData {

    PscItemData getPscItemData();

    default Optional<PscChapters> getPscChapters() {
        return getPscItemData().getPscChapters();
    }

    default void setPscChapters(PscChapters pscChapters) {
        getPscItemData().setPscChapters(pscChapters);
    }
}
