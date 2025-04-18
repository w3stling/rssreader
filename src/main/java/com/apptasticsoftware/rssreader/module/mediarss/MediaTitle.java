package com.apptasticsoftware.rssreader.module.mediarss;

import java.util.Optional;

public class MediaTitle {
    private String type;
    private String title;

    public Optional<String> getType() {
        return Optional.ofNullable(type);
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
