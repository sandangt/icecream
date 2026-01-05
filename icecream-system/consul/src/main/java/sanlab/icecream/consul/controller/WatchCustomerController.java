package sanlab.icecream.consul.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sanlab.icecream.consul.utils.CollectionQueryUtils;
import sanlab.icecream.fundamentum.dto.core.CustomerDto;
import sanlab.icecream.fundamentum.dto.exntended.CustomerExtendedDto;
import sanlab.icecream.consul.exception.HttpInternalServerErrorException;
import sanlab.icecream.consul.exception.HttpNotFoundException;
import sanlab.icecream.consul.exception.HttpServiceUnavailableException;
import sanlab.icecream.consul.service.CustomerService;
import sanlab.icecream.fundamentum.contractmodel.request.CollectionQueryRequest;
import sanlab.icecream.fundamentum.contractmodel.response.CollectionQueryResponse;
import sanlab.icecream.fundamentum.exception.IcRuntimeException;

import java.util.UUID;

import static sanlab.icecream.consul.exception.ConsulErrorModel.CUSTOMER_NOT_FOUND;
import static sanlab.icecream.consul.exception.ConsulErrorModel.FAIL_TO_PERSIST_DATA;
import static sanlab.icecream.fundamentum.constant.EPreAuthorizeRole.HAS_ROLE_WATCHER;

@RestController
@RequestMapping("/watcher/customers")
@PreAuthorize(HAS_ROLE_WATCHER)
@RequiredArgsConstructor
public class WatchCustomerController {

    private final CustomerService customerService;

    @GetMapping
    public CollectionQueryResponse<CustomerExtendedDto> getAll(@ModelAttribute CollectionQueryRequest request) {
        return customerService.getAll(CollectionQueryUtils.getPageRequest(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerExtendedDto> getById(@PathVariable UUID id) {
        try {
            var result = customerService.getById(id);
            return ResponseEntity.ok(result);
        } catch (IcRuntimeException ex) {
            var error = ex.getError();
            if (CUSTOMER_NOT_FOUND.equals(error)) throw new HttpNotFoundException(ex);
            throw new HttpInternalServerErrorException(ex);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerExtendedDto> update(@PathVariable UUID id,
                                                      @Valid @RequestBody CustomerDto requestBody) {
        try {
            var result = customerService.update(id, requestBody);
            return ResponseEntity.ok(result);
        } catch (IcRuntimeException ex) {
            var error = ex.getError();
            throw switch (error) {
                case CUSTOMER_NOT_FOUND -> new HttpNotFoundException(ex);
                case FAIL_TO_PERSIST_DATA -> new HttpServiceUnavailableException(ex);
                default -> new HttpInternalServerErrorException(ex);
            };
        }
    }

}
