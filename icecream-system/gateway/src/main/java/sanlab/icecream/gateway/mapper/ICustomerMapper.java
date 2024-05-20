package sanlab.icecream.gateway.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;
import sanlab.icecream.gateway.viewmodel.customer.CustomerVm;
import sanlab.icecream.sharedlib.proto.CustomerDTO;


@Mapper(componentModel = "spring")
public interface ICustomerMapper extends IBaseMapper {
    ICustomerMapper INSTANCE = Mappers.getMapper(ICustomerMapper.class);

    @BeanMapping(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    @Mapping(target = "createdOn", source = "createdOn", qualifiedByName = "odtToTimestamp")
    @Mapping(target = "lastModifiedOn", source = "lastModifiedOn", qualifiedByName = "odtToTimestamp")
    CustomerDTO vmToDTO(CustomerVm customer);

    @Mapping(target = "createdOn", source = "createdOn", qualifiedByName = "timestampToOdt")
    @Mapping(target = "lastModifiedOn", source = "lastModifiedOn", qualifiedByName = "timestampToOdt")
    CustomerVm dtoToVm(CustomerDTO customer);
}
