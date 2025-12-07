package com.apptasticsoftware.rssreader.util;

import java.time.Duration;

/**
 * Utility class for RSS reader.
 */
public class Util {

    private Util() {
        // Utility class
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

    public static Duration toDuration(String time) {
        if (time == null || time.isBlank()) {
            return null;
        }

        time = time.trim();

        // Handle formats: MM:SS, MM:SS.mmm, HH:MM:SS, HH:MM:SS.mmm
        String[] parts = time.split("\\.");
        String timeWithoutMillis = parts[0];
        int millis = 0;

        if (parts.length > 1) {
            // Parse milliseconds from the fractional part
            String millisStr = parts[1];
            // Pad or truncate to 3 digits
            if (millisStr.length() < 3) {
                millisStr = String.format("%-3s", millisStr).replace(' ', '0');
            } else if (millisStr.length() > 3) {
                millisStr = millisStr.substring(0, 3);
            }
            millis = Integer.parseInt(millisStr);
        }

        String[] timeParts = timeWithoutMillis.split(":");
        int hours;
        int minutes;
        int seconds;

        if (timeParts.length == 2) {
            // MM:SS format
            hours = 0;
            minutes = Integer.parseInt(timeParts[0]);
            seconds = Integer.parseInt(timeParts[1]);
        } else if (timeParts.length == 3) {
            // HH:MM:SS format
            hours = Integer.parseInt(timeParts[0]);
            minutes = Integer.parseInt(timeParts[1]);
            seconds = Integer.parseInt(timeParts[2]);
        } else {
            return null;
        }

        return Duration.ofHours(hours)
                .plusMinutes(minutes)
                .plusSeconds(seconds)
                .plusMillis(millis);
    }

}
