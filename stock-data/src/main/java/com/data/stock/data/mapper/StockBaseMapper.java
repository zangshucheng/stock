package com.data.stock.data.mapper;

import com.data.stock.data.domain.StockBase;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author zangshucheng
* @description 针对表【stock_base(股票基础信息)】的数据库操作Mapper
* @createDate 2023-11-05 12:05:38
* @Entity com.stock.stockdata.domain.StockBase
*/
public interface StockBaseMapper extends BaseMapper<StockBase> {

    /**
     * 根据唯一主键：stock_code 进行插入更新
     * @param stockBaseList
     */
    void replaceInto(@Param("stockBaseList") List<StockBase> stockBaseList);
}




