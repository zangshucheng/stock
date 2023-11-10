package com.data.stock.service.base.handler;

import com.data.stock.service.base.domain.StockBasicBO;

import java.util.List;

public interface StockBaseHandler {

    /**
     * 获取股票信息
     * @return
     */
    List<StockBasicBO> stockBase();
}
