package sanlab.icecream.frontier.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import sanlab.icecream.frontier.dto.core.AddressDto;
import sanlab.icecream.frontier.model.Address;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IAddressMapper {

    //region To DTO
    @Named("entityToDto")
    AddressDto entityToDto(Address address);

    @Named("entityToDtoIter")
    @IterableMapping(qualifiedByName = "entityToDto")
    List<AddressDto> entityToDto(List<Address> addresses);
    //endregion

    //region To Entity
    @Named("dtoToEntity")
    Address dtoToEntity(AddressDto addressDto);

    @Named("dtoToEntityIter")
    @IterableMapping(qualifiedByName = "dtoToEntity")
    List<Address> dtoToEntity(List<AddressDto> addressDtos);
    //endregion

}
