package sanlab.icecream.consul.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sanlab.icecream.consul.dto.core.CustomerDto;
import sanlab.icecream.consul.dto.extended.CustomerExtendedDto;
import sanlab.icecream.consul.mapper.ICustomerMapper;
import sanlab.icecream.consul.model.Customer;
import sanlab.icecream.consul.repository.crud.ICustomerRepository;
import sanlab.icecream.consul.utils.SecurityContextUtils;
import sanlab.icecream.consul.viewmodel.response.CollectionQueryResponse;
import sanlab.icecream.fundamentum.constant.ECustomerStatus;
import sanlab.icecream.fundamentum.exception.IcRuntimeException;

import java.util.List;
import java.util.UUID;

import static sanlab.icecream.consul.exception.ConsulErrorModel.CUSTOMER_NOT_FOUND;
import static sanlab.icecream.consul.exception.ConsulErrorModel.FAIL_TO_PERSIST_DATA;
import static sanlab.icecream.fundamentum.utils.ObjectUtils.copyNotNull;
import static sanlab.icecream.fundamentum.utils.RequestUtils.calculateTotalPage;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final ICustomerRepository customerRepository;
    private final ICustomerMapper customerMapper;

    @Transactional(readOnly = true)
    public CollectionQueryResponse<CustomerExtendedDto> getAll(Pageable pageable) {
        Page<Customer> paginatedItems = customerRepository.findAll(pageable);
        long total = customerRepository.count();
        List<CustomerExtendedDto> customerList = customerMapper.entityToExtendedDto(paginatedItems.stream().toList());
        return CollectionQueryResponse.<CustomerExtendedDto>builder()
            .total(total)
            .page(pageable.getPageNumber())
            .totalPages(calculateTotalPage(total, pageable.getPageSize()))
            .data(customerList)
            .build();
    }

    @Transactional(readOnly = true)
    public CustomerExtendedDto getById(UUID id) {
        var customer = customerRepository.findFirstByUserId(id)
            .orElseThrow(() -> new IcRuntimeException(CUSTOMER_NOT_FOUND, "id: %s".formatted(id)));
        return customerMapper.entityToExtendedDto(customer);
    }

    @Transactional
    public CustomerExtendedDto create(CustomerDto request) {
        try {
            Customer customer = customerRepository.save(customerMapper.dtoToEntity(request));
            return customerMapper.entityToExtendedDto(customer);
        } catch (Exception ex) {
            throw new IcRuntimeException(ex, FAIL_TO_PERSIST_DATA, "customer");
        }
    }

    @Transactional
    public CustomerExtendedDto createIfNotExist() {
        var userDetails = SecurityContextUtils.getRegisteredUserInfo();
        var customerOptional = customerRepository.findFirstByUserId(UUID.fromString(userDetails.getSub()));
        if (customerOptional.isPresent()) {
            return customerMapper.entityToExtendedDto(customerOptional.get());
        }
        try {
            var customerEntity = customerMapper.userDetailsToEntity(userDetails);
            customerEntity.setStatus(ECustomerStatus.ACTIVE);
            var result = customerRepository.save(customerEntity);
            return customerMapper.entityToExtendedDto(result);
        } catch (Exception ex) {
            throw new IcRuntimeException(ex, FAIL_TO_PERSIST_DATA, "customer");
        }
    }

    @Transactional
    public CustomerExtendedDto update(UUID id, CustomerDto request) {
        Customer targetCustomer = customerRepository.findFirstByUserId(id)
            .orElseThrow(() -> new IcRuntimeException(CUSTOMER_NOT_FOUND, "id: %s".formatted(id)));
        try {
            Customer sourceCustomer = customerMapper.dtoToEntity(request);
            copyNotNull(sourceCustomer, targetCustomer);
            return customerMapper.entityToExtendedDto(customerRepository.save(targetCustomer));
        } catch (Exception ex) {
            throw new IcRuntimeException(ex, FAIL_TO_PERSIST_DATA, "customer");
        }
    }

}
