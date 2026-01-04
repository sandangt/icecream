package sanlab.icecream.consul.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sanlab.icecream.fundamentum.dto.core.AddressDto;
import sanlab.icecream.fundamentum.dto.core.CustomerDto;
import sanlab.icecream.fundamentum.dto.core.ImageDto;
import sanlab.icecream.fundamentum.dto.exntended.CustomerExtendedDto;
import sanlab.icecream.consul.exception.HttpBadRequestException;
import sanlab.icecream.consul.exception.HttpInternalServerErrorException;
import sanlab.icecream.consul.exception.HttpNotFoundException;
import sanlab.icecream.consul.exception.HttpServiceUnavailableException;
import sanlab.icecream.consul.exception.HttpUnauthorizedException;
import sanlab.icecream.consul.service.CustomerService;
import sanlab.icecream.consul.utils.SecurityContextUtils;
import sanlab.icecream.fundamentum.exception.IcRuntimeException;

import java.util.UUID;

import static sanlab.icecream.consul.exception.ConsulErrorModel.ADDRESS_NOT_FOUND;
import static sanlab.icecream.consul.exception.ConsulErrorModel.CUSTOMER_NOT_FOUND;
import static sanlab.icecream.consul.exception.ConsulErrorModel.FAIL_TO_PERSIST_DATA;
import static sanlab.icecream.consul.exception.ConsulErrorModel.INVALID_UPDATE_USER_INFO_REQUEST;
import static sanlab.icecream.consul.exception.ConsulErrorModel.INVALID_USER_PRINCIPAL;
import static sanlab.icecream.fundamentum.constant.EPreAuthorizeRole.HAS_ROLE_NORMIE;

@RestController
@RequestMapping("/customers")
@PreAuthorize(HAS_ROLE_NORMIE)
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<CustomerExtendedDto> getCustomerProfile() {
        try {
            var userDetails = SecurityContextUtils.getRegisteredUserInfo();
            var result = customerService.getById(UUID.fromString(userDetails.getSub()));
            return ResponseEntity.ok(result);
        } catch (IcRuntimeException ex) {
            var error = ex.getError();
            if (INVALID_USER_PRINCIPAL.equals(error)) throw new HttpUnauthorizedException(ex);
            throw new HttpInternalServerErrorException(ex);
        }
    }

    @PostMapping
    public ResponseEntity<CustomerExtendedDto> createCustomerProfile() {
        try {
            var result = customerService.createIfNotExist();
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (IcRuntimeException ex) {
            var error = ex.getError();
            throw switch (error) {
                case INVALID_USER_PRINCIPAL -> new HttpUnauthorizedException(ex);
                case FAIL_TO_PERSIST_DATA -> new HttpServiceUnavailableException(ex);
                default -> new HttpInternalServerErrorException(ex);
            };
        }
    }

    @PutMapping
    public ResponseEntity<CustomerExtendedDto> updateCustomerProfile(@RequestBody CustomerDto payload) {
        try {
            var userDetails = SecurityContextUtils.getRegisteredUserInfo();
            var result = customerService.update(UUID.fromString(userDetails.getSub()), payload);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
        } catch (IcRuntimeException ex) {
            var error = ex.getError();
            throw switch (error) {
                case CUSTOMER_NOT_FOUND -> new HttpUnauthorizedException(ex);
                case INVALID_UPDATE_USER_INFO_REQUEST -> new HttpBadRequestException(ex);
                case FAIL_TO_PERSIST_DATA -> new HttpServiceUnavailableException(ex);
                default -> new HttpInternalServerErrorException(ex);
            };
        }
    }

    @PostMapping("/addresses")
    public ResponseEntity<CustomerExtendedDto> addAddress(@RequestBody AddressDto payload) {
        try {
            var userDetails = SecurityContextUtils.getRegisteredUserInfo();
            var result = customerService.addAddress(UUID.fromString(userDetails.getSub()), payload);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
        } catch (IcRuntimeException ex) {
            var error = ex.getError();
            throw switch (error) {
                case CUSTOMER_NOT_FOUND -> new HttpUnauthorizedException(ex);
                case FAIL_TO_PERSIST_DATA -> new HttpServiceUnavailableException(ex);
                default -> new HttpInternalServerErrorException(ex);
            };
        }
    }

    @PatchMapping("/addresses/{addressId}")
    public ResponseEntity<AddressDto> updateAddress(@PathVariable String addressId,
                                                    @RequestBody AddressDto payload) {
        try {
            var userDetails = SecurityContextUtils.getRegisteredUserInfo();
            payload.setId(UUID.fromString(addressId));
            var result = customerService.updateAddress(UUID.fromString(userDetails.getSub()), payload);
            return ResponseEntity.accepted().body(result);
        } catch (IcRuntimeException ex) {
            var error = ex.getError();
            throw switch (error) {
                case CUSTOMER_NOT_FOUND -> new HttpUnauthorizedException(ex);
                case ADDRESS_NOT_FOUND -> new HttpNotFoundException(ex);
                case FAIL_TO_PERSIST_DATA -> new HttpServiceUnavailableException(ex);
                default -> new HttpInternalServerErrorException(ex);
            };
        }
    }

    @DeleteMapping("/addresses/{addressId}")
    public ResponseEntity<CustomerExtendedDto> deleteAddress(@PathVariable String addressId) {
        try {
            var userDetails = SecurityContextUtils.getRegisteredUserInfo();
            var result = customerService.deleteAddress(
                UUID.fromString(userDetails.getSub()), UUID.fromString(addressId));
            return ResponseEntity.accepted().body(result);
        } catch (IcRuntimeException ex) {
            var error = ex.getError();
            throw switch (error) {
                case CUSTOMER_NOT_FOUND -> new HttpUnauthorizedException(ex);
                case ADDRESS_NOT_FOUND -> new HttpNotFoundException(ex);
                case FAIL_TO_PERSIST_DATA -> new HttpServiceUnavailableException(ex);
                default -> new HttpInternalServerErrorException(ex);
            };
        }
    }

    @PostMapping("/addresses/primary/{addressId}")
    public ResponseEntity<Void> setPrimaryAddress(@PathVariable String addressId) {
        try {
            var userDetails = SecurityContextUtils.getRegisteredUserInfo();
            customerService.setPrimaryAddress(
                UUID.fromString(userDetails.getSub()), UUID.fromString(addressId));
            return ResponseEntity.accepted().build();
        } catch (IcRuntimeException ex) {
            var error = ex.getError();
            throw switch (error) {
                case CUSTOMER_NOT_FOUND -> new HttpUnauthorizedException(ex);
                case ADDRESS_NOT_FOUND -> new HttpNotFoundException(ex);
                case FAIL_TO_PERSIST_DATA -> new HttpServiceUnavailableException(ex);
                default -> new HttpInternalServerErrorException(ex);
            };
        }
    }

    @PostMapping("/avatars")
    public ResponseEntity<ImageDto> uploadAvatar(@RequestParam("file") MultipartFile avatarFile) {
        try {
            var userDetails = SecurityContextUtils.getRegisteredUserInfo();
            var result = customerService.uploadAvatar(UUID.fromString(userDetails.getSub()), avatarFile);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
        } catch (IcRuntimeException ex) {
            var error = ex.getError();
            if (FAIL_TO_PERSIST_DATA.equals(error)) {
                throw new HttpServiceUnavailableException(ex);
            }
            throw new HttpInternalServerErrorException(ex);
        }
    }

}
