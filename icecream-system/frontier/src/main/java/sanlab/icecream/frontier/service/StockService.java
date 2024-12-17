package sanlab.icecream.frontier.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sanlab.icecream.frontier.dto.core.StockDto;
import sanlab.icecream.frontier.dto.extended.StockExtendedDto;
import sanlab.icecream.fundamentum.exception.ItemNotFoundException;
import sanlab.icecream.fundamentum.exception.StoringDatabaseException;
import sanlab.icecream.frontier.mapper.IStockMapper;
import sanlab.icecream.frontier.model.Stock;
import sanlab.icecream.frontier.repository.crud.IStockRepository;
import sanlab.icecream.frontier.viewmodel.response.CollectionQueryResponse;

import java.util.List;
import java.util.UUID;

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
            .orElseThrow(() -> ItemNotFoundException.stock(id));
    }

    public StockExtendedDto create(StockDto request) {
        try {
            Stock stock = stockRepository.save(stockMapper.dtoToEntity(request));
            return stockMapper.entityToExtendedDto(stock);
        } catch (Exception ignore) {
            throw new StoringDatabaseException("Error occurs when creating stock");
        }
    }

    public StockExtendedDto update(UUID id, StockDto request) {
        Stock targetStock = stockRepository.findById(id)
            .orElseThrow(() -> ItemNotFoundException.stock(id));
        Stock sourceStock = stockMapper.dtoToEntity(request);
        copyNotNull(sourceStock, targetStock);
        try {
            return stockMapper.entityToExtendedDto(stockRepository.save(targetStock));
        } catch (Exception ignore) {
            throw new StoringDatabaseException("Error occurs when creating stock");
        }
    }

}
