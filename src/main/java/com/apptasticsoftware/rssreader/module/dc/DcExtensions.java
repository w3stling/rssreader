package com.apptasticsoftware.rssreader.module.dc;

import com.apptasticsoftware.rssreader.FeedExtensionRegistry;
import com.apptasticsoftware.rssreader.util.Mapper;

public class DcExtensions {

    private DcExtensions() {
        // Prevent instantiation
    }

    public static void register(FeedExtensionRegistry<? extends DcChannel, ? extends DcItem> registry) {
        channelTagExtensions(registry);
        itemTagExtensions(registry);
    }

    private static void channelTagExtensions(FeedExtensionRegistry<? extends DcChannel, ? extends DcItem> registry) {
        registry.addChannelExtension("dc:title", (channel, value) -> { channel.setDcTitle(value); Mapper.mapIfEmpty(value, channel::getTitle, channel::setTitle); });
        registry.addChannelExtension("dc:creator", (channel, value) -> { channel.setDcCreator(value); Mapper.mapIfEmpty(value, channel::getManagingEditor, channel::setManagingEditor); });
        registry.addChannelExtension("dc:subject", DcChannel::setDcSubject);
        registry.addChannelExtension("dc:description", DcChannel::setDcDescription);
        registry.addChannelExtension("dc:publisher", DcChannel::setDcPublisher);
        registry.addChannelExtension("dc:contributor", DcChannel::setDcContributor);
        registry.addChannelExtension("dc:date", (channel, value) -> { channel.setDcDate(value); Mapper.mapIfEmpty(value, channel::getPubDate, channel::setPubDate); });
        registry.addChannelExtension("dc:type", DcChannel::setDcType);
        registry.addChannelExtension("dc:format", DcChannel::setDcFormat);
        registry.addChannelExtension("dc:identifier", DcChannel::setDcIdentifier);
        registry.addChannelExtension("dc:source", DcChannel::setDcSource);
        registry.addChannelExtension("dc:language", (channel, value) -> { channel.setDcLanguage(value); Mapper.mapIfEmpty(value, channel::getLanguage, channel::setLanguage); });
        registry.addChannelExtension("dc:relation", DcChannel::setDcRelation);
        registry.addChannelExtension("dc:coverage", DcChannel::setDcCoverage);
        registry.addChannelExtension("dc:rights", (channel, value) -> { channel.setDcRights(value); Mapper.mapIfEmpty(value, channel::getCopyright, channel::setCopyright); });
    }

    private static void itemTagExtensions(FeedExtensionRegistry<? extends DcChannel, ? extends DcItem> registry) {
        registry.addItemExtension("dc:title", (item, value) -> { item.setDcTitle(value); Mapper.mapIfEmpty(value, item::getTitle, item::setTitle); });
        registry.addItemExtension("dc:creator", (item, value) -> { item.setDcCreator(value); Mapper.mapIfEmpty(value, item::getAuthor, item::setAuthor); });
        registry.addItemExtension("dc:subject", (item, value) -> { item.setDcSubject(value); item.addCategory(value); } );
        registry.addItemExtension("dc:description", (item, value) -> { item.setDcDescription(value); Mapper.mapIfEmpty(value, item::getDescription, item::setDescription); });
        registry.addItemExtension("dc:publisher", DcItem::setDcPublisher);
        registry.addItemExtension("dc:contributor", DcItem::setDcContributor);
        registry.addItemExtension("dc:date", (item, value) -> { item.setDcDate(value); Mapper.mapIfEmpty(value, item::getPubDate, item::setPubDate); });
        registry.addItemExtension("dc:type", DcItem::setDcType);
        registry.addItemExtension("dc:format", DcItem::setDcFormat);
        registry.addItemExtension("dc:identifier", (item, value) -> { item.setDcIdentifier(value); Mapper.mapIfEmpty(value, item::getGuid, item::setGuid); });
        registry.addItemExtension("dc:source", DcItem::setDcSource);
        registry.addItemExtension("dc:language", DcItem::setDcLanguage);
        registry.addItemExtension("dc:relation", DcItem::setDcRelation);
        registry.addItemExtension("dc:coverage", DcItem::setDcCoverage);
        registry.addItemExtension("dc:rights", DcItem::setDcRights);
    }
}
