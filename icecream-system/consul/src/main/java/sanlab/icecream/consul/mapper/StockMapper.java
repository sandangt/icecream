package sanlab.icecream.consul.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import sanlab.icecream.fundamentum.dto.core.StockDto;
import sanlab.icecream.fundamentum.dto.exntended.StockExtendedDto;
import sanlab.icecream.consul.model.Stock;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = { AddressMapper.class })
public interface StockMapper {

    //region To DTO
    @Named("entityToDto")
    StockDto entityToDto(Stock stock);

    @Named("entityToDtoIter")
    @IterableMapping(qualifiedByName = "entityToDto")
    List<StockDto> entityToDto(List<Stock> stocks);

    @Named("entityToExtendedDto")
    @Mapping(target = "address", source = "addresses", qualifiedByName = "singleOutAddress")
    StockExtendedDto entityToExtendedDto(Stock stock);

    @Named("entityToExtendedDtoIter")
    @IterableMapping(qualifiedByName = "entityToExtendedDto")
    List<StockExtendedDto> entityToExtendedDto(List<Stock> stocks);
    //endregion

    //region To Entity
    @Named("dtoToEntity")
    Stock dtoToEntity(StockDto stockDto);

    @Named("dtoToEntityIter")
    @IterableMapping(qualifiedByName = "dtoToEntity")
    List<Stock> dtoToEntity(List<StockDto> stockDtos);
    //endregion

}
