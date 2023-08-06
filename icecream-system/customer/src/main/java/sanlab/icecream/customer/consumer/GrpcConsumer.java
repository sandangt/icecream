package sanlab.icecream.customer.consumer;

import java.util.List;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.lognet.springboot.grpc.GRpcService;
import sanlab.icecream.customer.service.CustomerService;
import sanlab.icecream.sharedlib.exception.ItemNotFoundException;
import sanlab.icecream.sharedlib.proto.CustomerCollectionResponse;
import sanlab.icecream.sharedlib.proto.CustomerDTO;
import sanlab.icecream.sharedlib.proto.CustomerRequest;
import sanlab.icecream.sharedlib.proto.CustomerResponse;
import sanlab.icecream.sharedlib.proto.CustomerServiceGrpc;
import sanlab.icecream.sharedlib.proto.PageInfoRequest;


@GRpcService
@RequiredArgsConstructor
public class GrpcConsumer extends CustomerServiceGrpc.CustomerServiceImplBase {

    private final CustomerService customerService;

    @Override
    public void getAllCustomers(PageInfoRequest request, StreamObserver<CustomerCollectionResponse> responseObserver) {
        List<CustomerDTO> customerList = customerService.getAllCustomers(request);
        CustomerCollectionResponse response = CustomerCollectionResponse.newBuilder()
            .addAllCustomerCollection(customerList).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getCustomerById(CustomerRequest request, StreamObserver<CustomerResponse> responseObserver) {
        try {
            CustomerDTO customer = customerService.getCustomerById(request.getCustomerId());
            CustomerResponse response = CustomerResponse.newBuilder().setCustomer(customer).build();
            responseObserver.onNext(response);
        } catch (ItemNotFoundException ex) {
            responseObserver.onError(ex);
        } finally {
            responseObserver.onCompleted();
        }
    }
}
