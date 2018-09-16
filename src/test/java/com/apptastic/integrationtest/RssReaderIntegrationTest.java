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
            assertTrue(!item.getGuid().get().isEmpty());
            assertFalse(item.getIsPermaLink().get());
            assertFalse(item.getTitle().get().isEmpty());
            assertFalse(item.getDescription().get().isEmpty());
            assertFalse(item.getPubDate().get().isEmpty());
            assertFalse(item.getLink().get().isEmpty());

            // Validate channel
            Channel channel = item.getChannel();
            assertNotNull(channel);
            assertEquals("Pressmeddelanden - Riksbanken", channel.getTitle().get());
            assertEquals("Pressmeddelanden från Sveriges riksbank som RSS-flöde.", channel.getDescription().get());
            assertEquals("sv", channel.getLanguage().get());
            assertEquals("https://www.riksbank.se/sv/rss/pressmeddelanden/", channel.getLink().get());
            assertFalse(channel.getCopyright().isPresent());
            assertFalse(channel.getGenerator().isPresent());
            assertTrue(channel.getLastBuildDate().isPresent() && !channel.getLastBuildDate().get().isEmpty());
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
            assertFalse(item.getGuid().isPresent());
            assertFalse(item.getIsPermaLink().isPresent());
            assertFalse(item.getTitle().get().isEmpty());
            assertFalse(item.getDescription().get().isEmpty());
            assertFalse(item.getPubDate().get().isEmpty());
            assertFalse(item.getLink().get().isEmpty());

            // Validate channel
            Channel channel = item.getChannel();
            assertNotNull(channel);
            assertEquals("Finanspolitiska rådet", channel.getTitle().get());
            assertEquals("Nyheter från Finanspolitiska rådet", channel.getDescription().get());
            assertEquals("sv", channel.getLanguage().get());
            assertEquals("http://www.finanspolitiskaradet.se/2.5dd459a31158f2d75c380003166.html", channel.getLink().get());
            assertEquals("Finanspolitiska rådet", channel.getCopyright().get());
            assertTrue(channel.getGenerator().isPresent());
            assertTrue(channel.getGenerator().get().startsWith("SiteVision"));
            assertFalse(channel.getLastBuildDate().isPresent());
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
            assertFalse(item.getGuid().isPresent());
            assertFalse(item.getIsPermaLink().isPresent());
            assertFalse(item.getTitle().get().isEmpty());
            assertFalse(item.getDescription().get().isEmpty());
            assertFalse(item.getPubDate().get().isEmpty());
            assertFalse(item.getLink().get().isEmpty());

            // Validate channel
            Channel channel = item.getChannel();
            assertNotNull(channel);
            assertEquals("Publikationer från Konjunkturinstitutet", channel.getTitle().get());
            assertEquals("Rapportutgåvor publicerade på konj.se", channel.getDescription().get());
            assertEquals("sv", channel.getLanguage().get());
            assertEquals("https://konj.se/om-ki/aktuellt/publikationer.html", channel.getLink().get());
            assertEquals("Konjunkturinstitutet", channel.getCopyright().get());
            assertTrue(channel.getGenerator().isPresent());
            assertTrue(channel.getGenerator().get().startsWith("SiteVision"));
            assertFalse(channel.getLastBuildDate().isPresent());
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
            assertTrue(!item.getGuid().get().isEmpty());
            assertTrue(item.getIsPermaLink().get());
            assertFalse(item.getTitle().get().isEmpty());
            assertFalse(item.getDescription().get().isEmpty());
            assertFalse(item.getPubDate().get().isEmpty());
            assertFalse(item.getLink().get().isEmpty());

            // Validate channel
            Channel channel = item.getChannel();
            assertNotNull(channel);
            assertEquals("www.scb.se - Statistiknyheter", channel.getTitle().get());
            assertEquals("Statistiknyheter via RSS", channel.getDescription().get());
            assertEquals("sv", channel.getLanguage().get());
            assertEquals("http://www.scb.se/sv_/Om-SCB/Nyheter-och-pressmeddelanden/Bevaka-nyheter-fran-SCB/Statistiknyheter-via-RSS/", channel.getLink().get());
            assertFalse(channel.getCopyright().isPresent());
            assertFalse(channel.getGenerator().isPresent());
            assertFalse(channel.getLastBuildDate().isPresent());
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
            assertFalse(item.getGuid().get().isEmpty());
            assertTrue(item.getIsPermaLink().get());
            assertFalse(item.getTitle().get().isEmpty());
            assertFalse(item.getDescription().get().isEmpty());
            assertFalse(item.getPubDate().get().isEmpty());
            assertFalse(item.getLink().get().isEmpty());

            // Validate channel
            Channel channel = item.getChannel();
            assertNotNull(channel);
            assertEquals("Nyheter", channel.getTitle().get());
            assertEquals("Senaste nyheterna på Ekobrottsmyndigheten", channel.getDescription().get());
            assertEquals("sv", channel.getLanguage().get());
            assertEquals("https://www.ekobrottsmyndigheten.se/sv/press/nyheter/", channel.getLink().get());
            assertFalse(channel.getCopyright().isPresent());
            assertTrue(channel.getGenerator().isPresent());
            assertTrue(channel.getGenerator().get().startsWith("Argotic Syndication"));
            assertFalse(channel.getLastBuildDate().isPresent());
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
            assertFalse(item.getGuid().get().isEmpty());
            assertFalse(item.getIsPermaLink().get());
            assertFalse(item.getTitle().get().isEmpty());
            assertFalse(item.getDescription().get().isEmpty());
            assertFalse(item.getPubDate().get().isEmpty());
            assertFalse(item.getLink().get().isEmpty());

            // Validate channel
            Channel channel = item.getChannel();
            assertNotNull(channel);
            assertEquals("RSS", channel.getTitle().get());
            assertFalse(channel.getDescription().isPresent());
            assertFalse(channel.getLanguage().isPresent());
            assertEquals("http://www.va.se/rss/", channel.getLink().get());
            assertFalse(channel.getCopyright().isPresent());
            assertFalse(channel.getGenerator().isPresent());
            assertFalse(channel.getLastBuildDate().isPresent());
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
            assertFalse(item.getGuid().get().isEmpty());
            assertTrue(item.getIsPermaLink().get());
            assertFalse(item.getTitle().get().isEmpty());
            assertFalse(item.getDescription().get().isEmpty());
            assertFalse(item.getPubDate().get().isEmpty());
            assertFalse(item.getLink().get().isEmpty());

            // Validate channel
            Channel channel = item.getChannel();
            assertNotNull(channel);
            assertEquals("Placera.se", channel.getTitle().get());
            assertTrue(channel.getDescription().isPresent());
            assertTrue(!channel.getDescription().get().isEmpty());
            assertFalse(channel.getLanguage().isPresent());
            assertEquals("https://www.placera.se", channel.getLink().get());
            assertFalse(channel.getCopyright().isPresent());
            assertFalse(channel.getGenerator().isPresent());
            assertFalse(channel.getLastBuildDate().isPresent());
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
            assertFalse(item.getGuid().get().isEmpty());
            assertTrue(item.getIsPermaLink().get());
            assertFalse(item.getTitle().get().isEmpty());
            assertFalse(item.getDescription().get().isEmpty());
            assertFalse(item.getPubDate().get().isEmpty());
            assertFalse(item.getLink().get().isEmpty());

            // Validate channel
            Channel channel = item.getChannel();
            assertNotNull(channel);
            assertEquals("breakit.se", channel.getTitle().get());
            assertEquals("Breakit är Sveriges nyhetssajt om techbolag och startups.", channel.getDescription().get());
            assertEquals("sv", channel.getLanguage().get());
            assertEquals("http://breakit.se", channel.getLink().get());
            assertFalse(channel.getCopyright().isPresent());
            assertFalse(channel.getGenerator().isPresent());
            assertTrue(channel.getLastBuildDate().isPresent());
            assertTrue(!channel.getLastBuildDate().get().isEmpty());
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
            assertFalse(item.getGuid().get().isEmpty());
            assertFalse(item.getIsPermaLink().get());
            assertFalse(item.getTitle().get().isEmpty());
            assertTrue(!item.getDescription().isPresent() || !item.getDescription().get().isEmpty());
            assertFalse(item.getPubDate().get().isEmpty());
            assertFalse(item.getLink().get().isEmpty());

            // Validate channel
            Channel channel = item.getChannel();
            assertNotNull(channel);
            assertEquals("Affärsvärlden", channel.getTitle().get());
            assertEquals("Nyheter från www.affarsvarlden.se", channel.getDescription().get());
            assertEquals("sv", channel.getLanguage().get());
            assertEquals("http://www.affarsvarlden.se/", channel.getLink().get());
            assertFalse(channel.getCopyright().isPresent());
            assertFalse(channel.getGenerator().isPresent());
            assertFalse(channel.getLastBuildDate().isPresent());
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
            assertFalse(item.getGuid().get().isEmpty());
            assertTrue(item.getIsPermaLink().get());
            assertFalse(item.getTitle().get().isEmpty());
            assertFalse(item.getDescription().get().isEmpty());
            assertFalse(item.getPubDate().get().isEmpty());
            assertFalse(item.getLink().get().isEmpty());

            // Validate channel
            Channel channel = item.getChannel();
            assertNotNull(channel);
            assertEquals("vafinans.se", channel.getTitle().get());
            assertEquals("www.vafinans.se bietet Finanznachrichten zum weltweiten Boersengeschehen", channel.getDescription().get());
            assertEquals("de-ch", channel.getLanguage().get());
            assertEquals("https://www.vafinans.se", channel.getLink().get());
            assertEquals("finanzen.net GmbH",channel.getCopyright().get());
            assertFalse(channel.getGenerator().isPresent());
            assertFalse(channel.getLastBuildDate().isPresent());
        }
    }

}
