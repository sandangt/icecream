package sanlab.icecream.frontier.controller;

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
import sanlab.icecream.frontier.dto.core.CustomerDto;
import sanlab.icecream.frontier.dto.extended.CustomerExtendedDto;
import sanlab.icecream.frontier.service.CustomerService;
import sanlab.icecream.frontier.utils.SecurityContextUtils;

import java.util.UUID;

import static sanlab.icecream.fundamentum.constant.PreAuthorizedAuthExp.NORMIE;

@RestController
@RequestMapping("/customers")
@PreAuthorize(NORMIE)
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<CustomerExtendedDto> getCustomerProfile() {
        var userDetails = SecurityContextUtils.getRegisteredUserInfo().get();
        var result = customerService.getById(UUID.fromString(userDetails.getSub()));
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<CustomerExtendedDto> createCustomerProfile() {
        var result = customerService.createIfNotExist();
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerExtendedDto> updateCustomerProfile(@PathVariable UUID id,
                                                                     @RequestBody CustomerDto payload) {
        var result = customerService.update(id, payload);
        return ResponseEntity.ok(result);
    }

}
