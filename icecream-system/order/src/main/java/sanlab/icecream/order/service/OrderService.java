package sanlab.icecream.order.service;

import java.util.List;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Service;
import sanlab.icecream.sharedlib.proto.OrderDTO;
import sanlab.icecream.sharedlib.proto.PageInfoRequest;


@Service
public class OrderService {

    public List<OrderDTO> getAllOrders(PageInfoRequest pageInfo) {
        throw new NotImplementedException();
    }

    public OrderDTO getOrderById(Long id) {
        throw new NotImplementedException();
    }

    public OrderDTO getOrderByOrderItemId(Long orderItemId) {
        throw new NotImplementedException();
    }

    public void insertOrder(OrderDTO orderDTO) {
        throw new NotImplementedException();
    }

    public void updateOrder(OrderDTO orderDTO) {
        throw new NotImplementedException();
    }
}
