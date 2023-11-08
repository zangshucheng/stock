package com.data.stock.data.mapper;

import com.data.stock.data.domain.StockLimitAnalysis;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author zangshucheng
* @description 针对表【stock_limit_analysis(股票涨跌分析)】的数据库操作Mapper
* @createDate 2023-11-08 21:12:05
* @Entity com.data.stock.data.domain.StockLimitAnalysis
*/
public interface StockLimitAnalysisMapper extends BaseMapper<StockLimitAnalysis> {

    /**
     * 删除当前时间的数据
     * @param tradeDate
     */
    void deletebyTradeDate(@Param("tradeDate") String tradeDate);
}




