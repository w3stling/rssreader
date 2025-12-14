package com.apptasticsoftware.rssreader.module.opensearch;

import com.apptasticsoftware.rssreader.FeedExtensionRegistry;

import java.util.List;
import java.util.Optional;

import static com.apptasticsoftware.rssreader.util.Mapper.mapInteger;
import static com.apptasticsoftware.rssreader.util.Util.getLast;

public class OpenSearchExtensions {
    private static final List<String> CHANNEL_PATHS = List.of("/rss/channel/", "/feed/");

    private OpenSearchExtensions() {

    }

    public static void register(FeedExtensionRegistry<? extends OpenSearchChannel, ? extends OpenSearchItem> registry) {
        onChannelTag(registry);
        channelTagExtensions(registry);
        channelAttributeExtensions(registry);
    }

    private static void onChannelTag(FeedExtensionRegistry<? extends OpenSearchChannel, ? extends OpenSearchItem> registry) {
        registry.addOnChannelTag("opensearch:Query", channel -> channel.addOpenSearchQuery(new OpenSearchQuery()));
        registry.addOnChannelTag("opensearch:query", channel -> channel.addOpenSearchQuery(new OpenSearchQuery()));
    }

    private static void channelTagExtensions(FeedExtensionRegistry<? extends OpenSearchChannel, ? extends OpenSearchItem> registry) {
        registry.addChannelExtension(CHANNEL_PATHS, "opensearch:totalResults", (c, v) -> mapInteger(v, c::setOpenSearchTotalResults));
        registry.addChannelExtension(CHANNEL_PATHS, "opensearch:startIndex", (c, v) -> mapInteger(v, c::setOpenSearchStartIndex));
        registry.addChannelExtension(CHANNEL_PATHS, "opensearch:itemsPerPage", (c, v) -> mapInteger(v, c::setOpenSearchItemsPerPage));
    }

    private static void channelAttributeExtensions(FeedExtensionRegistry<? extends OpenSearchChannel, ? extends OpenSearchItem> registry) {
        registry.addChannelExtension(CHANNEL_PATHS, "opensearch:Query", "role", (c, v) -> getLast(c.getOpenSearchQueries()).setRole(v));
        registry.addChannelExtension(CHANNEL_PATHS, "opensearch:Query", "title", (c, v) -> getLast(c.getOpenSearchQueries()).setTitle(v));
        registry.addChannelExtension(CHANNEL_PATHS, "opensearch:Query", "totalResults", (c, v) -> Optional.ofNullable(getLast(c.getOpenSearchQueries())).ifPresent(query -> mapInteger(v, query::setTotalResults)));
        registry.addChannelExtension(CHANNEL_PATHS, "opensearch:Query", "searchTerms", (c, v) -> getLast(c.getOpenSearchQueries()).setSearchTerms(v));
        registry.addChannelExtension(CHANNEL_PATHS, "opensearch:Query", "count", (c, v) -> Optional.ofNullable(getLast(c.getOpenSearchQueries())).ifPresent(query -> mapInteger(v, query::setCount)));
        registry.addChannelExtension(CHANNEL_PATHS, "opensearch:Query", "startIndex", (c, v) -> Optional.ofNullable(getLast(c.getOpenSearchQueries())).ifPresent(query -> mapInteger(v, query::setStartIndex)));
        registry.addChannelExtension(CHANNEL_PATHS, "opensearch:Query", "startPage", (c, v) -> Optional.ofNullable(getLast(c.getOpenSearchQueries())).ifPresent(query -> mapInteger(v, query::setStartPage)));
        registry.addChannelExtension(CHANNEL_PATHS, "opensearch:Query", "language", (c, v) -> getLast(c.getOpenSearchQueries()).setLanguage(v));
        registry.addChannelExtension(CHANNEL_PATHS, "opensearch:Query", "inputEncoding", (c, v) -> getLast(c.getOpenSearchQueries()).setInputEncoding(v));
        registry.addChannelExtension(CHANNEL_PATHS, "opensearch:Query", "outputEncoding", (c, v) -> getLast(c.getOpenSearchQueries()).setOutputEncoding(v));

        registry.addChannelExtension(CHANNEL_PATHS, "opensearch:query", "role", (c, v) -> getLast(c.getOpenSearchQueries()).setRole(v));
        registry.addChannelExtension(CHANNEL_PATHS, "opensearch:query", "title", (c, v) -> getLast(c.getOpenSearchQueries()).setTitle(v));
        registry.addChannelExtension(CHANNEL_PATHS, "opensearch:query", "totalResults", (c, v) -> Optional.ofNullable(getLast(c.getOpenSearchQueries())).ifPresent(query -> mapInteger(v, query::setTotalResults)));
        registry.addChannelExtension(CHANNEL_PATHS, "opensearch:query", "searchTerms", (c, v) -> getLast(c.getOpenSearchQueries()).setSearchTerms(v));
        registry.addChannelExtension(CHANNEL_PATHS, "opensearch:query", "count", (c, v) -> Optional.ofNullable(getLast(c.getOpenSearchQueries())).ifPresent(query -> mapInteger(v, query::setCount)));
        registry.addChannelExtension(CHANNEL_PATHS, "opensearch:query", "startIndex", (c, v) -> Optional.ofNullable(getLast(c.getOpenSearchQueries())).ifPresent(query -> mapInteger(v, query::setStartIndex)));
        registry.addChannelExtension(CHANNEL_PATHS, "opensearch:query", "startPage", (c, v) -> Optional.ofNullable(getLast(c.getOpenSearchQueries())).ifPresent(query -> mapInteger(v, query::setStartPage)));
        registry.addChannelExtension(CHANNEL_PATHS, "opensearch:query", "language", (c, v) -> getLast(c.getOpenSearchQueries()).setLanguage(v));
        registry.addChannelExtension(CHANNEL_PATHS, "opensearch:query", "inputEncoding", (c, v) -> getLast(c.getOpenSearchQueries()).setInputEncoding(v));
        registry.addChannelExtension(CHANNEL_PATHS, "opensearch:query", "outputEncoding", (c, v) -> getLast(c.getOpenSearchQueries()).setOutputEncoding(v));
    }
}
