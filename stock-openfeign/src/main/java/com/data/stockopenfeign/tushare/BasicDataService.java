package com.data.stockopenfeign.tushare;

import com.data.stockopenfeign.tushare.domain.TuShareStockBasicQueryDTO;

/**
 * 获取股票基础数据
 * 参考：https://tushare.pro/document/2?doc_id=25
 */
public interface BasicDataService {

    /**
     *  获取股票列表
     * @param requestDTO
     * @return
     */
    TuShareStockBasicPageDTO stockBasic(TuShareStockBasicQueryDTO requestDTO);
}
