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
    void testInvalidXmlEscape() {
        var fileInputSteam = fromFile("rss-invalid-xml-escape.xml");
        var list = new RssReader()
                .addFeedFilter(new InvalidXmlCharacterFilter())
                .read(fileInputSteam)
                .collect(Collectors.toList());

        assertEquals(10, list.size());
        assertEquals("Myrskylän kunta", list.get(0).getChannel().getTitle());
    }

    private InputStream fromFile(String fileName) {
        return getClass().getClassLoader().getResourceAsStream(fileName);
    }
}
