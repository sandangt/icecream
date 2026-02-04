package sanlab.icecream.consul.mapper;

import org.mapstruct.Context;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import sanlab.icecream.fundamentum.dto.core.AddressDto;
import sanlab.icecream.consul.model.Address;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    //region To DTO
    @Named("entityToDto")
    AddressDto entityToDto(Address address);

    @Named("entityToDtoIter")
    @IterableMapping(qualifiedByName = "entityToDto")
    List<AddressDto> entityToDto(List<Address> addresses);
    //endregion

    //region To Entity
    @Named("dtoToEntity")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    Address dtoToEntity(AddressDto addressDto);

    @Named("dtoToEntityIter")
    @IterableMapping(qualifiedByName = "dtoToEntity")
    List<Address> dtoToEntity(List<AddressDto> addressDtos);
    //endregion

    @Named("singleOutAddress")
    default AddressDto singleOutAddress(Set<Address> addresses) {
        return addresses.stream().findFirst()
            .map(this::entityToDto)
            .orElse(null);
    }

}
