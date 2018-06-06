package com.apptastic.integrationtest;

import com.apptastic.rssreader.Channel;
import com.apptastic.rssreader.Item;
import com.apptastic.rssreader.RssReader;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;


public class RssReaderIntegrationTest {

    @Test
    public void rssRiksbanken() throws IOException {
        RssReader reader = new RssReader();
        List<Item> items = reader.read("https://www.riksbank.se/sv/rss/pressmeddelanden").collect(Collectors.toList());

        assertTrue(!items.isEmpty());

        for (Item item : items) {
            // Validate item
            assertNotNull(item);
            assertTrue(!item.getGuid().isEmpty());
            assertFalse(item.getIsPermaLink());
            assertTrue(!item.getTitle().isEmpty());
            assertTrue(!item.getDescription().isEmpty());
            assertTrue(!item.getPubDate().isEmpty());
            assertTrue(!item.getLink().isEmpty());

            // Validate channel
            Channel channel = item.getChannel();
            assertNotNull(channel);
            assertEquals("Pressmeddelanden - Riksbanken", channel.getTitle());
            assertEquals("Pressmeddelanden från Sveriges riksbank som RSS-flöde.", channel.getDescription());
            assertEquals("sv", channel.getLanguage());
            assertEquals("https://www.riksbank.se/sv/rss/pressmeddelanden/", channel.getLink());
            assertNull(channel.getCopyright());
            assertNull(channel.getGenerator());
            assertTrue(channel.getLastBuildDate() != null && !channel.getLastBuildDate().isEmpty());
        }
    }


    @Test
    public void rssFinanspolitiskaradet() throws IOException {
        RssReader reader = new RssReader();
        List<Item> items = reader.read("http://www.finanspolitiskaradet.se/2.5dd459a31158f2d75c380003166/12.778e24d112a169fd1c1800036576.portlet?state=rss&sv.contenttype=text/xml;charset=UTF-8").collect(Collectors.toList());

        assertTrue(!items.isEmpty());

        for (Item item : items) {
            // Validate item
            assertNotNull(item);
            assertNull(item.getGuid());
            assertFalse(item.getIsPermaLink());
            assertTrue(!item.getTitle().isEmpty());
            assertTrue(!item.getDescription().isEmpty());
            assertTrue(!item.getPubDate().isEmpty());
            assertTrue(!item.getLink().isEmpty());

            // Validate channel
            Channel channel = item.getChannel();
            assertNotNull(channel);
            assertEquals("Finanspolitiska rådet", channel.getTitle());
            assertEquals("Nyheter från Finanspolitiska rådet", channel.getDescription());
            assertEquals("sv", channel.getLanguage());
            assertEquals("http://www.finanspolitiskaradet.se/2.5dd459a31158f2d75c380003166.html", channel.getLink());
            assertEquals("Finanspolitiska rådet", channel.getCopyright());
            assertEquals("SiteVision 4.2.2", channel.getGenerator());
            assertNull(channel.getLastBuildDate());
        }
    }


    @Test
    public void rssKonjunkturinstitutet() throws IOException {
        RssReader reader = new RssReader();
        List<Item> items = reader.read("https://www.konj.se/4.2de5c57614f808a95afcc13f/12.2de5c57614f808a95afcc354.portlet?state=rss&sv.contenttype=text/xml;charset=UTF-8").collect(Collectors.toList());

        assertTrue(!items.isEmpty());

        for (Item item : items) {
            // Validate item
            assertNotNull(item);
            assertNull(item.getGuid());
            assertFalse(item.getIsPermaLink());
            assertTrue(!item.getTitle().isEmpty());
            assertTrue(!item.getDescription().isEmpty());
            assertTrue(!item.getPubDate().isEmpty());
            assertTrue(!item.getLink().isEmpty());

            // Validate channel
            Channel channel = item.getChannel();
            assertNotNull(channel);
            assertEquals("Publikationer från Konjunkturinstitutet", channel.getTitle());
            assertEquals("Rapportutgåvor publicerade på konj.se", channel.getDescription());
            assertEquals("sv", channel.getLanguage());
            assertEquals("https://konj.se/om-ki/aktuellt/publikationer.html", channel.getLink());
            assertEquals("Konjunkturinstitutet", channel.getCopyright());
            assertEquals("SiteVision 4.5.0.1", channel.getGenerator());
            assertNull(channel.getLastBuildDate());
        }
    }


