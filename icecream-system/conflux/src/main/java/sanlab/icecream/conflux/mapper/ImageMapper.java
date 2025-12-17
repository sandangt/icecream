package sanlab.icecream.conflux.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import sanlab.icecream.conflux.model.enriched.ImageEnriched;
import sanlab.icecream.conflux.model.source.ImageSource;
import sanlab.icecream.fundamentum.dto.core.ImageDto;

import java.util.List;

@Mapper(componentModel = "spring", uses = { SharedMapper.class })
public interface ImageMapper {

    @Named("sourceToEnriched")
    ImageEnriched sourceToEnriched(ImageSource source);

    @Named("sourceToEnrichedIter")
    @IterableMapping(qualifiedByName = "sourceToEnriched")
    List<ImageEnriched> sourceToEnriched(List<ImageSource> source);

    @Named("dtoToEnriched")
    @Mapping(target = "id", source = "id", qualifiedByName = "uuidToString")
    ImageEnriched dtoToEnriched(ImageDto dto);

    @Named("dtoToEnrichedIter")
    @IterableMapping(qualifiedByName = "dtoToEnriched")
    List<ImageEnriched> dtoToEnriched(List<ImageDto> dto);

}
