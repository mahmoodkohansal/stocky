package ir.mahmood.sahame.controller;

import ir.mahmood.sahame.dto.BuyDto;
import ir.mahmood.sahame.dto.StockDto;
import ir.mahmood.sahame.entity.BuyEntity;
import ir.mahmood.sahame.exception.StockNotFoundException;
import ir.mahmood.sahame.exception.TSETMCException;
import ir.mahmood.sahame.service.StockService;
import ir.mahmood.sahame.service.TSETMCService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Log4j2
@RestController
@RequestMapping("/api/stocks/")
public class StockController {

    private TSETMCService tsetmcService;
    private StockService stockService;

    @Autowired
    public StockController(TSETMCService tsetmcService, StockService stockService) {
        this.tsetmcService = tsetmcService;
        this.stockService = stockService;
    }

    @GetMapping("/update-data")
    public String updateStocksBaseData() throws ExecutionException, InterruptedException {
        try {
            List<String> newStockIds = tsetmcService.getStockIds();

            log.info("New Stock Ids fetched from TSETMC");

            List<String> persistedStockIds = stockService.list().stream().map(StockDto::getTsetmcId)
                    .collect(Collectors.toList());

            log.info("Persisted Stock Ids fetched from DB");

            List<String> differenceStockIds = newStockIds.stream().filter(Predicate.not(persistedStockIds::contains)).collect(Collectors.toList());

            log.info("Starting to get " + differenceStockIds.size() + " new stock data from TSETMC");

            List<StockDto> stockDtos = new ArrayList<>();
            for (String stockId: differenceStockIds) {
                stockDtos.add(tsetmcService.getStockDetails(stockId).get());
            }
            log.info("Get all stocks data from TSETMC and create DTOs");

            stockService.bulkStore(stockDtos);

            log.info("Stock Details persisted in DB");

            return "Done";
        } catch (TSETMCException e) {
            log.error(e, e);
            return e.getMessage();
        }
    }

    @GetMapping("/")
    public Page<StockDto> listStocks(Pageable pageable, @RequestParam(value = "search", required = false) String search) {
        return stockService.list(pageable, search);
    }

    @PostMapping("/{stockId}/buy")
    public ResponseEntity<BuyEntity> buy(
            @PathVariable Integer stockId,
            @Valid @RequestBody BuyDto buyDto
    ) throws StockNotFoundException {
        return ResponseEntity.ok(stockService.buy(stockId, buyDto));
    }

    @PostMapping("/sell")
    public void sell() {

    }

    @GetMapping("/portfolio")
    public void portfolio() {

    }

}
