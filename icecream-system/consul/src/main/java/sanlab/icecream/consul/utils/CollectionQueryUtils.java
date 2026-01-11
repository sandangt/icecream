package sanlab.icecream.consul.utils;

import io.micrometer.common.util.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import sanlab.icecream.fundamentum.constant.ESortingOrder;
import sanlab.icecream.fundamentum.contractmodel.request.CollectionQueryRequest;
import sanlab.icecream.fundamentum.contractmodel.request.CollectionQueryRequest.PaginationRequest;

import java.util.Optional;

import static sanlab.icecream.fundamentum.contractmodel.request.CollectionQueryRequest.DEFAULT_PAGE_NUMBER;
import static sanlab.icecream.fundamentum.contractmodel.request.CollectionQueryRequest.DEFAULT_PAGE_SIZE;

public final class CollectionQueryUtils {

    private CollectionQueryUtils() {}

    public static PageRequest getPageRequest(CollectionQueryRequest req) {
        var reqOptional = Optional.ofNullable(req);
        var paginationOptional = reqOptional.map(CollectionQueryRequest::getPagination);
        int pageNumber = paginationOptional
            .map(PaginationRequest::getOffset)
            .filter(inner -> inner>0)
            .orElse(DEFAULT_PAGE_NUMBER);
        int pageSize = paginationOptional
            .map(PaginationRequest::getLimit)
            .filter(inner -> inner>0)
            .orElse(DEFAULT_PAGE_SIZE);
        if (reqOptional.map(CollectionQueryRequest::getSorting).isEmpty()
        || reqOptional.map(CollectionQueryRequest::getFilters)
                    .map(CollectionQueryRequest.FiltersRequest::getSearchText)
                    .filter(StringUtils::isNotEmpty).isPresent()
        ) {
            return PageRequest.of(pageNumber, pageSize);
        }
        var sortBy = Sort.by(req.getSorting().getField());
        sortBy = req.getSorting().getOrder() == ESortingOrder.ASC ? sortBy.ascending(): sortBy.descending();
        return PageRequest.of(pageNumber, pageSize, sortBy);
    }

    public static long getPageNumber(CollectionQueryRequest req) {
        var pageReq = getPageRequest(req);
        return pageReq.getPageNumber();
    }

    public static long getPageSize(CollectionQueryRequest req) {
        var pageReq = getPageRequest(req);
        return pageReq.getPageSize();
    }

}
