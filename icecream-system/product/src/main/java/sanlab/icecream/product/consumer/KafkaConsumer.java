package sanlab.icecream.product.consumer;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;
import sanlab.icecream.product.service.CategoryService;
import sanlab.icecream.product.service.ProductService;
import sanlab.icecream.sharedlib.constant.KafkaTopic;
import sanlab.icecream.sharedlib.proto.CategoryDTO;
import sanlab.icecream.sharedlib.proto.ProductCategoryDTO;
import sanlab.icecream.sharedlib.proto.ProductDTO;


@Controller
@RequiredArgsConstructor
public class KafkaConsumer {
    private final ProductService productService;
    private final CategoryService categoryService;

    @KafkaListener(topics = KafkaTopic.INSERT_PRODUCT, containerFactory = "productListenerContainerFactory")
    public void insertProduct(ConsumerRecord<String, ProductDTO> payload) {
        productService.insertProduct(payload.value());
    }

    @KafkaListener(topics = KafkaTopic.UPDATE_PRODUCT, containerFactory = "productListenerContainerFactory")
    public void updateProduct(ConsumerRecord<String, ProductDTO> payload) {
        productService.updateProduct(payload.value());
    }

    @KafkaListener(topics = KafkaTopic.INSERT_CATEGORY, containerFactory = "categoryListenerContainerFactory")
    public void insertCategory(ConsumerRecord<String, CategoryDTO> payload) {
        categoryService.insertCategory(payload.value());
    }

    @KafkaListener(topics = KafkaTopic.UPDATE_CATEGORY, containerFactory = "categoryListenerContainerFactory")
    public void updateCategory(ConsumerRecord<String, CategoryDTO> payload) {
        categoryService.updateCategory(payload.value());
    }

    @KafkaListener(topics = KafkaTopic.LABEL_PRODUCT, containerFactory = "relationshipListenerContainerFactory")
    public void labelProduct(ConsumerRecord<String, ProductCategoryDTO> payload) {
        productService.modifiedRelationship(payload.value());
    }
}
