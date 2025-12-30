package com.apptasticsoftware.rssreader.module.dc;

import com.apptasticsoftware.rssreader.FeedExtensionRegistry;

public class DcExtensions {

    private DcExtensions() {
        // Prevent instantiation
    }

    public static void register(FeedExtensionRegistry<? extends DcChannel, ? extends DcItem> registry) {
        channelTagExtensions(registry);
        itemTagExtensions(registry);
    }

    private static void channelTagExtensions(FeedExtensionRegistry<? extends DcChannel, ? extends DcItem> registry) {
        registry.addChannelExtension("dc:title", DcChannel::setDcTitle);
        registry.addChannelExtension("dc:creator", DcChannel::setDcCreator);
        registry.addChannelExtension("dc:subject", DcChannel::setDcSubject);
        registry.addChannelExtension("dc:description", DcChannel::setDcDescription);
        registry.addChannelExtension("dc:publisher", DcChannel::setDcPublisher);
        registry.addChannelExtension("dc:contributor", DcChannel::setDcContributor);
        registry.addChannelExtension("dc:date", DcChannel::setDcDate);
        registry.addChannelExtension("dc:type", DcChannel::setDcType);
        registry.addChannelExtension("dc:format", DcChannel::setDcFormat);
        registry.addChannelExtension("dc:identifier", DcChannel::setDcIdentifier);
        registry.addChannelExtension("dc:source", DcChannel::setDcSource);
        registry.addChannelExtension("dc:language", DcChannel::setDcLanguage);
        registry.addChannelExtension("dc:relation", DcChannel::setDcRelation);
        registry.addChannelExtension("dc:coverage", DcChannel::setDcCoverage);
        registry.addChannelExtension("dc:rights", DcChannel::setDcRights);
    }

    private static void itemTagExtensions(FeedExtensionRegistry<? extends DcChannel, ? extends DcItem> registry) {
        registry.addItemExtension("dc:title", DcItem::setDcTitle);
        registry.addItemExtension("dc:creator", DcItem::setDcCreator);
        registry.addItemExtension("dc:subject", DcItem::setDcSubject);
        registry.addItemExtension("dc:description", DcItem::setDcDescription);
        registry.addItemExtension("dc:publisher", DcItem::setDcPublisher);
        registry.addItemExtension("dc:contributor", DcItem::setDcContributor);
        registry.addItemExtension("dc:date", DcItem::setDcDate);
        registry.addItemExtension("dc:type", DcItem::setDcType);
        registry.addItemExtension("dc:format", DcItem::setDcFormat);
        registry.addItemExtension("dc:identifier", DcItem::setDcIdentifier);
        registry.addItemExtension("dc:source", DcItem::setDcSource);
        registry.addItemExtension("dc:language", DcItem::setDcLanguage);
        registry.addItemExtension("dc:relation", DcItem::setDcRelation);
        registry.addItemExtension("dc:coverage", DcItem::setDcCoverage);
        registry.addItemExtension("dc:rights", DcItem::setDcRights);
    }
}
