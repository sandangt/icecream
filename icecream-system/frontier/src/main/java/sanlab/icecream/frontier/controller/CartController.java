package sanlab.icecream.frontier.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sanlab.icecream.frontier.mapper.ICartMapper;
import sanlab.icecream.frontier.repository.crud.ICartRepository;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {

    private final ICartRepository cartRepository;

    private final ICartMapper cartMapper;

}
