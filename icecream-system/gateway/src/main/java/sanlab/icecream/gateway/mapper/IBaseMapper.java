package sanlab.icecream.gateway.mapper;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import com.google.protobuf.Timestamp;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import sanlab.icecream.sharedlib.datetime.DateTimeConverter;


@Mapper
public interface IBaseMapper {
    @Named("odtToTimestamp")
    default Timestamp odtToTimestamp(OffsetDateTime offsetDateTime) {
        return DateTimeConverter.OffsetDateTimeToProtobufTimestamp(offsetDateTime);
    }
    @Named("timestampToOdt")
    default OffsetDateTime timestampToOdt(Timestamp timestamp) {
        return DateTimeConverter.protobufTimestampToOffsetDateTime(timestamp, ZoneOffset.UTC);
    }
}
