package com.data.stock.service.daily;

public interface DailyService {

    /**
     * 股票当日行情
     * @return
     */
    void dailyMarket(String trdaeDate);

    /**
     * 股票当日涨停/跌停/炸板
     * @return
     */
    void dailyLimitList(String trdaeDate);
}
