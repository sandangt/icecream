package sanlab.icecream.order.service;

import java.util.List;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Service;
import sanlab.icecream.sharedlib.proto.OrderItemDTO;


@Service
public class OrderItemService {
    public List<OrderItemDTO> getOrderItemListByOrderId(Long orderId) {
        throw new NotImplementedException();
    }
    public OrderItemDTO getOrderItemById(Long id) {
        throw new NotImplementedException();
    }
    public void insertOrderItemIntoOrder(OrderItemDTO orderItemDTO, Long orderId) {
        throw new NotImplementedException();
    }
    public void removeOrderItemFromOrder(OrderItemDTO orderItemDTO, Long orderId) {
        throw new NotImplementedException();
    }
    public void updateOrderItem(OrderItemDTO orderItemDTO) {
        throw new NotImplementedException();
    }
}
