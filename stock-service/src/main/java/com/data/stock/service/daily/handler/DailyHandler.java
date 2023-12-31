package com.data.stock.service.daily.handler;

import com.data.stock.service.daily.domain.StockDailyBO;
import com.data.stock.service.daily.domain.StockLimitBO;

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

    /**
     * 股票当日涨停/跌停/炸板
     * @return
     */
    List<StockLimitBO> dailyLimitList(String trdaeDate);
}
