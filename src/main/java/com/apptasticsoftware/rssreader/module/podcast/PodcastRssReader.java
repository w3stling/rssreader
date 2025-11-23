package com.apptasticsoftware.rssreader.module.podcast;

import com.apptasticsoftware.rssreader.AbstractRssReader;
import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.module.itunes.ItunesExtensions;

import java.net.http.HttpClient;
import java.util.Optional;
import java.util.function.BiConsumer;

import static com.apptasticsoftware.rssreader.util.Mapper.*;

public class PodcastRssReader extends AbstractRssReader<PodcastChannel, PodcastItem> {

    /**
     * Constructor
     */
    public PodcastRssReader() {
        super();
    }

    /**
     * Constructor
     * @param httpClient http client
     */
    public PodcastRssReader(HttpClient httpClient) {
        super(httpClient);
    }

    @Override
    protected PodcastChannel createChannel(DateTimeParser dateTimeParser) {
        return new PodcastChannel(dateTimeParser);
    }

    @Override
    protected PodcastItem createItem(DateTimeParser dateTimeParser) {
        return new PodcastItem(dateTimeParser);
    }

    @Override
    protected void registerChannelTags() {
        super.registerChannelTags();
        var extensionRegistry = getFeedExtensionRegistry();
        //PodcastExtensions.register(mappingRegistry);
        ItunesExtensions.register(extensionRegistry);

        onChannelTags.put("podcast:block", channel -> channel.addPodcastBlock(new PodcastBlock()));
        onChannelTags.put("podcast:funding", channel -> channel.addPodcastFunding(new PodcastFunding()));
        onChannelTags.put("podcast:location", channel -> channel.addPodcastLocation(new PodcastLocation()));
        onChannelTags.put("podcast:value", channel -> channel.addPodcastValue(new PodcastValue()));
        onChannelTags.put("/rss/channel/podcast:value/podcast:valueRecipient", channel -> channel.getPodcastValues().getLast().addValueRecipient(new PodcastValueRecipient()));
        onChannelTags.put("podcast:valueTimeSplit", channel -> channel.getPodcastValues().getLast().addValueTimeSplit(new PodcastValueTimeSplit()));
        onChannelTags.put("/rss/channel/podcast:value/podcast:valueTimeSplit/podcast:valueRecipient", channel -> channel.getPodcastValues().getLast().getValueTimeSplits().getLast().addValueRecipient(new PodcastValueRecipient()));
        onChannelTags.put("/rss/channel/podcast:podroll/podcast:remoteItem", channel -> channel.addPodcastPodroll(new PodcastRemoteItem()));
        onChannelTags.put("podcast:person", channel ->channel.addPodcastPerson(new PodcastPerson()));
        onChannelTags.put("podcast:trailer", channel -> channel.addPodcastTrailer(new PodcastTrailer()));

        addChannelExtension("podcast:guid", PodcastChannel::setPodcastGuid);
        addChannelExtension("podcast:license", (channel, value) -> createIfNull(channel::getPodcastLicense, channel::setPodcastLicense, PodcastLicense::new).setLicense(value));
        addChannelExtension("podcast:locked", (channel, value) -> createIfNullOptional(channel::getPodcastLocked, channel::setPodcastLocked, PodcastLocked::new).ifPresent(locked -> mapBoolean(value, locked::setLocked)));
        addChannelExtension("podcast:block", (channel, value) -> Optional.ofNullable(channel.getPodcastBlocks().getLast()).ifPresent(block -> mapBoolean(value, block::setBlock)));
        addChannelExtension("podcast:funding", (channel, value) -> Optional.ofNullable(channel.getPodcastFundings().getLast()).ifPresent(funding -> funding.setFunding(value)));
        addChannelExtension("podcast:location", (channel, value) -> Optional.ofNullable(channel.getPodcastLocations().getLast()).ifPresent(location -> location.setLocation(value)));
        addChannelExtension("podcast:medium", PodcastChannel::setPodcastMedium);
        addChannelExtension("podcast:person", (channel, value) -> channel.getPodcastPersons().getLast().setPerson(value));
        addChannelExtension("podcast:trailer", (channel, value) -> Optional.ofNullable(channel.getPodcastTrailers().getLast()).ifPresent(trailer -> trailer.setTrailer(value)));
        addChannelExtension("podcast:updateFrequency", (channel, value) -> createIfNullOptional(channel::getPodcastUpdateFrequency, channel::setPodcastUpdateFrequency, PodcastUpdateFrequency::new).ifPresent(updateFrequency -> updateFrequency.setUpdateFrequency(value)));
    }

