package com.apptasticsoftware.rssreader;

import org.junit.jupiter.api.Test;

import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;


@SuppressWarnings("java:S5976")
class DateTimeTest {
    final DateTime dateTime = new DateTime();

    @Test
    void dateTimeFormat1() {
        var timestamp = dateTime.toEpochMilli("Fri, 01 Jun 2018 07:17:52 +0200");
        assertEquals(1527830272000L, timestamp);

        timestamp = dateTime.toEpochMilli("Fri, 1 Jun 2018 07:17:52 +0200");
        assertEquals(1527830272000L, timestamp);

        timestamp = dateTime.toEpochMilli("Wed, 02 Oct 2002 15:00:00 +0200");
        assertEquals(1033563600000L, timestamp);

        timestamp = dateTime.toEpochMilli("Wed, 2 Oct 2002 15:00:00 +0200");
        assertEquals(1033563600000L, timestamp);

        timestamp = dateTime.toEpochMilli("Thu, 02 Jun 22 07:46:24 +0000");
        assertEquals(1654155984000L, timestamp);

        timestamp = dateTime.toEpochMilli("Thu, 2 Jun 22 07:46:24 +0000");
        assertEquals(1654155984000L, timestamp);
    }

    @Test
    void dateTimeFormat2() {
        var timestamp = dateTime.toEpochMilli("2018-06-01T07:17:52+02:00");
        assertEquals(1527830272000L, timestamp);
    }

    @Test
    void dateTimeFormat3() {
        var timestamp = dateTime.toEpochMilli("Saturday, 26 August 2023 21:00:00 +07:00");
        assertEquals(1693058400000L, timestamp);

        timestamp = dateTime.toEpochMilli("Saturday, 26 Aug 2023 21:00:00 +07:00");
        assertEquals(1693058400000L, timestamp);

        timestamp = dateTime.toEpochMilli("Saturday, 26 August 2023 21:00:00 +0700");
        assertEquals(1693058400000L, timestamp);

        timestamp = dateTime.toEpochMilli("Saturday, 26 Aug 2023 21:00:00 +0700");
        assertEquals(1693058400000L, timestamp);

        timestamp = dateTime.toEpochMilli("Sat, 26 Aug 2023 21:00:00 +07:00");
        assertEquals(1693058400000L, timestamp);


        timestamp = dateTime.toEpochMilli("Saturday, 26 August 2023 21:00:00 -07:00");
        assertEquals(1693108800000L, timestamp);

        timestamp = dateTime.toEpochMilli("Saturday, 26 Aug 2023 21:00:00 -07:00");
        assertEquals(1693108800000L, timestamp);

        timestamp = dateTime.toEpochMilli("Saturday, 26 August 2023 21:00:00 -0700");
        assertEquals(1693108800000L, timestamp);

        timestamp = dateTime.toEpochMilli("Saturday, 26 Aug 2023 21:00:00 -0700");
        assertEquals(1693108800000L, timestamp);

        timestamp = dateTime.toEpochMilli("Sat, 26 Aug 2023 21:00:00 -07:00");
        assertEquals(1693108800000L, timestamp);
    }

    @Test
    void dateTimeFormat4() {
        var timestamp = dateTime.toEpochMilli("Sat, 30 Nov 2019 08:21:14 GMT");
        assertEquals(1575102074000L, timestamp);

        timestamp = dateTime.toEpochMilli("Wed, 02 Oct 2002 13:00:00 GMT");
        assertEquals(1033563600000L, timestamp);

        timestamp = dateTime.toEpochMilli("Wed, 9 Nov 2022 23:25:57 GMT");
        assertEquals(1668036357000L, timestamp);

        timestamp = dateTime.toEpochMilli("Wed, 15 Jan 2020 10:13:32 GMT+6");
        assertEquals(1579061612000L, timestamp);

        timestamp = dateTime.toEpochMilli("Wed, 15 Jan 2020 10:13:32 GMT+06:00");
        assertEquals(1579061612000L, timestamp);

        timestamp = dateTime.toEpochMilli("Tue, 01 Mar 2022 16:03:00 GMT+0000");
        assertEquals(1646150580000L, timestamp);
    }

