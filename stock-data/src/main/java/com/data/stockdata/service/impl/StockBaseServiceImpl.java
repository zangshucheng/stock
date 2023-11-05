package com.data.stockdata.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.data.stockdata.domain.StockBase;
import com.data.stockdata.service.StockBaseService;
import com.data.stockdata.mapper.StockBaseMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author zangshucheng
* @description 针对表【stock_base(股票基础信息)】的数据库操作Service实现
* @createDate 2023-11-05 12:05:38
*/
@Service
public class StockBaseServiceImpl extends ServiceImpl<StockBaseMapper, StockBase>
    implements StockBaseService{

    @Override
    public void replaceInto(List<StockBase> stockBaseList) {

    }
}




