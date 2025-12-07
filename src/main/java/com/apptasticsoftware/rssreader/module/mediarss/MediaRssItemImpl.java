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
package com.apptasticsoftware.rssreader.module.mediarss;

import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.ItemImpl;

import java.util.Objects;

/**
 * Class representing the media rss item.
 */
public class MediaRssItemImpl extends ItemImpl implements MediaRssItem {
    private final MediaRssItemData data = new MediaRssItemDataImpl();

    /**
     * Constructor
     *
     * @param dateTimeParser timestamp parser
     */
    public MediaRssItemImpl(DateTimeParser dateTimeParser) {
        super(dateTimeParser);
    }


    /**
     * Internal method to get media rss item data.
     *
     * @return media rss item data
     */
    @Override
    public MediaRssItemData getMediaRssItemData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MediaRssItem)) return false;
        if (!super.equals(o)) return false;
        MediaRssItem that = (MediaRssItem) o;
        return Objects.equals(getMediaRssItemData(), that.getMediaRssItemData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getMediaRssItemData());
    }
}
