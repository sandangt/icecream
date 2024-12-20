package sanlab.icecream.frontier.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sanlab.icecream.frontier.dto.core.CustomerDto;
import sanlab.icecream.frontier.dto.extended.CustomerExtendedDto;
import sanlab.icecream.frontier.mapper.ICustomerMapper;
import sanlab.icecream.frontier.model.Customer;
import sanlab.icecream.frontier.repository.crud.ICustomerRepository;
import sanlab.icecream.frontier.utils.SecurityContextUtils;
import sanlab.icecream.frontier.viewmodel.response.CollectionQueryResponse;
import sanlab.icecream.fundamentum.constant.ECustomerStatus;
import sanlab.icecream.fundamentum.exception.ItemNotFoundException;
import sanlab.icecream.fundamentum.exception.StoringDatabaseException;

import java.util.List;
import java.util.UUID;

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
            .orElseThrow(() -> ItemNotFoundException.customer(id));
        return customerMapper.entityToExtendedDto(customer);
    }

    @Transactional
    public CustomerExtendedDto create(CustomerDto request) {
        try {
            Customer customer = customerRepository.save(customerMapper.dtoToEntity(request));
            return customerMapper.entityToExtendedDto(customer);
        } catch (Exception ignored) {
            throw new StoringDatabaseException("Error occurs when creating customer profile");
        }
    }

    @Transactional
    public CustomerExtendedDto createIfNotExist() {
        var userDetails = SecurityContextUtils.getRegisteredUserInfo().get();
        var customerOptional = customerRepository.findFirstByUserId(UUID.fromString(userDetails.getSub()));
        if (customerOptional.isPresent()) {
            return customerMapper.entityToExtendedDto(customerOptional.get());
        }
        try {
            var customerEntity = customerMapper.userDetailsToEntity(userDetails);
            customerEntity.setStatus(ECustomerStatus.ACTIVE);
            var result = customerRepository.save(customerEntity);
            return customerMapper.entityToExtendedDto(result);
        } catch (Exception ignored) {
            throw new StoringDatabaseException("Error occurs when creating customer profile");
        }
    }

    @Transactional
    public CustomerExtendedDto update(UUID id, CustomerDto request) {
        Customer targetCustomer = customerRepository.findFirstByUserId(id)
            .orElseThrow(() -> ItemNotFoundException.customer(id));
        Customer sourceCustomer = customerMapper.dtoToEntity(request);
        copyNotNull(sourceCustomer, targetCustomer);
        try {
            return customerMapper.entityToExtendedDto(customerRepository.save(targetCustomer));
        } catch (Exception ignored) {
            throw new StoringDatabaseException("Error occurs when updating customer profile");
        }
    }

}
