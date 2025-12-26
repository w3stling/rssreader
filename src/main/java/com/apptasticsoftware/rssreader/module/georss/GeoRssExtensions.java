package com.apptasticsoftware.rssreader.module.georss;

import com.apptasticsoftware.rssreader.FeedExtensionRegistry;

import java.util.List;

import static com.apptasticsoftware.rssreader.util.Mapper.mapDouble;
import static com.apptasticsoftware.rssreader.util.Mapper.mapInteger;

public class GeoRssExtensions {
    private static final List<String> ITEM_PATHS = List.of("/rss/channel/item/", "/feed/entry/");

    public static void register(FeedExtensionRegistry<? extends GeoRssChannel, ? extends GeoRssItem> registry) {
        itemTagExtensions(registry);
    }

    private static void itemTagExtensions(FeedExtensionRegistry<? extends GeoRssChannel, ? extends GeoRssItem> registry) {
        registry.addItemExtension("georss:point", GeoRssItemData::setGeoRssPoint);
        registry.addItemExtension("georss:line", GeoRssItemData::setGeoRssLine);
        registry.addItemExtension("georss:polygon", GeoRssItemData::setGeoRssPolygon);
        registry.addItemExtension("georss:box", GeoRssItemData::setGeoRssBox);
        registry.addItemExtension("georss:elev", (item, value) -> mapDouble(value, item::setGeoRssElev));
        registry.addItemExtension("georss:floor",  (item, value) -> mapInteger(value, item::setGeoRssFloor));
        registry.addItemExtension("georss:radius", (item, value) -> mapDouble(value, item::setGeoRssRadius));
        registry.addItemExtension("georss:featuretypetag", GeoRssItemData::setGeoRssFeatureTypeTag);
        registry.addItemExtension("georss:relationshiptag", GeoRssItemData::setGeoRssRelationshipTag);
        registry.addItemExtension("georss:featurename", GeoRssItemData::setGeoRssFeatureName);

        registry.addItemExtension(ITEM_PATHS, "georss:where/gml:Point/gml:pos", GeoRssItemData::setGeoRssPoint);
        registry.addItemExtension(ITEM_PATHS, "georss:where/gml:LineString/gml:posList", GeoRssItemData::setGeoRssLine);
        registry.addItemExtension(ITEM_PATHS, "georss:where/gml:Polygon/gml:exterior/gml:LinearRing/gml:posList", GeoRssItemData::setGeoRssPolygon);
        registry.addItemExtension(ITEM_PATHS, "georss:where/gml:Envelope/gml:lowerCorner", GeoRssExtensions::mapEnvelope);
        registry.addItemExtension(ITEM_PATHS, "georss:where/gml:Envelope/gml:upperCorner", GeoRssExtensions::mapEnvelope);
    }

    private static void mapEnvelope(GeoRssItem geoRssItem, String text) {
        var existingValue = geoRssItem.getGeoRssBox().orElse("");
        var newValue = existingValue.trim() + " " + text.trim();
        geoRssItem.setGeoRssBox(newValue);
    }
}
