package com.apptasticsoftware.rssreader.module.podcast;

import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.FeedExtensionRegistry;

import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static com.apptasticsoftware.rssreader.util.Mapper.*;
import static com.apptasticsoftware.rssreader.util.Mapper.createIfNullOptional;
import static com.apptasticsoftware.rssreader.util.Mapper.mapBoolean;
import static com.apptasticsoftware.rssreader.util.Util.getLast;

public class PodcastExtensions {
    private static final List<String> CHANNEL_PATHS = List.of("/rss/channel/", "/feed/");
    private static final List<String> ITEM_PATHS = List.of("/rss/channel/item/", "/feed/entry/");

    private PodcastExtensions() {

    }

    public static void register(FeedExtensionRegistry<? extends PodcastChannel, ? extends PodcastItem> registry, DateTimeParser dateTimeParser) {
        onChannelTag(registry, dateTimeParser);
        channelTagExtensions(registry);
        channelAttributeExtensions(registry);

        onItemTag(registry);
        itemTagExtensions(registry);
        itemAttributesExtensions(registry);
    }

    @SuppressWarnings("java:S1192")
    private static void onChannelTag(FeedExtensionRegistry<? extends PodcastChannel, ? extends PodcastItem> registry, DateTimeParser dateTimeParser) {
        registry.addOnChannelTag("podcast:block", channel -> channel.addPodcastBlock(new PodcastBlock()));
        registry.addOnChannelTag("podcast:funding", channel -> channel.addPodcastFunding(new PodcastFunding()));
        registry.addOnChannelTag("podcast:location", channel -> channel.addPodcastLocation(new PodcastLocation()));
        registry.addOnChannelTag("podcast:value", channel -> channel.addPodcastValue(new PodcastValue()));
        registry.addOnChannelTag(CHANNEL_PATHS, "podcast:value/podcast:valueRecipient", channel -> getLast(channel.getPodcastValues()).addValueRecipient(new PodcastValueRecipient()));
        registry.addOnChannelTag("podcast:valueTimeSplit", channel -> getLast(channel.getPodcastValues()).addValueTimeSplit(new PodcastValueTimeSplit()));
        registry.addOnChannelTag(CHANNEL_PATHS, "podcast:value/podcast:valueTimeSplit/podcast:valueRecipient", channel -> getLast(getLast(channel.getPodcastValues()).getValueTimeSplits()).addValueRecipient(new PodcastValueRecipient()));
        registry.addOnChannelTag(CHANNEL_PATHS, "podcast:podroll/podcast:remoteItem", channel -> channel.addPodcastPodroll(new PodcastRemoteItem()));
        registry.addOnChannelTag("podcast:person", channel ->channel.addPodcastPerson(new PodcastPerson()));
        registry.addOnChannelTag("podcast:trailer", channel -> channel.addPodcastTrailer(new PodcastTrailer(dateTimeParser)));
        registry.addOnChannelTag("podcast:image", channel -> channel.addPodcastImage(new PodcastImage()));
        registry.addOnChannelTag("podcast:txt", channel -> channel.addPodcastTxt(new PodcastTxt()));
    }

    @SuppressWarnings("java:S1192")
    private static void channelTagExtensions(FeedExtensionRegistry<? extends PodcastChannel, ? extends PodcastItem> registry) {
        registry.addChannelExtension("podcast:guid", PodcastChannel::setPodcastGuid);
        registry.addChannelExtension("podcast:license", (channel, value) -> createIfNull(channel::getPodcastLicense, channel::setPodcastLicense, PodcastLicense::new).setLicense(value));
        registry.addChannelExtension("podcast:locked", (channel, value) -> createIfNullOptional(channel::getPodcastLocked, channel::setPodcastLocked, PodcastLocked::new).ifPresent(locked -> mapBoolean(value, locked::setLocked)));
        registry.addChannelExtension("podcast:block", (channel, value) -> Optional.ofNullable(getLast(channel.getPodcastBlocks())).ifPresent(block -> mapBoolean(value, block::setBlock)));
        registry.addChannelExtension("podcast:funding", (channel, value) -> Optional.ofNullable(getLast(channel.getPodcastFundings())).ifPresent(funding -> funding.setFunding(value)));
        registry.addChannelExtension("podcast:location", (channel, value) -> Optional.ofNullable(getLast(channel.getPodcastLocations())).ifPresent(location -> location.setLocation(value)));
        registry.addChannelExtension("podcast:medium", PodcastChannel::setPodcastMedium);
        registry.addChannelExtension("podcast:person", (channel, value) -> getLast(channel.getPodcastPersons()).setPerson(value));
        registry.addChannelExtension("podcast:trailer", (channel, value) -> Optional.ofNullable(getLast(channel.getPodcastTrailers())).ifPresent(trailer -> trailer.setTrailer(value)));
        registry.addChannelExtension("podcast:updateFrequency", (channel, value) -> createIfNullOptional(channel::getPodcastUpdateFrequency, channel::setPodcastUpdateFrequency, PodcastUpdateFrequency::new).ifPresent(updateFrequency -> updateFrequency.setUpdateFrequency(value)));
        registry.addChannelExtension("podcast:txt", (channel, value) -> getLast(channel.getPodcastTxts()).setTxt(value));
    }

