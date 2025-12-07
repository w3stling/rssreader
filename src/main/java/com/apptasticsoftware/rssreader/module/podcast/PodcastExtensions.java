package com.apptasticsoftware.rssreader.module.podcast;

import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.FeedExtensionRegistry;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static com.apptasticsoftware.rssreader.util.Mapper.*;
import static com.apptasticsoftware.rssreader.util.Mapper.createIfNullOptional;
import static com.apptasticsoftware.rssreader.util.Mapper.mapBoolean;

public class PodcastExtensions {

    public static void register(FeedExtensionRegistry<? extends PodcastChannel, ? extends PodcastItem> registry, DateTimeParser dateTimeParser) {
        onChannelTag(registry, dateTimeParser);
        channelTagExtensions(registry);
        channelAttributeExtensions(registry);

        onItemTag(registry);
        itemTagExtensions(registry);
        itemAttributesExtensions(registry);
    }

    private static void onChannelTag(FeedExtensionRegistry<? extends PodcastChannel, ? extends PodcastItem> registry, DateTimeParser dateTimeParser) {
        registry.addOnChannelTag("podcast:block", channel -> channel.addPodcastBlock(new PodcastBlock()));
        registry.addOnChannelTag("podcast:funding", channel -> channel.addPodcastFunding(new PodcastFunding()));
        registry.addOnChannelTag("podcast:location", channel -> channel.addPodcastLocation(new PodcastLocation()));
        registry.addOnChannelTag("podcast:value", channel -> channel.addPodcastValue(new PodcastValue()));
        registry.addOnChannelTag("/rss/channel/podcast:value/podcast:valueRecipient", channel -> channel.getPodcastValues().getLast().addValueRecipient(new PodcastValueRecipient()));
        registry.addOnChannelTag("podcast:valueTimeSplit", channel -> channel.getPodcastValues().getLast().addValueTimeSplit(new PodcastValueTimeSplit()));
        registry.addOnChannelTag("/rss/channel/podcast:value/podcast:valueTimeSplit/podcast:valueRecipient", channel -> channel.getPodcastValues().getLast().getValueTimeSplits().getLast().addValueRecipient(new PodcastValueRecipient()));
        registry.addOnChannelTag("/rss/channel/podcast:podroll/podcast:remoteItem", channel -> channel.addPodcastPodroll(new PodcastRemoteItem()));
        registry.addOnChannelTag("podcast:person", channel ->channel.addPodcastPerson(new PodcastPerson()));
        registry.addOnChannelTag("podcast:trailer", channel -> channel.addPodcastTrailer(new PodcastTrailer(dateTimeParser)));
        registry.addOnChannelTag("podcast:image", channel -> channel.addPodcastImage(new PodcastImage()));
        registry.addOnChannelTag("podcast:txt", channel -> channel.addPodcastTxt(new PodcastTxt()));
    }

    private static void channelTagExtensions(FeedExtensionRegistry<? extends PodcastChannel, ? extends PodcastItem> registry) {
        registry.addChannelExtension("podcast:guid", PodcastChannel::setPodcastGuid);
        registry.addChannelExtension("podcast:license", (channel, value) -> createIfNull(((PodcastChannel)channel)::getPodcastLicense, channel::setPodcastLicense, PodcastLicense::new).setLicense(value));
        registry.addChannelExtension("podcast:locked", (channel, value) -> createIfNullOptional(channel::getPodcastLocked, channel::setPodcastLocked, PodcastLocked::new).ifPresent(locked -> mapBoolean(value, locked::setLocked)));
        registry.addChannelExtension("podcast:block", (channel, value) -> Optional.ofNullable(channel.getPodcastBlocks().getLast()).ifPresent(block -> mapBoolean(value, block::setBlock)));
        registry.addChannelExtension("podcast:funding", (channel, value) -> Optional.ofNullable(channel.getPodcastFundings().getLast()).ifPresent(funding -> funding.setFunding(value)));
        registry.addChannelExtension("podcast:location", (channel, value) -> Optional.ofNullable(channel.getPodcastLocations().getLast()).ifPresent(location -> location.setLocation(value)));
        registry.addChannelExtension("podcast:medium", PodcastChannel::setPodcastMedium);
        registry.addChannelExtension("podcast:person", (channel, value) -> channel.getPodcastPersons().getLast().setPerson(value));
        registry.addChannelExtension("podcast:trailer", (channel, value) -> Optional.ofNullable(channel.getPodcastTrailers().getLast()).ifPresent(trailer -> trailer.setTrailer(value)));
        registry.addChannelExtension("podcast:updateFrequency", (channel, value) -> createIfNullOptional(channel::getPodcastUpdateFrequency, channel::setPodcastUpdateFrequency, PodcastUpdateFrequency::new).ifPresent(updateFrequency -> updateFrequency.setUpdateFrequency(value)));
        registry.addChannelExtension("podcast:txt", (channel, value) -> channel.getPodcastTxts().getLast().setTxt(value));
    }

