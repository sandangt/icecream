package sanlab.icecream.consul.repository.crud;

import sanlab.icecream.consul.model.ProductESearch;
import sanlab.icecream.fundamentum.contractmodel.request.CollectionQueryRequest;
import sanlab.icecream.fundamentum.contractmodel.response.CollectionQueryResponse;

public interface ProductESearchExtendRepository {

    CollectionQueryResponse<ProductESearch> searchAndFilter(CollectionQueryRequest req, boolean isFeatured);

}
