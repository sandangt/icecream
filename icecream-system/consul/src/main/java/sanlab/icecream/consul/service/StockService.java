package sanlab.icecream.consul.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sanlab.icecream.consul.dto.core.StockDto;
import sanlab.icecream.consul.dto.extended.StockExtendedDto;
import sanlab.icecream.consul.mapper.IStockMapper;
import sanlab.icecream.consul.model.Stock;
import sanlab.icecream.consul.repository.crud.IStockRepository;
import sanlab.icecream.consul.viewmodel.response.CollectionQueryResponse;
import sanlab.icecream.fundamentum.exception.IcRuntimeException;

import java.util.List;
import java.util.UUID;

import static sanlab.icecream.consul.exception.ConsulErrorModel.FAIL_TO_PERSIST_DATA;
import static sanlab.icecream.consul.exception.ConsulErrorModel.STOCK_NOT_FOUND;
import static sanlab.icecream.fundamentum.utils.ObjectUtils.copyNotNull;
import static sanlab.icecream.fundamentum.utils.RequestUtils.calculateTotalPage;

@Service
@RequiredArgsConstructor
public class StockService {

    private final IStockRepository stockRepository;

    private final IStockMapper stockMapper;

    public CollectionQueryResponse<StockExtendedDto> getAll(Pageable pageable) {
        Page<Stock> paginatedStocks = stockRepository.findAll(pageable);
        long total = stockRepository.count();
        List<StockExtendedDto> stockList = stockMapper.entityToExtendedDto(paginatedStocks.stream().toList());
        return CollectionQueryResponse.<StockExtendedDto>builder()
            .total(total)
            .page(pageable.getPageNumber())
            .totalPages(calculateTotalPage(total, pageable.getPageSize()))
            .data(stockList)
            .build();
    }

    public StockExtendedDto getById(UUID id) {
        return stockRepository.findById(id)
            .map(stockMapper::entityToExtendedDto)
            .orElseThrow(() -> new IcRuntimeException(STOCK_NOT_FOUND, "id: %s".formatted(id)));
    }

    public StockExtendedDto create(StockDto request) {
        try {
            Stock stock = stockRepository.save(stockMapper.dtoToEntity(request));
            return stockMapper.entityToExtendedDto(stock);
        } catch (Exception ex) {
            throw new IcRuntimeException(ex, FAIL_TO_PERSIST_DATA, "stock");
        }
    }

    public StockExtendedDto update(UUID id, StockDto request) {
        Stock targetStock = stockRepository.findById(id)
            .orElseThrow(() -> new IcRuntimeException(STOCK_NOT_FOUND, "id: %s".formatted(id)));
        try {
            Stock sourceStock = stockMapper.dtoToEntity(request);
            copyNotNull(sourceStock, targetStock);
            return stockMapper.entityToExtendedDto(stockRepository.save(targetStock));
        } catch (Exception ex) {
            throw new IcRuntimeException(ex, FAIL_TO_PERSIST_DATA, "stock");
        }
    }

}