    @SuppressWarnings("java:S1192")
    private static void channelAttributeExtensions(FeedExtensionRegistry<? extends PodcastChannel, ? extends PodcastItem> registry) {
        registry.addChannelExtension("podcast:license", "url", (channel, value) -> createIfNull(channel::getPodcastLicense, channel::setPodcastLicense, PodcastLicense::new).setUrl(value));
        registry.addChannelExtension("podcast:locked", "owner", (channel, value) -> createIfNullOptional(channel::getPodcastLocked, channel::setPodcastLocked, PodcastLocked::new).ifPresent(locked -> locked.setOwner(value)));
        registry.addChannelExtension("podcast:block", "id", (channel, value) -> Optional.ofNullable(getLast(channel.getPodcastBlocks())).ifPresent(block -> block.setId(value)));
        registry.addChannelExtension("podcast:funding", "url", (channel, value) -> Optional.ofNullable(getLast(channel.getPodcastFundings())).ifPresent(funding -> funding.setUrl(value)));
        registry.addChannelExtension("podcast:location", "rel", (channel, value) -> Optional.ofNullable(getLast(channel.getPodcastLocations())).ifPresent(location -> location.setRel(value)));
        registry.addChannelExtension("podcast:location", "geo", (channel, value) -> Optional.ofNullable(getLast(channel.getPodcastLocations())).ifPresent(location -> location.setGeo(value)));
        registry.addChannelExtension("podcast:location", "osm", (channel, value) -> Optional.ofNullable(getLast(channel.getPodcastLocations())).ifPresent(location -> location.setOsm(value)));
        registry.addChannelExtension("podcast:location", "country", (channel, value) -> Optional.ofNullable(getLast(channel.getPodcastLocations())).ifPresent(location -> location.setCountry(value)));
        registry.addChannelExtension("podcast:value", "type", (channel, value) -> Optional.ofNullable(getLast(channel.getPodcastValues())).ifPresent(podcastValue -> podcastValue.setType(value)));
        registry.addChannelExtension("podcast:value", "method", (channel, value) -> Optional.ofNullable(getLast(channel.getPodcastValues())).ifPresent(podcastValue -> podcastValue.setMethod(value)));
        registry.addChannelExtension("podcast:value", "suggested", (channel, value) -> Optional.ofNullable(getLast(channel.getPodcastValues())).ifPresent(podcastValue -> mapDouble(value, podcastValue::setSuggested)));
        registry.addChannelExtension("podcast:trailer", "pubdate", (channel, value) -> Optional.ofNullable(getLast(channel.getPodcastTrailers())).ifPresent(trailer -> trailer.setPubDate(value)));
        registry.addChannelExtension("podcast:trailer", "url", (channel, value) -> Optional.ofNullable(getLast(channel.getPodcastTrailers())).ifPresent(trailer -> trailer.setUrl(value)));
        registry.addChannelExtension("podcast:trailer", "type", (channel, value) -> Optional.ofNullable(getLast(channel.getPodcastTrailers())).ifPresent(trailer -> trailer.setType(value)));
        registry.addChannelExtension("podcast:trailer", "length", (channel, value) -> Optional.ofNullable(getLast(channel.getPodcastTrailers())).ifPresent(trailer -> mapLong(value, trailer::setLength)));
        registry.addChannelExtension("podcast:trailer", "season", (channel, value) -> Optional.ofNullable(getLast(channel.getPodcastTrailers())).ifPresent(trailer -> mapInteger(value, trailer::setSeason)));
        registry.addChannelExtension(CHANNEL_PATHS, "podcast:value/podcast:valueRecipient", "name", (channel, value) -> Optional.ofNullable(getLast(getLast(channel.getPodcastValues()).getValueRecipients())).ifPresent(valueRecipient -> valueRecipient.setName(value)));
        registry.addChannelExtension(CHANNEL_PATHS, "podcast:value/podcast:valueRecipient", "type", (channel, value) -> Optional.ofNullable(getLast(getLast(channel.getPodcastValues()).getValueRecipients())).ifPresent(valueRecipient -> valueRecipient.setType(value)));
        registry.addChannelExtension(CHANNEL_PATHS, "podcast:value/podcast:valueRecipient", "address", (channel, value) -> Optional.ofNullable(getLast(getLast(channel.getPodcastValues()).getValueRecipients())).ifPresent(valueRecipient -> valueRecipient.setAddress(value)));
        registry.addChannelExtension(CHANNEL_PATHS, "podcast:value/podcast:valueRecipient", "customKey", (channel, value) -> Optional.ofNullable(getLast(getLast(channel.getPodcastValues()).getValueRecipients())).ifPresent(valueRecipient -> valueRecipient.setCustomKey(value)));
        registry.addChannelExtension(CHANNEL_PATHS, "podcast:value/podcast:valueRecipient", "customValue", (channel, value) -> Optional.ofNullable(getLast(getLast(channel.getPodcastValues()).getValueRecipients())).ifPresent(valueRecipient -> valueRecipient.setCustomValue(value)));
        registry.addChannelExtension(CHANNEL_PATHS, "podcast:value/podcast:valueRecipient", "split", (channel, value) -> Optional.ofNullable(getLast(getLast(channel.getPodcastValues()).getValueRecipients())).ifPresent(valueRecipient -> mapInteger(value, valueRecipient::setSplit)));
        registry.addChannelExtension(CHANNEL_PATHS, "podcast:value/podcast:valueRecipient", "fee", (channel, value) -> Optional.ofNullable(getLast(getLast(channel.getPodcastValues()).getValueRecipients())).ifPresent(valueRecipient -> mapBoolean(value, valueRecipient::setFee)));
        registry.addChannelExtension("podcast:valueTimeSplit", "startTime", (channel, value) -> Optional.ofNullable(getLast(getLast(channel.getPodcastValues()).getValueTimeSplits())).ifPresent(valueRecipient -> mapInteger(value, valueRecipient::setStartTime)));
        registry.addChannelExtension("podcast:valueTimeSplit", "duration", (channel, value) -> Optional.ofNullable(getLast(getLast(channel.getPodcastValues()).getValueTimeSplits())).ifPresent(valueRecipient -> mapInteger(value, valueRecipient::setDuration)));
        registry.addChannelExtension("podcast:valueTimeSplit", "remoteStartTime", (channel, value) -> Optional.ofNullable(getLast(getLast(channel.getPodcastValues()).getValueTimeSplits())).ifPresent(valueRecipient -> mapInteger(value, valueRecipient::setRemoteStartTime)));
        registry.addChannelExtension("podcast:valueTimeSplit", "remotePercentage", (channel, value) -> Optional.ofNullable(getLast(getLast(channel.getPodcastValues()).getValueTimeSplits())).ifPresent(valueRecipient -> mapInteger(value, valueRecipient::setRemotePercentage)));
        registry.addChannelExtension(CHANNEL_PATHS, "podcast:value/podcast:valueTimeSplit/podcast:valueRecipient", "name", (channel, value) -> Optional.ofNullable(getLast(getLast(getLast(channel.getPodcastValues()).getValueTimeSplits()).getValueRecipients())).ifPresent(valueRecipient -> valueRecipient.setName(value)));
        registry.addChannelExtension(CHANNEL_PATHS, "podcast:value/podcast:valueTimeSplit/podcast:valueRecipient", "type", (channel, value) -> Optional.ofNullable(getLast(getLast(getLast(channel.getPodcastValues()).getValueTimeSplits()).getValueRecipients())).ifPresent(valueRecipient -> valueRecipient.setType(value)));
        registry.addChannelExtension(CHANNEL_PATHS, "podcast:value/podcast:valueTimeSplit/podcast:valueRecipient", "address", (channel, value) -> Optional.ofNullable(getLast(getLast(getLast(channel.getPodcastValues()).getValueTimeSplits()).getValueRecipients())).ifPresent(valueRecipient -> valueRecipient.setAddress(value)));
        registry.addChannelExtension(CHANNEL_PATHS, "podcast:value/podcast:valueTimeSplit/podcast:valueRecipient", "customKey", (channel, value) -> Optional.ofNullable(getLast(getLast(getLast(channel.getPodcastValues()).getValueTimeSplits()).getValueRecipients())).ifPresent(valueRecipient -> valueRecipient.setCustomKey(value)));
        registry.addChannelExtension(CHANNEL_PATHS, "podcast:value/podcast:valueTimeSplit/podcast:valueRecipient", "customValue", (channel, value) -> Optional.ofNullable(getLast(getLast(getLast(channel.getPodcastValues()).getValueTimeSplits()).getValueRecipients())).ifPresent(valueRecipient -> valueRecipient.setCustomValue(value)));
        registry.addChannelExtension(CHANNEL_PATHS, "podcast:value/podcast:valueTimeSplit/podcast:valueRecipient", "split", (channel, value) -> Optional.ofNullable(getLast(getLast(getLast(channel.getPodcastValues()).getValueTimeSplits()).getValueRecipients())).ifPresent(valueRecipient -> mapInteger(value, valueRecipient::setSplit)));
        registry.addChannelExtension(CHANNEL_PATHS, "podcast:value/podcast:valueTimeSplit/podcast:valueRecipient", "fee", (channel, value) -> Optional.ofNullable(getLast(getLast(getLast(channel.getPodcastValues()).getValueTimeSplits()).getValueRecipients())).ifPresent(valueRecipient -> mapBoolean(value, valueRecipient::setFee)));
        registry.addChannelExtension(CHANNEL_PATHS, "podcast:value/podcast:valueTimeSplit/podcast:remoteItem", "feedGuid", (channel, value) -> channelPodcastValuePodcastValueTimeSplitPodcastRemoteItem(channel, value, PodcastRemoteItem::setFeedGuid));
        registry.addChannelExtension(CHANNEL_PATHS, "podcast:value/podcast:valueTimeSplit/podcast:remoteItem", "feedUrl", (channel, value) -> channelPodcastValuePodcastValueTimeSplitPodcastRemoteItem(channel, value, PodcastRemoteItem::setFeedUrl));
        registry.addChannelExtension(CHANNEL_PATHS, "podcast:value/podcast:valueTimeSplit/podcast:remoteItem", "itemGuid", (channel, value) -> channelPodcastValuePodcastValueTimeSplitPodcastRemoteItem(channel, value, PodcastRemoteItem::setItemGuid));
        registry.addChannelExtension(CHANNEL_PATHS, "podcast:value/podcast:valueTimeSplit/podcast:remoteItem", "medium", (channel, value) -> channelPodcastValuePodcastValueTimeSplitPodcastRemoteItem(channel, value, PodcastRemoteItem::setMedium));
        registry.addChannelExtension(CHANNEL_PATHS, "podcast:value/podcast:valueTimeSplit/podcast:remoteItem", "title", (channel, value) -> channelPodcastValuePodcastValueTimeSplitPodcastRemoteItem(channel, value, PodcastRemoteItem::setTitle));
        registry.addChannelExtension(CHANNEL_PATHS, "podcast:podroll/podcast:remoteItem", "feedGuid", (channel, value) -> channelPodcastPodrollPodcastRemoteItem(channel, value, PodcastRemoteItem::setFeedGuid));
        registry.addChannelExtension(CHANNEL_PATHS, "podcast:podroll/podcast:remoteItem", "feedUrl", (channel, value) -> channelPodcastPodrollPodcastRemoteItem(channel, value, PodcastRemoteItem::setFeedUrl));
        registry.addChannelExtension(CHANNEL_PATHS, "podcast:podroll/podcast:remoteItem", "itemGuid", (channel, value) -> channelPodcastPodrollPodcastRemoteItem(channel, value, PodcastRemoteItem::setItemGuid));
        registry.addChannelExtension(CHANNEL_PATHS, "podcast:podroll/podcast:remoteItem", "medium", (channel, value) -> channelPodcastPodrollPodcastRemoteItem(channel, value, PodcastRemoteItem::setMedium));
        registry.addChannelExtension(CHANNEL_PATHS, "podcast:podroll/podcast:remoteItem", "title", (channel, value) -> channelPodcastPodrollPodcastRemoteItem(channel, value, PodcastRemoteItem::setTitle));
        registry.addChannelExtension(CHANNEL_PATHS, "podcast:publisher/podcast:remoteItem", "feedGuid", (channel, value) -> channelPodcastPublisherItemPodcastRemoteItem(channel, value, PodcastRemoteItem::setFeedGuid));
        registry.addChannelExtension(CHANNEL_PATHS, "podcast:publisher/podcast:remoteItem", "feedUrl", (channel, value) -> channelPodcastPublisherItemPodcastRemoteItem(channel, value, PodcastRemoteItem::setFeedUrl));
        registry.addChannelExtension(CHANNEL_PATHS, "podcast:publisher/podcast:remoteItem", "itemGuid", (channel, value) -> channelPodcastPublisherItemPodcastRemoteItem(channel, value, PodcastRemoteItem::setItemGuid));
        registry.addChannelExtension(CHANNEL_PATHS, "podcast:publisher/podcast:remoteItem", "medium", (channel, value) -> channelPodcastPublisherItemPodcastRemoteItem(channel, value, PodcastRemoteItem::setMedium));
        registry.addChannelExtension(CHANNEL_PATHS, "podcast:publisher/podcast:remoteItem", "title", (channel, value) -> channelPodcastPublisherItemPodcastRemoteItem(channel, value, PodcastRemoteItem::setTitle));
        registry.addChannelExtension("podcast:person", "role", (channel, value) -> getLast(channel.getPodcastPersons()).setRole(value));
        registry.addChannelExtension("podcast:person", "group", (channel, value) -> getLast(channel.getPodcastPersons()).setGroup(value));
        registry.addChannelExtension("podcast:person", "img", (channel, value) -> getLast(channel.getPodcastPersons()).setImg(value));
        registry.addChannelExtension("podcast:person", "href", (channel, value) -> getLast(channel.getPodcastPersons()).setHref(value));
        registry.addChannelExtension("podcast:updateFrequency", "rrule", (channel, value) -> createIfNullOptional(channel::getPodcastUpdateFrequency, channel::setPodcastUpdateFrequency, PodcastUpdateFrequency::new).ifPresent(updateFrequency -> updateFrequency.setRrule(value)));
        registry.addChannelExtension("podcast:updateFrequency", "complete", (channel, value) -> createIfNullOptional(channel::getPodcastUpdateFrequency, channel::setPodcastUpdateFrequency, PodcastUpdateFrequency::new).ifPresent(updateFrequency -> mapBoolean(value, updateFrequency::setComplete)));
        registry.addChannelExtension("podcast:updateFrequency", "dtstart", (channel, value) -> createIfNullOptional(channel::getPodcastUpdateFrequency, channel::setPodcastUpdateFrequency, PodcastUpdateFrequency::new).ifPresent(updateFrequency -> updateFrequency.setDtstart(value)));
        registry.addChannelExtension("podcast:chat", "server", (channel, value) -> createIfNullOptional(channel::getPodcastChat, channel::setPodcastChat, PodcastChat::new).ifPresent(podcastChat -> podcastChat.setServer(value)));
        registry.addChannelExtension("podcast:chat", "protocol", (channel, value) -> createIfNullOptional(channel::getPodcastChat, channel::setPodcastChat, PodcastChat::new).ifPresent(podcastChat -> podcastChat.setProtocol(value)));
        registry.addChannelExtension("podcast:chat", "accountId", (channel, value) -> createIfNullOptional(channel::getPodcastChat, channel::setPodcastChat, PodcastChat::new).ifPresent(podcastChat -> podcastChat.setAccountId(value)));
        registry.addChannelExtension("podcast:chat", "space", (channel, value) -> createIfNullOptional(channel::getPodcastChat, channel::setPodcastChat, PodcastChat::new).ifPresent(podcastChat -> podcastChat.setSpace(value)));
        registry.addChannelExtension("podcast:txt", "purpose", (channel, value) -> getLast(channel.getPodcastTxts()).setPurpose(value));
        registry.addChannelExtension("podcast:image", "href", (channel, value) -> getLast(channel.getPodcastImages()).setHref(value));
        registry.addChannelExtension("podcast:image", "alt", (channel, value) -> getLast(channel.getPodcastImages()).setAlt(value));
        registry.addChannelExtension("podcast:image", "aspect-ratio", (channel, value) -> getLast(channel.getPodcastImages()).setAspectRatio(value));
        registry.addChannelExtension("podcast:image", "width", (channel, value) -> Optional.of(getLast(channel.getPodcastImages())).ifPresent(image -> mapInteger(value, image::setWidth)));
        registry.addChannelExtension("podcast:image", "height", (channel, value) -> Optional.of(getLast(channel.getPodcastImages())).ifPresent(image -> mapInteger(value, image::setHeight)));
        registry.addChannelExtension("podcast:image", "type", (channel, value) -> getLast(channel.getPodcastImages()).setType(value));
        registry.addChannelExtension("podcast:image", "purpose", (channel, value) -> getLast(channel.getPodcastImages()).setPurpose(value));
        registry.addChannelExtension("podcast:images", "srcset", (channel, value) -> parsePodcastImages(channel::addPodcastImage, value));
        registry.addChannelExtension("podcast:podping", "usesPodping", (channel, value) -> mapBoolean(value, channel::setPodcastUsingPodping));
    }

