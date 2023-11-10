package com.data.stock.service.daily;

import com.data.stock.service.daily.domain.StockDailyBO;

import java.util.List;

public interface DailyService {

    /**
     * 股票当日行情
     * @return
     */
    void dailyMarket(String trdaeDate);
}