    @Test
    public void rssScb() throws IOException {
        RssReader reader = new RssReader();
        List<Item> items = reader.read("http://www.scb.se/rss/").collect(Collectors.toList());

        assertTrue(!items.isEmpty());

        for (Item item : items) {
            // Validate item
            assertNotNull(item);
            assertTrue(!item.getGuid().isEmpty());
            assertTrue(item.getIsPermaLink());
            assertTrue(!item.getTitle().isEmpty());
            assertTrue(!item.getDescription().isEmpty());
            assertTrue(!item.getPubDate().isEmpty());
            assertTrue(!item.getLink().isEmpty());

            // Validate channel
            Channel channel = item.getChannel();
            assertNotNull(channel);
            assertEquals("www.scb.se - Statistiknyheter", channel.getTitle());
            assertEquals("Statistiknyheter via RSS", channel.getDescription());
            assertEquals("sv", channel.getLanguage());
            assertEquals("http://www.scb.se/sv_/Om-SCB/Nyheter-och-pressmeddelanden/Bevaka-nyheter-fran-SCB/Statistiknyheter-via-RSS/", channel.getLink());
            assertNull(channel.getCopyright());
            assertNull(channel.getGenerator());
            assertNull(channel.getLastBuildDate());
        }
    }


    @Test
    public void rssEkobrottsmyndigheten() throws IOException {
        RssReader reader = new RssReader();
        List<Item> items = reader.read("https://www.ekobrottsmyndigheten.se/Templates/Handlers/News/HandlerNewsRss.ashx?languageBranch=sv").collect(Collectors.toList());

        assertTrue(!items.isEmpty());

        for (Item item : items) {
            // Validate item
            assertNotNull(item);
            assertTrue(!item.getGuid().isEmpty());
            assertTrue(item.getIsPermaLink());
            assertTrue(!item.getTitle().isEmpty());
            assertTrue(!item.getDescription().isEmpty());
            assertTrue(!item.getPubDate().isEmpty());
            assertTrue(!item.getLink().isEmpty());

            // Validate channel
            Channel channel = item.getChannel();
            assertNotNull(channel);
            assertEquals("Nyheter", channel.getTitle());
            assertEquals("Senaste nyheterna på Ekobrottsmyndigheten", channel.getDescription());
            assertEquals("sv", channel.getLanguage());
            assertEquals("https://www.ekobrottsmyndigheten.se/sv/press/nyheter/", channel.getLink());
            assertNull(channel.getCopyright());
            assertEquals("Argotic Syndication Framework 2008.0.2.0, http://www.codeplex.com/Argotic", channel.getGenerator());
            assertNull(channel.getLastBuildDate());
        }
    }


    @Test
    public void rssVeckansAffarer() throws IOException {
        RssReader reader = new RssReader();
        List<Item> items = reader.read("https://www.va.se/rss/").collect(Collectors.toList());

        assertTrue(!items.isEmpty());

        for (Item item : items) {
            // Validate item
            assertNotNull(item);
            assertTrue(!item.getGuid().isEmpty());
            assertFalse(item.getIsPermaLink());
            assertTrue(!item.getTitle().isEmpty());
            assertTrue(!item.getDescription().isEmpty());
            assertTrue(!item.getPubDate().isEmpty());
            assertTrue(!item.getLink().isEmpty());

            // Validate channel
            Channel channel = item.getChannel();
            assertNotNull(channel);
            assertEquals("RSS", channel.getTitle());
            assertNull(channel.getDescription());
            assertNull(channel.getLanguage());
            assertEquals("http://www.va.se/rss/", channel.getLink());
            assertNull(channel.getCopyright());
            assertNull(channel.getGenerator());
            assertNull(channel.getLastBuildDate());
        }
    }


    @Test
    public void rssPlacera() throws IOException {
        RssReader reader = new RssReader();
        List<Item> items = reader.read("https://www.avanza.se/placera/forstasidan.rss.xml").collect(Collectors.toList());

        assertTrue(!items.isEmpty());

        for (Item item : items) {
            // Validate item
            assertNotNull(item);
            assertTrue(!item.getGuid().isEmpty());
            assertTrue(item.getIsPermaLink());
            assertTrue(!item.getTitle().isEmpty());
            assertTrue(!item.getDescription().isEmpty());
            assertTrue(!item.getPubDate().isEmpty());
            assertTrue(!item.getLink().isEmpty());

            // Validate channel
            Channel channel = item.getChannel();
            assertNotNull(channel);
            assertEquals("Placera.se", channel.getTitle());
            assertTrue(!channel.getDescription().isEmpty());
            assertNull(channel.getLanguage());
            assertEquals("https://www.placera.se", channel.getLink());
            assertNull(channel.getCopyright());
            assertNull(channel.getGenerator());
            assertNull(channel.getLastBuildDate());
        }
    }
}
