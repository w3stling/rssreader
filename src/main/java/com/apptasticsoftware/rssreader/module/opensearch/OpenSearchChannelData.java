package com.apptasticsoftware.rssreader.module.opensearch;

import com.apptasticsoftware.rssreader.module.opensearch.internal.OpenSearchChannelDataProvider;
import java.util.List;
import java.util.Optional;

public interface OpenSearchChannelData {
    default Optional<Integer> getOpenSearchTotalResults() {
        return ((OpenSearchChannelDataProvider) this).openSearchChannelData().getOpenSearchTotalResults();
    }

    default void setOpenSearchTotalResults(Integer openSearchTotalResults) {
        ((OpenSearchChannelDataProvider) this).openSearchChannelData().setOpenSearchTotalResults(openSearchTotalResults);
    }

    default Optional<Integer> getOpenSearchStartIndex() {
        return ((OpenSearchChannelDataProvider) this).openSearchChannelData().getOpenSearchStartIndex();
    }

    default void setOpenSearchStartIndex(Integer openSearchStartIndex) {
        ((OpenSearchChannelDataProvider) this).openSearchChannelData().setOpenSearchStartIndex(openSearchStartIndex);
    }

    default Optional<Integer> getOpenSearchItemsPerPage() {
        return ((OpenSearchChannelDataProvider) this).openSearchChannelData().getOpenSearchItemsPerPage();
    }

    default void setOpenSearchItemsPerPage(Integer openSearchItemsPerPage) {
        ((OpenSearchChannelDataProvider) this).openSearchChannelData().setOpenSearchItemsPerPage(openSearchItemsPerPage);
    }

    default List<OpenSearchQuery> getOpenSearchQueries() {
        return ((OpenSearchChannelDataProvider) this).openSearchChannelData().getOpenSearchQueries();
    }

    default void addOpenSearchQuery(OpenSearchQuery openSearchQuery) {
        ((OpenSearchChannelDataProvider) this).openSearchChannelData().addOpenSearchQuery(openSearchQuery);
    }
}
