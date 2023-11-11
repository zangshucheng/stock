package com.data.stock.data.service;

import com.data.stock.data.domain.StockLimitAnalysis;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author zangshucheng
* @description 针对表【stock_limit_analysis(股票涨跌分析)】的数据库操作Service
* @createDate 2023-11-11 21:57:51
*/
public interface StockLimitAnalysisService extends IService<StockLimitAnalysis> {

    void deletebyTradeDate(String tradeDate);
}
