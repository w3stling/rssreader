package com.apptasticsoftware.rssreader.module.slash.internal;

import com.apptasticsoftware.rssreader.module.slash.SlashItemData;

import java.util.Objects;
import java.util.Optional;

/**
 * Implementation of SlashItemData storing Slash-specific item metadata.
 */
public class SlashItemDataImpl implements SlashItemData {
    private String slashSection;
    private String slashDepartment;
    private Integer slashComments;
    private String slashHitParade;

    @Override
    public SlashItemData getSlashItemData() {
        return this;
    }

    /**
     * Returns the Slash section of the item.
     *
     * @return an Optional containing the section, or empty if not set
     */
    public Optional<String> getSlashSection() {
        return Optional.ofNullable(slashSection);
    }

    /**
     * Sets the Slash section of the item.
     *
     * @param slashSection the section to set
     */
    public void setSlashSection(String slashSection) {
        this.slashSection = slashSection;
    }

    /**
     * Returns the Slash department of the item.
     *
     * @return an Optional containing the department, or empty if not set
     */
    public Optional<String> getSlashDepartment() {
        return Optional.ofNullable(slashDepartment);
    }

    /**
     * Sets the Slash department of the item.
     *
     * @param slashDepartment the department to set
     */
    public void setSlashDepartment(String slashDepartment) {
        this.slashDepartment = slashDepartment;
    }

    /**
     * Returns the number of comments for the item.
     *
     * @return an Optional containing the comment count, or empty if not set
     */
    public Optional<Integer> getSlashComments() {
        return Optional.ofNullable(slashComments);
    }

    /**
     * Sets the number of comments for the item.
     *
     * @param slashComments the comment count to set
     */
    public void setSlashComments(Integer slashComments) {
        this.slashComments = slashComments;
    }

    /**
     * Returns the Slash hit parade (comma-separated list of view counts).
     *
     * @return an Optional containing the hit parade, or empty if not set
     */
    public Optional<String> getSlashHitParade() {
        return Optional.ofNullable(slashHitParade);
    }

    /**
     * Sets the Slash hit parade (comma-separated list of view counts).
     *
     * @param slashHitParade the hit parade to set
     */
    public void setSlashHitParade(String slashHitParade) {
        this.slashHitParade = slashHitParade;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SlashItemDataImpl that = (SlashItemDataImpl) o;
        return Objects.equals(getSlashSection(), that.getSlashSection()) && Objects.equals(getSlashDepartment(), that.getSlashDepartment()) && Objects.equals(getSlashComments(), that.getSlashComments()) && Objects.equals(getSlashHitParade(), that.getSlashHitParade());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSlashSection(), getSlashDepartment(), getSlashComments(), getSlashHitParade());
    }
}
