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
import sanlab.icecream.consul.service.OrderService;
import sanlab.icecream.consul.utils.SecurityContextUtils;
import sanlab.icecream.consul.viewmodel.request.OrderRequest;

import java.util.UUID;

import static sanlab.icecream.fundamentum.constant.EPreAuthorizeRole.HAS_ROLE_NORMIE_AND_WATCHER;

@RestController
@RequestMapping("/orders")
@PreAuthorize(HAS_ROLE_NORMIE_AND_WATCHER)
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @GetMapping
    public ResponseEntity<OrderExtendedDto> get() {
        var userDetails = SecurityContextUtils.getRegisteredUserInfo();
        var result = service.get(UUID.fromString(userDetails.getSub()));
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<OrderExtendedDto> create(@RequestBody OrderRequest payload) {
        var userDetails = SecurityContextUtils.getRegisteredUserInfo();
        var result = service.create(UUID.fromString(userDetails.getSub()), payload);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping
    public ResponseEntity<OrderExtendedDto> cancel() {
        return null;
    }

}
