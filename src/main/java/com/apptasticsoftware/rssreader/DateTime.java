/*
 * MIT License
 *
 * Copyright (c) 2022, Apptastic Software
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.apptasticsoftware.rssreader;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import static java.time.temporal.ChronoField.*;

/**
 * Date Time util class for converting date time strings
 */
public class DateTime {
    private static ZoneId defaultZone = ZoneId.of("UTC");

    public static final DateTimeFormatter RFC_1123_DATE_TIME_NO_TIMEZONE;
    static {
        // manually code maps to ensure correct data always used
        // (locale data can be changed by application code)
        Map<Long, String> dow = new HashMap<>();
        dow.put(1L, "Mon");
        dow.put(2L, "Tue");
        dow.put(3L, "Wed");
        dow.put(4L, "Thu");
        dow.put(5L, "Fri");
        dow.put(6L, "Sat");
        dow.put(7L, "Sun");
        Map<Long, String> moy = new HashMap<>();
        moy.put(1L, "Jan");
        moy.put(2L, "Feb");
        moy.put(3L, "Mar");
        moy.put(4L, "Apr");
        moy.put(5L, "May");
        moy.put(6L, "Jun");
        moy.put(7L, "Jul");
        moy.put(8L, "Aug");
        moy.put(9L, "Sep");
        moy.put(10L, "Oct");
        moy.put(11L, "Nov");
        moy.put(12L, "Dec");
        RFC_1123_DATE_TIME_NO_TIMEZONE = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .parseLenient()
                .optionalStart()
                .appendText(DAY_OF_WEEK, dow)
                .appendLiteral(", ")
                .optionalEnd()
                .appendValue(DAY_OF_MONTH, 1, 2, SignStyle.NOT_NEGATIVE)
                .appendLiteral(' ')
                .appendText(MONTH_OF_YEAR, moy)
                .appendLiteral(' ')
                .appendValue(YEAR, 4)  // 2 digit year not handled
                .appendLiteral(' ')
                .appendValue(HOUR_OF_DAY, 2)
                .appendLiteral(':')
                .appendValue(MINUTE_OF_HOUR, 2)
                .optionalStart()
                .appendLiteral(':')
                .appendValue(SECOND_OF_MINUTE, 2)
                .toFormatter();
    }

    private DateTime() {

    }

    /**
     * Time zone to use if now zone information if found in date time string
     * @param defaultZone time zone to use
     */
    public static void setDefaultZone(ZoneId defaultZone) {
        DateTime.defaultZone = defaultZone;
    }

    /**
     * Converts date time string to LocalDateTime object. Note any time zone information in date time string is ignored.
     * @param dateTime date time string
     * @return local date time object
     */
    public static LocalDateTime toLocalDateTime(String dateTime) {
        if (dateTime == null)
            return null;

        DateTimeFormatter formatter = getDateTimeFormatter(dateTime);

        if (formatter == null) {
            throw new IllegalArgumentException("Unknown date time format " + dateTime);
        }

        return LocalDateTime.parse(dateTime, formatter);
    }

    /**
     * Converts date time string to ZonedDateTime object. Use if time date string contains time zone information.
     * @param dateTime date time string
     * @return zoned date time object
     */
    public static ZonedDateTime toZonedDateTime(String dateTime) {
        if (dateTime == null)
            return null;

        DateTimeFormatter formatter = getDateTimeFormatter(dateTime);

        if (formatter == null) {
            throw new IllegalArgumentException("Unknown date time format " + dateTime);
        }

        if (dateTime.length() == 19 ||
            ((dateTime.length() == 24 || dateTime.length() == 25) && dateTime.charAt(3) == ',')) {
            // Missing time zone information use default time zone. If not setting any default time zone system default
            // time zone is used.
            LocalDateTime localDateTime = LocalDateTime.parse(dateTime, formatter);
            return ZonedDateTime.of(localDateTime, defaultZone);
        }

        return ZonedDateTime.parse(dateTime, formatter);
    }

    private static DateTimeFormatter getDateTimeFormatter(String dateTime) {
        if (dateTime.length() >= 20 && dateTime.length() <= 31 && dateTime.charAt(4) == '-' && dateTime.charAt(10) == 'T')
            return DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        else if (dateTime.length() >= 29 && dateTime.length() <= 31)
            return DateTimeFormatter.RFC_1123_DATE_TIME;
        else if ((dateTime.length() == 24 || dateTime.length() == 25) && dateTime.charAt(3) == ',')
            return RFC_1123_DATE_TIME_NO_TIMEZONE;
        else if (dateTime.length() == 19)
            return DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        return null;
    }

    /**
     * Convert date time string to time in milliseconds
     * @param dateTime date time string
     * @return time in milliseconds
     */
    public static Long toEpochMilli(String dateTime) {
        ZonedDateTime zonedDateTime = toZonedDateTime(dateTime);

        if (zonedDateTime == null)
            return null;

        return zonedDateTime.toInstant().toEpochMilli();
    }


    /**
     * Comparator comparing publication date of Item class. Sorted in ascending order (oldest first)
     * @return comparator
     */
    public static Comparator<Item> pubDateComparator() {
        return Comparator.comparing(i -> i.getPubDate().map(DateTime::toEpochMilli).orElse(0L));
    }

}
