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
import sanlab.icecream.sharedlib.constant.GrpcChannel;
import sanlab.icecream.sharedlib.constant.KafkaTopic;
import sanlab.icecream.sharedlib.proto.CategoryCollectionResponse;
import sanlab.icecream.sharedlib.proto.CategoryDTO;
import sanlab.icecream.sharedlib.proto.CategoryRequest;
import sanlab.icecream.sharedlib.proto.CategoryResponse;
import sanlab.icecream.sharedlib.proto.ProductRequest;
import sanlab.icecream.sharedlib.proto.ProductServiceGrpc;


@Repository
public class CategoryRepository {
    private final ProductServiceGrpc.ProductServiceBlockingStub stub;
    private final KafkaTemplate<String, CategoryDTO> categoryProducer;
    public CategoryRepository(
        @Qualifier(GrpcChannel.PRODUCT) ManagedChannel productChannel,
        @Qualifier("category-producer") KafkaTemplate<String, CategoryDTO> categoryProducer
    ) {
        this.stub = ProductServiceGrpc.newBlockingStub(productChannel);
        this.categoryProducer = categoryProducer;
    }

    public Optional<List<CategoryDTO>> getAllCategories() {
        CategoryCollectionResponse response = stub.getAllCategories(null);
        return Optional.of(response.getCategoryCollectionList());
    }

    public Optional<CategoryDTO> getCategoryById(Long categoryId) {
        CategoryRequest request = CategoryRequest.newBuilder().setCategoryId(categoryId).build();
        try {
            CategoryResponse response = stub.getCategoryById(request);
            return Optional.of(response.getCategory());
        } catch (StatusRuntimeException e) {
            return Optional.empty();
        }
    }

    public Optional<CategoryDTO> getCategoryFromProductId(Long productId) {
        ProductRequest request = ProductRequest.newBuilder()
            .setProductId(productId)
            .build();
        try {
            CategoryResponse response = stub.getCategoryFromProductId(request);
            return Optional.of(response.getCategory());
        } catch (StatusRuntimeException e) {
            return Optional.empty();
        }
    }

    public void insertCategory(CategoryDTO messageValue) {
        categoryProducer.send(
            KafkaTopic.INSERT_CATEGORY,
            ZonedDateTime.now(ZoneOffset.UTC).toString(),
            messageValue
        );
    }

    public void updateCategory(CategoryDTO messageValue) {
        categoryProducer.send(
            KafkaTopic.UPDATE_CATEGORY,
            ZonedDateTime.now(ZoneOffset.UTC).toString(),
            messageValue
        );
    }
}