    @Test
    void dateTimeFormat5() {
        var timestamp = dateTime.toEpochMilli("2021-11-17T13:21:21Z");
        assertEquals(1637155281000L, timestamp);
    }

    @Test
    void dateTimeFormat6() {
        var timestamp = dateTime.toEpochMilli("Sun, 04 Sep 2022 09:42:16");
        assertEquals(1662284536000L, timestamp);

        timestamp = dateTime.toEpochMilli("Sun, 4 Sep 2022 09:42:16");
        assertEquals(1662284536000L, timestamp);

        timestamp = dateTime.toEpochMilli("Sat, 2 Aug 2025 7:15:12");
        assertEquals(1754118912000L, timestamp);
    }

    @Test
    void dateTimeFormat7() {
        //https://datatracker.ietf.org/doc/html/rfc4287#section-3.3
        var timestamp = dateTime.toEpochMilli("2003-12-13T18:30:02Z");
        assertEquals(1071340202000L, timestamp);

        timestamp = dateTime.toEpochMilli("2003-12-13T18:30:02.25Z");
        assertEquals(1071340202250L, timestamp);

        timestamp = dateTime.toEpochMilli("2003-12-13T18:30:02+01:00");
        assertEquals(1071336602000L, timestamp);

        timestamp = dateTime.toEpochMilli("2003-12-13T18:30:02.25+01:00");
        assertEquals(1071336602250L, timestamp);
    }

    @Test
    void dateTimeFormat8() {
        var timestamp = dateTime.toEpochMilli("2022-10-20 02:10:12");
        assertEquals(1666231812000L, timestamp);
    }

    @Test
    void dateTimeFormat9() {
        var timestamp = dateTime.toEpochMilli("Fri, 04 Nov 2022 23:00:18 Z");
        assertEquals(1667602818000L, timestamp);

        timestamp = dateTime.toEpochMilli("Fri, 4 Nov 2022 23:00:18 Z");
        assertEquals(1667602818000L, timestamp);
    }

    @Test
    void dateTimeFormat10() {
        var timestamp = dateTime.toEpochMilli("Mon, 07 Nov 2022 06:56:00 UTC");
        assertEquals(1667804160000L, timestamp);

        timestamp = dateTime.toEpochMilli("Mon, 7 Nov 2022 06:56:00 UTC");
        assertEquals(1667804160000L, timestamp);
    }

    @Test
    void dateTimeFormat11() {
        // Eastern time
        var timestamp = dateTime.toEpochMilli("Wed, 09 Nov 2022 00:21:54 EDT");
        assertEquals(1667967714000L, timestamp);

        timestamp = dateTime.toEpochMilli("Wed, 9 Nov 2022 00:21:54 EDT");
        assertEquals(1667967714000L, timestamp);

        timestamp = dateTime.toEpochMilli("Wed, 09 Nov 2022 00:21:54 EST");
        assertEquals(1667971314000L, timestamp);

        timestamp = dateTime.toEpochMilli("Wed, 9 Nov 2022 00:21:54 EST");
        assertEquals(1667971314000L, timestamp);


        // Central time
        timestamp = dateTime.toEpochMilli("Wed, 09 Nov 2022 00:21:54 CDT");
        assertEquals(1667971314000L, timestamp);

        timestamp = dateTime.toEpochMilli("Wed, 9 Nov 2022 00:21:54 CDT");
        assertEquals(1667971314000L, timestamp);

        timestamp = dateTime.toEpochMilli("Wed, 09 Nov 2022 00:21:54 CST");
        assertEquals(1667974914000L, timestamp);

        timestamp = dateTime.toEpochMilli("Wed, 9 Nov 2022 00:21:54 CST");
        assertEquals(1667974914000L, timestamp);


        // Mountain time
        timestamp = dateTime.toEpochMilli("Wed, 09 Nov 2022 00:21:54 MDT");
        assertEquals(1667974914000L, timestamp);

        timestamp = dateTime.toEpochMilli("Wed, 9 Nov 2022 00:21:54 MDT");
        assertEquals(1667974914000L, timestamp);

        timestamp = dateTime.toEpochMilli("Sat, 26 Aug 2023 7:15:12 MDT"); // new
        assertEquals(1693055712000L, timestamp);

        timestamp = dateTime.toEpochMilli("Sat, 26 Aug 2023 7:5:2 MDT"); // new
        assertEquals(1693055102000L, timestamp);


        timestamp = dateTime.toEpochMilli("Wed, 09 Nov 2022 00:21:54 MST");
        assertEquals(1667978514000L, timestamp);

        timestamp = dateTime.toEpochMilli("Wed, 9 Nov 2022 00:21:54 MST");
        assertEquals(1667978514000L, timestamp);


        // Pacific time
        timestamp = dateTime.toEpochMilli("Wed, 09 Nov 2022 00:21:54 PDT");
        assertEquals(1667978514000L, timestamp);

        timestamp = dateTime.toEpochMilli("Wed, 9 Nov 2022 00:21:54 PDT");
        assertEquals(1667978514000L, timestamp);

        timestamp = dateTime.toEpochMilli("Wed, 09 Nov 2022 00:21:54 PST");
        assertEquals(1667982114000L, timestamp);

        timestamp = dateTime.toEpochMilli("Wed, 9 Nov 2022 00:21:54 PST");
        assertEquals(1667982114000L, timestamp);
    }

