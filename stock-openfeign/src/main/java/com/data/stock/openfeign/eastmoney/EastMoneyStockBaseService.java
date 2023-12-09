package com.data.stock.openfeign.eastmoney;

import com.data.stock.openfeign.eastmoney.domain.EastDTPoolDTO;
import com.data.stock.openfeign.eastmoney.domain.EastLatestStockDTO;
import com.data.stock.openfeign.eastmoney.domain.EastZBPoolDTO;
import com.data.stock.openfeign.eastmoney.domain.EastZTPoolDTO;

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

    /**
     * 查询涨停板数据
     * @param tradeDate
     */
    List<EastZTPoolDTO.ZTPoolDTO> getTopicZTPool(String tradeDate);

    /**
     * 查询跌停板数据
     * @param tradeDate
     */
    List<EastDTPoolDTO.DTPoolDTO> getTopicDTPool(String tradeDate);

    /**
     * 查询炸板板数据
     * @param tradeDate
     */
    List<EastZBPoolDTO.ZBPoolDTO> getTopicZBPool(String tradeDate);
}
