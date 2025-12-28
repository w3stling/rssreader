package com.apptasticsoftware.rssreader.module.slash;

import java.util.Optional;

/**
 * Interface for accessing Slash RSS module item data.
 * Provides methods to get and set Slash-specific metadata including section, department, comments, and hit parade.
 *
 * @see <a href="http://purl.org/rss/1.0/modules/slash/">Slash RSS Module Specification</a>
 */
public interface SlashItemData {

    /**
     * Returns the underlying Slash item data object.
     *
     * @return the Slash item data
     */
    SlashItemData getSlashItemData();

    /**
     * Returns the Slash section (category) of the item.
     *
     * @return an Optional containing the section, or empty if not set
     */
    default Optional<String> getSlashSection() {
        return getSlashItemData().getSlashSection();
    }

    /**
     * Sets the Slash section (category) of the item.
     *
     * @param slashSection the section to set
     */
    default void setSlashSection(String slashSection) {
        getSlashItemData().setSlashSection(slashSection);
    }

    /**
     * Returns the Slash department of the item.
     *
     * @return an Optional containing the department, or empty if not set
     */
    default Optional<String> getSlashDepartment() {
        return getSlashItemData().getSlashDepartment();
    }

    /**
     * Sets the Slash department of the item.
     *
     * @param slashDepartment the department to set
     */
    default void setSlashDepartment(String slashDepartment) {
        getSlashItemData().setSlashDepartment(slashDepartment);
    }

    /**
     * Returns the number of comments for the item.
     *
     * @return an Optional containing the comment count, or empty if not set
     */
    default Optional<Integer> getSlashComments() {
        return getSlashItemData().getSlashComments();
    }

    /**
     * Sets the number of comments for the item.
     *
     * @param slashComments the comment count to set
     */
    default void setSlashComments(Integer slashComments) {
        getSlashItemData().setSlashComments(slashComments);
    }

    /**
     * Returns the Slash hit parade (comma-separated list of view counts).
     *
     * @return an Optional containing the hit parade, or empty if not set
     */
    default Optional<String> getSlashHitParade() {
        return getSlashItemData().getSlashHitParade();
    }

    /**
     * Sets the Slash hit parade (comma-separated list of view counts).
     *
     * @param slashHitParade the hit parade to set
     */
    default void setSlashHitParade(String slashHitParade) {
        getSlashItemData().setSlashHitParade(slashHitParade);
    }
}
