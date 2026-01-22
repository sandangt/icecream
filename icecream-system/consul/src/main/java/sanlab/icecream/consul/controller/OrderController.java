package sanlab.icecream.consul.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sanlab.icecream.consul.dto.extended.OrderExtendedDto;
import sanlab.icecream.consul.exception.HttpInternalServerErrorException;
import sanlab.icecream.consul.exception.HttpNotFoundException;
import sanlab.icecream.consul.exception.HttpServiceUnavailableException;
import sanlab.icecream.consul.exception.HttpUnauthorizedException;
import sanlab.icecream.consul.service.OrderService;
import sanlab.icecream.consul.utils.SecurityContextUtils;
import sanlab.icecream.consul.viewmodel.request.OrderRequest;
import sanlab.icecream.consul.viewmodel.response.CreateOrderResponse;
import sanlab.icecream.fundamentum.exception.IcRuntimeException;

import java.util.UUID;

import static sanlab.icecream.consul.exception.ConsulErrorModel.PAYMENT_REQUEST_INVOICE_FAILED;
import static sanlab.icecream.consul.exception.ConsulErrorModel.REPOSITORY_ADDRESS_NOT_FOUND;
import static sanlab.icecream.consul.exception.ConsulErrorModel.REPOSITORY_CUSTOMER_NOT_FOUND;
import static sanlab.icecream.consul.exception.ConsulErrorModel.REPOSITORY_PERSIST_DATA_FAILED;
import static sanlab.icecream.consul.exception.ConsulErrorModel.SECURITY_USER_PRINCIPAL_INVALID;
import static sanlab.icecream.fundamentum.constant.EPreAuthorizeRole.HAS_ROLE_NORMIE_AND_WATCHER;

@RestController
@RequestMapping("/orders")
@PreAuthorize(HAS_ROLE_NORMIE_AND_WATCHER)
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @GetMapping
    public ResponseEntity<OrderExtendedDto> get() {
        try {
            var userDetails = SecurityContextUtils.getRegisteredUserInfo();
            var result = service.get(UUID.fromString(userDetails.getSub()));
            return ResponseEntity.ok(result);
        } catch (IcRuntimeException ex) {
            var error =ex.getError();
            if (SECURITY_USER_PRINCIPAL_INVALID.equals(error)) throw new HttpUnauthorizedException(ex);
            throw new HttpInternalServerErrorException(ex);
        }
    }

    @PostMapping
    public ResponseEntity<CreateOrderResponse> create(@RequestBody OrderRequest payload) {
        try {
            var userDetails = SecurityContextUtils.getRegisteredUserInfo();
            var result = service.create(UUID.fromString(userDetails.getSub()), payload);
            return ResponseEntity.ok(result);
        } catch (IcRuntimeException ex) {
            var error = ex.getError();
            throw switch(error) {
                case SECURITY_USER_PRINCIPAL_INVALID, REPOSITORY_CUSTOMER_NOT_FOUND -> new HttpUnauthorizedException(ex);
                case REPOSITORY_ADDRESS_NOT_FOUND -> new HttpNotFoundException(ex);
                case REPOSITORY_PERSIST_DATA_FAILED, PAYMENT_REQUEST_INVOICE_FAILED -> new HttpServiceUnavailableException(ex);
                default -> new HttpInternalServerErrorException(ex);
            };
        }
    }

    @DeleteMapping
    public ResponseEntity<OrderExtendedDto> cancel() {
        return null;
    }

}
