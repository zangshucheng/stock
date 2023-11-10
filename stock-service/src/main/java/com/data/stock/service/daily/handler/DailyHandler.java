package com.data.stock.service.daily.handler;

import com.data.stock.service.daily.domain.StockDailyBO;

import java.util.List;

public interface DailyHandler {

    /**
     * 交易日历
     * @param tradeDate
     * @return
     */
    boolean tradeCalendar(String tradeDate);

    /**
     * 股票当日行情
     * @return
     */
    List<StockDailyBO> dailyMarket(String trdaeDate);
}