    @Override
    protected void registerChannelAttributes() {
        super.registerChannelAttributes();
        addChannelExtension("podcast:license", "url", (channel, value) -> createIfNull(channel::getPodcastLicense, channel::setPodcastLicense, PodcastLicense::new).setUrl(value));
        addChannelExtension("podcast:locked", "owner", (channel, value) -> createIfNullOptional(channel::getPodcastLocked, channel::setPodcastLocked, PodcastLocked::new).ifPresent(PodcastLocked::getOwner));
        addChannelExtension("podcast:block", "id", (channel, value) -> Optional.ofNullable(channel.getPodcastBlocks().getLast()).ifPresent(block -> block.setId(value)));
        addChannelExtension("podcast:funding", "url", (channel, value) -> Optional.ofNullable(channel.getPodcastFundings().getLast()).ifPresent(funding -> funding.setUrl(value)));
        addChannelExtension("podcast:location", "rel", (channel, value) -> Optional.ofNullable(channel.getPodcastLocations().getLast()).ifPresent(location -> location.setRel(value)));
        addChannelExtension("podcast:location", "geo", (channel, value) -> Optional.ofNullable(channel.getPodcastLocations().getLast()).ifPresent(location -> location.setGeo(value)));
        addChannelExtension("podcast:location", "osm", (channel, value) -> Optional.ofNullable(channel.getPodcastLocations().getLast()).ifPresent(location -> location.setOsm(value)));
        addChannelExtension("podcast:location", "country", (channel, value) -> Optional.ofNullable(channel.getPodcastLocations().getLast()).ifPresent(location -> location.setCountry(value)));
        addChannelExtension("podcast:value", "type", (channel, value) -> Optional.ofNullable(channel.getPodcastValues().getLast()).ifPresent(podcastValue -> podcastValue.setType(value)));
        addChannelExtension("podcast:value", "method", (channel, value) -> Optional.ofNullable(channel.getPodcastValues().getLast()).ifPresent(podcastValue -> podcastValue.setMethod(value)));
        addChannelExtension("podcast:value", "suggested", (channel, value) -> Optional.ofNullable(channel.getPodcastValues().getLast()).ifPresent(podcastValue -> mapDouble(value, podcastValue::setSuggested)));
        addChannelExtension("podcast:trailer", "pubdate", (channel, value) -> Optional.ofNullable(channel.getPodcastTrailers().getLast()).ifPresent(trailer -> trailer.setPubDate(value)));
        addChannelExtension("podcast:trailer", "url", (channel, value) -> Optional.ofNullable(channel.getPodcastTrailers().getLast()).ifPresent(trailer -> trailer.setUrl(value)));
        addChannelExtension("podcast:trailer", "type", (channel, value) -> Optional.ofNullable(channel.getPodcastTrailers().getLast()).ifPresent(trailer -> trailer.setType(value)));
        addChannelExtension("podcast:trailer", "length", (channel, value) -> Optional.ofNullable(channel.getPodcastTrailers().getLast()).ifPresent(trailer -> mapLong(value, trailer::setLength)));
        addChannelExtension("podcast:trailer", "season", (channel, value) -> Optional.ofNullable(channel.getPodcastTrailers().getLast()).ifPresent(trailer -> mapInteger(value, trailer::setSeason)));
        addChannelExtension("/rss/channel/podcast:value/podcast:valueRecipient", "name", (channel, value) -> Optional.ofNullable(channel.getPodcastValues().getLast().getValueRecipients().getLast()).ifPresent(valueRecipient -> valueRecipient.setName(value)));
        addChannelExtension("/rss/channel/podcast:value/podcast:valueRecipient", "type", (channel, value) -> Optional.ofNullable(channel.getPodcastValues().getLast().getValueRecipients().getLast()).ifPresent(valueRecipient -> valueRecipient.setType(value)));
        addChannelExtension("/rss/channel/podcast:value/podcast:valueRecipient", "address", (channel, value) -> Optional.ofNullable(channel.getPodcastValues().getLast().getValueRecipients().getLast()).ifPresent(valueRecipient -> valueRecipient.setAddress(value)));
        addChannelExtension("/rss/channel/podcast:value/podcast:valueRecipient", "customKey", (channel, value) -> Optional.ofNullable(channel.getPodcastValues().getLast().getValueRecipients().getLast()).ifPresent(valueRecipient -> valueRecipient.setCustomKey(value)));
        addChannelExtension("/rss/channel/podcast:value/podcast:valueRecipient", "customValue", (channel, value) -> Optional.ofNullable(channel.getPodcastValues().getLast().getValueRecipients().getLast()).ifPresent(valueRecipient -> valueRecipient.setCustomValue(value)));
        addChannelExtension("/rss/channel/podcast:value/podcast:valueRecipient", "split", (channel, value) -> Optional.ofNullable(channel.getPodcastValues().getLast().getValueRecipients().getLast()).ifPresent(valueRecipient -> mapInteger(value, valueRecipient::setSplit)));
        addChannelExtension("/rss/channel/podcast:value/podcast:valueRecipient", "fee", (channel, value) -> Optional.ofNullable(channel.getPodcastValues().getLast().getValueRecipients().getLast()).ifPresent(valueRecipient -> mapBoolean(value, valueRecipient::setFee)));
        addChannelExtension("podcast:valueTimeSplit", "startTime", (channel, value) -> Optional.ofNullable(channel.getPodcastValues().getLast().getValueTimeSplits().getLast()).ifPresent(valueRecipient -> mapInteger(value, valueRecipient::setStartTime)));
        addChannelExtension("podcast:valueTimeSplit", "duration", (channel, value) -> Optional.ofNullable(channel.getPodcastValues().getLast().getValueTimeSplits().getLast()).ifPresent(valueRecipient -> mapInteger(value, valueRecipient::setDuration)));
        addChannelExtension("podcast:valueTimeSplit", "remotePercentage", (channel, value) -> Optional.ofNullable(channel.getPodcastValues().getLast().getValueTimeSplits().getLast()).ifPresent(valueRecipient -> mapInteger(value, valueRecipient::setRemotePercentage)));
        addChannelExtension("/rss/channel/podcast:value/podcast:valueTimeSplit/podcast:valueRecipient", "name", (channel, value) -> Optional.ofNullable(channel.getPodcastValues().getLast().getValueTimeSplits().getLast().getValueRecipients().getLast()).ifPresent(valueRecipient -> valueRecipient.setName(value)));
        addChannelExtension("/rss/channel/podcast:value/podcast:valueTimeSplit/podcast:valueRecipient", "type", (channel, value) -> Optional.ofNullable(channel.getPodcastValues().getLast().getValueTimeSplits().getLast().getValueRecipients().getLast()).ifPresent(valueRecipient -> valueRecipient.setType(value)));
        addChannelExtension("/rss/channel/podcast:value/podcast:valueTimeSplit/podcast:valueRecipient", "address", (channel, value) -> Optional.ofNullable(channel.getPodcastValues().getLast().getValueTimeSplits().getLast().getValueRecipients().getLast()).ifPresent(valueRecipient -> valueRecipient.setAddress(value)));
        addChannelExtension("/rss/channel/podcast:value/podcast:valueTimeSplit/podcast:valueRecipient", "customKey", (channel, value) -> Optional.ofNullable(channel.getPodcastValues().getLast().getValueTimeSplits().getLast().getValueRecipients().getLast()).ifPresent(valueRecipient -> valueRecipient.setCustomKey(value)));
        addChannelExtension("/rss/channel/podcast:value/podcast:valueTimeSplit/podcast:valueRecipient", "customValue", (channel, value) -> Optional.ofNullable(channel.getPodcastValues().getLast().getValueTimeSplits().getLast().getValueRecipients().getLast()).ifPresent(valueRecipient -> valueRecipient.setCustomValue(value)));
        addChannelExtension("/rss/channel/podcast:value/podcast:valueTimeSplit/podcast:valueRecipient", "split", (channel, value) -> Optional.ofNullable(channel.getPodcastValues().getLast().getValueTimeSplits().getLast().getValueRecipients().getLast()).ifPresent(valueRecipient -> mapInteger(value, valueRecipient::setSplit)));
        addChannelExtension("/rss/channel/podcast:value/podcast:valueTimeSplit/podcast:valueRecipient", "fee", (channel, value) -> Optional.ofNullable(channel.getPodcastValues().getLast().getValueTimeSplits().getLast().getValueRecipients().getLast()).ifPresent(valueRecipient -> mapBoolean(value, valueRecipient::setFee)));
        addChannelExtension("/rss/channel/podcast:value/podcast:valueTimeSplit/podcast:remoteItem", "feedGuid", channelPodcastValuePodcastValueTimeSplitPodcastRemoteItem(PodcastRemoteItem::setFeedGuid));
        addChannelExtension("/rss/channel/podcast:value/podcast:valueTimeSplit/podcast:remoteItem", "feedUrl", channelPodcastValuePodcastValueTimeSplitPodcastRemoteItem(PodcastRemoteItem::setFeedUrl));
        addChannelExtension("/rss/channel/podcast:value/podcast:valueTimeSplit/podcast:remoteItem", "itemGuid", channelPodcastValuePodcastValueTimeSplitPodcastRemoteItem(PodcastRemoteItem::setItemGuid));
        addChannelExtension("/rss/channel/podcast:value/podcast:valueTimeSplit/podcast:remoteItem", "medium", channelPodcastValuePodcastValueTimeSplitPodcastRemoteItem(PodcastRemoteItem::setMedium));
        addChannelExtension("/rss/channel/podcast:value/podcast:valueTimeSplit/podcast:remoteItem", "title", channelPodcastValuePodcastValueTimeSplitPodcastRemoteItem(PodcastRemoteItem::setTitle));
        addChannelExtension("/rss/channel/podcast:podroll/podcast:remoteItem", "feedGuid", channelPodcastPodrollPodcastRemoteItem(PodcastRemoteItem::setFeedGuid));
        addChannelExtension("/rss/channel/podcast:podroll/podcast:remoteItem", "feedUrl", channelPodcastPodrollPodcastRemoteItem(PodcastRemoteItem::setFeedUrl));
        addChannelExtension("/rss/channel/podcast:podroll/podcast:remoteItem", "itemGuid", channelPodcastPodrollPodcastRemoteItem(PodcastRemoteItem::setItemGuid));
        addChannelExtension("/rss/channel/podcast:podroll/podcast:remoteItem", "medium", channelPodcastPodrollPodcastRemoteItem(PodcastRemoteItem::setMedium));
        addChannelExtension("/rss/channel/podcast:podroll/podcast:remoteItem", "title", channelPodcastPodrollPodcastRemoteItem(PodcastRemoteItem::setTitle));
        addChannelExtension("/rss/channel/podcast:publisher/podcast:remoteItem", "feedGuid", channelPodcastPublisherItemPodcastRemoteItem(PodcastRemoteItem::setFeedGuid));
        addChannelExtension("/rss/channel/podcast:publisher/podcast:remoteItem", "feedUrl", channelPodcastPublisherItemPodcastRemoteItem(PodcastRemoteItem::setFeedUrl));
        addChannelExtension("/rss/channel/podcast:publisher/podcast:remoteItem", "itemGuid", channelPodcastPublisherItemPodcastRemoteItem(PodcastRemoteItem::setItemGuid));
        addChannelExtension("/rss/channel/podcast:publisher/podcast:remoteItem", "medium", channelPodcastPublisherItemPodcastRemoteItem(PodcastRemoteItem::setMedium));
        addChannelExtension("/rss/channel/podcast:publisher/podcast:remoteItem", "title", channelPodcastPublisherItemPodcastRemoteItem(PodcastRemoteItem::setTitle));
        addChannelExtension("podcast:person", "role", (channel, value) -> channel.getPodcastPersons().getLast().setRole(value));
        addChannelExtension("podcast:person", "group", (channel, value) -> channel.getPodcastPersons().getLast().setGroup(value));
        addChannelExtension("podcast:person", "img", (channel, value) -> channel.getPodcastPersons().getLast().setImg(value));
        addChannelExtension("podcast:person", "href", (channel, value) -> channel.getPodcastPersons().getLast().setHref(value));

        addChannelExtension("podcast:updateFrequency", "rrule", (channel, value) -> createIfNullOptional(channel::getPodcastUpdateFrequency, channel::setPodcastUpdateFrequency, PodcastUpdateFrequency::new).ifPresent(updateFrequency -> updateFrequency.setRrule(value)));
        addChannelExtension("podcast:updateFrequency", "complete", (channel, value) -> createIfNullOptional(channel::getPodcastUpdateFrequency, channel::setPodcastUpdateFrequency, PodcastUpdateFrequency::new).ifPresent(updateFrequency -> updateFrequency.setComplete(value)));
        addChannelExtension("podcast:updateFrequency", "dtstart", (channel, value) -> createIfNullOptional(channel::getPodcastUpdateFrequency, channel::setPodcastUpdateFrequency, PodcastUpdateFrequency::new).ifPresent(updateFrequency -> updateFrequency.setDtstart(value)));


    }

