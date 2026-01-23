package sanlab.icecream.conflux.repository.rsocketclient.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import sanlab.icecream.conflux.repository.rsocketclient.ConsulProductRSocketRepository;
import sanlab.icecream.fundamentum.dto.core.CategoryDto;
import sanlab.icecream.fundamentum.dto.core.ImageDto;
import sanlab.icecream.fundamentum.dto.exntended.ProductExtendedDto;
import sanlab.icecream.fundamentum.utils.LogUtils;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static sanlab.icecream.fundamentum.constant.ConsulRSocketRoute.GET_CATEGORIES_BY_PRODUCT_ID;
import static sanlab.icecream.fundamentum.constant.ConsulRSocketRoute.GET_FEATURED_BANNER_BY_PRODUCT_ID;
import static sanlab.icecream.fundamentum.constant.ConsulRSocketRoute.GET_MEDIA_BY_PRODUCT_ID;
import static sanlab.icecream.fundamentum.constant.ConsulRSocketRoute.GET_PRODUCT_BY_ID;

@Slf4j
@Component
@RequiredArgsConstructor
public class ConsulProductRSocketRepositoryImpl implements ConsulProductRSocketRepository {

    private final RSocketRequester consulRSocketClient;

    private static final int RETRY_COUNT = 1;
    private static final Duration TIMEOUT_DURATION = Duration.ofMinutes(30);

    @Override
    public Optional<ProductExtendedDto> getProductById(String productId) {
        return consulRSocketClient.route(GET_PRODUCT_BY_ID, productId)
            .retrieveMono(ProductExtendedDto.class)
            .retry(RETRY_COUNT)
            .timeout(TIMEOUT_DURATION)
            .onErrorResume(ex -> {
                LogUtils.logError(log, "Error requesting product by id: {}", productId, ex);
                return Mono.empty();
            })
            .blockOptional();
    }

    @Override
    public List<CategoryDto> getCategoriesByProductId(String productId) {
        return consulRSocketClient.route(GET_CATEGORIES_BY_PRODUCT_ID, productId)
            .retrieveFlux(CategoryDto.class)
            .collectList()
            .retry(RETRY_COUNT)
            .timeout(TIMEOUT_DURATION)
            .onErrorResume(ex -> {
                LogUtils.logError(log, "Error requesting categories by product id: {}", productId, ex);
                return Mono.just(Collections.emptyList());
            })
            .blockOptional()
            .orElse(Collections.emptyList());
    }

    @Override
    public List<ImageDto> getMediaByProductId(String productId) {
        return consulRSocketClient.route(GET_MEDIA_BY_PRODUCT_ID, productId)
            .retrieveFlux(ImageDto.class)
            .collectList()
            .retry(RETRY_COUNT)
            .timeout(TIMEOUT_DURATION)
            .onErrorResume(ex -> {
                LogUtils.logError(log, "Error requesting media by product id: {}", productId, ex);
                return Mono.just(Collections.emptyList());
            })
            .blockOptional()
            .orElse(Collections.emptyList());
    }

    @Override
    public Optional<ImageDto> getFeaturedBannerByProductId(String productId) {
        return consulRSocketClient.route(GET_FEATURED_BANNER_BY_PRODUCT_ID, productId)
            .retrieveMono(ImageDto.class)
            .retry(RETRY_COUNT)
            .timeout(TIMEOUT_DURATION)
            .onErrorResume(ex -> {
                LogUtils.logError(log, "Error requesting featured banner by product id: {}", productId, ex);
                return Mono.empty();
            })
            .blockOptional();
    }
}
