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
import sanlab.icecream.consul.dto.extended.CartExtendedDto;
import sanlab.icecream.consul.exception.HttpInternalServerErrorException;
import sanlab.icecream.consul.exception.HttpNotFoundException;
import sanlab.icecream.consul.exception.HttpServiceUnavailableException;
import sanlab.icecream.consul.exception.HttpUnauthorizedException;
import sanlab.icecream.consul.service.CartService;
import sanlab.icecream.consul.utils.SecurityContextUtils;
import sanlab.icecream.consul.viewmodel.request.CartRequest;
import sanlab.icecream.fundamentum.exception.IcRuntimeException;

import java.util.UUID;

import static sanlab.icecream.consul.exception.ConsulErrorModel.CART_NOT_FOUND;
import static sanlab.icecream.consul.exception.ConsulErrorModel.CUSTOMER_NOT_FOUND;
import static sanlab.icecream.consul.exception.ConsulErrorModel.FAIL_TO_PERSIST_DATA;
import static sanlab.icecream.consul.exception.ConsulErrorModel.INVALID_USER_PRINCIPAL;
import static sanlab.icecream.consul.exception.ConsulErrorModel.PRODUCT_NOT_FOUND;
import static sanlab.icecream.fundamentum.constant.EPreAuthorizeRole.HAS_ROLE_NORMIE_AND_WATCHER;

@RestController
@RequestMapping("/carts")
@PreAuthorize(HAS_ROLE_NORMIE_AND_WATCHER)
@RequiredArgsConstructor
public class CartController {

    private final CartService service;

    @GetMapping
    public ResponseEntity<CartExtendedDto> get() {
        try {
            var userDetails = SecurityContextUtils.getRegisteredUserInfo();
            var result = service.get(UUID.fromString(userDetails.getSub()));
            return ResponseEntity.ok(result);
        } catch (IcRuntimeException ex) {
            var error = ex.getError();
            if (INVALID_USER_PRINCIPAL.equals(error)) throw new HttpUnauthorizedException(ex);
            throw new HttpInternalServerErrorException(ex);
        }
    }

    @PostMapping
    public ResponseEntity<CartExtendedDto> upsert(@RequestBody CartRequest payload) {
        try {
            var userDetails = SecurityContextUtils.getRegisteredUserInfo();
            var result = service.upsert(UUID.fromString(userDetails.getSub()), payload);
            return ResponseEntity.ok(result);
        } catch (IcRuntimeException ex) {
            var error = ex.getError();
            throw switch(error) {
                case INVALID_USER_PRINCIPAL, CUSTOMER_NOT_FOUND -> new HttpUnauthorizedException(ex);
                case FAIL_TO_PERSIST_DATA -> new HttpServiceUnavailableException(ex);
                case CART_NOT_FOUND -> new HttpNotFoundException(ex);
                default -> new HttpInternalServerErrorException(ex);
            };
        }
    }

    @DeleteMapping
    public ResponseEntity<CartExtendedDto> reset() {
        try {
            var userDetails = SecurityContextUtils.getRegisteredUserInfo();
            var result = service.reset(UUID.fromString(userDetails.getSub()));
            return ResponseEntity.ok(result);
        } catch (IcRuntimeException ex) {
            var error = ex.getError();
            throw switch(error) {
                case INVALID_USER_PRINCIPAL -> new HttpUnauthorizedException(ex);
                case FAIL_TO_PERSIST_DATA -> new HttpServiceUnavailableException(ex);
                default -> new HttpInternalServerErrorException(ex);
            };
        }
    }

}
