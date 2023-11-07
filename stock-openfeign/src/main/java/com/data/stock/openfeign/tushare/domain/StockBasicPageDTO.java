package com.data.stock.openfeign.tushare.domain;

import com.data.stock.openfeign.tushare.domain.StockBasicDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class StockBasicPageDTO implements Serializable {

    private List<StockBasicDTO> tuShareStockBasics;

    /**
     * 后面是否还存在数据
     */
    private boolean has_more;
}