    @Test
    void dateTimeFormat12() {
        assertEquals(1423026000000L, dateTime.toEpochMilli("Wednesday, 04 Feb 2015 00:00:00 EST"));
        // Eastern time
        var timestamp = dateTime.toEpochMilli("Wednesday, 09 Nov 2022 00:21:54 EDT");
        assertEquals(1667967714000L, timestamp);

        timestamp = dateTime.toEpochMilli("Wednesday, 9 Nov 2022 00:21:54 EDT");
        assertEquals(1667967714000L, timestamp);

        timestamp = dateTime.toEpochMilli("Wednesday, 09 Nov 2022 00:21:54 EST");
        assertEquals(1667971314000L, timestamp);

        timestamp = dateTime.toEpochMilli("Wednesday, 9 Nov 2022 00:21:54 EST");
        assertEquals(1667971314000L, timestamp);


        // Central time
        timestamp = dateTime.toEpochMilli("Wednesday, 09 Nov 2022 00:21:54 CDT");
        assertEquals(1667971314000L, timestamp);

        timestamp = dateTime.toEpochMilli("Wednesday, 9 Nov 2022 00:21:54 CDT");
        assertEquals(1667971314000L, timestamp);

        timestamp = dateTime.toEpochMilli("Wednesday, 09 Nov 2022 00:21:54 CST");
        assertEquals(1667974914000L, timestamp);

        timestamp = dateTime.toEpochMilli("Wednesday, 9 Nov 2022 00:21:54 CST");
        assertEquals(1667974914000L, timestamp);


        // Mountain time
        timestamp = dateTime.toEpochMilli("Wednesday, 09 Nov 2022 00:21:54 MDT");
        assertEquals(1667974914000L, timestamp);

        timestamp = dateTime.toEpochMilli("Wednesday, 9 Nov 2022 00:21:54 MDT");
        assertEquals(1667974914000L, timestamp);

        timestamp = dateTime.toEpochMilli("Wednesday, 09 Nov 2022 00:21:54 MST");
        assertEquals(1667978514000L, timestamp);

        timestamp = dateTime.toEpochMilli("Wednesday, 9 Nov 2022 00:21:54 MST");
        assertEquals(1667978514000L, timestamp);


        // Pacific time
        timestamp = dateTime.toEpochMilli("Wednesday, 09 Nov 2022 00:21:54 PDT");
        assertEquals(1667978514000L, timestamp);

        timestamp = dateTime.toEpochMilli("Wednesday, 9 Nov 2022 00:21:54 PDT");
        assertEquals(1667978514000L, timestamp);

        timestamp = dateTime.toEpochMilli("Wednesday, 09 Nov 2022 00:21:54 PST");
        assertEquals(1667982114000L, timestamp);

        timestamp = dateTime.toEpochMilli("Wednesday, 9 Nov 2022 00:21:54 PST");
        assertEquals(1667982114000L, timestamp);
    }