    private static void channelAttributeExtensions(FeedExtensionRegistry<? extends PodcastChannel, ? extends PodcastItem> registry) {
        registry.addChannelExtension("podcast:license", "url", (channel, value) -> createIfNull(channel::getPodcastLicense, channel::setPodcastLicense, PodcastLicense::new).setUrl(value));
        registry.addChannelExtension("podcast:locked", "owner", (channel, value) -> createIfNullOptional(channel::getPodcastLocked, channel::setPodcastLocked, PodcastLocked::new).ifPresent(locked -> locked.setOwner(value)));
        registry.addChannelExtension("podcast:block", "id", (channel, value) -> Optional.ofNullable(channel.getPodcastBlocks().getLast()).ifPresent(block -> block.setId(value)));
        registry.addChannelExtension("podcast:funding", "url", (channel, value) -> Optional.ofNullable(channel.getPodcastFundings().getLast()).ifPresent(funding -> funding.setUrl(value)));
        registry.addChannelExtension("podcast:location", "rel", (channel, value) -> Optional.ofNullable(channel.getPodcastLocations().getLast()).ifPresent(location -> location.setRel(value)));
        registry.addChannelExtension("podcast:location", "geo", (channel, value) -> Optional.ofNullable(channel.getPodcastLocations().getLast()).ifPresent(location -> location.setGeo(value)));
        registry.addChannelExtension("podcast:location", "osm", (channel, value) -> Optional.ofNullable(channel.getPodcastLocations().getLast()).ifPresent(location -> location.setOsm(value)));
        registry.addChannelExtension("podcast:location", "country", (channel, value) -> Optional.ofNullable(channel.getPodcastLocations().getLast()).ifPresent(location -> location.setCountry(value)));
        registry.addChannelExtension("podcast:value", "type", (channel, value) -> Optional.ofNullable(channel.getPodcastValues().getLast()).ifPresent(podcastValue -> podcastValue.setType(value)));
        registry.addChannelExtension("podcast:value", "method", (channel, value) -> Optional.ofNullable(channel.getPodcastValues().getLast()).ifPresent(podcastValue -> podcastValue.setMethod(value)));
        registry.addChannelExtension("podcast:value", "suggested", (channel, value) -> Optional.ofNullable(channel.getPodcastValues().getLast()).ifPresent(podcastValue -> mapDouble(value, podcastValue::setSuggested)));
        registry.addChannelExtension("podcast:trailer", "pubdate", (channel, value) -> Optional.ofNullable(channel.getPodcastTrailers().getLast()).ifPresent(trailer -> trailer.setPubDate(value)));
        registry.addChannelExtension("podcast:trailer", "url", (channel, value) -> Optional.ofNullable(channel.getPodcastTrailers().getLast()).ifPresent(trailer -> trailer.setUrl(value)));
        registry.addChannelExtension("podcast:trailer", "type", (channel, value) -> Optional.ofNullable(channel.getPodcastTrailers().getLast()).ifPresent(trailer -> trailer.setType(value)));
        registry.addChannelExtension("podcast:trailer", "length", (channel, value) -> Optional.ofNullable(channel.getPodcastTrailers().getLast()).ifPresent(trailer -> mapLong(value, trailer::setLength)));
        registry.addChannelExtension("podcast:trailer", "season", (channel, value) -> Optional.ofNullable(channel.getPodcastTrailers().getLast()).ifPresent(trailer -> mapInteger(value, trailer::setSeason)));
        registry.addChannelExtension("/rss/channel/podcast:value/podcast:valueRecipient", "name", (channel, value) -> Optional.ofNullable(channel.getPodcastValues().getLast().getValueRecipients().getLast()).ifPresent(valueRecipient -> valueRecipient.setName(value)));
        registry.addChannelExtension("/rss/channel/podcast:value/podcast:valueRecipient", "type", (channel, value) -> Optional.ofNullable(channel.getPodcastValues().getLast().getValueRecipients().getLast()).ifPresent(valueRecipient -> valueRecipient.setType(value)));
        registry.addChannelExtension("/rss/channel/podcast:value/podcast:valueRecipient", "address", (channel, value) -> Optional.ofNullable(channel.getPodcastValues().getLast().getValueRecipients().getLast()).ifPresent(valueRecipient -> valueRecipient.setAddress(value)));
        registry.addChannelExtension("/rss/channel/podcast:value/podcast:valueRecipient", "customKey", (channel, value) -> Optional.ofNullable(channel.getPodcastValues().getLast().getValueRecipients().getLast()).ifPresent(valueRecipient -> valueRecipient.setCustomKey(value)));
        registry.addChannelExtension("/rss/channel/podcast:value/podcast:valueRecipient", "customValue", (channel, value) -> Optional.ofNullable(channel.getPodcastValues().getLast().getValueRecipients().getLast()).ifPresent(valueRecipient -> valueRecipient.setCustomValue(value)));
        registry.addChannelExtension("/rss/channel/podcast:value/podcast:valueRecipient", "split", (channel, value) -> Optional.ofNullable(channel.getPodcastValues().getLast().getValueRecipients().getLast()).ifPresent(valueRecipient -> mapInteger(value, valueRecipient::setSplit)));
        registry.addChannelExtension("/rss/channel/podcast:value/podcast:valueRecipient", "fee", (channel, value) -> Optional.ofNullable(channel.getPodcastValues().getLast().getValueRecipients().getLast()).ifPresent(valueRecipient -> mapBoolean(value, valueRecipient::setFee)));
        registry.addChannelExtension("podcast:valueTimeSplit", "startTime", (channel, value) -> Optional.ofNullable(channel.getPodcastValues().getLast().getValueTimeSplits().getLast()).ifPresent(valueRecipient -> mapInteger(value, valueRecipient::setStartTime)));
        registry.addChannelExtension("podcast:valueTimeSplit", "duration", (channel, value) -> Optional.ofNullable(channel.getPodcastValues().getLast().getValueTimeSplits().getLast()).ifPresent(valueRecipient -> mapInteger(value, valueRecipient::setDuration)));
        registry.addChannelExtension("podcast:valueTimeSplit", "remoteStartTime", (channel, value) -> Optional.ofNullable(channel.getPodcastValues().getLast().getValueTimeSplits().getLast()).ifPresent(valueRecipient -> mapInteger(value, valueRecipient::setRemoteStartTime)));
        registry.addChannelExtension("podcast:valueTimeSplit", "remotePercentage", (channel, value) -> Optional.ofNullable(channel.getPodcastValues().getLast().getValueTimeSplits().getLast()).ifPresent(valueRecipient -> mapInteger(value, valueRecipient::setRemotePercentage)));
        registry.addChannelExtension("/rss/channel/podcast:value/podcast:valueTimeSplit/podcast:valueRecipient", "name", (channel, value) -> Optional.ofNullable(channel.getPodcastValues().getLast().getValueTimeSplits().getLast().getValueRecipients().getLast()).ifPresent(valueRecipient -> valueRecipient.setName(value)));
        registry.addChannelExtension("/rss/channel/podcast:value/podcast:valueTimeSplit/podcast:valueRecipient", "type", (channel, value) -> Optional.ofNullable(channel.getPodcastValues().getLast().getValueTimeSplits().getLast().getValueRecipients().getLast()).ifPresent(valueRecipient -> valueRecipient.setType(value)));
        registry.addChannelExtension("/rss/channel/podcast:value/podcast:valueTimeSplit/podcast:valueRecipient", "address", (channel, value) -> Optional.ofNullable(channel.getPodcastValues().getLast().getValueTimeSplits().getLast().getValueRecipients().getLast()).ifPresent(valueRecipient -> valueRecipient.setAddress(value)));
        registry.addChannelExtension("/rss/channel/podcast:value/podcast:valueTimeSplit/podcast:valueRecipient", "customKey", (channel, value) -> Optional.ofNullable(channel.getPodcastValues().getLast().getValueTimeSplits().getLast().getValueRecipients().getLast()).ifPresent(valueRecipient -> valueRecipient.setCustomKey(value)));
        registry.addChannelExtension("/rss/channel/podcast:value/podcast:valueTimeSplit/podcast:valueRecipient", "customValue", (channel, value) -> Optional.ofNullable(channel.getPodcastValues().getLast().getValueTimeSplits().getLast().getValueRecipients().getLast()).ifPresent(valueRecipient -> valueRecipient.setCustomValue(value)));
        registry.addChannelExtension("/rss/channel/podcast:value/podcast:valueTimeSplit/podcast:valueRecipient", "split", (channel, value) -> Optional.ofNullable(channel.getPodcastValues().getLast().getValueTimeSplits().getLast().getValueRecipients().getLast()).ifPresent(valueRecipient -> mapInteger(value, valueRecipient::setSplit)));
        registry.addChannelExtension("/rss/channel/podcast:value/podcast:valueTimeSplit/podcast:valueRecipient", "fee", (channel, value) -> Optional.ofNullable(channel.getPodcastValues().getLast().getValueTimeSplits().getLast().getValueRecipients().getLast()).ifPresent(valueRecipient -> mapBoolean(value, valueRecipient::setFee)));
        registry.addChannelExtension("/rss/channel/podcast:value/podcast:valueTimeSplit/podcast:remoteItem", "feedGuid", (channel, value) -> channelPodcastValuePodcastValueTimeSplitPodcastRemoteItem(channel, value, PodcastRemoteItem::setFeedGuid));
        registry.addChannelExtension("/rss/channel/podcast:value/podcast:valueTimeSplit/podcast:remoteItem", "feedUrl", (channel, value) -> channelPodcastValuePodcastValueTimeSplitPodcastRemoteItem(channel, value, PodcastRemoteItem::setFeedUrl));
        registry.addChannelExtension("/rss/channel/podcast:value/podcast:valueTimeSplit/podcast:remoteItem", "itemGuid", (channel, value) -> channelPodcastValuePodcastValueTimeSplitPodcastRemoteItem(channel, value, PodcastRemoteItem::setItemGuid));
        registry.addChannelExtension("/rss/channel/podcast:value/podcast:valueTimeSplit/podcast:remoteItem", "medium", (channel, value) -> channelPodcastValuePodcastValueTimeSplitPodcastRemoteItem(channel, value, PodcastRemoteItem::setMedium));
        registry.addChannelExtension("/rss/channel/podcast:value/podcast:valueTimeSplit/podcast:remoteItem", "title", (channel, value) -> channelPodcastValuePodcastValueTimeSplitPodcastRemoteItem(channel, value, PodcastRemoteItem::setTitle));
        registry.addChannelExtension("/rss/channel/podcast:podroll/podcast:remoteItem", "feedGuid", (channel, value) -> channelPodcastPodrollPodcastRemoteItem(channel, value, PodcastRemoteItem::setFeedGuid));
        registry.addChannelExtension("/rss/channel/podcast:podroll/podcast:remoteItem", "feedUrl", (channel, value) -> channelPodcastPodrollPodcastRemoteItem(channel, value, PodcastRemoteItem::setFeedUrl));
        registry.addChannelExtension("/rss/channel/podcast:podroll/podcast:remoteItem", "itemGuid", (channel, value) -> channelPodcastPodrollPodcastRemoteItem(channel, value, PodcastRemoteItem::setItemGuid));
        registry.addChannelExtension("/rss/channel/podcast:podroll/podcast:remoteItem", "medium", (channel, value) -> channelPodcastPodrollPodcastRemoteItem(channel, value, PodcastRemoteItem::setMedium));
        registry.addChannelExtension("/rss/channel/podcast:podroll/podcast:remoteItem", "title", (channel, value) -> channelPodcastPodrollPodcastRemoteItem(channel, value, PodcastRemoteItem::setTitle));
        registry.addChannelExtension("/rss/channel/podcast:publisher/podcast:remoteItem", "feedGuid", (channel, value) -> channelPodcastPublisherItemPodcastRemoteItem(channel, value, PodcastRemoteItem::setFeedGuid));
        registry.addChannelExtension("/rss/channel/podcast:publisher/podcast:remoteItem", "feedUrl", (channel, value) -> channelPodcastPublisherItemPodcastRemoteItem(channel, value, PodcastRemoteItem::setFeedUrl));
        registry.addChannelExtension("/rss/channel/podcast:publisher/podcast:remoteItem", "itemGuid", (channel, value) -> channelPodcastPublisherItemPodcastRemoteItem(channel, value, PodcastRemoteItem::setItemGuid));
        registry.addChannelExtension("/rss/channel/podcast:publisher/podcast:remoteItem", "medium", (channel, value) -> channelPodcastPublisherItemPodcastRemoteItem(channel, value, PodcastRemoteItem::setMedium));
        registry.addChannelExtension("/rss/channel/podcast:publisher/podcast:remoteItem", "title", (channel, value) -> channelPodcastPublisherItemPodcastRemoteItem(channel, value, PodcastRemoteItem::setTitle));
        registry.addChannelExtension("podcast:person", "role", (channel, value) -> channel.getPodcastPersons().getLast().setRole(value));
        registry.addChannelExtension("podcast:person", "group", (channel, value) -> channel.getPodcastPersons().getLast().setGroup(value));
        registry.addChannelExtension("podcast:person", "img", (channel, value) -> channel.getPodcastPersons().getLast().setImg(value));
        registry.addChannelExtension("podcast:person", "href", (channel, value) -> channel.getPodcastPersons().getLast().setHref(value));
        registry.addChannelExtension("podcast:updateFrequency", "rrule", (channel, value) -> createIfNullOptional(channel::getPodcastUpdateFrequency, channel::setPodcastUpdateFrequency, PodcastUpdateFrequency::new).ifPresent(updateFrequency -> updateFrequency.setRrule(value)));
        registry.addChannelExtension("podcast:updateFrequency", "complete", (channel, value) -> createIfNullOptional(channel::getPodcastUpdateFrequency, channel::setPodcastUpdateFrequency, PodcastUpdateFrequency::new).ifPresent(updateFrequency -> mapBoolean(value, updateFrequency::setComplete)));
        registry.addChannelExtension("podcast:updateFrequency", "dtstart", (channel, value) -> createIfNullOptional(channel::getPodcastUpdateFrequency, channel::setPodcastUpdateFrequency, PodcastUpdateFrequency::new).ifPresent(updateFrequency -> updateFrequency.setDtstart(value)));
        registry.addChannelExtension("podcast:chat", "server", (channel, value) -> createIfNullOptional(channel::getPodcastChat, channel::setPodcastChat, PodcastChat::new).ifPresent(podcastChat -> podcastChat.setServer(value)));
        registry.addChannelExtension("podcast:chat", "protocol", (channel, value) -> createIfNullOptional(channel::getPodcastChat, channel::setPodcastChat, PodcastChat::new).ifPresent(podcastChat -> podcastChat.setProtocol(value)));
        registry.addChannelExtension("podcast:chat", "accountId", (channel, value) -> createIfNullOptional(channel::getPodcastChat, channel::setPodcastChat, PodcastChat::new).ifPresent(podcastChat -> podcastChat.setAccountId(value)));
        registry.addChannelExtension("podcast:chat", "space", (channel, value) -> createIfNullOptional(channel::getPodcastChat, channel::setPodcastChat, PodcastChat::new).ifPresent(podcastChat -> podcastChat.setSpace(value)));
        registry.addChannelExtension("podcast:txt", "purpose", (channel, value) -> channel.getPodcastTxts().getLast().setPurpose(value));
        registry.addChannelExtension("podcast:image", "href", (channel, value) -> channel.getPodcastImages().getLast().setHref(value));
        registry.addChannelExtension("podcast:image", "alt", (channel, value) -> channel.getPodcastImages().getLast().setAlt(value));
        registry.addChannelExtension("podcast:image", "aspect-ratio", (channel, value) -> channel.getPodcastImages().getLast().setAspectRatio(value));
        registry.addChannelExtension("podcast:image", "width", (channel, value) -> Optional.of(channel.getPodcastImages().getLast()).ifPresent(image -> mapInteger(value, image::setWidth)));
        registry.addChannelExtension("podcast:image", "height", (channel, value) -> Optional.of(channel.getPodcastImages().getLast()).ifPresent(image -> mapInteger(value, image::setHeight)));
        registry.addChannelExtension("podcast:image", "type", (channel, value) -> channel.getPodcastImages().getLast().setType(value));
        registry.addChannelExtension("podcast:image", "purpose", (channel, value) -> channel.getPodcastImages().getLast().setPurpose(value));
        registry.addChannelExtension("podcast:images", "srcset", (channel, value) -> parsePodcastImages(channel::addPodcastImage, value));
        registry.addChannelExtension("podcast:podping", "usesPodping", (channel, value) -> mapBoolean(value, channel::setPodcastUsingPodping));
    }

