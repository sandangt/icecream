package sanlab.icecream.consul.repository.crud;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import sanlab.icecream.consul.model.ProductESearch;

import java.util.List;
import java.util.Optional;

public interface ProductESearchRepository extends ElasticsearchRepository<ProductESearch, String>, ProductESearchExtendRepository {

    List<ProductESearch> findByIdIn(List<String> ids);
    List<ProductESearch> findByNameContaining(String name);
    Optional<ProductESearch> findBySlug(String slug);

}
