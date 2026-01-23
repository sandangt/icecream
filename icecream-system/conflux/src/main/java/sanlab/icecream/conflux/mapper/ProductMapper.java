package sanlab.icecream.conflux.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import sanlab.icecream.conflux.model.enriched.ProductEnriched;
import sanlab.icecream.conflux.model.source.ProductSource;
import sanlab.icecream.fundamentum.dto.exntended.ProductExtendedDto;

import java.util.List;

@Mapper(componentModel = "spring", uses = { SharedMapper.class, ImageMapper.class, CategoryMapper.class })
public interface ProductMapper {

    @Named("sourceToEnriched")
    ProductEnriched sourceToEnriched(ProductSource source);

    @Named("sourceToEnrichedIter")
    @IterableMapping(qualifiedByName = "sourceToEnriched")
    List<ProductEnriched> sourceToEnriched(List<ProductSource> source);

    @Named("extendedDtoToEnriched")
    ProductEnriched extendedDtoToEnriched(ProductExtendedDto dto);

    @Named("extendedDtoToEnrichedIter")
    @IterableMapping(qualifiedByName = "extendedDtoToEnriched")
    List<ProductEnriched> extendedDtoToEnriched(List<ProductExtendedDto> dto);

}
