package com.apptasticsoftware.rssreader.util;

import com.apptasticsoftware.rssreader.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.apptasticsoftware.rssreader.util.Mapper.mapInteger;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapperTest {

    @ParameterizedTest
    @ValueSource(strings = {"true", "TRUE", "True", "yes", "YES", "Yes"})
    public void testMapBooleanTrue(String trueValue) {
        Item item = new Item(new DateTime());
        Mapper.mapBoolean(trueValue, item::setIsPermaLink);
        assertEquals(true, item.getIsPermaLink().orElse(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"false", "FALSE", "False", "no", "NO", "No"})
    public void testMapBooleanFalse(String falseValue) {
        Item item = new Item(new DateTime());
        Mapper.mapBoolean(falseValue, item::setIsPermaLink);
        assertEquals(false, item.getIsPermaLink().orElse(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "-1", "0", "12345", "-12345"})
    public void testMapInt(String intTextValue) {
        Image image = new Image();
        Mapper.mapInteger(intTextValue, image::setHeight);
        assertEquals(Integer.valueOf(intTextValue), image.getHeight().orElse(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"aaa", "a1", "1a"})
    public void testMapBadInt(String intTextValue) {
        Image image = new Image();
        Mapper.mapInteger(intTextValue, image::setHeight);
        assertTrue(image.getHeight().isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "-1", "0", "12345", "-12345"})
    public void testMapLong(String longTextValue) {
        Enclosure enclosure = new Enclosure();
        Mapper.mapLong(longTextValue, enclosure::setLength);
        assertEquals(Long.valueOf(longTextValue), enclosure.getLength().orElse(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"aaa", "a1", "1a"})
    public void testMapBadLong(String longTextValue) {
        Enclosure enclosure = new Enclosure();
        Mapper.mapLong(longTextValue, enclosure::setLength);
        assertTrue(enclosure.getLength().isEmpty());
    }


    @Test
    public void testCreateIfNull() {
        Channel channel = new Channel(new DateTime());
        Mapper.createIfNull(channel::getImage, channel::setImage, Image::new).setTitle("title");
        assertEquals("title", channel.getImage().map(Image::getTitle).orElse("-"));
        Mapper.createIfNull(channel::getImage, channel::setImage, Image::new).setUrl("url");
        assertEquals("url", channel.getImage().map(Image::getUrl).orElse("-"));
        assertEquals("title", channel.getImage().map(Image::getTitle).orElse("-"));
    }

    @Test
    public void testCreateIfNullOptional() {
        Channel channel = new Channel(new DateTime());
        Mapper.createIfNullOptional(channel::getImage, channel::setImage, Image::new).ifPresent(i -> mapInteger("200", i::setHeight));
        assertEquals(200, channel.getImage().flatMap(Image::getHeight).orElse(0));
        Mapper.createIfNullOptional(channel::getImage, channel::setImage, Image::new).ifPresent(i -> mapInteger("100", i::setWidth));
        assertEquals(100, channel.getImage().flatMap(Image::getWidth).orElse(0));
        assertEquals(200, channel.getImage().flatMap(Image::getHeight).orElse(0));
    }

}
