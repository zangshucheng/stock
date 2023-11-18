package com.data.stock.data.mapper;

import com.data.stock.data.domain.StockLimitAnalysis;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author zangshucheng
* @description 针对表【stock_limit_analysis(股票涨跌分析)】的数据库操作Mapper
* @createDate 2023-11-11 21:57:51
* @Entity com.data.stock.data.domain.StockLimitAnalysis
*/
public interface StockLimitAnalysisMapper extends BaseMapper<StockLimitAnalysis> {

    /**
     * 删除当前时间的数据
     * @param tradeDate
     */
    void deletebyTradeDate(@Param("tradeDate") String tradeDate);

    /**
     * 插入数据
     * @param stockLimitAnalysisList
     */
    void insertIgnore(@Param("stockLimitAnalysisList") List<StockLimitAnalysis> stockLimitAnalysisList);

    /**
     * 插入数据
     * @param stockLimitAnalysisList
     */
    void insertReplace(@Param("stockLimitAnalysisList") List<StockLimitAnalysis> stockLimitAnalysisList);
}




