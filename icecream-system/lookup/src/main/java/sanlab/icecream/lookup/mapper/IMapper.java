package sanlab.icecream.lookup.mapper;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import com.google.protobuf.Timestamp;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;
import sanlab.icecream.lookup.model.Media;
import sanlab.icecream.sharedlib.converter.DateTimeConverter;
import sanlab.icecream.sharedlib.proto.MediaDTO;


@Mapper(componentModel = "spring")
public interface IMapper {
    IMapper INSTANCE = Mappers.getMapper(IMapper.class);

    @Mapping(target = "createdOn", source = "createdOn", qualifiedByName = "timestampToOdt")
    @Mapping(target = "lastModifiedOn", source = "lastModifiedOn", qualifiedByName = "timestampToOdt")
    Media dtoToModel(MediaDTO media);

    @BeanMapping(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    @Mapping(target = "createdOn", source = "createdOn", qualifiedByName = "odtToTimestamp")
    @Mapping(target = "lastModifiedOn", source = "lastModifiedOn", qualifiedByName = "odtToTimestamp")
    MediaDTO modelToDTO(Media media);

    @Named("odtToTimestamp")
    default Timestamp odtToTimestamp(OffsetDateTime offsetDateTime) {
        return DateTimeConverter.OffsetDateTimeToProtobufTimestamp(offsetDateTime);
    }
    @Named("timestampToOdt")
    default OffsetDateTime timestampToOdt(Timestamp timestamp) {
        return DateTimeConverter.protobufTimestampToOffsetDateTime(timestamp, ZoneOffset.UTC);
    }
}
