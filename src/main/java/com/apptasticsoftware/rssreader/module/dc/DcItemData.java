package com.apptasticsoftware.rssreader.module.dc;

import com.apptasticsoftware.rssreader.module.dc.internal.DcItemDataProvider;
import java.util.Optional;

/**
 * Dublin Core metadata interface for RSS items.
 * Provides access to DC metadata elements as defined in the Dublin Core specification.
 */
public interface DcItemData {

    /**
     * Gets the resource title.
     * @return the title
     */
    default Optional<String> getDcTitle() {
        return ((DcItemDataProvider) this).dcItemData().getDcTitle();
    }

    /**
     * Sets the resource title.
     * @param dcTitle the title
     */
    default void setDcTitle(String dcTitle) {
        ((DcItemDataProvider) this).dcItemData().setDcTitle(dcTitle);
    }

    /**
     * Gets the entity responsible for creating the resource.
     * @return the creator
     */
    default Optional<String> getDcCreator() {
        return ((DcItemDataProvider) this).dcItemData().getDcCreator();
    }

    /**
     * Sets the entity responsible for creating the resource.
     * @param dcCreator the creator
     */
    default void setDcCreator(String dcCreator) {
        ((DcItemDataProvider) this).dcItemData().setDcCreator(dcCreator);
    }

    /**
     * Gets the topic of the resource.
     * @return the subject
     */
    default Optional<String> getDcSubject() {
        return ((DcItemDataProvider) this).dcItemData().getDcSubject();
    }

    /**
     * Sets the topic of the resource.
     * @param dcSubject the subject
     */
    default void setDcSubject(String dcSubject) {
        ((DcItemDataProvider) this).dcItemData().setDcSubject(dcSubject);
    }

    /**
     * Gets the account of the resource content.
     * @return the description
     */
    default Optional<String> getDcDescription() {
        return ((DcItemDataProvider) this).dcItemData().getDcDescription();
    }

    /**
     * Sets the account of the resource content.
     * @param dcDescription the description
     */
    default void setDcDescription(String dcDescription) {
        ((DcItemDataProvider) this).dcItemData().setDcDescription(dcDescription);
    }

    /**
     * Gets the entity responsible for making the resource available.
     * @return the publisher
     */
    default Optional<String> getDcPublisher() {
        return ((DcItemDataProvider) this).dcItemData().getDcPublisher();
    }

    /**
     * Sets the entity responsible for making the resource available.
     * @param dcPublisher the publisher
     */
    default void setDcPublisher(String dcPublisher) {
        ((DcItemDataProvider) this).dcItemData().setDcPublisher(dcPublisher);
    }

    /**
     * Gets the entity responsible for contributing to the resource.
     * @return the contributor
     */
    default Optional<String> getDcContributor() {
        return ((DcItemDataProvider) this).dcItemData().getDcContributor();
    }

    /**
     * Sets the entity responsible for contributing to the resource.
     * @param dcContributor the contributor
     */
    default void setDcContributor(String dcContributor) {
        ((DcItemDataProvider) this).dcItemData().setDcContributor(dcContributor);
    }

    /**
     * Gets the point or period of time associated with the resource.
     * @return the date
     */
    default Optional<String> getDcDate() {
        return ((DcItemDataProvider) this).dcItemData().getDcDate();
    }

    /**
     * Sets the point or period of time associated with the resource.
     * @param dcDate the date
     */
    default void setDcDate(String dcDate) {
        ((DcItemDataProvider) this).dcItemData().setDcDate(dcDate);
    }

    /**
     * Gets the nature or genre of the resource.
     * @return the type
     */
    default Optional<String> getDcType() {
        return ((DcItemDataProvider) this).dcItemData().getDcType();
    }

    /**
     * Sets the nature or genre of the resource.
     * @param dcType the type
     */
    default void setDcType(String dcType) {
        ((DcItemDataProvider) this).dcItemData().setDcType(dcType);
    }

    /**
     * Gets the file format or physical medium of the resource.
     * @return the format
     */
    default Optional<String> getDcFormat() {
        return ((DcItemDataProvider) this).dcItemData().getDcFormat();
    }

    /**
     * Sets the file format or physical medium of the resource.
     * @param dcFormat the format
     */
    default void setDcFormat(String dcFormat) {
        ((DcItemDataProvider) this).dcItemData().setDcFormat(dcFormat);
    }

    /**
     * Gets an unambiguous reference to the resource.
     * @return the identifier
     */
    default Optional<String> getDcIdentifier() {
        return ((DcItemDataProvider) this).dcItemData().getDcIdentifier();
    }

    /**
     * Sets an unambiguous reference to the resource.
     * @param dcIdentifier the identifier
     */
    default void setDcIdentifier(String dcIdentifier) {
        ((DcItemDataProvider) this).dcItemData().setDcIdentifier(dcIdentifier);
    }

    /**
     * Gets a related resource from which the described resource is derived.
     * @return the source
     */
    default Optional<String> getDcSource() {
        return ((DcItemDataProvider) this).dcItemData().getDcSource();
    }

    /**
     * Sets a related resource from which the described resource is derived.
     * @param dcSource the source
     */
    default void setDcSource(String dcSource) {
        ((DcItemDataProvider) this).dcItemData().setDcSource(dcSource);
    }

    /**
     * Gets the language of the resource.
     * @return the language
     */
    default Optional<String> getDcLanguage() {
        return ((DcItemDataProvider) this).dcItemData().getDcLanguage();
    }

    /**
     * Sets the language of the resource.
     * @param dcLanguage the language
     */
    default void setDcLanguage(String dcLanguage) {
        ((DcItemDataProvider) this).dcItemData().setDcLanguage(dcLanguage);
    }

    /**
     * Gets a related resource.
     * @return the relation
     */
    default Optional<String> getDcRelation() {
        return ((DcItemDataProvider) this).dcItemData().getDcRelation();
    }

    /**
     * Sets a related resource.
     * @param dcRelation the relation
     */
    default void setDcRelation(String dcRelation) {
        ((DcItemDataProvider) this).dcItemData().setDcRelation(dcRelation);
    }

    /**
     * Gets the spatial or temporal topic of the resource.
     * @return the coverage
     */
    default Optional<String> getDcCoverage() {
        return ((DcItemDataProvider) this).dcItemData().getDcCoverage();
    }

    /**
     * Sets the spatial or temporal topic of the resource.
     * @param dcCoverage the coverage
     */
    default void setDcCoverage(String dcCoverage) {
        ((DcItemDataProvider) this).dcItemData().setDcCoverage(dcCoverage);
    }

    /**
     * Gets information about rights held in and over the resource.
     * @return the rights
     */
    default Optional<String> getDcRights() {
        return ((DcItemDataProvider) this).dcItemData().getDcRights();
    }

    /**
     * Sets information about rights held in and over the resource.
     * @param dcRights the rights
     */
    default void setDcRights(String dcRights) {
        ((DcItemDataProvider) this).dcItemData().setDcRights(dcRights);
    }
}
