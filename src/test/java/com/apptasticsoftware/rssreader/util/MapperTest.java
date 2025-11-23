package com.apptasticsoftware.rssreader.util;

import com.apptasticsoftware.rssreader.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import static com.apptasticsoftware.rssreader.util.Mapper.mapInteger;
import static org.junit.jupiter.api.Assertions.*;

class MapperTest {

    @ParameterizedTest
    @ValueSource(strings = {"true", "TRUE", "True", "yes", "YES", "Yes"})
    void testMapBooleanTrue(String trueValue) {
        ItemImpl item = new ItemImpl(new DateTime());
        Mapper.mapBoolean(trueValue, item::setIsPermaLink);
        assertEquals(true, item.getIsPermaLink().orElse(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"false", "FALSE", "False", "no", "NO", "No"})
    void testMapBooleanFalse(String falseValue) {
        ItemImpl item = new ItemImpl(new DateTime());
        Mapper.mapBoolean(falseValue, item::setIsPermaLink);
        assertEquals(false, item.getIsPermaLink().orElse(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Bad value", ""})
    void testMapBooleanBadValue(String falseValue) {
        ItemImpl item = new ItemImpl(new DateTime());
        Mapper.mapBoolean(falseValue, item::setIsPermaLink);
        assertNull(item.getIsPermaLink().orElse(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "-1", "0", "12345", "-12345"})
    void testMapInt(String intTextValue) {
        Image image = new Image();
        Mapper.mapInteger(intTextValue, image::setHeight);
        assertEquals(Integer.valueOf(intTextValue), image.getHeight().orElse(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"aaa", "a1", "1a"})
    void testMapBadInt(String intTextValue) {
        Image image = new Image();
        Mapper.mapInteger(intTextValue, image::setHeight);
        assertTrue(image.getHeight().isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "-1", "0", "12345", "-12345"})
    void testMapLong(String longTextValue) {
        Enclosure enclosure = new Enclosure();
        Mapper.mapLong(longTextValue, enclosure::setLength);
        assertEquals(Long.valueOf(longTextValue), enclosure.getLength().orElse(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"aaa", "a1", "1a"})
    void testMapBadLong(String longTextValue) {
        Enclosure enclosure = new Enclosure();
        Mapper.mapLong(longTextValue, enclosure::setLength);
        assertTrue(enclosure.getLength().isEmpty());
    }


    @Test
    void testCreateIfNull() {
        ChannelImpl channel = new ChannelImpl(new DateTime());
        Mapper.createIfNull(channel::getImage, channel::setImage, Image::new).setTitle("title");
        assertEquals("title", channel.getImage().map(Image::getTitle).orElse("-"));
        Mapper.createIfNull(channel::getImage, channel::setImage, Image::new).setUrl("url");
        assertEquals("url", channel.getImage().map(Image::getUrl).orElse("-"));
        assertEquals("title", channel.getImage().map(Image::getTitle).orElse("-"));
    }

    @Test
    void testCreateIfNullOptional() {
        ChannelImpl channel = new ChannelImpl(new DateTime());
        Mapper.createIfNullOptional(channel::getImage, channel::setImage, Image::new).ifPresent(i -> mapInteger("200", i::setHeight));
        assertEquals(200, channel.getImage().flatMap(Image::getHeight).orElse(0));
        Mapper.createIfNullOptional(channel::getImage, channel::setImage, Image::new).ifPresent(i -> mapInteger("100", i::setWidth));
        assertEquals(100, channel.getImage().flatMap(Image::getWidth).orElse(0));
        assertEquals(200, channel.getImage().flatMap(Image::getHeight).orElse(0));
    }

    @Test
    void testBadNumberLogging() {
        var logger = Logger.getLogger("com.apptasticsoftware.rssreader.util");
        logger.setLevel(Level.ALL);

        var image = new Image();
        Mapper.mapInteger("-", image::setHeight);
        assertEquals(Optional.empty(), image.getHeight());

        logger.setLevel(Level.OFF);
        Mapper.mapInteger("-", image::setHeight);
        assertEquals(Optional.empty(), image.getHeight());

        Mapper.mapInteger("", image::setHeight);
        assertEquals(Optional.empty(), image.getHeight());

        Mapper.mapInteger(null, image::setHeight);
        assertEquals(Optional.empty(), image.getHeight());
    }

    @ParameterizedTest
    @MethodSource("mapIfEmptyParameters")
    void testMapIfEmpty(TestObject testObject, String value, String expected) {
        Mapper.mapIfEmpty(value, testObject::getText, testObject::setText);
        assertEquals(expected, testObject.getText());
    }

    @ParameterizedTest
    @MethodSource("mapIfEmptyParameters")
    void testOptionalMapIfEmpty(TestObject testObject, String value, String expected) {
        Mapper.mapIfEmpty(value, testObject::getOptionalText, testObject::setText);
        assertEquals(expected, testObject.getText());
    }

    private static Stream<Arguments> mapIfEmptyParameters() {
        return Stream.of(
                Arguments.of(new TestObject(null), "value", "value"),
                Arguments.of(new TestObject(""), "value", "value"),
                Arguments.of(new TestObject(null), "", null),
                Arguments.of(new TestObject(null), null, null),
                Arguments.of(new TestObject(""), "", ""),
                Arguments.of(new TestObject(""), null, ""),
                Arguments.of(new TestObject("value"), "other value", "value")
        );
    }


    static class TestObject {
        private String text;

        public TestObject(String value) {
            text = value;
        }

        public void setText(String value) {
            text = value;
        }

        public String getText() {
            return text;
        }

        public Optional<String> getOptionalText() {
            return Optional.ofNullable(text);
        }

    }

}
