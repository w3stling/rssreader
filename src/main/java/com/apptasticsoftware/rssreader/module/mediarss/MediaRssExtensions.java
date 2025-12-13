package com.apptasticsoftware.rssreader.module.mediarss;

import com.apptasticsoftware.rssreader.FeedExtensionRegistry;
import com.apptasticsoftware.rssreader.util.Mapper;

import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

import static com.apptasticsoftware.rssreader.util.Mapper.*;
import static com.apptasticsoftware.rssreader.util.Mapper.mapDouble;
import static com.apptasticsoftware.rssreader.util.Mapper.mapInteger;
import static com.apptasticsoftware.rssreader.util.Util.getLast;

public class MediaRssExtensions {
    private final static List<String> CHANNEL_PATHS = List.of("/rss/channel/", "/feed/");
    private final static List<String> ITEM_PATHS = List.of("/rss/channel/item/", "/feed/entry/");

    private MediaRssExtensions() {

    }

    public static void register(FeedExtensionRegistry<? extends MediaRssChannel, ? extends MediaRssItem> registry) {
        onChannelTag(registry);
        channelTagExtensions(registry);
        channelAttributeExtensions(registry);

        onItemTag(registry);
        itemTagExtensions(registry);
        itemAttributesExtensions(registry);
    }

    @SuppressWarnings("java:S1192")
    private static void onChannelTag(FeedExtensionRegistry<? extends MediaRssChannel, ? extends MediaRssItem> registry) {
        registry.addOnChannelTag(CHANNEL_PATHS, "media:rating", channel -> channel.addMediaRating(new MediaRating()));
        registry.addOnChannelTag(CHANNEL_PATHS, "media:thumbnail", channel -> channel.addMediaThumbnail(new MediaThumbnail()));
        registry.addOnChannelTag(CHANNEL_PATHS, "media:category", channel -> channel.addMediaCategory(new MediaCategory()));
        registry.addOnChannelTag(CHANNEL_PATHS, "media:hash", channel -> channel.addMediaHash(new MediaHash()));
        registry.addOnChannelTag(CHANNEL_PATHS, "media:credit", channel -> channel.addMediaCredit(new MediaCredit()));
        registry.addOnChannelTag(CHANNEL_PATHS, "media:text", channel -> channel.addMediaText(new MediaText()));
        registry.addOnChannelTag(CHANNEL_PATHS, "media:restriction", channel -> channel.addMediaRestriction(new MediaRestriction()));
        registry.addOnChannelTag(CHANNEL_PATHS, "media:price", channel -> channel.addMediaPrice(new MediaPrice()));
        registry.addOnChannelTag(CHANNEL_PATHS, "media:license", channel -> channel.addMediaLicense(new MediaLicense()));
        registry.addOnChannelTag(CHANNEL_PATHS, "media:subTitle", channel -> channel.addMediaSubTitle(new MediaSubTitle()));
        registry.addOnChannelTag(CHANNEL_PATHS, "media:peerLink", channel -> channel.addMediaPeerLink(new MediaPeerLink()));
        registry.addOnChannelTag(CHANNEL_PATHS, "media:location", channel -> channel.addMediaLocation(new MediaLocation()));
        registry.addOnChannelTag(CHANNEL_PATHS, "media:scenes/media:scene", channel -> channel.addMediaScene(new MediaScene()));
    }

    @SuppressWarnings("java:S1192")
    private static void channelTagExtensions(FeedExtensionRegistry<? extends MediaRssChannel, ? extends MediaRssItem> extensions) {
        extensions.addChannelExtension(CHANNEL_PATHS, "media:rating", (channel, value) -> channelMediaRating(channel, value, MediaRating::setRating));
        extensions.addChannelExtension(CHANNEL_PATHS, "media:title", (channel, value) -> channelMediaTitle(channel, value, MediaTitle::setTitle));
        extensions.addChannelExtension(CHANNEL_PATHS, "media:description", (channel, value) -> channelMediaDescription(channel, value, MediaDescription::setDescription));
        extensions.addChannelExtension(CHANNEL_PATHS, "media:keywords", (channel, value) -> channelMediaKeywords(channel, value, MediaRssChannel::addMediaKeyword));
        extensions.addChannelExtension(CHANNEL_PATHS, "media:category", (channel, value) -> channelMediaCategory(channel, value, MediaCategory::setCategory));
        extensions.addChannelExtension(CHANNEL_PATHS, "media:hash", (channel, value) -> channelMediaHash(channel, value, MediaHash::setHash));
        extensions.addChannelExtension(CHANNEL_PATHS, "media:credit", (channel, value) -> channelMediaCredit(channel, value, MediaCredit::setCredit));
        extensions.addChannelExtension(CHANNEL_PATHS, "media:copyright", (channel, value) -> channelMediaCopyright(channel, value, MediaCopyright::setCopyright));
        extensions.addChannelExtension(CHANNEL_PATHS, "media:text", (channel, value) -> channelMediaText(channel, value, MediaText::setText));
        extensions.addChannelExtension(CHANNEL_PATHS, "media:restriction", (channel, value) -> channelMediaRestriction(channel, value, MediaRestriction::setRestriction));
        extensions.addChannelExtension(CHANNEL_PATHS, "media:community/media:tags", (channel, value) -> channelMediaCommunityMediaTags(channel, value, MediaTags::setTags));
        extensions.addChannelExtension(CHANNEL_PATHS, "media:comments/media:comment", MediaRssChannel::addMediaComment);
        extensions.addChannelExtension(CHANNEL_PATHS, "media:embed/media:param", (channel, value) -> channelMediaEmbed(channel, value, MediaEmbed::addParamValue));
        extensions.addChannelExtension(CHANNEL_PATHS, "media:responses/media:response", MediaRssChannel::addMediaResponse);
        extensions.addChannelExtension(CHANNEL_PATHS, "media:backLinks/media:backLink", MediaRssChannel::addMediaBackLink);
        extensions.addChannelExtension(CHANNEL_PATHS, "media:license", (channel, value) -> channelMediaLicense(channel, value, MediaLicense::setLicense));
        extensions.addChannelExtension(CHANNEL_PATHS, "media:scenes/media:scene/sceneTitle", (channel, value) -> channelMediaScene(channel, value, MediaScene::setSceneTitle));
        extensions.addChannelExtension(CHANNEL_PATHS, "media:scenes/media:scene/sceneDescription", (channel, value) -> channelMediaScene(channel, value, MediaScene::setSceneDescription));
        extensions.addChannelExtension(CHANNEL_PATHS, "media:scenes/media:scene/sceneStartTime", (channel, value) -> channelMediaScene(channel, value, MediaScene::setSceneStartTime));
        extensions.addChannelExtension(CHANNEL_PATHS, "media:scenes/media:scene/sceneEndTime", (channel, value) -> channelMediaScene(channel, value, MediaScene::setSceneEndTime));
    }

    @SuppressWarnings("java:S1192")
    private static void channelAttributeExtensions(FeedExtensionRegistry<? extends MediaRssChannel, ? extends MediaRssItem> registry) {
        registry.addChannelExtension(CHANNEL_PATHS, "media:rating","scheme", (channel, value) -> channelMediaRating(channel, value, MediaRating::setScheme));
        registry.addChannelExtension(CHANNEL_PATHS, "media:title", "type", (channel, value) -> channelMediaTitle(channel, value, MediaTitle::setType));
        registry.addChannelExtension(CHANNEL_PATHS, "media:description", "type", (channel, value) -> channelMediaDescription(channel, value, MediaDescription::setType));
        registry.addChannelExtension(CHANNEL_PATHS, "media:thumbnail", "url", (channel, value) -> channelMediaThumbnail(channel, value, MediaThumbnail::setUrl));
        registry.addChannelExtension(CHANNEL_PATHS, "media:thumbnail", "height", (channel, value) -> channelMediaThumbnail(channel, value, (thumbnail, v) -> mapInteger(v, thumbnail::setHeight)));
        registry.addChannelExtension(CHANNEL_PATHS, "media:thumbnail", "width", (channel, value) -> channelMediaThumbnail(channel, value, (thumbnail, v) -> mapInteger(v, thumbnail::setWidth)));
        registry.addChannelExtension(CHANNEL_PATHS, "media:thumbnail", "time", (channel, value) -> channelMediaThumbnail(channel, value, MediaThumbnail::setTime));
        registry.addChannelExtension(CHANNEL_PATHS, "media:category", "scheme", (channel, value) -> channelMediaCategory(channel, value, MediaCategory::setScheme));
        registry.addChannelExtension(CHANNEL_PATHS, "media:category", "label", (channel, value) -> channelMediaCategory(channel, value, MediaCategory::setLabel));
        registry.addChannelExtension(CHANNEL_PATHS, "media:hash", "algo", (channel, value) -> channelMediaHash(channel, value, MediaHash::setAlgorithm));
        registry.addChannelExtension(CHANNEL_PATHS, "media:player", "url", (channel, value) -> channelMediaPlayer(channel, value, MediaPlayer::setUrl));
        registry.addChannelExtension(CHANNEL_PATHS, "media:player", "height", (channel, value) -> channelMediaPlayer(channel, value, (player, v) -> mapInteger(v, player::setHeight)));
        registry.addChannelExtension(CHANNEL_PATHS, "media:player", "width", (channel, value) -> channelMediaPlayer(channel, value, (player, v) -> mapInteger(v, player::setWidth)));
        registry.addChannelExtension(CHANNEL_PATHS, "media:credit", "role", (channel, value) -> channelMediaCredit(channel, value, MediaCredit::setRole));
        registry.addChannelExtension(CHANNEL_PATHS, "media:credit", "scheme", (channel, value) -> channelMediaCredit(channel, value, MediaCredit::setScheme));
        registry.addChannelExtension(CHANNEL_PATHS, "media:copyright", "url", (channel, value) -> channelMediaCopyright(channel, value, MediaCopyright::setUrl));
        registry.addChannelExtension(CHANNEL_PATHS, "media:text", "type", (channel, value) -> channelMediaText(channel, value, MediaText::setType));
        registry.addChannelExtension(CHANNEL_PATHS, "media:text", "lang", (channel, value) -> channelMediaText(channel, value, MediaText::setLang));
        registry.addChannelExtension(CHANNEL_PATHS, "media:text", "start", (channel, value) -> channelMediaText(channel, value, MediaText::setStart));
        registry.addChannelExtension(CHANNEL_PATHS, "media:text", "end", (channel, value) -> channelMediaText(channel, value, MediaText::setEnd));
        registry.addChannelExtension(CHANNEL_PATHS, "media:restriction", "type", (channel, value) -> channelMediaRestriction(channel, value, MediaRestriction::setType));
        registry.addChannelExtension(CHANNEL_PATHS, "media:restriction", "relationship", (channel, value) -> channelMediaRestriction(channel, value, MediaRestriction::setRelationship));
        registry.addChannelExtension(CHANNEL_PATHS, "media:community/media:starRating", "average", (channel, value) -> channelMediaCommunityMediaStarRating(channel, value, (starRating, v) -> mapDouble(v, starRating::setAverage)));
        registry.addChannelExtension(CHANNEL_PATHS, "media:community/media:starRating", "count", (channel, value) -> channelMediaCommunityMediaStarRating(channel, value, (starRating, v) -> mapInteger(v, starRating::setCount)));
        registry.addChannelExtension(CHANNEL_PATHS, "media:community/media:starRating", "max", (channel, value) -> channelMediaCommunityMediaStarRating(channel, value, (starRating, v) -> mapInteger(v, starRating::setMax)));
        registry.addChannelExtension(CHANNEL_PATHS, "media:community/media:starRating", "min", (channel, value) -> channelMediaCommunityMediaStarRating(channel, value, (starRating, v) -> mapInteger(v, starRating::setMin)));
        registry.addChannelExtension(CHANNEL_PATHS, "media:community/media:statistics", "views", (channel, value) -> channelMediaCommunityMediaStatistics(channel, value, (statistics, v) -> mapLong(v, statistics::setViews)));
        registry.addChannelExtension(CHANNEL_PATHS, "media:community/media:statistics", "favorites", (channel, value) -> channelMediaCommunityMediaStatistics(channel, value, (statistics, v) -> mapInteger(v, statistics::setFavorites)));
        registry.addChannelExtension(CHANNEL_PATHS, "media:embed", "url", (channel, value) -> channelMediaEmbed(channel, value, MediaEmbed::setUrl));
        registry.addChannelExtension(CHANNEL_PATHS, "media:embed", "width", (channel, value) -> channelMediaEmbed(channel, value, (c, v) -> mapInteger(v, c::setWidth)));
        registry.addChannelExtension(CHANNEL_PATHS, "media:embed", "height", (channel, value) -> channelMediaEmbed(channel, value, (c, v) -> mapInteger(v, c::setHeight)));
        registry.addChannelExtension(CHANNEL_PATHS, "media:embed/media:param", "name", (channel, value) -> channelMediaEmbed(channel, value, MediaEmbed::addParamName));
        registry.addChannelExtension(CHANNEL_PATHS, "media:status", "reason",  (channel, value) -> channelMediaStatus(channel, value, MediaStatus::setReason));
        registry.addChannelExtension(CHANNEL_PATHS, "media:status", "state",  (channel, value) -> channelMediaStatus(channel, value, MediaStatus::setState));
        registry.addChannelExtension(CHANNEL_PATHS, "media:price", "type",  (channel, value) -> channelMediaPrice(channel, value, MediaPrice::setType));
        registry.addChannelExtension(CHANNEL_PATHS, "media:price", "price",  (channel, value) -> channelMediaPrice(channel, value, (price, v) -> mapDouble(v, price::setPrice)));
        registry.addChannelExtension(CHANNEL_PATHS, "media:price", "currency",  (channel, value) -> channelMediaPrice(channel, value, MediaPrice::setCurrency));
        registry.addChannelExtension(CHANNEL_PATHS, "media:price", "info",  (channel, value) -> channelMediaPrice(channel, value, MediaPrice::setInfo));
        registry.addChannelExtension(CHANNEL_PATHS, "media:license", "type",  (channel, value) -> channelMediaLicense(channel, value, MediaLicense::setType));
        registry.addChannelExtension(CHANNEL_PATHS, "media:license", "href",  (channel, value) -> channelMediaLicense(channel, value, MediaLicense::setHref));
        registry.addChannelExtension(CHANNEL_PATHS, "media:subTitle", "type",  (channel, value) -> channelMediaSubTitle(channel, value, MediaSubTitle::setType));
        registry.addChannelExtension(CHANNEL_PATHS, "media:subTitle", "lang",  (channel, value) -> channelMediaSubTitle(channel, value, MediaSubTitle::setLang));
        registry.addChannelExtension(CHANNEL_PATHS, "media:subTitle", "href",  (channel, value) -> channelMediaSubTitle(channel, value, MediaSubTitle::setHref));
        registry.addChannelExtension(CHANNEL_PATHS, "media:peerLink", "type",  (channel, value) -> channelMediaPeerLink(channel, value, MediaPeerLink::setType));
        registry.addChannelExtension(CHANNEL_PATHS, "media:peerLink", "href",  (channel, value) -> channelMediaPeerLink(channel, value, MediaPeerLink::setHref));
        registry.addChannelExtension(CHANNEL_PATHS, "media:location", "description", (channel, value) -> channelMediaLocation(channel, value, MediaLocation::setDescription));
        registry.addChannelExtension(CHANNEL_PATHS, "media:location", "start", (channel, value) -> channelMediaLocation(channel, value, MediaLocation::setStart));
        registry.addChannelExtension(CHANNEL_PATHS, "media:location", "end", (channel, value) -> channelMediaLocation(channel, value, MediaLocation::setEnd));
        registry.addChannelExtension(CHANNEL_PATHS, "media:rights", "status", (channel, value) -> channelMediaRights(channel, value, MediaRights::setStatus));
    }

