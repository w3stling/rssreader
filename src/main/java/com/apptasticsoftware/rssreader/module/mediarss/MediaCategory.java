package com.apptasticsoftware.rssreader.module.mediarss;

import java.util.Objects;
import java.util.Optional;

/**
 * Allows a taxonomy to be set that gives an indication of the type of media content, and its particular contents.
 * The category can use different schemes to classify content, with the default being "http://search.yahoo.com/mrss/category_schema".
 *
 * Examples:
 * {@code
 * <!-- Using default Yahoo scheme -->
 * <media:category scheme="http://search.yahoo.com/mrss/category_schema">
 *     music/artist/album/song
 * </media:category>
 *
 * <!-- Using DMOZ directory with human readable label -->
 * <media:category scheme="http://dmoz.org"
 *                 label="Ace Ventura - Pet Detective">
 *     Arts/Movies/Titles/A/Ace_Ventura_Series/Ace_Ventura_-_Pet_Detective
 * </media:category>
 *
 * <!-- Using Flickr tags -->
 * <media:category scheme="urn:flickr:tags">
 *     ycantpark mobile
 * </media:category>
 * }
 */
public class MediaCategory {
    private String schema;
    private String label;
    private String category;

    /**
     * The URI that identifies the categorization scheme.
     * @return schema
     */
    public Optional<String> getSchema() {
        return Optional.ofNullable(schema);
    }

    /**
     * The URI that identifies the categorization scheme.
     * @param schema schema
     */
    public void setScheme(String schema) {
        this.schema = schema;
    }

    /**
     * The human-readable label that can be displayed in end user applications.
     * @return label
     */
    public Optional<String> getLabel() {
        return Optional.ofNullable(label);
    }

    /**
     * The human-readable label that can be displayed in end user applications.
     * @param label label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Allows a taxonomy to be set that gives an indication of the type of media content, and its particular contents
     * @return category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Allows a taxonomy to be set that gives an indication of the type of media content, and its particular contents
     * @param category category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MediaCategory that = (MediaCategory) o;
        return Objects.equals(getSchema(), that.getSchema()) && Objects.equals(getLabel(), that.getLabel()) && Objects.equals(getCategory(), that.getCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSchema(), getLabel(), getCategory());
    }
}
