package com.data.stockweb;

import com.data.stockstarter.stocktask.StockTask;
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
    private StockTask stockTask;

    @GetMapping("/test")
    public void test(){
        stockTask.execute();
        log.warn("ss");
    }
}
