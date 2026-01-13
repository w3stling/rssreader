package com.apptasticsoftware.rssreader.module.dc;

import java.util.Optional;

/**
 * Dublin Core metadata interface for RSS items.
 * Provides access to DC metadata elements as defined in the Dublin Core specification.
 */
public interface DcItemData {

    /**
     * Gets the Dublin Core item data.
     * @return the DC item data
     */
    DcItemData getDcItemData();

    /**
     * Gets the resource title.
     * @return the title
     */
    default Optional<String> getDcTitle() {
        return getDcItemData().getDcTitle();
    }

    /**
     * Sets the resource title.
     * @param dcTitle the title
     */
    default void setDcTitle(String dcTitle) {
        getDcItemData().setDcTitle(dcTitle);
    }

    /**
     * Gets the entity responsible for creating the resource.
     * @return the creator
     */
    default Optional<String> getDcCreator() {
        return getDcItemData().getDcCreator();
    }

    /**
     * Sets the entity responsible for creating the resource.
     * @param dcCreator the creator
     */
    default void setDcCreator(String dcCreator) {
        getDcItemData().setDcCreator(dcCreator);
    }

    /**
     * Gets the topic of the resource.
     * @return the subject
     */
    default Optional<String> getDcSubject() {
        return getDcItemData().getDcSubject();
    }

    /**
     * Sets the topic of the resource.
     * @param dcSubject the subject
     */
    default void setDcSubject(String dcSubject) {
        getDcItemData().setDcSubject(dcSubject);
    }

    /**
     * Gets the account of the resource content.
     * @return the description
     */
    default Optional<String> getDcDescription() {
        return getDcItemData().getDcDescription();
    }

    /**
     * Sets the account of the resource content.
     * @param dcDescription the description
     */
    default void setDcDescription(String dcDescription) {
        getDcItemData().setDcDescription(dcDescription);
    }

    /**
     * Gets the entity responsible for making the resource available.
     * @return the publisher
     */
    default Optional<String> getDcPublisher() {
        return getDcItemData().getDcPublisher();
    }

    /**
     * Sets the entity responsible for making the resource available.
     * @param dcPublisher the publisher
     */
    default void setDcPublisher(String dcPublisher) {
        getDcItemData().setDcPublisher(dcPublisher);
    }

    /**
     * Gets the entity responsible for contributing to the resource.
     * @return the contributor
     */
    default Optional<String> getDcContributor() {
        return getDcItemData().getDcContributor();
    }

    /**
     * Sets the entity responsible for contributing to the resource.
     * @param dcContributor the contributor
     */
    default void setDcContributor(String dcContributor) {
        getDcItemData().setDcContributor(dcContributor);
    }

    /**
     * Gets the point or period of time associated with the resource.
     * @return the date
     */
    default Optional<String> getDcDate() {
        return getDcItemData().getDcDate();
    }

    /**
     * Sets the point or period of time associated with the resource.
     * @param dcDate the date
     */
    default void setDcDate(String dcDate) {
        getDcItemData().setDcDate(dcDate);
    }

    /**
     * Gets the nature or genre of the resource.
     * @return the type
     */
    default Optional<String> getDcType() {
        return getDcItemData().getDcType();
    }

    /**
     * Sets the nature or genre of the resource.
     * @param dcType the type
     */
    default void setDcType(String dcType) {
        getDcItemData().setDcType(dcType);
    }

    /**
     * Gets the file format or physical medium of the resource.
     * @return the format
     */
    default Optional<String> getDcFormat() {
        return getDcItemData().getDcFormat();
    }

    /**
     * Sets the file format or physical medium of the resource.
     * @param dcFormat the format
     */
    default void setDcFormat(String dcFormat) {
        getDcItemData().setDcFormat(dcFormat);
    }

    /**
     * Gets an unambiguous reference to the resource.
     * @return the identifier
     */
    default Optional<String> getDcIdentifier() {
        return getDcItemData().getDcIdentifier();
    }

    /**
     * Sets an unambiguous reference to the resource.
     * @param dcIdentifier the identifier
     */
    default void setDcIdentifier(String dcIdentifier) {
        getDcItemData().setDcIdentifier(dcIdentifier);
    }

    /**
     * Gets a related resource from which the described resource is derived.
     * @return the source
     */
    default Optional<String> getDcSource() {
        return getDcItemData().getDcSource();
    }

    /**
     * Sets a related resource from which the described resource is derived.
     * @param dcSource the source
     */
    default void setDcSource(String dcSource) {
        getDcItemData().setDcSource(dcSource);
    }

    /**
     * Gets the language of the resource.
     * @return the language
     */
    default Optional<String> getDcLanguage() {
        return getDcItemData().getDcLanguage();
    }

    /**
     * Sets the language of the resource.
     * @param dcLanguage the language
     */
    default void setDcLanguage(String dcLanguage) {
        getDcItemData().setDcLanguage(dcLanguage);
    }

    /**
     * Gets a related resource.
     * @return the relation
     */
    default Optional<String> getDcRelation() {
        return getDcItemData().getDcRelation();
    }

    /**
     * Sets a related resource.
     * @param dcRelation the relation
     */
    default void setDcRelation(String dcRelation) {
        getDcItemData().setDcRelation(dcRelation);
    }

    /**
     * Gets the spatial or temporal topic of the resource.
     * @return the coverage
     */
    default Optional<String> getDcCoverage() {
        return getDcItemData().getDcCoverage();
    }

    /**
     * Sets the spatial or temporal topic of the resource.
     * @param dcCoverage the coverage
     */
    default void setDcCoverage(String dcCoverage) {
        getDcItemData().setDcCoverage(dcCoverage);
    }

    /**
     * Gets information about rights held in and over the resource.
     * @return the rights
     */
    default Optional<String> getDcRights() {
        return getDcItemData().getDcRights();
    }

    /**
     * Sets information about rights held in and over the resource.
     * @param dcRights the rights
     */
    default void setDcRights(String dcRights) {
        getDcItemData().setDcRights(dcRights);
    }
}
