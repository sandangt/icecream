package sanlab.icecream.gateway.service.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sanlab.icecream.gateway.exception.ErrorCode;
import sanlab.icecream.gateway.mapper.ICustomerMapper;
import sanlab.icecream.gateway.repository.customer.CustomerRepository;
import sanlab.icecream.gateway.viewmodel.customer.CustomerResponseVm;
import sanlab.icecream.gateway.viewmodel.customer.CustomerVm;
import sanlab.icecream.sharedlib.exception.ItemNotFoundException;
import sanlab.icecream.sharedlib.proto.CustomerDTO;
import sanlab.icecream.sharedlib.proto.CustomerResponse;


@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final ICustomerMapper customerMapper;

    public CustomerResponseVm getCustomer(String username) {
        CustomerResponse customerResponse = customerRepository.getCustomerByUsername(username)
            .orElseThrow(() -> new ItemNotFoundException(
                String.format("Customer with email %s not found", username), ErrorCode.CUSTOMER_CUSTOMER_NOT_FOUND
            )
        );
        CustomerVm customerVm = customerMapper.dtoToVm(customerResponse.getCustomer());
        return new CustomerResponseVm(customerVm);
    }

    public void insertCustomer(CustomerVm customerVm) {
        CustomerDTO customerDTO = customerMapper.INSTANCE.vmToDTO(customerVm);
        customerRepository.insertCustomer(customerDTO);
    }

    public void updateCustomer(CustomerVm customerVm) {
        CustomerDTO customerDTO = customerMapper.INSTANCE.vmToDTO(customerVm);
        customerRepository.updateCustomer(customerDTO);
    }
}
