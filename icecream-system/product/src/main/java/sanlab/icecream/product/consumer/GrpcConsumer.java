package sanlab.icecream.product.consumer;

import java.util.List;

import com.google.protobuf.Empty;
import icecream.sharedlib.proto.CategoryCollectionResponse;
import icecream.sharedlib.proto.CategoryDTO;
import icecream.sharedlib.proto.CategoryRequest;
import icecream.sharedlib.proto.CategoryResponse;
import icecream.sharedlib.proto.PageInfoRequest;
import icecream.sharedlib.proto.ProductCollectionResponse;
import icecream.sharedlib.proto.ProductDTO;
import icecream.sharedlib.proto.ProductRequest;
import icecream.sharedlib.proto.ProductResponse;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.lognet.springboot.grpc.GRpcService;

import icecream.sharedlib.proto.ProductServiceGrpc;
import sanlab.icecream.product.service.CategoryService;
import sanlab.icecream.product.service.ProductService;
import sanlab.icecream.sharedlib.exception.ItemNotFoundException;


@GRpcService
@RequiredArgsConstructor
public class GrpcConsumer extends ProductServiceGrpc.ProductServiceImplBase {
    private final ProductService productService;
    private final CategoryService categoryService;

    @Override
    public void getAllProducts(PageInfoRequest request,
                               StreamObserver<ProductCollectionResponse> responseObserver) {
        List<ProductDTO> productList = productService.getAllProducts(request);
        ProductCollectionResponse response = ProductCollectionResponse.newBuilder()
            .addAllProductCollection(productList).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getProductById(ProductRequest request, StreamObserver<ProductResponse> responseObserver) {
        try {
            ProductDTO product = productService.getProductById(request.getProductId());
            ProductResponse response = ProductResponse.newBuilder().setProduct(product).build();
            responseObserver.onNext(response);
        } catch (ItemNotFoundException ex) {
            responseObserver.onError(ex);
        } finally {
            responseObserver.onCompleted();
        }
    }

    @Override
    public void getProductListFromCategoryId(CategoryRequest request,
                                             StreamObserver<ProductCollectionResponse> responseObserver) {
        try {
            List<ProductDTO> productList = productService.getProductListFromCategoryId(request.getCategoryId());
            ProductCollectionResponse response = ProductCollectionResponse.newBuilder()
                .addAllProductCollection(productList).build();
            responseObserver.onNext(response);
        } catch (ItemNotFoundException ex) {
            responseObserver.onError(ex);
        } finally {
            responseObserver.onCompleted();
        }
    }

    @Override
    public void getAllCategories(Empty request, StreamObserver<CategoryCollectionResponse> responseObserver) {
        List<CategoryDTO> categoryList = categoryService.getAllCategories();
        CategoryCollectionResponse response = CategoryCollectionResponse.newBuilder()
            .addAllCategoryCollection(categoryList)
            .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getCategoryByID(CategoryRequest request, StreamObserver<CategoryResponse> responseObserver) {
        try {
            CategoryDTO category = categoryService.getCategoryById(request.getCategoryId());
            CategoryResponse response = CategoryResponse.newBuilder().setCategory(category).build();
            responseObserver.onNext(response);
        } catch (ItemNotFoundException ex) {
            responseObserver.onError(ex);
        } finally {
            responseObserver.onCompleted();
        }
    }

    @Override
    public void getCategoryFromProductId(ProductRequest request, StreamObserver<CategoryResponse> responseObserver) {
        try {
            CategoryDTO category = categoryService.getCategoryByProductId(request.getProductId());
            CategoryResponse response = CategoryResponse.newBuilder().setCategory(category).build();
            responseObserver.onNext(response);
        } catch (ItemNotFoundException ex) {
            responseObserver.onError(ex);
        } finally {
            responseObserver.onCompleted();
        }
    }
}
