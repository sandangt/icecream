package sanlab.icecream.memoir.internalcontroller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sanlab.icecream.fundamentum.contractmodel.request.CollectionQueryRequest;
import sanlab.icecream.fundamentum.dto.AuditLogDto;

import java.util.List;
import java.util.UUID;

import static sanlab.icecream.fundamentum.constant.MemoirRSocketRoute.ROUTE_GET_ALL;
import static sanlab.icecream.fundamentum.constant.MemoirRSocketRoute.ROUTE_GET_BY_ID;

@Controller
public class AuditLogRSocketController {

    private static final List<AuditLogDto> LIST_RESPONSE = List.of(
        AuditLogDto.builder()
            .id(UUID.randomUUID())
            .userId("userid1")
            .email("userid1@email.com")
            .build(),
        AuditLogDto.builder()
            .id(UUID.randomUUID())
            .userId("userid2")
            .email("userid2@email.com")
            .build()
    );

    @MessageMapping(ROUTE_GET_ALL)
    public Flux<AuditLogDto> get(@Payload CollectionQueryRequest req) {
        return Flux.fromIterable(LIST_RESPONSE);
    }

    @MessageMapping(ROUTE_GET_BY_ID)
    public Mono<AuditLogDto> getById(String id) {
        return Mono.just(LIST_RESPONSE.getFirst());
    }

}
