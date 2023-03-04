package com.apptasticsoftware.rssreader;

import org.junit.jupiter.api.Test;

import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;


class DateTimeTest {

    @Test
    void dateTimeFormat1() {
        var timestamp = DateTime.toEpochMilli("Fri, 01 Jun 2018 07:17:52 +0200");
        assertEquals(1527830272000L, timestamp);

        timestamp = DateTime.toEpochMilli("Fri, 1 Jun 2018 07:17:52 +0200");
        assertEquals(1527830272000L, timestamp);

        timestamp = DateTime.toEpochMilli("Wed, 02 Oct 2002 15:00:00 +0200");
        assertEquals(1033563600000L, timestamp);

        timestamp = DateTime.toEpochMilli("Wed, 2 Oct 2002 15:00:00 +0200");
        assertEquals(1033563600000L, timestamp);

        timestamp = DateTime.toEpochMilli("Thu, 02 Jun 22 07:46:24 +0000");
        assertEquals(1654155984000L, timestamp);

        timestamp = DateTime.toEpochMilli("Thu, 2 Jun 22 07:46:24 +0000");
        assertEquals(1654155984000L, timestamp);
    }

    @Test
    void dateTimeFormat2() {
        var timestamp = DateTime.toEpochMilli("2018-06-01T07:17:52+02:00");
        assertEquals(1527830272000L, timestamp);
    }

    @Test
    void dateTimeFormat3() {
        DateTime.setDefaultZone(ZoneId.of("UTC"));
        var timestamp = DateTime.toEpochMilli("2018-06-01T07:17:52");
        assertEquals(1527837472000L, timestamp);
    }

    @Test
    void dateTimeFormat4() {
        var timestamp = DateTime.toEpochMilli("Sat, 30 Nov 2019 08:21:14 GMT");
        assertEquals(1575102074000L, timestamp);

        timestamp = DateTime.toEpochMilli("Wed, 02 Oct 2002 13:00:00 GMT");
        assertEquals(1033563600000L, timestamp);

        timestamp = DateTime.toEpochMilli("Wed, 9 Nov 2022 23:25:57 GMT");
        assertEquals(1668036357000L, timestamp);

        timestamp = DateTime.toEpochMilli("Wed, 15 Jan 2020 10:13:32 GMT+6");
        assertEquals(1579061612000L, timestamp);

        timestamp = DateTime.toEpochMilli("Wed, 15 Jan 2020 10:13:32 GMT+06:00");
        assertEquals(1579061612000L, timestamp);

        timestamp = DateTime.toEpochMilli("Tue, 01 Mar 2022 16:03:00 GMT+0000");
        assertEquals(1646150580000L, timestamp);
    }

    @Test
    void dateTimeFormat5() {
        var timestamp = DateTime.toEpochMilli("2021-11-17T13:21:21Z");
        assertEquals(1637155281000L, timestamp);
    }

    @Test
    void dateTimeFormat6() {
        var timestamp = DateTime.toEpochMilli("Sun, 04 Sep 2022 09:42:16");
        assertEquals(1662284536000L, timestamp);

        timestamp = DateTime.toEpochMilli("Sun, 4 Sep 2022 09:42:16");
        assertEquals(1662284536000L, timestamp);
    }

    @Test
    void dateTimeFormat7() {
        //https://datatracker.ietf.org/doc/html/rfc4287#section-3.3
        var timestamp = DateTime.toEpochMilli("2003-12-13T18:30:02Z");
        assertEquals(1071340202000L, timestamp);

        timestamp = DateTime.toEpochMilli("2003-12-13T18:30:02.25Z");
        assertEquals(1071340202250L, timestamp);

        timestamp = DateTime.toEpochMilli("2003-12-13T18:30:02+01:00");
        assertEquals(1071336602000L, timestamp);

        timestamp = DateTime.toEpochMilli("2003-12-13T18:30:02.25+01:00");
        assertEquals(1071336602250L, timestamp);
    }

    @Test
    void dateTimeFormat8() {
        var timestamp = DateTime.toEpochMilli("2022-10-20 02:10:12");
        assertEquals(1666231812000L, timestamp);
    }

    @Test
    void dateTimeFormat9() {
        var timestamp = DateTime.toEpochMilli("Fri, 04 Nov 2022 23:00:18 Z");
        assertEquals(1667602818000L, timestamp);

        timestamp = DateTime.toEpochMilli("Fri, 4 Nov 2022 23:00:18 Z");
        assertEquals(1667602818000L, timestamp);
    }

    @Test
    void dateTimeFormat10() {
        var timestamp = DateTime.toEpochMilli("Mon, 07 Nov 2022 06:56:00 UTC");
        assertEquals(1667804160000L, timestamp);

        timestamp = DateTime.toEpochMilli("Mon, 7 Nov 2022 06:56:00 UTC");
        assertEquals(1667804160000L, timestamp);
    }

