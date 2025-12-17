package sanlab.icecream.consul.repository.rsocketclient.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Component;
import sanlab.icecream.consul.repository.rsocketclient.AuditLogRSocketRepository;
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
        var response = memoirRSocketClient
            .route(ROUTE_GET_ALL)
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
