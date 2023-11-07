package com.data.stock.data.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.data.stock.data.domain.StockDaily;
import com.data.stock.data.service.StockDailyService;
import com.data.stock.data.mapper.StockDailyMapper;
import org.springframework.stereotype.Service;

/**
* @author zangshucheng
* @description 针对表【stock_daily】的数据库操作Service实现
* @createDate 2023-11-07 21:53:55
*/
@Service
public class StockDailyServiceImpl extends ServiceImpl<StockDailyMapper, StockDaily>
    implements StockDailyService{

    @Override
    public void deletebyTradeDate(String tradeDate) {
        this.baseMapper.deletebyTradeDate(tradeDate);
    }
}




