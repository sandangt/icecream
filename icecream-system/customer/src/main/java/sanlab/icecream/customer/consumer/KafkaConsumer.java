package sanlab.icecream.customer.consumer;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;
import sanlab.icecream.customer.service.CustomerService;
import sanlab.icecream.sharedlib.constant.KafkaTopic;
import sanlab.icecream.sharedlib.proto.CustomerDTO;


@Controller
@RequiredArgsConstructor
public class KafkaConsumer {
    private final CustomerService customerService;

    @KafkaListener(topics = KafkaTopic.INSERT_CUSTOMER, containerFactory = "customerListenerContainerFactory")
    public void insertCustomer(ConsumerRecord<String, CustomerDTO> payload) {
        customerService.insertCustomer(payload.value());
    }

    @KafkaListener(topics = KafkaTopic.UPDATE_CUSTOMER, containerFactory = "customerListenerContainerFactory")
    public void updateCustomer(ConsumerRecord<String, CustomerDTO> payload) {
        customerService.updateCustomer(payload.value());
    }
}
