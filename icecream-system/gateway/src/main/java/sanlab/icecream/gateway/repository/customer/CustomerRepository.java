package sanlab.icecream.gateway.repository.customer;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Optional;

import io.grpc.ManagedChannel;
import io.grpc.StatusRuntimeException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Repository;
import sanlab.icecream.sharedlib.constant.GrpcChannel;
import sanlab.icecream.sharedlib.constant.KafkaTopic;
import sanlab.icecream.sharedlib.proto.CustomerDTO;
import sanlab.icecream.sharedlib.proto.CustomerRequest;
import sanlab.icecream.sharedlib.proto.CustomerResponse;
import sanlab.icecream.sharedlib.proto.CustomerServiceGrpc;


@Repository
public class CustomerRepository {
    private final CustomerServiceGrpc.CustomerServiceBlockingStub stub;
    private final KafkaTemplate<String, CustomerDTO> customerProducer;
    public CustomerRepository(
        @Qualifier(GrpcChannel.CUSTOMER) ManagedChannel customerChannel,
        @Qualifier("customer-producer") KafkaTemplate<String, CustomerDTO> customerProducer
    ) {
        this.stub = CustomerServiceGrpc.newBlockingStub(customerChannel);
        this.customerProducer = customerProducer;
    }

    public Optional<CustomerResponse> getCustomerById(Long customerId) {
        CustomerRequest request = CustomerRequest.newBuilder()
            .setCustomerId(customerId)
            .build();
        try {
            CustomerResponse response = stub.getCustomerById(request);
            return Optional.ofNullable(response);
        } catch (StatusRuntimeException ex) {
            return Optional.empty();
        }
    }

    public Optional<CustomerResponse> getCustomerByUsername(String customerEmail) {
        CustomerRequest request = CustomerRequest.newBuilder()
            .setCustomerUsername(customerEmail)
            .build();
        try {
            CustomerResponse response = stub.getCustomerByUsername(request);
            return Optional.ofNullable(response);
        } catch (StatusRuntimeException ex) {
            return Optional.empty();
        }
    }

    public void insertCustomer(CustomerDTO messageValue) {
        customerProducer.send(
            KafkaTopic.INSERT_CUSTOMER,
            ZonedDateTime.now(ZoneOffset.UTC).toString(),
            messageValue
        );
    }

    public void updateCustomer(CustomerDTO messageValue) {
        customerProducer.send(
            KafkaTopic.UPDATE_CUSTOMER,
            ZonedDateTime.now(ZoneOffset.UTC).toString(),
            messageValue
        );
    }

}
