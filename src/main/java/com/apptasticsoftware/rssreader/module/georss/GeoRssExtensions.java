package com.apptasticsoftware.rssreader.module.georss;

import com.apptasticsoftware.rssreader.FeedExtensionRegistry;

import java.util.List;

import static com.apptasticsoftware.rssreader.util.Mapper.mapDouble;
import static com.apptasticsoftware.rssreader.util.Mapper.mapInteger;

/**
 * Utility class for registering GeoRSS feed extensions.
 */
public class GeoRssExtensions {
    private static final List<String> CHANNEL_PATHS = List.of("/rss/channel/", "/feed/");
    private static final List<String> ITEM_PATHS = List.of("/rss/channel/item/", "/feed/entry/");

    private GeoRssExtensions() {
        // Prevent instantiation
    }

    /**
     * Registers GeoRSS channel and item extensions with the provided registry.
     *
     * @param registry the feed extension registry
     */
    public static void register(FeedExtensionRegistry<? extends GeoRssChannel, ? extends GeoRssItem> registry) {
        channelTagExtensions(registry);
        itemTagExtensions(registry);
    }

    /**
     * Registers channel tag extensions.
     *
     * @param registry the feed extension registry
     */
    private static void channelTagExtensions(FeedExtensionRegistry<? extends GeoRssChannel, ? extends GeoRssItem> registry) {
        registry.addChannelExtension("georss:point", GeoRssChannel::setGeoRssPoint);
        registry.addChannelExtension("georss:line", GeoRssChannel::setGeoRssLine);
        registry.addChannelExtension("georss:polygon", GeoRssChannel::setGeoRssPolygon);
        registry.addChannelExtension("georss:box", GeoRssChannel::setGeoRssBox);
        registry.addChannelExtension("georss:elev", (item, value) -> mapDouble(value, item::setGeoRssElevation));
        registry.addChannelExtension("georss:floor",  (item, value) -> mapInteger(value, item::setGeoRssFloor));
        registry.addChannelExtension("georss:radius", (item, value) -> mapDouble(value, item::setGeoRssRadius));
        registry.addChannelExtension("georss:featuretypetag", GeoRssChannel::setGeoRssFeatureTypeTag);
        registry.addChannelExtension("georss:relationshiptag", GeoRssChannel::setGeoRssRelationshipTag);
        registry.addChannelExtension("georss:featurename", GeoRssChannel::setGeoRssFeatureName);
        registry.addChannelExtension("geo:lat", GeoRssExtensions::mapLatitude);
        registry.addChannelExtension("geo:long", GeoRssExtensions::mapLongitude);

        registry.addChannelExtension(CHANNEL_PATHS, "georss:where/gml:Point/gml:pos", GeoRssChannel::setGeoRssPoint);
        registry.addChannelExtension(CHANNEL_PATHS, "georss:where/gml:LineString/gml:posList", GeoRssChannel::setGeoRssLine);
        registry.addChannelExtension(CHANNEL_PATHS, "georss:where/gml:Polygon/gml:exterior/gml:LinearRing/gml:posList", GeoRssChannel::setGeoRssPolygon);
        registry.addChannelExtension(CHANNEL_PATHS, "georss:where/gml:Envelope/gml:lowerCorner", GeoRssExtensions::mapEnvelope);
        registry.addChannelExtension(CHANNEL_PATHS, "georss:where/gml:Envelope/gml:upperCorner", GeoRssExtensions::mapEnvelope);
    }

    /**
     * Registers item tag extensions.
     *
     * @param registry the feed extension registry
     */
    private static void itemTagExtensions(FeedExtensionRegistry<? extends GeoRssChannel, ? extends GeoRssItem> registry) {
        registry.addItemExtension("georss:point", GeoRssItem::setGeoRssPoint);
        registry.addItemExtension("georss:line", GeoRssItem::setGeoRssLine);
        registry.addItemExtension("georss:polygon", GeoRssItem::setGeoRssPolygon);
        registry.addItemExtension("georss:box", GeoRssItem::setGeoRssBox);
        registry.addItemExtension("georss:elev", (item, value) -> mapDouble(value, item::setGeoRssElevation));
        registry.addItemExtension("georss:floor",  (item, value) -> mapInteger(value, item::setGeoRssFloor));
        registry.addItemExtension("georss:radius", (item, value) -> mapDouble(value, item::setGeoRssRadius));
        registry.addItemExtension("georss:featuretypetag", GeoRssItem::setGeoRssFeatureTypeTag);
        registry.addItemExtension("georss:relationshiptag", GeoRssItem::setGeoRssRelationshipTag);
        registry.addItemExtension("georss:featurename", GeoRssItem::setGeoRssFeatureName);
        registry.addItemExtension("geo:lat", GeoRssExtensions::mapLatitude);
        registry.addItemExtension("geo:long", GeoRssExtensions::mapLongitude);

        registry.addItemExtension(ITEM_PATHS, "georss:where/gml:Point/gml:pos", GeoRssItem::setGeoRssPoint);
        registry.addItemExtension(ITEM_PATHS, "georss:where/gml:LineString/gml:posList", GeoRssItem::setGeoRssLine);
        registry.addItemExtension(ITEM_PATHS, "georss:where/gml:Polygon/gml:exterior/gml:LinearRing/gml:posList", GeoRssItemData::setGeoRssPolygon);
        registry.addItemExtension(ITEM_PATHS, "georss:where/gml:Envelope/gml:lowerCorner", GeoRssExtensions::mapEnvelope);
        registry.addItemExtension(ITEM_PATHS, "georss:where/gml:Envelope/gml:upperCorner", GeoRssExtensions::mapEnvelope);
    }

