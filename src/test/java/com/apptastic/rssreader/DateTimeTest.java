package com.apptastic.rssreader;

import org.junit.Test;

import java.time.ZoneId;

import static org.junit.Assert.*;

public class DateTimeTest {

    @Test
    public void dateTimeFormat1() {
        Long timestamp = DateTime.toEpochMilli("Fri, 01 Jun 2018 07:17:52 +0200");
        assertEquals(Long.valueOf(1527830272000L), timestamp);
    }

    @Test
    public void dateTimeFormat2() {
        Long timestamp = DateTime.toEpochMilli("2018-06-01T07:17:52+02:00");
        assertEquals(Long.valueOf(1527830272000L), timestamp);
    }

    @Test
    public void dateTimeFormat3() {
        DateTime.setDefaultZone(ZoneId.of("UTC"));
        Long timestamp = DateTime.toEpochMilli("2018-06-01T07:17:52");
        assertEquals(Long.valueOf(1527837472000L), timestamp);
    }

    @Test
    public void dateTimeFormat4() {
        Long timestamp = DateTime.toEpochMilli("Sat, 30 Nov 2019 08:21:14 GMT");
        assertEquals(Long.valueOf(1575102074000L), timestamp);
    }

    @Test
    public void badInputNull() {
        assertNull(DateTime.toLocalDateTime(null));
        assertNull(DateTime.toZonedDateTime(null));
        assertNull(DateTime.toEpochMilli(null));
    }

    @Test(expected=IllegalArgumentException.class)
    public void badInputZonedDateTime() {
        DateTime.toZonedDateTime("sdflksd");
    }


    @Test(expected=IllegalArgumentException.class)
    public void badInputLocalDateTime() {
        DateTime.toLocalDateTime("sdflksd");
    }
}
