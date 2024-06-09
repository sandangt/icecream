package sanlab.icecream.frontier.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import sanlab.icecream.frontier.model.Image;
import sanlab.icecream.frontier.viewmodel.dto.ImageDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-09T13:47:22+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.3 (Eclipse Adoptium)"
)
@Component
public class IImageMapperImpl implements IImageMapper {

    @Override
    public ImageDto entityToDto(Image image) {
        if ( image == null ) {
            return null;
        }

        ImageDto.ImageDtoBuilder imageDto = ImageDto.builder();

        imageDto.id( image.getId() );
        imageDto.description( image.getDescription() );
        imageDto.relativePath( image.getRelativePath() );
        imageDto.type( image.getType() );
        imageDto.createdAt( image.getCreatedAt() );
        imageDto.modifiedAt( image.getModifiedAt() );

        return imageDto.build();
    }

    @Override
    public List<ImageDto> entityToDto(List<Image> images) {
        if ( images == null ) {
            return null;
        }

        List<ImageDto> list = new ArrayList<ImageDto>( images.size() );
        for ( Image image : images ) {
            list.add( entityToDto( image ) );
        }

        return list;
    }

    @Override
    public Image dtoToEntity(ImageDto imageDto) {
        if ( imageDto == null ) {
            return null;
        }

        Image.ImageBuilder<?, ?> image = Image.builder();

        image.createdAt( imageDto.getCreatedAt() );
        image.modifiedAt( imageDto.getModifiedAt() );
        image.id( imageDto.getId() );
        image.description( imageDto.getDescription() );
        image.relativePath( imageDto.getRelativePath() );
        image.type( imageDto.getType() );

        return image.build();
    }

    @Override
    public List<Image> dtoToEntity(List<ImageDto> imageDtos) {
        if ( imageDtos == null ) {
            return null;
        }

        List<Image> list = new ArrayList<Image>( imageDtos.size() );
        for ( ImageDto imageDto : imageDtos ) {
            list.add( dtoToEntity( imageDto ) );
        }

        return list;
    }
}
