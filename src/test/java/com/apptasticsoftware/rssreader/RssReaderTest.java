package com.apptasticsoftware.rssreader;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpHeaders;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static com.github.npathai.hamcrestopt.OptionalMatchers.isPresentAndIs;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@SuppressWarnings("java:S5738")
class RssReaderTest {

    @Test
    void badFormattedXml() throws IOException {
        String response = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<rss xmlns:a10=\"http://www.w3.org/2005/Atom\" version=\"2.0\">\n" +
                "<channel xml:base=\"channel\">\n" +
                "<title>Title</title>\n" +
                "<description>Description</description>\n" +
                "<language>sv</language>\n" +
                "<lastBuildDate>Fri, 01 Jun 2018 07:17:52 +0200</lastBuildDate>\n" +
                "<ttl>10</ttl>\n" +
                "<link>https://www.dummy.com/</link>\n" +
                "<item xml:base=\"https://www.dummy.com/item1\">\n" +
                "<guid isPermaLink=\"false\">https://www.dummy.com/item1</guid>\n" +
                "<title>Title item 1</titl>\n" + // <----- Bad format
                "<description>Description item 1.</description>\n" +
                "<pubDate>Wed, 23 May 2018 09:30:20 +0200</pubDate>\n" +
                "<a10:updated>2018-05-23T09:30:20+02:00</a10:updated>\n" +
                "<link>https://www.dummy.com/item1</link>\n" +
                "</item>\n" +
                "</channel>\n" +
                "</rss>\n";

        var httpResponse = createMock(response);
        RssReader readerMock = Mockito.spy(RssReader.class);
        doReturn(httpResponse).when(readerMock).sendAsyncRequest(anyString());

        long count = readerMock.read("").count();
        assertEquals(0, count);
    }

    @SuppressWarnings("squid:S5976")
    @Test
    void leadingCRCharacter() throws IOException {
        String response = "\015<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<rss xmlns:a10=\"http://www.w3.org/2005/Atom\" version=\"2.0\">\n" +
                "<channel xml:base=\"channel\">\n" +
                "<title>Title</title>\n" +
                "<description>Description</description>\n" +
                "<language>sv</language>\n" +
                "<lastBuildDate>Fri, 01 Jun 2018 07:17:52 +0200</lastBuildDate>\n" +
                "<ttl>10</ttl>\n" +
                "<link>https://www.dummy.com/</link>\n" +
                "<item xml:base=\"https://www.dummy.com/item1\">\n" +
                "<guid isPermaLink=\"false\">https://www.dummy.com/item1</guid>\n" +
                "<title>Title item 1</title>\n" +
                "<description>Description item 1.</description>\n" +
                "<pubDate>Wed, 23 May 2018 09:30:20 +0200</pubDate>\n" +
                "<a10:updated>2018-05-23T09:30:20+02:00</a10:updated>\n" +
                "<link>https://www.dummy.com/item1</link>\n" +
                "</item>\n" +
                "</channel>\n" +
                "</rss>\n";

        var httpResponse = createMock(response);
        RssReader readerMock = Mockito.spy(RssReader.class);
        doReturn(httpResponse).when(readerMock).sendAsyncRequest(anyString());

        List<Item> items = readerMock.read("").collect(Collectors.toList());

        assertEquals(1, items.size());

        Item item = items.get(0);
        assertNotNull(item);
        assertThat(item.getTitle(), isPresentAndIs("Title item 1"));
        assertThat(item.getDescription(), isPresentAndIs("Description item 1."));
        assertThat(item.getPubDate(), isPresentAndIs("Wed, 23 May 2018 09:30:20 +0200"));
        assertThat(item.getLink(), isPresentAndIs("https://www.dummy.com/item1"));
        assertThat(item.getGuid(), isPresentAndIs("https://www.dummy.com/item1"));
        assertThat(item.getIsPermaLink(), isPresentAndIs(false));

        Channel channel = item.getChannel();
        assertNotNull(channel);
        assertThat(channel.getTitle(), is("Title"));
        assertThat(channel.getDescription(), is("Description"));
        assertThat(channel.getLanguage(), isPresentAndIs("sv"));
        assertThat(channel.getLastBuildDate(), isPresentAndIs("Fri, 01 Jun 2018 07:17:52 +0200"));
    }

