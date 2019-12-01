package com.apptastic.rssreader;

import org.junit.Test;

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
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import static org.junit.Assert.*;


public class RssReaderTest {

    @Test
    public void badFormattedXml() throws IOException {
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

        CompletableFuture<HttpResponse<InputStream>> httpResponse = createMock(response);
        RssReader readerMock = spy(RssReader.class);
        doReturn(httpResponse).when(readerMock).sendAsyncRequest(anyString());

        long count = readerMock.read("").count();
        assertEquals(0, count);
    }

    @Test
    public void leadingCRCharacter() throws IOException {
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

        CompletableFuture<HttpResponse<InputStream>> httpResponse = createMock(response);
        RssReader readerMock = spy(RssReader.class);
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
    public void leadingCRLDCharacters() throws IOException {
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

        CompletableFuture<HttpResponse<InputStream>> httpResponse = createMock(response);
        RssReader readerMock = spy(RssReader.class);
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
    public void emptyResponse() throws IOException {
        String response = "";

        CompletableFuture<HttpResponse<InputStream>> httpResponse = createMock(response);
        RssReader readerMock = spy(RssReader.class);
        doReturn(httpResponse).when(readerMock).sendAsyncRequest(anyString());

        long count = readerMock.read("").count();
        assertEquals(0, count);
    }

    @Test
    public void hashCodeTest() {
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

        Item item2 = new Item();
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
    public void equalsTest() {
        Item item1 = new Item();
        item1.setAuthor("a1");
        item1.setCategory("a2");
        item1.setTitle("a3");
        item1.setDescription("a4");
        item1.setGuid("a5");
        item1.setIsPermaLink(false);
        item1.setLink("a6");
        item1.setPubDate("a7");

        Item item2 = new Item();
        item2.setAuthor("b1");
        item2.setCategory("a2");
        item2.setTitle("a3");
        item2.setDescription("a4");
        item2.setGuid("a5");
        item2.setIsPermaLink(false);
        item2.setLink("a6");
        item2.setPubDate("a7");

        Item item3 = new Item();
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


    private CompletableFuture<HttpResponse<InputStream>> createMock(String response) {
        HttpResponse httpResponse = mock(HttpResponse.class);

        InputStream responseStream = new ByteArrayInputStream(response.getBytes());
        doReturn(responseStream).when(httpResponse).body();

        HttpHeaders httpHeaders = HttpHeaders.of(new HashMap<>(), (a, b) -> true);
        doReturn(httpHeaders).when(httpResponse).headers();

        return CompletableFuture.completedFuture(httpResponse);
    }
}