    /**
     * Maps envelope coordinates to GeoRSS box for channels.
     *
     * @param geoRssChannel the GeoRSS channel
     * @param text the envelope text
     */
    private static void mapEnvelope(GeoRssChannel geoRssChannel, String text) {
        var existingValue = geoRssChannel.getGeoRssBox().orElse("").trim();
        var newValue = existingValue + " " + text.trim();
        geoRssChannel.setGeoRssBox(newValue);
    }

    /**
     * Maps envelope coordinates to GeoRSS box for items.
     *
     * @param geoRssItem the GeoRSS item
     * @param text the envelope text
     */
    private static void mapEnvelope(GeoRssItem geoRssItem, String text) {
        var existingValue = geoRssItem.getGeoRssBox().orElse("").trim();
        var newValue = existingValue + " " + text.trim();
        geoRssItem.setGeoRssBox(newValue);
    }

    /**
     * Maps latitude value to GeoRSS point for channels.
     *
     * @param geoRssItem the GeoRSS channel
     * @param text the latitude text
     */
    private static void mapLatitude(GeoRssChannel geoRssItem, String text) {
        var existingValue = geoRssItem.getGeoRssPoint().orElse("").trim();
        if (!existingValue.isEmpty() && containsWhitespace(existingValue)) {
            return;
        }
        var newValue = existingValue.isEmpty() ? text.trim() : text.trim() + " " + existingValue;
        geoRssItem.setGeoRssPoint(newValue);
    }

    /**
     * Maps longitude value to GeoRSS point for channels.
     *
     * @param geoRssItem the GeoRSS channel
     * @param text the longitude text
     */
    private static void mapLongitude(GeoRssChannel geoRssItem, String text) {
        var existingValue = geoRssItem.getGeoRssPoint().orElse("").trim();
        if (!existingValue.isEmpty() && containsWhitespace(existingValue)) {
            return;
        }
        var newValue = existingValue.isEmpty() ? text.trim() : existingValue + " " + text.trim();
        geoRssItem.setGeoRssPoint(newValue);
    }

    /**
     * Maps latitude value to GeoRSS point for items.
     *
     * @param geoRssItem the GeoRSS item
     * @param text the latitude text
     */
    private static void mapLatitude(GeoRssItem geoRssItem, String text) {
        var existingValue = geoRssItem.getGeoRssPoint().orElse("").trim();
        if (!existingValue.isEmpty() && containsWhitespace(existingValue)) {
            return;
        }
        var newValue = existingValue.isEmpty() ? text.trim() : text.trim() + " " + existingValue;
        geoRssItem.setGeoRssPoint(newValue);
    }

    /**
     * Maps longitude value to GeoRSS point for items.
     *
     * @param geoRssItem the GeoRSS item
     * @param text the longitude text
     */
    private static void mapLongitude(GeoRssItem geoRssItem, String text) {
        var existingValue = geoRssItem.getGeoRssPoint().orElse("").trim();
        if (!existingValue.isEmpty() && containsWhitespace(existingValue)) {
            return;
        }
        var newValue = existingValue.isEmpty() ? text.trim() : existingValue + " " + text.trim();
        geoRssItem.setGeoRssPoint(newValue);
    }

    /**
     * Checks if the specified text contains whitespace characters.
     *
     * @param text the text to check
     * @return true if text contains whitespace, false otherwise
     */
    private static boolean containsWhitespace(String text) {
        if (text == null) {
            return false;
        }
        for (int i = 0; i < text.length(); i++) {
            if (Character.isWhitespace(text.charAt(i))) {
                return true;
            }
        }
        return false;
    }

}
