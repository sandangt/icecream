package sanlab.icecream.consul.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import sanlab.icecream.fundamentum.dto.core.CustomerDto;
import sanlab.icecream.consul.dto.core.RegisteredUserInfoDto;
import sanlab.icecream.fundamentum.dto.exntended.CustomerExtendedDto;
import sanlab.icecream.consul.dto.keycloak.KeycloakUserInfoDto;
import sanlab.icecream.consul.model.Customer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static sanlab.icecream.fundamentum.constant.ECustomerStatus.ACTIVE;

@Mapper(componentModel = "spring", uses = { SharedMapper.class, ImageMapper.class, AddressMapper.class })
public interface CustomerMapper {

    //region To DTO
    @Named("entityToDto")
    @Mapping(target = "status", source = "status", qualifiedByName = "nameToECustomerStatus")
    CustomerDto entityToDto(Customer customer);

    @Named("entityToDtoIter")
    @IterableMapping(qualifiedByName = "entityToDto")
    List<CustomerDto> entityToDto(List<Customer> customers);

    @Named("entityToExtendedDto")
    @Mapping(target = "status", source = "status", qualifiedByName = "nameToECustomerStatus")
    CustomerExtendedDto entityToExtendedDto(Customer customer);

    @Named("entityToExtendedDtoIter")
    @IterableMapping(qualifiedByName = "entityToExtendedDto")
    List<CustomerExtendedDto> entityToExtendedDto(List<Customer> customers);
    //endregion

    //region To Entity
    @Named("dtoToEntity")
    @Mapping(target = "media", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "addresses", ignore = true)
    @Mapping(target = "primaryAddress", ignore = true)
    @Mapping(target = "status", source = "status", qualifiedByName = "eCustomerStatusToName")
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

    // region Keycloak user info
    default KeycloakUserInfoDto dtoToKeycloakUserInfo(CustomerDto dto) {
        var userInfo = new KeycloakUserInfoDto();
        var dtoOptional = Optional.ofNullable(dto);
        dtoOptional.map(CustomerDto::getUserId).map(UUID::toString).ifPresent(userInfo::setId);
        dtoOptional.map(CustomerDto::getEmail).ifPresent(userInfo::setEmail);
        dtoOptional.map(CustomerDto::getUsername).ifPresent(userInfo::setUsername);
        dtoOptional.map(CustomerDto::getFirstName).ifPresent(userInfo::setFirstName);
        dtoOptional.map(CustomerDto::getLastName).ifPresent(userInfo::setLastName);
        dtoOptional.map(CustomerDto::getStatus)
            .filter(ACTIVE::equals)
            .ifPresent(ignored -> userInfo.setEnabled(true));
        return userInfo;
    }
    // endregion

}
