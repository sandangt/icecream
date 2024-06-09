package sanlab.icecream.frontier.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import sanlab.icecream.frontier.model.Category;
import sanlab.icecream.frontier.model.Image;
import sanlab.icecream.frontier.model.Product;
import sanlab.icecream.frontier.viewmodel.dto.CategoryDto;
import sanlab.icecream.frontier.viewmodel.dto.ImageDto;
import sanlab.icecream.frontier.viewmodel.dto.ProductDto;
import sanlab.icecream.frontier.viewmodel.response.ProductResponse;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-09T13:47:22+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.3 (Eclipse Adoptium)"
)
@Component
public class IProductMapperImpl implements IProductMapper {

    @Override
    public ProductDto entityToDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto.ProductDtoBuilder<?, ?> productDto = ProductDto.builder();

        productDto.id( product.getId() );
        productDto.name( product.getName() );
        productDto.description( product.getDescription() );
        productDto.price( product.getPrice() );
        productDto.status( product.getStatus() );
        productDto.quantity( product.getQuantity() );
        productDto.createdAt( product.getCreatedAt() );
        productDto.modifiedAt( product.getModifiedAt() );

        return productDto.build();
    }

    @Override
    public List<ProductDto> entityToDto(List<Product> products) {
        if ( products == null ) {
            return null;
        }

        List<ProductDto> list = new ArrayList<ProductDto>( products.size() );
        for ( Product product : products ) {
            list.add( entityToDto( product ) );
        }

        return list;
    }

    @Override
    public ProductResponse entityToResponse(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductResponse.ProductResponseBuilder<?, ?> productResponse = ProductResponse.builder();

        productResponse.id( product.getId() );
        productResponse.name( product.getName() );
        productResponse.description( product.getDescription() );
        productResponse.price( product.getPrice() );
        productResponse.status( product.getStatus() );
        productResponse.quantity( product.getQuantity() );
        productResponse.createdAt( product.getCreatedAt() );
        productResponse.modifiedAt( product.getModifiedAt() );
        productResponse.categories( categorySetToCategoryDtoList( product.getCategories() ) );
        productResponse.media( imageSetToImageDtoList( product.getMedia() ) );

        return productResponse.build();
    }

    @Override
    public List<ProductResponse> entityToResponse(List<Product> products) {
        if ( products == null ) {
            return null;
        }

        List<ProductResponse> list = new ArrayList<ProductResponse>( products.size() );
        for ( Product product : products ) {
            list.add( entityToResponse( product ) );
        }

        return list;
    }

    @Override
    public Product dtoToEntity(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        Product.ProductBuilder<?, ?> product = Product.builder();

        product.createdAt( productDto.getCreatedAt() );
        product.modifiedAt( productDto.getModifiedAt() );
        product.id( productDto.getId() );
        product.name( productDto.getName() );
        product.description( productDto.getDescription() );
        product.price( productDto.getPrice() );
        product.status( productDto.getStatus() );
        product.quantity( productDto.getQuantity() );

        return product.build();
    }

    @Override
    public List<Product> dtoToEntity(List<ProductDto> productDtos) {
        if ( productDtos == null ) {
            return null;
        }

        List<Product> list = new ArrayList<Product>( productDtos.size() );
        for ( ProductDto productDto : productDtos ) {
            list.add( dtoToEntity( productDto ) );
        }

        return list;
    }

    @Override
    public ProductResponse dtoToResponse(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        ProductResponse.ProductResponseBuilder<?, ?> productResponse = ProductResponse.builder();

        productResponse.id( productDto.getId() );
        productResponse.name( productDto.getName() );
        productResponse.description( productDto.getDescription() );
        productResponse.price( productDto.getPrice() );
        productResponse.status( productDto.getStatus() );
        productResponse.quantity( productDto.getQuantity() );
        productResponse.createdAt( productDto.getCreatedAt() );
        productResponse.modifiedAt( productDto.getModifiedAt() );

        return productResponse.build();
    }

    @Override
    public List<ProductResponse> dtoToResponse(List<ProductDto> productDtos) {
        if ( productDtos == null ) {
            return null;
        }

        List<ProductResponse> list = new ArrayList<ProductResponse>( productDtos.size() );
        for ( ProductDto productDto : productDtos ) {
            list.add( dtoToResponse( productDto ) );
        }

        return list;
    }

    protected CategoryDto categoryToCategoryDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDto.CategoryDtoBuilder<?, ?> categoryDto = CategoryDto.builder();

        categoryDto.id( category.getId() );
        categoryDto.slug( category.getSlug() );
        categoryDto.name( category.getName() );

        return categoryDto.build();
    }

    protected List<CategoryDto> categorySetToCategoryDtoList(Set<Category> set) {
        if ( set == null ) {
            return null;
        }

        List<CategoryDto> list = new ArrayList<CategoryDto>( set.size() );
        for ( Category category : set ) {
            list.add( categoryToCategoryDto( category ) );
        }

        return list;
    }

    protected ImageDto imageToImageDto(Image image) {
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

    protected List<ImageDto> imageSetToImageDtoList(Set<Image> set) {
        if ( set == null ) {
            return null;
        }

        List<ImageDto> list = new ArrayList<ImageDto>( set.size() );
        for ( Image image : set ) {
            list.add( imageToImageDto( image ) );
        }

        return list;
    }
}
