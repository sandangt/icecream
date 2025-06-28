package sanlab.icecream.memoir.controller;

import org.springframework.messaging.rsocket.service.RSocketExchange;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sanlab.icecream.fundamentum.dto.AuditLogDto;

import java.util.List;
import java.util.UUID;

@Controller
public class AuditLogController {

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

    @RSocketExchange("audit-logs.get")
    public Flux<AuditLogDto> get() {
        return Flux.fromIterable(LIST_RESPONSE);
    }

    @RSocketExchange("audit-logs.get.id")
    public Mono<AuditLogDto> getById(String id) {
        return Mono.just(LIST_RESPONSE.getFirst());
    }

}
