package com.apptasticsoftware.integrationtest;

import com.apptasticsoftware.rssreader.RssReader;
import com.apptasticsoftware.rssreader.filter.InvalidXmlCharacterFilter;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FeedFilterTest {

    @Test
    void testInvalidXmlCharacter0x6() {
        var fileInputSteam = fromFile("rss-invalid-xml-character-0x6.xml");
        var list = new RssReader()
                .addFeedFilter(new InvalidXmlCharacterFilter())
                .read(fileInputSteam)
                .collect(Collectors.toList());
        assertEquals(25, list.size());
    }

    @Test
    void testInvalidXmlEscapeString() {
        var fileInputSteam = fromFile("rss-invalid-xml-escape-string.xml");
        var list = new RssReader()
                .addFeedFilter(new InvalidXmlCharacterFilter())
                .read(fileInputSteam)
                .collect(Collectors.toList());

        assertEquals(10, list.size());
        assertEquals("Myrskylän kunta", list.get(0).getChannel().getTitle());
    }

    @Test
    void testInvalidXmlEscapeStringWithinCdata() {
        var fileInputSteam = fromFile("rss-invalid-xml-escape-string-within-cdata.xml");
        var list = new RssReader()
                .addFeedFilter(new InvalidXmlCharacterFilter())
                .read(fileInputSteam)
                .collect(Collectors.toList());

        assertEquals(1, list.size());
        var item = list.get(0);

        // Regular text should have converted entities
        assertEquals("Regular text with ä entity should be converted",
                item.getTitle().orElse(""));
        // CDATA sections should preserve original entities
        assertEquals("Text with &auml; entity inside CDATA should remain as &auml;",
                item.getChannel().getDescription());
        assertEquals("Another CDATA section with &auml; and &ouml; entities",
                item.getDescription().orElse(""));
    }

    private InputStream fromFile(String fileName) {
        return getClass().getClassLoader().getResourceAsStream(fileName);
    }
}
