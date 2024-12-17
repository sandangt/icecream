package sanlab.icecream.frontier.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sanlab.icecream.frontier.dto.core.StockDto;
import sanlab.icecream.frontier.dto.extended.StockExtendedDto;
import sanlab.icecream.frontier.service.StockService;
import sanlab.icecream.frontier.viewmodel.request.CollectionQueryRequest;
import sanlab.icecream.frontier.viewmodel.response.CollectionQueryResponse;

import java.util.UUID;

@RestController
@RequestMapping("/stocks")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @GetMapping
    public CollectionQueryResponse<StockExtendedDto> getAll(@ModelAttribute CollectionQueryRequest request) {
        return stockService.getAll(request.getPageRequest());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockExtendedDto> getById(@PathVariable UUID id) {
        var result = stockService.getById(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<StockExtendedDto> create(@Valid @RequestBody StockDto requestBody) {
        var result = stockService.create(requestBody);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockExtendedDto> update(@PathVariable UUID id,
                                                   @Valid @RequestBody StockDto requestBody) {
        var result = stockService.update(id, requestBody);
        return ResponseEntity.ok(result);
    }

}
