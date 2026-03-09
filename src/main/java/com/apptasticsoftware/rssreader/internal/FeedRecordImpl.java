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
package com.apptasticsoftware.rssreader.internal;

import com.apptasticsoftware.rssreader.Channel;
import com.apptasticsoftware.rssreader.FeedRecord;
import com.apptasticsoftware.rssreader.Item;

import java.util.Objects;
import java.util.Optional;

public class FeedRecordImpl<I extends Item, C extends Channel> implements FeedRecord<I, C> {
    private final String feedUrl;
    private final C channel;
    private final I item;

    public FeedRecordImpl(String feedUrl, C channel, I item) {
        this.feedUrl = Objects.requireNonNull(feedUrl);
        this.channel = channel;
        this.item = item;
    }

    @Override
    public String getFeedUrl() {
        return feedUrl;
    }

    @Override
    public Optional<C> getChannel() {
        return Optional.ofNullable(channel);
    }

    @Override
    public Optional<I> getItem() {
        return Optional.ofNullable(item);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FeedRecordImpl)) return false;
        FeedRecordImpl<?, ?> that = (FeedRecordImpl<?, ?>) o;
        return Objects.equals(feedUrl, that.feedUrl) &&
               Objects.equals(channel, that.channel) &&
               Objects.equals(item, that.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedUrl, channel, item);
    }
}
