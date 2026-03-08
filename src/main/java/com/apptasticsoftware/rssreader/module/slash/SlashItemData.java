package com.apptasticsoftware.rssreader.module.slash;

import com.apptasticsoftware.rssreader.module.slash.internal.SlashItemDataProvider;

import java.util.Optional;

/**
 * Interface for accessing Slash RSS module item data.
 * Provides methods to get and set Slash-specific metadata including section, department, comments, and hit parade.
 *
 * @see <a href="http://purl.org/rss/1.0/modules/slash/">Slash RSS Module Specification</a>
 */
public interface SlashItemData {

    /**
     * Returns the Slash section (category) of the item.
     *
     * @return an Optional containing the section, or empty if not set
     */
    default Optional<String> getSlashSection() {
        return ((SlashItemDataProvider) this).slashItemData().getSlashSection();
    }

    /**
     * Sets the Slash section (category) of the item.
     *
     * @param slashSection the section to set
     */
    default void setSlashSection(String slashSection) {
        ((SlashItemDataProvider) this).slashItemData().setSlashSection(slashSection);
    }

    /**
     * Returns the Slash department of the item.
     *
     * @return an Optional containing the department, or empty if not set
     */
    default Optional<String> getSlashDepartment() {
        return ((SlashItemDataProvider) this).slashItemData().getSlashDepartment();
    }

    /**
     * Sets the Slash department of the item.
     *
     * @param slashDepartment the department to set
     */
    default void setSlashDepartment(String slashDepartment) {
        ((SlashItemDataProvider) this).slashItemData().setSlashDepartment(slashDepartment);
    }

    /**
     * Returns the number of comments for the item.
     *
     * @return an Optional containing the comment count, or empty if not set
     */
    default Optional<Integer> getSlashComments() {
        return ((SlashItemDataProvider) this).slashItemData().getSlashComments();
    }

    /**
     * Sets the number of comments for the item.
     *
     * @param slashComments the comment count to set
     */
    default void setSlashComments(Integer slashComments) {
        ((SlashItemDataProvider) this).slashItemData().setSlashComments(slashComments);
    }

    /**
     * Returns the Slash hit parade (comma-separated list of view counts).
     *
     * @return an Optional containing the hit parade, or empty if not set
     */
    default Optional<String> getSlashHitParade() {
        return ((SlashItemDataProvider) this).slashItemData().getSlashHitParade();
    }

    /**
     * Sets the Slash hit parade (comma-separated list of view counts).
     *
     * @param slashHitParade the hit parade to set
     */
    default void setSlashHitParade(String slashHitParade) {
        ((SlashItemDataProvider) this).slashItemData().setSlashHitParade(slashHitParade);
    }
}
