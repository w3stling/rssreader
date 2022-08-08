package com.apptasticsoftware.rssreader;

import org.junit.jupiter.api.Test;

import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;


class DateTimeTest {

    @Test
    void dateTimeFormat1() {
        Long timestamp = DateTime.toEpochMilli("Fri, 01 Jun 2018 07:17:52 +0200");
        assertEquals(Long.valueOf(1527830272000L), timestamp);
    }

    @Test
    void dateTimeFormat2() {
        Long timestamp = DateTime.toEpochMilli("2018-06-01T07:17:52+02:00");
        assertEquals(Long.valueOf(1527830272000L), timestamp);
    }

    @Test
    void dateTimeFormat3() {
        DateTime.setDefaultZone(ZoneId.of("UTC"));
        Long timestamp = DateTime.toEpochMilli("2018-06-01T07:17:52");
        assertEquals(Long.valueOf(1527837472000L), timestamp);
    }

    @Test
    void dateTimeFormat4() {
        Long timestamp = DateTime.toEpochMilli("Sat, 30 Nov 2019 08:21:14 GMT");
        assertEquals(Long.valueOf(1575102074000L), timestamp);
    }

    @Test
    void dateTimeFormat5() {
        Long timestamp = DateTime.toEpochMilli("2021-11-17T13:21:21Z");
        assertEquals(Long.valueOf(1637155281000L), timestamp);
    }

    @Test
    void badInputNull() {
        assertNull(DateTime.toLocalDateTime(null));
        assertNull(DateTime.toZonedDateTime(null));
        assertNull(DateTime.toEpochMilli(null));
    }

    @Test
    void badInputZonedDateTime() {
        assertThrows(IllegalArgumentException.class, () ->
                DateTime.toZonedDateTime("sdflksd"));
    }


    @Test
    void badInputLocalDateTime() {
        assertThrows(IllegalArgumentException.class, () ->
                DateTime.toLocalDateTime("sdflksd"));
    }
}
