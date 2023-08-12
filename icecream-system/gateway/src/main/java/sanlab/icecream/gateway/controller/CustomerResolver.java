package sanlab.icecream.gateway.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import sanlab.icecream.gateway.service.customer.CustomerService;


@Controller
@RequiredArgsConstructor
public class CustomerResolver {
    private final CustomerService customerService;

}
