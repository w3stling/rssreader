package com.apptastic.rssreader;

import org.junit.Test;

import static org.junit.Assert.*;

public class DateTimeTest {

    @Test
    public void dateTimeFormat1() {
        Long timestamp = DateTime.toEpochMilli("Fri, 01 Jun 2018 07:17:52 +0200");
        assertEquals(Long.valueOf(1527837472000L), timestamp);
    }

    @Test
    public void dateTimeFormat2() {
        Long timestamp = DateTime.toEpochMilli("2018-06-01T07:17:52+02:00");
        assertEquals(Long.valueOf(1527837472000L), timestamp);
    }

    @Test
    public void dateTimeFormat3() {
        Long timestamp = DateTime.toEpochMilli("2018-06-01T07:17:52");
        assertEquals(Long.valueOf(1527837472000L), timestamp);
    }

}