    @SuppressWarnings("java:S1192")
    private static void onItemTag(FeedExtensionRegistry<? extends MediaRssChannel, ? extends MediaRssItem> registry) {
        // media:content
        registry.addOnItemTag(ITEM_PATHS, "media:content", item -> item.addMediaContents(new MediaContent()));
        registry.addOnItemTag(ITEM_PATHS, "media:content/media:rating", item -> Optional.ofNullable(getLast(item.getMediaContents())).ifPresent(c -> c.addMediaRating(new MediaRating())));
        registry.addOnItemTag(ITEM_PATHS, "media:content/media:thumbnail", item -> Optional.ofNullable(getLast(item.getMediaContents())).ifPresent(c -> c.addMediaThumbnail(new MediaThumbnail())));
        registry.addOnItemTag(ITEM_PATHS, "media:content/media:category", item -> Optional.ofNullable(getLast(item.getMediaContents())).ifPresent(c -> c.addMediaCategory(new MediaCategory())));
        registry.addOnItemTag(ITEM_PATHS, "media:content/media:hash", item -> Optional.ofNullable(getLast(item.getMediaContents())).ifPresent(c -> c.addMediaHash(new MediaHash())));
        registry.addOnItemTag(ITEM_PATHS, "media:content/media:credit", item -> Optional.ofNullable(getLast(item.getMediaContents())).ifPresent(c -> c.addMediaCredit(new MediaCredit())));
        registry.addOnItemTag(ITEM_PATHS, "media:content/media:text", item -> Optional.ofNullable(getLast(item.getMediaContents())).ifPresent(c -> c.addMediaText(new MediaText())));
        registry.addOnItemTag(ITEM_PATHS, "media:content/media:restriction", item -> Optional.ofNullable(getLast(item.getMediaContents())).ifPresent(c -> c.addMediaRestriction(new MediaRestriction())));
        registry.addOnItemTag(ITEM_PATHS, "media:content/media:price", item -> Optional.ofNullable(getLast(item.getMediaContents())).ifPresent(c -> c.addMediaPrice(new MediaPrice())));
        registry.addOnItemTag(ITEM_PATHS, "media:content/media:license", item -> Optional.ofNullable(getLast(item.getMediaContents())).ifPresent(c -> c.addMediaLicense(new MediaLicense())));
        registry.addOnItemTag(ITEM_PATHS, "media:content/media:subTitle", item -> Optional.ofNullable(getLast(item.getMediaContents())).ifPresent(c -> c.addMediaSubTitle(new MediaSubTitle())));
        registry.addOnItemTag(ITEM_PATHS, "media:content/media:peerLink", item -> Optional.ofNullable(getLast(item.getMediaContents())).ifPresent(c -> c.addMediaPeerLink(new MediaPeerLink())));
        registry.addOnItemTag(ITEM_PATHS, "media:content/media:location", item -> Optional.ofNullable(getLast(item.getMediaContents())).ifPresent(c -> c.addMediaLocation(new MediaLocation())));
        registry.addOnItemTag(ITEM_PATHS, "media:content/media:scenes/media:scene", item -> Optional.ofNullable(getLast(item.getMediaContents())).ifPresent(c -> c.addMediaScene(new MediaScene())));

        // media:group
        registry.addOnItemTag(ITEM_PATHS, "media:group/media:rating", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.addMediaRating(new MediaRating())));
        registry.addOnItemTag(ITEM_PATHS, "media:group/media:thumbnail", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.addMediaThumbnail(new MediaThumbnail())));
        registry.addOnItemTag(ITEM_PATHS, "media:group/media:category", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.addMediaCategory(new MediaCategory())));
        registry.addOnItemTag(ITEM_PATHS, "media:group/media:hash", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.addMediaHash(new MediaHash())));
        registry.addOnItemTag(ITEM_PATHS, "media:group/media:credit", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.addMediaCredit(new MediaCredit())));
        registry.addOnItemTag(ITEM_PATHS, "media:group/media:text", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.addMediaText(new MediaText())));
        registry.addOnItemTag(ITEM_PATHS, "media:group/media:restriction", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.addMediaRestriction(new MediaRestriction())));
        registry.addOnItemTag(ITEM_PATHS, "media:group/media:price", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.addMediaPrice(new MediaPrice())));
        registry.addOnItemTag(ITEM_PATHS, "media:group/media:license", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.addMediaLicense(new MediaLicense())));
        registry.addOnItemTag(ITEM_PATHS, "media:group/media:subTitle", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.addMediaSubTitle(new MediaSubTitle())));
        registry.addOnItemTag(ITEM_PATHS, "media:group/media:peerLink", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.addMediaPeerLink(new MediaPeerLink())));
        registry.addOnItemTag(ITEM_PATHS, "media:group/media:location", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.addMediaLocation(new MediaLocation())));
        registry.addOnItemTag(ITEM_PATHS, "media:group/media:scenes/media:scene", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.addMediaScene(new MediaScene())));

        // media:group / media:content
        registry.addOnItemTag(ITEM_PATHS, "media:group/media:content", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.addMediaContent(new MediaContent())));
        registry.addOnItemTag(ITEM_PATHS, "media:group/media:content/media:rating", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> getLast(g.getMediaContents()).addMediaRating(new MediaRating())));
        registry.addOnItemTag(ITEM_PATHS, "media:group/media:content/media:thumbnail", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> getLast(g.getMediaContents()).addMediaThumbnail(new MediaThumbnail())));
        registry.addOnItemTag(ITEM_PATHS, "media:group/media:content/media:category", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> getLast(g.getMediaContents()).addMediaCategory(new MediaCategory())));
        registry.addOnItemTag(ITEM_PATHS, "media:group/media:content/media:hash", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> getLast(g.getMediaContents()).addMediaHash(new MediaHash())));
        registry.addOnItemTag(ITEM_PATHS, "media:group/media:content/media:credit", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> getLast(g.getMediaContents()).addMediaCredit(new MediaCredit())));
        registry.addOnItemTag(ITEM_PATHS, "media:group/media:content/media:text", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> getLast(g.getMediaContents()).addMediaText(new MediaText())));
        registry.addOnItemTag(ITEM_PATHS, "media:group/media:content/media:restriction", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> getLast(g.getMediaContents()).addMediaRestriction(new MediaRestriction())));
        registry.addOnItemTag(ITEM_PATHS, "media:group/media:content/media:price", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> getLast(g.getMediaContents()).addMediaPrice(new MediaPrice())));
        registry.addOnItemTag(ITEM_PATHS, "media:group/media:content/media:license", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> getLast(g.getMediaContents()).addMediaLicense(new MediaLicense())));
        registry.addOnItemTag(ITEM_PATHS, "media:group/media:content/media:subTitle", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> getLast(g.getMediaContents()).addMediaSubTitle(new MediaSubTitle())));
        registry.addOnItemTag(ITEM_PATHS, "media:group/media:content/media:peerLink", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> getLast(g.getMediaContents()).addMediaPeerLink(new MediaPeerLink())));
        registry.addOnItemTag(ITEM_PATHS, "media:group/media:content/media:location", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> getLast(g.getMediaContents()).addMediaLocation(new MediaLocation())));
        registry.addOnItemTag(ITEM_PATHS, "media:group/media:content/media:scenes/media:scene", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> getLast(g.getMediaContents()).addMediaScene(new MediaScene())));

        // item
        registry.addOnItemTag(ITEM_PATHS, "media:rating", item -> item.addMediaRating(new MediaRating()));
        registry.addOnItemTag(ITEM_PATHS, "media:thumbnail", item -> item.addMediaThumbnail(new MediaThumbnail()));
        registry.addOnItemTag(ITEM_PATHS, "media:category", item -> item.addMediaCategory(new MediaCategory()));
        registry.addOnItemTag(ITEM_PATHS, "media:hash", item -> item.addMediaHash(new MediaHash()));
        registry.addOnItemTag(ITEM_PATHS, "media:credit", item -> item.addMediaCredit(new MediaCredit()));
        registry.addOnItemTag(ITEM_PATHS, "media:text", item -> item.addMediaText(new MediaText()));
        registry.addOnItemTag(ITEM_PATHS, "media:restriction", item -> item.addMediaRestriction(new MediaRestriction()));
        registry.addOnItemTag(ITEM_PATHS, "media:price", item -> item.addMediaPrice(new MediaPrice()));
        registry.addOnItemTag(ITEM_PATHS, "media:license", item -> item.addMediaLicense(new MediaLicense()));
        registry.addOnItemTag(ITEM_PATHS, "media:subTitle", item -> item.addMediaSubTitle(new MediaSubTitle()));
        registry.addOnItemTag(ITEM_PATHS, "media:peerLink", item -> item.addMediaPeerLink(new MediaPeerLink()));
        registry.addOnItemTag(ITEM_PATHS, "media:location", item -> item.addMediaLocation(new MediaLocation()));
        registry.addOnItemTag(ITEM_PATHS, "media:scenes/media:scene", item -> item.addMediaScene(new MediaScene()));
    }

    @SuppressWarnings("java:S1192")
    private static void itemTagExtensions(FeedExtensionRegistry<? extends MediaRssChannel, ? extends MediaRssItem> registry) {
        registry.addItemExtension(ITEM_PATHS, "media:content/media:rating", (item, value) -> itemMediaContentMediaRating(item, value, MediaRating::setRating));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:title", (item, value) -> itemMediaContentMediaTitle(item, value, MediaTitle::setTitle));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:description", (item, value) -> itemMediaContentMediaDescription(item, value, MediaDescription::setDescription));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:keywords", (item, value) -> itemMediaContentMediaKeywords(item, value, MediaContent::addMediaKeyword));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:category", (item, value) -> itemMediaContentMediaCategory(item, value, MediaCategory::setCategory));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:hash", (item, value) -> itemMediaContentMediaHash(item, value, MediaHash::setHash));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:credit", (item, value) -> itemMediaContentMediaCredit(item, value, MediaCredit::setCredit));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:copyright", (item, value) -> itemMediaContentMediaCopyright(item, value, MediaCopyright::setCopyright));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:text", (item, value) -> itemMediaContentMediaText(item, value, MediaText::setText));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:restriction", (item, value) -> itemMediaContentMediaRestriction(item, value, MediaRestriction::setRestriction));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:community/media:tags", (item, value) -> itemMediaContentMediaCommunityMediaTags(item, value, MediaTags::setTags));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:comments/media:comment", (item, value) -> itemMediaContentStringList(item, value, MediaContent::addMediaComment));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:embed/media:param", (item, value) -> itemMediaContentMediaEmbed(item, value, MediaEmbed::addParamValue));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:responses/media:response", (item, value) -> itemMediaContentStringList(item, value, MediaContent::addMediaResponse));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:backLinks/media:backLink", (item, value) -> itemMediaContentStringList(item, value, MediaContent::addMediaBackLink));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:license", (item, value) -> itemMediaContentMediaLicense(item, value, MediaLicense::setLicense));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:scenes/media:scene/sceneTitle", (item, value) -> itemMediaContentMediaScene(item, value, MediaScene::setSceneTitle));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:scenes/media:scene/sceneDescription", (item, value) -> itemMediaContentMediaScene(item, value, MediaScene::setSceneDescription));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:scenes/media:scene/sceneStartTime", (item, value) -> itemMediaContentMediaScene(item, value, MediaScene::setSceneStartTime));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:scenes/media:scene/sceneEndTime", (item, value) -> itemMediaContentMediaScene(item, value, MediaScene::setSceneEndTime));

        registry.addItemExtension(ITEM_PATHS, "media:group/media:rating", (item, value) -> itemMediaGroupMediaRating(item, value, MediaRating::setRating));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:title", (item, value) -> itemMediaGroupMediaTitle(item, value, MediaTitle::setTitle));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:description", (item, value) -> itemMediaGroupMediaDescription(item, value, MediaDescription::setDescription));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:keywords", (item, value) -> itemMediaGroupMediaKeywords(item, value, MediaGroup::addMediaKeyword));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:category", (item, value) -> itemMediaGroupMediaCategory(item, value, MediaCategory::setCategory));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:hash", (item, value) -> itemMediaGroupMediaHash(item, value, MediaHash::setHash));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:credit", (item, value) -> itemMediaGroupMediaCredit(item, value, MediaCredit::setCredit));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:copyright", (item, value) -> itemMediaGroupMediaCopyright(item, value, MediaCopyright::setCopyright));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:text", (item, value) -> itemMediaGroupMediaText(item, value, MediaText::setText));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:restriction", (item, value) -> itemMediaGroupMediaRestriction(item, value, MediaRestriction::setRestriction));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:community/media:tags", (item, value) -> itemMediaGroupMediaCommunityMediaTags(item, value, MediaTags::setTags));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:comments/media:comment", (item, value) -> itemMediaGroupStringList(item, value, MediaGroup::addMediaComment));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:embed/media:param", (item, value) -> itemMediaGroupMediaEmbed(item, value, MediaEmbed::addParamValue));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:responses/media:response", (item, value) -> itemMediaGroupStringList(item, value, MediaGroup::addMediaResponse));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:backLinks/media:backLink", (item, value) -> itemMediaGroupStringList(item, value, MediaGroup::addMediaBackLink));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:license", (item, value) -> itemMediaGroupMediaLicense(item, value, MediaLicense::setLicense));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:scenes/media:scene/sceneTitle", (item, value) -> itemMediaGroupMediaScene(item, value, MediaScene::setSceneTitle));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:scenes/media:scene/sceneDescription", (item, value) -> itemMediaGroupMediaScene(item, value, MediaScene::setSceneDescription));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:scenes/media:scene/sceneStartTime", (item, value) -> itemMediaGroupMediaScene(item, value, MediaScene::setSceneStartTime));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:scenes/media:scene/sceneEndTime", (item, value) -> itemMediaGroupMediaScene(item, value, MediaScene::setSceneEndTime));

        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:rating", (item, value) -> itemMediaGroupMediaContentMediaRating(item, value, MediaRating::setRating));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:title", (item, value) -> itemMediaGroupMediaContentMediaTitle(item, value, MediaTitle::setTitle));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:description", (item, value) -> itemMediaGroupMediaContentMediaDescription(item, value, MediaDescription::setDescription));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:keywords", (item, value) -> itemMediaGroupMediaContentMediaKeywords(item, value, MediaContent::addMediaKeyword));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:category", (item, value) -> itemMediaGroupMediaContentMediaCategory(item, value, MediaCategory::setCategory));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:hash", (item, value) -> itemMediaGroupMediaContentMediaHash(item, value, MediaHash::setHash));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:credit", (item, value) -> itemMediaGroupMediaContentMediaCredit(item, value, MediaCredit::setCredit));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:copyright", (item, value) -> itemMediaGroupMediaContentMediaCopyright(item, value, MediaCopyright::setCopyright));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:text", (item, value) -> itemMediaGroupMediaContentMediaText(item, value, MediaText::setText));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:restriction", (item, value) -> itemMediaGroupMediaContentMediaRestriction(item, value, MediaRestriction::setRestriction));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:community/media:tags", (item, value) -> itemMediaGroupMediaContentMediaCommunityMediaTags(item, value, MediaTags::setTags));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:comments/media:comment", (item, value) -> itemMediaGroupMediaContentStringList(item, value, MediaContent::addMediaComment));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:embed/media:param", (item, value) -> itemMediaGroupMediaContentMediaEmbed(item, value, MediaEmbed::addParamValue));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:responses/media:response", (item, value) -> itemMediaGroupMediaContentStringList(item, value, MediaContent::addMediaResponse));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:backLinks/media:backLink", (item, value) -> itemMediaGroupMediaContentStringList(item, value, MediaContent::addMediaBackLink));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:license", (item, value) -> itemMediaGroupMediaContentMediaLicense(item, value, MediaLicense::setLicense));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:scenes/media:scene/sceneTitle", (item, value) -> itemMediaGroupMediaContentMediaScene(item, value, MediaScene::setSceneTitle));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:scenes/media:scene/sceneDescription", (item, value) -> itemMediaGroupMediaContentMediaScene(item, value, MediaScene::setSceneDescription));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:scenes/media:scene/sceneStartTime", (item, value) -> itemMediaGroupMediaContentMediaScene(item, value, MediaScene::setSceneStartTime));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:scenes/media:scene/sceneEndTime", (item, value) -> itemMediaGroupMediaContentMediaScene(item, value, MediaScene::setSceneEndTime));

        registry.addItemExtension(ITEM_PATHS, "media:rating", (item, value) -> itemMediaRating(item, value, MediaRating::setRating));
        registry.addItemExtension(ITEM_PATHS, "media:title", (item, value) -> itemMediaTitle(item, value, MediaTitle::setTitle));
        registry.addItemExtension(ITEM_PATHS, "media:description", (item, value) -> itemMediaDescription(item, value, MediaDescription::setDescription));
        registry.addItemExtension(ITEM_PATHS, "media:keywords", (item, value) -> itemMediaKeywords(item, value, MediaRssItem::addMediaKeyword));
        registry.addItemExtension(ITEM_PATHS, "media:category", (item, value) -> itemMediaCategory(item, value, MediaCategory::setCategory));
        registry.addItemExtension(ITEM_PATHS, "media:hash", (item, value) -> itemMediaHash(item, value, MediaHash::setHash));
        registry.addItemExtension(ITEM_PATHS, "media:credit", (item, value) -> itemMediaCredit(item, value, MediaCredit::setCredit));
        registry.addItemExtension(ITEM_PATHS, "media:copyright", (item, value) -> itemMediaCopyright(item, value, MediaCopyright::setCopyright));
        registry.addItemExtension(ITEM_PATHS, "media:text", (item, value) -> itemMediaText(item, value, MediaText::setText));
        registry.addItemExtension(ITEM_PATHS, "media:restriction", (item, value) -> itemMediaRestriction(item, value, MediaRestriction::setRestriction));
        registry.addItemExtension(ITEM_PATHS, "media:community/media:tags", (item, value) -> itemMediaCommunityMediaTags(item, value, MediaTags::setTags));
        registry.addItemExtension(ITEM_PATHS, "media:comments/media:comment", MediaRssItem::addMediaComment);
        registry.addItemExtension(ITEM_PATHS, "media:embed/media:param", (item, value) -> itemMediaEmbed(item, value, MediaEmbed::addParamValue));
        registry.addItemExtension(ITEM_PATHS, "media:responses/media:response", MediaRssItem::addMediaResponse);
        registry.addItemExtension(ITEM_PATHS, "media:backLinks/media:backLink", MediaRssItem::addMediaBackLink);
        registry.addItemExtension(ITEM_PATHS, "media:license", (item, value) -> itemMediaLicense(item, value, MediaLicense::setLicense));
        registry.addItemExtension(ITEM_PATHS, "media:scenes/media:scene/sceneTitle", (item, value) -> itemMediaScene(item, value, MediaScene::setSceneTitle));
        registry.addItemExtension(ITEM_PATHS, "media:scenes/media:scene/sceneDescription", (item, value) -> itemMediaScene(item, value, MediaScene::setSceneDescription));
        registry.addItemExtension(ITEM_PATHS, "media:scenes/media:scene/sceneStartTime", (item, value) -> itemMediaScene(item, value, MediaScene::setSceneStartTime));
        registry.addItemExtension(ITEM_PATHS, "media:scenes/media:scene/sceneEndTime", (item, value) -> itemMediaScene(item, value, MediaScene::setSceneEndTime));
    }

    @SuppressWarnings("java:S1192")
    private static void itemAttributesExtensions(FeedExtensionRegistry<? extends MediaRssChannel, ? extends MediaRssItem> registry) {
        // media:content
        registry.addItemExtension(ITEM_PATHS, "media:content", "url", (item, value) -> itemMediaContent(item, value, MediaContent::setUrl));
        registry.addItemExtension(ITEM_PATHS, "media:content", "fileSize", (item, value) -> itemMediaContent(item, value, (content, v) -> mapLong(v, content::setFileSize)));
        registry.addItemExtension(ITEM_PATHS, "media:content", "type", (item, value) -> itemMediaContent(item, value, MediaContent::setType));
        registry.addItemExtension(ITEM_PATHS, "media:content", "medium", (item, value) -> itemMediaContent(item, value, MediaContent::setMedium));
        registry.addItemExtension(ITEM_PATHS, "media:content", "isDefault", (item, value) -> itemMediaContent(item, value, (content, v) -> mapBoolean(v, content::setDefault)));
        registry.addItemExtension(ITEM_PATHS, "media:content", "expression", (item, value) -> itemMediaContent(item, value, MediaContent::setExpression));
        registry.addItemExtension(ITEM_PATHS, "media:content", "bitrate", (item, value) -> itemMediaContent(item, value, (content, v) -> mapDouble(v, content::setBitRate)));
        registry.addItemExtension(ITEM_PATHS, "media:content", "framerate", (item, value) -> itemMediaContent(item, value, (content, v) -> mapDouble(v, content::setFrameRate)));
        registry.addItemExtension(ITEM_PATHS, "media:content", "samplingrate", (item, value) -> itemMediaContent(item, value, (content, v) -> mapDouble(v, content::setSamplingRate)));
        registry.addItemExtension(ITEM_PATHS, "media:content", "channels", (item, value) -> itemMediaContent(item, value, (content, v) -> mapInteger(v, content::setChannels)));
        registry.addItemExtension(ITEM_PATHS, "media:content", "duration", (item, value) -> itemMediaContent(item, value, (content, v) -> mapInteger(v, content::setDuration)));
        registry.addItemExtension(ITEM_PATHS, "media:content", "height", (item, value) -> itemMediaContent(item, value, (content, v) -> mapInteger(v, content::setHeight)));
        registry.addItemExtension(ITEM_PATHS, "media:content", "width", (item, value) -> itemMediaContent(item, value, (content, v) -> mapInteger(v, content::setWidth)));
        registry.addItemExtension(ITEM_PATHS, "media:content", "lang", (item, value) -> itemMediaContent(item, value, MediaContent::setLang));

        registry.addItemExtension(ITEM_PATHS, "media:content/media:rating", "scheme", (item, value) -> itemMediaContentMediaRating(item, value, MediaRating::setScheme));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:title", "type", (item, value) -> itemMediaContentMediaTitle(item, value, MediaTitle::setType));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:description", "type", (item, value) -> itemMediaContentMediaDescription(item, value, MediaDescription::setType));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:thumbnail", "url", (item, value) -> itemMediaContentMediaThumbnail(item, value, MediaThumbnail::setUrl));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:thumbnail", "height", (item, value) -> itemMediaContentMediaThumbnail(item, value, (thumbnail, v) -> mapInteger(v, thumbnail::setHeight)));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:thumbnail", "width", (item, value) -> itemMediaContentMediaThumbnail(item, value, (thumbnail, v) -> mapInteger(v, thumbnail::setWidth)));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:thumbnail", "time", (item, value) -> itemMediaContentMediaThumbnail(item, value, MediaThumbnail::setTime));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:category", "scheme", (item, value) -> itemMediaContentMediaCategory(item, value, MediaCategory::setScheme));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:category", "label", (item, value) -> itemMediaContentMediaCategory(item, value, MediaCategory::setLabel));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:hash", "algo", (item, value) -> itemMediaContentMediaHash(item, value, MediaHash::setAlgorithm));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:player", "url", (item, value) -> itemMediaContentMediaPlayer(item, value, MediaPlayer::setUrl));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:player", "height", (item, value) -> itemMediaContentMediaPlayer(item, value, (player, v) -> mapInteger(v, player::setHeight)));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:player", "width", (item, value) -> itemMediaContentMediaPlayer(item, value, (player, v) -> mapInteger(v, player::setWidth)));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:credit", "role", (item, value) -> itemMediaContentMediaCredit(item, value, MediaCredit::setRole));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:credit", "scheme", (item, value) -> itemMediaContentMediaCredit(item, value, MediaCredit::setScheme));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:copyright", "url", (item, value) -> itemMediaContentMediaCopyright(item, value, MediaCopyright::setUrl));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:text", "type", (item, value) -> itemMediaContentMediaText(item, value, MediaText::setType));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:text", "lang", (item, value) -> itemMediaContentMediaText(item, value, MediaText::setLang));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:text", "start", (item, value) -> itemMediaContentMediaText(item, value, MediaText::setStart));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:text", "end", (item, value) -> itemMediaContentMediaText(item, value, MediaText::setEnd));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:restriction", "type", (item, value) -> itemMediaContentMediaRestriction(item, value, MediaRestriction::setType));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:restriction", "relationship", (item, value) -> itemMediaContentMediaRestriction(item, value, MediaRestriction::setRelationship));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:community/media:starRating", "average", (item, value) -> itemMediaContentMediaCommunityMediaStarRating(item, value, (starRating, v) -> mapDouble(v, starRating::setAverage)));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:community/media:starRating", "count", (item, value) -> itemMediaContentMediaCommunityMediaStarRating(item, value, (starRating, v) -> mapInteger(v, starRating::setCount)));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:community/media:starRating", "max", (item, value) -> itemMediaContentMediaCommunityMediaStarRating(item, value, (starRating, v) -> mapInteger(v, starRating::setMax)));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:community/media:starRating", "min", (item, value) -> itemMediaContentMediaCommunityMediaStarRating(item, value, (starRating, v) -> mapInteger(v, starRating::setMin)));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:community/media:statistics", "views", (item, value) -> itemMediaContentMediaCommunityMediaStatistics(item, value, (statistics, v) -> mapLong(v, statistics::setViews)));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:community/media:statistics", "favorites", (item, value) -> itemMediaContentMediaCommunityMediaStatistics(item, value, (statistics, v) -> mapInteger(v, statistics::setFavorites)));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:embed", "url", (item, value) -> itemMediaContentMediaEmbed(item, value, MediaEmbed::setUrl));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:embed", "width", (item, value) -> itemMediaContentMediaEmbed(item, value, (i, v) -> mapInteger(v, i::setWidth)));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:embed", "height", (item, value) -> itemMediaContentMediaEmbed(item, value, (i, v) -> mapInteger(v, i::setHeight)));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:embed/media:param", "name", (item, value) -> itemMediaContentMediaEmbed(item, value, MediaEmbed::addParamName));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:status", "reason", (item, value) -> itemMediaContentMediaStatus(item, value, MediaStatus::setReason));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:status", "state", (item, value) -> itemMediaContentMediaStatus(item, value, MediaStatus::setState));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:price", "type", (item, value) -> itemMediaContentMediaPrice(item, value, MediaPrice::setType));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:price", "price", (item, value) -> itemMediaContentMediaPrice(item, value, (price, v) -> mapDouble(v, price::setPrice)));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:price", "currency", (item, value) -> itemMediaContentMediaPrice(item, value, MediaPrice::setCurrency));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:price", "info", (item, value) -> itemMediaContentMediaPrice(item, value, MediaPrice::setInfo));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:license", "type", (item, value) -> itemMediaContentMediaLicense(item, value, MediaLicense::setType));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:license", "href", (item, value) -> itemMediaContentMediaLicense(item, value, MediaLicense::setHref));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:subTitle", "type", (item, value) -> itemMediaContentMediaSubTitle(item, value, MediaSubTitle::setType));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:subTitle", "lang", (item, value) -> itemMediaContentMediaSubTitle(item, value, MediaSubTitle::setLang));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:subTitle", "href", (item, value) -> itemMediaContentMediaSubTitle(item, value, MediaSubTitle::setHref));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:peerLink", "type", (item, value) -> itemMediaContentMediaPeerLink(item, value, MediaPeerLink::setType));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:peerLink", "href", (item, value) -> itemMediaContentMediaPeerLink(item, value, MediaPeerLink::setHref));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:location", "description", (item, value) -> itemMediaContentMediaLocation(item, value, MediaLocation::setDescription));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:location", "start", (item, value) -> itemMediaContentMediaLocation(item, value, MediaLocation::setStart));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:location", "end", (item, value) -> itemMediaContentMediaLocation(item, value, MediaLocation::setEnd));
        registry.addItemExtension(ITEM_PATHS, "media:content/media:rights", "status", (item, value) -> itemMediaContentMediaRights(item, value, MediaRights::setStatus));

        // media:group
        registry.addItemExtension(ITEM_PATHS, "media:group/media:rating", "scheme", (item, value) -> itemMediaGroupMediaRating(item, value, MediaRating::setScheme));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:title", "type", (item, value) -> itemMediaGroupMediaTitle(item, value, MediaTitle::setType));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:description", "type", (item, value) -> itemMediaGroupMediaDescription(item, value, MediaDescription::setType));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:thumbnail", "url", (item, value) -> itemMediaGroupMediaThumbnail(item, value, MediaThumbnail::setUrl));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:thumbnail", "height", (item, value) -> itemMediaGroupMediaThumbnail(item, value, (thumbnail, v) -> mapInteger(v, thumbnail::setHeight)));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:thumbnail", "width", (item, value) -> itemMediaGroupMediaThumbnail(item, value, (thumbnail, v) -> mapInteger(v, thumbnail::setWidth)));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:thumbnail", "time", (item, value) -> itemMediaGroupMediaThumbnail(item, value, MediaThumbnail::setTime));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:category", "scheme", (item, value) -> itemMediaGroupMediaCategory(item, value, MediaCategory::setScheme));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:category", "label", (item, value) -> itemMediaGroupMediaCategory(item, value, MediaCategory::setLabel));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:hash", "algo", (item, value) -> itemMediaGroupMediaHash(item, value, MediaHash::setAlgorithm));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:player", "url", (item, value) -> itemMediaGroupMediaPlayer(item, value, MediaPlayer::setUrl));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:player", "height", (item, value) -> itemMediaGroupMediaPlayer(item, value, (i, v) -> mapInteger(v, i::setHeight)));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:player", "width", (item, value) -> itemMediaGroupMediaPlayer(item, value, (i, v) -> mapInteger(v, i::setWidth)));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:credit", "role", (item, value) -> itemMediaGroupMediaCredit(item, value, MediaCredit::setRole));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:credit", "scheme", (item, value) -> itemMediaGroupMediaCredit(item, value, MediaCredit::setScheme));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:copyright", "url", (item, value) -> itemMediaGroupMediaCopyright(item, value, MediaCopyright::setUrl));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:text", "type", (item, value) -> itemMediaGroupMediaText(item, value, MediaText::setType));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:text", "lang", (item, value) -> itemMediaGroupMediaText(item, value, MediaText::setLang));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:text", "start", (item, value) -> itemMediaGroupMediaText(item, value, MediaText::setStart));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:text", "end", (item, value) -> itemMediaGroupMediaText(item, value, MediaText::setEnd));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:restriction", "type", (item, value) -> itemMediaGroupMediaRestriction(item, value, MediaRestriction::setType));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:restriction", "relationship", (item, value) -> itemMediaGroupMediaRestriction(item, value, MediaRestriction::setRelationship));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:community/media:starRating", "average", (item, value) -> itemMediaGroupMediaCommunityMediaStarRating(item, value, (starRating, v) -> mapDouble(v, starRating::setAverage)));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:community/media:starRating", "count", (item, value) -> itemMediaGroupMediaCommunityMediaStarRating(item, value, (starRating, v) -> mapInteger(v, starRating::setCount)));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:community/media:starRating", "max", (item, value) -> itemMediaGroupMediaCommunityMediaStarRating(item, value, (starRating, v) -> mapInteger(v, starRating::setMax)));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:community/media:starRating", "min", (item, value) -> itemMediaGroupMediaCommunityMediaStarRating(item, value, (starRating, v) -> mapInteger(v, starRating::setMin)));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:community/media:statistics", "views", (item, value) -> itemMediaGroupMediaCommunityMediaStatistics(item, value, (statistics, v) -> mapLong(v, statistics::setViews)));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:community/media:statistics", "favorites", (item, value) -> itemMediaGroupMediaCommunityMediaStatistics(item, value, (statistics, v) -> mapInteger(v, statistics::setFavorites)));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:embed", "url", (item, value) -> itemMediaGroupMediaEmbed(item, value, MediaEmbed::setUrl));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:embed", "width", (item, value) -> itemMediaGroupMediaEmbed(item, value, (i, v) -> mapInteger(v, i::setWidth)));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:embed", "height", (item, value) -> itemMediaGroupMediaEmbed(item, value, (i, v) -> mapInteger(v, i::setHeight)));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:embed/media:param", "name", (item, value) -> itemMediaGroupMediaEmbed(item, value, MediaEmbed::addParamName));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:status", "reason", (item, value) -> itemMediaGroupMediaStatus(item, value, MediaStatus::setReason));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:status", "state", (item, value) -> itemMediaGroupMediaStatus(item, value, MediaStatus::setState));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:price", "type", (item, value) -> itemMediaGroupMediaPrice(item, value, MediaPrice::setType));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:price", "price", (item, value) -> itemMediaGroupMediaPrice(item, value, (price, v) -> mapDouble(v, price::setPrice)));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:price", "currency", (item, value) -> itemMediaGroupMediaPrice(item, value, MediaPrice::setCurrency));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:price", "info",  (item, value) -> itemMediaGroupMediaPrice(item, value, MediaPrice::setInfo));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:license", "type", (item, value) -> itemMediaGroupMediaLicense(item, value, MediaLicense::setType));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:license", "href", (item, value) -> itemMediaGroupMediaLicense(item, value, MediaLicense::setHref));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:subTitle", "type", (item, value) -> itemMediaGroupMediaSubTitle(item, value, MediaSubTitle::setType));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:subTitle", "lang", (item, value) -> itemMediaGroupMediaSubTitle(item, value, MediaSubTitle::setLang));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:subTitle", "href", (item, value) -> itemMediaGroupMediaSubTitle(item, value, MediaSubTitle::setHref));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:peerLink", "type", (item, value) -> itemMediaGroupMediaPeerLink(item, value, MediaPeerLink::setType));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:peerLink", "href", (item, value) -> itemMediaGroupMediaPeerLink(item, value, MediaPeerLink::setHref));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:location", "description", (item, value) -> itemMediaGroupMediaLocation(item, value, MediaLocation::setDescription));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:location", "start", (item, value) -> itemMediaGroupMediaLocation(item, value, MediaLocation::setStart));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:location", "end", (item, value) -> itemMediaGroupMediaLocation(item, value, MediaLocation::setEnd));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:rights", "status",(item, value) -> itemMediaGroupMediaRights(item, value, MediaRights::setStatus));

        // media:group / media:content
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content", "url", (item, value) -> itemMediaGroupMediaContent(item, value, MediaContent::setUrl));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content", "fileSize", (item, value) -> itemMediaGroupMediaContent(item, value, (content, v) -> mapLong(v, content::setFileSize)));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content", "type", (item, value) -> itemMediaGroupMediaContent(item, value, MediaContent::setType));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content", "medium", (item, value) -> itemMediaGroupMediaContent(item, value, MediaContent::setMedium));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content", "isDefault", (item, value) -> itemMediaGroupMediaContent(item, value, (content, v) -> mapBoolean(v, content::setDefault)));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content", "expression", (item, value) -> itemMediaGroupMediaContent(item, value, MediaContent::setExpression));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content", "bitrate", (item, value) -> itemMediaGroupMediaContent(item, value, (content, v) -> mapDouble(v, content::setBitRate)));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content", "framerate", (item, value) -> itemMediaGroupMediaContent(item, value, (content, v) -> mapDouble(v, content::setFrameRate)));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content", "samplingrate", (item, value) -> itemMediaGroupMediaContent(item, value, (content, v) -> mapDouble(v, content::setSamplingRate)));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content", "channels", (item, value) -> itemMediaGroupMediaContent(item, value, (content, v) -> mapInteger(v, content::setChannels)));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content", "duration", (item, value) -> itemMediaGroupMediaContent(item, value, (content, v) -> mapInteger(v, content::setDuration)));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content", "height", (item, value) -> itemMediaGroupMediaContent(item, value, (content, v) -> mapInteger(v, content::setHeight)));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content", "width", (item, value) -> itemMediaGroupMediaContent(item, value, (content, v) -> mapInteger(v, content::setWidth)));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content", "lang", (item, value) -> itemMediaGroupMediaContent(item, value, MediaContent::setLang));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:rating", "scheme", (item, value) -> itemMediaGroupMediaContentMediaRating(item, value, MediaRating::setScheme));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:title", "type", (item, value) -> itemMediaGroupMediaContentMediaTitle(item, value, MediaTitle::setType));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:description", "type", (item, value) -> itemMediaGroupMediaContentMediaDescription(item, value, MediaDescription::setType));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:thumbnail", "url", (item, value) -> itemMediaGroupMediaContentMediaThumbnail(item, value, MediaThumbnail::setUrl));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:thumbnail", "width", (item, value) -> itemMediaGroupMediaContentMediaThumbnail(item, value, (content, v) -> mapInteger(v, content::setWidth)));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:thumbnail", "height", (item, value) -> itemMediaGroupMediaContentMediaThumbnail(item, value, (content, v) -> mapInteger(v, content::setHeight)));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:thumbnail", "time", (item, value) -> itemMediaGroupMediaContentMediaThumbnail(item, value, MediaThumbnail::setTime));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:category", "scheme", (item, value) -> itemMediaGroupMediaContentMediaCategory(item, value, MediaCategory::setScheme));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:category", "label", (item, value) -> itemMediaGroupMediaContentMediaCategory(item, value, MediaCategory::setLabel));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:hash", "algo", (item, value) -> itemMediaGroupMediaContentMediaHash(item, value, MediaHash::setAlgorithm));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:player", "url", (item, value) -> itemMediaGroupMediaContentMediaPlayer(item, value, MediaPlayer::setUrl));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:player", "height", (item, value) -> itemMediaGroupMediaContentMediaPlayer(item, value, (content, v) -> mapInteger(v, content::setHeight)));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:player", "width", (item, value) -> itemMediaGroupMediaContentMediaPlayer(item, value, (content, v) -> mapInteger(v, content::setWidth)));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:credit", "role", (item, value) -> itemMediaGroupMediaContentMediaCredit(item, value, MediaCredit::setRole));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:credit", "scheme", (item, value) -> itemMediaGroupMediaContentMediaCredit(item, value, MediaCredit::setScheme));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:copyright", "url", (item, value) -> itemMediaGroupMediaContentMediaCopyright(item, value, MediaCopyright::setUrl));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:text", "type", (item, value) -> itemMediaGroupMediaContentMediaText(item, value, MediaText::setType));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:text", "lang", (item, value) -> itemMediaGroupMediaContentMediaText(item, value, MediaText::setLang));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:text", "start", (item, value) -> itemMediaGroupMediaContentMediaText(item, value, MediaText::setStart));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:text", "end", (item, value) -> itemMediaGroupMediaContentMediaText(item, value, MediaText::setEnd));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:restriction", "relationship", (item, value) -> itemMediaGroupMediaContentMediaRestriction(item, value, MediaRestriction::setRelationship));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:restriction", "type", (item, value) -> itemMediaGroupMediaContentMediaRestriction(item, value, MediaRestriction::setType));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:community/media:starRating", "average", (item, value) -> itemMediaGroupMediaContentMediaCommunityMediaStarRating(item, value, (starRating, v) -> mapDouble(v, starRating::setAverage)));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:community/media:starRating", "count", (item, value) -> itemMediaGroupMediaContentMediaCommunityMediaStarRating(item, value, (starRating, v) -> mapInteger(v, starRating::setCount)));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:community/media:starRating", "max", (item, value) -> itemMediaGroupMediaContentMediaCommunityMediaStarRating(item, value, (starRating, v) -> mapInteger(v, starRating::setMax)));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:community/media:starRating", "min", (item, value) -> itemMediaGroupMediaContentMediaCommunityMediaStarRating(item, value, (starRating, v) -> mapInteger(v, starRating::setMin)));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:community/media:statistics", "views", (item, value) -> itemMediaGroupMediaContentMediaCommunityMediaStatistics(item, value, (statistics, v) -> mapLong(v, statistics::setViews)));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:community/media:statistics", "favorites", (item, value) -> itemMediaGroupMediaContentMediaCommunityMediaStatistics(item, value, (statistics, v) -> mapInteger(v, statistics::setFavorites)));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:embed", "url", (item, value) -> itemMediaGroupMediaContentMediaEmbed(item, value, MediaEmbed::setUrl));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:embed", "width", (item, value) -> itemMediaGroupMediaContentMediaEmbed(item, value, (embed, v) -> mapInteger(v, embed::setWidth)));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:embed", "height", (item, value) -> itemMediaGroupMediaContentMediaEmbed(item, value, (embed, v) -> mapInteger(v, embed::setHeight)));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:embed/media:param", "name", (item, value) -> itemMediaGroupMediaContentMediaEmbed(item, value, MediaEmbed::addParamName));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:status", "state", (item, value) -> itemMediaGroupMediaContentMediaStatus(item, value, MediaStatus::setState));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:status", "reason", (item, value) -> itemMediaGroupMediaContentMediaStatus(item, value, MediaStatus::setReason));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:price", "type", (item, value) -> itemMediaGroupMediaContentMediaPrice(item, value, MediaPrice::setType));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:price", "info", (item, value) -> itemMediaGroupMediaContentMediaPrice(item, value, MediaPrice::setInfo));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:price", "price", (item, value) -> itemMediaGroupMediaContentMediaPrice(item, value, (content, v) -> mapDouble(v, content::setPrice)));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:price", "currency", (item, value) -> itemMediaGroupMediaContentMediaPrice(item, value, MediaPrice::setCurrency));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:license", "type", (item, value) -> itemMediaGroupMediaContentMediaLicense(item, value, MediaLicense::setType));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:license", "href", (item, value) -> itemMediaGroupMediaContentMediaLicense(item, value, MediaLicense::setHref));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:subTitle", "type", (item, value) -> itemMediaGroupMediaContentMediaSubTitle(item, value, MediaSubTitle::setType));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:subTitle", "lang", (item, value) -> itemMediaGroupMediaContentMediaSubTitle(item, value, MediaSubTitle::setLang));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:subTitle", "href", (item, value) -> itemMediaGroupMediaContentMediaSubTitle(item, value, MediaSubTitle::setHref));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:peerLink", "type", (item, value) -> itemMediaGroupMediaContentMediaPeerLink(item, value, MediaPeerLink::setType));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:peerLink", "href", (item, value) -> itemMediaGroupMediaContentMediaPeerLink(item, value, MediaPeerLink::setHref));
        registry.addItemExtension(ITEM_PATHS, "media:group/media:content/media:rights", "status", (item, value) -> itemMediaGroupMediaContentMediaRights(item, value, MediaRights::setStatus));

        // item
        registry.addItemExtension(ITEM_PATHS, "media:rating", "scheme", (item, value) -> itemMediaRating(item, value, MediaRating::setScheme));
        registry.addItemExtension(ITEM_PATHS, "media:title", "type", (item, value) -> itemMediaTitle(item, value, MediaTitle::setType));
        registry.addItemExtension(ITEM_PATHS, "media:description", "type", (item, value) -> itemMediaDescription(item, value, MediaDescription::setType));
        registry.addItemExtension(ITEM_PATHS, "media:thumbnail", "url", (item, value) -> itemMediaThumbnail(item, value, MediaThumbnail::setUrl));
        registry.addItemExtension(ITEM_PATHS, "media:thumbnail", "height", (item, value) -> itemMediaThumbnail(item, value, (thumbnail, v) -> mapInteger(v, thumbnail::setHeight)));
        registry.addItemExtension(ITEM_PATHS, "media:thumbnail", "width", (item, value) -> itemMediaThumbnail(item, value, (thumbnail, v) -> mapInteger(v, thumbnail::setWidth)));
        registry.addItemExtension(ITEM_PATHS, "media:thumbnail", "time", (item, value) -> itemMediaThumbnail(item, value, MediaThumbnail::setTime));
        registry.addItemExtension(ITEM_PATHS, "media:category", "scheme", (item, value) -> itemMediaCategory(item, value, MediaCategory::setScheme));
        registry.addItemExtension(ITEM_PATHS, "media:category", "label", (item, value) -> itemMediaCategory(item, value, MediaCategory::setLabel));
        registry.addItemExtension(ITEM_PATHS, "media:hash", "algo", (item, value) -> itemMediaHash(item, value, MediaHash::setAlgorithm));
        registry.addItemExtension(ITEM_PATHS, "media:player", "url", (item, value) -> itemMediaPlayer(item, value, MediaPlayer::setUrl));
        registry.addItemExtension(ITEM_PATHS, "media:player", "height", (item, value) -> itemMediaPlayer(item, value, (player, v) -> mapInteger(v, player::setHeight)));
        registry.addItemExtension(ITEM_PATHS, "media:player", "width", (item, value) -> itemMediaPlayer(item, value, (player, v) -> mapInteger(v, player::setWidth)));
        registry.addItemExtension(ITEM_PATHS, "media:credit", "role", (item, value) -> itemMediaCredit(item, value, MediaCredit::setRole));
        registry.addItemExtension(ITEM_PATHS, "media:credit", "scheme", (item, value) -> itemMediaCredit(item, value, MediaCredit::setScheme));
        registry.addItemExtension(ITEM_PATHS, "media:copyright", "url", (item, value) -> itemMediaCopyright(item, value, MediaCopyright::setUrl));
        registry.addItemExtension(ITEM_PATHS, "media:text", "type", (item, value) -> itemMediaText(item, value, MediaText::setType));
        registry.addItemExtension(ITEM_PATHS, "media:text", "lang", (item, value) -> itemMediaText(item, value, MediaText::setLang));
        registry.addItemExtension(ITEM_PATHS, "media:text", "start", (item, value) -> itemMediaText(item, value, MediaText::setStart));
        registry.addItemExtension(ITEM_PATHS, "media:text", "end", (item, value) -> itemMediaText(item, value, MediaText::setEnd));
        registry.addItemExtension(ITEM_PATHS, "media:restriction", "type", (item, value) -> itemMediaRestriction(item, value, MediaRestriction::setType));
        registry.addItemExtension(ITEM_PATHS, "media:restriction", "relationship", (item, value) -> itemMediaRestriction(item, value, MediaRestriction::setRelationship));
        registry.addItemExtension(ITEM_PATHS, "media:community/media:starRating", "average", (item, value) -> itemMediaCommunityMediaStarRating(item, value, (starRating, v) -> mapDouble(v, starRating::setAverage)));
        registry.addItemExtension(ITEM_PATHS, "media:community/media:starRating", "count", (item, value) -> itemMediaCommunityMediaStarRating(item, value, (starRating, v) -> mapInteger(v, starRating::setCount)));
        registry.addItemExtension(ITEM_PATHS, "media:community/media:starRating", "max", (item, value) -> itemMediaCommunityMediaStarRating(item, value, (starRating, v) -> mapInteger(v, starRating::setMax)));
        registry.addItemExtension(ITEM_PATHS, "media:community/media:starRating", "min", (item, value) -> itemMediaCommunityMediaStarRating(item, value, (starRating, v) -> mapInteger(v, starRating::setMin)));
        registry.addItemExtension(ITEM_PATHS, "media:community/media:statistics", "views", (item, value) -> itemMediaCommunityMediaStatistics(item, value, (statistics, v) -> mapLong(v, statistics::setViews)));
        registry.addItemExtension(ITEM_PATHS, "media:community/media:statistics", "favorites", (item, value) -> itemMediaCommunityMediaStatistics(item, value, (statistics, v) -> mapInteger(v, statistics::setFavorites)));
        registry.addItemExtension(ITEM_PATHS, "media:embed", "url", (item, value) -> itemMediaEmbed(item, value, MediaEmbed::setUrl));
        registry.addItemExtension(ITEM_PATHS, "media:embed", "width", (item, value) -> itemMediaEmbed(item, value, (i, v) -> mapInteger(v, i::setWidth)));
        registry.addItemExtension(ITEM_PATHS, "media:embed", "height", (item, value) -> itemMediaEmbed(item, value, (i, v) -> mapInteger(v, i::setHeight)));
        registry.addItemExtension(ITEM_PATHS, "media:embed/media:param", "name", (item, value) -> itemMediaEmbed(item, value, MediaEmbed::addParamName));
        registry.addItemExtension(ITEM_PATHS, "media:status", "reason", (item, value) -> itemMediaStatus(item, value, MediaStatus::setReason));
        registry.addItemExtension(ITEM_PATHS, "media:status", "state", (item, value) -> itemMediaStatus(item, value, MediaStatus::setState));
        registry.addItemExtension(ITEM_PATHS, "media:price", "type", (item, value) -> itemMediaPrice(item, value, MediaPrice::setType));
        registry.addItemExtension(ITEM_PATHS, "media:price", "price", (item, value) -> itemMediaPrice(item, value, (price, v) -> mapDouble(v, price::setPrice)));
        registry.addItemExtension(ITEM_PATHS, "media:price", "currency", (item, value) -> itemMediaPrice(item, value, MediaPrice::setCurrency));
        registry.addItemExtension(ITEM_PATHS, "media:price", "info", (item, value) -> itemMediaPrice(item, value, MediaPrice::setInfo));
        registry.addItemExtension(ITEM_PATHS, "media:license", "type", (item, value) -> itemMediaLicense(item, value, MediaLicense::setType));
        registry.addItemExtension(ITEM_PATHS, "media:license", "href", (item, value) -> itemMediaLicense(item, value, MediaLicense::setHref));
        registry.addItemExtension(ITEM_PATHS, "media:subTitle", "type", (item, value) -> itemMediaSubTitle(item, value, MediaSubTitle::setType));
        registry.addItemExtension(ITEM_PATHS, "media:subTitle", "lang", (item, value) -> itemMediaSubTitle(item, value, MediaSubTitle::setLang));
        registry.addItemExtension(ITEM_PATHS, "media:subTitle", "href", (item, value) -> itemMediaSubTitle(item, value, MediaSubTitle::setHref));
        registry.addItemExtension(ITEM_PATHS, "media:peerLink", "type", (item, value) -> itemMediaPeerLink(item, value, MediaPeerLink::setType));
        registry.addItemExtension(ITEM_PATHS, "media:peerLink", "href", (item, value) -> itemMediaPeerLink(item, value, MediaPeerLink::setHref));
        registry.addItemExtension(ITEM_PATHS, "media:location", "description", (item, value) -> itemMediaLocation(item, value, MediaLocation::setDescription));
        registry.addItemExtension(ITEM_PATHS, "media:location", "start", (item, value) -> itemMediaLocation(item, value, MediaLocation::setStart));
        registry.addItemExtension(ITEM_PATHS, "media:location", "end", (item, value) -> itemMediaLocation(item, value, MediaLocation::setEnd));
        registry.addItemExtension(ITEM_PATHS, "media:rights", "status", (item, value) -> itemMediaRights(item, value, MediaRights::setStatus));
    }

    // Channel mapping methods

    private static void channelMediaRating(MediaRssChannel channel, String value, BiConsumer<MediaRating, String> setter) {
        setter.accept(getLast(channel.getMediaRatings()), value);
    }

    private static void channelMediaTitle(MediaRssChannel channel, String value, BiConsumer<MediaTitle, String> setter) {
        var title = createIfNull(channel::getMediaTitle, channel::setMediaTitle, MediaTitle::new);
        setter.accept(title, value);
    }

    private static void channelMediaDescription(MediaRssChannel channel, String value, BiConsumer<MediaDescription, String> setter) {
        var description = createIfNull(channel::getMediaDescription, channel::setMediaDescription, MediaDescription::new);
        setter.accept(description, value);
    }

    private static void channelMediaKeywords(MediaRssChannel channel, String value, BiConsumer<MediaRssChannel, String> setter) {
        Mapper.split(value).forEach(keyword -> setter.accept(channel, keyword));
    }

    private static void channelMediaCategory(MediaRssChannel channel, String value, BiConsumer<MediaCategory, String> setter) {
        setter.accept(getLast(channel.getMediaCategories()), value);
    }

    private static void channelMediaHash(MediaRssChannel channel, String value, BiConsumer<MediaHash, String> setter) {
        setter.accept(getLast(channel.getMediaHashes()), value);
    }

    private static void channelMediaCredit(MediaRssChannel channel, String value, BiConsumer<MediaCredit, String> setter) {
        setter.accept(getLast(channel.getMediaCredits()), value);
    }

    private static void channelMediaCopyright(MediaRssChannel channel, String value, BiConsumer<MediaCopyright, String> setter) {
        var copyright = createIfNull(channel::getMediaCopyright, channel::setMediaCopyright, MediaCopyright::new);
        setter.accept(copyright, value);
    }

    private static void channelMediaText(MediaRssChannel channel, String value, BiConsumer<MediaText, String> setter) {
        setter.accept(getLast(channel.getMediaTexts()), value);
    }

    private static void channelMediaRestriction(MediaRssChannel channel, String value, BiConsumer<MediaRestriction, String> setter) {
        setter.accept(getLast(channel.getMediaRestrictions()), value);
    }

    private static void channelMediaCommunityMediaTags(MediaRssChannel channel, String value, BiConsumer<MediaTags, String> setter) {
        var community = createIfNull(channel::getMediaCommunity, channel::setMediaCommunity, MediaCommunity::new);
        var tags = createIfNull(community::getMediaTags, community::setMediaTags, MediaTags::new);
        setter.accept(tags, value);
    }

    private static void channelMediaEmbed(MediaRssChannel channel, String value, BiConsumer<MediaEmbed, String> setter) {
        var embed = createIfNull(channel::getMediaEmbed, channel::setMediaEmbed, MediaEmbed::new);
        setter.accept(embed, value);
    }

    private static void channelMediaLicense(MediaRssChannel channel, String value, BiConsumer<MediaLicense, String> setter) {
        setter.accept(getLast(channel.getMediaLicenses()), value);
    }

    private static void channelMediaScene(MediaRssChannel channel, String value, BiConsumer<MediaScene, String> setter) {
        setter.accept(getLast(channel.getMediaScenes()), value);
    }

    private static void channelMediaThumbnail(MediaRssChannel channel, String value, BiConsumer<MediaThumbnail, String> setter) {
        setter.accept(getLast(channel.getMediaThumbnails()), value);
    }

    private static void channelMediaPlayer(MediaRssChannel channel, String value, BiConsumer<MediaPlayer, String> setter) {
        var player = createIfNull(channel::getMediaPlayer, channel::setMediaPlayer, MediaPlayer::new);
        setter.accept(player, value);
    }

    private static void channelMediaCommunityMediaStarRating(MediaRssChannel channel, String value, BiConsumer<MediaStarRating, String> setter) {
        var community = createIfNull(channel::getMediaCommunity, channel::setMediaCommunity, MediaCommunity::new);
        var starRating = createIfNull(community::getMediaStarRating, community::setMediaStarRating, MediaStarRating::new);
        setter.accept(starRating, value);
    }

    private static void channelMediaCommunityMediaStatistics(MediaRssChannel channel, String value, BiConsumer<MediaStatistics, String> setter) {
        var community = createIfNull(channel::getMediaCommunity, channel::setMediaCommunity, MediaCommunity::new);
        var statistics = createIfNull(community::getMediaStatistics, community::setMediaStatistics, MediaStatistics::new);
        setter.accept(statistics, value);
    }

    private static void channelMediaStatus(MediaRssChannel channel, String value, BiConsumer<MediaStatus, String> setter) {
        var status = createIfNull(channel::getMediaStatus, channel::setMediaStatus, MediaStatus::new);
        setter.accept(status, value);
    }

    private static void channelMediaPrice(MediaRssChannel channel, String value, BiConsumer<MediaPrice, String> setter) {
        setter.accept(getLast(channel.getMediaPrices()), value);
    }

    private static void channelMediaSubTitle(MediaRssChannel channel, String value, BiConsumer<MediaSubTitle, String> setter) {
        setter.accept(getLast(channel.getMediaSubTitles()), value);
    }

    private static void channelMediaPeerLink(MediaRssChannel channel, String value, BiConsumer<MediaPeerLink, String> setter) {
        setter.accept(getLast(channel.getMediaPeerLinks()), value);
    }

    private static void channelMediaLocation(MediaRssChannel channel, String value, BiConsumer<MediaLocation, String> setter) {
        setter.accept(getLast(channel.getMediaLocations()), value);
    }

    private static void channelMediaRights(MediaRssChannel channel, String value, BiConsumer<MediaRights, String> setter) {
        var rights = createIfNull(channel::getMediaRights, channel::setMediaRights, MediaRights::new);
        setter.accept(rights, value);
    }

    // Item mapping methods

    private static void itemMediaContent(MediaRssItem item, String value, BiConsumer<MediaContent, String> setter) {
        Optional.ofNullable(getLast(item.getMediaContents()))
                .ifPresent(content -> setter.accept(content, value));
    }

    private static void itemMediaContentMediaThumbnail(MediaRssItem item, String value, BiConsumer<MediaThumbnail, String> setter) {
        var content = getLast(item.getMediaContents());
        var thumbnail = getLast(content.getMediaThumbnails());
        setter.accept(thumbnail, value);
    }

    private static void itemMediaContentMediaTitle(MediaRssItem item, String value, BiConsumer<MediaTitle, String> setter) {
        var content = getLast(item.getMediaContents());
        var title = createIfNull(content::getMediaTitle, content::setMediaTitle, MediaTitle::new);
        setter.accept(title, value);
    }

    private static void itemMediaContentMediaDescription(MediaRssItem item, String value, BiConsumer<MediaDescription, String> setter) {
        var content = getLast(item.getMediaContents());
        var description = createIfNull(content::getMediaDescription, content::setMediaDescription, MediaDescription::new);
        setter.accept(description, value);
    }

    private static void itemMediaContentMediaPlayer(MediaRssItem item, String value, BiConsumer<MediaPlayer, String> setter) {
        var content = getLast(item.getMediaContents());
        var player = createIfNull(content::getMediaPlayer, content::setMediaPlayer, MediaPlayer::new);
        setter.accept(player, value);
    }

    private static void itemMediaContentMediaCredit(MediaRssItem item, String value, BiConsumer<MediaCredit, String> setter) {
        var content = getLast(item.getMediaContents());
        var credit = getLast(content.getMediaCredits());
        setter.accept(credit, value);
    }

    private static void itemMediaContentMediaCopyright(MediaRssItem item, String value, BiConsumer<MediaCopyright, String> setter) {
        var content = getLast(item.getMediaContents());
        var copyright = createIfNull(content::getMediaCopyright, content::setMediaCopyright, MediaCopyright::new);
        setter.accept(copyright, value);
    }

    private static void itemMediaContentMediaHash(MediaRssItem item, String value, BiConsumer<MediaHash, String> setter) {
        var content = getLast(item.getMediaContents());
        setter.accept(getLast(content.getMediaHashes()), value);
    }

    private static void itemMediaContentMediaCategory(MediaRssItem item, String value, BiConsumer<MediaCategory, String> setter) {
        var content = getLast(item.getMediaContents());
        setter.accept(getLast(content.getMediaCategories()), value);
    }

    private static void itemMediaContentMediaText(MediaRssItem item, String value, BiConsumer<MediaText, String> setter) {
        var content = getLast(item.getMediaContents());
        setter.accept(getLast(content.getMediaTexts()), value);
    }

    private static void itemMediaContentMediaRating(MediaRssItem item, String value, BiConsumer<MediaRating, String> setter) {
        var content = getLast(item.getMediaContents());
        setter.accept(getLast(content.getMediaRatings()), value);
    }

    private static void itemMediaContentMediaLicense(MediaRssItem item, String value, BiConsumer<MediaLicense, String> setter) {
        var content = getLast(item.getMediaContents());
        setter.accept(getLast(content.getMediaLicenses()), value);
    }

    private static void itemMediaContentMediaRights(MediaRssItem item, String value, BiConsumer<MediaRights, String> setter) {
        var content = getLast(item.getMediaContents());
        var rights = createIfNull(content::getMediaRights, content::setMediaRights, MediaRights::new);
        setter.accept(rights, value);
    }

    private static void itemMediaContentMediaKeywords(MediaRssItem item, String value, BiConsumer<MediaContent, String> setter) {
        var content = getLast(item.getMediaContents());
        Mapper.split(value).forEach(keyword -> setter.accept(content, keyword));
    }

    private static void itemMediaContentMediaRestriction(MediaRssItem item, String value, BiConsumer<MediaRestriction, String> setter) {
        var content = getLast(item.getMediaContents());
        var restrictions = getLast(content.getMediaRestrictions());
        setter.accept(restrictions, value);
    }

    private static void itemMediaContentMediaCommunityMediaStarRating(MediaRssItem item, String value, BiConsumer<MediaStarRating, String> setter) {
        var content = getLast(item.getMediaContents());
        var community = createIfNull(content::getMediaCommunity, content::setMediaCommunity, MediaCommunity::new);
        var starRating = createIfNull(community::getMediaStarRating, community::setMediaStarRating, MediaStarRating::new);
        setter.accept(starRating, value);
    }

    private static void itemMediaContentMediaCommunityMediaStatistics(MediaRssItem item, String value, BiConsumer<MediaStatistics, String> setter) {
        var content = getLast(item.getMediaContents());
        var community = createIfNull(content::getMediaCommunity, content::setMediaCommunity, MediaCommunity::new);
        var statistics = createIfNull(community::getMediaStatistics, community::setMediaStatistics, MediaStatistics::new);
        setter.accept(statistics, value);
    }

    private static void itemMediaContentMediaCommunityMediaTags(MediaRssItem item, String value, BiConsumer<MediaTags, String> setter) {
        var content = getLast(item.getMediaContents());
        var community = createIfNull(content::getMediaCommunity, content::setMediaCommunity, MediaCommunity::new);
        var tags = createIfNull(community::getMediaTags, community::setMediaTags, MediaTags::new);
        setter.accept(tags, value);
    }

    private static void itemMediaContentStringList(MediaRssItem item, String value, BiConsumer<MediaContent, String> setter) {
        var content = getLast(item.getMediaContents());
        setter.accept(content, value);
    }

    private static void itemMediaContentMediaStatus(MediaRssItem item, String value, BiConsumer<MediaStatus, String> setter) {
        var content = getLast(item.getMediaContents());
        var status = createIfNull(content::getMediaStatus, content::setMediaStatus, MediaStatus::new);
        setter.accept(status, value);
    }

    private static void itemMediaContentMediaPrice(MediaRssItem item, String value, BiConsumer<MediaPrice, String> setter) {
        var content = getLast(item.getMediaContents());
        setter.accept(getLast(content.getMediaPrices()), value);
    }

    private static void itemMediaContentMediaEmbed(MediaRssItem item, String value, BiConsumer<MediaEmbed, String> setter) {
        var content = getLast(item.getMediaContents());
        var embed = createIfNull(content::getMediaEmbed, content::setMediaEmbed, MediaEmbed::new);
        setter.accept(embed, value);
    }

    private static void itemMediaContentMediaScene(MediaRssItem item, String value, BiConsumer<MediaScene, String> setter) {
        var content = getLast(item.getMediaContents());
        var scene = getLast(content.getMediaScenes());
        setter.accept(scene, value);
    }

    private static void itemMediaContentMediaSubTitle(MediaRssItem item, String value, BiConsumer<MediaSubTitle, String> setter) {
        var content = getLast(item.getMediaContents());
        setter.accept(getLast(content.getMediaSubTitles()), value);
    }

    private static void itemMediaContentMediaPeerLink(MediaRssItem item, String value, BiConsumer<MediaPeerLink, String> setter) {
        var content = getLast(item.getMediaContents());
        setter.accept(getLast(content.getMediaPeerLinks()), value);
    }

    private static void itemMediaContentMediaLocation(MediaRssItem item, String value, BiConsumer<MediaLocation, String> setter) {
        var content = getLast(item.getMediaContents());
        setter.accept(getLast(content.getMediaLocations()), value);
    }

    private static void itemMediaGroupMediaContent(MediaRssItem item, String value, BiConsumer<MediaContent, String> setter) {
        item.getMediaGroup()
                .map(group -> getLast(group.getMediaContents()))
                .ifPresent(content -> setter.accept(content, value));
    }

    private static void itemMediaGroupMediaTitle(MediaRssItem item, String value, BiConsumer<MediaTitle, String> setter) {
        var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
        var title = createIfNull(group::getMediaTitle, group::setMediaTitle, MediaTitle::new);
        setter.accept(title, value);
    }

    private static void itemMediaGroupMediaDescription(MediaRssItem item, String value, BiConsumer<MediaDescription, String> setter) {
        var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
        var description = createIfNull(group::getMediaDescription, group::setMediaDescription, MediaDescription::new);
        setter.accept(description, value);
    }

    private static void itemMediaGroupMediaCopyright(MediaRssItem item, String value, BiConsumer<MediaCopyright, String> setter) {
        var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
        var copyright = createIfNull(group::getMediaCopyright, group::setMediaCopyright, MediaCopyright::new);
        setter.accept(copyright, value);
    }

    private static void itemMediaGroupMediaHash(MediaRssItem item, String value, BiConsumer<MediaHash, String> setter) {
        var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
        setter.accept(getLast(group.getMediaHashes()), value);
    }

    private static void itemMediaGroupMediaPlayer(MediaRssItem item, String value, BiConsumer<MediaPlayer, String> setter) {
        var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
        var copyright = createIfNull(group::getMediaPlayer, group::setMediaPlayer, MediaPlayer::new);
        setter.accept(copyright, value);
    }

    private static void itemMediaGroupMediaRestriction(MediaRssItem item, String value, BiConsumer<MediaRestriction, String> setter) {
        var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
        var restriction = getLast(group.getMediaRestrictions());
        setter.accept(restriction, value);
    }

    private static void itemMediaGroupMediaText(MediaRssItem item, String value, BiConsumer<MediaText, String> setter) {
        var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
        var text = getLast(group.getMediaTexts());
        setter.accept(text, value);
    }

    private static void itemMediaGroupStringList(MediaRssItem item, String value, BiConsumer<MediaGroup, String> setter) {
        var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
        setter.accept(group, value);
    }

    private static void itemMediaGroupMediaKeywords(MediaRssItem item, String value, BiConsumer<MediaGroup, String> setter) {
        var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
        Mapper.split(value).forEach(keyword -> setter.accept(group, keyword));
    }

    private static void itemMediaGroupMediaCredit(MediaRssItem item, String value, BiConsumer<MediaCredit, String> setter) {
        var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
        setter.accept(getLast(group.getMediaCredits()), value);
    }

    private static void itemMediaGroupMediaCategory(MediaRssItem item, String value, BiConsumer<MediaCategory, String> setter) {
        var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
        setter.accept(getLast(group.getMediaCategories()), value);
    }

    private static void itemMediaGroupMediaRating(MediaRssItem item, String value, BiConsumer<MediaRating, String> setter) {
        var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
        var rating = getLast(group.getMediaRatings());
        setter.accept(rating, value);
    }

    private static void itemMediaGroupMediaThumbnail(MediaRssItem item, String value, BiConsumer<MediaThumbnail, String> setter) {
        var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
        var thumbnail = getLast(group.getMediaThumbnails());
        setter.accept(thumbnail, value);
    }

    private static void itemMediaGroupMediaCommunityMediaStarRating(MediaRssItem item, String value, BiConsumer<MediaStarRating, String> setter) {
        var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
        var community = createIfNull(group::getMediaCommunity, group::setMediaCommunity, MediaCommunity::new);
        var starRating = createIfNull(community::getMediaStarRating, community::setMediaStarRating, MediaStarRating::new);
        setter.accept(starRating, value);
    }

    private static void itemMediaGroupMediaCommunityMediaStatistics(MediaRssItem item, String value, BiConsumer<MediaStatistics, String> setter) {
        var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
        var community = createIfNull(group::getMediaCommunity, group::setMediaCommunity, MediaCommunity::new);
        var statistics = createIfNull(community::getMediaStatistics, community::setMediaStatistics, MediaStatistics::new);
        setter.accept(statistics, value);
    }

    private static void itemMediaGroupMediaCommunityMediaTags(MediaRssItem item, String value, BiConsumer<MediaTags, String> setter) {
        var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
        var community = createIfNull(group::getMediaCommunity, group::setMediaCommunity, MediaCommunity::new);
        var tags = createIfNull(community::getMediaTags, community::setMediaTags, MediaTags::new);
        setter.accept(tags, value);
    }

    private static void itemMediaGroupMediaStatus(MediaRssItem item, String value, BiConsumer<MediaStatus, String> setter) {
        var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
        var status = createIfNull(group::getMediaStatus, group::setMediaStatus, MediaStatus::new);
        setter.accept(status, value);
    }

    private static void itemMediaGroupMediaPrice(MediaRssItem item, String value, BiConsumer<MediaPrice, String> setter) {
        var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
        var price = getLast(group.getMediaPrices());
        setter.accept(price, value);
    }

    private static void itemMediaGroupMediaEmbed(MediaRssItem item, String value, BiConsumer<MediaEmbed, String> setter) {
        var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
        var embed = createIfNull(group::getMediaEmbed, group::setMediaEmbed, MediaEmbed::new);
        setter.accept(embed, value);
    }

    private static void itemMediaGroupMediaLicense(MediaRssItem item, String value, BiConsumer<MediaLicense, String> setter) {
        var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
        setter.accept(getLast(group.getMediaLicenses()), value);
    }

    private static void itemMediaGroupMediaRights(MediaRssItem item, String value, BiConsumer<MediaRights, String> setter) {
        var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
        var rights = createIfNull(group::getMediaRights, group::setMediaRights, MediaRights::new);
        setter.accept(rights, value);
    }

    private static void itemMediaGroupMediaScene(MediaRssItem item, String value, BiConsumer<MediaScene, String> setter) {
        var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
        var scene = getLast(group.getMediaScenes());
        setter.accept(scene, value);
    }

    private static void itemMediaGroupMediaSubTitle(MediaRssItem item, String value, BiConsumer<MediaSubTitle, String> setter) {
        var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
        setter.accept(getLast(group.getMediaSubTitles()), value);
    }

    private static void itemMediaGroupMediaPeerLink(MediaRssItem item, String value, BiConsumer<MediaPeerLink, String> setter) {
        var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
        setter.accept(getLast(group.getMediaPeerLinks()), value);
    }

    private static void itemMediaGroupMediaLocation(MediaRssItem item, String value, BiConsumer<MediaLocation, String> setter) {
        var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
        setter.accept(getLast(group.getMediaLocations()), value);
    }

    private static void itemMediaCommunityMediaStarRating(MediaRssItem item, String value, BiConsumer<MediaStarRating, String> setter) {
        var community = createIfNull(item::getMediaCommunity, item::setMediaCommunity, MediaCommunity::new);
        var starRating = createIfNull(community::getMediaStarRating, community::setMediaStarRating, MediaStarRating::new);
        setter.accept(starRating, value);
    }

    private static void itemMediaCommunityMediaStatistics(MediaRssItem item, String value, BiConsumer<MediaStatistics, String> setter) {
        var community = createIfNull(item::getMediaCommunity, item::setMediaCommunity, MediaCommunity::new);
        var statistics = createIfNull(community::getMediaStatistics, community::setMediaStatistics, MediaStatistics::new);
        setter.accept(statistics, value);
    }

    private static void itemMediaCommunityMediaTags(MediaRssItem item, String value, BiConsumer<MediaTags, String> setter) {
        var community = createIfNull(item::getMediaCommunity, item::setMediaCommunity, MediaCommunity::new);
        var tags = createIfNull(community::getMediaTags, community::setMediaTags, MediaTags::new);
        setter.accept(tags, value);
    }

    private static void itemMediaGroupMediaContentMediaTitle(MediaRssItem item, String value, BiConsumer<MediaTitle, String> setter) {
        var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
        var content = getLast(group.getMediaContents());
        var title = createIfNull(content::getMediaTitle, content::setMediaTitle, MediaTitle::new);
        setter.accept(title, value);
    }

    private static void itemMediaGroupMediaContentMediaDescription(MediaRssItem item, String value, BiConsumer<MediaDescription, String> setter) {
        var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
        var content = getLast(group.getMediaContents());
        var title = createIfNull(content::getMediaDescription, content::setMediaDescription, MediaDescription::new);
        setter.accept(title, value);
    }

    private static void itemMediaGroupMediaContentMediaKeywords(MediaRssItem item, String value, BiConsumer<MediaContent, String> setter) {
        var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
        var content = getLast(group.getMediaContents());
        Mapper.split(value).forEach(keyword -> setter.accept(content, keyword));
    }

    private static void itemMediaGroupMediaContentMediaThumbnail(MediaRssItem item, String value, BiConsumer<MediaThumbnail, String> setter) {
        var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
        var content = getLast(group.getMediaContents());
        var credit = getLast(content.getMediaThumbnails());
        setter.accept(credit, value);
    }

    private static void itemMediaGroupMediaContentMediaCredit(MediaRssItem item, String value, BiConsumer<MediaCredit, String> setter) {
        var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
        var content = getLast(group.getMediaContents());
        var credit = getLast(content.getMediaCredits());
        setter.accept(credit, value);
    }

    private static void itemMediaGroupMediaContentMediaCategory(MediaRssItem item, String value, BiConsumer<MediaCategory, String> setter) {
        var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
        var content = getLast(group.getMediaContents());
        var category = getLast(content.getMediaCategories());
        setter.accept(category, value);
    }

    private static void itemMediaGroupMediaContentMediaRating(MediaRssItem item, String value, BiConsumer<MediaRating, String> setter) {
        var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
        var content = getLast(group.getMediaContents());
        var rating = getLast(content.getMediaRatings());
        setter.accept(rating, value);
    }

    private static void itemMediaGroupMediaContentMediaCommunityMediaTags(MediaRssItem item, String value, BiConsumer<MediaTags, String> setter) {
        var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
        var content = getLast(group.getMediaContents());
        var community = createIfNull(content::getMediaCommunity, content::setMediaCommunity, MediaCommunity::new);
        var tags = createIfNull(community::getMediaTags, community::setMediaTags, MediaTags::new);
        setter.accept(tags, value);
    }

    private static void itemMediaGroupMediaContentMediaCommunityMediaStarRating(MediaRssItem item, String value, BiConsumer<MediaStarRating, String> setter) {
        var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
        var content = getLast(group.getMediaContents());
        var community = createIfNull(content::getMediaCommunity, content::setMediaCommunity, MediaCommunity::new);
        var starRating = createIfNull(community::getMediaStarRating, community::setMediaStarRating, MediaStarRating::new);
        setter.accept(starRating, value);
    }

    private static void itemMediaGroupMediaContentMediaCommunityMediaStatistics(MediaRssItem item, String value, BiConsumer<MediaStatistics, String> setter) {
        var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
        var content = getLast(group.getMediaContents());
        var community = createIfNull(content::getMediaCommunity, content::setMediaCommunity, MediaCommunity::new);
        var statistics = createIfNull(community::getMediaStatistics, community::setMediaStatistics, MediaStatistics::new);
        setter.accept(statistics, value);
    }

    private static void itemMediaGroupMediaContentMediaCopyright(MediaRssItem item, String value, BiConsumer<MediaCopyright, String> setter) {
        var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
        var content = getLast(group.getMediaContents());
        var copyright = createIfNull(content::getMediaCopyright, content::setMediaCopyright, MediaCopyright::new);
        setter.accept(copyright, value);
    }

    private static void itemMediaGroupMediaContentMediaHash(MediaRssItem item, String value, BiConsumer<MediaHash, String> setter) {
        var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
        var content = getLast(group.getMediaContents());
        var hash = getLast(content.getMediaHashes());
        setter.accept(hash, value);
    }

    private static void itemMediaGroupMediaContentMediaPlayer(MediaRssItem item, String value, BiConsumer<MediaPlayer, String> setter) {
        var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
        var content = getLast(group.getMediaContents());
        var player = createIfNull(content::getMediaPlayer, content::setMediaPlayer, MediaPlayer::new);
        setter.accept(player, value);
    }

    private static void itemMediaGroupMediaContentMediaRestriction(MediaRssItem item, String value, BiConsumer<MediaRestriction, String> setter) {
        var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
        var content = getLast(group.getMediaContents());
        var restriction = getLast(content.getMediaRestrictions());
        setter.accept(restriction, value);
    }

    private static void itemMediaGroupMediaContentMediaText(MediaRssItem item, String value, BiConsumer<MediaText, String> setter) {
        var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
        var content = getLast(group.getMediaContents());
        var text = getLast(content.getMediaTexts());
        setter.accept(text, value);
    }

    private static void itemMediaGroupMediaContentStringList(MediaRssItem item, String value, BiConsumer<MediaContent, String> setter) {
        var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
        var content = getLast(group.getMediaContents());
        setter.accept(content, value);
    }

    private static void itemMediaGroupMediaContentMediaEmbed(MediaRssItem item, String value, BiConsumer<MediaEmbed, String> setter) {
        var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
        var content = getLast(group.getMediaContents());
        var embed = createIfNull(content::getMediaEmbed, content::setMediaEmbed, MediaEmbed::new);
        setter.accept(embed, value);
    }

    private static void itemMediaGroupMediaContentMediaLicense(MediaRssItem item, String value, BiConsumer<MediaLicense, String> setter) {
        var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
        var content = getLast(group.getMediaContents());
        var license = getLast(content.getMediaLicenses());
        setter.accept(license, value);
    }

    private static void itemMediaGroupMediaContentMediaSubTitle(MediaRssItem item, String value, BiConsumer<MediaSubTitle, String> setter) {
        var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
        var content = getLast(group.getMediaContents());
        var license = getLast(content.getMediaSubTitles());
        setter.accept(license, value);
    }

    private static void itemMediaGroupMediaContentMediaPeerLink(MediaRssItem item, String value, BiConsumer<MediaPeerLink, String> setter) {
        var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
        var content = getLast(group.getMediaContents());
        var peerLinks = getLast(content.getMediaPeerLinks());
        setter.accept(peerLinks, value);
    }

    private static void itemMediaGroupMediaContentMediaRights(MediaRssItem item, String value, BiConsumer<MediaRights, String> setter) {
        var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
        var content = getLast(group.getMediaContents());
        var rights = createIfNull(content::getMediaRights, content::setMediaRights, MediaRights::new);
        setter.accept(rights, value);
    }

    private static void itemMediaGroupMediaContentMediaScene(MediaRssItem item, String value, BiConsumer<MediaScene, String> setter) {
        var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
        var content = getLast(group.getMediaContents());
        var license = getLast(content.getMediaScenes());
        setter.accept(license, value);
    }

    private static void itemMediaGroupMediaContentMediaStatus(MediaRssItem item, String value, BiConsumer<MediaStatus, String> setter) {
        var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
        var content = getLast(group.getMediaContents());
        var status = createIfNull(content::getMediaStatus, content::setMediaStatus, MediaStatus::new);
        setter.accept(status, value);
    }

    private static void itemMediaGroupMediaContentMediaPrice(MediaRssItem item, String value, BiConsumer<MediaPrice, String> setter) {
        var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
        var content = getLast(group.getMediaContents());
        var price = getLast(content.getMediaPrices());
        setter.accept(price, value);
    }

    private static void itemMediaStatus(MediaRssItem item, String value, BiConsumer<MediaStatus, String> setter) {
        var status = createIfNull(item::getMediaStatus, item::setMediaStatus, MediaStatus::new);
        setter.accept(status, value);
    }

    private static void itemMediaPrice(MediaRssItem item, String value, BiConsumer<MediaPrice, String> setter) {
        setter.accept(getLast(item.getMediaPrices()), value);
    }

    private static void itemMediaLicense(MediaRssItem item, String value, BiConsumer<MediaLicense, String> setter) {
        setter.accept(getLast(item.getMediaLicenses()), value);
    }

    private static void itemMediaRestriction(MediaRssItem item, String value, BiConsumer<MediaRestriction, String> setter) {
        setter.accept(getLast(item.getMediaRestrictions()), value);
    }

    private static void itemMediaScene(MediaRssItem item, String value, BiConsumer<MediaScene, String> setter) {
        setter.accept(getLast(item.getMediaScenes()), value);
    }

    private static void itemMediaLocation(MediaRssItem item, String value, BiConsumer<MediaLocation, String> setter) {
        setter.accept(getLast(item.getMediaLocations()), value);
    }

    private static void itemMediaSubTitle(MediaRssItem item, String value, BiConsumer<MediaSubTitle, String> setter) {
        setter.accept(getLast(item.getMediaSubTitles()), value);
    }

    private static void itemMediaPeerLink(MediaRssItem item, String value, BiConsumer<MediaPeerLink, String> setter) {
        setter.accept(getLast(item.getMediaPeerLinks()), value);
    }

    private static void itemMediaEmbed(MediaRssItem item, String value, BiConsumer<MediaEmbed, String> setter) {
        var embed = createIfNull(item::getMediaEmbed, item::setMediaEmbed, MediaEmbed::new);
        setter.accept(embed, value);
    }

    private static void itemMediaTitle(MediaRssItem item, String value, BiConsumer<MediaTitle, String> setter) {
        var title = createIfNull(item::getMediaTitle, item::setMediaTitle, MediaTitle::new);
        setter.accept(title, value);
    }

    private static void itemMediaDescription(MediaRssItem item, String value, BiConsumer<MediaDescription, String> setter) {
        var description = createIfNull(item::getMediaDescription, item::setMediaDescription, MediaDescription::new);
        setter.accept(description, value);
    }

    private static void itemMediaCopyright(MediaRssItem item, String value, BiConsumer<MediaCopyright, String> setter) {
        var copyright = createIfNull(item::getMediaCopyright, item::setMediaCopyright, MediaCopyright::new);
        setter.accept(copyright, value);
    }

    private static void itemMediaHash(MediaRssItem item, String value, BiConsumer<MediaHash, String> setter) {
        setter.accept(getLast(item.getMediaHashes()), value);
    }

    private static void itemMediaPlayer(MediaRssItem item, String value, BiConsumer<MediaPlayer, String> setter) {
        var player = createIfNull(item::getMediaPlayer, item::setMediaPlayer, MediaPlayer::new);
        setter.accept(player, value);
    }

    private static void itemMediaText(MediaRssItem item, String value, BiConsumer<MediaText, String> setter) {
        setter.accept(getLast(item.getMediaTexts()), value);
    }

    private static void itemMediaThumbnail(MediaRssItem item, String value, BiConsumer<MediaThumbnail, String> setter) {
        setter.accept(getLast(item.getMediaThumbnails()), value);
    }

    private static void itemMediaKeywords(MediaRssItem item, String value, BiConsumer<MediaRssItem, String> setter) {
        Mapper.split(value).forEach(keyword -> setter.accept(item, keyword));
    }

    private static void itemMediaRating(MediaRssItem item, String value, BiConsumer<MediaRating, String> setter) {
        setter.accept(getLast(item.getMediaRatings()), value);
    }

    private static void itemMediaCategory(MediaRssItem item, String value, BiConsumer<MediaCategory, String> setter) {
        setter.accept(getLast(item.getMediaCategories()), value);
    }

    private static void itemMediaCredit(MediaRssItem item, String value, BiConsumer<MediaCredit, String> setter) {
        setter.accept(getLast(item.getMediaCredits()), value);
    }

    private static void itemMediaRights(MediaRssItem item, String value, BiConsumer<MediaRights, String> setter) {
        var rights = createIfNull(item::getMediaRights, item::setMediaRights, MediaRights::new);
        setter.accept(rights, value);
    }
}