    private static void onItemTag(FeedExtensionRegistry<? extends PodcastChannel, ? extends PodcastItem> registry) {
        registry.addOnItemTag("podcast:image", item -> item.addPodcastImage(new PodcastImage()));
        registry.addOnItemTag("podcast:soundbite", item -> item.addPodcastSoundbite(new PodcastSoundbite()));
        registry.addOnItemTag("podcast:transcript", item -> item.addPodcastTranscript(new PodcastTranscript()));
        registry.addOnItemTag("podcast:person", item -> item.addPodcastPerson(new PodcastPerson()));
        registry.addOnItemTag("podcast:alternateEnclosure", item ->item.addPodcastAlternateEnclosure(new PodcastAlternateEnclosure()));
        registry.addOnItemTag("podcast:source", item -> item.getPodcastAlternateEnclosures().getLast().addSource(new PodcastSource()));
        registry.addOnItemTag("podcast:value", item -> item.addPodcastValue(new PodcastValue()));
        registry.addOnItemTag("/rss/channel/item/podcast:value/podcast:valueRecipient", item ->item.getPodcastValues().getLast().addValueRecipient(new PodcastValueRecipient()));
        registry.addOnItemTag("podcast:valueTimeSplit", item -> item.getPodcastValues().getLast().addValueTimeSplit(new PodcastValueTimeSplit()));
        registry.addOnItemTag("/rss/channel/item/podcast:value/podcast:valueTimeSplit/podcast:valueRecipient", item ->item.getPodcastValues().getLast().getValueTimeSplits().getLast().addValueRecipient(new PodcastValueRecipient()));
        registry.addOnItemTag("podcast:socialInteract", item -> item.addPodcastSocialInteract(new PodcastSocialInteract()));
        registry.addOnItemTag("podcast:contentLink", item -> item.addPodcastContentLink(new PodcastContentLink()));
        registry.addOnItemTag("podcast:txt", item -> item.addPodcastTxt(new PodcastTxt()));
    }

