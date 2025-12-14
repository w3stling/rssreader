package com.apptasticsoftware.rssreader.module.opensearch;

import java.util.List;
import java.util.Optional;

public interface OpenSearchChannelData {

    OpenSearchChannelData getOpenSearchChannelData();

    default Optional<Integer> getOpenSearchTotalResults() {
        return getOpenSearchChannelData().getOpenSearchTotalResults();
    }

    default void setOpenSearchTotalResults(Integer openSearchTotalResults) {
        getOpenSearchChannelData().setOpenSearchTotalResults(openSearchTotalResults);
    }

    default Optional<Integer> getOpenSearchStartIndex() {
        return getOpenSearchChannelData().getOpenSearchStartIndex();
    }

    default void setOpenSearchStartIndex(Integer openSearchStartIndex) {
        getOpenSearchChannelData().setOpenSearchStartIndex(openSearchStartIndex);
    }

    default Optional<Integer> getOpenSearchItemsPerPage() {
        return getOpenSearchChannelData().getOpenSearchItemsPerPage();
    }

    default void setOpenSearchItemsPerPage(Integer openSearchItemsPerPage) {
        getOpenSearchChannelData().setOpenSearchItemsPerPage(openSearchItemsPerPage);
    }

    default List<OpenSearchQuery> getOpenSearchQueries() {
        return getOpenSearchChannelData().getOpenSearchQueries();
    }

    default void addOpenSearchQuery(OpenSearchQuery openSearchQuery) {
        getOpenSearchChannelData().addOpenSearchQuery(openSearchQuery);
    }
}
