package com.apptasticsoftware.rssreader.util;

import java.time.Duration;

/**
 * Utility class for RSS reader.
 */
public class Util {

    private Util() {

    }

    /**
     * Convert a time period string to hours.
     *
     * @param period the time period string (e.g., "daily", "weekly", "monthly", "yearly", "hourly")
     * @return the number of hours in the given time period, or 1 if the period is not recognized
     */
    public static int toMinutes(String period) {
        switch (period.toLowerCase(Default.getLocale())) {
            case "daily": return 1440;
            case "weekly": return 10080;
            case "monthly": return 43800;
            case "yearly": return 525600;
            case "hourly":
            default: return 60;
        }
    }

    public static Duration toDuration(double duration) {
        return Duration.ofMillis((long) (duration * 1000));
    }
}
