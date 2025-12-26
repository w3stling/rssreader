package com.apptasticsoftware.rssreader.module.georss;

import com.apptasticsoftware.rssreader.FeedExtensionRegistry;

import java.util.List;

import static com.apptasticsoftware.rssreader.util.Mapper.mapDouble;
import static com.apptasticsoftware.rssreader.util.Mapper.mapInteger;

public class GeoRssExtensions {
    private static final List<String> CHANNEL_PATHS = List.of("/rss/channel/", "/feed/");
    private static final List<String> ITEM_PATHS = List.of("/rss/channel/item/", "/feed/entry/");

    public static void register(FeedExtensionRegistry<? extends GeoRssChannel, ? extends GeoRssItem> registry) {
        channelTagExtensions(registry);
        itemTagExtensions(registry);
    }

    private static void channelTagExtensions(FeedExtensionRegistry<? extends GeoRssChannel, ? extends GeoRssItem> registry) {
        registry.addChannelExtension("georss:point", GeoRssChannel::setGeoRssPoint);
        registry.addChannelExtension("georss:line", GeoRssChannel::setGeoRssLine);
        registry.addChannelExtension("georss:polygon", GeoRssChannel::setGeoRssPolygon);
        registry.addChannelExtension("georss:box", GeoRssChannel::setGeoRssBox);
        registry.addChannelExtension("georss:elev", (item, value) -> mapDouble(value, item::setGeoRssElev));
        registry.addChannelExtension("georss:floor",  (item, value) -> mapInteger(value, item::setGeoRssFloor));
        registry.addChannelExtension("georss:radius", (item, value) -> mapDouble(value, item::setGeoRssRadius));
        registry.addChannelExtension("georss:featuretypetag", GeoRssChannel::setGeoRssFeatureTypeTag);
        registry.addChannelExtension("georss:relationshiptag", GeoRssChannel::setGeoRssRelationshipTag);
        registry.addChannelExtension("georss:featurename", GeoRssChannel::setGeoRssFeatureName);

        registry.addChannelExtension(CHANNEL_PATHS, "georss:where/gml:Point/gml:pos", GeoRssChannel::setGeoRssPoint);
        registry.addChannelExtension(CHANNEL_PATHS, "georss:where/gml:LineString/gml:posList", GeoRssChannel::setGeoRssLine);
        registry.addChannelExtension(CHANNEL_PATHS, "georss:where/gml:Polygon/gml:exterior/gml:LinearRing/gml:posList", GeoRssChannel::setGeoRssPolygon);
        registry.addChannelExtension(CHANNEL_PATHS, "georss:where/gml:Envelope/gml:lowerCorner", GeoRssExtensions::mapEnvelope);
        registry.addChannelExtension(CHANNEL_PATHS, "georss:where/gml:Envelope/gml:upperCorner", GeoRssExtensions::mapEnvelope);
    }

    private static void itemTagExtensions(FeedExtensionRegistry<? extends GeoRssChannel, ? extends GeoRssItem> registry) {
        registry.addItemExtension("georss:point", GeoRssItem::setGeoRssPoint);
        registry.addItemExtension("georss:line", GeoRssItem::setGeoRssLine);
        registry.addItemExtension("georss:polygon", GeoRssItem::setGeoRssPolygon);
        registry.addItemExtension("georss:box", GeoRssItem::setGeoRssBox);
        registry.addItemExtension("georss:elev", (item, value) -> mapDouble(value, item::setGeoRssElev));
        registry.addItemExtension("georss:floor",  (item, value) -> mapInteger(value, item::setGeoRssFloor));
        registry.addItemExtension("georss:radius", (item, value) -> mapDouble(value, item::setGeoRssRadius));
        registry.addItemExtension("georss:featuretypetag", GeoRssItem::setGeoRssFeatureTypeTag);
        registry.addItemExtension("georss:relationshiptag", GeoRssItem::setGeoRssRelationshipTag);
        registry.addItemExtension("georss:featurename", GeoRssItem::setGeoRssFeatureName);

        registry.addItemExtension(ITEM_PATHS, "georss:where/gml:Point/gml:pos", GeoRssItem::setGeoRssPoint);
        registry.addItemExtension(ITEM_PATHS, "georss:where/gml:LineString/gml:posList", GeoRssItem::setGeoRssLine);
        registry.addItemExtension(ITEM_PATHS, "georss:where/gml:Polygon/gml:exterior/gml:LinearRing/gml:posList", GeoRssItemData::setGeoRssPolygon);
        registry.addItemExtension(ITEM_PATHS, "georss:where/gml:Envelope/gml:lowerCorner", GeoRssExtensions::mapEnvelope);
        registry.addItemExtension(ITEM_PATHS, "georss:where/gml:Envelope/gml:upperCorner", GeoRssExtensions::mapEnvelope);
    }

    private static void mapEnvelope(GeoRssChannel geoRssChannel, String text) {
        var existingValue = geoRssChannel.getGeoRssBox().orElse("");
        var newValue = existingValue.trim() + " " + text.trim();
        geoRssChannel.setGeoRssBox(newValue);
    }

    private static void mapEnvelope(GeoRssItem geoRssItem, String text) {
        var existingValue = geoRssItem.getGeoRssBox().orElse("");
        var newValue = existingValue.trim() + " " + text.trim();
        geoRssItem.setGeoRssBox(newValue);
    }
}
