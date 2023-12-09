package com.data.stock.service.daily.impl;

import com.data.stock.data.domain.StockDaily;
import com.data.stock.data.domain.StockLimitAnalysis;
import com.data.stock.data.service.StockDailyService;
import com.data.stock.data.service.StockLimitAnalysisService;
import com.data.stock.service.daily.DailyService;
import com.data.stock.service.daily.domain.StockDailyBO;
import com.data.stock.service.daily.domain.StockLimitBO;
import com.data.stock.service.daily.handler.DailyHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resources;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DailyServiceImpl implements DailyService {

    @Autowired
    @Qualifier("eastMoneyDailyHandler")
    private DailyHandler dailyHandler;

    @Autowired
    private StockLimitAnalysisService limitAnalysisService;

    @Autowired
    private StockDailyService stockDailyService;

    @Override
    public void dailyMarket(String tradeDate) {

        List<StockDailyBO> stockDailys = dailyHandler.dailyMarket(tradeDate);

        if(CollectionUtils.isEmpty(stockDailys)){
            log.warn("交易日期：{} 未取到当天交易数据！");
        }

        List<StockDaily> stockDailyList = stockDailys.stream().map(d -> {
            StockDaily stockDaily = new StockDaily();
            stockDaily.setStockCode(d.getStockCode());
            stockDaily.setStockName(d.getStockName());
            stockDaily.setTradeDate(d.getTradeDate());
            stockDaily.setOpen(d.getOpen());
            stockDaily.setHigh(d.getHigh());
            stockDaily.setLow(d.getLow());
            stockDaily.setClose(d.getClose());
            stockDaily.setPreClose(d.getPreClose());
            stockDaily.setAmplitudeRatio(d.getAmplitudeRatio());
            stockDaily.setRation(d.getRation());
            stockDaily.setTradeVolume(d.getTradeVolume());
            stockDaily.setChangeRange(d.getChangeRange());
            stockDaily.setAmount(d.getAmount());
            stockDaily.setCirculateMarketValue(d.getCirculateMarketValue());
            stockDaily.setTurnoverRatio(d.getTurnoverRate());
            stockDaily.setVolumnRation(d.getVolumnRation());
            return stockDaily;
        }).collect(Collectors.toList());
        stockDailyService.deletebyTradeDate(tradeDate);
        //日行情数据入库
        stockDailyService.saveBatch(stockDailyList);
    }

    @Override
    public void dailyLimitList(String tradeDate) {
        List<StockLimitBO> stockLimitBOList = dailyHandler.dailyLimitList(tradeDate);
        if(CollectionUtils.isEmpty(stockLimitBOList)){
            log.warn("未获取到当日涨跌停数据，日期：{}", tradeDate);
            return;
        }
        List<StockLimitAnalysis> limitAnalyses = new ArrayList<>();
        stockLimitBOList.forEach(limit ->{
            StockLimitAnalysis stockLimitAnalysis = new StockLimitAnalysis();
            stockLimitAnalysis.setStockCode(limit.getStockCode());
            stockLimitAnalysis.setStockName(limit.getStockName());
            stockLimitAnalysis.setTradeDate(limit.getTradeDate());
//            stockLimitAnalysis.setHypeSubject();
            stockLimitAnalysis.setClose(limit.getClose());
            stockLimitAnalysis.setPctChange(limit.getPctChange());
            stockLimitAnalysis.setTurnoverRate(limit.getTurnoverRate());
            stockLimitAnalysis.setCirculateMarketValue(limit.getCirculateMarketValue());
            stockLimitAnalysis.setVolatility(limit.getVolatility());
            stockLimitAnalysis.setAmount(limit.getAmount());
            stockLimitAnalysis.setLimitAmount(limit.getLimitAmount());
            stockLimitAnalysis.setFirstTime(limit.getFirstTime());
            stockLimitAnalysis.setLastTime(limit.getLastTime());
            stockLimitAnalysis.setOpenTimes(limit.getOpenTimes());
            stockLimitAnalysis.setUpStatistics(limit.getUpStatistics());
            stockLimitAnalysis.setLimitTimes(limit.getLimitTimes());
            stockLimitAnalysis.setLimitType(limit.getLimitType());
            stockLimitAnalysis.setIndustry(limit.getIndustry());
            limitAnalyses.add(stockLimitAnalysis);
        });

        limitAnalysisService.insertReplace(limitAnalyses);
    }
}
