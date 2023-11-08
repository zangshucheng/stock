package com.data.stock.data.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.data.stock.data.mapper.StockBaseMapper;
import com.data.stock.data.domain.StockBase;
import com.data.stock.data.service.StockBaseService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
        this.baseMapper.replaceInto(stockBaseList);
    }

    public Map<String, StockBase> selectStockCodeMap(){
        List<StockBase> stockBases = this.lambdaQuery().select(StockBase::getTsCode, StockBase::getStockCode, StockBase::getStockName).list();
        if(!CollectionUtils.isEmpty(stockBases)){
            return stockBases.stream().collect(Collectors.toMap(StockBase::getTsCode, Function.identity()));
        }

        return null;
    }
}




