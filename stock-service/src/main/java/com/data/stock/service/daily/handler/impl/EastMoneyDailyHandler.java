package com.data.stock.service.daily.handler.impl;

import com.data.stock.common.utils.MathUtil;
import com.data.stock.openfeign.eastmoney.EastMoneyStockBaseService;
import com.data.stock.openfeign.eastmoney.domain.EastLatestStockDTO;
import com.data.stock.service.daily.domain.StockDailyBO;
import com.data.stock.service.daily.domain.StockLimitBO;
import com.data.stock.service.daily.handler.DailyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("eastMoneyDailyHandler")
public class EastMoneyDailyHandler implements DailyHandler {

    @Autowired
    private EastMoneyStockBaseService eastMoneyStockBaseService;

    @Override
    public boolean tradeCalendar(String tradeDate) {
        return false;
    }

    @Override
    public List<StockDailyBO> dailyMarket(String tradeDate) {

        List<EastLatestStockDTO> latestAllStockInfo = eastMoneyStockBaseService.getLatestAllStockInfo();

        List<StockDailyBO> stockDailyBOS = latestAllStockInfo.stream().map(s -> {
            StockDailyBO stockDailyBO = new StockDailyBO();
            stockDailyBO.setStockCode(s.getF12());
            stockDailyBO.setStockName(s.getF14());
            stockDailyBO.setClose(MathUtil.stringToBigdecimal(s.getF2()));
            stockDailyBO.setPctChange(MathUtil.stringToBigdecimal(s.getF3()));
            stockDailyBO.setChangeRange(MathUtil.stringToBigdecimal(s.getF4()));
            stockDailyBO.setTradeVolume(MathUtil.stringToBigdecimal(s.getF5()));
            stockDailyBO.setAmount(MathUtil.stringToBigdecimal(s.getF6()));
            stockDailyBO.setHigh(MathUtil.stringToBigdecimal(s.getF15()));
            stockDailyBO.setAmplitudeRatio(MathUtil.stringToBigdecimal(s.getF7()));
            stockDailyBO.setLow(MathUtil.stringToBigdecimal(s.getF16()));
            stockDailyBO.setOpen(MathUtil.stringToBigdecimal(s.getF17()));
            stockDailyBO.setPreClose(MathUtil.stringToBigdecimal(s.getF18()));
            stockDailyBO.setVolumnRation(MathUtil.stringToBigdecimal(s.getF10()));
            stockDailyBO.setTurnoverRate(MathUtil.stringToBigdecimal(s.getF8()));
            stockDailyBO.setCirculateMarketValue(MathUtil.stringToBigdecimal(s.getF21()));
            stockDailyBO.setTradeDate(tradeDate);
            return stockDailyBO;
        }).collect(Collectors.toList());

        return stockDailyBOS;
    }

    @Override
    public List<StockLimitBO> dailyLimitList(String trdaeDate) {




        return null;
    }
}