    @SuppressWarnings("java:S1192")
    private static void onItemTag(FeedExtensionRegistry<? extends PodcastChannel, ? extends PodcastItem> registry) {
        registry.addOnItemTag("podcast:image", item -> item.addPodcastImage(new PodcastImage()));
        registry.addOnItemTag("podcast:soundbite", item -> item.addPodcastSoundbite(new PodcastSoundbite()));
        registry.addOnItemTag("podcast:transcript", item -> item.addPodcastTranscript(new PodcastTranscript()));
        registry.addOnItemTag("podcast:person", item -> item.addPodcastPerson(new PodcastPerson()));
        registry.addOnItemTag("podcast:alternateEnclosure", item ->item.addPodcastAlternateEnclosure(new PodcastAlternateEnclosure()));
        registry.addOnItemTag("podcast:source", item -> getLast(item.getPodcastAlternateEnclosures()).addSource(new PodcastSource()));
        registry.addOnItemTag("podcast:value", item -> item.addPodcastValue(new PodcastValue()));
        registry.addOnItemTag(ITEM_PATHS, "podcast:value/podcast:valueRecipient", item -> getLast(item.getPodcastValues()).addValueRecipient(new PodcastValueRecipient()));
        registry.addOnItemTag("podcast:valueTimeSplit", item -> getLast(item.getPodcastValues()).addValueTimeSplit(new PodcastValueTimeSplit()));
        registry.addOnItemTag(ITEM_PATHS, "podcast:value/podcast:valueTimeSplit/podcast:valueRecipient", item -> getLast(getLast(item.getPodcastValues()).getValueTimeSplits()).addValueRecipient(new PodcastValueRecipient()));
        registry.addOnItemTag("podcast:socialInteract", item -> item.addPodcastSocialInteract(new PodcastSocialInteract()));
        registry.addOnItemTag("podcast:contentLink", item -> item.addPodcastContentLink(new PodcastContentLink()));
        registry.addOnItemTag("podcast:txt", item -> item.addPodcastTxt(new PodcastTxt()));
    }

