package sanlab.icecream.consul.internalcontroller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sanlab.icecream.consul.exception.HttpInternalServerErrorException;
import sanlab.icecream.consul.exception.HttpNotFoundException;
import sanlab.icecream.consul.internalcontroller.exceptionadvisor.RSocketExceptionAdvisorMixin;
import sanlab.icecream.consul.service.CategoryService;
import sanlab.icecream.consul.service.ImageService;
import sanlab.icecream.consul.service.ProductService;
import sanlab.icecream.fundamentum.dto.core.CategoryDto;
import sanlab.icecream.fundamentum.dto.core.ImageDto;
import sanlab.icecream.fundamentum.dto.exntended.ProductExtendedDto;
import sanlab.icecream.fundamentum.exception.IcRuntimeException;
import sanlab.icecream.fundamentum.utils.LogUtils;

import java.util.UUID;

import static sanlab.icecream.consul.exception.ConsulErrorModel.REPOSITORY_CATEGORY_NOT_FOUND;
import static sanlab.icecream.consul.exception.ConsulErrorModel.REPOSITORY_IMAGE_NOT_FOUND;
import static sanlab.icecream.consul.exception.ConsulErrorModel.REPOSITORY_PRODUCT_NOT_FOUND;
import static sanlab.icecream.fundamentum.constant.ConsulRSocketRoute.GET_CATEGORIES_BY_PRODUCT_ID;
import static sanlab.icecream.fundamentum.constant.ConsulRSocketRoute.GET_FEATURED_BANNER_BY_PRODUCT_ID;
import static sanlab.icecream.fundamentum.constant.ConsulRSocketRoute.GET_MEDIA_BY_PRODUCT_ID;
import static sanlab.icecream.fundamentum.constant.ConsulRSocketRoute.GET_PRODUCTS_BY_CATEGORY_ID;
import static sanlab.icecream.fundamentum.constant.ConsulRSocketRoute.GET_PRODUCTS_BY_IMAGE_ID;
import static sanlab.icecream.fundamentum.constant.ConsulRSocketRoute.GET_PRODUCT_BY_ID;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ProductEnrichmentRSocketController implements RSocketExceptionAdvisorMixin {

    private final ProductService productService;
    private final ImageService imageService;
    private final CategoryService categoryService;

    @MessageMapping(GET_PRODUCT_BY_ID)
    public Mono<ProductExtendedDto> getProductById(@DestinationVariable String id) {
        try {
            var result = productService.getById(UUID.fromString(id));
            return Mono.just(result);
        } catch (IcRuntimeException ex) {
            LogUtils.logError(log, "Error retrieving product with id: {}", id, ex);
            var error = ex.getError();
            if (REPOSITORY_PRODUCT_NOT_FOUND.equals(error)) throw new HttpNotFoundException(ex);
            throw new HttpInternalServerErrorException(ex);
        }
    }

    @MessageMapping(GET_CATEGORIES_BY_PRODUCT_ID)
    public Flux<CategoryDto> getCategoriesByProductId(@DestinationVariable String id) {
        try {
            var categories = productService.getCategoriesByProductId(UUID.fromString(id));
            return Flux.fromIterable(categories);
        } catch (IcRuntimeException ex) {
            LogUtils.logError(log, "Error retrieving categories for product id: {}", id, ex);
            var error = ex.getError();
            if (REPOSITORY_PRODUCT_NOT_FOUND.equals(error)) throw new HttpNotFoundException(ex);
            throw new HttpInternalServerErrorException(ex);
        }
    }


    @MessageMapping(GET_MEDIA_BY_PRODUCT_ID)
    public Flux<ImageDto> getMediaByProductId(@DestinationVariable String id) {
        try {
            var media = productService.getMediaByProductId(UUID.fromString(id));
            return Flux.fromIterable(media);
        } catch (IcRuntimeException ex) {
            LogUtils.logError(log, "Error retrieving media for product id: {}", id, ex);
            var error = ex.getError();
            if (REPOSITORY_PRODUCT_NOT_FOUND.equals(error)) throw new HttpNotFoundException(ex);
            throw new HttpInternalServerErrorException(ex);
        }
    }

    @MessageMapping(GET_FEATURED_BANNER_BY_PRODUCT_ID)
    public Mono<ImageDto> getFeaturedBannerByProductId(@DestinationVariable String id) {
        try {
            var featuredBanner = productService.getFeaturedBannerByProductId(UUID.fromString(id));
            return Mono.justOrEmpty(featuredBanner);
        } catch (IcRuntimeException ex) {
            LogUtils.logError(log, "Error retrieving featured banner for product id: {}", id, ex);
            var error = ex.getError();
            if (REPOSITORY_PRODUCT_NOT_FOUND.equals(error)) throw new HttpNotFoundException(ex);
            throw new HttpInternalServerErrorException(ex);
        }
    }

    @MessageMapping(GET_PRODUCTS_BY_CATEGORY_ID)
    public Flux<ProductExtendedDto> getProductsByCategoryId(@DestinationVariable String id) {
        try {
            var products = categoryService.getAllProducts(UUID.fromString(id));
            return Flux.fromIterable(products);
        } catch (IcRuntimeException ex) {
            LogUtils.logError(log, "Error retrieving products for category id: {}", id, ex);
            var error = ex.getError();
            if (REPOSITORY_CATEGORY_NOT_FOUND.equals(error)) throw new HttpNotFoundException(ex);
            throw new HttpInternalServerErrorException(ex);
        }
    }

    @MessageMapping(GET_PRODUCTS_BY_IMAGE_ID)
    public Flux<ProductExtendedDto> getProductsByImageId(@DestinationVariable String id) {
        try {
            var products = imageService.getAllProducts(UUID.fromString(id));
            return Flux.fromIterable(products);
        } catch (IcRuntimeException ex) {
            LogUtils.logError(log, "Error retrieving products for image id: {}", id, ex);
            var error = ex.getError();
            if (REPOSITORY_IMAGE_NOT_FOUND.equals(error)) throw new HttpNotFoundException(ex);
            throw new HttpInternalServerErrorException(ex);
        }
    }

}
