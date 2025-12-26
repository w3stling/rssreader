package com.apptasticsoftware.rssreader.module.georss;

import com.apptasticsoftware.rssreader.FeedExtensionRegistry;

import static com.apptasticsoftware.rssreader.util.Mapper.mapDouble;
import static com.apptasticsoftware.rssreader.util.Mapper.mapInteger;

public class GeoRssExtensions {

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
    }
}
