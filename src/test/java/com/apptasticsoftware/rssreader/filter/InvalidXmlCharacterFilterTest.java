package com.apptasticsoftware.rssreader.filter;

import com.apptasticsoftware.rssreader.RssReader;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InvalidXmlCharacterFilterTest {

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
    void testInvalidXmlEscapeStringExtra() {
        var fileInputSteam = fromFile("rss-invalid-xml-escape-string-extra.xml");
        var list = new RssReader()
                .addFeedFilter(new InvalidXmlCharacterFilter())
                .read(fileInputSteam)
                .collect(Collectors.toList());

        assertEquals(1, list.size());
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

    @Test
    void testCdataEndingWithTripleRightSquareBrackets() {
        var items = new RssReader()
                .addFeedFilter(new InvalidXmlCharacterFilter())
                .read(fromFile("rss-cdata-ending-triple-right-square-brackets.xml")).collect(Collectors.toList());

        assertEquals(20, items.size());
        var item = items.get(0);

        assertEquals("Sorti le 21 octobre dernier, Painkiller, le FPS action-coop « revisitant » le cultissime et unique Painkiller des années 2000 cher à nos cœurs, n&#8217;a pas connu le succès escompté et sa petite fanbase de joueurs commençait à s&#8217;inquiéter de ne pas avoir de nouvelles. Fort heureusement, la semaine dernière, le studio Anshar a publié [&#8230;]", item.getDescription().orElse(""));
    }

    private InputStream fromFile(String fileName) {
        return getClass().getClassLoader().getResourceAsStream(fileName);
    }
}
