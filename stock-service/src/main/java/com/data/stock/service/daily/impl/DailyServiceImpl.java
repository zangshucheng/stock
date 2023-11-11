package com.data.stock.service.daily.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.data.stock.common.constant.MagicNumberConstants;
import com.data.stock.common.constant.StringConstants;
import com.data.stock.common.utils.DateUtil;
import com.data.stock.common.utils.MathUtil;
import com.data.stock.data.domain.StockDaily;
import com.data.stock.data.domain.StockLimitAnalysis;
import com.data.stock.data.service.StockDailyService;
import com.data.stock.data.service.StockLimitAnalysisService;
import com.data.stock.openfeign.tushare.domain.TuSahreStockBasicPageDTO;
import com.data.stock.openfeign.tushare.domain.TuSahreStockDailyDTO;
import com.data.stock.service.daily.DailyService;
import com.data.stock.service.daily.domain.StockDailyBO;
import com.data.stock.service.daily.handler.DailyHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DailyServiceImpl implements DailyService {

    @Autowired
    private DailyHandler tuShareDailyHandler;

    @Autowired
    private StockLimitAnalysisService limitAnalysisService;

    @Autowired
    private StockDailyService stockDailyService;

    @Override
    public void dailyMarket(String tradeDate) {
        List<StockDailyBO> stockDailys = tuShareDailyHandler.dailyMarket(tradeDate);

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
            stockDaily.setPctChange(d.getPctChg());
            stockDaily.setTradeVolume(d.getTradeVolume());
            stockDaily.setChangeRange(d.getChangeRange());
            stockDaily.setAmount(d.getAmount());
            stockDaily.setCirculateMarketValue(d.getCirculateMarketValue());
            stockDaily.setTurnoverRate(d.getTurnoverRate());
            stockDaily.setVolumnRation(d.getVolumnRation());
            return stockDaily;
        }).collect(Collectors.toList());
        stockDailyService.deletebyTradeDate(tradeDate);
        //日行情数据入库
        stockDailyService.saveBatch(stockDailyList);

        List<StockLimitAnalysis> limitAnalyses = new ArrayList<>();

        stockDailyList.stream().forEach(stock ->{

            String limitType = null;

            //判断涨停
            if(MathUtil.upLimt(stock.getStockCode(), stock.getClose(), stock.getPreClose())){
                limitType = StringConstants.LIMIT_TYPE_UP;
            }

            //判断跌停
            if(MathUtil.downLimt(stock.getStockCode(), stock.getClose(), stock.getPreClose())){
                limitType = StringConstants.LIMIT_TYPE_DOWN;
            }

            //符合涨跌停
            if(StringUtils.isNotBlank(limitType)){
                StockLimitAnalysis stockLimitAnalysis = new StockLimitAnalysis();
                stockLimitAnalysis.setStockCode(stock.getStockCode());
                stockLimitAnalysis.setLimitType(limitType);
                stockLimitAnalysis.setStockName(stock.getStockName());
                stockLimitAnalysis.setTradeDate(stock.getTradeDate());
                stockLimitAnalysis.setPrice(stock.getClose());
                stockLimitAnalysis.setRangePercent(stock.getPctChange());
                stockLimitAnalysis.setCirculateMarketValue(stock.getCirculateMarketValue());
                stockLimitAnalysis.setTurnoverRate(stock.getTurnoverRate());
                limitAnalyses.add(stockLimitAnalysis);
            }
        });

        if(!CollectionUtils.isEmpty(limitAnalyses)){
            //删除当前数据
            limitAnalysisService.deletebyTradeDate(tradeDate);
            limitAnalysisService.saveBatch(limitAnalyses);
        }
    }
}
