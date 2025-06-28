package sanlab.icecream.consul.repository.rsocketclient.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Component;
import sanlab.icecream.consul.repository.rsocketclient.IAuditLogRsockRepository;
import sanlab.icecream.fundamentum.dto.AuditLogDto;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuditLogRsockRepository implements IAuditLogRsockRepository {

    private final RSocketRequester memoirRsockClient;

    private static final String ROUTE_GET_ALL = "audit-logs.get";
    private static final String ROUTE_GET_BY_ID = "audit-logs.get.id";

    @Override
    public List<AuditLogDto> getAll() {
        var response = memoirRsockClient
            .route(ROUTE_GET_ALL)
            .retrieveFlux(AuditLogDto.class);
        return response.toStream()
            .toList();
    }

    @Override
    public Optional<AuditLogDto> getById() {
        var response = memoirRsockClient
            .route(ROUTE_GET_BY_ID)
            .data("this is id")
            .retrieveMono(AuditLogDto.class)
            .block();
        return Optional.ofNullable(response);
    }
}
