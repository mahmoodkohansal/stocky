package ir.mahmood.sahame.controller;

import ir.mahmood.sahame.dto.StockDto;
import ir.mahmood.sahame.exception.TSETMCException;
import ir.mahmood.sahame.service.StockService;
import ir.mahmood.sahame.service.TSETMCService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Component
@RestController
@RequestMapping("/")
public class BaseController {

    private TSETMCService tsetmcService;
    private StockService stockService;

    @Autowired
    public BaseController(TSETMCService tsetmcService, StockService stockService) {
        this.tsetmcService = tsetmcService;
        this.stockService = stockService;
    }

    @GetMapping("/test")
    public String test() {
        try {
//            String stockPrices = tsetmcService.getStockPrices("46348559193224090");
            List<String> stockIds = tsetmcService.getStockIds();

            log.info("Stock Ids fetched from TSETMC");

            List<StockDto> stockDtos = new ArrayList<>();

            int count = 0;
            for (String stockId: stockIds) {
                stockDtos.add(tsetmcService.getStockDetails(stockId));
                count += 1;

                if (count % 10 == 0) {
                    log.info("Stock " + (count - 10)  + " - " + count + " are ready to persist in DB");
                    stockService.bulkStore(stockDtos);
                    log.info("Stock " + (count - 10)  + " - " + count + " persisted in DB");
                }
            }

            log.info("Stock Details persist in DB");

            return "Done";
        } catch (TSETMCException e) {
            log.error(e, e);
            return e.getMessage();
        }
    }
}