    @Test
    void leadingCRLDCharacters() throws IOException {
        String response = "\015\012<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<rss xmlns:a10=\"http://www.w3.org/2005/Atom\" version=\"2.0\">\n" +
                "<channel xml:base=\"channel\">\n" +
                "<title>Title</title>\n" +
                "<description>Description</description>\n" +
                "<language>sv</language>\n" +
                "<lastBuildDate>Fri, 01 Jun 2018 07:17:52 +0200</lastBuildDate>\n" +
                "<ttl>10</ttl>\n" +
                "<link>https://www.dummy.com/</link>\n" +
                "<item xml:base=\"https://www.dummy.com/item1\">\n" +
                "<guid isPermaLink=\"false\">https://www.dummy.com/item1</guid>\n" +
                "<title>Title item 1</title>\n" +
                "<description>Description item 1.</description>\n" +
                "<pubDate>Wed, 23 May 2018 09:30:20 +0200</pubDate>\n" +
                "<a10:updated>2018-05-23T09:30:20+02:00</a10:updated>\n" +
                "<link>https://www.dummy.com/item1</link>\n" +
                "</item>\n" +
                "</channel>\n" +
                "</rss>\n";

        var httpResponse = createMock(response);
        RssReader readerMock = Mockito.spy(RssReader.class);
        doReturn(httpResponse).when(readerMock).sendAsyncRequest(anyString());

        List<Item> items = readerMock.read("").collect(Collectors.toList());

        assertEquals(1, items.size());

        Item item = items.get(0);
        assertNotNull(item);
        assertThat(item.getTitle(), isPresentAndIs("Title item 1"));
        assertThat(item.getDescription(), isPresentAndIs("Description item 1."));
        assertThat(item.getPubDate(), isPresentAndIs("Wed, 23 May 2018 09:30:20 +0200"));
        assertThat(item.getLink(), isPresentAndIs("https://www.dummy.com/item1"));
        assertThat(item.getGuid(), isPresentAndIs("https://www.dummy.com/item1"));
        assertThat(item.getIsPermaLink(), isPresentAndIs(false));

        Channel channel = item.getChannel();
        assertNotNull(channel);
        assertThat(channel.getTitle(), is("Title"));
        assertThat(channel.getDescription(), is("Description"));
        assertThat(channel.getLanguage(), isPresentAndIs("sv"));
        assertThat(channel.getLastBuildDate(), isPresentAndIs("Fri, 01 Jun 2018 07:17:52 +0200"));
    }

    @Test
    void leadingWhitespace() throws IOException {
        String response = "  <?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<rss xmlns:a10=\"http://www.w3.org/2005/Atom\" version=\"2.0\">\n" +
                "<channel xml:base=\"channel\">\n" +
                "<title>Title</title>\n" +
                "<description>Description</description>\n" +
                "<language>sv</language>\n" +
                "<lastBuildDate>Fri, 01 Jun 2018 07:17:52 +0200</lastBuildDate>\n" +
                "<ttl>10</ttl>\n" +
                "<link>https://www.dummy.com/</link>\n" +
                "<item xml:base=\"https://www.dummy.com/item1\">\n" +
                "<guid isPermaLink=\"false\">https://www.dummy.com/item1</guid>\n" +
                "<title>Title item 1</title>\n" +
                "<description>Description item 1.</description>\n" +
                "<pubDate>Wed, 23 May 2018 09:30:20 +0200</pubDate>\n" +
                "<a10:updated>2018-05-23T09:30:20+02:00</a10:updated>\n" +
                "<link>https://www.dummy.com/item1</link>\n" +
                "</item>\n" +
                "</channel>\n" +
                "</rss>\n";

        var httpResponse = createMock(response);
        RssReader readerMock = Mockito.spy(RssReader.class);
        doReturn(httpResponse).when(readerMock).sendAsyncRequest(anyString());

        List<Item> items = readerMock.read("").collect(Collectors.toList());

        assertEquals(1, items.size());

        Item item = items.get(0);
        assertNotNull(item);
        assertThat(item.getTitle(), isPresentAndIs("Title item 1"));
        assertThat(item.getDescription(), isPresentAndIs("Description item 1."));
        assertThat(item.getPubDate(), isPresentAndIs("Wed, 23 May 2018 09:30:20 +0200"));
        assertThat(item.getLink(), isPresentAndIs("https://www.dummy.com/item1"));
        assertThat(item.getGuid(), isPresentAndIs("https://www.dummy.com/item1"));
        assertThat(item.getIsPermaLink(), isPresentAndIs(false));

        Channel channel = item.getChannel();
        assertNotNull(channel);
        assertThat(channel.getTitle(), is("Title"));
        assertThat(channel.getDescription(), is("Description"));
        assertThat(channel.getLanguage(), isPresentAndIs("sv"));
        assertThat(channel.getLastBuildDate(), isPresentAndIs("Fri, 01 Jun 2018 07:17:52 +0200"));
    }

