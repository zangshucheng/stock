package com.data.stock.starter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"com.data.stock"})
@EnableScheduling
@MapperScan(basePackages = {"com.data.stock.data.mapper"})
@EnableFeignClients(basePackages = "com.data.stock.openfeign")
public class StockStarterApplication {


    public static void main(String[] args) {
        SpringApplication.run(StockStarterApplication.class, args);
    }

}
