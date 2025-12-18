package sanlab.icecream.consul.repository.rsocketclient.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Component;
import sanlab.icecream.consul.repository.rsocketclient.AuditLogRSocketRepository;
import sanlab.icecream.fundamentum.contractmodel.request.CollectionQueryRequest;
import sanlab.icecream.fundamentum.dto.core.AuditLogDto;

import java.util.List;
import java.util.Optional;

import static sanlab.icecream.fundamentum.constant.MemoirRSocketRoute.ROUTE_GET_ALL;
import static sanlab.icecream.fundamentum.constant.MemoirRSocketRoute.ROUTE_GET_BY_ID;

@Component
@RequiredArgsConstructor
public class AuditLogRSocketRepositoryImpl implements AuditLogRSocketRepository {

    private final RSocketRequester memoirRSocketClient;

    @Override
    public List<AuditLogDto> getAll() {
        var paginationReq = CollectionQueryRequest.PaginationRequest.builder()
            .limit(10)
            .offset(1)
            .build();
        var filtersReq = CollectionQueryRequest.FiltersRequest
            .builder()
            .build();
        var sortingReq = CollectionQueryRequest.SortingRequest
            .builder()
            .field("createdAt")
            .order(sanlab.icecream.fundamentum.constant.ESortingOrder.DESC)
            .build();
        CollectionQueryRequest req = CollectionQueryRequest.builder()
            .pagination(paginationReq)
            .filters(filtersReq)
            .sorting(sortingReq)
            .build();
        var response = memoirRSocketClient
            .route(ROUTE_GET_ALL)
            .data(req)
            .retrieveFlux(AuditLogDto.class);
        return response.toStream()
            .toList();
    }

    @Override
    public Optional<AuditLogDto> getById() {
        var response = memoirRSocketClient
            .route(ROUTE_GET_BY_ID)
            .data("this is id")
            .retrieveMono(AuditLogDto.class)
            .block();
        return Optional.ofNullable(response);
    }
}
