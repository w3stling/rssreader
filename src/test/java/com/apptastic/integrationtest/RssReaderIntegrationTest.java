package com.apptastic.integrationtest;

import com.apptastic.rssreader.Channel;
import com.apptastic.rssreader.Item;
import com.apptastic.rssreader.RssReader;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.npathai.hamcrestopt.OptionalMatchers.*;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;


public class RssReaderIntegrationTest {

    @Test
    public void rssRiksbanken() throws IOException {
        RssReader reader = new RssReader();
        List<Item> items = reader.read("https://www.riksbank.se/sv/rss/pressmeddelanden").collect(Collectors.toList());

        assertTrue(!items.isEmpty());

        for (Item item : items) {
            // Validate item
            assertNotNull(item);
            assertThat(item.getGuid(), isPresentAnd(not(isEmptyString())));
            assertThat(item.getIsPermaLink(), isPresentAnd(is(false)));
            assertThat(item.getTitle(), isPresentAnd(not(isEmptyString())));
            assertThat(item.getDescription(), isPresentAnd(not(isEmptyString())));
            assertThat(item.getPubDate(), isPresentAnd(not(isEmptyString())));
            assertThat(item.getLink(), isPresentAnd(not(isEmptyString())));

            // Validate channel
            Channel channel = item.getChannel();
            assertNotNull(channel);
            assertThat(channel.getTitle(), isPresentAndIs("Pressmeddelanden - Riksbanken"));
            assertThat(channel.getDescription(), isPresentAndIs("Pressmeddelanden från Sveriges riksbank som RSS-flöde."));
            assertThat(channel.getLanguage(), isPresentAndIs("sv"));
            assertThat(channel.getLink(), isPresentAndIs("https://www.riksbank.se/sv/rss/pressmeddelanden/"));
            assertThat(channel.getCopyright(), isEmpty());
            assertThat(channel.getGenerator(), isEmpty());
            assertThat(channel.getLastBuildDate(), isPresentAnd(not(isEmptyString())));
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
            assertThat(item.getGuid(), isEmpty());
            assertThat(item.getIsPermaLink(), isEmpty());
            assertThat(item.getTitle(), isPresent());
            assertThat(item.getDescription(), isPresent());
            assertThat(item.getPubDate(), isPresent());
            assertThat(item.getLink(), isPresent());

            // Validate channel
            Channel channel = item.getChannel();
            assertNotNull(channel);
            assertThat(channel.getTitle(), isPresentAndIs("Finanspolitiska rådet"));
            assertThat(channel.getLanguage(), isPresentAndIs("sv"));
            assertThat(channel.getLink(), isPresentAndIs("http://www.finanspolitiskaradet.se/2.5dd459a31158f2d75c380003166.html"));
            assertThat(channel.getCopyright(), isPresentAndIs("Finanspolitiska rådet"));
            assertThat(channel.getCopyright(), isPresent());
            assertThat(channel.getGenerator(), isPresentAnd(startsWith("SiteVision")));
            assertThat(channel.getLastBuildDate(), isEmpty());
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
            assertThat(item.getGuid(), isPresent());
            assertThat(item.getIsPermaLink(), isPresentAndIs(false));
            assertThat(item.getTitle(), isPresent());
            assertThat(item.getDescription(), isPresent());
            assertThat(item.getPubDate(), isPresent());
            assertThat(item.getLink(), isPresent());

            // Validate channel
            Channel channel = item.getChannel();
            assertNotNull(channel);
            assertThat(channel.getTitle(), isPresentAndIs("Publikationer från Konjunkturinstitutet"));
            assertThat(channel.getDescription(), isPresentAndIs("Rapportutgåvor publicerade på konj.se"));
            assertThat(channel.getLanguage(), isPresentAndIs("sv"));
            assertThat(channel.getLink(), isPresentAndIs("https://konj.se/om-ki/aktuellt/publikationer.html"));
            assertThat(channel.getCopyright(), isPresentAndIs("Konjunkturinstitutet"));
            assertThat(channel.getGenerator(), isPresentAnd(startsWith("SiteVision")));
            assertThat(channel.getLastBuildDate(), isEmpty());
        }
    }


