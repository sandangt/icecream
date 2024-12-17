package sanlab.icecream.frontier.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import sanlab.icecream.frontier.dto.core.StockDto;
import sanlab.icecream.frontier.dto.extended.StockExtendedDto;
import sanlab.icecream.frontier.model.Stock;

import java.util.List;

@Mapper(componentModel = "spring", uses = { IAddressMapper.class })
public interface IStockMapper {

    //region To DTO
    @Named("entityToDto")
    StockDto entityToDto(Stock stock);

    @Named("entityToDtoIter")
    @IterableMapping(qualifiedByName = "entityToDto")
    List<StockDto> entityToDto(List<Stock> stocks);

    @Named("entityToExtendedDto")
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
