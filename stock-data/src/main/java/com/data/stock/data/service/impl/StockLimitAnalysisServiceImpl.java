package com.data.stock.data.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.data.stock.data.domain.StockLimitAnalysis;
import com.data.stock.data.service.StockLimitAnalysisService;
import com.data.stock.data.mapper.StockLimitAnalysisMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author zangshucheng
* @description 针对表【stock_limit_analysis(股票涨跌分析)】的数据库操作Service实现
* @createDate 2023-11-11 21:57:51
*/
@Service
public class StockLimitAnalysisServiceImpl extends ServiceImpl<StockLimitAnalysisMapper, StockLimitAnalysis>
    implements StockLimitAnalysisService{

    @Override
    public void deletebyTradeDate(String tradeDate) {
        this.baseMapper.deletebyTradeDate(tradeDate);
    }

    @Override
    public void insertIgnore(List<StockLimitAnalysis> stockLimitAnalysisList) {
        this.baseMapper.insertIgnore(stockLimitAnalysisList);
    }

    @Override
    public void insertReplace(List<StockLimitAnalysis> stockLimitAnalysisList) {
        this.baseMapper.insertReplace(stockLimitAnalysisList);
    }
}




