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
        StockBasicRequestDTO request = new StockBasicRequestDTO(TuShareURLConstants.STOCK_BASIC_FIELDS);
        request.setParams(queryDTO);
        request.setToken(tuShareToken);
        request.setApi_name(TuShareURLConstants.STOCK_BASIC_URL);
        String response = HttpUtil.post(tuShareURL, JSONUtil.toJsonStr(request));

        if (Objects.isNull(request)) {
            log.warn("请求接口 ：{} 失败，未获取到任何响应", TuShareURLConstants.STOCK_BASIC_URL);
            return null;
        }

        TuShareStockBasicResponseDTO tuShareStockBasicResponseDTO = JSONUtil.toBean(response, TuShareStockBasicResponseDTO.class);

        if (Objects.isNull(tuShareStockBasicResponseDTO) || tuShareStockBasicResponseDTO.getCode() != 0) {
            log.warn("请求接口 ：{} 失败, response:{}，", TuShareURLConstants.STOCK_BASIC_URL, tuShareStockBasicResponseDTO);
            return null;
        }

        List<String> fields = tuShareStockBasicResponseDTO.getData().getFields();
        List<List<String>> items = tuShareStockBasicResponseDTO.getData().getItems();

        if (CollectionUtils.isEmpty(fields) || CollectionUtils.isEmpty(items)) {
            log.warn("请求接口 ：{} 数据存在问题, response:{}，", TuShareURLConstants.STOCK_BASIC_URL, tuShareStockBasicResponseDTO);
            return null;
        }

        List<StockBasicDTO> stockBasics = new ArrayList<>();

        for (List<String> item : items) {
            StockBasicDTO tuShareStockBasicDTO = null;
            for (int i = 0; i < fields.size(); i++) {
                try {
                    Field declaredField = StockBasicDTO.class.getDeclaredField(fields.get(i));
                    //有这个字段
                    if (!Objects.isNull(declaredField)) {
                        if (Objects.isNull(tuShareStockBasicDTO)) {
                            tuShareStockBasicDTO = new StockBasicDTO();
                        }

                        declaredField.setAccessible(true);
                        declaredField.set(tuShareStockBasicDTO, item.get(i));
                    }
                } catch (Exception e) {
                    log.warn("请求接口 ：{} 解析数据失败, response:{}，", TuShareURLConstants.STOCK_BASIC_URL, tuShareStockBasicResponseDTO);
                }
            }

            if (!Objects.isNull(tuShareStockBasicDTO)) {
                stockBasics.add(tuShareStockBasicDTO);
            }
        }

        StockBasicPageDTO tuShareStockBasicPageDTO = new StockBasicPageDTO();
        tuShareStockBasicPageDTO.setHas_more(tuShareStockBasicResponseDTO.getData().isHas_more());
        tuShareStockBasicPageDTO.setTuShareStockBasics(stockBasics);
        return tuShareStockBasicPageDTO;
    }

    private <T> List<T> getDataObjectResponse(Class t, CommonResponseDataDTO data) {
        List<T> stockDatas = new ArrayList<>();

        for (List<String> item : data.getItems()) {
            T stock = null;
            for (int i = 0; i < data.getFields().size(); i++) {
                try {
                    Field declaredField = StockBasicDTO.class.getDeclaredField(data.getFields().get(i));
                    //有这个字段
                    if (!Objects.isNull(declaredField)) {
                        if (Objects.isNull(stock)) {
                            stock = (T) t.getClass().newInstance();
                        }

                        declaredField.setAccessible(true);
                        declaredField.set(stock, item.get(i));
                    }
                } catch (Exception e) {
                    log.warn("请求接口 ：{} 解析数据失败, response:{}，", TuShareURLConstants.STOCK_BASIC_URL, data);
                }
            }

            if (!Objects.isNull(stock)) {
                stockDatas.add(stock);
            }
        }

        return stockDatas;
    }

    @Override
    public TradeCalendarPageDTO tradeCalendar(TradeCalendarQueryDTO requestDTO) {
        StockBasicRequestDTO request = new StockBasicRequestDTO();
        request.setParams(queryDTO);
        request.setToken(tuShareToken);
        request.setApi_name(TuShareURLConstants.STOCK_BASIC_URL);
        String response = HttpUtil.post(tuShareURL, JSONUtil.toJsonStr(request));

        String response = HttpUtil.post(tuShareURL, JSONUtil.toJsonStr(request));

        if (Objects.isNull(request)) {
            log.warn("请求接口 ：{} 失败，未获取到任何响应", TuShareURLConstants.STOCK_BASIC_URL);
            return null;
        }

        return null;
    }
}
