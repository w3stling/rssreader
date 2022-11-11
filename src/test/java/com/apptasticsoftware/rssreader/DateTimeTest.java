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

        timestamp = DateTime.toEpochMilli("Wed, 02 Oct 2002 13:00:00 GMT");
        assertEquals(Long.valueOf(1033563600000L), timestamp);

        timestamp = DateTime.toEpochMilli("Wed, 02 Oct 2002 15:00:00 +0200");
        assertEquals(Long.valueOf(1033563600000L), timestamp);
    }

    @Test
    void dateTimeFormat5() {
        Long timestamp = DateTime.toEpochMilli("2021-11-17T13:21:21Z");
        assertEquals(Long.valueOf(1637155281000L), timestamp);
    }

    @Test
    void dateTimeFormat6() {
        Long timestamp = DateTime.toEpochMilli("Sun, 04 Sep 2022 09:42:16");
        assertEquals(Long.valueOf(1662284536000L), timestamp);

        timestamp = DateTime.toEpochMilli("Sun, 4 Sep 2022 09:42:16");
        assertEquals(Long.valueOf(1662284536000L), timestamp);
    }

    @Test
    void dateTimeFormat7() {
        //https://datatracker.ietf.org/doc/html/rfc4287#section-3.3
        Long timestamp = DateTime.toEpochMilli("2003-12-13T18:30:02Z");
        assertEquals(Long.valueOf(1071340202000L), timestamp);

        timestamp = DateTime.toEpochMilli("2003-12-13T18:30:02.25Z");
        assertEquals(Long.valueOf(1071340202250L), timestamp);

        timestamp = DateTime.toEpochMilli("2003-12-13T18:30:02+01:00");
        assertEquals(Long.valueOf(1071336602000L), timestamp);

        timestamp = DateTime.toEpochMilli("2003-12-13T18:30:02.25+01:00");
        assertEquals(Long.valueOf(1071336602250L), timestamp);
    }

    @Test
    void dateTimeFormat8() {
        var timestamp = DateTime.toEpochMilli("2022-10-20 02:10:12");
        assertEquals(Long.valueOf(1666231812000L), timestamp);
    }

    @Test
    void dateTimeFormat9() {
        var timestamp = DateTime.toEpochMilli("Fri, 04 Nov 2022 23:00:18 Z");
        assertEquals(Long.valueOf(1667602818000L), timestamp);
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
