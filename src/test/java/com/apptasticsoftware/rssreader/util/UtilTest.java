package com.apptasticsoftware.rssreader.util;

import com.apptasticsoftware.rssreader.module.mediarss.MediaScene;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Duration;
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

    @ParameterizedTest
    @MethodSource("provideTimeData")
    void mediaSceneStartEndTime(String time, Duration expectedDuration) {
        var mediaScene = new MediaScene();
        mediaScene.setSceneStartTime(time);
        mediaScene.setSceneEndTime(time);
        assertEquals(expectedDuration, mediaScene.getSceneStartTimeAsDuration());
        assertEquals(expectedDuration, mediaScene.getSceneEndTimeAsDuration());
    }

    private static Stream<Arguments> provideTimeData() {
        return Stream.of(
                Arguments.of("00:01", Duration.parse("PT1S")),
                Arguments.of("00:00:01", Duration.parse("PT1S")),
                Arguments.of("00:01.123", Duration.parse("PT1.123S")),
                Arguments.of("00:00:01.123", Duration.parse("PT1.123S")),
                Arguments.of("01:02", Duration.parse("PT1M2S")),
                Arguments.of("00:01:02", Duration.parse("PT1M2S")),
                Arguments.of("01:02.123", Duration.parse("PT1M2.123S")),
                Arguments.of("00:01:02.123", Duration.parse("PT1M2.123S")),
                Arguments.of("01:02:03", Duration.parse("PT1H2M3S")),
                Arguments.of("01:02:03.123", Duration.parse("PT1H2M3.123S")),
                Arguments.of(null, null),
                Arguments.of("", null),
                Arguments.of(" ", null),
                Arguments.of("          ", null)
        );
    }
}