    private static void itemTagExtensions(FeedExtensionRegistry<? extends PodcastChannel, ? extends PodcastItem> registry) {
        registry.addItemExtension("podcast:season", (item, value) -> createIfNullOptional(item::getPodcastSeason, item::setPodcastSeason, PodcastSeason::new).ifPresent(season -> mapInteger(value, season::setSeason)));
        registry.addItemExtension("podcast:episode", (item, value) -> createIfNullOptional(item::getPodcastEpisode, item::setPodcastEpisode, PodcastEpisode::new).ifPresent(season -> mapDouble(value, season::setEpisode)));
        registry.addItemExtension("podcast:soundbite", (item, value) -> item.getPodcastSoundbites().getLast().setSoundbite(value));
        registry.addItemExtension("podcast:person", (item, value) -> item.getPodcastPersons().getLast().setPerson(value));
        registry.addItemExtension("podcast:contentLink", (item, value) -> item.getPodcastContentLinks().getLast().setContentLink(value));
        registry.addItemExtension("podcast:txt", (item, value) -> item.getPodcastTxts().getLast().setTxt(value));
    }

    private static void itemAttributesExtensions(FeedExtensionRegistry<? extends PodcastChannel, ? extends PodcastItem> registry) {
        registry.addItemExtension("podcast:image", "href", (item, value) -> item.getPodcastImages().getLast().setHref(value));
        registry.addItemExtension("podcast:image", "alt", (item, value) -> item.getPodcastImages().getLast().setAlt(value));
        registry.addItemExtension("podcast:image", "aspect-ratio", (item, value) -> item.getPodcastImages().getLast().setAspectRatio(value));
        registry.addItemExtension("podcast:image", "width", (item, value) -> Optional.of(item.getPodcastImages().getLast()).ifPresent(image -> mapInteger(value, image::setWidth)));
        registry.addItemExtension("podcast:image", "height", (item, value) -> Optional.of(item.getPodcastImages().getLast()).ifPresent(image -> mapInteger(value, image::setHeight)));
        registry.addItemExtension("podcast:image", "type", (item, value) -> item.getPodcastImages().getLast().setType(value));
        registry.addItemExtension("podcast:image", "purpose", (item, value) -> item.getPodcastImages().getLast().setPurpose(value));
        registry.addItemExtension("podcast:images", "srcset", (item, value) -> parsePodcastImages(item::addPodcastImage, value));
        registry.addItemExtension("podcast:season", "name", (item, value) -> createIfNull(item::getPodcastSeason, item::setPodcastSeason, PodcastSeason::new).setName(value));
        registry.addItemExtension("podcast:episode", "display", (item, value) -> createIfNull(item::getPodcastEpisode, item::setPodcastEpisode, PodcastEpisode::new).setDisplay(value));
        registry.addItemExtension("podcast:chapters", "url", (item, value) -> createIfNull(item::getPodcastChapters, item::setPodcastChapters, PodcastChapters::new).setUrl(value));
        registry.addItemExtension("podcast:chapters", "type", (item, value) -> createIfNull(item::getPodcastChapters, item::setPodcastChapters, PodcastChapters::new).setType(value));
        registry.addItemExtension("podcast:soundbite", "startTime", (item, value) -> Optional.of(item.getPodcastSoundbites().getLast()).ifPresent(soundBite -> mapDouble(value, soundBite::setStartTime)));
        registry.addItemExtension("podcast:soundbite", "duration", (item, value) -> Optional.of(item.getPodcastSoundbites().getLast()).ifPresent(soundBite -> mapDouble(value, soundBite::setDuration)));
        registry.addItemExtension("podcast:soundbite", "duration", (item, value) -> Optional.of(item.getPodcastSoundbites().getLast()).ifPresent(soundBite -> mapDouble(value, soundBite::setDuration)));
        registry.addItemExtension("podcast:transcript", "url", (item, value) -> item.getPodcastTranscripts().getLast().setUrl(value));
        registry.addItemExtension("podcast:transcript", "type", (item, value) -> item.getPodcastTranscripts().getLast().setType(value));
        registry.addItemExtension("podcast:transcript", "language", (item, value) -> item.getPodcastTranscripts().getLast().setLanguage(value));
        registry.addItemExtension("podcast:transcript", "rel", (item, value) -> item.getPodcastTranscripts().getLast().setRel(value));
        registry.addItemExtension("podcast:person", "role", (item, value) -> item.getPodcastPersons().getLast().setRole(value));
        registry.addItemExtension("podcast:person", "group", (item, value) -> item.getPodcastPersons().getLast().setGroup(value));
        registry.addItemExtension("podcast:person", "img", (item, value) -> item.getPodcastPersons().getLast().setImg(value));
        registry.addItemExtension("podcast:person", "href", (item, value) -> item.getPodcastPersons().getLast().setHref(value));
        registry.addItemExtension("podcast:alternateEnclosure", "type", (item, value) -> item.getPodcastAlternateEnclosures().getLast().setType(value));
        registry.addItemExtension("podcast:alternateEnclosure", "length", (item, value) -> Optional.of(item.getPodcastAlternateEnclosures().getLast()).ifPresent(alternateEnclosures -> mapLong(value, alternateEnclosures::setLength)));
        registry.addItemExtension("podcast:alternateEnclosure", "bitrate", (item, value) -> Optional.of(item.getPodcastAlternateEnclosures().getLast()).ifPresent(alternateEnclosures -> mapDouble(value, alternateEnclosures::setBitrate)));
        registry.addItemExtension("podcast:alternateEnclosure", "height", (item, value) -> Optional.of(item.getPodcastAlternateEnclosures().getLast()).ifPresent(alternateEnclosures -> mapInteger(value, alternateEnclosures::setHeight)));
        registry.addItemExtension("podcast:alternateEnclosure", "lang", (item, value) -> item.getPodcastAlternateEnclosures().getLast().setLang(value));
        registry.addItemExtension("podcast:alternateEnclosure", "title", (item, value) -> item.getPodcastAlternateEnclosures().getLast().setTitle(value));
        registry.addItemExtension("podcast:alternateEnclosure", "rel", (item, value) -> item.getPodcastAlternateEnclosures().getLast().setRel(value));
        registry.addItemExtension("podcast:alternateEnclosure", "codecs", (item, value) -> item.getPodcastAlternateEnclosures().getLast().setCodecs(value));
        registry.addItemExtension("podcast:alternateEnclosure", "default", (item, value) -> Optional.of(item.getPodcastAlternateEnclosures().getLast()).ifPresent(alternateEnclosures -> mapBoolean(value, alternateEnclosures::setDefaults)));
        registry.addItemExtension("podcast:source", "uri", (item, value) -> item.getPodcastAlternateEnclosures().getLast().getSources().getLast().setUri(value));
        registry.addItemExtension("podcast:source", "contentType", (item, value) -> item.getPodcastAlternateEnclosures().getLast().getSources().getLast().setContentType(value));
        registry.addItemExtension("podcast:integrity", "type", (item, value) -> { var alt = item.getPodcastAlternateEnclosures().getLast(); createIfNull(alt::getIntegrity, alt::setIntegrity, PodcastIntegrity::new).setType(value);});
        registry.addItemExtension("podcast:integrity", "value", (item, value) -> { var alt = item.getPodcastAlternateEnclosures().getLast(); createIfNull(alt::getIntegrity, alt::setIntegrity, PodcastIntegrity::new).setValue(value);});
        registry.addItemExtension("podcast:value", "type", (item, value) -> item.getPodcastValues().getLast().setType(value));
        registry.addItemExtension("podcast:value", "method", (item, value) -> item.getPodcastValues().getLast().setMethod(value));
        registry.addItemExtension("podcast:value", "suggested", (item, value) -> Optional.of(item.getPodcastValues().getLast()).ifPresent(podcastValue -> mapDouble(value, podcastValue::setSuggested)));
        registry.addItemExtension("/rss/channel/item/podcast:value/podcast:valueRecipient", "name", (item, value) -> item.getPodcastValues().getLast().getValueRecipients().getLast().setName(value));
        registry.addItemExtension("/rss/channel/item/podcast:value/podcast:valueRecipient", "type", (item, value) -> item.getPodcastValues().getLast().getValueRecipients().getLast().setType(value));
        registry.addItemExtension("/rss/channel/item/podcast:value/podcast:valueRecipient", "address", (item, value) -> item.getPodcastValues().getLast().getValueRecipients().getLast().setAddress(value));
        registry.addItemExtension("/rss/channel/item/podcast:value/podcast:valueRecipient", "customKey", (item, value) -> item.getPodcastValues().getLast().getValueRecipients().getLast().setCustomKey(value));
        registry.addItemExtension("/rss/channel/item/podcast:value/podcast:valueRecipient", "customValue", (item, value) -> item.getPodcastValues().getLast().getValueRecipients().getLast().setCustomValue(value));
        registry.addItemExtension("/rss/channel/item/podcast:value/podcast:valueRecipient", "split", (item, value) -> Optional.of(item.getPodcastValues().getLast().getValueRecipients().getLast()).ifPresent(recipient -> mapInteger(value, recipient::setSplit)));
        registry.addItemExtension("/rss/channel/item/podcast:value/podcast:valueRecipient", "fee", (item, value) -> Optional.of(item.getPodcastValues().getLast().getValueRecipients().getLast()).ifPresent(recipient -> mapBoolean(value, recipient::setFee)));
        registry.addItemExtension("podcast:valueTimeSplit", "startTime", (item, value) -> Optional.ofNullable(item.getPodcastValues().getLast().getValueTimeSplits().getLast()).ifPresent(valueRecipient -> mapInteger(value, valueRecipient::setStartTime)));
        registry.addItemExtension("podcast:valueTimeSplit", "duration", (item, value) -> Optional.ofNullable(item.getPodcastValues().getLast().getValueTimeSplits().getLast()).ifPresent(valueRecipient -> mapInteger(value, valueRecipient::setDuration)));
        registry.addItemExtension("podcast:valueTimeSplit", "remoteStartTime", (item, value) -> Optional.ofNullable(item.getPodcastValues().getLast().getValueTimeSplits().getLast()).ifPresent(valueRecipient -> mapInteger(value, valueRecipient::setRemoteStartTime)));
        registry.addItemExtension("podcast:valueTimeSplit", "remotePercentage", (item, value) -> Optional.ofNullable(item.getPodcastValues().getLast().getValueTimeSplits().getLast()).ifPresent(valueRecipient -> mapInteger(value, valueRecipient::setRemotePercentage)));
        registry.addItemExtension("podcast:remoteItem", "feedGuid", (item, value) -> itemPodcastRemoteItem(item, value, PodcastRemoteItem::setFeedGuid));
        registry.addItemExtension("podcast:remoteItem", "feedUrl", (item, value) -> itemPodcastRemoteItem(item, value, PodcastRemoteItem::setFeedUrl));
        registry.addItemExtension("podcast:remoteItem", "itemGuid", (item, value) -> itemPodcastRemoteItem(item, value, PodcastRemoteItem::setItemGuid));
        registry.addItemExtension("podcast:remoteItem", "medium", (item, value) -> itemPodcastRemoteItem(item, value, PodcastRemoteItem::setMedium));
        registry.addItemExtension("podcast:remoteItem", "title", (item, value) -> itemPodcastRemoteItem(item, value, PodcastRemoteItem::setTitle));
        registry.addItemExtension("/rss/channel/item/podcast:value/podcast:valueTimeSplit/podcast:valueRecipient", "name", (item, value) -> Optional.ofNullable(item.getPodcastValues().getLast().getValueTimeSplits().getLast().getValueRecipients().getLast()).ifPresent(valueRecipient -> valueRecipient.setName(value)));
        registry.addItemExtension("/rss/channel/item/podcast:value/podcast:valueTimeSplit/podcast:valueRecipient", "type", (item, value) -> Optional.ofNullable(item.getPodcastValues().getLast().getValueTimeSplits().getLast().getValueRecipients().getLast()).ifPresent(valueRecipient -> valueRecipient.setType(value)));
        registry.addItemExtension("/rss/channel/item/podcast:value/podcast:valueTimeSplit/podcast:valueRecipient", "address", (item, value) -> Optional.ofNullable(item.getPodcastValues().getLast().getValueTimeSplits().getLast().getValueRecipients().getLast()).ifPresent(valueRecipient -> valueRecipient.setAddress(value)));
        registry.addItemExtension("/rss/channel/item/podcast:value/podcast:valueTimeSplit/podcast:valueRecipient", "customKey", (item, value) -> Optional.ofNullable(item.getPodcastValues().getLast().getValueTimeSplits().getLast().getValueRecipients().getLast()).ifPresent(valueRecipient -> valueRecipient.setCustomKey(value)));
        registry.addItemExtension("/rss/channel/item/podcast:value/podcast:valueTimeSplit/podcast:valueRecipient", "customValue", (item, value) -> Optional.ofNullable(item.getPodcastValues().getLast().getValueTimeSplits().getLast().getValueRecipients().getLast()).ifPresent(valueRecipient -> valueRecipient.setCustomValue(value)));
        registry.addItemExtension("/rss/channel/item/podcast:value/podcast:valueTimeSplit/podcast:valueRecipient", "split", (item, value) -> Optional.ofNullable(item.getPodcastValues().getLast().getValueTimeSplits().getLast().getValueRecipients().getLast()).ifPresent(valueRecipient -> mapInteger(value, valueRecipient::setSplit)));
        registry.addItemExtension("/rss/channel/item/podcast:value/podcast:valueTimeSplit/podcast:valueRecipient", "fee", (item, value) -> Optional.ofNullable(item.getPodcastValues().getLast().getValueTimeSplits().getLast().getValueRecipients().getLast()).ifPresent(valueRecipient -> mapBoolean(value, valueRecipient::setFee)));
        registry.addItemExtension("podcast:socialInteract", "protocol", (item, value) -> item.getPodcastSocialInteracts().getLast().setProtocol(value));
        registry.addItemExtension("podcast:socialInteract", "uri", (item, value) -> item.getPodcastSocialInteracts().getLast().setUri(value));
        registry.addItemExtension("podcast:socialInteract", "accountId", (item, value) -> item.getPodcastSocialInteracts().getLast().setAccountId(value));
        registry.addItemExtension("podcast:socialInteract", "accountUrl", (item, value) -> item.getPodcastSocialInteracts().getLast().setAccountUrl(value));
        registry.addItemExtension("podcast:socialInteract", "priority", (item, value) -> Optional.of(item.getPodcastSocialInteracts().getLast()).ifPresent(socialInteracts -> mapInteger(value, socialInteracts::setPriority)));
        registry.addItemExtension("podcast:contentLink", "href", (item, value) -> item.getPodcastContentLinks().getLast().setHref(value));
        registry.addItemExtension("podcast:chat", "server", (item, value) -> createIfNullOptional(item::getPodcastChat, item::setPodcastChat, PodcastChat::new).ifPresent(podcastChat -> podcastChat.setServer(value)));
        registry.addItemExtension("podcast:chat", "protocol", (item, value) -> createIfNullOptional(item::getPodcastChat, item::setPodcastChat, PodcastChat::new).ifPresent(podcastChat -> podcastChat.setProtocol(value)));
        registry.addItemExtension("podcast:chat", "accountId", (item, value) -> createIfNullOptional(item::getPodcastChat, item::setPodcastChat, PodcastChat::new).ifPresent(podcastChat -> podcastChat.setAccountId(value)));
        registry.addItemExtension("podcast:chat", "space", (item, value) -> createIfNullOptional(item::getPodcastChat, item::setPodcastChat, PodcastChat::new).ifPresent(podcastChat -> podcastChat.setSpace(value)));
        registry.addItemExtension("podcast:liveItem", "status", PodcastItemData::setPodcastLiveItemStatus);
        registry.addItemExtension("podcast:liveItem", "start", PodcastItemData::setPodcastLiveItemStart);
        registry.addItemExtension("podcast:liveItem", "end", PodcastItemData::setPodcastLiveItemEnd);
        registry.addItemExtension("podcast:txt", "purpose", (item, value) -> item.getPodcastTxts().getLast().setPurpose(value));
    }

