package sanlab.icecream.sharedlib.datetime;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import com.google.protobuf.Timestamp;


public class DateTimeConverter {
    public static OffsetDateTime protobufTimestampToOffsetDateTime(Timestamp time, ZoneOffset offset) {
        if (time == null) return null;
        if (offset == null) offset = ZoneOffset.UTC;
        return Instant.ofEpochSecond(time.getSeconds(), time.getNanos()).atOffset(offset);
    }

    public static Timestamp OffsetDateTimeToProtobufTimestamp(OffsetDateTime time) {
        if (time == null) return Timestamp.newBuilder().setNanos(0).setSeconds(0).build();
        return Timestamp.newBuilder()
            .setSeconds(time.toEpochSecond())
            .setNanos(time.getNano())
            .build();
    }
}
