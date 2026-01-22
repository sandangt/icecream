package sanlab.icecream.consul.repository.crud.impl;

import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.ChildScoreMode;
import co.elastic.clients.elasticsearch._types.query_dsl.MultiMatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.NestedQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.NumberRangeQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.RangeQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.TermQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.TermsQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.TermsQueryField;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import sanlab.icecream.consul.model.ProductESearch;
import sanlab.icecream.consul.repository.crud.ProductESearchExtendRepository;
import sanlab.icecream.consul.utils.CollectionQueryUtils;
import sanlab.icecream.fundamentum.contractmodel.request.CollectionQueryRequest;
import sanlab.icecream.fundamentum.contractmodel.response.CollectionQueryResponse;
import sanlab.icecream.fundamentum.utils.RequestUtils;

import java.util.List;
import java.util.Optional;

public class ProductESearchExtendRepositoryImpl implements ProductESearchExtendRepository {

    private final ElasticsearchOperations elasticsearchOperations;
    private RangeQuery.Builder inner;

    public ProductESearchExtendRepositoryImpl(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    private static final String FIELD_NAME = "name^2";
    private static final String FIELD_SLUG = "slug^2";
    private static final String FIELD_DESCRIPTION = "description";
    private static final String FIELD_BRIEF_DESCRIPTION = "briefDescription";
    private static final String FIELD_STATUS = "status";
    private static final String FIELD_CATEGORY = "categories";
    private static final String FIELD_CATEGORY_SLUG = "categories.slug";
    private static final String FIELD_PRICE = "price";
    private static final String FIELD_IS_FEATURED = "isFeatured";
    private static final String FIELD_CREATED_AT = "createdAt";
    private static final String FIELD_MODIFIED_AT = "modifiedAt";
    private static final Sort DEFAULT_SORT_BY = Sort.by(Sort.Direction.DESC, "_score");

    @Override
    public CollectionQueryResponse<ProductESearch> searchAndFilter(CollectionQueryRequest req, boolean isFeatured) {
        var filtersOptional = Optional.ofNullable(req.getFilters());
        BoolQuery.Builder boolBuilder = new BoolQuery.Builder();
        filtersOptional
            .map(CollectionQueryRequest.FiltersRequest::getSearchText)
            .filter(StringUtils::isNotEmpty)
            .ifPresent(text -> {
                MultiMatchQuery multiFuzzyQuery = MultiMatchQuery.of(inner -> inner
                    .query(text)
                    .fields(FIELD_NAME, FIELD_SLUG, FIELD_DESCRIPTION, FIELD_BRIEF_DESCRIPTION)
                    .fuzziness("AUTO")
                );
                boolBuilder.should(multiFuzzyQuery._toQuery());
            });
        filtersOptional
            .map(CollectionQueryRequest.FiltersRequest::getStatuses)
            .filter(CollectionUtils::isNotEmpty)
            .ifPresent(statuses -> {
                var fieldValues = statuses.stream().map(FieldValue::of).toList();
                var termsQueryField = new TermsQueryField.Builder().value(fieldValues).build();
                TermsQuery statusQuery = TermsQuery.of(inner -> inner
                    .field(FIELD_STATUS)
                    .terms(termsQueryField)
                );
                boolBuilder.filter(statusQuery._toQuery());
            });
        filtersOptional
            .map(CollectionQueryRequest.FiltersRequest::getCategories)
            .filter(CollectionUtils::isNotEmpty)
            .ifPresent(categorySlugs -> {
                var fieldValues = categorySlugs.stream().map(FieldValue::of).toList();
                var termsQueryField = new TermsQueryField.Builder().value(fieldValues).build();
                TermsQuery categorySlugQuery = TermsQuery.of(inner -> inner
                    .field(FIELD_CATEGORY_SLUG)
                    .terms(termsQueryField)
                );
                NestedQuery categoryQuery = NestedQuery.of(inner -> inner
                    .path(FIELD_CATEGORY)
                    .query(categorySlugQuery._toQuery())
                    .scoreMode(ChildScoreMode.None)
                );
                boolBuilder.filter(categoryQuery._toQuery());
            });
        filtersOptional
            .map(CollectionQueryRequest.FiltersRequest::getMinPrice)
            .ifPresent(minPrice -> {
                var numberRangeVal = NumberRangeQuery
                    .of(inner -> inner.field(FIELD_PRICE).gte(Double.valueOf(minPrice)));
                boolBuilder.filter(RangeQuery.of(inner -> inner.number(numberRangeVal))._toQuery());
            });
        filtersOptional
            .map(CollectionQueryRequest.FiltersRequest::getMaxPrice)
            .ifPresent(maxPrice -> {
                var numberRangeVal = NumberRangeQuery
                    .of(inner -> inner.field(FIELD_PRICE).lte(Double.valueOf(maxPrice)));
                boolBuilder.filter(RangeQuery.of(inner -> inner.number(numberRangeVal))._toQuery());
            });
        filtersOptional
            .map(CollectionQueryRequest.FiltersRequest::getCreatedBefore)
            .ifPresent(createdBefore -> {
                var numberRangeVal = NumberRangeQuery
                    .of(inner -> inner.field(FIELD_CREATED_AT).gte(Double.valueOf(createdBefore)));
                boolBuilder.filter(RangeQuery.of(inner -> inner.number(numberRangeVal))._toQuery());
            });
        filtersOptional
            .map(CollectionQueryRequest.FiltersRequest::getCreatedAfter)
            .ifPresent(createdAfter -> {
                var numberRangeVal = NumberRangeQuery
                    .of(inner -> inner.field(FIELD_CREATED_AT).lte(Double.valueOf(createdAfter)));
                boolBuilder.filter(RangeQuery.of(inner -> inner.number(numberRangeVal))._toQuery());
            });
        filtersOptional
            .map(CollectionQueryRequest.FiltersRequest::getModifiedBefore)
            .ifPresent(modifiedBefore -> {
                var numberRangeVal = NumberRangeQuery
                    .of(inner -> inner.field(FIELD_MODIFIED_AT).gte(Double.valueOf(modifiedBefore)));
                boolBuilder.filter(RangeQuery.of(inner -> inner.number(numberRangeVal))._toQuery());
            });
        filtersOptional
            .map(CollectionQueryRequest.FiltersRequest::getModifiedAfter)
            .ifPresent(modifiedAfter -> {
                var numberRangeVal = NumberRangeQuery
                    .of(inner -> inner.field(FIELD_CREATED_AT).lte(Double.valueOf(modifiedAfter)));
                boolBuilder.filter(RangeQuery.of(inner -> inner.number(numberRangeVal))._toQuery());
            });
        boolBuilder.filter(TermQuery.of(inner -> inner.field(FIELD_IS_FEATURED).value(isFeatured))._toQuery());
        var pageable = CollectionQueryUtils.getPageRequest(req);
        if (pageable.getSort().isUnsorted()) {
            pageable = pageable.withSort(DEFAULT_SORT_BY);
        }

        NativeQuery query = NativeQuery.builder()
            .withQuery(boolBuilder.build()._toQuery())
            .withPageable(pageable)
            .build();
        var searchHits = elasticsearchOperations.search(query, ProductESearch.class);
        List<ProductESearch> content = searchHits.getSearchHits().stream()
            .map(SearchHit::getContent)
            .toList();
        long total = searchHits.getTotalHits();
        long page = CollectionQueryUtils.getPageNumber(req);
        long totalPages = RequestUtils.calculateTotalPage(total, CollectionQueryUtils.getPageSize(req));

        return CollectionQueryResponse.<ProductESearch>builder()
            .total(total)
            .page(page)
            .totalPages(totalPages)
            .data(content)
            .build();
    }

}
