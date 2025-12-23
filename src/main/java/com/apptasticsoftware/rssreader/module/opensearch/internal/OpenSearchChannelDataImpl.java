package com.apptasticsoftware.rssreader.module.opensearch.internal;

import com.apptasticsoftware.rssreader.module.opensearch.OpenSearchChannelData;
import com.apptasticsoftware.rssreader.module.opensearch.OpenSearchQuery;

import java.util.*;

import static com.apptasticsoftware.rssreader.util.Mapper.emptyListIfNull;

public class OpenSearchChannelDataImpl implements OpenSearchChannelData {
    private Integer openSearchTotalResults;
    private Integer openSearchStartIndex;
    private Integer openSearchItemsPerPage;
    private List<OpenSearchQuery> openSearchQueries;

    @Override
    public OpenSearchChannelData getOpenSearchChannelData() {
        return this;
    }

    @Override
    public Optional<Integer> getOpenSearchTotalResults() {
        return Optional.ofNullable(openSearchTotalResults);
    }

    @Override
    public void setOpenSearchTotalResults(Integer openSearchTotalResults) {
        this.openSearchTotalResults = openSearchTotalResults;
    }

    @Override
    public Optional<Integer> getOpenSearchStartIndex() {
        return Optional.ofNullable(openSearchStartIndex);
    }

    @Override
    public void setOpenSearchStartIndex(Integer openSearchStartIndex) {
        this.openSearchStartIndex = openSearchStartIndex;
    }

    @Override
    public Optional<Integer> getOpenSearchItemsPerPage() {
        return Optional.ofNullable(openSearchItemsPerPage);
    }

    @Override
    public void setOpenSearchItemsPerPage(Integer openSearchItemsPerPage) {
        this.openSearchItemsPerPage = openSearchItemsPerPage;
    }

    @Override
    public List<OpenSearchQuery> getOpenSearchQueries() {
        return emptyListIfNull(openSearchQueries);
    }

    @Override
    public void addOpenSearchQuery(OpenSearchQuery openSearchQuery) {
        if (openSearchQueries == null) {
            openSearchQueries = new ArrayList<>();
        }
        this.openSearchQueries.add(openSearchQuery);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OpenSearchChannelDataImpl that = (OpenSearchChannelDataImpl) o;
        return Objects.equals(getOpenSearchTotalResults(), that.getOpenSearchTotalResults()) && Objects.equals(getOpenSearchStartIndex(), that.getOpenSearchStartIndex()) && Objects.equals(getOpenSearchItemsPerPage(), that.getOpenSearchItemsPerPage()) && Objects.equals(getOpenSearchQueries(), that.getOpenSearchQueries());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOpenSearchTotalResults(), getOpenSearchStartIndex(), getOpenSearchItemsPerPage(), getOpenSearchQueries());
    }
}
