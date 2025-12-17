package sanlab.icecream.conflux.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sanlab.icecream.conflux.mapper.CategoryMapper;
import sanlab.icecream.conflux.mapper.ImageMapper;
import sanlab.icecream.conflux.mapper.ProductMapper;
import sanlab.icecream.conflux.model.source.CategorySource;
import sanlab.icecream.conflux.model.source.ImageSource;
import sanlab.icecream.conflux.model.source.ProductCategorySource;
import sanlab.icecream.conflux.model.source.ProductImageSource;
import sanlab.icecream.conflux.model.source.ProductSource;
import sanlab.icecream.conflux.repository.queue.ProductEnrichmentSinkRepository;
import sanlab.icecream.conflux.repository.rsocketclient.ConsulCategoryRSocketRepository;
import sanlab.icecream.conflux.repository.rsocketclient.ConsulImageRSocketRepository;
import sanlab.icecream.conflux.repository.rsocketclient.ConsulProductRSocketRepository;
import sanlab.icecream.fundamentum.dto.core.CategoryDto;
import sanlab.icecream.fundamentum.dto.core.ImageDto;
import sanlab.icecream.fundamentum.utils.LogUtils;

import java.util.List;
import java.util.Optional;

import static sanlab.icecream.fundamentum.constant.TableName.QUEUE_CATEGORY;
import static sanlab.icecream.fundamentum.constant.TableName.QUEUE_IMAGE;
import static sanlab.icecream.fundamentum.constant.TableName.QUEUE_PRODUCT;
import static sanlab.icecream.fundamentum.constant.TableName.QUEUE_PRODUCT_CATEGORY;
import static sanlab.icecream.fundamentum.constant.TableName.QUEUE_PRODUCT_IMAGE;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductEnrichmentService {

    private final ProductEnrichmentSinkRepository productSinkRepository;
    private final ConsulProductRSocketRepository consulProductRepository;
    private final ConsulCategoryRSocketRepository consulCategoryRepository;
    private final ConsulImageRSocketRepository consulImageRepository;

    private final ProductMapper productMapper;
    private final CategoryMapper categoryMapper;
    private final ImageMapper imageMapper;
    private final ObjectMapper objectMapper;

    public void process(String topic, JsonNode payload) {
        if (StringUtils.isEmpty(topic)) {
            LogUtils.logInfo(log, "Received empty topic, skipping processing.");
            return;
        }
        if (Optional.ofNullable(payload).filter(inner -> !inner.isEmpty()).isEmpty()) {
            LogUtils.logInfo(log, "Received empty product payload, skipping processing.");
            return;
        }
        switch(topic) {
            case QUEUE_PRODUCT -> processProduct(payload);
            case QUEUE_CATEGORY -> processCategory(payload);
            case QUEUE_IMAGE -> processImage(payload);
            case QUEUE_PRODUCT_CATEGORY -> processProductCategory(payload);
            case QUEUE_PRODUCT_IMAGE -> processProductImage(payload);
        }
    }

    private void processProduct(JsonNode payload) {
        var productSource = objectMapper.convertValue(payload, ProductSource.class);
        List<CategoryDto> categories = consulProductRepository.getCategoriesByProductId(productSource.getId());
        List<ImageDto> media = consulProductRepository.getMediaByProductId(productSource.getId());
        Optional<ImageDto> featuredBannerOpt = consulProductRepository.getFeaturedBannerByProductId(productSource.getId());
        var productEnriched = productMapper.sourceToEnriched(productSource);
        var categoryEnrichedList = categoryMapper.dtoToEnriched(categories);
        var mediaEnrichedList = imageMapper.dtoToEnriched(media);
        productEnriched.setCategories(categoryEnrichedList);
        productEnriched.setMedia(mediaEnrichedList);
        featuredBannerOpt.ifPresent(inner -> {
            var featuredBannerEnriched = imageMapper.dtoToEnriched(inner);
            productEnriched.setFeaturedBanner(featuredBannerEnriched);
        });
        productSinkRepository.produce(productEnriched);
    }

    private void processCategory(JsonNode payload) {
        var categorySource = objectMapper.convertValue(payload, CategorySource.class);
        var productList = consulCategoryRepository.getProductsByCategoryId(categorySource.getId());
        var productEnrichedList = productMapper.extendedDtoToEnriched(productList);
        productEnrichedList.forEach(productSinkRepository::produce);
    }

    private void processImage(JsonNode payload) {
        var imageSource = objectMapper.convertValue(payload, ImageSource.class);
        var productList = consulImageRepository.getProductsByImageId(imageSource.getId());
        var productEnrichedList = productMapper.extendedDtoToEnriched(productList);
        productEnrichedList.forEach(productSinkRepository::produce);
    }

    private void processProductCategory(JsonNode payload) {
        var productCategory = objectMapper.convertValue(payload, ProductCategorySource.class);
        var productOpt = consulProductRepository.getProductById(productCategory.getProductId());
        if (productOpt.isEmpty()) {
            LogUtils.logInfo(log, "Product not found for id: {}", productCategory.getProductId());
            return;
        }
        var productEnriched = productMapper.extendedDtoToEnriched(productOpt.get());
        productSinkRepository.produce(productEnriched);

    }

    private void processProductImage(JsonNode payload) {
        var productImage = objectMapper.convertValue(payload, ProductImageSource.class);
        var productOpt = consulProductRepository.getProductById(productImage.getProductId());
        if (productOpt.isEmpty()) {
            LogUtils.logInfo(log, "Product not found for id: {}", productImage.getProductId());
            return;
        }
        var productEnriched = productMapper.extendedDtoToEnriched(productOpt.get());
        productSinkRepository.produce(productEnriched);
    }

}
