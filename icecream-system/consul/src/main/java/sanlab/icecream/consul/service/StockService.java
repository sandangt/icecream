package sanlab.icecream.consul.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sanlab.icecream.fundamentum.dto.core.StockDto;
import sanlab.icecream.fundamentum.dto.exntended.StockExtendedDto;
import sanlab.icecream.consul.mapper.StockMapper;
import sanlab.icecream.consul.model.Stock;
import sanlab.icecream.consul.repository.crud.StockRepository;
import sanlab.icecream.fundamentum.contractmodel.response.CollectionQueryResponse;
import sanlab.icecream.fundamentum.exception.IcRuntimeException;

import java.util.List;
import java.util.UUID;

import static sanlab.icecream.consul.exception.ConsulErrorModel.REPOSITORY_PERSIST_DATA_FAILED;
import static sanlab.icecream.consul.exception.ConsulErrorModel.REPOSITORY_STOCK_NOT_FOUND;
import static sanlab.icecream.fundamentum.utils.ObjectUtils.copyNotNull;
import static sanlab.icecream.fundamentum.utils.RequestUtils.calculateTotalPage;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

    private final StockMapper stockMapper;

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
            .orElseThrow(() -> new IcRuntimeException(REPOSITORY_STOCK_NOT_FOUND, "id: %s".formatted(id)));
    }

    public StockExtendedDto create(StockDto request) {
        try {
            Stock stock = stockRepository.save(stockMapper.dtoToEntity(request));
            return stockMapper.entityToExtendedDto(stock);
        } catch (Exception ex) {
            throw new IcRuntimeException(ex, REPOSITORY_PERSIST_DATA_FAILED, "stock");
        }
    }

    public StockExtendedDto update(UUID id, StockDto request) {
        Stock targetStock = stockRepository.findById(id)
            .orElseThrow(() -> new IcRuntimeException(REPOSITORY_STOCK_NOT_FOUND, "id: %s".formatted(id)));
        try {
            Stock sourceStock = stockMapper.dtoToEntity(request);
            copyNotNull(sourceStock, targetStock);
            return stockMapper.entityToExtendedDto(stockRepository.save(targetStock));
        } catch (Exception ex) {
            throw new IcRuntimeException(ex, REPOSITORY_PERSIST_DATA_FAILED, "stock");
        }
    }

}
