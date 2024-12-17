package sanlab.icecream.frontier.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sanlab.icecream.frontier.mapper.IOrderMapper;
import sanlab.icecream.frontier.repository.crud.IOrderRepository;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final IOrderRepository orderRepository;

    private final IOrderMapper orderMapper;

}
