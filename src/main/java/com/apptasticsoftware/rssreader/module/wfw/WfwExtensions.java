package com.apptasticsoftware.rssreader.module.wfw;

import com.apptasticsoftware.rssreader.FeedExtensionRegistry;

/**
 * Utility class for registering Well-Formed Web Comment API (WFW) extensions with the RSS feed parser.
 * This class provides a convenient way to register WFW-specific tags and their handlers with the feed extension registry.
 *
 * <p>The WFW namespace requires a declaration with
 * <a href="http://wellformedweb.org/CommentAPI/">http://wellformedweb.org/CommentAPI/</a> as the URI
 * in the top-level RSS element, typically with the "wfw" namespace identifier.</p>
 *
 * <p>This class is not meant to be instantiated.</p>
 *
 * @see <a href="http://wellformedweb.org/CommentAPI/">Well-Formed Web Comment API</a>
 */
public class WfwExtensions {

    private WfwExtensions() {
        // Prevent instantiation
    }

    /**
     * Registers all WFW channel and item extensions with the provided feed extension registry.
     * This method should be called during feed parser initialization to enable WFW namespace support.
     *
     * @param registry the feed extension registry to register extensions with
     */
    public static void register(FeedExtensionRegistry<? extends WfwChannel, ? extends WfwItem> registry) {
        itemTagExtensions(registry);
    }

    /**
     * Registers WFW item-level tag extensions with the feed extension registry.
     * This method registers handlers for the following WFW elements:
     * <ul>
     *   <li>{@code wfw:commentRss} - URL of an RSS feed containing comments for an item</li>
     *   <li>{@code wfw:commentRSS} - Alternative spelling variant (both spellings are supported for compatibility)</li>
     *   <li>{@code wfw:comment} - URL endpoint for submitting comments for an item</li>
     * </ul>
     *
     * @param registry the feed extension registry to register item extensions with
     */
    private static void itemTagExtensions(FeedExtensionRegistry<? extends WfwChannel, ? extends WfwItem> registry) {
        registry.addItemExtension("wfw:commentRss", WfwItem::setWfwCommentRss);
        registry.addItemExtension("wfw:commentRSS", WfwItem::setWfwCommentRss);
        registry.addItemExtension("wfw:comment", WfwItem::setWfwComment);
    }
}
