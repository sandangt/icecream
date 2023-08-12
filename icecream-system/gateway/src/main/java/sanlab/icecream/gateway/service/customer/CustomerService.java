package sanlab.icecream.gateway.service.customer;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Service;
import sanlab.icecream.gateway.mapper.ICustomerMapper;
import sanlab.icecream.gateway.repository.customer.CustomerRepository;
import sanlab.icecream.gateway.viewmodel.customer.CustomerVm;


@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final ICustomerMapper customerMapper;

    public CustomerVm getCustomer() {
        throw new NotImplementedException();
    }

    public void insertCustomer() {
        throw new NotImplementedException();
    }

    public void updateCustomer() {
        throw new NotImplementedException();
    }
}