    private static BiConsumer<PodcastChannel, String> channelPodcastValuePodcastValueTimeSplitPodcastRemoteItem(BiConsumer<PodcastRemoteItem, String> setter) {
        return (channel, value) -> {
            var timeSplit = channel.getPodcastValues().getLast().getValueTimeSplits().getLast();
            var remoteItem = createIfNull(timeSplit::getRemoteItem, timeSplit::setRemoteItem, PodcastRemoteItem::new);
            setter.accept(remoteItem, value);
        };
    }

    private static BiConsumer<PodcastChannel, String> channelPodcastPodrollPodcastRemoteItem(BiConsumer<PodcastRemoteItem, String> setter) {
        return (channel, value) -> {
            var remoteItem = channel.getPodcastPodrolls().getLast();
            setter.accept(remoteItem, value);
        };
    }

    private static BiConsumer<PodcastChannel, String> channelPodcastPublisherItemPodcastRemoteItem(BiConsumer<PodcastRemoteItem, String> setter) {
        return (channel, value) -> {
            var remoteItem = createIfNull(channel::getPodcastPublisher, channel::setPodcastPublisher, PodcastRemoteItem::new);
            setter.accept(remoteItem, value);
        };
    }

    private static BiConsumer<PodcastItem, String> itemPodcastRemoteItem(BiConsumer<PodcastRemoteItem, String> setter) {
        return (item, value) -> {
            var timeSplit = item.getPodcastValues().getLast().getValueTimeSplits().getLast();
            var remoteItem = createIfNull(timeSplit::getRemoteItem, timeSplit::setRemoteItem, PodcastRemoteItem::new);
            setter.accept(remoteItem, value);
        };
    }

