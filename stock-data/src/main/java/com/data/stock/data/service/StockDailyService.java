package com.data.stock.data.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.data.stock.data.domain.StockDaily;

/**
* @author zangshucheng
* @description 针对表【stock_daily】的数据库操作Service
* @createDate 2023-11-07 21:53:55
*/
public interface StockDailyService extends IService<StockDaily> {
    void deletebyTradeDate(String tradeDate);
}
