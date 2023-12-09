package com.data.stock.data.service;

import com.data.stock.data.domain.StockDaily;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author zangshucheng
* @description 针对表【stock_daily】的数据库操作Service
* @createDate 2023-11-25 13:04:29
*/
public interface StockDailyService extends IService<StockDaily> {
    void deletebyTradeDate(String tradeDate);
}
