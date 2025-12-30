package com.apptasticsoftware.rssreader.module.dc.internal;

import java.util.Objects;
import java.util.Optional;

public class MetaData {
    private String dcTitle;
    private String dcCreator;
    private String dcSubject;
    private String dcDescription;
    private String dcPublisher;
    private String dcContributor;
    private String dcDate;
    private String dcType;
    private String dcFormat;
    private String dcIdentifier;
    private String dcSource;
    private String dcLanguage;
    private String dcRelation;
    private String dcCoverage;
    private String dcRights;

    public Optional<String> getDcTitle() {
        return Optional.ofNullable(dcTitle);
    }

    public void setDcTitle(String dcTitle) {
        this.dcTitle = dcTitle;
    }

    public Optional<String> getDcCreator() {
        return Optional.ofNullable(dcCreator);
    }

    public void setDcCreator(String dcCreator) {
        this.dcCreator = dcCreator;
    }

    public Optional<String> getDcSubject() {
        return Optional.ofNullable(dcSubject);
    }

    public void setDcSubject(String dcSubject) {
        this.dcSubject = dcSubject;
    }

    public Optional<String> getDcDescription() {
        return Optional.ofNullable(dcDescription);
    }

    public void setDcDescription(String dcDescription) {
        this.dcDescription = dcDescription;
    }

    public Optional<String> getDcPublisher() {
        return Optional.ofNullable(dcPublisher);
    }

    public void setDcPublisher(String dcPublisher) {
        this.dcPublisher = dcPublisher;
    }

    public Optional<String> getDcContributor() {
        return Optional.ofNullable(dcContributor);
    }

    public void setDcContributor(String dcContributor) {
        this.dcContributor = dcContributor;
    }

    public Optional<String> getDcDate() {
        return Optional.ofNullable(dcDate);
    }

    public void setDcDate(String dcDate) {
        this.dcDate = dcDate;
    }

    public Optional<String> getDcType() {
        return Optional.ofNullable(dcType);
    }

    public void setDcType(String dcType) {
        this.dcType = dcType;
    }

    public Optional<String> getDcFormat() {
        return Optional.ofNullable(dcFormat);
    }

    public void setDcFormat(String dcFormat) {
        this.dcFormat = dcFormat;
    }

    public Optional<String> getDcIdentifier() {
        return Optional.ofNullable(dcIdentifier);
    }

    public void setDcIdentifier(String dcIdentifier) {
        this.dcIdentifier = dcIdentifier;
    }

    public Optional<String> getDcSource() {
        return Optional.ofNullable(dcSource);
    }

    public void setDcSource(String dcSource) {
        this.dcSource = dcSource;
    }

    public Optional<String> getDcLanguage() {
        return Optional.ofNullable(dcLanguage);
    }

    public void setDcLanguage(String dcLanguage) {
        this.dcLanguage = dcLanguage;
    }

    public Optional<String> getDcRelation() {
        return Optional.ofNullable(dcRelation);
    }

    public void setDcRelation(String dcRelation) {
        this.dcRelation = dcRelation;
    }

    public Optional<String> getDcCoverage() {
        return Optional.ofNullable(dcCoverage);
    }

    public void setDcCoverage(String dcCoverage) {
        this.dcCoverage = dcCoverage;
    }

    public Optional<String> getDcRights() {
        return Optional.ofNullable(dcRights);
    }

    public void setDcRights(String dcRights) {
        this.dcRights = dcRights;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MetaData metaData = (MetaData) o;
        return Objects.equals(getDcTitle(), metaData.getDcTitle()) && Objects.equals(getDcCreator(), metaData.getDcCreator()) && Objects.equals(getDcSubject(), metaData.getDcSubject()) && Objects.equals(getDcDescription(), metaData.getDcDescription()) && Objects.equals(getDcPublisher(), metaData.getDcPublisher()) && Objects.equals(getDcContributor(), metaData.getDcContributor()) && Objects.equals(getDcDate(), metaData.getDcDate()) && Objects.equals(getDcType(), metaData.getDcType()) && Objects.equals(getDcFormat(), metaData.getDcFormat()) && Objects.equals(getDcIdentifier(), metaData.getDcIdentifier()) && Objects.equals(getDcSource(), metaData.getDcSource()) && Objects.equals(getDcLanguage(), metaData.getDcLanguage()) && Objects.equals(getDcRelation(), metaData.getDcRelation()) && Objects.equals(getDcCoverage(), metaData.getDcCoverage()) && Objects.equals(getDcRights(), metaData.getDcRights());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDcTitle(), getDcCreator(), getDcSubject(), getDcDescription(), getDcPublisher(), getDcContributor(), getDcDate(), getDcType(), getDcFormat(), getDcIdentifier(), getDcSource(), getDcLanguage(), getDcRelation(), getDcCoverage(), getDcRights());
    }
}
