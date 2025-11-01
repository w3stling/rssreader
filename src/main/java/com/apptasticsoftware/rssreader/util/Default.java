package com.apptasticsoftware.rssreader.util;

import com.apptasticsoftware.rssreader.DateTime;
import com.apptasticsoftware.rssreader.DateTimeParser;

import java.util.Locale;

/**
 * Provides default implementations for various components.
 */
@SuppressWarnings("javaarchitecture:S7091")
public class Default {

    private Default() {
        // Utility class
    }

    /**
     * Get the default date time parser.
     * @return date time parser
     */
    public static DateTimeParser getDateTimeParser() {
        return new DateTime();
    }

    /**
     * Get the default locale.
     * @return locale
     */
    public static Locale getLocale() {
        return Locale.ENGLISH;
    }
}
