package sanlab.icecream.consul.utils;

import io.micrometer.common.util.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import sanlab.icecream.fundamentum.constant.ESortingOrder;
import sanlab.icecream.fundamentum.contractmodel.request.CollectionQueryRequest;
import sanlab.icecream.fundamentum.contractmodel.request.CollectionQueryRequest.PaginationRequest;

import java.util.Optional;

public final class CollectionQueryUtils {

    private CollectionQueryUtils() {}

    private static final int DEFAULT_PAGE_STARTER_OFFSET = 0;
    private static final int DEFAULT_PAGE_LIMIT = 10;

    public static PageRequest getPageRequest(CollectionQueryRequest req) {
        var reqOptional = Optional.ofNullable(req);
        var paginationOptional = reqOptional.map(CollectionQueryRequest::getPagination);
        int pageOffset = paginationOptional
            .map(PaginationRequest::getOffset)
            .filter(inner -> inner>0)
            .orElse(DEFAULT_PAGE_STARTER_OFFSET);
        int pageLimit = paginationOptional
            .map(PaginationRequest::getLimit)
            .filter(inner -> inner>0)
            .orElse(DEFAULT_PAGE_LIMIT);
        if (isSortingValid(req)) {
            return PageRequest.of(pageOffset, pageLimit);
        }
        var sortBy = Sort.by(req.getSorting().getField());
        sortBy = req.getSorting().getOrder() == ESortingOrder.ASC ? sortBy.ascending(): sortBy.descending();
        return PageRequest.of(pageOffset, pageLimit, sortBy);
    }

    public static long getPageNumber(CollectionQueryRequest req) {
        var pageReq = getPageRequest(req);
        return pageReq.getPageNumber();
    }

    public static long getPageSize(CollectionQueryRequest req) {
        var pageReq = getPageRequest(req);
        return pageReq.getPageSize();
    }

    private static boolean isSortingValid(CollectionQueryRequest req) {
        var reqOptional = Optional.ofNullable(req);
        return reqOptional.map(CollectionQueryRequest::getSorting).isEmpty()
            || reqOptional.map(CollectionQueryRequest::getFilters)
                        .map(CollectionQueryRequest.FiltersRequest::getSearchText)
                        .filter(StringUtils::isNotEmpty)
                        .isPresent();
    }

}