    private static void channelPodcastValuePodcastValueTimeSplitPodcastRemoteItem(PodcastChannel channel, String value, BiConsumer<PodcastRemoteItem, String> setter) {
        var timeSplit = channel.getPodcastValues().getLast().getValueTimeSplits().getLast();
        var remoteItem = createIfNull(timeSplit::getRemoteItem, timeSplit::setRemoteItem, PodcastRemoteItem::new);
        setter.accept(remoteItem, value);
    }

    private static void channelPodcastPodrollPodcastRemoteItem(PodcastChannel channel, String value, BiConsumer<PodcastRemoteItem, String> setter) {
        var remoteItem = channel.getPodcastPodrolls().getLast();
        setter.accept(remoteItem, value);
    }

    private static void channelPodcastPublisherItemPodcastRemoteItem(PodcastChannel channel, String value, BiConsumer<PodcastRemoteItem, String> setter) {
        var remoteItem = createIfNull(channel::getPodcastPublisher, channel::setPodcastPublisher, PodcastRemoteItem::new);
        setter.accept(remoteItem, value);
    }

    private static void itemPodcastRemoteItem(PodcastItem item, String value, BiConsumer<PodcastRemoteItem, String> setter) {
        var timeSplit = item.getPodcastValues().getLast().getValueTimeSplits().getLast();
        var remoteItem = createIfNull(timeSplit::getRemoteItem, timeSplit::setRemoteItem, PodcastRemoteItem::new);
        setter.accept(remoteItem, value);
    }