    @Override
    protected void registerItemTags() {
        super.registerItemTags();
        onItemTags.put("podcast:image", item -> item.addPodcastImage(new PodcastImage()));
        onItemTags.put("podcast:soundbite", item ->item.addPodcastSoundbite(new PodcastSoundbite()));
        onItemTags.put("podcast:transcript", item ->item.addPodcastTranscript(new PodcastTranscript()));
        onItemTags.put("podcast:person", item ->item.addPodcastPerson(new PodcastPerson()));
        onItemTags.put("podcast:alternateEnclosure", item ->item.addPodcastAlternateEnclosure(new PodcastAlternateEnclosure()));
        onItemTags.put("podcast:source", item ->item.getPodcastAlternateEnclosures().getLast().addSource(new PodcastSource()));
        onItemTags.put("podcast:value", item ->item.addPodcastValue(new PodcastValue()));
        onItemTags.put("/rss/channel/item/podcast:value/podcast:valueRecipient", item ->item.getPodcastValues().getLast().addValueRecipient(new PodcastValueRecipient()));
        onItemTags.put("podcast:valueTimeSplit", item -> item.getPodcastValues().getLast().addValueTimeSplit(new PodcastValueTimeSplit()));
        onItemTags.put("/rss/channel/item/podcast:value/podcast:valueTimeSplit/podcast:valueRecipient", item ->item.getPodcastValues().getLast().getValueTimeSplits().getLast().addValueRecipient(new PodcastValueRecipient()));
        onItemTags.put("podcast:socialInteract", item ->item.addPodcastSocialInteract(new PodcastSocialInteract()));

        addItemExtension("podcast:season", (item, value) -> createIfNullOptional(item::getPodcastSeason, item::setPodcastSeason, PodcastSeason::new).ifPresent(season -> mapInteger(value, season::setSeason)));
        addItemExtension("podcast:episode", (item, value) -> createIfNullOptional(item::getPodcastEpisode, item::setPodcastEpisode, PodcastEpisode::new).ifPresent(season -> mapDouble(value, season::setEpisode)));
        addItemExtension("podcast:soundbite", (item, value) -> item.getPodcastSoundbites().getLast().setSoundbite(value));
        addItemExtension("podcast:person", (item, value) -> item.getPodcastPersons().getLast().setPerson(value));
    }

