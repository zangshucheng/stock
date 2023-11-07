package com.data.stock.task;

import com.data.stock.common.constant.MagicNumberConstants;
import com.data.stock.common.constant.TuShareURLConstants;
import com.data.stock.common.utils.DateUtil;
import com.data.stock.openfeign.tushare.BasicDataService;
import com.data.stock.openfeign.tushare.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class StockdailyTask implements StockTask{

    @Autowired
    private BasicDataService basicDataService;

    @Override
    public void execute() {
        //非交易日不去拉取数据
        if(!isTradeDay()){
            return;
        }

        List<StockDailyDTO> tuShareStockBasics = new ArrayList<>();
        int offset = MagicNumberConstants.STOCK_BASIC_OFFSET_START;
        StockBasicPageDTO<StockDailyDTO> stockDailyPageDTO = null;
        do {
            //取数据
            stockDailyPageDTO = basicDataService.dailyMarket(new StockDailyQueryDTO(MagicNumberConstants.STOCK_BASIC_LIMIT, offset, DateUtil.getDateFormat()));

            if(Objects.isNull(stockDailyPageDTO) || CollectionUtils.isEmpty(stockDailyPageDTO.getTuShareStockBasics())){
                break;
            }
            tuShareStockBasics.addAll(stockDailyPageDTO.getTuShareStockBasics());
            offset += MagicNumberConstants.STOCK_BASIC_LIMIT;
        }while (!Objects.isNull(stockDailyPageDTO) && stockDailyPageDTO.isHas_more());


    }

    private boolean isTradeDay(){
        TradeCalendarQueryDTO requestDTO = new TradeCalendarQueryDTO();
        requestDTO.setStart_date(DateUtil.getDateFormat());
        requestDTO.setEnd_date(DateUtil.getDateFormat());
        StockBasicPageDTO<TradeCalendarDTO> stockBasicPageDTO = basicDataService.tradeCalendar(requestDTO);

        if(!Objects.isNull(stockBasicPageDTO) && !CollectionUtils.isEmpty(stockBasicPageDTO.getTuShareStockBasics())){
            String isOpen = stockBasicPageDTO.getTuShareStockBasics().get(0).getIs_open();
            return TuShareURLConstants.TRADE_OPEN.equals(isOpen);
        }

        return false;
    }
}
