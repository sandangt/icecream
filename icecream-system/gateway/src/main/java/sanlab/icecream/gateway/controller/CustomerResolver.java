package sanlab.icecream.gateway.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import sanlab.icecream.gateway.constant.PreAuthorizedRole;
import sanlab.icecream.gateway.exception.controller.NotFoundException;
import sanlab.icecream.gateway.service.customer.CustomerService;
import sanlab.icecream.gateway.viewmodel.ResponseVm;
import sanlab.icecream.gateway.viewmodel.customer.CustomerResponseVm;
import sanlab.icecream.gateway.viewmodel.customer.CustomerVm;
import sanlab.icecream.sharedlib.exception.ItemNotFoundException;


@Controller
@RequiredArgsConstructor
public class CustomerResolver {
    private final CustomerService customerService;

    @PreAuthorize(PreAuthorizedRole.NORMIE_AND_VIP)
    @SchemaMapping(typeName = "Query", field = "customer")
    public CustomerResponseVm customer() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        try {
            return customerService.getCustomer(auth.getName());
        } catch (ItemNotFoundException ex) {
            throw new NotFoundException();
        }
    }

    @PreAuthorize(PreAuthorizedRole.NORMIE_AND_VIP)
    @SchemaMapping(typeName = "Mutation", field = "insertCustomer")
    public ResponseVm insertCustomer(@Argument("customer") CustomerVm customer) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        customer.setUsername(auth.getName());
        customerService.insertCustomer(customer);
        return new ResponseVm(true);
    }

    @PreAuthorize(PreAuthorizedRole.NORMIE_AND_VIP)
    @SchemaMapping(typeName = "Mutation", field = "updateCustomer")
    public ResponseVm updateCustomer(@Argument("customer") CustomerVm customer) {
        customerService.updateCustomer(customer);
        return new ResponseVm(true);
    }
}