    @Test
    void dateTimeFormat13() {
        var timestamp = dateTime.toEpochMilli("Wed, 02 Oct 2002 13:00:00 CET");
        assertEquals(1033556400000L, timestamp);

        timestamp = dateTime.toEpochMilli("Wed, 2 Oct 2002 13:00:00 CET");
        assertEquals(1033556400000L, timestamp);
    }

    @Test
    void dateTimeFormat14() {
        var timestamp = dateTime.toEpochMilli("Fri, 03 Mar 2023 13:13:53-5:30");
        assertEquals(1677869033000L, timestamp);

        timestamp = dateTime.toEpochMilli("Fri, 03 Mar 2023 13:13:53+5:30");
        assertEquals(1677829433000L, timestamp);

        timestamp = dateTime.toEpochMilli("Fri, 3 Mar 2023 13:13:53-5:30");
        assertEquals(1677869033000L, timestamp);

        timestamp = dateTime.toEpochMilli("Fri, 3 Mar 2023 13:13:53+5:30");
        assertEquals(1677829433000L, timestamp);

        timestamp = dateTime.toEpochMilli("Fri, 03 Mar 2023 13:13:53-10:30");
        assertEquals(1677887033000L, timestamp);

        timestamp = dateTime.toEpochMilli("Fri, 03 Mar 2023 13:13:53+10:30");
        assertEquals(1677811433000L, timestamp);

        timestamp = dateTime.toEpochMilli("Fri, 3 Mar 2023 13:13:53-10:30");
        assertEquals(1677887033000L, timestamp);

        timestamp = dateTime.toEpochMilli("Fri, 3 Mar 2023 13:13:53+10:30");
        assertEquals(1677811433000L, timestamp);

        timestamp = dateTime.toEpochMilli("Fri, 03 Mar 2023 13:13:53-05:30");
        assertEquals(1677869033000L, timestamp);

        timestamp = dateTime.toEpochMilli("Fri, 03 Mar 2023 13:13:53+05:30");
        assertEquals(1677829433000L, timestamp);

        timestamp = dateTime.toEpochMilli("Fri, 3 Mar 2023 13:13:53-05:30");
        assertEquals(1677869033000L, timestamp);

        timestamp = dateTime.toEpochMilli("Fri, 3 Mar 2023 13:13:53+05:30");
        assertEquals(1677829433000L, timestamp);
    }

    @Test
    void dateTimeFormat15() {
        var timestamp = dateTime.toEpochMilli("2023-02-28T17:37:08.823050123+00:00");
        assertEquals(1677605828823L, timestamp);

        timestamp = dateTime.toEpochMilli("2023-02-28T17:37:08.823050+00:00");
        assertEquals(1677605828823L, timestamp);

        timestamp = dateTime.toEpochMilli("2023-02-28T17:37:08.823+00:00");
        assertEquals(1677605828823L, timestamp);
    }

    @Test
    void dateTimeFormat16() {
        var timestamp = dateTime.toEpochMilli("2023-08-07T10:06:05-0400");
        assertEquals(1691417165000L, timestamp);

        timestamp = dateTime.toEpochMilli("2023-08-07T10:06:05+0400");
        assertEquals(1691388365000L, timestamp);
    }

    @Test
    void dateTimeFormat17() {
        var timestamp = dateTime.toEpochMilli("1 Dec 2024 09:15:08 +0000");
        assertEquals(1733044508000L, timestamp);

        timestamp = dateTime.toEpochMilli("01 Dec 2024 09:15:08 +0000");
        assertEquals(1733044508000L, timestamp);
    }

