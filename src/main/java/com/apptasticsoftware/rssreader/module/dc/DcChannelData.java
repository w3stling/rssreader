package com.apptasticsoftware.rssreader.module.dc;

import java.util.Optional;

/**
 * Dublin Core metadata interface for RSS channels.
 * Provides access to DC metadata elements as defined in the Dublin Core specification.
 */
public interface DcChannelData {

    /**
     * Gets the Dublin Core channel data.
     * @return the DC channel data
     */
    DcChannelData getDcChannelData();

    /**
     * Gets the resource title.
     * @return the title
     */
    default Optional<String> getDcTitle() {
        return getDcChannelData().getDcTitle();
    }

    /**
     * Sets the resource title.
     * @param dcTitle the title
     */
    default void setDcTitle(String dcTitle) {
        getDcChannelData().setDcTitle(dcTitle);
    }

    /**
     * Gets the entity responsible for creating the resource.
     * @return the creator
     */
    default Optional<String> getDcCreator() {
        return getDcChannelData().getDcCreator();
    }

    /**
     * Sets the entity responsible for creating the resource.
     * @param dcCreator the creator
     */
    default void setDcCreator(String dcCreator) {
        getDcChannelData().setDcCreator(dcCreator);
    }

    /**
     * Gets the topic of the resource.
     * @return the subject
     */
    default Optional<String> getDcSubject() {
        return getDcChannelData().getDcSubject();
    }

    /**
     * Sets the topic of the resource.
     * @param dcSubject the subject
     */
    default void setDcSubject(String dcSubject) {
        getDcChannelData().setDcSubject(dcSubject);
    }

    /**
     * Gets the account of the resource content.
     * @return the description
     */
    default Optional<String> getDcDescription() {
        return getDcChannelData().getDcDescription();
    }

    /**
     * Sets the account of the resource content.
     * @param dcDescription the description
     */
    default void setDcDescription(String dcDescription) {
        getDcChannelData().setDcDescription(dcDescription);
    }

    /**
     * Gets the entity responsible for making the resource available.
     * @return the publisher
     */
    default Optional<String> getDcPublisher() {
        return getDcChannelData().getDcPublisher();
    }

    /**
     * Sets the entity responsible for making the resource available.
     * @param dcPublisher the publisher
     */
    default void setDcPublisher(String dcPublisher) {
        getDcChannelData().setDcPublisher(dcPublisher);
    }

    /**
     * Gets the entity responsible for contributing to the resource.
     * @return the contributor
     */
    default Optional<String> getDcContributor() {
        return getDcChannelData().getDcContributor();
    }

    /**
     * Sets the entity responsible for contributing to the resource.
     * @param dcContributor the contributor
     */
    default void setDcContributor(String dcContributor) {
        getDcChannelData().setDcContributor(dcContributor);
    }

    /**
     * Gets the point or period of time associated with the resource.
     * @return the date
     */
    default Optional<String> getDcDate() {
        return getDcChannelData().getDcDate();
    }

    /**
     * Sets the point or period of time associated with the resource.
     * @param dcDate the date
     */
    default void setDcDate(String dcDate) {
        getDcChannelData().setDcDate(dcDate);
    }

    /**
     * Gets the nature or genre of the resource.
     * @return the type
     */
    default Optional<String> getDcType() {
        return getDcChannelData().getDcType();
    }

    /**
     * Sets the nature or genre of the resource.
     * @param dcType the type
     */
    default void setDcType(String dcType) {
        getDcChannelData().setDcType(dcType);
    }

    /**
     * Gets the file format or physical medium of the resource.
     * @return the format
     */
    default Optional<String> getDcFormat() {
        return getDcChannelData().getDcFormat();
    }

    /**
     * Sets the file format or physical medium of the resource.
     * @param dcFormat the format
     */
    default void setDcFormat(String dcFormat) {
        getDcChannelData().setDcFormat(dcFormat);
    }

    /**
     * Gets an unambiguous reference to the resource.
     * @return the identifier
     */
    default Optional<String> getDcIdentifier() {
        return getDcChannelData().getDcIdentifier();
    }

    /**
     * Sets an unambiguous reference to the resource.
     * @param dcIdentifier the identifier
     */
    default void setDcIdentifier(String dcIdentifier) {
        getDcChannelData().setDcIdentifier(dcIdentifier);
    }

    /**
     * Gets a related resource from which the described resource is derived.
     * @return the source
     */
    default Optional<String> getDcSource() {
        return getDcChannelData().getDcSource();
    }

    /**
     * Sets a related resource from which the described resource is derived.
     * @param dcSource the source
     */
    default void setDcSource(String dcSource) {
        getDcChannelData().setDcSource(dcSource);
    }

    /**
     * Gets the language of the resource.
     * @return the language
     */
    default Optional<String> getDcLanguage() {
        return getDcChannelData().getDcLanguage();
    }

    /**
     * Sets the language of the resource.
     * @param dcLanguage the language
     */
    default void setDcLanguage(String dcLanguage) {
        getDcChannelData().setDcLanguage(dcLanguage);
    }

    /**
     * Gets a related resource.
     * @return the relation
     */
    default Optional<String> getDcRelation() {
        return getDcChannelData().getDcRelation();
    }

    /**
     * Sets a related resource.
     * @param dcRelation the relation
     */
    default void setDcRelation(String dcRelation) {
        getDcChannelData().setDcRelation(dcRelation);
    }

    /**
     * Gets the spatial or temporal topic of the resource.
     * @return the coverage
     */
    default Optional<String> getDcCoverage() {
        return getDcChannelData().getDcCoverage();
    }

    /**
     * Sets the spatial or temporal topic of the resource.
     * @param dcCoverage the coverage
     */
    default void setDcCoverage(String dcCoverage) {
        getDcChannelData().setDcCoverage(dcCoverage);
    }

    /**
     * Gets information about rights held in and over the resource.
     * @return the rights
     */
    default Optional<String> getDcRights() {
        return getDcChannelData().getDcRights();
    }

    /**
     * Sets information about rights held in and over the resource.
     * @param dcRights the rights
     */
    default void setDcRights(String dcRights) {
        getDcChannelData().setDcRights(dcRights);
    }
}
