package com.data.stock.web;


import com.data.stock.task.StockTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/test")
public class Testcontroller {

    @Autowired
    private StockTask stockBasicTask;

    @Autowired
    private StockTask stockdailyTask;

    @GetMapping("/daily")
    public void testDaily(){
        stockdailyTask.execute();
        log.warn("ss");
    }

    @GetMapping("/base")
    public void testBase(){
        stockBasicTask.execute();
        log.warn("ss");
    }
}
