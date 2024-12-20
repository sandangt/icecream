package sanlab.icecream.frontier.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import sanlab.icecream.frontier.dto.core.CustomerDto;
import sanlab.icecream.frontier.dto.core.RegisteredUserInfoDto;
import sanlab.icecream.frontier.dto.extended.CustomerExtendedDto;
import sanlab.icecream.frontier.model.Customer;

import java.util.List;
import java.util.UUID;

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

    // region User Details to Entity
    @Named("userDetailsToEntity")
    default Customer userDetailsToEntity(RegisteredUserInfoDto userDetails) {
        return Customer.builder()
            .userId(UUID.fromString(userDetails.getSub()))
            .email(userDetails.getEmail())
            .username(userDetails.getPreferredUsername())
            .firstName(userDetails.getGivenName())
            .lastName(userDetails.getFamilyName())
            .build();
    }
    // endregion

}
