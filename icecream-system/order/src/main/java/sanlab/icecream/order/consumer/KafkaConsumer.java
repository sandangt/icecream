package sanlab.icecream.order.consumer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import sanlab.icecream.order.service.OrderItemService;
import sanlab.icecream.order.service.OrderService;


@Controller
@RequiredArgsConstructor
public class KafkaConsumer {
    private final OrderService orderService;
    private final OrderItemService orderItemService;
}
