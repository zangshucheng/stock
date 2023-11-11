package com.data.stock.service.base.impl;

import com.data.stock.data.domain.StockBase;
import com.data.stock.data.service.StockBaseService;
import com.data.stock.service.base.BaseStockService;
import com.data.stock.service.base.domain.StockBasicBO;
import com.data.stock.service.base.handler.StockBaseHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BaseStockServiceImpl implements BaseStockService {

    @Autowired
    private StockBaseHandler tuShareBaseHandler;

    @Autowired
    private StockBaseService baseService;

    @Override
    public void stockBase() {
        List<StockBasicBO> stockBasicBOS = tuShareBaseHandler.stockBase();

        if(CollectionUtils.isEmpty(stockBasicBOS)){
            log.warn("未取到股票信息！");
        }

        List<StockBase> stockBases = stockBasicBOS.stream().map(StockBasicBO::buildStockBase).collect(Collectors.toList());
        baseService.replaceInto(stockBases);
    }
}
