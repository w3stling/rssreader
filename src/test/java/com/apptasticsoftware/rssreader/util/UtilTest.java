package com.apptasticsoftware.rssreader.util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UtilTest {

    @ParameterizedTest(name = "{0} is expected to output {1}")
    @MethodSource("periodToHoursTestData")
    void periodToHours(String period, int expectedHours) {
        assertEquals(expectedHours, Util.toMinutes(period));
    }

    private static Stream<Arguments> periodToHoursTestData() {
        return Stream.of(
                Arguments.of("daily", 1440),
                Arguments.of("weekly", 10080),
                Arguments.of("monthly", 43800),
                Arguments.of("yearly", 525600),
                Arguments.of("hourly", 60),
                Arguments.of("unknown", 60)
        );
    }
}
