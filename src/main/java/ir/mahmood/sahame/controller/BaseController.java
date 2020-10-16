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
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Log4j2
@Component
@RestController
@RequestMapping("/site/")
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
        return "1";
    }
}
