package com.apptasticsoftware.rssreader.module.podcast;

import com.apptasticsoftware.rssreader.AbstractRssReader;
import com.apptasticsoftware.rssreader.DateTimeParser;

import java.net.http.HttpClient;
import java.util.Optional;

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
        onChannelTags.put("podcast:block", channel -> channel.addPodcastBlock(new PodcastBlock()));
        onChannelTags.put("podcast:funding", channel -> channel.addPodcastFunding(new PodcastFunding()));
        onChannelTags.put("podcast:location", channel -> channel.addPodcastLocation(new PodcastLocation()));
        onChannelTags.put("podcast:value", channel -> channel.addPodcastValue(new PodcastValue()));
        onChannelTags.put("podcast:valueRecipient", channel -> channel.getPodcastValues().getLast().addValueRecipient(new PodcastValueRecipient()));
        onChannelTags.put("podcast:trailer", channel -> channel.addPodcastTrailer(new PodcastTrailer()));

        addChannelExtension("podcast:guid", PodcastChannel::setPodcastGuid);
        addChannelExtension("podcast:license", (channel, value) -> createIfNull(channel::getPodcastLicense, channel::setPodcastLicense, PodcastLicense::new).setLicense(value));
        addChannelExtension("podcast:locked", (channel, value) -> createIfNullOptional(channel::getPodcastLocked, channel::setPodcastLocked, PodcastLocked::new).ifPresent((locked) -> mapBoolean(value, locked::setLocked)));
        addChannelExtension("podcast:block", (channel, value) -> Optional.ofNullable(channel.getPodcastBlocks().getLast()).ifPresent(block -> mapBoolean(value, block::setBlock)));
        addChannelExtension("podcast:funding", (channel, value) -> Optional.ofNullable(channel.getPodcastFundings().getLast()).ifPresent(funding -> funding.setFunding(value)));
        addChannelExtension("podcast:location", (channel, value) -> Optional.ofNullable(channel.getPodcastLocations().getLast()).ifPresent(location -> location.setLocation(value)));
        addChannelExtension("podcast:medium", PodcastChannel::setPodcastMedium);
        addChannelExtension("podcast:trailer", (channel, value) -> Optional.ofNullable(channel.getPodcastTrailers().getLast()).ifPresent(trailer -> trailer.setTrailer(value)));
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
        addChannelExtension("podcast:valueRecipient", "name", (channel, value) -> Optional.ofNullable(channel.getPodcastValues().getLast().getValueRecipients().getLast()).ifPresent(valueRecipient -> valueRecipient.setName(value)));
        addChannelExtension("podcast:valueRecipient", "type", (channel, value) -> Optional.ofNullable(channel.getPodcastValues().getLast().getValueRecipients().getLast()).ifPresent(valueRecipient -> valueRecipient.setType(value)));
        addChannelExtension("podcast:valueRecipient", "address", (channel, value) -> Optional.ofNullable(channel.getPodcastValues().getLast().getValueRecipients().getLast()).ifPresent(valueRecipient -> valueRecipient.setAddress(value)));
        addChannelExtension("podcast:valueRecipient", "customKey", (channel, value) -> Optional.ofNullable(channel.getPodcastValues().getLast().getValueRecipients().getLast()).ifPresent(valueRecipient -> valueRecipient.setCustomKey(value)));
        addChannelExtension("podcast:valueRecipient", "customValue", (channel, value) -> Optional.ofNullable(channel.getPodcastValues().getLast().getValueRecipients().getLast()).ifPresent(valueRecipient -> valueRecipient.setCustomValue(value)));
        addChannelExtension("podcast:valueRecipient", "split", (channel, value) -> Optional.ofNullable(channel.getPodcastValues().getLast().getValueRecipients().getLast()).ifPresent(valueRecipient -> mapInteger(value, valueRecipient::setSplit)));
        addChannelExtension("podcast:valueRecipient", "fee", (channel, value) -> Optional.ofNullable(channel.getPodcastValues().getLast().getValueRecipients().getLast()).ifPresent(valueRecipient -> mapBoolean(value, valueRecipient::setFee)));
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
        onItemTags.put("podcast:valueRecipient", item ->item.getPodcastValues().getLast().addValueRecipient(new PodcastValueRecipient()));
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
        addItemExtension("podcast:valueRecipient", "name", (item, value) -> item.getPodcastValues().getLast().getValueRecipients().getLast().setName(value));
        addItemExtension("podcast:valueRecipient", "type", (item, value) -> item.getPodcastValues().getLast().getValueRecipients().getLast().setType(value));
        addItemExtension("podcast:valueRecipient", "address", (item, value) -> item.getPodcastValues().getLast().getValueRecipients().getLast().setAddress(value));
        addItemExtension("podcast:valueRecipient", "customKey", (item, value) -> item.getPodcastValues().getLast().getValueRecipients().getLast().setCustomKey(value));
        addItemExtension("podcast:valueRecipient", "customValue", (item, value) -> item.getPodcastValues().getLast().getValueRecipients().getLast().setCustomValue(value));
        addItemExtension("podcast:valueRecipient", "split", (item, value) -> Optional.of(item.getPodcastValues().getLast().getValueRecipients().getLast()).ifPresent(recipient -> mapInteger(value, recipient::setSplit)));
        addItemExtension("podcast:valueRecipient", "fee", (item, value) -> Optional.of(item.getPodcastValues().getLast().getValueRecipients().getLast()).ifPresent(recipient -> mapBoolean(value, recipient::setFee)));
        addItemExtension("podcast:socialInteract", "protocol", (item, value) -> item.getPodcastSocialInteracts().getLast().setProtocol(value));
        addItemExtension("podcast:socialInteract", "uri", (item, value) -> item.getPodcastSocialInteracts().getLast().setUri(value));
        addItemExtension("podcast:socialInteract", "accountId", (item, value) -> item.getPodcastSocialInteracts().getLast().setAccountId(value));
        addItemExtension("podcast:socialInteract", "accountUrl", (item, value) -> item.getPodcastSocialInteracts().getLast().setAccountUrl(value));
        addItemExtension("podcast:socialInteract", "priority", (item, value) -> Optional.of(item.getPodcastSocialInteracts().getLast()).ifPresent(socialInteracts -> mapInteger(value, socialInteracts::setPriority)));
    }
}
