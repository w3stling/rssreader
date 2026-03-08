package com.apptasticsoftware.rssreader.module.dc;

import com.apptasticsoftware.rssreader.module.dc.internal.DcChannelDataProvider;
import java.util.Optional;

/**
 * Dublin Core metadata interface for RSS channels.
 * Provides access to DC metadata elements as defined in the Dublin Core specification.
 */
public interface DcChannelData {

    /**
     * Gets the resource title.
     * @return the title
     */
    default Optional<String> getDcTitle() {
        return ((DcChannelDataProvider) this).dcChannelData().getDcTitle();
    }

    /**
     * Sets the resource title.
     * @param dcTitle the title
     */
    default void setDcTitle(String dcTitle) {
        ((DcChannelDataProvider) this).dcChannelData().setDcTitle(dcTitle);
    }

    /**
     * Gets the entity responsible for creating the resource.
     * @return the creator
     */
    default Optional<String> getDcCreator() {
        return ((DcChannelDataProvider) this).dcChannelData().getDcCreator();
    }

    /**
     * Sets the entity responsible for creating the resource.
     * @param dcCreator the creator
     */
    default void setDcCreator(String dcCreator) {
        ((DcChannelDataProvider) this).dcChannelData().setDcCreator(dcCreator);
    }

    /**
     * Gets the topic of the resource.
     * @return the subject
     */
    default Optional<String> getDcSubject() {
        return ((DcChannelDataProvider) this).dcChannelData().getDcSubject();
    }

    /**
     * Sets the topic of the resource.
     * @param dcSubject the subject
     */
    default void setDcSubject(String dcSubject) {
        ((DcChannelDataProvider) this).dcChannelData().setDcSubject(dcSubject);
    }

    /**
     * Gets the account of the resource content.
     * @return the description
     */
    default Optional<String> getDcDescription() {
        return ((DcChannelDataProvider) this).dcChannelData().getDcDescription();
    }

    /**
     * Sets the account of the resource content.
     * @param dcDescription the description
     */
    default void setDcDescription(String dcDescription) {
        ((DcChannelDataProvider) this).dcChannelData().setDcDescription(dcDescription);
    }

    /**
     * Gets the entity responsible for making the resource available.
     * @return the publisher
     */
    default Optional<String> getDcPublisher() {
        return ((DcChannelDataProvider) this).dcChannelData().getDcPublisher();
    }

    /**
     * Sets the entity responsible for making the resource available.
     * @param dcPublisher the publisher
     */
    default void setDcPublisher(String dcPublisher) {
        ((DcChannelDataProvider) this).dcChannelData().setDcPublisher(dcPublisher);
    }

    /**
     * Gets the entity responsible for contributing to the resource.
     * @return the contributor
     */
    default Optional<String> getDcContributor() {
        return ((DcChannelDataProvider) this).dcChannelData().getDcContributor();
    }

    /**
     * Sets the entity responsible for contributing to the resource.
     * @param dcContributor the contributor
     */
    default void setDcContributor(String dcContributor) {
        ((DcChannelDataProvider) this).dcChannelData().setDcContributor(dcContributor);
    }

    /**
     * Gets the point or period of time associated with the resource.
     * @return the date
     */
    default Optional<String> getDcDate() {
        return ((DcChannelDataProvider) this).dcChannelData().getDcDate();
    }

    /**
     * Sets the point or period of time associated with the resource.
     * @param dcDate the date
     */
    default void setDcDate(String dcDate) {
        ((DcChannelDataProvider) this).dcChannelData().setDcDate(dcDate);
    }

    /**
     * Gets the nature or genre of the resource.
     * @return the type
     */
    default Optional<String> getDcType() {
        return ((DcChannelDataProvider) this).dcChannelData().getDcType();
    }

    /**
     * Sets the nature or genre of the resource.
     * @param dcType the type
     */
    default void setDcType(String dcType) {
        ((DcChannelDataProvider) this).dcChannelData().setDcType(dcType);
    }

    /**
     * Gets the file format or physical medium of the resource.
     * @return the format
     */
    default Optional<String> getDcFormat() {
        return ((DcChannelDataProvider) this).dcChannelData().getDcFormat();
    }

    /**
     * Sets the file format or physical medium of the resource.
     * @param dcFormat the format
     */
    default void setDcFormat(String dcFormat) {
        ((DcChannelDataProvider) this).dcChannelData().setDcFormat(dcFormat);
    }

    /**
     * Gets an unambiguous reference to the resource.
     * @return the identifier
     */
    default Optional<String> getDcIdentifier() {
        return ((DcChannelDataProvider) this).dcChannelData().getDcIdentifier();
    }

    /**
     * Sets an unambiguous reference to the resource.
     * @param dcIdentifier the identifier
     */
    default void setDcIdentifier(String dcIdentifier) {
        ((DcChannelDataProvider) this).dcChannelData().setDcIdentifier(dcIdentifier);
    }

    /**
     * Gets a related resource from which the described resource is derived.
     * @return the source
     */
    default Optional<String> getDcSource() {
        return ((DcChannelDataProvider) this).dcChannelData().getDcSource();
    }

    /**
     * Sets a related resource from which the described resource is derived.
     * @param dcSource the source
     */
    default void setDcSource(String dcSource) {
        ((DcChannelDataProvider) this).dcChannelData().setDcSource(dcSource);
    }

    /**
     * Gets the language of the resource.
     * @return the language
     */
    default Optional<String> getDcLanguage() {
        return ((DcChannelDataProvider) this).dcChannelData().getDcLanguage();
    }

    /**
     * Sets the language of the resource.
     * @param dcLanguage the language
     */
    default void setDcLanguage(String dcLanguage) {
        ((DcChannelDataProvider) this).dcChannelData().setDcLanguage(dcLanguage);
    }

    /**
     * Gets a related resource.
     * @return the relation
     */
    default Optional<String> getDcRelation() {
        return ((DcChannelDataProvider) this).dcChannelData().getDcRelation();
    }

    /**
     * Sets a related resource.
     * @param dcRelation the relation
     */
    default void setDcRelation(String dcRelation) {
        ((DcChannelDataProvider) this).dcChannelData().setDcRelation(dcRelation);
    }

    /**
     * Gets the spatial or temporal topic of the resource.
     * @return the coverage
     */
    default Optional<String> getDcCoverage() {
        return ((DcChannelDataProvider) this).dcChannelData().getDcCoverage();
    }

    /**
     * Sets the spatial or temporal topic of the resource.
     * @param dcCoverage the coverage
     */
    default void setDcCoverage(String dcCoverage) {
        ((DcChannelDataProvider) this).dcChannelData().setDcCoverage(dcCoverage);
    }

    /**
     * Gets information about rights held in and over the resource.
     * @return the rights
     */
    default Optional<String> getDcRights() {
        return ((DcChannelDataProvider) this).dcChannelData().getDcRights();
    }

    /**
     * Sets information about rights held in and over the resource.
     * @param dcRights the rights
     */
    default void setDcRights(String dcRights) {
        ((DcChannelDataProvider) this).dcChannelData().setDcRights(dcRights);
    }
}
