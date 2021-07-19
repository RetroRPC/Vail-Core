package me.siding.core.utils;

import org.apache.commons.lang3.time.FastDateFormat;

import java.text.DecimalFormat;
import java.time.ZoneId;
import java.util.Locale;
import java.util.TimeZone;

public class EnglishTimeZoneFormats {
    public static TimeZone SERVER_TIME_ZONE = TimeZone.getTimeZone("EST");
    public static ZoneId SERVER_ZONE_ID = SERVER_TIME_ZONE.toZoneId();
    public static FastDateFormat DAY_MTH_HR_MIN = FastDateFormat.getInstance("dd/MM HH:mm", SERVER_TIME_ZONE,
            Locale.ENGLISH);
    public static FastDateFormat DAY_MTH_HR_MIN_SECS = FastDateFormat.getInstance("dd/MM HH:mm:ss",
            SERVER_TIME_ZONE, Locale.ENGLISH);
    public static FastDateFormat DAY_MTH_YR_HR_MIN_AMPM = FastDateFormat.getInstance("dd/MM/yy hh:mma",
            SERVER_TIME_ZONE, Locale.ENGLISH);
    public static FastDateFormat DAY_MTH_HR_MIN_AMPM = FastDateFormat.getInstance("dd/MM hh:mma",
            SERVER_TIME_ZONE, Locale.ENGLISH);
    public static FastDateFormat HR_MIN_AMPM = FastDateFormat.getInstance("hh:mma", SERVER_TIME_ZONE,
            Locale.ENGLISH);
    public static FastDateFormat HR_MIN_AMPM_TIMEZONE = FastDateFormat.getInstance("hh:mma z", SERVER_TIME_ZONE,
            Locale.ENGLISH);
    public static FastDateFormat HR_MIN = FastDateFormat.getInstance("hh:mm", SERVER_TIME_ZONE, Locale.ENGLISH);
    public static FastDateFormat MIN_SECS = FastDateFormat.getInstance("mm:ss", SERVER_TIME_ZONE, Locale.ENGLISH);
    public static FastDateFormat KOTH_FORMAT = FastDateFormat.getInstance("m:ss", SERVER_TIME_ZONE,
            Locale.ENGLISH);
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static ThreadLocal<DecimalFormat> REMAINING_SECONDS = new ThreadLocal() {
        protected DecimalFormat initialValue() {
            return new DecimalFormat("0.#");
        }
    };

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static ThreadLocal<DecimalFormat> REMAINING_SECONDS_TRAILING = new ThreadLocal() {
        protected DecimalFormat initialValue() {
            return new DecimalFormat("0.0");
        }
    };
}