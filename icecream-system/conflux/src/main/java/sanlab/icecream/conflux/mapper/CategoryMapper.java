package sanlab.icecream.conflux.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import sanlab.icecream.conflux.model.enriched.CategoryEnriched;
import sanlab.icecream.conflux.model.source.CategorySource;
import sanlab.icecream.fundamentum.dto.core.CategoryDto;

import java.util.List;

@Mapper(componentModel = "spring", uses = { SharedMapper.class })
public interface CategoryMapper {

    @Named("sourceToEnriched")
    CategoryEnriched sourceToEnriched(CategorySource source);

    @Named("sourceToEnrichedIter")
    @IterableMapping(qualifiedByName = "sourceToEnriched")
    List<CategoryEnriched> sourceToEnriched(List<CategorySource> source);

    @Named("dtoToEnriched")
    @Mapping(target = "id", source = "id", qualifiedByName = "uuidToString")
    CategoryEnriched dtoToEnriched(CategoryDto dto);

    @Named("dtoToEnrichedIter")
    @IterableMapping(qualifiedByName = "dtoToEnriched")
    List<CategoryEnriched> dtoToEnriched(List<CategoryDto> dto);

}
