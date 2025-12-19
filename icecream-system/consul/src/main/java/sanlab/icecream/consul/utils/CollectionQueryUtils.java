package sanlab.icecream.consul.utils;

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
        var paginationOptional = Optional.ofNullable(req.getPagination());
        int pageNumber = paginationOptional
            .map(PaginationRequest::getOffset)
            .filter(inner -> inner>0)
            .orElse(DEFAULT_PAGE_NUMBER);
        int pageSize = paginationOptional
            .map(PaginationRequest::getLimit)
            .filter(inner -> inner>0)
            .orElse(DEFAULT_PAGE_SIZE);
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
