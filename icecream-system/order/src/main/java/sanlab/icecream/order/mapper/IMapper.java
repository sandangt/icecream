package sanlab.icecream.order.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface IMapper {
    IMapper INSTANCE = Mappers.getMapper(IMapper.class);
}
