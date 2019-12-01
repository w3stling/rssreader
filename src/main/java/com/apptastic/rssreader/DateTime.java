/*
 * MIT License
 *
 * Copyright (c) 2018, Apptastic Software
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
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Date Time util class for converting date time strings
 */
public class DateTime {

    /**
     * Converts date time string to LocalDateTime object
     * @param dateTime date time string
     * @return local date time object
     */
    public static LocalDateTime toLocalDateTime(String dateTime) {
        if (dateTime == null)
            return null;

        DateTimeFormatter formatter = null;

        if (dateTime.length() == 31)
            formatter = DateTimeFormatter.RFC_1123_DATE_TIME;
        else if (dateTime.length() == 25)
            formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        else if (dateTime.length() == 19)
            formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        if (formatter == null) {
            throw new IllegalArgumentException("Unknown date time format " + dateTime);
        }

        Objects.requireNonNull(formatter, "Unknown date time format");

        return LocalDateTime.parse(dateTime, formatter);
    }

    /**
     * Convert date time string to time in milliseconds
     * @param dateTime date time string
     * @return time in milliseconds
     */
    public static Long toEpochMilli(String dateTime) {
        LocalDateTime localDateTime = toLocalDateTime(dateTime);

        if (localDateTime == null)
            return null;

        return localDateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
    }
}
