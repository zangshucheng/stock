package com.data.stockstarter.stocktask;

import com.data.stockdata.domain.StockBase;
import com.data.stockdata.service.StockBaseService;
import com.data.stockopenfeign.tushare.BasicDataService;
import com.data.stockopenfeign.tushare.TuShareStockBasicPageDTO;
import com.data.stockopenfeign.tushare.domain.TuShareStockBasicDTO;
import com.data.stockopenfeign.tushare.domain.TuShareStockBasicQueryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class StockBasicTask implements StockTask{

    /**
     * 每次取数据的条数
     */
    public static final int STOCK_BASIC_LIMIT = 10000;

    /**
     * 其实查询位置
     */
    public static final int STOCK_BASIC_OFFSET_START = 0;

    @Autowired
    private BasicDataService basicDataService;

    @Autowired
    private StockBaseService service;

    @Scheduled
    public void execute() {

        List<TuShareStockBasicDTO> tuShareStockBasics = new ArrayList<>();
        int offset = STOCK_BASIC_OFFSET_START;
        TuShareStockBasicPageDTO tuShareStockBasicPageDTO = null;
        do {
            //取数据
            tuShareStockBasicPageDTO = basicDataService.stockBasic(new TuShareStockBasicQueryDTO(STOCK_BASIC_LIMIT, offset));

            if(Objects.isNull(tuShareStockBasicPageDTO) || CollectionUtils.isEmpty(tuShareStockBasicPageDTO.getTuShareStockBasics())){
                break;
            }
            tuShareStockBasics.addAll(tuShareStockBasicPageDTO.getTuShareStockBasics());
            offset += STOCK_BASIC_LIMIT;
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
            service.replaceInto(stockBaseList);
        }
    }
}