    @Test
    void Cdata() throws IOException {
        String response = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<rss xmlns:a10=\"http://www.w3.org/2005/Atom\" version=\"2.0\">\n" +
                "<channel xml:base=\"channel\">\n" +
                "<title>Title</title>\n" +
                "<description><![CDATA[<p class=\"bodytext\">Description.</p>]]></description>\n" +
                "<language>sv</language>\n" +
                "<lastBuildDate>Fri, 01 Jun 2018 07:17:52 +0200</lastBuildDate>\n" +
                "<ttl>10</ttl>\n" +
                "<link>https://www.dummy.com/</link>\n" +
                "<item xml:base=\"https://www.dummy.com/item1\">\n" +
                "<guid isPermaLink=\"false\">https://www.dummy.com/item1</guid>\n" +
                "<title>Title item 1</title>\n" +
                "<description>Description item 1.</description>\n" +
                "<pubDate>Wed, 23 May 2018 09:30:20 +0200</pubDate>\n" +
                "<a10:updated>2018-05-23T09:30:20+02:00</a10:updated>\n" +
                "<link>https://www.dummy.com/item1</link>\n" +
                "</item>\n" +
                "</channel>\n" +
                "</rss>\n";

        var httpResponse = createMock(response);
        RssReader readerMock = Mockito.spy(RssReader.class);
        doReturn(httpResponse).when(readerMock).sendAsyncRequest(anyString());

        List<Item> items = readerMock.read("").collect(Collectors.toList());

        assertEquals(1, items.size());

        Item item = items.get(0);
        assertNotNull(item);
        assertThat(item.getTitle(), isPresentAndIs("Title item 1"));
        assertThat(item.getDescription(), isPresentAndIs("Description item 1."));
        assertThat(item.getPubDate(), isPresentAndIs("Wed, 23 May 2018 09:30:20 +0200"));
        assertThat(item.getLink(), isPresentAndIs("https://www.dummy.com/item1"));
        assertThat(item.getGuid(), isPresentAndIs("https://www.dummy.com/item1"));
        assertThat(item.getIsPermaLink(), isPresentAndIs(false));

        Channel channel = item.getChannel();
        assertNotNull(channel);
        assertThat(channel.getTitle(), is("Title"));
        assertThat(channel.getDescription(), is("<p class=\"bodytext\">Description.</p>"));
        assertThat(channel.getLanguage(), isPresentAndIs("sv"));
        assertThat(channel.getLastBuildDate(), isPresentAndIs("Fri, 01 Jun 2018 07:17:52 +0200"));
    }

    @Test
    void emptyResponse() throws IOException {
        String response = "";

        var httpResponse = createMock(response);
        RssReader readerMock = Mockito.spy(RssReader.class);
        doReturn(httpResponse).when(readerMock).sendAsyncRequest(anyString());

        long count = readerMock.read("").count();
        assertEquals(0, count);
    }

    @Test
    void itemHashCodeTest() {
        Item item1 = new Item();
        item1.setAuthor("a1");
        item1.setCategory("a2");
        item1.setTitle("a3");
        item1.setDescription("a4");
        item1.setGuid("a5");
        item1.setIsPermaLink(false);
        item1.setLink("a6");
        item1.setPubDate("a7");
        int h1 = item1.hashCode();

        Item item2 = new Item(new DateTime());
        item2.setAuthor("b1");
        item2.setCategory("a2");
        item2.setTitle("a3");
        item2.setDescription("a4");
        item2.setGuid("a5");
        item2.setIsPermaLink(false);
        item2.setLink("a6");
        item2.setPubDate("a7");
        int h2 = item2.hashCode();

        assertNotEquals(h1, h2);
    }