    @Test
    void testWrongDayOfWeek() {
        assertEquals(1423026000000L, dateTime.toEpochMilli("Monday, 04 Feb 2015 00:00:00 EST"));
        // Eastern time
        var timestamp = dateTime.toEpochMilli("Monday, 09 Nov 2022 00:21:54 EDT");
        assertEquals(1667967714000L, timestamp);

        timestamp = dateTime.toEpochMilli("Monday, 9 Nov 2022 00:21:54 EDT");
        assertEquals(1667967714000L, timestamp);

        timestamp = dateTime.toEpochMilli("Monday, 09 Nov 2022 00:21:54 EST");
        assertEquals(1667971314000L, timestamp);

        timestamp = dateTime.toEpochMilli("Monday, 9 Nov 2022 00:21:54 EST");
        assertEquals(1667971314000L, timestamp);


        // Central time
        timestamp = dateTime.toEpochMilli("Monday, 09 Nov 2022 00:21:54 CDT");
        assertEquals(1667971314000L, timestamp);

        timestamp = dateTime.toEpochMilli("Monday, 9 Nov 2022 00:21:54 CDT");
        assertEquals(1667971314000L, timestamp);

        timestamp = dateTime.toEpochMilli("Monday, 09 Nov 2022 00:21:54 CST");
        assertEquals(1667974914000L, timestamp);

        timestamp = dateTime.toEpochMilli("Monday, 9 Nov 2022 00:21:54 CST");
        assertEquals(1667974914000L, timestamp);


        // Mountain time
        timestamp = dateTime.toEpochMilli("Monday, 09 Nov 2022 00:21:54 MDT");
        assertEquals(1667974914000L, timestamp);

        timestamp = dateTime.toEpochMilli("Monday, 9 Nov 2022 00:21:54 MDT");
        assertEquals(1667974914000L, timestamp);

        timestamp = dateTime.toEpochMilli("Monday, 09 Nov 2022 00:21:54 MST");
        assertEquals(1667978514000L, timestamp);

        timestamp = dateTime.toEpochMilli("Monday, 9 Nov 2022 00:21:54 MST");
        assertEquals(1667978514000L, timestamp);


        // Pacific time
        timestamp = dateTime.toEpochMilli("Monday, 09 Nov 2022 00:21:54 PDT");
        assertEquals(1667978514000L, timestamp);

        timestamp = dateTime.toEpochMilli("Monday, 9 Nov 2022 00:21:54 PDT");
        assertEquals(1667978514000L, timestamp);

        timestamp = dateTime.toEpochMilli("Monday, 09 Nov 2022 00:21:54 PST");
        assertEquals(1667982114000L, timestamp);

        timestamp = dateTime.toEpochMilli("Monday, 9 Nov 2022 00:21:54 PST");
        assertEquals(1667982114000L, timestamp);

        timestamp = dateTime.toEpochMilli("Tue, 23 May 2019 02:00:00 -0700");
        assertEquals(1558602000000L, timestamp);
    }

    @Test
    void dateOnly() {
        var timestamp = dateTime.toEpochMilli("2023-03-10");
        assertEquals(1678406400000L, timestamp);

        timestamp = dateTime.toEpochMilli("20230310");
        assertEquals(1678406400000L, timestamp);
    }

    @Test
    void timestampWithNoTimezone() {
        var dateTime = new DateTime();
        var timestamp = dateTime.toEpochMilli("2018-06-01T07:17:52");
        assertEquals(1527837472000L, timestamp);

        dateTime = new DateTime(ZoneId.of("Australia/Sydney"));
        timestamp = dateTime.toEpochMilli("2018-06-01T07:17:52");
        assertEquals(1527801472000L, timestamp);

        dateTime = new DateTime(ZoneId.of("America/Chicago"));
        timestamp = dateTime.toEpochMilli("2018-06-01T07:17:52");
        assertEquals(1527855472000L, timestamp);

        dateTime = new DateTime(ZoneId.of("Europe/Paris"));
        timestamp = dateTime.toEpochMilli("2018-06-01T07:17:52");
        assertEquals(1527830272000L, timestamp);

        timestamp = dateTime.toEpochMilli("2000-12-17T01:17");
        assertEquals(977015820000L, timestamp);
    }

    @Test
    void badInputNull() {
        assertNull(dateTime.toLocalDateTime(null));
        assertNull(dateTime.toZonedDateTime(null));
        assertNull(dateTime.toEpochMilli(null));
    }

    @Test
    void badInputZonedDateTime() {
        assertThrows(IllegalArgumentException.class, () ->
                dateTime.toZonedDateTime("sdflksd"));
    }

    @Test
    void badInputLocalDateTime() {
        assertThrows(IllegalArgumentException.class, () ->
                dateTime.toLocalDateTime("sdflksd"));
    }

}
