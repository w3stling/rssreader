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
public class MediaRssReader extends AbstractRssReader<Channel, MediaRssItem> {

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
    protected Channel createChannel(DateTimeParser dateTimeParser) {
        return new Channel(dateTimeParser);
    }

    @Override
    protected MediaRssItem createItem(DateTimeParser dateTimeParser) {
        return new MediaRssItem(dateTimeParser);
    }


    @Override
    protected void registerItemTags() {
        super.registerItemTags();
        // media:content
        onItemTags.put("/rss/channel/item/media:content", item -> item.addMediaContents(new MediaContent()));
        onItemTags.put("/rss/channel/item/media:content/media:rating", item -> Optional.ofNullable(item.getMediaContents().getLast()).ifPresent(c -> c.addMediaRating(new MediaRating())));
        onItemTags.put("/rss/channel/item/media:content/media:credit", item -> Optional.ofNullable(item.getMediaContents().getLast()).ifPresent(c -> c.addMediaCredit(new MediaCredit())));
        onItemTags.put("/rss/channel/item/media:content/media:hash", item -> Optional.ofNullable(item.getMediaContents().getLast()).ifPresent(c -> c.addMediaHash(new MediaHash())));
        onItemTags.put("/rss/channel/item/media:content/media:category", item -> Optional.ofNullable(item.getMediaContents().getLast()).ifPresent(c -> c.addMediaCategory(new MediaCategory())));
        onItemTags.put("/rss/channel/item/media:content/media:text", item -> Optional.ofNullable(item.getMediaContents().getLast()).ifPresent(c -> c.addMediaText(new MediaText())));
        onItemTags.put("/rss/channel/item/media:content/media:restriction", item -> Optional.ofNullable(item.getMediaContents().getLast()).ifPresent(c -> c.addMediaRestriction(new MediaRestriction())));
        onItemTags.put("/rss/channel/item/media:content/media:price", item -> Optional.ofNullable(item.getMediaContents().getLast()).ifPresent(c -> c.addMediaPrice(new MediaPrice())));
        onItemTags.put("/rss/channel/item/media:content/media:subTitle", item -> Optional.ofNullable(item.getMediaContents().getLast()).ifPresent(c -> c.addMediaSubTitle(new MediaSubTitle())));
        onItemTags.put("/rss/channel/item/media:content/media:peerLink", item -> Optional.ofNullable(item.getMediaContents().getLast()).ifPresent(c -> c.addMediaPeerLink(new MediaPeerLink())));
        onItemTags.put("/rss/channel/item/media:content/media:location", item -> Optional.ofNullable(item.getMediaContents().getLast()).ifPresent(c -> c.addMediaLocation(new MediaLocation())));
        onItemTags.put("/rss/channel/item/media:content/media:scenes/media:scene", item -> Optional.ofNullable(item.getMediaContents().getLast()).ifPresent(c -> c.addMediaScene(new MediaScene())));
        onItemTags.put("/rss/channel/item/media:content/media:thumbnail", item -> Optional.ofNullable(item.getMediaContents().getLast()).ifPresent(c -> c.addMediaThumbnail(new MediaThumbnail())));

        super.addItemExtension("/rss/channel/item/media:content/media:credit", itemMediaContentMediaCredit(MediaCredit::setCredit));
        super.addItemExtension("/rss/channel/item/media:content/media:title", itemMediaContentMediaTitle(MediaTitle::setTitle));
        super.addItemExtension("/rss/channel/item/media:content/media:description", itemMediaContentMediaDescription(MediaDescription::setDescription));
        super.addItemExtension("/rss/channel/item/media:content/media:copyright", itemMediaContentMediaCopyright(MediaCopyright::setCopyright));
        super.addItemExtension("/rss/channel/item/media:content/media:hash", itemMediaContentMediaHash(MediaHash::setHash));
        super.addItemExtension("/rss/channel/item/media:content/media:category", itemMediaContentMediaCategory(MediaCategory::setCategory));
        super.addItemExtension("/rss/channel/item/media:content/media:text", itemMediaContentMediaText(MediaText::setText));
        super.addItemExtension("/rss/channel/item/media:content/media:rating", itemMediaContentMediaRating(MediaRating::setRating));
        super.addItemExtension("/rss/channel/item/media:content/media:keywords", itemMediaContentMediaKeywords(MediaContent::addMediaKeyword));
        super.addItemExtension("/rss/channel/item/media:content/media:restriction", itemMediaContentMediaRestriction(MediaRestriction::setRestriction));
        super.addItemExtension("/rss/channel/item/media:content/media:community/media:tags", itemMediaContentMediaCommunityMediaTags(MediaTags::setTags));
        super.addItemExtension("/rss/channel/item/media:content/media:comments/media:comment", itemMediaContentStringList(MediaContent::addMediaComment));
        super.addItemExtension("/rss/channel/item/media:content/media:responses/media:response", itemMediaContentStringList(MediaContent::addMediaResponse));
        super.addItemExtension("/rss/channel/item/media:content/media:backLinks/media:backLink", itemMediaContentStringList(MediaContent::addMediaBackLink));
        super.addItemExtension("/rss/channel/item/media:content/media:embed/media:param", itemMediaContentMediaEmbed(MediaEmbed::addParamValue));
        super.addItemExtension("/rss/channel/item/media:content/media:scenes/media:scene/sceneTitle", itemMediaContentMediaScene(MediaScene::setSceneTitle));
        super.addItemExtension("/rss/channel/item/media:content/media:scenes/media:scene/sceneDescription", itemMediaContentMediaScene(MediaScene::setSceneDescription));
        super.addItemExtension("/rss/channel/item/media:content/media:scenes/media:scene/sceneStartTime", itemMediaContentMediaScene(MediaScene::setSceneStartTime));
        super.addItemExtension("/rss/channel/item/media:content/media:scenes/media:scene/sceneEndTime", itemMediaContentMediaScene(MediaScene::setSceneEndTime));

        // media:group
        onItemTags.put("/rss/channel/item/media:group/media:rating", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.addMediaRating(new MediaRating())));
        onItemTags.put("/rss/channel/item/media:group/media:content", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.addMediaContent(new MediaContent())));
        onItemTags.put("/rss/channel/item/media:group/media:thumbnail", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.addMediaThumbnail(new MediaThumbnail())));
        onItemTags.put("/rss/channel/item/media:group/media:credit", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.addMediaCredit(new MediaCredit())));
        onItemTags.put("/rss/channel/item/media:group/media:hash", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.addMediaHash(new MediaHash())));
        onItemTags.put("/rss/channel/item/media:group/media:category", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.addMediaCategory(new MediaCategory())));
        onItemTags.put("/rss/channel/item/media:group/media:restriction", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.addMediaRestriction(new MediaRestriction())));
        onItemTags.put("/rss/channel/item/media:group/media:text", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.addMediaText(new MediaText())));
        onItemTags.put("/rss/channel/item/media:group/media:price", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.addMediaPrice(new MediaPrice())));
        onItemTags.put("/rss/channel/item/media:group/media:subTitle", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.addMediaSubTitle(new MediaSubTitle())));
        onItemTags.put("/rss/channel/item/media:group/media:peerLink", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.addMediaPeerLink(new MediaPeerLink())));
        onItemTags.put("/rss/channel/item/media:group/media:location", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.addMediaLocation(new MediaLocation())));
        onItemTags.put("/rss/channel/item/media:group/media:scenes/media:scene", item -> createIfNullOptional(item::getMediaGroup, item::setMediaGroup, MediaGroup::new).ifPresent(g -> g.addMediaScene(new MediaScene())));

        super.addItemExtension("/rss/channel/item/media:group/media:title", itemMediaGroupMediaTitle(MediaTitle::setTitle));
        super.addItemExtension("/rss/channel/item/media:group/media:description", itemMediaGroupMediaDescription(MediaDescription::setDescription));
        super.addItemExtension("/rss/channel/item/media:group/media:keywords", itemMediaGroupMediaKeywords(MediaGroup::addMediaKeyword));
        super.addItemExtension("/rss/channel/item/media:group/media:credit", itemMediaGroupMediaCredit(MediaCredit::setCredit));
        super.addItemExtension("/rss/channel/item/media:group/media:category", itemMediaGroupMediaCategory(MediaCategory::setCategory));
        super.addItemExtension("/rss/channel/item/media:group/media:rating", itemMediaGroupMediaRating(MediaRating::setRating));
        super.addItemExtension("/rss/channel/item/media:group/media:community/media:tags", itemMediaGroupMediaCommunityMediaTags(MediaTags::setTags));
        super.addItemExtension("/rss/channel/item/media:group/media:copyright", itemMediaGroupMediaCopyright(MediaCopyright::setCopyright));
        super.addItemExtension("/rss/channel/item/media:group/media:hash", itemMediaGroupMediaHash(MediaHash::setHash));
        super.addItemExtension("/rss/channel/item/media:group/media:restriction", itemMediaGroupMediaRestriction(MediaRestriction::setRestriction));
        super.addItemExtension("/rss/channel/item/media:group/media:text", itemMediaGroupMediaText(MediaText::setText));
        super.addItemExtension("/rss/channel/item/media:group/media:comments/media:comment", itemMediaGroupStringList(MediaGroup::addMediaComment));
        super.addItemExtension("/rss/channel/item/media:group/media:responses/media:response", itemMediaGroupStringList(MediaGroup::addMediaResponse));
        super.addItemExtension("/rss/channel/item/media:group/media:backLinks/media:backLink", itemMediaGroupStringList(MediaGroup::addMediaBackLink));
        super.addItemExtension("/rss/channel/item/media:group/media:embed/media:param", itemMediaGroupMediaEmbed(MediaEmbed::addParamValue));
        super.addItemExtension("/rss/channel/item/media:group/media:scenes/media:scene/sceneTitle", itemMediaGroupMediaScene(MediaScene::setSceneTitle));
        super.addItemExtension("/rss/channel/item/media:group/media:scenes/media:scene/sceneDescription", itemMediaGroupMediaScene(MediaScene::setSceneDescription));
        super.addItemExtension("/rss/channel/item/media:group/media:scenes/media:scene/sceneStartTime", itemMediaGroupMediaScene(MediaScene::setSceneStartTime));
        super.addItemExtension("/rss/channel/item/media:group/media:scenes/media:scene/sceneEndTime", itemMediaGroupMediaScene(MediaScene::setSceneEndTime));

        // item
        onItemTags.put("/rss/channel/item/media:rating", item -> item.addMediaRating(new MediaRating()));
        onItemTags.put("/rss/channel/item/media:subTitle", item -> item.addMediaSubTitle(new MediaSubTitle()));
        onItemTags.put("/rss/channel/item/media:peerLink", item -> item.addMediaPeerLink(new MediaPeerLink()));
        onItemTags.put("/rss/channel/item/media:restriction", item -> item.addMediaRestriction(new MediaRestriction()));
        onItemTags.put("/rss/channel/item/media:location", item -> item.addMediaLocation(new MediaLocation()));
        onItemTags.put("/rss/channel/item/media:price", item -> item.addMediaPrice(new MediaPrice()));
        onItemTags.put("/rss/channel/item/media:scenes/media:scene", item -> item.addMediaScene(new MediaScene()));
        onItemTags.put("/rss/channel/item/media:hash", item -> item.addMediaHash(new MediaHash()));
        onItemTags.put("/rss/channel/item/media:text", item -> item.addMediaText(new MediaText()));
        onItemTags.put("/rss/channel/item/media:thumbnail", item -> item.addMediaThumbnail(new MediaThumbnail()));
        onItemTags.put("/rss/channel/item/media:category", item -> item.addMediaCategory(new MediaCategory()));
        onItemTags.put("/rss/channel/item/media:credit", item -> item.addMediaCredit(new MediaCredit()));
        onItemTags.put("/rss/channel/item/media:license", item -> item.addMediaLicense(new MediaLicense()));

        // media:community
        super.addItemExtension("/rss/channel/item/media:community/media:tags", itemMediaCommunityMediaTags(MediaTags::setTags));

        // media:comment
        super.addItemExtension("/rss/channel/item/media:comments/media:comment", MediaRssItem::addMediaComment);

        // media:response
        super.addItemExtension("/rss/channel/item/media:responses/media:response", MediaRssItem::addMediaResponse);

        // media:backLink
        super.addItemExtension("/rss/channel/item/media:backLinks/media:backLink", MediaRssItem::addMediaBackLink);

        // media:license
        super.addItemExtension("/rss/channel/item/media:license",  itemMediaLicense(MediaLicense::setLicense));

        // media:restriction
        super.addItemExtension("/rss/channel/item/media:restriction", itemMediaRestriction(MediaRestriction::setRestriction));

        // media:scene
        super.addItemExtension("/rss/channel/item/media:scenes/media:scene/sceneTitle", itemMediaScene(MediaScene::setSceneTitle));
        super.addItemExtension("/rss/channel/item/media:scenes/media:scene/sceneDescription", itemMediaScene(MediaScene::setSceneDescription));
        super.addItemExtension("/rss/channel/item/media:scenes/media:scene/sceneStartTime", itemMediaScene(MediaScene::setSceneStartTime));
        super.addItemExtension("/rss/channel/item/media:scenes/media:scene/sceneEndTime", itemMediaScene(MediaScene::setSceneEndTime));

        // media:embed
        super.addItemExtension("/rss/channel/item/media:embed/media:param", itemMediaEmbed(MediaEmbed::addParamValue));

        // media:title
        super.addItemExtension("/rss/channel/item/media:title", itemMediaTitle(MediaTitle::setTitle));

        // media:description
        super.addItemExtension("/rss/channel/item/media:description", itemMediaDescription(MediaDescription::setDescription));

        // media:copyright
        super.addItemExtension("/rss/channel/item/media:copyright", itemMediaCopyright(MediaCopyright::setCopyright));

        // media:hash
        super.addItemExtension("/rss/channel/item/media:hash", itemMediaHash(MediaHash::setHash));

        // media:text
        super.addItemExtension("/rss/channel/item/media:text", itemMediaText(MediaText::setText));

        // media:keywords
        super.addItemExtension("/rss/channel/item/media:keywords", itemMediaKeywords(MediaRssItem::addMediaKeyword));

        // media:rating
        super.addItemExtension("/rss/channel/item/media:rating", itemMediaRating(MediaRating::setRating));

        // media:category
        super.addItemExtension("/rss/channel/item/media:category", itemMediaCategory(MediaCategory::setCategory));

        // media:credit
        super.addItemExtension("/rss/channel/item/media:credit", itemMediaCredit(MediaCredit::setCredit));
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

        super.addItemExtension("/rss/channel/item/media:content/media:thumbnail", "url", itemMediaContentMediaThumbnail(MediaThumbnail::setUrl));
        super.addItemExtension("/rss/channel/item/media:content/media:thumbnail", "height", itemMediaContentMediaThumbnail((thumbnail, value) -> mapInteger(value, thumbnail::setHeight)));
        super.addItemExtension("/rss/channel/item/media:content/media:thumbnail", "width", itemMediaContentMediaThumbnail((thumbnail, value) -> mapInteger(value, thumbnail::setWidth)));
        super.addItemExtension("/rss/channel/item/media:content/media:thumbnail", "time", itemMediaContentMediaThumbnail(MediaThumbnail::setTime));
        super.addItemExtension("/rss/channel/item/media:content/media:player", "url", itemMediaContentMediaPlayer(MediaPlayer::setUrl));
        super.addItemExtension("/rss/channel/item/media:content/media:player", "height", itemMediaContentMediaPlayer((player, value) -> mapInteger(value, player::setHeight)));
        super.addItemExtension("/rss/channel/item/media:content/media:player", "width", itemMediaContentMediaPlayer((player, value) -> mapInteger(value, player::setWidth)));
        super.addItemExtension("/rss/channel/item/media:content/media:credit", "role", itemMediaContentMediaCredit(MediaCredit::setRole));
        super.addItemExtension("/rss/channel/item/media:content/media:credit", "scheme", itemMediaContentMediaCredit(MediaCredit::setScheme));
        super.addItemExtension("/rss/channel/item/media:content/media:title", "type", itemMediaContentMediaTitle(MediaTitle::setType));
        super.addItemExtension("/rss/channel/item/media:content/media:description", "type", itemMediaContentMediaDescription(MediaDescription::setType));
        super.addItemExtension("/rss/channel/item/media:content/media:copyright", "url", itemMediaContentMediaCopyright(MediaCopyright::setUrl));
        super.addItemExtension("/rss/channel/item/media:content/media:hash", "algo", itemMediaContentMediaHash(MediaHash::setAlgorithm));
        super.addItemExtension("/rss/channel/item/media:content/media:category", "scheme", itemMediaContentMediaCategory(MediaCategory::setScheme));
        super.addItemExtension("/rss/channel/item/media:content/media:category", "label", itemMediaContentMediaCategory(MediaCategory::setLabel));
        super.addItemExtension("/rss/channel/item/media:content/media:text", "type", itemMediaContentMediaText(MediaText::setType));
        super.addItemExtension("/rss/channel/item/media:content/media:text", "lang", itemMediaContentMediaText(MediaText::setLang));
        super.addItemExtension("/rss/channel/item/media:content/media:text", "start", itemMediaContentMediaText(MediaText::setStart));
        super.addItemExtension("/rss/channel/item/media:content/media:text", "end", itemMediaContentMediaText(MediaText::setEnd));
        super.addItemExtension("/rss/channel/item/media:content/media:rating", "scheme", itemMediaContentMediaRating(MediaRating::setScheme));
        super.addItemExtension("/rss/channel/item/media:content/media:restriction", "type", itemMediaContentMediaRestriction(MediaRestriction::setType));
        super.addItemExtension("/rss/channel/item/media:content/media:restriction", "relationship", itemMediaContentMediaRestriction(MediaRestriction::setRelationship));
        super.addItemExtension("/rss/channel/item/media:content/media:community/media:starRating", "average", itemMediaContentMediaCommunityMediaStarRating((starRating, value) -> mapDouble(value, starRating::setAverage)));
        super.addItemExtension("/rss/channel/item/media:content/media:community/media:starRating", "count", itemMediaContentMediaCommunityMediaStarRating((starRating, value) -> mapInteger(value, starRating::setCount)));
        super.addItemExtension("/rss/channel/item/media:content/media:community/media:starRating", "max", itemMediaContentMediaCommunityMediaStarRating((starRating, value) -> mapInteger(value, starRating::setMax)));
        super.addItemExtension("/rss/channel/item/media:content/media:community/media:starRating", "min", itemMediaContentMediaCommunityMediaStarRating((starRating, value) -> mapInteger(value, starRating::setMin)));
        super.addItemExtension("/rss/channel/item/media:content/media:community/media:statistics", "views", itemMediaContentMediaCommunityMediaStatistics((statistics, value) -> mapLong(value, statistics::setViews)));
        super.addItemExtension("/rss/channel/item/media:content/media:community/media:statistics", "favorites", itemMediaContentMediaCommunityMediaStatistics((statistics, value) -> mapInteger(value, statistics::setFavorites)));
        super.addItemExtension("/rss/channel/item/media:content/media:status", "reason",  itemMediaContentMediaStatus(MediaStatus::setReason));
        super.addItemExtension("/rss/channel/item/media:content/media:status", "state",  itemMediaContentMediaStatus(MediaStatus::setState));
        super.addItemExtension("/rss/channel/item/media:content/media:price", "type",  itemMediaContentMediaPrice(MediaPrice::setType));
        super.addItemExtension("/rss/channel/item/media:content/media:price", "price",  itemMediaContentMediaPrice((price, value) -> mapDouble(value, price::setPrice)));
        super.addItemExtension("/rss/channel/item/media:content/media:price", "currency",  itemMediaContentMediaPrice(MediaPrice::setCurrency));
        super.addItemExtension("/rss/channel/item/media:content/media:price", "info",  itemMediaContentMediaPrice(MediaPrice::setInfo));
        super.addItemExtension("/rss/channel/item/media:content/media:embed", "url", itemMediaContentMediaEmbed(MediaEmbed::setUrl));
        super.addItemExtension("/rss/channel/item/media:content/media:embed", "width", itemMediaContentMediaEmbed((item, value) -> mapInteger(value, item::setWidth)));
        super.addItemExtension("/rss/channel/item/media:content/media:embed", "height", itemMediaContentMediaEmbed((item, value) -> mapInteger(value, item::setHeight)));
        super.addItemExtension("/rss/channel/item/media:content/media:embed/media:param", "name", itemMediaContentMediaEmbed(MediaEmbed::addParamName));
        super.addItemExtension("/rss/channel/item/media:content/media:subTitle", "type",  itemMediaContentMediaSubTitle(MediaSubTitle::setType));
        super.addItemExtension("/rss/channel/item/media:content/media:subTitle", "lang",  itemMediaContentMediaSubTitle(MediaSubTitle::setLang));
        super.addItemExtension("/rss/channel/item/media:content/media:subTitle", "href",  itemMediaContentMediaSubTitle(MediaSubTitle::setHref));
        super.addItemExtension("/rss/channel/item/media:content/media:peerLink", "type",  itemMediaContentMediaPeerLink(MediaPeerLink::setType));
        super.addItemExtension("/rss/channel/item/media:content/media:peerLink", "href",  itemMediaContentMediaPeerLink(MediaPeerLink::setHref));
        super.addItemExtension("/rss/channel/item/media:content/media:location", "description", itemMediaContentMediaLocation(MediaLocation::setDescription));
        super.addItemExtension("/rss/channel/item/media:content/media:location", "start", itemMediaContentMediaLocation(MediaLocation::setStart));
        super.addItemExtension("/rss/channel/item/media:content/media:location", "end", itemMediaContentMediaLocation(MediaLocation::setEnd));


        // media:group
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
        super.addItemExtension("/rss/channel/item/media:group/media:title", "type", itemMediaGroupMediaTitle(MediaTitle::setType));
        super.addItemExtension("/rss/channel/item/media:group/media:description", "type", itemMediaGroupMediaDescription(MediaDescription::setType));
        super.addItemExtension("/rss/channel/item/media:group/media:thumbnail", "url", itemMediaGroupMediaThumbnail(MediaThumbnail::setUrl));
        super.addItemExtension("/rss/channel/item/media:group/media:thumbnail", "height", itemMediaGroupMediaThumbnail((thumbnail, value) -> mapInteger(value, thumbnail::setHeight)));
        super.addItemExtension("/rss/channel/item/media:group/media:thumbnail", "width", itemMediaGroupMediaThumbnail((thumbnail, value) -> mapInteger(value, thumbnail::setWidth)));
        super.addItemExtension("/rss/channel/item/media:group/media:thumbnail", "time", itemMediaGroupMediaThumbnail(MediaThumbnail::setTime));
        super.addItemExtension("/rss/channel/item/media:group/media:community/media:starRating", "average", itemMediaGroupMediaCommunityMediaStarRating((starRating, value) -> mapDouble(value, starRating::setAverage)));
        super.addItemExtension("/rss/channel/item/media:group/media:community/media:starRating", "count", itemMediaGroupMediaCommunityMediaStarRating((starRating, value) -> mapInteger(value, starRating::setCount)));
        super.addItemExtension("/rss/channel/item/media:group/media:community/media:starRating", "max", itemMediaGroupMediaCommunityMediaStarRating((starRating, value) -> mapInteger(value, starRating::setMax)));
        super.addItemExtension("/rss/channel/item/media:group/media:community/media:starRating", "min", itemMediaGroupMediaCommunityMediaStarRating((starRating, value) -> mapInteger(value, starRating::setMin)));
        super.addItemExtension("/rss/channel/item/media:group/media:community/media:statistics", "views", itemMediaGroupMediaCommunityMediaStatistics((statistics, value) -> mapLong(value, statistics::setViews)));
        super.addItemExtension("/rss/channel/item/media:group/media:community/media:statistics", "favorites", itemMediaGroupMediaCommunityMediaStatistics((statistics, value) -> mapInteger(value, statistics::setFavorites)));
        super.addItemExtension("/rss/channel/item/media:group/media:status", "reason",  itemMediaGroupMediaStatus(MediaStatus::setReason));
        super.addItemExtension("/rss/channel/item/media:group/media:status", "state",  itemMediaGroupMediaStatus(MediaStatus::setState));
        super.addItemExtension("/rss/channel/item/media:group/media:credit", "role", itemMediaGroupMediaCredit(MediaCredit::setRole));
        super.addItemExtension("/rss/channel/item/media:group/media:credit", "scheme", itemMediaGroupMediaCredit(MediaCredit::setScheme));
        super.addItemExtension("/rss/channel/item/media:group/media:category", "scheme", itemMediaGroupMediaCategory(MediaCategory::setScheme));
        super.addItemExtension("/rss/channel/item/media:group/media:category", "label", itemMediaGroupMediaCategory(MediaCategory::setLabel));
        super.addItemExtension("/rss/channel/item/media:group/media:rating", "scheme", itemMediaGroupMediaRating(MediaRating::setScheme));
        super.addItemExtension("/rss/channel/item/media:group/media:copyright", "url", itemMediaGroupMediaCopyright(MediaCopyright::setUrl));
        super.addItemExtension("/rss/channel/item/media:group/media:hash", "algo", itemMediaGroupMediaHash(MediaHash::setAlgorithm));
        super.addItemExtension("/rss/channel/item/media:group/media:restriction", "type", itemMediaGroupMediaRestriction(MediaRestriction::setType));
        super.addItemExtension("/rss/channel/item/media:group/media:restriction", "relationship", itemMediaGroupMediaRestriction(MediaRestriction::setRelationship));
        super.addItemExtension("/rss/channel/item/media:group/media:text", "type", itemMediaGroupMediaText(MediaText::setType));
        super.addItemExtension("/rss/channel/item/media:group/media:text", "lang", itemMediaGroupMediaText(MediaText::setLang));
        super.addItemExtension("/rss/channel/item/media:group/media:text", "start", itemMediaGroupMediaText(MediaText::setStart));
        super.addItemExtension("/rss/channel/item/media:group/media:text", "end", itemMediaGroupMediaText(MediaText::setEnd));
        super.addItemExtension("/rss/channel/item/media:group/media:price", "type",  itemMediaGroupMediaPrice(MediaPrice::setType));
        super.addItemExtension("/rss/channel/item/media:group/media:price", "price",  itemMediaGroupMediaPrice((price, value) -> mapDouble(value, price::setPrice)));
        super.addItemExtension("/rss/channel/item/media:group/media:price", "currency",  itemMediaGroupMediaPrice(MediaPrice::setCurrency));
        super.addItemExtension("/rss/channel/item/media:group/media:price", "info",  itemMediaGroupMediaPrice(MediaPrice::setInfo));
        super.addItemExtension("/rss/channel/item/media:group/media:embed", "url", itemMediaGroupMediaEmbed(MediaEmbed::setUrl));
        super.addItemExtension("/rss/channel/item/media:group/media:embed", "width", itemMediaGroupMediaEmbed((item, value) -> mapInteger(value, item::setWidth)));
        super.addItemExtension("/rss/channel/item/media:group/media:embed", "height", itemMediaGroupMediaEmbed((item, value) -> mapInteger(value, item::setHeight)));
        super.addItemExtension("/rss/channel/item/media:group/media:embed/media:param", "name", itemMediaGroupMediaEmbed(MediaEmbed::addParamName));
        super.addItemExtension("/rss/channel/item/media:group/media:subTitle", "type",  itemMediaGroupMediaSubTitle(MediaSubTitle::setType));
        super.addItemExtension("/rss/channel/item/media:group/media:subTitle", "lang",  itemMediaGroupMediaSubTitle(MediaSubTitle::setLang));
        super.addItemExtension("/rss/channel/item/media:group/media:subTitle", "href",  itemMediaGroupMediaSubTitle(MediaSubTitle::setHref));
        super.addItemExtension("/rss/channel/item/media:group/media:peerLink", "type",  itemMediaGroupMediaPeerLink(MediaPeerLink::setType));
        super.addItemExtension("/rss/channel/item/media:group/media:peerLink", "href",  itemMediaGroupMediaPeerLink(MediaPeerLink::setHref));
        super.addItemExtension("/rss/channel/item/media:group/media:location", "description", itemMediaGroupMediaLocation(MediaLocation::setDescription));
        super.addItemExtension("/rss/channel/item/media:group/media:location", "start", itemMediaGroupMediaLocation(MediaLocation::setStart));
        super.addItemExtension("/rss/channel/item/media:group/media:location", "end", itemMediaGroupMediaLocation(MediaLocation::setEnd));

        // media:community
        super.addItemExtension("/rss/channel/item/media:community/media:starRating", "average", itemMediaCommunityMediaStarRating((starRating, value) -> mapDouble(value, starRating::setAverage)));
        super.addItemExtension("/rss/channel/item/media:community/media:starRating", "count", itemMediaCommunityMediaStarRating((starRating, value) -> mapInteger(value, starRating::setCount)));
        super.addItemExtension("/rss/channel/item/media:community/media:starRating", "max", itemMediaCommunityMediaStarRating((starRating, value) -> mapInteger(value, starRating::setMax)));
        super.addItemExtension("/rss/channel/item/media:community/media:starRating", "min", itemMediaCommunityMediaStarRating((starRating, value) -> mapInteger(value, starRating::setMin)));
        super.addItemExtension("/rss/channel/item/media:community/media:statistics", "views", itemMediaCommunityMediaStatistics((statistics, value) -> mapLong(value, statistics::setViews)));
        super.addItemExtension("/rss/channel/item/media:community/media:statistics", "favorites", itemMediaCommunityMediaStatistics((statistics, value) -> mapInteger(value, statistics::setFavorites)));

        // media:status
        super.addItemExtension("/rss/channel/item/media:status", "reason",  itemMediaStatus(MediaStatus::setReason));
        super.addItemExtension("/rss/channel/item/media:status", "state",  itemMediaStatus(MediaStatus::setState));

        // media:price
        super.addItemExtension("/rss/channel/item/media:price", "type",  itemMediaPrice(MediaPrice::setType));
        super.addItemExtension("/rss/channel/item/media:price", "price",  itemMediaPrice((price, value) -> mapDouble(value, price::setPrice)));
        super.addItemExtension("/rss/channel/item/media:price", "currency",  itemMediaPrice(MediaPrice::setCurrency));
        super.addItemExtension("/rss/channel/item/media:price", "info",  itemMediaPrice(MediaPrice::setInfo));

        // media:license
        super.addItemExtension("/rss/channel/item/media:license", "type",  itemMediaLicense(MediaLicense::setType));
        super.addItemExtension("/rss/channel/item/media:license", "href",  itemMediaLicense(MediaLicense::setHref));

        // media:subTitle
        super.addItemExtension("/rss/channel/item/media:subTitle", "type",  itemMediaSubTitle(MediaSubTitle::setType));
        super.addItemExtension("/rss/channel/item/media:subTitle", "lang",  itemMediaSubTitle(MediaSubTitle::setLang));
        super.addItemExtension("/rss/channel/item/media:subTitle", "href",  itemMediaSubTitle(MediaSubTitle::setHref));

        // media:peerLink
        super.addItemExtension("/rss/channel/item/media:peerLink", "type",  itemMediaPeerLink(MediaPeerLink::setType));
        super.addItemExtension("/rss/channel/item/media:peerLink", "href",  itemMediaPeerLink(MediaPeerLink::setHref));

        // media:restriction
        super.addItemExtension("/rss/channel/item/media:restriction", "type", itemMediaRestriction(MediaRestriction::setType));
        super.addItemExtension("/rss/channel/item/media:restriction", "relationship", itemMediaRestriction(MediaRestriction::setRelationship));

        // media:location
        super.addItemExtension("/rss/channel/item/media:location", "description", itemMediaLocation(MediaLocation::setDescription));
        super.addItemExtension("/rss/channel/item/media:location", "start", itemMediaLocation(MediaLocation::setStart));
        super.addItemExtension("/rss/channel/item/media:location", "end", itemMediaLocation(MediaLocation::setEnd));

        // media:embed
        super.addItemExtension("/rss/channel/item/media:embed", "url", itemMediaEmbed(MediaEmbed::setUrl));
        super.addItemExtension("/rss/channel/item/media:embed", "width", itemMediaEmbed((item, value) -> mapInteger(value, item::setWidth)));
        super.addItemExtension("/rss/channel/item/media:embed", "height", itemMediaEmbed((item, value) -> mapInteger(value, item::setHeight)));
        super.addItemExtension("/rss/channel/item/media:embed/media:param", "name", itemMediaEmbed(MediaEmbed::addParamName));

        // media:title
        super.addItemExtension("/rss/channel/item/media:title", "type", itemMediaTitle(MediaTitle::setType));

        // media:description
        super.addItemExtension("/rss/channel/item/media:description", "type", itemMediaDescription(MediaDescription::setType));

        // media:copyright
        super.addItemExtension("/rss/channel/item/media:copyright", "url", itemMediaCopyright(MediaCopyright::setUrl));

        // media:hash
        super.addItemExtension("/rss/channel/item/media:hash", "algo", itemMediaHash(MediaHash::setAlgorithm));

        // media:player
        super.addItemExtension("/rss/channel/item/media:player", "url", itemMediaPlayer(MediaPlayer::setUrl));
        super.addItemExtension("/rss/channel/item/media:player", "height", itemMediaPlayer((player, value) -> mapInteger(value, player::setHeight)));
        super.addItemExtension("/rss/channel/item/media:player", "width", itemMediaPlayer((player, value) -> mapInteger(value, player::setWidth)));

        // media:text
        super.addItemExtension("/rss/channel/item/media:text", "type", itemMediaText(MediaText::setType));
        super.addItemExtension("/rss/channel/item/media:text", "lang", itemMediaText(MediaText::setLang));
        super.addItemExtension("/rss/channel/item/media:text", "start", itemMediaText(MediaText::setStart));
        super.addItemExtension("/rss/channel/item/media:text", "end", itemMediaText(MediaText::setEnd));

        // media:thumbnail
        super.addItemExtension("/rss/channel/item/media:thumbnail", "url", itemMediaThumbnail(MediaThumbnail::setUrl));
        super.addItemExtension("/rss/channel/item/media:thumbnail", "height", itemMediaThumbnail((thumbnail, value) -> mapInteger(value, thumbnail::setHeight)));
        super.addItemExtension("/rss/channel/item/media:thumbnail", "width", itemMediaThumbnail((thumbnail, value) -> mapInteger(value, thumbnail::setWidth)));
        super.addItemExtension("/rss/channel/item/media:thumbnail", "time", itemMediaThumbnail(MediaThumbnail::setTime));

        // media:rating
        super.addItemExtension("/rss/channel/item/media:rating", "scheme", itemMediaRating(MediaRating::setScheme));

        // media:category
        super.addItemExtension("/rss/channel/item/media:category", "scheme", itemMediaCategory(MediaCategory::setScheme));
        super.addItemExtension("/rss/channel/item/media:category", "label", itemMediaCategory(MediaCategory::setLabel));

        // media:credit
        super.addItemExtension("/rss/channel/item/media:credit", "role", itemMediaCredit(MediaCredit::setRole));
        super.addItemExtension("/rss/channel/item/media:credit", "scheme", itemMediaCredit(MediaCredit::setScheme));

        // media:rights
        super.addItemExtension("/rss/channel/item/media:rights", "status", itemMediaRights(MediaRights::setStatus));
    }

    private static BiConsumer<MediaRssItem, String> itemMediaContent(BiConsumer<MediaContent, String> setter) {
        return (item, value) -> Optional.ofNullable(item.getMediaContents().getLast())
                .ifPresent(content -> setter.accept(content, value));
    }

    private static BiConsumer<MediaRssItem, String> itemMediaContentMediaThumbnail(BiConsumer<MediaThumbnail, String> setter) {
        return (item, value) -> {
            var content = item.getMediaContents().getLast();
            Optional.ofNullable(content.getMediaThumbnails().getLast()).ifPresent(thumbnail -> setter.accept(thumbnail, value));
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
            Optional.ofNullable(content.getMediaCredits().getLast()).ifPresent(credit -> setter.accept(credit, value));
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
            Optional.ofNullable(tags).ifPresent(tag -> setter.accept(tags, value));
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
            var tags = createIfNull(content::getMediaEmbed, content::setMediaEmbed, MediaEmbed::new);
            Optional.ofNullable(tags).ifPresent(tag -> setter.accept(tags, value));
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
            Optional.ofNullable(group.getMediaRatings().getLast())
                    .ifPresent(rating -> setter.accept(rating, value));

        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaGroupMediaThumbnail(BiConsumer<MediaThumbnail, String> setter) {
        return (item, value) -> {
            var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
            Optional.ofNullable(group.getMediaThumbnails().getLast())
                    .ifPresent(thumbnail -> setter.accept(thumbnail, value));
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
            Optional.ofNullable(tags).ifPresent(tag -> setter.accept(tags, value));
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
            setter.accept(group.getMediaPrices().getLast(), value);
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaGroupMediaEmbed(BiConsumer<MediaEmbed, String> setter) {
        return (item, value) -> {
            var group = createIfNull(item::getMediaGroup, item::setMediaGroup, MediaGroup::new);
            var tags = createIfNull(group::getMediaEmbed, group::setMediaEmbed, MediaEmbed::new);
            Optional.ofNullable(tags).ifPresent(tag -> setter.accept(tag, value));
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
            Optional.ofNullable(tags).ifPresent(tag -> setter.accept(tags, value));
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
            var tags = createIfNull(item::getMediaEmbed, item::setMediaEmbed, MediaEmbed::new);
            Optional.ofNullable(tags).ifPresent(tag -> setter.accept(tags, value));
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaTitle(BiConsumer<MediaTitle, String> setter) {
        return (item, value) -> {
            var tags = createIfNull(item::getMediaTitle, item::setMediaTitle, MediaTitle::new);
            Optional.ofNullable(tags).ifPresent(tag -> setter.accept(tags, value));
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaDescription(BiConsumer<MediaDescription, String> setter) {
        return (item, value) -> {
            var tags = createIfNull(item::getMediaDescription, item::setMediaDescription, MediaDescription::new);
            Optional.ofNullable(tags).ifPresent(tag -> setter.accept(tags, value));
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaCopyright(BiConsumer<MediaCopyright, String> setter) {
        return (item, value) -> {
            var tags = createIfNull(item::getMediaCopyright, item::setMediaCopyright, MediaCopyright::new);
            Optional.ofNullable(tags).ifPresent(tag -> setter.accept(tags, value));
        };
    }

    private static BiConsumer<MediaRssItem, String> itemMediaHash(BiConsumer<MediaHash, String> setter) {
        return (item, value) -> setter.accept(item.getMediaHashes().getLast(), value);
    }

    private static BiConsumer<MediaRssItem, String> itemMediaPlayer(BiConsumer<MediaPlayer, String> setter) {
        return (item, value) -> {
            var tags = createIfNull(item::getMediaPlayer, item::setMediaPlayer, MediaPlayer::new);
            Optional.ofNullable(tags).ifPresent(tag -> setter.accept(tags, value));
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

    /*
    private static BiConsumer<MediaRssItem, String> itemMediaRating(BiConsumer<MediaRating, String> setter) {
        return (item, value) -> {
            var tags = createIfNull(item::getMediaRating, item::setMediaRating, MediaRating::new);
            Optional.ofNullable(tags).ifPresent(tag -> setter.accept(tags, value));
        };
    }
    */
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
            var tags = createIfNull(item::getMediaRights, item::setMediaRights, MediaRights::new);
            Optional.ofNullable(tags).ifPresent(tag -> setter.accept(tags, value));
        };
    }
}