    private static void parsePodcastImages(Consumer<PodcastImage> addImage, String value) {
        if (value == null || value.trim().isEmpty()) {
            return;
        }

        // Split by comma to get individual image entries (HTML5 srcset format)
        String[] entries = value.split(",");

        for (String entry : entries) {
            try {
                entry = entry.trim();
                if (entry.isEmpty()) {
                    continue;
                }

                // Find the last whitespace to separate URL from descriptor
                // According to HTML5 spec, URL is followed by optional whitespace and a descriptor
                int lastWhitespace = entry.lastIndexOf(' ');
                int lastTab = entry.lastIndexOf('\t');
                int lastNewline = entry.lastIndexOf('\n');
                int lastCarriageReturn = entry.lastIndexOf('\r');

                int descriptorStart = Math.max(Math.max(lastWhitespace, lastTab), Math.max(lastNewline, lastCarriageReturn));

                if (descriptorStart <= 0) {
                    // No descriptor found, just a URL
                    continue;
                }

                String url = entry.substring(0, descriptorStart).trim();
                String descriptor = entry.substring(descriptorStart).trim();

                if (url.isEmpty() || descriptor.isEmpty()) {
                    continue;
                }

                // Create a new PodcastImage and add it to the item
                PodcastImage image = new PodcastImage();
                image.setHref(url);

                // Parse width descriptor (e.g., "150w" -> 150)
                // or pixel density descriptor (e.g., "2x" -> 2)
                if (descriptor.endsWith("w") || descriptor.endsWith("W")) {
                    String widthStr = descriptor.substring(0, descriptor.length() - 1);
                    mapInteger(widthStr, image::setWidth);
                } else if (descriptor.endsWith("x") || descriptor.endsWith("X")) {
                    String densityStr = descriptor.substring(0, descriptor.length() - 1);
                    mapDouble(densityStr, density -> image.setWidth(density.intValue()));
                }

                addImage.accept(image);
            } catch (Exception e) {
                // Ignore parsing errors for individual entries
            }
        }
    }
}
