package sanlab.icecream.conflux.repository.rsocketclient.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import sanlab.icecream.conflux.repository.rsocketclient.ConsulCategoryRSocketRepository;
import sanlab.icecream.fundamentum.dto.exntended.ProductExtendedDto;
import sanlab.icecream.fundamentum.utils.LogUtils;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

import static sanlab.icecream.fundamentum.constant.ConsulRSocketRoute.GET_PRODUCTS_BY_CATEGORY_ID;

@Slf4j
@Component
@RequiredArgsConstructor
public class ConsulCategoryRSocketRepositoryImpl implements ConsulCategoryRSocketRepository {

    private final RSocketRequester consulRSocketClient;

    private static final int RETRY_COUNT = 1;
    private static final Duration TIMEOUT_DURATION = Duration.ofMinutes(30);

    @Override
    public List<ProductExtendedDto> getProductsByCategoryId(String categoryId) {
        return consulRSocketClient.route(GET_PRODUCTS_BY_CATEGORY_ID, categoryId)
                .retrieveFlux(ProductExtendedDto.class)
                .collectList()
                .retry(RETRY_COUNT)
                .timeout(TIMEOUT_DURATION)
                .onErrorResume(ex -> {
                    LogUtils.logError(log, "Error fetching products by category ID {}: {}", categoryId, ex.getMessage());
                    return Mono.just(Collections.emptyList());
                })
                .blockOptional()
                .orElse(Collections.emptyList());
    }

}
