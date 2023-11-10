package com.data.stock.service.daily.handler.impl;

import com.data.stock.common.constant.MagicNumberConstants;
import com.data.stock.common.constant.TuShareURLConstants;
import com.data.stock.common.utils.DateUtil;
import com.data.stock.data.domain.StockBase;
import com.data.stock.data.service.StockBaseService;
import com.data.stock.openfeign.tushare.TuSahreBasicDataService;
import com.data.stock.openfeign.tushare.domain.*;
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

@Service("tuShareDailyHandler")
@Slf4j
public class TuShareDailyHandler implements DailyHandler {

    @Autowired
    private TuSahreBasicDataService tuSahreBasicDataService;

    @Autowired
    private StockBaseService stockBaseService;

    @Override
    public boolean tradeCalendar(String tradeDate) {
        TuSahreTradeCalendarQueryDTO requestDTO = new TuSahreTradeCalendarQueryDTO();
        requestDTO.setStart_date(tradeDate);
        requestDTO.setEnd_date(tradeDate);
        TuSahreStockBasicPageDTO<TuSahreTradeCalendarDTO> tuSahreStockBasicPageDTO = tuSahreBasicDataService.tradeCalendar(requestDTO);

        if (!Objects.isNull(tuSahreStockBasicPageDTO) && !CollectionUtils.isEmpty(tuSahreStockBasicPageDTO.getTuShareStockBasics())) {
            String isOpen = tuSahreStockBasicPageDTO.getTuShareStockBasics().get(0).getIs_open();
            return TuShareURLConstants.TRADE_OPEN.equals(isOpen);
        }

        return false;
    }

    @Override
    public List<StockDailyBO> dailyMarket(String trdaeDate) {
        //非交易日不去拉取数据
        if (!tradeCalendar(trdaeDate)) {
            log.warn("当前不是交易日，日期：{}", trdaeDate);
            return null;
        }

        List<TuSahreStockDailyDTO> tuShareStockBasics = new ArrayList<>();
        int offset = MagicNumberConstants.STOCK_BASIC_OFFSET_START;
        TuSahreStockBasicPageDTO<TuSahreStockDailyDTO> stockDailyPageDTO = null;
        do {
            //取数据
            stockDailyPageDTO = tuSahreBasicDataService.dailyMarket(new TuSahreStockDailyQueryDTO(MagicNumberConstants.STOCK_BASIC_LIMIT, offset, trdaeDate));

            if (Objects.isNull(stockDailyPageDTO) || CollectionUtils.isEmpty(stockDailyPageDTO.getTuShareStockBasics())) {
                break;
            }
            tuShareStockBasics.addAll(stockDailyPageDTO.getTuShareStockBasics());
            offset += MagicNumberConstants.STOCK_BASIC_LIMIT;
        } while (!Objects.isNull(stockDailyPageDTO) && stockDailyPageDTO.isHas_more());

        if (CollectionUtils.isEmpty(tuShareStockBasics)) {
            log.warn("未取到股票日行情信息");
            return null;
        }

        Map<String, StockBase> tsMap = stockBaseService.selectStockCodeMap();

        List<StockDailyBO> stockDailyList = tuShareStockBasics.stream().map(d -> {
            StockDailyBO stockDaily = new StockDailyBO();
            stockDaily.setStockCode(tsMap.get(d.getTs_code()).getStockCode());
            stockDaily.setStockName(tsMap.get(d.getTs_code()).getStockName());
            stockDaily.setTradeDate(d.getTrade_date());
            stockDaily.setOpen(new BigDecimal(d.getOpen()));
            stockDaily.setHigh(new BigDecimal(d.getHigh()));
            stockDaily.setLow(new BigDecimal(d.getLow()));
            stockDaily.setClose(new BigDecimal(d.getClose()));
            stockDaily.setPreClose(new BigDecimal(d.getPre_close()));
            stockDaily.setPctChg(new BigDecimal(d.getPct_chg()));
            stockDaily.setChangeRange(new BigDecimal(d.getChange()));
            stockDaily.setTradeVolume(new BigDecimal(d.getAmount()));
            stockDaily.setAmount(new BigDecimal(d.getAmount()));
            return stockDaily;
        }).collect(Collectors.toList());

        return stockDailyList;
    }
}
