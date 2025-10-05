/*
 * MIT License
 *
 * Copyright (c) 2022, Apptastic Software
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.apptasticsoftware.rssreader.module.mediarss;

import com.apptasticsoftware.rssreader.AbstractRssReader;
import com.apptasticsoftware.rssreader.Channel;
import com.apptasticsoftware.rssreader.DateTimeParser;
import com.apptasticsoftware.rssreader.util.Mapper;

import java.net.http.HttpClient;
import java.util.Optional;
import java.util.function.BiConsumer;

import static com.apptasticsoftware.rssreader.util.Mapper.*;

/**
 * Class for reading media rss feeds.
 */
public class MediaRssReader extends AbstractRssReader<MediaRssChannel, MediaRssItem> {

    /**
     * Constructor
     */
    public MediaRssReader() {
        super();
    }

    /**
     * Constructor
     * @param httpClient http client
     */
    public MediaRssReader(HttpClient httpClient) {
        super(httpClient);
    }

    @Override
    protected MediaRssChannel createChannel(DateTimeParser dateTimeParser) {
        return new MediaRssChannel(dateTimeParser);
    }

    @Override
    protected MediaRssItem createItem(DateTimeParser dateTimeParser) {
        return new MediaRssItem(dateTimeParser);
    }


    @Override
    protected void registerChannelTags() {
        super.registerChannelTags();

        onChannelTags.put("/rss/channel/media:rating", channel -> channel.addMediaRating(new MediaRating()));
        onChannelTags.put("/rss/channel/media:thumbnail", channel -> channel.addMediaThumbnail(new MediaThumbnail()));
        onChannelTags.put("/rss/channel/media:category", channel -> channel.addMediaCategory(new MediaCategory()));
        onChannelTags.put("/rss/channel/media:hash", channel -> channel.addMediaHash(new MediaHash()));
        onChannelTags.put("/rss/channel/media:credit", channel -> channel.addMediaCredit(new MediaCredit()));
        onChannelTags.put("/rss/channel/media:text", channel -> channel.addMediaText(new MediaText()));
        onChannelTags.put("/rss/channel/media:restriction", channel -> channel.addMediaRestriction(new MediaRestriction()));
        onChannelTags.put("/rss/channel/media:price", channel -> channel.addMediaPrice(new MediaPrice()));
        onChannelTags.put("/rss/channel/media:license", channel -> channel.addMediaLicense(new MediaLicense()));
        onChannelTags.put("/rss/channel/media:subTitle", channel -> channel.addMediaSubTitle(new MediaSubTitle()));
        onChannelTags.put("/rss/channel/media:peerLink", channel -> channel.addMediaPeerLink(new MediaPeerLink()));
        onChannelTags.put("/rss/channel/media:location", channel -> channel.addMediaLocation(new MediaLocation()));
        onChannelTags.put("/rss/channel/media:scenes/media:scene", channel -> channel.addMediaScene(new MediaScene()));

        super.addChannelExtension("/rss/channel/media:rating", channelMediaRating(MediaRating::setRating));
        super.addChannelExtension("/rss/channel/media:title", channelMediaTitle(MediaTitle::setTitle));
        super.addChannelExtension("/rss/channel/media:description", channelMediaDescription(MediaDescription::setDescription));
        super.addChannelExtension("/rss/channel/media:keywords", channelMediaKeywords(MediaRssChannel::addMediaKeyword));
        super.addChannelExtension("/rss/channel/media:category", channelMediaCategory(MediaCategory::setCategory));
        super.addChannelExtension("/rss/channel/media:hash", channelMediaHash(MediaHash::setHash));
        super.addChannelExtension("/rss/channel/media:credit", channelMediaCredit(MediaCredit::setCredit));
        super.addChannelExtension("/rss/channel/media:copyright", channelMediaCopyright(MediaCopyright::setCopyright));
        super.addChannelExtension("/rss/channel/media:text", channelMediaText(MediaText::setText));
        super.addChannelExtension("/rss/channel/media:restriction", channelMediaRestriction(MediaRestriction::setRestriction));
        super.addChannelExtension("/rss/channel/media:community/media:tags", channelMediaCommunityMediaTags(MediaTags::setTags));
        super.addChannelExtension("/rss/channel/media:comments/media:comment", MediaRssChannel::addMediaComment);
        super.addChannelExtension("/rss/channel/media:embed/media:param", channelMediaEmbed(MediaEmbed::addParamValue));
        super.addChannelExtension("/rss/channel/media:responses/media:response", MediaRssChannel::addMediaResponse);
        super.addChannelExtension("/rss/channel/media:backLinks/media:backLink", MediaRssChannel::addMediaBackLink);
        super.addChannelExtension("/rss/channel/media:license",  channelMediaLicense(MediaLicense::setLicense));
        super.addChannelExtension("/rss/channel/media:scenes/media:scene/sceneTitle", channelMediaScene(MediaScene::setSceneTitle));
        super.addChannelExtension("/rss/channel/media:scenes/media:scene/sceneDescription", channelMediaScene(MediaScene::setSceneDescription));
        super.addChannelExtension("/rss/channel/media:scenes/media:scene/sceneStartTime", channelMediaScene(MediaScene::setSceneStartTime));
        super.addChannelExtension("/rss/channel/media:scenes/media:scene/sceneEndTime", channelMediaScene(MediaScene::setSceneEndTime));
    }

    @Override
    protected void registerChannelAttributes() {
        super.registerChannelAttributes();

        super.addChannelExtension("/rss/channel/media:rating", "scheme", channelMediaRating(MediaRating::setScheme));
        super.addChannelExtension("/rss/channel/media:title", "type", channelMediaTitle(MediaTitle::setType));
        super.addChannelExtension("/rss/channel/media:description", "type", channelMediaDescription(MediaDescription::setType));
        super.addChannelExtension("/rss/channel/media:thumbnail", "url", channelMediaThumbnail(MediaThumbnail::setUrl));
        super.addChannelExtension("/rss/channel/media:thumbnail", "height", channelMediaThumbnail((thumbnail, value) -> mapInteger(value, thumbnail::setHeight)));
        super.addChannelExtension("/rss/channel/media:thumbnail", "width", channelMediaThumbnail((thumbnail, value) -> mapInteger(value, thumbnail::setWidth)));
        super.addChannelExtension("/rss/channel/media:thumbnail", "time", channelMediaThumbnail(MediaThumbnail::setTime));
        super.addChannelExtension("/rss/channel/media:category", "scheme", channelMediaCategory(MediaCategory::setScheme));
        super.addChannelExtension("/rss/channel/media:category", "label", channelMediaCategory(MediaCategory::setLabel));
        super.addChannelExtension("/rss/channel/media:hash", "algo", channelMediaHash(MediaHash::setAlgorithm));
        super.addChannelExtension("/rss/channel/media:player", "url", channelMediaPlayer(MediaPlayer::setUrl));
        super.addChannelExtension("/rss/channel/media:player", "height", channelMediaPlayer((player, value) -> mapInteger(value, player::setHeight)));
        super.addChannelExtension("/rss/channel/media:player", "width", channelMediaPlayer((player, value) -> mapInteger(value, player::setWidth)));
        super.addChannelExtension("/rss/channel/media:credit", "role", channelMediaCredit(MediaCredit::setRole));
        super.addChannelExtension("/rss/channel/media:credit", "scheme", channelMediaCredit(MediaCredit::setScheme));
        super.addChannelExtension("/rss/channel/media:copyright", "url", channelMediaCopyright(MediaCopyright::setUrl));
        super.addChannelExtension("/rss/channel/media:text", "type", channelMediaText(MediaText::setType));
        super.addChannelExtension("/rss/channel/media:text", "lang", channelMediaText(MediaText::setLang));
        super.addChannelExtension("/rss/channel/media:text", "start", channelMediaText(MediaText::setStart));
        super.addChannelExtension("/rss/channel/media:text", "end", channelMediaText(MediaText::setEnd));
        super.addChannelExtension("/rss/channel/media:restriction", "type", channelMediaRestriction(MediaRestriction::setType));
        super.addChannelExtension("/rss/channel/media:restriction", "relationship", channelMediaRestriction(MediaRestriction::setRelationship));
        super.addChannelExtension("/rss/channel/media:community/media:starRating", "average", channelMediaCommunityMediaStarRating((starRating, value) -> mapDouble(value, starRating::setAverage)));
        super.addChannelExtension("/rss/channel/media:community/media:starRating", "count", channelMediaCommunityMediaStarRating((starRating, value) -> mapInteger(value, starRating::setCount)));
        super.addChannelExtension("/rss/channel/media:community/media:starRating", "max", channelMediaCommunityMediaStarRating((starRating, value) -> mapInteger(value, starRating::setMax)));
        super.addChannelExtension("/rss/channel/media:community/media:starRating", "min", channelMediaCommunityMediaStarRating((starRating, value) -> mapInteger(value, starRating::setMin)));
        super.addChannelExtension("/rss/channel/media:community/media:statistics", "views", channelMediaCommunityMediaStatistics((statistics, value) -> mapLong(value, statistics::setViews)));
        super.addChannelExtension("/rss/channel/media:community/media:statistics", "favorites", channelMediaCommunityMediaStatistics((statistics, value) -> mapInteger(value, statistics::setFavorites)));
        super.addChannelExtension("/rss/channel/media:embed", "url", channelMediaEmbed(MediaEmbed::setUrl));
        super.addChannelExtension("/rss/channel/media:embed", "width", channelMediaEmbed((channel, value) -> mapInteger(value, channel::setWidth)));
        super.addChannelExtension("/rss/channel/media:embed", "height", channelMediaEmbed((channel, value) -> mapInteger(value, channel::setHeight)));
        super.addChannelExtension("/rss/channel/media:embed/media:param", "name", channelMediaEmbed(MediaEmbed::addParamName));
        super.addChannelExtension("/rss/channel/media:status", "reason",  channelMediaStatus(MediaStatus::setReason));
        super.addChannelExtension("/rss/channel/media:status", "state",  channelMediaStatus(MediaStatus::setState));
        super.addChannelExtension("/rss/channel/media:price", "type",  channelMediaPrice(MediaPrice::setType));
        super.addChannelExtension("/rss/channel/media:price", "price",  channelMediaPrice((price, value) -> mapDouble(value, price::setPrice)));
        super.addChannelExtension("/rss/channel/media:price", "currency",  channelMediaPrice(MediaPrice::setCurrency));
        super.addChannelExtension("/rss/channel/media:price", "info",  channelMediaPrice(MediaPrice::setInfo));
        super.addChannelExtension("/rss/channel/media:license", "type",  channelMediaLicense(MediaLicense::setType));
        super.addChannelExtension("/rss/channel/media:license", "href",  channelMediaLicense(MediaLicense::setHref));
        super.addChannelExtension("/rss/channel/media:subTitle", "type",  channelMediaSubTitle(MediaSubTitle::setType));
        super.addChannelExtension("/rss/channel/media:subTitle", "lang",  channelMediaSubTitle(MediaSubTitle::setLang));
        super.addChannelExtension("/rss/channel/media:subTitle", "href",  channelMediaSubTitle(MediaSubTitle::setHref));
        super.addChannelExtension("/rss/channel/media:peerLink", "type",  channelMediaPeerLink(MediaPeerLink::setType));
        super.addChannelExtension("/rss/channel/media:peerLink", "href",  channelMediaPeerLink(MediaPeerLink::setHref));
        super.addChannelExtension("/rss/channel/media:location", "description", channelMediaLocation(MediaLocation::setDescription));
        super.addChannelExtension("/rss/channel/media:location", "start", channelMediaLocation(MediaLocation::setStart));
        super.addChannelExtension("/rss/channel/media:location", "end", channelMediaLocation(MediaLocation::setEnd));
        super.addChannelExtension("/rss/channel/media:rights", "status", channelMediaRights(MediaRights::setStatus));
    }

