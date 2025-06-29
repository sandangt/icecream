package sanlab.icecream.consul.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sanlab.icecream.consul.dto.core.CustomerDto;
import sanlab.icecream.consul.dto.extended.CustomerExtendedDto;
import sanlab.icecream.consul.exception.HttpInternalServerErrorException;
import sanlab.icecream.consul.exception.HttpServiceUnavailableException;
import sanlab.icecream.consul.exception.HttpUnauthorizedException;
import sanlab.icecream.consul.service.CustomerService;
import sanlab.icecream.consul.utils.SecurityContextUtils;
import sanlab.icecream.fundamentum.exception.IcRuntimeException;

import java.util.UUID;

import static sanlab.icecream.consul.exception.ConsulErrorModel.CUSTOMER_NOT_FOUND;
import static sanlab.icecream.consul.exception.ConsulErrorModel.FAIL_TO_PERSIST_DATA;
import static sanlab.icecream.consul.exception.ConsulErrorModel.INVALID_USER_PRINCIPAL;
import static sanlab.icecream.fundamentum.constant.PreAuthorizedAuthExp.NORMIE;

@RestController
@RequestMapping("/customers")
@PreAuthorize(NORMIE)
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
            return ResponseEntity.ok(result);
        } catch (IcRuntimeException ex) {
            var error = ex.getError();
            throw switch (error) {
                case INVALID_USER_PRINCIPAL -> new HttpUnauthorizedException(ex);
                case FAIL_TO_PERSIST_DATA -> new HttpServiceUnavailableException(ex);
                default -> new HttpInternalServerErrorException(ex);
            };
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerExtendedDto> updateCustomerProfile(@PathVariable UUID id,
                                                                     @RequestBody CustomerDto payload) {
        try {
            var result = customerService.update(id, payload);
            return ResponseEntity.ok(result);
        } catch (IcRuntimeException ex) {
            var error = ex.getError();
            throw switch (error) {
                case CUSTOMER_NOT_FOUND -> new HttpUnauthorizedException(ex);
                case FAIL_TO_PERSIST_DATA -> new HttpServiceUnavailableException(ex);
                default -> new HttpInternalServerErrorException(ex);
            };
        }
    }

}
