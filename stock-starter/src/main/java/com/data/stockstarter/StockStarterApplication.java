package com.data.stockstarter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"com.data"})
@EnableScheduling
@MapperScan(basePackages = {"com.data.stockdata.mapper"})
public class StockStarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockStarterApplication.class, args);
    }

}