    private static void itemTagExtensions(FeedExtensionRegistry<? extends PodcastChannel, ? extends PodcastItem> registry) {
        registry.addItemExtension("podcast:season", (item, value) -> createIfNullOptional(item::getPodcastSeason, item::setPodcastSeason, PodcastSeason::new).ifPresent(season -> mapInteger(value, season::setSeason)));
        registry.addItemExtension("podcast:episode", (item, value) -> createIfNullOptional(item::getPodcastEpisode, item::setPodcastEpisode, PodcastEpisode::new).ifPresent(season -> mapDouble(value, season::setEpisode)));
        registry.addItemExtension("podcast:soundbite", (item, value) -> getLast(item.getPodcastSoundbites()).setSoundbite(value));
        registry.addItemExtension("podcast:person", (item, value) -> getLast(item.getPodcastPersons()).setPerson(value));
        registry.addItemExtension("podcast:contentLink", (item, value) -> getLast(item.getPodcastContentLinks()).setContentLink(value));
        registry.addItemExtension("podcast:txt", (item, value) -> getLast(item.getPodcastTxts()).setTxt(value));
    }

    @SuppressWarnings("java:S1192")
    private static void itemAttributesExtensions(FeedExtensionRegistry<? extends PodcastChannel, ? extends PodcastItem> registry) {
        registry.addItemExtension("podcast:image", "href", (item, value) -> getLast(item.getPodcastImages()).setHref(value));
        registry.addItemExtension("podcast:image", "alt", (item, value) -> getLast(item.getPodcastImages()).setAlt(value));
        registry.addItemExtension("podcast:image", "aspect-ratio", (item, value) -> getLast(item.getPodcastImages()).setAspectRatio(value));
        registry.addItemExtension("podcast:image", "width", (item, value) -> Optional.of(getLast(item.getPodcastImages())).ifPresent(image -> mapInteger(value, image::setWidth)));
        registry.addItemExtension("podcast:image", "height", (item, value) -> Optional.of(getLast(item.getPodcastImages())).ifPresent(image -> mapInteger(value, image::setHeight)));
        registry.addItemExtension("podcast:image", "type", (item, value) -> getLast(item.getPodcastImages()).setType(value));
        registry.addItemExtension("podcast:image", "purpose", (item, value) -> getLast(item.getPodcastImages()).setPurpose(value));
        registry.addItemExtension("podcast:images", "srcset", (item, value) -> parsePodcastImages(item::addPodcastImage, value));
        registry.addItemExtension("podcast:season", "name", (item, value) -> createIfNull(item::getPodcastSeason, item::setPodcastSeason, PodcastSeason::new).setName(value));
        registry.addItemExtension("podcast:episode", "display", (item, value) -> createIfNull(item::getPodcastEpisode, item::setPodcastEpisode, PodcastEpisode::new).setDisplay(value));
        registry.addItemExtension("podcast:chapters", "url", (item, value) -> createIfNull(item::getPodcastChapters, item::setPodcastChapters, PodcastChapters::new).setUrl(value));
        registry.addItemExtension("podcast:chapters", "type", (item, value) -> createIfNull(item::getPodcastChapters, item::setPodcastChapters, PodcastChapters::new).setType(value));
        registry.addItemExtension("podcast:soundbite", "startTime", (item, value) -> Optional.of(getLast(item.getPodcastSoundbites())).ifPresent(soundBite -> mapDouble(value, soundBite::setStartTime)));
        registry.addItemExtension("podcast:soundbite", "duration", (item, value) -> Optional.of(getLast(item.getPodcastSoundbites())).ifPresent(soundBite -> mapDouble(value, soundBite::setDuration)));
        registry.addItemExtension("podcast:soundbite", "duration", (item, value) -> Optional.of(getLast(item.getPodcastSoundbites())).ifPresent(soundBite -> mapDouble(value, soundBite::setDuration)));
        registry.addItemExtension("podcast:transcript", "url", (item, value) -> getLast(item.getPodcastTranscripts()).setUrl(value));
        registry.addItemExtension("podcast:transcript", "type", (item, value) -> getLast(item.getPodcastTranscripts()).setType(value));
        registry.addItemExtension("podcast:transcript", "language", (item, value) -> getLast(item.getPodcastTranscripts()).setLanguage(value));
        registry.addItemExtension("podcast:transcript", "rel", (item, value) -> getLast(item.getPodcastTranscripts()).setRel(value));
        registry.addItemExtension("podcast:person", "role", (item, value) -> getLast(item.getPodcastPersons()).setRole(value));
        registry.addItemExtension("podcast:person", "group", (item, value) -> getLast(item.getPodcastPersons()).setGroup(value));
        registry.addItemExtension("podcast:person", "img", (item, value) -> getLast(item.getPodcastPersons()).setImg(value));
        registry.addItemExtension("podcast:person", "href", (item, value) -> getLast(item.getPodcastPersons()).setHref(value));
        registry.addItemExtension("podcast:alternateEnclosure", "type", (item, value) -> getLast(item.getPodcastAlternateEnclosures()).setType(value));
        registry.addItemExtension("podcast:alternateEnclosure", "length", (item, value) -> Optional.of(getLast(item.getPodcastAlternateEnclosures())).ifPresent(alternateEnclosures -> mapLong(value, alternateEnclosures::setLength)));
        registry.addItemExtension("podcast:alternateEnclosure", "bitrate", (item, value) -> Optional.of(getLast(item.getPodcastAlternateEnclosures())).ifPresent(alternateEnclosures -> mapDouble(value, alternateEnclosures::setBitrate)));
        registry.addItemExtension("podcast:alternateEnclosure", "height", (item, value) -> Optional.of(getLast(item.getPodcastAlternateEnclosures())).ifPresent(alternateEnclosures -> mapInteger(value, alternateEnclosures::setHeight)));
        registry.addItemExtension("podcast:alternateEnclosure", "lang", (item, value) -> getLast(item.getPodcastAlternateEnclosures()).setLang(value));
        registry.addItemExtension("podcast:alternateEnclosure", "title", (item, value) -> getLast(item.getPodcastAlternateEnclosures()).setTitle(value));
        registry.addItemExtension("podcast:alternateEnclosure", "rel", (item, value) -> getLast(item.getPodcastAlternateEnclosures()).setRel(value));
        registry.addItemExtension("podcast:alternateEnclosure", "codecs", (item, value) -> getLast(item.getPodcastAlternateEnclosures()).setCodecs(value));
        registry.addItemExtension("podcast:alternateEnclosure", "default", (item, value) -> Optional.of(getLast(item.getPodcastAlternateEnclosures())).ifPresent(alternateEnclosures -> mapBoolean(value, alternateEnclosures::setDefaults)));
        registry.addItemExtension("podcast:source", "uri", (item, value) -> getLast(getLast(item.getPodcastAlternateEnclosures()).getSources()).setUri(value));
        registry.addItemExtension("podcast:source", "contentType", (item, value) -> getLast(getLast(item.getPodcastAlternateEnclosures()).getSources()).setContentType(value));
        registry.addItemExtension("podcast:integrity", "type", (item, value) -> { var alt = getLast(item.getPodcastAlternateEnclosures()); createIfNull(alt::getIntegrity, alt::setIntegrity, PodcastIntegrity::new).setType(value);});
        registry.addItemExtension("podcast:integrity", "value", (item, value) -> { var alt = getLast(item.getPodcastAlternateEnclosures()); createIfNull(alt::getIntegrity, alt::setIntegrity, PodcastIntegrity::new).setValue(value);});
        registry.addItemExtension("podcast:value", "type", (item, value) -> getLast(item.getPodcastValues()).setType(value));
        registry.addItemExtension("podcast:value", "method", (item, value) -> getLast(item.getPodcastValues()).setMethod(value));
        registry.addItemExtension("podcast:value", "suggested", (item, value) -> Optional.of(getLast(item.getPodcastValues())).ifPresent(podcastValue -> mapDouble(value, podcastValue::setSuggested)));
        registry.addItemExtension(ITEM_PATHS, "podcast:value/podcast:valueRecipient", "name", (item, value) -> getLast(getLast(item.getPodcastValues()).getValueRecipients()).setName(value));
        registry.addItemExtension(ITEM_PATHS, "podcast:value/podcast:valueRecipient", "type", (item, value) -> getLast(getLast(item.getPodcastValues()).getValueRecipients()).setType(value));
        registry.addItemExtension(ITEM_PATHS, "podcast:value/podcast:valueRecipient", "address", (item, value) -> getLast(getLast(item.getPodcastValues()).getValueRecipients()).setAddress(value));
        registry.addItemExtension(ITEM_PATHS, "podcast:value/podcast:valueRecipient", "customKey", (item, value) -> getLast(getLast(item.getPodcastValues()).getValueRecipients()).setCustomKey(value));
        registry.addItemExtension(ITEM_PATHS, "podcast:value/podcast:valueRecipient", "customValue", (item, value) -> getLast(getLast(item.getPodcastValues()).getValueRecipients()).setCustomValue(value));
        registry.addItemExtension(ITEM_PATHS, "podcast:value/podcast:valueRecipient", "split", (item, value) -> Optional.of(getLast(getLast(item.getPodcastValues()).getValueRecipients())).ifPresent(recipient -> mapInteger(value, recipient::setSplit)));
        registry.addItemExtension(ITEM_PATHS, "podcast:value/podcast:valueRecipient", "fee", (item, value) -> Optional.of(getLast(getLast(item.getPodcastValues()).getValueRecipients())).ifPresent(recipient -> mapBoolean(value, recipient::setFee)));
        registry.addItemExtension("podcast:valueTimeSplit", "startTime", (item, value) -> Optional.ofNullable(getLast(getLast(item.getPodcastValues()).getValueTimeSplits())).ifPresent(valueRecipient -> mapInteger(value, valueRecipient::setStartTime)));
        registry.addItemExtension("podcast:valueTimeSplit", "duration", (item, value) -> Optional.ofNullable(getLast(getLast(item.getPodcastValues()).getValueTimeSplits())).ifPresent(valueRecipient -> mapInteger(value, valueRecipient::setDuration)));
        registry.addItemExtension("podcast:valueTimeSplit", "remoteStartTime", (item, value) -> Optional.ofNullable(getLast(getLast(item.getPodcastValues()).getValueTimeSplits())).ifPresent(valueRecipient -> mapInteger(value, valueRecipient::setRemoteStartTime)));
        registry.addItemExtension("podcast:valueTimeSplit", "remotePercentage", (item, value) -> Optional.ofNullable(getLast(getLast(item.getPodcastValues()).getValueTimeSplits())).ifPresent(valueRecipient -> mapInteger(value, valueRecipient::setRemotePercentage)));
        registry.addItemExtension("podcast:remoteItem", "feedGuid", (item, value) -> itemPodcastRemoteItem(item, value, PodcastRemoteItem::setFeedGuid));
        registry.addItemExtension("podcast:remoteItem", "feedUrl", (item, value) -> itemPodcastRemoteItem(item, value, PodcastRemoteItem::setFeedUrl));
        registry.addItemExtension("podcast:remoteItem", "itemGuid", (item, value) -> itemPodcastRemoteItem(item, value, PodcastRemoteItem::setItemGuid));
        registry.addItemExtension("podcast:remoteItem", "medium", (item, value) -> itemPodcastRemoteItem(item, value, PodcastRemoteItem::setMedium));
        registry.addItemExtension("podcast:remoteItem", "title", (item, value) -> itemPodcastRemoteItem(item, value, PodcastRemoteItem::setTitle));
        registry.addItemExtension(ITEM_PATHS, "podcast:value/podcast:valueTimeSplit/podcast:valueRecipient", "name", (item, value) -> Optional.ofNullable(getLast(getLast(getLast(item.getPodcastValues()).getValueTimeSplits()).getValueRecipients())).ifPresent(valueRecipient -> valueRecipient.setName(value)));
        registry.addItemExtension(ITEM_PATHS, "podcast:value/podcast:valueTimeSplit/podcast:valueRecipient", "type", (item, value) -> Optional.ofNullable(getLast(getLast(getLast(item.getPodcastValues()).getValueTimeSplits()).getValueRecipients())).ifPresent(valueRecipient -> valueRecipient.setType(value)));
        registry.addItemExtension(ITEM_PATHS, "podcast:value/podcast:valueTimeSplit/podcast:valueRecipient", "address", (item, value) -> Optional.ofNullable(getLast(getLast(getLast(item.getPodcastValues()).getValueTimeSplits()).getValueRecipients())).ifPresent(valueRecipient -> valueRecipient.setAddress(value)));
        registry.addItemExtension(ITEM_PATHS, "podcast:value/podcast:valueTimeSplit/podcast:valueRecipient", "customKey", (item, value) -> Optional.ofNullable(getLast(getLast(getLast(item.getPodcastValues()).getValueTimeSplits()).getValueRecipients())).ifPresent(valueRecipient -> valueRecipient.setCustomKey(value)));
        registry.addItemExtension(ITEM_PATHS, "podcast:value/podcast:valueTimeSplit/podcast:valueRecipient", "customValue", (item, value) -> Optional.ofNullable(getLast(getLast(getLast(item.getPodcastValues()).getValueTimeSplits()).getValueRecipients())).ifPresent(valueRecipient -> valueRecipient.setCustomValue(value)));
        registry.addItemExtension(ITEM_PATHS, "podcast:value/podcast:valueTimeSplit/podcast:valueRecipient", "split", (item, value) -> Optional.ofNullable(getLast(getLast(getLast(item.getPodcastValues()).getValueTimeSplits()).getValueRecipients())).ifPresent(valueRecipient -> mapInteger(value, valueRecipient::setSplit)));
        registry.addItemExtension(ITEM_PATHS, "podcast:value/podcast:valueTimeSplit/podcast:valueRecipient", "fee", (item, value) -> Optional.ofNullable(getLast(getLast(getLast(item.getPodcastValues()).getValueTimeSplits()).getValueRecipients())).ifPresent(valueRecipient -> mapBoolean(value, valueRecipient::setFee)));
        registry.addItemExtension("podcast:socialInteract", "protocol", (item, value) -> getLast(item.getPodcastSocialInteracts()).setProtocol(value));
        registry.addItemExtension("podcast:socialInteract", "uri", (item, value) -> getLast(item.getPodcastSocialInteracts()).setUri(value));
        registry.addItemExtension("podcast:socialInteract", "accountId", (item, value) -> getLast(item.getPodcastSocialInteracts()).setAccountId(value));
        registry.addItemExtension("podcast:socialInteract", "accountUrl", (item, value) -> getLast(item.getPodcastSocialInteracts()).setAccountUrl(value));
        registry.addItemExtension("podcast:socialInteract", "priority", (item, value) -> Optional.of(getLast(item.getPodcastSocialInteracts())).ifPresent(socialInteracts -> mapInteger(value, socialInteracts::setPriority)));
        registry.addItemExtension("podcast:contentLink", "href", (item, value) -> getLast(item.getPodcastContentLinks()).setHref(value));
        registry.addItemExtension("podcast:chat", "server", (item, value) -> createIfNullOptional(item::getPodcastChat, item::setPodcastChat, PodcastChat::new).ifPresent(podcastChat -> podcastChat.setServer(value)));
        registry.addItemExtension("podcast:chat", "protocol", (item, value) -> createIfNullOptional(item::getPodcastChat, item::setPodcastChat, PodcastChat::new).ifPresent(podcastChat -> podcastChat.setProtocol(value)));
        registry.addItemExtension("podcast:chat", "accountId", (item, value) -> createIfNullOptional(item::getPodcastChat, item::setPodcastChat, PodcastChat::new).ifPresent(podcastChat -> podcastChat.setAccountId(value)));
        registry.addItemExtension("podcast:chat", "space", (item, value) -> createIfNullOptional(item::getPodcastChat, item::setPodcastChat, PodcastChat::new).ifPresent(podcastChat -> podcastChat.setSpace(value)));
        registry.addItemExtension("podcast:liveItem", "status", PodcastItemData::setPodcastLiveItemStatus);
        registry.addItemExtension("podcast:liveItem", "start", PodcastItemData::setPodcastLiveItemStart);
        registry.addItemExtension("podcast:liveItem", "end", PodcastItemData::setPodcastLiveItemEnd);
        registry.addItemExtension("podcast:txt", "purpose", (item, value) -> getLast(item.getPodcastTxts()).setPurpose(value));
    }

