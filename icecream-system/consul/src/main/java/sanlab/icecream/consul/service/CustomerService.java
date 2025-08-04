package sanlab.icecream.consul.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sanlab.icecream.consul.dto.core.AddressDto;
import sanlab.icecream.consul.dto.core.CustomerDto;
import sanlab.icecream.consul.dto.core.ImageDto;
import sanlab.icecream.consul.dto.extended.CustomerExtendedDto;
import sanlab.icecream.consul.mapper.AddressMapper;
import sanlab.icecream.consul.mapper.CustomerMapper;
import sanlab.icecream.consul.mapper.ImageMapper;
import sanlab.icecream.consul.model.Address;
import sanlab.icecream.consul.model.Customer;
import sanlab.icecream.consul.repository.crud.AddressRepository;
import sanlab.icecream.consul.repository.crud.CustomerRepository;
import sanlab.icecream.consul.repository.queue.ImageQueueRepository;
import sanlab.icecream.consul.repository.restclient.IdentityRepository;
import sanlab.icecream.consul.utils.SecurityContextUtils;
import sanlab.icecream.consul.viewmodel.response.CollectionQueryResponse;
import sanlab.icecream.fundamentum.constant.ECustomerStatus;
import sanlab.icecream.fundamentum.constant.EFileHandlingAction;
import sanlab.icecream.fundamentum.constant.EFileType;
import sanlab.icecream.fundamentum.dto.FileHandlingDto;
import sanlab.icecream.fundamentum.exception.IcRuntimeException;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static sanlab.icecream.consul.exception.ConsulErrorModel.ADDRESS_NOT_FOUND;
import static sanlab.icecream.consul.exception.ConsulErrorModel.CUSTOMER_NOT_FOUND;
import static sanlab.icecream.consul.exception.ConsulErrorModel.FAIL_TO_PERSIST_DATA;
import static sanlab.icecream.fundamentum.constant.EImageType.AVATAR;
import static sanlab.icecream.fundamentum.utils.ObjectUtils.copyNotNull;
import static sanlab.icecream.fundamentum.utils.RequestUtils.calculateTotalPage;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final IdentityRepository identityRepository;
    private final CustomerMapper customerMapper;
    private final AddressMapper addressMapper;
    private final AddressRepository addressRepository;
    private final ImageService imageService;
    private final ImageMapper imageMapper;
    private final ImageQueueRepository fileHandlingRepository;

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
        identityRepository.updateUserInfoByUserId(id, customerMapper.dtoToKeycloakUserInfo(request));
        try {
            customerRepository.updateCustomerInfo(id, request);
            return customerMapper.entityToExtendedDto(
                customerRepository
                    .findFirstByUserId(id)
                    .orElseThrow(() -> new IcRuntimeException(CUSTOMER_NOT_FOUND, "id: %s".formatted(id)))
            );
        } catch (Exception ex) {
            throw new IcRuntimeException(ex, FAIL_TO_PERSIST_DATA, "customer");
        }
    }

    @Transactional
    public CustomerExtendedDto addAddress(UUID id, AddressDto request) {
        Customer targetCustomer = customerRepository.findFirstByUserId(id)
            .orElseThrow(() -> new IcRuntimeException(CUSTOMER_NOT_FOUND, "id: %s".formatted(id)));
        Address requestAddress = addressMapper.dtoToEntity(request);
        try {
            Address savedAddress = addressRepository.save(requestAddress);
            Set<Address> addressSet = targetCustomer.getAddresses();
            addressSet.add(savedAddress);
            targetCustomer.setAddresses(addressSet);
            return customerMapper.entityToExtendedDto(customerRepository.save(targetCustomer));
        } catch (Exception ex) {
            throw new IcRuntimeException(ex, FAIL_TO_PERSIST_DATA, "customer");
        }
    }

    @Transactional
    public AddressDto updateAddress(UUID id, AddressDto request) {
        if (!customerRepository.existsByUserId(id)) {
            throw new IcRuntimeException(CUSTOMER_NOT_FOUND, "id: %s".formatted(id));
        }
        Address targetAddress = addressRepository.findFirstById(request.getId())
            .orElseThrow(() -> new IcRuntimeException(ADDRESS_NOT_FOUND, "id: %s".formatted(request.getId())));
        try {
            Address sourceAddress = addressMapper.dtoToEntity(request);
            copyNotNull(sourceAddress, targetAddress);
            Address result = addressRepository.save(targetAddress);
            return addressMapper.entityToDto(result);
        } catch (Exception ex) {
            throw new IcRuntimeException(ex, FAIL_TO_PERSIST_DATA, "address");
        }
    }

    @Transactional
    public void setPrimaryAddress(UUID id, UUID addressId) {
        if (!customerRepository.existsByUserId(id)) {
            throw new IcRuntimeException(CUSTOMER_NOT_FOUND, "id: %s".formatted(id));
        }
        if (!addressRepository.existsById(addressId)) {
            throw new IcRuntimeException(ADDRESS_NOT_FOUND, "id: %s".formatted(addressId));
        }
        try {
            customerRepository.setPrimaryAddress(id, addressId);
        } catch (Exception ex) {
            throw new IcRuntimeException(ex, FAIL_TO_PERSIST_DATA, "customer");
        }
    }

    @Transactional
    public CustomerExtendedDto deleteAddress(UUID id, UUID addressId) {
        Customer customer = customerRepository.findFirstByUserId(id)
            .orElseThrow(() -> new IcRuntimeException(CUSTOMER_NOT_FOUND, "id: %s".formatted(id)));
        Address address = addressRepository.findFirstById(addressId)
            .orElseThrow(() -> new IcRuntimeException(ADDRESS_NOT_FOUND, "id: %s".formatted(addressId)));
        Set<Address> addresses = customer.getAddresses();
        Address primaryAddress = customer.getPrimaryAddress();
        addresses.remove(address);
        if (address.equals(primaryAddress)) {
            customer.setPrimaryAddress(null);
        }
        try {
            var result = customerRepository.save(customer);
            addressRepository.deleteById(addressId);
            return customerMapper.entityToExtendedDto(result);
        } catch (Exception ex) {
            throw new IcRuntimeException(ex, FAIL_TO_PERSIST_DATA, "customer & address");
        }
    }

    public ImageDto uploadAvatar(UUID id, MultipartFile avatarFile) {
        Customer customer = customerRepository.findFirstByUserId(id)
            .orElseThrow(() -> new IcRuntimeException(CUSTOMER_NOT_FOUND, "id: %s".formatted(id)));
        var imageDto = imageService.upsertCustomerAvatar(id, avatarFile);
        var image = imageMapper.dtoToEntity(imageDto);
        var mediaSet = customer.getMedia();
        if (!mediaSet.isEmpty()) {
            mediaSet.removeIf(item -> {
                if (!AVATAR.equals(item.getType())) return false;
                if (!StringUtils.equals(item.getRelativePath(), imageDto.getRelativePath())) {
                    var fileHandlingDto = FileHandlingDto.builder()
                        .relativePath(item.getRelativePath())
                        .action(EFileHandlingAction.DELETE)
                        .fileType(EFileType.IMAGE)
                        .build();
                    fileHandlingRepository.delete(fileHandlingDto);
                }
                return true;
            });
        }
        try {
            mediaSet.add(image);
            customer.setMedia(mediaSet);
            customerRepository.save(customer);
            return imageDto;
        } catch (Exception ex) {
            throw new IcRuntimeException(ex, FAIL_TO_PERSIST_DATA, "customer");
        }
    }

}