    @Override
    protected void registerItemAttributes() {
        super.registerItemAttributes();

        addItemExtension("podcast:image", "href", (item, value) -> item.getPodcastImages().getLast().setHref(value));
        addItemExtension("podcast:image", "alt", (item, value) -> item.getPodcastImages().getLast().setAlt(value));
        addItemExtension("podcast:image", "aspect-ratio", (item, value) -> item.getPodcastImages().getLast().setAspectRatio(value));
        addItemExtension("podcast:image", "width", (item, value) -> Optional.of(item.getPodcastImages().getLast()).ifPresent(image -> mapInteger(value, image::setWidth)));
        addItemExtension("podcast:image", "height", (item, value) -> Optional.of(item.getPodcastImages().getLast()).ifPresent(image -> mapInteger(value, image::setHeight)));
        addItemExtension("podcast:image", "type", (item, value) -> item.getPodcastImages().getLast().setType(value));
        addItemExtension("podcast:image", "purpose", (item, value) -> item.getPodcastImages().getLast().setPurpose(value));
        addItemExtension("podcast:season", "name", (item, value) -> createIfNull(item::getPodcastSeason, item::setPodcastSeason, PodcastSeason::new).setName(value));
        addItemExtension("podcast:episode", "display", (item, value) -> createIfNull(item::getPodcastEpisode, item::setPodcastEpisode, PodcastEpisode::new).setDisplay(value));
        addItemExtension("podcast:chapters", "url", (item, value) -> createIfNull(item::getPodcastChapters, item::setPodcastChapters, PodcastChapters::new).setUrl(value));
        addItemExtension("podcast:chapters", "type", (item, value) -> createIfNull(item::getPodcastChapters, item::setPodcastChapters, PodcastChapters::new).setType(value));
        addItemExtension("podcast:soundbite", "startTime", (item, value) -> Optional.of(item.getPodcastSoundbites().getLast()).ifPresent(soundBite -> mapDouble(value, soundBite::setStartTime)));
        addItemExtension("podcast:soundbite", "duration", (item, value) -> Optional.of(item.getPodcastSoundbites().getLast()).ifPresent(soundBite -> mapDouble(value, soundBite::setDuration)));
        addItemExtension("podcast:soundbite", "duration", (item, value) -> Optional.of(item.getPodcastSoundbites().getLast()).ifPresent(soundBite -> mapDouble(value, soundBite::setDuration)));
        addItemExtension("podcast:transcript", "url", (item, value) -> item.getPodcastTranscripts().getLast().setUrl(value));
        addItemExtension("podcast:transcript", "type", (item, value) -> item.getPodcastTranscripts().getLast().setType(value));
        addItemExtension("podcast:transcript", "language", (item, value) -> item.getPodcastTranscripts().getLast().setLanguage(value));
        addItemExtension("podcast:transcript", "rel", (item, value) -> item.getPodcastTranscripts().getLast().setRel(value));
        addItemExtension("podcast:person", "role", (item, value) -> item.getPodcastPersons().getLast().setRole(value));
        addItemExtension("podcast:person", "group", (item, value) -> item.getPodcastPersons().getLast().setGroup(value));
        addItemExtension("podcast:person", "img", (item, value) -> item.getPodcastPersons().getLast().setImg(value));
        addItemExtension("podcast:person", "href", (item, value) -> item.getPodcastPersons().getLast().setHref(value));
        addItemExtension("podcast:alternateEnclosure", "type", (item, value) -> item.getPodcastAlternateEnclosures().getLast().setType(value));
        addItemExtension("podcast:alternateEnclosure", "length", (item, value) -> Optional.of(item.getPodcastAlternateEnclosures().getLast()).ifPresent(alternateEnclosures -> mapLong(value, alternateEnclosures::setLength)));
        addItemExtension("podcast:alternateEnclosure", "bitrate", (item, value) -> Optional.of(item.getPodcastAlternateEnclosures().getLast()).ifPresent(alternateEnclosures -> mapDouble(value, alternateEnclosures::setBitrate)));
        addItemExtension("podcast:alternateEnclosure", "height", (item, value) -> Optional.of(item.getPodcastAlternateEnclosures().getLast()).ifPresent(alternateEnclosures -> mapInteger(value, alternateEnclosures::setHeight)));
        addItemExtension("podcast:alternateEnclosure", "lang", (item, value) -> item.getPodcastAlternateEnclosures().getLast().setLang(value));
        addItemExtension("podcast:alternateEnclosure", "title", (item, value) -> item.getPodcastAlternateEnclosures().getLast().setTitle(value));
        addItemExtension("podcast:alternateEnclosure", "rel", (item, value) -> item.getPodcastAlternateEnclosures().getLast().setRel(value));
        addItemExtension("podcast:alternateEnclosure", "codecs", (item, value) -> item.getPodcastAlternateEnclosures().getLast().setCodecs(value));
        addItemExtension("podcast:alternateEnclosure", "default", (item, value) -> Optional.of(item.getPodcastAlternateEnclosures().getLast()).ifPresent(alternateEnclosures -> mapBoolean(value, alternateEnclosures::setDefaults)));
        addItemExtension("podcast:source", "uri", (item, value) -> item.getPodcastAlternateEnclosures().getLast().getSources().getLast().setUri(value));
        addItemExtension("podcast:source", "contentType", (item, value) -> item.getPodcastAlternateEnclosures().getLast().getSources().getLast().setContentType(value));
        addItemExtension("podcast:integrity", "type", (item, value) -> { var alt = item.getPodcastAlternateEnclosures().getLast(); createIfNull(alt::getIntegrity, alt::setIntegrity, PodcastIntegrity::new).setType(value);});
        addItemExtension("podcast:integrity", "value", (item, value) -> { var alt = item.getPodcastAlternateEnclosures().getLast(); createIfNull(alt::getIntegrity, alt::setIntegrity, PodcastIntegrity::new).setValue(value);});
        addItemExtension("podcast:value", "type", (item, value) -> item.getPodcastValues().getLast().setType(value));
        addItemExtension("podcast:value", "method", (item, value) -> item.getPodcastValues().getLast().setMethod(value));
        addItemExtension("podcast:value", "suggested", (item, value) -> Optional.of(item.getPodcastValues().getLast()).ifPresent(podcastValue -> mapDouble(value, podcastValue::setSuggested)));
        addItemExtension("/rss/channel/item/podcast:value/podcast:valueRecipient", "name", (item, value) -> item.getPodcastValues().getLast().getValueRecipients().getLast().setName(value));
        addItemExtension("/rss/channel/item/podcast:value/podcast:valueRecipient", "type", (item, value) -> item.getPodcastValues().getLast().getValueRecipients().getLast().setType(value));
        addItemExtension("/rss/channel/item/podcast:value/podcast:valueRecipient", "address", (item, value) -> item.getPodcastValues().getLast().getValueRecipients().getLast().setAddress(value));
        addItemExtension("/rss/channel/item/podcast:value/podcast:valueRecipient", "customKey", (item, value) -> item.getPodcastValues().getLast().getValueRecipients().getLast().setCustomKey(value));
        addItemExtension("/rss/channel/item/podcast:value/podcast:valueRecipient", "customValue", (item, value) -> item.getPodcastValues().getLast().getValueRecipients().getLast().setCustomValue(value));
        addItemExtension("/rss/channel/item/podcast:value/podcast:valueRecipient", "split", (item, value) -> Optional.of(item.getPodcastValues().getLast().getValueRecipients().getLast()).ifPresent(recipient -> mapInteger(value, recipient::setSplit)));
        addItemExtension("/rss/channel/item/podcast:value/podcast:valueRecipient", "fee", (item, value) -> Optional.of(item.getPodcastValues().getLast().getValueRecipients().getLast()).ifPresent(recipient -> mapBoolean(value, recipient::setFee)));
        addItemExtension("podcast:valueTimeSplit", "startTime", (item, value) -> Optional.ofNullable(item.getPodcastValues().getLast().getValueTimeSplits().getLast()).ifPresent(valueRecipient -> mapInteger(value, valueRecipient::setStartTime)));
        addItemExtension("podcast:valueTimeSplit", "duration", (item, value) -> Optional.ofNullable(item.getPodcastValues().getLast().getValueTimeSplits().getLast()).ifPresent(valueRecipient -> mapInteger(value, valueRecipient::setDuration)));
        addItemExtension("podcast:valueTimeSplit", "remotePercentage", (item, value) -> Optional.ofNullable(item.getPodcastValues().getLast().getValueTimeSplits().getLast()).ifPresent(valueRecipient -> mapInteger(value, valueRecipient::setRemotePercentage)));
        addItemExtension("podcast:remoteItem", "feedGuid", itemPodcastRemoteItem(PodcastRemoteItem::setFeedGuid));
        addItemExtension("podcast:remoteItem", "feedUrl", itemPodcastRemoteItem(PodcastRemoteItem::setFeedUrl));
        addItemExtension("podcast:remoteItem", "itemGuid", itemPodcastRemoteItem(PodcastRemoteItem::setItemGuid));
        addItemExtension("podcast:remoteItem", "medium", itemPodcastRemoteItem(PodcastRemoteItem::setMedium));
        addItemExtension("podcast:remoteItem", "title", itemPodcastRemoteItem(PodcastRemoteItem::setTitle));
        addItemExtension("/rss/channel/item/podcast:value/podcast:valueTimeSplit/podcast:valueRecipient", "name", (item, value) -> Optional.ofNullable(item.getPodcastValues().getLast().getValueTimeSplits().getLast().getValueRecipients().getLast()).ifPresent(valueRecipient -> valueRecipient.setName(value)));
        addItemExtension("/rss/channel/item/podcast:value/podcast:valueTimeSplit/podcast:valueRecipient", "type", (item, value) -> Optional.ofNullable(item.getPodcastValues().getLast().getValueTimeSplits().getLast().getValueRecipients().getLast()).ifPresent(valueRecipient -> valueRecipient.setType(value)));
        addItemExtension("/rss/channel/item/podcast:value/podcast:valueTimeSplit/podcast:valueRecipient", "address", (item, value) -> Optional.ofNullable(item.getPodcastValues().getLast().getValueTimeSplits().getLast().getValueRecipients().getLast()).ifPresent(valueRecipient -> valueRecipient.setAddress(value)));
        addItemExtension("/rss/channel/item/podcast:value/podcast:valueTimeSplit/podcast:valueRecipient", "customKey", (item, value) -> Optional.ofNullable(item.getPodcastValues().getLast().getValueTimeSplits().getLast().getValueRecipients().getLast()).ifPresent(valueRecipient -> valueRecipient.setCustomKey(value)));
        addItemExtension("/rss/channel/item/podcast:value/podcast:valueTimeSplit/podcast:valueRecipient", "customValue", (item, value) -> Optional.ofNullable(item.getPodcastValues().getLast().getValueTimeSplits().getLast().getValueRecipients().getLast()).ifPresent(valueRecipient -> valueRecipient.setCustomValue(value)));
        addItemExtension("/rss/channel/item/podcast:value/podcast:valueTimeSplit/podcast:valueRecipient", "split", (item, value) -> Optional.ofNullable(item.getPodcastValues().getLast().getValueTimeSplits().getLast().getValueRecipients().getLast()).ifPresent(valueRecipient -> mapInteger(value, valueRecipient::setSplit)));
        addItemExtension("/rss/channel/item/podcast:value/podcast:valueTimeSplit/podcast:valueRecipient", "fee", (item, value) -> Optional.ofNullable(item.getPodcastValues().getLast().getValueTimeSplits().getLast().getValueRecipients().getLast()).ifPresent(valueRecipient -> mapBoolean(value, valueRecipient::setFee)));
        addItemExtension("podcast:socialInteract", "protocol", (item, value) -> item.getPodcastSocialInteracts().getLast().setProtocol(value));
        addItemExtension("podcast:socialInteract", "uri", (item, value) -> item.getPodcastSocialInteracts().getLast().setUri(value));
        addItemExtension("podcast:socialInteract", "accountId", (item, value) -> item.getPodcastSocialInteracts().getLast().setAccountId(value));
        addItemExtension("podcast:socialInteract", "accountUrl", (item, value) -> item.getPodcastSocialInteracts().getLast().setAccountUrl(value));
        addItemExtension("podcast:socialInteract", "priority", (item, value) -> Optional.of(item.getPodcastSocialInteracts().getLast()).ifPresent(socialInteracts -> mapInteger(value, socialInteracts::setPriority)));
    }
}
