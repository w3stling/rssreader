package com.apptasticsoftware.rssreader.module.dc;

import java.util.Optional;

public interface DcItemData {

    DcItemData getDcItemData();

    default Optional<String> getDcTitle() {
        return getDcItemData().getDcTitle();
    }

    default void setDcTitle(String dcTitle) {
        getDcItemData().setDcTitle(dcTitle);
    }

    default Optional<String> getDcCreator() {
        return getDcItemData().getDcCreator();
    }

    default void setDcCreator(String dcCreator) {
        getDcItemData().setDcCreator(dcCreator);
    }

    default Optional<String> getDcSubject() {
        return getDcItemData().getDcSubject();
    }

    default void setDcSubject(String dcSubject) {
        getDcItemData().setDcSubject(dcSubject);
    }

    default Optional<String> getDcDescription() {
        return getDcItemData().getDcDescription();
    }

    default void setDcDescription(String dcDescription) {
        getDcItemData().setDcDescription(dcDescription);
    }

    default Optional<String> getDcPublisher() {
        return getDcItemData().getDcPublisher();
    }

    default void setDcPublisher(String dcPublisher) {
        getDcItemData().setDcPublisher(dcPublisher);
    }

    default Optional<String> getDcContributor() {
        return getDcItemData().getDcContributor();
    }

    default void setDcContributor(String dcContributor) {
        getDcItemData().setDcContributor(dcContributor);
    }

    default Optional<String> getDcDate() {
        return getDcItemData().getDcDate();
    }

    default void setDcDate(String dcDate) {
        getDcItemData().setDcDate(dcDate);
    }

    default Optional<String> getDcType() {
        return getDcItemData().getDcType();
    }

    default void setDcType(String dcType) {
        getDcItemData().setDcType(dcType);
    }

    default Optional<String> getDcFormat() {
        return getDcItemData().getDcFormat();
    }

    default void setDcFormat(String dcFormat) {
        getDcItemData().setDcFormat(dcFormat);
    }

    default Optional<String> getDcIdentifier() {
        return getDcItemData().getDcIdentifier();
    }

    default void setDcIdentifier(String dcIdentifier) {
        getDcItemData().setDcIdentifier(dcIdentifier);
    }

    default Optional<String> getDcSource() {
        return getDcItemData().getDcSource();
    }

    default void setDcSource(String dcSource) {
        getDcItemData().setDcSource(dcSource);
    }

    default Optional<String> getDcLanguage() {
        return getDcItemData().getDcLanguage();
    }

    default void setDcLanguage(String dcLanguage) {
        getDcItemData().setDcLanguage(dcLanguage);
    }

    default Optional<String> getDcRelation() {
        return getDcItemData().getDcRelation();
    }

    default void setDcRelation(String dcRelation) {
        getDcItemData().setDcRelation(dcRelation);
    }

    default Optional<String> getDcCoverage() {
        return getDcItemData().getDcCoverage();
    }

    default void setDcCoverage(String dcCoverage) {
        getDcItemData().setDcCoverage(dcCoverage);
    }

    default Optional<String> getDcRights() {
        return getDcItemData().getDcRights();
    }

    default void setDcRights(String dcRights) {
        getDcItemData().setDcRights(dcRights);
    }
}
