package com.apptasticsoftware.rssreader.module.slash.internal;

import com.apptasticsoftware.rssreader.module.slash.SlashItemData;

import java.util.Objects;
import java.util.Optional;

public class SlashItemDataImpl implements SlashItemData {
    private String slashSection;
    private String slashDepartment;
    private Integer slashComments;
    private String slashHitParade;

    public SlashItemData getSlashItemData() {
        return this;
    }

    public Optional<String> getSlashSection() {
        return Optional.ofNullable(slashSection);
    }

    public void setSlashSection(String slashSection) {
        this.slashSection = slashSection;
    }

    public Optional<String> getSlashDepartment() {
        return Optional.ofNullable(slashDepartment);
    }

    public void setSlashDepartment(String slashDepartment) {
        this.slashDepartment = slashDepartment;
    }

    public Optional<Integer> getSlashComments() {
        return Optional.ofNullable(slashComments);
    }

    public void setSlashComments(Integer slashComments) {
        this.slashComments = slashComments;
    }

    public Optional<String> getSlashHitParade() {
        return Optional.ofNullable(slashHitParade);
    }

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
