package com.data.stock.web;


import com.data.stock.service.base.BaseStockService;
import com.data.stock.service.daily.DailyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/test")
public class Testcontroller {

    @Autowired
    private BaseStockService baseStockService;

    @Autowired
    private DailyService dailyService;

    @GetMapping("/daily")
    public void testDaily(String tradeDate){
        dailyService.dailyMarket(tradeDate);
        log.warn("ss");
    }

    @GetMapping("/dailyLimit")
    public void testDailyLimit(String tradeDate){
        dailyService.dailyLimitList(tradeDate);
        log.warn("ss");
    }

    @GetMapping("/base")
    public void testBase(){
        baseStockService.stockBase();
        log.warn("ss");
    }
}