    @Test
    public void rssScb() throws IOException {
        RssReader reader = new RssReader();
        List<Item> items = reader.read("https://www.scb.se/Feed/statistiknyheter/").collect(Collectors.toList());

        assertTrue(!items.isEmpty());

        for (Item item : items) {
            // Validate item
            assertNotNull(item);
            assertThat(item.getGuid(), isPresent());
            assertThat(item.getIsPermaLink(), isEmpty());
            assertThat(item.getTitle(), isPresent());
            assertThat(item.getDescription(), isPresent());
            assertThat(item.getPubDate(), isPresent());
            assertThat(item.getLink(), isPresent());

            // Validate channel
            Channel channel = item.getChannel();
            assertNotNull(channel);
            assertThat(channel.getTitle(), isPresentAndIs("Statistiska centralbyrån - Statistiknyheter"));
            assertThat(channel.getDescription(), isPresentAndIs("Statistiknyheter via RSS"));
            assertThat(channel.getLanguage(), isEmpty());
            assertThat(channel.getLink(), isPresentAndIs("http://www.scb.se/feed/statistiknyheter/"));
            assertThat(channel.getCopyright(), isEmpty());
            assertThat(channel.getGenerator(), isEmpty());
            assertThat(channel.getLastBuildDate(), isPresent());
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
            assertThat(item.getGuid(), isPresent());
            assertThat(item.getIsPermaLink(), isPresentAndIs(true));
            assertThat(item.getTitle(), isPresent());
            assertThat(item.getDescription(), isPresent());
            assertThat(item.getPubDate(), isPresent());
            assertThat(item.getLink(), isPresent());

            // Validate channel
            Channel channel = item.getChannel();
            assertNotNull(channel);
            assertThat(channel.getTitle(), isPresentAndIs("Nyheter"));
            assertThat(channel.getDescription(), isPresentAndIs("Senaste nyheterna på Ekobrottsmyndigheten"));
            assertThat(channel.getLanguage(), isPresentAndIs("sv"));
            assertThat(channel.getLink(), isPresentAndIs("https://www.ekobrottsmyndigheten.se/sv/press/nyheter/"));
            assertThat(channel.getCopyright(), isEmpty());
            assertThat(channel.getGenerator(), isPresentAnd(startsWith("Argotic Syndication")));
            assertThat(channel.getLastBuildDate(), isEmpty());
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
            assertThat(item.getGuid(), isPresent());
            assertThat(item.getIsPermaLink(), isPresentAndIs(false));
            assertThat(item.getTitle(), isPresentAnd(not(isEmptyString())));
            assertThat(item.getDescription(), isPresentAnd(not(isEmptyString())));
            assertThat(item.getPubDate(), isPresentAnd(not(isEmptyString())));
            assertThat(item.getLink(), isPresentAnd(not(isEmptyString())));

            // Validate channel
            Channel channel = item.getChannel();
            assertNotNull(channel);
            assertThat(channel.getTitle(), isPresentAndIs("RSS"));
            assertThat(channel.getDescription(), isEmpty());
            assertThat(channel.getDescription(), isEmpty());
            assertThat(channel.getLink(), isPresentAndIs("http://www.va.se/rss/"));
            assertThat(channel.getCopyright(), isEmpty());
            assertThat(channel.getGenerator(), isEmpty());
            assertThat(channel.getLastBuildDate(), isEmpty());
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
            assertThat(item.getGuid(), isPresentAnd(not(isEmptyString())));
            assertThat(item.getIsPermaLink(), isPresentAndIs(true));
            assertThat(item.getTitle(), isPresentAnd(not(isEmptyString())));
            assertThat(item.getDescription(), isPresentAnd(not(isEmptyString())));
            assertThat(item.getPubDate(), isPresentAnd(not(isEmptyString())));
            assertThat(item.getLink(), isPresentAnd(not(isEmptyString())));

            // Validate channel
            Channel channel = item.getChannel();
            assertNotNull(channel);
            assertThat(channel.getTitle(), isPresentAndIs("Placera.se"));
            assertThat(channel.getDescription(), isPresentAnd(not(isEmptyString())));
            assertThat(channel.getLink(), isPresentAndIs("https://www.placera.se"));
            assertThat(channel.getCopyright(), isEmpty());
            assertThat(channel.getGenerator(), isEmpty());
            assertThat(channel.getLastBuildDate(), isEmpty());
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
            assertThat(item.getGuid(), isPresentAnd(not(isEmptyString())));
            assertThat(item.getIsPermaLink(), isPresentAndIs(true));
            assertThat(item.getTitle(), isPresentAnd(not(isEmptyString())));
            assertThat(item.getDescription(), isPresentAnd(not(isEmptyString())));
            assertThat(item.getPubDate(), isPresentAnd(not(isEmptyString())));
            assertThat(item.getLink(), isPresentAnd(not(isEmptyString())));

            // Validate channel
            Channel channel = item.getChannel();
            assertNotNull(channel);
            assertThat(channel.getTitle(), isPresentAndIs("breakit.se"));
            assertThat(channel.getDescription(), isPresentAndIs("Breakit är Sveriges nyhetssajt om techbolag och startups."));
            assertThat(channel.getLanguage(), isPresentAndIs("sv"));
            assertThat(channel.getLink(), isPresentAndIs("http://breakit.se"));
            assertThat(channel.getCopyright(), isEmpty());
            assertThat(channel.getGenerator(), isEmpty());
            assertThat(channel.getLastBuildDate(), isPresentAnd(not(isEmptyString())));
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
            assertThat(item.getGuid(), isPresentAnd(not(isEmptyString())));
            assertThat(item.getIsPermaLink(), isPresentAndIs(false));
            assertThat(item.getTitle(), isPresentAnd(not(isEmptyString())));
            assertThat(item.getDescription(), anyOf(isEmpty(), isPresentAnd(not(isEmptyString()))));
            assertThat(item.getPubDate(), isPresent());
            assertThat(item.getLink(), isPresent());

            // Validate channel
            Channel channel = item.getChannel();
            assertNotNull(channel);
            assertThat(channel.getTitle(), isPresentAndIs("Affärsvärlden"));
            assertThat(channel.getDescription(), isPresentAndIs("Nyheter från www.affarsvarlden.se"));
            assertThat(channel.getLanguage(), isPresentAndIs("sv"));
            assertThat(channel.getLink(), isPresentAndIs("http://www.affarsvarlden.se/"));
            assertThat(channel.getCopyright(), isEmpty());
            assertThat(channel.getGenerator(), isEmpty());
            assertThat(channel.getLastBuildDate(), isEmpty());
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
            assertThat(item.getGuid(), isPresentAnd(not(isEmptyString())));
            assertThat(item.getIsPermaLink(), isPresentAndIs(true));
            assertThat(item.getTitle(), isPresent());
            assertThat(item.getDescription(), isPresent());
            assertThat(item.getPubDate(), isPresent());
            assertThat(item.getLink(), isPresent());

            // Validate channel
            Channel channel = item.getChannel();
            assertNotNull(channel);
            assertThat(channel.getTitle(), isPresentAndIs("vafinans.se"));
            assertThat(channel.getDescription(), isPresentAndIs("www.vafinans.se bietet Finanznachrichten zum weltweiten Boersengeschehen"));
            assertThat(channel.getLanguage(), isPresentAndIs("de-ch"));
            assertThat(channel.getLink(), isPresentAndIs("https://www.vafinans.se"));
            assertThat(channel.getCopyright(), isPresentAndIs("finanzen.net GmbH"));
            assertThat(channel.getGenerator(), isEmpty());
            assertThat(channel.getLastBuildDate(), isEmpty());
        }
    }

}
