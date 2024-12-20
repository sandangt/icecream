package sanlab.icecream.frontier.controller.watcher;

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
import sanlab.icecream.frontier.dto.core.CustomerDto;
import sanlab.icecream.frontier.dto.extended.CustomerExtendedDto;
import sanlab.icecream.frontier.service.CustomerService;
import sanlab.icecream.frontier.viewmodel.request.CollectionQueryRequest;
import sanlab.icecream.frontier.viewmodel.response.CollectionQueryResponse;

import java.util.UUID;

import static sanlab.icecream.fundamentum.constant.PreAuthorizedAuthExp.WATCHER;

@RestController
@RequestMapping("/watcher/customers")
@PreAuthorize(WATCHER)
@RequiredArgsConstructor
public class WatchCustomerController {

    private final CustomerService customerService;

    @GetMapping
    public CollectionQueryResponse<CustomerExtendedDto> getAll(@ModelAttribute CollectionQueryRequest request) {
        return customerService.getAll(request.getPageRequest());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerExtendedDto> getById(@PathVariable UUID id) {
        var result = customerService.getById(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerExtendedDto> update(@PathVariable UUID id,
                                                      @Valid @RequestBody CustomerDto requestBody) {
        var result = customerService.update(id, requestBody);
        return ResponseEntity.ok(result);
    }

}