    private static void channelPodcastValuePodcastValueTimeSplitPodcastRemoteItem(PodcastChannel channel, String value, BiConsumer<PodcastRemoteItem, String> setter) {
        var timeSplit = getLast(getLast(channel.getPodcastValues()).getValueTimeSplits());
        var remoteItem = createIfNull(timeSplit::getRemoteItem, timeSplit::setRemoteItem, PodcastRemoteItem::new);
        setter.accept(remoteItem, value);
    }

    private static void channelPodcastPodrollPodcastRemoteItem(PodcastChannel channel, String value, BiConsumer<PodcastRemoteItem, String> setter) {
        var remoteItem = getLast(channel.getPodcastPodrolls());
        setter.accept(remoteItem, value);
    }

    private static void channelPodcastPublisherItemPodcastRemoteItem(PodcastChannel channel, String value, BiConsumer<PodcastRemoteItem, String> setter) {
        var remoteItem = createIfNull(channel::getPodcastPublisher, channel::setPodcastPublisher, PodcastRemoteItem::new);
        setter.accept(remoteItem, value);
    }

    private static void itemPodcastRemoteItem(PodcastItem item, String value, BiConsumer<PodcastRemoteItem, String> setter) {
        var timeSplit = getLast(getLast(item.getPodcastValues()).getValueTimeSplits());
        var remoteItem = createIfNull(timeSplit::getRemoteItem, timeSplit::setRemoteItem, PodcastRemoteItem::new);
        setter.accept(remoteItem, value);
    }

    @SuppressWarnings({"java:S3776", "java:S135"})
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
