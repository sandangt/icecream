package sanlab.icecream.fundamentum.utils;

import lombok.Getter;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public final class DatetimeUtils {

    private DatetimeUtils() {}

    public enum PatternType {
        TYPE_1("MMM dd, yyyy hh:mm a"),
        TYPE_2("MMM dd, yyyy hh:mm:ss a"),
        TYPE_3("MMM dd, yyyy"),
        TYPE_4("yyyy/MM/dd hh:mm a"),
        TYPE_5("MMM dd, yyyy hh:mm a"),
        TYPE_6("yyyy-MM-dd"),
        TYPE_7("yyyy/MM/dd"),
        TYPE_8("yyyy/MM/dd HH:mm"),
        TYPE_9("MM/dd/yyyy HH:mm:ss"),
        TYPE_10("yyyy.MM.dd_HH.mm.ss"),
        TYPE_11("yyyy-MM-dd'T'HH:mm:ss"),
        TYPE_12("yyyy-MM-dd'T'HH:mm:ss.SSS"),
        ;
        @Getter
        private final String pattern;

        PatternType(String pattern) {
            this.pattern = pattern;
        }

    }

    public static final String ZERO_UTC_OFFSET = "+0000";
    public static final TimeZone UTC_TIMEZONE = TimeZone.getTimeZone("UTC");
    public static final ZoneId UTC_ZONE_ID = ZoneId.of(ZERO_UTC_OFFSET);
    public static final long ONE_DAY_IN_MILLIS = 24 * 60 * 60 * 1000L;
    public static final long ONE_DAY_IN_SECONDS = ONE_DAY_IN_MILLIS / 1000L;
    private static final int ONE_YEAR_IN_DAYS = 365;
    public static final long ONE_YEAR_IN_MILLIS = ONE_YEAR_IN_DAYS * ONE_DAY_IN_MILLIS;
    public static final long ONE_YEAR_IN_HOURS = ONE_YEAR_IN_DAYS * 24L;


    public static String toFormalStringUTC(long epochMillis, PatternType type) {
        Instant instant = Instant.ofEpochMilli(epochMillis);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(type.getPattern()).withZone(UTC_ZONE_ID);
        return formatter.format(instant);
    }


}
