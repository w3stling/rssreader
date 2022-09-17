package com.apptasticsoftware.integrationtest;

import com.apptasticsoftware.rssreader.*;
import org.junit.jupiter.api.Test;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.github.npathai.hamcrestopt.OptionalMatchers.*;
import static com.github.npathai.hamcrestopt.OptionalMatchers.isEmpty;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;


class RssReaderIntegrationTest {

    @Test
    void nullHttpClient() {
        assertThrows(NullPointerException.class, () ->
                new RssReader(null));
    }


    @Test
    void rssRiksbanken() throws IOException {
        RssReader reader = new RssReader();
        List<Item> items = reader.read("https://www.riksbank.se/sv/rss/pressmeddelanden").collect(Collectors.toList());

        assertFalse(items.isEmpty());

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
            assertThat(channel.getLastBuildDate(), isPresentAnd(not(emptyString())));

            // Validate item
            assertNotNull(item);
            assertThat(item.getGuid(), isPresentAnd(not(emptyString())));
            assertThat(item.getIsPermaLink(), isPresentAnd(is(false)));
            assertThat(item.getTitle(), isPresentAnd(not(emptyString())));
            assertThat(item.getDescription(), isPresentAnd(not(emptyString())));
            assertThat(item.getPubDate(), isPresentAnd(not(emptyString())));
            assertThat(item.getLink(), isPresentAnd(not(emptyString())));
        }
    }




    @Test
    void rssKonjunkturinstitutet() throws IOException {
        RssReader reader = new RssReader();
        List<Item> items = reader.read("https://www.konj.se/4.2de5c57614f808a95afcc13f/12.2de5c57614f808a95afcc354.portlet?state=rss&sv.contenttype=text/xml;charset=UTF-8").collect(Collectors.toList());

        assertFalse(items.isEmpty());

        for (Item item : items) {
            // Validate channel
            Channel channel = item.getChannel();
            assertNotNull(channel);
            assertThat(channel.getTitle(), is("Publikationer från Konjunkturinstitutet"));
            assertThat(channel.getDescription(), is("Rapportutgåvor publicerade på konj.se"));
            assertThat(channel.getLanguage(), isPresentAndIs("sv"));
            assertThat(channel.getLink(), is("https://www.konj.se/om-ki/aktuellt/publikationer.html"));
            assertThat(channel.getCopyright(), isPresentAndIs("Konjunkturinstitutet"));
            assertThat(channel.getGenerator(), isPresentAnd(startsWith("Sitevision")));
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
    void rssScb() throws IOException {
        RssReader reader = new RssReader();
        List<Item> items = reader.read("https://www.scb.se/Feed/statistiknyheter/").collect(Collectors.toList());

        assertFalse(items.isEmpty());

        for (Item item : items) {
            // Validate channel
            Channel channel = item.getChannel();
            assertNotNull(channel);
            assertThat(channel.getTitle(), is("Statistiska centralbyrån - Statistiknyheter"));
            assertThat(channel.getDescription(), is("Statistiknyheter via RSS"));
            assertThat(channel.getLanguage(), isEmpty());
            assertThat(channel.getLink().toLowerCase(), is("http://www.scb.se/feed/statistiknyheter/"));
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
    void rssPlacera() throws IOException {
        RssReader reader = new RssReader();
        List<Item> items = reader.read("https://www.avanza.se/placera/forstasidan.rss.xml").collect(Collectors.toList());

        assertFalse(items.isEmpty());

        for (Item item : items) {
            // Validate channel
            Channel channel = item.getChannel();
            assertNotNull(channel);
            assertThat(channel.getTitle(), is("Placera.se"));
            assertThat(channel.getDescription(), is(not(emptyString())));
            assertThat(channel.getLink(), is("https://www.placera.se"));
            assertThat(channel.getCopyright(), isEmpty());
            assertThat(channel.getGenerator(), isEmpty());
            assertThat(channel.getLastBuildDate(), isEmpty());

            // Validate item
            assertNotNull(item);
            assertThat(item.getGuid(), isPresentAnd(not(emptyString())));
            assertThat(item.getIsPermaLink(), isPresentAndIs(true));
            assertThat(item.getTitle(), isPresentAnd(not(emptyString())));
            assertThat(item.getDescription(), isPresentAnd(not(emptyString())));
            assertThat(item.getPubDate(), isPresentAnd(not(emptyString())));
            assertThat(item.getLink(), isPresentAnd(not(emptyString())));
        }
    }

    @Test
    void rssPlaceraString() throws IOException, InterruptedException {
        String rssText = getRssFeedAsString("https://www.avanza.se/placera/forstasidan.rss.xml");
        InputStream inputStream = new ByteArrayInputStream(rssText.getBytes(StandardCharsets.UTF_8));

        RssReader reader = new RssReader();
        List<Item> items = reader.read(inputStream).collect(Collectors.toList());

        assertFalse(items.isEmpty());

        for (Item item : items) {
            // Validate channel
            Channel channel = item.getChannel();
            assertNotNull(channel);
            assertThat(channel.getTitle(), is("Placera.se"));
            assertThat(channel.getDescription(), is(not(emptyString())));
            assertThat(channel.getLink(), is("https://www.placera.se"));
            assertThat(channel.getCopyright(), isEmpty());
            assertThat(channel.getGenerator(), isEmpty());
            assertThat(channel.getLastBuildDate(), isEmpty());

            // Validate item
            assertNotNull(item);
            assertThat(item.getGuid(), isPresentAnd(not(emptyString())));
            assertThat(item.getIsPermaLink(), isPresentAndIs(true));
            assertThat(item.getTitle(), isPresentAnd(not(emptyString())));
            assertThat(item.getDescription(), isPresentAnd(not(emptyString())));
            assertThat(item.getPubDate(), isPresentAnd(not(emptyString())));
            assertThat(item.getLink(), isPresentAnd(not(emptyString())));
        }
    }


    @Test
    void rssBreakit() throws IOException {
        RssReader reader = new RssReader();
        List<Item> items = reader.read("https://www.breakit.se/feed/artiklar").collect(Collectors.toList());

        DayOfWeek dayOfWeek = DayOfWeek.of(LocalDate.now().get(ChronoField.DAY_OF_WEEK));
        if (items.isEmpty() && dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            return; // Brakit articles are removed after one day and no articles published on Saturday or Sunday
        }

        assertFalse(items.isEmpty());

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
            assertThat(channel.getLastBuildDate(), isPresentAnd(not(emptyString())));

            // Validate item
            assertNotNull(item);
            assertThat(item.getGuid(), isPresentAnd(not(emptyString())));
            assertThat(item.getIsPermaLink(), isPresentAndIs(true));
            assertThat(item.getTitle(), isPresentAnd(not(emptyString())));
            assertThat(item.getDescription(), isPresentAnd(not(emptyString())));
            assertThat(item.getPubDate(), isPresentAnd(not(emptyString())));
            assertThat(item.getLink(), isPresentAnd(not(emptyString())));
        }
    }

    @Test
    void rssRealtid() throws IOException {
        RssReader reader = new RssReader();
        List<Item> items = reader.read("https://www.realtid.se/rss/senaste").collect(Collectors.toList());

        assertFalse(items.isEmpty());

        for (Item item : items) {
            // Validate channel
            Channel channel = item.getChannel();
            assertNotNull(channel);
            assertThat(channel.getTitle(), is("Realtid"));
            assertThat(channel.getDescription(), is(""));
            assertThat(channel.getLanguage(), isPresentAndIs("sv"));
            assertThat(channel.getLink(), is("https://www.realtid.se/rss/senaste"));
            assertThat(channel.getCopyright(), isEmpty());
            assertThat(channel.getGenerator(), isEmpty());
            assertThat(channel.getLastBuildDate(), isEmpty());

            // Validate item
            assertNotNull(item);
            assertThat(item.getGuid(), isPresentAnd(not(emptyString())));
            assertThat(item.getIsPermaLink(), isPresentAndIs(false));
            assertThat(item.getTitle(), isPresentAnd(not(emptyString())));
            //assertThat(item.getDescription(), isPresentAnd(not(emptyString())));
            assertThat(item.getPubDate(), isPresentAnd(not(emptyString())));
            assertThat(item.getLink(), isPresentAnd(not(emptyString())));
        }
    }

    @Test
    void rssVAFinansBadUrl() {
        RssReader reader = new RssReader();
        assertThrows(IOException.class, () ->
                reader.read("https://www.vafinans.se/rss/nyheter2"));
    }

    @Test
    void feedForAll() throws IOException {
        RssReader reader = new RssReader();
        List<Item> items = reader.read("https://feedforall.com/sample-feed.xml").collect(Collectors.toList());

        assertFalse(items.isEmpty());

        for (Item item : items) {
            // Validate channel
            Channel channel = item.getChannel();
            assertNotNull(channel);
            assertThat(channel.getTitle(), is("Sample Feed - Favorite RSS Related Software & Resources"));
            assertThat(channel.getDescription(), is("Take a look at some of FeedForAll's favorite software and resources for learning more about RSS."));
            assertThat(channel.getLink(), is("http://www.feedforall.com"));
            assertThat(channel.getCategory(), isPresentAndIs("Computers/Software/Internet/Site Management/Content Management"));
            assertThat(channel.getLanguage(), isPresentAndIs("en-us"));
            assertThat(channel.getCopyright(), isPresentAndIs("Copyright 2004 NotePage, Inc."));
            assertThat(channel.getGenerator(), isPresentAndIs("FeedForAll Beta1 (0.0.1.8)"));
            assertThat(channel.getPubDate(), isPresentAndIs("Tue, 26 Oct 2004 14:06:44 -0500"));
            assertThat(channel.getPubDateZonedDateTime(), isPresentAndIs(DateTime.toZonedDateTime("Tue, 26 Oct 2004 14:06:44 -0500")));
            assertThat(channel.getLastBuildDate(), isPresentAndIs("Mon, 1 Nov 2004 13:17:17 -0500"));
            assertThat(channel.getLastBuildDateZonedDateTime(), isPresentAndIs(DateTime.toZonedDateTime("Mon, 1 Nov 2004 13:17:17 -0500")));

            // Validate item
            assertNotNull(item);
            assertThat(item.getGuid(), isEmpty());
            assertThat(item.getIsPermaLink(), isEmpty());
            assertThat(item.getTitle(), isPresent());
            assertThat(item.getDescription(), isPresent());
            assertThat(item.getPubDate(), isPresent());
            assertThat(item.getPubDateZonedDateTime(), isPresent());
            assertThat(item.getLink(), isPresent());
        }
    }

    @Test
    void investingcom() throws IOException {
        RssReader reader = new RssReader();
        List<Item> items = reader.read("https://se.investing.com/rss/news.rss").collect(Collectors.toList());

        assertFalse(items.isEmpty());

        for (Item item : items) {
            // Validate channel
            Channel channel = item.getChannel();
            assertNotNull(channel);
            assertThat(channel.getTitle(), is("Alla nyheter"));
            assertThat(channel.getDescription(), is(""));
            assertThat(channel.getLanguage(), isEmpty());
            assertThat(channel.getLink(), is("https://se.investing.com"));
            assertThat(channel.getCopyright(), isEmpty());
            assertThat(channel.getGenerator(), isEmpty());
            assertThat(channel.getLastBuildDate(), isEmpty());

            // Validate item
            assertNotNull(item);
            assertThat(item.getGuid(), isEmpty());
            assertThat(item.getIsPermaLink(), isEmpty());
            assertThat(item.getTitle(), isPresentAnd(not(emptyString())));
            assertThat(item.getDescription(), anyOf(isEmpty(), isPresentAnd(not(emptyString()))));
            assertThat(item.getPubDate(), isPresent());
            assertThat(item.getLink(), isPresent());
        }
    }

    @Test
    void investingcom_mest_lasta() throws IOException {
        RssReader reader = new RssReader();
        List<Item> items = reader.read("https://se.investing.com/rss/news_285.rss").collect(Collectors.toList());

        assertFalse(items.isEmpty());

        for (Item item : items) {
            // Validate channel
            Channel channel = item.getChannel();
            assertNotNull(channel);
            assertThat(channel.getTitle(), is("Mest lästa nyheter"));
            assertThat(channel.getDescription(), is(""));
            assertThat(channel.getLanguage(), isEmpty());
            assertThat(channel.getLink(), is("https://se.investing.com"));
            assertThat(channel.getCopyright(), isEmpty());
            assertThat(channel.getGenerator(), isEmpty());
            assertThat(channel.getLastBuildDate(), isEmpty());

            // Validate item
            assertNotNull(item);
            assertThat(item.getGuid(), isEmpty());
            assertThat(item.getIsPermaLink(), isEmpty());
            assertThat(item.getTitle(), isPresentAnd(not(emptyString())));
            assertThat(item.getDescription(), anyOf(isEmpty(), isPresentAnd(not(emptyString()))));
            assertThat(item.getPubDate(), isPresent());
            assertThat(item.getLink(), isPresent());
            if(item.getEnclosure().isPresent()) {
                assertNotNull(item.getEnclosure().get().getUrl());
                assertNotNull(item.getEnclosure().get().getType());
            }
        }
    }


    @Test
    void diDigital() throws IOException {
        RssReader reader = new RssReader();
        List<Item> items = reader.read("https://digital.di.se/rss").collect(Collectors.toList());

        assertFalse(items.isEmpty());

        for (Item item : items) {
            // Validate channel
            Channel channel = item.getChannel();
            assertNotNull(channel);
            assertThat(channel.getTitle(), is("Di Digital - Senaste nytt"));
            assertThat(channel.getDescription(), is(""));
            assertThat(channel.getLanguage(), isEmpty());
            assertThat(channel.getLink(), is("https://digital.di.se/rss"));
            assertThat(channel.getCopyright(), isEmpty());
            assertThat(channel.getGenerator(), isEmpty());
            assertThat(channel.getLastBuildDate(), isEmpty());

            // Validate item
            assertNotNull(item);
            assertThat(item.getGuid(), isPresentAnd(not(emptyString())));
            assertThat(item.getIsPermaLink(), isPresentAnd(is(false)));
            assertThat(item.getTitle(), isPresentAnd(not(emptyString())));
            assertThat(item.getDescription(), anyOf(isEmpty(), isPresentAnd(not(emptyString()))));
            assertThat(item.getPubDate(), isPresent());
            assertThat(item.getLink(), isPresent());
        }
    }


    @Test
    void rssWorldOfTank() throws IOException {
        RssReader reader = new RssReader();
        List<Item> items = reader.read("https://worldoftanks.eu/en/rss/news/").collect(Collectors.toList());
        assertFalse(items.isEmpty());

        for (Item item : items) {
            // Validate channel
            Channel channel = item.getChannel();
            assertNotNull(channel);
            assertThat(channel.getTitle(), is("World of Tanks news — free tank game, official WoT website | World of Tanks"));
            assertThat(channel.getDescription(), is("World of Tanks news — read the latest news on the free MMO World of Tanks game, the best game for boys"));
            assertThat(channel.getLanguage(), isPresentAndIs("en"));
            assertThat(channel.getLink(), is("https://worldoftanks.eu/en/news/"));
            assertThat(channel.getCopyright(), isEmpty());
            assertThat(channel.getGenerator(), isEmpty());
            assertThat(channel.getLastBuildDate(), isEmpty());
            assertThat(channel.getImage(), isPresent());
            assertThat(channel.getImage().map(Image::getTitle).orElse(null), is("World of Tanks news — free tank game, official WoT website | World of Tanks"));
            assertThat(channel.getImage().map(Image::getLink).orElse(null), is("https://worldoftanks.eu/en/news/"));
            assertThat(channel.getImage().map(Image::getUrl), isPresentAnd(not(emptyString())));
            assertThat(channel.getImage().get().getDescription(), isEmpty());
            assertThat(channel.getImage().get().getWidth(), isEmpty());
            assertThat(channel.getImage().get().getHeight(), isEmpty());

            // Validate item
            assertNotNull(item);
            assertThat(item.getGuid(), isPresentAnd(not(emptyString())));
            assertThat(item.getIsPermaLink(), isPresentAnd(is(true)));
            assertThat(item.getTitle(), isPresentAnd(not(emptyString())));
            assertThat(item.getDescription(), isPresentAnd(not(emptyString())));
            assertThat(item.getPubDate(), isPresentAnd(not(emptyString())));
            assertThat(item.getLink(), isPresentAnd(not(emptyString())));
        }
    }

    @Test
    void zonedDateTime() throws IOException {
        RssReader reader = new RssReader();
        List<Item> items = reader.read("https://www.breakit.se/feed/artiklar").collect(Collectors.toList());

        assertFalse(items.isEmpty());

        ZonedDateTime dateTime = items.stream()
                                      .sorted()
                                      .findFirst()
                                      .flatMap(Item::getPubDate)
                                      .map(DateTime::toZonedDateTime)
                                      .orElse(null);
        assertNotNull(dateTime);
    }

    @Test
    void dateTime() throws IOException {
        RssReader reader = new RssReader();
        List<Item> items = reader.read("https://www.breakit.se/feed/artiklar").collect(Collectors.toList());

        assertFalse(items.isEmpty());

        Optional<ZonedDateTime> dateTime = items.stream()
                                                .findFirst()
                                                .flatMap(Item::getPubDate)
                                                .map(DateTime::toZonedDateTime);

        assertThat(dateTime, isPresent());
    }


    @Test
    void httpClient() throws IOException, KeyManagementException, NoSuchAlgorithmException {
        SSLContext context = SSLContext.getInstance("TLSv1.3");
        context.init(null, null, null);

        HttpClient httpClient = HttpClient.newBuilder()
                .sslContext(context)
                .connectTimeout(Duration.ofSeconds(15))
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();

        RssReader reader = new RssReader(httpClient);
        List<Item> items = reader.read("https://www.breakit.se/feed/artiklar").collect(Collectors.toList());

        assertFalse(items.isEmpty());

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
            assertThat(channel.getLastBuildDate(), isPresent());

            // Validate item
            assertNotNull(item);
            assertThat(item.getGuid(), isPresentAnd(not(emptyString())));
            assertThat(item.getIsPermaLink(), isPresentAnd(is(true)));
            assertThat(item.getTitle(), isPresentAnd(not(emptyString())));
            assertThat(item.getDescription(), anyOf(isEmpty(), isPresentAnd(not(emptyString()))));
            assertThat(item.getPubDate(), isPresent());
            assertThat(item.getLink(), isPresent());
        }
    }

    private String getRssFeedAsString(String url) throws IOException, InterruptedException {
        HttpRequest req = HttpRequest.newBuilder(URI.create(url))
                .timeout(Duration.ofSeconds(25))
                .GET()
                .build();

        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();

        HttpResponse<String> response = client.send(req, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    @Test
    void testItemExtension() throws IOException {
        List<Item> items = new RssReader().addItemExtension("dc:creator", Item::setAuthor)
                                          .addItemExtension("dc:date", Item::setPubDate)
                                          .read("https://lwn.net/headlines/rss")
                                          .collect(Collectors.toList());

        for (Item item : items) {
            assertThat(item.getAuthor(), isPresentAnd(not(emptyString())));
            assertThat(item.getPubDate(), isPresentAnd(not(emptyString())));
        }
    }

    @Test
    void testChannelExtension() throws IOException {
        List<Item> items = new RssReader().addChannelExtension("syn:updatePeriod", Channel::setCategory)
                                          .read("https://lwn.net/headlines/rss")
                                          .collect(Collectors.toList());

        for (Item item : items) {
            assertThat(item.getChannel().getCategory(), isPresentAnd(not(emptyString())));
        }
    }

    @Test
    void testUserAgent() throws IOException {
        List<Item> items = new RssReader().setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X x.y; rv:42.0) Gecko/20100101")
                                          .read("https://lwn.net/headlines/rss")
                                          .collect(Collectors.toList());

        for (Item item : items) {
            assertThat(item.getChannel().getTitle(), is("LWN.net"));
        }
    }

    @Test
    void testHttpHeader() throws IOException {
        List<Item> items = new RssReader().addHeader("If-None-Match", "response_version1")
                                          .read("https://lwn.net/headlines/rss")
                                          .collect(Collectors.toList());

        for (Item item : items) {
            assertThat(item.getChannel().getTitle(), is("LWN.net"));
        }
    }
}
