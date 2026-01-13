package com.apptasticsoftware.rssreader.module.dc.internal;

import java.util.Objects;
import java.util.Optional;

/**
 * Base class for Dublin Core metadata elements.
 */
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

    /**
     * Gets the name given to the resource.
     * @return title
     */
    public Optional<String> getDcTitle() {
        return Optional.ofNullable(dcTitle);
    }

    /**
     * Sets the name given to the resource.
     * @param dcTitle title
     */
    public void setDcTitle(String dcTitle) {
        this.dcTitle = dcTitle;
    }

    /**
     * Gets the entity primarily responsible for making the resource.
     * @return creator
     */
    public Optional<String> getDcCreator() {
        return Optional.ofNullable(dcCreator);
    }

    /**
     * Sets the entity primarily responsible for making the resource.
     * @param dcCreator creator
     */
    public void setDcCreator(String dcCreator) {
        this.dcCreator = dcCreator;
    }

    /**
     * Gets the topic of the resource.
     * @return subject
     */
    public Optional<String> getDcSubject() {
        return Optional.ofNullable(dcSubject);
    }

    /**
     * Sets the topic of the resource.
     * @param dcSubject subject
     */
    public void setDcSubject(String dcSubject) {
        this.dcSubject = dcSubject;
    }

    /**
     * Gets the description or account of the resource.
     * @return description
     */
    public Optional<String> getDcDescription() {
        return Optional.ofNullable(dcDescription);
    }

    /**
     * Sets the description or account of the resource.
     * @param dcDescription description
     */
    public void setDcDescription(String dcDescription) {
        this.dcDescription = dcDescription;
    }

    /**
     * Gets the entity responsible for making the resource available.
     * @return publisher
     */
    public Optional<String> getDcPublisher() {
        return Optional.ofNullable(dcPublisher);
    }

    /**
     * Sets the entity responsible for making the resource available.
     * @param dcPublisher publisher
     */
    public void setDcPublisher(String dcPublisher) {
        this.dcPublisher = dcPublisher;
    }

    /**
     * Gets the entity responsible for making contributions to the resource.
     * @return contributor
     */
    public Optional<String> getDcContributor() {
        return Optional.ofNullable(dcContributor);
    }

    /**
     * Sets the entity responsible for making contributions to the resource.
     * @param dcContributor contributor
     */
    public void setDcContributor(String dcContributor) {
        this.dcContributor = dcContributor;
    }

    /**
     * Gets a point or period of time associated with the resource (W3CDTF format).
     * @return date
     */
    public Optional<String> getDcDate() {
        return Optional.ofNullable(dcDate);
    }

    /**
     * Sets a point or period of time associated with the resource (W3CDTF format).
     * @param dcDate date
     */
    public void setDcDate(String dcDate) {
        this.dcDate = dcDate;
    }

    /**
     * Gets the nature or genre of the resource.
     * @return type
     */
    public Optional<String> getDcType() {
        return Optional.ofNullable(dcType);
    }

    /**
     * Sets the nature or genre of the resource.
     * @param dcType type
     */
    public void setDcType(String dcType) {
        this.dcType = dcType;
    }

    /**
     * Gets the file format, physical medium, or dimensions of the resource.
     * @return format
     */
    public Optional<String> getDcFormat() {
        return Optional.ofNullable(dcFormat);
    }

    /**
     * Sets the file format, physical medium, or dimensions of the resource.
     * @param dcFormat format
     */
    public void setDcFormat(String dcFormat) {
        this.dcFormat = dcFormat;
    }

    /**
     * Gets an unambiguous reference to the resource.
     * @return identifier
     */
    public Optional<String> getDcIdentifier() {
        return Optional.ofNullable(dcIdentifier);
    }

    /**
     * Sets an unambiguous reference to the resource.
     * @param dcIdentifier identifier
     */
    public void setDcIdentifier(String dcIdentifier) {
        this.dcIdentifier = dcIdentifier;
    }

    /**
     * Gets a related resource from which the described resource is derived.
     * @return source
     */
    public Optional<String> getDcSource() {
        return Optional.ofNullable(dcSource);
    }

    /**
     * Sets a related resource from which the described resource is derived.
     * @param dcSource source
     */
    public void setDcSource(String dcSource) {
        this.dcSource = dcSource;
    }

    /**
     * Gets the language of the resource.
     * @return language
     */
    public Optional<String> getDcLanguage() {
        return Optional.ofNullable(dcLanguage);
    }

    /**
     * Sets the language of the resource.
     * @param dcLanguage language
     */
    public void setDcLanguage(String dcLanguage) {
        this.dcLanguage = dcLanguage;
    }

    /**
     * Gets a related resource.
     * @return relation
     */
    public Optional<String> getDcRelation() {
        return Optional.ofNullable(dcRelation);
    }

    /**
     * Sets a related resource.
     * @param dcRelation relation
     */
    public void setDcRelation(String dcRelation) {
        this.dcRelation = dcRelation;
    }

    /**
     * Gets the spatial or temporal topic, spatial applicability, or jurisdiction of the resource.
     * @return coverage
     */
    public Optional<String> getDcCoverage() {
        return Optional.ofNullable(dcCoverage);
    }

    /**
     * Sets the spatial or temporal topic, spatial applicability, or jurisdiction of the resource.
     * @param dcCoverage coverage
     */
    public void setDcCoverage(String dcCoverage) {
        this.dcCoverage = dcCoverage;
    }

    /**
     * Gets information about rights held in and over the resource.
     * @return rights
     */
    public Optional<String> getDcRights() {
        return Optional.ofNullable(dcRights);
    }

    /**
     * Sets information about rights held in and over the resource.
     * @param dcRights rights
     */
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
