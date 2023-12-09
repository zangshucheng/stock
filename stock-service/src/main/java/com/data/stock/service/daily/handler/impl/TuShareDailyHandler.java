package com.data.stock.service.daily.handler.impl;

import com.data.stock.common.constant.MagicNumberConstants;
import com.data.stock.common.constant.TuShareURLConstants;
import com.data.stock.common.utils.MathUtil;
import com.data.stock.data.domain.StockBase;
import com.data.stock.data.service.StockBaseService;
import com.data.stock.openfeign.tushare.TuSahreBasicDataService;
import com.data.stock.openfeign.tushare.domain.*;
import com.data.stock.service.daily.domain.StockDailyBO;
import com.data.stock.service.daily.domain.StockLimitBO;
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
import java.util.function.Function;
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

        //获取每日行情
        List<TuSahreStockDailyDTO> tuShareStockBasics = new ArrayList<>();
        int offset = MagicNumberConstants.STOCK_BASIC_OFFSET_START;
        TuSahreStockBasicPageDTO<TuSahreStockDailyDTO> stockDailyPageDTO = null;
        do {
            //取数据
            stockDailyPageDTO = tuSahreBasicDataService.daily(new TuSahreStockDailyQueryDTO(MagicNumberConstants.STOCK_BASIC_LIMIT, offset, trdaeDate));

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

        //获取每日指标
        List<TuShareDailyBasicDTO> tuShareDailyBasics = new ArrayList<>();

        offset = MagicNumberConstants.STOCK_BASIC_OFFSET_START;
        TuSahreStockBasicPageDTO<TuShareDailyBasicDTO> dailyBasicPageDTO = null;
        do {
            //取数据
            dailyBasicPageDTO = tuSahreBasicDataService.dailyBasic(new TuShareDailyBasicQueryDTO(MagicNumberConstants.STOCK_BASIC_LIMIT, offset, trdaeDate));

            if (Objects.isNull(dailyBasicPageDTO) || CollectionUtils.isEmpty(dailyBasicPageDTO.getTuShareStockBasics())) {
                break;
            }
            tuShareDailyBasics.addAll(dailyBasicPageDTO.getTuShareStockBasics());
            offset += MagicNumberConstants.STOCK_BASIC_LIMIT;
        } while (!Objects.isNull(dailyBasicPageDTO) && dailyBasicPageDTO.isHas_more());

        Map<String, TuShareDailyBasicDTO> dailyBasicMap = tuShareDailyBasics.stream().collect(Collectors.toMap(TuShareDailyBasicDTO::getTs_code, Function.identity()));

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
            stockDaily.setRation(new BigDecimal(d.getPct_chg()));
            stockDaily.setChangeRange(new BigDecimal(d.getChange()));
            stockDaily.setTradeVolume(new BigDecimal(d.getAmount()));
            stockDaily.setAmount(new BigDecimal(d.getAmount()));
            stockDaily.setCirculateMarketValue(MathUtil.stringToBigdecimalDivide(dailyBasicMap.get(d.getTs_code()).getCirc_mv(), 10000));
            stockDaily.setTurnoverRate(MathUtil.stringToBigdecimal(dailyBasicMap.get(d.getTs_code()).getTurnover_rate()));
            stockDaily.setVolumnRation(MathUtil.stringToBigdecimal(dailyBasicMap.get(d.getTs_code()).getVolume_ratio()));
            return stockDaily;
        }).collect(Collectors.toList());

        return stockDailyList;
    }

    @Override
    public List<StockLimitBO> dailyLimitList(String trdaeDate) {
        //非交易日不去拉取数据
        if (!tradeCalendar(trdaeDate)) {
            log.warn("当前不是交易日，日期：{}", trdaeDate);
            return null;
        }

        List<TuShareLimitListDTO> tuShareLimitListDTOList = new ArrayList<>();

        int offset = MagicNumberConstants.STOCK_BASIC_OFFSET_START;
        TuSahreStockBasicPageDTO<TuShareLimitListDTO> tuSahreLimitListPageDTO = null;
        do {
            //取数据
            tuSahreLimitListPageDTO = tuSahreBasicDataService.limitList(new TuShareLimitListQueryDTO(MagicNumberConstants.STOCK_DAILY_LIMIT, offset, trdaeDate));

            if (Objects.isNull(tuSahreLimitListPageDTO) || CollectionUtils.isEmpty(tuSahreLimitListPageDTO.getTuShareStockBasics())) {
                break;
            }
            tuShareLimitListDTOList.addAll(tuSahreLimitListPageDTO.getTuShareStockBasics());
            offset += MagicNumberConstants.STOCK_DAILY_LIMIT;
        } while (!Objects.isNull(tuSahreLimitListPageDTO) && tuSahreLimitListPageDTO.isHas_more());

        if (CollectionUtils.isEmpty(tuShareLimitListDTOList)) {
            log.warn("未取到股票日行情信息");
            return null;
        }
        List<StockLimitBO> stockLimitBOList = new ArrayList<>();
        Map<String, StockBase> tsMap = stockBaseService.selectStockCodeMap();
        tuShareLimitListDTOList.stream().forEach(limit ->{
            StockLimitBO stockLimitBO = new StockLimitBO();
            stockLimitBO.setStockCode(tsMap.get(limit.getTs_code()).getStockCode());
            stockLimitBO.setStockName(tsMap.get(limit.getTs_code()).getStockName());
            stockLimitBO.setTradeDate(limit.getTrade_date());
//            stockLimitBO.setHypeSubject();
            stockLimitBO.setClose(MathUtil.stringToBigdecimal(limit.getClose()));
            stockLimitBO.setPctChange(MathUtil.stringToBigdecimal(limit.getPct_chg()));
            stockLimitBO.setTurnoverRate(MathUtil.stringToBigdecimal(limit.getTurnover_ratio()));
            stockLimitBO.setCirculateMarketValue(MathUtil.stringToBigdecimalDivide(limit.getFloat_mv(), 100000000));
            stockLimitBO.setVolatility(MathUtil.stringToBigdecimal(limit.getSwing()));
            stockLimitBO.setAmount(MathUtil.stringToBigdecimalDivide(limit.getAmount(), 100000000));
            stockLimitBO.setLimitAmount(MathUtil.stringToBigdecimalDivide(limit.getLimit_amount(), 100000000));
            stockLimitBO.setFirstTime(limit.getFirst_time());
            stockLimitBO.setLastTime(limit.getLast_time());
            stockLimitBO.setOpenTimes(MathUtil.stringToInteger(limit.getOpen_times()));
            stockLimitBO.setUpStatistics(limit.getUp_stat());
            stockLimitBO.setLimitTimes(MathUtil.stringToInteger(limit.getLimit_times()));
            stockLimitBO.setLimitType(limit.getLimitType());
            stockLimitBO.setIndustry(limit.getIndustry());
            stockLimitBOList.add(stockLimitBO);
        });
        return stockLimitBOList;
    }
}