    @Test
    void dateTimeFormat11() {
        // Eastern time
        var timestamp = DateTime.toEpochMilli("Wed, 09 Nov 2022 00:21:54 EDT");
        assertEquals(1667967714000L, timestamp);

        timestamp = DateTime.toEpochMilli("Wed, 9 Nov 2022 00:21:54 EDT");
        assertEquals(1667967714000L, timestamp);

        timestamp = DateTime.toEpochMilli("Wed, 09 Nov 2022 00:21:54 EST");
        assertEquals(1667971314000L, timestamp);

        timestamp = DateTime.toEpochMilli("Wed, 9 Nov 2022 00:21:54 EST");
        assertEquals(1667971314000L, timestamp);


        // Central time
        timestamp = DateTime.toEpochMilli("Wed, 09 Nov 2022 00:21:54 CDT");
        assertEquals(1667971314000L, timestamp);

        timestamp = DateTime.toEpochMilli("Wed, 9 Nov 2022 00:21:54 CDT");
        assertEquals(1667971314000L, timestamp);

        timestamp = DateTime.toEpochMilli("Wed, 09 Nov 2022 00:21:54 CST");
        assertEquals(1667974914000L, timestamp);

        timestamp = DateTime.toEpochMilli("Wed, 9 Nov 2022 00:21:54 CST");
        assertEquals(1667974914000L, timestamp);


        // Mountain time
        timestamp = DateTime.toEpochMilli("Wed, 09 Nov 2022 00:21:54 MDT");
        assertEquals(1667974914000L, timestamp);

        timestamp = DateTime.toEpochMilli("Wed, 9 Nov 2022 00:21:54 MDT");
        assertEquals(1667974914000L, timestamp);

        timestamp = DateTime.toEpochMilli("Wed, 09 Nov 2022 00:21:54 MST");
        assertEquals(1667978514000L, timestamp);

        timestamp = DateTime.toEpochMilli("Wed, 9 Nov 2022 00:21:54 MST");
        assertEquals(1667978514000L, timestamp);


        // Pacific time
        timestamp = DateTime.toEpochMilli("Wed, 09 Nov 2022 00:21:54 PDT");
        assertEquals(1667978514000L, timestamp);

        timestamp = DateTime.toEpochMilli("Wed, 9 Nov 2022 00:21:54 PDT");
        assertEquals(1667978514000L, timestamp);

        timestamp = DateTime.toEpochMilli("Wed, 09 Nov 2022 00:21:54 PST");
        assertEquals(1667982114000L, timestamp);

        timestamp = DateTime.toEpochMilli("Wed, 9 Nov 2022 00:21:54 PST");
        assertEquals(1667982114000L, timestamp);
    }

    @Test
    void dateTimeFormat12() {
        assertEquals(1423026000000L, DateTime.toEpochMilli("Wednesday, 04 Feb 2015 00:00:00 EST"));
        // Eastern time
        var timestamp = DateTime.toEpochMilli("Wednesday, 09 Nov 2022 00:21:54 EDT");
        assertEquals(1667967714000L, timestamp);

        timestamp = DateTime.toEpochMilli("Wednesday, 9 Nov 2022 00:21:54 EDT");
        assertEquals(1667967714000L, timestamp);

        timestamp = DateTime.toEpochMilli("Wednesday, 09 Nov 2022 00:21:54 EST");
        assertEquals(1667971314000L, timestamp);

        timestamp = DateTime.toEpochMilli("Wednesday, 9 Nov 2022 00:21:54 EST");
        assertEquals(1667971314000L, timestamp);


        // Central time
        timestamp = DateTime.toEpochMilli("Wednesday, 09 Nov 2022 00:21:54 CDT");
        assertEquals(1667971314000L, timestamp);

        timestamp = DateTime.toEpochMilli("Wednesday, 9 Nov 2022 00:21:54 CDT");
        assertEquals(1667971314000L, timestamp);

        timestamp = DateTime.toEpochMilli("Wednesday, 09 Nov 2022 00:21:54 CST");
        assertEquals(1667974914000L, timestamp);

        timestamp = DateTime.toEpochMilli("Wednesday, 9 Nov 2022 00:21:54 CST");
        assertEquals(1667974914000L, timestamp);


        // Mountain time
        timestamp = DateTime.toEpochMilli("Wednesday, 09 Nov 2022 00:21:54 MDT");
        assertEquals(1667974914000L, timestamp);

        timestamp = DateTime.toEpochMilli("Wednesday, 9 Nov 2022 00:21:54 MDT");
        assertEquals(1667974914000L, timestamp);

        timestamp = DateTime.toEpochMilli("Wednesday, 09 Nov 2022 00:21:54 MST");
        assertEquals(1667978514000L, timestamp);

        timestamp = DateTime.toEpochMilli("Wednesday, 9 Nov 2022 00:21:54 MST");
        assertEquals(1667978514000L, timestamp);


        // Pacific time
        timestamp = DateTime.toEpochMilli("Wednesday, 09 Nov 2022 00:21:54 PDT");
        assertEquals(1667978514000L, timestamp);

        timestamp = DateTime.toEpochMilli("Wednesday, 9 Nov 2022 00:21:54 PDT");
        assertEquals(1667978514000L, timestamp);

        timestamp = DateTime.toEpochMilli("Wednesday, 09 Nov 2022 00:21:54 PST");
        assertEquals(1667982114000L, timestamp);

        timestamp = DateTime.toEpochMilli("Wednesday, 9 Nov 2022 00:21:54 PST");
        assertEquals(1667982114000L, timestamp);
    }

