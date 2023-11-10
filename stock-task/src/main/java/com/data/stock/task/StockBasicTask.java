package com.data.stock.task;

import com.data.stock.common.constant.MagicNumberConstants;
import com.data.stock.common.utils.DateUtil;
import com.data.stock.data.domain.StockBase;
import com.data.stock.data.service.StockBaseService;
import com.data.stock.openfeign.tushare.BasicDataService;
import com.data.stock.openfeign.tushare.domain.StockBasicPageDTO;
import com.data.stock.openfeign.tushare.domain.StockBasicDTO;
import com.data.stock.openfeign.tushare.domain.StockBasicQueryDTO;
import com.data.stock.openfeign.tushare.domain.TradeCalendarQueryDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component("stockBasicTask")
@Slf4j
public class StockBasicTask implements StockTask{

    @Autowired
    private BasicDataService basicDataService;

    @Autowired
    private StockBaseService service;

//    @Scheduled
    public void execute() {
        List<StockBasicDTO> tuShareStockBasics = new ArrayList<>();
        int offset = MagicNumberConstants.STOCK_BASIC_OFFSET_START;
        StockBasicPageDTO tuShareStockBasicPageDTO = null;
        do {
            //取数据
            tuShareStockBasicPageDTO = basicDataService.stockBasic(new StockBasicQueryDTO(MagicNumberConstants.STOCK_BASIC_LIMIT, offset));

            if(Objects.isNull(tuShareStockBasicPageDTO) || CollectionUtils.isEmpty(tuShareStockBasicPageDTO.getTuShareStockBasics())){
                break;
            }
            tuShareStockBasics.addAll(tuShareStockBasicPageDTO.getTuShareStockBasics());
            offset += MagicNumberConstants.STOCK_BASIC_LIMIT;
        }while (!Objects.isNull(tuShareStockBasicPageDTO) && tuShareStockBasicPageDTO.isHas_more());

        //数据插入
        if(!CollectionUtils.isEmpty(tuShareStockBasics)){
            List<StockBase> stockBaseList = tuShareStockBasics.stream().map(basic -> {
                StockBase stockBase = new StockBase();
                stockBase.setTsCode(basic.getTs_code());
                stockBase.setStockName(basic.getName());
                stockBase.setStockCode(basic.getSymbol());
                stockBase.setArea(basic.getArea());
                stockBase.setIndustry(basic.getIndustry());
                stockBase.setFullName(basic.getFullname());
                stockBase.setEnName(basic.getEnname());
                stockBase.setCnSpell(basic.getCnspell());
                stockBase.setMarket(basic.getMarket());
                stockBase.setStatus(basic.getList_status());
                stockBase.setIsHs(basic.getIs_hs());
                return stockBase;
            }).collect(Collectors.toList());
            service.saveOrUpdateBatch(stockBaseList);
        }
    }
}
