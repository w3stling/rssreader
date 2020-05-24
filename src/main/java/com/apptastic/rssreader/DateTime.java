/*
 * MIT License
 *
 * Copyright (c) 2020,, Apptastic Software
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
package com.apptastic.rssreader;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

/**
 * Date Time util class for converting date time strings
 */
public class DateTime {
    private static ZoneId defaultZone = ZoneId.of("UTC");

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

        if (dateTime.length() == 19) {
            // Missing time zone information use default time zone. If not setting any default time zone system default
            // time zone is used.
            LocalDateTime localDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            return ZonedDateTime.of(localDateTime, defaultZone);
        }

        if (formatter == null) {
            throw new IllegalArgumentException("Unknown date time format " + dateTime);
        }

        return ZonedDateTime.parse(dateTime, formatter);
    }

    private static DateTimeFormatter getDateTimeFormatter(String dateTime) {
        if (dateTime.length() >= 29 && dateTime.length() <= 31)
            return DateTimeFormatter.RFC_1123_DATE_TIME;
        else if (dateTime.length() == 25)
            return DateTimeFormatter.ISO_OFFSET_DATE_TIME;
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
