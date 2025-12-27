package com.apptasticsoftware.rssreader.module.slash;

import java.util.Optional;

public interface SlashItemData {

    SlashItemData getSlashItemData();

    default Optional<String> getSlashSection() {
        return getSlashItemData().getSlashSection();
    }

    default void setSlashSection(String slashSection) {
        getSlashItemData().setSlashSection(slashSection);
    }

    default Optional<String> getSlashDepartment() {
        return getSlashItemData().getSlashDepartment();
    }

    default void setSlashDepartment(String slashDepartment) {
        getSlashItemData().setSlashDepartment(slashDepartment);
    }

    default Optional<Integer> getSlashComments() {
        return getSlashItemData().getSlashComments();
    }

    default void setSlashComments(Integer slashComments) {
        getSlashItemData().setSlashComments(slashComments);
    }

    default Optional<String> getSlashHitParade() {
        return getSlashItemData().getSlashHitParade();
    }

    default void setSlashHitParade(String slashHitParade) {
        getSlashItemData().setSlashHitParade(slashHitParade);
    }
}
