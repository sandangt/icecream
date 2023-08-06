package sanlab.icecream.gateway.repository.product;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import io.grpc.ManagedChannel;
import io.grpc.StatusRuntimeException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Repository;

import sanlab.icecream.gateway.viewmodel.PageInfoRequestVm;
import sanlab.icecream.sharedlib.constant.GrpcChannel;
import sanlab.icecream.sharedlib.constant.KafkaTopic;
import sanlab.icecream.sharedlib.proto.CategoryRequest;
import sanlab.icecream.sharedlib.proto.PageInfoRequest;
import sanlab.icecream.sharedlib.proto.ProductCategoryRelationship;
import sanlab.icecream.sharedlib.proto.ProductDTO;
import sanlab.icecream.sharedlib.proto.ProductRequest;
import sanlab.icecream.sharedlib.proto.ProductResponse;
import sanlab.icecream.sharedlib.proto.ProductResponseCollection;
import sanlab.icecream.sharedlib.proto.ProductServiceGrpc;


@Repository
public class ProductRepository {
    private final ProductServiceGrpc.ProductServiceBlockingStub stub;
    private final KafkaTemplate<String, ProductDTO> productProducer;
    private final KafkaTemplate<String, ProductCategoryRelationship> relationshipProducer;
    public ProductRepository(
        @Qualifier(GrpcChannel.PRODUCT) ManagedChannel productChannel,
        @Qualifier("product-producer") KafkaTemplate<String, ProductDTO> productProducer,
        @Qualifier("product-category-producer") KafkaTemplate<String, ProductCategoryRelationship> relationshipProducer
    ) {
        this.stub = ProductServiceGrpc.newBlockingStub(productChannel);
        this.productProducer = productProducer;
        this.relationshipProducer = relationshipProducer;
    }

    public Optional<List<ProductResponse>> getAllProducts(PageInfoRequestVm pageInfo) {
        PageInfoRequest request = PageInfoRequest.newBuilder()
            .setOffset(pageInfo.offset()).setLimit(pageInfo.limit())
            .build();
        ProductResponseCollection response = stub.getAllProducts(request);
        return Optional.of(response.getProductResponseList());
    }

    public Optional<ProductResponse> getProductById(Long productId) {
        ProductRequest request = ProductRequest.newBuilder()
            .setProductId(productId)
            .build();
        try {
            ProductResponse response = stub.getProductById(request);
            return Optional.of(response);
        } catch (StatusRuntimeException e) {
            return Optional.empty();
        }
    }

    public Optional<List<ProductResponse>> getProductListFromCategoryId(Long categoryId) {
        CategoryRequest request = CategoryRequest.newBuilder().setCategoryId(categoryId).build();
        try {
            ProductResponseCollection response = stub.getProductListFromCategoryId(request);
            return Optional.of(response.getProductResponseList());
        } catch (StatusRuntimeException e) {
            return Optional.empty();
        }
    }

    public void insertProduct(ProductDTO messageValue) {
        productProducer.send(
            KafkaTopic.INSERT_PRODUCT,
            ZonedDateTime.now(ZoneOffset.UTC).toString(),
            messageValue
        );
    }

    public void updateProduct(ProductDTO messageValue) {
        productProducer.send(
            KafkaTopic.UPDATE_PRODUCT,
            ZonedDateTime.now(ZoneOffset.UTC).toString(),
            messageValue
        );
    }

    public void labelProduct(Long productId, Long categoryId) {
        ProductCategoryRelationship message = ProductCategoryRelationship.newBuilder()
            .setCategoryId(categoryId)
            .setProductId(productId)
            .setInRelationship(true)
            .build();
        relationshipProducer.send(
            KafkaTopic.LABEL_PRODUCT,
            ZonedDateTime.now(ZoneOffset.UTC).toString(),
            message
        );
    }

    public void unlabelProduct(Long productId, Long categoryId) {
        ProductCategoryRelationship message = ProductCategoryRelationship.newBuilder()
            .setCategoryId(categoryId)
            .setProductId(productId)
            .setInRelationship(false)
            .build();
        relationshipProducer.send(
            KafkaTopic.LABEL_PRODUCT,
            ZonedDateTime.now(ZoneOffset.UTC).toString(),
            message
        );
    }
}
