package ir.mahmood.sahame.job;

import ir.mahmood.sahame.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class StockPriceJob {
    private static final Logger log = LoggerFactory.getLogger(BaseController.class);

    @Scheduled(cron = "0 0 19 * * ?", zone = "Asia/Tehran")
    public void getPrices() {

    }

}
