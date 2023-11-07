package com.data.stock.openfeign.tushare;

import com.data.stock.openfeign.tushare.domain.StockBasicPageDTO;
import com.data.stock.openfeign.tushare.domain.StockBasicQueryDTO;
import com.data.stock.openfeign.tushare.domain.TradeCalendarPageDTO;
import com.data.stock.openfeign.tushare.domain.TradeCalendarQueryDTO;

/**
 * 获取股票基础数据
 * 参考：https://tushare.pro/document/2?doc_id=25
 */
public interface BasicDataService {

    /**
     *  获取股票列表
     * @param requestDTO
     * @return
     */
    StockBasicPageDTO stockBasic(StockBasicQueryDTO requestDTO);

    /**
     * 获取各大交易所交易日历数据,默认提取的是上交所
     * @param requestDTO
     * @return
     */
    TradeCalendarPageDTO tradeCalendar(TradeCalendarQueryDTO requestDTO);
}
