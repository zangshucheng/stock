package com.data.stock.openfeign.tushare.impl;


import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.data.stock.common.constant.TuShareURLConstants;
import com.data.stock.common.utils.DateUtil;
import com.data.stock.openfeign.tushare.BasicDataService;
import com.data.stock.openfeign.tushare.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.data.stock.common.constant.TuShareURLConstants.STOCK_BASIC_FIELDS;

@Service
@Slf4j
public class BasicDataServiceImpl implements BasicDataService {

    @Value("${tu.share.token:dae960427ac8f728cd1d4115a9f884e7c5e9ecbe0a25cd703c2ceee1}")
    private String tuShareToken;

    @Value("${tu.share.url:http://api.tushare.pro}")
    private String tuShareURL;

    @Override
    public StockBasicPageDTO stockBasic(StockBasicQueryDTO queryDTO) {
        StockBasicPageDTO<StockBasicDTO> stockBasicPageDTO =
                stockCommonPostRequest(StockBasicDTO.class, queryDTO, TuShareURLConstants.STOCK_BASIC_FIELDS, TuShareURLConstants.STOCK_BASIC_URL);
        return stockBasicPageDTO;
    }

    @Override
    public StockBasicPageDTO tradeCalendar(TradeCalendarQueryDTO requestDTO) {
        StockBasicPageDTO<TradeCalendarDTO> tradeCalendarPageDTO =
                stockCommonPostRequest(TradeCalendarDTO.class, requestDTO, TuShareURLConstants.TRADE_CAL_FIELDS, TuShareURLConstants.TRADE_CAL);
        return tradeCalendarPageDTO;
    }

    @Override
    public StockBasicPageDTO dailyMarket(StockDailyQueryDTO requestDTO) {
        StockBasicPageDTO<StockDailyDTO> stockBasicPageDTO =
                stockCommonPostRequest(StockDailyDTO.class, requestDTO, TuShareURLConstants.DAILY_FIELDS, TuShareURLConstants.DAILY);
        return stockBasicPageDTO;
    }

    private <T, C> StockBasicPageDTO<T> stockCommonPostRequest(Class t, C c, List<String> showFields, String apiName) {

        StockBasicRequestDTO request = new StockBasicRequestDTO(showFields);
        request.setParams(c);
        request.setToken(tuShareToken);
        request.setApi_name(apiName);
        String response = HttpUtil.post(tuShareURL, JSONUtil.toJsonStr(request));

        if (Objects.isNull(request)) {
            log.warn("请求接口 ：{} 失败，未获取到任何响应", apiName);
            return null;
        }

        TuShareStockBasicResponseDTO tuShareStockBasicResponseDTO = JSONUtil.toBean(response, TuShareStockBasicResponseDTO.class);

        if (Objects.isNull(tuShareStockBasicResponseDTO) || tuShareStockBasicResponseDTO.getCode() != 0) {
            log.warn("请求接口 ：{} 失败, response:{}，", apiName, tuShareStockBasicResponseDTO);
            return null;
        }

        List<String> fields = tuShareStockBasicResponseDTO.getData().getFields();
        List<List<String>> items = tuShareStockBasicResponseDTO.getData().getItems();

        if (CollectionUtils.isEmpty(fields) || CollectionUtils.isEmpty(items)) {
            log.warn("请求接口 ：{} 数据存在问题, response:{}，", apiName, tuShareStockBasicResponseDTO);
            return null;
        }

        List<T> stockBasics = new ArrayList<>();

        for (List<String> item : items) {
            T stock = null;
            for (int i = 0; i < fields.size(); i++) {
                try {
                    Field declaredField = t.getDeclaredField(fields.get(i));
                    //有这个字段
                    if (!Objects.isNull(declaredField)) {
                        if (Objects.isNull(stock)) {
                            stock = (T) t.newInstance();
                        }

                        declaredField.setAccessible(true);
                        declaredField.set(stock, item.get(i));
                    }
                } catch (Exception e) {
                    log.warn("请求接口 ：{} 解析数据失败, response:{}，", TuShareURLConstants.STOCK_BASIC_URL, item);
                }
            }

            if (!Objects.isNull(stock)) {
                stockBasics.add(stock);
            }
        }

        StockBasicPageDTO<T> tuShareStockBasicPageDTO = new StockBasicPageDTO();
        tuShareStockBasicPageDTO.setHas_more(tuShareStockBasicResponseDTO.getData().isHas_more());
        tuShareStockBasicPageDTO.setTuShareStockBasics(stockBasics);
        return tuShareStockBasicPageDTO;
    }
}
