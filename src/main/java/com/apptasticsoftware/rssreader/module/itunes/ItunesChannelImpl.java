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
package com.apptasticsoftware.rssreader.module.itunes;

import com.apptasticsoftware.rssreader.internal.ChannelImpl;
import com.apptasticsoftware.rssreader.DateTimeParser;

import java.util.*;

/**
 * Class representing the Itunes channel.
 */
public class ItunesChannelImpl extends ChannelImpl implements ItunesChannel {
    private final ItunesChannelDataImpl data = new ItunesChannelDataImpl();

    /**
     * Constructor
     * @param dateTimeParser timestamp parser
     */
    public ItunesChannelImpl(DateTimeParser dateTimeParser) {
        super(dateTimeParser);
    }

    @Override
    public ItunesChannelData getItunesChannelData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ItunesChannelImpl that = (ItunesChannelImpl) o;
        return Objects.equals(getItunesChannelData(), that.getItunesChannelData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getItunesChannelData());
    }
}
