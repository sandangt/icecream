package sanlab.icecream.conflux.repository.rsocketclient;

import sanlab.icecream.fundamentum.dto.exntended.ProductExtendedDto;

import java.util.List;

public interface ConsulImageRSocketRepository {
    List<ProductExtendedDto> getProductsByImageId(String imageId);
}
