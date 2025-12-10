package com.apptasticsoftware.rssreader;

import java.time.DayOfWeek;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public interface Channel {
    /**
     * Get the name of the channel. It's how people refer to your service. If you have an HTML website that contains the same information as your RSS file, the title of your channel should be the same as the title of your website.
     * @return title
     */
    String getTitle();

    /**
     * Set the name of the channel. It's how people refer to your service. If you have an HTML website that contains the same information as your RSS file, the title of your channel should be the same as the title of your website.
     * @param title title
     */
    void setTitle(String title);

    /**
     * Get phrase or sentence describing the channel.
     * @return description
     */
    String getDescription();

    /**
     * Set phrase or sentence describing the channel.
     * @param description channel description
     */
    void setDescription(String description);
    /**
     * Get category for the channel.
     *
     * @deprecated
     * This method be removed in a future version.
     * <p> Use {@link ChannelImpl#getCategories()} instead.
     *
     * @return category
     */
    @SuppressWarnings("java:S1133")
    @Deprecated(since="3.3.0", forRemoval=true)
    Optional<String> getCategory();

    /**
     * Set category for the channel.
     *
     * @deprecated
     * This method be removed in a future version.
     * <p> Use {@link ChannelImpl#addCategory(String category)} instead.
     *
     * @param category channel category
     */
    @SuppressWarnings("java:S1133")
    @Deprecated(since="3.3.0", forRemoval=true)
    void setCategory(String category);

    /**
     * Get categories for the channel.
     * @return list of categories
     */
    List<String> getCategories();

    /**
     * Add category for the channel.
     * @param category channel category
     */
    void addCategory(String category);

    /**
     * Get the language the channel is written in.
     * @return language
     */
    Optional<String> getLanguage();

    /**
     * Set the language the channel is written in.
     * @param language language
     */
    void setLanguage(String language);

    /**
     * Get the URL to the HTML website corresponding to the channel.
     * @return link
     */
    String getLink();

    /**
     * Set the URL to the HTML website corresponding to the channel.
     * @param link URL
     */
    void setLink(String link);

    /**
     * Get copyright notice for content in the channel.
     * @return URL
     */
    Optional<String> getCopyright();

    /**
     * Set copyright notice for content in the channel.
     * @param copyright copyright
     */
    void setCopyright(String copyright);

    /**
     * Get a string indicating the program used to generate the channel.
     * @return generator
     */
    Optional<String> getGenerator();

    /**
     * Set a string indicating the program used to generate the channel.
     * @param generator generator
     */
    void setGenerator(String generator);

    /**
     * Get ttl (time to live). It's a number of minutes that indicates how long a channel can be cached before
     * refreshing from the source.
     * @return time to live
     */
    Optional<String> getTtl();

    /**
     * Set ttl (time to live). It's a number of minutes that indicates how long a channel can be cached before
     * refreshing from the source.
     * @param ttl time to live
     */
    void setTtl(String ttl);

    /**
     * Get the publication date for the content in the channel.
     * @return publication date
     */
    Optional<String> getPubDate();

    /**
     * Get the publication date for the content in the channel.
     * @return publication date
     */
    Optional<ZonedDateTime> getPubDateAsZonedDateTime();

    /**
     * Get the publication date for the content in the channel.
     *
     * @deprecated
     * <p> Use {@link Channel#getPubDateAsZonedDateTime()} instead.
     *
     * @return publication date
     */
    @SuppressWarnings("java:S1133")
    @Deprecated(since="3.12.0", forRemoval=true)
    Optional<ZonedDateTime> getPubDateZonedDateTime();

    /**
     * Set the publication date for the content in the channel.
     * @param pubDate publication date
     */
    void setPubDate(String pubDate);

    /**
     * Get the last time the content of the channel changed.
     * @return last build date
     */
    Optional<String> getLastBuildDate();

    /**
     * Get the last time the content of the channel changed.
     * @return last build date
     */
    Optional<ZonedDateTime> getLastBuildDateAsZonedDateTime();

    /**
     * Get the last time the content of the channel changed.
     *
     * @deprecated
     * <p> Use {@link Channel#getLastBuildDateAsZonedDateTime()} instead.
     *
     * @return last build date
     */
    @SuppressWarnings("java:S1133")
    @Deprecated(since="3.12.0", forRemoval=true)
    Optional<ZonedDateTime> getLastBuildDateZonedDateTime();

    /**
     * Set the last time the content of the channel changed.
     * @param lastBuildDate last build date
     */
    void setLastBuildDate(String lastBuildDate);

    /**
     * Get email address for person responsible for editorial content.
     * @return managing editor
     */
    Optional<String> getManagingEditor();

    /**
     * Set email address for person responsible for editorial content.
     * @param managingEditor managing editor
     */
    void setManagingEditor(String managingEditor);

    /**
     * Get email address for person responsible for technical issues relating to channel.
     * @return web master
     */
    Optional<String> getWebMaster();

    /**
     * Set email address for person responsible for technical issues relating to channel.
     * @param webMaster web master
     */
    void setWebMaster(String webMaster);
    /**
     * Get the documentation for the format used in the RSS file.
     * @return documentation
     */
    String getDocs();

    /**
     * Set  the documentation for the format used in the RSS file.
     * @param docs documentation
     */
    void setDocs(String docs);

    /**
     * Get the PICS rating for the channel.
     * @return rating
     */
    String getRating();

    /**
     * Set the PICS rating for the channel.
     * @param rating rating
     */
    void setRating(String rating);

    /**
     * Get the list of hours that aggregators can skip reading the channel.
     * The values are numbers between 0 and 23, representing times in GMT.
     * Hour zero is the hour beginning at midnight.
     * Aggregators may not read the channel during hours listed in the skipHours element.
     * @return list of skip hours
     */
    List<Integer> getSkipHours();

    /**
     * Add an hour that aggregators can skip reading the channel.
     * Valid values are numbers between 0 and 23, representing times in GMT.
     * Hour zero is the hour beginning at midnight.
     * @param skipHour the hour to skip reading the channel (0-23)
     */
    void addSkipHour(Integer skipHour);

    /**
     * Get the list of days that aggregators can skip reading the channel.
     * The values can be Monday, Tuesday, Wednesday, Thursday, Friday, Saturday or Sunday.
     * Aggregators may not read the channel during days listed in the skipDays element.
     * @return list of skip days
     */
    List<String> getSkipDays();

    /**
     * Get the list of days that aggregators can skip reading the channel as DayOfWeek enum values.
     * The values are converted from the string representation (Monday, Tuesday, etc.) to their corresponding DayOfWeek enum values.
     * Aggregators may not read the channel during days listed in the skipDays element.
     * @return list of skip days as DayOfWeek enum values
     */
    List<DayOfWeek> getSkipDaysAsDayOfWeek();

    /**
     * Add a day that aggregators can skip reading the channel.
     * Valid values are Monday, Tuesday, Wednesday, Thursday, Friday, Saturday or Sunday.
     * @param skipDay the day to skip reading the channel
     */
    void addSkipDay(String skipDay);

    /**
     * Get a GIF, JPEG or PNG image that can be displayed with the channel.
     * @return image
     */
    Optional<Image> getImage();

    /**
     * Set a GIF, JPEG or PNG image that can be displayed with the channel.
     * @param image image
     */
    void setImage(Image image);
}
