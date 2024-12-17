package sanlab.icecream.frontier.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import sanlab.icecream.frontier.dto.core.CustomerDto;
import sanlab.icecream.frontier.dto.extended.CustomerExtendedDto;
import sanlab.icecream.frontier.model.Customer;

import java.util.List;

@Mapper(componentModel = "spring", uses = { IImageMapper.class, IAddressMapper.class })
public interface ICustomerMapper {

    //region To DTO
    @Named("entityToDto")
    CustomerDto entityToDto(Customer customer);

    @Named("entityToDtoIter")
    @IterableMapping(qualifiedByName = "entityToDto")
    List<CustomerDto> entityToDto(List<Customer> customers);

    @Named("entityToExtendedDto")
    CustomerExtendedDto entityToExtendedDto(Customer customer);

    @Named("entityToExtendedDtoIter")
    @IterableMapping(qualifiedByName = "entityToExtendedDto")
    List<CustomerExtendedDto> entityToExtendedDto(List<Customer> customers);
    //endregion

    //region To Entity
    @Named("dtoToEntity")
    Customer dtoToEntity(CustomerDto customerDto);

    @Named("dtoToEntityIter")
    @IterableMapping(qualifiedByName = "dtoToEntity")
    List<Customer> dtoToEntity(List<CustomerDto> customerDtos);
    //endregion

}
