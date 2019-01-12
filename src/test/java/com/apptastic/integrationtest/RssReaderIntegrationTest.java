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
            // Validate channel
            Channel channel = item.getChannel();
            assertNotNull(channel);
            assertThat(channel.getTitle(), is("Pressmeddelanden - Riksbanken"));
            assertThat(channel.getDescription(), is("Pressmeddelanden från Sveriges riksbank som RSS-flöde."));
            assertThat(channel.getLanguage(), isPresentAndIs("sv"));
            assertThat(channel.getLink(), is("https://www.riksbank.se/sv/rss/pressmeddelanden/"));
            assertThat(channel.getCopyright(), isEmpty());
            assertThat(channel.getGenerator(), isEmpty());
            assertThat(channel.getLastBuildDate(), isPresentAnd(not(isEmptyString())));

            // Validate item
            assertNotNull(item);
            assertThat(item.getGuid(), isPresentAnd(not(isEmptyString())));
            assertThat(item.getIsPermaLink(), isPresentAnd(is(false)));
            assertThat(item.getTitle(), isPresentAnd(not(isEmptyString())));
            assertThat(item.getDescription(), isPresentAnd(not(isEmptyString())));
            assertThat(item.getPubDate(), isPresentAnd(not(isEmptyString())));
            assertThat(item.getLink(), isPresentAnd(not(isEmptyString())));
        }
    }


    @Test
    public void rssFinanspolitiskaradet() throws IOException {
        RssReader reader = new RssReader();
        List<Item> items = reader.read("http://www.finanspolitiskaradet.se/2.5dd459a31158f2d75c380003166/12.778e24d112a169fd1c1800036576.portlet?state=rss&sv.contenttype=text/xml;charset=UTF-8").collect(Collectors.toList());

        assertTrue(!items.isEmpty());

        for (Item item : items) {
            // Validate channel
            Channel channel = item.getChannel();
            assertNotNull(channel);
            assertThat(channel.getTitle(), is("Finanspolitiska rådet"));
            assertThat(channel.getLanguage(), isPresentAndIs("sv"));
            assertThat(channel.getLink(), is("http://www.finanspolitiskaradet.se/2.5dd459a31158f2d75c380003166.html"));
            assertThat(channel.getCopyright(), isPresentAndIs("Finanspolitiska rådet"));
            assertThat(channel.getCopyright(), isPresent());
            assertThat(channel.getGenerator(), isPresentAnd(startsWith("SiteVision")));
            assertThat(channel.getLastBuildDate(), isEmpty());

            // Validate item
            assertNotNull(item);
            assertThat(item.getGuid(), isEmpty());
            assertThat(item.getIsPermaLink(), isEmpty());
            assertThat(item.getTitle(), isPresent());
            assertThat(item.getDescription(), isPresent());
            assertThat(item.getPubDate(), isPresent());
            assertThat(item.getLink(), isPresent());
        }
    }


    @Test
    public void rssKonjunkturinstitutet() throws IOException {
        RssReader reader = new RssReader();
        List<Item> items = reader.read("https://www.konj.se/4.2de5c57614f808a95afcc13f/12.2de5c57614f808a95afcc354.portlet?state=rss&sv.contenttype=text/xml;charset=UTF-8").collect(Collectors.toList());

        assertTrue(!items.isEmpty());

        for (Item item : items) {
            // Validate channel
            Channel channel = item.getChannel();
            assertNotNull(channel);
            assertThat(channel.getTitle(), is("Publikationer från Konjunkturinstitutet"));
            assertThat(channel.getDescription(), is("Rapportutgåvor publicerade på konj.se"));
            assertThat(channel.getLanguage(), isPresentAndIs("sv"));
            assertThat(channel.getLink(), is("https://konj.se/om-ki/aktuellt/publikationer.html"));
            assertThat(channel.getCopyright(), isPresentAndIs("Konjunkturinstitutet"));
            assertThat(channel.getGenerator(), isPresentAnd(startsWith("SiteVision")));
            assertThat(channel.getLastBuildDate(), isEmpty());

            // Validate item
            assertNotNull(item);
            assertThat(item.getGuid(), isPresent());
            assertThat(item.getIsPermaLink(), isPresentAndIs(false));
            assertThat(item.getTitle(), isPresent());
            assertThat(item.getDescription(), isPresent());
            assertThat(item.getPubDate(), isPresent());
            assertThat(item.getLink(), isPresent());
        }
    }


    @Test
    public void rssScb() throws IOException {
        RssReader reader = new RssReader();
        List<Item> items = reader.read("https://www.scb.se/Feed/statistiknyheter/").collect(Collectors.toList());

        assertTrue(!items.isEmpty());

        for (Item item : items) {
            // Validate channel
            Channel channel = item.getChannel();
            assertNotNull(channel);
            assertThat(channel.getTitle(), is("Statistiska centralbyrån - Statistiknyheter"));
            assertThat(channel.getDescription(), is("Statistiknyheter via RSS"));
            assertThat(channel.getLanguage(), isEmpty());
            assertThat(channel.getLink(), is("http://www.scb.se/feed/statistiknyheter/"));
            assertThat(channel.getCopyright(), isEmpty());
            assertThat(channel.getGenerator(), isEmpty());
            assertThat(channel.getLastBuildDate(), isPresent());

            // Validate item
            assertNotNull(item);
            assertThat(item.getGuid(), isPresent());
            assertThat(item.getIsPermaLink(), isEmpty());
            assertThat(item.getTitle(), isPresent());
            assertThat(item.getDescription(), isPresent());
            assertThat(item.getPubDate(), isPresent());
            assertThat(item.getLink(), isPresent());
        }
    }


    @Test
    public void rssEkobrottsmyndigheten() throws IOException {
        RssReader reader = new RssReader();
        List<Item> items = reader.read("https://www.ekobrottsmyndigheten.se/Templates/Handlers/News/HandlerNewsRss.ashx?languageBranch=sv").collect(Collectors.toList());

        assertTrue(!items.isEmpty());

        for (Item item : items) {
            // Validate channel
            Channel channel = item.getChannel();
            assertNotNull(channel);
            assertThat(channel.getTitle(), is("Nyheter"));
            assertThat(channel.getDescription(), is("Senaste nyheterna på Ekobrottsmyndigheten"));
            assertThat(channel.getLanguage(), isPresentAndIs("sv"));
            assertThat(channel.getLink(), is("https://www.ekobrottsmyndigheten.se/sv/press/nyheter/"));
            assertThat(channel.getCopyright(), isEmpty());
            assertThat(channel.getGenerator(), isPresentAnd(startsWith("Argotic Syndication")));
            assertThat(channel.getLastBuildDate(), isEmpty());

            // Validate item
            assertNotNull(item);
            assertThat(item.getGuid(), isPresent());
            assertThat(item.getIsPermaLink(), isPresentAndIs(true));
            assertThat(item.getTitle(), isPresent());
            assertThat(item.getDescription(), isPresent());
            assertThat(item.getPubDate(), isPresent());
            assertThat(item.getLink(), isPresent());
        }
    }


    @Test
    public void rssVeckansAffarer() throws IOException {
        RssReader reader = new RssReader();
        List<Item> items = reader.read("https://www.va.se/rss/").collect(Collectors.toList());

        assertTrue(!items.isEmpty());

        for (Item item : items) {
            // Validate channel
            Channel channel = item.getChannel();
            assertNotNull(channel);
            assertThat(channel.getTitle(), is("RSS"));
            assertThat(channel.getDescription(), is(""));
            assertThat(channel.getLink(), is("http://www.va.se/rss/"));
            assertThat(channel.getCopyright(), isEmpty());
            assertThat(channel.getGenerator(), isEmpty());
            assertThat(channel.getLastBuildDate(), isEmpty());

            // Validate item
            assertNotNull(item);
            assertThat(item.getGuid(), isPresent());
            assertThat(item.getIsPermaLink(), isPresentAndIs(false));
            assertThat(item.getTitle(), isPresentAnd(not(isEmptyString())));
            assertThat(item.getDescription(), isPresentAnd(not(isEmptyString())));
            assertThat(item.getPubDate(), isPresentAnd(not(isEmptyString())));
            assertThat(item.getLink(), isPresentAnd(not(isEmptyString())));
        }
    }


    @Test
    public void rssPlacera() throws IOException {
        RssReader reader = new RssReader();
        List<Item> items = reader.read("https://www.avanza.se/placera/forstasidan.rss.xml").collect(Collectors.toList());

        assertTrue(!items.isEmpty());

        for (Item item : items) {
            // Validate channel
            Channel channel = item.getChannel();
            assertNotNull(channel);
            assertThat(channel.getTitle(), is("Placera.se"));
            assertThat(channel.getDescription(), is(not(isEmptyString())));
            assertThat(channel.getLink(), is("https://www.placera.se"));
            assertThat(channel.getCopyright(), isEmpty());
            assertThat(channel.getGenerator(), isEmpty());
            assertThat(channel.getLastBuildDate(), isEmpty());

            // Validate item
            assertNotNull(item);
            assertThat(item.getGuid(), isPresentAnd(not(isEmptyString())));
            assertThat(item.getIsPermaLink(), isPresentAndIs(true));
            assertThat(item.getTitle(), isPresentAnd(not(isEmptyString())));
            assertThat(item.getDescription(), isPresentAnd(not(isEmptyString())));
            assertThat(item.getPubDate(), isPresentAnd(not(isEmptyString())));
            assertThat(item.getLink(), isPresentAnd(not(isEmptyString())));
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
            // Validate channel
            Channel channel = item.getChannel();
            assertNotNull(channel);
            assertThat(channel.getTitle(), is("breakit.se"));
            assertThat(channel.getDescription(), is("Breakit är Sveriges nyhetssajt om techbolag och startups."));
            assertThat(channel.getLanguage(), isPresentAndIs("sv"));
            assertThat(channel.getLink(), is("http://breakit.se"));
            assertThat(channel.getCopyright(), isEmpty());
            assertThat(channel.getGenerator(), isEmpty());
            assertThat(channel.getLastBuildDate(), isPresentAnd(not(isEmptyString())));

            // Validate item
            assertNotNull(item);
            assertThat(item.getGuid(), isPresentAnd(not(isEmptyString())));
            assertThat(item.getIsPermaLink(), isPresentAndIs(true));
            assertThat(item.getTitle(), isPresentAnd(not(isEmptyString())));
            assertThat(item.getDescription(), isPresentAnd(not(isEmptyString())));
            assertThat(item.getPubDate(), isPresentAnd(not(isEmptyString())));
            assertThat(item.getLink(), isPresentAnd(not(isEmptyString())));
        }
    }

    @Test
    public void rssRealtid() throws IOException {
        RssReader reader = new RssReader();
        List<Item> items = reader.read("https://www.realtid.se/rss/senaste").collect(Collectors.toList());

        assertTrue(!items.isEmpty());

        for (Item item : items) {
            // Validate channel
            Channel channel = item.getChannel();
            assertNotNull(channel);
            assertThat(channel.getTitle(), is(""));
            assertThat(channel.getDescription(), is(""));
            assertThat(channel.getLanguage(), isPresentAndIs("sv"));
            assertThat(channel.getLink(), is("https://www.realtid.se/rss/senaste"));
            assertThat(channel.getCopyright(), isEmpty());
            assertThat(channel.getGenerator(), isEmpty());
            assertThat(channel.getLastBuildDate(), isEmpty());

            // Validate item
            assertNotNull(item);
            assertThat(item.getGuid(), isPresentAnd(not(isEmptyString())));
            assertThat(item.getIsPermaLink(), isPresentAndIs(false));
            assertThat(item.getTitle(), isPresentAnd(not(isEmptyString())));
            //assertThat(item.getDescription(), isPresentAnd(not(isEmptyString())));
            assertThat(item.getPubDate(), isPresentAnd(not(isEmptyString())));
            assertThat(item.getLink(), isPresentAnd(not(isEmptyString())));
        }
    }

    @Test
    public void rssAffarsvarlden() throws IOException {
        RssReader reader = new RssReader();
        List<Item> items = reader.read("https://www.affarsvarlden.se/rss.xml").collect(Collectors.toList());

        assertTrue(!items.isEmpty());

        for (Item item : items) {
            // Validate channel
            Channel channel = item.getChannel();
            assertNotNull(channel);
            assertThat(channel.getTitle(), is("Affärsvärlden"));
            assertThat(channel.getDescription(), is("Nyheter från www.affarsvarlden.se"));
            assertThat(channel.getLanguage(), isPresentAndIs("sv"));
            assertThat(channel.getLink(), is("http://www.affarsvarlden.se/"));
            assertThat(channel.getCopyright(), isEmpty());
            assertThat(channel.getGenerator(), isEmpty());
            assertThat(channel.getLastBuildDate(), isEmpty());

            // Validate item
            assertNotNull(item);
            assertThat(item.getGuid(), isPresentAnd(not(isEmptyString())));
            assertThat(item.getIsPermaLink(), isPresentAndIs(false));
            assertThat(item.getTitle(), isPresentAnd(not(isEmptyString())));
            assertThat(item.getDescription(), anyOf(isEmpty(), isPresentAnd(not(isEmptyString()))));
            assertThat(item.getPubDate(), isPresent());
            assertThat(item.getLink(), isPresent());
        }
    }

    @Ignore
    @Test
    public void rssVAFinans() throws IOException {
        RssReader reader = new RssReader();
        List<Item> items = reader.read("https://www.vafinans.se/rss/nyheter").collect(Collectors.toList());

        assertTrue(!items.isEmpty());

        for (Item item : items) {
            // Validate channel
            Channel channel = item.getChannel();
            assertNotNull(channel);
            assertThat(channel.getTitle(), is("vafinans.se"));
            assertThat(channel.getDescription(), is("www.vafinans.se bietet Finanznachrichten zum weltweiten Boersengeschehen"));
            assertThat(channel.getLanguage(), isPresentAndIs("de-ch"));
            assertThat(channel.getLink(), is("https://www.vafinans.se"));
            assertThat(channel.getCopyright(), isPresentAndIs("finanzen.net GmbH"));
            assertThat(channel.getGenerator(), isEmpty());
            assertThat(channel.getLastBuildDate(), isEmpty());

            // Validate item
            assertNotNull(item);
            assertThat(item.getGuid(), isPresentAnd(not(isEmptyString())));
            assertThat(item.getIsPermaLink(), isPresentAndIs(true));
            assertThat(item.getTitle(), isPresent());
            assertThat(item.getDescription(), isPresent());
            assertThat(item.getPubDate(), isPresent());
            assertThat(item.getLink(), isPresent());
        }
    }

    @Test
    public void rssVAFinansBadUrl() throws IOException {
        RssReader reader = new RssReader();
        List<Item> items = reader.read("https://www.vafinans.se/rss/nyheter2").collect(Collectors.toList());

        assertTrue(items.isEmpty());
    }

    @Ignore
    @Test
    public void rssVAFinansAsync() {
        RssReader reader = new RssReader();
        List<Item> items = reader.readAsync("https://www.vafinans.se/rss/nyheter").join().collect(Collectors.toList());

        assertTrue(!items.isEmpty());

        for (Item item : items) {
            // Validate channel
            Channel channel = item.getChannel();
            assertNotNull(channel);
            assertThat(channel.getTitle(), is("vafinans.se"));
            assertThat(channel.getDescription(), is("www.vafinans.se bietet Finanznachrichten zum weltweiten Boersengeschehen"));
            assertThat(channel.getLanguage(), isPresentAndIs("de-ch"));
            assertThat(channel.getLink(), is("https://www.vafinans.se"));
            assertThat(channel.getCopyright(), isPresentAndIs("finanzen.net GmbH"));
            assertThat(channel.getGenerator(), isEmpty());
            assertThat(channel.getLastBuildDate(), isEmpty());

            // Validate item
            assertNotNull(item);
            assertThat(item.getGuid(), isPresentAnd(not(isEmptyString())));
            assertThat(item.getIsPermaLink(), isPresentAndIs(true));
            assertThat(item.getTitle(), isPresent());
            assertThat(item.getDescription(), isPresent());
            assertThat(item.getPubDate(), isPresent());
            assertThat(item.getLink(), isPresent());
        }
    }

    @Test
    public void feedForAll() throws IOException {
        RssReader reader = new RssReader();
        List<Item> items = reader.read("https://feedforall.com/sample-feed.xml").collect(Collectors.toList());

        assertTrue(!items.isEmpty());

        for (Item item : items) {
            // Validate channel
            Channel channel = item.getChannel();
            assertNotNull(channel);
            assertThat(channel.getTitle(), is("FeedForAll Sample Feed"));
            assertThat(channel.getDescription(), is("FeedForAll Sample Feed"));
            assertThat(channel.getLink(), is("http://www.feedforall.com/industry-solutions.htm"));
            assertThat(channel.getCategory(), isPresentAndIs("Computers/Software/Internet/Site Management/Content Management"));
            assertThat(channel.getLanguage(), isPresentAndIs("en-us"));
            assertThat(channel.getCopyright(), isPresentAndIs("Copyright 2004 NotePage, Inc."));
            assertThat(channel.getGenerator(), isPresentAndIs("FeedForAll Beta1 (0.0.1.8)"));
            assertThat(channel.getPubDate(), isPresentAndIs("Tue, 26 Oct 2004 14:06:44 -0500"));
            assertThat(channel.getLastBuildDate(), isPresentAndIs("Mon, 1 Nov 2004 13:17:17 -0500"));

            // Validate item
            assertNotNull(item);
            assertThat(item.getGuid(), isEmpty());
            assertThat(item.getIsPermaLink(), isEmpty());
            assertThat(item.getTitle(), isPresent());
            assertThat(item.getDescription(), isPresent());
            assertThat(item.getPubDate(), isPresent());
            assertThat(item.getLink(), isPresent());
        }
    }

    @Test
    public void rss2sample() throws IOException {
        RssReader reader = new RssReader();
        List<Item> items = reader.read("https://cyber.harvard.edu/rss/examples/rss2sample.xml").collect(Collectors.toList());

        assertTrue(!items.isEmpty());

        for (Item item : items) {
            // Validate channel
            Channel channel = item.getChannel();
            assertNotNull(channel);
            assertThat(channel.getTitle(), is("Liftoff News"));
            assertThat(channel.getDescription(), is("Liftoff to Space Exploration."));
            assertThat(channel.getCategory(), isEmpty());
            assertThat(channel.getLanguage(), isPresentAndIs("en-us"));
            assertThat(channel.getLink(), is("http://liftoff.msfc.nasa.gov/"));
            assertThat(channel.getCopyright(), isEmpty());
            assertThat(channel.getGenerator(), isPresentAndIs("Weblog Editor 2.0"));
            assertThat(channel.getPubDate(), isPresentAndIs("Tue, 10 Jun 2003 04:00:00 GMT"));
            assertThat(channel.getLastBuildDate(), isPresentAndIs("Tue, 10 Jun 2003 09:41:01 GMT"));
            assertThat(channel.getManagingEditor(), isPresentAndIs("editor@example.com"));
            assertThat(channel.getWebMaster(), isPresentAndIs("webmaster@example.com"));

            // Validate item
            assertNotNull(item);
            assertThat(item.getGuid(), isPresentAnd(not(isEmptyString())));
            assertThat(item.getIsPermaLink(), isPresentAndIs(false));
            assertThat(item.getDescription(), isPresentAnd(not(isEmptyString())));
            assertThat(item.getPubDate(), isPresentAnd(not(isEmptyString())));
        }
    }

}
