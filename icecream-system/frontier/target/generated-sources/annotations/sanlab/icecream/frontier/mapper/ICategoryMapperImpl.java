package sanlab.icecream.frontier.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import sanlab.icecream.frontier.model.Category;
import sanlab.icecream.frontier.model.Product;
import sanlab.icecream.frontier.viewmodel.dto.CategoryDto;
import sanlab.icecream.frontier.viewmodel.dto.ProductDto;
import sanlab.icecream.frontier.viewmodel.response.CategoryResponse;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-12T01:16:46+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.3 (Eclipse Adoptium)"
)
@Component
public class ICategoryMapperImpl implements ICategoryMapper {

    @Override
    public CategoryDto entityToDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDto.CategoryDtoBuilder<?, ?> categoryDto = CategoryDto.builder();

        categoryDto.id( category.getId() );
        categoryDto.slug( category.getSlug() );
        categoryDto.name( category.getName() );

        return categoryDto.build();
    }

    @Override
    public List<CategoryDto> entityToDto(List<Category> categories) {
        if ( categories == null ) {
            return null;
        }

        List<CategoryDto> list = new ArrayList<CategoryDto>( categories.size() );
        for ( Category category : categories ) {
            list.add( entityToDto( category ) );
        }

        return list;
    }

    @Override
    public CategoryResponse entityToResponse(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryResponse.CategoryResponseBuilder<?, ?> categoryResponse = CategoryResponse.builder();

        categoryResponse.id( category.getId() );
        categoryResponse.slug( category.getSlug() );
        categoryResponse.name( category.getName() );
        categoryResponse.products( productSetToProductDtoList( category.getProducts() ) );

        return categoryResponse.build();
    }

    @Override
    public List<CategoryResponse> entityToResponse(List<Category> categories) {
        if ( categories == null ) {
            return null;
        }

        List<CategoryResponse> list = new ArrayList<CategoryResponse>( categories.size() );
        for ( Category category : categories ) {
            list.add( entityToResponse( category ) );
        }

        return list;
    }

    @Override
    public Category dtoToEntity(CategoryDto categoryDto) {
        if ( categoryDto == null ) {
            return null;
        }

        Category category = new Category();

        category.setName( categoryDto.getName() );
        category.setId( categoryDto.getId() );
        category.setSlug( categoryDto.getSlug() );

        return category;
    }

    @Override
    public List<Category> dtoToEntity(List<CategoryDto> categoryDtos) {
        if ( categoryDtos == null ) {
            return null;
        }

        List<Category> list = new ArrayList<Category>( categoryDtos.size() );
        for ( CategoryDto categoryDto : categoryDtos ) {
            list.add( dtoToEntity( categoryDto ) );
        }

        return list;
    }

    protected ProductDto productToProductDto(Product product) {
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

    protected List<ProductDto> productSetToProductDtoList(Set<Product> set) {
        if ( set == null ) {
            return null;
        }

        List<ProductDto> list = new ArrayList<ProductDto>( set.size() );
        for ( Product product : set ) {
            list.add( productToProductDto( product ) );
        }

        return list;
    }
}
