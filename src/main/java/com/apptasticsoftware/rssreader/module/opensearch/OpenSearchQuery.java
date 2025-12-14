package com.apptasticsoftware.rssreader.module.opensearch;

import java.util.Objects;
import java.util.Optional;

public class OpenSearchQuery {
    private String role;
    private String title;
    private Integer totalResults;
    private String searchTerms;
    private Integer count;
    private Integer startIndex;
    private Integer startPage;
    private String language;
    private String inputEncoding;
    private String outputEncoding;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Optional<String> getTitle() {
        return Optional.ofNullable(title);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Optional<Integer> getTotalResults() {
        return Optional.ofNullable(totalResults);
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Optional<String> getSearchTerms() {
        return Optional.ofNullable(searchTerms);
    }

    public void setSearchTerms(String searchTerms) {
        this.searchTerms = searchTerms;
    }

    public Optional<Integer> getCount() {
        return Optional.ofNullable(count);
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Optional<Integer> getStartIndex() {
        return Optional.ofNullable(startIndex);
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Optional<Integer> getStartPage() {
        return Optional.ofNullable(startPage);
    }

    public void setStartPage(Integer startPage) {
        this.startPage = startPage;
    }

    public Optional<String> getLanguage() {
        return Optional.ofNullable(language);
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Optional<String> getInputEncoding() {
        return Optional.ofNullable(inputEncoding);
    }

    public void setInputEncoding(String inputEncoding) {
        this.inputEncoding = inputEncoding;
    }

    public Optional<String> getOutputEncoding() {
        return Optional.ofNullable(outputEncoding);
    }

    public void setOutputEncoding(String outputEncoding) {
        this.outputEncoding = outputEncoding;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OpenSearchQuery that = (OpenSearchQuery) o;
        return Objects.equals(getRole(), that.getRole()) && Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getTotalResults(), that.getTotalResults()) && Objects.equals(getSearchTerms(), that.getSearchTerms()) && Objects.equals(getCount(), that.getCount()) && Objects.equals(getStartIndex(), that.getStartIndex()) && Objects.equals(getStartPage(), that.getStartPage()) && Objects.equals(getLanguage(), that.getLanguage()) && Objects.equals(getInputEncoding(), that.getInputEncoding()) && Objects.equals(getOutputEncoding(), that.getOutputEncoding());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRole(), getTitle(), getTotalResults(), getSearchTerms(), getCount(), getStartIndex(), getStartPage(), getLanguage(), getInputEncoding(), getOutputEncoding());
    }
}
