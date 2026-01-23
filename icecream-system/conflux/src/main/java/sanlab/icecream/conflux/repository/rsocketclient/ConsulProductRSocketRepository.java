package sanlab.icecream.conflux.repository.rsocketclient;

import sanlab.icecream.fundamentum.dto.core.CategoryDto;
import sanlab.icecream.fundamentum.dto.core.ImageDto;
import sanlab.icecream.fundamentum.dto.exntended.ProductExtendedDto;

import java.util.List;
import java.util.Optional;

public interface ConsulProductRSocketRepository {

    Optional<ProductExtendedDto> getProductById(String productId);
    List<CategoryDto> getCategoriesByProductId(String productId);
    List<ImageDto> getMediaByProductId(String productId);
    Optional<ImageDto> getFeaturedBannerByProductId(String productId);

}
