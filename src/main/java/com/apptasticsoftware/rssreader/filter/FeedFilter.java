package com.apptasticsoftware.rssreader.filter;

import java.io.InputStream;

/**
 * An interface for filtering RSS or Atom feed streams. This filter can
 * modify or clean feed data before it is processed by the feed parser,
 * which maps it to {@link com.apptasticsoftware.rssreader.Item} objects.
 */
public interface FeedFilter {

    /**
     * Filters the provided XML feed stream.
     *
     * @param feedStream the input stream of the feed to be filtered
     * @return a filtered input stream
     */
    InputStream filter(InputStream feedStream);
}
