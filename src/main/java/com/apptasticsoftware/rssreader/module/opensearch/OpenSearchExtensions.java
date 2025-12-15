package com.apptasticsoftware.rssreader.module.opensearch;

import com.apptasticsoftware.rssreader.FeedExtensionRegistry;

import java.util.List;
import java.util.Optional;

import static com.apptasticsoftware.rssreader.util.Mapper.mapInteger;
import static com.apptasticsoftware.rssreader.util.Util.getLast;

public class OpenSearchExtensions {
    private static final List<String> CHANNEL_PATHS = List.of("/rss/channel/", "/feed/");
    private static final String QUERY_TAG = "opensearch:Query";
    private static final String QUERY_TAG_OTHER = "opensearch:query";

    private OpenSearchExtensions() {

    }

    public static void register(FeedExtensionRegistry<? extends OpenSearchChannel, ? extends OpenSearchItem> registry) {
        onChannelTag(registry);
        channelTagExtensions(registry);
        channelAttributeExtensions(registry);
    }

    private static void onChannelTag(FeedExtensionRegistry<? extends OpenSearchChannel, ? extends OpenSearchItem> registry) {
        registry.addOnChannelTag(QUERY_TAG, channel -> channel.addOpenSearchQuery(new OpenSearchQuery()));
        registry.addOnChannelTag(QUERY_TAG_OTHER, channel -> channel.addOpenSearchQuery(new OpenSearchQuery()));
    }

    private static void channelTagExtensions(FeedExtensionRegistry<? extends OpenSearchChannel, ? extends OpenSearchItem> registry) {
        registry.addChannelExtension(CHANNEL_PATHS, "opensearch:totalResults", (c, v) -> mapInteger(v, c::setOpenSearchTotalResults));
        registry.addChannelExtension(CHANNEL_PATHS, "opensearch:startIndex", (c, v) -> mapInteger(v, c::setOpenSearchStartIndex));
        registry.addChannelExtension(CHANNEL_PATHS, "opensearch:itemsPerPage", (c, v) -> mapInteger(v, c::setOpenSearchItemsPerPage));
    }

    private static void channelAttributeExtensions(FeedExtensionRegistry<? extends OpenSearchChannel, ? extends OpenSearchItem> registry) {
        for (var tag : List.of(QUERY_TAG, QUERY_TAG_OTHER)) {
            registry.addChannelExtension(CHANNEL_PATHS, tag, "role", (c, v) -> getLast(c.getOpenSearchQueries()).setRole(v));
            registry.addChannelExtension(CHANNEL_PATHS, tag, "title", (c, v) -> getLast(c.getOpenSearchQueries()).setTitle(v));
            registry.addChannelExtension(CHANNEL_PATHS, tag, "totalResults", (c, v) -> Optional.ofNullable(getLast(c.getOpenSearchQueries())).ifPresent(query -> mapInteger(v, query::setTotalResults)));
            registry.addChannelExtension(CHANNEL_PATHS, tag, "searchTerms", (c, v) -> getLast(c.getOpenSearchQueries()).setSearchTerms(v));
            registry.addChannelExtension(CHANNEL_PATHS, tag, "count", (c, v) -> Optional.ofNullable(getLast(c.getOpenSearchQueries())).ifPresent(query -> mapInteger(v, query::setCount)));
            registry.addChannelExtension(CHANNEL_PATHS, tag, "startIndex", (c, v) -> Optional.ofNullable(getLast(c.getOpenSearchQueries())).ifPresent(query -> mapInteger(v, query::setStartIndex)));
            registry.addChannelExtension(CHANNEL_PATHS, tag, "startPage", (c, v) -> Optional.ofNullable(getLast(c.getOpenSearchQueries())).ifPresent(query -> mapInteger(v, query::setStartPage)));
            registry.addChannelExtension(CHANNEL_PATHS, tag, "language", (c, v) -> getLast(c.getOpenSearchQueries()).setLanguage(v));
            registry.addChannelExtension(CHANNEL_PATHS, tag, "inputEncoding", (c, v) -> getLast(c.getOpenSearchQueries()).setInputEncoding(v));
            registry.addChannelExtension(CHANNEL_PATHS, tag, "outputEncoding", (c, v) -> getLast(c.getOpenSearchQueries()).setOutputEncoding(v));
        }
    }
}
