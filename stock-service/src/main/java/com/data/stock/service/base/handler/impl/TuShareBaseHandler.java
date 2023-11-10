package com.data.stock.service.base.handler.impl;

import com.data.stock.common.constant.MagicNumberConstants;
import com.data.stock.openfeign.tushare.TuSahreBasicDataService;
import com.data.stock.openfeign.tushare.domain.TuSahreStockBasicDTO;
import com.data.stock.openfeign.tushare.domain.TuSahreStockBasicPageDTO;
import com.data.stock.openfeign.tushare.domain.TuSahreStockBasicQueryDTO;
import com.data.stock.service.base.domain.StockBasicBO;
import com.data.stock.service.base.handler.StockBaseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service("tuShareBaseHandler")
public class TuShareBaseHandler implements StockBaseHandler {

    @Autowired
    private TuSahreBasicDataService basicDataService;

    @Override
    public List<StockBasicBO> stockBase() {

        List<TuSahreStockBasicDTO> tuShareStockBasics = new ArrayList<>();
        int offset = MagicNumberConstants.STOCK_BASIC_OFFSET_START;
        TuSahreStockBasicPageDTO tuShareTuSahreStockBasicPageDTO = null;
        do {
            //取数据
            tuShareTuSahreStockBasicPageDTO = basicDataService.stockBasic(new TuSahreStockBasicQueryDTO(MagicNumberConstants.STOCK_BASIC_LIMIT, offset));

            if(Objects.isNull(tuShareTuSahreStockBasicPageDTO) || CollectionUtils.isEmpty(tuShareTuSahreStockBasicPageDTO.getTuShareStockBasics())){
                break;
            }
            tuShareStockBasics.addAll(tuShareTuSahreStockBasicPageDTO.getTuShareStockBasics());
            offset += MagicNumberConstants.STOCK_BASIC_LIMIT;
        }while (!Objects.isNull(tuShareTuSahreStockBasicPageDTO) && tuShareTuSahreStockBasicPageDTO.isHas_more());

        //数据插入
        if(!CollectionUtils.isEmpty(tuShareStockBasics)){
            List<StockBasicBO> stockBaseList = tuShareStockBasics.stream().map(basic -> {
                StockBasicBO stockBase = new StockBasicBO();
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

            return stockBaseList;
        }

        return null;
    }
}
