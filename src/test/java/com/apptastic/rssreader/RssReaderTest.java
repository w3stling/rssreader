package com.apptastic.rssreader;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

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

        InputStream responseStream = new ByteArrayInputStream(response.getBytes());
        RssReader readerMock = spy(RssReader.class);
        doReturn(responseStream).when(readerMock).sendRequest(anyString());

        readerMock.read("").forEach(i -> System.out.println(i.getTitle()));
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

        InputStream responseStream = new ByteArrayInputStream(response.getBytes());
        RssReader readerMock = spy(RssReader.class);
        doReturn(responseStream).when(readerMock).sendRequest(anyString());

        List<Item> items = readerMock.read("").collect(Collectors.toList());

        assertEquals(1, items.size());

        Item item = items.get(0);

        assertNotNull(item);
        assertEquals("Title item 1", item.getTitle());
        assertEquals("Description item 1.", item.getDescription());
        assertEquals("Wed, 23 May 2018 09:30:20 +0200", item.getPubDate());
        assertEquals("https://www.dummy.com/item1", item.getLink());
        assertEquals("https://www.dummy.com/item1", item.getGuid());
        assertFalse(item.getIsPermaLink());

        Channel channel = item.getChannel();

        assertNotNull(channel);
        assertEquals("Title", channel.getTitle());
        assertEquals("Description", channel.getDescription());
        assertEquals("sv", channel.getLanguage());
        assertEquals("Fri, 01 Jun 2018 07:17:52 +0200", channel.getLastBuildDate());
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

        InputStream responseStream = new ByteArrayInputStream(response.getBytes());
        RssReader readerMock = spy(RssReader.class);
        doReturn(responseStream).when(readerMock).sendRequest(anyString());

        List<Item> items = readerMock.read("").collect(Collectors.toList());

        assertEquals(1, items.size());

        Item item = items.get(0);

        assertNotNull(item);
        assertEquals("Title item 1", item.getTitle());
        assertEquals("Description item 1.", item.getDescription());
        assertEquals("Wed, 23 May 2018 09:30:20 +0200", item.getPubDate());
        assertEquals("https://www.dummy.com/item1", item.getLink());
        assertEquals("https://www.dummy.com/item1", item.getGuid());
        assertFalse(item.getIsPermaLink());

        Channel channel = item.getChannel();

        assertNotNull(channel);
        assertEquals("Title", channel.getTitle());
        assertEquals("Description", channel.getDescription());
        assertEquals("sv", channel.getLanguage());
        assertEquals("Fri, 01 Jun 2018 07:17:52 +0200", channel.getLastBuildDate());
    }

    @Test
    public void emptyResponse() throws IOException {
        String response = "";

        InputStream responseStream = new ByteArrayInputStream(response.getBytes());
        RssReader readerMock = spy(RssReader.class);
        doReturn(responseStream).when(readerMock).sendRequest(anyString());

        readerMock.read("").forEach(i -> System.out.println(i.getTitle()));
    }


    @Test
    public void failToResetStream() {
        try {
            String response = "";
            InputStream responseStream = spy(new BufferedInputStream(new ByteArrayInputStream(response.getBytes())));

            doThrow(new IOException("Dummy reset")).when(responseStream).reset();
            RssReader readerMock = spy(RssReader.class);
            doReturn(responseStream).when(readerMock).sendRequest(anyString());

            readerMock.read("");
            fail();
        }
        catch (IOException e) {
            assertEquals("Dummy reset", e.getMessage());
        }
    }

    @Test
    public void failToCloseStream() throws IOException {
        String response = "";
        InputStream responseStream = spy(new ByteArrayInputStream(response.getBytes()));

        doThrow(new IOException("Dummy close")).when(responseStream).close();
        RssReader readerMock = spy(RssReader.class);
        doReturn(responseStream).when(readerMock).sendRequest(anyString());

        readerMock.read("").forEach(i -> System.out.println(i.getTitle()));
    }
}
