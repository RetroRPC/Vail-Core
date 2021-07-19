package me.siding.core.utils;

import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;

public class TimeUtil {

    // The input is an integer in seconds.
    public static String formatTime(int secs) {
        int remainder = secs % 86400;

        int days = secs / 86400;
        int hours = remainder / 3600;
        int minutes = (remainder / 60) - (hours * 60);
        int seconds = (remainder % 3600) - (minutes * 60);

        String fDays = (days > 0 ? " " + days + " DAY" + (days > 1 ? "S" : "") : "");
        String fHours = (hours > 0 ? " " + hours + " HOUR" + (hours > 1 ? "S" : "") : "");
        String fMinutes = (minutes > 0 ? " " + minutes + " MINUTE" + (minutes > 1 ? "S" : "") : "");
        String fSeconds = (seconds > 0 ? " " + seconds + " SECOND" + (seconds > 1 ? "S" : "") : "");

        return new StringBuilder().append(fDays).append(fHours)
                .append(fMinutes).append(fSeconds).toString();
    }

    public static String Uptime() {
        return TimeUtil.formatTime((int) TimeUnit.MILLISECONDS.toSeconds(ManagementFactory.getRuntimeMXBean().getUptime()));
    }
}