    @Test
    void dateTimeFormat13() {
        var timestamp = DateTime.toEpochMilli("Wed, 02 Oct 2002 13:00:00 CET");
        assertEquals(1033556400000L, timestamp);

        timestamp = DateTime.toEpochMilli("Wed, 2 Oct 2002 13:00:00 CET");
        assertEquals(1033556400000L, timestamp);
    }

    @Test
    void dateTimeFormat14() {
        var timestamp = DateTime.toEpochMilli("Fri, 03 Mar 2023 13:13:53-5:30");
        assertEquals(1677869033000L, timestamp);

        timestamp = DateTime.toEpochMilli("Fri, 03 Mar 2023 13:13:53+5:30");
        assertEquals(1677829433000L, timestamp);

        timestamp = DateTime.toEpochMilli("Fri, 03 Mar 2023 13:13:53-10:30");
        assertEquals(1677887033000L, timestamp);

        timestamp = DateTime.toEpochMilli("Fri, 03 Mar 2023 13:13:53+10:30");
        assertEquals(1677811433000L, timestamp);
    }

    @Test
    void testWrongDayOfWeek() {
        assertEquals(1423026000000L, DateTime.toEpochMilli("Monday, 04 Feb 2015 00:00:00 EST"));
        // Eastern time
        var timestamp = DateTime.toEpochMilli("Monday, 09 Nov 2022 00:21:54 EDT");
        assertEquals(1667967714000L, timestamp);

        timestamp = DateTime.toEpochMilli("Monday, 9 Nov 2022 00:21:54 EDT");
        assertEquals(1667967714000L, timestamp);

        timestamp = DateTime.toEpochMilli("Monday, 09 Nov 2022 00:21:54 EST");
        assertEquals(1667971314000L, timestamp);

        timestamp = DateTime.toEpochMilli("Monday, 9 Nov 2022 00:21:54 EST");
        assertEquals(1667971314000L, timestamp);


        // Central time
        timestamp = DateTime.toEpochMilli("Monday, 09 Nov 2022 00:21:54 CDT");
        assertEquals(1667971314000L, timestamp);

        timestamp = DateTime.toEpochMilli("Monday, 9 Nov 2022 00:21:54 CDT");
        assertEquals(1667971314000L, timestamp);

        timestamp = DateTime.toEpochMilli("Monday, 09 Nov 2022 00:21:54 CST");
        assertEquals(1667974914000L, timestamp);

        timestamp = DateTime.toEpochMilli("Monday, 9 Nov 2022 00:21:54 CST");
        assertEquals(1667974914000L, timestamp);


        // Mountain time
        timestamp = DateTime.toEpochMilli("Monday, 09 Nov 2022 00:21:54 MDT");
        assertEquals(1667974914000L, timestamp);

        timestamp = DateTime.toEpochMilli("Monday, 9 Nov 2022 00:21:54 MDT");
        assertEquals(1667974914000L, timestamp);

        timestamp = DateTime.toEpochMilli("Monday, 09 Nov 2022 00:21:54 MST");
        assertEquals(1667978514000L, timestamp);

        timestamp = DateTime.toEpochMilli("Monday, 9 Nov 2022 00:21:54 MST");
        assertEquals(1667978514000L, timestamp);


        // Pacific time
        timestamp = DateTime.toEpochMilli("Monday, 09 Nov 2022 00:21:54 PDT");
        assertEquals(1667978514000L, timestamp);

        timestamp = DateTime.toEpochMilli("Monday, 9 Nov 2022 00:21:54 PDT");
        assertEquals(1667978514000L, timestamp);

        timestamp = DateTime.toEpochMilli("Monday, 09 Nov 2022 00:21:54 PST");
        assertEquals(1667982114000L, timestamp);

        timestamp = DateTime.toEpochMilli("Monday, 9 Nov 2022 00:21:54 PST");
        assertEquals(1667982114000L, timestamp);

        timestamp = DateTime.toEpochMilli("Tue, 23 May 2019 02:00:00 -0700");
        assertEquals(1558602000000L, timestamp);
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
