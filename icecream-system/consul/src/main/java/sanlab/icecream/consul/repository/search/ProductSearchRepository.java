package sanlab.icecream.consul.repository.search;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import sanlab.icecream.consul.model.ProductESearch;

import java.util.List;
import java.util.Optional;

public interface ProductSearchRepository extends ElasticsearchRepository<ProductESearch, String>, ProductSearchExtendRepository {

    List<ProductESearch> findByIdIn(List<String> ids);
    List<ProductESearch> findByNameContaining(String name);
    Optional<ProductESearch> findBySlug(String slug);

}