    @Override
    protected void registerItemTags() {
        super.registerItemTags();
        // media:content
        onItemTags.put("/rss/channel/item/media:content", item -> item.addMediaContents(new MediaContent()));
        onItemTags.put("/rss/channel/item/media:content/media:rating", item -> Optional.ofNullable(item.getMediaContents().getLast()).ifPresent(c -> c.addMediaRating(new MediaRating())));
        onItemTags.put("/rss/channel/item/media:content/media:thumbnail", item -> Optional.ofNullable(item.getMediaContents().getLast()).ifPresent(c -> c.addMediaThumbnail(new MediaThumbnail())));
        onItemTags.put("/rss/channel/item/media:content/media:category", item -> Optional.ofNullable(item.getMediaContents().getLast()).ifPresent(c -> c.addMediaCategory(new MediaCategory())));
        onItemTags.put("/rss/channel/item/media:content/media:hash", item -> Optional.ofNullable(item.getMediaContents().getLast()).ifPresent(c -> c.addMediaHash(new MediaHash())));
        onItemTags.put("/rss/channel/item/media:content/media:credit", item -> Optional.ofNullable(item.getMediaContents().getLast()).ifPresent(c -> c.addMediaCredit(new MediaCredit())));
        onItemTags.put("/rss/channel/item/media:content/media:text", item -> Optional.ofNullable(item.getMediaContents().getLast()).ifPresent(c -> c.addMediaText(new MediaText())));
        onItemTags.put("/rss/channel/item/media:content/media:restriction", item -> Optional.ofNullable(item.getMediaContents().getLast()).ifPresent(c -> c.addMediaRestriction(new MediaRestriction())));
        onItemTags.put("/rss/channel/item/media:content/media:price", item -> Optional.ofNullable(item.getMediaContents().getLast()).ifPresent(c -> c.addMediaPrice(new MediaPrice())));
        onItemTags.put("/rss/channel/item/media:content/media:license", item -> Optional.ofNullable(item.getMediaContents().getLast()).ifPresent(c -> c.addMediaLicense(new MediaLicense())));
        onItemTags.put("/rss/channel/item/media:content/media:subTitle", item -> Optional.ofNullable(item.getMediaContents().getLast()).ifPresent(c -> c.addMediaSubTitle(new MediaSubTitle())));
        onItemTags.put("/rss/channel/item/media:content/media:peerLink", item -> Optional.ofNullable(item.getMediaContents().getLast()).ifPresent(c -> c.addMediaPeerLink(new MediaPeerLink())));
        onItemTags.put("/rss/channel/item/media:content/media:location", item -> Optional.ofNullable(item.getMediaContents().getLast()).ifPresent(c -> c.addMediaLocation(new MediaLocation())));
        onItemTags.put("/rss/channel/item/media:content/media:scenes/media:scene", item -> Optional.ofNullable(item.getMediaContents().getLast()).ifPresent(c -> c.addMediaScene(new MediaScene())));

        super.addItemExtension("/rss/channel/item/media:content/media:rating", itemMediaContentMediaRating(MediaRating::setRating));
        super.addItemExtension("/rss/channel/item/media:content/media:title", itemMediaContentMediaTitle(MediaTitle::setTitle));
        super.addItemExtension("/rss/channel/item/media:content/media:description", itemMediaContentMediaDescription(MediaDescription::setDescription));
        super.addItemExtension("/rss/channel/item/media:content/media:keywords", itemMediaContentMediaKeywords(MediaContent::addMediaKeyword));
        super.addItemExtension("/rss/channel/item/media:content/media:category", itemMediaContentMediaCategory(MediaCategory::setCategory));
        super.addItemExtension("/rss/channel/item/media:content/media:hash", itemMediaContentMediaHash(MediaHash::setHash));
        super.addItemExtension("/rss/channel/item/media:content/media:credit", itemMediaContentMediaCredit(MediaCredit::setCredit));
        super.addItemExtension("/rss/channel/item/media:content/media:copyright", itemMediaContentMediaCopyright(MediaCopyright::setCopyright));
        super.addItemExtension("/rss/channel/item/media:content/media:text", itemMediaContentMediaText(MediaText::setText));
        super.addItemExtension("/rss/channel/item/media:content/media:restriction", itemMediaContentMediaRestriction(MediaRestriction::setRestriction));
        super.addItemExtension("/rss/channel/item/media:content/media:community/media:tags", itemMediaContentMediaCommunityMediaTags(MediaTags::setTags));
        super.addItemExtension("/rss/channel/item/media:content/media:comments/media:comment", itemMediaContentStringList(MediaContent::addMediaComment));
        super.addItemExtension("/rss/channel/item/media:content/media:embed/media:param", itemMediaContentMediaEmbed(MediaEmbed::addParamValue));
        super.addItemExtension("/rss/channel/item/media:content/media:responses/media:response", itemMediaContentStringList(MediaContent::addMediaResponse));
        super.addItemExtension("/rss/channel/item/media:content/media:backLinks/media:backLink", itemMediaContentStringList(MediaContent::addMediaBackLink));
        super.addItemExtension("/rss/channel/item/media:content/media:license", itemMediaContentMediaLicense(MediaLicense::setLicense));
        super.addItemExtension("/rss/channel/item/media:content/media:scenes/media:scene/sceneTitle", itemMediaContentMediaScene(MediaScene::setSceneTitle));
        super.addItemExtension("/rss/channel/item/media:content/media:scenes/media:scene/sceneDescription", itemMediaContentMediaScene(MediaScene::setSceneDescription));
        super.addItemExtension("/rss/channel/item/media:content/media:scenes/media:scene/sceneStartTime", itemMediaContentMediaScene(MediaScene::setSceneStartTime));
        super.addItemExtension("/rss/channel/item/media:content/media:scenes/media:scene/sceneEndTime", itemMediaContentMediaScene(MediaScene::setSceneEndTime));

        // media:group
        onItemTags.put("/rss/channel/item/media:group/media:rating", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.addMediaRating(new MediaRating())));
        onItemTags.put("/rss/channel/item/media:group/media:thumbnail", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.addMediaThumbnail(new MediaThumbnail())));
        onItemTags.put("/rss/channel/item/media:group/media:category", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.addMediaCategory(new MediaCategory())));
        onItemTags.put("/rss/channel/item/media:group/media:hash", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.addMediaHash(new MediaHash())));
        onItemTags.put("/rss/channel/item/media:group/media:credit", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.addMediaCredit(new MediaCredit())));
        onItemTags.put("/rss/channel/item/media:group/media:text", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.addMediaText(new MediaText())));
        onItemTags.put("/rss/channel/item/media:group/media:restriction", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.addMediaRestriction(new MediaRestriction())));
        onItemTags.put("/rss/channel/item/media:group/media:price", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.addMediaPrice(new MediaPrice())));
        onItemTags.put("/rss/channel/item/media:group/media:license", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.addMediaLicense(new MediaLicense())));
        onItemTags.put("/rss/channel/item/media:group/media:subTitle", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.addMediaSubTitle(new MediaSubTitle())));
        onItemTags.put("/rss/channel/item/media:group/media:peerLink", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.addMediaPeerLink(new MediaPeerLink())));
        onItemTags.put("/rss/channel/item/media:group/media:location", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.addMediaLocation(new MediaLocation())));
        onItemTags.put("/rss/channel/item/media:group/media:scenes/media:scene", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.addMediaScene(new MediaScene())));

        super.addItemExtension("/rss/channel/item/media:group/media:rating", itemMediaGroupMediaRating(MediaRating::setRating));
        super.addItemExtension("/rss/channel/item/media:group/media:title", itemMediaGroupMediaTitle(MediaTitle::setTitle));
        super.addItemExtension("/rss/channel/item/media:group/media:description", itemMediaGroupMediaDescription(MediaDescription::setDescription));
        super.addItemExtension("/rss/channel/item/media:group/media:keywords", itemMediaGroupMediaKeywords(MediaGroup::addMediaKeyword));
        super.addItemExtension("/rss/channel/item/media:group/media:category", itemMediaGroupMediaCategory(MediaCategory::setCategory));
        super.addItemExtension("/rss/channel/item/media:group/media:hash", itemMediaGroupMediaHash(MediaHash::setHash));
        super.addItemExtension("/rss/channel/item/media:group/media:credit", itemMediaGroupMediaCredit(MediaCredit::setCredit));
        super.addItemExtension("/rss/channel/item/media:group/media:copyright", itemMediaGroupMediaCopyright(MediaCopyright::setCopyright));
        super.addItemExtension("/rss/channel/item/media:group/media:text", itemMediaGroupMediaText(MediaText::setText));
        super.addItemExtension("/rss/channel/item/media:group/media:restriction", itemMediaGroupMediaRestriction(MediaRestriction::setRestriction));
        super.addItemExtension("/rss/channel/item/media:group/media:community/media:tags", itemMediaGroupMediaCommunityMediaTags(MediaTags::setTags));
        super.addItemExtension("/rss/channel/item/media:group/media:comments/media:comment", itemMediaGroupStringList(MediaGroup::addMediaComment));
        super.addItemExtension("/rss/channel/item/media:group/media:embed/media:param", itemMediaGroupMediaEmbed(MediaEmbed::addParamValue));
        super.addItemExtension("/rss/channel/item/media:group/media:responses/media:response", itemMediaGroupStringList(MediaGroup::addMediaResponse));
        super.addItemExtension("/rss/channel/item/media:group/media:backLinks/media:backLink", itemMediaGroupStringList(MediaGroup::addMediaBackLink));
        super.addItemExtension("/rss/channel/item/media:group/media:license", itemMediaGroupMediaLicense(MediaLicense::setLicense));
        super.addItemExtension("/rss/channel/item/media:group/media:scenes/media:scene/sceneTitle", itemMediaGroupMediaScene(MediaScene::setSceneTitle));
        super.addItemExtension("/rss/channel/item/media:group/media:scenes/media:scene/sceneDescription", itemMediaGroupMediaScene(MediaScene::setSceneDescription));
        super.addItemExtension("/rss/channel/item/media:group/media:scenes/media:scene/sceneStartTime", itemMediaGroupMediaScene(MediaScene::setSceneStartTime));
        super.addItemExtension("/rss/channel/item/media:group/media:scenes/media:scene/sceneEndTime", itemMediaGroupMediaScene(MediaScene::setSceneEndTime));

        // media:group / media:content
        onItemTags.put("/rss/channel/item/media:group/media:content", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.addMediaContent(new MediaContent())));
        onItemTags.put("/rss/channel/item/media:group/media:content/media:rating", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.getMediaContents().getLast().addMediaRating(new MediaRating())));
        onItemTags.put("/rss/channel/item/media:group/media:content/media:thumbnail", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.getMediaContents().getLast().addMediaThumbnail(new MediaThumbnail())));
        onItemTags.put("/rss/channel/item/media:group/media:content/media:category", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.getMediaContents().getLast().addMediaCategory(new MediaCategory())));
        onItemTags.put("/rss/channel/item/media:group/media:content/media:hash", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.getMediaContents().getLast().addMediaHash(new MediaHash())));
        onItemTags.put("/rss/channel/item/media:group/media:content/media:credit", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.getMediaContents().getLast().addMediaCredit(new MediaCredit())));
        onItemTags.put("/rss/channel/item/media:group/media:content/media:text", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.getMediaContents().getLast().addMediaText(new MediaText())));
        onItemTags.put("/rss/channel/item/media:group/media:content/media:restriction", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.getMediaContents().getLast().addMediaRestriction(new MediaRestriction())));
        onItemTags.put("/rss/channel/item/media:group/media:content/media:price", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.getMediaContents().getLast().addMediaPrice(new MediaPrice())));
        onItemTags.put("/rss/channel/item/media:group/media:content/media:license", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.getMediaContents().getLast().addMediaLicense(new MediaLicense())));
        onItemTags.put("/rss/channel/item/media:group/media:content/media:subTitle", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.getMediaContents().getLast().addMediaSubTitle(new MediaSubTitle())));
        onItemTags.put("/rss/channel/item/media:group/media:content/media:peerLink", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.getMediaContents().getLast().addMediaPeerLink(new MediaPeerLink())));
        onItemTags.put("/rss/channel/item/media:group/media:content/media:location", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.getMediaContents().getLast().addMediaLocation(new MediaLocation())));
        onItemTags.put("/rss/channel/item/media:group/media:content/media:scenes/media:scene", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.getMediaContents().getLast().addMediaScene(new MediaScene())));

        super.addItemExtension("/rss/channel/item/media:group/media:content/media:rating", itemMediaGroupMediaContentMediaRating(MediaRating::setRating));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:title", itemMediaGroupMediaContentMediaTitle(MediaTitle::setTitle));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:description", itemMediaGroupMediaContentMediaDescription(MediaDescription::setDescription));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:keywords", itemMediaGroupMediaContentMediaKeywords(MediaContent::addMediaKeyword));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:category", itemMediaGroupMediaContentMediaCategory(MediaCategory::setCategory));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:hash", itemMediaGroupMediaContentMediaHash(MediaHash::setHash));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:credit", itemMediaGroupMediaContentMediaCredit(MediaCredit::setCredit));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:copyright", itemMediaGroupMediaContentMediaCopyright(MediaCopyright::setCopyright));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:text", itemMediaGroupMediaContentMediaText(MediaText::setText));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:restriction", itemMediaGroupMediaContentMediaRestriction(MediaRestriction::setRestriction));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:community/media:tags", itemMediaGroupMediaContentMediaCommunityMediaTags(MediaTags::setTags));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:comments/media:comment", itemMediaGroupMediaContentStringList(MediaContent::addMediaComment));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:embed/media:param", itemMediaGroupMediaContentMediaEmbed(MediaEmbed::addParamValue));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:responses/media:response", itemMediaGroupMediaContentStringList(MediaContent::addMediaResponse));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:backLinks/media:backLink", itemMediaGroupMediaContentStringList(MediaContent::addMediaBackLink));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:license", itemMediaGroupMediaContentMediaLicense(MediaLicense::setLicense));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:scenes/media:scene/sceneTitle", itemMediaGroupMediaContentMediaScene(MediaScene::setSceneTitle));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:scenes/media:scene/sceneDescription", itemMediaGroupMediaContentMediaScene(MediaScene::setSceneDescription));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:scenes/media:scene/sceneStartTime", itemMediaGroupMediaContentMediaScene(MediaScene::setSceneStartTime));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:scenes/media:scene/sceneEndTime", itemMediaGroupMediaContentMediaScene(MediaScene::setSceneEndTime));

        // item
        onItemTags.put("/rss/channel/item/media:rating", item -> item.addMediaRating(new MediaRating()));
        onItemTags.put("/rss/channel/item/media:thumbnail", item -> item.addMediaThumbnail(new MediaThumbnail()));
        onItemTags.put("/rss/channel/item/media:category", item -> item.addMediaCategory(new MediaCategory()));
        onItemTags.put("/rss/channel/item/media:hash", item -> item.addMediaHash(new MediaHash()));
        onItemTags.put("/rss/channel/item/media:credit", item -> item.addMediaCredit(new MediaCredit()));
        onItemTags.put("/rss/channel/item/media:text", item -> item.addMediaText(new MediaText()));
        onItemTags.put("/rss/channel/item/media:restriction", item -> item.addMediaRestriction(new MediaRestriction()));
        onItemTags.put("/rss/channel/item/media:price", item -> item.addMediaPrice(new MediaPrice()));
        onItemTags.put("/rss/channel/item/media:license", item -> item.addMediaLicense(new MediaLicense()));
        onItemTags.put("/rss/channel/item/media:subTitle", item -> item.addMediaSubTitle(new MediaSubTitle()));
        onItemTags.put("/rss/channel/item/media:peerLink", item -> item.addMediaPeerLink(new MediaPeerLink()));
        onItemTags.put("/rss/channel/item/media:location", item -> item.addMediaLocation(new MediaLocation()));
        onItemTags.put("/rss/channel/item/media:scenes/media:scene", item -> item.addMediaScene(new MediaScene()));

        super.addItemExtension("/rss/channel/item/media:rating", itemMediaRating(MediaRating::setRating));
        super.addItemExtension("/rss/channel/item/media:title", itemMediaTitle(MediaTitle::setTitle));
        super.addItemExtension("/rss/channel/item/media:description", itemMediaDescription(MediaDescription::setDescription));
        super.addItemExtension("/rss/channel/item/media:keywords", itemMediaKeywords(MediaRssItem::addMediaKeyword));
        super.addItemExtension("/rss/channel/item/media:category", itemMediaCategory(MediaCategory::setCategory));
        super.addItemExtension("/rss/channel/item/media:hash", itemMediaHash(MediaHash::setHash));
        super.addItemExtension("/rss/channel/item/media:credit", itemMediaCredit(MediaCredit::setCredit));
        super.addItemExtension("/rss/channel/item/media:copyright", itemMediaCopyright(MediaCopyright::setCopyright));
        super.addItemExtension("/rss/channel/item/media:text", itemMediaText(MediaText::setText));
        super.addItemExtension("/rss/channel/item/media:restriction", itemMediaRestriction(MediaRestriction::setRestriction));
        super.addItemExtension("/rss/channel/item/media:community/media:tags", itemMediaCommunityMediaTags(MediaTags::setTags));
        super.addItemExtension("/rss/channel/item/media:comments/media:comment", MediaRssItem::addMediaComment);
        super.addItemExtension("/rss/channel/item/media:embed/media:param", itemMediaEmbed(MediaEmbed::addParamValue));
        super.addItemExtension("/rss/channel/item/media:responses/media:response", MediaRssItem::addMediaResponse);
        super.addItemExtension("/rss/channel/item/media:backLinks/media:backLink", MediaRssItem::addMediaBackLink);
        super.addItemExtension("/rss/channel/item/media:license",  itemMediaLicense(MediaLicense::setLicense));
        super.addItemExtension("/rss/channel/item/media:scenes/media:scene/sceneTitle", itemMediaScene(MediaScene::setSceneTitle));
        super.addItemExtension("/rss/channel/item/media:scenes/media:scene/sceneDescription", itemMediaScene(MediaScene::setSceneDescription));
        super.addItemExtension("/rss/channel/item/media:scenes/media:scene/sceneStartTime", itemMediaScene(MediaScene::setSceneStartTime));
        super.addItemExtension("/rss/channel/item/media:scenes/media:scene/sceneEndTime", itemMediaScene(MediaScene::setSceneEndTime));
    }

    @SuppressWarnings("java:S1192")
    @Override
    protected void registerItemAttributes() {
        super.registerItemAttributes();

        // media:content
        super.addItemExtension("/rss/channel/item/media:content", "url", itemMediaContent(MediaContent::setUrl));
        super.addItemExtension("/rss/channel/item/media:content", "fileSize", itemMediaContent((content, value) -> mapLong(value, content::setFileSize)));
        super.addItemExtension("/rss/channel/item/media:content", "type", itemMediaContent(MediaContent::setType));
        super.addItemExtension("/rss/channel/item/media:content", "medium", itemMediaContent(MediaContent::setMedium));
        super.addItemExtension("/rss/channel/item/media:content", "isDefault", itemMediaContent((content, value) -> mapBoolean(value, content::setDefault)));
        super.addItemExtension("/rss/channel/item/media:content", "expression", itemMediaContent(MediaContent::setExpression));
        super.addItemExtension("/rss/channel/item/media:content", "bitrate", itemMediaContent((content, value) -> mapDouble(value, content::setBitrate)));
        super.addItemExtension("/rss/channel/item/media:content", "framerate", itemMediaContent((content, value) -> mapDouble(value, content::setFramerate)));
        super.addItemExtension("/rss/channel/item/media:content", "samplingrate", itemMediaContent((content, value) -> mapDouble(value, content::setSamplingrate)));
        super.addItemExtension("/rss/channel/item/media:content", "channels", itemMediaContent((content, value) -> mapInteger(value, content::setChannels)));
        super.addItemExtension("/rss/channel/item/media:content", "duration", itemMediaContent((content, value) -> mapInteger(value, content::setDuration)));
        super.addItemExtension("/rss/channel/item/media:content", "height", itemMediaContent((content, value) -> mapInteger(value, content::setHeight)));
        super.addItemExtension("/rss/channel/item/media:content", "width", itemMediaContent((content, value) -> mapInteger(value, content::setWidth)));
        super.addItemExtension("/rss/channel/item/media:content", "lang", itemMediaContent(MediaContent::setLang));

        super.addItemExtension("/rss/channel/item/media:content/media:rating", "scheme", itemMediaContentMediaRating(MediaRating::setScheme));
        super.addItemExtension("/rss/channel/item/media:content/media:title", "type", itemMediaContentMediaTitle(MediaTitle::setType));
        super.addItemExtension("/rss/channel/item/media:content/media:description", "type", itemMediaContentMediaDescription(MediaDescription::setType));
        super.addItemExtension("/rss/channel/item/media:content/media:thumbnail", "url", itemMediaContentMediaThumbnail(MediaThumbnail::setUrl));
        super.addItemExtension("/rss/channel/item/media:content/media:thumbnail", "height", itemMediaContentMediaThumbnail((thumbnail, value) -> mapInteger(value, thumbnail::setHeight)));
        super.addItemExtension("/rss/channel/item/media:content/media:thumbnail", "width", itemMediaContentMediaThumbnail((thumbnail, value) -> mapInteger(value, thumbnail::setWidth)));
        super.addItemExtension("/rss/channel/item/media:content/media:thumbnail", "time", itemMediaContentMediaThumbnail(MediaThumbnail::setTime));
        super.addItemExtension("/rss/channel/item/media:content/media:category", "scheme", itemMediaContentMediaCategory(MediaCategory::setScheme));
        super.addItemExtension("/rss/channel/item/media:content/media:category", "label", itemMediaContentMediaCategory(MediaCategory::setLabel));
        super.addItemExtension("/rss/channel/item/media:content/media:hash", "algo", itemMediaContentMediaHash(MediaHash::setAlgorithm));
        super.addItemExtension("/rss/channel/item/media:content/media:player", "url", itemMediaContentMediaPlayer(MediaPlayer::setUrl));
        super.addItemExtension("/rss/channel/item/media:content/media:player", "height", itemMediaContentMediaPlayer((player, value) -> mapInteger(value, player::setHeight)));
        super.addItemExtension("/rss/channel/item/media:content/media:player", "width", itemMediaContentMediaPlayer((player, value) -> mapInteger(value, player::setWidth)));
        super.addItemExtension("/rss/channel/item/media:content/media:credit", "role", itemMediaContentMediaCredit(MediaCredit::setRole));
        super.addItemExtension("/rss/channel/item/media:content/media:credit", "scheme", itemMediaContentMediaCredit(MediaCredit::setScheme));
        super.addItemExtension("/rss/channel/item/media:content/media:copyright", "url", itemMediaContentMediaCopyright(MediaCopyright::setUrl));
        super.addItemExtension("/rss/channel/item/media:content/media:text", "type", itemMediaContentMediaText(MediaText::setType));
        super.addItemExtension("/rss/channel/item/media:content/media:text", "lang", itemMediaContentMediaText(MediaText::setLang));
        super.addItemExtension("/rss/channel/item/media:content/media:text", "start", itemMediaContentMediaText(MediaText::setStart));
        super.addItemExtension("/rss/channel/item/media:content/media:text", "end", itemMediaContentMediaText(MediaText::setEnd));
        super.addItemExtension("/rss/channel/item/media:content/media:restriction", "type", itemMediaContentMediaRestriction(MediaRestriction::setType));
        super.addItemExtension("/rss/channel/item/media:content/media:restriction", "relationship", itemMediaContentMediaRestriction(MediaRestriction::setRelationship));
        super.addItemExtension("/rss/channel/item/media:content/media:community/media:starRating", "average", itemMediaContentMediaCommunityMediaStarRating((starRating, value) -> mapDouble(value, starRating::setAverage)));
        super.addItemExtension("/rss/channel/item/media:content/media:community/media:starRating", "count", itemMediaContentMediaCommunityMediaStarRating((starRating, value) -> mapInteger(value, starRating::setCount)));
        super.addItemExtension("/rss/channel/item/media:content/media:community/media:starRating", "max", itemMediaContentMediaCommunityMediaStarRating((starRating, value) -> mapInteger(value, starRating::setMax)));
        super.addItemExtension("/rss/channel/item/media:content/media:community/media:starRating", "min", itemMediaContentMediaCommunityMediaStarRating((starRating, value) -> mapInteger(value, starRating::setMin)));
        super.addItemExtension("/rss/channel/item/media:content/media:community/media:statistics", "views", itemMediaContentMediaCommunityMediaStatistics((statistics, value) -> mapLong(value, statistics::setViews)));
        super.addItemExtension("/rss/channel/item/media:content/media:community/media:statistics", "favorites", itemMediaContentMediaCommunityMediaStatistics((statistics, value) -> mapInteger(value, statistics::setFavorites)));
        super.addItemExtension("/rss/channel/item/media:content/media:embed", "url", itemMediaContentMediaEmbed(MediaEmbed::setUrl));
        super.addItemExtension("/rss/channel/item/media:content/media:embed", "width", itemMediaContentMediaEmbed((item, value) -> mapInteger(value, item::setWidth)));
        super.addItemExtension("/rss/channel/item/media:content/media:embed", "height", itemMediaContentMediaEmbed((item, value) -> mapInteger(value, item::setHeight)));
        super.addItemExtension("/rss/channel/item/media:content/media:embed/media:param", "name", itemMediaContentMediaEmbed(MediaEmbed::addParamName));
        super.addItemExtension("/rss/channel/item/media:content/media:status", "reason",  itemMediaContentMediaStatus(MediaStatus::setReason));
        super.addItemExtension("/rss/channel/item/media:content/media:status", "state",  itemMediaContentMediaStatus(MediaStatus::setState));
        super.addItemExtension("/rss/channel/item/media:content/media:price", "type",  itemMediaContentMediaPrice(MediaPrice::setType));
        super.addItemExtension("/rss/channel/item/media:content/media:price", "price",  itemMediaContentMediaPrice((price, value) -> mapDouble(value, price::setPrice)));
        super.addItemExtension("/rss/channel/item/media:content/media:price", "currency",  itemMediaContentMediaPrice(MediaPrice::setCurrency));
        super.addItemExtension("/rss/channel/item/media:content/media:price", "info",  itemMediaContentMediaPrice(MediaPrice::setInfo));
        super.addItemExtension("/rss/channel/item/media:content/media:license", "type", itemMediaContentMediaLicense(MediaLicense::setType));
        super.addItemExtension("/rss/channel/item/media:content/media:license", "href", itemMediaContentMediaLicense(MediaLicense::setHref));
        super.addItemExtension("/rss/channel/item/media:content/media:subTitle", "type",  itemMediaContentMediaSubTitle(MediaSubTitle::setType));
        super.addItemExtension("/rss/channel/item/media:content/media:subTitle", "lang",  itemMediaContentMediaSubTitle(MediaSubTitle::setLang));
        super.addItemExtension("/rss/channel/item/media:content/media:subTitle", "href",  itemMediaContentMediaSubTitle(MediaSubTitle::setHref));
        super.addItemExtension("/rss/channel/item/media:content/media:peerLink", "type",  itemMediaContentMediaPeerLink(MediaPeerLink::setType));
        super.addItemExtension("/rss/channel/item/media:content/media:peerLink", "href",  itemMediaContentMediaPeerLink(MediaPeerLink::setHref));
        super.addItemExtension("/rss/channel/item/media:content/media:location", "description", itemMediaContentMediaLocation(MediaLocation::setDescription));
        super.addItemExtension("/rss/channel/item/media:content/media:location", "start", itemMediaContentMediaLocation(MediaLocation::setStart));
        super.addItemExtension("/rss/channel/item/media:content/media:location", "end", itemMediaContentMediaLocation(MediaLocation::setEnd));
        super.addItemExtension("/rss/channel/item/media:content/media:rights", "status", itemMediaContentMediaRights(MediaRights::setStatus));

        // media:group
        super.addItemExtension("/rss/channel/item/media:group/media:rating", "scheme", itemMediaGroupMediaRating(MediaRating::setScheme));
        super.addItemExtension("/rss/channel/item/media:group/media:title", "type", itemMediaGroupMediaTitle(MediaTitle::setType));
        super.addItemExtension("/rss/channel/item/media:group/media:description", "type", itemMediaGroupMediaDescription(MediaDescription::setType));
        super.addItemExtension("/rss/channel/item/media:group/media:thumbnail", "url", itemMediaGroupMediaThumbnail(MediaThumbnail::setUrl));
        super.addItemExtension("/rss/channel/item/media:group/media:thumbnail", "height", itemMediaGroupMediaThumbnail((thumbnail, value) -> mapInteger(value, thumbnail::setHeight)));
        super.addItemExtension("/rss/channel/item/media:group/media:thumbnail", "width", itemMediaGroupMediaThumbnail((thumbnail, value) -> mapInteger(value, thumbnail::setWidth)));
        super.addItemExtension("/rss/channel/item/media:group/media:thumbnail", "time", itemMediaGroupMediaThumbnail(MediaThumbnail::setTime));
        super.addItemExtension("/rss/channel/item/media:group/media:category", "scheme", itemMediaGroupMediaCategory(MediaCategory::setScheme));
        super.addItemExtension("/rss/channel/item/media:group/media:category", "label", itemMediaGroupMediaCategory(MediaCategory::setLabel));
        super.addItemExtension("/rss/channel/item/media:group/media:hash", "algo", itemMediaGroupMediaHash(MediaHash::setAlgorithm));
        super.addItemExtension("/rss/channel/item/media:group/media:player", "url", itemMediaGroupMediaPlayer(MediaPlayer::setUrl));
        super.addItemExtension("/rss/channel/item/media:group/media:player", "height", itemMediaGroupMediaPlayer((item, value) -> mapInteger(value, item::setHeight)));
        super.addItemExtension("/rss/channel/item/media:group/media:player", "width", itemMediaGroupMediaPlayer((item, value) -> mapInteger(value, item::setWidth)));
        super.addItemExtension("/rss/channel/item/media:group/media:credit", "role", itemMediaGroupMediaCredit(MediaCredit::setRole));
        super.addItemExtension("/rss/channel/item/media:group/media:credit", "scheme", itemMediaGroupMediaCredit(MediaCredit::setScheme));
        super.addItemExtension("/rss/channel/item/media:group/media:copyright", "url", itemMediaGroupMediaCopyright(MediaCopyright::setUrl));
        super.addItemExtension("/rss/channel/item/media:group/media:text", "type", itemMediaGroupMediaText(MediaText::setType));
        super.addItemExtension("/rss/channel/item/media:group/media:text", "lang", itemMediaGroupMediaText(MediaText::setLang));
        super.addItemExtension("/rss/channel/item/media:group/media:text", "start", itemMediaGroupMediaText(MediaText::setStart));
        super.addItemExtension("/rss/channel/item/media:group/media:text", "end", itemMediaGroupMediaText(MediaText::setEnd));
        super.addItemExtension("/rss/channel/item/media:group/media:restriction", "type", itemMediaGroupMediaRestriction(MediaRestriction::setType));
        super.addItemExtension("/rss/channel/item/media:group/media:restriction", "relationship", itemMediaGroupMediaRestriction(MediaRestriction::setRelationship));
        super.addItemExtension("/rss/channel/item/media:group/media:community/media:starRating", "average", itemMediaGroupMediaCommunityMediaStarRating((starRating, value) -> mapDouble(value, starRating::setAverage)));
        super.addItemExtension("/rss/channel/item/media:group/media:community/media:starRating", "count", itemMediaGroupMediaCommunityMediaStarRating((starRating, value) -> mapInteger(value, starRating::setCount)));
        super.addItemExtension("/rss/channel/item/media:group/media:community/media:starRating", "max", itemMediaGroupMediaCommunityMediaStarRating((starRating, value) -> mapInteger(value, starRating::setMax)));
        super.addItemExtension("/rss/channel/item/media:group/media:community/media:starRating", "min", itemMediaGroupMediaCommunityMediaStarRating((starRating, value) -> mapInteger(value, starRating::setMin)));
        super.addItemExtension("/rss/channel/item/media:group/media:community/media:statistics", "views", itemMediaGroupMediaCommunityMediaStatistics((statistics, value) -> mapLong(value, statistics::setViews)));
        super.addItemExtension("/rss/channel/item/media:group/media:community/media:statistics", "favorites", itemMediaGroupMediaCommunityMediaStatistics((statistics, value) -> mapInteger(value, statistics::setFavorites)));
        super.addItemExtension("/rss/channel/item/media:group/media:embed", "url", itemMediaGroupMediaEmbed(MediaEmbed::setUrl));
        super.addItemExtension("/rss/channel/item/media:group/media:embed", "width", itemMediaGroupMediaEmbed((item, value) -> mapInteger(value, item::setWidth)));
        super.addItemExtension("/rss/channel/item/media:group/media:embed", "height", itemMediaGroupMediaEmbed((item, value) -> mapInteger(value, item::setHeight)));
        super.addItemExtension("/rss/channel/item/media:group/media:embed/media:param", "name", itemMediaGroupMediaEmbed(MediaEmbed::addParamName));
        super.addItemExtension("/rss/channel/item/media:group/media:status", "reason",  itemMediaGroupMediaStatus(MediaStatus::setReason));
        super.addItemExtension("/rss/channel/item/media:group/media:status", "state",  itemMediaGroupMediaStatus(MediaStatus::setState));
        super.addItemExtension("/rss/channel/item/media:group/media:price", "type",  itemMediaGroupMediaPrice(MediaPrice::setType));
        super.addItemExtension("/rss/channel/item/media:group/media:price", "price",  itemMediaGroupMediaPrice((price, value) -> mapDouble(value, price::setPrice)));
        super.addItemExtension("/rss/channel/item/media:group/media:price", "currency",  itemMediaGroupMediaPrice(MediaPrice::setCurrency));
        super.addItemExtension("/rss/channel/item/media:group/media:price", "info",  itemMediaGroupMediaPrice(MediaPrice::setInfo));
        super.addItemExtension("/rss/channel/item/media:group/media:license", "type", itemMediaGroupMediaLicense(MediaLicense::setType));
        super.addItemExtension("/rss/channel/item/media:group/media:license", "href", itemMediaGroupMediaLicense(MediaLicense::setHref));
        super.addItemExtension("/rss/channel/item/media:group/media:subTitle", "type",  itemMediaGroupMediaSubTitle(MediaSubTitle::setType));
        super.addItemExtension("/rss/channel/item/media:group/media:subTitle", "lang",  itemMediaGroupMediaSubTitle(MediaSubTitle::setLang));
        super.addItemExtension("/rss/channel/item/media:group/media:subTitle", "href",  itemMediaGroupMediaSubTitle(MediaSubTitle::setHref));
        super.addItemExtension("/rss/channel/item/media:group/media:peerLink", "type",  itemMediaGroupMediaPeerLink(MediaPeerLink::setType));
        super.addItemExtension("/rss/channel/item/media:group/media:peerLink", "href",  itemMediaGroupMediaPeerLink(MediaPeerLink::setHref));
        super.addItemExtension("/rss/channel/item/media:group/media:location", "description", itemMediaGroupMediaLocation(MediaLocation::setDescription));
        super.addItemExtension("/rss/channel/item/media:group/media:location", "start", itemMediaGroupMediaLocation(MediaLocation::setStart));
        super.addItemExtension("/rss/channel/item/media:group/media:location", "end", itemMediaGroupMediaLocation(MediaLocation::setEnd));
        super.addItemExtension("/rss/channel/item/media:group/media:rights", "status", itemMediaGroupMediaRights(MediaRights::setStatus));

        // media:group / media:content
        super.addItemExtension("/rss/channel/item/media:group/media:content", "url", itemMediaGroupMediaContent(MediaContent::setUrl));
        super.addItemExtension("/rss/channel/item/media:group/media:content", "fileSize", itemMediaGroupMediaContent((content, value) -> mapLong(value, content::setFileSize)));
        super.addItemExtension("/rss/channel/item/media:group/media:content", "type", itemMediaGroupMediaContent(MediaContent::setType));
        super.addItemExtension("/rss/channel/item/media:group/media:content", "medium", itemMediaGroupMediaContent(MediaContent::setMedium));
        super.addItemExtension("/rss/channel/item/media:group/media:content", "isDefault", itemMediaGroupMediaContent((content, value) -> mapBoolean(value, content::setDefault)));
        super.addItemExtension("/rss/channel/item/media:group/media:content", "expression", itemMediaGroupMediaContent(MediaContent::setExpression));
        super.addItemExtension("/rss/channel/item/media:group/media:content", "bitrate", itemMediaGroupMediaContent((content, value) -> mapDouble(value, content::setBitrate)));
        super.addItemExtension("/rss/channel/item/media:group/media:content", "framerate", itemMediaGroupMediaContent((content, value) -> mapDouble(value, content::setFramerate)));
        super.addItemExtension("/rss/channel/item/media:group/media:content", "samplingrate", itemMediaGroupMediaContent((content, value) -> mapDouble(value, content::setSamplingrate)));
        super.addItemExtension("/rss/channel/item/media:group/media:content", "channels", itemMediaGroupMediaContent((content, value) -> mapInteger(value, content::setChannels)));
        super.addItemExtension("/rss/channel/item/media:group/media:content", "duration", itemMediaGroupMediaContent((content, value) -> mapInteger(value, content::setDuration)));
        super.addItemExtension("/rss/channel/item/media:group/media:content", "height", itemMediaGroupMediaContent((content, value) -> mapInteger(value, content::setHeight)));
        super.addItemExtension("/rss/channel/item/media:group/media:content", "width", itemMediaGroupMediaContent((content, value) -> mapInteger(value, content::setWidth)));
        super.addItemExtension("/rss/channel/item/media:group/media:content", "lang", itemMediaGroupMediaContent(MediaContent::setLang));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:rating", "scheme", itemMediaGroupMediaContentMediaRating(MediaRating::setScheme));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:title", "type", itemMediaGroupMediaContentMediaTitle(MediaTitle::setType));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:description", "type", itemMediaGroupMediaContentMediaDescription(MediaDescription::setType));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:thumbnail", "url", itemMediaGroupMediaContentMediaThumbnail(MediaThumbnail::setUrl));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:thumbnail", "width", itemMediaGroupMediaContentMediaThumbnail((content, value) -> mapInteger(value, content::setWidth)));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:thumbnail", "height", itemMediaGroupMediaContentMediaThumbnail((content, value) -> mapInteger(value, content::setHeight)));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:thumbnail", "time", itemMediaGroupMediaContentMediaThumbnail(MediaThumbnail::setTime));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:category", "scheme", itemMediaGroupMediaContentMediaCategory(MediaCategory::setScheme));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:category", "label", itemMediaGroupMediaContentMediaCategory(MediaCategory::setLabel));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:hash", "algo", itemMediaGroupMediaContentMediaHash(MediaHash::setAlgorithm));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:player", "url", itemMediaGroupMediaContentMediaPlayer(MediaPlayer::setUrl));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:player", "height", itemMediaGroupMediaContentMediaPlayer((content, value) -> mapInteger(value, content::setHeight)));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:player", "width", itemMediaGroupMediaContentMediaPlayer((content, value) -> mapInteger(value, content::setWidth)));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:credit", "role", itemMediaGroupMediaContentMediaCredit(MediaCredit::setRole));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:credit", "scheme", itemMediaGroupMediaContentMediaCredit(MediaCredit::setScheme));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:copyright", "url", itemMediaGroupMediaContentMediaCopyright(MediaCopyright::setUrl));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:text", "type", itemMediaGroupMediaContentMediaText(MediaText::setType));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:text", "lang", itemMediaGroupMediaContentMediaText(MediaText::setLang));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:text", "start", itemMediaGroupMediaContentMediaText(MediaText::setStart));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:text", "end", itemMediaGroupMediaContentMediaText(MediaText::setEnd));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:restriction", "relationship", itemMediaGroupMediaContentMediaRestriction(MediaRestriction::setRelationship));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:restriction", "type", itemMediaGroupMediaContentMediaRestriction(MediaRestriction::setType));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:community/media:starRating", "average", itemMediaGroupMediaContentMediaCommunityMediaStarRating((starRating, value) -> mapDouble(value, starRating::setAverage)));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:community/media:starRating", "count", itemMediaGroupMediaContentMediaCommunityMediaStarRating((starRating, value) -> mapInteger(value, starRating::setCount)));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:community/media:starRating", "max", itemMediaGroupMediaContentMediaCommunityMediaStarRating((starRating, value) -> mapInteger(value, starRating::setMax)));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:community/media:starRating", "min", itemMediaGroupMediaContentMediaCommunityMediaStarRating((starRating, value) -> mapInteger(value, starRating::setMin)));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:community/media:statistics", "views", itemMediaGroupMediaContentMediaCommunityMediaStatistics((statistics, value) -> mapLong(value, statistics::setViews)));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:community/media:statistics", "favorites", itemMediaGroupMediaContentMediaCommunityMediaStatistics((statistics, value) -> mapInteger(value, statistics::setFavorites)));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:embed", "url", itemMediaGroupMediaContentMediaEmbed(MediaEmbed::setUrl));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:embed", "width", itemMediaGroupMediaContentMediaEmbed((embed, value) -> mapInteger(value, embed::setWidth)));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:embed", "height", itemMediaGroupMediaContentMediaEmbed((embed, value) -> mapInteger(value, embed::setHeight)));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:embed/media:param", "name", itemMediaGroupMediaContentMediaEmbed(MediaEmbed::addParamName));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:status", "state", itemMediaGroupMediaContentMediaStatus(MediaStatus::setState));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:status", "reason", itemMediaGroupMediaContentMediaStatus(MediaStatus::setReason));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:price", "type", itemMediaGroupMediaContentMediaPrice(MediaPrice::setType));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:price", "info", itemMediaGroupMediaContentMediaPrice(MediaPrice::setInfo));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:price", "price", itemMediaGroupMediaContentMediaPrice((content, value) -> mapDouble(value, content::setPrice)));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:price", "currency", itemMediaGroupMediaContentMediaPrice(MediaPrice::setCurrency));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:license", "type", itemMediaGroupMediaContentMediaLicense(MediaLicense::setType));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:license", "href", itemMediaGroupMediaContentMediaLicense(MediaLicense::setHref));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:subTitle", "type", itemMediaGroupMediaContentMediaSubTitle(MediaSubTitle::setType));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:subTitle", "lang", itemMediaGroupMediaContentMediaSubTitle(MediaSubTitle::setLang));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:subTitle", "href", itemMediaGroupMediaContentMediaSubTitle(MediaSubTitle::setHref));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:peerLink", "type", itemMediaGroupMediaContentMediaPeerLink(MediaPeerLink::setType));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:peerLink", "href", itemMediaGroupMediaContentMediaPeerLink(MediaPeerLink::setHref));
        super.addItemExtension("/rss/channel/item/media:group/media:content/media:rights", "status", itemMediaGroupMediaContentMediaRights(MediaRights::setStatus));

        // item
        super.addItemExtension("/rss/channel/item/media:rating", "scheme", itemMediaRating(MediaRating::setScheme));
        super.addItemExtension("/rss/channel/item/media:title", "type", itemMediaTitle(MediaTitle::setType));
        super.addItemExtension("/rss/channel/item/media:description", "type", itemMediaDescription(MediaDescription::setType));
        super.addItemExtension("/rss/channel/item/media:thumbnail", "url", itemMediaThumbnail(MediaThumbnail::setUrl));
        super.addItemExtension("/rss/channel/item/media:thumbnail", "height", itemMediaThumbnail((thumbnail, value) -> mapInteger(value, thumbnail::setHeight)));
        super.addItemExtension("/rss/channel/item/media:thumbnail", "width", itemMediaThumbnail((thumbnail, value) -> mapInteger(value, thumbnail::setWidth)));
        super.addItemExtension("/rss/channel/item/media:thumbnail", "time", itemMediaThumbnail(MediaThumbnail::setTime));
        super.addItemExtension("/rss/channel/item/media:category", "scheme", itemMediaCategory(MediaCategory::setScheme));
        super.addItemExtension("/rss/channel/item/media:category", "label", itemMediaCategory(MediaCategory::setLabel));
        super.addItemExtension("/rss/channel/item/media:hash", "algo", itemMediaHash(MediaHash::setAlgorithm));
        super.addItemExtension("/rss/channel/item/media:player", "url", itemMediaPlayer(MediaPlayer::setUrl));
        super.addItemExtension("/rss/channel/item/media:player", "height", itemMediaPlayer((player, value) -> mapInteger(value, player::setHeight)));
        super.addItemExtension("/rss/channel/item/media:player", "width", itemMediaPlayer((player, value) -> mapInteger(value, player::setWidth)));
        super.addItemExtension("/rss/channel/item/media:credit", "role", itemMediaCredit(MediaCredit::setRole));
        super.addItemExtension("/rss/channel/item/media:credit", "scheme", itemMediaCredit(MediaCredit::setScheme));
        super.addItemExtension("/rss/channel/item/media:copyright", "url", itemMediaCopyright(MediaCopyright::setUrl));
        super.addItemExtension("/rss/channel/item/media:text", "type", itemMediaText(MediaText::setType));
        super.addItemExtension("/rss/channel/item/media:text", "lang", itemMediaText(MediaText::setLang));
        super.addItemExtension("/rss/channel/item/media:text", "start", itemMediaText(MediaText::setStart));
        super.addItemExtension("/rss/channel/item/media:text", "end", itemMediaText(MediaText::setEnd));
        super.addItemExtension("/rss/channel/item/media:restriction", "type", itemMediaRestriction(MediaRestriction::setType));
        super.addItemExtension("/rss/channel/item/media:restriction", "relationship", itemMediaRestriction(MediaRestriction::setRelationship));
        super.addItemExtension("/rss/channel/item/media:community/media:starRating", "average", itemMediaCommunityMediaStarRating((starRating, value) -> mapDouble(value, starRating::setAverage)));
        super.addItemExtension("/rss/channel/item/media:community/media:starRating", "count", itemMediaCommunityMediaStarRating((starRating, value) -> mapInteger(value, starRating::setCount)));
        super.addItemExtension("/rss/channel/item/media:community/media:starRating", "max", itemMediaCommunityMediaStarRating((starRating, value) -> mapInteger(value, starRating::setMax)));
        super.addItemExtension("/rss/channel/item/media:community/media:starRating", "min", itemMediaCommunityMediaStarRating((starRating, value) -> mapInteger(value, starRating::setMin)));
        super.addItemExtension("/rss/channel/item/media:community/media:statistics", "views", itemMediaCommunityMediaStatistics((statistics, value) -> mapLong(value, statistics::setViews)));
        super.addItemExtension("/rss/channel/item/media:community/media:statistics", "favorites", itemMediaCommunityMediaStatistics((statistics, value) -> mapInteger(value, statistics::setFavorites)));
        super.addItemExtension("/rss/channel/item/media:embed", "url", itemMediaEmbed(MediaEmbed::setUrl));
        super.addItemExtension("/rss/channel/item/media:embed", "width", itemMediaEmbed((item, value) -> mapInteger(value, item::setWidth)));
        super.addItemExtension("/rss/channel/item/media:embed", "height", itemMediaEmbed((item, value) -> mapInteger(value, item::setHeight)));
        super.addItemExtension("/rss/channel/item/media:embed/media:param", "name", itemMediaEmbed(MediaEmbed::addParamName));
        super.addItemExtension("/rss/channel/item/media:status", "reason",  itemMediaStatus(MediaStatus::setReason));
        super.addItemExtension("/rss/channel/item/media:status", "state",  itemMediaStatus(MediaStatus::setState));
        super.addItemExtension("/rss/channel/item/media:price", "type",  itemMediaPrice(MediaPrice::setType));
        super.addItemExtension("/rss/channel/item/media:price", "price",  itemMediaPrice((price, value) -> mapDouble(value, price::setPrice)));
        super.addItemExtension("/rss/channel/item/media:price", "currency",  itemMediaPrice(MediaPrice::setCurrency));
        super.addItemExtension("/rss/channel/item/media:price", "info",  itemMediaPrice(MediaPrice::setInfo));
        super.addItemExtension("/rss/channel/item/media:license", "type",  itemMediaLicense(MediaLicense::setType));
        super.addItemExtension("/rss/channel/item/media:license", "href",  itemMediaLicense(MediaLicense::setHref));
        super.addItemExtension("/rss/channel/item/media:subTitle", "type",  itemMediaSubTitle(MediaSubTitle::setType));
        super.addItemExtension("/rss/channel/item/media:subTitle", "lang",  itemMediaSubTitle(MediaSubTitle::setLang));
        super.addItemExtension("/rss/channel/item/media:subTitle", "href",  itemMediaSubTitle(MediaSubTitle::setHref));
        super.addItemExtension("/rss/channel/item/media:peerLink", "type",  itemMediaPeerLink(MediaPeerLink::setType));
        super.addItemExtension("/rss/channel/item/media:peerLink", "href",  itemMediaPeerLink(MediaPeerLink::setHref));
        super.addItemExtension("/rss/channel/item/media:location", "description", itemMediaLocation(MediaLocation::setDescription));
        super.addItemExtension("/rss/channel/item/media:location", "start", itemMediaLocation(MediaLocation::setStart));
        super.addItemExtension("/rss/channel/item/media:location", "end", itemMediaLocation(MediaLocation::setEnd));
        super.addItemExtension("/rss/channel/item/media:rights", "status", itemMediaRights(MediaRights::setStatus));
    }

    // Channel mapping methods

    private static BiConsumer<MediaRssChannel, String> channelMediaRating(BiConsumer<MediaRating, String> setter) {
        return (channel, value) -> setter.accept(channel.getMediaRatings().getLast(), value);
    }

    private static BiConsumer<MediaRssChannel, String> channelMediaTitle(BiConsumer<MediaTitle, String> setter) {
        return (channel, value) -> {
            var title = createIfNull(channel::getMediaTitle, channel::setMediaTitle, MediaTitle::new);
            setter.accept(title, value);
        };
    }

    private static BiConsumer<MediaRssChannel, String> channelMediaDescription(BiConsumer<MediaDescription, String> setter) {
        return (channel, value) -> {
            var description = createIfNull(channel::getMediaDescription, channel::setMediaDescription, MediaDescription::new);
            setter.accept(description, value);
        };
    }

    private static BiConsumer<MediaRssChannel, String> channelMediaKeywords(BiConsumer<MediaRssChannel, String> setter) {
        return (channel, value) ->
                Mapper.split(value).forEach(keyword -> setter.accept(channel, keyword));
    }

    private static BiConsumer<MediaRssChannel, String> channelMediaCategory(BiConsumer<MediaCategory, String> setter) {
        return (channel, value) -> setter.accept(channel.getMediaCategories().getLast(), value);
    }

    private static BiConsumer<MediaRssChannel, String> channelMediaHash(BiConsumer<MediaHash, String> setter) {
        return (channel, value) -> setter.accept(channel.getMediaHashes().getLast(), value);
    }

    private static BiConsumer<MediaRssChannel, String> channelMediaCredit(BiConsumer<MediaCredit, String> setter) {
        return (channel, value) -> setter.accept(channel.getMediaCredits().getLast(), value);
    }

    private static BiConsumer<MediaRssChannel, String> channelMediaCopyright(BiConsumer<MediaCopyright, String> setter) {
        return (channel, value) -> {
            var copyright = createIfNull(channel::getMediaCopyright, channel::setMediaCopyright, MediaCopyright::new);
            setter.accept(copyright, value);
        };
    }

    private static BiConsumer<MediaRssChannel, String> channelMediaText(BiConsumer<MediaText, String> setter) {
        return (channel, value) -> setter.accept(channel.getMediaTexts().getLast(), value);
    }

    private static BiConsumer<MediaRssChannel, String> channelMediaRestriction(BiConsumer<MediaRestriction, String> setter) {
        return (channel, value) -> setter.accept(channel.getMediaRestrictions().getLast(), value);
    }

    private static BiConsumer<MediaRssChannel, String> channelMediaCommunityMediaTags(BiConsumer<MediaTags, String> setter) {
        return (channel, value) -> {
            var community = createIfNull(channel::getMediaCommunity, channel::setMediaCommunity, MediaCommunity::new);
            var tags = createIfNull(community::getMediaTags, community::setMediaTags, MediaTags::new);
            setter.accept(tags, value);
        };
    }

    private static BiConsumer<MediaRssChannel, String> channelMediaEmbed(BiConsumer<MediaEmbed, String> setter) {
        return (channel, value) -> {
            var embed = createIfNull(channel::getMediaEmbed, channel::setMediaEmbed, MediaEmbed::new);
            setter.accept(embed, value);
        };
    }

    private static BiConsumer<MediaRssChannel, String> channelMediaLicense(BiConsumer<MediaLicense, String> setter) {
        return (channel, value) -> setter.accept(channel.getMediaLicenses().getLast(), value);
    }

    private static BiConsumer<MediaRssChannel, String> channelMediaScene(BiConsumer<MediaScene, String> setter) {
        return (channel, value) -> setter.accept(channel.getMediaScenes().getLast(), value);
    }

    private static BiConsumer<MediaRssChannel, String> channelMediaThumbnail(BiConsumer<MediaThumbnail, String> setter) {
        return (channel, value) -> setter.accept(channel.getMediaThumbnails().getLast(), value);
    }

    private static BiConsumer<MediaRssChannel, String> channelMediaPlayer(BiConsumer<MediaPlayer, String> setter) {
        return (channel, value) -> {
            var player = createIfNull(channel::getMediaPlayer, channel::setMediaPlayer, MediaPlayer::new);
            setter.accept(player, value);
        };
    }

    private static BiConsumer<MediaRssChannel, String> channelMediaCommunityMediaStarRating(BiConsumer<MediaStarRating, String> setter) {
        return (channel, value) -> {
            var community = createIfNull(channel::getMediaCommunity, channel::setMediaCommunity, MediaCommunity::new);
            var starRating = createIfNull(community::getMediaStarRating, community::setMediaStarRating, MediaStarRating::new);
            setter.accept(starRating, value);
        };
    }

    private static BiConsumer<MediaRssChannel, String> channelMediaCommunityMediaStatistics(BiConsumer<MediaStatistics, String> setter) {
        return (channel, value) -> {
            var community = createIfNull(channel::getMediaCommunity, channel::setMediaCommunity, MediaCommunity::new);
            var statistics = createIfNull(community::getMediaStatistics, community::setMediaStatistics, MediaStatistics::new);
            setter.accept(statistics, value);
        };
    }

    private static BiConsumer<MediaRssChannel, String> channelMediaStatus(BiConsumer<MediaStatus, String> setter) {
        return (channel, value) -> {
            var status = createIfNull(channel::getMediaStatus, channel::setMediaStatus, MediaStatus::new);
            setter.accept(status, value);
        };
    }

    private static BiConsumer<MediaRssChannel, String> channelMediaPrice(BiConsumer<MediaPrice, String> setter) {
        return (channel, value) -> setter.accept(channel.getMediaPrices().getLast(), value);
    }

    private static BiConsumer<MediaRssChannel, String> channelMediaSubTitle(BiConsumer<MediaSubTitle, String> setter) {
        return (channel, value) -> setter.accept(channel.getMediaSubTitles().getLast(), value);
    }

    private static BiConsumer<MediaRssChannel, String> channelMediaPeerLink(BiConsumer<MediaPeerLink, String> setter) {
        return (channel, value) -> setter.accept(channel.getMediaPeerLinks().getLast(), value);
    }

    private static BiConsumer<MediaRssChannel, String> channelMediaLocation(BiConsumer<MediaLocation, String> setter) {
        return (channel, value) -> setter.accept(channel.getMediaLocations().getLast(), value);
    }

    private static BiConsumer<MediaRssChannel, String> channelMediaRights(BiConsumer<MediaRights, String> setter) {
        return (channel, value) -> {
            var rights = createIfNull(channel::getMediaRights, channel::setMediaRights, MediaRights::new);
            setter.accept(rights, value);
        };
    }

    // Item mapping methods

    private static BiConsumer<MediaRssItem, String> itemMediaContent(BiConsumer<MediaContent, String> setter) {
        return (item, value) -> Optional.ofNullable(item.getMediaContents().getLast())
                .ifPresent(content -> setter.accept(content, value));
    }

    private static BiConsumer<MediaRssItem, String> itemMediaContentMediaThumbnail(BiConsumer<MediaThumbnail, String> setter) {
        return (item, value) -> {
            var content = item.getMediaContents().getLast();
            var thumbnail = content.getMediaThumbnails().getLast();
            setter.accept(thumbnail, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaContentMediaTitle(BiConsumer<MediaTitle, String> setter) {
        return (item, value) -> {
            var content = item.getMediaContents().getLast();
            var title = createIfNull(content::getMediaTitle, content::setMediaTitle, MediaTitle::new);
            setter.accept(title, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaContentMediaDescription(BiConsumer<MediaDescription, String> setter) {
        return (item, value) -> {
            var content = item.getMediaContents().getLast();
            var description = createIfNull(content::getMediaDescription, content::setMediaDescription, MediaDescription::new);
            setter.accept(description, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaContentMediaPlayer(BiConsumer<MediaPlayer, String> setter) {
        return (item, value) -> {
            var content = item.getMediaContents().getLast();
            var player = createIfNull(content::getMediaPlayer, content::setMediaPlayer, MediaPlayer::new);
            setter.accept(player, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaContentMediaCredit(BiConsumer<MediaCredit, String> setter) {
        return (item, value) -> {
            var content = item.getMediaContents().getLast();
            var credit = content.getMediaCredits().getLast();
            setter.accept(credit, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaContentMediaCopyright(BiConsumer<MediaCopyright, String> setter) {
        return (item, value) -> {
            var content = item.getMediaContents().getLast();
            var copyright = createIfNull(content::getMediaCopyright, content::setMediaCopyright, MediaCopyright::new);
            setter.accept(copyright, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaContentMediaHash(BiConsumer<MediaHash, String> setter) {
        return (item, value) -> {
            var content = item.getMediaContents().getLast();
            setter.accept(content.getMediaHashes().getLast(), value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaContentMediaCategory(BiConsumer<MediaCategory, String> setter) {
        return (item, value) -> {
            var content = item.getMediaContents().getLast();
            setter.accept(content.getMediaCategories().getLast(), value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaContentMediaText(BiConsumer<MediaText, String> setter) {
        return (item, value) -> {
            var content = item.getMediaContents().getLast();
            setter.accept(content.getMediaTexts().getLast(), value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaContentMediaRating(BiConsumer<MediaRating, String> setter) {
        return (item, value) -> {
            var content = item.getMediaContents().getLast();
            setter.accept(content.getMediaRatings().getLast(), value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaContentMediaLicense(BiConsumer<MediaLicense, String> setter) {
        return (item, value) -> {
            var content = item.getMediaContents().getLast();
            setter.accept(content.getMediaLicenses().getLast(), value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaContentMediaRights(BiConsumer<MediaRights, String> setter) {
        return (item, value) -> {
            var content = item.getMediaContents().getLast();
            var rights = createIfNull(content::getMediaRights, content::setMediaRights, MediaRights::new);
            setter.accept(rights, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaContentMediaKeywords(BiConsumer<MediaContent, String> setter) {
        return (item, value) -> {
            var content = item.getMediaContents().getLast();
            Mapper.split(value).forEach(keyword -> setter.accept(content, keyword));
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaContentMediaRestriction(BiConsumer<MediaRestriction, String> setter) {
        return (item, value) -> {
            var content = item.getMediaContents().getLast();
            var restrictions = content.getMediaRestrictions().getLast();
            setter.accept(restrictions, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaContentMediaCommunityMediaStarRating(BiConsumer<MediaStarRating, String> setter) {
        return (item, value) -> {
            var content = item.getMediaContents().getLast();
            var community = createIfNull(content::getMediaCommunity, content::setMediaCommunity, MediaCommunity::new);
            var starRating = createIfNull(community::getMediaStarRating, community::setMediaStarRating, MediaStarRating::new);
            setter.accept(starRating, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaContentMediaCommunityMediaStatistics(BiConsumer<MediaStatistics, String> setter) {
        return (item, value) -> {
            var content = item.getMediaContents().getLast();
            var community = createIfNull(content::getMediaCommunity, content::setMediaCommunity, MediaCommunity::new);
            var statistics = createIfNull(community::getMediaStatistics, community::setMediaStatistics, MediaStatistics::new);
            setter.accept(statistics, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaContentMediaCommunityMediaTags(BiConsumer<MediaTags, String> setter) {
        return (item, value) -> {
            var content = item.getMediaContents().getLast();
            var community = createIfNull(content::getMediaCommunity, content::setMediaCommunity, MediaCommunity::new);
            var tags = createIfNull(community::getMediaTags, community::setMediaTags, MediaTags::new);
            setter.accept(tags, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaContentStringList(BiConsumer<MediaContent, String> setter) {
        return (item, value) -> {
            var content = item.getMediaContents().getLast();
            setter.accept(content, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaContentMediaStatus(BiConsumer<MediaStatus, String> setter) {
        return (item, value) -> {
            var content = item.getMediaContents().getLast();
            var status = createIfNull(content::getMediaStatus, content::setMediaStatus, MediaStatus::new);
            setter.accept(status, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaContentMediaPrice(BiConsumer<MediaPrice, String> setter) {
        return (item, value) -> {
            var content = item.getMediaContents().getLast();
            setter.accept(content.getMediaPrices().getLast(), value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaContentMediaEmbed(BiConsumer<MediaEmbed, String> setter) {
        return (item, value) -> {
            var content = item.getMediaContents().getLast();
            var embed = createIfNull(content::getMediaEmbed, content::setMediaEmbed, MediaEmbed::new);
            setter.accept(embed, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaContentMediaScene(BiConsumer<MediaScene, String> setter) {
        return (item, value) -> {
            var content = item.getMediaContents().getLast();
            var scene = content.getMediaScenes().getLast();
            setter.accept(scene, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaContentMediaSubTitle(BiConsumer<MediaSubTitle, String> setter) {
        return (item, value) -> {
            var content = item.getMediaContents().getLast();
            setter.accept(content.getMediaSubTitles().getLast(), value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaContentMediaPeerLink(BiConsumer<MediaPeerLink, String> setter) {
        return (item, value) -> {
            var content = item.getMediaContents().getLast();
            setter.accept(content.getMediaPeerLinks().getLast(), value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaContentMediaLocation(BiConsumer<MediaLocation, String> setter) {
        return (item, value) -> {
            var content = item.getMediaContents().getLast();
            setter.accept(content.getMediaLocations().getLast(), value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaGroupMediaContent(BiConsumer<MediaContent, String> setter) {
        return (item, value) -> item.getMediaGroup()
                .map(group -> group.getMediaContents().getLast())
                .ifPresent(content -> setter.accept(content, value));
    }

    private static BiConsumer<MediaRssItem, String> itemMediaGroupMediaTitle(BiConsumer<MediaTitle, String> setter) {
        return (item, value) -> {
            var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
            var title = createIfNull(group::getMediaTitle, group::setMediaTitle, MediaTitle::new);
            setter.accept(title, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaGroupMediaDescription(BiConsumer<MediaDescription, String> setter) {
        return (item, value) -> {
            var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
            var description = createIfNull(group::getMediaDescription, group::setMediaDescription, MediaDescription::new);
            setter.accept(description, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaGroupMediaCopyright(BiConsumer<MediaCopyright, String> setter) {
        return (item, value) -> {
            var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
            var copyright = createIfNull(group::getMediaCopyright, group::setMediaCopyright, MediaCopyright::new);
            setter.accept(copyright, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaGroupMediaHash(BiConsumer<MediaHash, String> setter) {
        return (item, value) -> {
            var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
            setter.accept(group.getMediaHashes().getLast(), value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaGroupMediaPlayer(BiConsumer<MediaPlayer, String> setter) {
        return (item, value) -> {
            var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
            var copyright = createIfNull(group::getMediaPlayer, group::setMediaPlayer, MediaPlayer::new);
            setter.accept(copyright, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaGroupMediaRestriction(BiConsumer<MediaRestriction, String> setter) {
        return (item, value) -> {
            var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
            var restriction = group.getMediaRestrictions().getLast();
            setter.accept(restriction, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaGroupMediaText(BiConsumer<MediaText, String> setter) {
        return (item, value) -> {
            var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
            var text = group.getMediaTexts().getLast();
            setter.accept(text, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaGroupStringList(BiConsumer<MediaGroup, String> setter) {
        return (item, value) -> {
            var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
            setter.accept(group, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaGroupMediaKeywords(BiConsumer<MediaGroup, String> setter) {
        return (item, value) -> {
            var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
            Mapper.split(value).forEach(keyword -> setter.accept(group, keyword));
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaGroupMediaCredit(BiConsumer<MediaCredit, String> setter) {
        return (item, value) -> {
            var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
            setter.accept(group.getMediaCredits().getLast(), value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaGroupMediaCategory(BiConsumer<MediaCategory, String> setter) {
        return (item, value) -> {
            var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
            setter.accept(group.getMediaCategories().getLast(), value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaGroupMediaRating(BiConsumer<MediaRating, String> setter) {
        return (item, value) -> {
            var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
            var rating = group.getMediaRatings().getLast();
            setter.accept(rating, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaGroupMediaThumbnail(BiConsumer<MediaThumbnail, String> setter) {
        return (item, value) -> {
            var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
            var thumbnail = group.getMediaThumbnails().getLast();
            setter.accept(thumbnail, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaGroupMediaCommunityMediaStarRating(BiConsumer<MediaStarRating, String> setter) {
        return (item, value) -> {
            var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
            var community = createIfNull(group::getMediaCommunity, group::setMediaCommunity, MediaCommunity::new);
            var starRating = createIfNull(community::getMediaStarRating, community::setMediaStarRating, MediaStarRating::new);
            setter.accept(starRating, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaGroupMediaCommunityMediaStatistics(BiConsumer<MediaStatistics, String> setter) {
        return (item, value) -> {
            var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
            var community = createIfNull(group::getMediaCommunity, group::setMediaCommunity, MediaCommunity::new);
            var statistics = createIfNull(community::getMediaStatistics, community::setMediaStatistics, MediaStatistics::new);
            setter.accept(statistics, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaGroupMediaCommunityMediaTags(BiConsumer<MediaTags, String> setter) {
        return (item, value) -> {
            var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
            var community = createIfNull(group::getMediaCommunity, group::setMediaCommunity, MediaCommunity::new);
            var tags = createIfNull(community::getMediaTags, community::setMediaTags, MediaTags::new);
            setter.accept(tags, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaGroupMediaStatus(BiConsumer<MediaStatus, String> setter) {
        return (item, value) -> {
            var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
            var status = createIfNull(group::getMediaStatus, group::setMediaStatus, MediaStatus::new);
            setter.accept(status, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaGroupMediaPrice(BiConsumer<MediaPrice, String> setter) {
        return (item, value) -> {
            var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
            var price = group.getMediaPrices().getLast();
            setter.accept(price, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaGroupMediaEmbed(BiConsumer<MediaEmbed, String> setter) {
        return (item, value) -> {
            var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
            var embed = createIfNull(group::getMediaEmbed, group::setMediaEmbed, MediaEmbed::new);
            setter.accept(embed, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaGroupMediaLicense(BiConsumer<MediaLicense, String> setter) {
        return (item, value) -> {
            var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
            setter.accept(group.getMediaLicenses().getLast(), value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaGroupMediaRights(BiConsumer<MediaRights, String> setter) {
        return (item, value) -> {
            var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
            var rights = createIfNull(group::getMediaRights, group::setMediaRights, MediaRights::new);
            setter.accept(rights, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaGroupMediaScene(BiConsumer<MediaScene, String> setter) {
        return (item, value) -> {
            var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
            var scene = group.getMediaScenes().getLast();
            setter.accept(scene, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaGroupMediaSubTitle(BiConsumer<MediaSubTitle, String> setter) {
        return (item, value) -> {
            var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
            setter.accept(group.getMediaSubTitles().getLast(), value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaGroupMediaPeerLink(BiConsumer<MediaPeerLink, String> setter) {
        return (item, value) -> {
            var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
            setter.accept(group.getMediaPeerLinks().getLast(), value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaGroupMediaLocation(BiConsumer<MediaLocation, String> setter) {
        return (item, value) -> {
            var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
            setter.accept(group.getMediaLocations().getLast(), value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaCommunityMediaStarRating(BiConsumer<MediaStarRating, String> setter) {
        return (item, value) -> {
            var community = createIfNull(item::getMediaCommunity, item::setMediaCommunity, MediaCommunity::new);
            var starRating = createIfNull(community::getMediaStarRating, community::setMediaStarRating, MediaStarRating::new);
            setter.accept(starRating, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaCommunityMediaStatistics(BiConsumer<MediaStatistics, String> setter) {
        return (item, value) -> {
            var community = createIfNull(item::getMediaCommunity, item::setMediaCommunity, MediaCommunity::new);
            var statistics = createIfNull(community::getMediaStatistics, community::setMediaStatistics, MediaStatistics::new);
            setter.accept(statistics, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaCommunityMediaTags(BiConsumer<MediaTags, String> setter) {
        return (item, value) -> {
            var community = createIfNull(item::getMediaCommunity, item::setMediaCommunity, MediaCommunity::new);
            var tags = createIfNull(community::getMediaTags, community::setMediaTags, MediaTags::new);
            setter.accept(tags, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaGroupMediaContentMediaTitle(BiConsumer<MediaTitle, String> setter) {
        return (item, value) -> {
            var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
            var content = group.getMediaContents().getLast();
            var title = createIfNull(content::getMediaTitle, content::setMediaTitle, MediaTitle::new);
            setter.accept(title, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaGroupMediaContentMediaDescription(BiConsumer<MediaDescription, String> setter) {
        return (item, value) -> {
            var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
            var content = group.getMediaContents().getLast();
            var title = createIfNull(content::getMediaDescription, content::setMediaDescription, MediaDescription::new);
            setter.accept(title, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaGroupMediaContentMediaKeywords(BiConsumer<MediaContent, String> setter) {
        return (item, value) -> {
            var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
            var content = group.getMediaContents().getLast();
            Mapper.split(value).forEach(keyword -> setter.accept(content, keyword));
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaGroupMediaContentMediaThumbnail(BiConsumer<MediaThumbnail, String> setter) {
        return (item, value) -> {
            var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
            var content = group.getMediaContents().getLast();
            var credit = content.getMediaThumbnails().getLast();
            setter.accept(credit, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaGroupMediaContentMediaCredit(BiConsumer<MediaCredit, String> setter) {
        return (item, value) -> {
            var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
            var content = group.getMediaContents().getLast();
            var credit = content.getMediaCredits().getLast();
            setter.accept(credit, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaGroupMediaContentMediaCategory(BiConsumer<MediaCategory, String> setter) {
        return (item, value) -> {
            var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
            var content = group.getMediaContents().getLast();
            var category = content.getMediaCategories().getLast();
            setter.accept(category, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaGroupMediaContentMediaRating(BiConsumer<MediaRating, String> setter) {
        return (item, value) -> {
            var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
            var content = group.getMediaContents().getLast();
            var rating = content.getMediaRatings().getLast();
            setter.accept(rating, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaGroupMediaContentMediaCommunityMediaTags(BiConsumer<MediaTags, String> setter) {
        return (item, value) -> {
            var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
            var content = group.getMediaContents().getLast();
            var community = createIfNull(content::getMediaCommunity, content::setMediaCommunity, MediaCommunity::new);
            var tags = createIfNull(community::getMediaTags, community::setMediaTags, MediaTags::new);
            setter.accept(tags, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaGroupMediaContentMediaCommunityMediaStarRating(BiConsumer<MediaStarRating, String> setter) {
        return (item, value) -> {
            var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
            var content = group.getMediaContents().getLast();
            var community = createIfNull(content::getMediaCommunity, content::setMediaCommunity, MediaCommunity::new);
            var starRating = createIfNull(community::getMediaStarRating, community::setMediaStarRating, MediaStarRating::new);
            setter.accept(starRating, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaGroupMediaContentMediaCommunityMediaStatistics(BiConsumer<MediaStatistics, String> setter) {
        return (item, value) -> {
            var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
            var content = group.getMediaContents().getLast();
            var community = createIfNull(content::getMediaCommunity, content::setMediaCommunity, MediaCommunity::new);
            var statistics = createIfNull(community::getMediaStatistics, community::setMediaStatistics, MediaStatistics::new);
            setter.accept(statistics, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaGroupMediaContentMediaCopyright(BiConsumer<MediaCopyright, String> setter) {
        return (item, value) -> {
            var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
            var content = group.getMediaContents().getLast();
            var copyright = createIfNull(content::getMediaCopyright, content::setMediaCopyright, MediaCopyright::new);
            setter.accept(copyright, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaGroupMediaContentMediaHash(BiConsumer<MediaHash, String> setter) {
        return (item, value) -> {
            var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
            var content = group.getMediaContents().getLast();
            var hash = content.getMediaHashes().getLast();
            setter.accept(hash, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaGroupMediaContentMediaPlayer(BiConsumer<MediaPlayer, String> setter) {
        return (item, value) -> {
            var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
            var content = group.getMediaContents().getLast();
            var player = createIfNull(content::getMediaPlayer, content::setMediaPlayer, MediaPlayer::new);
            setter.accept(player, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaGroupMediaContentMediaRestriction(BiConsumer<MediaRestriction, String> setter) {
        return (item, value) -> {
            var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
            var content = group.getMediaContents().getLast();
            var restriction = content.getMediaRestrictions().getLast();
            setter.accept(restriction, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaGroupMediaContentMediaText(BiConsumer<MediaText, String> setter) {
        return (item, value) -> {
            var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
            var content = group.getMediaContents().getLast();
            var text = content.getMediaTexts().getLast();
            setter.accept(text, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaGroupMediaContentStringList(BiConsumer<MediaContent, String> setter) {
        return (item, value) -> {
            var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
            var content = group.getMediaContents().getLast();
            setter.accept(content, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaGroupMediaContentMediaEmbed(BiConsumer<MediaEmbed, String> setter) {
        return (item, value) -> {
            var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
            var content = group.getMediaContents().getLast();
            var embed = createIfNull(content::getMediaEmbed, content::setMediaEmbed, MediaEmbed::new);
            setter.accept(embed, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaGroupMediaContentMediaLicense(BiConsumer<MediaLicense, String> setter) {
        return (item, value) -> {
            var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
            var content = group.getMediaContents().getLast();
            var license = content.getMediaLicenses().getLast();
            setter.accept(license, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaGroupMediaContentMediaSubTitle(BiConsumer<MediaSubTitle, String> setter) {
        return (item, value) -> {
            var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
            var content = group.getMediaContents().getLast();
            var license = content.getMediaSubTitles().getLast();
            setter.accept(license, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaGroupMediaContentMediaPeerLink(BiConsumer<MediaPeerLink, String> setter) {
        return (item, value) -> {
            var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
            var content = group.getMediaContents().getLast();
            var peerLinks = content.getMediaPeerLinks().getLast();
            setter.accept(peerLinks, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaGroupMediaContentMediaRights(BiConsumer<MediaRights, String> setter) {
        return (item, value) -> {
            var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
            var content = group.getMediaContents().getLast();
            var rights = createIfNull(content::getMediaRights, content::setMediaRights, MediaRights::new);
            setter.accept(rights, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaGroupMediaContentMediaScene(BiConsumer<MediaScene, String> setter) {
        return (item, value) -> {
            var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
            var content = group.getMediaContents().getLast();
            var license = content.getMediaScenes().getLast();
            setter.accept(license, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaGroupMediaContentMediaStatus(BiConsumer<MediaStatus, String> setter) {
        return (item, value) -> {
            var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
            var content = group.getMediaContents().getLast();
            var status = createIfNull(content::getMediaStatus, content::setMediaStatus, MediaStatus::new);
            setter.accept(status, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaGroupMediaContentMediaPrice(BiConsumer<MediaPrice, String> setter) {
        return (item, value) -> {
            var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
            var content = group.getMediaContents().getLast();
            var price = content.getMediaPrices().getLast();
            setter.accept(price, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaStatus(BiConsumer<MediaStatus, String> setter) {
        return (item, value) -> {
            var status = createIfNull(item::getMediaStatus, item::setMediaStatus, MediaStatus::new);
            setter.accept(status, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaPrice(BiConsumer<MediaPrice, String> setter) {
        return (item, value) -> setter.accept(item.getMediaPrices().getLast(), value);
    }

    private static BiConsumer<MediaRssItem, String> itemMediaLicense(BiConsumer<MediaLicense, String> setter) {
        return (item, value) -> setter.accept(item.getMediaLicenses().getLast(), value);
    }

    private static BiConsumer<MediaRssItem, String> itemMediaRestriction(BiConsumer<MediaRestriction, String> setter) {
        return (item, value) -> setter.accept(item.getMediaRestrictions().getLast(), value);
    }

    private static BiConsumer<MediaRssItem, String> itemMediaScene(BiConsumer<MediaScene, String> setter) {
        return (item, value) -> setter.accept(item.getMediaScenes().getLast(), value);
    }

    private static BiConsumer<MediaRssItem, String> itemMediaLocation(BiConsumer<MediaLocation, String> setter) {
        return (item, value) -> setter.accept(item.getMediaLocations().getLast(), value);
    }

    private static BiConsumer<MediaRssItem, String> itemMediaSubTitle(BiConsumer<MediaSubTitle, String> setter) {
        return (item, value) -> setter.accept(item.getMediaSubTitles().getLast(), value);
    }

    private static BiConsumer<MediaRssItem, String> itemMediaPeerLink(BiConsumer<MediaPeerLink, String> setter) {
        return (item, value) -> setter.accept(item.getMediaPeerLinks().getLast(), value);
    }

    private static BiConsumer<MediaRssItem, String> itemMediaEmbed(BiConsumer<MediaEmbed, String> setter) {
        return (item, value) -> {
            var embed = createIfNull(item::getMediaEmbed, item::setMediaEmbed, MediaEmbed::new);
            setter.accept(embed, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaTitle(BiConsumer<MediaTitle, String> setter) {
        return (item, value) -> {
            var title = createIfNull(item::getMediaTitle, item::setMediaTitle, MediaTitle::new);
            setter.accept(title, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaDescription(BiConsumer<MediaDescription, String> setter) {
        return (item, value) -> {
            var description = createIfNull(item::getMediaDescription, item::setMediaDescription, MediaDescription::new);
            setter.accept(description, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaCopyright(BiConsumer<MediaCopyright, String> setter) {
        return (item, value) -> {
            var copyright = createIfNull(item::getMediaCopyright, item::setMediaCopyright, MediaCopyright::new);
            setter.accept(copyright, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaHash(BiConsumer<MediaHash, String> setter) {
        return (item, value) -> setter.accept(item.getMediaHashes().getLast(), value);
    }

    private static BiConsumer<MediaRssItem, String> itemMediaPlayer(BiConsumer<MediaPlayer, String> setter) {
        return (item, value) -> {
            var player = createIfNull(item::getMediaPlayer, item::setMediaPlayer, MediaPlayer::new);
            setter.accept(player, value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaText(BiConsumer<MediaText, String> setter) {
        return (item, value) -> setter.accept(item.getMediaTexts().getLast(), value);
    }

    private static BiConsumer<MediaRssItem, String> itemMediaThumbnail(BiConsumer<MediaThumbnail, String> setter) {
        return (item, value) -> setter.accept(item.getMediaThumbnails().getLast(), value);
    }

    private static BiConsumer<MediaRssItem, String> itemMediaKeywords(BiConsumer<MediaRssItem, String> setter) {
        return (item, value) ->
                Mapper.split(value).forEach(keyword -> setter.accept(item, keyword));
    }

    private static BiConsumer<MediaRssItem, String> itemMediaRating(BiConsumer<MediaRating, String> setter) {
        return (item, value) -> setter.accept(item.getMediaRatings().getLast(), value);
    }

    private static BiConsumer<MediaRssItem, String> itemMediaCategory(BiConsumer<MediaCategory, String> setter) {
        return (item, value) -> setter.accept(item.getMediaCategories().getLast(), value);
    }

    private static BiConsumer<MediaRssItem, String> itemMediaCredit(BiConsumer<MediaCredit, String> setter) {
        return (item, value) -> setter.accept(item.getMediaCredits().getLast(), value);
    }

    private static BiConsumer<MediaRssItem, String> itemMediaRights(BiConsumer<MediaRights, String> setter) {
        return (item, value) -> {
            var rights = createIfNull(item::getMediaRights, item::setMediaRights, MediaRights::new);
            setter.accept(rights, value);
        };
    }
}
