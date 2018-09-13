package com.apptastic.integrationtest;

import com.apptastic.rssreader.Channel;
import com.apptastic.rssreader.Item;
import com.apptastic.rssreader.RssReader;
import org.junit.Test;

import java.io.IOException;
import java.util.Calendar;
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
            assertTrue(channel.getGenerator().startsWith("SiteVision"));
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
            assertTrue(channel.getGenerator().startsWith("SiteVision"));
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
            assertTrue(channel.getGenerator().startsWith("Argotic Syndication"));
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


    @Test
    public void rssBreakit() throws IOException {
        RssReader reader = new RssReader();
        List<Item> items = reader.read("https://www.breakit.se/feed/artiklar").collect(Collectors.toList());

        if (items.isEmpty() && (
                Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
                Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY))
            return; // Brakit articles are removed after one day and no articles published on Saturday or Sunday

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
            assertEquals("breakit.se", channel.getTitle());
            assertEquals("Breakit är Sveriges nyhetssajt om techbolag och startups.", channel.getDescription());
            assertEquals("sv", channel.getLanguage());
            assertEquals("http://breakit.se", channel.getLink());
            assertNull(channel.getCopyright());
            assertNull(channel.getGenerator());
            assertTrue(!channel.getLastBuildDate().isEmpty());
        }
    }


    @Test
    public void rssAffarsvarlden() throws IOException {
        RssReader reader = new RssReader();
        List<Item> items = reader.read("https://www.affarsvarlden.se/rss.xml").collect(Collectors.toList());

        assertTrue(!items.isEmpty());

        for (Item item : items) {
            // Validate item
            assertNotNull(item);
            assertTrue(!item.getGuid().isEmpty());
            assertTrue(!item.getIsPermaLink());
            assertTrue(!item.getTitle().isEmpty());
            assertTrue(item.getDescription() == null || !item.getDescription().isEmpty());
            assertTrue(!item.getPubDate().isEmpty());
            assertTrue(!item.getLink().isEmpty());

            // Validate channel
            Channel channel = item.getChannel();
            assertNotNull(channel);
            assertEquals("Affärsvärlden", channel.getTitle());
            assertEquals("Nyheter från www.affarsvarlden.se", channel.getDescription());
            assertEquals("sv", channel.getLanguage());
            assertEquals("http://www.affarsvarlden.se/", channel.getLink());
            assertNull(channel.getCopyright());
            assertNull(channel.getGenerator());
            assertNull(channel.getLastBuildDate());
        }
    }

    //
    @Test
    public void rssVAFinans() throws IOException {
        RssReader reader = new RssReader();
        List<Item> items = reader.read("https://www.vafinans.se/rss/nyheter").collect(Collectors.toList());

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
            assertEquals("vafinans.se", channel.getTitle());
            assertEquals("www.vafinans.se bietet Finanznachrichten zum weltweiten Boersengeschehen", channel.getDescription());
            assertEquals("de-ch", channel.getLanguage());
            assertEquals("https://www.vafinans.se", channel.getLink());
            assertEquals("finanzen.net GmbH",channel.getCopyright());
            assertNull(channel.getGenerator());
            assertNull(channel.getLastBuildDate());
        }
    }

}
