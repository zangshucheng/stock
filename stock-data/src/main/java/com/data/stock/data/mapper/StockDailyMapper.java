package com.data.stock.data.mapper;

import com.data.stock.data.domain.StockDaily;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author zangshucheng
* @description 针对表【stock_daily】的数据库操作Mapper
* @createDate 2023-11-07 21:53:55
* @Entity com.data.stock.data.domain.StockDaily
*/
public interface StockDailyMapper extends BaseMapper<StockDaily> {

    void deletebyTradeDate(@Param("tradeDate") String tradeDate);
}




