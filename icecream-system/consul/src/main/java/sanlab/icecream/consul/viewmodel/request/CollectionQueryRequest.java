package sanlab.icecream.consul.viewmodel.request;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import sanlab.icecream.fundamentum.constant.ESortingOrder;

@Data
@Builder
public class CollectionQueryRequest {

    private String searchText;
    private PaginationRequest pagination;
    private SortingRequest sorting;

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

    public PageRequest getPageRequest() {
        int offset = pagination.getOffset();
        int limit = pagination.getLimit();
        var sortBy = Sort.by(sorting.getField());
        sortBy = sorting.getOrder() == ESortingOrder.ASC ? sortBy.ascending(): sortBy.descending();
        return PageRequest.of(offset, limit, sortBy);
    }

}
