package com.apptasticsoftware.rssreader.util;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

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

    /**
     * Convert a time period string to a Duration object.
     *
     * Supports the following formats:
     * <ul>
     *   <li>S - seconds only (e.g., "30")</li>
     *   <li>S.mmm - seconds with milliseconds (e.g., "30.500")</li>
     *   <li>MM:SS - minutes and seconds (e.g., "1:30")</li>
     *   <li>MM:SS.mmm - minutes, seconds, and milliseconds (e.g., "1:30.500")</li>
     *   <li>HH:MM:SS - hours, minutes, and seconds (e.g., "1:2:30")</li>
     *   <li>HH:MM:SS.mmm - hours, minutes, seconds, and milliseconds (e.g., "1:2:30.500")</li>
     * </ul>
     *
     * @param time the time period string to convert
     * @return a Duration object representing the specified time period, or null if the input is null, blank, or invalid
     */
    public static Duration toDuration(String time) {
        if (time == null || time.isBlank()) {
            return null;
        }

        time = time.trim();

        // Handle formats: S, S.mmm, MM:SS, MM:SS.mmm, HH:MM:SS, HH:MM:SS.mmm
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

        if (timeParts.length == 1) {
            // S format (seconds only)
            hours = 0;
            minutes = 0;
            seconds = Integer.parseInt(timeParts[0]);
        } else if (timeParts.length == 2) {
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

    /**
     * Retrieves the last element from a list.
     *
     * @param <T> the type of elements in the list
     * @param list the list to get the last element from
     * @return the last element in the list
     * @throws NoSuchElementException if the list is null or empty
     */
    public static <T> T getLast(List<T> list) {
        if (list == null || list.isEmpty()) {
            throw new NoSuchElementException("List is empty or null");
        }
        return list.get(list.size() - 1);
    }

    public static boolean isBlank(String text) {
        return text == null || text.isBlank();
    }
}
