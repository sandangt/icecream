package sanlab.icecream.consul.utils;

import sanlab.icecream.consul.viewmodel.request.CollectionQueryRequest;

import java.util.Optional;

public class CollectionQueryUtils {

    private CollectionQueryUtils() {}

    public static long calculateTotalPages(CollectionQueryRequest req, long totalItems) {
        var reqOptional = Optional.ofNullable(req);
        if (reqOptional.isEmpty() || totalItems <=0) {
            return 0;
        }
        var pagination = reqOptional
            .map(CollectionQueryRequest::getPageRequest)
            .get();
        return (totalItems + pagination.getPageSize() - 1) / pagination.getPageSize();
    }


}
