package sanlab.icecream.customer.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sanlab.icecream.customer.exception.ErrorCode;
import sanlab.icecream.customer.mapper.IMapper;
import sanlab.icecream.customer.model.Customer;
import sanlab.icecream.customer.repository.ICustomerAddressRepository;
import sanlab.icecream.customer.repository.ICustomerRepository;
import sanlab.icecream.sharedlib.exception.ItemNotFoundException;
import sanlab.icecream.sharedlib.proto.CustomerDTO;
import sanlab.icecream.sharedlib.proto.PageInfoRequest;


@Service
@RequiredArgsConstructor
public class CustomerService {
    private final ICustomerRepository customerRepository;
    private final ICustomerAddressRepository customerAddressRepository;
    private final IMapper mapper;

    public List<CustomerDTO> getAllCustomers(PageInfoRequest pageInfo) {
        Pageable page = PageRequest.of(
            Math.round((float) pageInfo.getOffset() / (float) pageInfo.getLimit()),
            pageInfo.getLimit()
        );
        return customerRepository.findAllByOrderByLastModifiedOnDesc(page)
            .stream()
            .map(mapper.INSTANCE::modelToDTO)
            .toList();
    }

    public CustomerDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
            .orElseThrow(() -> new ItemNotFoundException(
                String.format("Customer with ID: %s not found", id), ErrorCode.CUSTOMER_NOT_FOUND
            )
        );
        return mapper.INSTANCE.modelToDTO(customer);
    }

    public void insertCustomer(CustomerDTO customerDTO) {
        customerRepository.save(mapper.INSTANCE.dtoToModel(customerDTO));
    }

    public void updateCustomer(CustomerDTO customerDTO) {
        Customer customer = customerRepository.findById(customerDTO.getId())
            .orElseThrow(() -> new ItemNotFoundException(
                String.format("Customer with ID: %s not found", customerDTO.getId()), ErrorCode.CUSTOMER_NOT_FOUND
            ));
        mapper.INSTANCE.updateCustomerFromDTO(customerDTO, customer);
        customerRepository.save(customer);
    }
}
