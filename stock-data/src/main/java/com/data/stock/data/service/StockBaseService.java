package com.data.stock.data.service;

import com.data.stock.data.domain.StockBase;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author zangshucheng
* @description 针对表【stock_base(股票基础信息)】的数据库操作Service
* @createDate 2023-11-05 12:05:38
*/
public interface StockBaseService extends IService<StockBase> {

    /**
     * 根据唯一主键：stock_code 进行插入更新
     * @param stockBaseList
     */
    void replaceInto(List<StockBase> stockBaseList);
}