    @Test
    void itemEqualsTest() {
        Item item1 = new Item(new DateTime());
        item1.setAuthor("a1");
        item1.setCategory("a2");
        item1.setTitle("a3");
        item1.setDescription("a4");
        item1.setGuid("a5");
        item1.setIsPermaLink(false);
        item1.setLink("a6");
        item1.setPubDate("a7");

        Item item2 = new Item(new DateTime());
        item2.setAuthor("b1");
        item2.setCategory("a2");
        item2.setTitle("a3");
        item2.setDescription("a4");
        item2.setGuid("a5");
        item2.setIsPermaLink(false);
        item2.setLink("a6");
        item2.setPubDate("a7");

        Item item3 = new Item(new DateTime());
        item3.setAuthor("a1");
        item3.setCategory("a2");
        item3.setTitle("a3");
        item3.setDescription("a4");
        item3.setGuid("a5");
        item3.setIsPermaLink(false);
        item3.setLink("a6");
        item3.setPubDate("a7");

        assertNotEquals(item1, item2);
        assertEquals(item1, item3);
    }


    @Test
    void channelHashCodeTest() {
        Channel channel1 = new Channel();
        channel1.setTitle("a1");
        channel1.setDescription("a2");
        channel1.setCategory("a3");
        channel1.setLanguage("a4");
        channel1.setLink("a5");
        channel1.setCopyright("a6");
        channel1.setGenerator("a7");
        channel1.setTtl("a8");
        channel1.setPubDate("20220922T10:11:12");
        channel1.setLastBuildDate("20220922T10:11:13");
        channel1.setManagingEditor("a9");
        channel1.setWebMaster("a10");
        channel1.setRating("a11");
        int h1 = channel1.hashCode();

        Channel channel2 = new Channel(new DateTime());
        channel2.setTitle("b1");
        channel2.setDescription("a2");
        channel2.setCategory("a3");
        channel2.setLanguage("a4");
        channel2.setLink("a5");
        channel2.setCopyright("a6");
        channel2.setGenerator("a7");
        channel2.setTitle("a8");
        channel2.setPubDate("20220922T10:11:12");
        channel2.setLastBuildDate("20220922T10:11:13");
        channel2.setManagingEditor("a9");
        channel2.setWebMaster("a10");
        channel2.setRating("a11");
        int h2 = channel2.hashCode();

        assertNotEquals(h1, h2);
    }


    @Test
    void channelEqualsTest() {
        Channel channel1 = new Channel(new DateTime());
        channel1.setTitle("a1");
        channel1.setDescription("a2");
        channel1.setCategory("a3");
        channel1.setLanguage("a4");
        channel1.setLink("a5");
        channel1.setCopyright("a6");
        channel1.setGenerator("a7");
        channel1.setTtl("a8");
        channel1.setPubDate("20220922T10:11:12");
        channel1.setLastBuildDate("20220922T10:11:13");
        channel1.setManagingEditor("a9");
        channel1.setWebMaster("a10");
        channel1.setRating("a11");

        Channel channel2 = new Channel(new DateTime());
        channel2.setTitle("b1");
        channel2.setDescription("a2");
        channel2.setCategory("a3");
        channel2.setLanguage("a4");
        channel2.setLink("a5");
        channel2.setCopyright("a6");
        channel2.setGenerator("a7");
        channel2.setTitle("a8");
        channel2.setPubDate("20220922T10:11:12");
        channel2.setLastBuildDate("20220922T10:11:13");
        channel2.setManagingEditor("a9");
        channel2.setWebMaster("a10");
        channel2.setRating("a11");

        Channel channel3 = new Channel(new DateTime());
        channel3.setTitle("a1");
        channel3.setDescription("a2");
        channel3.setCategory("a3");
        channel3.setLanguage("a4");
        channel3.setLink("a5");
        channel3.setCopyright("a6");
        channel3.setGenerator("a7");
        channel3.setTtl("a8");
        channel3.setPubDate("20220922T10:11:12");
        channel3.setLastBuildDate("20220922T10:11:13");
        channel3.setManagingEditor("a9");
        channel3.setWebMaster("a10");
        channel3.setRating("a11");

        assertNotEquals(channel1, channel2);
        assertEquals(channel1, channel3);
    }


