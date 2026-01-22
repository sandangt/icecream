package sanlab.icecream.consul.controller;

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
import sanlab.icecream.consul.utils.CollectionQueryUtils;
import sanlab.icecream.fundamentum.dto.core.StockDto;
import sanlab.icecream.fundamentum.dto.exntended.StockExtendedDto;
import sanlab.icecream.consul.exception.HttpInternalServerErrorException;
import sanlab.icecream.consul.exception.HttpNotFoundException;
import sanlab.icecream.consul.exception.HttpServiceUnavailableException;
import sanlab.icecream.consul.service.StockService;
import sanlab.icecream.fundamentum.contractmodel.request.CollectionQueryRequest;
import sanlab.icecream.fundamentum.contractmodel.response.CollectionQueryResponse;
import sanlab.icecream.fundamentum.exception.IcRuntimeException;

import java.util.UUID;

import static sanlab.icecream.consul.exception.ConsulErrorModel.REPOSITORY_PERSIST_DATA_FAILED;
import static sanlab.icecream.consul.exception.ConsulErrorModel.REPOSITORY_STOCK_NOT_FOUND;

@RestController
@RequestMapping("/stocks")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @GetMapping
    public CollectionQueryResponse<StockExtendedDto> getAll(@ModelAttribute CollectionQueryRequest request) {
        return stockService.getAll(CollectionQueryUtils.getPageRequest(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockExtendedDto> getById(@PathVariable UUID id) {
        try {
            var result = stockService.getById(id);
            return ResponseEntity.ok(result);
        } catch (IcRuntimeException ex) {
            var error = ex.getError();
            if (REPOSITORY_STOCK_NOT_FOUND.equals(error)) throw new HttpNotFoundException(ex);
            throw new HttpInternalServerErrorException(ex);
        }
    }

    @PostMapping
    public ResponseEntity<StockExtendedDto> create(@Valid @RequestBody StockDto requestBody) {
        try {
            var result = stockService.create(requestBody);
            return ResponseEntity.ok(result);
        } catch (IcRuntimeException ex) {
            var error = ex.getError();
            if (REPOSITORY_PERSIST_DATA_FAILED.equals(error)) throw new HttpServiceUnavailableException(ex);
            throw new HttpInternalServerErrorException(ex);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockExtendedDto> update(@PathVariable UUID id,
                                                   @Valid @RequestBody StockDto requestBody) {
        try {
            var result = stockService.update(id, requestBody);
            return ResponseEntity.ok(result);
        } catch (IcRuntimeException ex) {
            var error = ex.getError();
            throw switch (error) {
                case REPOSITORY_STOCK_NOT_FOUND -> new HttpNotFoundException(ex);
                case REPOSITORY_PERSIST_DATA_FAILED -> new HttpServiceUnavailableException(ex);
                default -> new HttpInternalServerErrorException(ex);
            };
        }
    }

}
