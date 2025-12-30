package com.apptasticsoftware.rssreader.module.dc;

import java.util.Optional;

public interface DcChannelData {

    DcChannelData getDcChannelData();

    default Optional<String> getDcTitle() {
        return getDcChannelData().getDcTitle();
    }

    default void setDcTitle(String dcTitle) {
        getDcChannelData().setDcTitle(dcTitle);
    }

    default Optional<String> getDcCreator() {
        return getDcChannelData().getDcCreator();
    }

    default void setDcCreator(String dcCreator) {
        getDcChannelData().setDcCreator(dcCreator);
    }

    default Optional<String> getDcSubject() {
        return getDcChannelData().getDcSubject();
    }

    default void setDcSubject(String dcSubject) {
        getDcChannelData().setDcSubject(dcSubject);
    }

    default Optional<String> getDcDescription() {
        return getDcChannelData().getDcDescription();
    }

    default void setDcDescription(String dcDescription) {
        getDcChannelData().setDcDescription(dcDescription);
    }

    default Optional<String> getDcPublisher() {
        return getDcChannelData().getDcPublisher();
    }

    default void setDcPublisher(String dcPublisher) {
        getDcChannelData().setDcPublisher(dcPublisher);
    }

    default Optional<String> getDcContributor() {
        return getDcChannelData().getDcContributor();
    }

    default void setDcContributor(String dcContributor) {
        getDcChannelData().setDcContributor(dcContributor);
    }

    default Optional<String> getDcDate() {
        return getDcChannelData().getDcDate();
    }

    default void setDcDate(String dcDate) {
        getDcChannelData().setDcDate(dcDate);
    }

    default Optional<String> getDcType() {
        return getDcChannelData().getDcType();
    }

    default void setDcType(String dcType) {
        getDcChannelData().setDcType(dcType);
    }

    default Optional<String> getDcFormat() {
        return getDcChannelData().getDcFormat();
    }

    default void setDcFormat(String dcFormat) {
        getDcChannelData().setDcFormat(dcFormat);
    }

    default Optional<String> getDcIdentifier() {
        return getDcChannelData().getDcIdentifier();
    }

    default void setDcIdentifier(String dcIdentifier) {
        getDcChannelData().setDcIdentifier(dcIdentifier);
    }

    default Optional<String> getDcSource() {
        return getDcChannelData().getDcSource();
    }

    default void setDcSource(String dcSource) {
        getDcChannelData().setDcSource(dcSource);
    }

    default Optional<String> getDcLanguage() {
        return getDcChannelData().getDcLanguage();
    }

    default void setDcLanguage(String dcLanguage) {
        getDcChannelData().setDcLanguage(dcLanguage);
    }

    default Optional<String> getDcRelation() {
        return getDcChannelData().getDcRelation();
    }

    default void setDcRelation(String dcRelation) {
        getDcChannelData().setDcRelation(dcRelation);
    }

    default Optional<String> getDcCoverage() {
        return getDcChannelData().getDcCoverage();
    }

    default void setDcCoverage(String dcCoverage) {
        getDcChannelData().setDcCoverage(dcCoverage);
    }

    default Optional<String> getDcRights() {
        return getDcChannelData().getDcRights();
    }

    default void setDcRights(String dcRights) {
        getDcChannelData().setDcRights(dcRights);
    }
}
