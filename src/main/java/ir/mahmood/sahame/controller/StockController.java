package ir.mahmood.sahame.controller;

import ir.mahmood.sahame.exception.TSETMCException;
import ir.mahmood.sahame.service.TSETMCService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/stock/")
public class StockController {

    public TSETMCService tsetmcService;

    @Autowired
    public StockController(TSETMCService tsetmcService) {
        this.tsetmcService = tsetmcService;
    }

    @GetMapping("/test")
    public String test() {
        try {
//            String stockPrices = tsetmcService.getStockPrices("46348559193224090");
            List<String> stockPrices = tsetmcService.getStockIds();
            log.info(stockPrices);
            return String.join(" ,", stockPrices);
        } catch (TSETMCException e) {
            log.error(e, e);
            return e.getMessage();
        }
    }

    @PostMapping("/buy")
    public void buy() {

    }

    @PostMapping("/sell")
    public void sell() {

    }

    @GetMapping("/portfolio")
    public void portfolio() {

    }

}
