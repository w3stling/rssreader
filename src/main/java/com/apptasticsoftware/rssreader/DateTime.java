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
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.*;
import java.util.Comparator;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_TIME;

/**
 * Date Time util class for converting date time strings
 */
public class DateTime {
    private static ZoneId defaultZone = ZoneId.of("UTC");
    public static final DateTimeFormatter RFC_1123_DATE_TIME_NO_TIMEZONE;
    public static final DateTimeFormatter ISO_LOCAL_DATE_TIME_SPECIAL;
    public static final DateTimeFormatter RFC_1123_DATE_TIME_SPECIAL;
    public static final DateTimeFormatter RFC_1123_DATE_TIME_SPECIAL_EST;
    public static final DateTimeFormatter RFC_1123_DATE_TIME_SPECIAL_EDT;
    public static final DateTimeFormatter RFC_1123_DATE_TIME_SPECIAL_CST;
    public static final DateTimeFormatter RFC_1123_DATE_TIME_SPECIAL_CDT;
    public static final DateTimeFormatter RFC_1123_DATE_TIME_SPECIAL_MST;
    public static final DateTimeFormatter RFC_1123_DATE_TIME_SPECIAL_MDT;
    public static final DateTimeFormatter RFC_1123_DATE_TIME_SPECIAL_PST;
    public static final DateTimeFormatter RFC_1123_DATE_TIME_SPECIAL_PDT;

    static {

        RFC_1123_DATE_TIME_NO_TIMEZONE = DateTimeFormatter.ofPattern("EEE, d LLL yyyy HH:mm:ss")
                                                          .withZone(ZoneId.of("UTC"));

        ISO_LOCAL_DATE_TIME_SPECIAL = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .append(ISO_LOCAL_DATE)
                .appendLiteral(' ')
                .append(ISO_LOCAL_TIME)
                .toFormatter();

        RFC_1123_DATE_TIME_SPECIAL = DateTimeFormatter.ofPattern("EEE, d LLL yyyy HH:mm:ss z");

        RFC_1123_DATE_TIME_SPECIAL_EDT = DateTimeFormatter.ofPattern("EEE, d LLL yyyy HH:mm:ss 'EDT'").withZone(ZoneOffset.ofHours(-4));
        RFC_1123_DATE_TIME_SPECIAL_EST = DateTimeFormatter.ofPattern("EEE, d LLL yyyy HH:mm:ss 'EST'").withZone(ZoneOffset.ofHours(-5));
        RFC_1123_DATE_TIME_SPECIAL_CDT = DateTimeFormatter.ofPattern("EEE, d LLL yyyy HH:mm:ss 'CDT'").withZone(ZoneOffset.ofHours(-5));
        RFC_1123_DATE_TIME_SPECIAL_CST = DateTimeFormatter.ofPattern("EEE, d LLL yyyy HH:mm:ss 'CST'").withZone(ZoneOffset.ofHours(-6));
        RFC_1123_DATE_TIME_SPECIAL_MDT = DateTimeFormatter.ofPattern("EEE, d LLL yyyy HH:mm:ss 'MDT'").withZone(ZoneOffset.ofHours(-6));
        RFC_1123_DATE_TIME_SPECIAL_MST = DateTimeFormatter.ofPattern("EEE, d LLL yyyy HH:mm:ss 'MST'").withZone(ZoneOffset.ofHours(-7));
        RFC_1123_DATE_TIME_SPECIAL_PDT = DateTimeFormatter.ofPattern("EEE, d LLL yyyy HH:mm:ss 'PDT'").withZone(ZoneOffset.ofHours(-7));
        RFC_1123_DATE_TIME_SPECIAL_PST = DateTimeFormatter.ofPattern("EEE, d LLL yyyy HH:mm:ss 'PST'").withZone(ZoneOffset.ofHours(-8));
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
        else if ((dateTime.length() == 28 || dateTime.length() == 29) && dateTime.charAt(3) == ',' && dateTime.endsWith(" UTC"))
            return RFC_1123_DATE_TIME_SPECIAL;
        else if ((dateTime.length() == 28 || dateTime.length() == 29) && dateTime.charAt(3) == ',' && dateTime.endsWith(" EDT"))
            return RFC_1123_DATE_TIME_SPECIAL_EDT;
        else if ((dateTime.length() == 28 || dateTime.length() == 29) && dateTime.charAt(3) == ',' && dateTime.endsWith(" EST"))
            return RFC_1123_DATE_TIME_SPECIAL_EST;
        else if ((dateTime.length() == 28 || dateTime.length() == 29) && dateTime.charAt(3) == ',' && dateTime.endsWith(" CDT"))
            return RFC_1123_DATE_TIME_SPECIAL_CDT;
        else if ((dateTime.length() == 28 || dateTime.length() == 29) && dateTime.charAt(3) == ',' && dateTime.endsWith(" CST"))
            return RFC_1123_DATE_TIME_SPECIAL_CST;
        else if ((dateTime.length() == 28 || dateTime.length() == 29) && dateTime.charAt(3) == ',' && dateTime.endsWith(" MDT"))
            return RFC_1123_DATE_TIME_SPECIAL_MDT;
        else if ((dateTime.length() == 28 || dateTime.length() == 29) && dateTime.charAt(3) == ',' && dateTime.endsWith(" MST"))
            return RFC_1123_DATE_TIME_SPECIAL_MST;
        else if ((dateTime.length() == 28 || dateTime.length() == 29) && dateTime.charAt(3) == ',' && dateTime.endsWith(" PDT"))
            return RFC_1123_DATE_TIME_SPECIAL_PDT;
        else if ((dateTime.length() == 28 || dateTime.length() == 29) && dateTime.charAt(3) == ',' && dateTime.endsWith(" PST"))
            return RFC_1123_DATE_TIME_SPECIAL_PST;
        else if (dateTime.length() >= 28 && dateTime.length() <= 31)
            return DateTimeFormatter.RFC_1123_DATE_TIME;
        else if ((dateTime.length() == 26 || dateTime.length() == 27) && dateTime.charAt(3) == ',' && dateTime.endsWith(" Z"))
            return RFC_1123_DATE_TIME_SPECIAL;
        else if ((dateTime.length() == 24 || dateTime.length() == 25) && dateTime.charAt(3) == ',')
            return RFC_1123_DATE_TIME_NO_TIMEZONE;
        else if (dateTime.length() == 19 && dateTime.charAt(10) == 'T')
            return DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        else if (dateTime.length() == 19 && dateTime.charAt(10) == ' ')
            return ISO_LOCAL_DATE_TIME_SPECIAL;
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
