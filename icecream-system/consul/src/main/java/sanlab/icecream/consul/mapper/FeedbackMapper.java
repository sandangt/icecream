package sanlab.icecream.consul.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import sanlab.icecream.fundamentum.dto.core.FeedbackDto;
import sanlab.icecream.fundamentum.dto.exntended.FeedbackExtendedDto;
import sanlab.icecream.consul.model.Feedback;

import java.util.List;

@Mapper(componentModel = "spring", uses = { ProductMapper.class, CustomerMapper.class })
public interface FeedbackMapper {

    //region To DTO
    @Named("entityToDto")
    FeedbackDto entityToDto(Feedback feedback);

    @Named("entityToDtoIter")
    @IterableMapping(qualifiedByName = "entityToDto")
    List<FeedbackDto> entityToDto(List<Feedback> feedbacks);

    @Named("entityToExtendedDto")
    FeedbackExtendedDto entityToExtendedDto(Feedback feedback);

    @Named("entityToExtendedDtoIter")
    @IterableMapping(qualifiedByName = "entityToExtendedDto")
    List<FeedbackExtendedDto> entityToExtendedDto(List<Feedback> feedbacks);
    //endregion

    //region To Entity
    @Named("dtoToEntity")
    @BeanMapping(ignoreByDefault = true)
    Feedback dtoToEntity(FeedbackDto feedbackDto);

    @Named("dtoToEntityIter")
    @IterableMapping(qualifiedByName = "dtoToEntity")
    List<Feedback> dtoToEntity(List<FeedbackDto> feedbackDtos);
    //endregion

}
