package sanlab.icecream.order.consumer;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.lognet.springboot.grpc.GRpcService;
import sanlab.icecream.order.service.OrderItemService;
import sanlab.icecream.order.service.OrderService;
import sanlab.icecream.sharedlib.proto.OrderCollectionResponse;
import sanlab.icecream.sharedlib.proto.OrderItemCollectionResponse;
import sanlab.icecream.sharedlib.proto.OrderItemRequest;
import sanlab.icecream.sharedlib.proto.OrderItemResponse;
import sanlab.icecream.sharedlib.proto.OrderRequest;
import sanlab.icecream.sharedlib.proto.OrderResponse;
import sanlab.icecream.sharedlib.proto.OrderServiceGrpc;
import sanlab.icecream.sharedlib.proto.PageInfoRequest;


@GRpcService
@RequiredArgsConstructor
public class GrpcConsumer extends OrderServiceGrpc.OrderServiceImplBase {
    private final OrderService orderService;
    private final OrderItemService orderItemService;

    @Override
    public void getAllOrders(PageInfoRequest request, StreamObserver<OrderCollectionResponse> responseObserver) {
        super.getAllOrders(request, responseObserver);
    }

    @Override
    public void getOrderById(OrderRequest request, StreamObserver<OrderResponse> responseObserver) {
        super.getOrderById(request, responseObserver);
    }

    @Override
    public void getOrderItemList(OrderRequest request, StreamObserver<OrderItemCollectionResponse> responseObserver) {
        super.getOrderItemList(request, responseObserver);
    }

    @Override
    public void getOrderItemById(OrderItemRequest request, StreamObserver<OrderItemResponse> responseObserver) {
        super.getOrderItemById(request, responseObserver);
    }
}
