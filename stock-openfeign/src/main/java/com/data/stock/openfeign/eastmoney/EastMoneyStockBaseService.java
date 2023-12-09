package com.data.stock.openfeign.eastmoney;

import com.data.stock.openfeign.eastmoney.domain.EastLatestStockDTO;

import java.util.List;

public interface EastMoneyStockBaseService {

    /**
     * 获取全量最新股票信息
     * @return
     */
    List<EastLatestStockDTO> getLatestAllStockInfo();

    /**
     * 搜索最新的股票信息
     * @param stockName
     * @return
     */
    EastLatestStockDTO getLatestStockInfo(String stockName);
}
