package sanlab.icecream.product.consumer;

import java.util.List;

import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.lognet.springboot.grpc.GRpcService;

import sanlab.icecream.product.service.CategoryService;
import sanlab.icecream.product.service.ProductService;
import sanlab.icecream.sharedlib.exception.ItemNotFoundException;
import sanlab.icecream.sharedlib.proto.CategoryRequest;
import sanlab.icecream.sharedlib.proto.CategoryResponse;
import sanlab.icecream.sharedlib.proto.CategoryResponseCollection;
import sanlab.icecream.sharedlib.proto.PageInfoRequest;
import sanlab.icecream.sharedlib.proto.ProductRequest;
import sanlab.icecream.sharedlib.proto.ProductResponse;
import sanlab.icecream.sharedlib.proto.ProductResponseCollection;
import sanlab.icecream.sharedlib.proto.ProductServiceGrpc;


@GRpcService
@RequiredArgsConstructor
public class GrpcConsumer extends ProductServiceGrpc.ProductServiceImplBase {

    private final ProductService productService;
    private final CategoryService categoryService;

    @Override
    public void getAllProducts(PageInfoRequest request, StreamObserver<ProductResponseCollection> responseObserver) {
        List<ProductResponse> productList = productService.getAllProducts(request);
        ProductResponseCollection response = ProductResponseCollection.newBuilder()
            .addAllProductResponse(productList).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getProductById(ProductRequest request, StreamObserver<ProductResponse> responseObserver) {
        try {
            ProductResponse response = productService.getProductById(request.getProductId());
            responseObserver.onNext(response);
        } catch (ItemNotFoundException ex) {
            responseObserver.onError(ex);
        } finally {
            responseObserver.onCompleted();
        }
    }

    @Override
    public void getProductListFromCategoryId(CategoryRequest request, StreamObserver<ProductResponseCollection> responseObserver) {
        try {
            List<ProductResponse> productList = productService.getProductListFromCategoryId(request.getCategoryId());
            ProductResponseCollection response = ProductResponseCollection.newBuilder()
                .addAllProductResponse(productList).build();
            responseObserver.onNext(response);
        } catch (ItemNotFoundException ex) {
            responseObserver.onError(ex);
        } finally {
            responseObserver.onCompleted();
        }
    }

    @Override
    public void getAllCategories(Empty request, StreamObserver<CategoryResponseCollection> responseObserver) {
        List<CategoryResponse> categoryList = categoryService.getAllCategories();
        CategoryResponseCollection response = CategoryResponseCollection.newBuilder()
            .addAllCategoryResponse(categoryList)
            .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getCategoryById(CategoryRequest request, StreamObserver<CategoryResponse> responseObserver) {
        try {
            CategoryResponse response = categoryService.getCategoryById(request.getCategoryId());
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
            CategoryResponse response = categoryService.getCategoryByProductId(request.getProductId());
            responseObserver.onNext(response);
        } catch (ItemNotFoundException ex) {
            responseObserver.onError(ex);
        } finally {
            responseObserver.onCompleted();
        }
    }
}
