package com.data.stock.data.service;

import com.data.stock.data.domain.StockLimitAnalysis;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author zangshucheng
* @description 针对表【stock_limit_analysis(股票涨跌分析)】的数据库操作Service
* @createDate 2023-11-11 21:57:51
*/
public interface StockLimitAnalysisService extends IService<StockLimitAnalysis> {

    void deletebyTradeDate(String tradeDate);

    /**
     * 插入数据
     * @param stockLimitAnalysisList
     */
    void insertIgnore(List<StockLimitAnalysis> stockLimitAnalysisList);
}
