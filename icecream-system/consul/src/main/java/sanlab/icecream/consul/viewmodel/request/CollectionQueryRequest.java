package sanlab.icecream.consul.viewmodel.request;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import sanlab.icecream.fundamentum.constant.ESortingOrder;

import java.util.List;
import java.util.Optional;

@Data
@Builder
public class CollectionQueryRequest {

    private PaginationRequest pagination;
    private SortingRequest sorting;
    private FiltersRequest filters;

    private static final int DEFAULT_PAGE_NUMBER = 1;
    private static final int DEFAULT_PAGE_SIZE = 10;

    @Data
    @Builder
    public static class PaginationRequest {
        private int limit;
        private int offset;
    }

    @Data
    @Builder
    public static class SortingRequest {
        private String field;
        private ESortingOrder order;
    }

    @Data
    @Builder
    public static class FiltersRequest {
        private String searchText;
        private List<String> categories;
        private List<String> statuses;
        private Long minPrice;
        private Long maxPrice;
        private Long createdBefore;
        private Long createdAfter;
        private Long modifiedBefore;
        private Long modifiedAfter;
    }

    public PageRequest getPageRequest() {
        var paginationOptional = Optional.ofNullable(pagination);
        int pageNumber = paginationOptional
            .map(PaginationRequest::getOffset)
            .filter(inner -> inner>0)
            .orElse(DEFAULT_PAGE_NUMBER);
        int pageSize = paginationOptional
            .map(PaginationRequest::getLimit)
            .filter(inner -> inner>0)
            .orElse(DEFAULT_PAGE_SIZE);
        var sortBy = Sort.by(sorting.getField());
        sortBy = sorting.getOrder() == ESortingOrder.ASC ? sortBy.ascending(): sortBy.descending();
        return PageRequest.of(pageNumber, pageSize, sortBy);
    }

    public long getPageNumber() {
        var pageReq = getPageRequest();
        return pageReq.getPageNumber();
    }

}
