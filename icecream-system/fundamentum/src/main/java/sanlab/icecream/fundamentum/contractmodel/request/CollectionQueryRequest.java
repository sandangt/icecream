package sanlab.icecream.fundamentum.contractmodel.request;

import lombok.Builder;
import lombok.Data;
import sanlab.icecream.fundamentum.constant.ESortingOrder;

import java.util.List;

@Data
@Builder
public class CollectionQueryRequest {

    private PaginationRequest pagination;
    private SortingRequest sorting;
    private FiltersRequest filters;

    public static final int DEFAULT_PAGE_NUMBER = 1;
    public static final int DEFAULT_PAGE_SIZE = 10;

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

}
