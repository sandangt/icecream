package sanlab.icecream.consul.repository.search;

import sanlab.icecream.consul.model.ProductESearch;
import sanlab.icecream.consul.viewmodel.request.CollectionQueryRequest;
import sanlab.icecream.consul.viewmodel.response.CollectionQueryResponse;

public interface ProductSearchExtendRepository {

    CollectionQueryResponse<ProductESearch> searchAndFilter(CollectionQueryRequest req);

}
