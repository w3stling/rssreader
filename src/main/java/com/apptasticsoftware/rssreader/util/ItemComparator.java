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
package com.apptasticsoftware.rssreader.util;

import com.apptasticsoftware.rssreader.DateTime;
import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.Item;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.Objects;

/**
 * Provides different comparators for sorting item objects.
 */
public final class ItemComparator {

    private ItemComparator() {

    }

    /**
     * Comparator for sorting Items on publication date in ascending order (oldest first)
     * @param <I> any class that extend Item
     * @return comparator
     */
    public static <I extends Item> Comparator<I> oldestItemFirst() {
        var dateTime = new DateTime();
        return Comparator.comparing((I i) -> i.getPubDate().map(dateTime::toInstant).orElse(Instant.EPOCH));
    }

    /**
     * Comparator for sorting Items on publication date in ascending order (oldest first)
     * @param <I> any class that extend Item
     * @param dateTimeParser date time parser
     * @return comparator
     */
    public static <I extends Item> Comparator<I> oldestItemFirst(DateTimeParser dateTimeParser) {
        Objects.requireNonNull(dateTimeParser, "Date time parser must not be null");
        return Comparator.comparing((I i) -> i.getPubDate().map(dateTimeParser::parse).map(ZonedDateTime::toInstant).orElse(Instant.EPOCH));
    }

    /**
     * Comparator for sorting Items on publication date in descending order (newest first)
     * @param <I> any class that extend Item
     * @return comparator
     */
    public static <I extends Item> Comparator<I> newestItemFirst() {
        var dateTime = new DateTime();
        return Comparator.comparing((I i) -> i.getPubDate().map(dateTime::toInstant).orElse(Instant.EPOCH)).reversed();
    }

    /**
     * Comparator for sorting Items on publication date in descending order (newest first)
     * @param <I> any class that extend Item
     * @param dateTimeParser date time parser
     * @return comparator
     */
    public static <I extends Item> Comparator<I> newestItemFirst(DateTimeParser dateTimeParser) {
        Objects.requireNonNull(dateTimeParser, "Date time parser must not be null");
        return Comparator.comparing((I i) -> i.getPubDate().map(dateTimeParser::parse).map(ZonedDateTime::toInstant).orElse(Instant.EPOCH)).reversed();
    }

    /**
     * Comparator for sorting Items on channel title
     * @param <I> any class that extend Item
     * @return comparator
     */
    public static <I extends Item> Comparator<I> channelTitle() {
        return Comparator.comparing((I i) -> i.getChannel().getTitle());
    }

}