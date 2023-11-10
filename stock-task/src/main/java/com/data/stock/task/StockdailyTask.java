package com.data.stock.task;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.data.stock.common.constant.MagicNumberConstants;
import com.data.stock.common.constant.StringConstants;
import com.data.stock.common.constant.TuShareURLConstants;
import com.data.stock.common.utils.DateUtil;
import com.data.stock.common.utils.MathUtil;
import com.data.stock.data.domain.StockBase;
import com.data.stock.data.domain.StockDaily;
import com.data.stock.data.domain.StockLimitAnalysis;
import com.data.stock.data.service.StockBaseService;
import com.data.stock.data.service.StockDailyService;
import com.data.stock.data.service.StockLimitAnalysisService;
import com.data.stock.openfeign.tushare.BasicDataService;
import com.data.stock.openfeign.tushare.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Component("stockdailyTask")
@Slf4j
public class StockdailyTask implements StockTask{

    @Autowired
    private BasicDataService basicDataService;

    @Autowired
    private StockDailyService stockDailyService;

    @Autowired
    private StockBaseService stockBaseService;

    @Autowired
    private StockLimitAnalysisService limitAnalysisService;

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

        Map<String, StockBase> tsMap = stockBaseService.selectStockCodeMap();

        List<StockDaily> stockDailyList = tuShareStockBasics.stream().map(d -> {
            StockDaily stockDaily = new StockDaily();
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
        stockDailyService.deletebyTradeDate(DateUtil.getDateFormat());
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
                stockLimitAnalysis.setRangePercent(stock.getPctChg());
                limitAnalyses.add(stockLimitAnalysis);
            }
        });

        if(!CollectionUtils.isEmpty(limitAnalyses)){
            //删除当前数据
            limitAnalysisService.deletebyTradeDate(DateUtil.getDateFormat());
            limitAnalysisService.saveBatch(limitAnalyses);
        }
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
