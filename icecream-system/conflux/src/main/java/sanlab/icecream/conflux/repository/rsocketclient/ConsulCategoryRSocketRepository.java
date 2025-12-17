package sanlab.icecream.conflux.repository.rsocketclient;

import sanlab.icecream.fundamentum.dto.exntended.ProductExtendedDto;

import java.util.List;

public interface ConsulCategoryRSocketRepository {
    List<ProductExtendedDto> getProductsByCategoryId(String categoryId);
}
