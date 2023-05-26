package sanlab.icecream.product.mapper;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import com.google.protobuf.Timestamp;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import sanlab.icecream.product.model.Category;
import sanlab.icecream.product.model.Product;
import sanlab.icecream.sharedlib.datetime.DateTimeConverter;
import sanlab.icecream.sharedlib.proto.CategoryDTO;
import sanlab.icecream.sharedlib.proto.ProductDTO;


@Mapper(componentModel = "spring")
public interface IMapper {
    IMapper INSTANCE = Mappers.getMapper(IMapper.class);

    @Mapping(target = "createdOn", source = "createdOn", qualifiedByName = "timestampToOdt")
    @Mapping(target = "lastModifiedOn", source = "lastModifiedOn", qualifiedByName = "timestampToOdt")
    Product DTOToModel(ProductDTO product);


    @BeanMapping(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    @Mapping(target = "createdOn", source = "createdOn", qualifiedByName = "odtToTimestamp")
    @Mapping(target = "lastModifiedOn", source = "lastModifiedOn", qualifiedByName = "odtToTimestamp")
    ProductDTO modelToDTO(Product product);

    @Mapping(target = "createdOn", source = "createdOn", qualifiedByName = "timestampToOdt")
    @Mapping(target = "lastModifiedOn", source = "lastModifiedOn", qualifiedByName = "timestampToOdt")
    Category DTOToModel(CategoryDTO category);


    @BeanMapping(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    @Mapping(target = "createdOn", source = "createdOn", qualifiedByName = "odtToTimestamp")
    @Mapping(target = "lastModifiedOn", source = "lastModifiedOn", qualifiedByName = "odtToTimestamp")
    CategoryDTO modelToDTO(Category category);

    @Named("odtToTimestamp")
    default Timestamp odtToTimestamp(OffsetDateTime offsetDateTime) {
        return DateTimeConverter.OffsetDateTimeToProtobufTimestamp(offsetDateTime);
    }
    @Named("timestampToOdt")
    default OffsetDateTime timestampToOdt(Timestamp timestamp) {
        return DateTimeConverter.protobufTimestampToOffsetDateTime(timestamp, ZoneOffset.UTC);
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "createdOn", source = "createdOn", ignore = true)
    @Mapping(target = "createdBy", source = "createdBy", ignore = true)
    @Mapping(target = "lastModifiedOn", source = "lastModifiedOn", ignore = true)
    @Mapping(target = "lastModifiedBy", source = "lastModifiedBy", ignore = true)
    void updateCategoryFromDTO(CategoryDTO categoryDTO, @MappingTarget Category category);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "createdOn", source = "createdOn", ignore = true)
    @Mapping(target = "createdBy", source = "createdBy", ignore = true)
    @Mapping(target = "lastModifiedOn", source = "lastModifiedOn", ignore = true)
    @Mapping(target = "lastModifiedBy", source = "lastModifiedBy", ignore = true)
    void updateProductFromDTO(ProductDTO productDTO, @MappingTarget Product product);
}