    @Test
    void imageHashCodeTest() {
        Image image1 = new Image();
        image1.setTitle("a1");
        image1.setLink("a2");
        image1.setUrl("a3");
        image1.setDescription("a4");
        image1.setHeight(1);
        image1.setWidth(2);
        int h1 = image1.hashCode();

        Image image2 = new Image();
        image2.setTitle("b1");
        image2.setLink("a2");
        image2.setUrl("a3");
        image2.setDescription("a4");
        image2.setHeight(1);
        image2.setWidth(2);
        int h2 = image2.hashCode();

        assertNotEquals(h1, h2);
    }


    @Test
    void imageEqualsTest() {
        Image image1 = new Image();
        image1.setTitle("a1");
        image1.setLink("a2");
        image1.setUrl("a3");
        image1.setDescription("a4");
        image1.setHeight(1);
        image1.setWidth(2);

        Image image2 = new Image();
        image2.setTitle("b1");
        image2.setLink("a2");
        image2.setUrl("a3");
        image2.setDescription("a4");
        image2.setHeight(1);
        image2.setWidth(2);

        Image image3 = new Image();
        image3.setTitle("a1");
        image3.setLink("a2");
        image3.setUrl("a3");
        image3.setDescription("a4");
        image3.setHeight(1);
        image3.setWidth(2);

        assertNotEquals(image1, image2);
        assertEquals(image1, image3);
    }


    @Test
    void enclosureHashCodeTest() {
        Enclosure enclosure1 = new Enclosure();
        enclosure1.setUrl("a1");
        enclosure1.setType("a2");
        enclosure1.setLength(1L);
        int h1 = enclosure1.hashCode();

        Enclosure enclosure2 = new Enclosure();
        enclosure2.setUrl("b1");
        enclosure2.setType("a2");
        enclosure2.setLength(1L);
        int h2 = enclosure2.hashCode();

        assertNotEquals(h1, h2);
    }


    @Test
    void enclosureEqualsTest() {
        Enclosure enclosure1 = new Enclosure();
        enclosure1.setUrl("a1");
        enclosure1.setType("a2");
        enclosure1.setLength(1L);

        Enclosure enclosure2 = new Enclosure();
        enclosure2.setUrl("b1");
        enclosure2.setType("a2");
        enclosure2.setLength(1L);

        Enclosure enclosure3 = new Enclosure();
        enclosure3.setUrl("a1");
        enclosure3.setType("a2");
        enclosure3.setLength(1L);

        assertNotEquals(enclosure1, enclosure2);
        assertEquals(enclosure1, enclosure3);
    }


    @Test
    void equalsContract() {
        EqualsVerifier.simple().forClass(Channel.class).withIgnoredFields("dateTimeParser").withIgnoredFields("category").withIgnoredFields("syUpdatePeriod").withIgnoredFields("syUpdateFrequency").withNonnullFields("categories").verify();
        EqualsVerifier.simple().forClass(Item.class).withIgnoredFields("defaultComparator").withIgnoredFields("dateTimeParser").withIgnoredFields("category").withNonnullFields("categories").withIgnoredFields("enclosure").withNonnullFields("enclosures").verify();
        EqualsVerifier.simple().forClass(Enclosure.class).verify();
        EqualsVerifier.simple().forClass(Image.class).verify();
    }


    private CompletableFuture createMock(String response) {
        var httpResponse = mock(HttpResponse.class);
        InputStream responseStream = new ByteArrayInputStream(response.getBytes());
        doReturn(responseStream).when(httpResponse).body();

        HttpHeaders httpHeaders = HttpHeaders.of(new HashMap<>(), (a, b) -> true);
        doReturn(httpHeaders).when(httpResponse).headers();

        return CompletableFuture.completedFuture(httpResponse);
    }
}
