package com.data.stock.task;

import com.data.stock.common.constant.MagicNumberConstants;
import com.data.stock.common.constant.TuShareURLConstants;
import com.data.stock.common.utils.DateUtil;
import com.data.stock.data.domain.StockDaily;
import com.data.stock.data.service.StockBaseService;
import com.data.stock.data.service.StockDailyService;
import com.data.stock.openfeign.tushare.BasicDataService;
import com.data.stock.openfeign.tushare.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
public class StockdailyTask implements StockTask{

    @Autowired
    private BasicDataService basicDataService;

    @Autowired
    private StockDailyService stockDailyService;

    @Autowired
    private StockBaseService stockBaseService;

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

        if(CollectionUtils.isEmpty(tuShareStockBasics)){
            log.warn("未取到股票日行情信息");
            return;
        }

        Map<String, String> tsMap = stockBaseService.selectStockCodeMap();

        List<StockDaily> stockDailyList = tuShareStockBasics.stream().map(d -> {
            StockDaily stockDaily = new StockDaily();
            stockDaily.setStockCode(tsMap.get(d.getTs_code()));
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
        stockDailyService.deletebyTradeDate(DateUtil.getDateFormat());
        //日行情数据入库
        stockDailyService.saveBatch(stockDailyList);


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
