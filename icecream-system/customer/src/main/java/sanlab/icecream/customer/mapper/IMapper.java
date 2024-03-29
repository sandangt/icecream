package sanlab.icecream.customer.mapper;


import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import com.google.protobuf.Timestamp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import sanlab.icecream.customer.model.Customer;
import sanlab.icecream.sharedlib.converter.DateTimeConverter;
import sanlab.icecream.sharedlib.proto.CustomerDTO;


@Mapper(componentModel = "spring")
public interface IMapper {
    IMapper INSTANCE = Mappers.getMapper(IMapper.class);

    @Mapping(target = "createdOn", source = "createdOn", qualifiedByName = "odtToTimestamp")
    @Mapping(target = "lastModifiedOn", source = "lastModifiedOn", qualifiedByName = "odtToTimestamp")
    CustomerDTO modelToDTO(Customer customer);

    @Mapping(target = "createdOn", source = "createdOn", qualifiedByName = "timestampToOdt")
    @Mapping(target = "lastModifiedOn", source = "lastModifiedOn", qualifiedByName = "timestampToOdt")
    Customer dtoToModel(CustomerDTO customer);

    @Named("odtToTimestamp")
    default Timestamp odtToTimestamp(OffsetDateTime offsetDateTime) {
        return DateTimeConverter.OffsetDateTimeToProtobufTimestamp(offsetDateTime);
    }
    @Named("timestampToOdt")
    default OffsetDateTime timestampToOdt(Timestamp timestamp) {
        return DateTimeConverter.protobufTimestampToOffsetDateTime(timestamp, ZoneOffset.UTC);
    }
}
