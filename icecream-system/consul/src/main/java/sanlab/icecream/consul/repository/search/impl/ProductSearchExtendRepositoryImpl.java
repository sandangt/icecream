package sanlab.icecream.consul.repository.search.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import sanlab.icecream.consul.model.ProductESearch;
import sanlab.icecream.consul.repository.search.ProductSearchExtendRepository;
import sanlab.icecream.consul.viewmodel.request.CollectionQueryRequest;
import sanlab.icecream.consul.viewmodel.response.CollectionQueryResponse;
import sanlab.icecream.fundamentum.constant.EProductStatus;
import sanlab.icecream.fundamentum.utils.RequestUtils;

import java.util.List;
import java.util.Optional;

public class ProductSearchExtendRepositoryImpl implements ProductSearchExtendRepository {

    private final ElasticsearchOperations elasticsearchOperations;

    public ProductSearchExtendRepositoryImpl(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    private static final String FIELD_NAME = "name";
    private static final String FIELD_SLUG = "slug";
    private static final String FIELD_DESCRIPTION = "description";
    private static final String FIELD_BRIEF_DESCRIPTION = "briefDescription";
    private static final String FIELD_STATUS = "status";
    private static final String FIELD_PRICE = "price";
    private static final String FIELD_CREATED_AT = "createdAt";
    private static final String FIELD_MODIFIED_AT = "modifiedAt";

    @Override
    public CollectionQueryResponse<ProductESearch> searchAndFilter(CollectionQueryRequest req, boolean isFeatured) {
        var filtersOptional = Optional.ofNullable(req.getFilters());
        var criteria = new Criteria();
        filtersOptional
            .map(CollectionQueryRequest.FiltersRequest::getSearchText)
            .filter(StringUtils::isNotEmpty)
            .ifPresent(text -> {
            Criteria searchCriteria = new Criteria(FIELD_NAME).fuzzy(text)
                .or(new Criteria(FIELD_SLUG).fuzzy(text))
                .or(new Criteria(FIELD_DESCRIPTION).fuzzy(text))
                .or(new Criteria(FIELD_BRIEF_DESCRIPTION).fuzzy(text));
            criteria.and(searchCriteria);
        });
        filtersOptional
            .map(CollectionQueryRequest.FiltersRequest::getStatuses)
            .filter(CollectionUtils::isNotEmpty)
            .ifPresent(statuses -> {
                var statusList = statuses.stream()
                    .map(inner -> EProductStatus.fromName(inner).getCode())
                    .toList();
                Criteria statusCriteria = new Criteria(FIELD_STATUS).in(statusList);
                criteria.and(statusCriteria);
            });
        filtersOptional
            .map(CollectionQueryRequest.FiltersRequest::getMinPrice)
            .ifPresent(minPrice -> criteria.and(new Criteria(FIELD_PRICE).greaterThanEqual(minPrice)));
        filtersOptional
            .map(CollectionQueryRequest.FiltersRequest::getMaxPrice)
            .ifPresent(maxPrice -> criteria.and(new Criteria(FIELD_PRICE).lessThanEqual(maxPrice)));
        filtersOptional
            .map(CollectionQueryRequest.FiltersRequest::getCreatedBefore)
            .ifPresent(createdBefore -> criteria.and(new Criteria(FIELD_CREATED_AT).greaterThanEqual(createdBefore)));
        filtersOptional
            .map(CollectionQueryRequest.FiltersRequest::getCreatedAfter)
            .ifPresent(createdAfter -> criteria.and(new Criteria(FIELD_CREATED_AT).lessThanEqual(createdAfter)));
        filtersOptional
            .map(CollectionQueryRequest.FiltersRequest::getModifiedBefore)
            .ifPresent(modifiedBefore -> criteria.and(new Criteria(FIELD_MODIFIED_AT).greaterThanEqual(modifiedBefore)));
        filtersOptional
            .map(CollectionQueryRequest.FiltersRequest::getModifiedAfter)
            .ifPresent(modifiedAfter -> criteria.and(new Criteria(FIELD_MODIFIED_AT).lessThanEqual(modifiedAfter)));

        CriteriaQuery searchQuery = new CriteriaQuery(criteria);
        searchQuery.setPageable(req.getPageRequest());
        var searchHits = elasticsearchOperations.search(searchQuery, ProductESearch.class);
        List<ProductESearch> content = searchHits.getSearchHits().stream()
            .map(SearchHit::getContent)
            .toList();
        long total = searchHits.getTotalHits();
        long page = req.getPageNumber();
        long totalPages = RequestUtils.calculateTotalPage(total, req.getPageSize());

        return CollectionQueryResponse.<ProductESearch>builder()
            .total(total)
            .page(page)
            .totalPages(totalPages)
            .data(content)
            .build();
    }

}
